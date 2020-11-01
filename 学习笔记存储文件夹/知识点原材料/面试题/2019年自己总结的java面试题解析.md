# 《Java面试题解析》

# 多线程、并发相关

## 1. 线程池相关

### 1.1 线程池的参数

（参考《实战Java高并发程序设计（第二版）》3.2.3 刨根问底：核心线程池的内部实现 P124/420）



#### 三个例子

对于核心的几个线程池，无论是newFixedThreadPool()方法、newSingleThreadExecutor()方法，还是newCachedThreadPool()方法，虽然看起来创建的线程有着完全不同的功能特点，但其内部实现均使用了ThreadPoolExecutor类。

```java
public static ExecutorService newFixedThreadPool(int nThreads){
    return new ThreadPoolExecutor(nThreads,nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
}

public static ExecutorService newSingleThreadExecutor(){
    return new FinalizableDelegatedExecutorService(
        new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
}

public static ExecutorService newCachedThreadPool(){
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
}
```



#### ThreadPoolExecutor的构造函数及其参数

ThreadPoolExecutor类最重要的构造函数：

~~~java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler)
~~~

函数的参数含义如下。

- corePoolSize：指定了线程池中的线程数量。
- maximumPoolSize：指定了线程池中的最大线程数量。
- keepAliveTime：当线程池线程数量超过corePoolSize时，多余的空闲线程的存活时间，即超过corePoolSize的空闲线程，在多长时间内会被销毁。
- unit：keepAliveTime的单位。
- workQueue：任务队列，被提交但尚未被执行的任务。
- threadFactory：线程工厂，用于创建线程，一般用默认的即可。
- handler：拒绝策略。当任务太多来不及处理时，如何拒绝任务。

以上参数大部分都很简单，只有参数workQueue和handler需要进行详细说明。



#### 任务队列workQueue

参数workQueue指被提交但未执行的任务队列，它是一个BlockingQueue接口的对象，仅用于存放Runnable对象。根据队列功能分类，在ThreadPoolExecutor类的构造函数中可使用以下几种BlockingQueue接口。

- 直接提交的队列：该功能由SynchronousQueue对象提供。SynchronousQueue是一个特殊的BlockingQueue。SynchronousQueue没有容量，每一个插入操作都要等待一个相应的删除操作，反之，每一个删除操作都要等待对应的插入操作。如果使用SynchronousQueue，则提交的任务不会被真实的保存，而总是将新任务提交给线程执行，如果没有空闲的进程，则尝试创建新的进程，如果进程数量已经达到最大值，则执行拒绝策略。因此，使用SynchronousQueue队列，通常要设置很大的maximumPoolSize值，否则很容易执行拒绝策略。
- 有界的任务队列：有界的任务队列可以使用ArrayBlockingQueue类实现。ArrayBlockingQueue类的构造函数必须带一个容量参数，表示该队列的最大容量：`public ArrayBlockingQueue(int capacity)`
  当使用有界的任务队列时，若有新的任务需要执行，如果线程池的实际线程数小于corePoolSize，则会优先创建新的线程，若大于corePoolSize，则会将新任务加入等待队列。若等待队列已满，无法加入，则在总线程数不大于maximumPoolSize的前提下，创建新的进程执行任务。若大于maximumPoolSize，则执行拒绝策略。可见，有界队列仅当在任务队列装满时，才可能将线程数提升到corePoolSize以上，换言之，除非系统非常繁忙，否则要确保核心线程数维持在corePoolSize。
- 无界的任务队列：无界任务队列可以通过LinkedBlockingQueue类实现。与有界队列相比，除非系统资源耗尽，否则无界的任务队列不存在任务入队失败的情况。当有新的任务到来，系统的线程数小于corePoolSize时，线程池会生成新的线程执行任务，但当系统的线程数小于corePoolSize时，线程池会生成新的线程执行任务，但当系统的线程数达到corePoolSize后，就不会继续增加了。若后序仍有新的任务加入，而又没有空闲的线程资源，则任务直接进入队列等待。若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。
- 优先任务队列：优先任务队列是带有执行优先级的队列。它通过PriorityBlockingQueue类实现，可以控制任务的执行先后顺序。它是一个特殊的无界队列。无论是有界队列ArrayBlockingQueue类，还是未指定大小的无界队列LinkedBlockingQueue类都是按照先进先出算法处理任务的。而PriorityBlockingQueue类则可以根据任务自身的优先级顺序先后执行，在确保系统性能的同时，也能有很好的质量保证（总是确保高优先级的任务先执行）。



#### 三个特例的解析

回顾newFixedThreadPool()方法的实现，它返回了一个corePoolSize和maximumPoolSize大小一样的，并且使用了LinkedBlockingQueue任务队列的线程池。因为对于固定大小的线程池而言，不存在线程数量的动态变化，因此corePoolSize和maximumPoolSize可以相等。同时，它使用无界队列存放无法立即执行的任务，当任务提交非常频繁的时候，该队列可能迅速膨胀，从而耗尽系统资源。

newSingleThreadExecutor()方法返回的单线程线程池，是newFixedThreadPool()方法的一种退化，只是简单地将线程池线程数量设置为1。

newCachedThreadPool()方法返回corePoolSize为0，maximumPoolSize无穷大的线程池，这意味着在没任务时，该线程池内无线程，而当任务被提交时，该线程池会使用空闲的线程执行任务，若无空闲线程，则将任务加入SynchronousQueue队列，而SynchronousQueue队列是一种直接提交的队列，它总会迫使线程池增加新的线程执行任务。当任务执行完毕后，由于corePoolSize为0，因此空闲线程又会在指定时间内（60秒）被回收。

对于newCachedThreadPool()方法，如果同时有大量任务被提交，而任务的执行又不那么快时，那么系统便会开启等量的线程处理，这样做可能很快耗尽系统的资源。



### 1.2 优化线程池线程数量

（参考《实战Java高并发程序设计（第二版）》3.2.7 合理的选择：优化线程池线程数量 P135/420）

线程池的大小对系统的性能有一定的影响。在Java Concurrecy in Practice一书中给出了估算线程池大小的公式：

```
Ncpu = CPU的数量
Ucpu = 目标CPU的使用率，0<=Ucpu<=1
W/C = 等待时间与计算时间的比率
```

为保持处理器达到期望的使用率，最优的线程池大小等于：

```
Nthreads = Ncpu * Ucpu * (1 + W/C)
```

在Java中，可以通过如下代码取得可用的CPU数量。

```java
Runtime.getRuntime().availableProcessors()
```



## 2. 锁的优化

### 2.1 有助于提高锁性能的方法

（参考《实战Java高并发程序设计（第二版）》4.1 有助于提高锁性能的几点建议 P178/420）

#### 减少锁持有时间

只在必要时进行同步，这样就能明显减少线程持有锁的时间，提高系统的吞吐量。

例子：Pattern类的matcher()方法。matcher()方法有条件地进行锁申请，只有在表达式未编译时，进行局部的加锁。这种处理方式大大提高了matcher()方法的执行效率和可靠性。

减少锁的持有时间有助于降低锁冲突的可能性，进而提升系统的并发能力。

#### 减少锁粒度

典型的使用场景：ConcurrentHashMap类的实现。

对于ConcurrentHashMap类，它内部进一步细分了若干个小的HashMap，称之为段（SEGMENT）。默认情况下，一个ConcurrentHashMap类可以细分为16个段。如果需要在ConcurrentHashMap类中增加一个新的表项，并不是将整个HashMap加锁，而是首先根据hashcode得到该表项应该被存放到哪个段中，然后对该段加锁，并完成put()方法操作。

减少锁粒度会带来一个新的问题，即当系统需要取得全局锁时，其消耗的资源会比较多。比如ConcurrentHashMap类的size()方法，在计算总数时，先要获得所有段的锁再求和。但是，ConcurrentHashMap类的size()方法并不总是这样执行的，事实上，size()方法会先使用无锁的方式求和，如果失败才会尝试这种加锁的方法。但不管怎么说，在高并发场合ConcurrentHashMap类的size()方法的性能依然要差于同步的HashMap。

所谓减少锁粒度，就是指缩小锁定对象的范围，从而降低了锁冲突的可能性，进而提高系统的并发能力。

#### 用读写分离锁来替换独占锁

使用读写分离锁来替代独占锁是减小锁粒度的一种特殊情况。如果说减小锁粒度是通过分割数据结构实现的，那么读写分离锁则是对系统功能点的分割。

在读多写少的场合使用读写锁可以有效提升系统的并发能力。

#### 锁分离

如果将读写锁的思想进一步延伸，就是锁分离。读写锁根据读写操作功能上的不同，进行了有效的锁分离。依据应用程序的特点，使用类似的分离思想，也可以对独占锁进行分离。一个典型的案例就是java.util.concurrent.LinkedBlockingQueue的实现。

在JDK的实现中，用两把不同的锁分离了take()方法和put()方法的操作。

#### 锁粗化

虚拟机在遇到一连串连续地对同一个锁不断进行请求和释放的操作时，便会把所有的锁操作整合成对锁的一次请求，从而减少对锁的请求同步的次数，这个操作叫做锁的粗化。

性能优化就是根据运行时的真实情况对各个资源点进行权衡折中的过程。锁粗化的思想和减少锁持有时间是相反的，但在不同的场合，它们的效果并不相同，因此要根据实际情况进行权衡。



### 2.2 Java虚拟机对锁优化

（参考《实战Java高并发程序设计（第二版）》4.2 Java虚拟机对锁优化所做的努力 P185/420）

#### 锁偏向

锁偏向是一种针对对锁操作的优化手段。它的核心思想是：如果一个线程获得了锁，那么锁就进入偏向模式。当这个线程再次请求锁时，无须再做任何同步操作。这样就节省了大量有关锁申请的操作，从而提高了程序性能。因此，对于几乎没有锁竞争的场合，偏向锁有比较好的优化效果，因为连续多次极有可能是同一个线程请求相同的锁。而对于锁竞争比较激烈的场合，其效果不佳。因为在竞争激烈的场合，最有可能的情况是每次都是不同的线程来请求相同的锁。这样偏向模式会失效，因此还不如不启用偏向锁。使用Java虚拟机参数-XX:+UseBiasedLocing可以开启偏向锁。

#### 轻量级锁

如果偏向锁失败，那么虚拟机并不会立即挂起线程，它还会使用一种称为轻量级锁的优化手段。轻量级锁的操作也很方便，它只是简单地将对象头部作为指针指向持有锁的线程堆栈的内部，来判断一个线程是否持有对象锁。如果线程获得轻量级锁成功，则可以顺利进入临界区。如果轻量级锁加锁失败，则表示其他线程抢先争夺到了锁，那么当前线程的锁请求就会膨胀为重量级锁。

#### 自旋锁

锁碰撞后，为了避免线程真实地在操作系统层面挂起，虚拟机还会做最后的努力——自旋锁。当前线程暂时无法获得锁，而且什么时候可以获得锁是一个未知数，也许在几个CPU时钟周期后就可以得到锁。如果这样，简单粗暴地挂起线程可能是一种得不偿失的操作。系统会假设在不久的将来，线程可以得到这把锁。因此，虚拟机会让当前线程做几个空循环（这也是自旋的含义），在经过若干个循环后，如果可以得到锁，那么就顺利进入临界区。如果还不能获得锁，才会真正将线程在操作系统层面挂起。

#### 锁消除

锁消除是一种更彻底的锁优化。Java虚拟机在JIT编译时，通过对运行上下文的扫描，去除不可能存在共享资源竞争的锁。通过锁消除，可以节省毫无意义的请求锁时间。

锁消除涉及的一项关键技术为逃逸分析。所谓逃逸分析就是观察某一个变量是否会逃出某一个作用域。逃逸分析必须在-server模式下进行，可以使用-XX:+DoEscapeAnalysis参数打开逃逸分析。使用-XX:+EliminateLocks参数可以打开锁消除。

## 3. ThreadLocal

SimpleDateFormat不是线程安全的

### 3.1 ThreadLocal的实现原理

（参考《实战Java高并发程序设计（第二版）》4.3.2 ThreadLocal的实现原理 P189/420）

#### set()和get()方法

```java
public void set(T value){
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if(map!=null) map.set(this,value);
    else createMap(t,value);
}
```

set()方法，在set时，首先获得当前线程对象，然后通过getMap()方法拿到线程的ThreadLocalMap，并将值存入ThreadLocalMap中。而ThreadLocalMap可以理解为一个Map（虽然不是，但是你可以把它简单地理解成HashMap），但是它是定义在Thread内部的成员。

而设置到ThreadLocal中的数据，也正是写入了threadLocals的这个Map。其中，key为ThreadLocal当前对象，value就是我们需要的值。而threadLocals本身就保存了当前自己所在线程的所有“局部变量”，也就是一个ThreadLocal变量的集合。

在进行get()方法操作时，自然就是将这个Map中的数据拿出来。

~~~java
public T get(){
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if(map!=null){
        ThreadLocalMap.Entry e = map.getEntry(this);
        if(e!=null) return (T)e.value;
    }
    return setInitialValue();
}
~~~

get()方法先取得当前线程的ThreadLocalMap对象，然后通过将自己作为key取得内部的实际数据。

#### ThreadLocal的回收

在了解ThreadLocal的内部实现后，我们自然会引出一个问题：那就是这些变量是维护在Thread内部的（ThreadLocalMap定义所在类），这也意味着只要线程不退出，对象的引用将一直存在。

如果你希望即使回收对象，最好使用ThreadLocal.remove()方法将这个变量移除。

另外一个有趣的情况是JDK也可能允许你想释放普通变量一样释放ThreadLocal。比如，我们有时候为了加速垃圾回收，会特意写出类似obj = null的代码。

要了解这里的回收机制，我们需要进一步了解Thread.ThreadLocalMap的实现。之前我们说过，ThreadLocalMap是一个类似HashMap的东西。更准确地说，它更加类似WeakHashMap。

ThreadLocalMap的实现使用了弱引用。弱引用是比强引用弱得多的引用。Java虚拟机在垃圾回收时，如果发现弱引用，就会立即回收。ThreadLocalMap内部由一系列Entry构成，每一个Entry都是WeakReference<ThreadLocal\>。

```java
static class Entry extends WeakReference<ThreadLocal>{
    /** The value associated with this ThreadLocal. */
    Object value;
    Entry(ThreadLocal k, Object v){
        super(k);
        value = v;
    }
}
```

这里的参数k就是Map的key，v就是Map的value，其中k也是ThreadLocal实例，作为弱引用使用。因此，虽然这里使用ThreadLocal作为Map的key，但是实际上，它并不真正持有ThreadLocal的引用。而当ThreadLocal的外部强引用被回收时，ThreadLocalMap中的key就会变成null。当系统进行ThreadLocalMap清理时（比如将新的变量加入表中，就会自动进行一次清理），就会将这些垃圾数据回收



## 4. Future

（参考《实战Java高并发程序设计（第二版）》5.5 Future模式 P246/420）

Future模式是多线程开发中非常常见的一种设计模式，它的核心思想是异步调用。当我们需要调用一个函数方法时，如果这个函数执行得很慢，那么我们就要进行等待。但有时候，我们可能并不急着要结果。因此，我们可以让被调者立即返回，让它在后台慢慢处理这个请求。对于调用者来说，则可以先处理一些其他任务，在真正需要数据的场合再去尝试获得需要的数据。

对于Future模式来说，虽然它无法立即给出你需要的数据，但是它会返回一个契约给你，将来你可以凭借这个契约去重新获取你需要的信息。

图5.7的模型展示了一个广义Future模式的实现，从Data_Future对象可以看到，虽然call本身仍然需要很长一段时间处理程序。但是，服务程序不等数据处理完成便立即返回客户端一个伪造的数据（相当于商品的订单，而不是商品本身），实现了Future模式的客户端在拿到这个返回结果后，并不急于对其进行处理，而是去调用了其他业务逻辑，充分利用了等待时间，这就是Future模式的核心所在。在完成了其他业务逻辑的处理后，再使用返回比较慢的Future数据。在整个调用过程中，不存在所谓的等待，充分利用了所有的时间片段，从而提高系统的响应速度。

### 4.1 Future模式的主要角色

为了让大家更清晰地认识Future模式的基本结构。在这里给出一个非常简单的Future模式的实现，它的主要参与者：

| 参与者     | 作用                                                         |
| ---------- | ------------------------------------------------------------ |
| Main       | 系统启动，调用Client发出请求                                 |
| Client     | 返回Data对象，立即返回FutureData，并开启ClientThread线程装配RealData |
| Data       | 返回数据的接口                                               |
| FutureData | Future数据构造很快，但是是一个虚拟的数据，需要装配RealData   |
| RealData   | 真实数据，其构造是比较慢的                                   |

### 4.2 Future模式的简单实现

在这个实现中，有一个核心接口Data，这就是客户端希望获取的数据。在Future模式中，这个Data接口有两个重要的实现，一个RealData，也就是真实数据，这就是我们最终需要获得的、有价值的信息。另外一个就是FutureData，它是用来提取RealData的一个“订单”。因此FutureData可以立即返回。

下面是Data接口：

```java
public interface Data{
    public String getResult();
}
```

FutureData实现了一个快速返回的RealData包装。它只是一个包装，或者说是一个RealData的虚拟实现。因此，它可以很快被构造并返回。当使用FutureData的getResult()方法时，如果实际的数据没有准备好，那么程序就会阻塞，等RealData准备好并注入FutureData中才最终返回数据。

> 注意：FutureData是Future模式的关键。它实际上是真实数据RealData的代理，封装了获取RealData的等待过程。

```java
public class FutureData implements Data {
    protected RealData realdata = null; //FutureData是RealData的包装
    protected boolean isReady = false;
    public synchronized void setRealData(RealData realdata){
        if(isReady){
            return;
        }
        this.realdata = realdata;
        isReady = true;
        notifyAll(); //RealData已经被注入，通知getResult()方法
    }
    public synchronized String getResult(){ //等待RealData构造完成
        while(!isReady){
            try{
                wait(); //一直等待，直到RealData被注入
            }catch(InterruptedException e){
                
            }
        }
        return realdata.result; //由RealData实现
    }
}
```

RealData是最终需要使用的数据模型。它的构造很慢。用sleep()函数模拟这个过程，简单地模拟一个字符串的构造。

```java
public class RealData implements Data{
    protected final String result;
    public RealData(String para){
        //RealData的构造可能很慢，需要用户等待很久，这里使用sleep模拟
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            sb.append(para);
            try{
                //这里使用sleep，代替一个很慢的操作过程
                Thread.sleep(100);
            }catch(InterruptedException e){
                
            }
        }
        return sb.toString();
    }
    public String getResult() {
        return result;
    }
}
```

接下来就是客户端程序，Client主要实现了获取FutureData，开启构造RealData的线程，并在接受请求后，很快返回FutureData。注意，它不会等待数据真的构造完毕再返回，而是立即返回FutureData，即使这个时候FutureData内并没有真实数据。

```java
public class Client{
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(){
            public void run(){ //RealData的构建很慢，所以在单独的线程中进行
                RealData realdata = new RealData(queryStr);
                future.setRealData(realdata);
            }
        }
    }.start();
    return future; //FutureData会被立即返回
}
```

最后，就是我们的主函数Main，它主要负责调用Client发起请求，并消费返回的数据。

```java
public static void main(String[] args) {
    Client client = new Client();
    //这里会立即返回，因为得到的是FutureData而不是RealData
    Data data = client.request("name");
    System.out.println("请求完毕");
    try{
        //这里可以用一个sleep代替对其他业务逻辑的处理
        //在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
        Thread.sleep(2000);
    }catch(InterruptedException e){
        
    }
    //使用真实的数据
    System.out.println("数据 = "+data.getResult());
}
```

### 4.3 JDK中的Future模式

Future模式很常用，因此JDK内部已经为我们准备好了一套完整的实现。显然，这个实现要比我们前面提出的方案复杂得多。本节简单向大家介绍一下它的使用方式。

首先，让我们看一下Future模式的基本结构。其中Future接口类似于前文描述的订单或者说是契约。通过它，你可以得到真实的数据。RunnableFuture继承了Future和Runnable两个接口，其中run()方法用于构造真实的数据。它有一个具体的实现FutureTask类。FutureTask类有一个内部类Sync，一些实质性的工作会委托Sync类实现。而Sync类最终会调用Callable接口，完成实际数据的组装工作。

Callable接口只有一个方法call()，它会返回需要构造的实际数据。这个Callable接口也是Future框架和应用程序之间的重要接口。要实现自己的业务系统，通常需要实现自己的Callable对象。此外，FutureTask类也与应用密切相关，通常可以使用Callable实例构造一个FUtureTask实例，并将它提交给线程池。

下面我们将展示这个内置的Future模式的使用方法。

```java
public class RealData implements Callable<String>{
    private String para;
    public RealData (String para){
        this.para = para;
    }
    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            sb.append(para);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                
            }
        }
        return sb.toString();
    }
}
```

上述代码实现了Callable接口，它的call()方法会构造我们需要的真实数据并返回。当然这个过程可能是缓慢的，这里使用Thread.sleep()方法模拟它。

```java
public class FutureMain{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //构造FutureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //执行FutureTask,相当于上例中的client.request("a") 发送请求
        //在这里开启线程进行RealData的call()方法执行
        executor.submit(future);
        
        System.out.println("请求完毕");
        try{
            //这里依然可以做额外的数据操作，使用sleep代替其他业务逻辑的处理
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }
        //相当于5.5.2节中的data.getResult()方法，取得call()方法的返回值
        //如果此时call()方法没有执行完成，则依然会等待
        System.out.println("数据 = " + future.get());
    }
}
```

上述代码就是使用Future模式的典型。第4行构造了FutureTask对象实例，表示这个任务是有返回值的。构造FutureTask时，使用使用Callable接口告诉FutureTask我们需要的数据应该如何产生。接着在第8行将FutureTask提交给线程池。显然，作为一个简单的任务提交，这里必然是立即返回的，因此程序不会阻塞。接下来，我们不用关心数据是任何产生的，可以去做一些其他事情，然后在需要的时候通过Future.get()方法（第19行）得到实际的数据。

除基本的功能外，JDK还未Future接口提供了一些简单的控制功能。

```java
boolean cancel（boolean mayInterruptIfRunning）; //取消任务
boolean isCancelled(); //是否已经取消
boolean isDone(); //是否已完成
V get() throws InterruptedException, ExecutionException; //取得返回对象
V get(long timeout, TimeUnit unit) //取得返回对象，可以设置超时时间
```



## 5. Fork/Join

（参考《实战Java高并发程序设计（第二版）》3.2.9 分而治之：Fork/Join框架 P140/420）

Fork一词的原始含义是吃饭用的叉子，也有分叉的意思。在Linux平台中，方法fork()用来创建子进程，使得系统进程可以多一个执行分支。在Java中也沿用了类似的命名方式。

而join()方法的含义在之前的章节中已经解释过，这里表示等待。也就是使用fork()方法后系统多了一个执行分支（线程），所以需要等待这个执行分支执行完毕，才有可能得到最终的结果，因此join()方法就表示等待。

在实际使用中，如果毫无顾忌地使用fork()方法开启线程进行处理，那么很有可能导致系统开启过多的线程而严重影响性能。所以，在JDK中，给出了一个ForkJoinPool线程池，对于fork()方法并不急着开启线程，而是提交给ForkJoinPool线程池进行处理，以节省系统资源。

由于线程池的优化，提交的任务和线程数量并不是一对一的关系。在绝大多数情况下，一个物理线程实际上是需要处理多个逻辑任务的。因此，每个线程必然需要拥有一个任务队列。因此，在实际执行过程中，可能遇到这么一种情况：线程A已经把自己的任务都执行完了，而线程B还是一堆任务等着处理，此时，线程A就会“帮助”线程B，从线程B的任务队列中哪一个任务过来处理，尽可能地达到平衡。一个值得注意的地方是，当一个线程试图“帮助”其他线程时，总是从任务队列的底部开始获取数据，而线程试图执行自己的任务时，则是从相反的顶部开始获取数据。因此，这种行为也十分有利于避免数据竞争。

下面我们来看一个ForkJoinPool线程池的一个重要的接口：

`public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task)`

你可以向ForkJoinPool线程池提交一个ForkJoinTask任务。所谓ForkJoinTask任务就是支持fork()方法分解及join()方法等待的任务。ForkJoinTask任务有两个重要的子类，RecursiveAction类和RecursiveTask类。它们分别表示没有返回值的任务和可以携带返回值的任务。

在使用Fork/Join框架时需要注意，如果任务划分层次很多，一直得不到返回，那么可能出现两种情况。第一，系统内的线程数量越积越多，导致性能严重下降。第二，函数的调用层次变多，最终导致栈溢出。不同版本的JDK内部实现机制可能有差异，从而导致其表现不同。

此外，ForkJoin线程池使用一个无锁的栈来管理空闲进程。如果一个工作进程暂时取不到可用的任务，则可能被挂起，挂起的线程将会被压入由线程池维护的栈中。待将来有任务可用时，再从栈中唤醒这些线程。



## 6. synchronized底层实现

（参考《Java多线程编程实战指南-核心篇》3.3 内部锁：synchronized关键字 P102/480）

Java平台中的任何一个对象都有唯一一个与之关联的锁。这种锁被称为监视器（Monitor）或者内部锁（Intrinsic Lock）。内部锁是一种排他锁，它能够保障原子性、可见性和有序性。

内部锁是通过synchronized关键字实现的。synchronized关键字可以用来修饰方法以及代码块（花括号"{}"包裹的代码）。

synchronized关键字修饰的方法就被称为同步方法（Synchronized Method）。synchronized修饰的静态方法就被称为同步静态方法，synchronized修饰的实例方法就被称为同步实例方法。同步方法的整个方法体就是一个临界区。

synchronized关键字修饰的代码块被称为同步块（Synchronized Block），其语法如下所示：

~~~java
synchronized (锁句柄){
    //在次代码块中访问共享数据
}
~~~

synchronized关键字所引导的代码块就是临界区。锁句柄是一个对象的引用（或者能够返回对象的表达式）。例如，锁句柄可以填写为this关键字（表示当前对象）。习惯上我们也直接称锁句柄为锁。锁句柄对应的监视器就被称为相应同步块的引导锁。相应地，我们称呼相应的同步块为该锁引导的同步块。

同步实例方法相当于以“this”为引导锁的同步块（实际上，Java虚拟机及编译器对同步块和同步方法的处理方式是不同的，但是这并不影响我们做出这样的理解）。因此同步方法：

~~~java
public synchronized short nextSequence() {
    //bla bla
}
~~~

可以改写为：

~~~java
public short nextSequence(){
    synchronized(this){
        //bla bla
    }
}
~~~

作为锁句柄的变量通常采用final修饰。这是因为锁句柄变量的值一旦改变，会导致执行同一个同步块的多个线程实际上使用不同的锁，从而导致竞态。有鉴于此，通常我们会使用private修饰作为锁句柄的变量。

> 注意：作为锁句柄的变量通常采用private final修饰，如：private final Object lock = new Object();

同步静态方法相当于以当前类对象（Java中的类本身也是一个对象）为引导锁的同步块。例如同步静态方法：

~~~java
public class SynchronizedMethodExample {
    public static synchronized void staticMethod() {
        //在此访问共享数据
    }
    // ....
}
~~~

相当于：

~~~java
public class SynchronizedMethodExample {
    public static void staticMethod(){
        synchronized(SynchronizedMethodExample.class){
            //在此访问共享数据
        }
    }
    // ....
}
~~~

线程在执行临界区代码的时候必须持有该临界区的引导锁。一个线程执行到同步块（同步方法也可看作同步块）时必须先申请该同步块的引导锁，只有申请成功（获得）该锁的线程才能够执行相应的临界区。一个线程执行完临界区代码后引导该临界区的锁就会被自动释放。在这个过程中，线程对内部锁的申请与释放的动作由Java虚拟机负责代为实施，这也正是synchronized实现的锁被称为内部锁的原因。

内部锁的使用并不会导致锁泄露。这是因为Java编译器（javac）在将同步块代码编译为字节码的时候，对临界区中可能抛出的而程序代码中又未捕获的异常进行了特殊（代为）处理，这使得临界区的代码即使抛出异常也不会妨碍内部锁的释放。

### 内部锁的调度

Java虚拟机会为每个内部锁分配一个入口集（Entry Set），用于记录等待获得相应内部锁的线程。多个线程申请同一个锁的时候，只有一个申请者能够成为该锁的持有线程（即申请锁的操作成功），而其他申请者的申请操作会失败。这些申请失败的线程并不会抛出异常，而是会被暂停（生命周期状态变为BLOCKED）并被存入相应锁的入口集中等待再次申请锁的机会。入口集中的线程就被称为相应内部锁的等待线程。当这些线程申请的锁被其持有线程释放的时候，该锁的入口集中的一个任意线程会被Java虚拟机唤醒，从而得到再次申请锁的机会。由于Java虚拟机对内部锁的调度仅支持非公平调度，被唤醒的等待线程占用处理器运行时可能还有其他新的活跃线程（处于RUNNABLE状态，且未进入过入口集）与该线程抢占这个被释放锁，因此被唤醒的线程不一定就能成为该锁的持有线程。

另外，Java虚拟机如何从一个锁的入口集中选择一个等待线程，作为下一个可以参与再次申请相应锁的线程，这个细节与Java虚拟机的具体实现有关：这个被选中的线程有可能是入口集中等待时间最长的线程，也可能是等待时间最短的线程，或者完全是随机的一个线程。因此，我们不能依赖这个具体的选择算法



### 从Java虚拟机看synchronized关键字的实现

（参考《Java多线程编程实战指南-核心篇》11.5.2 synchronized关键字的实现 P413/480）

Java虚拟机（JIT编译器）会在monitorenter（用于申请锁的字节码命令）对应的指令后临界区开始前的地方插入一个获取屏障。Java虚拟机会在临界区结束后monitorexit（用于释放锁的字节码指令）对应的指令前的地方插入一个释放屏障。这里，获取屏障和释放屏障一起保障了临界区内的任何读、写操作都无法被重排序到临界区之外，再加上锁的排他性，这使得临界区内的操作具有原子性。

synchronized关键字对有序性的保障与volatile关键字对有序性的保障实现原理是一样的，也是通过释放屏障和获取屏障的配对使用实现的。释放屏障是的写线程在临界区中执行的读、写操作先于monitorexit对应的指令（相当于写操作）被提交，而获取屏障使得读线程必须在获得锁（相当于read-modify-write操作）之后才能够执行临界区中的操作。写线程以及读线程通过这种释放屏障和获取屏障的配对使用实现了有序性。

Java虚拟机也会在monitorexit对应的指令（相当于写操作）之后插入一个StoreLoad屏障。这个处理的目的与在volatile写操作之后插入一个StoreLoad屏障类似。该屏障充当了存储屏障，从而确保锁的持有线程在释放锁之前所执行的所有操作的结果能够到达高速缓存，并消除了存储转发的副作用。另外，该屏障禁止了monitorexit对应的指令与其他同步块的monitorenter对应的指令进行重排序，这保障了monitorenter与monitorexit总是成对的，从而使synchronized块的并列（一个synchronized块之后又有其他synchronized块）以及synchronized块的嵌套（一个synchronized块内包含其他synchronized块）成为可能。



## 7. volatile

