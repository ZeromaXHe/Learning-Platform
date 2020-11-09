# Effective Java（第三版）笔记

## 第1章 引言

Java语言支持四种类型：接口（包括注释）、类（包括enum）、数组和基本类型。前三种类型通常被称为引用类型（reference type），类实例和数组是对象（object），而基本类型的值不是对象。

本书不再使用“接口继承”这种说法，而是简单地说，一个类实现（implement）了一个接口，或者一个接口扩展（extend）了另一个接口。

## 第2章 创建和销毁对象

### 第1条 用静态工厂方法代替构造器

对于类而言，为了让客户端获取它自身的一个实例，最传统的方法就是提供一个公有的构造器。还有一种方法，也应该在每个程序员的工具箱中占有一席之地。类可以提供一个共有的静态工厂方法（static factory method），它只是一个返回类的实例的静态方法。下面是一个来自Boolean（基本类型boolean的装箱类）的简单示例。这个方法将boolean基本类型值转换成了一个Boolean对象引用：

~~~java
public static Boolean valueOf(boolean b){
    return b? Boolean.TRUE: Boolean.FALSE;
}
~~~

注意，静态工厂方法与设计模式中的工厂方法（Factory Method）模式不同。本条目中所指的静态工厂方法并不直接对应于设计模式（Pattern Design）中的工厂方法。

如果不通过共有的构造器，或者说除了共有的构造器外，类还可以给它的客户端提供静态工厂方法。提供静态工厂方法而不是公有的构造器，这样做既有优势，也有劣势。

**静态工厂方法与构造器不同的第一大优势在于，它们有名称。** 如果构造器的参数本身没有确切地描述正被返回的对象，那么具有恰当名称的静态工厂会更容易使用，产生的客户端代码也更易阅读。例如，构造器BigInteger(int, int, Random)返回的BigInteger可能为素数，如果用名叫BigInteger.probablePrime的静态工厂方法来表示，显然更为清楚。（Java4版本中增加了这个方法。）

一个类只能有一个带有指定签名的构造器。编程人员通常知道如何避开这一限制：通过提供两个构造器，它们的参数列表只在参数类型的顺序上有所不同。实际上这并不是个好主意。面对这样的API，用户永远也记不住该用哪个构造器，结果常常会调用错误的构造器。并且在读到使用了这些构造器的代码时，如果没有参考类的文档，往往不知所云。

由于静态工厂方法有名称，所以它们不受上述限制。当一个类需要多个带有相同签名的构造器时，就用静态工厂方法代替构造器，并且仔细地选择名称以便突出静态工厂方法之间的区别。

**静态工厂方法与构造器不同的第二大优势在于，不必在每次调用它们的时候都创建一个新对象。** 这使得不可变类（详见第17条）可以使用预先构建好的实例，或者将构建好的实例缓存起来，进行重复利用，从而避免创建不必要的重复对象。Boolean.valueOf(boolean)方法说明了这项技术：它从来不创建对象。这种方法类似于享元（Flyweight）模式。如果程序经常请求创建相同的对象，并且创建对象的代价很高，则这项技术可以极大地提升性能。

静态工厂方法能够为重复的调用返回相同对象，这样有助于类总能严格控制在某个时刻哪些实例应该存在。这种类被称作*实例受控的类*（instance-controlled）。编写实例受控的类有几个原因。实例受控使得类可以确保它是一个Singleton（详见第3条）或者是不可实例化的（详见第4条）。它还使得不可变的值类（详见第17条）可以确保不会存在两个相等的实例，即当且仅当a==b时，a.equals(b)才为true。这是*享元*模式的基础。枚举（enum）类型（详见第34条）保证了这一点。

**静态工厂方法与构造器不同的第三大优势在于，它们可以返回原返回类型的任何子类型的对象。** 这样我们在选择返回对象的类时就有了更大的灵活性。

这种灵活性的一种应用是，API可以返回对象，同时又不会使对象的类变成公有的。以这种方式隐藏实现类会使API变得非常简洁。这项技术适用于*基于接口的框架*（interface based framework）（详见第20条），因为在这种框架中，接口为静态工厂方法提供了自然返回类型。

