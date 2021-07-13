package Functional_Programming_in_Scala.chapter2

// 一行注释
/* 另一行注释 */
/** 文档说明 */
/**
 * 声明一个单例对象，即同时声明一个类和它的唯一实例
 */
object MyModule {
  /**
   * abs方法接收一个integer并返回一个integer
   *
   * @param n
   * @return
   */
  def abs(n: Int) =
  // 如果n小于0，返回-n
    if (n < 0) -n
    else n

  def factorial(n: Int): Int = {
    /**
     * 一个内部函数，或局部定义函数。在Scala中把一个函数定义在另一个函数体内很常见。
     * 在函数式编程中，我们不应该认为它跟一个局部整数或局部的字符串有什么不同。
     *
     * @param n
     * @param acc
     * @return
     */
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)

    go(n, 1)
  }

  /**
   * 一个私有方法只能被MyModule里的其他成员调用
   *
   * @param x
   * @return
   */
  private def formatAbs(x: Int) = {
    // 字符串里有2个的占位符，%d代表数字。
    val msg = "The absolute value of %d is %d"
    // 在字符串里将2个%d占位符分别替换为x和abs(x)
    msg.format(x, abs(x))
  }

  private def formatFactorial(n: Int) = {
    val msg = "The factorial of %d is %d."
    msg.format(n, factorial(n))
  }

  /**
   * 用类型A做参数代替掉String类型的硬编码，并且用一个对数组里每个元素进行测试的函数替代掉之前用于判断元素是否与给定key相等的硬编码。
   * @param as
   * @param p
   * @tparam A
   * @return
   */
  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if (n >= as.length) -1
      // 如果函数p匹配当前元素，就找到了相匹配的元素，返回数组当前索引值。
      else if (p(as(n))) n
      else loop(n + 1)

    loop(0)
  }

  /**
   * Unit类似于Java或C语言里的void
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(formatFactorial(7))
  }
}
