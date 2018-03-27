package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import enums.Mode;
import runnables.Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Control extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private static Controller control;
	private static boolean paused = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Control frame = new Control();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Control() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControl = new JLabel("Control");
		lblControl.setFont(new Font("Century", Font.PLAIN, 35));
		lblControl.setBounds(149, 4, 137, 69);
		contentPane.add(lblControl);
		
		JButton startBtn = new JButton("Start");	  
		startBtn.setBounds(50,71,100, 40);
		contentPane.add(startBtn);
		
		JButton pauseBtn = new JButton("Pause");		  
		pauseBtn.setBounds(159,71,100, 40);	
		contentPane.add(pauseBtn);
		pauseBtn.setEnabled(false);
		
		JButton stopBtn = new JButton("Stop");			 
		stopBtn.setBounds(270,71,100, 40);				
		contentPane.add(stopBtn);
		stopBtn.setEnabled(false);
		
		String[] modes = { Mode.TRAIN.toString(), Mode.ONESHOT.toString(), Mode.RANDOM.toString()};		
	    final JComboBox<String> modeDropDown = new JComboBox<String>(modes); 
		modeDropDown.setFont(new Font("Tahoma", Font.PLAIN, 17));
		modeDropDown.setBounds(160, 127, 100, 20);
		contentPane.add(modeDropDown);
		
		startBtn.addActionListener(new ActionListener() { // buttons active when start is pressed
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(false);
		        pauseBtn.setEnabled(true);
		        stopBtn.setEnabled(true);  
		        modeDropDown.setEnabled(false);
		        
		        control = new Controller(View.getUserid());
		        control.setMode(Mode.valueOf(modeDropDown.getSelectedItem().toString()));
				Thread t = new Thread(control);
				t.start();
				
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
		        	control.pause();
		        }
		        else {						//if continue then pause
		        	paused = true;
		        	pauseBtn.setText("Pause");
		        	control.resume();
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
		        control.terminate();
		     }
		     
		   }
		 );
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.controlf.setVisible(false);
				View.profilef.setVisible(true);
			}
		});
		menuBar.add(mntmProfile);
		
		JMenuItem mntmControl = new JMenuItem("Control");
		mntmControl.setBackground(SystemColor.activeCaption);
		menuBar.add(mntmControl);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		mntmStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.controlf.setVisible(false);
				View.statsf.setVisible(true);
			}
		});
		menuBar.add(mntmStatistics);
		
	}
}