在Java 8之前，接口不能有静态方法，因此按照惯例，接口Type的静态工厂方法被放在一个名为Types的*不可实例化的伴生类*（详见第4条）中。例如，Java Collections Framework的集合接口有45个工具实现，分别提供了不可修改的集合、同步集合，等等。几乎所有这些实现都通过静态工厂方法在一个不可实例化的类（java.util.Collections）中导出。所有返回对象的类都是非共有的。

现在的Collections Framework API比导出45个独立共有类的那种实现方式要小得多，每种便利实现都对应一个类。这不仅仅是指API数量上的减少，也是*概念意义*上的减少：为了使用这个API，用户必须掌握的概念在数量和难度上都减少了。程序员知道，被返回的对象是由相关的接口精确指定的，所以他们不需要阅读有关的类文档。此外，使用这种静态工厂方法时，甚至要求客户端通过接口来引用被返回的对象，而不是通过它的实现类来引用被返回的对象，这是一种良好的习惯（详见64条）。

从Java 8版本开始，接口中不能包含静态方法的这一限制成为历史，因此一般没有任何理由给接口提供一个不可实例化的伴生类。已经被放在这种类中的许多公有的静态成员，应该被放到接口中去。但是要注意，仍然有必要将这些静态方法背后的大部分实现代码，单独放进一个包级私有的类中。这是因为在Java 8中仍要求接口的所有静态成员都必须是公有的。在Java 9中允许接口有私有的静态方法，但是静态域和静态成员类仍然需要是公有的。

**静态工厂的第四大优势在于，所返回的对象的类可以随着每次调用而发生变化，这取决于静态工厂方法的参数值。** 只要是已声明的返回类型的子类型，都是允许的。返回对象的类也可能随着发行版本的不同而不同。

EnumSet（详见第36条）没有公有的构造器，只有静态工厂方法。在OpenJDK实现中，它们返回两种子类之一的一个实例，具体则取决于底层枚举类型的大小：如果它的元素有64个或者更少，就像大多数枚举类型一样，静态工厂方法就会返回一个RegularEnumSet实例，用单个long进行支持；如果枚举类型有65个或者更多元素，工厂就返回JumboEnumSet实例，用一个long数组进行支持。

这两个实现类的存在对于客户端来说是不可见的。如果RegularEnumSet不能再给小的枚举类型提供性能优势，就可能从未来的发行版本中将它删除，不会造成任何负面的影响。同样地，如果事实证明对性能有好处，也可能在未来的发行版本中添加第三甚至第四个EnumSet实现。客户端永远不知道也不关心它们从工厂方法中得到的对象的类，它们只关心它是EnumSet的某个子类。

**静态工厂的第五大优势在于，方法返回的对象所属的类，在编写包含该静态工厂方法的类时可以不存在。** 这种灵活的静态工厂方法构成了*服务提供者框架*（Service Provider Framework）的基础，例如JDBC（Java数据库连接）API。服务提供者框架是指这样一个系统：多个服务提供者实现一个服务，系统为服务提供者的客户端提供多个实现，并把它们从多个实现中解耦出来。

服务提供者框架中有三个重要的组件：*服务接口*（Service Interface），这是提供者实现的；*提供者注册API*（Provider Registration API），这是提供者用来注册实现的；*服务访问API*（Service Access API），这是客户端用来获取服务的实例。服务访问API是客户端用来指定某种选择实现的条件。如果没有这样的规定，API就会返回默认实现的一个实例，或者允许客户端遍历所有可用的实现。服务访问API是“灵活的静态工厂”，它构成了服务提供者框架的基础。

服务提供者框架的第四个组件*服务提供者接口*（Service Provider Interface）是可选的，它表示产生服务接口之实例的工厂对象。如果没有服务提供者接口，实现就通过反射方式进行实例化（详见第65条）。对于JDBC来说，Connection就是其服务接口的一部分，DriverManager.registerDriver是提供者注册API，DriverManager.getConnection是服务访问API，Driver是服务提供者接口。

