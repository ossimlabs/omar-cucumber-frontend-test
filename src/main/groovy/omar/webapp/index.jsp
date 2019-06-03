<%@ page import="omar.cucumber.frontend.test.CucumberTest"%>
<html>
<head>
</head>
<body>
<%!
  public void callfrontend() {
    CucumberTest frontend = new CucumberTest();
    frontend.startTest();
  }
%>
<%
  String requestMethod = request.getMethod();
  if(requestMethod.equals("POST")){
    callfrontend();
  }
%>
<form name="frontendTestForm" action="#" method="post">
  <input type="submit" id="frontendTestBtn" value="Run Test" style="background-color: green; height: 50px; width: 100px; color: white; font-size: 16px">
</form>
<h2>Please wait until test is complete before clicking the links below or you will see old data.</h2>
<p><a href="./reports/html/">Test Report</a></br>
<a href="./reports/json/frontend.json">Test Json</a></p>
</body>
</html>