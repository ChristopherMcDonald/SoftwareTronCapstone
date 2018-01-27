package controllers;

import java.util.HashMap;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				String[] users = new String[]{"Sharon", "plzwork"};
				String[] myReturns = new String[]{"Hi", "5", "true", "jan27"};
				String[] nextShot = new String[]{"test"};

				save("signup_proc",users);
				save("returned",myReturns);

				query("next_shot", nextShot);
				query("sign_in",users);

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
		String argus = "(";
		for(int i=0; i < values.length - 1; i++) {
			argus = argus + "?,";
		}

		System.out.println(argus);

		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");

		for(int j = 0; j < values.length; j++) {
			System.out.println(j + " - " + values[j]); 
			
			switch(values[j].getClass().getSimpleName()) {
				case("Double"): 	cs.setDouble(j, (Double) values[j]);
				case("String"): 	cs.setString(j, (String) values[j]);
				case("Integer"): 	cs.setInt(j, (Integer) values[j]);
				default:			throw new IllegalArgumentException();
			}
			
		}
		cs.execute();
		
		return cs.getResultSet();
	}

	public static boolean save(String proc, Object[] values) throws SQLException {
		String argus = "(";
		for(int i=0; i < values.length - 1; i++) {
			argus = argus + "?,";
		}

		System.out.println(argus);

		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");

		for(int j = 0; j < values.length; j++) {
			System.out.println(j + " - " + values[j]); 
			
			switch(values[j].getClass().getSimpleName()) {
				case("Double"): 	cs.setDouble(j, (Double) values[j]);
				case("String"): 	cs.setString(j, (String) values[j]);
				case("Integer"): 	cs.setInt(j, (Integer) values[j]);
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
