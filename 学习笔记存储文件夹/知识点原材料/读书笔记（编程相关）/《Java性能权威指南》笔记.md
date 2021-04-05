# 第1章 导论

## 1.3 全面的性能调优

本书关注于如何以最佳方式利用JVM和Java平台API，让程序运行得更快。但除了这两点，还有许多外在的因素影响性能。书中这些因素时不时会出现，但因为它们不只影响Java，所以不会深入讨论。JVM和Java平台的性能只是高性能主题中的一小部分。

本书会覆盖一些外部因素，这些因素的重要性不亚于Java的性能调优。本书中基于Java的调优方法可以和这些因素相互补充，但这些因素多数已经超过了本书讨论的范围。

### 1.3.1 编写更好的算法

### 1.3.2 编写更少的代码

### 1.3.3 老调重弹的过早优化

“过早优化”一词公认是由高德纳发明的，开发人员常常据此宣称：只有在运行时才能知道代码的性能有多要紧。但你可能从来没注意到，完整的原话是“我们不应该把大量时间都耗费在那些小的性能改进上；过早考虑优化是所有噩梦的根源”。

这句名言的重点是，最终你应该编写清晰、直接、易读和易理解的代码。这里的“优化”应该理解为虽然算法和设计改变了复杂程序的结构，但是提供了更好的性能。那些真正的优化最好留到以后，等到性能分析表明这些措施有巨大收益的时候才进行。

而这里所指的过早优化，并不包括避免那些已经知道对性能不好的代码结构。每行代码，如果有两种简单、直接的编程方式，那就应该选择性能更好的那种。

### 1.3.4 其他：数据库很可能就是瓶颈

本书并不关注整体系统的性能。对于整体系统，我们需要采取结构化方法针对系统的所有方法分析性能。CPU使用率、I/O延迟、系统整体的吞吐量都必须测量和分析。只有到那时，我们才能判定到底是哪个组件导致了性能瓶颈。

另一方面，不要忽视初步分析。如果数据库是瓶颈（提示：的确是的话），那么无论怎么优化访问数据库的Java应用，都无助于整体性能；实际上可能适得其反。作为一般性原则，系统负载增加越大，系统性能就会越糟糕。如果更改了Java应用使得它更有效，这只会增加已经过载的数据库的负载，整体性能实际反而会下降。导致的风险是，可能会得出错误结论，即认为不应该改进JVM。

增加系统某个组件的负载从而导致整个系统性能变慢，这项原则不仅限于数据库。CPU密集型的应用服务器增加负载，或者越来越多线程试图获取已经有线程等待的锁，还有许多其他场景，也都适用这项原则。第9章展示了一个仅涉及JVM的极端例子。

### 1.3.5 常见的优化

# 第2章 性能测试方法

## 2.1 原则1：测试真实应用

第1条原则就是，应该在产品实际使用的环境进行性能测试。性能测试大体上可以分为3种，每种都有其优点和不足，只有适用于实际应用的才能取得最好的效果。

### 2.1.1 微基准测试

第1种是微基准测试。微基准测试用来测量微小代码单元的性能，包括调用同步方法的用时与非同步方法的用时比较，创建线程的代价与使用线程池的代价，执行某种算法的耗时与其替代实现的耗时，等等。

微基准测试看起来很好，但要写对却很困难。考虑以下代码，被测的方法是计算出第50个斐波那契数，这段代码试图用微基准测试来测试不同实现的性能：

~~~java
public void doTest() {
    // 主循环
    double l;
    long then = System.currentTimeMillis();
    for (int i = 0; i < nLoops; i++) {
        l = fibImpl1(50);
    }
    long now = System.currentTimeMillis();
    System.out.println("Elapsed time: " + (now - then));
}
...
private double fibImpl1(int n) {
    if(n < 0) throw new IllegalArgumentException("Must be > 0");
    if(n == 0) return 0d;
    if(n == 1) return 1d;
    double d = fibImpl1(n - 2) + fibImpl(n - 1);
    if(Double.isInfinite(d)) throw new ArithmeticException("Overflow");
    return d;
}
~~~

代码看起来简单，却存在很多问题。

#### 1. 必须使用被测的结果

这段代码的最大问题是，实际上它永远都不会改变程序的任何状态。因为斐波那契的计算结果从来没有被使用，所以编译器可以很放心地去除计算结果。智能的编译器（包括当前的Java 7和Java 8）最终执行的是以下代码：

~~~java
long then = System.currentTimeMillis();
long now = System.currentTimeMillis();
System.out.println("Elapsed time: " + (now - then));
~~~

结果是，无论计算斐波那契的方法如何实现，循环执行了多少次，实际的流逝时间其实只有几毫秒。循环如何被消除的细节请参见第4章。

有个方法可以解决这个问题，即确保读取被测结果，而不只是简单地写。实际上，将局部变量l地定义改为实例变量（并用关键字volatile声明）就能测试这个方法的性能了。（实例变量l必须声明为volatile的原因请参见第9章。）

