<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete Procedure</title>
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

            Vector<String> vecResult = dbcontroller.FindAllProcedures();
            if (vecResult == null) {
                content.append("Query result is null!");
            }

            content.append("<tr><th>ProcedureNo</th><th>Name</th><th>Cost</th>");

            if (vecResult != null && vecResult.size() > 0) {
                for (int i=0; i<vecResult.size(); i++) {
                    String row = vecResult.get(i);
                    String[] detail = row.split("##");
                
                    content.append("<tr id=\"tablerow_>" + i + "\">");
                    content.append("<td class=\"postlist\"><b>" + detail[0] + "</b></td>");
                    content.append("<td>"
                        + "<b>" + detail[1] + "</b></td>");
                    content.append("<td>" + detail[2] + "</td>"
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
         <h2>Procedure Deletion Tables</h2>
         <div>
         <form action="deleteActions/deleteproceduretable.jsp" method="GET">
                Procedure(Delete using ProcedureNo): <br>
                <input type="text" name="procedureid"><br>
                <input type="submit" value="Submit">
        </form>
    </div>

     <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerpa = new DatabaseController();

            dbcontrollerpa.Open();

            StringBuffer contentpa = new StringBuffer();
            contentpa.append("<br/><table>");

            Vector<String> vecResultpa = dbcontrollerpa.FindAllAppointmentProcedures();
            if (vecResultpa == null) {
                contentpa.append("Query result is null!");
            }

            contentpa.append("<tr><th>AppointmentNo</th><th>ProcedureNo</th>");

            if (vecResultpa != null && vecResultpa.size() > 0) {
                for (int i=0; i<vecResultpa.size(); i++) {
                    String row = vecResultpa.get(i);
                    String[] detail = row.split("##");
                
                    contentpa.append("<tr id=\"tablerow_>" + i + "\">");
                    contentpa.append("<td class=\"postlist\"><b>" + detail[0] + "</b></td>");
                    contentpa.append("<td>"
                        + "<b>" + detail[1] + "</b></td>");
                    contentpa.append("</tr>");
                }
            }
            out.write(contentpa.toString());
            dbcontrollerpa.Commit();
            dbcontrollerpa.Close();
        %>
    </div>
         <br>
         <br>
         <h2>Appointment Procedure Deletion Table</h2>
    <div>
         <form action="deleteActions/deleteprocedureappointment.jsp" method="GET">
                
                Appointment(Delete using AppointmentNo): <br>
                <input type="text" name="appointmentid"><br>
                Procedure(Delete using ProcedureNo): <br>
                <input type="text" name="procedureid"><br>
                <input type="submit" value="Submit">
        </form>
    </div>

      <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerpl = new DatabaseController();

            dbcontrollerpl.Open();

            StringBuffer contentpl = new StringBuffer();
            contentpl.append("<br/><table>");

            Vector<String> vecResultpl = dbcontrollerpl.FindAllProcedureLengths();
            if (vecResultpl == null) {
                contentpl.append("Query result is null!");
            }

            contentpl.append("<tr><th>ProcedureNo</th><th>Length</th>");

            if (vecResultpl != null && vecResultpl.size() > 0) {
                for (int i=0; i<vecResultpl.size(); i++) {
                    String row = vecResultpl.get(i);
                    String[] detail = row.split("##");
                
                    contentpl.append("<tr id=\"tablerow_>" + i + "\">");
                    contentpl.append("<td class=\"postlist\"><b>" + detail[0] + "</b></td>");
                    contentpl.append("<td>"
                        + "<b>" + detail[1] + "</b></td>");
                    contentpl.append("</tr>");
                }
            }
            out.write(contentpl.toString());
            dbcontrollerpl.Commit();
            dbcontrollerpl.Close();
        %>
    </div>
         <br>
         <br>
         <h2>Procedure Length Deletion Table</h2>
    <div>
         <form action="deleteActions/deleteprocedurelength.jsp" method="GET">
                Procedure(Delete using ProcedureNo): <br>
                <input type="text" name="procedurelengthid"><br>
                <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
