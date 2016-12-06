<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
		dbController.DatabaseController" %>
<head>
  <title>Update Insurance Provider</title>
  <meta charset="UTF-8"/>
</head>
<body>
  <div id="result">
    <%
       request.setCharacterEncoding("UTF-8");
       response.setContentType("text/html;charset=UTF-8");
       
       DatabaseController dbcontroller = new DatabaseController();
       
       dbcontroller.Open();
       
       out.write("<h2>Update Patient Insurance Provider</h2>");
       
       StringBuffer content = new StringBuffer();
       StringBuffer  insuranceProv= new StringBuffer();
       String result = null;
       Integer patientID = 0;

       content.append("<br/>");
       content.append("<form method='post'>");
       content.append("Patient ID Number: <input type='text' name='patientID' value=0><br/>");
       content.append("Name of Insurance Provider: <input type='text' name='insuranceProv' value=''><br/>");
       content.append("<input type='submit' name='submitID'>");
       content.append("</form>");
       
       out.write(content.toString());

       if(request.getParameter("submitID")!=null) {

          insuranceProv.append(request.getParameter("insuranceProv"));
          patientID = Integer.valueOf(Integer.parseInt(request.getParameter("patientID")));

          result = dbcontroller.updatePatientInsurance(patientID,insuranceProv.toString());
          out.write("<br/>");
          out.write(result);
       }
       
       dbcontroller.Close();
       %>
  </div>
</body>
</html>
