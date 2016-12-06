<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="scripts/jspCaller.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		DB Dentistry
	</header>
  <div id="result">
  <a href="index.html">Return to home page.</a>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            Vector<String> vecResult = dbcontroller.FindAllAppointments();
            if (vecResult == null) {
                content.append("Query result is null!");
            }

            content.append("<tr><th>AppointmentNo</th><th>PatientNo</th><th>Day</th>");

            if (vecResult != null && vecResult.size() > 0) {
                for (int i=0; i<vecResult.size(); i++) {
                    String row = vecResult.get(i);
                    String[] detail = row.split("##");
                
                    content.append("<tr id=\"tablerow_>" + i + "\">");
                    content.append("<td class=\"postlist\"><b>" + detail[0] + "</b></td>");
                    content.append("<td>"
                        + "<b>" + detail[1] + "</b></td>");
                    content.append("<td>" + detail[2].split(" ")[0] + "</td>"
                        + "<td>");
                    content.append("</tr>");
                }
            }
            out.write(content.toString());
            dbcontroller.Commit();
            dbcontroller.Close();
        %>
    </div>
         <br>
         <br>
         <h2>Appointment Deletion Tables</h2>
         <div>
         <form action="deleteActions/deleteappointmentpatient.jsp" method="GET">
                Both fields required to delete an entry. <br><br>
                Appointment(Delete using AppointmentNo): <br>
                <input type="text" name="appointmentid"><br>
                Patient(Delete using PatientNo): <br>
                <input type="text" name="patientid"><br>
                <input type="submit" value="Submit">
        </form>
    </div>
    
   
   
    
</body>
</html>
