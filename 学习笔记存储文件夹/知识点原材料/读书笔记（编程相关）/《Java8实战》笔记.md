# Java8实战

## 第1章 为什么要关心Java8

### 1.1.2 流处理

Java8可以透明地把输入的不相关部分拿到几个CPU内核上去分别执行你的Stream操作流水线——这几乎免费的并行，用不着去费劲搞Thread了。

## 第3章 Lambda表达式

### 3.2.2 函数描述符

@FunctionalInterface又是怎么一回事？

这个标注用于表示该接口会设计成一个函数式接口。如果你用@FunctionalInterface定义了一个接口，而它却不是函数式接口的话，编译器将返回一个提示原因的错误。例如，错误信息可能是“Multiple non-overriding abstract methods found in interface Foo”，表明存在多个抽象方法。请注意，@FunctionalInterface不是必需的，但对于为此设计的接口而言，使用它是比较好的做法。它就像是@Override标注表示方法被重写了。

### 3.4.3 Function

异常、Lambda，还有函数式接口又是怎么回事呢？

请注意，任何函数式接口都不允许抛出受检异常（checked exception）。如果你需要Lambda表达式来抛出异常，有两种办法：定义一个自己的函数式接口，并声明受检异常，或者把Lambda包在一个try/catch块中。

### 3.5.4 使用局部变量

尽管如此，还有一点点小麻烦：关于能对这些变量做什么有一些限制。 Lambda可以没有限制地捕获（也就是在其主体中引用）实例变量和静态变量。但局部变量必须显式声明为final，或事实上是final。换句话说， Lambda表达式只能捕获指派给它们的局部变量一次。（注：捕获实例变量可以被看作捕获最终局部变量this。） 例如，下面的代码无法编译，因为portNumber变量被赋值两次：

~~~ java
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
portNumber = 31337;
~~~

对局部变量的限制

你可能会问自己，为什么局部变量有这些限制。第一，实例变量和局部变量背后的实现有一个关键不同。实例变量都存储在堆中，而局部变量则保存在栈上。如果Lambda可以直接访问局部变量，而且Lambda是在一个线程中使用的，则使用Lambda的线程，可能会在分配该变量的线程将这个变量收回之后，去访问该变量。因此， Java在访问自由局部变量时，实际上是在访问它的副本，而不是访问原始变量。如果局部变量仅仅赋值一次那就没有什么区别了——因此就有了这个限制。

第二，这一限制不鼓励你使用改变外部变量的典型命令式编程模式（我们会在以后的各章中解释，这种模式会阻碍很容易做到的并行处理）。

### 3.6.2 构造方法引用

对于一个现有构造函数，你可以利用它的名称和关键字new来创建它的一个引用：`ClassName::new`。它的功能与指向静态方法的引用类似。例如，假设有一个构造函数没有参数。它适合Supplier的签名`() -> Apple`。你可以这样做：

```java
Supplier<Apple> c1 = Apple::new;
Apple a1 = c1.get();
```
这就等价于：

```java
Supplier<Apple> c1 = () -> new Apple();
Apple a1 = c1.get();
```

如果你的构造函数的签名是Apple(Integer weight)，那么它就适合Function接口的签名，于是你可以这样写：

```java
Function<Integer, Apple> c2 = Apple::new;
Apple a2 = c2.apply(110);
```

这就等价于：

```java
Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
Apple a2 = c2.apply(110);
```

如果你有一个具有两个参数的构造函数Apple(String color, Integer weight)，那么它就适合BiFunction接口的签名，于是你可以这样写：

```java
BiFunction<String, Integer, Apple> c3 = Apple::new;
Apple a3 = c3.apply("green", 110);
```

这就等价于：

~~~java
BiFunction<String, Integer, Apple> c3 =
    (color, weight) -> new Apple(color, weight);
Apple a3 = c3.apply("green", 110);
~~~

测验3.7：构造函数引用

你已经看到了如何将有零个、一个、两个参数的构造函数转变为构造函数引用。那要怎么样才能对具有三个参数的构造函数，比如Color(int, int, int)， 使用构造函数引用呢？

答案：你看，构造函数引用的语法是ClassName::new，那么在这个例子里面就是Color::new。但是你需要与构造函数引用的签名匹配的函数式接口。但是语言本身并没有提供这样的函数式接口，你可以自己创建一个：
~~~java
public interface TriFunction<T, U, V, R>{
    R apply(T t, U u, V v);
}
~~~
现在你可以像下面这样使用构造函数引用了：
~~~java
TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
~~~

### 3.8.1 比较器复合

我们前面看到，你可以使用静态方法Comparator.comparing，根据提取用于比较的键值的Function来返回一个Comparator，如下所示：
~~~java
Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
~~~
1.逆序

如果你想要对苹果按重量递减排序怎么办？用不着去建立另一个Comparator的实例。接口有一个默认方法reversed可以使给定的比较器逆序。因此仍然用开始的那个比较器，只要修改一下前一个例子就可以对苹果按重量递减排序：
~~~java
inventory.sort(comparing(Apple::getWeight).reversed());
~~~
2.比较器链

上面说得都很好，但如果发现有两个苹果一样重怎么办？哪个苹果应该排在前面呢？你可能需要再提供一个Comparator来进一步定义这个比较。比如，在按重量比较两个苹果之后，你可能想要按原产国排序。 thenComparing方法就是做这个用的。它接受一个函数作为参数（就像comparing方法一样），如果两个对象用第一个Comparator比较之后是一样的，就提供第二个Comparator。你又可以优雅地解决这个问题了：
~~~java
inventory.sort(comparing(Apple::getWeight)
    .reversed()
    .thenComparing(Apple::getCountry));
~~~

### 3.8.2 谓词复合

谓词接口包括三个方法： negate、 and和or，让你可以重用已有的Predicate来创建更复杂的谓词。比如，你可以使用negate方法来返回一个Predicate的非，比如苹果不是红的：
~~~java
Predicate<Apple> notRedApple = redApple.negate();
~~~
你可能想要把两个Lambda用and方法组合起来，比如一个苹果既是红色又比较重：
~~~java
Predicate<Apple> redAndHeavyApple =
    redApple.and(a -> a.getWeight() > 150);
