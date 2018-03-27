package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnector {

	static Connection myConn;

	public static void main(String[] args) {
		//if connection is successful
		if(connect(3306, "localhost")){
			try {
				//connect for real
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
				
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

	public static ResultSet query(String proc, Object[] values, String[] types) throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
		
		String argus = "(";
		for(int i=0; i < values.length - 1; i++) {
			argus = argus + "?,";
		}

		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");

		for(int j = 0; j < values.length; j++) {
			
			switch(types[j]) {
				case("Double"): 
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.DOUBLE);
					else					cs.setDouble(j+1, (Double) values[j]);
					break;
				case("String"):
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.VARCHAR);
					else					cs.setString(j+1, (String) values[j]);
					break;
				case("Integer"): 	
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.INTEGER);
					else					cs.setInt(j+1, (Integer) values[j]);
					break;
				case("Date"):
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.DATE);
					else 					cs.setDate(j+1, (Date) values[j]);
					break;
				default:			throw new IllegalArgumentException();
			}
			
		}
		cs.execute();
		
		return cs.getResultSet();
	}

	public static boolean save(String proc, Object[] values, String[] types) throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
		
		String argus = "(";
		for(int i=0; i < values.length - 1; i++) {
			argus = argus + "?,";
		}

		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");

		for(int j = 0; j < values.length; j++) {

			
			switch(types[j]) {
				case("Double"): 
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.DOUBLE);
					else					cs.setDouble(j+1, (Double) values[j]);
					break;
				case("String"):
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.VARCHAR);
					else					cs.setString(j+1, (String) values[j]);
					break;
				case("Integer"): 	
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.INTEGER);
					else					cs.setInt(j+1, (Integer) values[j]);
					break;
				case("Date"):
					if(values[j] == null) 	cs.setNull(j + 1, java.sql.Types.DATE);
					else 					cs.setDate(j+1, (Date) values[j]);
					break;
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
