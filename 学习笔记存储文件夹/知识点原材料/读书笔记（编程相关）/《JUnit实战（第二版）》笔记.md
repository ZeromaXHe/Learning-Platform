# 第一部分 认识JUnit

# 第1章 JUnit起步

## 1.1 证实它能运行

## 1.2 从零开始

## 1.3 理解单元测试框架

单元测试框架应该遵循以下几条最佳实践规则。CalculatorTest程序中一些不起眼的改进就突出体现了所有单元测试框架应该遵循（按照我们的经验）的三大规则：

- 每个单元测试都必须独立于其他所有单元测试而运行；
- 框架应该以单个测试为单位来检测和报告错误；
- 应该易于定义要运行哪些单元测试。

## 1.4 JUnit的设计目标

JUnit团队已经为框架定义了3个不相关的目标：

- 框架必须帮助我们编写有用的测试；
- 框架必须帮助我们创建具有长久价值的测试； 
- 框架必须帮助我们通过复用代码来降低编写测试的成本。

## 1.5 安装JUnit

## 1.6 使用JUnit测试

JUnit拥有许多功能，可以使编写、运行测试更加容易。通过本书，你将可以了解到这些功能在实际中的各种运用。

- 针对每个单元测试，单独测试类实例和类加载器，以避免副作用。
- JUnit注释提供了资源初始化和回收方法：@Before、@BeforeClass、@After和@AfterClass。
- 各种不同的assert方法使得检查测试结果更加简单。
- 与各种流行工具（如Ant和Maven）的整合，以及与流行IDE（如Eclipse、NetBeans、IntelliJ和JBuilder）的整合。

事不宜迟，我们赶紧来看一下代码1.4，看看使用JUnit编写的简单Calculator测试会使什么样子。

~~~java
import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = Calculator.add(10, 50);
        assertEquals(60, result, 0);
    }
}
~~~

虽然在JUnit3中我们需要扩展TestCase类，但是在JUnit4中，我们已经不需要这样做了。

# 第2章 探索JUnit的核心

## 2.1 探索JUnit核心

定义 个测试类的要求是，这个类必须是公共的并且包含了一个无参数的构造函数。在我们的示例中，因为我们没有定义任何其他构造函数，所以我们也不需要定义无参数的构造函数 Java 会为我们隐式地创建它。

创建一个测试方法的要求是，这个方法必须使用＠Test注释，是公共的，不带任何参数，并且返回 void 类型。

JUnit 在调用（执行）每个＠Test 方法之前，为测试类创建一个新的实例。这有助于提供测试方法之间的独立性，并且避免在测试代码中产生意外的副作用。因为每个测试方法运行于一个新的测试类实例上，所以找们就不能在测试方法之间重用各个实例变量值。

为了进行测试验证，我们使用 JUnit 的 Assert 类提供的 assert 方法。正如你在之前的示例中所看到的那样，我们在测试类中静态地导入这些方法。另外，根据我们对静态导入的喜好，我们还可以导入 JUnit 的 Assert 类本身。表 2.1 列出了一些最流行的 assert 方法。

| assertXXX方法                      | 作用                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| assertArrayEquals("message", A, B) | 断言A数组和B数组相等                                         |
| assertEquals("message", A, B)      | 断言A对象和B对象相等，这个断言在比较两个对象时调用了equals()方法 |
| assertSame("message", A, B)        | 断言A对象与B对象相同。之前的assert方法是检查A与B是否有相同的值（使用了equals方法），而assertSame方法则是检查A与B就是同一个对象（使用的是==操作符） |
| assertTrue("message", A)           | 断言A条件为真                                                |
| assertNotNull("message", A)        | 断言A对象不为null                                            |

当你需要一次运行多个测试类时 ，你就要创建另一个叫做测试集（test suite 或 Suite）的对象。你的测试集也是一个特定的测试运行器（或 Runner），因此可以像运行测试类那样运行它。一旦你理解了测试类、Suite 与 Runner 是如何工作的，你就可以编写你所需要的任何测试了。这3个对象形成了 JUnit 框架的核心。

