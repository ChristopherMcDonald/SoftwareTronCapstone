package ui;
import java.awt.Font;
import java.sql.Date;

import javax.swing.*;

import enums.Mode;

public class View {

	public static Welcome welcomef = new Welcome();
	public static Signup signupf = new Signup();
	public static Login loginf = new Login();
	public static Profile profilef = new Profile();
	public static Control controlf = new Control();
	public static Statistics statsf = new Statistics();
	public static Tests testf = new Tests();

	public static int user_id;
	public static String username;

	public static void main(String[] args) {
		welcomef.setVisible(true);
	}
	public static void setUsername(String name) {
		username = name;
	}
	public static String getUsername() {
		return username;
	}

	public static void setUserid(int id) {
		user_id = id;
	}

	public static int getUserid() {
		return user_id;
	}
}
