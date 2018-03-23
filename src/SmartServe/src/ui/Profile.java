package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Profile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfile = new JLabel("Profile");
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setFont(new Font("Century", Font.PLAIN, 35));
		lblProfile.setBounds(144, 4, 137, 69);
		contentPane.add(lblProfile);
		
		JLabel lblUserName = new JLabel();
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(125, 84, 156, 14);
		contentPane.add(lblUserName);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(144, 109, 119, 23);
		btnChangePassword.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnChangePassword);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Log Out Successful", "Log Out", JOptionPane.PLAIN_MESSAGE);
				View.profilef.setVisible(false);
				View.welcomef.setVisible(true);
			}
		});
		btnLogOut.setBounds(154, 143, 89, 23);
		contentPane.add(btnLogOut);
		
		//todo: change name
		lblUserName.setText("Welcome " + "Sharon Platkin");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		menuBar.add(mntmProfile);
		
		JMenuItem mntmControl = new JMenuItem("Control");
		mntmControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.profilef.setVisible(false);
				View.controlf.setVisible(true);
			}
		});
		menuBar.add(mntmControl);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		mntmStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.profilef.setVisible(false);
				View.statsf.setVisible(true);
			}
		});
		menuBar.add(mntmStatistics);
		
	}
}
