package net.atos.cis.web.endpoint
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import scala.xml.Elem

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
    response.getWriter().println(getCisView());
    response.flushBuffer();
  }

  def handlePost(request: HttpServletRequest, response: HttpServletResponse) {
    response.setContentType("application/xml;charset=utf-8")
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println(getCustDetails(getCustId(request)))
    response.flushBuffer();
  }

  def getCustId(request: HttpServletRequest): String = {
    request.getParameter("custId");
  }

  def getCustDetails(custId: String): Elem =
    <CISQuery xsi:schemaLocation="http://www.autotdd.com/ca CISquery%20v1_0%202010-07-05.xsd" xmlns="http://www.autotdd.com/ca" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <NameInfo>
        <PersonNameTitle>MR</PersonNameTitle>
        <PersonGivenName>ADAM</PersonGivenName>
        <PersonFamilyName>APPLE</PersonFamilyName>
        <PersonNameSuffix>BSC</PersonNameSuffix>
        <PersonRequestedName>John APPLE</PersonRequestedName>
        <PersonNameStartDate>1981-08-1</PersonNameStartDate>
      </NameInfo>
      <PersonalInfo>
        <NINO>CL100100A</NINO>
        <MaritalStatus>
          <MaritalStatus>m</MaritalStatus>
          <VerificationLevel>Level 1</VerificationLevel>
        </MaritalStatus>
        <Nationality>GB</Nationality>
        <BirthDate>
          <PersonBirthDate>1970-08-13</PersonBirthDate>
          <VerificationLevel>Level 2</VerificationLevel>
        </BirthDate>
        <GenderCurrent>2</GenderCurrent>
        <GenderAtRegistration>2</GenderAtRegistration>
        <DisabilityData/>
        <SpecialNeedsData/>
      </PersonalInfo>
      <ContactInfo>
        <PreferredLanguages>en</PreferredLanguages>
      </ContactInfo>
      <Relationships>
        <NINO>DP100100A</NINO>
        <RelationshipType>09 Child</RelationshipType>
        <RelationshipStartDate>1970-10-12</RelationshipStartDate>
      </Relationships>
      <Addresses>
        <Line1>12 FREESTYLE MEADOWS</Line1>
        <Line2>ASHTON UNDER LYNE</Line2>
        <PostCode>AL5 9IK</PostCode>
        <AddressStartDate>2003-11-05</AddressStartDate>
      </Addresses>
    </CISQuery>

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

  def main(args: Array[String]) {
    val s = new Server(8091);
    s.setHandler(cisHandler);
    s.start
    System.out.println("Started server thread")
    s.join
  }