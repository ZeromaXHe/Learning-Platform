# 第1部分 认识JUnit

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

现在我们通过引入和测试一个真实的组件，来更深入地研究JUnit。在这一章中，我们实现了一个使用Controller设计模式的小型应用程序。然后，我们使用JUnit测试该应用程序的每一部分。我们也介绍了一些编写和组织测试时的”JUnit最佳实践“。

## 3.1 引入controller组件

一般而言，controller可以处理以下事务：

- 接受请求；
- 根据请求执行任意常用计算；
- 选择一个合适的请求处理器；
- 路由请求，以便处理器可以执行相关的业务逻辑；
- 可能提供一个顶层处理器来处理错误和异常。

### 3.1.1 设计接口

~~~java
public interface Request {
    String getName();
}
public interface Response {
    
}
public interface RequestHandler {
    Response process(Request request) throws Exception;
}
public interface Controller {
    Response precessRequest(Request request);
    void addHandler(Request request, RequestHandler requestHandler);
}
~~~



### 3.1.2 实现基类

~~~java
[...]
import java.util.HashMap;
import java.util.Map;

public class DefaultController implements Controller {
    private Map requestHandlers = new HashMap();
    
    protected RequestHandler getHandler(Request request) {
        if (!this.requestHandlers.containsKey(request.getName())){
            String message = "Cannot find handler for request name " + "[" + request.getName() + "]";
            throw new RuntimeException(message);
        }
        return (RequestHandler)this.requestHandlers.get(request.getName());
    }
    
    public Response processRequest(Request request) {
        Response response;
        try {
            response = getHandler(request).process(request);
        } catch (Exception exception) {
            response = new ErrorResponse(request, exception);
        }
        return response;
    }
    
    public void addHandler(Request request, RequestHandler requestHandler) {
        if (!this.requestHandlers.containsKey(request.getName()) {
            throw new RuntimeException("A request handler has already been registered for request name [" + request.getName() + "]");
        } else {
            this.requestHandlers.put(request.getName(), requestHandler);
        }
    }
}
~~~

对于许多开发者来说，下一步将是七拼八凑地编写一个stub应用程序，使它能够与controller骨架相匹配。但是，作为深受测试影响的开发者，我们可以为controller编写一个测试集（test suite），而不必为一个stub应用程序手忙脚乱。这就是单元测试的魅力。

## 3.2 让我们来测试它

### 3.2.1 测试DefaultController

~~~java
[...]
import org.junit.core.Test;
import static org.junit.Assert.*;

public class TestDefaultController {
    private DefaultController controller;
    
    @Before
    public void instantiate() throws Exception {
        controller = new DefaultController();
    }
    
