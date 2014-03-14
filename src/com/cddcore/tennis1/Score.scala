package com.cddcore.tennis1

import org.cddcore.engine.Engine

case class Score(name: String)

object Score {
  val love = Score("love")
  val s15 = Score("Fifteen")
  val s30 = Score("Thirty")
  val s40 = Score("Forty")
  val deuce = Score("deuce")
  val advantage = Score("advantage")
  val noScore = Score("")
  val won = Score("won")
  val lost = Score("lost")
  
  val lookup = Map(0 -> love, 1 -> s15, 2 -> s30, 3 -> s40)
}