<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Demo</title>
    <meta charset="UTF-8"/>
</head>
<body>
    <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            out.write("<h2>All Patients</h2>");

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            Vector<String> vecResult = dbcontroller.FindAllPatients();
            if (vecResult == null) {
                content.append("Query result is null!");
            }

            content.append("<tr><th>PatientNo</th><th>NextVisit</th><th>OutstandingCost</th>"
                + "<th>Address</th>");

            if (vecResult != null && vecResult.size() > 0) {
                for (int i=0; i<vecResult.size(); i++) {
                    String row = vecResult.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 5) {
                    }
                
                    content.append("<tr id=\"tablerow_>" + i + "\">");
                    content.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    content.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1].split(" ")[0] + "</b></a></td>");
                    content.append("<td>" + detail[2] + "</td>"
                        + "<td>" + detail[3] + "</td>");
                    content.append("</tr>");
                }
            }
            out.write(content.toString());

            dbcontroller.Close();
        %>
    </div>
</body>
</html>
