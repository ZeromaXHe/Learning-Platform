# 第6章 任务执行

## 6.2 Executor框架

任务是一组逻辑工作单元，而线程则是使任务异步执行的机制。我们已经分析了两种通过线程来执行任务的策略，即把所有任务放在单个线程中串行执行，以及将每个任务放在各自的线程中执行。这两种方式都存在一些严格的限制：串行执行的问题在于其糟糕的响应性和吞吐量，而“为每个任务分配一个线程”的问题在于资源管理的复杂性。

在第5章中，我们介绍了如何通过有界队列来防止高负荷的应用程序耗尽内存。线程池简化了线程的管理工作，并且java.util.concurrent提供了一种灵活的线程池实现作为Executor框架的一部分。在Java类库中，任务执行的主要抽象不是Thread，而是Executor，如程序清单6-3所示。

~~~java
public interface Executor {
    void execute(Runnable command);
}
~~~

虽然Executor是个简单的接口，但它却为灵活且强大的异步任务执行框架提供了基础，该框架能支持多种不同类型的任务执行策略。它提供了一种标准的方法将任务的提交过程与执行过程解耦开来，并用Runnable来表示任务。Executor的实现还提供了对生命周期的支持，以及统计信息收集、应用程序管理机制和性能监视等机制。

Executor基于生产者-消费者模式，提交任务的操作相当于生产者（生成待完成的工作单元），执行任务的线程则相当于消费者（执行完这些工作单元）。如果要在程序中实现一个生产者-消费者的设计，那么最简单的方式通常就是使用Executor。

### 6.2.1 示例：基于Executor的Web服务器

### 6.2.2 执行策略

通过将任务的提交与执行解耦开来，从而无须太大的困难就可以为某种类型的任务指定和修改执行策略。在执行策略中定义了任务执行的“What、Where、When、How”等方面。

各种执行策略都是一种资源管理工具，最佳策略取决于可用的计算资源以及对服务质量的需求。通过限制并发任务的数量，可以确保应用程序不会由于资源耗尽而失败，或者由于在稀缺资源上发生竞争而严重影响性能。通过将任务的提交与任务的执行策略分离开来，有助于在部署阶段选择与可用硬件资源最匹配的执行策略。

### 6.2.3 线程池

线程池，从字面含义来看，是指管理一组同构工作线程的资源池。线程池是工作队列（Work Queue）密切相关的，其中在工作队列中保存了所有等待执行的任务。工作者线程（Worker Thread）的任务很简单：从工作队列中获取一个任务，执行任务，然后返回线程池并等待下一个任务。

“在线程池中执行任务”比“为每个任务分配一个线程”优势更多。通过重用现有的线程而不是创建新线程，可以在处理多个请求时分摊在线程创建和销毁过程中产生的巨大开销。另一个额外的好处是，当请求到达时，工作线程通常已经存在，因此不会由于等待创建线程而延迟任务的执行，从而提高了响应性。通过适当调整线程池的大小，可以创建足够多的线程以便使处理器保持忙碌状态，同时还可以防止过多多线程相互竞争资源而使应用程序耗尽内存或失败。

类库提供了一个灵活的线程池以及一些有用的默认配置。可以通过调用Executors中的静态工厂方法之一来创建一个线程池：

**newFixedThreadPool**。newFixedThreadPool将创建一个固定长度的线程池，每当提交一个任务时就创建一个线程，直到达到线程池的最大数量，这时线程池的规模将不再变化（如果某个线程由于发生了未预期的Exception而结束，那么线程池会补充一个新的线程）。

**newCachedThreadPool**。newCachedThreadPool将创建一个可缓存的线程池，如果线程池的当前规模超过了处理需求时，那么将回收空闲的线程，而当需求增加时，则可以添加新的线程，线程池的规模不存在任何限制。

**newSingleThreadExecutor**。newSingleThreadExecutor是一个单线程的Executor，它创建单个工作者线程来执行任务，如果这个线程异常结束，会创建另一个线程来替代。newSingleThreadExecutor能确保依照任务在队列中的顺序来串行执行（例如FIFO、LIFO、优先级）。

**newScheduledThreadPool**。newScheduledThreadPool创建了一个固定长度的线程池，而且以延迟或定时的方式来执行任务，类似于Timer（参见6.2.5节）。

