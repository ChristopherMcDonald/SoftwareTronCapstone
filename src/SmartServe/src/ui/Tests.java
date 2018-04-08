package ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.SQLConnector;
import enums.Mode;
import runnables.Controller;

public class Tests extends JFrame {

	private JPanel contentPane;
	private Controller control;

	public Tests() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblControl = new JLabel("Testing");
		lblControl.setFont(new Font("Century", Font.PLAIN, 35));
		lblControl.setBounds(149, 4, 137, 69);
		contentPane.add(lblControl);

		JButton test1Btn = new JButton("Test 1");			 
		test1Btn.setBounds(110,71,100, 40);				
		contentPane.add(test1Btn);

		JButton test2Btn = new JButton("Test 2");			 
		test2Btn.setBounds(219,71,100, 40);				
		contentPane.add(test2Btn);

		JButton test3Btn = new JButton("Test 3");			 
		test3Btn.setBounds(110,131,100, 40);				
		contentPane.add(test3Btn);
		
		JButton test4Btn = new JButton("Test 4");			 
		test4Btn.setBounds(219,131,100, 40);				
		contentPane.add(test4Btn);

		test1Btn.addActionListener(new ActionListener() { // buttons active when start is pressed
			public void actionPerformed(ActionEvent ae) {
				control = new Controller(35);
				control.setMode(Mode.ONESHOT);
				control.setShots(48);
				Thread t = new Thread(control);
				System.out.println("Zone: 17, Pitch: 20, Roll: 0");
				t.start();
			}
		});
		
		test2Btn.addActionListener(new ActionListener() { // buttons active when start is pressed
			public void actionPerformed(ActionEvent ae) {
				control = new Controller(35);
				control.setMode(Mode.ONESHOT);
				control.setShots(91);
				Thread t = new Thread(control);
				System.out.println("Zone: 12, Pitch: 10, Roll: 90");
				t.start();
			}
		});
		
		test3Btn.addActionListener(new ActionListener() { // buttons active when start is pressed
			public void actionPerformed(ActionEvent ae) {
				control = new Controller(35);
				control.setMode(Mode.ONESHOT);
				control.setShots(148,193);
				Thread t = new Thread(control);
				System.out.println("Zone: 5, Pitch: 10, Roll: 180");
				System.out.println("Zone: 2, Pitch: 0, Roll: 270");
				t.start();
			}
		});
		
		test4Btn.addActionListener(new ActionListener() { // buttons active when start is pressed
			public void actionPerformed(ActionEvent ae) {
				control = new Controller(35);
				control.setMode(Mode.ONESHOT);
				control.setShots(148,193,148);
				Thread t = new Thread(control);
				System.out.println("Zone: 5, Pitch: 10, Roll: 180");
				System.out.println("Zone: 2, Pitch: 0, Roll: 270");
				System.out.println("Zone: 5, Pitch: 10, Roll: 180");
				t.start();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.testf.setVisible(false);
				View.profilef.setVisible(true);
			}
		});
		menuBar.add(mntmProfile);
		
		JMenuItem mntmControl = new JMenuItem("Control");
		mntmControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.testf.setVisible(false);
				View.controlf.setVisible(true);
			}
		});
		menuBar.add(mntmControl);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		mntmStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.testf.setVisible(false);
				View.statsf.setVisible(true);
			}
		});
		menuBar.add(mntmStatistics);
		
		JMenuItem mntmTests = new JMenuItem("Testing");
		mntmTests.setBackground(SystemColor.activeCaption);
		menuBar.add(mntmTests);
	}

}
