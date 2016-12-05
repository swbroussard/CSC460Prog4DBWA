<!DOCTYPE html>
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<head>
    <title>Insert</title>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		DB Dentistry
	</header>
    <a href="index.html">Return to homepage.</a>
    <div id="patient">
        <h3>Insert a new patient</h3>
        <form action="insertpatient.jsp" method="GET">
            First name: <br>
            <input type="text" name="givenname"><br>
            Last name: <br>
            <input type="text" name="surname"><br>
            Address: <br>
            <input type="text" name="address"><br>
            Insurance Provider: <br>
            <input type="text" name="insuranceProv"><br>
            <input type="submit" value="Submit">
        </form>
    </div><br/>
    <div id="appointment">
        <h3>Insert a new appointment</h3>
        <form action="insertappointment.jsp" method="GET">
            Patient:
            <%
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");

                DatabaseController dbcontroller = new DatabaseController();

                dbcontroller.Open();

                StringBuffer content = new StringBuffer();

                Vector<String> result = dbcontroller.FindAllPatients();

                if (result == null) {
                    content.append("Unable to find any patients!");
                }

                if (result != null && result.size() > 0) {
                    content.append("<select name=\"patientno\">");

                    for (int i = 0; i < result.size(); i++) {
                        String tmp = result.get(i);
                        String patientNo = tmp.split("##")[0];

                        content.append("<option value='" + patientNo
                            + "'>" + patientNo + "</option>");
                    }
                    content.append("</select>");
                }

                out.write(content.toString());
                dbcontroller.Close();
            %>
            <br/><br/>
            Date: 
            <select name='day' id='dayddl'>
            <option value='1'>1</option>
            <option value='2'>2</option>
            <option value='3'>3</option>
            <option value='4'>4</option>
            <option value='5'>5</option>
            <option value='6'>6</option>
            <option value='7'>7</option>
            <option value='8'>8</option>
            <option value='9'>9</option>
            <option value='10'>10</option>
            <option value='11'>11</option>
            <option value='12'>12</option>
            <option value='13'>13</option>
            <option value='14'>14</option>
            <option value='15'>15</option>
            <option value='16'>16</option>
            <option value='17'>17</option>
            <option value='18'>18</option>
            <option value='19'>19</option>
            <option value='20'>20</option>
            <option value='21'>21</option>
            <option value='22'>22</option>
            <option value='23'>23</option>
            <option value='24'>24</option>
            <option value='25'>25</option>
            <option value='26'>26</option>
            <option value='27'>27</option>
            <option value='28'>28</option>
            <option value='29'>29</option>
            <option value='30'>30</option>
            <option value='31'>31</option>
            </select>

            Month
            <select name='month' id='monthddl'>
            <option value='1'>1</option>
            <option value='2'>2</option>
            <option value='3'>3</option>
            <option value='4'>4</option>
            <option value='5'>5</option>
            <option value='6'>6</option>
            <option value='7'>7</option>
            <option value='8'>8</option>
            <option value='9'>9</option>
            <option value='10'>10</option>
            <option value='11'>11</option>
            <option value='12'>12</option>
            </select>

            Year
            <select name='year' id='blah'>
            <option value='2016'>2016</option>
            <option value='2017'>2017</option>
            </select>
            <br/><br/>

            Procedure:
            <%
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");

                dbcontroller = new DatabaseController();

                dbcontroller.Open();

                content = new StringBuffer();

                result = dbcontroller.ListAllProcedures();

                if (result == null) {
                    content.append("Query for procedures is null!");
                }

                if (result != null && result.size() > 0) {
                    content.append("<select name=\"procedureno\">");
                    for (int i = 0; i < result.size(); i++) {
                        String[] detail = result.get(i).split("##");

                        content.append("<option value='" + detail[0] + "'>"
                            + detail[1] + " (" + detail[0] + ")</option>");
                    }
                    content.append("</select>");
                }

                out.write(content.toString());
                dbcontroller.Close();
            %>
            <br/><br/>
            <input type="submit" value="Submit">
        </form>
    </div>
    <div class="lab">
        <br/>
        <h3>Insert a new lab</h3>
        <form action="insertlab.jsp" method="GET">
        Lab Name:
        <input type="text" name="name"><br/><br/>
        Equipment:</br>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            dbcontroller = new DatabaseController();

            dbcontroller.Open();

            content = new StringBuffer();

            result = dbcontroller.ListAllEquipment();

            if (result == null) {
                content.append("Query returned null!");
            } else if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    // Detail[0] = EquipmentNo, Deatil[1] = EquipmentName
                    String[] detail = result.get(i).split("##");
                    content.append("<input type=\"checkbox\" name=\"id\" "
                        + "value=\"" + detail[0] +  "\">" + detail[1] + "</input><br/>");
                }
            }

            out.write(content.toString());
            dbcontroller.Close();
        %>
        <input type="submit" value="Submit">
        </form>
    </div>
    <div class="equipment">
        <br/>
        <h3>Insert a new piece of equipment</h3>
        <form action="insertequipment.jsp" method="GET">
            Equipment Name:
            <input type="text" name="name"><br/><br/>
            Cost (Whole Dollar):
            <input type="text" name="cost"><br/><br/>
            <input type="submit" value="Submit"><br/>
        </form>
    </div>
    <div class="procedure">
        <br/>
        <h3>Insert a new procedure</h3>
        <form action="insertprocedure.jsp" method="GET">
            Procedure Name:
            <input type="text" name="name"><br/><br/>
            Cost (Whole Dollar): 
            <input type="text" name="cost"><br/><br/>
            Length (In hours):
            <input type="text" name="length"><br/><br/>
            
            Equipment:</br>
            <%
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
    
                dbcontroller = new DatabaseController();
    
                dbcontroller.Open();
    
                content = new StringBuffer();
    
                result = dbcontroller.ListAllEquipment();
    
                if (result == null) {
                    content.append("Query returned null!");
                } else if (result != null && result.size() > 0) {
                    for (int i = 0; i < result.size(); i++) {
                        // Detail[0] = EquipmentNo, Deatil[1] = EquipmentName
                        String[] detail = result.get(i).split("##");
                        content.append("<input type=\"checkbox\" name=\"id\" "
                            + "value=\"" + detail[0] +  "\">" + detail[1] + "</input><br/>");
                    }
                }
    
                out.write(content.toString());
                dbcontroller.Close();
            %>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
