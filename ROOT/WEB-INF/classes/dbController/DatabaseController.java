package dbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.*;

/**
 * Servlet implementation class for Servlet: DatabaseController
 *
 */
public class DatabaseController {
  static final long serialVersionUID = 1L;
  /**
   * A handle to the connection to the DBMS.
   */
  protected Connection connection_;
  /**
   * A handle to the statement.
   */
  protected Statement statement_;
  /**
   * The connect string to specify the location of DBMS
   */
  protected String connect_string_ = null;
  /**
   * The password that is used to connect to the DBMS.
   */
  protected String password = null;
  /**
   * The username that is used to connect to the DBMS.
   */
  protected String username = null;


  public DatabaseController() {
    // your cs login name
    username = "username"; 
    // your Oracle password, NNNN is the last four digits of your CSID
    password = "password";
    connect_string_ = "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
  }


  /**
   * Closes the DBMS connection that was opened by the open call.
   */
  public void Close() {
    try {
      statement_.close();
      connection_.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    connection_ = null;
  }


  /**
   * Commits all update operations made to the dbms.
   * If auto-commit is on, which is by default, it is not necessary to call
   * this method.
   */
  public void Commit() {
    try {
      if (connection_ != null && !connection_.isClosed())
        connection_.commit();
    } catch (SQLException e) {
      System.err.println("Commit failed");
      e.printStackTrace();
    }
  }

    public void Open() {
	try {
	    Class.forName("oracle.jdbc.OracleDriver");
	    connection_ = DriverManager.getConnection(connect_string_, username, password);
	    statement_ = connection_.createStatement();
	    return;
	} catch (SQLException sqlex) {
	    sqlex.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    System.exit(1); //programemer/dbsm error
	} catch (Exception ex) {
	    ex.printStackTrace();
	    System.exit(2);
	}
    }


  public Vector<String> FindAllPatients() {
    String sql_query = "SELECT * FROM cameronsmith.Patient";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_patients = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("patientNo") + "##" 
            + rs.getString("nextVisit") + "##" + rs.getString("outstandingCost") 
            + "##" + rs.getString("address"); 
        result_patients.add(temp_record);
      }
      return result_patients;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }

    public String insertPatient(String givenname, String surname, String address,
            String insuranceProv) {
        String patNo = "SELECT patientNo FROM cameronsmith.Patient";
        System.out.println("HERE");

        Integer max = 0; // Max patient number.
        try {
            ResultSet rs = statement_.executeQuery(patNo);

            while(rs.next()) {
                Integer currNo = new Integer(rs.getString("patientNo"));
                if (currNo > max) {
                    max = currNo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Insert into Patient related tables.
        Integer nextPatNo = max + 1;
        String insertUpdate = "INSERT INTO cameronsmith.Patient VALUES (" +
            (nextPatNo).toString() + ", NULL, NULL, " + address + ")";

        String insertName = "INSERT INTO cameronsmith.PatientName VALUES (" +
            (nextPatNo).toString() + ", " + givenname + ", " + surname + ")";

        String insertInsurance = "INSERT INTO cameronsmith.PatientInsurance VALUES (" +
            (nextPatNo).toString() + ", " + insuranceProv + ")";

        try {
            statement_.executeUpdate(insertUpdate);
            connection_.commit();
            statement_.executeUpdate(insertName);
            connection_.commit();
            statement_.executeUpdate(insertInsurance);
            connection_.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the Patient that was just inserted.
        String toReturn = null;

        try {
            ResultSet rs = statement_.executeQuery("SELECT * "
                + "FROM cameronsmith.Patient, cameronsmith.PatientName, "
                + "cameronsmith.PatientInsurance"
                + " WHERE "
                + "cameronsmith.Patient.patientNo=cameronsmith.PatientName.patientNo"
                + " AND "
                + "cameronsmith.Patient.patientNo=cameronsmith.PatientInsurance.patientNo"
                + " AND "
                + "cameronsmith.Patient.patientNo=" + (nextPatNo).toString());

            if (rs.next()) {
                toReturn = rs.getString("patientNo") + "##"
                    + rs.getString("givenname") + "##"
                    + rs.getString("surname") + "##"
                    + rs.getString("nextVisit") + "##"
                    + rs.getString("outstandingCost") + "##"
                    + rs.getString("address") + "##"
                    + rs.getString("insuranceProv");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Vector<String> CommonProcedure() {
        
        String sqlQuery = "SELECT name, count(name) as occurence "
            + "FROM cameronsmith.Procedure p, cameronsmith.AppointmentProcedure ap, "
            + "cameronsmith.Appointment a "
            + "WHERE p.procedureno=ap.procedureno "
            + "  AND ap.appointmentno=a.appointmentno "
            + "  AND extract(YEAR from day)=2016 "
            + "GROUP BY name "
            + "ORDER BY occurence DESC";

        Vector<String> result = new Vector<String>();
        try {
            ResultSet rs = statement_.executeQuery(sqlQuery);

            while (rs.next()) {
                String tmp = rs.getString("name") + "##"
                    + rs.getString("occurence");
                result.add(tmp);
            }

            return result;
        } catch (SQLException e) {
            result.add("ERROR##ERROR");
        }

        
        return result;
    }
}
