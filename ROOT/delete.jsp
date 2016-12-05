<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="scripts/jspCaller.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		DB Dentistry
	</header>
  <div id="result">
  <a href="index.html">Return to home page.</a>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

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
            dbcontroller.Commit();
            dbcontroller.Close();
        %>
    </div>
         <br>
         <br>
         <h2>Patient Deletion Table</h2>
         <div>
         <form action="deleteActions/deletepatient.jsp" method="GET">
                Patient(Delete using PatientNo): <br>
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
            dbcontrollerpn.Commit();
            dbcontrollerpn.Close();
        %>
    </div>
     <div>
         <br>
        <br>
        <h2>Patient Names Table</h2>
         <form action="deleteActions/deletepatientname.jsp" method="GET">
                Patient Name(Delete using PatientNo): <br>
                <input type="text" name="patientnameid"><br>
                <input type="submit" value="Submit">
        </form>
    </div>
     <div id="resultInsurance">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontrollerpi = new DatabaseController();

            dbcontrollerpi.Open();

            StringBuffer contentpi = new StringBuffer();
            contentpi.append("<br/><table>");

            Vector<String> vecResultpi = dbcontrollerpi.FindAllPatientInsurance();
            if (vecResultpi == null) {
                contentpi.append("Query result is null!");
            }

            contentpi.append("<tr><th>PatientNo</th><th>InsuranceProv</th>");

            if (vecResultpi != null && vecResultpi.size() > 0) {
                for (int i=0; i<vecResultpi.size(); i++) {
                    String row = vecResultpi.get(i);
                    String[] detail = row.split("##");
                    if (detail.length != 3) {
                    }
                
                    contentpi.append("<tr id=\"tablerow_>" + i + "\">");
                    contentpi.append("<td class=\"postlist\"><a href=\"javascript:void(0)\" "
                        + "\"><b>" + detail[0] + "</b></a></td>");
                    contentpi.append("<td><a href=\"javascript:void(0)\" >"
                        + "<b>" + detail[1] + "</b></a></td>");
                    contentpi.append("</tr>");
                }
            }
            out.write(contentpi.toString());
            dbcontrollerpi.Commit();
            dbcontrollerpi.Close();
        %>
    </div>
     <div>
         <br>
         <br>
         <h2>Patient Insurance Tables</h2>
         <form action="deleteActions/deletepatientinsurance.jsp" method="GET">
                Patient Insurance(Delete using PatientNo): <br>
                <input type="text" name="patientinsurancenumber"><br>
                <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
