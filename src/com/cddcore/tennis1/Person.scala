package com.cddcore.tennis1

trait CashItem {
  def price: Int
  def contribution: Int
  def htmlPrice = <b>{ price }</b>
}

case class IncomeItem(price: Int) extends CashItem {
  def contribution = price
}

case class ExpenseItem(price: Int) extends CashItem {
  val contribution = -price
}

case class Person {

  private def findTotal1(expenses: List[CashItem]): Int = {
    expenses match {
      case List(ci) => ci.contribution
      case ci :: tail => ci.contribution + findTotal1(tail)
    }
  }
}

object ExpenseItem {
  def apply(ints: Int*): List[ExpenseItem] =
    {
      ints.map((x) => ExpenseItem(x)).toList
    }
}

object IncomeItem {
  def apply(ints: Int*): List[IncomeItem] =
    {
      ints.map((x) => IncomeItem(x)).toList
    }
}

object Person {

  val p1 = Person()

  def main(args: Array[String]) {
    println(p1.findTotal1(IncomeItem(1, 2) ::: ExpenseItem(3, 4) ::: IncomeItem(4, 7)))
  }
}