~~~
你可以进一步组合谓词，表达要么是重（ 150克以上）的红苹果，要么是绿苹果：
~~~java
Predicate<Apple> redAndHeavyAppleOrGreen =
    redApple.and(a -> a.getWeight() > 150)
    .or(a -> "green".equals(a.getColor()));
~~~
这一点为什么很好呢？从简单Lambda表达式出发，你可以构建更复杂的表达式，但读起来仍然和问题的陈述差不多！请注意， and和or方法是按照在表达式链中的位置，从左向右确定优先级的。因此， a.or(b).and(c)可以看作(a || b) && c。

### 3.8.3 函数复合

最后，你还可以把Function接口所代表的Lambda表达式复合起来。 Function接口为此配了andThen和compose两个默认方法，它们都会返回Function的一个实例。

andThen方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。

你也可以类似地使用compose方法，先把给定的函数用作compose的参数里面给的那个函数，然后再把函数本身用于结果。比如在上一个例子里用compose的话，它将意味着f(g(x))，而andThen则意味着g(f(x))

## 第4章 引入流

### 4.3.1 只能遍历一次

请注意，和迭代器类似，流只能遍历一次。遍历完之后，我们就说这个流已经被消费掉了。你可以从原始数据源那里再获得一个新的流来重新遍历一遍，就像迭代器一样（这里假设它是集合之类的可重复的源，如果是I/O通道就没戏了）。例如，以下代码会抛出一个异常，说流已被消费掉了：
~~~java
List<String> title = Arrays.asList("Java8", "In", "Action");
Stream<String> s = title.stream();
s.forEach(System.out::println);
s.forEach(System.out::println); // java.lang.IllegalStateException: 流已被操作或关闭
~~~
所以要记得，流只能消费一次！

## 第5章 使用流

### 5.1 筛选和切片：

5.1.1 用谓词筛选：Streams接口支持filter方法（你现在应该很熟悉了）。该操作会接受一个谓词（一个返回boolean的函数）作为参数，并返回一个包括所有符合谓词的元素的流。

5.1.2 筛选各异的元素：流还支持一个叫作distinct的方法，它会返回一个元素各异（根据流所生成元素的 hashCode和equals方法实现）的流。

5.1.3 截短流：流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递给limit。如果流是有序的，则最多会返回前n个元素。请注意limit也可以用在无序流上，比如源是一个Set。这种情况下， limit的结果不会以任何顺序排列。

5.1.4 跳过元素：流还支持skip(n)方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一个空流。请注意， limit(n)和skip(n)是互补的！

### 5.2 映射

5.2.1 对流中每一个元素应用函数：流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一个新版本”而不是去“修改”）

如果你要找出每道菜的名称有多长，怎么做？你可以像下面这样，再链接上一个map：

~~~java
List<Integer> dishNameLengths = menu.stream()
    .map(Dish::getName)
    .map(String::length)
    .collect(toList());
~~~

5.2.2 流的扁平化：使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流。

### 5.3 查找和匹配

5.3.1 检查谓词是否至少匹配一个元素：anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”。

5.3.2 检查谓词是否匹配所有元素：allMatch方法的工作原理和anyMatch类似，但它会看看流中的元素是否都能匹配给定的谓词。和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配。

anyMatch、 allMatch和noneMatch这三个操作都用到了我们所谓的短路，这就是大家熟悉的Java中&&和||运算符短路在流中的版本。

5.3.3 查找元素：findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用。

~~~java
Optional<Dish> dish =
    menu.stream()
    .filter(Dish::isVegetarian)
    .findAny();
~~~
Optional\<T>类（ java.util.Optional）是一个容器类，代表一个值存在或不存在。在上面的代码中， findAny可能什么元素都没找到。 Java 8的库设计人员引入了Optional\<T>，这样就不用返回众所周知容易出问题的null了。

Optional里面几种可以迫使你显式地检查值是否存在或处理值不存在的情形的方法也不错。
 isPresent()将在Optional包含值的时候返回true, 否则返回false。
 ifPresent(Consumer\<T> block)会在值存在的时候执行给定的代码块。我们在第3章介绍了Consumer函数式接口；它让你传递一个接收T类型参数，并返回void的Lambda表达式。
 T get()会在值存在时返回值，否则抛出一个NoSuchElement异常。
 T orElse(T other)会在值存在时返回值，否则返回一个默认值。

