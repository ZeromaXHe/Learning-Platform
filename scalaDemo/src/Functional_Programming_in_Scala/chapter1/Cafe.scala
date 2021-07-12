package Functional_Programming_in_Scala.chapter1

class Cafe {
  /**
   * buyCoffee方法返回一对包含Coffee和Charge的值，使用类型(Coffee, Charge)来表示。这里不涉及支付相关的任何处理。
   *
   * @param cc
   * @return
   */
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = new Coffee()
    // 用小括号创建一个cpu和Charge的数据“对”，之间用逗号分隔。
    (cup, Charge(cc, cup.price))
  }

  /**
   * List[Coffee]是一个承载Coffee的不可变的单向链表。我们将在第3章讨论数据类型的更多细节
   *
   * @param cc
   * @param n
   * @return
   */
  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    // List.fill(n)(x)创建一个对x复制n份的列表。我们将在后续章节解释这个有趣的函数调用语法
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    // unzip将数值对儿列表，分成一对儿（pair）列表。这里我们用一行代码对这个pair解构成2个值（coffee列表和charge列表）
    val (coffees, charges) = purchases.unzip
    // charges.reduce对整个charge列表规划成一个charge，每次使用combine来组合两个charge。
    // reduce是一个高阶函数的例子，我们将在下一章做适当的介绍
    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

  def coalesce(charges: List[Charge]): List[Charge] =
    charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
}
