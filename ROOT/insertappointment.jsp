<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Insert Appointment</title>
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
//            content.append("<br/><table>");

            String patientNo = request.getParameter("patientno");
            String day = request.getParameter("day");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String procedureNo = request.getParameter("procedureno");

            int dayAsInt = Integer.parseInt(day);
            int monthAsInt = Integer.parseInt(month);
            int yearAsInt = Integer.parseInt(year);

            // Make sure that date provided is valid.
            boolean validDate= true;
            if ((dayAsInt > 30 && (monthAsInt == 2 || monthAsInt == 4 ||
                    monthAsInt == 6 || monthAsInt == 9 || monthAsInt == 11))
                    || (dayAsInt > 28 && monthAsInt == 2 && yearAsInt % 4 != 0)) {
                validDate = false; 
            }

            String monthString = null;
            switch (Integer.parseInt(month)) {
                case 1:
                    monthString = "Jan";
                    break;
                case 2:
                    monthString = "Feb";
                    break;
                case 3:
                    monthString = "Mar";
                    break;
                case 4:
                    monthString = "Apr";
                    break;
                case 5:
                    monthString = "May";
                    break;
                case 6:
                    monthString = "Jun";
                    break;
                case 7:
                    monthString = "Jul";
                    break;
                case 8:
                    monthString = "Aug";
                    break;
                case 9:
                    monthString = "Sep";
                    break;
                case 10:
                    monthString = "Oct";
                    break;
                case 11:
                    monthString = "Nov";
                    break;
                case 12:
                    monthString = "Dec";
                    break;
            }

            content.append(patientNo + " " + day + " " + monthString + " " + year 
                + " " + procedureNo);

            if (!validDate) {
                content.append("\nInvalid date!");
            }

            out.write(content.toString());
            dbcontroller.Close();
        %>
</body>
</html>
