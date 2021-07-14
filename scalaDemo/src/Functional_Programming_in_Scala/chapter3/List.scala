package Functional_Programming_in_Scala.chapter3

// List是一个泛型的数据类型，类型参数用A表示
// 类型参数A前边的+是一个型变(variance)的符号，标志着A是协变的或正向（positive）的参数
sealed trait List[+A]

// 用于表现空List的List数据构造器
case object Nil extends List[Nothing]

// 另一个数据构造器，呈现非空List。注意尾部是另一个List[A]，当然这个尾部也可能为Nil或另一个Cons
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  /**
   * 利用模式匹配对一个整数型List进行合计
   *
   * @param ints
   * @return
   */
  def sum(ints: List[Int]): Int = ints match {
    // 空列表的累加值为0
    case Nil => 0
    // 对一个头部是x的列表进行累加，这个过程是用x加上该列表剩余部分的累加值
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  /**
   * 可变参数（可以是一个或多个该类型的参数）函数语法
   *
   * @param as
   * @tparam A
   * @return
   */
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  @scala.annotation.tailrec
  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] =
    as match {
      case Cons(h, t) if f(h) => dropWhile(t)(f)
      case _ => as
    }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def product2(ns: List[Double]) =
  // _ * _是对(x, y) => x * y更简练的写法
    foldRight(ns, 1.0)(_ * _)
}
