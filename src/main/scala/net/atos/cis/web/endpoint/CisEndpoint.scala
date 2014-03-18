package net.atos.cis.web.endpoint

import scala.xml.Elem
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import net.atos.cis.Cis

object CisEndpoint {
  private val MethodPost: String = "POST";

  private val MethodGet: String = "GET";

  val cisHandler = new AbstractHandler {
    def handle(target: String, baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
      System.out.println("In CIS handle " + request.getMethod())
      if (request.getMethod() == MethodGet)
        handleGet(request, response)
      else
        handlePost(request, response)

      baseRequest.setHandled(true)
    }
  }

  def handleGet(request: HttpServletRequest, response: HttpServletResponse) {
    response.setContentType("text/html;charset=utf-8")
    response.setStatus(HttpServletResponse.SC_OK);
    val url = request.getRequestURI()
    println(url)
    url match {
      case "/" => {
        response.getWriter().println(getCisView())
        response.setContentType("text/html;charset=utf-8")
      }
      case _ => {
        response.setContentType("application/xml;charset=utf-8")
        response.getWriter().println(Cis().ni2PersonDetails(url.substring(1)))
      }
    }
    response.flushBuffer();
  }

  def handlePost(request: HttpServletRequest, response: HttpServletResponse) {
    response.setContentType("application/xml;charset=utf-8")
    response.setStatus(HttpServletResponse.SC_OK);
    val url = request.getParameter("custId");
    response.getWriter().println(Cis().ni2PersonDetails(url))
    response.flushBuffer();
  }

  def getCisView(): Elem =
    <html>
      <head>
        <title>CIS Details</title>
        <h1>CIS Details</h1>
      </head>
      <body>
        <form action="http://localhost:8091" method="POST">
          <table>
            <tr>
              <td>
                <input type="text" name="custId"></input>
              </td>
              <td>
                <input type="submit" value="Submit"/>
              </td>
            </tr>
          </table>
        </form>
      </body>
    </html>

  def defaultPort = {
    val portString = System.getenv("PORT")
    println("PortString[" + portString + "]")
    val port = portString match { case null => 8091; case _ => portString.toInt }
    println("Port[" + port + "]")
    port
  }

  def main(args: Array[String]) {
    val s = new Server(defaultPort);
    s.setHandler(cisHandler);
    s.start
    System.out.println("Started server thread")
    s.join
  }
}