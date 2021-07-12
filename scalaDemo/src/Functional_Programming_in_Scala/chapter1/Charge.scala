package Functional_Programming_in_Scala.chapter1

/**
 * case 类只有一个主构造器，构造参数紧跟着类名后边（这里类名是Charge）。
 * 这个列表中参数都是public的、不可修改的，访问时使用面向对象的方式，中间用点来标注，如other.cc
 *
 * @param cc
 * @param amount
 */
case class Charge(cc: CreditCard, amount: Double) {
  def combine(other: Charge): Charge =
    if (cc == other.cc)
      Charge(cc, amount + other.amount)
    else
      throw new Exception("Can't combine charges to different cards")
}
