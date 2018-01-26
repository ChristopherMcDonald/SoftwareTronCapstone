package controllers;

import java.util.HashMap;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnector {
	
	private int port; // port if successfully connect
	private String address; // address if successfully connect
	static Connection myConn;
	
	
	public static void main(String[] args) {
		/*
		System.out.print(connect(3306, "localhost"));
		
		try {
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
		
		Map testMap = new HashMap();
		
		query("next_shot", testMap,1);
		query("sign_in",testMap,1);
		
		save("signup_proc",testMap,2);
		save("returned",testMap,3);
		
		
		
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public static boolean connect(int port) { 		
		try {
			//get connection
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/smartserve?useSSL=false","root", "smarTserve91");
			
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
	
	public static ResultSet query(String proc, Map<String, String> values, int numArgs) {
		// TODO implement
		return null;
	}
	
	public static boolean save(String proc, Map<String, String> values, int numArgs) {
		try {
			
			CallableStatement cs = myConn.prepareCall("{call "+ proc +"(?,?)}");
			
			Map users = new HashMap();
	        
	        // Add a user
	        users.put("Harit","44");

	         
	        System.out.println("Total people: " + users.size());
	        // Iterate over all entries
	        for(Object key: users.keySet()) {
	            System.out.println(key + " - " + users.get(key)); 
	            cs.setString(1, (String) users.get(key));
	            cs.setString(2, (String) key);
	        }
			cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/* Method to add rows
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
	
	//Method to delete rows
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
