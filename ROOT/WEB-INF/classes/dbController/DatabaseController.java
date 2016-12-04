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

    public String Delete(Integer id, String tablename, String columnname){
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
            return "This id:" + id + "is deleted from the " + tablename;
      } catch (SQLException e) {
            e.printStackTrace();
      }
      return "Deletion failed";
    }

    public String DeleteMulti(Integer idOne, Integer idTwo, String tablename, String columnNameOne, String columnNameTwo){

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

    // public String DeletePatientName(Integer id){
    //   String deleteFromPatientTable = "DELETE FROM cameronsmith.PatientName WHERE patientNo=" +
    //         id;

    //   try {
    //         statement_.execute(deleteFromPatientTable);
    //         //connection_.commit();
    //         return "This id:" + id + "is deleted from the table";
    //   } catch (SQLException e) {
    //         e.printStackTrace();
    //   }
    //   return "Deletion failed";
    // }
}
