package Programming_in_Scala.chapter3

import java.math.BigInteger

import scala.io.Source

object Program3_1 {
  val big = new BigInteger("12345")

  def main(args: Array[String]): Unit = {
    println("=====[3.1]=====")
    program3_1()
    println("=====[3.2]=====")
    program3_2()
    println("=====[3.3]=====")
    program3_3()
    println("=====[3.4]=====")
    program3_4()
    println("=====[3.5]=====")
    program3_5()
    println("=====[3.6]=====")
    program3_6()
    println("=====[3.7]=====")
    program3_7()
    println("=====[3.8]=====")
    program3_8()
    println("=====[3.10]=====")
    program3_10(args);
    println("=====[3.11]=====")
    program3_11(args);
  }

  /**
   * 清单3.1 用类型参数化数组
   */
  def program3_1(): Unit = {
    val greetStrings = new Array[String](3)
    greetStrings(0) = "Hello"
    greetStrings(1) = ","
    greetStrings(2) = "world!\n"
    for (i <- 0 to 2)
      print(greetStrings(i))
  }

  /**
   * 清单3.2 创造和初始化数组
   */
  def program3_2(): Unit = {
    val numNames = Array("zero", "one", "two")
    // 上面实际就是调用了创造并返回新数组的apply工厂方法。
    // apply方法可以有不定个数的参数，定义在Array的伴生对象（companion object）中。
    val numNames2 = Array.apply("zero", "one", "two")
  }

  /**
   * 清单3.3 创造和初始化列表
   */
  def program3_3(): Unit = {
    val oneTwoThree = List(1, 2, 3)

    // 列表类中定义了“:::”方法实现叠加功能
    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = oneTwo ::: threeFour
    // List(1, 2) and List(3, 4) were not mutated.
    println("" + oneTwo + " and " + threeFour + " were not mutated.")
    // Thus, List(1, 2, 3, 4) is a new List.
    println("Thus, " + oneTwoThreeFour + " is a new List.")

    // 列表类最常用的操作符或许是'::'，发音为“cons”。
    // 它可以把新元素组合到现有列表的最前端，然后返回作为执行结果的新列表。
    val twoThree = List(2, 3)
    val oneTwoThree2 = 1 :: twoThree
    println(oneTwoThree2)

    // Nil是空列表的简写，所以可以使用cons操作符把所有元素都串起来，并以Nil作结尾来定义新列表。
    val oneTwoThree3 = 1 :: 2 :: 3 :: Nil
    println(oneTwoThree3)
  }

  /**
   * 清单3.4 创造和使用元组
   */
  def program3_4(): Unit = {
    val pair = (99, "Luftballons")
    println(pair._1)
    println(pair._2)
  }

  /**
   * 清单3.5 创造、初始化和使用不可变集
   */
  def program3_5(): Unit = {
    var jetSet = Set("Boeing", "Airbus")
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))

    val hashSet = scala.collection.immutable.HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")
  }

  /**
   * 清单3.6 创建、初始化和使用可变集
   */
  def program3_6(): Unit = {
    val movieSet = scala.collection.mutable.Set("Hitch", "Poltergeist")
    // movieSet.+=("Shrek")
    movieSet += "Shrek"
    println(movieSet)
  }

  /**
   * 清单3.7 创造、初始化和使用可变映射
   */
  def program3_7(): Unit = {
    val treasureMap = scala.collection.mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))
  }

  /**
   * 清单3.8 创造、初始化和使用不可变映射
   */
  def program3_8(): Unit = {
    val romanNumeral = Map(
      1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
    )
    println(romanNumeral(4))
  }

  /**
   * 清单3.10 从文件中读入行
   *
   * @param args
   */
  def program3_10(args: Array[String]): Unit = {
    if (args.length > 0) {
      for (line <- Source.fromFile(args(0)).getLines())
        print(line.length + " " + line)
    }
    else
      Console.err.println("Please enter filename")
  }

  /**
   * 清单3.11 对文件的每行记录打印格式化的字符数量
   *
   * @param args
   */
  def program3_11(args: Array[String]): Unit = {
    def widthOfLength(s: String) = s.length.toString.length

    if (args.length > 0) {
      val lines = Source.fromFile(args(0)).getLines.toList
      val longestLine = lines.reduceLeft(
        (a, b) => if (a.length > b.length) a else b
      )
      val maxWidth = widthOfLength(longestLine)
      for (line <- lines) {
        val numSpaces = maxWidth - widthOfLength(line)
        val padding = " " * numSpaces
        print(padding + line.length + " | " + line)
      }
    }
    else
      Console.err.println("Please enter filename")
  }
}
