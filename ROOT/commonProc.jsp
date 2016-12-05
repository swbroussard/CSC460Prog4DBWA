<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Most Common Procedures of 2016</title>
    <meta charse="UTF-8"/>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		DB Dentistry
	</header>
    <a href="index.html">Return to homepage.</a>
    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        DatabaseController dbcontroller = new DatabaseController();

        dbcontroller.Open();

        StringBuffer content = new StringBuffer();
        content.append("<br/><table>");

        Vector<String> vecResult = dbcontroller.CommonProcedure();
        if (vecResult == null) {
            content.append("Query result is null!");
        }

        content.append("<tr><th>Procedure</th><th>Amount</th></tr>");

        if (vecResult != null && vecResult.size() > 0) {
            for (int i=0; i<vecResult.size(); i++) {
                String row = vecResult.get(i);
                String[] detail = row.split("##");

                content.append("<tr><td>" + detail[0]
                 + "</td><td>" + detail[1] + "</td></tr>");
            }
        }

        out.write(content.toString());
        dbcontroller.Close();
    %>
</body>
</html>
