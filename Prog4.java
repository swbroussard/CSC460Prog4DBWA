/*=============================================================================
 |	 Assignment:  Program #4:  Database-driven Web Application
 |		 Author:  Steven Broussard (stevenwbroussard@email.arizona.edu
 |		 Grader:  Zhenge Zhoa
 |
 |		 Course:  CSC 460 - Database Design
 |	 Instructor:  L. McCann
 |	   Due Date:  December 6, 2016 before 12:29PM
 |
 |	Description:  Prog3 is the controller of the of the web application. It governs
 |				  the type of information sent to the webpage and database. For
 |				  examples the different types of views for the user.
 |
 |	   Language:  Java JE 1.8
 | Ex. Packages:  none...for now
 |				  
 | Deficiencies:  none...for now
 *===========================================================================*/
import java.io.*;
import java.sql.*;

public class Prog4 {
	//global variable to call the library of methods  that return a string of the SQL statement
	private String[] tables = {"Patient", "PatientName", "PatientInsurance", "Appointment", "Procedure", 
		"ProcedureLength", "ProcedureEquipment", "AppointmentProcedure", "Lab", "Equipment", "LabEquipment"};
	private Connection dbconn = null;

	/* join:
	 * Joins an array of strings, separated by a specified string.
	 *
	 * @param strings - The array of Strings.
	 * @param sep - What to separate the strings with.
	 */
	public String join(String[] strings, String sep) {

		if (strings.length == 0) 
			return "";

		toReturn = "";
		for (int i=0; i<strings.length-1; i++) {
			toReturn += strings[i];
		}
		
		toReturn += strings[strings.length - 1];
		return toReturn;
	}
	
	/* insert:
	 * Inserts a tuple into a specified table.
	 *
	 * @param table(String) - The table to insert the tuple into.
	 * @param tuple(String[]) - A list of attributes of the tuple.
	 * @returns true if the tuple is successfully inserted into the table, false otherwise.
	 */
	public void insert(String table, String[] tuple) {
		
		// If the table specified is not valid, return false.
		if (!Arrays.asList(tables).contains(table))
			return false;

		// Setup the statement and result set.
		Statement stmnt = null;
		ResultSet answr = null;

		try {
			stmnt = dbconn.createStatement();
			stmnt.executeUpdate("INSERT INTO " + table 
				+ "VALUES (" + join(tuple, ",") + ")");
		} catch (SQLException e) {
			System.err.println("*** SQLException: Unable to add tuple to table " + table);
			System.err.println("\tMessage:	 " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	public static void main (String[] args) {

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

		// Locate the JDBC Driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("*** ClassNotFoundException: "
				+ "Make sure driver is on Classpath.");
			System.exit(-1);
		}

		// Establish connection to Oracle DB.
		try {
			dbconn = DriverManager.getConnection(oracleURL, username, password);
			dbconn.setAutoCommit(false);
		} catch (SQLException e) {
			System.err.println("*** SQLException: "
				+ "Could not open JDBC connection.");
			System.err.println("\tMessage:	 " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}	
	}
}
