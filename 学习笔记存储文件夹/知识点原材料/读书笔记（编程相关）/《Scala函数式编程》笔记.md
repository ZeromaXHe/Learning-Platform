# 推荐序1

另一个对我代码品味影响很大的技术是Lua中的协程（coroutine）。Lua的作者Roberto Ierusalimschy把协程称为“单趟延续执行流”（One-shot continuation）。有了协程或延续执行流，程序员可以手动切换执行流，不再需要编写事件回调函数，而可以编写直接命令式风格代码但不阻塞真正的线程。

而在其他不支持协程或者延续执行流的语言中，程序员需要非阻塞或异步编程时，就必须采用层层嵌套回调函数的CPS(Continuation-Passing Style)风格。这种风格在逻辑复杂时，会陷入“回调地狱”（Callback Hell）的陷阱，使得代码很难读懂，维护起来很难。

Scala语言本书并不支持协程或者延续执行流。

# 第一部分 函数式编程介绍

我们以一个激进的前提开始读这本书——限制自己只用纯函数来构造程序，纯函数是没有副作用的，比如读取文件或修改内存时。这种函数式编程的理念，或许非常不同于你以往的编程方式。因此，我们从头开始，重新学习如何用函数式风格来写一些简单的程序。

# 1 什么是函数式编程

函数式编程（FP）基于一个简单又蕴意深远的前提：只用**纯函数**来构造程序——换句话说，函数没有**副作用**（side effects）。什么是副作用？一个带有副作用的函数不仅只是简单地返回一个值，还干了一些其他事情，比如：

- 修改一个变量
- 直接修改数据结构
- 设置一个对象的成员
- 抛出一个异常或以一个错误停止
- 打印到终端或读取用户的输入
- 读取或写入一个文件
- 在屏幕上绘画

函数式编程限制的是**怎样**写程序，而非表达**什么样**的程序。通过本书我们将学习到如何没有副作用地表达我们的程序，包括执行I/O、处理错误、修改数据。我们将学习到为什么遵循函数式编程的规范是极其有益的，因为用纯函数编程更加**模块化**。由于纯函数的模块化特性，它们很容易被测试、复用、并行化、泛化以及推导。此外，纯函数减少了产生bug的可能性。

## 1.1 函数式编程的好处：一个简单的例子

### 1.1.1 一段带有副作用的程序

假设我们要为一家咖啡店的购物编写一段程序，先用一段带有副作用的Scala程序（也称作**不纯的**的程序）来实现。

~~~scala
// 类似于java，关键字class表示一个类，类体用大括号包围起来。
class Cafe { 
    // 关键字def表示一个方法。
    // cc:CreditCard定义了参数名cc和类型CreditCard。
    // 在参数列表之后的Coffee是buyCoffee方法的返回值类型。
    // 等号之后的花括号里的代码块是方法体。
    def buyCoffee(cc: CreditCard): Coffee = { 
        // 不需要分号，语句之间通过换行符来界定。
        val cup = new Coffee()
        // 副作用。信用卡计费。
        cc.charge(cup.price)
        // 我们不需要声明return，因为cup是最后一条语句，会自动return。
        cup
    }
}
~~~

cc.charge(cup.price)这行是一个副作用的例子。信用卡的计费涉及与外部世界的一些交互——假设需要通过web service联系信用卡公司、授权交易、对卡片计费，并且（如果前边执行成功）持久化一些记录以便以后引用。我们的函数只不过返回一杯咖啡，这些其他行为也**额外**（on the side）发生了，因此也被称为“副作用”（我们将在本章后边正式定义副作用）。

副作用导致这段代码很难测试。我们不希望测试逻辑真的去联系信用卡公司并对卡片计费。缺乏可测试性预示着设计的修改：按理说CreditCard不应该知道如何联系信用卡实际执行一次计费，同样也不应该知道怎么把一次计费持久化到内部系统。我们可以让CreditCard忽略掉这些事情，通过传递一个Payments对象给buyCoffee函数，使代码更加模块化和可测化。

~~~scala
class Cafe {
    def buyCoffee(cc: CreditCard, p:Payments): Coffee = {
        val cup = new Coffee()
        p.charge(cc, cup.price)
        cup
    }
}
~~~

虽然当我们调用p.charge(cc, cup.price)的时候仍然有副作用发生，但至少恢复了一些可测试性。Payments可以是一个接口，我们可以写一个适合于测试的mock实现这个接口。但这也不够理想，即便用一个具体类可能更好，我们也不得不让Payments成为一个接口，否则，任何mock都很难被使用。举个例子，在调用完buyCoffee之后或许我们要检测一些内部状态，我们的测试不得不保证这些状态在调用charge后已经被适当修改。虽然可以使用一个mock框架或相似的东西来为我们处理这些细节，但这样有些小题大做了，我们只不过想测试一下buyCoffee产生的计费是否等于这杯咖啡的价格而已。

