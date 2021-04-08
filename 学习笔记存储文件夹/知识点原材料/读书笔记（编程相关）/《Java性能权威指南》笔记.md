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

假设业务处理的算法有所改进，处理量达到了200 RPS，系统能承受的负载也相应增加。LDAP系统可以处理这些增加的负载：目前为止一切都好，数据将以200 RPS的速率注入业务处理模块，而它也将以200 RPS的速率输出。

但数据库只能以100 RPS的速率装载数据。虽然向数据库发送请求的速率为200 RPS，输出到其他模块的速率却只有100 RPS。即便业务逻辑处理的效率加倍，系统整体的吞吐量仍然只能达到100 RPS。所以，除非花时间改善环境其他方面的效率，否则业务逻辑做再多改进也是无效的。

> **多JVM时的全系统测试**
>
> 全应用测试有个很重要的场景，就是同一台机器上同时运行多个应用。许多JVM的默认调优都是默认假定整个机器的资源都归JVM使用。如果单独测试，优化效果很好。如果在其他应用（包括但不限于Java程序）运行的时候进行测试，性能会有很大的不同。

本例子中，优化业务处理并不完全是浪费时间：在系统其他性能瓶颈上曾经付出的努力，终究会有好处。进一步说，这中间有个优先顺序：不进行整体应用的测试，就不可能知道哪部分的优化会产生回报。

### 2.1.3 介基准测试

我的调优工作包括Java SE和EE，每种都会有一组类似微基准测试的测试。对于Java SE工程师来说，这个术语意思是样本甚至比2.1.1节的还要小：测量很小的东西。Java EE工程师则将这个术语用于其他地方：测量某方面性能的基准测试，但仍然要执行大量代码。

介基准测试并不局限于Java EE：它是一个术语，我用来表示做一些实际工作，但不是完整应用的基准测试。

### 2.1.4 代码示例

贯穿全书的许多例子都来自于一个示例应用，计算某只股票在一段时间内的“历史”最高价和最低价，以及标准差。因为所有数据皆为虚构，价格和股票代码也是随机生成，所以这里的历史标上了引号。

基本接口StockPrice表示某股票某天的价格区间：

~~~java
public interface StockPrice {
    String getSymbol();
    Date getDate();
    BigDecimal getClosingPrice();
    BigDecimal getHigh();
    BigDecimal getLow();
    BigDecimal getOpeningPrice();
    boolean isYearHigh();
    boolean isYearLow();
    Collection<? extends StockOptionPrice> getOptions();
}
~~~

通常，那些示例应用都是对一组股价进行处理，这些股价表示一段时间内的股票历史（比如1年或25年，取决于具体的示例）：

~~~java
public interface StockPriceHistory {
    StockPrice getPrice(Date d);
    Collection<StockPrice> getPrices(Date startDate, Date endDate);
    Map<Date, StockPrice> getAllEntries();
    Map<BigDecimal, ArrayList<Date>> getHistogram();
    BigDecimal getAveragePrice();
    Date getFirstDate();
    BigDecimal getHighPrice();
    Date getLastDate();
    BigDecimal getLowPrice();
    BigDecimal getStdDev();
    String getSymbol();
}
~~~

这个接口的基本实现是从数据库载入股价：

~~~java
public class StockPriceHistoryImpl implements StockPriceHistory {
    ...
    public stockPriceHistoryImpl (String s, Date startDate, Date endDate, EntityManager em) {
        Date curDate = new Date(startDate.getTime());
        symbol = s;
        while(!curDate.after(endDate)){
            stockPriceImpl sp = em.find(StockPriceImpl.class, new StockPricePK(s, (Date) curDate.clone()));
            if(sp != null) {
                Date d = (Date) curDate.clone();
                if(firstDate == null){
                    firstDate = d;
                }
                prices.put(d, sp);
                lastDate = d;
            }
            curDate.setTime(curDate.getTime() + msPerDay);
        }
    }
}
~~~

计算标准差需要知晓BigDecimal数的平方根。标准Java API不支持这个函数，示例将采用以下方法。

~~~java
public static BigDecimal sqrtB(BigDecimal bd) {
    BigDecimal initial = bd;
    BigDecimal diff;
    do {
        BigDecimal sDivX = bd.divide(initial, 8, RoundingMode.FLOOR);
        BigDecimal sum = sDivX.add(initial);
        BigDecimal div = sum.divide(TWO, 8, RoundingMode.FLOOR);
        diff =  div.substract(initial).abs();
        diff.setScale(8, RoundingMode.FLOOR);
        initial = div;
    } while(diff.compareTo(error) > 0);
    return initial;
}
~~~

这是巴比伦平方根计算法的实现。它不是最有效的实现，特别是初始值可以估算得更好，可以少几轮迭代。这是经过深思熟虑的，因为计算需要花费一些时间（模拟业务逻辑）

## 2.2 原则2：理解批处理流逝时间、吞吐量和响应时间

### 2.2.1 批处理流逝时间

虚拟机会花几分钟（或更长时间）全面优化代码并以最高性能执行。由于这个（以及其他）原因，研究Java的性能优化就要密切注意代码优化的热身期：大多数时候，应该在运行代码执行足够长时间，已经编译并优化之后再测量性能。

> **其他影响应用热身的因素**
>
> 通常认为的应用热身，指的就是等待编译器优化运行代码，不过基于代码的运行时长还有其他一些影响性能的因素。
>
> 例如，JPA通常都会缓存从数据库读取的数据。与此类似，应用程序读文件时，操作系统就会将文件载入内存。

### 2.2.2 吞吐量测试