> **DEFINITION**
>
> **测试类** （Test class 或 TestCase 或 test case）——一个包含一个或者多个测试的类，而这些测试就是指那些用＠Test 注释的方法．使用一个测试类，可以把具有公共行为的测试归入一组。在本书的后续部分中，如果我们提到**测试**的 时候，我们指的是一个用＠Test 注释的方法；如果找们提到一个测试用例（或测试类），我们指的是一个包含了这些测试方法的类。也就是一组测试．通常在生产类和测试类之间都存在着一对一的对应关系。

**测试集** （Suite 或者 test suite）——一组测试。测试集是一种把多个相关测试归入一组的便捷方式。比如，如果你没有为测试类定义一个测试集，那么 JUnit 会自动提供一个测试集，包含测试类中所有的测试（在后面的章节中会详细介绍）。一个测试集通常会将同一个包中的测试类归入一个组。

**测试运行器**（Runner 或 test runner）——执行测试集的程序。JUnit 提供了多种运行器来执行你的测试。本章随后会介绍这些运行器，并且教你如何编写自己的测试运行器。

让我们来看一看JUnit核心对象的具体职责，如表2.2所示。

| JUnit概念 | 责任                                                         | 介绍章节 |
| --------- | ------------------------------------------------------------ | -------- |
| Assert    | 让你去定义你想测试的条件。当条件成立时，assert方法保持沉默：但条件不成立时则抛出异常 | 2.1小节  |
| 测试      | 一个以@Test注释的方法定义了一个测试。为了运行这个方法，JUnit会创建一个包含类的新实例，然后再调用这个被注释的方法 | 2.1小节  |
| 测试类    | 一个测试类是@Test方法的容器                                  | 2.1小节  |
| Suite     | Suite允许你将测试类归成一组                                  | 2.3小节  |
| Runner    | Runner类用来运行测试，JUnit 4是向后兼容的，可以运行JUnit 3的测试 | 2.2小节  |

## 2.2 运行参数化测试

Parameterized（参数化）的测试运行器允许你使用不同的参数多次运行同一个测试。代码2.2给出一个Parameterized运行器的实例（你可以在第1章的源代码示例中找到这个测试）。

~~~java
[...]
@RunWith(value=Parameterized.class)
public class ParameterizedTest {
    private double expected;
    private double valueOne;
    private double valueTwo;
    
    @Parameters
    public static Collection<Integer[]> getTestParameters() {
        return Arrays.asList(new Integer[][] {
            {2, 1, 1}, // expected, valueOne, valueTwo
            {3, 2, 1}, // expected, valueOne, valueTwo
            {4, 3, 1}, // expected, valueOne, valueTwo
        });
    }
    
    public ParameterizedTest(double expected, double valueOne, double valueTwo) {
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }
    
    @Test
    public void sum() {
        Calculator calc = new Calculator();
        assertEquals(expected, calc.add(valueOne, valueTwo), 0);
    }
}
~~~

要使用Parameterized的测试运行器来运行一个测试类，那就必须要满足以下要求。首先，测试类必须使用@RunWith注释，并且要将Parameterized类作为它的参数。其次，你必须声明测试中所使用的实例变量，同时提供一个用@Parameters注释的方法，这里提供的是getTestParameters方法。此外，这个方法的签名必须是@Parameter public static java.util.Collection，无任何参数。Collection元素必须是相同长度的数组。这个数组的长度必须要和这个唯一的公共构造函数的参数数量相匹配。

## 2.3 JUnit的测试运行器

### 2.3.1 测试运行器简介

| 运行器                                        | 目的                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| org.junit.internal.runners.JUnit38ClassRunner | 这个运行器包含在当前的JUnit版本中，仅仅是为了向后兼容。它将测试用例作为JUnit3.8的测试用例来启动 |
| org.junit.runners.JUnit4                      | 这个运行器将测试用例作为JUnit4的测试用例来启动               |
| org.junit.runners.Parameterized               | 这个测试运行器可以使用不同参数来运行相同的测试集             |
| org.junit.runners.Suite                       | Suite是一个包含不同测试的容器，同时Suite也是一个运行器，可以运行一个测试类中所有以@Test注释的方法 |

