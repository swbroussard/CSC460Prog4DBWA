<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete Records</title>
    <meta charset="UTF-8"/>
</head>
<body>
    <a href="../index.html">Return to home page.</a>
    <div id="result"> 
        <% 
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            String patientID = request.getParameter("patientnameid");
            //Integer patientID = Integer.parseInt(givenname);
            out.println(patientID);
           

            String res = dbcontroller.Delete(patientID, "PatientName", "patientNo");
            out.println(res);
            if (res == null) {
                content.append("Failed to insert into the database!");
            }

            dbcontroller.Commit();
            dbcontroller.Close();

        %>
    </div>
</body>
</html>