newFixedThreadPool和newCachedThreadPool这两个工厂方法返回通用的ThreadPoolExecutor实例，这些实例可以直接用来构造专门用途的executor。我们将在第8章深入讨论线程池的各个配置选项。

从“为每任务分配一个线程”策略变成基于线程池的策略，将对应用程序的稳定性产生重大的影响：Web服务器不会再在高负载情况下失败（尽管服务器不会因为创建了过多的线程而失败，但在足够长的时间内，如果任务到达的速度总是超过任务执行的速度，那么服务器仍有可能（只是更不易）耗尽内存，因为等待执行的Runnable队列将不断增长。可以通过使用一个有界工作队列在Executor框架内部解决这个问题）。由于服务器不会创建数千个线程来争夺有限的CPU和内存资源，因此服务器的性能将平缓地降低。通过使用Executor，可以实现各种调优、管理、监视、记录日志、错误报告和其他功能，如果不使用任务执行框架，那么要增加这些功能是非常困难的。

### 6.2.4 Executor的生命周期

我们已经知道如何创建一个Executor，但并没有讨论如何关闭它。Executor的实现通常会创建线程来执行任务。但JVM只有在所有（非守护）线程全部终止后才会退出。因此，如果无法正确地关闭Executor，那么JVM将无法结束。

由于Executor以异步方式来执行任务，因此在任何时刻，之前提交任务的状态不是立即可见的。有些任务可能已经完成，有些可能正在运行，而其他的任务可能在队列中等待执行。当关闭应用程序时，可能采用最平缓的关闭形式（完成所有已经启动的任务，并且不再接收任何新的任务），也可能采用最粗暴的关闭形式（直接关掉机房的电源），以及其他各种可能的形式。既然Executor是为应用程序提供服务的，因此它们也是可关闭的（无论采用平缓的方式还是粗暴的方式），并将在关闭操作中受影响的任务的状态反馈给应用程序。

为了解决执行服务的生命周期问题，Executor扩展了ExecutorService接口，添加了一些用于生命周期管理的方法（同时还有一些用于任务提交的便利方法）。在程序清单6-7中给出了ExecutorService中的生命周期管理方法。

~~~java
public interface ExecutorService extends Executor {
    void shutdown();
    List<Runnable> shutdownNow();
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout, TimeUnit unit) throw InterruptedException;
    // …… 其他用于任务提交的便利方法
}
~~~

ExecutorService的生命周期有3种状态：运行、关闭和已终止。ExecutorService在初始创建时处于运行状态。shutdown方法将执行平缓的关闭过程：不再接受新的任务，同时等待已经提交的任务执行完成——包括那些还未开始执行的任务。shutdownNow方法将执行粗暴的关闭过程：它将尝试取消所有运行中的任务，并且不再启动队列中尚未开始执行的任务。

在ExecutorService关闭后提交的任务将由“拒绝执行处理器（Rejected Execution Handler）”来处理（请参见8.3.3节），它会抛弃任务，或者使得execute方法抛出一个未检查的RejectedExecutionException。等所有任务都完成后，ExecutorService将转入终止状态。可以调用awaitTermination来等待ExecutorService到达终止状态，或者通过调用isTerminated来轮询ExecutorService是否已经终止。通常在调用awaitTermination之后会立即调用shutdown，从而产生同步地关闭ExecutorService的效果。（第7章将进一步介绍Executor的关闭和任务取消等方面的内容。）

### 6.2.5 延迟任务与周期任务

Timer类负责管理延迟任务（“在100ms后执行该任务”）以及周期任务（“每10ms执行一次该任务”）。然而，Timer存在一些缺陷，因此应该考虑使用ScheduledThreadPoolExecutor来代替它。可以通过ScheduledThreadPoolExecutor的构造函数或newScheduledThreadPool工厂方法来创建该类的对象。

Timer在执行所有定时任务时只会创建一个线程。如果某个任务的执行时间过长，那么将破坏其他TimerTask的定时精确性。例如某个周期TimerTask需要每10ms执行一次，而另一个TimerTask需要执行40ms，那么这个周期任务或者在40ms任务执行完成后快速连续地调用4次，或者彻底“丢失”4次调用（取决于它是基于固定速率来调度还是基于固定延时来调度）。线程池能弥补这个缺陷，它可以提供多个线程来执行延时任务和周期任务。