服务提供者框架模式有着无数种变体。例如，服务访问API可以返回比提供者需要的更丰富的服务接口。这就是*桥接*（Bridge）模式。依赖注入框架（详见第5条）可以被看做是一个强大的服务提供者。从Java 6版本开始，Java平台就提供了一个通用的服务提供者框架java.util.ServiceLoader，因此你不需要（一般来说也不应该）再自己编写了（详见第59条）。JDBC不用ServiceLoader，因此前者出现得比后者早。

**静态工厂方法的主要缺点在于，类如果不含公有的或者受保护的构造器，就不能被子类化。** 例如，要想将Collections Framework中的任何便利的实现类子类化，这是不可能的。但是这样也许会因祸得福，因为它鼓励程序员使用复合（composition），而不是继承（详见第18条），这正是不可变类型所需要的（详见第17条）。

**静态工厂方法的第二个缺点在于，程序员很难发现它们。** 在API文档中，它们没有像构造器那样在API文档中明确标识出来，因此，对于提供了静态工厂方法而不是构建器的类来说，要项查明如何实例化一个类是非常困难的。Javadoc工具总有一天会注意到静态工厂方法。同时，通过在类或者接口注释中关注静态工厂，并遵守标准的命名习惯，也可以弥补这一劣势。下面是静态工厂方法的一些惯用名称。这里只列出了其中的一小部分：

- from——类型转换方法，它只有单个参数，返回该类型的一个相对应得实例，例如：`Date d = Date.from(instant);`
- of——聚合方法，带有多个参数，返回该类型得一个实例，把它们合并起来，例如：`Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);`
- valueOf——比from和of更烦琐得一种替代方法，例如：`BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);`
- instance或者getInstance——返回得实例是通过方法的（如有）参数来描述的，但是不能说与参数具有同样的值，例如：`StackWalker luke = StackWalker.getInstance(options);`
- create或者newInstance——像instance或者getInstance一样，但create或者newInstance能够确保每次调用都返回一个新的实例，例如：`Object newArray = Array.newInstance(classObject, arrayLen);`
- getType——像getInstance一样，但是在工厂方法处于不同的类中的时候使用。Type表示工厂方法所返回的对象类型，例如：`FileStore fs = Files.getFileStore(path);`
- newType——像newInstance一样，但是在工厂方法处于不同的类中的时候使用。Type表示工厂方法所返回的对象类型，例如：`BufferedReader br = Files.newBufferedReader(path);`
- type——getType和newType的简版，例如：`List<Complaint> litany = Collections.list(legacyLitany);`

简而言之，静态工厂方法和公有构造器都各有用处，我们需要理解它们各自的长处。静态工厂经常更加合适，因此切忌第一反应就是提供公有的构造器，而不先考虑静态工厂。

### 第2条：遇到多个构造器参数时要考虑使用构建器

静态工厂和构造器有个共同的局限性：它们都不能很好地扩展到大量的可选参数。比如用一个类表示包装食品外面显示的营养成分标签。这些标签中有几个域是必需的：每份的含量、每罐的含量以及每份的卡路里。还有超过20个的可选域：总脂肪量、饱和脂肪量、转化脂肪、胆固醇、钠，等等。大多数产品在某几个可选域中都会有非零的值。

对于这样的类，应该用哪种构造器或者静态工厂来编写呢？程序员一向习惯采用*重叠构造器*（ telescoping constructor ）模式，在这种模式下，提供的第一个构造器只有必要的参数，第二个构造器有一个可选参数，第三个构造器有两个可选参数，依此类推，最后一个构造器含所有可选的参数。下面有个示例，为了简单起见，它只显示四个可选域：

~~~java
// Telescoping constructor pattern - does not scale well! 
public class NutritionFacts { 
    private final int servingSize; // (mL〕required
    private final int servings; // (per container）required
    private final int calories; // (per serving) optional
    private final int fat; // (g/serving) optional 
    private final int sodium; // (mg/serving) optional
    private final int carbohydrate; // (g/serving) optional 
    public NutritionFacts (int servingSize int servings) { 
        this (servingSize, servings, 0); 
    }
    public NutritionFacts(int servingSize, int servings, int calories) { 
        this(servingSize, servings, calories, 0) ; 
    }
    public NutritionFacts(int servingSize, int servings, int calories, int fat) { 
        this(servingSize, servings, calories, fat , 0); 
    }
    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) { 
        this(servingSize, servings, calories, fat, sodium, 0);
    }
    public NutritionFacts(int servingSize, int servings, int calories , int fat, int sodium, int carbohydrate) { 
        this.servingSize = servingSize;
        this.servings = servings;
        this calories = calories; 
        this.fat = fat; 
        this.sodium = sodium; 
        this.carbohydrate = carbohydrate;
    }
}
~~~

