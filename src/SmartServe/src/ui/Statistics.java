package ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.SQLConnector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Statistics extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dateInput0;
	private JTextField dateInputF;
	private JTextField velocityInput0;
	private JTextField velocityInputF;
	private JTextField angleInput0;
	private JTextField angleInputF;
	String zonesString = "";
	int zoneOutput;
	double angleOutput;
	double velocityOutput;
	boolean returnOutput;
	Date dateOutput;
	private JTable statsTable;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 670, 297);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblStats = new JLabel("Statistics");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Century", Font.PLAIN, 35));
		lblStats.setBounds(241, 0, 153, 69);
		contentPane.add(lblStats);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(211, 84, 0, 143);
		contentPane.add(separator);
		
		
		JButton btnZone2 = new JButton("2");
		btnZone2.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone2.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "2,";
				}
				else {
					btnZone2.setBackground(null);
					selected = true;
					index = zonesString.indexOf('2');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone2.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone2.setBounds(10, 155, 29, 29);
		btnZone2.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone2);
		
		JButton btnZone3 = new JButton("3");
		btnZone3.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone3.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "3,";
				}
				else {
					btnZone3.setBackground(null);
					selected = true;
					index = zonesString.indexOf('3');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone3.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone3.setBounds(10, 127, 29, 29);
		btnZone3.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone3);
		
		JButton btnZone4 = new JButton("4");
		btnZone4.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone4.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "4,";
				}
				else {
					btnZone4.setBackground(null);
					selected = true;
					index = zonesString.indexOf('4');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone4.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone4.setBounds(10, 99, 29, 29);
		btnZone4.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone4);
		
		JButton btnZone5 = new JButton("5");
		btnZone5.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone5.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "5,";
				}
				else {
					btnZone5.setBackground(null);
					selected = true;
					index = zonesString.indexOf('5');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone5.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone5.setBounds(10, 71, 29, 29);
		btnZone5.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone5);
		
		JButton btnZone6 = new JButton("6");
		btnZone6.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone6.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "6,";
				}
				else {
					btnZone6.setBackground(null);
					selected = true;
					index = zonesString.indexOf('6');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone6.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone6.setBounds(38, 155, 29, 29);
		btnZone6.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone6);
		
		JButton btnZone7 = new JButton("7");
		btnZone7.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone7.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "7,";
				}
				else {
					btnZone7.setBackground(null);
					selected = true;
					index = zonesString.indexOf('7');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone7.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone7.setBounds(38, 127, 29, 29);
		btnZone7.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone7);
		
		JButton btnZone8 = new JButton("8");
		btnZone8.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone8.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "8,";
				}
				else {
					btnZone8.setBackground(null);
					selected = true;
					index = zonesString.indexOf('8');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone8.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone8.setBounds(38, 99, 29, 29);
		btnZone8.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone8);
		
		JButton btnZone9 = new JButton("9");
		btnZone9.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone9.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "9,";
				}
				else {
					btnZone9.setBackground(null);
					selected = true;
					index = zonesString.indexOf('9');
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+2);
				}
			}
		});
		btnZone9.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone9.setBounds(38, 71, 29, 29);
		btnZone9.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone9);
		
		JButton btnZone10 = new JButton("10");
		btnZone10.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone10.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "10,";
				}
				else {
					btnZone10.setBackground(null);
					selected = true;
					index = zonesString.indexOf("10");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone10.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone10.setBounds(66, 155, 29, 29);
		btnZone10.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone10);
		
		JButton btnZone11 = new JButton("11");
		btnZone11.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone11.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "11,";
				}
				else {
					btnZone11.setBackground(null);
					selected = true;
					index = zonesString.indexOf("11");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone11.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone11.setBounds(66, 127, 29, 29);
		btnZone11.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone11);
		
		JButton btnZone12 = new JButton("12");
		btnZone12.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone12.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "12,";
				}
				else {
					btnZone12.setBackground(null);
					selected = true;
					index = zonesString.indexOf("12");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone12.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone12.setBounds(66, 99, 29, 29);
		btnZone12.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone12);
		
		JButton btnZone13 = new JButton("13");
		btnZone13.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone13.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "13,";
				}
				else {
					btnZone13.setBackground(null);
					selected = true;
					index = zonesString.indexOf("13");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone13.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone13.setBounds(66, 71, 29, 29);
		btnZone13.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone13);
		
		JButton btnZone14 = new JButton("14");
		btnZone14.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone14.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "14,";
				}
				else {
					btnZone14.setBackground(null);
					selected = true;
					index = zonesString.indexOf("14");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone14.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone14.setBounds(94, 155, 29, 29);
		btnZone14.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone14);
		
		JButton btnZone15 = new JButton("15");
		btnZone15.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone15.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "15,";
				}
				else {
					btnZone15.setBackground(null);
					selected = true;
					index = zonesString.indexOf("15");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone15.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone15.setBounds(94, 127, 29, 29);
		btnZone15.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone15);
		
		JButton btnZone16 = new JButton("16");
		btnZone16.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone16.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "16,";
				}
				else {
					btnZone16.setBackground(null);
					selected = true;
					index = zonesString.indexOf("16");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone16.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone16.setBounds(94, 99, 29, 29);
		btnZone16.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone16);
		
		JButton btnZone17 = new JButton("17");
		btnZone17.addActionListener(new ActionListener() {
			boolean selected = true;
			int index;
			public void actionPerformed(ActionEvent arg0) {	
				if(selected) {
					btnZone17.setBackground(new Color(200,200,200));
					selected = false;
					zonesString += "17,";
				}
				else {
					btnZone17.setBackground(null);
					selected = true;
					index = zonesString.indexOf("17");
					zonesString = zonesString.substring(0, index) + zonesString.substring(index+3);
				}
			}
		});
		btnZone17.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 13));
		btnZone17.setBounds(94, 71, 29, 29);
		btnZone17.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnZone17);

		dateInput0 = new JTextField();
		dateInput0.setBounds(127, 176, 47, 20);
		contentPane.add(dateInput0);
		dateInput0.setColumns(10);
		
		dateInputF = new JTextField();
		dateInputF.setBounds(200, 176, 47, 20);
		contentPane.add(dateInputF);
		dateInputF.setColumns(10);
		
		velocityInput0 = new JTextField();
		velocityInput0.setBounds(127, 84, 29, 20);
		contentPane.add(velocityInput0);
		velocityInput0.setColumns(10);
		
		velocityInputF = new JTextField();
		velocityInputF.setBounds(189, 84, 29, 20);
		contentPane.add(velocityInputF);
		velocityInputF.setColumns(10);
		
		angleInput0 = new JTextField();
		angleInput0.setBounds(127, 131, 29, 20);
		contentPane.add(angleInput0);
		angleInput0.setColumns(10);
		
		angleInputF = new JTextField();
		angleInputF.setBounds(189, 131, 29, 20);
		contentPane.add(angleInputF);
		angleInputF.setColumns(10);
		
		JLabel dash0 = new JLabel("-");
		dash0.setHorizontalAlignment(SwingConstants.CENTER);
		dash0.setBounds(184, 179, 9, 14);
		contentPane.add(dash0);
		
		JLabel dash1 = new JLabel("-");
		dash1.setHorizontalAlignment(SwingConstants.CENTER);
		dash1.setBounds(166, 86, 9, 14);
		contentPane.add(dash1);
		
		JLabel dash2 = new JLabel("-");
		dash2.setHorizontalAlignment(SwingConstants.CENTER);
		dash2.setBounds(170, 134, 9, 14);
		contentPane.add(dash2);
		
		JLabel velocityLbl = new JLabel("Velocity Range");
		velocityLbl.setBounds(127, 71, 113, 14);
		contentPane.add(velocityLbl);
		
		JLabel angleLbl = new JLabel("Angle Range");
		angleLbl.setBounds(127, 114, 124, 14);
		contentPane.add(angleLbl);
		
		JLabel dateLbl = new JLabel("Date Range (YYYY-MM-DD)");
		dateLbl.setBounds(127, 162, 190, 14);
		
		contentPane.add(dateLbl);
		
		String[] cols = {"Zone", "Velocity" , "Angle", "Returned?","Date"};
	    DefaultTableModel model = new DefaultTableModel(cols,0);
	    statsTable = new JTable(model);
	    statsTable.setBounds(281, 57, 363, 170);
		contentPane.add(statsTable);
		
		JButton btnNewButton = new JButton("Get Statistics");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
			    model.addRow(cols);
				Object[] statsObj = new Object[]{
						//Login.user_id,
						35,
						zonesString,
						velocityInput0.getText(), 
						velocityInputF.getText(),
						angleInput0.getText(),
						angleInputF.getText(),
						dateInput0.getText(),
						dateInputF.getText()
				};
				for(int i=0; i < statsObj.length; i++) {
					if(statsObj[i].equals("")) {
						statsObj[i] = null;
					}
				}
				String[] statsTypes = new String[] {
						"Integer", "String", "Integer", "Integer", "Integer", "Integer", "String", "String"
				};
			
				try {
					ResultSet rs = SQLConnector.query("statistics", statsObj, statsTypes);
					
					//loop through get results
					while(rs.next()) {
						zoneOutput = rs.getInt("zone_id");
						velocityOutput = rs.getDouble("velocity");
						angleOutput = rs.getDouble("angle");
						returnOutput = rs.getBoolean("returned");
						dateOutput = rs.getDate("time_stamp");
						Object[] row = {zoneOutput, velocityOutput,angleOutput,returnOutput,dateOutput};
				    	model.addRow(row);
						
					}
					

				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			    
			}
		});
		btnNewButton.setBounds(66, 204, 125, 23);
		contentPane.add(btnNewButton); 
        
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.statsf.setVisible(false);
				View.profilef.setVisible(true);
			}
		});
		menuBar.add(mntmProfile);
		
		JMenuItem mntmControl = new JMenuItem("Control");
		mntmControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.statsf.setVisible(false);
				View.controlf.setVisible(true);
			}
		});
		menuBar.add(mntmControl);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		menuBar.add(mntmStatistics);
		
		
	}
}