例如，在前面的代码中你需要显式地检查Optional对象中是否存在一道菜可以访问其名称：
~~~java
menu.stream()
    .filter(Dish::isVegetarian)
    .findAny()
    .ifPresent(d -> System.out.println(d.getName());
~~~

5.3.4 查找第一个元素：有些流有一个出现顺序（ encounter order）来指定流中项目出现的逻辑顺序（比如由List或排序好的数据列生成的流）。对于这种流，你可能想要找到第一个元素。为此有一个findFirst方法，它的工作方式类似于findAny。

何时使用findFirst和findAny

你可能会想，为什么会同时有findFirst和findAny呢？答案是并行。找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流时限制较少。

### 5.4 规约

到目前为止，你见到过的终端操作都是返回一个boolean（ allMatch之类的）、 void（ forEach）或Optional对象（ findAny等）。你也见过了使用collect来将流中的所有元素组合成一个List。

在本节中，你将看到如何把一个流中的元素组合起来，使用reduce操作来表达更复杂的查询，比如“计算菜单中的总卡路里”或“菜单中卡路里最高的菜是哪一个”。此类查询需要将流中所有元素反复结合起来，得到一个值，比如一个Integer。这样的查询可以被归类为归约操作（将流归约成一个值）。用函数式编程语言的术语来说，这称为折叠(fold），因为你可以将这个操作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。

你可以像下面这样对流中所有的元素求和：
~~~java
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
~~~
你也很容易把所有的元素相乘，只需要将另一个Lambda： (a, b) -> a * b传递给reduce操作就可以了：
~~~java
int product = numbers.stream().reduce(1, (a, b) -> a * b);
~~~
你可以使用方法引用让这段代码更简洁。在Java 8中， Integer类现在有了一个静态的sum方法来对两个数求和，这恰好是我们想要的，用不着反复用Lambda写同一段代码了：
~~~java
int sum = numbers.stream().reduce(0, Integer::sum);
~~~
reduce还有一个重载的变体，它不接受初始值，但是会返回一个Optional对象：
~~~java
Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
~~~
为什么它返回一个Optional\<Integer>呢？考虑流中没有任何元素的情况。 reduce操作无法返回其和，因为它没有初始值。这就是为什么结果被包裹在一个Optional对象里，以表明和可能不存在。

你可以像下面这样使用reduce来计算流中的最大值:
~~~java
Optional<Integer> max = numbers.stream().reduce(Integer::max);
~~~
要计算最小值，你需要把Integer.min传给reduce来替换Integer.max：
~~~java
Optional<Integer> min = numbers.stream().reduce(Integer::min);
~~~

**归约方法的优势与并行化**

相比于前面写的逐步迭代求和，使用reduce的好处在于，这里的迭代被内部迭代抽象掉了，这让内部实现得以选择并行执行reduce操作。而迭代式求和例子要更新共享变量sum，这不是那么容易并行化的。如果你加入了同步，很可能会发现线程竞争抵消了并行本应带来的性能提升！这种计算的并行化需要另一种办法：将输入分块，分块求和，最后再合并起来。但这样的话代码看起来就完全不一样了。你在第7章会看到使用分支/合并框架来做是什么样子。但现在重要的是要认识到，可变的累加器模式对于并行化来说是死路一条。你需要一种新的模式，这正是reduce所提供的。你还将在第7章看到，使用流来对所有的元素并行求和时，你的代码几乎不用修改： stream()换成了parallelStream()。
~~~java
int sum = numbers.parallelStream().reduce(0, Integer::sum);
~~~
但要并行执行这段代码也要付一定代价，我们稍后会向你解释：传递给reduce的Lambda不能更改状态（如实例变量），而且操作必须满足结合律才可以按任意顺序执行。

**流操作：无状态和有状态**

你已经看到了很多的流操作。乍一看流操作简直是灵丹妙药，而且只要在从集合生成流的时候把Stream换成parallelStream就可以实现并行。

当然，对于许多应用来说确实是这样，就像前面的那些例子。你可以把一张菜单变成流，用filter选出某一类的菜肴，然后对得到的流做map来对卡路里求和，最后reduce得到菜单的总热量。这个流计算甚至可以并行进行。但这些操作的特性并不相同。它们需要操作的内部状态还是有些问题的。

诸如map或filter等操作会从输入流中获取每一个元素，并在输出流中得到0或1个结果。这些操作一般都是**无状态**的：它们没有内部状态（假设用户提供的Lambda或方法引用没有内部可变状态）。

但诸如reduce、 sum、 max等操作需要内部状态来累积结果。在上面的情况下，内部状态很小。在我们的例子里就是一个int或double。不管流中有多少元素要处理，内部状态都是有界的。

相反，诸如sort或distinct等操作一开始都和filter和map差不多——都是接受一个流，再生成一个流（中间操作），但有一个关键的区别。从流中排序和删除重复项时都需要知道先前的历史。例如，排序要求所有元素都放入缓冲区后才能给输出流加入一个项目，这一操作的存储要求是无界的。要是流比较大或是无限的，就可能会有问题（把质数流倒序会做什么呢？它应当返回最大的质数，但数学告诉我们它不存在）。我们把这些操作叫作**有状态操作**。

| 操作      | 类型              | 返回类型    | 使用的类型/函数式接口  | 函数描述符     |
| --------- | ----------------- | ----------- | ---------------------- | -------------- |
| filter    | 中间              | Stream\<T>   | Predicate\<T>           | T -> boolean   |
| distinct  | 中间(有状态-无界) | Stream\<T>   |                        |                |
| skip      | 中间(有状态-有界) | Stream\<T>   | long                   |                |
| limit     | 中间(有状态-有界) | Stream\<T> | long                   |                |
| map       | 中间              | Stream\<R>  | Function\<T, R>         | T -> R         |
| flatMap   | 中间              | Stream\<R>  | Function\<T, Stream\<R>> | T -> Stream\<R> |
| sorted    | 中间(有状态-无界) | Stream\<T>  | Comparator\<T>          | (T, T) -> int  |
| anyMatch  | 终端              | boolean     | Predicate\<T>           | T -> boolean   |
| noneMatch | 终端              | boolean     | Predicate\<T>           | T -> boolean   |
| allMatch  | 终端              | boolean     | Predicate\<T>           | T -> boolean   |
| findAny   | 终端              | Optional\<T> |                        |                |
| findFirst | 终端              | Optional\<T> |                        |                |
| forEach   | 终端              | void        | Consumer\<T>            | T -> void      |
| collect   | 终端              | R           | Collector\<T, A, R>     |                |
| reduce    | 终端(有状态-有界) | Optional\<T> | BinaryOperator\<T>      | (T, T) -> T    |
| count     | 终端              | long        |                        |                |

### 5.6 数值流

我们在前面看到了可以使用reduce方法计算流中元素的总和。例如，你可以像下面这样计算菜单的热量：
~~~java
int calories = menu.stream()
    .map(Dish::getCalories)
    .reduce(0, Integer::sum);
~~~
这段代码的问题是，它有一个暗含的装箱成本。每个Integer都必须拆箱成一个原始类型，再进行求和。

但不要担心， Stream API还提供了原始类型流特化，专门支持处理数值流的方法。

### 5.6.1 原始类型流特化

Java 8引入了三个原始类型特化流接口来解决这个问题： IntStream、 DoubleStream和LongStream，分别将流中的元素特化为int、 long和double，从而避免了暗含的装箱成本。每个接口都带来了进行常用数值归约的新方法，比如对数值流求和的sum，找到最大元素的max。此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在于流的复杂性，而是装箱造成的复杂性——即类似int和Integer之间的效率差异。

**1. 映射到数值流**

将流转换为特化版本的常用方法是mapToInt、 mapToDouble和mapToLong。这些方法和前面说的map方法的工作方式一样，只是它们返回的是一个特化流，而不是Stream\<T>。例如，你可以像下面这样用mapToInt对menu中的卡路里求和：
~~~java
int calories = menu.stream()
    .mapToInt(Dish::getCalories)
    .sum();
~~~
这里， mapToInt会从每道菜中提取热量（用一个Integer表示），并返回一个IntStream（而不是一个Stream\<Integer>）。然后你就可以调用IntStream接口中定义的sum方法，对卡路里求和了！请注意，如果流是空的， sum默认返回0。 IntStream还支持其他的方便方法，如max、 min、 average等。

**2. 转换回对象流**

同样，一旦有了数值流，你可能会想把它转换回非特化流。例如， IntStream上的操作只能产生原始整数：IntStream 的 map 操作接受的Lambda必须接受int并返回 int（一个IntUnaryOperator）。但是你可能想要生成另一类值，比如Dish。为此，你需要访问Stream接口中定义的那些更广义的操作。要把原始流转换成一般流（每个int都会装箱成一个Integer），可以使用boxed方法，如下所示：

~~~java
IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
Stream<Integer> stream = intStream.boxed();
~~~

在下一节中会看到，在需要将数值范围装箱成为一个一般流时， boxed尤其有用。  

**3. 默认值OptionalInt  **

求和的那个例子很容易，因为它有一个默认值： 0。但是，如果你要计算IntStream中的最大元素，就得换个法子了，因为0是错误的结果。如何区分没有元素的流和最大值真的是0的流呢？前面我们介绍了Optional类，这是一个可以表示值存在或不存在的容器。 Optional可以用Integer、 String等参考类型来参数化。对于三种原始流特化，也分别有一个Optional原始类型特化版本： OptionalInt、 OptionalDouble和OptionalLong。

例如，要找到IntStream中的最大元素，可以调用max方法，它会返回一个OptionalInt：

~~~java
OptionalInt maxCalories = menu.stream()
	.mapToInt(Dish::getCalories)
	.max();
~~~

现在，如果没有最大值的话，你就可以显式处理OptionalInt去定义一个默认值了：

~~~java
int max = maxCalories.orElse(1);  
~~~

### 5.6.2 数值范围

和数字打交道时，有一个常用的东西就是数值范围。比如，假设你想要生成1和100之间的所有数字。 Java 8引入了两个可以用于IntStream和LongStream的静态方法，帮助生成这种范围：range和rangeClosed。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。但range是不包含结束值的，而rangeClosed则包含结束值。  

~~~java
IntStream evenNumbers = IntStream.rangeClosed(1, 100)
	.filter(n -> n % 2 == 0);
System.out.println(evenNumbers.count());
~~~

这里我们用了rangeClosed方法来生成1到100之间的所有数字。它会产生一个流，然后你可以链接filter方法，只选出偶数。到目前为止还没有进行任何计算。最后，你对生成的流调用count。因为count是一个终端操作，所以它会处理流，并返回结果50，这正是1到100（包括两端）中所有偶数的个数。请注意，比较一下，如果改用IntStream.range(1, 100)，则结果将会是49个偶数，因为range是不包含结束值的。

### 5.6.3 数值流应用：勾股数

~~~java
Stream<int[]> pythagoreanTriples =
    IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a ->
            IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .mapToObj(b ->
                    new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
        );
~~~

更好的方法：

~~~java
Stream<double[]> pythagoreanTriples2 =
    IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a ->
            IntStream.rangeClosed(a, 100)
                .mapToObj(
                    b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                .filter(t -> t[2] % 1 == 0));
~~~

### 5.7 构建流

**5.7.1 由值创建流**

你可以使用静态方法Stream.of，通过显式值创建一个流。它可以接受任意数量的参数。
~~~java
Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
stream.map(String::toUpperCase).forEach(System.out::println);
~~~

你可以使用empty得到一个空流，如下所示：
~~~java
Stream<String> emptyStream = Stream.empty();
~~~

**5.7.2 由数组创建流**

你可以使用静态方法Arrays.stream从数组创建一个流。它接受一个数组作为参数。例如，你可以将一个原始类型int的数组转换成一个IntStream，如下所示：
~~~java
int[] numbers = {2, 3, 5, 7, 11, 13};
int sum = Arrays.stream(numbers).sum();
~~~

**5.7.3 由文件生成流**

Java中用于处理文件等I/O操作的NIO API（非阻塞 I/O）已更新，以便利用Stream API。java.nio.file.Files中的很多静态方法都会返回一个流。例如，一个很有用的方法是Files.lines，它会返回一个由指定文件中的各行构成的字符串流。使用你迄今所学的内容，你可以用这个方法看看一个文件中有多少各不相同的词：
~~~java
long uniqueWords = 0;
try(Stream<String> lines =
    Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
    .distinct()
    .count();
}
catch(IOException e){
}
~~~

**5.7.4 由函数生成流：创建无限流**

Stream API提供了两个静态方法来从函数生成流： Stream.iterate和Stream.generate。这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流。由iterate和generate产生的流会用给定的函数按需创建值，因此可以无穷无尽地计算下去！一般来说，应该使用limit(n)来对这种流加以限制，以避免打印无穷多个值。

1. 迭代

我们先来看一个iterate的简单例子，然后再解释：
~~~java
Stream.iterate(0, n -> n + 2)
.limit(10)
.forEach(System.out::println);
~~~
iterate方法接受一个初始值（在这里是0），还有一个依次应用在每个产生的新值上的Lambda（ UnaryOperator<t>类型）。

测验5.4：斐波那契元组序列：
~~~java
Stream.iterate(new int[]{0, 1},
        t -> new int[]{t[1], t[0]+t[1]})
    .limit(20)
    .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
~~~

2. 生成

与iterate方法类似， generate方法也可让你按需生成一个无限流。但generate不是依次对每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供新的值。我们先来看一个简单的用法：
~~~java
Stream.generate(Math::random)
    .limit(5)
    .forEach(System.out::println);
~~~

下面的代码就是如何创建一个在调用时返回下一个斐波纳契项的IntSupplier：
~~~java
IntSupplier fib = new IntSupplier(){
    private int previous = 0;
    private int current = 1;
    public int getAsInt(){
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
    }
};
IntStream.generate(fib).limit(10).forEach(System.out::println);
~~~
前面的代码创建了一个IntSupplier的实例。此对象有可变的状态：它在两个实例变量中记录了前一个斐波纳契项和当前的斐波纳契项。 getAsInt在调用时会改变对象的状态，由此在每次调用时产生新的值。相比之下， 使用iterate的方法则是纯粹不变的：它没有修改现有状态，但在每次迭代时会创建新的元组。你将在第7章了解到，你应该始终采用不变的方法，以便并行处理流，并保持结果正确。

## 第6章 用流收集数据

### 6.2 规约和汇总

我们先来举一个简单的例子，利用counting工厂方法返回的收集器，数一数菜单里有多少种菜：
~~~java
long howManyDishes = menu.stream().collect(Collectors.counting());
~~~
这还可以写得更为直接：
~~~java
long howManyDishes = menu.stream().count();
~~~

### 6.2.1 查找流中的最大值和最小值

你可以使用两个收集器， Collectors.maxBy和Collectors.minBy，来计算流中的最大或最小值。这两个收集器接收一个Comparator参数来比较流中的元素。你可以创建一个Comparator来根据所含热量对菜肴进行比较，并把它传递给Collectors.maxBy：
~~~java
Comparator<Dish> dishCaloriesComparator =
    Comparator.comparingInt(Dish::getCalories);
Optional<Dish> mostCalorieDish =
    menu.stream()
        .collect(maxBy(dishCaloriesComparator));
~~~

### 6.2.2 汇总

Collectors类专门为汇总提供了一个工厂方法： Collectors.summingInt。它可接受一个把对象映射为求和所需int的函数，并返回一个收集器；该收集器在传递给普通的collect方法后即执行我们需要的汇总操作。举个例子来说，你可以这样求出菜单列表的总热量：
~~~java
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
~~~
Collectors.summingLong和Collectors.summingDouble方法的作用完全一样，可以用于求和字段为long或double的情况。

但汇总不仅仅是求和；还有Collectors.averagingInt，连同对应的averagingLong和averagingDouble可以计算数值的平均数：
~~~java
double avgCalories =
    menu.stream().collect(averagingInt(Dish::getCalories));
~~~

不过很多时候，你可能想要得到两个或更多这样的结果，而且你希望只需一次操作就可以完成。在这种情况下，你可以使用summarizingInt工厂方法返回的收集器。例如，通过一次summarizing操作你可以就数出菜单中元素的个数，并得到菜肴热量总和、平均值、最大值和最小值：
~~~java
IntSummaryStatistics menuStatistics =
    menu.stream().collect(summarizingInt(Dish::getCalories));
~~~
这个收集器会把所有这些信息收集到一个叫作IntSummaryStatistics的类里，它提供了方便的取值（ getter）方法来访问结果。打印menuStatisticobject会得到以下输出：
~~~java
IntSummaryStatistics{count=9, sum=4300, min=120,
                     average=477.777778, max=800}
~~~
同样，相应的summarizingLong和summarizingDouble工厂方法有相关的LongSummaryStatistics和DoubleSummaryStatistics类型，适用于收集的属性是原始类型long或double的情况

###6.2.3 连接字符串

joining工厂方法返回的收集器会把对流中每一个对象应用toString方法得到的所有字符串连接成一个字符串。这意味着你把菜单中所有菜肴的名称连接起来，如下所示：
~~~java
String shortMenu = menu.stream().map(Dish::getName).collect(joining());
~~~
请注意， joining在内部使用了StringBuilder来把生成的字符串逐个追加起来。此外还要注意，如果Dish类有一个toString方法来返回菜肴的名称，那你无需用提取每一道菜名称的函数来对原流做映射就能够得到相同的结果：
~~~java
String shortMenu = menu.stream().collect(joining());
~~~
二者均可产生以下字符串：
`porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon`
但该字符串的可读性并不好。幸好， joining工厂方法有一个重载版本可以接受元素之间的分界符，这样你就可以得到一个逗号分隔的菜肴名称列表：
~~~java
String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
~~~
正如我们预期的那样，它会生成：
`pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon`

### 6.2.4 广义的规约汇总

事实上，我们已经讨论的所有收集器，都是一个可以用reducing工厂方法定义的归约过程的特殊情况而已。 Collectors.reducing工厂方法是所有这些特殊情况的一般化。可以说，先前讨论的案例仅仅是为了方便程序员而已。（但是，请记得方便程序员和可读性是头等大事！ ）例如，可以用reducing方法创建的收集器来计算你菜单的总热量，如下所示：
~~~java
int totalCalories = menu.stream().collect(reducing(
                                    0, Dish::getCalories, (i, j) -> i + j));
~~~
它需要三个参数。

 第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值
和而言0是一个合适的值。
 第二个参数就是你在6.2.2节中使用的函数，将菜肴转换成一个表示其所含热量的int。
 第三个参数是一个BinaryOperator，将两个项目累积成一个同类型的值。这里它就是
对两个int求和。

同样，你可以使用下面这样单参数形式的reducing来找到热量最高的菜，如下所示：
~~~java
Optional<Dish> mostCalorieDish =
    menu.stream().collect(reducing(
        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
~~~
你可以把单参数reducing工厂方法创建的收集器看作三参数方法的特殊情况，它把流中的第一个项目作为起点，把恒等函数（即一个函数仅仅是返回其输入参数）作为一个转换函数。这也意味着，要是把单参数reducing收集器传递给空流的collect方法，收集器就没有起点；正如我们在6.2.1节中所解释的，它将因此而返回一个Optional<Dish>对象。

## 6.3 分组

假设你要把菜单中的菜按照类型进行分类，有肉的放一组，有鱼的放一组，其他的都放另一组。用Collectors.groupingBy工厂方法返回的收集器就可以轻松地完成这项任务，如下所示：
~~~java
Map<Dish.Type, List<Dish>> dishesByType =
    menu.stream().collect(groupingBy(Dish::getType));
~~~
其结果是下面的Map：
`{FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza],MEAT=[pork, beef, chicken]}`

这里，你给groupingBy方法传递了一个Function（以方法引用的形式），它提取了流中每一道Dish的Dish.Type。我们把这个Function叫作分类函数，因为它用来把流中的元素分成不同的组。如图6-4所示，分组操作的结果是一个Map，把分组函数返回的值作为映射的键，把流中所有具有这个分类值的项目的列表作为对应的映射值。

但是，分类函数不一定像方法引用那样可用，因为你想用以分类的条件可能比简单的属性访问器要复杂。例如，你可能想把热量不到400卡路里的菜划分为“低热量”（ diet），热量400到700卡路里的菜划为“普通”（ normal），高于700卡路里的划为“高热量”（ fat）。由于Dish类的作者没有把这个操作写成一个方法，你无法使用方法引用，但你可以把这个逻辑写成Lambda表达式：
~~~java
public enum CaloricLevel { DIET, NORMAL, FAT }
Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
    groupingBy(dish -> {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
} ));
~~~

### 6.3.1 多级分组

要实现多级分组，我们可以使用一个由双参数版本的Collectors.groupingBy工厂方法创建的收集器，它除了普通的分类函数之外，还可以接受collector类型的第二个参数。那么要进行二级分组的话，我们可以把一个内层groupingBy传递给外层groupingBy，并定义一个为流中项目分类的二级标准
~~~java
Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
    menu.stream().collect(
        groupingBy(Dish::getType,
            groupingBy(dish -> {
                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
            } )
        )
    );
~~~

### 6.3.2 按子组收集数据

在上一节中，我们看到可以把第二个groupingBy收集器传递给外层收集器来实现多级分组。但进一步说，传递给第一个groupingBy的第二个收集器可以是任何类型，而不一定是另一个groupingBy。例如，要数一数菜单中每类菜有多少个，可以传递counting收集器作为groupingBy收集器的第二个参数：
~~~java
Map<Dish.Type, Long> typesCount = menu.stream().collect(
    groupingBy(Dish::getType, counting()));
~~~
其结果是下面的Map：
`{MEAT=3, FISH=2, OTHER=4}`

还要注意，普通的单参数groupingBy(f)（其中f是分类函数）实际上是groupingBy(f,toList())的简便写法。

再举一个例子，你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类型分类：
~~~java
Map<Dish.Type, Optional<Dish>> mostCaloricByType =
    menu.stream()
        .collect(groupingBy(Dish::getType,
                            maxBy(comparingInt(Dish::getCalories))));
~~~
这个分组的结果显然是一个map，以Dish的类型作为键，以包装了该类型中热量最高的Dish的Optional<Dish>作为值：
`{FISH=Optional[salmon], OTHER=Optional[pizza], MEAT=Optional[pork]}`

**1. 把收集器的结果转换为另一种类型**

因为分组操作的Map结果中的每个值上包装的Optional没什么用，所以你可能想要把它们去掉。要做到这一点，或者更一般地来说，把收集器返回的结果转换为另一种类型，你可以使用Collectors.collectingAndThen工厂方法返回的收集器

~~~java
Map<Dish.Type, Dish> mostCaloricByType =
    menu.stream()
        .collect(groupingBy(Dish::getType,
            collectingAndThen(
                maxBy(comparingInt(Dish::getCalories)),
                Optional::get)));
~~~
这个工厂方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器。这个收集器相当于旧收集器的一个包装， collect操作的最后一步就是将返回值用转换函数做一个映射。在这里，被包起来的收集器就是用maxBy建立的那个，而转换函数Optional::get则把返回的Optional中的值提取出来。前面已经说过，这个操作放在这里是安全的，因为reducing收集器永远都不会返回Optional.empty()。其结果是下面的Map：
`{FISH=salmon, OTHER=pizza, MEAT=pork}`

**2. 与groupingBy联合使用的其他收集器的例子**

一般来说，通过groupingBy工厂方法的第二个参数传递的收集器将会对分到同一组中的所有流元素执行进一步归约操作。例如，你还重用求出所有菜肴热量总和的收集器，不过这次是对每一组Dish求和：
~~~java
Map<Dish.Type, Integer> totalCaloriesByType =
    menu.stream().collect(groupingBy(Dish::getType,
                                summingInt(Dish::getCalories)));
~~~
然而常常和groupingBy联合使用的另一个收集器是mapping方法生成的。这个方法接受两个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来。其目的是在累加之前对每个输入元素应用一个映射函数，这样就可以让接受特定类型元素的收集器适应不同类型的对象。我们来看一个使用这个收集器的实际例子。比方说你想要知道，对于每种类型的Dish，菜单中都有哪些CaloricLevel。 我们可以把groupingBy和mapping收集器结合起来，如下所示：
~~~java
Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
    menu.stream().collect(
        groupingBy(Dish::getType, mapping(
            dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT; },
            toSet() )));
~~~
这里，就像我们前面见到过的，传递给映射方法的转换函数将Dish映射成了它的CaloricLevel：生成的CaloricLevel流传递给一个toSet收集器，它和toList类似，不过是把流中的元素累积到一个Set而不是List中，以便仅保留各不相同的值。如先前的示例所示，这个映射收集器将会收集分组函数生成的各个子流中的元素，让你得到这样的Map结果：
`{OTHER=[DIET, NORMAL], MEAT=[DIET, NORMAL, FAT], FISH=[DIET, NORMAL]}`

由此你就可以轻松地做出选择了。如果你想吃鱼并且在减肥，那很容易找到一道菜；同样，如果你饥肠辘辘，想要很多热量的话，菜单中肉类部分就可以满足你的饕餮之欲了。请注意在上一个示例中，对于返回的Set是什么类型并没有任何保证。但通过使用toCollection，你就可以有更多的控制。例如，你可以给它传递一个构造函数引用来要求HashSet
~~~java
Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
    menu.stream().collect(
        groupingBy(Dish::getType, mapping(
            dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT; },
            toCollection(HashSet::new) )));
~~~

### 6.4 分区

分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数。分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以分为两组——true是一组， false是一组。
~~~java
Map<Boolean, List<Dish>> partitionedMenu =
    menu.stream().collect(partitioningBy(Dish::isVegetarian));
~~~

### 6.4.1 分区的优势

而且就像你在分组中看到的， partitioningBy工厂方法有一个重载版本，可以像下面这样传递第二个收集器：
~~~java
Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
    menu.stream().collect(
        partitioningBy(Dish::isVegetarian,
        groupingBy(Dish::getType)));
~~~

| 工厂方法          | 返回类型               | 用于                                                         |
| ----------------- | ---------------------- | ------------------------------------------------------------ |
| toList            | List\<T>               | 把流中所有项目收集到一个List                                 |
| toSet             | Set\<T>                | 把流中所有项目收集到一个Set，删除重复项                      |
| toCollection      | Collection\<T>         | 把流中所有项目收集到给定的供应源创建的集合                   |
| counting          | Long                   | 计算流中元素的个数                                           |
| summingInt        | Integer                | 对流中项目的一个整数属性求和                                 |
| averagingInt      | Double                 | 计算流中项目Integer属性的平均值                              |
| summarizingInt    | IntSummaryStatistics   | 收集关于流中项目Integer属性的统计值，例如最大、最小、总和与平均值 |
| joining           | String                 | 连接对流中每个项目调用toString方法所生成的字符串             |
| maxBy             | Optional\<T>           | 一个包裹了流中按照给定比较器选出的最大元素的Optional，或如果流为空则为Optional.empty() |
| minBy             | Optional\<T>           | 一个包裹了流中按照给定比较器选出的最小元素的Optional，或如果流为空则为Optional.empty() |
| reducing          | 规约操作产生的类型     | 从一个作为累加器的初始值开始，利用BinaryOperator与流中的元素逐个结合，从而将流规约为单个值 |
| collectingAndThen | 转换函数返回的类型     | 包裹另一个收集器，对其结果应用转换函数                       |
| groupingBy        | Map\<K, List\<T>>      | 根据项目的一个属性的值对流中的项目作问组，并将属性值作为结果Map的键 |
| partitioningBy    | Map\<Boolean,List\<T>> | 根据对流中每个项目应用谓词的结果来对项目进行分区             |


### 6.5 收集器接口

首先让我们在下面的列表中看看Collector接口的定义，它列出了接口的签名以及声明的五个方法。
~~~java
public interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    Function<A, R> finisher();
    BinaryOperator<A> combiner();
    Set<Characteristics> characteristics();
}
~~~
本列表适用以下定义。
 T是流中要收集的项目的泛型。
 A是累加器的类型，累加器是在收集过程中用于累积部分结果的对象。
 R是收集操作得到的对象（通常但并不一定是集合）的类型。

