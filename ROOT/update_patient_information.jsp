<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
		dbController.DatabaseController" %>
<head>
  <title>Update Information</title>
  <meta charset="UTF-8"/>
</head>
<body>
  <div id="result">
    <%
       request.setCharacterEncoding("UTF-8");
       response.setContentType("text/html;charset=UTF-8");
       
       DatabaseController dbcontroller = new DatabaseController();
       
       dbcontroller.Open();
       
       out.write("<h2>Update Patient Information</h2>");
       
       StringBuffer content = new StringBuffer();
       StringBuffer nextVisit = new StringBuffer();
       StringBuffer address = new StringBuffer();
       Integer outstandingCost = 0;
       String result = null;
       Integer patientID = 0;

       content.append("<br/>");
       content.append("<form method='post'>");
       content.append("Patient ID Number: <input type='text' name='patientID' value=0><br/>");
       content.append("Next Appointment(dd-mm-yy): <input type='text' name='nextVisit'value=''><br/>");
       content.append("Outstanding Balance: <input type='text' name='outstandingCost' value=-1><br/>");
       content.append("Address: <input type='text' name='address' value=''><br/>");
       content.append("<input type='submit' name='submitID'>");
       content.append("</form>");
       
       out.write(content.toString());

       if(request.getParameter("submitID")!=null) {

          nextVisit.append(request.getParameter("nextVisit"));
          address.append(request.getParameter("address"));
          outstandingCost = Integer.valueOf(Integer.parseInt(request.getParameter("outstandingCost")));
          patientID = Integer.valueOf(Integer.parseInt(request.getParameter("patientID")));

          result = dbcontroller.updatePatient(patientID,nextVisit.toString(),outstandingCost,address.toString());
          out.write("<br/>");
          out.write(result);
       }
       
       dbcontroller.Close();
       %>
  </div>
</body>
</html>