Timer的另一个问题是，如果TimerTask抛出了一个未检查的异常，那么Timer将表现出糟糕的行为。Timer线程并不捕获异常，因此当TimerTask抛出未检查的异常时将终止定时线程。这种情况下，Timer也不会恢复线程的执行，而是会错误地认为整个Timer都被取消了。因此，已经被调度但尚未执行的TimerTask将不会再执行，新的任务也不能被调用。（这个问题称之为“线程泄漏[Thread Leakage]”， 7.3节将介绍该问题以及如何避免它。）

在Java5.0或更高的JDK中，将很少使用Timer。

如果要构建自己的调度服务，那么可以使用DelayQueue，它实现了BlockingQueue，并为ScheduledThreadPoolExecutor提供调度功能。DelayQueue管理着一组Delayed对象。每个Delayed对象都有一个相应的延迟时间：在DelayQueue中，只有某个元素逾期后，才能从DelayQueue中执行take操作。从DelayQueue中返回的对象将根据它们的延迟时间进行排序。

## 6.3 找出可利用的并行性

### 6.3.2 携带结果的任务Callable与Future

Executor框架使用Runnable作为其基本的任务表现形式。Runnable是一种有很大局限的抽象，虽然run能写入到日志文件或者将结果放入某个共享的数据结构，但它不能返回一个值或抛出一个受检查的异常。

许多任务实际上都是存在延迟的计算——执行数据库查询，从网络上获取资源，或者计算某个复杂的功能。对于这些任务，Callable是一种更好的抽象：它认为主入口点（即call）将返回一个值，并可能抛出一个异常。（要使用Callable来表示无返回值的任务，可使用`Callable<Void>`）在Executor中包含了一些辅助方法能将其他类型的任务封装为一个Callable，例如Runnable和java.security.PrivilegedAction。

Runnable和Callable描述的都是抽象的计算任务。这些任务通常是有范围的，即都有一个明确的起始点，并且最终会结束。Executor执行的任务有4个生命周期阶段：创建、提交、开始和完成。由于有些任务可能要执行很长的时间，因此通常希望能够取消这些任务。在Executor框架中，已提交但尚未开始的任务可以取消，但对于那些已经开始执行的任务，只有当它们能响应中断时，才能取消。取消一个已经完成的任务不会有任何影响。（第7章将进一步介绍取消操作。）

Future表示一个任务的生命周期，并提供了相应的方法来判断是否已经完成或取消，以及获取任务的结果和取消任务等。在程序清单6-11中给出了Callable和Future。在Future规范中包含的隐含意义是，任务的生命周期只能前进，不能后退，就像ExecutorService的生命周期一样。当某个任务完成后，它就永远停留在“完成”状态上。

~~~java
public interface Callable<V> {
    V call() throws Exception;
}

public interface Future<V> {
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    V get() throws InterruptedException, ExecutionException, CancellationException;
    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, CancellationException, TimeoutException;
}
~~~

get方法的行为取决于任务的状态（尚未开始、正在运行、已完成）。如果任务已完成，那么get就会立即返回或者抛出一个Exception，如果任务没有完成，那么get将阻塞并直到任务完成。如果任务抛出了异常，那么get将该异常封装为ExecutionException并重新抛出。如果任务被取消，那么get将抛出CancellationException。如果get抛出了ExecutionException，那么可以通过getCause来获取被封装的初始异常。

可以通过许多方法创建一个Future来描述任务。ExecutionService中的所有submit方法都将返回一个Future，从而将一个Runnable或Callable提交给Executor，并得到一个Future用来获得任务的执行结果或者取消任务。还可以显式地为某个指定的Runnable或Callable实例化一个FutureTask。（由于FutureTask实现了Runnable，因此可以将它提交给Executor来执行，或者直接调用它的run方法。）

从Java6开始，ExecutorService实现可以改写AbstractExecutorService中的newTaskFor方法，从而根据已提交的Runnable或Callable来控制Future的实例化过程。在默认实现中仅创建了一个新的FutureTask，如程序清单6-12所示。

~~~java
protected <T> RunnableFuture<T> newTaskFor(Callable<T> task) {
    return new FutureTask<T>(task);
}
~~~

在将Runnable或Callable提交到Executor的过程中，包含了一个安全发布过程（请参见3.5节），即将Runnable或Callable从提交线程发布到最终执行任务的线程。类似地，在设置Future结果的过程中也包含了一个安全发布，即将这个结果从计算它的线程发布到任何通过get获得它的线程。

### 6.3.4 在异构任务并行化中存在的局限

