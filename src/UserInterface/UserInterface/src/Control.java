import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class Control extends JFrame {

	private JPanel contentPane;

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
		
		JButton startBtn = new JButton("Start");	  
		startBtn.setBounds(50,59,100, 40);
		contentPane.add(startBtn);
		
		JButton pauseBtn = new JButton("Pause");		  
		pauseBtn.setBounds(160,59,100, 40);	
		contentPane.add(pauseBtn);
		
		JButton stopBtn = new JButton("Stop");			 
		stopBtn.setBounds(270,59,100, 40);				
		contentPane.add(stopBtn);
		
		String[] modes = { "Random", "Single", "Train"};
		JComboBox modeComboBox = new JComboBox(modes);
		modeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		modeComboBox.setBounds(160, 127, 100, 20);
		contentPane.add(modeComboBox);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		menuBar.add(mntmProfile);
		
		JMenuItem mntmControl = new JMenuItem("Control");
		menuBar.add(mntmControl);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		menuBar.add(mntmStatistics);
		
	}
}
