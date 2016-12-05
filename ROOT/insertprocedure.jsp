<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Insert Procedure</title>
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

        boolean invalidInput = false;
        Integer cost = 0;
        Float length = new Float(0.0);
        try {
            cost = Integer.parseInt(request.getParameter("cost"));
        } catch (NumberFormatException e) {
            content.append("<br/>Not a valid number for 'cost'. Please try again.");
            invalidInput = true;
        }

        try {
            length = Float.parseFloat(request.getParameter("length"));
        } catch (NumberFormatException e) {
            content.append("<br/>Not a valid number for 'length'. "
                + "Please try again.");
            invalidInput = true;
        }
        String[] equipment = request.getParameterValues("id");

        if (!invalidInput) {
            String result = dbcontroller.insertProcedure(name, cost, length, equipment);

            if (result == null) {
                content.append("<br/>Failed to insert!");
            } else {
                content.append("<br/><table><tr><th>Procedure No.</th>"
                    + "<th>Name</th><th>Cost</th><th>Length</th>"
                    + "<th>Equipment Nos.</th></tr>");

                String[] detail = result.split("##");

                String equipmentnos = "";
                for (int i = 0; i < equipment.length; i++) {
                    if (i != equipment.length - 1)
                        equipmentnos += equipment[i] + ", ";
                    else
                        equipmentnos += equipment[i];
                }

                if (equipmentnos.length() == 0)
                    equipmentnos = "null";

                content.append("<tr><td>" + detail[0] + "</td>"
                    + "<td>" + detail[1] + "</td>"
                    + "<td>" + detail[2] + "</td>"
                    + "<td>" + detail[3] + "</td>"
                    + "<td>" + equipmentnos + "</td>");
            }

        }

        out.write(content.toString());
        dbcontroller.Close();
    %>
</body>
</html>