当你想要创建实例的时候，就利用参数列表最短的构造器，但该列表中包含了要设置的所有参数

这个构造器调用通常需要许多你本不想设置的参数，但还是不得不为它们传递值这个例子中，我们给 fat 传递了一个值为0,如果“仅仅” 是这6个参数，看起来还不算太糟糕，问题是随着参数数目的增加，它很快就失去了控制。

**简而言之，重叠构造器模式可行，但是当有许多参数的时候，客户端代码会很难缩写，并且仍然较难以阅读**。如果读者想知道那些值是什么意思，必须很仔细地数着这些参数来探个究竟。一长串类型相同的参数会导致一些微妙的错误。如果客户端不小心颠倒了其中两个参数的顺序，编译器也不会出错，但是程序在运行时会出现错误的行为（详见第 51 条）。

遇到许多可选的构造器参数的时候，还有第二种代替办法，即 JavaBeans 模式，在这种模式下，先调用一个无参构造器来创建对象，然后再调用 setter 方法来设置每个必要的参数，以及每个相关的可选参数：
~~~java
// JavaBeans Pattern - allows inconsistency, mandates mutability 
public class NutritionFacts { 
    // Parameters initialized to default values (if any)
    private int servingSize = - 1 ; // Required; no default value
    private int servings = -1; // Required; no default value 
    private int calories = 0 ; 
    private int fat = 0; 
    private int sodium = 0; 
    private int carbohydrate = 0; 

    public NutriitionFacts() { } 
    // Setters
    public void setServingSize(int val) { servingSize = val; } 
    public void setServings(int val ) { servings = val; } 
    public void setCalories(int val ) { calories = val; } 
    public void setFat(int val) { fat = val;} 
    public void setSodium(int val) { sodium = val; } 
    public void setCarbohydrate(int val) { carbohydrate = val; } 
}
~~~
这种模式弥补了重叠构造器模式的不足。说得明白一点，就是创建实例很容易，这样产生的代码读起来也很容易：
~~~java
NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
cocaCola.setCalories(100);
cocaCola.setSodium(35);
cocaCola.setCarbohydrate(27);
~~~

遗憾的是， JavaBeans 模式自身有着很严重的缺点。因为构造过程被分到了几个调用中，**在构造过程中 JavaBean 可能处于不一致的状态**。类无法仅仅通过检验构造器参数的有效性来保证一致性。试图使用处于不一致状态的对象将会导致失败，这种失败与包含错误的代码大相径庭，因此调试起来十分困难。与此相关的另一点不足在于， **JavaBeans 模式使得把类做成不可变的可能性不复存在** （详见第 17 条），这就需要程序员付出额外的努力来确保它的线程安全。

当对象的构造完成，并且不允许在冻结之前使用时，通过手工“冻结”对象可以弥补这些不足，但是这种方式十分笨拙，在实践中很少使用。此外，它甚至会在运行时导致错误，因为编译器无法确保程序员会在使用之前先调用对象上的 freeze 方法进行冻结。