    @Test
    public void testMethod() {
        throw new RuntimeException("implement me");
    }
}
~~~

这里用到了一项“最佳实践”：对还没有实现的测试代码抛出一个异常。这种做法可以防止测试通过并且提醒自己必须实现这部分代码。既然你已经拥有了一个基本的测试，那么下一步就是判断先测试什么。

### 3.2.2 添加一个处理器

当务之急是：你应该测试是否能够添加一个RequestHandler。

针对第一个测试，看起来你可以进行以下操作：

- 添加一个RequestHandler，引用一个Request；
- 获取一个RequestHandler并传递同一个Request；
- 检查你获得的RequestHandler是否就是添加的那一个。

#### 测试从何而来

要创建一个单元测试，你需要两种类型的对象：一种是你要测试的**领域对象**（Domain Object），另一种是用来与被测对象交互的**测试对象**（Test Object）。

> **JUnit最佳实践：一次只能单元测试一个对象**
>
> 单元测试的一个至关重要的方面就是细粒度。一个单元测试独立地检查你创建的每一个对象，这样你就可以在问题发生的第一时间把它们隔离起来。如果被测试的对象多于一个，那么一旦这些对象发生了变化，你就无法预测它们将如何相互影响。当一个对象与其他复杂的对象交互时，你可以使用可预测的测试对象将被测试的对象包围起来。软件测试的另一种形式是集成测试，检查正在工作的对象彼此之间是如何交互的。更多关于其他类型的测试的内容，可以参考本书的第4章。

#### 测试类存在于何处

你把测试类放在什么地方呢？Java提供了几种选择。对于初学者来说，你可以进行以下操作之一：

- 把测试类作为包（package）中的公有类；
- 把测试类作为测试用例类的内部类。

如果这些类很简单并且以后也不太可能有很大改变，那么把它们编写成内部类是最简单的做法。在这个示例中的类都非常简单。代码3.5显示了你可以添加到TestDefaultController类中的内部类。

~~~java
public class TestDefaultController {
    [...]
    private class SampleRequest implements Request {
        public String getName() {
            return "Test";
        }
    }
    private class SampleHandler implements RequestHandler {
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }
    private class SampleResponse implements Response {
        // empty
    }
    [...]
}
~~~



~~~java
[...]
import static org.junit.Assert.*;

public class TestDefaultController {
    [...]
    @Test
    public void testAddHandler() {
        Request request = new SampleRequest();
        RequestHandler handler = new SampleHandler();
        controller.addHandler(request, handler);
        RequestHandler handler2 = controller.getHandler(request);
        assertSame("Handler we set in controller should be the same handler we get", handler2, handler);
    }
}
~~~

> **JUnit最佳实践：选择有意义的测试方法名字**
>
> 你可能看到，一个方法是带有@Test注释的测试方法。你也必须能够通过阅读一个方法的名字来了解这个方法是用来测试什么的。

随着你创建了更多这样的测试，你就会注意到你遵循着以下一个模式。

1. 通过把环境设置成已知状态（如创建对象，获取资源）来创建测试。测试前的状态通常称为Test Fixture。
2. 调用待测试的方法
3. 确认测试结果，通常通过调用一个或更多的assert方法来实现。

### 3.2.3 请求处理

~~~java
[...]
import static org.junit.Assert.*;

public class TestDefaultController {
    [...]
    @Test
    public void testProcessRequest() {
        Request request = new SampleRequest();
        RequestHandler handler = new SampleHandler();
        controller.addHandler(request, handler);
        Response response = controller.processRequest(request);
        assertNotNull("Must not return a null response", reponse);
        assertSame("Response should be of type SampleResponse", SampleResponse.class, response.class);
    }
}
~~~

> **JUnit最佳实践：在assert调用中解释失败的原因**
>
> 无论何时，只要你使用了JUnit的任何`assert*`方法，就要确保自己使用第一个参数为String类型的那个签名。这个参数让你可以提供一个有意义的文本描述，在断言失败时JUnit的test runner就会显示这个描述。如果不使用这个参数，那么当测试失败时，要找出失败的原因就会比较困难。

#### 分离初始化逻辑

因为testAddHandler和testProcessRequest这两个测试的初始化行为是一样的，所以你可以试着把这部分代码移进一个@Before注释的方法。

注意，你不要试图通过在一个测试方法中测试多个操作来共享初始化代码

> **JUnit最佳实践：一个单元测试等于一个@Test方法**
>
> 不要试图把多个测试塞进一个方法。这样导致的结果就是测试方法变得更加复杂，难以阅读也难以理解。更糟糕的是，你在测试方法中编写的逻辑越多，测试失败的可能性就越大，需要进行的调试可能性也就越大。

每一个测试方法都必须尽可能地简洁清晰和目的明确。这正是JUnit为你提供了@Before、@After、@BeforeClass和@AfterClass注释的原因，所以你可以在多个测试之间共享fixture，而不必把测试方法组合起来。

### 3.2.4 改进testProcessRequest

## 3.3 测试异常处理

> **JUnit最佳实践：测试任何可能失败的事物**
>
> 单元测试有助于确保你的方法遵守与其他方法的API契约。如果契约仅仅要求其他组件遵守，而没有对你的方法提出什么约束，那么就可能没有什么有意义的行为值得测试。但是，如果方法以任何方式改变了参数值或者字段值，那么这个方法就在提供独特的行为，这就需要测试。这个方法并不仅仅是个中间人——它是一个具有自己的行为的方法，可以想象，未来的改变可能会破坏它的行为。如果一个方法被改变了，变得不再简单，那么当发生这个改变时，你就应当为这个方法增加一个测试，但在改变之前则不需要增加。

### 3.3.1 模拟异常条件

你如何模拟异常条件以测试错误处理器是否正常工作呢？为了测试对正常请求的处理，你创建了一个SampleRequestHandler，这个处理器返回了一个SampleRequest。那么为了测试错误条件的处理，你可以创建一个SampleExceptionHandler，相反这个处理器会抛出一个异常。

> **JUnit最佳实践：让测试改善代码**
>
> 编写单元测试常常有助于你写出更好的代码。理由很简单：一个测试用例就是一位你的代码的用户。只有在使用代码时你才能发现代码的缺点。因此，不要犹豫，应当根据测试时发现的问题重构代码，使其更加易于使用。测试驱动开发（TDD）的实践就依赖于这条原则。提供先编写测试，你就可以从代码用户的角度来开发你的类。关于TDD的更多内容可以参见第5章。

### 3.3.2 测试异常

1. 插入应当抛出异常的语句；
2. 向@Test注释添加expected参数，来指定你所预期的异常类型；
3. 一切正常进行。

> **JUnit最佳实践：使异常测试更易于阅读**
>
> 通常@Test注释中的expected参数会明确告知开发者，应该产生什么类型的异常。但是你可以更进一步。除了以一种清晰易懂的方式命名你的测试方法，来表示这个方法要测试一个异常条件，你也可以向产生expected异常的代码行中添加一些代码注释，以突出显示。

> **JUnit最佳实践：让测试改善代码**
>
> 一种识别异常路径的简单方法是检查待测代码中的不同分支路径。这里的“分支路径”指的是if子句、switch语句和try/catch块的结果。当你开始跟踪这些分支时，你有时可能会觉得测试每个分支路径实在是太痛苦了。如果代码难以测试，那么通常也难以使用。

## 3.4 超时测试

JUnit为我们提供了@Test注释的另一个参数，称为timeout。在这个参数中，你能够指定以毫秒为单位的运行时间，并且如果测试执行超时，那么JUnit就会把这个测试标记为失败。

现在，对某些开发者来说，我们已经到了一些超时测试就可能使整个构建失败的地步。所以，有时候跳过一些测试是个好主意。在JUnit3.x中，我们为了跳过车市，就不得不修改测试方法的名称（使其不以test前缀为开头）。然而，在JUnit4.x版本中，我们有一个好方法来跳过测试。唯一需要我们做的是使用@Ignore注释来注释@Test方法，如代码3.16所示。

~~~java
[...]
@Test(timeout=130)
@Ignore(value="Ignore for new until we decide a decent time limit")
public void testProcessMultipleRequestTimeout() {
    [...]
}
~~~

正如你所看到的，我们唯一所做的是将@Ignore注释添加到方法中。这个注释接收了一个value参数，这个参数让我们插入一个信息，来说明我们为什么跳过这个测试。

> **JUnit最佳实践：总是为跳过测试说明原因**
>
> 正如你在以上代码中所看到的，我们说明了为什么需要跳过该测试的执行。在实际中这是一个很好的做法。首先，你向其他的开发成员说明了你为什么想跳过该测试的执行，其次，你也向自己证实了你知道这个测试是做什么的，而且你没有忽略它只是因为它失败了。

上文已经提到过，在JUnit3.x中，跳过测试方法的执行的唯一方式就是重命名方法或者把方法注释掉。这样你就无法知道有多少测试被跳过了。而在JUnit 4.x中，当你使用@Ignore注释方法时，你会获得详细的统计数据，除了通过和失败的测试数量，还包括JUnit跳过了多少测试。

## 3.5 引入Hamcrest匹配器

一些断言太大并且很难看懂。例如，来看一下代码3.17中的代码

~~~java
[...]
public class HamcrestTest {
    private List<String> values;
    
