package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  
import alt.*;

public class View {
	
	private Controller control;

	public static void main(String[] args) {  
		
		JFrame f = new JFrame();				//creating instance of JFrame  
		Controller c = new Controller();
		Thread t = new Thread(c);
		t.start();

		JButton startBtn = new JButton("Start");		//creating instance of JButton  
		startBtn.setBounds(50,100,100, 40);			//x axis, y axis, width, height  
		
		JButton pauseBtn = new JButton("Pause");		//creating instance of JButton  
		pauseBtn.setBounds(150,100,100, 40);			//x axis, y axis, width, height 
		
		JButton stopBtn = new JButton("Stop");		//creating instance of JButton  
		stopBtn.setBounds(250,100,100, 40);			//x axis, y axis, width, height  

		f.add(startBtn);								//adding button in JFrame  
		f.add(pauseBtn);
		f.add(stopBtn);

		f.setSize(400,500);						//400 width and 500 height  
		f.setLayout(null);						//using no layout managers  
		f.setVisible(true);						//making the frame visible  
		
		startBtn.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(false);
		        pauseBtn.setEnabled(true);
		        stopBtn.setEnabled(true);  
		     }
		   }
		 );
		
		pauseBtn.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(false);
		        pauseBtn.setEnabled(true);
		        stopBtn.setEnabled(true);  
		     }
		   }
		 );
		
		stopBtn.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(true);
		        pauseBtn.setEnabled(false);
		        stopBtn.setEnabled(false);  
		     }
		   }
		 );
	}  
}  