只有当大量相互独立且同构的任务可以并发进行处理时，才能体现出将程序的工作负载分配到多个任务中带来的真正性能提升。

### 6.3.5 CompletionService：Executor与BlockingQueue

如果向Executor提交了一组计算任务，并且希望在计算完成后获得结果，那么可以保留与每个任务关联的Future，然后反复使用get方法，同时将参数timeout指定为0，从而通过轮询来判断任务是否完成。这种方法虽然可行，但却有些繁琐。幸运的是，还有一种更好的方法：完成服务（CompletionService）。

CompletionService将Executor和BlockingQueue的功能融合在一起。你可以将Callable任务提交给它来执行，然后使用类似于队列操作的take和poll等方法来获得已完成的结果，而这些结果会在完成时将被封装为Future。ExecutorCompletionService实现了CompletionService，并将计算部分委托给一个Executor。

ExecutorCompletionService的实现非常简单。在构造函数中创建一个BlockingQueue来保存计算完成的结果。当计算完成时，调用FutureTask中的done方法。当提交某个任务时，该任务将首先包装为一个QueueingFuture，这是FutureTask的一个子类，然后再改写子类的done方法，并将结果放入BlockingQueue中，如程序清单6-14所示。take和poll方法委托给了BlockingQueue，这些方法会在得出结果之前阻塞。

~~~java
private class QueueingFuture<V> extends FutureTask<V> {
    QueueingFuture(Callable<V> c) {
        super(c);
    }
    QueueingFuture(Runnable t, V r) {
        super(t, r);
    }
    
    protected void done() {
        completionQueue.add(this);
    }
}
~~~

### 6.3.7 为任务设置时限

有时候，如果某个任务无法在指定时间内完成，那么将不再需要它的结果，此时可以放弃这个任务。

在有限时间内执行任务的主要困难在于，要确保得到答案的时间不会超过限定的时间，或者在限定的时间内无法获得答案。在支持时间限制的Future.get中支持这种需求：当结果可用时，它将立即返回，如果在指定时限内没有计算出结果，那么将抛出TimeoutException。

在使用限时任务时需要注意，当这些任务超时后应该立即停止，从而避免为继续计算一个不再使用的结果而浪费计算资源。要实现这个功能，可以由任务本身来管理它的限定时间，并且在超时后中止执行或取消任务。此时可再次使用Future，如果一个限时get方法抛出了TimeoutException，那么可以通过Future来取消任务。如果编写的任务是可取消的（参见第7章），那么可以提前中止它，以免消耗过多的资源。在程序清单6-13和6-16的代码中使用了这项技术。

程序清单6-16给出了限时Future.get的一种典型应用。在它生成的页面中包括响应用户请求内容以及从广告服务器上获得的广告。它将获取广告的任务提交给一个Executor，然后计算剩余的文本页面内容，最后等待广告信息，直到超出指定的时间。（传递给get的timeout参数的计算方法是，将指定时限减去当前时间。这可能得到负数。但java.util.concurrent中所有与时限相关的方法都将负数视为0，因此不需要额外的代码来处理这种情况。）如果get超时，那么将取消广告获取任务，并转而使用默认的广告信息。

~~~java
Page renderPageWithAd() throws InterruptedException {
    long endNanos = System.nanoTime() + TIME_BUDGET;
    Future<Ad> f = exec.submit(new FetchAdTask());
    // 在等待广告的同时显示页面
    Page page = renderPageBody();
    Ad ad;
    try {
        // 只等待指定的时间长度
        long timeLeft = endNanos - System.nanoTime();
        ad = f.get(timeLeft, NANOSECONDS);
    } catch (ExecutionException e) {
        ad = DEFAULT_AD;
    } catch (TimeoutException e) {
        ad = DEFAULT_AD;
        f.cancel(true);
    }
    page.setAd(ad);
    return page;
}
~~~

# 第7章 取消与关闭

任务和线程的启动很容易。在大多数时候，我们都会让它们运行直到结束，或者让它们自行停止。然而，有时候我们希望提前结束任务或线程，或许是因为用户取消了操作，或者应用程序需要被快速关闭。

要使任务和线程能安全、快速、可靠地停止下来，并不是一件容易的事。Java没有提供任何机制来安全地终止线程。但它提供了中断（Interruption），这是一种协作机制，能够使一个线程终止另一个线程的当前工作。