幸运的是，还有第三种替代方法，它既能保证像重叠构造器模式那样的安全性，也能保证像 JavaBeans 模式那么好的可读性。这就是*建造者*（Builder）模式的一种形式。它不直接生成想要的对象，而是让客户端利用所有必要的参数调用构造器（或者静态工厂），得到一个 builder 对象 然后客户端在 builder 对象上调用类似于setter的方法，来设置每个相关的可选参数。最后 客户端调用无参的 build 方法来生成通常是不可变的对象。这个 builder 通常是它构建的类的静态成员类（详见第 24 条） 下面就是它的示例：
~~~java
// Builder Pattern
public class NutritionFacts { 
    private final int servingSize; 
    private final int servings;
    private final int calories; 
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder{
        // Required parameters
        private final int servingSize; 
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories = 0 ; 
        private int fat = 0; 
        private int sodium = 0; 
        private int carbohydrate = 0; 

        public Builder(int servingSize, int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val){
            colories = val;
            return this;
        }
        public Builder fat(int val){
            fat = val;
            return this;
        }
        public Builder sodium(int val){
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
~~~

注意 NutritionFacts 是不可变的，所有的默认参数值都单独放在一个 builder 的设值方法返回 builder 本身，以便把调用链接起来，得到一个流式的 API。下面就是其客户端代码：
~~~java
NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
    .calories(100).sodium(35).carbohydrate(27).build();
~~~

这样的客户端代码很容易编写，更为重要的是易于阅读。**Builder模式模拟了具名的可选参数**，就像 Python 和 Scala 编程语言中的一样。

为了简洁起见，示例中省略了有效性检查。 要想尽快侦测到无效的参数， 可以在 builder 构造器和方法中检查参数的有效性。查看不可变量，包括 build 方法调用的构造器中的多个参数。为了确保这些不变量免受攻击，从 builder 复制完参数之后，要检查对象域（详见第 50 条）。如果检查失败，就抛出 IllegalArgumentException（详见第72条），其中的详细信息会说明哪些参数是无效的（详见第 75 条）。

**Builder 模式也适用于类层次结构**。使用平行层次结构的 builder 时， 各自嵌套在相应的类中。抽象类有抽象的 builder ，具体类有具体的 builder。假设用类层次根部的一个抽象类表示各式各样的比萨：
~~~java
// Builder pattern for class hierarchies
public abstract class Pizza { 
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE } 
    final Set<Topping> toppings; 
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings= EnumSet.noneOf(Topping.class); 
        public T addTopping(Topping topping) { 
            toppings.add(Objects.requieNonNull(topping)); 
            return self();
        } 
        abstact Pizza build() ; 
        // Subclasses must override this method to return ” this" 
        protected abstract T self(); 
    } 
    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone(); // See Item 50
    }
}
~~~
注意， Pizza Builder 型是*泛型*（generic type ），带有一个*递归类型参数*（recursive type parameter ），详见第 30 条。它和抽象的 self 方法一样，允许在子类中适当地进行方法链接，不需要转换类型。这个针对 Java 缺乏 self 型的解决方案，被称作模拟的 self 类型（ simulated self-type)。

这里有两个具体的 Pizza 子类，其中一个表示经典纽约风味的比萨，另一个表示馅料内置的半月型（ calzone ）比萨。前者需要一个尺寸参数，后者则要你指定酱汁应该内置还是外置：

~~~java
public class NyPizza extends Pizza { 
    public enum Size { SMALL, MEDIUM, LARGE } 
    private final Size size;
 
    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size; 

        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }
        @Override public NyPizza build() { 
            return new NyPizza(this); 
        }
        @Override protected Builder self() { return this; } 
    }
    private NyPizza(Builder builder){
        super(builder);
        size = builder.size;
    }
}
public class Calzone extends Pizza { 
    private final boolean sauceInside; 
    public static class Builder extends Pizza.Builder<Builder>{
        private boolean sauceInside = false; // Default 
    
        public Builde sauceInside() { 
            sauceInside = true;
            return this; 
        }
        @Override public Calzone build() { 
            return new Calzone(this);
        }
        @Override protected Builder self(){ return this; } 
    }

    private Calzone(Builder builder){
        super(builder);
        sauceInside = builde.sauceInside;
    }
}
~~~

注意，每个子类的构建器中的 build 方法，都声明返回正确的子类： NyPizza.Builder 的 build 方法返回 NyPizza ，而 Calzone.Builder 中的则返回 Calzone。在该方法中，子类方法声明返回超级类中声明的返回类型的子类型，这被称作*协变返回类型*(covariant return type)。它允许客户端无须转换类型就能使用这些构建器。

这些“层次化构建器”的客户端代码本质上与简单的 NutritionFacts构建器一样。为了简洁起见，下列客户端代码示例假设是在枚举常量上静态导人：
~~~java
NyPizza pizza = new NyPizza.Builder(SMALL)
    .addTopping(SAUSAGE).addTopping(ONION).build(); 
