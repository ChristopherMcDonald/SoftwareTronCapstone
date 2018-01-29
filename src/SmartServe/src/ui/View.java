package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  
import alt.*;

public class View {
	
	private Controller control;
	private static boolean paused = true;		
	public static void main(String[] args) {  
		
		JFrame f = new JFrame();				//creating instance of JFrame  
		Controller c = new Controller();
		Thread t = new Thread(c);
		t.start();

		JButton startBtn = new JButton("Start");		//creating instance of JButton  
		startBtn.setBounds(50,100,100, 40);				//x axis, y axis, width, height  
		
		JButton pauseBtn = new JButton("Pause");		//creating instance of JButton  
		pauseBtn.setBounds(150,100,100, 40);			//x axis, y axis, width, height 
							//is it paused?
		
		JButton stopBtn = new JButton("Stop");			//creating instance of JButton  
		stopBtn.setBounds(250,100,100, 40);				//x axis, y axis, width, height  
		
		String[] modes = { "Training","Random"};		//training modes
	    final JComboBox<String> modeDropDown = new JComboBox<String>(modes); //creating instance of JComboBox(Dropdown)
	    modeDropDown.setBounds(150, 150, 100,40);		//x acis, y axis, width, height
	    
	    f.add(modeDropDown);							//add JComboBox in JFrame
		f.add(startBtn);								//adding buttons in JFrame  
		f.add(pauseBtn);
		f.add(stopBtn);

		f.setSize(400,500);						//400 width and 500 height  
		f.setLayout(null);						//using no layout managers  
		f.setVisible(true);						//making the frame visible  
		
		startBtn.setEnabled(true);				//start button enabled
        pauseBtn.setEnabled(false);				//pause button disabled
        stopBtn.setEnabled(false);  			//stop button disabled
        modeDropDown.setEnabled(true);			//mode drop down enabled

		
		startBtn.addActionListener(new ActionListener() { // buttons active when start is pressed
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(false);
		        pauseBtn.setEnabled(true);
		        stopBtn.setEnabled(true);  
		        modeDropDown.setEnabled(false);
		     }
		   }
		 );
		
		pauseBtn.addActionListener(new ActionListener() { //buttons active when pause is pressed
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(false);
		        pauseBtn.setEnabled(true);
		        stopBtn.setEnabled(true);  
		        modeDropDown.setEnabled(false);
		        
		        if(paused) { 				//if pause then continue
		        	paused = false;
		        	pauseBtn.setText("Continue");
		        }
		        else {						//if continue then pause
		        	paused = true;
		        	pauseBtn.setText("Pause");
		        }
		     }
		   }
		 );
		
		stopBtn.addActionListener(new ActionListener() { //buttons active when stop is pressed
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(true);
		        pauseBtn.setEnabled(false);
		        stopBtn.setEnabled(false);  
		        modeDropDown.setEnabled(true);
		     }
		   }
		 );
	}  
}  