<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete</title>
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

            out.write("<h2>Deletion Tables</h2>");

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            Vector<String> vecResult = dbcontroller.FindAllPatients();
            if (vecResult == null) {
                content.append("Query result is null!");
            }

            content.append("<tr><th>PatientNo</th><th>NextVisit</th><th>OutstandingCost</th>"
                + "<th>Address</th>");

            if (vecResult != null && vecResult.size() > 0) {
                for (int i=0; i<vecResult.size(); i++) {
                    String row = vecResult.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 5) {
                    }
                
                    content.append("<tr id=\"tablerow_>" + i + "\">");
                    content.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    content.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1].split(" ")[0] + "</b></a></td>");
                    content.append("<td>" + detail[2] + "</td>"
                        + "<td>" + detail[3] + "</td>");
                    content.append("</tr>");
                }
            }
            out.write(content.toString());

            dbcontroller.Close();
        %>
    </div>
    <div>
     <form action="deletepatient.jsp" method="GET">
            Patient ID: <br>
            <input type="text" name="patientid"><br>
            <input type="submit" value="Submit">
    </form>
    </div>

     <div id="resultNames">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerpn = new DatabaseController();

            dbcontrollerpn.Open();

            

            StringBuffer contentpn = new StringBuffer();
            contentpn.append("<br/><table>");

            Vector<String> vecResultpn = dbcontrollerpn.FindAllPatientNames();
            if (vecResultpn == null) {
                contentpn.append("Query result is null!");
            }

            contentpn.append("<tr><th>PatientNo</th><th>Givenname</th><th>surname</th>");

            if (vecResultpn != null && vecResultpn.size() > 0) {
                for (int i=0; i<vecResultpn.size(); i++) {
                    String row = vecResultpn.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 4) {
                    }
                
                    contentpn.append("<tr id=\"tablerow_>" + i + "\">");
                    contentpn.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    contentpn.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1].split(" ")[0] + "</b></a></td>");
                    contentpn.append("<td>" + detail[2] + "</td>");
                    contentpn.append("</tr>");
                }
            }
            out.write(contentpn.toString());

            dbcontrollerpn.Close();
        %>
    </div>
    <div>
     <form action="deletepatientname.jsp" method="GET">
            Patient Name ID: <br>
            <input type="text" name="patientnameid"><br>
            <input type="submit" value="Submit">
    </form>
    </div>
</body>
</html>