这种协作式的方法是必要的，我们很少希望某个任务、线程或服务立即停止，因为这种立即停止会使共享的数据结构处于不一致的状态。相反，在编写任务和服务时可以使用一种协作的方式：当需要停止时，它们首先会清除当前正在执行的工作，然后再结束。这提供了更好的灵活性，因为任务本身的代码比发出取消请求的代码更清楚如何执行清除工作。

生命周期结束（End-of-Lifecycle）的问题会使任务、服务以及程序的设计和实现等过程变得复杂，而这个在程序设计中非常重要的要素却经常被忽略。一个在行为良好的软件与勉强运行的软件之间的最主要区别就是，行为良好的软件能很完善地处理失败、关闭和取消等过程。本章将给出各种实现取消和中断的机制，以及如何编写任务和服务，使它们能对取消请求做出响应。

## 7.1 任务取消

如果外部代码能在某个操作正常之前将其置入“完成”状态，那么这个操作就可以称为可取消的（Cancellable）。取消某个操作的原因很多：

**用户请求取消**。用户点击图形界面程序中的“取消”按钮，或者通过管理接口来发出取消请求，例如JMX（Java Management Exceptions）。

**有时间限制的操作**。例如，某个应用程序需要在有限时间内搜索问题空间，并在这个时间内选择最佳的解决方案。当计时器超时时，需要取消所有正在搜索的任务。

**应用程序事件**。例如，应用程序对某个问题空间进行分解并搜索，从而使不同的任务可以搜索问题空间中的不同区域。当其中一个任务找到了解决方案时，所有其他仍在搜索的任务都将被取消。

**错误**。网页爬虫程序搜索相关的页面，并将页面或摘要数据保存到硬盘。当一个爬虫任务发生错误时（例如，磁盘空间已满），那么所有搜索任务都会取消，此时可能会记录它们的当前状态，以便稍后重新启动。

**关闭**。当一个程序或服务关闭时，必须对正在处理和等待处理的工作执行某种操作。在平缓的关闭过程中，当前正在执行的任务将继续执行直到完成，而在立即关闭过程中，当前的任务则可能取消。

在Java中没有一种安全的抢占式方法来停止进程，因此也就没有安全的抢占式方法来停止任务。只有一些协作式的机制，使请求取消的任务和代码都遵循一种协商好的协议。

### 7.1.1 中断



# 第8章 线程池的使用

## 8.1 在任务与执行策略之间的隐性耦合

我们已经知道，Executor框架可以将任务的提交与任务的执行策略解耦开来。就像许多对复杂过程的解耦操作那样，这种论断多少有些言过其实了。虽然Executor框架为指定和修改执行策略都提供了相当大的灵活性，但并非所有的任务都能适用所有的执行策略。有些类型的任务需要明确地指定执行策略，包括：

**依赖性任务**。大多数行为正确的任务都是独立的：它们不依赖于其他任务的执行时序、执行结果或其他效果。当在线程池中执行独立的任务时，可以随意地改变线程池的大小和配置，这些修改只会对执行性能产生影响。然而，如果提交给线程池的任务需要依赖其他的任务，那么就隐含地给执行策略带来了约束，此时必须小心地维持这些执行策略以避免产生活跃性问题（请参见8.1.1节）。

**使用线程封闭机制的任务**。与线程池相比，单线程的Executor能够对并发性做出更强的承诺。它们能确保任务不会并发地执行，使你能够放宽代码对线程安全的要求。对象可以封闭在任务线程中，使得在该线程中执行的任务在访问该对象时不需要同步，即使这些资源不是线程安全的也没有问题。这种情形将在任务与执行策略之间形成隐式的耦合——任务要求其执行所在的Executor是单线程的（这个要求并不需要这么严格，只要确保任务不会并发执行，并提供足够的同步机制，使得一个任务对内存的作用对于下一个任务一定是可见——这正是newSingleThreadExecutor提供的保证）。如果将Executor从单线程环境改为线程池环境，那么将会失去线程安全性。

**对响应时间敏感的任务**。GUI应用程序对于响应时间是敏感的：如果用户在点击按钮后需要很长延迟才能得到可见的反馈，那么他们会感到不满。如果将一个运行时间较长的任务提交到单线程的Executor中，或者将多个运行时间较长的任务提交到一个只包含少量线程的线程池中，那么将降低由该Executor管理的服务的响应性。