### 6.5.1 理解Collector接口

现在我们可以一个个来分析Collector接口声明的五个方法了。通过分析，你会注意到，前四个方法都会返回一个会被collect方法调用的函数，而第五个方法characteristics则提供了一系列特征，也就是一个提示列表，告诉collect方法在执行归约操作的时候可以应用哪些优化（比如并行化）。

**1. 建立新的结果容器： supplier方法**

supplier方法必须返回一个结果为空的Supplier，也就是一个无参数函数，在调用时它会创建一个空的累加器实例，供数据收集过程使用。很明显，对于将累加器本身作为结果返回的收集器，比如我们的ToListCollector，在对空流执行操作的时候，这个空的累加器也代表了收集过程的结果。在我们的ToListCollector中， supplier返回一个空的List

**2. 将元素添加到结果容器： accumulator方法**

accumulator方法会返回执行归约操作的函数。当遍历到流中第n个元素时，这个函数执行时会有两个参数：保存归约结果的累加器（已收集了流中的前 n1 个项目）， 还有第n个元素本身。该函数将返回void，因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的元素的效果。对于ToListCollector，这个函数仅仅会把当前项目添加至已经遍历过的项目的列表

**3. 对结果容器应用最终转换： finisher方法**

在遍历完流后， finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加器对象转换为整个集合操作的最终结果。通常，就像ToListCollector的情况一样，累加器对象恰好符合预期的最终结果，因此无需进行转换。所以finisher方法只需返回identity函数

