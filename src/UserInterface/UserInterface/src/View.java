import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.Container;
import java.awt.GridLayout;


public class View extends ApplicationFrame {
	
	public static Welcome welcomef = new Welcome();
	public static Signup signupf = new Signup();
	public static Login loginf = new Login();
	public static Profile profilef = new Profile();
	public static Control controlf = new Control();
	public static Statistics statsf = new Statistics();
	
	
	public View(String title) {
	      super( title ); 
	      setContentPane(createDemoPanel( ));
	}

	private static boolean paused = true;		
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
		
		String[] modes = { "Random", "Single", "Train"};		//training modes
	    final JComboBox<String> modeDropDown = new JComboBox<String>(modes); //creating instance of JComboBox(Dropdown)
	    modeDropDown.setBounds(100, 150, 200,40);		//x axis, y axis, width, height
	    
	    View demo = new View( "Mobile Sales" );  
	      demo.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( demo );    
	      //demo.setVisible( true );
	      
	    f.add(title);									//add title in JFrame
	    
		f.add(startBtn);								//adding buttons in JFrame  
		f.add(pauseBtn);
		f.add(stopBtn);

		f.add(modeDropDown);							//add JComboBox in JFrame
		
		
		
		f.setSize(400,500);						//400 width and 500 height  
		f.setLayout(null);						//using no layout managers  
		//f.setVisible(true);						//making the frame visible  
		welcomef.setVisible(true);				//making the welcome frame visible  

		
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
	
		   
		   private PieDataset createDataset( ) {
		      DefaultPieDataset dataset = new DefaultPieDataset( );
		      dataset.setValue( "IPhone 5s" , new Double( 20 ) );  
		      dataset.setValue( "SamSung Grand" , new Double( 20 ) );   
		      dataset.setValue( "MotoG" , new Double( 40 ) );    
		      dataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
		      return dataset;         
		   }
		   
		   private JFreeChart createChart( PieDataset dataset ) {
		      JFreeChart chart = ChartFactory.createPieChart(      
		         "Mobile Sales",   // chart title 
		         dataset,          // data    
		         true,             // include legend   
		         true, 
		         false);

		      return chart;
		   }
		   
		   public JPanel createDemoPanel( ) {
		      JFreeChart chart = createChart(createDataset( ) );  
		      return new ChartPanel( chart ); 
		   }

}  