（参考《Java多线程编程实战指南-核心篇》3.8 轻量级同步机制：volatile关键字 P121/480）

volatile字面有“易挥发”的意思，引申开来就是有“不稳定”的意思。volatile关键字用于修饰共享可变变量，即没有使用final关键字修饰的实例变量或静态变量，相应的变量就被称为volatile变量。

volatile关键字表示被修饰的变量的值容易变化（即被其他线程更改），因而不稳定。volatile变量的不稳定性意味着对这种变量的读和写操作都必须从高速缓存或者主内存（也是通过高速缓存读取）中读取，以读取变量的相对新值。因此，==volatile变量不会被编译器分配到寄存器进行存储，对volatile变量的读写操作都是内存访问（访问高速缓存相当于主内存）操作==。

volatile关键字常被称为轻量级锁，其作用与锁的作用有相同的地方：保证可见性和有序性。所不同的是，在原子性方面它仅能保障写volatile变量操作的原子性，但没有锁的排他性；其次，volatile关键字的使用不会引起上下文切换（这是volatile被冠以“轻量级”的原因）。因此，volatile更像是一个轻量级简易（功能比锁有限）锁。

### 7.1 volatile的作用

volatile关键字的作用包括：保障可见性、保障有序性和保障long/double型变量读写操作的原子性。

> 约定：访问同一个volatile变量的线程被称为同步在这个变量之上的线程，其中读取这个变量的线程被称为读线程，更新这个变量的线程被称为写线程。一个线程可以既是读线程又是写线程。

volatile关键字能够保障对long/double型变量的写操作具有原子性。在Java语言中，对long型和double型以外的任何类型的变量的写操作都是原子操作。考虑到某些32位Java虚拟机上对long/double型变量进行的写操作可能不具有原子性，正如第2章的"long/double型变量写操作的非原子Demo"所展示的，Java语言规范特别地规定对long/double型volatile变量的写操作和读操作也具有原子性。因此，要解决上述Demo中出现的问题（读取到更新的“中间结果”），我们只需要将其中的共享变量value采用volatile修饰，如下所示：

`static volatile long value = 0;`

但是，volatile仅仅保障对其修饰的变量的写操作（以及读操作）本身的原子性，而这并不表示对volatile变量的赋值操作一定具有原子性。例如，如下对volatile变量count1的赋值操作并不原子操作：

`count1 = count2 + 1;`

如果变量count2也是一个共享变量，那么该赋值操作实际上是一个read-modify-write操作。其执行过程中其他线程可能已经更新了count2的值，因此该操作不具备不可分割性，也就不是原子操作。如果变量count2是一个局部变量，那么该赋值操作就是一个原子操作。

一般而言，对volatile变量的操作，其左边表达式中只要涉及共享变量（包括被赋值的volatile本身），那么这个赋值操作就不是原子操作。要保障这样操作的原子性，我们仍然需要借助锁。

又如这样一个赋值操作：

`volatile Map map = new HashMap();`

可以分解为如下伪代码所示的几个子操作：

```java
objRef = allocate(HashMap.class); //子操作①：分配对象所需的存储空间
invokeConstructor(objRef); //子操作②：初始化objRef引用的对象
aMap = objRef; //子操作③：将对象引用写入变量aMap
```

虽然volatile关键字仅保障其中的子操作③是一个原子操作，但是由于子操作①和②仅涉及局部变量而未涉及共享变量，因此对变量aMap的赋值操作仍然是一个原子操作。

> 约定：volatile关键字在原子性方面仅保障对被修饰的变量的读操作，写操作本身的原子性。如果要保障对volatile变量的赋值操作的原子性，那么这个赋值操作不能涉及任何共享变量（包括被赋值的volatile变量本身）的访问。

写线程对volatile变量的写操作会产生类似于释放锁的效果。读线程对volatile变量的读操作会产生类似于获得锁的效果。因此，volatile具有保障有序性和可见性的作用。

对于volatile变量的写操作，Java虚拟机会在该操作之前插入一个释放屏障，并在该操作之后插入一个存储屏障。

其中，释放屏障禁止了volatile写操作与读操作之前的任何读、写操作进行重排序，从而保证了volatile写操作之前的任何读、写操作会先于voilatile写操作被提交，即其他线程看到写线程对volatile变量的更新时，写线程在更新volatile变量之前所执行的内存操作的结果对于读线程必然也是可见的。这就保障了读线程对写线程在更新volatile变量前对共享变量所执行的更新操作的感知顺序与相应的源代码顺序一致，即保障了有序性。

### 7.2 volatile变量的开销



### 7.3 volatile的典型应用场景与实战案例





## 8. JUC包



## 9. 线程安全



## 10. FutureTask

（参考《Java多线程编程实战指南-核心篇》9.3 异步计算助手：FutureTask）

无论是Runnable实例还是Callable实例所表示的任务，只要我们将其提交给线程池执行，那么这些任务就是异步任务。

采用Runnable实例来表示异步任务，其**优点**是任务既可以交给一个专门的工作者线程执行（以相应的Runnable实例为参数创建并启动一个工作者线程），也可以交给一个线程池或者Executor的其他实现类来执行；其**缺点**是我们无法直接获取任务的执行结果。

使用Callable实例来表示异步任务，其**优点**是我们可以通过ThreadPoolExecutor.submit(Callable\<T>)的返回值获取任务的处理结果；其**缺点**是Callable实例表示的异步任务只能交给线程池执行。因此，使用Callable实例来表示异步任务会使任务执行方式的灵活性大为受限。



java.util.concurrent.FutureTask类融合了Runnable接口和Callable接口的优点：FutureTask是Runnable接口的一个实现类，因此FutureTask表示的异步任务可以交给专门的工作者线程执行，也可以交给Executor实例（比如线程池）执行；FutureTask还能够直接返回其代表的异步任务的处理结果。ThreadPoolExecutor.submit(Callable\<T> task)的返回值就是一个FutureTask实例。

FutureTask是java.util.concurrent.RunnableFuture接口的一个实现类。由于RunnableFuture接口继承了Future接口和Runnable接口，因此FutureTask既是Runnable接口的实现类也是Future接口的实现。FutureTask的一个构造器可以将Callable实例转换为Runnable实例，该构造器的声明如下：

`public FutureTask(Callable<V> callable)`

该构造器使得我们能够方便地创建一个能够返回处理结果的异步任务。我们可以将任务的处理逻辑封装在一个Callable实例中，并以该实例为参数创建一个FutureTask实例。由于FutureTask类实现了Runnable接口，因此上述构造器的作用就相当于将Callable实例转换为Runnable实例，而FutureTask实例本身也代表了我们要执行的任务。

我们可以用FutureTask实例（Runnable实例）为参数来创建并启动一个工作者线程以执行相应的任务，也可以将FutureTask实例交给Executor执行（通过Executor.execute(Runnable task)调用）。FutureTask类还实现了Future接口，这使得我们在调用Executor.executor(Runnable task)这样只认Runnable接口的方法来执行任务的情况下依然能够获取任务的执行结果：一个工作者线程（可以是线程池中的一个工作者线程）负责调用FutureTask.run()执行相应的任务，另外一个线程则调用FutureTask.get()来获取任务的执行结果。

因此，FutureTask实例可被看做一个异步任务，它使得任务的执行和对任务执行结果的处理得以并发执行，从而有利于提高系统的并发性。



ThreadPoolExecutor.submit(Callable\<T> task)方法继承自AbstractExecutorService.submit(Callable\<T> task)。AbstractExecutorService.submit(Callable\<T> task)内部实现就是借助FutureTask的：

```java
public <T> Future<T> submit(Callable<T> task){
    if(task == null) throw new NullPointerException();
    RunnableFuture<T> ftask = newTaskFor(task);
    execute(ftask);
    return ftask;
}
```

submit方法会根据指定的Callable实例task创建一个FutureTask实例ftask，并通过Executor.execute(Runnable)调用异步执行ftask所代表的任务，然后返回ftask，以便该方法的调用方能够获取任务的执行结果。

FutureTask还支持以回调（Callback）的方式处理任务的执行结果。当FutureTask实例所代表的任务执行结束后，FutureTask.done()会被执行。FutureTask.done()是个protected方法，FutureTask子类可以覆盖该方法并在其中实现对任务执行结果的处理。FutureTask.done()中的代码可以通过FutureTask.get()调用来获取任务的执行结果，此时由于任务的执行结束既包括正常终止，也包括异常终止以及任务被取消而导致的终止，因此FutureTask.done()方法中代码可能需要在调用FutureTask.get()前调用FutureTask.isCancelled()来判断任务是否被取消，以免FutureTask.get()调用抛出CancellationException异常（运行时异常）

### 10.1 实践：实现XML文档的异步解析

Java标准库所提供的XML文档解析器javax.xml.parsers.DocumentBuilder 仅支持以同步的方式去解析XML文档，这意味着直接使用DocumentBuilder解析XML文档，我们必须等待XML文档解析完毕才能从XML文档中查询数据。利用FutureTask我们可以自行实现一个支持异步解析的XML解析器XMLDocumentParser。

```java
/**
* 支持异步解析器的XML解析器
*
* @author Viscent Huang
*/
public class XMLDocumentParser {
    public static ParsingTask newTask(InputStream in){
        return new ParsingTask(in);
    }
    
    public static ParsingTask newTask(URL url) throws IOException {
        return newTask(url, 30000, 30000);
    }
    
    //完整代码见本书配套下载资源
    
    //封装对XML解析结果进行处理的回调方法
    public abstract static class ResultHandler {
        abstract void onSuccess(Document document);
        
        void onError(Throwable e){
            e.printStrackTrace();
        }
    }
    
    public static class ParsingTask{
        private final InputStream in;
        private volatile Executor executor;
        private volatile ResultHandler resultHandler;
        
        public ParsingTask(InputStream in, Executor executor, ResultHandler resultHandler){
            this.in  = in;
            this.executor = executor;
            this.resultHandler = resultHandler;
        }
        
        public ParsingTask(InputStream in){
            this(in, null, null);
        }

        public Future<Document> execute() throws Exception {
            FutureTask<Document> ft;
            final Callable<Document> task = new Callable<Document>() {
                @Override
                public Document call() throws Exception {
                    return doParse(in);
                }
            };
            final Executor theExecutor = executor;
            //解析模式：异步/同步
            final boolean isAsyncParsing = (null != theExecutor);
            final ResultHandler rh;
            if(isAsyncParsing && null !=(rh = resultHandler)){
                ft = new FutureTask<Document>(task){
                    @Override
                    protected void done(){
                        //回调ResultHandler的相关方法对XML解析结果进行处理
                        callbackResultHandler(this, rh);
                    }
                }; //FutureTask匿名类结束
            }else{
                ft = new FutureTask<Document>(task);
            }
            if(isAsyncParsing) {
                theExecutor.execute(ft); //交给Executor执行，以支持异步执行
            }else{
                ft.run(); //直接（同步）执行
            }
            return ft;
        }

        void callbackResultHandler(FutureTask<Document> ft, ResultHandler rh) {
            //获取任务处理结果前判断任务是否被取消
            if(ft.isCancelled()){
                Debug.info("parsing cancelled.%s", ParsingTask.this);
                return;
            }
            try{
                Document doc = ft.get();
                rh.onSuccess(doc);
            }catch(InterruptedException ignored){
                Debug.info("retrieving result cancelled.%s", ParsingTask.this);
            }catch(ExecutionException e){
                rh.onError(e.getCause());
            }
        } 

        static Document doParse(InputStream in) throws Exception{
            Document document = null;
            try{
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                document = db.parse(in);
            }finally{
                Tools.silentClose(in);
            }
            return document;
        }

        public ParsingTask setExecutor(Executor executor){
            this.executor = executor;
            return this;
        }

        public ParsingTask setResultHandler(ResultHandler resultHandler){
            this.resultHandler = resultHandler;
            return this;
        }
    }
}
```

XMLDocumentParser不仅支持异步方式解析，还支持同步方式解析。利用XMLDocumentParser，每次解析意味着创建一个ParsingTask实例并执行该实例的execute()方法。在ParsingTask.execute()中，我们先创建一个Callable\<Document>实例task来表示针对指定输入流（InputStream）进行的XML解析任务。接着，我们设定XML文档的解析模式：如果客户端代码为当前ParsingTask实例关联了一个Executor实例（即ParsingTask.setExecutor方法被执行过），那么我们就将解析模式设置为异步解析，否则就将解析模式设置为同步解析。

然后我们以task为参数创建相应的FutureTask实例ft：在异步解析模式下，我们创建一个FutureTask的匿名子类，并在该子类的done()中实现XML解析结果的回调（Callback）处理——若解析成功则以解析结果（org.w3c.dom.Document）为参数调用ResultHandler.onSuccess方法，若解析失败则调用ResultHandler.onError方法；在同步解析模式下，我们直接通过new创建一个FutureTask实例。

接下来便是安排ft的执行：在异步解析模式下，我们直接通过new创建一个FutureTask实例。接下来便是安排ft的执行；在异步解析模式下，我们会将ft交给指定的Executor实例来执行；在同步解析模式下，我们直接调用ft.run()来执行XML解析任务。此后，ParsingTask.executor()直接返回ft。

使用XMLDocumentParser以异步方式进行XML解析，我们只需要：

```java
private Executor executor = ...
// ...
URL url = "http://yourhost/data/feed.xml";
XMLDocumentParser.newTask(url).setExecutor(executor).setResultHandler{
    new ResultHandler(){
        @Override
        public void onSuccess(Document document){
            process(document);
        }
    }
}).execute();
```

这里我们指定了一个ResultHandler以回调的方式去处理XML解析结果。同样是异步解析，我们也可以不指定ResultHandler，而是在程序需要XML解析结果的时候自己通过Future.get()调用来获取：

```java
Future<Document> future = XMLDocumentParser.newTask(url).setExecutor(es).execute();
doSomething(); //执行其他操作
process(future.get());
```

如果要采用同步方式解析XML解析，我们只需要：

```java
Future<Document> future;
future = XMLDocumentParser.newTask(url).execute();
process(future.get());	//直接获取解析结果进行处理
```

由此可见，在不使用ResultHandler的情况下，异步解析方式和同步解析方式的客户端代码编写方式几乎是一样的：异步解析方式比同步方式多了一个ParsingTask.setExecutor方法调用；在异步解析方式下，客户端代码在ParsingTask.execute()调用与Future.get()调用之间往往会执行其他操作，以减少因XML一步解析未完成而导致Future.get()调用造成等待的可能性。

从上述分析可知，FutureTask的使用既可以发挥异步编程的好处，又可以在一定程度上屏蔽同步编程与异步编程之间的差异，这简化了代码。

### 10.2 可重复执行的异步任务

FutureTask基本上是被设计用来表示一次性执行的任务，其内部会维护一个表示任务运行状态（包括未开始运行、已经运行结束等）的状态变量，FutureTask.run()在执行任务处理逻辑前会判断相应任务的运行状态，如果该任务已经被执行过，那么FutureTask.run()会直接返回（并不会抛出异常）。因此，FutureTask实例所代表的任务是无法被重复执行的。这意味着同一个FutureTask实例不能多次提交给Executor实例执行（尽管这样做不会导致异常的抛出）。

FutureTask.runAndReset()能够打破这种限制，使得一个FutureTask实例所代表的任务能够多次被执行。FutureTask.runAndReset()是一个protected方法，它能够执行FutureTask实例所代表的任务但是不记录任务的处理结果。因此，如果同一个对象所表示的任务需要被多次执行，并且我们需要对该任务每次的执行结果进行处理，那么FutureTask仍然是不适用的，此时我们可以考虑使用抽象异步任务类AsyncTask来表示这种任务。

# 设计模式



## 1. 六大设计原则

（参考《设计模式之禅》（第二版）第一部分 大旗不挥，谁敢冲锋——6大设计原则全新解读 P21/824）

### 1.1 单一职责原则

单一职责原则的英文名称是Single Responsibility Principle，简称是SRP。

对于单一职责原则，我的建议是接口一定要做到单一职责，类的设计尽量做到只有一个原因引起变化。

### 1.2 里氏替换原则

在面向对象的语言中， 继承是必不可少的、 非常优秀的语言机制， 它有如下优点：

- 代码共享， 减少创建类的工作量， 每个子类都拥有父类的方法和属性；
- 提高代码的重用性；
- 子类可以形似父类， 但又异于父类， “龙生龙， 凤生凤， 老鼠生来会打洞”是说子拥有父的“种”， “世界上没有两片完全相同的叶子”是指明子与父的不同；
- 提高代码的可扩展性， 实现父类的方法就可以“为所欲为”了， 君不见很多开源框架的扩展接口都是通过继承父类来完成的；
- 提高产品或项目的开放性。

自然界的所有事物都是优点和缺点并存的， 即使是鸡蛋， 有时候也能挑出骨头来， 继承的缺点如下：

- 继承是侵入性的。 只要继承， 就必须拥有父类的所有属性和方法；
- 降低代码的灵活性。 子类必须拥有父类的属性和方法， 让子类自由的世界中多了些约束；
- 增强了耦合性。 当父类的常量、 变量和方法被修改时， 需要考虑子类的修改， 而且在缺乏规范的环境下， 这种修改可能带来非常糟糕的结果——大段的代码需要重构。 

从整体上来看， 利大于弊， 怎么才能让“利”的因素发挥最大的作用， 同时减少“弊”带来的麻烦呢？ 解决方案是引入里氏替换原则（Liskov Substitution Principle， LSP） ， 什么是里氏替换原则呢？ 它有两种定义：

- 第一种定义， 也是最正宗的定义： If for each object o1 of type S there is an object o2 of type T such that for all programs P defined in terms of T,the behavior of P is unchanged when o1 is substituted for o2 then S is a subtype of T.（如果对每一个类型为S的对象o1， 都有类型为T的对象o2， 使得以T定义的所有程序P在所有的对象o1都代换成o2时， 程序P的行为没有发生变化， 那么类型S是类型T的子类型。 ）
- 第二种定义： Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it.（所有引用基类的地方必须能透明地使用其子类的对象。 ）

第二个定义是最清晰明确的， 通俗点讲， 只要父类能出现的地方子类就可以出现， 而且替换为子类也不会产生任何错误或异常， 使用者可能根本就不需要知道是父类还是子类。 但是， 反过来就不行了， 有子类出现的地方， 父类未必就能适应。 

1. 子类中方法的前置条件必须与超类中被覆写的方法的前置条件相同或者更宽松。 
2. 父类的一个方法的返回值是一个类型T， 子类的相同方法（重载或覆写） 的返回值为S， 那么里氏替换原则就要求S必须小于等于T， 也就是说， 要么S和T是同一个类型， 要么S是T的子类 。

在项目中， 采用里氏替换原则时， 尽量避免子类的“个性”， 一旦子类有“个性”， 这个子类和父类之间的关系就很难调和了， 把子类当做父类使用， 子类的“个性”被抹杀——委屈了点； 把子类单独作为一个业务来使用， 则会让代码间的耦合关系变得扑朔迷离——缺乏类替换的标准 

### 1.3 依赖倒置原则

依赖倒置原则（Dependence Inversion Principle,DIP） 这个名字看着有点别扭， “依赖”还“倒置”， 这到底是什么意思？ 依赖倒置原则的原始定义是：

High level modules should not depend upon low level modules. Both should depend upon abstractions. Abstractions should not depend upon details. Details should depend upon abstractions.

翻译过来， 包含三层含义：

- 高层模块不应该依赖低层模块， 两者都应该依赖其抽象；
- 抽象不应该依赖细节；
- 细节应该依赖抽象。 

依赖倒置原则在Java语言中的表现就是：

- 模块间的依赖通过抽象发生， 实现类之间不发生直接的依赖关系， 其依赖关系是通过接口或抽象类产生的；
- 接口或抽象类不依赖于实现类；
- 实现类依赖接口或抽象类。

更加精简的定义就是“面向接口编程”——OOD（Object-Oriented Design， 面向对象设计） 的精髓之一。 

采用依赖倒置原则可以减少类间的耦合性， 提高系统的稳定性， 降低并行开发引起的风险， 提高代码的可读性和可维护性。 



对象的依赖关系有三种方式来传递 

1. 构造函数传递依赖对象 ，这种方式叫做构造函数注入 
2. Setter方法传递依赖对象 ，这是Setter依赖注入 
3. 接口声明依赖对象 ，该方法也叫做接口注入。 

我们怎么在项目中使用这个规则呢？ 只要遵循以下的几个规则就可以： 

- 每个类尽量都有接口或抽象类， 或者抽象类和接口两者都具备 
- 变量的表面类型尽量是接口或者是抽象类 
- 任何类都不应该从具体类派生 
- 尽量不要覆写基类的方法 
- 结合里氏替换原则使用 

### 1.4 接口隔离原则

它有两种定义， 如下所示：

- Clients should not be forced to depend upon interfaces that they don't use.（客户端不应该依赖它不需要的接口。 ）
- The dependency of one class to another one should depend on the smallest possible interface.（类间的依赖关系应该建立在最小的接口上。 ）

新事物的定义一般都比较难理解， 晦涩难懂是正常的。 我们把这两个定义剖析一下， 先说第一种定义： “客户端不应该依赖它不需要的接口”， 那依赖什么？ 依赖它需要的接口， 客户端需要什么接口就提供什么接口， 把不需要的接口剔除掉， 那就需要对接口进行细化， 保证其纯洁性； 再看第二种定义： “类间的依赖关系应该建立在最小的接口上”， 它要求是最小的接口， 也是要求接口细化， 接口纯洁， 与第一个定义如出一辙， 只是一个事物的两种不同描述。 

我们可以把这两个定义概括为一句话： 建立单一接口， 不要建立臃肿庞大的接口。 再通俗一点讲： 接口尽量细化， 同时接口中的方法尽量少。  

看到这里大家有可能要疑惑了， 这与单一职责原则不是相同的吗？ 错， 接口隔离原则与单一职责的审视角度是不相同的， 单一职责要求的是类和接口职责单一， 注重的是职责， 这是业务逻辑上的划分， 而接口隔离原则要求接口的方法尽量少。 例如一个接口的职责可能包含10个方法， 这10个方法都放在一个接口中， 并且提供给多个模块访问， 各个模块按照规定的权限来访问， 在系统外通过文档约束“不使用的方法不要访问”， 按照单一职责原则是允许的， 按照接口隔离原则是不允许的，因为它要求“尽量使用多个专门的接口”。 专门的接口指什么？ 就是指提供给每个模块的都应该是单一接口， 提供给几个模块就应该有几个接口， 而不是建立一个庞大的臃肿的接口， 容纳所有的客户端访问。 

接口隔离原则是对接口的定义， 同时也是对类的定义， 接口和类尽量使用原子接口或原子类来组装。 但是， 这个原子该怎么划分是设计模式中的一大难题， 在实践中可以根据以下几个规则来衡量：

- 一个接口只服务于一个子模块或业务逻辑；
- 通过业务逻辑压缩接口中的public方法， 接口时常去回顾， 尽量让接口达到“满身筋骨肉”， 而不是“肥嘟嘟”的一大堆方法；
- 已经被污染了的接口， 尽量去修改， 若变更的风险较大， 则采用适配器模式进行转化处理；
- 了解环境， 拒绝盲从。 每个项目或产品都有特定的环境因素， 别看到大师是这样做的你就照抄。 千万别， 环境不同， 接口拆分的标准就不同。 深入了解业务逻辑， 最好的接口设计就出自你的手中！ 

### 1.5 迪米特法则

迪米特法则（Law of Demeter， LoD） 也称为最少知识原则（Least Knowledge Principle， LKP） ， 虽然名字不同， 但描述的是同一个规则： 一个对象应该对其他对象有最少的了解。 通俗地讲， 一个类应该对自己需要耦合或调用的类知道得最少， 你（被耦合或调用的类） 的内部是如何复杂都和我没关系， 那是你的事情， 我就知道你提供的这么多public
方法， 我就调用这么多， 其他的我一概不关心 

迪米特法则的核心观念就是类间解耦， 弱耦合， 只有弱耦合了以后， 类的复用率才可以提高。 其要求的结果就是产生了大量的中转或跳转类， 导致系统的复杂性提高， 同时也为维护带来了难度。 读者在采用迪米特法则时需要反复权衡， 既做到让结构清晰， 又做到高内聚低耦合。 

### 1.6 开闭原则

开闭原则的定义：

Software entities like classes,modules and functions should be open for extension but closed for modifications.（一个软件实体如类、 模块和函数应该对扩展开放， 对修改关闭。 ） 



为什么要采用开闭原则：

首先， 开闭原则非常著名， 只要是做面向对象编程的， 甭管是什么语言， Java也好， C++也好， 或者是Smalltalk， 在开发时都会提及开闭原则。

其次， 开闭原则是最基础的一个原则， 前五章节介绍的原则都是开闭原则的具体形态，也就是说前五个原则就是指导设计的工具和方法， 而开闭原则才是其精神领袖。 

最后，开闭原则是非常重要的：开闭原则可以提高复用性 ；开闭原则可以提高可维护性 ；



如何使用开闭原则：

1. 抽象约束：
   抽象是对一组事物的通用描述， 没有具体的实现， 也就表示它可以有非常多的可能性，可以跟随需求的变化而变化。 因此， 通过接口或抽象类可以约束一组可能变化的行为， 并且能够实现对扩展开放， 其包含三层含义： 
   第一， 通过接口或抽象类约束扩展， 对扩展进行边界限定， 不允许出现在接口或抽象类中不存在的public方法； 
   第二， 参数类型、 引用对象尽量使用接口或者抽象类， 而不是实现类；
   第三， 抽象层尽量保持稳定， 一旦确定即不允许修改。 
2. 元数据（metadata） 控制模块行为 ：
   编程是一个很苦很累的活， 那怎么才能减轻我们的压力呢？ 答案是尽量使用元数据来控制程序的行为， 减少重复开发。 什么是元数据？ 用来描述环境和数据的数据， 通俗地说就是配置参数， 参数可以从文件中获得， 也可以从数据库中获得。 其中达到极致的就是控制反转（Inversion of Control） 。
3. 制定项目章程 ：
   在一个团队中， 建立项目章程是非常重要的， 因为章程中指定了所有人员都必须遵守的约定， 对项目来说， 约定优于配置。  
4. 封装变化：
   对变化的封装包含两层含义： 
   第一， 将相同的变化封装到一个接口或抽象类中；
   第二，将不同的变化封装到不同的接口或抽象类中， 不应该有两个不同的变化出现在同一个接口或抽象类中。
   封装变化， 也就是受保护的变化（protected variations） ， 找出预计有变化或不稳定的点， 我们为这些变化点创建稳定的接口， 准确地讲是封装可能发生的变化， 一旦预测到或“第六感”发觉有变化， 就可以进行封装， 23个设计模式都是从各个不同的角度对变化进行封装的， 我们会在各个模式中逐步讲解。 



## 2. 单例模式

（参考[《在java中写出完美的单例模式》](https://www.cnblogs.com/dongyu666/p/6971783.html) 、《Effective Java》（第3版）第3条：用私有构造器或者枚举类型强化Singleton属性 P24/306、[《为什么最好的单例模式是枚举单例》](https://blog.csdn.net/qq_36642340/article/details/82055786)、[《Java并发笔记——单例与双重检测》](https://www.cnblogs.com/NaLanZiYi-LinEr/p/7492571.html)）

### 2.1 懒汉式(延迟加载)

#### 简单版本

```java
public class Singleton{
    private static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance==null){
            instance = new Singleton();
        }
        return instance;
    }
}
```

问题在于，当多线程工作的时候，如果有多个线程同时运行到if (instance == null)，都判断为null，那么两个线程就各自会创建一个实例——这样一来，就不是单例了。

#### synchronized版本

```java
public class Singleton{
    private static Singleton instance;
    private Singleton(){}
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
```

这种写法也有一个问题：给getInstance方法加锁，虽然会避免了可能会出现的多个实例问题，但是会强制除进入临界区的线程之外的所有线程等待，实际上会对程序的执行效率造成负面影响。

#### 双重检查版本

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Single3 getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

但是这种双重检测机制在JDK1.5之前是有问题的。要弄清楚为什么这个版本可能出现的问题，首先，我们需要弄清楚几个概念：原子操作、指令重排。

简单来说，原子操作（atomic）就是不可分割的操作，在计算机中，就是指不会因为线程调度被打断的操作。

指令重排简单来说，就是计算机为了提高执行效率，会做的一些优化，在不影响最终结果的情况下，可能会对一些语句的执行顺序进行调整。

问题主要在于instance = new Singleton()这句，这并非是一个原子操作：

1. 给 singleton 分配内存
2. 调用 Singleton 的构造函数来初始化成员变量，形成实例
3. 将singleton对象指向分配的内存空间（执行完这步 singleton才是非 null 了）

但是在 JVM 的即时编译器中存在指令重排序的优化。也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。

这里的关键在于——线程T1对instance的写操作没有完成，线程T2就执行了读操作。

#### 终极版本

```java
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

 JDK1.5之后，可以使用volatile关键字修饰变量来解决无序写入产生的问题，因为volatile关键字的一个重要作用是禁止指令重排序，即保证不会出现内存分配、返回对象引用、初始化这样的顺序，从而使得双重检测真正发挥作用。把instance声明为volatile之后，对它的写操作就会有一个内存屏障（什么是内存屏障？），这样，在它的赋值完成之前，就不用会调用读操作。

注意：volatile阻止的不singleton = new Singleton()这句话内部[1-2-3]的指令重排，而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）。

不过，非要挑点刺的话还是能挑出来的，就是这个写法有些复杂了，不够优雅、简洁。

### 2.2 饿汉式（非延迟加载）

如上所说，饿汉式单例是指：指全局的单例实例在类装载时构建的实现方式。

由于类装载的过程是由类加载器（ClassLoader）来执行的，这个过程也是由JVM来保证同步的，所以这种方式先天就有一个优势——能够免疫许多由多线程引起的问题。

#### 公有域方法

```java
public class Singleton{
    public static final Singleton INSTANCE = new Singleton();
    private Singleton(){}
}
```

要提醒一点：享有特权的客户端可以借助AccessibleObject.setAccessable方法，通过反射机制调用私有构造器。如果要抵御这种攻击，可以修改构造器，让它在被要求创建第二个实例的时候抛出异常。

公有域方法的主要优势在于，API很清楚地表明了这个类是一个Singleton：公有的静态域是final的，所以该域总是包含相同的对象引用。第二个优势在于它很简单。

#### 静态工厂方法

```java
public class Singleton{
    private static final Singleton INSTANCE = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return INSTANCE;
    }
}
```

静态工厂方法的优势之一在于，它提供了灵活性：在不改变其API的前提下，我们可以改变这个类是否应该为Singleton的想法。工厂方法返回该类的唯一实例，但是，它很容易被修改，比如改成为每个调用该方法的线程返回一个唯一的实例。第二个优势是，如果应用程序需要，可以编写一个泛型Singleton工厂。使用静态工厂的最后一个优势是，可以通过方法引用作为提供者，比如Singleton::instance就是一个Supplier<Singleton\>。除非满足以上任意一种优势，否则还是优先考虑公有域的方法。



它们的缺点也就只是饿汉式单例本身的缺点所在了——由于INSTANCE的初始化是在类加载时进行的，而类的加载是由ClassLoader来做的，所以开发者本来对于它初始化的时机就很难去准确把握：

1. 可能由于初始化的太早，造成资源的浪费
2. 如果初始化本身依赖于一些其他数据，那么也就很难保证其他数据会在它初始化之前准备好。

当然，如果所需的单例占用的资源很少，并且也不依赖于其他数据，那么这种实现方式也是很好的。

### 2.3 Effective Java的写法

#### 静态内部类

《Effective Java》一书的第一版中推荐了一个写法：

```java
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

