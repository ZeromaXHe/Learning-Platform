# 第14章 构建自定义的同步工具

## 14.4 Synchronizer剖析

在ReentrantLock和Semaphore这两个接口之间存在许多共同点。这两个类都可以用做一个“阀门”，即每次只允许一定量的线程通过，并当线程到达阀门时，可以通过（在调用lock或acquire时成功返回），也可以等待（在调用lock或acquire时阻塞），还可以取消（在调用tryLock或tryAcquire时返回“假”，表示在指定的时间内锁是不可用的或者无法获得许可）。而且，这两个接口都支持可中断的、不可中断的以及限时的获取操作，并且也都支持等待线程执行公平或非公平的队列操作。

列出了这种共性后，你或许会认为Semaphore是基于ReentrantLock实现的，或者认为ReentrantLock实际上是带有一个许可的Semaphore。这些实现方式都是可行的，一个很常见的练习就是，证明可以通过锁来实现计数信号量（如程序清单14-12中的SemaphoreOnLock所示），以及可以通过计数信号量来实现锁。

~~~java
// 并非 java.util.concurrent.Semaphore的真实实现方式
@ThreadSafe
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    // 条件谓词：permitsAvailable(permits > 0)
    private final Condition permitsAvailable = lock.newCondition();
    @GuardedBy("lock")
    private int permits;
    
    SemaphoreOnLock(int initialPermits) {
        lock.lock();
        try {
            permits = initialPermits;
        } finally {
            lock.unlock();
        }
    }
    
    // 阻塞并直到：permitsAvailable
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while(permits <= 0){
                permitsAvailable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }
    
    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
~~~

事实上，它们在实现时都使用了一个共同的基类，即AbstractQueuedSynchronizer（AQS），这个类也是其他许多同步类的基类。AQS是一个用于构建锁和同步器的框架，许多同步器都可以通过AQS很容易并且高效地构造出来。不仅ReentrantLock和Semaphore是基于AQS构建的，还包括CountDownLatch、ReentrantReadWriteLock、SynchronousQueue和FutureTask。

AQS解决了在实现同步器时涉及的大量细节问题，例如等待线程采用FIFO队列操作顺序。在不同的同步器中还可以定义一些灵活的标准来判断某个线程是应该通过还是需要等待。

基于AQS来构建同步器能带来许多好处。它不仅能极大地减少实现工作，而且也不必处理在多个位置上发生的竞争问题（这是在没有使用AQS来构建同步器时的情况）。在SemaphoreOnLock中，获取许可的操作可能在两个时刻阻塞——当锁保护信号量状态时，以及当许可不可用时。在基于AQS构建的同步器中，只可能在一个时刻发生阻塞，从而降低上下文切换的开销，并提高吞吐量。在设计AQS时充分考虑了可伸缩性，因此java.util.concurrent中所有基于AQS构建的同步器都能获得这个优势。

## 14.5 AbstractQueuedSynchronizer

大多数开发者都不会直接使用AQS，标准同步器类的集合能够满足绝大多数情况的需求。但如果能了解标准同步器类的实现方式，那么对于理解它们的工作原理是非常有帮助的。

在基于AQS构建的同步器类中，最基本的操作包括各种形式的获取操作和释放操作。获取操作是一种依赖状态的操作，并且通常会阻塞。当使用锁或信号量时，“获取”操作的含义就很直观，即获取的是锁或者许可，并且调用者可能会一直等到直到同步器类处于可被获取的状态。在使用CountDownLatch时，“获取”操作意味着“等待并直到闭锁达到结束状态”，而使用FutureTask时，则意味着“等待并直到任务已经完成”。“释放”并不是一个可阻塞的操作，当执行“释放”操作时，所有在请求时被阻塞的线程都会开始执行。

如果一个类想成为状态依赖的类，那么它必须拥有一些状态。AQS负责管理同步器类中的状态，它管理了一个整数状态信息，可以通过getState，setState以及compareAndSetState等protected类型方法来进行操作。这个整数可以用于表示任意状态。例如，ReentrantLock用它来表示所有者线程已经重复获取该锁的次数，Semaphore用它来表示剩余的许可数量，FutureTask用它来表示任务的状态（尚未开始、正在运行、已完成以及已取消）。在同步器类中还可以自行管理一些额外的状态变量，例如，ReentrantLock保存了锁的当前所有者的信息，这样就能区分某个获取操作是重入的还是竞争的。

程序清单14-13给出了AQS中的获取操作与释放操作的形式。根据同步器的不同，获取操作可以是一种独占操作（例如ReentrantLock），也可以是一个非独占操作（例如Semaphore和CountDownLatch）。一个获取操作包括两部分。首先，同步器判断当前状态是否允许获得操作，如果是，则允许线程执行，否则获取操作将阻塞或失败。这种判断是由同步器的语义决定的。例如，对于锁来说，如果它没有被某个线程持有，那么就能被成功地获取，而对于闭锁来说，如果它处于结束状态，那么也能被成功地获取。

