<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Delete Records</title>
    <meta charset="UTF-8"/>
</head>
<body>
    <a href="../index.html">Return to home page.</a>
    <div id="resultAppointmentPatient"> 
        <% 
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            DatabaseController dbcontroller = new DatabaseController();

            dbcontroller.Open();

            StringBuffer content = new StringBuffer();
            content.append("<br/><table>");

            String appointmentID = request.getParameter("appointmentid");
           // Integer appointmentID = Integer.parseInt(givenname);
            String patientID = request.getParameter("patientid");
            //Integer patientID = Integer.parseInt(givenPatientNo);
            out.println(appointmentID + " " + patientID);
           

            String res = dbcontroller.DeleteMulti(appointmentID, patientID, "Appointment", "appointmentNo", "patientNo");
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
