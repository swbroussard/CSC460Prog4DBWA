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

            String procedureid = request.getParameter("procedureid");
            //Integer procedureid = Integer.parseInt(givenname);
            out.println(procedureid);
           

            String res = dbcontroller.Delete(procedureid, "Procedure", "procedureNo");
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