撇开对测试的担心，这里还有另一个问题：buyCoffee方法很难被复用。假设一个叫Alice的顾客，要订购12杯咖啡。最理想的情况是只要复用这个方法，通过循环来调用12次buyCoffee。但是基于当前的程序，会陷入12次对支付系统的调用，对Alice的信用卡执行12次计费！

### 1.1.2 函数式的解法：去除副作用

函数式的解法是消除副作用，通过让buyCoffee方法在返回咖啡（Coffee）的时候把费用（Charge）也作为值一并返回。计费的处理包括发送到信用卡公司、持久化这条记录等，这些过程将在其他地方来做。下面是这段函数式解法的大致样子（先别太注重Scala的语法，下一章会详细讲解）：

~~~scala
class Cafe {
    // buyCoffee方法返回一对儿包含Coffee和Charge的值，使用类型(Coffee, Charge)来表示。这里不涉及支付相关的任何处理。
    def buyCoffee(cc:CreditCard) : (Coffee, Charge) = {
        val cup = new Coffee()
        // 用小括号创建一个cup和Charge的数据“对”，之间用逗号间隔。
        (cup, Charge(cc, cup.price))
    }
}
~~~

我们把费用的创建过程跟它的执行过程分离。buyCoffee函数现在的返回值里除了Coffee还有一个费用的值。我们很快就会看到如何更方便地在一个事务里复用这个函数来买多杯咖啡。不过先看看“Charge”怎么定义？我们所构造的这个数据类型由信用卡（CreditCard）和金额（amount）组成，并提供了一个combine函数，以便对同一张信用卡合并费用：

~~~scala
// case类只有一个主构造器，构造参数紧跟着类名后边（这里类名是Charge）。
// 这个列表中参数都是public的、不可修改的，访问时使用面向对象的方式，中间用点来标注，如other.cc。
case class Charge(cc:CreditCard, amount:Double) {
    def combine(other:Charge): Charge = 
    	// if表达式跟java里的语法一样，但它也会返回其中一个分支结果的值。
    	// 如果 cc == other.cc，那么combine将返回Charge(..);
    	// 否则在else分支会抛出异常。
    	if (cc == other.cc) 
    		// case类可以不通过new关键字创建。
    		// 我们只需要在类名后面传递一组参数到主构造器。
    		Charge(cc, amount + other.amount)
    	else
    		// 抛出异常的语法也和Java或其他语言相似。
    		// 我们在后续章节会讨论更多用函数式来处理错误条件的方法。
    		throw new Exception("Can't combine charges to different cards")
}
~~~

现在我们来看看buyCoffees方法如何实现购买n杯咖啡。与之前不同，现在可以利用buyCoffee方法实现，这正是我们所希望的。

~~~scala
class Cafe {
    def buyCoffee(cc:CreditCard): (Coffee, Charge) = ...
    // List[Coffee]是一个承载Coffee的不可变的单向链表。
    // 我们将在第3章讨论数据类型的更多细节。
    def buyCoffees(cc:CreditCard, n:Int): (List[Coffee], Charge) = {
        // List.fill(n)(x)创建一个对x复制n份的列表。
        // 我们将在后续章节解释这个有趣的函数调用语法。
        val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
        // unzip将数值对儿列表，分成一对儿（pair）列表。
        // 这里我们用一行代码对这个pair解构成2个值（coffee列表和charge列表）
        // charges.reduce对整个charge列表规约成一个charge，每次使用combine来组合两个charge。
        // reduce是一个高阶函数的例子，我们将在下一章做适当的介绍。
        val (coffees, charges) = purchases.unzip(coffees, charges.reduce((c1, c2) => c1.combine(c2)))
    }
}
~~~

总体上看，这个解决方案有显著的改善，现在我们可以直接复用buyCoffee来定义buyCoffee函数，这两个函数都很简单并容易测试，不需要实现一些Payments接口来进行复杂的mock！事实上，Cafe现在完全忽略了计费是如何处理的。当然我们可以用一个Payments类来做付款处理，但Cafe并不需要了解它。

让Charge成为一等（first-class）值，还有一些我们没有预期到的好处：我们能更容易地组装业务逻辑。因为Charge是一等值，我们可以用下面的函数把同一张信用卡的费用合并为一个List[Charge]:

~~~scala
def coalesce(charges: List[Charge]): List[Charge] = 
	charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
~~~

我们像传值一样传递函数给groupBy、map和reduce方法。在接下来的几章，你将学会读和写类似这样的一行程序。`_.cc` 和`_ combine _`是匿名函数的语法，在后边的章节会讲到。

> **现实世界是如何的？**
>
> 对函数式程序员而言，程序的实现，应该有一个纯的内核和一层很薄的外围来处理副作用。
>
> 但即便如此，某些时候我们不得不面对现实情况产生的副作用，并通过一些外部系统提交费用处理。通过本书，我们将发现很多看似必须存在副作用的程序都有对应的函数式实现。如果不存在对应的函数式实现，我们会找到一种方式构造代码让副作用发生但不可见（例如，可以在一些函数体里声明局部变量，或者当没有外围函数可以观察到这种情况时，写入一个文件）。

## 1.2 （纯）函数究竟是什么