Calzone calzone = new Calzone.Builder()
    .addTopping(HAM).sauceInside().build();
~~~
与构造器相比，builder的微略优势在于，它可以有多个可变（varargs）参数。因为 builder 是利用单独的方法来设置每一个参数。此外，构造器还可以将多次调用某一个方法而传人的参数集中到一个域中，如前面的调用了两次 addTopping 方法的代码所示。

Builder 模式十分灵活，可以利用单个 builder 构建多个对象。build 的参数可以在调用 build 方法来创建对象期间进行调整，也可以随着不同的对象而改变 builder 可以自动填充某些域，例如每次创建对象时自动增加序列号。

Builder 模式的确也有它自身的不足。为了创建对象，必须先创建它的构建器。虽然创建这个构建器的开销在实践中可能不那么明显，但是在某些十分注重性能的情况下，可能就成问题了。Builder 模式还比重叠构造器模式更加冗长，因此它只在有很多参数的时候才使用，比如4个或者更多个参数。但是记住，将来你可能需要添加参数。如果一开始就使用构造器或者静态工厂，等到类需要多个参数时才添加构造器，就会无法控制，那些过时的构造器或者静态工厂显得十分不协调。因此，通常最好一开始就使用构建器。

简而言之，**如果类的构造器或者静态工厂中具有多个参数，设计这种类时， Builder模式就是一种不错的选择**，特别是当大多数参数都是可选或者类型相同的时候。与使用重叠构造器模式相比，使用 Builder 模式的客户端代码将更易于阅读和编写，构建器也比 JavaBeans 更加安全。

### 第3条：用私有构造器或者枚举类型强化Singleton属性

Singleton 是指仅仅被实例化一次的类。Singleton通常被用来代表一个无状态的对象，如函数（详见第24条），或者那些本质上唯一的系统组件。**使类称为Singleton会使它的客户端测试变得十分困难**，因为不可能给Singleton替换模拟实现，除非实现一个充当其类型的接口。

实现 Singleton 有两种常见的方法。这两种方法都要保持构造器为私有的，并导出公有的静态成员，以便允许客户端能够访问该类的唯一实例。在第一种方法中，公有静态成员是个final域：
~~~java
// Singleton with public final field 
public class Elvis {
    public static final Elvis INSTANCE= new Elvis(); 
    private Elvis() { ... } 

    public void leaveTheBuilding() { ... }
}
~~~

私有构造器仅被调用一次，用来实例化公有的静态 final 域 Elvis.INSTANCE 由于缺少公有的或者受保护的构造器，所以保证了 Elvis 的全局唯一性; 一旦 Elvis 类被实例化，将只会存在一个 Elvis 实例，不多也不少。客户端的任何行为都不会改变这一点，但要提醒一点：享有特权的客户端可以借助 AccessibleObject.setAccessible 方法，通过反射机制（详见第 65 条）调用私有构造器。如果需要抵御这种攻击，可以修改构造器，让它在被要求创建第二个实例的时候抛出异常。

在实现 Singleton 的第二种方法中，公有的成员是个静态工厂方法：
~~~java
// Singleton with static factory
public class Elvis { 
    private static final Elvis INSTANCE = new Elvis(); 
    private Elvis() { ... } 
    public static Elvis getInstance() { return INSTANCE; } 

    public void leaveTheBuilding() { ... }
}
~~~

对于静态方法 Elvis.getInstance 的所有调用，都会返回同一个对象引用，所以，永远不会创建其 Elvis 实例（上述提醒依然适用）。

公有域方法的主要优势在于， API 很清楚地表明了这个类是一个 Singleton；公有的静态域是 final 的，所以该域总是包含相同的对象引用。第二个优势在于它更简单。

静态工厂方法的优势之一在于，它提供了灵活性：在不改变其 API 的前提下，我们可以改变该类是否应该为 Singleton 的想法。工厂方法返回该类的唯一实例，但是，它很容易被修改，比如改成为每个调用该方法的线程返回一个唯一的实例。第二个优势是，如果应用程序需要，可以编写一个*泛型 Singleton 工厂*(generic singleton factory) （详见第 30 条）。使用静态工厂的最后一个优势是，可以通过*方法引用*（method reference）作为提供者，比如Elvis::instance 就是 Supplier<Elvis>。除非满足以上任意一种优势，否则还是优先考虑公有域（public-field ）的方法。

