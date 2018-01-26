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
		if(connect(3306, "localhost")){
			try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve?useSSL=false","root", "smarTserve91");
			
			Map users = new HashMap();
			Map myReturns = new HashMap();
			
	        // Add a user
	        users.put(1,"Sharon"); //user name
	        users.put(2, "plzwork"); //password
	        
	        myReturns.put(1, "Hi"); //user
	        myReturns.put(2, "5"); //shot
	        myReturns.put(3, "true"); //returned
	        myReturns.put(4, "jan26"); //timestamp
	        
	        //query("next_shot", testMap,1);
			//query("sign_in",testMap,1);
			
			save("signup_proc",users,2);
			save("returned",myReturns,4);
			
			myConn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
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
	
	public static ResultSet query(String proc, Map<String, String> values, int numArgs) {
		try {
			//create a statement
			Statement myStmt1;
			myStmt1 = myConn.createStatement();
			ResultSet rs = myStmt1.executeQuery("select * from user");
			//process results
			
			//need to figure out how to process these without knowing the names of the columns
			while (rs.next()) {
				System.out.println("user_name: " + rs.getString("user_name") + ", " + "password: " + rs.getString("password"));
			}
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean save(String proc, Map<Integer, Object> values, int numArgs) {
		try {
				
		String argus = "(";
		for(int i=0; i < numArgs-1; i++) {
			argus = argus + "?,";
		}
		
		System.out.println(argus);
		
		CallableStatement cs = myConn.prepareCall("{call "+ proc + argus + "?)}");
		
		for(int key: values.keySet()) {
			System.out.println(key + " - " + values.get(key)); 
	        cs.setString(key, (String) values.get(key));
	    }
		cs.executeUpdate();
		return true;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
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
