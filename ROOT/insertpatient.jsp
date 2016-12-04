<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Insert Patient</title>
    <meta charset="UTF-8"/>
</head>
<body>
    <a href="index.html">Return to homepage.</a>
    <div id="result"> 
        <% 
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            String givenname = request.getParameter("givenname");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String insuranceProv = request.getParameter("insuranceProv");

            String res = dbcontroller.insertPatient(givenname, surname, address,
                insuranceProv);

            if (res == null) {
                content.append("Failed to insert into the database!");
            }

            content.append("<tr><th>PatientNo</th><th>FirstName</th><th>LastName</th>"
                + "<th>NextVisit</th><th>OutstandingCost</th>"
                + "<th>Address</th><th>InsuranceProv.</th>");
            
            if (res != null) {
                String[] detail = res.split("##");

                content.append("<tr>");
                content.append("<td>" + detail[0] + "</td>");
                content.append("<td>" + detail[1] + "</td>");
                content.append("<td>" + detail[2] + "</td>");
                content.append("<td>" + detail[3] + "</td>");
                content.append("<td>" + detail[4] + "</td>");
                content.append("<td>" + detail[5] + "</td>");
                content.append("<td>" + detail[6] + "</td>");
                content.append("</tr>");
            }
            

            content.append("</table>");
            out.write(content.toString());
            dbcontroller.Close();

        %>
    </div>
</body>
</html>
