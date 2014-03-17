package net.atos.cis

trait NinoToCis {
  def ni2PersonDetails(nino: String): String

}

class NinoToCisFileSystem extends NinoToCis {

  def ni2PersonDetails(nino: String): String = {

    val detailsFileName = s"Cis/${nino}.txt"

    try {
      val url = getClass.getClassLoader.getResource(detailsFileName)

      val xmlString = scala.io.Source.fromURL(url).mkString

      xmlString

    } catch {
      case e: Throwable => "<NoDetailsFound/>"
    }
  }

}

object Cis {

  def apply(): NinoToCis = new NinoToCisFileSystem

  def main(args: Array[String]) {
    Cis().ni2PersonDetails("CL100100A")
  }
}