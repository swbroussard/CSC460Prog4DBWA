<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
		dbController.DatabaseController" %>
<head>
  <title>update</title>
  <meta charset="UTF-8"/>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <header>
    DB Dentistry
  </header>
  <div id="result">
    <%
       request.setCharacterEncoding("UTF-8");
       response.setContentType("text/html;charset=UTF-8");
       
       DatabaseController dbcontroller = new DatabaseController();
       
       dbcontroller.Open();
       
       out.write("<h2>Update Patient</h2>");
       
       StringBuffer webContent = new StringBuffer();
       
       webContent.append("<br/><table><tr>");
       
       webContent.append("<th><a href=./update_patient_information.jsp>Update Patient Information</a></th>");
       
       webContent.append("<th><a href=./update_patient_name.jsp>Update Patient Name</a></th>");
       
       webContent.append("<th><a href=./update_patient_insurance.jsp>Update Patient Insurance Provider</a></th>");
       
       webContent.append("</table>");
       
       out.write(webContent.toString());
       
       dbcontroller.Close();
       %>
  </div>
</body>
</html>