~~~java
boolean acquire() throws InterruptedException {
    while(当前状态不允许获取操作) {
        if(需要阻塞获取请求) {
            如果当前线程不在队列中，则将其插入队列;
            阻塞当前线程;
        } else {
            返回失败;
        }
    }
    可能更新同步器的状态;
    如果线程位于队列中，则将其移出队列;
    返回成功;
}

void release() {
    更新同步器的状态;
    if(新的状态允许某个被阻塞的线程获取成功) {
        解除队列中一个或多个线程的阻塞状态
    }
}
~~~

其次，就是更新同步器的状态，获取同步器的某个线程可能会对其他线程能否也获取该同步器造成影响。例如，当获取一个锁后，锁的状态将从“未被持有”变成“已被持有”，而从Semaphore中获取一个许可后，将把剩余许可的数量减1。然而，当一个线程获取闭锁时，并不会影响其他线程能否获取它，因此获取闭锁的操作不会改变闭锁的状态。

如果某个同步器支持独占的获取操作，那么需要实现一些保护方法，包括tryAcquire、tryRelease和isHeldExclusively等，而对于支持共享获取的同步器，则应该实现tryAcquireShared和tryReleaseShared等方法。AQS中的acquire、acquireShared、release和releaseShared等方法都将调用这些方法在子类中带有前缀try的版本来判断某个操作是否能执行。在同步器的子类中，可以根据其获取操作和释放操作的语义，使用getState、setState以及compareAndSetState来检查和更新状态，并通过返回的状态值来告知基类“获取”或“释放”同步器的操作是否成功。例如，如果tryAcquireShared返回一个负值，那么表示获取操作失败，返回零值表示同步器通过独占方式被获取，返回正值则表示同步器通过非独占方式被获取。对于tryRelease和tryReleaseShared方法来说，如果释放操作使得所有在获取同步器时被阻塞的线程恢复执行，那么这两个方法应该返回true。

为了使支持条件队列的锁（例如ReentrantLock）实现起来更简单，AQS还提供了一些机制来构造与同步器相关联的条件变量。

### 一个简单的闭锁

程序清单14-14中的OneShotLatch是一个使用AQS实现的二元闭锁。它包含两个共有方法：await和signal，分别对应获取操作和释放操作。起初，闭锁是关闭的，任何调用await的线程都将阻塞并直到闭锁被打开。当通过调用signal打开闭锁时，所有等待中的线程都将被释放，并且随后到达闭锁的线程也被允许执行。

~~~java
@ThreadSafe
public class oneShotLatch {
    private final Sync sync = new Sync();
    
    public void signal() {
        sync.releaseShared(0);
    }
    
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }
    
    private class Sync extends AbstractQueuedSynchronizer {
        protected int tryAcquireShared(int ignored) {
            // 如果闭锁是开的（state == 1），那么这个操作将成功，否则将失败
            return (getState() == 1) ? 1: -1;
        }
        
        protected boolean tryReleaseShared(int ignored) {
            setState(1); // 现在打开闭锁
            return true; // 现在其他的线程可以获取该闭锁
        }
    }
}
~~~

在OneShotLatch中，AQS状态用来表示闭锁状态——关闭（0）或者打开（1）。await方法调用AQS的acquireSharedInterruptibly，然后接着调用OneShotLatch中的tryAcquireShared方法。在tryAcquireShared 的实现中必须返回一个值来表示该获取操作能否执行。如果之前已经打开了闭锁，那么tryAcquireShared将返回成功并允许线程通过，否则就会返回一个表示获取操作失败的值。acquireSharedInterruptibly方法在处理失败的方式，是把这个线程放入等待队列中。类似地，signal将调用releaseShared，接下来又会调用tryReleaseShared。在tryReleaseShared中将无条件地把闭锁的状态设置为打开，（通过返回值）表示该同步器处于完全被释放的状态。因为AQS让所有等待中的线程都尝试重新请求该同步器，并且由于tryAcquireShared将返回成功，因此现在的请求操作将成功。

OneShotLatch是一个功能全面的、可用的、性能较好的同步器，并且仅使用了大约20多行代码就实现了。当然，它缺少了一些有用的特性，例如限时的请求操作以及检查闭锁的状态，但这些功能实现起来同样很容易，因为AQS提供了限时版本的获取方法，以及一些在常见检查中使用的辅助方法。

oneShotLatch也可以通过扩展AQS来实现，而不是将一些功能委托给AQS，但这种做法并不合理，原因有很多。这样做将破坏OneShotLatch接口（只有两个方法）的简洁性，并且虽然AQS的公共方法不允许调用者破坏闭锁的状态，但调用者仍可以很容易地误用它们。java.util.concurrent中的所有同步器类都没有直接扩展AQS，而是都将它们的相应功能委托给私有的AQS子类来实现。

## 14.6 java.util.concurrent同步器类中的AQS