吞吐量测试是基于一段时间内所能完成的工作量。

客户端常常有多个线程在处理，所以吞吐量就是所有客户端所完成的操作总量。通常这个数字就是每秒完成的操作量，而不是测量期间的总操作量。这个指标常常被称作每秒事务数（TPS）、每秒请求数（RPS）或每秒操作次数（OPS）。

### 2.2.3 响应时间测试

最后一个常用的测试指标是响应时间：从客户端发送请求至收到响应之间的流逝时间。

响应时间测试和吞吐量测试（假设后者是基于客户端-服务端模式）之间的差别是，响应时间测试中的客户端线程会在操作之间休眠一段时间。这被称为思考时间。

> **思考时间和吞吐量**
>
> 有两种方法
>
> 另外一种方法是周期时间（Cycle Time）

衡量响应时间有两种方法。响应时间可以报告为平均值：请求时间的总和除以请求数。响应时间也可以报告为百分位请求，例如第90百分位响应时间。

两种方法的一个区别在于，平均值会受离群值影响。

## 2.3 原则3：用统计方法应对性能的变化

学生t检验（Student's t-test，以下称t检验），这是一种针对一组数据及其变化的统计分析。顺便说一句，”学生“是首次发表该检验的科学家（即威廉·戈斯特）的笔名。t检验计算出的p值，是指原假设（null hypothesis）成立时的概率。（有一些程序和类库可以计算t检验，本节的结果是用Apache Commons Mathematics类库中的TTest计算的。）

> **统计学及其语义**
>
> 正确表述t检验结果的语句应该像这样：试样与基线有差别的可能性为57%，差别预计最大有25%。
>
> 不过通常会这么描述：结果改善25%的置信度（confidence level）为57%。确切地说，这种说法与前面并不一致，也会让统计学家们抓狂，不过这种说法简短而易于为人接受，也不算太离谱。

t检验通常与α值一起使用，α值是一个点（有点随意），如果结果达到这个点那就是统计显著性（statistical significance）。通常α值设置为0.1——意思是说，如果试样和基线只在10%（0.1）的时间里相同（或反过来讲，90%的时间里试样和基线有差异），那结果就被认为是统计显著。其他常用的α值还有0.05（置信度95%）或0.01（置信度为99%）。如果测试的p值小于1-α值，则被认为是统计显著。

> **统计学中的显著性与重要性**
>
> 显著性差异并不意味着统计结果对我们更重要。

## 2.4 原则4：尽早频繁测试

理想情况下，在代码提交到中心源代码仓库前，性能测试就应该作为过程的一部分允许，如果代码引入了性能衰减，提交就会被阻止。

遵循以下准则，可以使得尽早频繁测试变得最有用

自动化一切

测试一切

在真实系统上运行

# 第3章 Java性能调优工具箱

## 3.1 操作系统的工具与分析

实际上性能分析的起点与Java无关：它是一组操作系统自带的基本监控工具。在基于Unix的系统上，有sar（System Accounting Report）及其组成工具，例如vmstat、iostat、prstat等。在WIndows上，有图形化资源监视器以及像typeperf这样的命令行工具。

### 3.1.1 CPU使用率

通常CPU使用率可以分为两类：用户态时间和系统态时间（Windows上被称作privileged time）。用户态时间是CPU执行应用代码所占时间，而系统态时间则是CPU执行内核代码所占时间的百分比。任何使用底层系统资源的操作，都会导致应用占用更多的系统态时间。

性能调优的目的是，在尽可能短的时间让CPU使用率尽可能地高。这听起来有点不合常理。

#### 1.Java和单CPU的使用率

#### 2.Java和多CPU的使用率

另外在多线程多CPU下，需要重点考虑以下CPU空闲的情形：即便有事可做，CPU仍然空闲。这在程序没有更多线程可用的时候可能会出现。

### 3.1.2 CPU运行队列

Windows和Unix系统都可以监控可运行（意味着没有被I/O阻塞、休眠等）的线程数。Unix系统称之为运行队列（run queue）

如果试图运行的线程数超过了可用的CPU，性能就会下降。一般来说，Windows的处理器队列长度最好为0，小于或等于Unix系统CPU的数目。

### 3.1.3 磁盘使用率

监控磁盘使用率有两个目的。第一个目的与应用本身有关：如果应用正在做大量的磁盘I/O操作，那I/O就很容易成为瓶颈。

监控磁盘使用率的第二个理由是——即使预计应用不会有很高的I/O——有助于监控系统是否在进行内存交换。

### 3.1.4 网络使用率

## 3.2 Java监控工具

- jcmd
  它用来打印Java进程所涉及的基本类、线程和VM信息。
- jconsole
  提供JVM活动的图形化视图，包括线程的使用、类的使用和GC活动。
- jhat
  读取内存堆转储，并有助于分析。这是事后使用的工具。
- jmap
  提供堆转储和其他JVM内存使用的信息。可以适用于脚本，但堆转储必须在事后分析工具中使用。
- jinfo
  查看JVM的系统属性，可以动态设置一些系统属性。可适用于脚本。
- jstack
  转储Java进程的栈信息。可适用于脚本。
- jstat
  提供GC和类装载活动的信息。可适用于脚本。
- jvisualvm
  监视JVM的GUI工具，可用来剖析运行的应用，分析JVM堆转储（事后活动，虽然jvisualvm也可以实时抓取程序的堆转储）。

### 3.2.1 基本的VM信息

**运行时间**

此命令可以查看JVM运行的时长：

~~~
% jcmd process_id VM.uptime
~~~

**系统属性**

以下命令可以显示System.getProperties()的各个条目。