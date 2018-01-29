package ui;
import javax.swing.*;  
import alt.*;

public class View {
	
	private Controller control;
	
	public static void main(String[] args) {  
		
		JFrame f = new JFrame();				//creating instance of JFrame  
		Controller c = new Controller();
		Thread t = new Thread(c);
		t.start();

		JButton b = new JButton("Start");		//creating instance of JButton  
		b.setBounds(130,100,100, 40);			//x axis, y axis, width, height  
		
		JButton b1 = new JButton("Pause");		//creating instance of JButton  
		b.setBounds(180,100,100, 40);			//x axis, y axis, width, height 
		
		JButton b2 = new JButton("Stop");		//creating instance of JButton  
		b.setBounds(230,100,100, 40);			//x axis, y axis, width, height  

		f.add(b);								//adding button in JFrame  
		f.add(b1);
		f.add(b2);

		f.setSize(400,500);						//400 width and 500 height  
		f.setLayout(null);						//using no layout managers  
		f.setVisible(true);						//making the frame visible  
	}  
}  