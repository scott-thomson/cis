package net.atos.cis

object Cis {

  def ni2PersonDetails(nino: String): String = {
    val detailsFileName = s"Cis/${nino}.txt"

    val url = getClass.getClassLoader.getResource(detailsFileName)

    val xmlString = scala.io.Source.fromURL(url).mkString
    println(xmlString)
    xmlString
  }

  def main(args: Array[String]) {
    ni2PersonDetails("CL100100A")
  }
}