这三个方法已经足以对流进行顺序归约，至少从逻辑上看可以按图6-7进行。实践中的实现细节可能还要复杂一点，一方面是因为流的延迟性质，可能在collect操作之前还需要完成其他中间操作的流水线，另一方面则是理论上可能要进行并行归约。

**4. 合并两个结果容器： combiner方法**

四个方法中的最后一个——combiner方法会返回一个供归约操作使用的函数，它定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并。对于toList而言，这个方法的实现非常简单，只要把从流的第二个部分收集到的项目列表加到遍历第一部分时得到的列表后面就行了

有了这第四个方法，就可以对流进行并行归约了。它会用到Java 7中引入的分支/合并框架和Spliterator抽象，我们会在下一章中讲到。

 原始流会以递归方式拆分为子流，直到定义流是否需要进一步拆分的一个条件为非（如果分布式工作单位太小，并行计算往往比顺序计算要慢，而且要是生成的并行任务比处理器内核数多很多的话就毫无意义了）。
 现在，所有的子流都可以并行处理，即对每个子流应用图6-7所示的顺序归约算法。
 最后，使用收集器combiner方法返回的函数，将所有的部分结果两两合并。这时会把原始流每次拆分时得到的子流对应的结果合并起来。

**5. characteristics方法**
最后一个方法——characteristics会返回一个不可变的Characteristics集合，它定义了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示。Characteristics是一个包含三个项目的枚举。

 UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
 CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
 IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检查地转换为结果R是安全的。