如果测试类中没有提供任何运行器，那么 JUnit 将会使用一个默认的运行器。如果你希望 JUnit 使用某个特定的测试运行器 ，那么就使用 @RunWith 注释来指定测试运行器类

### 2.3.2 JUnitCore facade

为了能够尽可能快捷地运行测试，JUnit提供了一个facade（org.junit.runner.JUnitCore），它可以运行任何测试运行器。JUnit设计这个facade来执行你的测试，并收集测试结果与统计信息。

> **设计模式实践：facade**
>
> facade是一种设计模式，它为子系统中的一组接口提供了一个统一的接口。facade定义了一个更高级别的接口，使得子系统更易于使用。你可以使用facade来将一些复杂的对象交互简化成一个单独的接口。

### 2.3.3 自定义测试运行器

不像JUnit框架中的其他元素，这里没有Runner接口。相反，JUnit自带的几个测试运行器都继承了org.junit.runner.Runner类。如果想创建你自己的测试运行器，你就需要扩展Runner类。关于这个话题的详细介绍，可以参考本书的附录B。

## 2.4 用Suite来组合测试

### 2.4.1 组合一组测试类

下一步就是运行多个测试类。为了简化这个任务，JUnit提供了测试Suite。这个Suite是一个容器，用来把几个测试归在一起，并把它们作为一个集合一起运行。

JUnit设计Suite的目的就是为了运行一个或者多个测试用例。测试运行器会启动Suite；然后运行哪个测试用例是由Suite来决定的。

你可能会疑惑，在第1章最后给出的那个示例中，你并没有定义一个Suite，这个示例是如何运行起来的呢？为了使简单的事情可以保持简单，如果你没有提供一个自己的Suite，那么测试运行器会自动创建一个Suite。

默认的Suite会扫描你的测试类，找出所有以@Test注释的方法。默认的Suite会在内部为每个@Test方法创建一个测试类的实例。然后JUnit就会独立地执行每个@Test方法，以避免潜在的负面影响。

如果你想将另一个测试添加到CalculatorTest类，比如testSubstract，同时你使用@Test注释这个测试，那么默认的Suite就会自动包含这个测试。

Suite对象其实是一个Runner，可以执行测试类中所有@Test注释的方法。

代码2.3显示了如何将多个测试类组合成一个单独的测试集（test suite）。

~~~java
[...]
@RunWith(value=org.junit.runners.Suite.class)
@SuiteClasses(value=(FolderConfigurationTest.class,
                     FileConfigurationTest.class))
public class FileSystemConfigurationTestSuite {
    
}
~~~

在代码2.3中，我们使用@RunWith注释制定了相应的运行器，并且通过在@SuiteClasses注释中指定测试类，来列出我们想要在这个测试中包含的所有测试。这些测试类中的所有@Test方法都将包含到该Suite中。

### 2.4.2 组合一组测试集

由于JUnit采用了一种精妙的构建方式，所以使用JUnit来构建一组测试集也就成为了可能。例如，代码2.4串联了几个不同的文件，以展示多个测试用例是如何组合成多个测试集，然后这些测试集又组合成了一个主测试集。

~~~java
[...]
public class TestCaseA {
    @Test
    public void testA1() {
        // omitted
    }
}

[...]
public class TestCaseB {
    @Test
    public void testB1() {
        // omitted
    }
}

[...]
@RunWith[value=Suite.class]
@SuiteClasses(value = {TestCaseA.class})
public class TestSuiteA {   
}

[...]
@RunWith[value=Suite.class]
@SuiteClasses(value = {TestCaseB.class})
public class TestSuiteB {   
}

[...]
@RunWith[value = Suite.class]
@SuiteClasses(value = {TestCaseA.class, TestCaseB.class})
public class MasterTestSuite {   
}
~~~

### 2.4.3 Suite、IDE、Ant与Maven

# 第3章 掌握JUnit