为了将利用上述方法实现的 Singleton 类变成是可序列化（Serializable）（详见第 12 章），仅仅在声明中加上 implements Serializable 是不够的。为了维护并保证 Singleton, 必须声明所有实例域都是瞬时（transient）的，并提供一个 readResolve 方法（详见第89条）。否则，每次反序列化一个序列化的实例时，都会创建一个新的实例，比如，在我
们的例子中，会导致“假冒的 Elvis”。为了防止发生这种情况，要在 Elvis 类中加入如下 readResolve 方法：
~~~java
// readResolve method to preserve singleton property
private Object readResolve() { 
    // Return the one true Elvis and let the garbage collector
    // take care of the Elvis impersonator
    return INSTANCE;
}
~~~
实现Singleton的第三种方法是声明一个包含单个元素的枚举类型：
~~~java
// Enum singleton - the preferred approach 
public enum Elvis { 
    INSTANCE; 
    public void leaveTheBuilding() { ... }
}
~~~
这种方法在功能上与公有域方法相似，但更加简洁，无偿地提供了序列化机制，绝对防止多次实例化，即使是在面对复杂的序列化或者反射攻击的时候。虽然这种方法还没有广泛采用，但是**单元素枚举类型经常成为实现 Singleton 的最佳方法**。注意，如果 Singleton必须扩展一个超类，而不是扩展 Enum 的时候，则不宜使用这个方法（虽然可以声明枚举去实现接口）。

### 第4条：通过私有构造器强化不可实例化的能力

有时可能需要编写只包含静态方法和静态域的类。这些类的名声很不好，因为有些人在面向对象的语言中滥用这样的类来编写过程化的程序，但它们也确实有特别的用处。我们可以利用这种类，以 java.lang.Math 或者 java.util.Arrays 的方式，把基本类型的值或者数组类型上的相关方法组织起来 我们也可以通过 java.util.Collections 的方式，把实现特定接口的对象上的静态方法，包括工厂方法（详见第 1 条）组织起来 （从 Java 8 开始，也可以把这些方法放进接口中，假定这是你自己编写的接口可以进行修改。最后，还可以利用这种类把 final 类上的方法组织起来，因为不能把它们放在子类中。

这样的*工具类*（ utility class ）不希望被实例化，因为实例化对它没有任何意义。然而在缺少显式构造器的情况下，编译器会自动提供一个公有的、无参的*缺省构造器*（default constructor）。对于用户而言，这个构造器与其他的构造器没有任何区别。在已发行的 API 中常常可以看到一些被无意识地实例化的类。

**企图通过将类做成抽象类来强制该类不可被实例化是行不通的**。该类可以被子类化，并且该子类也可以被实例化。这样做甚至会误导用户，以为这种类是专门为了继承而设计的（详见第 19 条）。然而，有一些简单的习惯用法可以确保类不可被实例化。由于只有当类不包含显式的构造器时，编译器才会生成缺省的构造器，因此只要让这个类包含一个私有构造器，它就不能被实例化：
~~~java
// Noninstantiable utility class 
public class UtilityClass { 
    // Suppress default constructor for noninstantiability 
    private UtilityClass() { 
        throw new AssertionError();
    }
    ... // Remainder omitted
}
~~~

由于显式的构造器是私有的，所以不可以在该类的外部访问它。AssertionError不是必需的，但是它可以避免不小心在类的内部调用构造器。它保证该类在任何情况下都不会被实例化。这种习惯用法有点违背直觉，好像构造器就是专门设计成不能被调用
此，明智的做法就是在代码中增加一条注释，如上所示。

这种习惯用法也有副作用，它使得一个类不能被子类化。所有的构造器都必须显式或隐式地调用超类（ superclass ）构造器，在这种情形下，子类就没有可访问的超类构造器可调用了。

### 第5条：优先考虑依赖注入来引用资源

