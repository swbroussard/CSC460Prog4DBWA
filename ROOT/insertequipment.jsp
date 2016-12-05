<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Insert Equipment</title>
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
        String cost = request.getParameter("cost");

        boolean costIsNumber = true;
        for (char c: cost.toCharArray()) {
            if (!Character.isDigit(c)) {
                content.append(cost + " is not a number! Please try again.");
                costIsNumber = false;
                break;
            }
        }

        if (costIsNumber) {
            String result = dbcontroller.insertEquipment(name, cost);

            if (result == null) {
                content.append("Insert returned null!");
            } else {
                content.append("<br/><table>");
                content.append("<tr><th>Equipment No.</th><th>Name</th>"
                    + "<th>Cost</th></tr>");

                String[] detail = result.split("##");

                content.append("<tr><td>" + detail[0] + "</td>"
                    + "<td>" + detail[1] + "</td><td>" + detail[2] + "</td></tr>");
                content.append("</table>");
             }
        }

        out.write(content.toString());
        dbcontroller.Close();
        
    %>
</body>
</html>