我们迄今开发的ToListCollector是IDENTITY_FINISH的，因为用来累积流中元素的List已经是我们要的最终结果，用不着进一步转换了，但它并不是UNORDERED，因为用在有序流上的时候，我们还是希望顺序能够保留在得到的List中。最后，它是CONCURRENT的，但我们刚才说过了，仅仅在背后的数据源无序时才会并行处理。

### 6.5.2 全部融合到一起

前一小节中谈到的五个方法足够我们开发自己的ToListCollector了。你可以把它们都融合起来，如下面的代码清单所示。
~~~java
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.indentity();
    }
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                            IDENTITY_FINISH, CONCURRENT));
    }
}
~~~
请注意，这个实现与Collectors.toList方法并不完全相同，但区别仅仅是一些小的优化。这些优化的一个主要方面是Java API所提供的收集器在需要返回空列表时使用了Collections.emptyList()这个单例（ singleton）。这意味着它可安全地替代原生Java，来收集菜单流中的所有Dish的列表：
`List<Dish> dishes = menuStream.collect(new ToListCollector<Dish>());`
这个实现和标准的
`List<Dish> dishes = menuStream.collect(toList());`
构造之间的其他差异在于toList是一个工厂，而ToListCollector必须用new来实例化。

**进行自定义收集而不去实现Collector**

