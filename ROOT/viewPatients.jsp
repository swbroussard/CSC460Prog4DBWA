<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Find Patients</title>
    <meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		DB Dentistry
	</header>
    <a href="index.html">Return to homepage.</a>
    <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            StringBuffer content = new StringBuffer();
            content.append("<br/>");

            content.append("<table>");

            Vector<String> vecResult =
                dbcontroller.FindAllPatients();

            if (vecResult == null) {
                content.append("Query result is null!");
            }

            content.append("<tr><th>PatientNo</th><th>Next Visit</th>"
                + "<th>Outstanding Cost</th><th>Address</th></tr>");

            if (vecResult != null && vecResult.size() > 0) {
                for (int i = 0; i < vecResult.size(); i++) {
                    String row = vecResult.get(i);
                    String[] detail = row.split("##");

                    content.append("<tr><td>" + detail[0] + "</td>" 
                        + "<td>" + detail[1] + "</td>"
                        + "<td>" + detail[2] + "</td>"
                        + "<td>" + detail[3] + "</td></tr>");
                }
            }

            out.write(content.toString());
            dbcontroller.Close();
        %>
    </div>
</body>
</html>
