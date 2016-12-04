<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete Procedure</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="scripts/jspCaller.js"></script>
</head>
<body>
  <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            out.write("<h2>Procedure Deletion Tables</h2>");

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
                    if (detail.length != 3) {
                    }
                
                    content.append("<tr id=\"tablerow_>" + i + "\">");
                    content.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    content.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
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

            out.write("<h2>Appointment Procedure Deletion Table</h2>");

            StringBuffer contentpa = new StringBuffer();
            contentpa.append("<br/><table>");

            Vector<String> vecResultpa = dbcontrollerpa.FindAllAppointmentProcedures();
            if (vecResultpa == null) {
                contentpa.append("Query result is null!");
            }

            contentpa.append("<tr><th>ProcedureNo</th><th>Name</th><th>Cost</th>");

            if (vecResultpa != null && vecResultpa.size() > 0) {
                for (int i=0; i<vecResultpa.size(); i++) {
                    String row = vecResultpa.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 3) {
                    }
                
                    contentpa.append("<tr id=\"tablerow_>" + i + "\">");
                    contentpa.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    contentpa.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
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

</body>
</html>