对于IDENTITY_FINISH的收集操作，还有一种方法可以得到同样的结果而无需从头实现新的Collectors接口。Stream有一个重载的collect方法可以接受另外三个函数——supplier、accumulator和combiner，其语义和Collector接口的相应方法返回的函数完全相同。
~~~java
List<Dish> dishes = menuStream.collect(
                        ArrayList::new,
                        List::add,
                        List::addAll);
~~~
我们认为，这第二种形式虽然比前一个写法更为紧凑和简洁，却不那么易读。此外，以恰当的类来实现自己的自定义收集器有助于重用并可避免代码重复。另外值得注意的是，这第二个collect方法不能传递任何Characteristics，所以它永远都是一个IDENTITY_FINISH和CONCURRENT但并非UNORDERED的收集器。

## 第7章 并行数据处理和性能

### 7.1.1 将顺序流转换为并行流

你可以把流转换成并行流，从而让前面的函数归约过程（也就是求和）并行运行——对顺序流调用parallel方法
~~~java
public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
}
~~~
在上面的代码中，对流中所有数字求和的归纳过程的执行方式和5.4.1节中说的差不多。不同之处在于Stream在内部分成了几块。因此可以对不同的块独立并行进行归纳操作，如图7-1所示。最后，同一个归纳操作会将各个子流的部分归纳结果合并起来，得到整个原始流的归纳结果。

