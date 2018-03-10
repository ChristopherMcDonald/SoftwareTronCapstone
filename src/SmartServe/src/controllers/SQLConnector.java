package controllers;

import java.util.HashMap;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class SQLConnector {

	private int port; // port if successfully connect
	private String address; // address if successfully connect
	static Connection myConn;


	public static void main(String[] args) {
		//if connection is successful
		if(connect(3306, "localhost")){
			try {
				//connect for real
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");

				/*Data to get from UI*/
				
				/*Users 
				 *@Params username, password
				 *returns boolean - successful save?
				 *passed into signup_proc
				 */
				
				//example;
				//Object[] users = new Object[]{"Sharon", "march10"};
				
				/*myReturns 
				 *@Params user_id, shot_id, returned, timestamp
				 *returns boolean - successful save?
				 *passed into returned proc
				 */
				
				//example;
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				//Object[] myReturns = new Object[]{25, 1, 1, sdf.format(new Date(System.currentTimeMillis()))};
				
				/*Signin 
				 *@Params username, password
				 *returns userid as resultset
				 *passed into login proc
				 */
				/* Examples!
				 * 
				 * 
				Object[] signin = new Object[]{"Sharon", "apples"};

				//signup_proc saves the user to the database
				save("signup_proc",users);
				//returned proc saves the return stats to the database
				save("returned",myReturns);
				//login proc gets the user id of a user
				//getInt(1) gets first cell from resultset 
				ResultSet rs = query("login_proc",signin);
				rs.next();
				int result = rs.getInt(1);
				*/
				
				myConn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static boolean connect(int port) { 		
		try {
			//get connection
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/smartserve?useSSL=false","root", "smarTserve91");
			myConn.close();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public static boolean connect(int port, String address) {
		try {
			//get connection
			myConn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/smartserve","root", "smarTserve91");
			myConn.close();
			return true;

		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public static ResultSet query(String proc, Object[] values) throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
		
		String argus = "(";
		for(int i=0; i < values.length - 1; i++) {
			argus = argus + "?,";
		}

		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");

		for(int j = 0; j < values.length; j++) {
			
			switch(values[j].getClass().getSimpleName()) {
				case("Double"): 	cs.setDouble(j+1, (Double) values[j]); break;
				case("String"): 	cs.setString(j+1, (String) values[j]); break;
				case("Integer"): 	cs.setInt(j+1, (Integer) values[j]); break;
				default:			throw new IllegalArgumentException();
			}
			
		}
		cs.execute();
		
		return cs.getResultSet();
	}

	public static boolean save(String proc, Object[] values) throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
		
		String argus = "(";
		for(int i=0; i < values.length - 1; i++) {
			argus = argus + "?,";
		}

		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");

		for(int j = 0; j < values.length; j++) {
			
			switch(values[j].getClass().getSimpleName()) {
				case("Double"): 	cs.setDouble(j+1, (Double) values[j]); break;
				case("String"): 	cs.setString(j+1, (String) values[j]); break;
				case("Integer"): 	cs.setInt(j+1, (Integer) values[j]); break;
				case("Date"):		cs.setDate(j+1, (Date) values[j]); break;
				default:			throw new IllegalArgumentException();
			}
			
		}
		cs.executeUpdate();
		return true;
	}

	/*Methods without procs
	 * 
	 * Method to add rows
	 * 
	static void add_user(String name, String password) {
		try {
			//execute SQL query 
			//insert
		    PreparedStatement preparedStmt = myConn.prepareStatement(
		    		"INSERT INTO user (user_name, password)" 
		    		+ "VALUES (?,?)"
		    );

		    //set parameters
		    preparedStmt.setString (1, name);
		    preparedStmt.setString (2, password);

		    //execute
			preparedStmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//Method to delete rows - USED FOR TESTING
	static void delete_user(int id) {
		try {
			//execute SQL query 
			//insert
		    PreparedStatement preparedStmt = myConn.prepareStatement("DELETE FROM user WHERE user_id = ?");

		    //set parameters
		    preparedStmt.setInt(1, id);

		    //execute
			preparedStmt.execute();

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	 */
}
