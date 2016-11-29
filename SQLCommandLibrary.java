/*+----------------------------------------------------------------------
 ||
 ||  Class: SQLCommandLibrary
 ||
 ||         Author: Steven Broussard (stevenwbroussard@email.arizona.edu)
 ||
 ||        Purpose: Library of SQL Command set up as though they are Java
 ||                 commands.
 ||
 ||  Inherits From: none
 ||
 ||     Interfaces: none
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants: none
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors: none
 ||
 ||  Class Methods: createTable
 ||                 grantTable
 ||                 insert
 ||                 deleteTable
 ||                 whichPercent
 ||                 noTestPercent
 ||                 grabPercent
 ||
 ||  Inst. Methods: none
 ||
 ++-----------------------------------------------------------------------*/

public class SQLCommandLibrary {
	/*---------------------------------------------------------------------
    |  Method: createTable
    |
    |  Purpose: string that represent SQL statement to create a table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String fileName - name of the table
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String createTable(String fileName) {
		String createATable = "create table " + fileName + " ("
			              + "distNum   integer,"
			              + "schoolNum integer,"
			              + "distName  varchar2(30),"
			              + "schName   varchar2(30),"
			              + "totNumEn  integer,"
			              + "numNoTest integer," 
			              + "perNoTest float,"
			              + "numMin    integer,"
			              + "perMin    float,"
			              + "numBasic  integer,"
			              + "perBasic  float,"
			              + "numPro    integer,"
			              + "perPro    float,"
			              + "numAdvan  integer,"
			              + "perAdvan  float"
			              + ")";
		return createATable;
	}
	
	/*---------------------------------------------------------------------
    |  Method: grantTable
    |
    |  Purpose: grants public access to the table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String tableName - name of the table in Oracle database
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String grantTable(String tableName) {
		String grantIt = "GRANT SELECT " + tableName + " TO PUBLIC";
		return grantIt;
	}
	
	/*---------------------------------------------------------------------
    |  Method: insert
    |
    |  Purpose: insert a row in to a table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String tableName - name of table
    |              int distNum - school district number column
    |              int schlNum - school number column
    |              String distName - name of school district column
    |              String schlName - school name column
    |              int totNum - total number of students enolled at school
    |              int numNoTested - number of student not tested
    |              double perNotTested - percentage of students not tested
    |              int numMin - number of student took minimum test
    |              double perMin - percentage of student passed minimum test
    |              int numBasic - number of student took basic test
    |              double perBasic - percentage of student passed basic test
    |              int proNum - number of student took proficient test
    |              double perPro - percentage of student passed proficient test
    |              int numAdv - number of student took advance test
    |              double perAdv - percentage of student passed advance test
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String insert(String tableName,
			             int distNum,
			             int schlNum,
			             String distName,
			             String schlName,
			             int totNum,
			             int numNoTested,
			             double perNotTested,
			             int numMin,
			             double perMin,
			             int numBasic,
			             double perBasic,
			             int proNum,
			             double perPro,
			             int numAdv,
			             double perAdv) {
		
		String insertRow = "insert into " + tableName + " values (" + distNum + "," 
			                                                        + schlNum 
			                                                        + ",'" + distName + "'," 
			                                                        + "'" + schlName + "'," 
				                                                    + totNum + ","
				                                                    + numNoTested + ","
				                                                    + perNotTested + ","
				                                                    + numMin + ","
				                                                    + perMin + ","
				                                                    + numBasic + ","
				                                                    + perBasic + ","
				                                                    + proNum + ","
				                                                    + perPro + ","
				                                                    + numAdv + ","
				                                                    + perAdv + ")";
		
		return insertRow;
	}
	
	/*---------------------------------------------------------------------
    |  Method: deleteTable
    |
    |  Purpose: delete the input table name and its contents
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String tableName - name of the table to drop
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String deleteTable(String tableName) {
		String dropTable = "drop table " + tableName;
		
		return dropTable;
	}
	
	/*---------------------------------------------------------------------
    |  Method: whichPercent
    |
    |  Purpose: return top five schools of the chosen percents from each table
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String percent - input of which percent to use
    |              String tableName - name of the table
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String whichPercent(String percent, String tableName) {
		String outputPercent = "";
		
		outputPercent = "select " + "schName, " + percent 
				        + " from " + tableName
				        + " order by " + percent + " desc";
		
		return outputPercent;
	}
	
	/*---------------------------------------------------------------------
    |  Method: noTestPercent
    |
    |  Purpose: query table for noTest percent from a table with the school name
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String schoolName - input of which schoool name
    |              String tableName - name of the table
    |              String distNum - school number id
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String noTestPercent(String schoolName, String distNum, String tableName) {
		String grabNoTestPer = "select perNoTest" +
	                           " from " + tableName +
	                           " where schName='" + schoolName + "' and distNum='" + distNum + "'";
		return grabNoTestPer;
	}
	
	/*---------------------------------------------------------------------
    |  Method: grabPercent
    |
    |  Purpose: query table for input percent and school name
    |
    |  Pre-condition: none
    |
    |  Post-condition: none
    |
    |  Parameters: String schoolName - input of which school name
    |              String percent - input of which percents
    |              String tableName - name of the table
    |              String distNum - district id
    |
    |  Returns: String
    *-------------------------------------------------------------------*/
	public String grabPercent(String schoolName, String distNum, String tableName, String percent) {
		String grabNoTestPer = "select " + percent +
	                           " from " + tableName +
	                           " where schName='" + schoolName + "' and distNum='" + distNum + "'";
		return grabNoTestPer;
	}
}