**使用ThreadLocal的任务**。ThreadLocal使每个线程都可以拥有某个变量的一个私有“版本”。然而，只要条件允许，Executor可以自由地重用这些线程。在标准的Executor实现中，当执行需求较低时将回收空闲线程，而当需求增加时将添加新的线程，并且如果从任务中抛出了一个未检查异常，那么将用一个新的工作者线程来替代抛出异常的线程。只有当线程本地值的生命周期受限于任务的生命周期时，在线程池的线程中使用ThreadLocal才有意义，而在线程池的线程中不应该使用ThreadLocal在任务之间传递值。

只有当任务都是同类型的并且相互独立时，线程池的性能才能达到最佳。如果将运行时间较长的与运行时间较短的任务混合在一起，那么除非线程池很大，否则将可能造成“拥塞”。如果提交的任务依赖于其他任务，那么除非线程池无限大，否则将可能造成死锁。

### 8.1.1 线程饥饿死锁

在线程池中，如果任务依赖于其他任务，那么可能产生死锁。在单线程的Executor中，如果一个任务将另一个任务提交到同一个Executor，并且等待这个被提交任务的结果，那么通常会引发死锁。第二个任务停留在工作队列中，并等待第一个任务完成，而第一个任务又无法完成，因为它在等待第二个任务的完成。在更大的线程池中，如果所有正在执行任务的线程都由于等待其他仍处于工作队列的任务而阻塞，那么会发生同样的问题。这种现象被称为线程饥饿死锁（Thread Starvation DeadLock），只要线程池中的任务需要无限期地等待一些必须由池中其他任务才能提供的资源或条件，例如某个任务等待另一个任务的返回值或执行结果，那么除非线程池足够大，否则将发生线程饥饿死锁。

### 8.1.2 运行时间较长的任务

如果任务阻塞的时间过长，那么即使不出现死锁，线程池的响应性也会变得糟糕。执行时间较长的任务不仅会造成线程池堵塞，甚至还会增加执行时间较短任务的服务时间。如果线程池中线程的数量远小于在稳定状态下执行时间较长任务的数量，那么到最后可能所有的线程都会运行这些执行时间较长的任务，从而影响整体的响应性。

有一项技术可以缓解执行时间较长任务造成的影响，即限定任务等待资源的时间，而不要无限制地等待。在平台类库的大多数可阻塞方法中，都同时定义了限时版本和无限时版本，例如Thread.join、BlockingQueue.put、CountDownLatch.await以及Selector.select等。如果等待超时，那么可以把任务标识为失败，然后中止任务或者将任务重新放回队列以便随后执行。这样，无论任务的最终结果是否成功，这种办法都能确保任务总能继续执行下去，并将线程释放出来以执行一些能更快完成的任务。如果在线程池中总是充满了被阻塞的任务，那么也可能表面线程池的规模过小。

## 8.2 设置线程池的大小

线程池的理想大小取决于被提交任务的类型以及所部署系统的特性。在代码中通常不会固定线程池的大小，而应该通过某种配置机制来提供，或者根据Runtime.availableProcessors来动态计算。

幸运的是，要设置线程池的大小也并不困难，只需要避免“过大”和“过小”这两种极端情况。如果线程池过大，那么大量的线程将在相对很少的CPU和内存资源上发生竞争，这不仅会导致更高的内存使用量，而且还可能耗尽资源。如果线程池过小，那么将导致许多空闲的处理器无法执行工作，从而降低吞吐率。

对于计算密集型的任务，在拥有$N_{cpu}$个处理器的系统上，当线程池的大小为$N_{cpu}+1$时，通常能实现最优的利用率。（即使当计算密集型的线程偶尔由于页缺失故障或者其他原因而暂停时，这个“额外”的线程也能确保CPU的时钟周期不会被浪费。）对于包含I/O操作或者其他阻塞操作的任务，由于线程并不会一直执行，因此线程池的规模应该更大。要正确地设置线程池的大小，你必须估算出任务的等待时间与计算时间的比值。这种估算不需要很精确，并且可以通过一些分析或监控工具来获得。你还可以通过另一种方法来调节线程池的大小：在某个基准负载下，分别设置不同大小的线程池来运行应用程序，并观察CPU利用率的水平。

给定下列定义：
$$
N_{cpu} = \text{number of CPUs} \\
U_{cpu} = \text{target CPU utilization}, \quad 0\leq U_{cpu}\leq 1 \\
\frac{W}{C} = \text{ratio of wait time to compute time}
$$
要使处理器达到期望的使用率，线程池的最优大小等于：
$$
N_{threads} = N_{cpu} * U_{cpu} * (1+\frac{W}{C})
$$
可以通过Runtime来获得CPU的数目：