这种写法非常巧妙：

- 对于内部类SingletonHolder，它是一个饿汉式的单例实现，在SingletonHolder初始化的时候会由ClassLoader来保证同步，使INSTANCE是一个真·单例。 
- 同时，由于SingletonHolder是一个内部类，只在外部类的Singleton的getInstance()中被使用，所以它被加载的时机也就是在getInstance()方法第一次被调用的时候。

——它利用了ClassLoader来保证了同步，同时又能让开发者控制类加载的时机。从内部看是一个饿汉式的单例，但是从外部看来，又的确是懒汉式的实现。

简直是神乎其技。

#### 枚举

实现Singleton还可以声明一个包含单个元素的枚举类型：

```java
// Effective Java 第二版推荐写法
public enum SingleInstance {
    INSTANCE;
    public void fun1() { 
        // do something
    }
}
// 使用
SingleInstance.INSTANCE.fun1();
```

由于创建枚举实例的过程是线程安全的，所以这种写法也没有同步的问题。

枚举类还能能防止利用反射方式获取枚举对象，调用反射newInstance方法时会检查是否为枚举类，如果是将报错，错误如下：`Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects`

枚举类能防止使用序列化与反序列化获取新的枚举对象。在序列化的时候Java仅仅是将枚举对象的name属性输出到结果中，反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象。同时，编译器是不允许任何对这种序列化机制的定制的，因此禁用了writeObject、readObject、readObjectNoData、writeReplace和readResolve等方法。

Effective Java（第3版）中的评价：这种方法在功能上与公有域方法相似，但更加简洁，无偿提供了序列化机制，绝对防止多次实例化，即使是在面对复杂的序列化或者反射攻击的时候。虽然这种方法还没有广泛采用，但是单元素的枚举类型经常称为实现Singleton的最佳方法。注意，如果Singleton必须扩展一个超类，而不是扩展Enum的时候，则不宜使用这个方法（虽然可以声明枚举去实现接口）。

### 2.4 单例模式的应用

（参考《设计模式之禅》（第二版）7.3 单例模式的应用 P114/824）

#### 2.4.1 单例模式的优点

- 由于单例模式在内存中只有一个实例， 减少了内存开支， 特别是一个对象需要频繁地创建、 销毁时， 而且创建或销毁时性能又无法优化， 单例模式的优势就非常明显。
- 由于单例模式只生成一个实例， 所以减少了系统的性能开销， 当一个对象的产生需要比较多的资源时， 如读取配置、 产生其他依赖对象时， 则可以通过在应用启动时直接产生一个单例对象， 然后用永久驻留内存的方式来解决（在Java EE中采用单例模式时需要注意JVM垃圾回收机制） 。
- 单例模式可以避免对资源的多重占用， 例如一个写文件动作， 由于只有一个实例存在内存中， 避免对同一个资源文件的同时写操作。
- 单例模式可以在系统设置全局的访问点， 优化和共享资源访问， 例如可以设计一个单例类， 负责所有数据表的映射处理。 

#### 2.4.2 单例模式的缺点

- 单例模式一般没有接口， 扩展很困难， 若要扩展， 除了修改代码基本上没有第二种途径可以实现。 单例模式为什么不能增加接口呢？ 因为接口对单例模式是没有任何意义的， 它要求“自行实例化”， 并且提供单一实例、 接口或抽象类是不可能被实例化的。 当然， 在特殊情况下， 单例模式可以实现接口、 被继承等， 需要在系统开发中根据环境判断。
- 单例模式对测试是不利的。 在并行开发环境中， 如果单例模式没有完成， 是不能进行测试的， 没有接口也不能使用mock的方式虚拟一个对象。
- 单例模式与单一职责原则有冲突。 一个类应该只实现一个逻辑， 而不关心它是否是单例的， 是不是要单例取决于环境， 单例模式把“要单例”和业务逻辑融合在一个类中。 

#### 2.4.3 单例模式的使用场景

在一个系统中， 要求一个类有且仅有一个对象， 如果出现多个对象就会出现“不良反应”， 可以采用单例模式， 具体的场景如下：

- 要求生成唯一序列号的环境；
- 在整个项目中需要一个共享访问点或共享数据， 例如一个Web页面上的计数器， 可以不用把每次刷新都记录到数据库中， 使用单例模式保持计数器的值， 并确保是线程安全的；
- 创建一个对象需要消耗的资源过多， 如要访问IO和数据库等资源；
- 需要定义大量的静态常量和静态方法（如工具类） 的环境， 可以采用单例模式（当然， 也可以直接声明为static的方式） 

#### 2.4.4 单例模式的注意事项

首先， 在高并发情况下， 请注意单例模式的线程同步问题。  解决线程不安全的方法很有多， 可以在getSingleton方法前加synchronized关键字， 也可以在getSingleton方法内增加synchronized来实现， 但都不是最优秀的单例模式， 建议读者使用如代码清单7-3所示的方式（有的书上把代码清单7-3中的单例称为饿汉式单例， 在代码清单7-4中增加了synchronized的单例称为懒汉式单例） 。 

其次， 需要考虑对象的复制情况。 在Java中， 对象默认是不可以被复制的， 若实现了Cloneable接口， 并实现了clone方法， 则可以直接通过对象复制方式创建一个新对象， 对象复制是不用调用类的构造函数， 因此即使是私有的构造函数， 对象仍然可以被复制。 在一般情况下， 类复制的情况不需要考虑， 很少会出现一个单例类会主动要求被复制的情况， 解决该问题的最好方法就是单例类不要实现Cloneable接口。 

### 2.5 最佳实践

单例模式是23个模式中比较简单的模式， 应用也非常广泛， 如在Spring中， 每个Bean默认就是单例的， 这样做的优点是Spring容器可以管理这些Bean的生命期， 决定什么时候创建出来， 什么时候销毁， 销毁的时候要如何处理， 等等。 如果采用非单例模式（Prototype类型） ， 则Bean初始化后的管理交由J2EE容器， Spring容器不再跟踪管理Bean的生命周期。 

## 3. 工厂方法模式

（参考《设计模式之禅》（第二版）第8章 工厂方法模式 P121/824）

### 3.1 工厂方法模式的定义

工厂方法模式使用的频率非常高， 在我们日常的开发中总能见到它的身影。 其定义为：

Define an interface for creating an object,but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.（定义一个用于创建对象的接口， 让子类决定实例化哪一个类。 工厂方法使一个类的实例化延迟到其子类。 ） 

![1567675729867](E:\个人文件\博客\java面试题解析.assets\1567675729867.png)

在工厂方法模式中，抽象产品类Product负责定义产品的共性， 实现对事物最抽象的定义； Creator为抽象创建类， 也就是抽象工厂， 具体如何创建产品类是由具体的实现工厂ConcreteCreator完成的。 工厂方法模式的变种较多， 我们来看一个比较实用的通用源码。 

抽象产品类：

~~~java
public abstract class Product{
    //产品类的公共方法
    public void method1(){
        //业务逻辑处理
    }
    //抽象方法
    public abstract void method2();
}
~~~

具体的产品类可以有多个，都继承于抽象产品类，其源代码如下：

具体产品类：

~~~java
public class ConcreteProduct1 extends Product{
    public void method2(){
        //业务逻辑处理
    }
}
public class ConcreteProduct2 extends Product{
	public void method2(){
        //业务逻辑处理
    }
}
~~~

抽象工厂类负责定义产品对象的产生，源代码如下：

抽象工厂类：

~~~java
public abstract class Creator{
    /*创建一个产品对象，其输入参数类型可以自行设置
    通常为String、Enum、Class等，当然也可以为空*/
    public abstract <T extends Product> T createProduct(Class<T> c);
}
~~~

具体如何产生一个产品的对象，是由具体的工厂类实现的，如下：

具体工厂类：

~~~java
public class ConcreteCreator extends Creator{
    public <T extends Product> T createProduct(Class<T>){
        Product product = null;
        try{
            product = (Product)Class.forName(c.getName()).newInstance();
        }catch(Exception e){
            //异常处理
        }
        return (T)product;
    }
}
~~~

场景类的调用方法如下：

~~~java
public class Client{
    public static void main(String[] args){
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        /*继续业务处理*/
    }
}
~~~

### 3.2 工厂方法模式的优点

首先， 良好的封装性， 代码结构清晰。 一个对象创建是有条件约束的， 如一个调用者需要一个具体的产品对象， 只要知道这个产品的类名（或约束字符串） 就可以了， 不用知道创建对象的艰辛过程， 降低模块间的耦合。

其次， 工厂方法模式的扩展性非常优秀。 在增加产品类的情况下， 只要适当地修改具体的工厂类或扩展一个工厂类， 就可以完成“拥抱变化”。 例如在我们的例子中， 需要增加一个棕色人种， 则只需要增加一个BrownHuman类， 工厂类不用任何修改就可完成系统扩展。

再次， 屏蔽产品类。 这一特点非常重要， 产品类的实现如何变化， 调用者都不需要关心， 它只需要关心产品的接口， 只要接口保持不变， 系统中的上层模块就不要发生变化。 因为产品类的实例化工作是由工厂类负责的， 一个产品对象具体由哪一个产品生成是由工厂类决定的。 在数据库开发中， 大家应该能够深刻体会到工厂方法模式的好处： 如果使用JDBC连接数据库， 数据库从MySQL切换到Oracle， 需要改动的地方就是切换一下驱动名称（前提条件是SQL语句是标准语句） ， 其他的都不需要修改， 这是工厂方法模式灵活性的一个直接案例。

最后， 工厂方法模式是典型的解耦框架。 高层模块值需要知道产品的抽象类， 其他的实现类都不用关心， 符合迪米特法则， 我不需要的就不要去交流； 也符合依赖倒置原则， 只依赖产品类的抽象； 当然也符合里氏替换原则， 使用产品子类替换产品父类， 没问题！ 

### 3.3 工厂方法模式的使用场景

首先， 工厂方法模式是new一个对象的替代品， 所以在所有需要生成对象的地方都可以使用， 但是需要慎重地考虑是否要增加一个工厂类进行管理， 增加代码的复杂度。

其次， 需要灵活的、 可扩展的框架时， 可以考虑采用工厂方法模式。 万物皆对象， 那万物也就皆产品类， 例如需要设计一个连接邮件服务器的框架， 有三种网络协议可供选择：POP3、 IMAP、 HTTP， 我们就可以把这三种连接方法作为产品类， 定义一个接口如IConnectMail， 然后定义对邮件的操作方法， 用不同的方法实现三个具体的产品类（也就是连接方式） 再定义一个工厂方法， 按照不同的传入条件， 选择不同的连接方式。 如此设计，可以做到完美的扩展， 如某些邮件服务器提供了WebService接口， 很好， 我们只要增加一个产品类就可以了。

再次， 工厂方法模式可以用在异构项目中， 例如通过WebService与一个非Java的项目交互， 虽然WebService号称是可以做到异构系统的同构化， 但是在实际的开发中， 还是会碰到很多问题， 如类型问题、 WSDL文件的支持问题， 等等。 从WSDL中产生的对象都认为是一个产品， 然后由一个具体的工厂类进行管理， 减少与外围系统的耦合。

最后， 可以使用在测试驱动开发的框架下。 例如， 测试一个类A， 就需要把与类A有关联关系的类B也同时产生出来， 我们可以使用工厂方法模式把类B虚拟出来， 避免类A与类B的耦合。 目前由于JMock和EasyMock的诞生， 该使用场景已经弱化了， 读者可以在遇到此种情况时直接考虑使用JMock或EasyMock。 

### 3.4 工厂方法模式的扩展

工厂方法模式有很多扩展，而且与其他模式结合使用威力更大，下面将介绍4种扩展。

#### 3.4.1 缩小为简单工厂模式

我们这样考虑一个问题： 一个模块仅需要一个工厂类， 没有必要把它产生出来， 使用静态的方法就可以了 

简单工厂模式中的工厂类：

~~~java
public class HumanFactory{
    public static <T extends Human> T createHuman(Class<T> c){
        //定义一个生产出的人种
        Human human = null;
        try{
            //产生一个人种
            human = (Human)Class.forName(c.getName()).newInstance();
        }catch(Exception e){
            System.out.println("人种生成错误！");
        }
        return (T)human;
    }
}
~~~

HumanFactory类仅有两个地方发生变化： 去掉继承抽象类， 并在createHuman前增加static关键字； 工厂类发生变化， 也同时引起了调用者NvWa的变化， 如下所示。

 ~~~java
