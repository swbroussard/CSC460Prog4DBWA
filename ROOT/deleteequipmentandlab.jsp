<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete Equipment and Lab</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="scripts/jspCaller.js"></script>
</head>
<body>
  <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            out.write("<h2>Equipment Deletion Tables</h2>");

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            Vector<String> vecResult = dbcontroller.FindAllEquipment();
            if (vecResult == null) {
                content.append("Query result is null!");
            }

            content.append("<tr><th>EquipmentNo</th><th>Name</th><th>Cost</th>");

            if (vecResult != null && vecResult.size() > 0) {
                for (int i=0; i<vecResult.size(); i++) {
                    String row = vecResult.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 3) {
                    }
                
                    content.append("<tr id=\"tablerow_>" + i + "\">");
                    content.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    content.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
                    content.append("<td>" + detail[2] + "</td>"
                        + "<td>");
                    content.append("</tr>");
                }
            }
            out.write(content.toString());
            dbcontroller.Commit();
            dbcontroller.Close();
        %>
    </div>
         <br>
         <br>
         <div>
         <form action="deleteActions/deleteequipment.jsp" method="GET">
                Equipment(Delete using EquipmentNo): <br>
                <input type="text" name="equipmentId"><br>
                <input type="submit" value="Submit">
        </form>
    </div>

     <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerle = new DatabaseController();

            dbcontrollerle.Open();

           

            StringBuffer contentle = new StringBuffer();
            contentle.append("<br/><table>");

            Vector<String> vecResultle = dbcontrollerle.FindAllLabEquipment();
            if (vecResultle == null) {
                contentle.append("Query result is null!");
            }

            contentle.append("<tr><th>LabNo</th><th>EquipmentNo</th>");

            if (vecResultle != null && vecResultle.size() > 0) {
                for (int i=0; i<vecResultle.size(); i++) {
                    String row = vecResultle.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 3) {
                    }
                
                    contentle.append("<tr id=\"tablerow_>" + i + "\">");
                    contentle.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    contentle.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
                    contentle.append("</tr>");
                }
            }
            out.write(contentle.toString());
            dbcontrollerle.Commit();
            dbcontrollerle.Close();
        %>
    </div>
         <br>
         <br>
         <h2> Lab-Equipment Tables</h2>
         <div>
         <form action="deleteActions/deletelabequipment.jsp" method="GET">
                Equipment(Delete using EquipmentNo): <br>
                <input type="text" name="equipmentId"><br>
                Lab(Delete using EquipmentNo): <br>
                <input type="text" name="labId"><br>
                <input type="submit" value="Submit">
        </form>
    </div>

    <div id="resultlab">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerla = new DatabaseController();

            dbcontrollerla.Open();

            

            StringBuffer contentla = new StringBuffer();
            contentla.append("<br/><table>");

            Vector<String> vecResultla = dbcontrollerla.FindAllLab();
            if (vecResultla == null) {
                contentla.append("Query result is null!");
            }

            contentla.append("<tr><th>LabNo</th><th>Name</th>");

            if (vecResultla != null && vecResultla.size() > 0) {
                for (int i=0; i<vecResultla.size(); i++) {
                    String row = vecResultla.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 3) {
                    }
                
                    contentla.append("<tr id=\"tablerow_>" + i + "\">");
                    contentla.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    contentla.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
                    contentla.append("</tr>");
                }
            }
            out.write(contentla.toString());
            dbcontrollerla.Commit();
            dbcontrollerla.Close();
        %>
    </div>
         <br>
         <br>
         <h2> Lab Table </h2>
         <div>
         <form action="deleteActions/deletelab.jsp" method="GET">
                Lab(Delete using LabNo): <br>
                <input type="text" name="labId"><br>
                <input type="submit" value="Submit">
        </form>
    </div>

     <div id="resultProcedureEquipment">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerpe = new DatabaseController();

            dbcontrollerpe.Open();

            

            StringBuffer contentpe = new StringBuffer();
            contentpe.append("<br/><table>");

            Vector<String> vecResultpe = dbcontrollerpe.FindAllProcedureEquipment();
            if (vecResultpe == null) {
                contentpe.append("Query result is null!");
            }

            contentpe.append("<tr><th>ProcedureNo</th><th>EquipmentNo</th>");

            if (vecResultpe != null && vecResultpe.size() > 0) {
                for (int i=0; i<vecResultpe.size(); i++) {
                    String row = vecResultpe.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 3) {
                    }
                
                    contentpe.append("<tr id=\"tablerow_>" + i + "\">");
                    contentpe.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    contentpe.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
                    contentpe.append("</tr>");
                }
            }
            out.write(contentpe.toString());
            dbcontrollerpe.Commit();
            dbcontrollerpe.Close();
        %>
    </div>
         <br>
         <br>
         <h2> Procedure-Equipment Table </h2>
         <div>
         <form action="deleteActions/deleteequipment.jsp" method="GET">
                Equipment(Delete using EquipmentNo): <br>
                <input type="text" name="equipmentId"><br>
                <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
