package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;

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
import javax.swing.JTextField;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;

public class Statistics extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static JInternalFrame internalFrame;

	private static JTextField dateInput0;
	private static JTextField dateInputF;
	private static JTextField rollInput0;
	private static JTextField rollInputF;
	private static JTextField pitchInput0;
	private static JTextField pitchInputF;
	private static String zonesString = "";
	private static JLabel lblNoResults;

	int zoneOutput;
	double pitchOutput;
	double rollOutput;
	boolean returnOutput;
	Date dateOutput;
	private static Object[][] outputData; //row#, values(0:zone, 1:pitch, 2:roll, 3: returned, 4:date)

	static int rowCount;
	private static String[] cols = {"Zone", "Roll" , "Pitch", "Returned?","Date"};
	private static DefaultTableModel model = new DefaultTableModel(cols,0);
	private static JTable statsTable = new JTable(model);

	static Graph statsGraph;
	static JButton btnxZone;
	static JButton btnxRoll;
	static JButton btnxPitch;

	static boolean graphView = true;
	static boolean chartView = false;


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
		setBounds(200, 200, 731, 350);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		createLbls();
		createZoneBtns();
		createTextInputs();
		setJMenuBar(createMenu());

		JButton btnGetStats = new JButton("Get Statistics");
		btnGetStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] statsObj = new Object[]{
						//View.getUserid(),
						35,
						zonesString,
						rollInput0.getText(),
						rollInputF.getText(),
						pitchInput0.getText(),
						pitchInputF.getText(),
						dateInput0.getText(),
						dateInputF.getText()
				};
				for(int i=0; i < statsObj.length; i++) {
					if(statsObj[i].equals("")) {
						statsObj[i] = null;
					}
				}
				String[] statsTypes = new String[] {
						"Integer", "String", "String", "String", "String", "String", "String", "String"
				};
				try {
					ResultSet rs = SQLConnector.query("statistics", statsObj, statsTypes);
					if(rs.last()) {
						rowCount = rs.getRow();
						lblNoResults.setVisible(false);
						rs.beforeFirst();
						outputData = new Object[rowCount][5];

						int counter = 0;
						//loop through get results
						while(rs.next()) {
							zoneOutput = rs.getInt("zone_id");
							rollOutput = rs.getDouble("roll");
							pitchOutput = rs.getDouble("pitch");
							returnOutput = rs.getBoolean("returned");
							dateOutput = rs.getDate("time_stamp");
							outputData[counter][0] = zoneOutput;
							outputData[counter][1] = rollOutput;
							outputData[counter][2] = pitchOutput;
							outputData[counter][3] = returnOutput;
							outputData[counter][4] = dateOutput;
							counter++;
						}
						if(chartView) {
							showChart();

						}
						else {
							showGraph();
						}
					}
					else {
						outputData = new Object[rowCount][5];
						noResults();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		btnGetStats.setBounds(66, 254, 125, 23);
		contentPane.add(btnGetStats);

		JButton btnChartView = new JButton("Chart View");
		btnChartView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rowCount!=0) {
					graphView = false;
					chartView = true;
					showChart();
				}
				else {
					noResults();
				}
			}
		});
		btnChartView.setBounds(616, 53, 89, 23);
		btnChartView.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnChartView);

		JButton btnGraphView = new JButton("Graph View");
		btnGraphView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rowCount!=0) {
					graphView = true;
					chartView = false;
					showGraph();
				}
				else {
					noResults();
				}
			}
		});
		btnGraphView.setBounds(527, 53, 89, 23);
		btnGraphView.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnGraphView);

		btnxZone = new JButton("Zone");
		btnxZone.setBounds(374, 254, 89, 23);
		contentPane.add(btnxZone);
		btnxZone.setVisible(false);

		btnxRoll = new JButton("Roll");
		btnxRoll.setBounds(473, 254, 89, 23);
		contentPane.add(btnxRoll);
		btnxRoll.setVisible(false);

		btnxPitch = new JButton("Pitch");
		btnxPitch.setBounds(572, 254, 89, 23);
		contentPane.add(btnxPitch);
		btnxPitch.setVisible(false);

		internalFrame = new JInternalFrame("");
		internalFrame.setLayout(null);
		internalFrame.setBounds(281, 87, 403, 170);
		internalFrame.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
		contentPane.add(internalFrame);
		internalFrame.setVisible(true);

	}

	public static void noResults() {
		lblNoResults.setVisible(true);
	}

	public static void showChart() {
		model.setRowCount(0);
	    model.addRow(cols);
		internalFrame.add(statsTable);
		for(int i=0; i < outputData.length; i++) {
			model.addRow(outputData[i]);
		}
	}

	public static void showGraph() {
		//default
		statsGraph = new Graph("Graph",dataReturn("zone"),"zone",1);
		internalFrame.add(statsGraph.chartPanel);
		statsGraph.pack();

		btnxZone.setVisible(true);
		btnxZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statsGraph = new Graph("Graph",dataReturn("zone"),"zone",1);
				internalFrame.add(statsGraph.chartPanel);
				statsGraph.pack();
			}
		});

		btnxRoll.setVisible(true);
		btnxRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statsGraph = new Graph("Graph",dataReturn("roll"),"roll",90);
				internalFrame.add(statsGraph.chartPanel);
				statsGraph.pack();
			}
		});

		btnxPitch.setVisible(true);
		btnxPitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statsGraph = new Graph("Graph",dataReturn("pitch"),"pitch",10);
				internalFrame.add(statsGraph.chartPanel);
				statsGraph.pack();
			}
		});


	}

	public static Double[] dataReturn(String xAxis) {
		int initial;
		int last;
		int step;
		int size;
		int pos;

		if(xAxis.equals("zone")) {
			initial = 2;
			last = 17+1;
			step = 1;
			size = 16;
			pos = 0;
		}
		else if(xAxis.equals("roll")) {
			initial = 0;
			last = 270+1;
			step = 90;
			size = 4;
			pos = 1;
		}
		else if(xAxis.equals("pitch")) {
			initial = 0;
			last = 30+1;
			step = 10;
			size = 4;
			pos = 2;
		}
		else {
			return null;
		}

		Double returnRate[] = new Double[size];
		int iterator = 0;
		for(int i=initial; i<last; i+=step) {
			double xCount = 0.0;
			int trueCount = 0;
			for(int j=0; j < outputData.length; j++) {
				if(Double.parseDouble(outputData[j][pos].toString())==i){
					xCount++;
					if(outputData[j][3].toString() == "true") {
						trueCount++;
					}
				}
			}

			if(xCount==0) {
				returnRate[iterator] = 0.0;
				iterator++;
			}
			else{
				returnRate[iterator] = trueCount/xCount*100.0;
				iterator++;
			}
		}
		return returnRate;
	}

	public static void createLbls() {
		JLabel lblStats = new JLabel("Statistics");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Century", Font.PLAIN, 35));
		lblStats.setBounds(241, 0, 153, 69);
		contentPane.add(lblStats);

		JLabel dash0 = new JLabel("-");
		dash0.setHorizontalAlignment(SwingConstants.CENTER);
		dash0.setBounds(119, 221, 9, 14);
		contentPane.add(dash0);

		JLabel dash1 = new JLabel("-");
		dash1.setHorizontalAlignment(SwingConstants.CENTER);
		dash1.setBounds(174, 106, 9, 14);
		contentPane.add(dash1);

		JLabel dash2 = new JLabel("-");
		dash2.setHorizontalAlignment(SwingConstants.CENTER);
		dash2.setBounds(174, 170, 9, 14);
		contentPane.add(dash2);

		JLabel lblZoneSelection = new JLabel("Zone Selection");
		lblZoneSelection.setHorizontalAlignment(SwingConstants.CENTER);
		lblZoneSelection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZoneSelection.setBounds(10, 55, 113, 14);
		contentPane.add(lblZoneSelection);

		JLabel rollLbl = new JLabel("Roll Range");
		rollLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rollLbl.setBounds(138, 72, 113, 23);
		contentPane.add(rollLbl);

		JLabel pitchLbl = new JLabel("Pitch Range");
		pitchLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pitchLbl.setBounds(138, 139, 89, 20);
		contentPane.add(pitchLbl);

		JLabel dateLbl = new JLabel("Date Range (YYYY-MM-DD)");
		dateLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateLbl.setBounds(20, 195, 190, 23);
		contentPane.add(dateLbl);

		lblNoResults = new JLabel("No Results Found!");
		lblNoResults.setFont(new Font("Century", Font.PLAIN, 20));
		lblNoResults.setBounds(421, 130, 200, 23);
		contentPane.add(lblNoResults);
		lblNoResults.setVisible(false);
	}

	public static void createZoneBtns() {
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

	}

	public static void createTextInputs() {
		dateInput0 = new JTextField();
		dateInput0.setBounds(138, 218, 89, 20);
		contentPane.add(dateInput0);
		dateInput0.setColumns(10);

		dateInputF = new JTextField();
		dateInputF.setBounds(20, 218, 89, 20);
		contentPane.add(dateInputF);
		dateInputF.setColumns(10);

		rollInput0 = new JTextField();
		rollInput0.setBounds(138, 103, 29, 20);
		contentPane.add(rollInput0);
		rollInput0.setColumns(10);

		rollInputF = new JTextField();
		rollInputF.setBounds(193, 103, 29, 20);
		contentPane.add(rollInputF);
		rollInputF.setColumns(10);

		pitchInput0 = new JTextField();
		pitchInput0.setBounds(138, 167, 29, 20);
		contentPane.add(pitchInput0);
		pitchInput0.setColumns(10);

		pitchInputF = new JTextField();
		pitchInputF.setBounds(193, 167, 29, 20);
		contentPane.add(pitchInputF);
		pitchInputF.setColumns(10);
	}
	public static JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

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
		mntmStatistics.setBackground(SystemColor.activeCaption);
		menuBar.add(mntmStatistics);

		JMenuItem mntmTests = new JMenuItem("Testing");
		mntmTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.statsf.setVisible(false);
				View.testf.setVisible(true);
			}
		});
		menuBar.add(mntmTests);

        return menuBar;
	}
}