public class NvWa{
    public static void main(String[] args){
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
        
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
 ~~~

运行结果没有发生变化， 但是我们的类图变简单了， 而且调用者也比较简单， 该模式是工厂方法模式的弱化， 因为简单， 所以称为简单工厂模式（Simple Factory Pattern） ， 也叫做静态工厂模式。 在实际项目中， 采用该方法的案例还是比较多的， 其缺点是工厂类的扩展比较困难， 不符合开闭原则， 但它仍然是一个非常实用的设计模式。

#### 3.4.2 升级为多个工厂类

当我们在做一个比较复杂的项目时， 经常会遇到初始化一个对象很耗费精力的情况， 所有的产品类都放到一个工厂方法中进行初始化会使代码结构不清晰。 例如， 一个产品类有5个具体实现， 每个实现类的初始化（不仅仅是new， 初始化包括new一个对象， 并对对象设置一定的初始值） 方法都不相同， 如果写在一个工厂方法中， 势必会导致该方法巨大无比，那该怎么办？ 

考虑到需要结构清晰， 我们就为每个产品定义一个创造者， 然后由调用者自己去选择与哪个工厂方法关联。  

每个人种（具体的产品类） 都对应了一个创建者， 每个创建者都独立负责创建对应的产品对象， 非常符合单一职责原则， 按照这种模式我们来看看代码变化。 

多工厂模式的抽象工厂类如下所示：

 ~~~java
public abstract class AbstractHumanFactory{
    public abstract Human createHuman();
}
 ~~~

注意 抽象方法中已经不再需要传递相关参数了， 因为每一个具体的工厂都已经非常明确自己的职责： 创建自己负责的产品类对象。 

三个具体的创建工厂都非常简单， 但是， 如果一个系统比较复杂时工厂类也会相应地变
复杂。 场景类NvWa修改后的代码如下所示。 

~~~java
public class NvWa{
    public static void main(String[] args){
        Human whiteHuman = (new WhiteHumanFactory()).createHuman();
        whiteHuman.getColor();
        whiteHuman.talk();
        
        Human blackHuman = (new BlackHumanFactory()).createHuman();
        blackHuman.getColor();
        blackHuman.talk();
        
        Human yellowHuman = (new YellowHumanFactory()).createHuman();
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
~~~

我们回顾一下， 每一个产品类都对应了一个创建类， 好处就是创建类的职责清晰， 而且结构简单， 但是给可扩展性和可维护性带来了一定的影响。 为什么这么说呢？ 如果要扩展一个产品类， 就需要建立一个相应的工厂类， 这样就增加了扩展的难度。因为工厂类和产品类的数量相同， 维护时需要考虑两个对象之间的关系。

当然， 在复杂的应用中一般采用多工厂的方法， 然后再增加一个协调类， 避免调用者与各个子工厂交流， 协调类的作用是封装子工厂类， 对高层模块提供统一的访问接口。 

#### 3.4.3 代替单例模式

第7章讲述了单例模式以及扩展出的多例模式， 并且指出了单例和多例的一些缺点， 我们是不是可以采用工厂方法模式实现单例模式的功能呢？ 单例模式的核心要求就是在内存中只有一个对象， 通过工厂方法模式也可以只在内存中生产一个对象。

Singleton定义了一个private的无参构造函数， 目的是不允许通过new的方式创建一个对象 

单例类：

~~~java
public class Singleton{
    //不允许通过new产生一个对象
    private Singleton(){
        
    }
    public void dosomething(){
        //业务处理
    }
}
~~~

Singleton保证不能通过正常的渠道建立一个对象， 那SingletonFactory如何建立一个单例对象呢？ 答案是通过反射方式创建。



#### 3.4.4 延迟初始化

何为延迟初始化（Lazy initialization） ？ 一个对象被消费完毕后， 并不立刻释放， 工厂类保持其初始状态， 等待再次被使用。  



## 4. 抽象工厂模式

（参考《设计模式之禅》（第二版）第9章 抽象工厂方法模式 P141/824）

### 4.1 抽象工厂模式的定义

抽象工厂模式（Abstract Factory Pattern） 是一种比较常用的模式， 其定义如下：

Provide an interface for creating families of related or dependent objects without specifying their concrete classes.（为创建一组相关或相互依赖的对象提供一个接口， 而且无须指定它们的具体类。 ） 

![1567730177433](E:\个人文件\博客\java面试题解析.assets\1567730177433.png)

抽象工厂模式是工厂方法模式的升级版本， 在有多个业务品种、 业务分类时， 通过抽象工厂模式产生需要的对象是一种非常好的解决方式。 

抽象产品类：

~~~java
public abstract class AbstractProductA{
    //每个产品共有的方法
    public void shareMethod(){
        
    }
    //每个产品相同方法，不同实现
    public abstract void doSomething();
}
~~~

两个具体的产品实现类如下所示。

产品A1(A2：将A1改为A2)的实现类：

~~~java
public class ProductA1 extends AbstractProductA {
    public void doSomething(){
        System.out.println("产品A1的实现方法");
    }
}
~~~

产品B与此类似，不再赘述。抽象工厂类AbstractCreator的职责是定义每个工厂要实现的功能，在通用代码中，抽象工厂类定义了两个产品族的产品创建，如下所示。

抽象工厂类：

~~~java
public abstract class AbstractCreator{
    //创建A产品家族
    public abstract AbstractProductA createProductA();
    //创建B产品家族
    public abstract AbstractProductB createProductB();
}
~~~

注意 有N个产品族，在抽象工厂类中就应该有N个创建方法。

如何创建一个产品， 则是由具体的实现类来完成的， Creator1和Creator2如下所示。 

产品等级1（2：将1改为2）的实现类：

~~~java
public class Creator1 extends AbstractCreator {
    //只生产产品等级为1的A产品
    public AbstractProductA createProductA(){
        return new ProductA1();
    }
    //只生产产品等级为1的B产品
    public AbstractProductB createProductB(){
        return new ProductB1();
    }
}
~~~

注意 有M个产品等级就应该有M个实现工厂类，在每个实现工厂中，实现不同产品族的生产任务。

场景类：

~~~java
public class Client{
    public static void main(String[] args){
        //定义出两个工厂
        AbstractCreator creator1 = new Creator1();
        AbstractCreator creator2 = new Creator2();
        //产生A1对象
        AbstractProductA a1 = creator1.createProductA();
        //产生A2对象
        AbstractProductA a2 = creator2.createProductA();
        //产生A1对象
        AbstractProductB b1 = creator1.createProductB();
        //产生A2对象
        AbstractProductB b2 = creator2.createProductB();
        /*然后在这里就可以为所欲为了……*/
    }
}
~~~

在场景类中， 没有任何一个方法与实现类有关系， 对于一个产品来说， 我们只要知道它的工厂方法就可以直接产生一个产品对象， 无须关心它的实现类。 

### 4.2 抽象工厂模式的优点

- 封装性， 每个产品的实现类不是高层模块要关心的， 它要关心的是什么？ 是接口， 是抽象， 它不关心对象是如何创建出来， 这由谁负责呢？ 工厂类， 只要知道工厂类是谁， 我就能创建出一个需要的对象， 省时省力， 优秀设计就应该如此。
- 产品族内的约束为非公开状态。 例如生产男女比例的问题上， 猜想女娲娘娘肯定有自己的打算， 不能让女盛男衰， 否则女性的优点不就体现不出来了吗？ 那在抽象工厂模式， 就应该有这样的一个约束： 每生产1个女性， 就同时生产出1.2个男性， 这样的生产过程对调用工厂类的高层模块来说是透明的， 它不需要知道这个约束， 我就是要一个黄色女性产品就可以了， 具体的产品族内的约束是在工厂内实现的。 

### 4.3 抽象工厂模式的缺点

抽象工厂模式的最大缺点就是产品族扩展非常困难， 为什么这么说呢？ 我们以通用代码为例， 如果要增加一个产品C， 也就是说产品家族由原来的2个增加到3个， 看看我们的程序有多大改动吧！ 抽象类AbstractCreator要增加一个方法createProductC()， 然后两个实现类都要修改， 想想看， 这严重违反了开闭原则， 而且我们一直说明抽象类和接口是一个契约。 改变契约， 所有与契约有关系的代码都要修改， 那么这段代码叫什么？ 叫“有毒代码”， ——只要与这段代码有关系， 就可能产生侵害的危险！ 

### 4.4 抽象工厂模式的使用场景

抽象工厂模式的使用场景定义非常简单： 一个对象族（或是一组没有任何关系的对象）都有相同的约束， 则可以使用抽象工厂模式。 什么意思呢？ 例如一个文本编辑器和一个图片处理器， 都是软件实体， 但是Unix下的文本编辑器和Windows下的文本编辑器虽然功能和界面都相同， 但是代码实现是不同的， 图片处理器也有类似情况。 也就是具有了共同的约束条件： 操作系统类型。 于是我们可以使用抽象工厂模式， 产生不同操作系统下的编辑器和图片处理器。 

### 4.5 抽象工厂模式的注意事项

在抽象工厂模式的缺点中， 我们提到抽象工厂模式的产品族扩展比较困难， 但是一定要清楚， 是产品族扩展困难， 而不是产品等级。 在该模式下， 产品等级是非常容易扩展的， 增加一个产品等级， 只要增加一个工厂类负责新增加出来的产品生产任务即可。 也就是说横向扩展容易， 纵向扩展困难。 以人类为例子， 产品等级中只有男、 女两个性别， 现实世界还有一种性别： 双性人， 既是男人也是女人（俗语就是阴阳人） ， 那我们要扩展这个产品等级也是非常容易的， 增加三个产品类， 分别对应不同的肤色， 然后再创建一个工厂类， 专门负责不同肤色人的双性人的创建任务， 完全通过扩展来实现需求的变更， 从这一点上看， 抽象工厂模式是符合开闭原则的。 

### 4.6 最佳实践

一个模式在什么情况下才能够使用， 是很多读者比较困惑的地方。 抽象工厂模式是一个简单的模式， 使用的场景非常多， 大家在软件产品开发过程中， 涉及不同操作系统的时候，都可以考虑使用抽象工厂模式， 例如一个应用， 需要在三个不同平台（Windows、 Linux、Android（Google发布的智能终端操作系统） ） 上运行， 你会怎么设计？ 分别设计三套不同的应用？ 非也， 通过抽象工厂模式屏蔽掉操作系统对应用的影响。 三个不同操作系统上的软件功能、 应用逻辑、 UI都应该是非常类似的， 唯一不同的是调用不同的工厂方法， 由不同的产品类去处理与操作系统交互的信息。 

## 5. 模板方法模式

（参考《设计模式之禅》（第二版）第10章 模板方法模式 P155/824）

### 5.1 模板方法模式

模板方法模式（Template Method Pattern） 是如此简单， 以致让你感觉你已经能够掌握其精髓了。 其定义如下：

Define the skeleton of an algorithm in an operation,deferring some steps to subclasses.Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.（定义一个操作中的算法的框架， 而将一些步骤延迟到子类中。 使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。 ） 

![1567732469776](E:\个人文件\博客\java面试题解析.assets\1567732469776.png)

模板方法模式确实非常简单， 仅仅使用了Java的继承机制， 但它是一个应用非常广泛的模式。 其中， AbstractClass叫做抽象模板， 它的方法分为两类：

- 基本方法
  基本方法也叫做基本操作， 是由子类实现的方法， 并且在模板方法被调用。
- 模板方法
  可以有一个或几个， 一般是一个具体方法， 也就是一个框架， 实现对基本方法的调度，
  完成固定的逻辑 

注意 为了防止恶意的操作， 一般模板方法都加上final关键字， 不允许被覆写 

在类图中还有一个角色： 具体模板。 ConcreteClass1和ConcreteClass2属于具体模板， 实现父类所定义的一个或多个抽象方法， 也就是父类定义的基本方法在子类中得以实现。 

抽象模板类：

~~~java
public abstract class AbstractClass{
    //基本方法
    protected abstract void doSomething();
    //基本方法
    protected abstract void doAnything();
    //模板方法
    public void templateMethod(){
        /*调用基本方法，完成相关的逻辑*/
        this.doAnything();
        this.doSomething();
    }
}
~~~

具体模板类：

~~~java
public class ConcreteClass1 extends AbstractClass{
    //实现基本方法
    protected void doAnything(){
        //业务逻辑处理
    }
    protected void doSomething(){
        //业务逻辑处理
    }
}

public class ConcreteClass2 extends AbstractClass{
    //实现基本方法
    protected void doAnything(){
        //业务逻辑处理
    }
    protected void doSomething(){
        //业务逻辑处理
    }
}
~~~

场景类：

~~~java
public class Client {
    public static void main(String[] args){
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        //调用模板方法
        class1.templateMethod();
        class2.templateMethod();
    }
}
~~~

注意 抽象模板中的基本方法尽量设计为protected类型， 符合迪米特法则， 不需要暴露的属性或方法尽量不要设置为protected类型。 实现类若非必要， 尽量不要扩大父类中的访问权限 

### 5.2 模板方法模式的优点

- 封装不变部分， 扩展可变部分
  把认为是不变部分的算法封装到父类实现， 而可变部分的则可以通过继承来继续扩展。
  在悍马模型例子中， 是不是就非常容易扩展？ 例如增加一个H3型号的悍马模型， 很容易
  呀， 增加一个子类， 实现父类的基本方法就可以了。
- 提取公共部分代码， 便于维护
  我们例子中刚刚走过的弯路就是最好的证明， 如果我们不抽取到父类中， 任由这种散乱
  的代码发生， 想想后果是什么样子？ 维护人员为了修正一个缺陷， 需要到处查找类似的代
  码！
- 行为由父类控制， 子类实现
  基本方法是由子类实现的， 因此子类可以通过扩展的方式增加相应的功能， 符合开闭原
  则。 

### 5.3 模板方法模式的缺点

按照我们的设计习惯， 抽象类负责声明最抽象、 最一般的事物属性和方法， 实现类完成具体的事物属性和方法。 但是模板方法模式却颠倒了， 抽象类定义了部分抽象方法， 由子类实现， 子类执行的结果影响了父类的结果， 也就是子类对父类产生了影响， 这在复杂的项目中， 会带来代码阅读的难度， 而且也会让新手产生不适感 

### 5.4 模板方法模式的使用场景

- 多个子类有公有的方法， 并且逻辑基本相同时。
- 重要、 复杂的算法， 可以把核心算法设计为模板方法， 周边的相关细节功能则由各个子类实现。
- 重构时， 模板方法模式是一个经常使用的模式， 把相同的代码抽取到父类中， 然后通过钩子函数（见“模板方法模式的扩展”） 约束其行为。 

### 5.5 模板方法模式的扩展

看到没， H1型号的悍马是由客户自己控制是否要响喇叭， 也就是说外界条件改变， 影响到模板方法的执行。 在我们的抽象类中isAlarm的返回值就是影响了模板方法的执行结果， 该方法就叫做钩子方法（Hook Method） 。 有了钩子方法模板方法模式才算完美， 大家可以想想， 由子类的一个方法返回值决定公共部分的执行结果， 是不是很有吸引力呀！ 

模板方法模式就是在模板方法中按照一定的规则和顺序调用基本方法， 具体到前面那个例子， 就是run()方法按照规定的顺序（先调用start()， 然后再调用engineBoom()， 再调用alarm()， 最后调用stop()） 调用本类的其他方法， 并且由isAlarm()方法的返回值确定run()中的执行顺序变更。 

### 5.6 最佳实践

模板方法在一些开源框架中应用非常多， 它提供了一个抽象类， 然后开源框架写了一堆子类。 在《××× In Action》 中就说明了， 如果你需要扩展功能， 可以继承这个抽象类， 然后覆写protected方法， 再然后就是调用一个类似execute方法， 就完成你的扩展开发， 非常容易扩展的一种模式 



## 6. 建造者模式

（参考《设计模式之禅》（第二版）第11章 建造者模式 P173/824）

### 6.1 建造者模式的定义

建造者模式（Builder Pattern） 也叫做生成器模式， 其定义如下：

Separate the construction of a complex object from its representation so that the same construction process can create different representations.（将一个复杂对象的构建与它的表示分离， 使得同样的构建过程可以创建不同的表示。 ） 

![1567734152081](E:\个人文件\博客\java面试题解析.assets\1567734152081.png)

在建造者模式中， 有如下4个角色：

- Product产品类
  通常是实现了模板方法模式， 也就是有模板方法和基本方法， 这个参考第10章的模板方法模式。 例子中的BenzModel和BMWModel就属于产品类。
- Builder抽象建造者
  规范产品的组建， 一般是由子类实现。 例子中的CarBuilder就属于抽象建造者。
- ConcreteBuilder具体建造者
  实现抽象类定义的所有方法， 并且返回一个组建好的对象。 例子中的BenzBuilder和BMWBuilder就属于具体建造者。
- Director导演类
  负责安排已有模块的顺序， 然后告诉Builder开始建造， 在上面的例子中就是我们的老大， ××公司找到老大， 说我要这个或那个类型的车辆模型， 然后老大就把命令传递给我， 我和我的团队就开始拼命地建造， 于是一个项目建设完毕了。 

建造者模式的通用源代码也比较简单， 先看Product类， 通常它是一个组合或继承（如模板方法模式） 产生的类。

产品类：

~~~java
public class Product {
    public void doSomething(){
        //独立业务处理
    }
}
~~~

抽象建造者：

~~~java
public abstract class Builder{
    //设置产品的不同部分，以获得不同的产品
    public abstract void setPart();
    //建造产品
    public abstract Product buildProduct();
}
~~~

其中， setPart方法是零件的配置， 什么是零件？ 其他的对象， 获得一个不同零件， 或者不同的装配顺序就可能产生不同的产品。 具体的建造者如下所示。 

具体建造者：

~~~java
public class ConcreteProduct extends Builder {
    private Product product = new Product();
    //设置产品零件
    public void setPart(){
        /*产品类内的逻辑处理*/
    }
    //组建一个产品
    public Product buildProduct(){
        return product;
    }
}
~~~

需要注意的是，如果有多个产品类就有几个具体的建造者， 而且这多个产品类具有相同接口或抽象类， 参考我们上面的例子。 

导演类：

~~~java
public class Director{
    private Builder builder = new ConcreteProduct();
    //构建不同的产品
    public Product getAProduct(){
        builder.setPart();
        /*设置不同的零件，产生不同的产品*/
        return builder.buildProduct();
    }
}
~~~

导演类起到封装的作用， 避免高层模块深入到建造者内部的实现类。 当然， 在建造者模式比较庞大时， 导演类可以有多个。 

### 6.2 建造者模式的优点

- 封装性
  使用建造者模式可以使客户端不必知道产品内部组成的细节， 如例子中我们就不需要关心每一个具体的模型内部是如何实现的， 产生的对象类型就是CarModel。
- 建造者独立， 容易扩展
  BenzBuilder和BMWBuilder是相互独立的， 对系统的扩展非常有利。
- 便于控制细节风险
  由于具体的建造者是独立的， 因此可以对建造过程逐步细化， 而不对其他的模块产生任何影响。 

### 6.3 建造者模式的使用场景

- 相同的方法， 不同的执行顺序， 产生不同的事件结果时， 可以采用建造者模式。
- 多个部件或零件， 都可以装配到一个对象中， 但是产生的运行结果又不相同时， 则可以使用该模式。
- 产品类非常复杂， 或者产品类中的调用顺序不同产生了不同的效能， 这个时候使用建造者模式非常合适。
- 在对象创建过程中会使用到系统中的一些其他对象， 这些对象在产品对象的创建过程中不易得到时， 也可以采用建造者模式封装该对象的创建过程。 该种场景只能是一个补偿方法， 因为一个对象不容易获得， 而在设计阶段竟然没有发觉， 而要通过创建者模式柔化创建过程， 本身已经违反设计的最初目标。 

注意  建造者模式关注的是零件类型和装配工艺（顺序） ， 这是它与工厂方法模式最大不同的地方， 虽然同为创建类模式， 但是注重点不同。 

### 6.4 建造者模式的扩展

已经不用扩展了， 因为我们在汽车模型制造的例子中已经对建造者模式进行了扩展， 引入了模板方法模式。 可能大家会比较疑惑， 为什么在其他介绍设计模式的书籍上创建者模式并不是这样说的？ 读者请注意， 建造者模式中还有一个角色没有说明， 就是零件， 建造者怎么去建造一个对象？ 是零件的组装， 组装顺序不同对象效能也不同， 这才是建造者模式要表达的核心意义， 而怎么才能更好地达到这种效果呢？ 引入模板方法模式是一个非常简单而有效的办法。

大家看到这里估计就开始犯嘀咕了， 这个建造者模式和工厂模式非常相似呀， 是的， 非常相似， 但是记住一点你就可以游刃有余地使用了： 建造者模式最主要的功能是基本方法的调用顺序安排， 也就是这些基本方法已经实现了， 通俗地说就是零件的装配， 顺序不同产生的对象也不同； 而工厂方法则重点是创建， 创建零件是它的主要职责， 组装顺序则不是它关心的。 

## 7. 代理模式

（参考《设计模式之禅》（第二版）第12章 代理模式 P192/824）

### 7.1 代理模式的定义

代理模式（Proxy Pattern） 是一个使用率非常高的模式， 其定义如下：

Provide a surrogate or placeholder for another object to control access to it.（为其他对象提供一种代理以控制对这个对象的访问。 ） 

![1567735572844](E:\个人文件\博客\java面试题解析.assets\1567735572844.png)

代理模式也叫做委托模式， 它是一项基本设计技巧。 许多其他的模式， 如状态模式、 策略模式、 访问者模式本质上是在更特殊的场合采用了委托模式， 而且在日常的应用中， 代理模式可以提供非常好的访问控制。 在一些著名开源软件中也经常见到它的身影， 如Struts2的Form元素映射就采用了代理模式（准确地说是动态代理模式） 。  

我们先看一下类图中的三个角色的定义：

- Subject抽象主题角色
  抽象主题类可以是抽象类也可以是接口， 是一个最普通的业务类型定义， 无特殊要求。
- RealSubject具体主题角色
  也叫做被委托角色、 被代理角色。 它才是冤大头， 是业务逻辑的具体执行者。
- Proxy代理主题角色
  也叫做委托类、 代理类。 它负责对真实角色的应用， 把所有抽象主题类定义的方法限制委托给真实主题角色实现， 并且在真实主题角色处理完毕前后做预处理和善后处理工作。 

抽象主题类：

~~~java
public interface Subject {
    //定义一个方法
    public void request();
}
~~~

真实主题类：

~~~java
public class RealSubject implements Subject {
    //实现方法
    public void request(){
        //业务逻辑处理
    }
}
~~~

代理类：

~~~java
public class Proxy implements Subject {
    //要代理哪个实现类
    private Subject subject = null;
    //默认被代理者
    public Proxy(){
        this.subject = new Proxy();
    }
    //通过构造函数传递代理者
    public Proxy(Object... objects){
        
    }
    //实现接口中定义的方法
    public void request(){
        this.before();
        this.subject.request();
        this.after();
    }
    //预处理
    private void before(){
        //do something
    }
    //善后处理
    private void after(){
        //do something
    }
}
~~~

看到这里， 大家别惊讶， 为什么会出现before和after方法， 继续看下去， 这是一个“引子”， 能够引出一个崭新的编程模式。

一个代理类可以代理多个被委托者或被代理者， 因此一个代理类具体代理哪个真实主题角色， 是由场景类决定的。 当然， 最简单的情况就是一个主题类和一个代理类， 这是最简洁的代理模式。 在通常情况下， 一个接口只需要一个代理类就可以了， 具体代理哪个实现类由高层模块来决定， 也就是在代理类的构造函数中传递被代理者， 例如我们可以在代理类Proxy中增加如下所示的构造函数。 

~~~java
public Proxy（Subject _subject）{
    this.subject = _subject;
}
~~~

你要代理谁就产生该代理的实例， 然后把被代理者传递进来， 该模式在实际的项目应用中比较广泛。 

### 7.2 代理模式的优点

- 职责清晰
  真实的角色就是实现实际的业务逻辑， 不用关心其他非本职责的事务， 通过后期的代理完成一件事务， 附带的结果就是编程简洁清晰。
- 高扩展性
  具体主题角色是随时都会发生变化的， 只要它实现了接口， 甭管它如何变化， 都逃不脱如来佛的手掌（接口） ， 那我们的代理类完全就可以在不做任何修改的情况下使用。
- 智能化
  这在我们以上的讲解中还没有体现出来， 不过在我们以下的动态代理章节中你就会看到代理的智能化有兴趣的读者也可以看看Struts是如何把表单元素映射到对象上的。 

### 7.3 代理模式的使用场景

我相信第一次接触到代理模式的读者肯定很郁闷， 为什么要用代理呀？ 想想现实世界吧， 打官司为什么要找个律师？ 因为你不想参与中间过程的是是非非， 只要完成自己的答辩就成， 其他的比如事前调查、 事后追查都由律师来搞定， 这就是为了减轻你的负担。 代理模式的使用场景非常多， 大家可以看看Spring AOP， 这是一个非常典型的动态代理。 

### 7.4 代理模式的扩展

#### 7.4.1 普通代理

在网络上代理服务器设置分为透明代理和普通代理， 是什么意思呢？ 透明代理就是用户不用设置代理服务器地址， 就可以直接访问， 也就是说代理服务器对用户来说是透明的， 不用知道它存在的； 普通代理则是需要用户自己设置代理服务器的IP地址， 用户必须知道代理的存在。 我们设计模式中的普通代理和强制代理也是类似的一种结构， 普通代理就是我们要知道代理的存在， 也就是类似的GamePlayerProxy这个类的存在， 然后才能访问； 强制代理则是调用者直接调用真实角色， 而不用关心代理是否存在， 其代理的产生是由真实角色决定的， 这样的解释还是比较复杂， 我们还是用实例来讲解。

首先说普通代理， 它的要求就是客户端只能访问代理角色， 而不能访问真实角色， 这是比较简单的。 我们以上面的例子作为扩展， 我自己作为一个游戏玩家， 我肯定自己不练级了， 也就是场景类不能再直接new一个GamePlayer对象了， 它必须由GamePlayerProxy来进行模拟场景 

改动很小， 仅仅修改了两个实现类的构造函数， GamePlayer的构造函数增加了_gamePlayer参数， 而代理角色则只要传入代理者名字即可， 而不需要说是替哪个对象做代理。 

在该模式下， 调用者只知代理而不用知道真实的角色是谁， 屏蔽了真实角色的变更对高层模块的影响， 真实的主题角色想怎么修改就怎么修改， 对高层次的模块没有任何的影响， 只要你实现了接口所对应的方法， 该模式非常适合对扩展性要求较高的场合。 当然， 在实际的项目中， 一般都是通过约定来禁止new一个真实的角色， 这也是一个非常好的方案

注意  普通代理模式的约束问题， 尽量通过团队内的编程规范类约束， 因为每一个主题类是可被重用的和可维护的， 使用技术约束的方式对系统维护是一种非常不利的因素。 

####  7.4.2 强制代理

强制代理在设计模式中比较另类， 为什么这么说呢？ 一般的思维都是通过代理找到真实的角色， 但是强制代理却是要“强制”， 你必须通过真实角色查找到代理角色， 否则你不能访问。 甭管你是通过代理类还是通过直接new一个主题角色类， 都不能访问， 只有通过真实角色指定的代理类才可以访问， 也就是说由真实角色管理代理角色。 这么说吧， 高层模块new了一个真实角色的对象， 返回的却是代理角色 

#### 7.4.3 代理是有个性的

一个类可以实现多个接口， 完成不同任务的整合。 也就是说代理类不仅仅可以实现主题接口， 也可以实现其他接口完成不同的任务， 而且代理的目的是在目标对象方法的基础上作增强， 这种增强的本质通常就是对目标对象的方法进行拦截和过滤。 例如游戏代理是需要收费的， 升一级需要5元钱， 这个计算功能就是代理类的个性， 它应该在代理的接口中定义 

代理类不仅仅是可以有自己的运算方法， 通常的情况下代理的职责并不一定单一， 它可以组合其他的真实角色， 也可以
实现自己的职责， 比如计算费用。 代理类可以为真实角色预处理消息、 过滤消息、 消息转发、 事后处理消息等功能。 当然一个代理类， 可以代理多个真实角色， 并且真实角色之间可以有耦合关系， 读者可以自行扩展一下。

#### 7.4.4 动态代理

什么是动态代理？ 动态代理是在实现阶段不用关心代理谁， 而在运行阶段才指定代理哪一个对象。 相对来说， 自己写代理类的方式就是静态代理。 本章节的核心部分就在动态代理上， 现在有一个非常流行的名称叫做面向横切面编程， 也就是AOP（Aspect Oriented Programming） ， 其核心就是采用了动态代理机制 

### 7.5 最佳实践

代理模式应用得非常广泛， 大到一个系统框架、 企业平台， 小到代码片段、 事务处理，稍不留意就用到代理模式。 可能该模式是大家接触最多的模式， 而且有了AOP大家写代理就更加简单了， 有类似Spring AOP和AspectJ这样非常优秀的工具， 拿来主义即可！ 不过， 大家可以看看源代码， 特别是调试时， 只要看到类似$Proxy0这样的结构， 你就应该知道这是一个动态代理了。

友情提醒， 在学习AOP框架时， 弄清楚几个名词就成： 切面（Aspect） 、 切入点（JoinPoint） 、 通知（Advice） 、 织入（Weave） 就足够了， 理解了这几个名词， 应用时你就可以游刃有余了！ 



## 8. 原型模式

（参考《设计模式之禅》（第二版）第13章 原型模式 P222/824）

### 8.1 原型模式的定义

原型模式（Prototype Pattern） 的简单程度仅次于单例模式和迭代器模式。 正是由于简单， 使用的场景才非常地多， 其定义如下：

Specify the kinds of objects to create using a prototypical instance,and create new objects by copying this prototype.（用原型实例指定创建对象的种类， 并且通过拷贝这些原型创建新的对象。 ） 

![1567739622175](E:\个人文件\博客\java面试题解析.assets\1567739622175.png)

原型模式通用源码：

~~~java
public class PrototypeClass implements Cloneable {
    //覆写父类Object方法
    @Override
    public PrototypeCLass clone(){
        PrototypeClass prototypeClass = null;
        try{
            prototypeClass = (PrototypeClass)super.clone();
        }catch (CloneNotSupportedException e){
            //异常处理
        }
        return prototypeClass;
    }
}
~~~

实现一个接口， 然后重写clone方法， 就完成了原型模式！ 

### 8.2 原型模式的优点

- 性能优良
  原型模式是在内存二进制流的拷贝， 要比直接new一个对象性能好很多， 特别是要在一个循环体内产生大量的对象时， 原型模式可以更好地体现其优点。
- 逃避构造函数的约束
  这既是它的优点也是缺点， 直接在内存中拷贝， 构造函数是不会执行的（参见13.4节） 。 优点就是减少了约束， 缺点也是减少了约束， 需要大家在实际应用时考虑。 

### 8.3 原型模式的使用场景

- 资源优化场景
  类初始化需要消化非常多的资源， 这个资源包括数据、 硬件资源等。
- 性能和安全要求的场景
  通过new产生一个对象需要非常繁琐的数据准备或访问权限， 则可以使用原型模式。
- 一个对象多个修改者的场景
  一个对象需要提供给其他对象访问， 而且各个调用者可能都需要修改其值时， 可以考虑使用原型模式拷贝多个对象供调用者使用。

在实际项目中， 原型模式很少单独出现， 一般是和工厂方法模式一起出现， 通过clone的方法创建一个对象， 然后由工厂方法提供给调用者。 原型模式已经与Java融为一体， 大家可以随手拿来使用。 

### 8.4 原型模式的注意事项

#### 8.4.1 构造函数不会被执行

一个实现了Cloneable并重写了clone方法的类A， 有一个无参构造或有参构造B， 通过new关键字产生了一个对象S， 再然后通过S.clone()方式产生了一个新的对象T， 那么在对象拷贝时构造函数B是不会被执行的。  

对象拷贝时构造函数确实没有被执行， 这点从原理来讲也是可以讲得通的， Object类的clone方法的原理是从内存中（具体地说就是堆内存） 以二进制流的方式进行拷贝， 重新分配一个内存块， 那构造函数没有被执行也是非常正常的了。 

#### 8.4.2 浅拷贝和深拷贝

Java做了一个偷懒的拷贝动作， Object类提供的方法clone只是拷贝本对象， 其对象内部的数组、 引用对象等都不拷贝， 还是指向原生对象的内部元素地址， 这种拷贝就叫做浅拷贝。 

但是对于String类型， Java就希望你把它认为是基本类型， 它是没有clone方法的， 处理机制也比较特殊， 通过字符串池（stringpool）在需要的时候才在内存中创建新的字符串， 读者在使用的时候就把String当做基本类使用即可 

注意 使用原型模式时， 引用的成员变量必须满足两个条件才不会被拷贝： 一是类的成员变量， 而不是方法内变量； 二是必须是一个可变的引用对象， 而不是一个原始类型或不可变对象 

对私有的类变量进行独立的拷贝 ，就实现了完全的拷贝， 两个对象之间没有任何的瓜葛了， 你修改你的， 我修改我的， 不相互影响， 这种拷贝就叫做深拷贝。 深拷贝还有一种实现方式就是通过自己写二进制流来操作对象， 然后实现对象的深拷贝， 这个大家有时间自己实现一下。 

注意 深拷贝和浅拷贝建议不要混合使用， 特别是在涉及类的继承时， 父类有多个引用的情况就非常复杂， 建议的方案是深拷贝和浅拷贝分开实现。 

#### 8.4.3 clone和final两个冤家

对象的clone与对象内的final关键字是有冲突的。



## 9. 中介者模式

（参考《设计模式之禅》（第二版）第14章 中介者模式 P239/824）

### 9.1 中介者模式的定义

中介者模式的定义为： Define an object that encapsulates how a set of objects interact.Mediator promotes loose coupling by keeping objects from referring to each other explicitly,and it lets you vary their interaction independently.（用一个中介对象封装一系列的对象交互， 中介者使各对象不需要显示地相互作用， 从而使其耦合松散， 而且可以独立地改变它们之间的交互。 ） 

![1567741594331](E:\个人文件\博客\java面试题解析.assets\1567741594331.png)

从类图中看， 中介者模式由以下几部分组成：

- Mediator 抽象中介者角色
  抽象中介者角色定义统一的接口， 用于各同事角色之间的通信。
- Concrete Mediator 具体中介者角色
  具体中介者角色通过协调各同事角色实现协作行为， 因此它必须依赖于各个同事角色。
- Colleague 同事角色
  每一个同事角色都知道中介者角色， 而且与其他的同事角色通信的时候， 一定要通过中介者角色协作。 每个同事类的行为分为两种： 一种是同事本身的行为， 比如改变对象本身的状态， 处理自己的行为等， 这种行为叫做自发行为（Self-Method） ， 与其他的同事类或中介者没有任何的依赖； 第二种是必须依赖中介者才能完成的行为， 叫做依赖方法（DepMethod） 。 

通用抽象中介者：

~~~java
public abstract class Mediator {
    //定义同事类
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;
    //通过getter/setter方法把同事类注入进来
    public ConcreteColleague1 getC1(){
        return c1;
    }
    public void setC1(ConcreteColleague1 c1){
        this.c1 = c1;
    }
    public ConcreteColleague2 getC2(){
        return c2;
    }
    public void setC2(ConcreteColleague1 c2){
        this.c2 = c2;
    }
    //中介者模式的业务逻辑
    public abstract void doSomething1();
    public abstract void doSomething2();
}
~~~

中介者所具有的方法doSomething1和doSomething2都是比较复杂的业务逻辑， 为同事类服务， 其实现是依赖各个同事类来完成的。 

抽象同事类：

~~~java
public abstract class Colleague {
    protected Mediator mediator;
    public Colleague(Mediator _mediator) {
        this.mediator = _mediator;
    }
}
~~~

这个基类也非常简单。 一般来说， 中介者模式中的抽象都比较简单， 是为了建立这个中介而服务的 

具体同事类：

~~~java
public class ConcreteColleague1 extends Colleague {
    //通过构造函数传递中介者
    public ConcreteColleague1 (Mediator _mediator) {
        super(_mediator);
    }
    //自有方法 self-method
    public void selfMethod1() {
        //处理自己的业务逻辑
    }
    //依赖方法 dep-method
    public void depMethod1() {
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托给中介者处理
        super.mediator.doSomething1();
    }
}

public class ConcreteColleague2 extends Colleague {
    //通过构造函数传递中介者
    public ConcreteColleague2 (Mediator _mediator) {
        super(_mediator);
    }
    //自有方法 self-method
    public void selfMethod2() {
        //处理自己的业务逻辑
    }
    //依赖方法 dep-method
    public void depMethod2() {
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托给中介者处理
        super.mediator.doSomething2();
    }
}
~~~

为什么同事类要使用构造函数注入中介者， 而中介者使用getter/setter方式注入同事类呢？ 这是因为同事类必须有中介者， 而中介者却可以只有部分同事类。 

### 9.2 中介者模式的优缺点

中介者模式的优点就是减少类间的依赖， 把原有的一对多的依赖变成了一对一的依赖，同事类只依赖中介者， 减少了依赖， 当然同时也降低了类间的耦合。 

中介者模式的缺点就是中介者会膨胀得很大， 而且逻辑复杂， 原本N个对象直接的相互依赖关系转换为中介者和同事类的依赖关系， 同事类越多， 中介者的逻辑就越复杂。 

### 9.3 中介者模式的使用场景

中介者模式简单， 但是简单不代表容易使用， 很容易被误用。 在面向对象的编程中， 对象和对象之间必然会有依赖关系， 如果某个类和其他类没有任何相互依赖的关系， 那这个类就是一个“孤岛”， 在项目中就没有存在的必要了！ 就像是某个人如果永远独立生活， 与任何人都没有关系， 那这个人基本上就算是野人了——排除在人类这个定义之外。

类之间的依赖关系是必然存在的， 一个类依赖多个类的情况也是存在的， 存在即合理，那是否可以说只要有多个依赖关系就考虑使用中介者模式呢？ 答案是否定的。 中介者模式未必能帮你把原本凌乱的逻辑整理得清清楚楚， 而且中介者模式也是有缺点的， 这个缺点在使用不当时会被放大， 比如原本就简单的几个对象依赖关系， 如果为了使用模式而加入了中介者， 必然导致中介者的逻辑复杂化， 因此中介者模式的使用需要“量力而行”！ 中介者模式适用于多个对象之间紧密耦合的情况， 紧密耦合的标准是： 在类图中出现了蜘蛛网状结构。 在这种情况下一定要考虑使用中介者模式， 这有利于把蜘蛛网梳理为星型结构， 使原本复杂混乱的关系变得清晰简单。 

### 9.4 中介者模式的实际应用

- MVC框架
  大家都应该使用过Struts， MVC框架， 其中的C（Controller） 就是一个中介者， 叫做前端控制器(Front Controller)， 它的作用就是把M(Model， 业务逻辑)和V（View， 视图） 隔离开，协调M和V协同工作， 把M运行的结果和V代表的视图融合成一个前端可以展示的页面， 减少M和V的依赖关系。 MVC框架已经成为一个非常流行、 成熟的开发框架， 这也是中介者模式的优点的一个体现。
- 媒体网关
  媒体网关也是一个典型的中介者模式， 比如使用MSN时， 张三发消息给李四， 其过程应该是这样的： 张三发送消息， MSN服务器(中介者)接收到消息， 查找李四， 把消息发送到李四， 同时通知张三， 消息已经发送。 在这里， MSN服务器就是一个中转站， 负责协调两个客户端的信息交流， 与此相反的就是IPMsg（也叫飞鸽） ， 它没有使用中介者， 而直接使用了UDP广播的方式， 每个客户端既是客户端也是服务器端。 

### 9.5 最佳实践

本章讲述的中介者模式很少用到接口或者抽象类， 这与依赖倒置原则是冲突的， 这是什么原因呢？ 首先， 既然是同事类而不是兄弟类（有相同的血缘） ， 那就说明这些类之间是协作关系， 完成不同的任务， 处理不同的业务， 所以不能在抽象类或接口中严格定义同事类必须具有的方法（从这点也可以看出继承是高侵入性的） 。 这是不合适的， 就像你我是同事，虽然我们大家都是朝九晚五地上班， 但是你跟我干的活肯定不同， 不可能抽象出一个父类统一定义同事所必须有的方法。 当然， 每个同事都要吃饭、 上厕所， 可以把这些最基本的信息封装到抽象中， 但这些最基本的行为或属性是中介者模式要关心的吗？ 如果两个对象不能提炼出共性， 那就不要刻意去追求两者的抽象， 抽象只要定义出模式需要的角色即可。 当然如果严格遵守面向接口编程的话， 则是需要抽象的， 这就需要读者在实际开发中灵活掌握。 其次， 在一个项目中， 中介者模式可能被多个模块采用， 每个中介者所围绕的同事类各不相同， 你能抽象出一个具有共性的中介者吗？ 不可能， 一个中介者抽象类一般只有一个实现者， 除非中介者逻辑非常复杂， 代码量非常大， 这时才会出现多个中介者的情况。 所以， 对于中介者来说， 抽象已经没有太多的必要。 

中介者模式是一个非常好的封装模式， 也是一个很容易被滥用的模式， 一个对象依赖几个对象是再正常不过的事情， 但是纯理论家就会要求使用中介者模式来封装这种依赖关系，这是非常危险的！ 使用中介模式就必然会带来中介者的膨胀问题， 这在一个项目中是很不恰当的。 大家可以在如下的情况下尝试使用中介者模式：

- N个对象之间产生了相互的依赖关系（N＞2） 。
- 多个对象有依赖关系， 但是依赖的行为尚不确定或者有发生改变的可能， 在这种情况下一般建议采用中介者模式， 降低变更引起的风险扩散。
- 产品开发。 一个明显的例子就是MVC框架， 把中介者模式应用到产品中， 可以提升产品的性能和扩展性， 但是对于项目开发就未必， 因为项目是以交付投产为目标， 而产品则是以稳定、 高效、 扩展为宗旨。 



## 10. 命令模式

（参考《设计模式之禅》（第二版）第15章 命令模式 P262/824）

### 10.1 命令模式的定义

命令模式是一个高内聚的模式， 其定义为： Encapsulate a request as an object,thereby letting you parameterize clients with different requests,queue or log requests,and support undoable operations.（将一个请求封装成一个对象， 从而让你使用不同的请求把客户端参数化， 对请求排队或者记录请求日志， 可以提供命令的撤销和恢复功能。 ） 

![1567754464423](E:\个人文件\博客\java面试题解析.assets\1567754464423.png)

在该类图中， 我们看到三个角色：

- Receive接收者角色
  该角色就是干活的角色， 命令传递到这里是应该被执行的， 具体到我们上面的例子中就是Group的三个实现类。
- Command命令角色
  需要执行的所有命令都在这里声明。
- Invoker调用者角色
  接收到命令， 并执行命令。 在例子中， 我（项目经理） 就是这个角色。

命令模式比较简单， 但是在项目中非常频繁地使用， 因为它的封装性非常好， 把请求方（Invoker） 和执行方（Receiver） 分开了， 扩展性也有很好的保障， 通用代码比较简单。 







# 数据结构、集合相关

## 1. HashMap、HashTable、TreeMap、LinkedHashMap、ConcurrentHashMap

（参考[《浅谈hashMap、hashTable、ConcurrentHashMap（1.6、1.8）》][https://www.imooc.com/article/details/id/19737]、《实战Java高并发程序设计（第二版）》3.3.2 线程安全的HashMap P147/420）

### 1.1 HashMap

HashMap是最常用的集合类框架之一，它实现了Map接口，所以存储的元素也是键值对映射的结构，并允许使用null值和null键，其内元素是无序的，如果要保证有序，可以使用LinkedHashMap。

在hashMap的size达到`initialCapacity * loadFactor`时会对hashMap进行扩容，值得注意的是hashMap的size大小总是2的幂次方。初始容量和加载因子的选取也是影响HashMap性能的原因之一，加载因子过高虽然减少了空间开销，但同时也增加了查找某个条目的时间；加载因子过低也可能引起HashMap容易执行rehash操作。

当hashMap扩容的时候会调用transfer方法，它是采用头插法来转移旧值到新的hashMap中去，假如转移前链表顺序是 1->2->3，那么转移后就会变成 3->2->1，那么在多线程的情况下就可能造成 1->2 的同时 2->1 的环形链表，进而形成死循环。

那在多线程下使用HashMap我们可以采用如下几种方案：

- 在外部包装HashMap，实现同步机制。
  使用集合的工具类 Collections的静态方法synchronizedMap()，在这个方法中创建了工具类 Collections中的内部类SynchronizedMap的实例来实现HashMap的线程安全：Map m = Collections.synchronizedMap(new HashMap(...));，这里就是对HashMap做了一次包装
  synchronizedMap()方法会生成一个名为SynchronizedMap的Map。它使用委托，将自己所有Map相关功能交给传入的HashMap实现，而自己则主要负责保证线程安全。SynchronizedMap内包装了一个Map m。通过Object mutex实现对这个Map m的互斥操作。

  ~~~java
  public V get(Object key){
      synchronized(mutex){return m.get(key);}
  }
  ~~~

  而其他所有相关的Map操作都会使用这个mutex进行同步，从而实现线程安全。

- 使用java.util.HashTable，效率最低

- 使用java.util.concurrent.ConcurrentHashMap，相对安全，效率较高。

### 1.2 HashTable

HashTable和HashMap很相似，但HashTable是线程安全的，同时HashTable中的key和value都不能为null。

在Hashtable中很多操作Hashtable的方法都加上了synchronized关键字来保证线程安全，比如：get和put方法。

从上面可以看出Hashtable和Collections.synchronizedMap(hashMap)基本上是对读写进行加锁操作，一个线程在读写元素，其余线程必须等待，性能是十分糟糕的，而且Hashtable已经过时了。

### 1.3 ConcurrentHashMap

为了提高高并发下的性能，我们来看下并发安全的ConcurrentHashMap。ConcurrentHashMap之所以高效是因为它更好的降低了锁的粒度，锁加在了每个Segment上而不是直接加在整个HashMap上，参数concurrencyLevel是用户估计的并发级别，就是说你觉得最多有多少线程共同修改这个map，根据这个来确定Segment数组的大小，concurrencyLevel默认为16。ConcurrentHashMap的key和value都不能为null。

先来看下ConcurrentHashMap 在JDK1.6中的版本。

ConcurrentHashMap采用分段锁的机制，实现并发的更新操作，底层采用数组+链表+红黑树的存储结构。

其包含两个核心静态内部类 Segment (默认分为16段) 和HashEntry。

Segment继承ReentrantLock用来充当锁的角色，每个 Segment 对象守护每个散列映射表的若干个桶。

HashEntry 用来封装映射表的键 / 值对；
每个桶是由若干个 HashEntry 对象链接起来的链表。

一个 ConcurrentHashMap 实例中包含由若干个 Segment 对象组成的数组，如图所示：


![img](https://img.mukewang.com/598581d50001106005020537.png)

ConcurrentHashMap 在JDK1.8中的版本

而在JDK1.8中的实现已经抛弃了Segment分段锁机制，利用CAS+Synchronized来保证并发更新的安全，底层依然采用数组+链表+红黑树的存储结构。

CAS，Compare and Swap即比较并替换，CAS有三个操作数：内存值V、旧的预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，将内存值修改为B并返回true，如果不相同则证明内存值在并发的情况下被其它线程修改过了，则不作任何修改，返回false，等待下次再修改。

table：默认为null，初始化发生在第一次插入操作，默认大小为16的数组，用来存储Node节点数据，扩容时大小总是2的幂次方。

Node：保存key，value及key的hash值的数据结构。

1.6中采用ReentrantLock 分段锁的方式，使多个线程在不同的segment上进行写操作不会发现阻塞行为；1.8中直接采用了内置锁synchronized。



## 2. 排序

### 2.1 归并排序



### 2.2 快速排序

~~~java
private static <T extends Comparable<? super T>> void quicksort(T[] a, int left, int right){
    if(left+CUTOFF<=right){
        T pivot = median3(a, left, right);
        
        // Begin partition
        int i = left, j = right - 1;
        for(;;){
            while(a[++i].compareTo(pivot)<0){}
            while(a[--j].compareTo(pivot)>0){}
            if(i<j) swapReference(a, i, j);
            else break;
        }
        swapReference(a, i, right-1); // Restore pivot
        
        quicksort(a,left,i-1);// sort small elements
        quicksort(a,i+1,right);// sort large elements
    }
    else insertSort(a, left, right); // Do an insertion sort on the subarray
}
~~~



### 2.3 外排序



# 虚拟机相关

## 1. java内存区域

（参考《深入理解Java虚拟机：JVM高级特性与最佳实践（第二版）》 2.2 运行时数据区域 P61/457）

运行时数据区：

- 由所有线程共享的数据区：方法区、堆
- 线程隔离的数据区：虚拟机栈、本地方法栈、程序计数器

执行引擎 -> 本地库接口 -> 本地方法库

### 1.1 程序计数器

由于Java虚拟机的多线程是通过线程轮流切换并分配处理器执行时间的方式来实现的，在任何一个确定的时刻，一个处理器（对于多核处理器来说是一个内核）都只会执行一条线程中的指令。因此，为了线程切换后能恢复到正确的执行位置，每条线程都需要有一个独立的程序计数器，每条线程之间计数器互不影响，独立存储，我们称这类内存区域为“线程私有”的内存。

如果线程正在执行的是一个Java方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行的是Native方法，这个计数器值则为空（Undefined）。此内存区域是唯一一个在Java虚拟机规范中没有规定任何OutOfMemoryError情况的区域。

### 1.2 Java虚拟机栈

与程序计数器一样，Java虚拟机栈也是线程私有的，它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧用于存储局部变量表、操作数栈、动态链接、方法出口等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。

经常有人把Java内存区分为堆内存（Heap）和栈内存（Stack），这种分发比较粗糙，Java内存区域的划分实际上远比这复杂。这里所指的“栈”就是现在讲的虚拟机栈，或者说是虚拟机栈中局部变量表部分。

局部变量表存放了编译期可知的各种基本数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference类型，它不等于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其他与此对象相关的位置）和returnAddress类型（指向一条字节码指令的地址）。

其中64位长度的long和double类型的数据会占用2个局部变量空间（Slot），其余的数据类型只占用1个。局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在帧中分配多大的局部变量空间是完全确定的，在方法运行期间不会改变局部变量表的大小。

在Java虚拟机规范中，对这个区域规定了两种异常状况：如果线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverflowError异常；如果虚拟机栈可以动态扩展（当前大部分的Java虚拟机都可动态扩展，只不过Java虚拟机规范中也允许固定长度的虚拟机栈），如果扩展时无法申请到足够的内存，就会抛出OutOfMemoryError异常。

### 1.3 本地方法栈

本地方法栈（Native Method Stack）与虚拟机栈所发挥的作用是非常相似的，它们之间的区别不过是虚拟机栈执行Java方法（也就是字节码）服务，而本地方法栈则为虚拟机使用到的Native方法服务。在虚拟机规范中对本地方法栈中使用的语言、使用方式与数据结构并没有强制规定，因此具体的虚拟机可以自由实现它。甚至有的虚拟机（譬如Sun HotSpot虚拟机）直接就把本地方法栈和虚拟机栈合二为一。与虚拟机栈一样，本地方法栈区域也会抛出StackOverflowError和OutOfMemoryError异常。

### 1.4 Java堆

对于大多数应用来说，Java堆（Java Heap）是Java 虚拟机所管理的内存中最大的一块。Java堆是被所有线程贡献的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。这一点在Java虚拟机规范中的描述是：所有的对象实例以及数组都要在堆上分配，但是随着JIT编译器的发展与逃逸分析技术逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化发生，所有的对象都分配在堆上也渐渐变得不是那么“绝对”了。

Java堆是垃圾收集器管理的主要区域，因此很多时候也被称作“GC堆”。从内存回收的角度来看，由于现在收集器基本都采用分代收集算法，所以Java堆中还可以细分为：新生代和老年代；再细致一点的有Eden空间、From Survivor空间、To Survivor空间等。从内存分配的角度来看，线程共享的Java堆中可能划分出多个线程私有的分配缓冲区（Thread Local Allocation Buffer，TLAB）。不过无论如何划分，都与存放内容无关，无论哪个区域，存储的都仍然是对象实例，进一步划分的目的是为了更好地回收内存，或者更快地分配内存。

根据Java虚拟机规范的规定，Java堆可以处于物理上不连续的内存空间中，只有逻辑上是连续的即可，就像我们的磁盘空间一样。在实现时，既可以实现成固定大小的，也可以是可扩展的，不过当前主流的虚拟机都是按照可扩展来实现的（通过-Xmx和-Xms来控制）。如果堆中没有内存完成实例分配，并且堆也无法再扩展时，将会抛出OutOfMemoryError异常。

### 1.5 方法区

方法区（Method Area）与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。虽然Java虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫做Non-Heap（非堆），目的应该是与Java堆区分开来。

对于习惯在HotSpot虚拟机上开发、部署程序的开发者来说，很多人都更愿意把方法区称为“永久代”（Permanent Generation），本质上两者并不等价，仅仅是因为HotSpot虚拟机的设计团队选择把GC分代收集扩展至方法区，或者说使用永久代来实现方法区而已，这样HotSpot的垃圾收集器可以像管理Java堆一样管理这部分内存，能够省去专门为方法区编写内存管理代码的工作。

> jdk1.8中则把永久代给完全删除了，取而代之的是 MetaSpace(元空间)。
>
> **为什么废弃永久代（PermGen）**
>
> 1. 由于永久代内存经常不够用或发生内存泄露，爆出异常*java.lang.OutOfMemoryError: PermGen*
> 2. 永久代会为 GC 带来不必要的复杂度，并且回收效率偏低。
> 3. Oracle 可能会将HotSpot 与 JRockit 合二为一。

Java虚拟机规范对方法区的限制非常宽松，除了和Java堆一样不需要连续的内存和可以选择固定大小或者可扩展外，还可以选择不实现垃圾收集。相对而言，垃圾收集行为在这个区域是比较少出现的，但并非数据进入了方法区就如永久代的名字一样“永久”存在了。这区域的内存回收目标主要是针对常量池的回收和对类型的卸载，一般来说这区域的回收“成绩”比较难以令人满意，尤其是类型的卸载，条件相当苛刻，但是这部分区域的回收确实是必要的。在Sun公司的BUG列表中，曾出现过的若干个严重的BUG就是由于低版本的HotSpot虚拟机对此区域未完全回收而导致内存泄漏。

根据Java虚拟机规范的规定，当方法区无法满足内存分配需求时，将抛出OutOfMemoryError异常。

### 1.6 运行时常量池

运行时常量池（Runtime Constant Pool）是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池（Constant Pool Table），用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放。

Java虚拟机对Class文件每一部分（自然也包括常量池）的格式都有严格的规定，每一个字节用于存储哪种数据都必须符合规范上的要求才会被虚拟机认可、装载和执行，但对于运行时常量池，Java虚拟机规范没有做任何细节的要求，不同的提供商实现的虚拟机可以按照自己的需要来实现这个内存区域。不过，一般来说，除了保存Class文件中描述的符号引用外，还会把翻译出来的直接引用也存储在运行时常量池中。

运行时常量池相当于Class文件常量池的另外一个重要特征是具备动态性，Java 语言并不要求常量一定只有编译器才能产生，也就是并非预置入Class文件中常量池的内容才能进入方法区运行时常量池，运行期间有可能将新的常量放入池中，这种特性被开发人员利用得比较多的便是String类的intern()方法。

既然运行时常量池是方法区的一部分，自然受到方法区内存的限制，当常量池无法再申请到内存时会抛出OutOfMemoryError异常。

## 2.  垃圾收集

### 2.1 判断对象是否存活

（参考《深入理解Java虚拟机：JVM高级特性与最佳实践（第二版）》 3.2 对象已死吗 P85/457）

#### 引用计数算法

引用计数算法（Reference Counting）的实现简单，判定效率也很高，在大部分情况下它都是不错的算法，也有一些比较著名的应用案例，例如微软公司的COM（Component Object Model）技术、使用ActionScript 3 的 FlashPlayer、Python语言和在游戏脚本领域被广泛运用的Squirrel中都使用了引用计数算法进行内存管理。但是，至少主流的Java虚拟机里面没有选用引用计数算法来管理内存，其中最主要的原因是它很难解决对象之间互相循环引用的问题。

#### 可达性分析算法

在主流的商用程序语言（Java，C#，甚至包括前面提到的古老的Lisp）的主流实现中，都是称通过可达性分析（Reachability Analysis）来判断对象是否存活的。这个算法的基本思路就是通过一系列的称为“GC Roots”的对象作为起始点，从这些节点开始向下搜索，搜索所走过的路径称为引用链（Reference Chain），当一个对象到GC Roots没有任何引用链相连（用图论的话来说，就是从GC Roots到这个对象不可达）时，则证明此对象是不可用的。

Java语言中，可作为GC Roots的对象包括下面几种：

- 虚拟机栈（栈帧中的本地变量表）中引用的对象。
- 方法区中类静态属性引用的对象。
- 方法区中常量引用的对象。
- 本地方法栈中JNI（即一般说的Native方法）引用的对象。

### 2.2 垃圾收集算法

（参考《深入理解Java虚拟机：JVM高级特性与最佳实践（第二版）》 3.3 垃圾收集算法 P92/457）

#### 标记-清除算法

最基础的收集算法是“标记-清除”（Mark-Sweep）算法，如同它的名字一样，算法分为“标记”和“清除”两个阶段：首先标记出所有需要回收的对象，在标记完成后统一回收所有被标记的对象，它的标记过程其实在前一节讲述对象标记判定时已经介绍过了。之所以说它是最基础的收集算法，是因为后续的收集算法都是基于这种思路并对其不足进行改进而得到的。

它的主要不足有两个：

- 一个是效率问题，标记和清除两个过程效率都不高；
- 另一个是空间问题，标记清除之后会产生大量不连续的内存碎片，空间碎片太多可能会导致以后在程序运行过程中需要分配较大对象时，无法找到足够的连续内存而不得不提前触发另一次垃圾收集动作。

#### 复制算法

为了解决效率问题，一种称为“复制”（Copying）的收集算法出现了，它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用的内存空间一次清理掉。这样使得每次都是对整个半区进行内存回收，内存分配时也就不用考虑内存碎片等复杂情况，只有移动堆顶指针，按顺序分配内存即可，实现简单，运行高效。

只是这种算法的代价是将内存缩小为原来的一半，未免太高了一点。

现在商业虚拟机都采用这种收集算法来回收新生代，IBM的公司专门研究表明，新生代中的对象98%是“朝生夕死”的，所以并不需要按照1：1的比例来划分内存空间，而是将内存分为一块较大的Eden空间和两块较小的Survivor空间，每次使用Eden和其中一块Survivor。（这里说明一下，在HotSpot中这种分代方式从最初就是这种布局，与IBM的研究并没有什么实际联系）当回收时，将Eden和Survivor中还存活着的对象一次性地复制到另一块Survivor空间上，最后清理掉Eden和刚才使用过的Survivor空间。

HotSpot虚拟机默认Eden和Survivor的大小比例是8：1，也就是每次新生代中可用内存空间为整个新生代容量的90%（80%+10%），只有10%的内存会被“浪费”。当然，98%的对象回收只是一般场景下的数据，我们没有办法保证每次回收都只有不多于10%的对象存活，当Survivor空间不够时，需要依赖其他内存（这里指老年代）进行分配担保（Handle Promotion）

内存的分配担保是指：如果另一块Survivor空间没有足够空间存放上一次新生代收集下来的存活对象时，这些对象将直接通过分配担保机制进入老年代。关于对新生代进行分配担保的内容，在本章稍后在讲解垃圾收集器执行规则时还会再详细讲解。

#### 标记-整理算法

复制收集算法在对象存活率较高时就要进行较多的复制操作，效率将会变低。更关键的是，如果不想浪费50%的空间，就需要有额外的空间进行分配担保，以应对被使用的内存中所有对象都100%存活的极端情况，所以在老年代一般不能直接选用这种算法。

根据老年代的特点，有人提出了另外一种“标记-整理”（Mark-Compact）算法，标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活对象都向一端移动，然后直接清理掉端边界以外的内存。

#### 分代收集算法

当前商业虚拟机的垃圾收集都采用“分代收集”（Generational Collection）算法，这种算法并没有什么新思想，只是根据对象存活周期的不同将内存划分为几块。一般是把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。而老年代中因为对象存活率高，没有额外空间对它进行分配担保，就必须使用“标记-清理”或者“标记-整理”算法来回收。



### 2.3 垃圾收集器

（参考《深入理解Java虚拟机：JVM高级特性与最佳实践（第二版）》 3.5 垃圾收集器 P98/457）

#### Serial收集器

Serial收集器是最基本、发展历史最悠久的收集器，曾经（在JDK 1.3.1之前）是虚拟机新生代收集的唯一选择。大家看名字就会知道，这个收集器是一个单线程的收集器，但它的“单线程”的意义并不仅仅说明它只会使用一个CPU或一条收集线程去完成垃圾收集工作，更重要的是在它进行垃圾收集时，必须暂停其他所有的工作进程，直到它收集结束。

> Serial / Serial Old收集器：
>
> 新生代采取复制算法，暂停所有用户线程，单个GC线程；
>
> 老年代采用标记-整理算法，暂停所有用户线程，单个GC线程。

实际上到现在为止，它依然是虚拟机运行在Client模式下的默认新生代收集器。它也有着优于其他收集器的地方：简单而高效（与其他收集器的单线程比），对于限定单个CPU的环境来说，Serial收集器由于没有线程交互的开销，专心做垃圾收集自然可以获得最高的单线程收集效率。在用户的桌面应用场景中，分配给虚拟机的内存一般来说不会很大，收集几十兆甚至一两百兆的新生代（仅仅是新生代使用的内存，桌面应用基本上不会再大了），停顿时间完全可以控制在几十毫秒最多一百多毫秒以内，只要不是频繁发生，这点停顿是可以接受的。所以，Serial收集器对于运行在Client模式下的虚拟机来说是一个很好的选择。

#### ParNew收集器

ParNew收集器其实就是Serial收集器的多线程版本，除了使用多条线程进行垃圾收集之外，其余行为包括Serial收集器可用的所有控制参数（例如：-XX:SurvivorRatio、-XX:PretenureSizeThreshold、-XX:HandlePromotionFailure等）、收集算法、Stop The World、对象分配规则、回收策略等都与Serial收集器完全一样，在实现上，这两种收集器也共用了相当多的代码。

> ParNew / Serial Old收集器：
>
> 新生代：采用复制算法，暂停所有用户线程，多个GC线程；
>
> 老年代：采取标记-整理算法，暂停所有用户线程，单个GC线程。

ParNew收集器除了多线程收集以外，其他与Serial收集器相比并没有太多创新之处，但它却是许多运行在Server模式下的虚拟机中首选的新生代收集器，其中有一个与性能无关但很重要的原因是，除了Serial收集器外，目前只有它能与CMS收集器配合工作。在JDK1.5时期，HotSpot推出了一款在强交互应用中几乎可认为有划时代意义的垃圾收集器——CMS收集器（Concurrent Mark Sweep），这款收集器是HotSpot虚拟机中第一款真正意义上的并发（Concurrent）收集器，它第一次实现了让垃圾收集线程与用户线程（基本上）同时工作。

不幸的是，CMS作为老年代的收集器，却无法与JDK 1.4.0中已经存在的新生代收集器Parallel Scavenger配合工作，所以在JDK 1.5中使用CMS来收集老年代的时候，新生代只能选择ParNew或者Serial收集器中的一个。ParNew收集器也是使用-XX:+UseConcMarkSweepGC选项后的默认新生代收集器，也可以使用-XX:+UseParNewGC选项来强制指定它。

ParNew收集器在单CPU的环境中绝对不会有比Serial收集器更好的效果，甚至由于存在线程交互的开销，该收集器在通过超线程技术实现的两个CPU的环境中都不能百分之百地保证可以超越Serial收集器。当然，随着可以使用的CPU的数量的增加，它对于GC时系统资源的有效利用还是很有好处的。它默认开启的收集线程数与CPU的数量相同，在CPU非常多的环境下，可以使用-XX:ParallelGCThreads参数来限制垃圾收集的线程数。

#### Parallel Scavenger 收集器

Parallel Scavenger收集器是一个新生代收集器，它也是使用复制算法的收集器，又是并行的多线程收集器……看上去和ParNew都一样，那它有什么特别之处呢？

Parallel Scavenger收集器的特点是它的关注点与其他收集器不同，CMS等收集器的关注点是尽可能地缩短垃圾收集时用户线程的停顿时间，而Parallel Scavenger收集器的目标则是达到一个可控制的吞吐量（Throughput）。所谓吞吐量就是CPU用于运行用户代码的时间与CPU总消耗时间的比值，即吞吐量=运行用户代码时间/(运行用户代码时间+垃圾收集时间)，虚拟机总共运行了100分钟，其中垃圾收集花掉1分钟，那吞吐量就是99%。

停顿时间越短就越是和需要与用户交互的程序，良好的响应速度能提升用户体验，而高吞吐量则可以高效率地利用CPU时间，尽快完成程序的运算任务，主要适合在后台运算而不需要太多交互的任务。

自适应调节策略也是Parallel Scavenger收集器与ParNew收集器的一个重要区别。开启-XX:+UseAdaptiveSizePolicy后，就不需要手工指定新生代的大小（-Xmn）、Eden与Survivor区的比例（-XX:SuvivorRatio）、晋升老年代对象年龄（-XX:PretenureSizeThreshold）等细节参数了，虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间或者最大的吞吐量，这种调节方式称为GC自适应的调节策略（GC Ergonomics）。

#### Serial Old 收集器

Serial Old是Serial收集器的老年代版本，它同样是一个单线程收集器，使用“标记-整理”算法。这个收集器的主要意义也是在于给Client模式下的虚拟机使用。

如果在Server模式下，那么他主要还有两大用途：

- 一种用途是在JDK 1.5以及之前的版本中与Parallel Scavenger收集器搭配使用；（需要说明一下，Parallel Scavenger收集器架构中本身有PS MarkSweep收集器来进行老年代收集，并非直接使用了Serial Old收集器，但是这个PS MarkSweep收集器与Serial Old的实现非常接近，所以在官方的许多资料中都是直接以Serial Old代替PS MarkSweep进行讲解）
- 另一种用途就是作为CMS收集器的后备预案，在并发收集发生Concurrent Mode Failure时使用。

这两点都将在后面的内容中详细讲解。

#### Parallel Old 收集器

Parallel Old是Parallel Scavenger收集器的老年代版本，使用多线程和“标记-整理”算法。这个收集器是在JDK1.6中才开始提供的，在此之前，新生代的Parallel Scavenger收集器一直处于比较尴尬的状态。原因是，如果新生代选择了Parallel Scavenger收集器，老年代除了Serial Old（PS MarkSweep）收集器外别无选择（还记得上面说过Parallel Scavenger收集器无法与CMS收集器配合工作吗？）。由于老年代Serial Old 收集器在服务端应用性能上的“拖累”，使用了Parallel Scavenger收集器也未必能在整体应用上获得吞吐量最大化的效果，由于单线程的老年代收集中无法充分利用服务器多CPU的处理能力，在老年代很大而且硬件比较高级的环境中，这种组合的吞吐量甚至还不一定有ParNew加CMS组合“给力”。

直到Parallel Old收集器出现后，“吞吐量优先”收集器终于有了比较名副其实的应用组合，在注重吞吐量以及CPU资源敏感的场合，都可以优先考虑Parallel Scavenge加Parallel Old 收集器。

> Parallel Scavenger / Parallel Old 收集器：
>
> 新生代：多个GC线程
>
> 老年代：多个GC线程

#### CMS收集器

CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。目前很大一部分的Java应用集中在互联网或者B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。CMS收集器就非常符合这类应用的需求。

从名字（包含“Mark Sweep”）上就可以看出，CMS收集器是基于“标记-清除”算法实现的，它的运作过程相对于前面几种收集器来说复杂一些，整个过程分为4个步骤，包括：

- 初始标记（CMS initial mark）
- 并发标记（CMS concurrent mark）
- 重新标记（CMS remark）
- 并发清除（CMS concurrent sweep）

其中，初始标记、重新标记这两个步骤仍然需要“Stop The World”。初始标记仅仅只是标记一下GC Roots能直接关联到的对象，速度很快，并发标记阶段就是进行GC Roots Tracing的过程，而重新标记阶段则是为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记稍长一些，但远比并发标记的时间短。 

由于整个过程中耗时最长的并发标记和并发清除过程收集器线程都可以与用户线程一起工作，所以，从总体上来说，CMS收集器的内存回收过程是与用户线程一起并发执行的。

CMS是一款优秀的收集器，它的主要优点在名字上已经体现出来了：并发收集、低停顿，Sun公司的一些官方文档中也称之为并发低停顿收集器（Concurrent Low Pause Collector）。但是CMS还远达不到完美的程度，它有以下3个明显的缺点：

- CMS收集器对CPU资源非常敏感。其实，面向并发设计的程序都对CPU资源比较敏感。在并发阶段，它虽然不会导致用户线程停顿，但是会因为占用了一部分线程（或者说CPU资源）而导致应用程序变慢，总吞吐量会降低。
- CMS收集器无法处理浮动垃圾（Floating Garbage），可能出现“Concurrent Mode Failure”失败而导致另一次Full GC的产生。由于CMS并发清理阶段用户进程还在运行着，伴随程序运行自然就还会有新的垃圾不断产生，这一部分垃圾出现在标记过程之后，CMS无法在当次收集中处理掉它们，只好留待下一次GC时再清理掉。这一部分垃圾就称为“浮动垃圾”。也是由于在垃圾收集阶段用户进程还需要运行，那也就还需要预留有足够的内存空间给用户进程使用，因此CMS收集器不能像其他收集器那样等到老年代几乎完全被填满再进行收集，需要预留一部分空间提供并发收集时的程序运作使用。
- CMS是一款基于“标记-清除”算法实现的收集器，这意味着收集结束时会有大量空间碎片产生。空间碎片过多时，将会给大对象分配带来很大麻烦，往往会出现老年代还有很大空间剩余，但是无法找到足够大的连续空间来分配当前对象，不得不提前触发一次Full GC。为了解决这个问题，CMS提供了一个-XX:+UseCMSCompactAtFullCollection开关参数（默认就是开启的），用于在CMS收集器顶不住要进行FullGC时开启内存碎片的合并整理过程。

#### G1收集器

G1（Garbage-First）收集器是当今收集器技术发展的最前沿成果之一，早在JDK1.7刚刚确立项目目标，Sun公司给出的JDK 1.7 RoadMap 里面，它就被视为JDK 1.7中HotSpot虚拟机的一个重要的进化特征。直至JDK 7u4，Sun公司才认为它达到足够成熟的商用程度，移除了“Experimental”的标识。

G1是一款面向服务端应用的垃圾收集器。HotSpot开发团队赋予它的使命是（在比较长期的）未来可以替换掉JDK 1.5中发布的CMS收集器。与其他GC收集器相比，G1具备如下特点。

- 并行与并发：G1能充分利用多CPU、多核环境下的硬件优势，使用多个CPU（CPU或者CPU核心）来缩短Stop-The-World停顿的时间，部分其他收集器原本需要停顿Java线程执行的GC动作，G1收集器仍然可以通过并发的方式让Java程序继续执行。
- 分代收集：与其他收集器一样，分代概念在G1中依然得以保留。虽然G1可以不需要其他收集器配合就能独立管理整个GC堆，但它能够采用不同的方式去处理新创建的对象和已经存活了一段时间、熬过多次GC的旧对象以获取更好的收集效果。
- 空间整合：与CMS的“标记-清理”算法不同，G1从整体来看是基于“标记-整理”算法实现的收集器，从局部（两个Region之间）上来看是基于“复制”算法实现的，但无论如何，这两种算法都意味着G1运作期间不会产生内存空间碎片，收集后能提供规整的可用内存。这种特性有利于程序长时间运行，分配大对象时不会因为无法找到连续内存空间而提前触发下一次GC。
- 可预测的停顿：这是G1相对于CMS的另一大优势，降低停顿时间是G1和CMS共同的关注点，但G1除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为M毫秒的时间片段内，消耗在垃圾收集上的时间不得超过N毫秒，这几乎已经是实时Java（RTSJ）的垃圾收集器的特征了。

在G1之前的其他收集器进行收集的范围都是整个新生代或者老年代，而G1不再是这样。使用G1收集器时，Java堆的内存布局就与其他收集器有很大差别，它将整个Java堆划分为多个大小相等的独立区域（Region），虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔离的了，它们都是一部分Region（不需要连续）的集合。

G1收集器之所以能建立可预测的停顿时间模型，是因为它可以有计划地避免在整个Java堆中进行全区域的垃圾收集。G1跟踪各个Region里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的Region（这也就是Garbage-First名称的来由）。这种使用Region划分内存空间以及有优先级的区域回收方式，保证了G1收集器在有限的时间内可以获取尽可能高的收集效率。

在G1收集器中，Region之间的对象引用以及其他收集器的新生代和老年代之间的对象引用，虚拟机都是使用Remembered Set来避免全堆扫描的。G1中每个Region都有一个与之对应的Remembered Set来避免全堆扫描的。G1中每个Region都有一个与之对应的Remembered Set，虚拟机发现程序在对Reference类型的数据进行写操作时，会产生一个Write Barrier暂时中断写操作，检查Reference引用的对象是否处于不同的Region之中（在分代的例子中就是检查是否老年代中的对象引用了新生代中的对象），如果是，便通过CardTable把相关引用信息记录到被引用对象所属的Region的Remembered Set之中。当进行内存回收时，在GC根节点的枚举范围中加入Remembered Set 即可保证不对全堆扫描也不会有遗漏。

如果不计算维护Remembered Set的操作，G1收集器的运作大致可划分为以下几个步骤：

- 初始标记（Initial Marking）
- 并发标记（Concurrent Marking）
- 最终标记（Final Marking）
- 筛选回收（Live Data Counting and Evacuation）

### 2.4 内存分配与回收策略

（参考《深入理解Java虚拟机：JVM高级特性与最佳实践（第二版）》 3.6 内存分配与回收策略 P114/457）

对象的内存分配，往大方向讲，就是在堆上分配（但有可能经过JIT编译后被拆散为标量类型并间接地栈上分配），对象主要分配在新生代的Eden区上，如果启动了本地线程分配缓冲，将按线程优先在TLAB上分配。少数情况下也可能会直接分配在老年代中，分配的规则并不是百分之百固定的，其细节取决于当前使用的是哪一种垃圾收集器组合，还有虚拟机中与内存相关的参数的设置。

接下来我们将会讲解几条最普遍的内存分配规则，并通过代码去验证这些规则。本节下面的代码在测试时使用Client模式虚拟机运行，没有手工指定收集器组合，换句话说，验证的是在使用Serial/Serial Old 收集器下（ParNew/Serial Old收集器组合的规则也基本一致）的内存分配和回收策略。

#### 对象优先在Eden分配

大多数情况下，对象在新生代Eden区中分配。当Eden区没有足够空间进行分配时，虚拟机将发起一次Minor GC。

>新生代GC（Minor GC）：指发生在新生代的垃圾收集动作，因为Java对象大多都具备朝生夕灭的特性，所以Minor GC非常频繁，一般回收速度也较快。
>
>老年代GC（Major GC / Full GC）：指发生在老年代的GC，出现了Major GC，经常会伴随至少一次的Minor GC（但非绝对的，在Parallel Scavenger收集器的收集策略里就有直接进行Major GC的策略选择过程）。Major GC 的速度一般会比Minor GC慢10倍以上。

#### 大对象直接进入老年代

所谓的大对象是指，需要大量连续内存空间的Java 对象，最典型的大对象就是那种很长的字符串以及数组（笔者列出的例子中的byte[]数组就是典型的大对象）。大对象对虚拟机的内存分配来说就是一个坏消息（替Java 虚拟机抱怨一句，比遇到一个大对象更加坏的消息就是遇到一群“朝生夕灭”的“短命大对象”，写程序的时候应当避免），经常出现大对象很容易导致内存还有不少空间时就提前触发垃圾收集以获取足够的连续空间来“安置”它们。

#### 长期存活对象将进入老年代

既然虚拟机采用了分代收集的思想来管理内存，那么内存回收时就必须能识别哪些对象应放入新生代，哪些对象应放入老年代中。为了做到这点，虚拟机给每个对象定义了一个对象年龄（Age）计数器。如果对象在Eden出生并经过第一次Minor GC后仍然存活，并且能够被Survivor容纳的话，将被移动到Survivor空间中，并且对象年龄设为1.对象在Survivor区每熬过一次Minor GC，年龄就会增加1岁，当它的年龄增加到一定程度（默认为15岁），就会被晋升到老年代中。对象晋升老年代的年龄阈值，可以通过参数-XX:MaxTenuringThreshold设置。

#### 动态对象年龄判定

为了能更好的适应不同程序的内存状况，虚拟机并不是永远地要求对象的年龄必须达到了MaxTenuringThreshold才能晋升老年代，如果在Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无需等到MaxTenuringThreshold中要求的年龄。

#### 空间分配担保

在发生Minor GC之前，虚拟机会先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，如果这个条件成立，那么Minor GC可以确保是安全的。如果不成立，则虚拟机会查看HandlePromotionFailure设置值是否允许担保失败。如果允许，那么会继续检查老年代最大可用空间是否大于历次晋升到老年代对象的平均大小，如果大于，将尝试着进行一次Minor GC，尽管这次Minor GC是有风险的；如果小于，或者HandlePromotionFailure设置不允许冒险，那这时也要改为进行一次Full GC。

下面解释一下“冒险”是冒了什么风险，前面提到过，新生代使用复制收集算法，但为了内存利用率，只使用其中一个Survivor空间来作为轮换备份，因此当出现大量对象在Minor GC后仍然存活的情况（最极端的情况就是内存回收后新生代中所有对象都存活），就需要老年代进行分配担保，把Survivor无法容纳的对象直接进入老年代。

一共有多少对象会活下来在实际完成内存回收之前是无法明确知道的，所以只好取之前每次回收晋升到老年代对象容量的平均大小值作为经验值，与老年代的剩余空间进行比较，决定是否进行Full GC 来让老年代腾出更多空间。

取平均值来进行比较其实仍然是一种动态概率的手段，也就是说，如果某次Minor GC存活后的对象突增，远远高于平均值的话，依然会导致担保失败（HandlePromotionFailure）。如果出现了HandlePromotionFailure失败，那就只好在失败后重新发起一次Full GC。虽然担保失败时绕的圈子是最大的，但大部分情况下还是会将HandlePromotionFailure开关打开，避免Full GC过于频繁。

JDK 6 Update 24之后，HandlePromotionFailure参数不会再影响到虚拟机的空间分配担保策略。JDK 6 Update 24之后的规则变为只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小就会进行Minor GC，否则将进行Full GC



## 3. 虚拟机类加载机制

### 3.1 类加载的时机



### 3.2 类加载的过程



### 3.3 类加载器



## 4.虚拟机调优



## 5. 虚拟机性能监控与故障处理工具



## 6. Java内存模型

# IO相关



## 1. NIO

（参考《深入分析Java Web技术内幕 修订版》2.4 NIO的工作方式 P43（68/491））

### 1.1 BIO带来的挑战

BIO即阻塞I/O，不管是磁盘I/O还是网络I/O,数据在写入OutputStream或者从InputStream读取时都有可能会阻塞，一旦有阻塞，线程将会失去CPU的使用权，这在当前的大规模访问量和有性能要求的情况下是不能被接受的。虽然当前的网络I/O有一些解决办法，如一个客户端对应一个处理线程，出现阻塞时只是一个线程阻塞而不会影响其他线程工作，还有为了减少系统线程的开销，采用线程池的办法来减少线程创建和回收的成本。

但是在一些使用场景下仍然是无法解决的。如当前一些需要大量HTTP长连接的情况，像淘宝现在使用的Web 旺旺，服务端需要同时保持几百万的HTTP连接，但并不是每时每刻这些连接都在传输数据，在这种情况下不可能同时创建这么多线程来保持连接。即使线程的数量不是问题，也仍然有一些问题是无法避免的，比如我们想给某些客户端更高的服务优先级时，很难通过设计线程优先级来完成。另外一种情况是，每个客户端的请求在服务端可能需要访问一些竞争资源，这些客户端在不同线程中，因此需要同步，要实现这种同步操作远比用单线程复杂得多。

以上这些情况都说明，我们需要另外一种新的I/O操作方式。

### 1.2 NIO的工作机制

有两个关键类：Channel和Selector，它们是NIO中的两个核心概念。

Channel比Socket更加具体，可以把它比作某种具体的交通工具，如汽车或高铁，而可把Selector比作一个车站的车辆运行调度系统，也就是它可以轮询每个Channel的状态。这里还有一个Buffer类，它也比Stream更加具体，我们可以将它比作车上的座位。与Stream不同，Stream只能代表一个座位，至于是什么座位由你自己去想象，也就是你不知道自己上的是什么车，因为你并不能选择。而这些信息都已经被封装在了运输工具（Socket）里面。

NIO引入了Channel、Buffer和Selector，就是想把这些信息具体化，让程序员有机会去控制它们。例如我们可以控制Buffer的容量、是否扩容以及如何扩容。

理解了这些概念之后，我们看一下它们实际上是如何工作的，下面是一段典型的NIO代码：

~~~java
public void selector() throws IOException{
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    Selector selector = Selector.open();
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.configureBlocking(false);//设置为非阻塞方式
    ssc.socket().bind(new InetSocketAddress(8080));
    ssc.register(selector, SelectionKey.OP_ACCEPT);//注册监听的事件
    while(true){
        Set selectedKeys = selector.selectedKeys();//取得所有key集合
        Iterator it = selectedKeys.iterator();
        while(it.hasNext()){
            SelectionKey key = (SelectionKey) it.next();
            if((key.readyOps() & SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT){
                ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssChannel.accept();//接收到服务端的请求
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
                it.remove();
            }else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                SocketChannel sc = (SocketChannel) key.channel();
                while(true){
                    buffer.clear();
                    int n = sc.read(buffer);//读取数据
                    if(n<=0){
                        break;
                    }
                    buffer.flip();
                }
                it.remove();
            }
        }
    }
}
~~~

调用Selector的静态工厂创建一个选择器，创建一个服务端的Channel，绑定到一个Socket对象，并把这个通信信道注册到选择器上，把这个通信信道设置为非阻塞模式。然后就可以调用Selector的selectedKeys方法来检查已经注册在这个选择器上的所有通信信道是否有需要的事件发生，如果有某个事件发生，将会返回所有的SelectionKey，通过这个对象的Channel方法就可以取得这个通信信道对象，从而读取通信的数据，而这里读取的数据是Buffer，这个Buffer是我们可以控制的缓冲器。

在上面的这段程序中，将Server端的监听连接请求和处理请求的事件放在一个线程中，但是在事件应用中，我们通常会把它们放在两个线程中：一个线程专门负责监听客户端的连接请求，而且是以阻塞方式执行的；另一个线程专门负责处理请求，这个专门处理请求的线程才会真正采用NIO的方式，像Web服务器Tomcat和Jetty都是使用这个处理方式。

### 1.3 Buffer的工作方式

下面讨论Buffer如何接受和写出数据。

可以把Buffer简单地理解为一组基本数据类型的元素列表，它通过几个变量来保存这个数据的当前位置状态，也就是有4个索引：

- capacity（缓冲区数组的总长度）
- position（下一个要操作的数据元素的位置）
- limit（缓冲区数组中不可操作的下一个元素的位置，limit<=capacity）
- mark（用于记录当前position的前一个位置或者默认是0）

我们通过ByteBuffer.allocate(11)方法创建了一个11个byte的数组缓冲区，初始状态position的位置为0,capacity和limit默认都是数组长度。当我们写入5个字节时，position位置变化为5,limit和capacity不变。

这时候我们需要将缓冲区的5个字节数据写入Channel通信信道，所以我们调用byteBuffer.flip()方法，数组的状态position变成0，limit变为5，capacity不变。

这时底层操作系统就可以从缓冲区中正确读取这5个字节数据并发送出去了。在下一次写数据之前我们再调一下clear()方法，缓冲区的索引状态又回到初始位置。

这里还要说明一下mark，当我们调用mark()方法时，它将记录当前position的前一个位置，当我们调用reset时，position将恢复mark记录下来的值。

还有一点需要说明，通过Channel获取的I/O数据首先要经过操作系统的Socket缓冲区，再将数据复制到Buffer中，这个操作系统缓冲区就是底层TCP所关联的RecvQ或者SendQ队列，从操作系统缓冲区到用户缓冲区复制数据比较耗性能，Buffer提供了另外一种直接操作操作系统缓冲区的方式，即ByteBuffer.allocateDirector(size)，这个方法返回的DirectByteBuffer就是与底层存储空间关联的缓冲区，它通过Native代码操作非JVM堆的内存空间。每次创建或者释放的时候都调用一次System.gc()。注意，在使用DirectByteBuffer时可能会引起JVM内存泄露问题。

### 1.4 NIO的数据访问方式

NIO提供了比传统的文件访问方式更好的方法，NIO有两个优化方法：一个是FileChannel.transferTo、FileChannel.transferFrom；另一个是FileChannel.map。

FileChannel.transferXXX与传统的访问文件方式相比可以减少数据从内核到用户空间的复制，数据直接在内核空间中移动，在Linux中使用sendfile系统调用。

FileChannel.map将文件按照一定大小块映射为内存区域，当程序访问这个内存区域时将直接操作这个文件数据，这种方式省去了数据从内核空间向用户空间复制的损耗。这种方式适合对大文件的只读性操作，如大文件的MD5校验。但是这种方式是和操作系统的底层I/O实现相关的。

# Spring相关

## 1. AOP

### 1.1 AOP概念

### 1.2 AOP原理

### 1.3 cglib

（参考《Spring源码深度解析（第二版）》7.2.1 注册AnnotationAwareAspectJAutoProxyCreator P187/443）

proxy-target-class: SpringAOP部分使用JDK动态代理或者cglib来为目标对象创建代理（建议尽量使用JDK的动态代理）。如果被代理的目标对象实现了至少一个接口，则会使用JDK动态代理。所有该目标类型实现的接口都将被代理。若该目标对象没有实现任何接口，则创建一个cglib代理。

如果你希望强制使用cglib代理（例如希望代理目标对象的所有方法，而不只是实现自接口的方法），那也可以。但是需要考虑一下两个问题：

- 无法通知（advise）Final方法，因为它们不能被覆写。
- 你需要将cglib二进制发行包放在classpath下面。

与之相比，JDK本身就提供了动态代理，强制使用cglib代理需要将\<aop:config>的proxy-target-class属性设为true。

当需要使用cglib代理和@AspectJ自动代理支持，可以将\<aop:aspectj-autoproxy>的proxy-target-class属性设置为true。

而实现使用的过程中才会发现细节问题的差别：

- JDK动态代理：其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接口的实现类来完成对目标对象的代理。
- cglib代理：实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类。cglib是高效的代码生成包，底层是依靠ASM（开源的Java字节码编辑类库）操作字节码实现的，性能比JDK强。



## 2. 事务

### 2.1 事务的隔离级别

事务的隔离级别（五个）：

- `ISOLATION_DEFAULT`：默认事务隔离级别
- `ISOLATION_READ_UNCOMMITTED`：读未提交
- `ISOLATION_READ_COMMITTED`：读已提交
- `ISOLATION_REPEATABLE_READ`：重复读
- `ISOLATION_SERIALIZABLE`：串行化

> 回顾：事务并发存在的问题
>
> - 脏读：一个事务里，读取到另外一个事务未提交的数据
> - 不可重复读：一个事务里，多次读取的数据不一致：受到其它update的干扰了
> - 虚读/幻读：一个事务里，多次读取的数据不一致：受到其它insert、delete干扰了
>
> 解决事务并发问题的方案：通过设置不能的隔离级别
>
> - read uncommited：读未提交，什么问题都没有解决
> - read committed：读已提交，解决了脏读。 Oracle默认隔离级别
> - repeatable read：重复读，解决了脏读、不可重复读。MySql默认隔离级别
> - serializable：串行化，解决了所有问题

### 2.2 事务的传播行为

事务的传播行为（七个）：用于解决业务方法调用业务方法时，事务的统一性问题的

> 以下三个，是要当前事务的

- `PROPAGATION_REQUIRED`：需要有事务。默认
  - 如果已有事务，就使用这个事务
  - 如果没有事务，就创建事务。
- `PROPAGATION_SUPPORTS`：支持事务
  - 如果有事务，就使用当前事务，
  - 如果没有事务，就以非事务方式执行（没有事务）
- `PROPAGATION_MANDATORY`：强制的
  - 如果有事务，就使用当前事务
  - 如果没有事务，就抛异常

> 以下三个，是不要当前事务的

- `PROPAGATION_REQUIRES_NEW`：新建的
  - 如果有事务，就把事务挂起，再新建事务
  - 如果没有事务，新建事务
- `PROPAGATION_NOT_SUPPORTED`：不支持的
  - 如果有事务，就把事务挂起，以非事务方式执行
  - 如果没有事务，就以非事务方式执行
- `PROPAGATION_NEVER`：非事务的
  - 如果有事务，就抛异常
  - 如果没有事务，就以非事务方式执行

> 最后一个，是特殊的

- `PROPAGATION_NESTED`：嵌套的
  - 如果有事务，就在事务里再嵌套一个事务执行
  - 如果没有事务，就是类似`REQUIRED`的操作



## 3. Bean的生命周期



## 4. IOC



## 5. Spring的整体结构

（参考《Spring源码深度解析（第二版）》1.1 Spring的整体架构 P9/443）

Spring框架是一个分层架构，它包含一系列的功能要素，并被分为大约20个模块。

![1566803671501](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1566803671501.png)

这些模块被总结为一下几部分。

### 5.1  Core Container

Core Container（核心容器）包含有Core、Beans、Context和Expression Language模块。

Core和Beans模块是框架的基础部分，提供IoC（转控制）和依赖注入特性。这里的基础概念是BeanFactory，它提供Factory模式的经典实现来消除对程序性单例模式的需要，并真正地允许你从程序逻辑中分离出依赖关系和配置。

- Core模块主要包含Spring框架基本的核心工具类，Spring的其他组件都要用到这个包里的类，Core模块是其他组件的基本核心。
- Beans模块是所有应用都要用到的，它包含访问配置文件、创建和管理bean以及进行Inversion of Control/Dependency Injection(IoC、DI)操作相关的所有类。
- Context模块构建于Core和Beans模块基础上，提供了一种类似于JNDI注册器的框架式的对象访问方法。Context模块继承了Beans的特性，为Spring核心提供了大量扩展，添加了对国际化（例如资源绑定）、事件传播、资源加载和对Context的透明创建的支持。Context模块同时也支持J2EE的一些特性，例如EJB、JMX和基础的远程处理。ApplicationContext接口是Context模块的关键。
- Expression Language模块提供了强大的表达式语言，用于在运行时查询和操纵对象。它是JSP 2.1规范中定义的unified expression language的扩展。该语言支持设置/获取属性的值，属性的分配，方法的调用，访问数组上下文、容器和索引器、逻辑和算术运算符、命名变量以及从Spring的IoC容器中根据名称检索对象。它也支持list投影、选择和一般的list聚合。

### 5.2 Data Access/Integration

Data Access/Integration 层包含JDBC、ORM、OXM、JMS和Transaction模块。

- JDBC模块提供了一个JDBC抽象层，它可以消除冗长的JDBC编码和解析数据库厂商特有的错误代码。这个模块包含了Spring对JDBC数据访问进行封装的所有类。
- ORM模块为流行的对象-关系映射API，如JPA、JDO、Hibernate、iBatis等，提供了一个交互层。利用ORM封装包，可以混合使用所有Spring提供的特性进行O/R映射，如前边提到的简单声明性事务管理。

Spring框架插入了若干个ORM框架，从而提供了ORM的对象关系工具，其中包括JDO、Hibernate和iBatisSQL Map。所有这些都遵从Spring的通用事务和DAO异常层次结构。

- OXM模块提供了一个对Object/XML 映射实现的抽象层，Object/XML映射实现包括JAXB、Castor、XMLBeans、JiBX和XStream。
- JMS（Java Message Service）模块主要包含了一些制造和消费消息的特性。
- Transaction模块支持编程和声明性的事务管理，这些事务类必须实现特定的接口，并且对所有的POJO都适用。

### 5.3 Web

Web上下文模块建立在应用程序上下文模块之上，为基于Web的应用程序提供了上下文。所以Spring框架支持与Jakarta Structs的集成。Web模块还简化了处理大部分请求以及将请求参数绑定到域对象的工作。Web层包含了Web、Web-Servlet、Web-Struts和Web-Porlet模块。

- Web模块：提供了基础的面向Web的集成特性。例如，多文件上传、使用servlet listeners初始化IoC容器以及一个面向Web的应用上下文。它还包含Spring远程支持中Web 的相关部分。
- Web-Servlet模块web.servlet.jar: 该模块包含Spring的model-view-controller（MVC）实现。Spring的MVC框架使得模型范围内的代码和web forms之间能够清楚地分离开来，并与Spring框架的其他特性集成在一起。
- Web-Struts模块：该模块提供了对Struts的支持，使得类在Spring应用中能够与一个典型的Struts Web层集成在一起。注意，该支持在Spring 3.0中已被弃用。
- Web-Porlet：提供了用于Portlet环境和Web-Servlet模块的MVC的实现。

### 5.4 AOP

- Aspects模块提供了对AspectJ的集成支持。
- Instrumentation模块提供了class instrumentation支持和classloader实现，使得可以在特定的应用服务器上使用。

### 5.5 Test

Test模块支持使用JUnit和TestNG对Spring组件进行测试。



# MyBatis相关

## 1. MyBatis如何进行类型转换



## 2. MyBatis的XML有什么标签



# Spring MVC相关

## 1. Spring MVC的原理和流程



# Spring Boot相关

## 1. 加载顺序

（参考《Spring Cloud 微服务实战》翟永超 第2章 微服务构建：Spring Boot P44/442）

为了能够更合理地重写各属性的值，Spring Boot使用了下面这种较为特别的属性加载顺序：

1. 在命令行中传入的参数。
2. SPRING_APPLICATION_JSON中的属性。SPRING_APPLICATION_JSON是以JSON格式配置在系统环境变量中的内容。
3. java:comp/env中的JNDI属性。
4. Java 的系统属性，可以通过System.getProperties()获得的内容。
5. 操作系统的环境变量。
6. 通过random.\*配置的随机属性。
7. 位于当前应用jar包之外，针对不同{profile}环境的配置文件内容，例如application-{profile}.properties或是YAML定义的配置文件。
8. 位于当前应用jar包之内，针对不同{profile}环境的配置文件内容，例如application-{profile}.properties或是YAML定义的配置文件。
9. 位于当前应用jar包之外的application.properties和YAML配置内容。
10. 位于当前应用jar包之内的application.properties和YAML配置内容。
11. 在@Configuration注解修改的类中，通过@PropertySource注解定义的属性。
12. 应用默认属性，使用SpringApplication.setDefaultProperties定义的内容。

优先级按上面顺序由高到低，数字越小优先级越高。

可以看到，其中第7项和第9项都是从应用jar包之外读取配置文件，所以，实现外部化配置的原理就是从此切入，为其指定外部配置文件的加载位置来取代jar包之内的配置内容。通过这样的实现，我们的工程在配置中就变得非常干净，只需在本地放置开发需要的配置即可，而不用关心其他环境的配置，由其对于环境的负责人去维护即可。



## 2.  Starter原理



# 计算机网络相关

## 1. TCP

### 1.1 TCP最主要的特点

（参考《计算机网络（第7版）》谢希仁 5.3.1 TCP最主要的特点 P221/464）

- TCP是**面向连接的运输层协议**。也就是说，应用程序在使用TCP协议之前，必须先建立TCP连接。在传送数据完毕后，必须释放已经建立的TCP连接。也就是说，应用进程之间的通信好像在“打电话”：通话前要先拨号建立连接，通话结束后要挂机释放连接。
- 每一条TCP连接只能有两个**端点（endpoint）**，每一条TCP连接只能是**点对点**的（一对一）。
- TCP提供**可靠交付**的服务。通过TCP连接传送的数据，无差错、不丢失、不重复，而且按序到达。
- TCP提供**全双工通信**。TCP允许通信双方的应用进程在任何时候都能发送数据。TCP连接的两端都设有发送缓存和接收缓存，用来临时存放双向通信的数据。在发送时，应用程序在把数据传送给TCP的缓存后，就可以做自己的事，而TCP在合适的时候把数据发送出去。在接收时，TCP把收到的数据放入缓存，上层的应用进程在合适的时候读取缓存中的数据。
- **面向字节流**。TCP中的“流”（stream）指的是**流入到进程或从进程流出的字节序列**。“面向字节流”的含义是：虽然应用程序和TCP的交互是一次一个数据块（大小不等），但TCP把应用程序交下来的数据仅仅看成是一连串的**无结构的字节流**。TCP不保证接受方应用程序所收到的数据块和发送方应用程序所发出的数据块具有对应大小的关系（例如，发送方应用程序交给发送方的TCP共10个数据块，但接收方的TCP可能只用了4个数据块就把收到的字节流交付上层的应用程序）。但接收方应用程序收到的字节流必须和发送方应用程序发出的字节流完全一样。当然，接收方的应用程序必须有能力识别收到的字节流，把它还原成有意义的应用层数据。

### 1.2 TCP的运输连接管理（三次握手、四次挥手）

（参考《计算机网络（第7版）》谢希仁 5.9 TCP的运输连接管理 P248/464）

TCP是面向连接的协议。运输连接是用来传送TCP报文的。TCP运输连接的建立和释放是每一次面向连接的通信中必不可少的过程。因此，运输连接就有三个阶段，即：**连接建立**、**数据传送**和**连接释放**。运输连接的管理就是使运输连接的建立和释放都能正常地进行。

在TCP连接建立过程中要解决一下三个问题：

1. 要使每一方能够确知对方的存在。
2. 要允许双方协商一些参数（如最大窗口值、是否使用窗口扩大选项和时间戳选项以及服务质量等）。
3. 能够对运输实体资源（如缓存大小、连接表中的项目等）进行分配。

#### 1.2.1 TCP的连接建立（三次握手）

TCP建立连接的过程叫做握手，握手需要在客户和服务器之间交换三个TCP报文段。

假定主机A运行的是TCP客户程序，而B运行的是TCP服务器程序。最初两端的TCP进程都处于CLOSED（关闭）状态。注意，在本例中，A主动打开连接，而B被动打开连接。

- 一开始，B的TCP服务器进程先创建传输控制块TCB，准备接受客户进程的连接请求。然后服务器进程就处于LISTEN（收听）状态，等待客户的连接请求。如有，即做出响应。
- A的TCP客户进程也是首先创建传输控制模块TCB。然后，在打算建立TCP连接时，向B发出连接请求报文段，这时首部中的同步位SYN=1，同时选择一个初始序号seq = x。TCP规定，SYN报文段（即SYN=1的报文段）不能携带数据，但要消耗掉一个序号，这时，TCP客户进程进入SYN-SENT（同步已发送）状态。
- B收到连接请求报文段后，如同意建立连接，则向A发送确认。在确认报文段中应把SYN位和ACK位都置1，确认号是ack = x+1，同时也为自己选择一个初始序号seq = y。请注意，这个报文段也不能携带数据，但同样要消耗掉一个序号。这时TCP服务器进程进入SYN-RCVD（同步收到）状态。
- TCP客户进程收到B的确认后，还要向B给出确认。确认报文段的ACK置1，确认号ack = y + 1，而自己的序号seq = x + 1。TCP的标准规定，ACK报文段可以携带数据。但**如果不携带数据则不消耗序号**，在这种情况下，下一个数据报文段的序号仍是seq = x+1。这时，TCP连接已经建立，A进入ESTABLISHED（已建立连接）状态。
- 当B收到A的确认后，也进入ESTABLISHED状态。

上面给出的连接建立过程叫做**三报文握手**（三次握手）。请注意，B发送给A的报文段，也可以拆成两个报文段。可以先发送一个确认报文段（ACK = 1， ack = x+1），然后在发送一个同步报文段（SYN = 1，seq = y）。这样的过程就变成了**四报文握手**，但效果是一样的。

为什么A最后还要发送一次确认呢？这主要是为了防止已失效的连接请求报文段突然又传送到了B因而产生错误。

所谓“已失效的连接请求报文段”是这样产生的。考虑一种正常情况，A发出连接请求，但因连接请求报文丢失而未收到确认。于是A再重传一次连接请求。后来收到了确认，建立了连接。数据传输完毕后，就释放了连接。A共发送了两个连接请求报文段，其中第一个丢失，第二个到达了B，没有“已失效的请求报文段”。

现假定出现一种异常情况，即A发出的第一个连接请求报文并没有丢失，而是在某些网络结点长时间滞留了，以致延误到连接释放以后的某个时间才到达B。本来这是一个早已失效的报文段。但B收到此失效的连接请求报文段后，就误认为A又发出了一次新的连接请求。于是就向A发出确认报文段，同意建立连接。假定不采用报文握手，那么只要B发出确认，新的连接就建立了。

由于现在A并没有发出建立连接的请求，因此不会理睬B的确认，也不会向B发送数据。但B却认为新的运输连接已经建立了，并一直等待A发来数据。B的许多资源就这样白白浪费了。

采用三报文握手的办法，可以防止上述现象的发生。例如在刚才的异常情况下，A不会像B的确认发出确认。B由于收不到确认，就知道A并没有要求建立连接。

#### 1.2.2 TCP的连接释放（四次挥手）

TCP连接释放过程比较复杂，我们仍结合双方状态的改变来阐明连接释放的过程。

- 数据传输后，通信双方都可释放连接。现在A和B都处于ESTABLISHED状态。A的应用进程先向其TCP发出连接释放报文段，并停止再发送数据，主动关闭TCP连接。A把连接释放报文段首部的终止控制位FIN置1，其序号seq = u,它等于前面已传送过的数据的最后一个字节的序号加1.这时A进入FIN-WAIT-1（终止等待1）状态，等待B的确认。请注意，TCP规定，FIN报文段即使不携带数据，它也消耗掉一个序号。
- B收到连接释放报文段后即发出确认，确认号是ack = u+1，而这个报文段自己的序号是v，等于B之前已传送过的数据的最后一个字节的序号加1.然后B进入CLOSE-WAIT（关闭等待）状态。TCP服务器进程这时应通知高层应用进程，因而从A到B这个方向的连接就释放了，这时的TCP连接处于**半关闭（half-close）**状态，即A已经没有数据要发送了，但B若发送数据，A仍要接受。也就是说，从B到A这个方向的连接并未关闭，这个状态可能会持续一段时间。
- A收到来自B的确认后，就进入FIN-WAIT-2（终止等待2）状态，等待B发出的连接释放报文段。
- 若B已经没有要向A发送的数据，其应用进程就通知TCP释放连接。这时B发出连接释放报文段必须使FIN=1.现假定B的序号为w（在半关闭状态B可能又发送了一些数据）。B还必须重复上次已发送过的确认号ack = u+1。这时B就进入LAST-ACK（最后确认）状态，等待A的确认。
- A在收到B的连接释放报文段后，必须对此发出确认。在确认报文段中把ACK置1，确认号ack = w+1，而自己的序号是seq = u+1(根据TCP标准，前面发送过的FIN报文段要消耗一个序号)。然后进入到TIME-WAIT（时间等待）状态。请注意，现在TCP连接还没有释放掉。必须经过**时间等待计时器（TIME-WAIT timer）**设置的时间2MSL后，A才进入到CLOSED状态。时间MSL叫做**最长报文段寿命（Maximum Segment Lifetime）**，RFC 793建议设为2分钟。但这个完全是从工程上来考虑的，对于现在的网络，MSL=2分钟可能太长了一些。因此TCP允许不同的实现可根据具体情况使用更小的MSL值。因此，从A进入到TIME-WAIT状态后，要经过4分钟才能进入到CLOSED状态，才能开始建立下一个新的连接。当A撤销相应的传输控制块TCB后，就结束了这次的TCP连接。

为什么A在TIME-WAIT状态必须等待2MSL时间呢？这有两个理由

第一，为了保证A发送的最后一个ACK报文段能到达B。这个ACK报文段有可能丢失，因而使处在LAST-ACK状态的B收不到对已发送FIN+ACK报文段的确认。B会超时重传这个FIN+ACK的报文段，而A能在2MSL时间内收到这个重传的FIN+ACK报文段。接着A重传一次确认，重新启动2MSL计时器，最后，A和B都正常进入到CLOSED状态。如果A在TIME-WAIT状态下不等待一段时间，而是在发送完ACK报文段后立即释放连接，那么就无法收到B重传的FIN+ACK报文段，因而也不会再发送一次确认报文段。这样，B就无法按照正常步骤进入CLOSED状态。

第二，防止上一节提到的“已失效的连接请求报文段”出现在本连接中。A在发送完最后一个ACK报文段后，再经过时间2MSL，就可以使本连接持续的时间内所产生的所有报文段都从网络中消失。这样就可以使下一个新的连接中不会出现这种旧的连接请求报文段。

B只要收到了A发出的确认，就进入CLOSED状态。同样，B在撤销相对应的传输控制块TCB后，就结束了这次TCP连接。我们注意到，B结束TCP连接的时间要比A早一些。

上述的TCP连接释放过程是四报文握手（四次挥手）。

除时间等待计时器外，TCP还设有一个**保活计时器（keepalive timer）**。设想有这样的情况：客户已主动与服务器建立了TCP连接。但后来客户端的主机突然出故障。显然，服务器以后就不能再收到客户发来的数据。因此，应当有措施使服务器不要再白白等待下去。这就是使用保活计时器。服务器每收到一次客户的数据，就重新设置保活计时器，时间的设置通常是两小时。若两小时没有收到客户的数据，服务器就发送一个探测报文段，以后则每隔75秒钟发送一次。若一连发送10个探测报文段后仍无客户的响应，服务器就认为客户端出了故障，接着就关闭这个连接。

### 1.3 TCP的拥塞控制

（参考《计算机网络（第7版）》谢希仁 5.8 TCP的拥塞控制 P239/464）

#### 1.3.1 拥塞控制的一般原理

在计算机网络中的链路容量（即带宽）、交换结点中的缓存和处理机等，都是网络的资源。在某些时间，若对网络中某一资源的需求超过了该资源所能提供的可用部分，网络的性能就要变坏。这种情况就叫做**拥塞（congestion）**。可以把出现网络拥塞的条件写成如下的关系式：`∑对资源的需求>可用资源`

若网络有许多资源同时呈现供应不足，网络的性能就要变坏，整个网络的吞吐量将随输入负荷的增大而下降。

网络拥塞往往是由许多因素引起的。例如，当某个结点缓存的容量太小时，到达该节点的分组因无存储空间暂存而不得不被丢弃。现在设想该结点缓存的容量扩展到非常大，于是凡到达该结点的分组均可在结点的缓存队列中排队，不受任何限制。由于输出链路的容量和处理机的速度并未提高，因此在这队列中的绝大多数分组的排队等待时间将会大大增加，结果上层软件只好把它们进行重传（因为早就超时了）。由此可见，简单地扩大缓存的存储空间同样会造成网络资源的严重浪费，因而解决不了网络拥塞的问题。

又如，处理机处理的速率太慢可能引起网络的拥塞。简单地将处理机的速率提高，可能会使上述情况缓解一些，但往往又会将瓶颈转移到其他地方。问题的实质往往是整个系统的各个部分不匹配。只有所有的部分都平衡了，问题才会得到解决。

拥塞常常趋于恶化。如果一个路由器没有足够的缓存空间，它就会丢弃一些新到的分组。但当分组被丢弃时，发送这一分组的源点就会重传这一分组，甚至可能还要重传多次。这样会引起更多的分组流入网络和被网络中的路由器丢弃。可见拥塞引起的重传并不会缓解网络的拥塞，反而会加剧网络的拥塞。

拥塞控制与流量控制的关系密切，它们之间也存在着一些差别。所谓**拥塞控制**就是**防止过多的数据注入到网络中，这样可以使网络中的路由器或链路不致过载**。拥塞控制所要做的都有一个前提，就是**网络能够承受现有的网络负荷**。拥塞控制是一个**全局性的过程**，涉及到所有的主机、所有的路由器，以及降低网络传输性能有关的所有因素。但TCP连接的端点只要迟迟不能收到对方的确认信息，就猜想在当前网络中的某处很可能发生了拥塞，但这时却无法知道拥塞到底发生在网络的何处，也无法知道发生拥塞的具体原因。（是访问某个服务器的通信量过大？还是某个地区出现自然灾害？）

相反，**流量控制往往是指点对点通信量的控制**，是一个**端到端**的问题（接收端控制发送端）。流量控制所要做的就是抑制发送端发送数据的速率，以便使接收端来得及接收。

拥塞控制和流量控制之所以常常被弄混，是因为某些拥塞控制算法是向发送端发送控制报文，并告诉发送端，网络已出现麻烦，必须放慢发送速率。这点又和流量控制是很相似的。

#### 1.3.2 TCP的拥塞控制方法

TCP进行拥塞控制的算法有四种，即**慢开始（slow-start）**、**拥塞避免（congestion avoidance）**、**快重传（fast retransmit）**和**快恢复（fast recovery）**（见2009年9月公布的草案标准RFC 5681）。下面就介绍这些算法的原理。为了集中精力讨论拥塞控制，我们假定：

1. 数据是单方向传送的，对方只传送确认报文。
2. 接收方总是有足够大的缓存空间，因而发送窗口的大小由网络的拥塞程度来决定。

##### 慢开始

下面讨论的拥塞控制也叫做**基于窗口**的拥塞控制。为此，发送方维持一个叫做**拥塞窗口**cwnd（congestion window）的状态变量。拥塞窗口的大小取决于网络的拥塞程度，并且动态地在变化。**发送方让自己的发送窗口等于拥塞窗口**。

发送方控制拥塞窗口的原则是：只要网络没有出现拥塞，拥塞窗口就可以再增大一些，以便把更多的分组发送出去，这样就可以提高网络的利用率。但只要网络出现拥塞或者有可能出现拥塞，就必须把拥塞窗口减少一些，以减少注入到网络中的分组数，以便缓解网络出现的拥塞。

发送方又是如何知道网络发生了拥塞呢？我们知道，当网络发生拥塞时，路由器就要丢弃分组。因此只要发送方没有按时收到应该到达的确认报文，也就是说，只要出现了超时，就可以猜想网络可能出现了拥塞。现在通信线路的传输质量一般都很好，因传输出差错而丢弃分组的概率是很小的（远小于1%）。因此，判断网络拥塞的依据就是出现了超时。

下面将讨论拥塞窗口cwnd的大小是怎样变化的。我们从“慢开始算法”讲起。

**慢开始**算法的思路是这样的：当主机开始发送数据时，由于并不清楚网络的负荷情况，所以如果立即把大量字节数据字节注入到网络，那么就有可能引起网络发生拥塞。经验证明，较好的方法是先探测一下，即**由小到大逐渐增大发送窗口**，也就是说，**由小到大逐渐增大拥塞窗口数值**。

旧的规定是这样的：在刚刚开始发送报文段时，先把初始拥塞窗口cwnd设置为1至2个发送方的最大报文段SMSS（Sender Maximum Segment Size）的数值，但新的RFC 5681把初始拥塞窗口cwnd设置为不超过2至4个SMSS的数值。具体规定如下：

- 若SMSS>2190字节，则设置初始拥塞窗口cwnd = 2×SMSS字节，且**不得超过**2个报文段。
- 若（SMSS>1095字节）且（SMSS≤2190字节），则设置初始拥塞窗口cwnd = 3×SMSS字节，且**不得超过**3个报文段。
- 若SMSS≤1095字节，则设置初始拥塞窗口cwnd = 4×SMSS字节，且**不得超过**4个报文段。

可见这个规定就是限制初始拥塞窗口的字节数。

慢开始规定，在每收到一个**对新的报文段的确认**后，可以把拥塞窗口增加最多一个SMSS的数值。更具体些，就是`拥塞窗口cwnd每次的增加量 = min(N,SMSS)`

其中N是原先未被确认的、但现在被刚收到的确认报文段所确认的字节数。不难看出，当N<SMSS时，拥塞窗口每次的增加量要小于SMSS。

用这样的方法逐步增大发送方的拥塞窗口cwnd，可以使分组注入到网络的速率更加合理。下面用例子说明慢开始算法的原理。请注意，虽然实际上TCP是用字节数作为窗口大小的单位。但为叙述方便起见，我们**用报文段的个数作为窗口大小的单位**，这样可以使用较小的数字来阐明拥塞控制的原理。

在一开始发送方先设置cwnd = 1，发送第一个报文段M~1~，接收方收到后确认M~1~ 。发送方收到对M~1~ 的确认后，把cwnd从1增加到2，于是发送方接着发送M~2~ 和M~3~ 两个报文段。接收方收到后发回对M~2~ 和M~3~ 的确认。发送方每收到一个**对新报文段的确认**（重传的不算在内）就使发送方的拥塞窗口加1，因此发送方在收到两个确认后，cwnd就从2增大到4，并可发送M~4~ \~ M~7~ 共4个报文段。因此使用慢开始算法后，**每经过一个传输轮次（transmission round），拥塞窗口cwnd就加倍。**

这里我们使用了一个名词——**传输轮次**。一个传输轮次所经历的时间其实就是往返时间RTT（请注意，RTT并非是恒定的数值）。使用“传输轮次”是更加强调：把拥塞窗口cwnd所允许发送的报文段都连续发送出去，并收到了对已发送的最后一个字节的确认。例如，拥塞窗口cwnd的大小是4个报文段，那么这时的往返时间RTT就是发送方连续发送4个报文段，并收到这4个报文段的确认，总共经历的时间。

我们还要指出，慢开始的“慢”并不是指cwnd的增长速率慢，而是指在TCP开始发送报文段时先设置cwnd = 1，使得发送方在开始时只发送一个报文段（目的是试探一下网络的拥塞情况），然后再逐渐增大cwnd。这当然比设置大的cwnd值一下子把许多报文段注入到网络要“**慢得多**”。这对防止网络出现拥塞是一个非常好的方法。

在TCP的实际运行中，发送方只要收到一个对新报文段的确认，其拥塞窗口cwnd就立即加1，并可以立即发送新的报文段，而不需要等这个轮次中所有的确认都收到后再发送新的报文段。

##### 拥塞避免

为了防止拥塞窗口cwnd增长过大引起网络拥塞，还需要设置一个**慢开始门限**ssthresh状态变量（如何设置ssthresh，后面还要讲）。慢开始门限ssthresh的用法如下：

- 当cwnd<ssthresh时，使用上述的慢开始算法。
- 当cwnd>ssthresh时，停止使用慢开始算法而改用拥塞避免算法。
- 当cwnd=ssthresh时，既可以使用慢开始算法，也可以使用拥塞避免算法。

**拥塞避免**算法的思路是让拥塞窗口cwnd缓慢地增大，即每经过一个往返时间RTT就把发送方的拥塞窗口cwnd加1，而不是像慢开始阶段那样加倍增长。因此在拥塞避免阶段就有“**加法增大**”AI（Additive Increase）的特点。这表明在拥塞避免阶段，拥塞窗口cwnd**按线性规律缓慢增长**，比慢开始算法的拥塞窗口增长速率缓慢得多。

但请注意，“拥塞避免”并非完全能够避免了拥塞。“拥塞避免”是说把拥塞窗口控制为按线性规律增长，使网络比较不容易出现拥塞。

##### 快重传

当发送方一连收到3个对同一个报文段的重复确认（记为3-ACK）的情况，关于这个问题要解释如下。

有时，个别报文段会在网络中丢失，但实际上网络并未发生拥塞。如果发送方迟迟收不到确认，就会产生超时，就会误认为网络发生了拥塞，这就导致发送放错误地启动慢开始，把拥塞窗口cwnd又设置为1，因而降低了传输效率。

采用快重传算法可以让发送方**尽早知道发生了个别报文段的丢失**。快重传算法首先要求接收方不要等待自己发送数据时才进行捎带确认，而是要**立即发送确认**，即使收到了**失序的报文段**也要立即发出对已收到的报文段的重复确认。快重传算法规定，发送方只要**一连收到3个重复确认**，就知道接收方确实没收到报文段，因而应当**立即进行重传**（即“快重传”），这样就不会出现超时，发送方也就不会误认为出现了网络拥塞。使用快重传可以使整个网络的吞吐量提高约20%。

##### 快恢复

在快重传后，发送方知道现在只是丢失了个别报文段。于是不启动慢开始，而是执行**快恢复**算法。这是，发送方调整门限值ssthresh = cwnd/2，同时设置拥塞窗口cwnd = ssthresh，并开始执行拥塞避免算法。

TCP Reno版本，表示区别于老的TCP Tahao版本。

请注意，也有的快恢复实现是把快恢复开始时的拥塞窗口cwnd值再增大一些（增大3个报文段的长度），即等于新的ssthresh+3×MSS。这样做的理由是：既然发送方收到3个重复的确认，就表明有3个分组已经离开了网络。这3个分组不再消耗网络的资源而是停留在接收方的缓存中（接收方发送出3个重复的确认就证明了这个事实）。可见现在网络中并不是堆积了分组而是减少了3个分组。因此可以适当把拥塞窗口扩大些。

在拥塞避免阶段，拥塞窗口是按线性规律增大的，这常称为**加性增大**AI（Additive Increase）。而一旦出现超时或3个重复的确认，就要把门限值设置为当前拥塞窗口值的一半，并大大减少拥塞窗口的数值。这常称为“**乘性减小**”MD（Multiplicative Decrease）。两者合在一起就是所谓的AIMD算法。

采用这样的拥塞控制方法使得TCP的性能有明显的改进。

~~~mermaid
graph TD
连接建立-->slowstart[慢开始: 拥塞窗口cwnd=1按指数规律增大, cwnd>=ssthresh]
slowstart-->congestionAvoidance[拥塞避免: 拥塞窗口cwnd按线性规律增大]

slowstart--3个重复ACK-->3ACK[ssthresh = cwnd/2, cwnd = ssthresh]
congestionAvoidance--3个重复ACK-->3ACK
3ACK-->congestionAvoidance

congestionAvoidance-->连接终止

congestionAvoidance--超时-->timeout[ssthresh = cwnd/2, cwnd = 1]
slowstart--超时-->timeout
timeout-->slowstart
~~~

在这一节的开始我们就假定了接收方总是有足够大的缓存空间，因而发送窗口的大小由网络的拥塞程度来决定。但实际上接收方的缓存空间总是有限的。接收方根据自己的接受能力设定了接收方窗口rwnd，并把这个窗口值写入TCP首部中的窗口字段，传送给发送方。因此，**接收方窗口**又称为**通知窗口**（advertised window）。因此，从接收方对发送方的流量控制的角度考虑，**发送方的发送窗口一定不能超过对方给出的接收方窗口值rwnd**。

如果把本节所讨论的拥塞控制和接收方对发送方的流量控制一起考虑，那么很显然，发送方的窗口的上限值应当取为接收方窗口rwnd和拥塞窗口cwnd这两个变量中较小的一个，也就是说：`发送方窗口的上限值 = Min[rwnd, cwnd]`



## 2. SSL、TLS



## 3. HTTP

### 1.1 HTTP幂等设计



### 1.2 HTTP访问流程



### 1.3 HTTP状态码



# 前端相关



## 1. JavaScript



### 1.1 闭包

（参考《JavaScript高级程序设计（第3版）》 7.2 闭包 P178（197/749））

有不少开发人员总是搞不清楚匿名函数和闭包这两个概念，因此经常混用。**闭包**是指有权访问另一个函数作用域中的变量的函数。创建闭包的常见方式，就是在一个函数内部创建另一个函数。

第4章介绍了作用域链的概念。而有关如何创建作用域链以及作用域链有什么作用的细节，对彻底理解闭包至关重要。当某个函数第一次被调用时，会创建一个执行环境（execution context）及相应的作用域链，并把作用域链赋值给一个特殊的内部属性（即[scope]）。然后，使用this.arguments和其他命名函数的值来初始化函数的活动对象（activation object）。但在作用域链中，外部函数的活动对象始终处于第二位，外部函数的外部函数的活动对象处于第三位，……直至作为作用域终点的全局执行环境。



## 2. 防止表单重复提交

# 操作系统相关

## 1. 进程间通信方式



## 2. 内存分配算法



## 3. 缓存淘汰算法



# Linux相关

## 1. I/O多路复用（select、poll、epoll）



## 2. Linux命令



## 3. linux的文件权限（比如：如何修改）

# Java基础

## 1. 异常继承体系



## 2. 变量初始化顺序



## 3. 自动装箱、拆箱

~~~java
public class SublimeTextTest{
	public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c==d);//true
        System.out.println(e==f);//false，超过了127
        System.out.println(c==(a+b));//true
        System.out.println(c.equals(a+b));//true
        System.out.println(g==(a+b));//true
        //System.out.println(g==c);没有运算无法自动拆箱
        System.out.println(g.equals(a+b));//false
    }
}
~~~



## 4. 语法糖

（参考《深入理解Java虚拟机：JVM高级特性与最佳实践（最新第二版）》 10.3 java语法糖的味道 P334/457）

除了介绍的泛型、自动装箱、自动拆箱、遍历循环、变长参数和条件编译之外，Java语言还有不少其他的语法糖，如内部类、枚举类、断言语句、对枚举和字符串（在JDK 1.7中支持）的switch支持、try语句中定义和关闭资源（在JDK1.7中支持）等，读者可以通过跟踪Javac源码、反编译Class文件等方式了解它们本质实现。

# 编译原理相关

## 1. 编译型语言和解释型语言



# 数据库相关

## 1. MySQL引擎



### 1.1 InnoDB存储引擎

（参考《高性能MySQL（第三版）》 1.5.1  InnoDB存储引擎 P52/800）

InnoDB是MySQL的默认事务型引擎，也是最重要、使用最广泛的存储引擎。它被设计用来处理大量的短期（short-lived）事务，短期事务大部分情况是正常提交的，很少会被回滚。InnoDB的性能和自动崩溃恢复特性，使得它在非事务型存储的需求中也很流行。

- InnoDB的数据存储在表空间（tablespace）中，表空间是由InnoDB管理的一个黑盒子，由一系列的数据文件组成。在MySQL 4.1以后的版本中，InnoDB可以将每个表的数据和索引存放在单独的文件中。InnoDB也可以使用裸设备作为表空间的存储介质，但现代的文件系统使得裸设备不再是必要的选择。
- InnoDB采用MVCC来支持高并发，并且实现了四个标准的隔离级别。其默认级别是REPEATABLE READ（可重复读），并且通过间隙锁（next-key locking）策略防止幻读的出现。间隙锁使得InnoDB不仅仅锁定查询涉及的行，还会对索引中的间隙进行锁定，以防止幻影行的插入。
- InnoDB表是基于聚簇索引建立的。InnoDB的索引结构和MySQL的其他存储引擎有很大的不同，聚簇索引对主键查询有很高的性能，不过它的二级索引（secondary index，非主键索引）中必须包含主键列，所以如果主键列很大的话，其他的所有索引都会很大。因此，若表中的索引较多的话，主键应当尽可能的小。InnoDB的存储格式是平台独立的，也就是说可以将数据和索引文件从Intel平台复制到PowerPC或者Sun SPARC平台。
- InnoDB内部做了很多优化，包括从磁盘读取数据时采用的可预测性预读，能够自动在内存中创建hash索引以加速读操作的自适应哈希索引（adaptive hash index），以及能够加速插入操作的插入缓冲区（insert buffer）等。
- InnoDB通过一些机制和工具支持真正的热备份，Oracle提供的MySQL Enterprise Backup、Persona提供的开源的XtraBackup都可以做到这一点，MySQL的其他存储引擎不支持热备份，要获取一致性视图需要停止对所有表的写入，而在读写混合场景中，停止写入可能也意味着停止读取。



### 1.2 MyISAM存储引擎

（参考《高性能MySQL（第三版）》 1.5.2  MyISAM存储引擎 P53/800）

在MySQL 5.1及以前的版本，MyISAM是默认的存储引擎。MyISAM提供了大量的特性，包括全文索引、压缩、空间函数（GIS）等，但MyISAM不支持事务和行级锁，而且有一个毫无疑问的缺陷就是崩溃后无法安全恢复。对于只读的数据，或者表比较小、可以忍受修复（repair）操作，则依然可以继续使用MyISAM（但请不要默认使用MyISAM，而是应当默认使用InnoDB）。

MyISAM会将表存储在两个文件中：数据文件和索引文件，分别以.MYD和.MYI为扩展名。MyISAM表可以包含动态或者静态（长度固定）行。MySQL会根据表的定义来决定采用何种行格式。MyISAM表可以存储的行记录数，一般受限于可用的磁盘空间，或者操作系统中单个文件的最大尺寸。

#### MyISAM特性

作为MySQL最早的存储引擎之一，MyISAM有一些已经开发出来很多年的特性，可以满足用户的实际需求。

**加锁和并发**

MyISAM对整张表加锁，而不是针对行。读取时会对需要读到的所有表加共享锁，写入时则对表加排他锁。但是在表有读取查询的同时，也可以往表中插入新的记录（这被称为并发插入，CONCURRENT INSERT）。

**修复**

对于MyISAM表，MySQL可以手工或者自动执行检查和修复操作，但这里说的修复和事务恢复以及崩溃恢复是不同的概念。执行表的修复可能导致一些数据丢失，而且修复操作是非常慢的。可以通过CHECK TABLE mytable检查表的错误，如果有错误可以通过执行REPAIR TABLE mytable进行修复。另外，如果MySQL服务器已经关闭，也可以通过myisamchk命令行工具进行检查和修复操作。

**索引特性**

对于MyISAM表，即使是BLOB和TEXT等长字段，也可以基于其前500个字符创建索引。MyISAM也支持全文索引，这是一种基于分词创建的索引，可以支持复杂的查询。

**延迟更新索引键**

创建MyISAM表的时候，如果指定了DELAY_KEY_WRITE选项，在每次修改执行完成时，不会立刻将修改的索引数据写入磁盘，而是会写到内存中的键缓冲区（in-memory key buffer），只有在清理键缓冲区或者关闭表的时候才会将对应的索引块写入到磁盘。这种方式可以极大地提升写入性能，但是在数据库或者主机崩溃时会造成索引损坏，需要执行修复操作。延迟更新索引键的特性，可以在全局设置，也可以为单个表设置。

#### MyISAM压缩表

如果表在创建并导入数据以后，不会再进行修改操作，那么这样的表或许适合采用MyISAM压缩表。

可以使用myisampack对MyISAM表进行压缩（也叫做打包pack）。压缩表是不能进行修改的（除非先将表解除压缩，修改数据，然后再次压缩）。压缩表可以极大地减少磁盘空间占用，因此也可以减少磁盘I/O，从而提升查询性能。压缩表也支持索引，但索引也是只读的。

以现在硬件能力，对大多数应用场景，读取压缩表数据时的解压带来的开销影响并不大，而影响I/O带来的好处则要大得多。压缩时表中的记录是独立压缩的，所以读取单行的时候不需要去解压整个表（甚至也不解压行所在的整个页面）

#### MyISAM性能

MyISAM引擎设计简单，数据以紧密格式存储，所以在某些场景下的性能很好。MyISAM有一些服务器级别的性能扩展限制，比如对索引键缓冲区（key cache）的Mutex锁，MariaDB基于段（segment）的索引键缓冲区机制来避免该问题。但MyISAM最典型的性能问题还是表锁的问题，如果你发现所有的查询都长期处于“Locked”状态，那么毫无疑问表锁就是罪魁祸首。

### 1.3 MySQL内建的其他存储引擎

#### Archive引擎



#### Blackhole引擎



#### CSV引擎



#### Federated引擎



#### Memory引擎



#### Merge引擎



#### NDB集群引擎



## 2. ACID

（参考《从Paxos到Zookeeper 分布式一致性原理与实践》 1.2.1 ACID P21/438）

事务（Transaction）是由一系列对系统中数据进行访问与更新的操作所组成的一个程序执行逻辑单元（Unit），狭义上的事务特指数据库事务。一方面，当多个应用程序并发访问数据库时，事务可以在这些应用程序之间提供一个隔离方法，以防止彼此的操作互相干扰。另一方面，事务为数据库操作序列提供了一个从失败中恢复到正常状态的方法，同时提供了数据库即使在异常状态下仍能保持数据一致性的方法。

事务具有四个特征，分别是原子性（Atomicity），一致性（Consistency），隔离性（Isolation）和持久性（Durability），简称为事务的ACID特性。

### 2.1 原子性

事务的原子性是指事务必须是一个原子的操作序列单元。事务中包含的各项操作在一次执行过程中，只允许出现以下两种状态之一。

- 全部成功执行
- 全部不执行

任何一项操作失败都将导致整个事务失败，同时其他已经被执行的操作都将被撤销并回滚，只有所有的操作全部成功，整个事务才算是成功完成。

### 2.2 一致性

事务的一致性是指事务的执行不能破坏数据库数据的完整性和一致性，一个事务在执行之前和执行之后，数据库都必须处于一致性状态。也就是说，事务执行的结果必须是使数据库从一个一致性状态转变到另一个一致性状态，因此当数据库只包含成功事务提交的结果时，就能说数据库处于一致性状态。而如果数据库系统在运行过程中发生故障，有些事务尚未完成就被迫中断，这些未完成的事务对数据库所做的修改有一部分已写入物理数据库，这时数据库就处于一种不正确的状态，或者说是不一致的状态。

### 2.3 隔离性

事务的隔离性是指在并发环境中，并发的事务是相互隔离的，一个事务的执行不能被其他事务干扰。也就是说，不同的事务并发操纵相同的数据时，每个事务都有各自完整的数据空间，即一个事务内部的操作及使用的数据对其他并发事务是隔离的，并发执行的各个事务之间不能互相干扰。

在标准SQL规范中，定义了4个事务隔离级别，不同的隔离级别对事务的处理不同，如未授权读取、授权读取、可重复读和串行化。

#### 2.3.1 未授权读取

未授权读取也被称为读未提交（Read Uncommitted），该隔离级别允许脏读取，其隔离级别最低。换句话说，如果一个事务正在处理某一数据，并对其进行了更新，但同时尚未完成事务，因此还没有进行事务提交；而与此同时，允许另一个事务也能够访问该数据。举个例子来说，事务A和事务B同时进行，事务A在整个执行阶段，会将某数据项的值从1开始，做一系列加法操作（比如说加1操作）直到变成10之后进行事务提交，此时，事务B能够看到这个数据项在事务A操作过程中的所有中间值（如1变成2、2变成3等），而对这一系列的中间值的读取就是未授权读取。

#### 2.3.2 授权读取

授权读取也被称为读已提交（Read Committed），它和未授权读取非常相近，唯一的区别就是授权读取只允许获取已经被提交的数据。同样以上面的例子来说，事务A和事务B同时进行，事务A进行与上述同样的操作，此时，事务B无法看到这个数据项在事务A操作过程中的所有中间值，只能看到最终的10。另外，如果说有一个事务C，和事务A进行非常类似的操作，只是事务C是将数据项从10加到20，此时事务B也同样可以读到20，即授权读取允许不可重复读取。

#### 2.3.3 可重复读取

可重复读取（Repeatable Read），简单地说，就是保证在事务处理过程中，多次读取同一个数据时，其值都和事务开始时刻是一致的。因此该事务级别禁止了不可重复读取和脏读取，但是有可能出现幻影数据。所谓幻影数据，就是指同样的事务操作，在前后两个时间段内执行对同一个数据项的读取，可能出现不一致的结果。在上面的例子，可重复读取隔离级别能够保证事务B在第一次事务操作过程中，始终对数据项读取到1，但是在下一次事务操作中，即使事务B（注意，事务名字虽然相同，但是指的是另一次事务操作）采用同样的查询方式，就可能会读取到10或20.

#### 2.3.4 串行化

串行化（Serializable）是最严格的事务隔离级别。它要求所有事务都被串行执行，即事务只能一个接一个地进行处理，不能并发执行。

以上4个隔离级别的隔离性依次增强，分别解决不同的问题。

|  隔离级别  |  脏读  | 可重复读 |  幻读  |
| :--------: | :----: | :------: | :----: |
| 未授权读取 |  存在  |  不可以  |  存在  |
|  授权读取  | 不存在 |  不可以  |  存在  |
| 可重复读取 | 不存在 |   可以   |  存在  |
|   串行化   | 不存在 |   可以   | 不存在 |

事务隔离级别越高，就越能保证数据的完整性和一致性，但同时对并发性能的影响也越大。通常，对于绝大多数的应用程序来说，可以优先考虑将数据库系统的隔离级设置为授权读取，这能够在避免脏读取的同时保证较好的并发性能。尽管这种事务隔离级别会导致不可重复读、虚读和第二类丢失更新等并发问题，但较为科学的做法是在可能出现这类问题的个别场合中，由应用程序主动采用悲观锁或乐观锁来进行事务控制。

### 2.4 持久性

事务的持久性也被称为永久性，是指一个事务一旦提交，它对数据库中对应数据的状态变更就应该是永久性的。换句话说，一旦某个事务成功结束，那么它对数据库所做的更新就必须被永久保存下来——即使发生系统崩溃或机器宕机等故障，只要数据库能够重新启动，那么一定能够将其恢复到事务成功结束时的状态。



## 3. 索引

### 3.1 索引的类型

（参考《高性能MySQL（第三版）》 5.1.1 索引的类型 P178/800）

#### B-Tree索引

当人们谈论索引的时候，如果没有特别指明类型，那多半说的是B-Tree索引，它使用B-Tree数据结构来存储数据。大多数MySQL引擎都支持这种索引。Archive引擎是一个例外。

我们使用术语“B-Tree”是因为MySQL在CREATE TABLE和其他语句中也使用该关键字。不过，底层的存储引擎也可能使用不同的存储结构，例如，NDB集群存储引擎内部实际上使用了T-Tree结构存储这种索引，即使其名字是BTREE；InnoDB则使用的是B+Tree。

可以使用B-Tree索引的查询类型。B-Tree索引适用于全键值、键值范围或键前缀查找。其中键前缀查找只适用于根据最左前缀的查找。

- **全值匹配**：全值匹配指的是和索引中的所有列进行匹配
- **匹配最左前缀**：只使用索引的第一列。
- **匹配列前缀**：也可以只匹配某一列的值的开头部分。
- **匹配范围值**
- **精确匹配某一列并范围匹配另外一列**
- **只访问索引的查询**

因为索引树中的节点是有序的，所以除了按值查找之外，索引还可以用于查询中的ORDER BY操作（按顺序查找）。一般来说，如果B-Tree可以按照某种方式查找到值，那么也可以按照这种方式用于排序。所以，如果ORDER BY子句满足前面列出的几种查询类型，则这个索引也可以满足对于的排序需求。

下面是一些关于B-Tree索引的限制：

- 如果不是按照索引的最左列开始查找，则无法使用索引。
- 不能跳过索引中的列。
- 如果查询中有某个列的范围查询，则其右边所有列都无法使用索引优化查找。



#### 哈希索引

哈希索引（hash index）基于哈希表实现，只有精确匹配索引所有列的查询才有效。

InnoDB引擎有一个特殊的功能叫做“自适应哈希索引（adaptive hash index）”。当InnoDB注意到某些索引值被使用得非常频繁时，它会在内存中基于B-Tree索引之上再创建一个哈希索引，这样就让B-Tree索引也具有哈希索引的一些优点，比如快速的哈希查找。这是一个完全自动的、内部的行为，用户无法控制或者配置，不过如果有必要，完全可以关闭该功能。



### 3.2 索引的优点

（参考《高性能MySQL（第三版）》 5.2 索引的类型 P188/800）

最常见的B-Tree索引，按照顺序存储数据，所以MySQL可以用来做ORDER BY和GROUP BY操作。因为数据是有序的，所以B-Tree也就会将相关的列值都存储在一起。最后，因为索引中存储了实际的列值，所以某些查询只使用索引就能够完成全部查询。据此特性，总结下来索引有如下三个优点：

1. 索引大大减小了服务器需要扫描的数据量
2. 索引可以帮助服务器避免排序和临时表
3. 索引可以将随机I/O变为顺序I/O

Lahdenmaki和Leach在Relational Database Index Design and the Optimizers一书中介绍了如何评价一个索引是否适合某个查询的“三星系统”（three-star system）：索引将相关的记录放到一起则获得一星；如果索引中的数据顺序和查找中的排列顺序一致则获得二星；如果索引中的列包含了查询中需要的全部列则获得“三星”。



### 3.3 高性能的索引策略

（参考《高性能MySQL（第三版）》 5.3 高性能的索引策略 P189/800）

#### 独立的列

如果查询中的列不是独立的，则MySQL就不会使用索引。“独立的列”是指索引列不能是表达式的一部分，也不能是函数的参数。

例如，下面这个查询无法使用actor_id列的索引：`mysql> SELECT actor_id FROM sakila.actor WHERE actor_id + 1 = 5;`

我们应该养成简化WHERE条件的习惯，始终将索引列单独放在比较符号的一侧。

下面是另一个常见的错误：`mysql> SELECT ... WHERE TO_DAYS(CURRENT_DATE) - TO_DAYS(date_col) <= 10;`



## 4. MySQL分布式锁、MVCC

（参考《高性能MySQL（第三版）》 1.4  多版本并发控制 P48/800）

MySQL的大多数事务型存储引擎实现的都不是简单的行级锁。基于提升并发性能的考虑，它们一般都同时实现了多版本并发控制（MVCC）。不仅是MySQL，包括Oracle、PostgreSQL等其他数据库系统也都实现了MVCC，但各自的实现机制不尽相同，因为MVCC没有一个统一的实现标准。

MVCC的实现，是通过保存数据在某个时间点的快照来实现的。也就是说，不管需要执行多长时间，每个事务看到的数据都是一致的。根据事务开始时间的不同，每个事务对同一张表，同一时刻看到的数据可能是不一样的。

前面说到不同存储引擎的MVCC实现是不同的，典型的有乐观（optimistic）并发控制和悲观（pessimistic）并发控制。下面我们通过InnoDB的简化版行为来说明MVCC是如何工作的。

==InnoDB的MVCC是通过在每行记录后面保存两个隐藏的列来实现的==。这两个列，一个保存了行的创建时间，一个保存行的过期时间（或删除时间）。当然存储的并不是实际的时间值，而是系统版本号（system version number）。每开始一个新的事务，系统版本号都会自动递增。事务开始时刻的系统版本号会作为事务的版本号，用来和查询到每行记录的版本号进行比较。下面看一下在REPEATABLE READ隔离级别下，MVCC具体是如何操作的。

**SELECT**

InnoDB会根据一下两个条件检查每行目录：

- InnoDB只查找版本早于当前事务版本的数据行（也就是，行的系统版本号小于或等于事务的系统版本号），这样可以确保事务读取的行，要么是在事务开始前已经存在的，要么是事务自身插入或者修改过的。
- 行的删除版本要么未定义，要么大于当前事务版本号。这可以确保事务读取到的行，在事务开始之前未被删除。

只有符合上述两个条件的记录，才能返回作为查询结果。

**INSERT**

InnoDB为新插入的每一行保存当前系统版本号作为行版本号。

**DELETE**

InnoDB为删除的每一行保存当前系统版本号作为行删除标识。

**UPDATE**

InnoDB为插入一行新纪录，保存当前系统版本号作为行版本号，同时保存当前系统版本号到原来的行作为行删除标识。



保存这两个额外系统版本号，使大多数读操作都可以不用加锁。这样设计使得读数据操作很简单，性能很好，而且也能保证只会读取到符合标准的行。不足之处是每行记录都需要额外的存储空间，需要做更多的行检查工作，以及一些额外的维护工作。

MVCC只在REPEATABLE READ和READ COMMITED 两个隔离级别下工作。其他两个隔离级别都和MVCC不兼容，因为READ COMMITED 总是读取最新的数据行，而不是符合当前事务版本的数据行。而SERIALIZABLE 则会对所有读取的行都加锁。



## 5. 表的设计



## 6. 分库分表



## 7. 面对大数据，数据库层的解决办法



## 8. 事务隔离级别



## 9. 数据库优化



## 10. count(1),count(0),count(*)的区别







# 分布式相关

## 1. 远程过程调用 rpc原理



## 2. Dubbo原理



## 3. CAP定理

（参考《从Paxos到Zookeeper 分布式一致性原理与实践》 1.2.3 CAP和BASE理论 P25/438）

CAP理论告诉我们，一个分布式系统不可能同时满足一致性（C：Consistency），可用性（A：Availability）和分区容错性（P：Partition tolerance）这三个基本需求，最多只能同时满足其中的两项。

### 3.1 一致性

在分布式环境中，一致性是指数据在多个副本之间是否能够保持一致的特性。在一致性的需求下，当一个系统在数据一致的状态下执行更新操作后，应该保证系统的数据仍然处于一致的状态。

对于一个将数据副本分布在不同分布式节点上的系统来说，如果对第一个节点的数据进行了更新操作并且更新成功后，却没有使得第二个节点上的数据得到相应的更新，于是在对第二个节点的数据进行读取操作时，获取的依然是老数据（或称为脏数据），这就是典型的分布式数据不一致情况。在分布式系统中，如果能够做到针对一个数据项的更新操作执行成功后，所有的用户都可以读取到其最新的值，那么这样的系统就被认为具有强一致性（或严格的一致性）。

### 3.2 可用性

==可用性是指系统提供的服务必须一直处于可用的状态，对于用户的每一个操作请求总是能够在有限的时间内返回结果==。这里我们重点看下“有限的时间内”和“返回结果”。

“有限的时间内”是指，对于用户的一个操作请求，系统必须能够在指定的时间（即响应时间）内返回对应的处理结果，如果超过了这个时间范围，那么系统就被认为不可用的。另外，“有限的时间内”是一个在系统设计之初就设定好的系统运行指标，通常不同的系统之间会有很大的不同。比如说，一个在线搜索引擎来说，通常在0.5秒内需要给出用户搜索关键词对应的检索结果。以Google为例，搜索“分布式”这一关键词，Google能够在0.3秒左右的时间，返回大约上千万条检索结果。而对于一个面向HIVE的海量数据查询平台来说，正常的一次数据检索时间可能在20秒到30秒之间，而如果是一个时间跨度较大的数据内容查询，“有限的时间”有时候甚至会长达几分钟。

从上面的例子中我们可以看出，用户对于一个系统的请求响应时间的期望值不尽相同。但是，无论系统之间的差异有多大，唯一相同的一点就是对于用户请求，系统必须存在一个合理的响应时间，否则用户便会对系统感到失望。

“返回结果”是可用性的另一个非常重要的指标，它要求系统在完成对用户请求的处理后，返回一个正常的响应结果。正常的响应结果通常能够明确地反映出对请求的处理结果，即成功或失败，而不是一个让用户感到迷惑的返回结果。

让我们再来看看上面提到的在线搜索引擎的例子，如果用户输入指定的搜索关键词后，返回的结果是一个系统错误，通常类似于“OutOfMemoryError”或“System Has Crashed”等提示语，那么我们认为此时系统是不可用的。

### 3.3 分区容错性

分区容错性约束了一个分布式系统需要具有如下特性：分布式系统在遇到任何网络分区故障的时候，仍然需要能够保证对外提供满足一致性和可用性的服务，除非是整个网络环境都发生了故障。

网络分区是指在分布式系统中，不同的节点分布在不同的子网络（机房或异地网络等）中，由于一些特殊的原因导致这些子网络之间出现网络不连通的状况，但各个子网络的内部网络是正常的，从而导致整个系统的网络环境被切分成了若干个孤立的区域。需要注意的是，组成一个分布式系统的每个节点的加入和退出都可以看作是一个特殊的网络分区。



以上就是对CAP原理中一致性，可用性和分区容错性的讲解。

| 放弃CAP定理 |                             说明                             |
| :---------: | :----------------------------------------------------------: |
|    放弃P    | 如果希望能够避免系统出现分区容错性问题，一种较为简单的做法是将所有的数据（或者仅仅是那些与事务相关的数据）都放在一个分布式节点上，这样的做法虽然无法100%地保证系统不会出错，但至少不会碰到由于网络分区带来的负面影响。但同时需要注意的是，放弃P的同时也就意味着放弃了系统的可扩展性 |
|    放弃A    | 相对于放弃“分区容错性”来说，放弃可用性则正好相反，其做法是一旦系统遇到网络分区或其他故障时，那么受到影响的服务需要等待一定的时间，因此在等待期间系统无法对外提供正常的服务，即不可用 |
|    放弃C    | 这里所说的放弃一致性，并不是完全不需要一致性，如果真是这样的话，那么系统的数据都是没有意义的，整个系统也是没有价值的。<br/>事实上，放弃一致性指的是放弃数据的强一致性，而保留数据的最终一致性，这样的系统无法保证数据保持实时的一致性，但是能够承诺的是，数据最终会达到一个一致的状态，这就引入了一个时间窗口的概念，具体多久能够达到数据一致取决于系统的设计，主要包括数据副本在不同节点之间的复制时间长短 |



## 4. BASE理论

（参考《从Paxos到Zookeeper 分布式一致性原理与实践》 1.2.3 CAP和BASE理论 P28/438）

BASE是Basically Available（基本可用）、Soft state（软状态）和Eventually consistent（最终一致性）三个短语的简写，是由来自eBay的结构师Dan Pritchett在其文章BASE:An Acid Alternative中第一次明确提出的。BASE是对CAP中一致性和可用性权衡的结果，其来源于对于大规模互联网系统分布式实践的总结，是基于CAP定理逐步演化而来的，其核心思想是即使无法做到强一致性（Strong consistency），但每个应用都可以根据自身的业务特点，采用适当的方式来使系统达到最终一致性（Eventual consistency）。

### 4.1 基本可用

基本可用是指分布式系统在出现不可预知故障的时候，允许损失部分可用性——但请注意，这绝不等价于系统不可用。以下两个就是“基本可用”的典型例子。

- 相对时间上的损失：正常情况下，一个在线搜索引擎需要在0.5秒之内返回给用户相应的查询结果，但由于出现故障（比如系统部分机房发生断点或断网故障），查询结果的响应时间增加到了1~2秒。
- 功能上的损失：正常情况下，在一个电子商务网站上进行购物，消费者几乎能够顺利地完成每一笔订单，但是在一些节日大促购物高峰的时候，由于消费者的购物行为激增，为了保护购物系统的稳定性，部分消费者可能会被引导到一个降级页面。

### 4.2 弱状态

弱状态也称为软状态，和硬状态相对，是指允许系统中的数据存在中间状态，并认为该中间状态的存在不会影响系统的整体可用性，即允许系统在不同节点的数据副本之间进行数据同步的过程存在延时。

### 4.3 最终一致性

最终一致性强调的是系统中所有的数据副本，在经过一段时间的同步后，最终能够达到一个一致的状态。因此，最终一致性的本质是需要系统保证最终数据能够达到一致，而不需要实时保证系统数据的强一致性。

亚马逊首席技术官Werner Vogels在于2008年发表的一篇经典文章Eventually Consistent Revisited中，对最终一致性进行了非常详细的介绍。他认为最终一致性是一种特殊的弱一致性：系统能够保证在没有其他新的更新操作的情况下，数据最终一定能够达到一致的状态，因此所有客户端对系统的数据访问都能够获取到最新的值。同时，在没有发生故障的前提下，数据达到一致状态的时间延迟，取决于网络延迟，系统负载和数据复制方案设计等因素。

在实际工程实践中，最终一致性存在以下五类主要变种。

#### 4.3.1 因果一致性（Causal consistency）

因果一致性是指，如果进程A在更新完某个数据项后通知了进程B，那么进程B之后对该数据项的访问都应该能够获取到进程A更新后的最新值，并且如果进程B要对该数据项进行更新操作的话，务必基于进程A更新后的最新值，并且如果进程B要对该数据项进行更新操作的话，务必基于进程A更新后的最新值，即不能发生丢失更新情况。与此同时，与进程A无因果关系的进程C的数据访问则没有这样的限制。

#### 4.3.2 读己之所写（Read your writes）

读己之所写是指，进程A更新一个数据项之后，它自己总是能够访问到更新过的最新值，而不会看到旧值。也就是说，对于单个数据获取者来说，其读取到的数据，一定不会比自己上次写入的值旧。因此，读己之所写也可以看做是一种特殊的因果一致性。

#### 4.3.3 会话一致性（Session consistency）

会话一致性将对系统数据的访问过程框定在了一个会话当中：系统能保证在同一个有效的会话中实现“读己之所写”的一致性，也就是说，执行更能操作之后，客户端能够在同一个会话中始终读取到该数据项的最新值。

#### 4.3.4 单调读一致性（Monotonic read consistency）

单调读一致性是指如果一个进程从系统中读取一个数据项的某个值后，那么系统对于该进程后续的任何数据访问都不应该返回更旧的值。

#### 4.3.5 单调写一致性（Monotonic write consistency）

单调写一致性是指，一个系统需要能够保证来自一个进程的写操作被顺序地执行。



以上就是最终一致性的五种常见的变种，在实际系统实践中，可以将其中的若干个变种互相结合起来，以构建一个具有最终一致性特性的分布式系统。事实上，最终一致性并不是只有那些大型分布式系统才涉及的特性，许多现代的关系型数据库都采用了最终一致性模型。在现代关系型数据库中，大多都会采用同步和异步方式来实现主备数据复制技术。在同步方式中，数据的复制过程通常是更新事务的一部分，因此在事务完成后，主备数据库的数据就会达到一致。而在异步方式中，备库的更新往往会存在延时，这取决于事务日志在主备数据库之间传输的时间长短，如果传输时间过长或者甚至在日志传输过程中出现异常导致无法及时将事务应用到备库上，那么很显然，从备库中读取的数据将是旧的，因此就出现了数据不一致的情况。当然，无论是采用多次重试还是人为数据订正，关系型数据库还是能够保证最终数据达到一致——这就是系统提供最终一致性保证的经典案例。

总的来说，BASE理论面向的是大型高可用可扩展的分布式系统，和传统事务的ACID特性是相反的，它完全不同于ACID的强一致性模型，而是提出通过牺牲强一致性来获得可用性，并允许数据在一段时间内是不一致的，但最终达到一致状态。但同时，在实际的分布式场景中，不同业务单元和组件对数据一致性的要求是不同的，因此在具体的分布式系统架构设计过程中，ACID特性与BASE理论往往又会结合在一起使用。



## 5. 一致性协议

（参考《从Paxos到Zookeeper 分布式一致性原理与实践》 第2章 一致性协议 P32/438）

为了解决分布式一致性问题，在长期的探索研究过程中，涌现出了一大批经典的一致性协议和算法，其中最著名的就是二阶段提交协议、三阶段提交协议和Paxos算法了。

在分布式系统中，每一个机器节点虽然都能够明确地知道自己在进行事务操作过程中的结果是成功或失败，但却无法直接获取到其他分布式节点的操作结果。因此，当一个事务操作需要跨越多个分布式节点的时候，为了保持事务处理的ACID特性，就需要引入一个称为“协调者（Coordinator）”的组件来统一调度所有分布式节点的执行逻辑，这些被调度的分布式节点则被称为“参与者（Participant）”。协调者负责调度参与者的行为，并最终决定这些参与者是否要把事务真正进行提交。基于这个思想，衍生出了二阶段提交和三阶段提交两种协议。

其中二阶段提交协议解决了分布式事务的原子性问题，保证了分布式事务的多个参与者要么都执行成功，要么都执行失败。但是，在二阶段解决部分分布式事务问题的同时，依然存在一些难以解决的诸如同步阻塞、无限期等待和“脑裂”等问题。三阶段提交协议则是在二阶段提交协议的基础上，添加了PreCommit过程，从而避免了二阶段提交协议中的无限期等待问题。而Paxos算法引入了“过半”的理念，通俗地将就是少数服从多数的原则。同时，Paxos算法支持分布式节点角色之间的轮换，这极大地避免了分布式单点的出现，因此Paxos算法既解决了无限期等待的问题，也解决了“脑裂”问题，是目前来说最优秀的分布式一致性协议之一。

### 5.1 2PC

2PC，是Two-Phase Commit的缩写，即二阶段提交，是计算机网络尤其是数据库领域内，为了使基于分布式系统架构下的所有节点在进行事务处理过程中能够保持原子性和一致性而设计的一种算法。通常，二阶段提交协议也被认为是一种一致性协议，用来保证分布式系统数据的一致性。目前，绝大部分的关系型数据库都是采用二阶段提交协议来完成分布式事务处理的，利用该协议能够非常方便地完成所有分布式事务参与者的协调，统一决定事务的提交或回滚，从而能够有效地保证分布式数据一致性，因此二阶段提交协议被广泛地应用在许多分布式系统中。

#### 5.1.1 协议说明

顾名思义，二阶段提交协议是将事务的提交过程分成了两个阶段来进行处理，其执行流程如下。

**阶段一：提交事务请求**

1. 事务询问。
   协调者向所有参与者发送事务内容，询问是否可以执行事务提交操作，并开始等待各参与者的响应。
2. 执行事务。
   各参与者节点执行事务操作，并将Undo和Redo信息记入事务日志中。
3. 各参与者向协调者反馈事务询问的响应。
   如果参与者成功执行了事务操作，那么就反馈给协调者Yes响应，表示事务可以执行；如果参与者没有成功执行事务，那么就反馈给协调者No相应，表示事务不可以执行。

由于上面讲述的内容在形式上近似是协调者组织各参与者对一次事务操作的投票表态过程，因此二阶段提交协议的阶段一也被称为“投票阶段”，即各参与者投票表明是否要继续执行接下去的事务提交操作。

**阶段二：执行事务提交**

在阶段二中，协调者会根据各参与者的反馈情况来决定最终是否可以进行事务提交操作，正常情况下，包含一下两种可能。

*执行事务提交*

假如协调者从所有的参与者获得的反馈都是Yes响应，那么就会执行事务提交。

1. 发送提交请求。
   协调者向所有参与者节点发出Commit请求。
2. 事务提交。
   参与者接收到Commit请求后，会正式执行事务提交操作，并在完成提交之后释放在整个事务执行期间占用的事务资源。
3. 反馈事务提交结果。
   参与者在完成事务提交之后，向协调者发送Ack消息。
4. 完成事务。
   协调者接收到所有参与者反馈的Ack消息后，完成事务。

*中断事务*

假如任何一个参与者向协调者反馈了No响应，或者在等待超时之后，协调者尚无法收到所有参与者的反馈响应，那么就会中断事务。

1. 发送回滚请求。
   协调者向所有参与者节点发出Rollback请求。
2. 事务回滚。
   参与者接收到Rollback请求后，会利用其在阶段一中记录的Undo信息来执行事务回滚操作，并在完成回滚之后释放在整个事务执行期间占用的资源。
3. 反馈事务回滚结果。
   参与者在完成事务回滚之后，向协调者发送Ack消息。
4. 中断事务。
   协调者接受到所有参与者反馈的Ack消息后，完成事务中断。

以上就是二阶段提交过程中，前后两个阶段分别进行的处理逻辑。简单地讲，二阶段提交将一个事务的处理过程分为了投票和执行两个阶段，其核心是对每个事务都采用先尝试后提交的处理方式，因此也可以将二阶段提交看做一个强一致性的算法。

#### 5.1.2 优缺点

二阶段提交协议的优点：原理简单，实现方便。

二阶段提交协议的缺点：同步阻塞、单点问题、脑裂、太过保守。

**同步阻塞**

二阶段提交协议存在的最明显也是最大的一个问题就是同步阻塞，这会极大地限制分布式系统的性能。在二阶段提交的执行过程中，所有参与该事务操作的逻辑都处于阻塞状态，也就是说，各个参与者在等待其他参与者响应的过程中，将无法进行其他任何操作。

**单点问题**

在上面的讲解过程中，相信读者可以看出，协调者的角色在整个二阶段提交协议中起到了非常重要的作用。一旦协调者出现问题，那么整个二阶段提交流程将无法运转，更为严重的是，如果协调者是在阶段二中出现问题的话，那么其他参与者将会一直处于锁定事务资源的状态中，而无法继续完成事务操作。

**数据不一致**

在二阶段提交协议的阶段二，即执行事务提交的时候，当协调者向所有的参与者发送Commit请求之后，发生了局部网络异常或者是协调者在尚未发送完Commit请求之前自身发生了崩溃，导致最终只有部分参与者收到了Commit请求。于是，这部分收到了Commit请求的参与者就会进行事务的提交，而其他没有收到Commit请求的参与者则无法进行事务提交，于是整个分布式系统便出现了数据不一致性现象。

**太过保守**

如果在协调者指示参与者进行事务提交询问的过程中，参与者出现故障而导致协调者始终无法获取到所有参与者的响应信息的话，这时协调者只能依靠自身的超时机制来判断是否需要中断事务，这样的策略显得比较保守。换句话说，二阶段提交协议没有设计较为完善的容错机制，任意一个节点的失败都会导致整个事务的失败。

### 5.2 3PC

在上文中，我们讲解了二阶段提交协议的设计和实现原理，并明确提出可其在实际运行过程中可能存在的诸如同步阻塞、协调者的单点问题、脑裂和太过保守的容错机制等缺陷，因此研究者在二阶段提交协议的基础上进行了改进，提出了三阶段提交协议。

#### 5.2.1 协议说明

3PC，是Three-Phase Commit的缩写，即三阶段提交，是2PC的改进版，其将二阶段提交协议的“提交事务请求”过程一分为二，形成了由CanCommit、PreCommit和doCommit三个阶段组成的事务处理协议。

**阶段一：CanCommit**

1. 事务询问。
   协调者向所有的参与者发送一个包含事务内容的canCommit请求，询问是否可以执行事务提交操作，并开始等待各参与者的响应。
2. 各参与者向协调者反馈事务询问的响应。
   参与者在接受到来自协调者的canCommit请求后，正常情况下，如果其自身认为可以顺利执行事务，那么会反馈Yes响应，并进入预备状态，否则反馈No响应。

**阶段二：PreCommit**

在阶段二中，协调者会根据各参与者的反馈情况来决定是否可以进行事务的PreCommit操作，正常情况下，包含两种可能。

*执行事务预提交*

加入协调者从所有的参与者获得的反馈都是Yes响应，那么就会执行事务预提交。

1. 发送预提交请求。
   协调者向所有参与者节点发出preCommit的请求，并进入Prepared阶段。
2. 事务预提交。
   参与者接受到preCommit请求后，会执行事务操作，并将Undo和Redo信息记录到事务日志中。
3. 各参与者向协调者反馈事务执行的响应。
   如果参与者成功执行了事务操作，那么就会反馈给协调者Ack响应，同时等待最终的指令：提交（commit）或中止（abort）

*中断事务*

假如任何一个参与者向协调者反馈了No响应，或者在等待超时之后，协调者尚无法接收到所有参与者的反馈响应，那么就会中断事务。

1. 发送中断请求。
   协调者向所有参与者节点发出abort请求。
2. 中断事务。
   无论是收到来自协调者的abort请求，或者是在等待协调者请求过程中出现超时，参与者都会中断事务。

**阶段三：doCommit**

该阶段将进行真正的事务提交，会存在以下两种可能的情况。

*执行提交*

1. 发送提交请求。
   进入这一阶段，假如协调者处于正常工作状态，并且它接收到了来自所有参与者的Ack响应，那么它将从“预提交”状态转换到“提交”状态，并向所有的参与者发送doCommit请求。
2. 事务提交。
   参与者收到doCommit请求后，会正式执行事务提交操作，并在完成提交之后释放在整个事务执行期间占用的事务资源。
3. 反馈事务提交结果。
   参与者在完成事务提交之后，向协调者发送Ack消息。
4. 完成事务。
   协调者接收到所有参与者反馈的Ack消息后，完成事务。

*中断事务*

进入这一阶段，假设协调者处于正常状态，并且有任意一个参与者向协调者反馈了No响应，或者在等待超时之后，协调者尚无法接收到所有参与者的反馈响应，那么就会中断事务。

1. 发送中断请求。
   协调者向所有的参与者节点发送abort请求。
2. 事务回滚。
   参与者接收到abort请求后，会利用其在阶段二中记录的Undo信息来执行事务回滚操作，并在完成回滚之后释放在整个事务执行期间占用的资源。
3. 反馈事务回滚结果。
   参与者在完成事务回滚之后，向协调者发送Ack消息。
4. 中断事务。
   协调者接收到所有参与者反馈的Ack消息后，中断事务。

需要注意的是，一旦进入阶段三，可能会存在以下两种故障。

- 协调者出现问题。
- 协调者和参与者之间的网络出现故障。

无论出现哪种情况，最终都会导致参与者无法及时接收到来自协调者的doCommit或是abort请求，针对这样的异常情况，参与者都会在等待超时之后，继续进行事务提交。

#### 5.2.2 优缺点

三阶段提交协议的优点：相较于二阶段提交协议，三阶段提交协议最大优点就是降低了参与者的阻塞范围，并且能够在出现单点故障后继续达成一致。

三阶段提交协议的缺点：三阶段提交协议在去除阻塞的同时也引入了新的问题，那就是在参与者接收到preCommit消息后，如果网络出现分区，此时协调者所在的节点和参与者无法进行正常的网络通信，在这种情况下，该参与者依然会进行事务的提交，这必然出现数据的不一致性。

### 5.3 Paxos算法

Paxos算法的核心是一个一致性算法，我们将从对一致性问题的描述开始来讲解该算法需要解决的实际需求。

#### 5.3.1 问题描述

假设有一组可以提出提案的进程集合，那么对于一个一致性算法来说需要保证以下几点：

- 在这些被提出的提案中，只有一个会被选定。
- 如果没有提案被提出，那么就不会有被选定的提案。
- 当一个提案被选定后，进程应该可以获取被选定的提案信息。

对于一致性来说，安全性（Safety）需求如下：

- 只有被提出的提案才能被选定（Chosen）。
- 只能有一个值被选定。
- 如果某个进程认为某个提案被选定了，那么这个提案必须是真的被选定的那个。

在对Paxos算法的讲解过程中，我们不去精确地定义其活性（liveness）需求，==从整体上来说，Paxos算法的目标就是要保证最终有一个提案会被选定，当提案被选定后，进程最终也能获取到被选定的提案==。

在该一致性算法中，有三种参与角色，我们用Proposer、Acceptor和Learner来表示。在具体的实现中，一个进程可能充当不止一种角色，在这里我们并不关心进程如何映射到各种角色。假设不同参与者之间可以通过收发消息来进行通信，那么：

- 每个参与者以任意的速度执行，可能会因为出错而停止，也可能会重启。同时，即使一个提案被选定后，所有的参与者也都有可能失败或重启，因此除非那些失败或重启的参与者可以记录某些信息，否则将无法确定最终的值。
- 消息在传输过程中可能会出现不可预知的延迟，也可能会重复或丢失，但是消息不会被损坏，即消息内容不会被篡改（拜占庭式的问题）。

#### 5.3.2 提案的选定

**Proposer生成提案**

现在我们来看看，在P2c的基础上如何进行提案的生成。对于一个Proposer来说，获取那些已经被通过的提案远比预测未来得简单。因此，Proposer在产生一个编号为M<sub>n</sub>的提案时，必须要知道当前某一个将要或已经被半数以上Acceptor批准的编号小于M<sub>n</sub>但为最大编号的提案。并且，Proposer会要求所有的Acceptor都不要再批准任何编号小于M<sub>n</sub>的提案——这就引出了如下的提案生成算法。

1. Proposer选择一个新的提案编号M<sub>n</sub>，然后向某个Acceptor集合的成员发送请求，要求该集合中的Acceptor做出如下回应。

   - 向Proposer承诺，保证不再批准任何编号小于M<sub>n</sub>的提案。
   - 如果Acceptor已经批准过任何提案，那么其就向Proposer反馈当前该Acceptor已经批准的编号小于M<sub>n</sub>但为最大编号的那个提案的值。

   我们将该请求称为编号为M<sub>n</sub>的提案的Prepare请求。

2. 如果Proposer收到了来自半数以上的Acceptor的响应结果，那么它就可以产生编号为M<sub>n</sub>，Value值为V<sub>n</sub>的提案，这里的V<sub>n</sub>是所有响应中编号最大的提案的Value值。当然还存在另一种情况，就是半数以上的Acceptor都没有批准过任何提案，即响应中不包含任何的提案，那么此时V<sub>n</sub>值就可以由Proposer任意选择。

在确定提案之后，Proposer就会将该提案再次发送给某个Acceptor集合，并期望获得它们的批准，我们称此请求为Accept请求。需要注意的一点是，此时接受Accept请求的Acceptor集合不一定是之前响应Prepare请求的Acceptor集合——这点相信读者也能够明白，任意两个半数以上的Acceptor集合，必定包含至少一个公共Acceptor。

**Acceptor批准提案**

在上文中，我们已经讲解了Paxos算法中Proposer的处理逻辑，下面我们来看看Acceptor是如何批准提案的。

根据上面的内容，一个Acceptor可能会收到来自Proposer的两种请求，分别是Prepare请求和Acceptor请求，对这两类请求做出响应的条件分别如下。

- Prepare请求：Acceptor可以在任何时候响应一个Prepare请求。
- Accept请求：在不违背Accept现有承诺的前提下，可以任意响应Accept请求。

因此，对Acceptor逻辑处理的约束条件，大体可以定义如下。

> P1a:一个Acceptor只要尚未响应过任何编号大于M<sub>n</sub>的Prepare请求，那么他就可以接受这个编号为M<sub>n</sub>的提案。

从上面这个约束条件中，我们可以看出，P1a包含P1。同时，值得一提的是，Paxos算法允许Acceptor忽略任何请求而不用担心破坏其算法的安全性。

**算法优化**

在上面的内容中，我们分别从Proposer和Acceptor对提案的生成和批准两方面来讲解了Paxos算法在提案选定过程中的算法细节，同时也在提案的编号全局唯一的前提下，获得了一个满足安全性需求的提案选定算法，接下来我们再对这个初步算法做一个小优化。尽可能地忽略Prepare请求：

> 假设一个Acceptor收到了一个编号为M<sub>n</sub>的Prepare请求，但此时该Acceptor已经对编号大于M<sub>n</sub>的Prepare请求做出了响应，因此它肯定不会再批准任何新的编号为M<sub>n</sub>的提案，那么很显然，Acceptor就没有必要对这个Prepare请求做出响应，于是Acceptor可以选择忽略这样的Prepare请求。同时，Acceptor也可以忽略掉那些它已经批准过的提案的Prepare请求。

通过这个优化，每个Acceptor只需要记住它已经批准的提案的最大编号以及它已经做出Prepare请求响应的提案的最大编号，以便在出现故障或节点重启的情况下，也能保证P2c的不变性。而对于Proposer来说，只要它可以保证不会产生具有相同编号的提案，那么就可以丢弃任意的提案以及它所有的运行时状态信息。

**算法陈述**

综合前面讲解的内容，我们来对Paxos算法的提案选定过程进行一个陈述。结合Proposer和Acceptor对提案的处理逻辑，就可以得到如下类似于两阶段提交的算法执行过程。

*阶段一*

1. Proposer选择一个提案编号M<sub>n</sub>，然后向Acceptor的某个超过半数的子集成员发送编号为M<sub>n</sub>的Prepare请求。
2. 如果一个Acceptor收到一个编号为M<sub>n</sub>的Prepare请求，且编号M<sub>n</sub>大于该Acceptor已经响应的所有Prepare请求的编号，那么它就会将它已经批准过的最大编号的提案作为响应反馈给Proposer，同时该Acceptor会承诺不会再批准任何编号小于M<sub>n</sub>的提案。
   举个例子来说，假定一个Acceptor已经响应过的所有Prepare请求对应的提案编号分别为1、2、……、5和7，那么该Acceptor在接收到一个编号为8的Prepare请求后，就会将编号为7的提案作为响应反馈给Proposer。

*阶段二*

1. 如果Proposer收到来自半数以上的Acceptor对于其发出的编号为M<sub>n</sub>的Prepare请求的响应，那么它就会发送一个针对[M<sub>n</sub>, V<sub>n</sub>]提案的Accept请求给Acceptor。注意，V<sub>n</sub>的值就是收到的响应中编号最大的提案的值，如果响应中不包括任何提案，那么它就是任意值。
2. 如果Acceptor收到这个针对[M<sub>n</sub>, V<sub>n</sub>]提案的Accept请求，只要该Acceptor尚未编号大于M<sub>n</sub>的Prepare请求做出响应，它就可以通过这个提案。

当然，在实际运行过程中，每一个Proposer都有可能会产生多个提案，但只要每个Proposer都遵循如上所述的算法运行，就一定能够保证算法执行的正确性。值得一提的是，每个Proposer都可以在任意时刻丢弃一个提案，哪怕针对该提案的请求的响应在提案被丢弃后会到达，但根据Paxos算法的一系列规约，依然可以保证其在提案选定上的正确性。事实上，如果某个Proposer已经在试图生成编号更大的提案，那么丢弃一些旧的提案未尝不是一个好的选择。因此，如果一个Acceptor因为已经收到过更大编号的Prepare请求而忽略某个编号更小的Prepare或者Accept请求，那么它也应当通知其对应的Proposer，以便该Proposer也能够将该提案进行丢弃——这和上面“算法优化”部分中提到的提案丢弃是一致的。

#### 5.3.3 提案的获取

在上文中，我们已经介绍了如何来选定一个提案，下面我们再来看看如何让Learner获取提案，大体可以有以下几种方案。

*方案一*

Learner获取一个已经被选定的提案的前提是，该提案已经被半数以前的Acceptor批准。因此，最简单的做法就是一旦Acceptor批准了一个提案，就将该提案发送给所有的Learner。

很显然，这种做法显然可以让Learner尽快地获取被选定的提案，但是却需要让每个Acceptor与所有的Learner逐个进行一次通信，通信的次数至少为二者个数的乘积。

*方案二*

另一种可行的方案是，我们可以让所有的Acceptor将它们对提案的批准情况，统一发送给一个特定的Learner（下文中我们将这样的Learner称为“主Learner”），在不考虑拜占庭将军问题的前提下，我们假定Learner之间可以通过消息通信来互相感知提案的选定情况。基于这样的前提，当主Learner被通知一个提案已经被选定时，它会负责通知其他的Learner。

在这种方案中，Acceptor首先会将得到批准的提案发送给主Learner，再由其同步给其他Learner，因此较方案一而言，方案二虽然需要多一个步骤才能将提案通知到所有的Learner，但其通信次数却大大减少了，通常只是Acceptor和Learner的个数总和。但同时，该方案引入了一个新的不稳定因素：主Learner随时可能出现故障。

*方案三*

在讲解方案二的时候，我们提到，方案二最大的问题在于主Learner存在单点问题，即主Learner随时可能出现故障。因此，对方案二进行改进，可以将主Learner的范围扩大，即Acceptor可以将批准的提案发送给一个特定的Learner集合，该集合中的每个Learner都可以在一个提案被选定后通知所有其他的Learner。这个Learner集合中的Learner个数越多，可靠性就越好，但同时网络通信的复杂度也就越高。

#### 5.3.4 通过选取主Proposer保证算法的活性

根据前面的内容讲解，我们已经基本上了解了Paxos算法的核心逻辑，下面我们再来看看Paxos算法在实际运作过程中的一些细节。假设存在这样一种极端情况，有两个Proposer依次提出了一系列编号递增的议案，但是最终都无法被选定，具体流程如下：

> Proposer P<sub>1</sub>提出了一个编号为M<sub>1</sub>的提案，并完成了上述阶段一的流程。但与此同时，另外一个Proposer P<sub>2</sub>提出了一个编号为M<sub>2</sub>（M<sub>2</sub>\>M<sub>1</sub>）的提案，同样也完成了阶段一的流程，于是Acceptor已经承诺不再批准编号小于M<sub>2</sub>的提案了。因此，当P<sub>1</sub>再次进入阶段二的时候，其发出的Accept请求将被Acceptor忽略，于是P<sub>1</sub>再次进入阶段一并提出了一个编号为M<sub>3</sub>（M<sub>3</sub>\>M<sub>2</sub>）的提案，而这又导致P<sub>2</sub>在第二阶段的Accept请求被忽略，以此类推，提案的选定过程将陷入死循环

为了保证Paxos算法流程的可持续性，以避免陷入上述提到的“死循环”，就必须选择一个主Proposer，并规定只有主Proposer才能提出议案。这样一来，只要主Proposer和过半的Acceptor能够正常进行网络通信，那么但凡主Proposer提出一个编号更高的提案，该提案终将会被批准。当然，如果Proposer发现当前算法流程中已经有一个编号更大 的提案被提出或正在接受批准，那么它会丢弃当前这个编号较小的提案，并最终能够选出一个编号足够大的提案。因此，如果系统中有足够多的组件（包括Proposer、Acceptor和其他网络通信组件）能够正常工作，那么通过选择一个主Proposer，整套Paxos算法流程就能够保持活性。

# Redis相关

## 1. AOF和RDB



## 2. Redis的数据结构



## 3. Redis的缓存淘汰策略

# RabbitMQ相关

## 1. 死信队列



# 算法、手写代码相关

## 1. 数组的全排列



## 2. 写一个死锁程序



## 3. 判断一棵树是否是AVL



## 4. 一篇文章获取出现次数最多的字母？如果是单词呢？ 如果是一本书呢？如果是要在上亿个号码中找出出现最多的呢？说出你的思路，把你能想到的方法都说出来



## 5. 将一棵树从右边看过去的节点依次从上到下输出



## 6. 最大连续子序列和



## 7. 很长的二进制串，求模3的余数



# 机器学习相关

## 1. 聚类算法



## 2. 分类算法