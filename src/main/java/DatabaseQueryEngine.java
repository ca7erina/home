
import java.sql.*;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/*----------------------------------------------------------------
 *
 *  Compilation:   javac DatabaseQueryEngine.java
 *  Execution:     java -cp .;postgresqljdbc4.jar  DatabaseQueryEngine (windows)
 *	Execution:     java -cp .:postgresqljdbc4.jar  DatabaseQueryEngine (Linux)
 * 
 *  Executes a SELECT or UPDATE/DELETE query on a PostgreSQL database

	To compile and run this code. 
	To compile just do          javac DatabaseQueryEngine.java on the terminal command line. 

	To run this code you will need to include the JAR file which contains the code for accessing databases in Java

	In WINDOWS    java -cp .;postgresqljdbc4.jar  DatabaseQueryEngine
	In LINUX      java -cp .:postgreqsljdbc4.jar  DatabaseQueryEngine

	There are other ways to run this code - for example if you use an IDE such as Eclipse. We cannot provide you with support in the CS130 lab for any other approach
	execpt using the command line window approach. 
*/




public class DatabaseQueryEngine
{
	public static void main(String[] args)
	{

		String YOUR_CS_USER_NAME = "hdit1505";  // you must type your CS user name here
		String YOUR_CS_PASSWORD = "19870625"; // you must type your password here - BE CAREFUL don't share this with anyone. 

		String YOUR_DATABASE_SCHEMA = YOUR_CS_USER_NAME; // this is the schema or database in the Webcourse.cs.nuim.ie postgresql database that you use. 

		// When you have figured out the correct query syntax you can copy and paste it here. 
		// Remember you'll need to enclose the query in double quotes because that is what Java requires. 
		
		String Q1="select * from allstudents where date_part('year',dob) >=2015-20";
		String Q2="select * from allstudents where (gender = 'Female') and (homestate in ('Ohio','Nebraska','Idaho','Indiana'));";
		String Q3="update modules set campus = 'Commercialisation and Innovation Campus' where campus = 'Incubation-Hub'";
		String Q4="delete from modules where modulecode = 'DSG-059'";
		String Q5="update AllStudents set studentid = '20194741' where fullname = 'Jacqueline Martinez'";
		String Q6="select sum(modules.credits) from AllStudents, modules, studies where (AllStudents.studentid=studies.studentid) and (modules.modulecode = studies.modulecode) and fullname = 'Barbara Ray'";

		String The_Query_To_Run = "select sum(modules.credits) from AllStudents, modules, studies where (AllStudents.studentid=studies.studentid) and (modules.modulecode = studies.modulecode) and fullname = 'Barbara Ray'";

		// You'll need to uncomment which method call you need - do you need a SELECT query or an UPDATE/DELETE query

		// Uncomment this for SELECT queries

		execute_SELECT_Query_on_Database(YOUR_CS_USER_NAME, YOUR_CS_PASSWORD, YOUR_DATABASE_SCHEMA,The_Query_To_Run);
	
		// Uncomment this for UPDATE or DELETE queries. 
		
		// execute_UPDATE_or_DELETE_on_Database(YOUR_CS_USER_NAME, YOUR_CS_PASSWORD, YOUR_DATABASE_SCHEMA,The_Query_To_Run);
	

		
	}

	/*
		The method execute_SELECT_Query_on_Database allows you to execute any SELECT query on the database. 
		You will need to supply a number of important parameters for the method. 
		auser --> this is your username for the CS130 course database
		apassword --> this is the password corresponding to your username for the CS130 course database. 
		ascheme --> this is the name of the database or schema within the webcourse postgreSQL database which you want to query. So this schema could be called
		the same as the value of auser or it might be public which is the publicly accessible schema created and used by Peter Mooney for demonstration purposes. 
	
		How to use this?
		This is a static method - so all you need to do is to call this method with the correct parameters

		For example
		execute_SELECT_Query_on_Database("PeterM","PeterPassword","public","SELECT count(*) from tablename");

		RESULTS:
		The method will create a very simple HTML webpage in the same folder as this Java Code. 
		You'll be able to see the results of your SELECT query in this file. Just click on the file. 

		YOU DO NOT NEED TO EDIT ANY OF THE CODE IN THE METHOD - If you do so you're on your own. We're not trying to write Java code we're just using it
		as a means of example of accessing the database away from PGAdmin
	*/

