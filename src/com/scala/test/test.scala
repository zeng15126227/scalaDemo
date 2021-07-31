package com.scala.test

import java.util
import java.util.Date

import scala.collection.mutable.ListBuffer

object test {
  private val name = "dundun"
  var info = baisApp(_: Date, "info", _: String)
  var error = baisApp(_: Date, "error", _: String)

  def main(args: Array[String]): Unit = {
    implicitParam
  }

  def mixTable(): Unit = {
    for (i <- 1 to 9; j <- 1 to 9 if (i >= j)) {
      if (j <= i) print(s"$i*$j=${i * j}\t")
      if (i == j) println()
    }
  }

  def yieldDemo(): Unit = {
    var seq =
      for (i <- 1 to 10) yield {
        println(i)
      }
  }

  def baisApp(date: Date, tp: String, msg: String): Unit = {
    println(s"date:$date\ttp:$tp\tmsg:$msg")
  }

  def variableParam(a: Int*): Unit = {
    a.foreach((param: Int) => {
      println(param)
    })

    a.foreach(println(_))

    a.foreach(println)
  }

  def compute(a: Int, b: Int, f: (Int, Int) => Int): Unit = {
    var res: Int = f(a, b)
    println(res)
  }

  def factory(t: String): (Int, Int) => Int = {
    if (t.equals("+")) {
      return (a: Int, b: Int) => {
        a + b
      }
    } else {
      return (a: Int, b: Int) => {
        a * b
      }
    }
  }

  def kelihua(a: Int*)(b: String*): Unit = {
    a.foreach(println)
    b.foreach(println)
  }

  def collectionDemo(): Unit = {
    //不可变数组
    val a = Array[Int](1, 2, 3, 4, 5)
    println(a(3))
    //不可变链表
    val b = List[Int](1, 2, 3, 4, 5)
    println(b)
    var b2 = 4 :: b
    println(b == b2)
    //可变列表
    val c = new ListBuffer[Int];
    c.+=(1)
    c.+=(2)
    c.+=(3)
    println(c)
    println(b ++ c)
    println(b ++: c)

    val t2 = (11, "sdfsdf")
    val iterator = t2.productIterator
    while (iterator.hasNext) {
      println(iterator.next())
    }

    val map01: Map[String, Int] = Map(("a", 33), "b" -> 22, ("c", 3434), ("a", 3333))
    println(map01.size)
    println(map01.get("a").getOrElse("null"))
  }

  def iterDemo(): Unit = {
    val strings: List[String] = List("james harden", "kaven durant", "kary inrven")
    val words: List[String] = strings.flatMap(_.split(" "))
    val tuples: List[(String, Int)] = words.map((_, 1))
    tuples.foreach(println)

    val iterator: Iterator[String] = strings.iterator
    val iteratorMap: Iterator[String] = iterator.flatMap(_.split(" "))
    val tuplesIter: Iterator[(String, Int)] = iteratorMap.map((_, 1))
    while (tuplesIter.hasNext) {
      println(tuplesIter.next())
    }
  }

  def classDemo(): Unit = {
    class Cat() {}
    var dundun = new Cat()
    var dundundun = new Cat()
    println(dundun == dundundun)
    println(dundun.equals(dundundun))
  }

  def caseClassDemo(): Unit = {
    case class Cat() {}
    var dundun = new Cat()
    var dundundun = new Cat()
    println(dundun == dundundun)
    println(dundun.equals(dundundun))
  }

  def matchDemo(): Unit = {
    def matchFun(x: Any): String = {
      x match {
        case 1 => s"...is 1"
        case 88 => s"...is 88"
        case false => s"...is false"
        case w: Int if w > 50 => s"$w...is  > 50"
        case _ => "wo ye bu zhi dao sha lei xing"
      }
    }

    println(matchFun(1))
    println(matchFun(88))
    println(matchFun(false))
    println(matchFun(51))
    println(matchFun(21))
  }

  def partFunDemo(): Unit = {
    def partFun: PartialFunction[Any, String] = {
      case 1 => s"...is 1"
      case 88 => s"...is 88"
      case false => s"...is false"
      case w: Int if w > 50 => s"$w...is  > 50"
      case _ => "wo ye bu zhi dao sha lei xing"
    }

    println(partFun(1))
    println(partFun(88))
    println(partFun(false))
    println(partFun(51))
    println(partFun(21))
  }

  def implicatDemo1(): Unit = {
    val list = new util.LinkedList[Int]()
    list.add(1)
    list.add(2)
    list.add(3)

    def foreach[T](p: util.LinkedList[T], f: (T) => Unit): Unit = {
      var list = p.iterator()
      while (list.hasNext){
        f(list.next())
      }
    }

    foreach(list,println)
  }
  def implicatDemo2(): Unit = {
    val list = new util.LinkedList[Int]()
    list.add(1)
    list.add(2)
    list.add(3)

    class XXX[T](p: util.LinkedList[T]){
      var list = p.iterator();
      def foreach(f: (T) => Unit): Unit = {
        while (list.hasNext){
          f(list.next())
        }
      }
    }

    new XXX(list).foreach(println)
  }
  def implicatDemo3(): Unit = {
    val list = new util.LinkedList[Int]()
    list.add(1)
    list.add(2)
    list.add(3)

    class XXX[T](p: util.LinkedList[T]){
      var list = p.iterator();
      def foreach(f: (T) => Unit): Unit = {
        while (list.hasNext){
          f(list.next())
        }
      }
    }

    implicit def dundunCat[T](p: util.LinkedList[T])={
      new XXX(p)
    }

    list.foreach(println)
  }

  def implicitClassDemo(): Unit ={
    val list = new util.LinkedList[Int]()
    list.add(1)
    list.add(2)
    list.add(3)

    implicit class XXX[T](p: util.LinkedList[T]){
      var list = p.iterator();
      def foreach(f: (T) => Unit): Unit = {
        while (list.hasNext){
          f(list.next())
        }
      }
    }

    list.foreach(println)
  }

  def implicitParam(): Unit ={
    implicit var param:String = "you yoyu you"
    def dundunCat(implicit p:String): Unit ={
      println(p)
    }
    dundunCat
  }
}


class test() {
  val name = "zxz"

  def msg(): Unit = {
    println(s"name:${name}")
    println(s"name:${test.name}")
  }
}
