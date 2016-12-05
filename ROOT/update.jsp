<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>update</title>
    <meta charset="UTF-8"/>
</head>
<body>
    <div id="result">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();
	   --need to make hyper links to the different pages for the different updates about patient--
	   --Then on each page, use input type tags and pass the informtion into the appropriate functions from the DatabaseController instance variable--
	   --Also on each page, make sure to use NULL variable set up from the DatabaseController class fucntions--
            out.write("<h2>Update Patient</h2>");

	   StringBuffer webContent = new StringBuffer();
	   
	   --Beginning of a tables--
	   webContent.append("<br/><table><tr>");
	   
	   --Link to update patient information; nextVisit outstanding balance, and address--
	   webContent.append("<th><a href=./update_patient_information.jsp>Update Patient Information</a><\th>");

	   --Link to update patient name; given name and surname or family name--
	   webContent.append("<th><a href=./update_patient_name.jsp>Update Patient Name</a><\th>");
	   
	   --Link to update patient insurance provider--
	   webContent.append("<th><a href=./update_patient_insurance.jsp>Update Patient Insurance Provider</a><\th>");

	   --Ending the table tag--
	   webContent.append("<\table>");

            --StringBuffer content = new StringBuffer();
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
            out.write(content.toString());--

            out.write(webContent.toString());

            dbcontroller.Close();
        %>
    </div>
</body>
</html>
