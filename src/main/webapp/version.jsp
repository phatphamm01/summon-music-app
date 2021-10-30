  Server info: <%= application.getServerInfo() %><br>
  Servlet version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
  JSP version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>
  Java version: <%= System.getProperty("java.version") %><br>

