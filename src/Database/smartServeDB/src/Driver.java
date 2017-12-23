import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		try {
			//get connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartserve","root", "smarTserve91");
			
			//create a statement
			Statement myStmt1 = myConn.createStatement();
			
			//execute SQL query

		    PreparedStatement preparedStmt = myConn.prepareStatement(
		    		"INSERT INTO user (user_name, password)" 
		    		+ "VALUES (?,?)"
		    );
			
		    preparedStmt.setString (1, "sharon3");
		    preparedStmt.setString (2, "test3");
			preparedStmt.execute();
			
			ResultSet myRs1 = myStmt1.executeQuery("select * from user");
			
			//process results
			while (myRs1.next()) {
				System.out.println(myRs1.getString("user_name") + ": " + myRs1.getString("password"));
			}
			
			myConn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
