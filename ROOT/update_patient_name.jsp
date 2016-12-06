<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
		dbController.DatabaseController" %>
<head>
  <title>Update Name</title>
  <meta charset="UTF-8"/>
</head>
<body>
  <div id="result">
    <%
       request.setCharacterEncoding("UTF-8");
       response.setContentType("text/html;charset=UTF-8");
       
       DatabaseController dbcontroller = new DatabaseController();
       
       dbcontroller.Open();
       
       out.write("<h2>Update Patient Name</h2>");
       
       StringBuffer givenName = new StringBuffer();
       StringBuffer surname = new StringBuffer();
       String result = null;
       Integer patientID = 0;

       content.append("<br/>");
       content.append("<form method='post'>");
       content.append("Patient ID Number: <input type='text' name='patientID' value=0><br/>");
       content.append("First name: <input type='text' name='givenname' value=''><br/>");
       content.append("Last Name: <input type='text' name='surname' value=''><br/>");
       content.append("<input type='submit' name='submitID'>");
       content.append("</form>");
       
       out.write(content.toString());

       if(request.getParameter("submitID")!=null) {

          givenName.append(request.getParameter("givenname"));
          surname.append(request.getParameter("surname"));
          patientID = Integer.valueOf(Integer.parseInt(request.getParameter("patientID")));

          result = dbcontroller.updatePatientName(patientID,givenName.toString(),surname.toString());
          out.write("<br/>");
          out.write(result);
       }
       
       dbcontroller.Close();
       %>
  </div>
</body>
</html>
