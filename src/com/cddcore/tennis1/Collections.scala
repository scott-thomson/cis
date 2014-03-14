package com.cddcore.tennis1

object Collections {

  val l1 = List(1, 2, 3, 4, 5, 6, 7, 8)
  
  implicit def toEI(x:Int) = ExpenseItem(x)
  
  val le: List[ExpenseItem] = List(1, 2, 3, 4, 5, 6, 7, 8)

  val l2 = List(1, "two", 3, "four", 5, "six", 7, "eight")

  val l3 = List("one", "two", "three", "four", "five", "six", "seven", "eight")

  def main(args: Array[String]) {
    println(le.sortBy(_.price).map("Price: " + _.price))
  }
}