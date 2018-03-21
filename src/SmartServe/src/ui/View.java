package ui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;

import enums.Mode;
import runnables.*;

public class View {
	
	public static Welcome welcomef = new Welcome();
	public static Signup signupf = new Signup();
	public static Login loginf = new Login();
	public static Profile profilef = new Profile();
	public static Control controlf = new Control();
	public static Statistics statsf = new Statistics();
		
	public static int user_id;
	public static String username;
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame();				//creating instance of JFrame  

		JLabel title = new JLabel("Welcome to SmartServe"); //create title JLabel
		title.setBounds(50,50,300,40);
		title.setFont(new Font("Arial", Font.PLAIN, 26));
		
		JButton startBtn = new JButton("Start");		//creating instance of JButton  
		startBtn.setBounds(50,100,100, 40);				//x axis, y axis, width, height  
		
		JButton pauseBtn = new JButton("Pause");		//creating instance of JButton  
		pauseBtn.setBounds(150,100,100, 40);			//x axis, y axis, width, height 
							//is it paused?
		
		JButton stopBtn = new JButton("Stop");			//creating instance of JButton  
		stopBtn.setBounds(250,100,100, 40);				//x axis, y axis, width, height  
		
		String[] modes = { Mode.RANDOM.toString(), Mode.ONESHOT.toString(), Mode.TRAIN.toString()};		//training modes
	    final JComboBox<String> modeDropDown = new JComboBox<String>(modes); //creating instance of JComboBox(Dropdown)
	    modeDropDown.setBounds(100, 150, 200,40);		//x acis, y axis, width, height
	    
	    System.out.print(new Date(System.currentTimeMillis()));
	    
	    f.add(title);									//add title in JFrame
	    
		f.add(startBtn);								//adding buttons in JFrame  
		f.add(pauseBtn);
		f.add(stopBtn);

		f.add(modeDropDown);							//add JComboBox in JFrame
		
		f.setSize(400,500);						//400 width and 500 height  
		f.setLayout(null);						//using no layout managers  
		f.setVisible(true);						//making the frame visible  
		welcomef.setVisible(true);
		
		startBtn.setEnabled(true);				//start button enabled
        pauseBtn.setEnabled(false);				//pause button disabled
        stopBtn.setEnabled(false);  			//stop button disabled
        modeDropDown.setEnabled(true);			//mode drop down enabled


		
		
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