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

           String equipmentID = request.getParameter("equipmentId");
           // Integer equipmentID = Integer.parseInt(givenEquipmentname);
            String labID = request.getParameter("labId");
            //Integer labID = Integer.parseInt(givenLabNo);
            out.println(labID + " " + equipmentID);
           

            String res = dbcontroller.DeleteMulti(labID, equipmentID, "LabEquipment", "labNo", "equipmentNo");
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
