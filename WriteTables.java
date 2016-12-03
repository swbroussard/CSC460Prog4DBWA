import java.io.*;
import java.sql.*;

public class WriteTables {

	public static void main (String [] args) {
		
		final String oracleURL =
			"jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
        
		String username = null, // Username and password of the
		       password = null; // user. Supplied as command line args.

        if (args.length == 2) {
			username = args[0];
			password = args[1];
		} else {
			System.out.println("\nUsage: java MakeTables <username> <password>");
			System.exit(-1);
		}

		String makeTable = "CREATE TABLE ";

        // Include files to create tables of.
        String[] files = {"Patient.csv", "PatientName.csv",
            "PatientInsurance.csv", "Appointment.csv", "Procedure.csv",
            "ProcedureLength.csv", "ProcedureEquipment.csv",
            "AppointmentProcedure.csv", "Lab.csv", "Equipment.csv",
            "LabEquipment.csv"};

        try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("*** ClassNotFoundException: "
				+ "Make sure driver is on Classpath.");
			System.exit(-1);
		}

        Connection dbconn = null;

		try {
			dbconn = DriverManager.getConnection(oracleURL, username, password);
			dbconn.setAutoCommit(false);
		} catch (SQLException e) {
			System.err.println("*** SQLException: "
				+ "Could not open JDBC connection.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		} 

		System.out.println("Connected successfully.");

        // Loop over the specified files.
        File currFile = null;
        for (int i=0; i<files.length; i++) {
            currFile = new File(files[i]);

            // Setup the statement and result set.
            Statement stmnt = null;
            ResultSet answr = null;

            String table = username + "." +
                currFile.getName().substring(0, currFile.getName().length() - 4);

            String tableFields = null;

            // Cases for each file.
            switch (currFile.getName()) {
                case "Patient.csv":
                    tableFields = "(\n"
                        + "  patientNo integer,\n"
                        + "  nextVisit date,\n"
                        + "  outstandingCost integer,\n"
                        + "  address varchar(50),\n"
                        + "  primary key(patientNo))";
                    break;
                case "PatientName.csv":
                    tableFields = "(\n"
                        + "  patientNo integer,\n"
                        + "  givenname varchar(50),\n"
                        + "  surname varchar(50),\n"
                        + "  primary key(patientNo))";
                    break;
                case "PatientInsurance.csv":
                    tableFields = "(\n"
                        + "  patientNo integer,\n"
                        + "  insuranceProv varchar(50),\n"
                        + "  primary key(patientNo))";
                    break;
                case "Appointment.csv":
                    tableFields = "(\n"
                        + "  appointmentNo integer,\n"
                        + "  patientNo integer,\n"
                        + "  day date,\n"
                        + "  primary key(appointmentNo, patientNo))";
                    break;
                case "Procedure.csv":
                    tableFields = "(\n"
                        + "  procedureNo integer,\n"
                        + "  name varchar(50),\n"
                        + "  cost integer,\n"
                        + "  primary key(procedureNo))";
                    break;
                case "ProcedureLength.csv":
                    tableFields = "(\n"
                        + "  procedureNo integer,\n"
                        + "  length float,\n"
                        + "  primary key(procedureNo))";
                    break;
                case "ProcedureEquipment.csv":
                    tableFields = "(\n"
                        + "  procedureNo integer,\n"
                        + "  equipment integer,\n"
                        + "  primary key(procedureNo, equipment))";
                    break;
                case "AppointmentProcedure.csv":
                    tableFields = "(\n"
                        + "  appointmentNo integer,\n"
                        + "  procedureNo integer,\n"
                        + "  primary key(appointmentNo, procedureNo))";
                    break;
                case "Lab.csv":
                    tableFields = "(\n"
                        + "  labNo integer,\n"
                        + "  name varchar(50),\n"
                        + "  primary key(labNo))";
                    break;
                case "Equipment.csv":
                    tableFields = "(\n"
                        + "  equipmentNo integer,\n"
                        + "  name varchar(50),\n"
                        + "  cost integer,\n"
                        + "  primary key(equipmentNo))";
                    break;
                case "LabEquipment.csv":
                    tableFields = "(\n"
                        + "  labNo integer,\n"
                        + "  equipmentNo integer,\n"
                        + "  primary key(labNo, equipmentNo))";
                    break;
            }

            // Make the table.
            boolean drop = false;
		    try {
				stmnt = dbconn.createStatement();
				stmnt.executeUpdate(makeTable + table + tableFields);
                System.out.println("Added table " + table);
			} catch (SQLException e) {
				System.out.println("Unable to create table: " + table);
                if (e.getErrorCode() == 955) {
                    System.out.println("Table already exists; drop it.");
                    drop = true;
                } else {
				    System.err.println("*** SQLException: ");
				    System.err.println("\tMessage:   " + e.getMessage());
				    System.err.println("\tSQLState:  " + e.getSQLState());
				    System.err.println("\tErrorCode: " + e.getErrorCode());
				    System.exit(-1);
                }
			}


            // Add the tuples to the table.
            if (!drop) {
                try (BufferedReader br = new BufferedReader(
    						new FileReader(currFile))) {
    
    				String line = null;
    				while ((line = br.readLine()) != null) {
    					String[] splitLine = line.split(",");
    					String values = "";
   
   						// Create a values string containing all the fields for
   						// each tuple of a table.
   						for (int j = 0; j < splitLine.length; j++){
   							values += splitLine[j] + ", ";
   						}
   						values = values.substring(0, values.length() - 2);
   
   						try {
   
   							stmnt = dbconn.createStatement();
   							stmnt.executeUpdate(
   								"INSERT INTO " + table + 
   								" VALUES ( " + values + " )");
                            stmnt.executeUpdate("GRANT SELECT, INSERT, UPDATE, "
                                + "DELETE ON " + table + " TO stevenwbroussard, "
                                + "sadler, pmaley, zhengezhao");
   							stmnt.close();
   						} catch (SQLException e) {
                            // If an insert fails, drop the table.
  							System.out.println("Nooooo...insert failed.");
   							System.out.println(values);
   							System.err.println("*** SQLException: "
   								+ "Could not open JDBC connection.");
   							System.err.println("\tMessage:   " + e.getMessage());
   							System.err.println("\tSQLState:  " + e.getSQLState());
   							System.err.println("\tErrorCode: " + e.getErrorCode());
                            drop = true;
                            break;
   						}
   					}
       			} catch (IOException e) {
       				System.out.println("File: " + currFile.getName()
       						+ " not found.");
       				System.exit(-1);
       			}
            }

            if (drop) {
                try {
                    System.out.println("Dropping table " + table + "...");
                    stmnt.executeUpdate("DROP TABLE " + table);
                } catch (SQLException e) {
                    System.out.println("Unable to drop table " + table);
                }
            }
        }
        
    }
}