    @Before
    public void setUpList() {
        values = new ArrayList<>();
        values.add("x");
        values.add("y");
        values.add("z");
    }
    
    @Test
    public void testWithoutHamcrest() {
        assertTrue(values.contains("one")
                  || values.contains("two")
                  || values.contains("three"));
    }
}
~~~

Hamcrest是一个库，包含了大量有用的匹配器对象（也称为约束或者谓语），它可以被植入到其他几种开发语言（如Java、C++、Objective-C、Python和PHP）中。请注意，Hamcrest本身并不是一个测试框架，但确切地说，它可以帮助你通过声明方式指定简答的匹配规则。这些匹配规则可以在许多不同的情况下使用，但是它们尤其适用于单元测试。

代码3.18是同一个测试方法，但这一次是使用Hamcrest库编写的。

~~~java
[...]
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.JUnitMatchers.hasItem;
[...]

@Test
public void testWithHamcrest(){
    assertThat(values, hasItem(anyOf(equalTo("one"), equalTo("two"), equalTo("three"))));
}
[...]
~~~

Hamcrest能为你提供标准断言无法提供的功能，即在断言失败时提供一些可读的描述信息。

表3.2列出了一些最常用的Hamcrest匹配器。

| 核心                                                         | 逻辑                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| anything                                                     | 绝对匹配，在你想使得assert语句更具可读性的某些情况下非常有用 |
| is                                                           | 仅用于改善语句的可读性                                       |
| allOf                                                        | 检查是否与所有包含的匹配器匹配（相对于&&运算符）             |
| anyOf                                                        | 检查是否与任一包含的匹配器匹配（相对于\|\|运算符）           |
| not                                                          | 与包含的匹配器的意思相反（相当于Java中的 ! 运算符）          |
| instanceOf、isCompatibleType                                 | 匹配对象是否是兼容类型（是另一个对象的实例）                 |
| sameInstance                                                 | 测试对象标识                                                 |
| notNullValue、nullValue                                      | 测试null值（或者非null值）                                   |
| hasProperty                                                  | 测试JavaBean是否具有某种属性                                 |
| hasEntry、hasKey、hasValue                                   | 测试给定的Map是否含有某个给定的实体、键或者值                |
| hasItem、hasItems                                            | 测试给定的集合包含了一个或多个元素                           |
| closeTo、greaterThan、greaterThanOrEqual、lessThan、lessThanOrEqual | 测试给定的数字是否接近于、大于、大于或等于、小于、小于或等于某个给定的值 |
| equalToIgnoringCase                                          | 通过忽略大小写，测试给定的字符串是否与另一个字符串相同       |
| equalToIgnoringWhiteSpace                                    | 通过忽略空格，测试给定的字符串是否与另一个字符串相同         |
| containsString、endsWith、startWith                          | 测试给定的字符串是否包含了某个字符串，是否是以某个字符串开始或结束 |

最后值得一提的是，Hamcrest极具可扩展性。编写用来检查某个特定条件的匹配器是非常容易的。唯一你需要做的是实现Matcher接口和一个命名适当的工厂方法（Factory Method）。

## 3.6 创建测试项目

> **JUnit最佳实践：相同的包，分离的目录**
>
> 把所有测试类和待测试类都放在同一个包中，但采用平行目录结构。为了可以访问受保护的方法，你需要把测试和待测试类放在同一个包中。为了简化文件管理，并让人明确地区分测试类和领域类，你就需要把测试放在一个单独的目录中。

# 第4章 软件测试原则

## 4.1 单元测试的必要性

- 带来比功能测试更广范围的测试覆盖；
- 提高团队工作效率；
- 监测衰退，降低对调试的需要；
- 能为我们带来重构的信心，以及在一般情况下做出改变的信心；
- 改进实现；
- 将期望的行为文档化；
- 启用代码覆盖率以及其他指标。

### 4.1.1 带来更高的测试覆盖率

### 4.1.2 提供团队效率

### 4.1.3 检测衰退和减少调试

### 4.1.4 自信地重构

> **JUnit最佳实践：重构**
>
> 当你为一个独立的用例或者功能链设计和编写代码时，你的设计可能是符合这个特性的，但可能就不符合下一个特性了。为了对所有的特性都保持一致的设计，敏捷方法论提倡通过重构来根据要求调整个代码。
>
> 你如何才能保证重构或者改进现有代码的设计不会损坏现有的代码呢？答案就是单元测试，它会告诉你代码在何时何地崩溃了。简而言之，单元测试能给你重构的信息。

### 4.1.5 改进实现

### 4.1.6 将预期的行为文档化

### 4.1.7 启用代码覆盖率以及其他指标

## 4.2 测试类型

验收测试→压力负荷测试→功能测试→集成测试→单元测试

### 4.2.1 软件测试的4种类型

我们可以将软件测试分为4类：

- 集成测试
- 功能测试
- 压力与负荷测试
- 验收测试

#### 集成软件测试

单个的单元测试是软件质量控制的根本，但是，当几个不同的工作单元合并到一个工作流中的时候，将会发生什么情况呢？一旦你针对某个类创建了测试并且已经执行了，那么下一步就是要将这个类与其他方法与服务建立关系。检查不同组件（可能此时它们正运行在目标环境中）之间的相互影响，这其实就是进行集成测试。

#### 功能软件测试

功能测试检查公共API边界处的代码。通常情况下，这相当于测试应用程序用例。

#### 压力测试

大多数的压力测试用来检查应用程序能否在某个指定的时间段内响应大量的用户请求。

#### 验收软件测试

验收测试就是我们最终级别的测试。通常由客户或者客户代理人进行验收测试，以确保应用程序已经满足了客户或者利益相关者所提出的任何目标。

### 4.2.2 单元测试的3种类型

编写单元测试与编写生产代码同时进行，可以确保你的应用程序从一开始就处于测试中。我们鼓励这样的流程，同时敦促程序员使用他们对实现的理解，来创建和维护可以在构建中自动运行的单元测试。使用你对实现细节的理解来编写测试也被称为**白盒测试**（white box testing）。

表4.3 总结了3种不同类型的单元测试。

| 测试类型     | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| 逻辑单元测试 | 这种单元测试主要针对一个单独的方法来检查代码，你可以通过mock objects或者stub来控制某个特定的测试方法的边界（参见本书的第2部分） |
| 集成单元测试 | 这种单元测试主要用来测试在真实环境（或者真实环境的一部分）中的不同组件之间的相互作用。例如，测试一段访问某个数据库的代码是否真正有效地调用了数据库（参见第16章与第17章） |
| 功能单元测试 | 这种单元测试已经超出了集成单元测试的边界，目的是为了确认了一个刺激响应。 |

严格来讲，功能单元测试并不是纯粹的单元测试，也不是纯粹的功能测试。它们相对于纯粹的单元测试而言，要更多地依赖于外部环境，但是它们又不像纯粹的功能测试那样会测试一个完整的工作流。我们之所以把功能单元测试放入我们的讨论范围内，是因为它们通常在开发环境中作为一系列测试中一部分是非常有用的。

## 4.3 黑盒测试与白盒测试

> **DEFINITION**
>
> **黑盒测试**（black box test）——一个黑盒测试不知道系统内部的状态与行为。测试仅仅依赖于系统外部接口来验证它的正确性

# 第2部分 不同的测试策略

# 第5章 测试覆盖率与开发

