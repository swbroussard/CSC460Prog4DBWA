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
  protected String password = "a5437";
  /**
   * The username that is used to connect to the DBMS.
   */
  protected String username = "cameronsmith";


  public DatabaseController() {
    // your cs login name
    username = "cameronsmith"; 
    // your Oracle password, NNNN is the last four digits of your CSID
    password = "a5437";
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

/*---------------------------------------------------------------------
    |  Author: 
  |
  |  Method getMostOwed()
    |
    |  Purpose: Find the 10 highest owing patients in the patients database
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
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

/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllPatientNames()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
  public Vector<String> FindAllPatientNames() {
    String sql_query = "SELECT * FROM cameronsmith.PatientName";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_patientnames = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("patientNo") + "##" 
            + rs.getString("givenname") + "##" + rs.getString("surname"); 
        result_patientnames.add(temp_record);
      }
      return result_patientnames;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }

/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllPatientInsurance()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
  public Vector<String> FindAllPatientInsurance() {
    String sql_query = "SELECT * FROM cameronsmith.PatientInsurance";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_patientinsurance = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("patientNo") + "##" 
            + rs.getString("insuranceProv"); 
        result_patientinsurance.add(temp_record);
      }
      return result_patientinsurance;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }

/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllAppointments()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
  public Vector<String> FindAllAppointments() {
    String sql_query = "SELECT * FROM cameronsmith.Appointment";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_appointment = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("appointmentNo") + "##" 
            + rs.getString("patientNo")+ "##" 
            + rs.getString("day"); 
        result_appointment.add(temp_record);
      }
      return result_appointment;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }

/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllProcedures()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
  public Vector<String> FindAllProcedures() {
    String sql_query = "SELECT * FROM cameronsmith.Procedure";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_procedure = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("procedureNo") + "##" 
            + rs.getString("name")+ "##" 
            + rs.getString("cost"); 
        result_procedure.add(temp_record);
      }
      return result_procedure;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllProcedures()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
    public Vector<String> FindAllProcedureLengths() {
    String sql_query = "SELECT * FROM cameronsmith.ProcedureLength";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_procedure = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("procedureNo") + "##" 
            + rs.getString("length"); 
        result_procedure.add(temp_record);
      }
      return result_procedure;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllProcedures()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
   public Vector<String> FindAllAppointmentProcedures() {
    String sql_query = "SELECT * FROM cameronsmith.AppointmentProcedure";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_appointmentprocedure = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("appointmentNo") + "##" 
            + rs.getString("procedureNo"); 
        result_appointmentprocedure.add(temp_record);
      }
      return result_appointmentprocedure;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllProcedures()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
  public Vector<String> FindAllEquipment() {
    String sql_query = "SELECT * FROM cameronsmith.Equipment";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_equipment = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("equipmentNo") + "##" 
            + rs.getString("name") + "##" 
            + rs.getString("cost") ; 
        result_equipment.add(temp_record);
      }
      return result_equipment;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllProcedures()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
   public Vector<String> FindAllProcedureEquipment() {
    String sql_query = "SELECT * FROM cameronsmith.ProcedureEquipment";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_procedureequipment = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("procedureNo") + "##" 
            + rs.getString("equipment"); 
        result_procedureequipment.add(temp_record);
      }
      return result_procedureequipment;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllLabEquipment()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
   public Vector<String> FindAllLabEquipment() {
    String sql_query = "SELECT * FROM cameronsmith.LabEquipment";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_labequipment = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("labNo") + "##" 
            + rs.getString("equipmentNo"); 
        result_labequipment.add(temp_record);
      }
      return result_labequipment;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindAllLab()
    |
    |  Purpose: Select all the information for this table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
  public Vector<String> FindAllLab() {
    String sql_query = "SELECT * FROM cameronsmith.Lab";
   try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_lab = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("labNo") + "##" 
            + rs.getString("name"); 
        result_lab.add(temp_record);
      }
      return result_lab;
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
            (nextPatNo).toString() + ", NULL, NULL, '" + address + "')";

        String insertName = "INSERT INTO cameronsmith.PatientName VALUES (" +
            (nextPatNo).toString() + ", '" + givenname + "', '" + surname + "')";

        String insertInsurance = "INSERT INTO cameronsmith.PatientInsurance VALUES (" +
            (nextPatNo).toString() + ", '" + insuranceProv + "')";

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

    public String insertAppointment(String patientno, String date, 
            String procedureno) {

        String maxAppNo = "SELECT MAX(appointmentno) "
            + "FROM cameronsmith.Appointment";

        Integer nextAppNo = null;
        try {
            ResultSet rs = statement_.executeQuery(maxAppNo);

            rs.next();
            nextAppNo = new Integer(rs.getString("max(appointmentno)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertAppointment = "INSERT INTO cameronsmith.Appointment "
            + "VALUES (" + (nextAppNo + 1) + ", " + patientno + ", '" + date + "')";
        String insertAppProc = "INSERT INTO cameronsmith.AppointmentProcedure "
            + "VALUES (" + (nextAppNo + 1) + ", " + procedureno + ")";

        try {
            statement_.executeUpdate(insertAppointment);
            statement_.executeUpdate(insertAppProc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String newApp = "SELECT a.appointmentno, patientno, day, procedureno "
            + "FROM cameronsmith.Appointment a, cameronsmith.AppointmentProcedure ap "
            + "WHERE a.appointmentno=ap.appointmentno"
            + "  AND a.appointmentno=" + (nextAppNo + 1);
        try {
            ResultSet rs = statement_.executeQuery(newApp);

            rs.next();
            return rs.getString("appointmentno") + "##"
                + rs.getString("patientno") + "##" + rs.getString("day") + "##"
                + rs.getString("procedureno");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
            e.printStackTrace();
        }

        return null;
    }


    public Vector<String> FindOutstandingCosts(String amount) {
        
        String sqlQuery = "SELECT p.patientno, givenname, surname, outstandingcost "
            + "FROM cameronsmith.Patient p, cameronsmith.PatientName pn "
            + "WHERE p.patientno=pn.patientno "
            + "  AND p.outstandingcost>=" + amount;

        Vector<String> result = new Vector<String>();
        try {
            ResultSet rs = statement_.executeQuery(sqlQuery);

            while (rs.next()) {
                String tmp = rs.getString("patientno") + "##"
                    + rs.getString("givenname") + "##"
                    + rs.getString("surname") + "##"
                    + rs.getString("outstandingcost");

                result.add(tmp);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method FindPatientInformation()
    |
    |  Purpose: Users input either a first name, last name or both. These names
    |           are then used to find all users that this string input could possibly be.
    |           It returns all results that fit the requirements.
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      String firstName, -First name of client
    |      String lastName   -Last name of client
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
    public Vector<String> FindPatientInformation(String firstName, String lastName) {
        boolean firstNameNull = firstName.length() == 0;
        boolean lastNameNull = lastName.length() == 0;
        String sql_query = null;

        if(firstNameNull){
         sql_query = "Select cameronsmith.PatientName.patientNo, givenname, surname, address, insuranceProv From cameronsmith.PatientName, cameronsmith.Patient, cameronsmith.PatientInsurance WHERE cameronsmith.PatientName.surname LIKE '%" + lastName + "%' AND cameronsmith.PatientName.PatientNo = cameronsmith.Patient.PatientNo AND cameronsmith.PatientName.PatientNo = cameronsmith.PatientInsurance.PatientNo";
        }else if(lastNameNull){
          sql_query = "Select cameronsmith.PatientName.patientNo, givenname, surname, address, insuranceProv From cameronsmith.PatientName, cameronsmith.Patient, cameronsmith.PatientInsurance WHERE cameronsmith.PatientName.givenname LIKE '%" + firstName + "%' AND cameronsmith.PatientName.PatientNo = cameronsmith.Patient.PatientNo AND cameronsmith.PatientName.PatientNo = cameronsmith.PatientInsurance.PatientNo";
        }else{
          sql_query = "Select cameronsmith.PatientName.patientNo, givenname, surname, address, insuranceProv From cameronsmith.PatientName, cameronsmith.Patient, cameronsmith.PatientInsurance WHERE cameronsmith.PatientName.givenname LIKE '%" + firstName + "%' AND cameronsmith.PatientName.surname LIKE '%" + lastName  + "%' AND cameronsmith.PatientName.PatientNo = cameronsmith.Patient.PatientNo AND cameronsmith.PatientName.PatientNo = cameronsmith.PatientInsurance.PatientNo";
        }

       try {
          ResultSet rs  = statement_.executeQuery(sql_query);
          Vector<String> result_lab = new Vector<String>();
          while (rs.next()) {
             String temp_record = rs.getString("patientNo") + "##" 
                + rs.getString("givenname")+ "##" 
                + rs.getString("surname")+ "##" 
                + rs.getString("address")+ "##" 
                + rs.getString("insuranceProv"); 
            result_lab.add(temp_record);
          }
          return result_lab;
        } catch (SQLException sqlex) {
          sqlex.printStackTrace();
        }
        return null;
    }
	
	/*---------------------------------------------------------------------
    |  Author: Steven Adler
	|
	|  Method findVisits
    |
    |  Purpose: finds the number of visits of all patients in descending order
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
	public Vector<String> FindVisits() {
        String sql_query = null;
        

        sql_query = "SELECT cameronsmith.PatientName.PatientNo, COUNT(cameronsmith.Appointment.patientNo) AS Loyalty FROM cameronsmith.PatientName, cameronsmith.Appointment WHERE cameronsmith.PatientName.patientNo = cameronsmith.Appointment.patientNo GROUP BY cameronsmith.PatientName.PatientNo ORDER BY Loyalty DESC";

     //    sql_query = "SELECT cameronsmith.PatientName.*, COUNT(cameronsmith.Appointment.PATIENTNO) AS Loyalty "
					// + "FROM cameronsmith.PatientName "
					// + "LEFT JOIN cameronsmith.Appointment "
					// + "ON cameronsmith.PatientName.PATIENTNO = cameronsmith.Appointment.PATIENTNO "
					// + "GROUP BY cameronsmith.Patient.PATIENTNO "
					// + "ORDER BY Loyalty DESC";


		try {
			ResultSet rs  = statement_.executeQuery(sql_query);
			Vector<String> result_lab = new Vector<String>();
			while (rs.next()) {
				String temp_record = rs.getString("patientNo") + "##" 
					+ rs.getString("Loyalty"); 
				result_lab.add(temp_record);
			}
          return result_lab;
        } catch (SQLException sqlex) {
          sqlex.printStackTrace();
        }
        return null;
    }


	/*---------------------------------------------------------------------
    |  Author: Steven Adler
	|
	|  Method getMostOwed()
    |
    |  Purpose: Find the 10 highest owing patients in the patients database
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters:
    |      none
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
public Vector<String> GetMostOwed() {
    String sql_query = null;
        
        sql_query = "SELECT givenname, surname, outstandingCost, address "
      + "FROM cameronsmith.Patient, cameronsmith.PatientName "
      + "WHERE cameronsmith.Patient.patientNo = cameronsmith.PatientName.patientNo "
      + "ORDER BY outstandingCost DESC";

        // sql_query = "SELECT givenname, surname, oustandingCost, address FROM cameronsmith.Patient, cameronsmith.PatientName WHERE cameronsmith.Patient.patientNo = cameronsmith.PatientName.patientNo ORDER BY outstandingCost DESC";


		try {
			ResultSet rs  = statement_.executeQuery(sql_query);
			Vector<String> result_lab = new Vector<String>();
			for (int i = 0; i < 10 && rs.next(); i++) {
				String temp_record = rs.getString("givenname") + "##"
					+ rs.getString("surname")+ "##" 
					+ rs.getString("outstandingCost")+ "##" 
					+ rs.getString("address");
				result_lab.add(temp_record);
			}
          return result_lab;
        } catch (SQLException sqlex) {
          sqlex.printStackTrace();
        }
        return null;
	}


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method Delete()
    |
    |  Purpose: This is a generic delete that will remove a record
    |           based on the id in its table, the tablename and the 
    |           columnname. It has a switch/case statement to perform
    |           cascading deletes if necessary. These deletes will happen
    |           in the necessary order to avoid stale records.
    |
    |  Pre-condition: none
    |
    |  Post-condition: The record in the database no longer exists
    |
    |  Parameters:
    |      String id, 
    |      String tablename, 
    |      String columnname
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
    public String Delete(String id, String tablename, String columnname){
      boolean valueWorks = testValue(id);
      if(!valueWorks){
          return "Deletion failed-Nan";
      }
      String deleteFromTable = "DELETE FROM cameronsmith."+ tablename +" WHERE "+ columnname + "=" + id;

      switch(tablename){
          case "PatientName": String deleteFromPatient = "DELETE FROM cameronsmith.Patient WHERE PatientNo=" + id;
                              String deleteFromPatientInsurance = "DELETE FROM cameronsmith.PatientInsurance WHERE PatientNo=" + id;
                              String deleteFromAppointmentPatient = "DELETE FROM cameronsmith.Appointment WHERE PatientNo=" + id;
                               try {
                                  statement_.execute(deleteFromPatient);
                                  statement_.execute(deleteFromPatientInsurance);
                                  statement_.execute(deleteFromAppointmentPatient);
                                   //connection_.commit();
                               } catch (SQLException e) {
                                  e.printStackTrace();
                               }
                              
                              break;
          case "Procedure":String deleteFromAppointmentProcedure = "DELETE FROM cameronsmith.AppointmentProcedure WHERE ProcedureNo=" + id;
                          String deleteFromProcedureLength = "DELETE FROM cameronsmith.ProcedureLength WHERE ProcedureNo=" + id;
                          String deleteFromProcedureEquipment = "DELETE FROM cameronsmith.ProcedureEquipment WHERE ProcedureNo=" + id;
                              
                               try {
                                  statement_.execute(deleteFromAppointmentProcedure);
                                  statement_.execute(deleteFromProcedureLength);
                                  statement_.execute(deleteFromProcedureEquipment);
                                   //connection_.commit();
                               } catch (SQLException e) {
                                  e.printStackTrace();
                               }
                              
                              break;
          case "Equipment":String deleteFromProcedureEquipmentFromEquipment = "DELETE FROM cameronsmith.ProcedureEquipment WHERE Equipment=" + id;
                           String deleteFromLabEquipment = "DELETE FROM cameronsmith.LabEquipment WHERE EquipmentNo=" + id;                         
                              
                               try {
                                  statement_.execute(deleteFromProcedureEquipmentFromEquipment);
                                  statement_.execute(deleteFromLabEquipment);
                                   //connection_.commit();
                               } catch (SQLException e) {
                                  e.printStackTrace();
                               }
                              
                              break;

          case "Lab":String deleteFromLabEquipmentFromLab = "DELETE FROM cameronsmith.LabEquipment WHERE labNo=" + id;
                                                  
                             try {
                                statement_.execute(deleteFromLabEquipmentFromLab);
                                 //connection_.commit();
                             } catch (SQLException e) {
                                e.printStackTrace();
                             }
                            
                            break;
          default: break;
      }
        try {
            statement_.execute(deleteFromTable);
            //connection_.commit();
            return "This id:" + id + "is deleted from the " + deleteFromTable;
      } catch (SQLException e) {
            e.printStackTrace();
      }
      return "Deletion failed";
    }

    public boolean testValue(String id){
      if(id.compareTo("") != 0 && id != null){
        char[] values = id.toCharArray();
        for(int i = 0; i < id.length(); i++){
          if(values[i] > 57 || values[i] < 48){
            return false;
          }
        }
        return true;
      }
      return false;
    }

    public Vector<String> ListAllProcedures() {
        
        String sqlQuery = "SELECT procedureno, name "
            + "FROM cameronsmith.Procedure";

        Vector<String> result = new Vector<String>();
        try {
            ResultSet rs = statement_.executeQuery(sqlQuery);

            while (rs.next()) {
                String tmp = rs.getString("procedureno") + "##"
                    + rs.getString("name");

                result.add(tmp);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String DeletePatient(Integer id){
      String deleteFromPatientTable = "DELETE FROM cameronsmith.Patient WHERE patientNo=" +
            id;


      try {
            statement_.execute(deleteFromPatientTable);
            //connection_.commit();
            return "This id:" + id + "is deleted from the " + deleteFromPatientTable;
      } catch (SQLException e) {
            e.printStackTrace();
      }
      return "Deletion failed";
    }


/*---------------------------------------------------------------------
    |  Author: Patrick Maley
    |
    |  Method DeleteMulti()
    |
    |  Purpose: This is a generic delete that will remove a record
    |           based on the id in its table, the tablename and the 
    |           columnname. It has a switch/case statement to perform
    |           cascading deletes if necessary. These deletes will happen
    |           in the necessary order to avoid stale records. This delete
    |           is deleting from tables where the primary key is composed of
    |           two columns.
    |
    |  Pre-condition: none
    |
    |  Post-condition: Record is deleted.
    |
    |  Parameters:
    |      String idOne, -Id of the column 1
    |      String idTwo, -Id of the column 1
    |      String tablename, -Name of the table
    |      String columnNameOne, -Name of the column 1
    |      String columnNameTwo -Name of the column 2
    |
    |  Returns: Vector<String> - results of the query
    *-------------------------------------------------------------------*/
    public String DeleteMulti(String idOne, String idTwo, String tablename, String columnNameOne, String columnNameTwo){
       boolean valueWorks = testValue(idOne);
       boolean valueWorksTwo = testValue(idTwo);
      if(!valueWorks || !valueWorksTwo){
          return "Deletion failed-Nan";
      }
      String deleteFromTable = "DELETE FROM cameronsmith."+ tablename +" WHERE "+ columnNameOne + "=" + idOne + " AND " + columnNameTwo + "=" + idTwo;

      switch(tablename){
              case "Appointment": String deleteFromAppointmentProcedure = "DELETE FROM cameronsmith.AppointmentProcedure WHERE appointmentNo=" + idOne;
                                  
                                   try {
                                      statement_.execute(deleteFromAppointmentProcedure);
                                       //connection_.commit();
                                   } catch (SQLException e) {
                                      e.printStackTrace();
                                   }
                                  
                                  break;
            
              default: break;
          }
      try {
            statement_.execute(deleteFromTable);
            //connection_.commit();
            return "This id:" + idOne + " " +idTwo + "is deleted from the " + tablename;
      } catch (SQLException e) {
            e.printStackTrace();
      }
      return "Deletion failed";
    }


    public String DeletePatientName(Integer id){
      String deleteFromPatientTable = "DELETE FROM cameronsmith.PatientName WHERE patientNo=" +
            id;

      try {
            statement_.execute(deleteFromPatientTable);
            //connection_.commit();
            return "This id:" + id + "is deleted from the table";
      } catch (SQLException e) {
            e.printStackTrace();
      }
      return "Deletion failed";
    }

    /*---------------------------------------------------------------------
     |  Method updatePatientQuery
     |
     |  Purpose: updates the specified patient for his or her next visit,
     |           outstanding cost, and/or adress as a SQL statement
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |      Integer id - patient's id
     |      String nextVisit - next appointment for patient
     |      Integer oustandingCost - outstand cost for patient or total
     |                               owe to dentist office
     |      String address - address of patient
     |      
     |
     |  Returns: String - SQL query statement for the specified updated query
     |                    if any
     *-------------------------------------------------------------------*/

    public String updatePatientQuery(Integer id, String nextVisit, Integer oustandingCost, String address) {
	String sqlStat = null;
	int field=0;

	sqlStat = "UPDATE cameronsmith.Patient ";

	if (!(nextVisit.equals(""))) {
	    sqlStat = sqlStat + "SET nextVisit=" + nextVisit;
	    field = field + 1;
	}
	if (!(address.equals(""))) {
	    if (field > 0) {
		sqlStat = ",address=" + address;
	    }
	    else {
		sqlStat = "SET address=" + address;
	    }
	    field = field + 1;
	}
	if (oustandingCost >= 0) {
	    if (field > 0) {
		sqlStat = ",oustandingCost=" + oustandingCost;
	    }
	    else {
		sqlStat = " SET oustandingCost=" + oustandingCost;
	    }
	    field = field + 1;
	}
	// returns an empty string for there is no input
	if (nextVisit.equals("") && address.equals("") && oustandingCost < 0)  {
	    return "";
	}

	sqlStat = sqlStat + " WHERE patientNo=" + id;

	return sqlStat;
    }

    /*---------------------------------------------------------------------
     |  Author: Steven Broussard
     |
     |  Method updatePatient
     |
     |  Purpose: updates the specified patient for his or her next visit,
     |           outstanding cost, and/or adress
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |      Integer id - patient's id
     |      String nextVisit - next appointment for patient
     |      Integer oustandingCost - outstand cost for patient or total
     |                               owe to dentist office
     |      String address - address of patient
     |      
     |
     |  Returns: String - the status of updated information about Patient Table
     *-------------------------------------------------------------------*/

    public String updatePatient(Integer id, String nextVisit, Integer oustandingCost, String address) {
	String status = "";
	String sqlStat = null;
	
	sqlStat = updatePatientQuery(id, nextVisit, oustandingCost, address);

	if (sqlStat.equals("")) {
	    return "No fields to update.";
	}
	else {
	    try {
		statement_.execute(sqlStat);
		status="updated ";
		if (!(nextVisit.equals(""))) {
		    status = status + "nextVisit ";
		}
		if (!(address.equals(""))) {
		    status = status + "address ";
		}
		if (oustandingCost >= 0) {
		    status = status + "oustandingCost";
		}
	    }
	    catch(SQLException s) {
		System.out.println("Something is wrong with the update statement for Patient Table.");
		s.printStackTrace();
	    }
	    return status;
	}
    }
    /*---------------------------------------------------------------------
     |  Author: Steven Broussard
     |
     |  Method updatePatientNameQuery
     |
     |  Purpose: updates the specified patient for his or her given name
     |           and surname as a SQL statement
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |      Integer id - patient's id
     |      String givenname - first name of patient
     |      String surnam - last name of patient
     |      
     |
     |  Returns: String - SQL query statement for the specified updated query
     |                    if any
     *-------------------------------------------------------------------*/

    public String updatePatientNameQuery(Integer id, String givenname, String surname) {
	String sqlStat = null;
	int field=0;

	sqlStat = "UPDATE cameronsmith.PatientName ";

	if (!(givenname.equals(""))) {
	    sqlStat = sqlStat + "SET givenname=" + givenname;
	    field = field + 1;
	}
	if (!(surname.equals(""))) {
	    if (field > 0) {
		sqlStat = ",surname=" + surname;
	    }
	    else {
		sqlStat = "SET surname=" + surname;
	    }
	    field = field + 1;
	}
	// returns an empty string for there is no input
	if (givenname.equals("") && surname.equals(""))  {
	    return "";
	}

	sqlStat = sqlStat + " WHERE patientNo=" + id;

	return sqlStat;
    }

    /*---------------------------------------------------------------------
     |  Author: Steven Broussard
     |
     |  Method updatePatientName
     |
     |  Purpose: updates the specified patient for his or her given name
     |           and surname
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |      Integer id - patient's id
     |      String givenname - first name of patient
     |      String surnam - last name of patient
     |      
     |
     |  Returns: String - Status of updated information about PatientName Table
     *-------------------------------------------------------------------*/

    public String updatePatientName(Integer id, String givenname, String surname) {
	String status = "";
	String sqlStat = null;
	
	sqlStat = updatePatientNameQuery(id, givenname, surname);

	if (sqlStat.equals("")) {
	    return "No fields to update.";
	}
	else {
	    try {
		statement_.execute(sqlStat);
		status="updated ";
		if (!(givenname.equals(""))) {
		    status = status + "givenname ";
		}
		if (!(surname.equals(""))) {
		    status = status + "surname";
		}
	    }
	    catch(SQLException s) {
		System.out.println("Something is wrong with the update statement for PatientName Table.");
		s.printStackTrace();
	    }
	    return status;
	}
    }

    /*---------------------------------------------------------------------
     |  Author: Steven Broussard
     |
     |  Method updatePatientInsuranceQuery
     |
     |  Purpose: updates the specified patient for his or her insurance privader as a SQL statement
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |      Integer id - patient's id
     |      String insuranceProv - insurance provider of patient
     |      
     |
     |  Returns: String - SQL query statement for the specified updated query
     |                    if any
     *-------------------------------------------------------------------*/

    public String updatePatientInsuranceQuery (Integer id,  String insuranceProv) {
	String sqlStat = null;

	sqlStat = "UPDATE cameronsmith.PatientInsurance ";

	if (!(insuranceProv.equals(""))) {
	    sqlStat = sqlStat + "SET insuranceProv=" + insuranceProv;
	}
	// returns an empty string for there is no input
	if (insuranceProv.equals(""))  {
	    return "";
	}

	sqlStat = sqlStat + " WHERE patientNo=" + id;

	return sqlStat;
    }

    /*---------------------------------------------------------------------
     |  Author: Steven Broussard
     |
     |  Method updatePatientInsurance
     |
     |  Purpose: updates the specified patient for his or her insurance privader
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |      Integer id - patient's id
     |      String insuranceProv - insurance provider of patient
     |      
     |
     |  Returns: String - status of updated information in PatientInsurance Table
     *-------------------------------------------------------------------*/

    public String updatePatientInsurance(Integer id, String insuranceProv) {
	String status = "";
	String sqlStat = null;
	
	sqlStat = updatePatientInsuranceQuery(id,insuranceProv);

	if (sqlStat.equals("")) {
	    return "No fields to update.";
	}
	else {
	    try {
		statement_.execute(sqlStat);
		status="updated ";
		if (!(insuranceProv.equals(""))) {
		    status = status + "insuranceProv";
		}
	    }
	    catch(SQLException s) {
		System.out.println("Something is wrong with the update statement for PatientInsurance Table.");
		s.printStackTrace();
	    }
	    return status;
	}
    }

    public Vector<String> ListAllEquipment() {
        
        String sqlQuery = "SELECT equipmentno, name "
            + "FROM cameronsmith.Equipment";

        Vector<String> result = new Vector<String>();
        try {
            ResultSet rs = statement_.executeQuery(sqlQuery);

            while (rs.next()) {
                String tmp = rs.getString("equipmentno") + "##"
                    + rs.getString("name");
                result.add(tmp);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insertLab(String name, String[] equipment) {
        
        String selectMax = "SELECT MAX(labno) "
            + "FROM cameronsmith.Lab";

        Integer maxLab = null;
        try {
            ResultSet rs = statement_.executeQuery(selectMax);

            rs.next();
            maxLab = new Integer(rs.getString("MAX(labno)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        maxLab++;

        String sqlQuery = "INSERT INTO cameronsmith.Lab "
            + "VALUES (" + maxLab.toString() + ", '" + name + "')";

        try {
            statement_.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < equipment.length; i++) {
            try {
            statement_.executeUpdate("INSERT INTO cameronsmith.LabEquipment "
                + "VALUES (" + maxLab.toString()+ ", " + equipment[i] + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String retrieveLab = "SELECT labno, name "
            + "FROM cameronsmith.Lab "
            + "WHERE labno=" + maxLab.toString();

        String result = "";
        try {
            ResultSet rs = statement_.executeQuery(retrieveLab);
            rs.next();

            result =  rs.getString("labno") + "##" + rs.getString("name");

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insertLabEquipment(String labno, String equipment) {
        
        String sqlUpdate = "INSERT INTO cameronsmith.LabEquipment "
            + "VALUES (" + labno + ", " + equipment + ")";

        try {
            statement_.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String insertEquipment(String name, String cost) {
        
        String maxEquipmentQuery = "SELECT MAX(equipmentno) "
            + "FROM cameronsmith.Equipment";

        Integer maxEquipment = 0;
        try {
            ResultSet rs = statement_.executeQuery(maxEquipmentQuery);

            rs.next();
            maxEquipment = new Integer(rs.getString("MAX(equipmentno)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        maxEquipment++;

        String sqlUpdate = "INSERT INTO cameronsmith.Equipment "
            + "VALUES (" + maxEquipment.toString() + ", '" + name + "', "
            + cost + ")";

        try {
            statement_.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = statement_.executeQuery("SELECT equipmentno, name, cost "
                + "FROM cameronsmith.Equipment "
                + "WHERE equipmentno=" + maxEquipment);

            rs.next();
            return rs.getString("equipmentno") + "##" + rs.getString("name")
                + "##" + rs.getString("cost");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String insertProcedure(String name, Integer cost, Float length,
            String[] equipment) {
        
        String maxProcQuery = "SELECT MAX(procedureno) "
            + "FROM cameronsmith.Procedure";

        Integer maxProc = null;
        try {
            ResultSet rs = statement_.executeQuery(maxProcQuery);

            rs.next();
            maxProc = new Integer(rs.getString("MAX(procedureno)"));
            maxProc++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (maxProc == null) return null;

        String insertProc = "INSERT INTO cameronsmith.Procedure "
            + "VALUES (" + maxProc.toString() + ", '" + name + "', "
            + cost.toString() + ")";

        String insertProcLength = "INSERT INTO cameronsmith.ProcedureLength "
            + "VALUES (" + maxProc.toString() + ", " + length.toString() + ")";

        try {
            statement_.executeUpdate(insertProc);
            statement_.executeUpdate(insertProcLength);

            for (int i = 0; i < equipment.length; i++) {
                statement_.executeUpdate(
                    "INSERT INTO cameronsmith.ProcedureEquipment "
                    + "VALUES (" + maxProc.toString() + ", " + equipment[i] + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlQuery = "SELECT p.procedureno, name, cost, length "
            + "FROM cameronsmith.Procedure p, cameronsmith.ProcedureLength pl "
            + "WHERE p.procedureno=" + maxProc.toString();

        try {
            ResultSet rs = statement_.executeQuery(sqlQuery);

            rs.next();
            return rs.getString("procedureno") + "##" + rs.getString("name")
                + "##" + rs.getString("cost") + "##" + rs.getString("length");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
