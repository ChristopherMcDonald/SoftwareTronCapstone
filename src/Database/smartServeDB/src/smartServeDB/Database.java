package smartServeDB;
import java.sql.*;

public class Database {
	static Connection myConn;
	
	public static void connection(String[] args) {
		try {
			//get connection
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve","root", "smarTserve91");
	
			
			//login_proc("user","hello");

			output_userTable();
			myConn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	static void output_userTable() {
		try {
			//create a statement
			Statement myStmt1;
			myStmt1 = myConn.createStatement();
			ResultSet myRs1 = myStmt1.executeQuery("select * from user");
			//process results
			while (myRs1.next()) {
				System.out.println("user_name: " + myRs1.getString("user_name") + ", " + "password: " + myRs1.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	static void login_proc(String name, String password) {
		try {
			CallableStatement cs = myConn.prepareCall("{call signup_proc(?, ?)}");
			cs.setString(1, name);
			cs.setString(2, password);
			//cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}

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