~~~java
int N_CPUS = Runtime.getRuntime().availableProcessors();
~~~

当然，CPU周期并不是唯一影响线程池大小的资源，还包括内存、文件句柄、套接字句柄和数据库连接等。计算这些资源对线程池的约束条件是更容易的：计算每个任务对该资源的需求量，然后用该资源的可用总量除以每个任务的需求量，所得结果就是线程池大小的上限。

当任务需要某种通过资源池来管理的资源时，例如数据库连接，那么线程池和资源池的大小将会互相影响。如果每个任务都需要一个数据库连接，那么连接池的大小就限制了线程池的大小。同样，当线程池中的任务是数据库连接的唯一使用者时，那么线程池的大小又将限制连接池的大小。

## 8.3 配置ThreadPoolExecutor

ThreadPoolExecutor为一些Executor提供了基本的实现，这些Executor是由Executors中的newCachedThreadPool、newFixedThreadPool和newScheduledThreadExecutor等工厂方法返回的。ThreadPoolExecutor是一个灵活的、稳定的线程池，允许进行各种定制。

如果默认的执行策略不能满足需求，那么可以通过ThreadPoolExecutor的构造函数来实例化一个对象，并根据自己的需求来定制，并且可以参考Executors的源代码来了解默认配置下的执行策略，然后再以这些执行策略为基础进行修改。ThreadPoolExecutor定义了很多构造函数，在程序清单8-2中给出了最常见的形式。

~~~java
public ThreadPoolExecutor(int corePoolSize,
                         int maximumPoolSize,
                         long keepAliveTime,
                         TimeUnit unit,
                         BlockingQueue<Runnable> workQueue,
                         ThreadFactory threadFactory,
                         RejectedExecutionHandler handler) { ... }
~~~

### 8.3.1 线程的创建与销毁

线程池的基本大小（Core Pool Size）、最大大小（Maximum Pool Size）以及存活时间等因素共同负责线程的创建与销毁。基本大小也就是线程池的目标大小，即在没有任务执行时（在创建ThreadPoolExecutor初期，线程并不会立即启动，而是等到有任务提交时才会启动，除非调用prestartAllCoreThreads）线程池的大小，并且只有在工作队列满了的情况下才会创建超出这个数量的线程。

（开发人员以免有时会将线程池的基本大小设置为零，从而最终销毁工作者线程以免阻碍JVM的退出。然而，如果在线程池中没有是由SynchronousQueue作为其工作队列（例如在newCachedThreadPool中就是如此），那么这种方式将产生一些奇怪的行为。如果线程池中的线程数量等于线程池的基本大小，那么仅当在工作队列已满的情况下ThreadPoolExecutor才会创建新的线程。因此，如果线程池的基本大小为零并且其工作队列有一定的容量，那么当把任务提交给该线程池时，只有当线程池的工作队列被填满后，才会开始执行任务，而这种行为通常并不是我们所希望的。在Java 6中，可以通过allowCoreThreadTimeOut来是线程池中的所有线程超时。对于一个大小有限的线程池并且在该线程池中包含一个工作队列，如果希望这个线程池在没有任务的情况下能销毁所有线程，那么可以启用这个特性并将基本大小设置为零）

线程池的最大大小表示可同时活动的线程数量的上限。如果某个线程的空闲时间超过了存活时间，那么将被标记为可回收的，并且当线程池的当前大小超过了基本大小时，这个线程将被终止。

通过调节线程池的基本大小和存活时间，可以帮助线程池回收空闲线程占有的资源，从而使得这些资源可以用于执行其他工作。（显然，这是一种折衷：回收空闲线程会产生额外的延迟，因为当需求增加时，必须创建新的线程来满足需求。）

newFixedThreadPool工厂方法将线程池的基本大小和最大大小设置为参数中指定的值，而且创建的线程池不会超时。newCachedThreadPool工厂方法将线程池的最大大小设置为Integer.MAX_VALUE，而将基本大小设置为零，并将超时设置为1分钟，这种方法创建出来的线程池可以被无限扩展，并且当需求降低时会自动收缩。其他形式的线程池可以通过显式的ThreadPoolExecutor构造函数来构造。

### 8.3.2 管理队列任务



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

