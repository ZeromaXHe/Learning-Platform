# 第12章 Java多线程程序的性能调校

## 12.1 Java虚拟机对内部锁的优化

自 Java6/Java7 开始，Java虚拟机对内部锁的实现进行了一些优化。这些优化主要包括锁消除（Lock Elision）、锁粗化（Lock Coarsening）、偏向锁（Biased Locking）以及适应性锁（Adaptive Locking）。这些优化仅在Java虚拟机server模式下起作用。

### 12.1.1 锁消除

锁消除（Lock Elision）是JIT编译器对内部锁的具体实现所做的一种优化，如下所示。（IBM J9 Java虚拟机也支持该优化）

~~~java
// 待编译字节码的等效代码
synchronized(monitor){
    doSomething;
}
// 有且仅有一个线程会执行这段代码
↓
// 编译后的机器码的等效代码
doSomething();
~~~

在动态编译同步块的时候，JIT编译器可以借助一种被称为逃逸分析（Escape Analysis）的技术来判断同步块所使用的锁对象是否能够被一个线程访问而没有被发布到其他线程。如果同步块所使用的锁对象通过这种分析被证实只能够被一个线程访问，那么JIT编译器在编译这个同步块的时候并不生成synchronized所表示的锁的申请与释放对应的机器码，而仅生成原临界区代码对应的机器码，这就造成了被动态编译的字节码就像是不包含monitorenter（申请锁）和monitorexit（释放锁）这两个字节码指令一样，即消除了锁的使用。这种编译器优化就被称为锁消除（Lock Elision），它使得特定情况下我们可以完全消除锁的开销。

Java标准库中的有些类（比如StringBuffer）虽然是线程安全的，但是在实际使用中我们往往不在多个线程间共享这些类的实例。而这些类在实现线程安全的时候往往借助于内部锁。因此，这些类是锁消除优化的常见目标。如清单12-1所示的例子中，JIT编译器在编译 toJSON 方法的时候会将其调用的 StringBuffer.append/toString 方法内联（Inline）到该方法之中，这相当于把 StringBuffer.append/toString 方法的方法体中的指令复制到toJSON的方法体中。这里的StringBuffer实例sbf是一个局部变量，并且该变量所引用的对象并没有被发布到其他线程，因此 sbf 引用的对象只能够被 sbf 所在的方法（toJSON方法）的当前执行线程（一个线程）访问。所以，JIT编译器此时可以消除toJSON方法中从 StringBuffer.append/toString 方法的方法体复制的指令所使用的内部锁。在这个例子中，StringBuffer.append/toString 方法本身所使用的锁并不会被消除，因为系统中可能还有其他地方在使用StringBuffer, 而这些代码可能会共享 StringBuffer 实例。

~~~java
// 清单12-1 可进行锁消除优化的示例代码
public class LockElisionExample {
    public static String toJSON(ProductInfo productInfo){
        StringBuffer sbf = new StringBuffer();
        sbf.append("{\"productID\":\"}").append(productInfo.productID);
        sbf.append("\",\"categoryID\":\"").append(productInfo.categoryID);
        sbf.append("\",\"rank\":\"").append(productInfo.rank);
        sbf.append("\",\"inventory\":\"").append(productInfo.inventory);
        sbf.append("\"}");
        
        return sbf.toString();
    }
}
~~~

锁消除优化所依赖的逃逸分析技术自Java SE 6u23起默认是开启的，但是锁消除优化是在Java 7开始引入的。（开启逃逸分析的虚拟机参数为“-XX:+DoEscapeAnalysis”, 关闭逃逸分析的虚拟机参数为“-XX:-DoEscapeAnalysis”。注意：“-XX:”开头的虚拟机参数表示相应的参数是“不稳定的”，即Oracle公司可能会在不事先通知的情况下更改甚至废弃相应的参数。）

从上述例子可以看出，锁消除优化还可能需要以JIT编译器的内联优化为前提。而一个方法是否会被JIT编译器内联取决于该方法的热度以及该方法对应的字节码的尺寸（Bytecode Size）。因此，锁消除优化能否被实施还取决于被调用的同步方法（或者带同步块的方法）是否能够被内联。

锁消除优化告诉我们在该使用锁的情况下必须使用锁，而不必过多在意锁的开销。开发人员应该在代码的逻辑层面考虑是否需要加锁，而至于代码运行层面上某个锁是否真的有必要使用则由JIT编译器来决定。锁消除优化并不表示开发人员在编写代码的时候可以随意使用内部锁（在不需要加锁的情况下加锁），因为锁消除是JIT编译器而不是javac所做的一种优化，而一段代码只有在其被执行的频率足够大的情况下才有可能会被JIT编译器优化。也就是说在JIT编译器优化介入之前，只要源代码中使用了内部锁，那么这个锁的开销就会存在。另外，JIT编译器所执行的内联优化、逃逸分析以及锁消除优化本身都是有其开销的。

在锁消除的作用下，利用ThreadLocal将一个线程安全的对象（比如Random）作为一个线程特有对象来使用，不仅仅可以避免锁的争用，还可以彻底消除这些对象内部所使用的锁的开销。

### 12.1.2 锁粗化

锁粗化（Lock Coarsening/Lock Merging）