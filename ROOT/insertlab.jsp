<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController"%>
<head>
    <title>Insert Lab</title>
    <meta charset="UTF-8">
</head>
<body>
    <a href="index.html">Return to homepage.</a>

    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        DatabaseController dbcontroller = new DatabaseController();

        dbcontroller.Open();

        StringBuffer content = new StringBuffer();

        String name = request.getParameter("name");
        String equipment[] = request.getParameterValues("id");

        String result = dbcontroller.insertLab(name, equipment);

        if (result == null) {
            content.append("Query returned null!");
        } else {
            content.append("<br/><table>"
                + "<tr><th>Lab No.</th><th>Lab Name</th>"
                + "<th>Equipment No's</th></tr>");
            String[] detail = result.split("##");

            String equipmentnos = "";
            for (int i = 0; i < equipment.length - 1; i++) {
                equipmentnos += equipment[i] + ", ";
            }

            equipmentnos += equipment[equipment.length - 1];
            content.append("<tr><td>" + detail[0] + "</td>"
                + "<td>" + detail[1] + "</td><td>" + equipmentnos + "</td></tr>");
            content.append("</table>") ;
        }

        out.write(content.toString());
        dbcontroller.Close();

    %>
</body>
</html>