> **多线程微基准测试**
>
> 即便本示例是单线程微基准测试，也必需使用volatile变量。
>
> 编写多线程微基准测试时务必深思熟虑。当若干个线程同时执行小段代码时，极有可能会产生同步瓶颈（以及其他线程问题）。所以，如果我们过多依赖多线程基准测试的结果，就常常会将大量时间花费在优化那些真实场景中很少出现的同步瓶颈上，而不是性能需求更迫切的地方。
>
> 考虑这样的微基准测试，即有两个线程同时调用同步方法。由于基准测试的代码量相对于被测方法来说比较少，所以多数时间都是在执行同步方法。假设执行同步方法的时间只占整个微基准测试的50%，即便少到只有两个线程，同时执行同步代码的概率仍然很高。因此基准测试运行得很慢，并且随着线程数的增加，竞争所导致的性能问题将愈演愈烈。最终结果就是，测试衡量的是JVM如何处理竞争，而不是微基准测试的本来目的。

#### 2. 不要包括无关的操作

即使使用了被测结果，依然还有隐患。上述代码只有一个操作：计算第50个斐波那契数。可想而知，其中有些迭代操作是多余的。如果编译器足够智能的话，就能发现这个问题，从而只执行一遍循环——至少可以少几次迭代，因为那些迭代是多余的。

另外，fibImpl(1000) 的性能可能与fibImpl(1)相差很大。如果目的是为了比较不同实现的性能，测试的输入就应该考虑用一系列数据。

也就是说，解决这个问题，需要给fibImpl传入不同的参数。可以使用随机值，但仍然必须小心。

下面是种简单方法，即在循环中使用随机数生成器：

~~~java
for(int i = 0; i < nLoops; i++) {
    l = fibImpl1(random.nextInteger());
}
~~~

可以看到，循环中包括了计算随机数，所以测试的总时间是计算斐波那契数列的时间，加上生成一组随机数的时间。这可不是我们的目的。

微基准测试中的输入值必须事先计算好，比如：

~~~java
int[] input = new int[nLoops];
for(int i = 0; i < nLoops; i++){
    input[i] = random.nextInt();
}
long then = System.currentTimeMillis();
for(int i = 0; i < nLoopsl; i++){
    try {
        l = fibImpl1(input[i]);
    } catch (IllegalArgumentException iae){
        
    }
}
long now = System.currentTimeMillis();
~~~

#### 3. 必须输入合理的参数

此处还有第3个隐患，就是测试的输入值范围：任意选择的随机输入值对于这段被测代码的用法来说并不具有代表性。

> **热身期**
>
> Java的一个特点就是代码执行的越多性能越好，第4章将会覆盖这个主题。基于这点，微基准测试应该包括热身期，使得编译器能生成优化的代码。
>
> 本章后续将深入讨论热身期的优缺点。微基准测试需要热身期，否则测量的是编译而不是被测代码的性能了。

综合所有因素，正确的微基准测试代码看起来应该是这样：

~~~java
package net.sdo;

import java.util.Random;

public class FibonacciTest {
    private volatile double l;
    private int nLoops;
    private int[] input;
    
    public static void main(String[] args) {
        FibonacciTest ft = new FibonacciTest(Integer.parseInt(args[0]));
        ft.doTest(true);
        ft.doTest(false);
    }
    
    private FibonacciTest(int n){
        nLoops = n;
        input = new Random();
        Random r = new Random();
        for(int i = 0; i < nLoops; i++){
            input[i] = r.nextInt(100);
        }
    }
    
    private void doTest(boolean isWarmup) {
        long then = System.currentTimeMillis();
        for(int i = 0; i < nLoops; i++){
            l = fibImpl1(input[i]);
        }
        if(!isWarmup){
            long now = System.currentTimeMillis();
            System.out.println("Elapsed time:" + (now - then));
        }
    }
    
    private double fibImpl1(int n) {
        if(n < 0) throw new IllegalArgumentException("Must be > 0");
        if(n == 0) return 0d;
        if(n == 1) return 1d;
        double d = fibImpl1(n - 2) + fibImpl(n - 1);
        if(Double.isInfinite(d)) throw new ArithmeticException("Overflow");
        return d;
    }
}
~~~

甚至这个微基准测试的测量结果中仍然有一些与计算斐波那契数没有太大关系：调用fibImpl1()的循环和方法开销，将每个结果都写到volatile变量中也会有额外的开销。

此外还需要留意编译效应。编译器编译方法时，会依据代码的性能分析反馈来决定所使用的最佳优化策略。性能分析反馈基于以下因素：频繁调用的方法、调用时的栈深度、方法参数的实际类型（包括子类）等，它还依赖于代码实际运行的环境。编译器对于相同代码的优化在微基准测试中和实际应用中经常有所不同。如果用相同的测试测量斐波那契方法的其他实现，就能看到各种编译效应，特别是当这个实现与当前的实现处在不同的类中时。

最终，还有探讨微基准测试实际意味着什么。比如这里讨论的基准测试，它有大量的循环，整体时间以秒计，但每轮循环迭代通常是纳秒级。没错，纳秒累计起来，“积少成多”就会成为频繁出现的性能问题。特别是在做回归测试的时候，追踪级别设为纳秒很有意义。

微基准测试难于编写，真正管用的又很有限。所以，应该了解这些相关的隐患后再做出决定，是微基准测试合情合理值得做，还是关注宏观的测试更好。

### 2.1.2 宏基准测试

