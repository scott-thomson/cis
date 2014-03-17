package nt.atos.cis.web.endpoint

import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object CisEndpoint {
  private val MethodPost: String = "POST";
  
  private val MethodGet: String = "GET";
  
  val cisHandler = new AbstractHandler {
	  def handle(target: String, baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
	    System.out.println("In CIS handle")
		  if (request.getMethod() == MethodGet )
		    
		    handleGet(request, response)
		  else
		    handlePost(request, response)
		    
		  baseRequest.setHandled(true)
	  }
  }
    
  def handleGet(request: HttpServletRequest, response: HttpServletResponse) {
		  response.setContentType("text/html;charset=utf-8")
		  response.setStatus(HttpServletResponse.SC_OK);
		  response.getWriter().println("<h1>CIS Get Hello</h1>");
		  response.flushBuffer();
  }
  
  def handlePost(request: HttpServletRequest, response: HttpServletResponse) {  	    
		  response.setContentType("text/html;charset=utf-8")
		  response.setStatus(HttpServletResponse.SC_OK);
		  response.getWriter().println("<h1>CIS Post Hello</h1>");
		  response.flushBuffer();
  }
  
  def main(args: Array[String]) {
	  val s = new Server(8080);
	  s.setHandler(cisHandler);
	  s.start
	  System.out.println("Started server thread")
	  s.join
  }
}