有许多类会依赖一个或多个底层的资源。例如，拼写检查器需要依赖词典。因此，像下面这样把类实现为静态工具类的做法并不少见（详见第4条）：
~~~java
// Inappopriate use of static utility - inflexible & untestable! 
public class SpellChecker{
    private static final Lexicon dictionary = ...; 
    private SpellChecker(){} // Noninstantiable 
    public static boolean isValid(String Word) { ... }
    public static List<String> suggestions(String typo) { ... } 
}
~~~
同样地，将这些类实现为 Singleton 的做法也并不少见（详见第 3 条）
~~~java
// Inappropriate use of singleton - inflexible & untestable! 
public class SpellChecker{
    private final Lexicon dictionary = ... ; 
    private SpellChecker(...) {}
    public static INSTANCE = new SpellChecker(...);
    public boolean isValid(String Word) { ... } 
    public List<String> suggestions(String typo){...}
}
~~~
以上两种方法都不理想，因为它们都是假定只有一本词典可用。实际上，每一种语言都有自己的词典，特殊同汇还要使用特殊的词典 此外，可能还需要用特殊的词典进行测试。因此假定用一本词典，就能满足所有需求，这简直是痴心妄想。

建议尝试用 SpellChecker 来支持多词典，即在现有的拼写检查器中，设 dictionary 域为 nonfinal ，并添加一个方法用它来修改词典，但是这样的设置会显得很笨拙、容易出错，并且无法并行工作。**静态工具类和 Singleton 类不适合于需要引用底层资源的类**。

这里需要的是能够支持类的多个实例（在本例中是指 SpellChecker），每一个实例都使用客户端指定的资源（在本例中是指词典）。满足该需求的最简单的模式是，**当创建一个新的实例时，就将该资源传到构造器中。**这是依赖注入（dependency injection）的一种形式：词典（dictionary）是拼写检查器的一个依赖（ dependency ），在创建拼写检查器时就将词典注
入（ injected ）其中。
~~~java
// Dependency injection provides flexibility and testability 
public class SpellChecker{
    private final Lexicon dictionary;
    public SpellChecker(Lexicon dictionary) { 
        this.dictionary = Objects.requireNonNull(dictionary);
    }
    public boolean isValid(String word) { ... } 
    public List<String> suggestions(String typo) { ... }
}
~~~
依赖注入模式就是这么简单，因此许多程序员使用多年，却不知道它还有名字呢。虽然这个拼写检查器的范例中只有一个资源（词典），但是依赖注入却适用于任意数量的资源，以及任意的依赖形式。依赖注入的对象资源具有不可变性（详见第 17 条），因此多个客户端可以共享依赖对象（假设客户端们想要的是同一个底层资源）。依赖注入也同样适用于构造器、静态工厂（详见第1条）和构建器（详见第2条）.

这个程序模式的另一种有用的变体是，将资源工厂（factory）传给构造器。工厂是可以被重复调用来创建类型实例的一个对象。这类工厂具体表现为*工厂方法*（Factory Method) 模式。 Java8 中增加的接口 Supplier<T>，最适合用于表示工厂。带Supplier<T>的方法，通常应该限制输入工厂的类型参数使用*有限制的通配符类型*( bounded wildcard type），详见第 31 条，以便客户端能够传入一个工厂，来创建指定类型的任意子类型。例如，下面是一个生产马赛克的方法，它利用客户端提供的工厂来生产每一片马赛克：
~~~java
Mosaic create(Supplier<? extends Tile> tileFactory) { ... }
~~~ 
虽然依赖注人极大地提升了灵活性和可测试性，但它会导致大型项目凌乱不堪，因为它通常包含上千个依赖。不过这种凌乱用一个*依赖注入框架*（dependency injection framework)便可以终结，如 Dagger、Guice 或者 Spring。这些框架的用法超
了本书的讨论范畴，但是，请注意：设计成手动依赖注入的API，一般都适用于这些框架。

总而言之，不要用 Singleton 和静态工具类来实现依赖一个或多个底层资源的类，且该资源的行为会影响到该类的行为；也不要直接用这个类来创建这些资源。而应该将这些资源或者工厂传给构造器（或者静态工厂，或者构建器），通过它们来创建类。这个实践就被称作依赖注入，它极大地提升了类的灵活性、可重用性和可测试性。

### 第6条：避免创建不必要的对象