	public static void execute_SELECT_Query_on_Database(String auser, String apassword, String aschema,String aqueryString)
	{
		Connection con = null;
		
		Statement stmt = null;
		try
		{
			//This is your schema in the CS130 database. 
			// You should change this to be your schema name ie dm18920

			String schemaName = aschema;

			String url = "jdbc:postgresql://webcourse.cs.nuim.ie/cs130?currentSchema="+schemaName+"&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			
			// Now for the username and password that you normally use to logon to the PostgreSQL PGAdmin server
			String user =  auser;
			String password = apassword;
			
			Class dbDriver = Class.forName("org.postgresql.Driver");
	
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("******* Successfully connected to webcourse.cs.nuim.ie using SSL with validation\n\n");
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(aqueryString);
			System.out.println("\t\tEXECUTING SELECT QUERY STATEMENT\t\t");

			writeQueryResultsToOutputFile(rs,aqueryString,"output.html");

      		System.out.println("\t\tFINISHED Writing Results to output HTML file\n");

			rs.close();
			stmt.close();
			con.close();			
			System.out.println("****** Successfully disconnected to webcourse.cs.nuim.ie using SSL with validation\n\n");
			
		}
		catch (Exception ex)
		{
			System.out.println("ERROR: An error has occured connecting to webcourse.cs.nuim.ie using SSL with validation\n\n");
			ex.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					System.out.println("****** Connection to webcourse.cs.nuim.ie using SSL with validation. CLOSED\n\n");
					System.out.println("ATTENTION MOODLE QUIZ CODE:  SQL" + generateResultCode() + "SQL");
				}
				catch (Exception e){
					System.out.println("****** Connection closing exception to webcourse.cs.nuim.ie using SSL with validation\n\n");
				}
			}
		}
	}

	/*
		The method execute_UPDATE_or_DELETE_on_Database allows you to execute a DELETE or UPDATE query on the database. 
		You will need to supply a number of important parameters for the method. 
		auser --> this is your username for the CS130 course database
		apassword --> this is the password corresponding to your username for the CS130 course database. 
		ascheme --> this is the name of the database or schema within the webcourse postgreSQL database which you want to query. So this schema could be called
		the same as the value of auser or it might be public which is the publicly accessible schema created and used by Peter Mooney for demonstration purposes. 
	
		How to use this?
		This is a static method - so all you need to do is to call this method with the correct parameters

		For example
		execute_UPDATE_or_DELETE_on_Database("PeterM","PeterPassword","public","UPDATE tablename SET X = 14 WHERE Y < 20");

		YOU DO NOT NEED TO EDIT ANY OF THE CODE IN THE METHOD - If you do so you're on your own. We're not trying to write Java code we're just using it
		as a means of example of accessing the database away from PGAdmin
	*/
	public static void execute_UPDATE_or_DELETE_on_Database(String auser, String apassword, String aschema,String aqueryString)
	{
		Connection con = null;
		
		Statement stmt = null;
		try
		{
			//This is your schema in the CS130 database. 
			// You should change this to be your schema name ie dm18920

			String schemaName = aschema;

			String url = "jdbc:postgresql://webcourse.cs.nuim.ie/cs130?currentSchema="+schemaName+"&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			
			// Now for the username and password that you normally use to logon to the PostgreSQL PGAdmin server
			String user =  auser;
			String password = apassword;
			
			Class dbDriver = Class.forName("org.postgresql.Driver");
	
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("******* Successfully connected to webcourse.cs.nuim.ie using SSL with validation\n\n");
			
			stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(aqueryString);
			System.out.println("\t\tEXECUTING UPDATE OR DELETE QUERY STATEMENT\t\t");

      		System.out.println("\t\tYour query string is: " + aqueryString + "\n");

  			String timeStamp = new SimpleDateFormat("HH:mm:ss E,dd, MMMMM yyyy").format(Calendar.getInstance().getTime());
  			System.out.println("\t\tQuery executed at " + timeStamp);
      		
 			if (rowsAffected > 0)
 			{
 					System.out.println("\t\tRESULT: Number of Rows Affected [" + rowsAffected + "]");
 			}
 			else
 			{
  					System.out.println("\t\tRESULT: This query did not affect any rows");				
 			}

			
			stmt.close();
			con.close();			
			System.out.println("****** Successfully disconnected to webcourse.cs.nuim.ie using SSL with validation\n\n");
			
		}
		catch (Exception ex)
		{
			System.out.println("ERROR: An error has occured connecting to webcourse.cs.nuim.ie using SSL with validation\n\n");
			ex.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					System.out.println("****** Connection to webcourse.cs.nuim.ie using SSL with validation. CLOSED\n\n");
					System.out.println("ATTENTION MOODLE QUIZ CODE:  SQL" + generateResultCode() + "SQL");
				}
				catch (Exception e){
					System.out.println("****** Connection closing exception to webcourse.cs.nuim.ie using SSL with validation\n\n");
				}
			}
		}
	}

	/*
	This method takes the result of a SELECT query and writes the ResultSet to an output HTML file 
	You will not need to do anything with this method. 
	*/
	public static void writeQueryResultsToOutputFile(ResultSet rs, String aqueryString, String outputFileName)
	{
		try
		{

   			ResultSetMetaData rsmd = rs.getMetaData();

   			PrintWriter outwriter = new PrintWriter(outputFileName);

   			System.out.println("\t\tWriting Results to output HTML file called [" + outputFileName + "]");

   			outwriter.println("<html><head><title>SQL Select Statement Output</title></head>");
   			outwriter.println("<body>");

      		outwriter.println("<h1>Your Select Query Output</h1>");
      		outwriter.println("<p><b>Your query was ....</b> <br/>");
      		outwriter.println("<code>" + aqueryString + "</code></p>");
      		outwriter.println("<img src = 'https://www.maynoothuniversity.ie/sites/all/themes/nuim_themes/nuim/logo.png'/><br/>");
      		outwriter.println("<img src = 'http://www.postgresql.org/media/img/layout/hdr_left.png'/><br/>");

  			String timeStamp = new SimpleDateFormat("HH:mm:ss E,dd, MMMMM yyyy").format(Calendar.getInstance().getTime());
  			outwriter.println("<p>Output generated at <b>" + timeStamp + "</b></p>");
      		
      		int numberOfColumns = rsmd.getColumnCount();
  			
      		outwriter.println("<p>There are " + numberOfColumns + " columns in your output ResultSet</p>");	

      		outwriter.println("<table border = '1'>");	

      		outwriter.println("<tr>");
      		for (int i = 1; i <= numberOfColumns; i++) {
        		String columnName = rsmd.getColumnName(i);
        		outwriter.print("<th>"+columnName+"</th>");
      		}
      		outwriter.println("</tr>");
      		int rows = 0;
     		while (rs.next()) {

     			rows = rows + 1;
     			outwriter.println("<tr>");
        		for (int i = 1; i <= numberOfColumns; i++) 
        		{
          			
          			String columnValue = rs.getString(i);
          			outwriter.print("<td>"+columnValue + "</th>");
        		}
        		outwriter.println("</tr>"); 
      		}
      		outwriter.println("<table>"); 

      		outwriter.println("<h2>There are <b>" + rows + "</b> rows in the output ResultSet</h2>");

      		outwriter.println("</body>");
      		outwriter.println("</html>");
      		outwriter.close();
		}
		catch (Exception ex)
		{
			System.out.println("ERROR: An error has occured while writing the query ResultSet to the output file\n\n");
			ex.printStackTrace();
		}



	} // end of method. 

	public static String generateResultCode()
	{
		String fullCode = UUID.randomUUID().toString().toUpperCase().substring(0,5);	
		
		return fullCode;
	}

} // end of Java code