请注意，在现实中，对顺序流调用parallel方法并不意味着流本身有任何实际的变化。它在内部实际上就是设了一个boolean标志，表示你想让调用parallel之后进行的所有操作都并行执行。类似地，你只需要对并行流调用sequential方法就可以把它变成顺序流。请注意，你可能以为把这两个方法结合起来，就可以更细化地控制在遍历流时哪些操作要并行执行，哪些要顺序执行。

**配置并行流使用的线程池**

并行流内部使用了默认的ForkJoinPool（ 7.2节会进一步讲到分支/合并框架），它默认的线程数量就是你的处理器数量，这个值是由Runtime.getRuntime().availableProcessors()得到的。但是你可以通过系统属性java.util.concurrent.ForkJoinPool.common.parallelism来改变线程池大小，如下所示：
`System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");`
这是一个全局设置，因此它将影响代码中所有的并行流。反过来说，目前还无法专为某个并行流指定这个值。一般而言，让ForkJoinPool的大小等于处理器数量是个不错的默认值，除非你有很好的理由，否则我们强烈建议你不要修改它。

### 7.1.2 测量流性能

那到底要怎么利用多核处理器，用流来高效地并行求和呢？我们在第5章中讨论了一个叫LongStream.rangeClosed的方法。这个方法与iterate相比有两个优点。
 LongStream.rangeClosed直接产生原始类型的long数字，没有装箱拆箱的开销。
 LongStream.rangeClosed会生成数字范围， 很容易拆分为独立的小块。例如，范围1~20可分为1~5、 6~10、 11~15和16~20。

### 7.1.4 高效使用并行流

 如果有疑问，测量。把顺序流转成并行流轻而易举，但却不一定是好事。我们在本节中已经指出，并行流并不总是比顺序流快。此外，并行流有时候会和你的直觉不一致，所以在考虑选择顺序流还是并行流时，第一个也是最重要的建议就是用适当的基准来检查其性能。
 留意装箱。自动装箱和拆箱操作会大大降低性能。 Java 8中有原始类型流（ IntStream、LongStream、 DoubleStream）来避免这种操作，但凡有可能都应该用这些流。
 有些操作本身在并行流上的性能就比顺序流差。特别是limit和findFirst等依赖于元素顺序的操作，它们在并行流上执行的代价非常大。例如， findAny会比findFirst性能好，因为它不一定要按顺序来执行。你总是可以调用unordered方法来把有序流变成无序流。那么，如果你需要流中的n个元素而不是专门要前n个的话，对无序并行流调用limit可能会比单个有序流（比如数据源是一个List）更高效。
 还要考虑流的操作流水线的总计算成本。设N是要处理的元素的总数， Q是一个元素通过流水线的大致处理成本，则N*Q就是这个对成本的一个粗略的定性估计。 Q值较高就意味着使用并行流时性能好的可能性比较大。
 对于较小的数据量，选择并行流几乎从来都不是一个好的决定。并行处理少数几个元素的好处还抵不上并行化造成的额外开销。
 要考虑流背后的数据结构是否易于分解。例如， ArrayList的拆分效率比LinkedList高得多，因为前者用不着遍历就可以平均拆分，而后者则必须遍历。另外，用range工厂方法创建的原始类型流也可以快速分解。最后，你将在7.3节中学到，你可以自己实现Spliterator来完全掌控分解过程。
 流自身的特点，以及流水线中的中间操作修改流的方式，都可能会改变分解过程的性能。例如，一个SIZED流可以分成大小相等的两部分，这样每个部分都可以比较高效地并行处理，但筛选操作可能丢弃的元素个数却无法预测，导致流本身的大小未知。
 还要考虑终端操作中合并步骤的代价是大是小（例如Collector中的combiner方法）。如果这一步代价很大，那么组合每个子流产生的部分结果所付出的代价就可能会超出通过并行流得到的性能提升。

流的数据源和可分解性

| 源              | 可分解性 |
| --------------- | -------- |
| ArrayList       | 极佳     |
| LinkedList      | 差       |
| IntStream.range | 极佳     |
| Stream.iterate  | 差       |
| HashSet         | 好       |
| TreeSet         | 好       |

### 7.2 分支合并框架

分支/合并框架的目的是以递归方式将可以并行的任务拆分成更小的任务，然后将每个子任务的结果合并起来生成整体结果。它是ExecutorService接口的一个实现，它把子任务分配给线程池（称为ForkJoinPool）中的工作线程。  

### 7.2.1 使用RecursiveTask

要把任务提交到这个池，必须创建RecursiveTask<R>的一个子类，其中R是并行化任务（以及所有子任务）产生的结果类型，或者如果任务不返回结果，则是RecursiveAction类型（当然它可能会更新其他非局部机构）。要定义RecursiveTask， 只需实现它唯一的抽象方法compute：
`protected abstract R compute();`
这个方法同时定义了将任务拆分成子任务的逻辑，以及无法再拆分或不方便再拆分时，生成单个子任务结果的逻辑。  