import java.awt.BorderLayout;
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
import java.awt.Choice;
import javax.swing.JButton;

public class Profile extends JFrame {

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
		lblProfile.setFont(new Font("Century", Font.PLAIN, 35));
		lblProfile.setBounds(149, 4, 137, 69);
		contentPane.add(lblProfile);
		
		JLabel lblUserNameSharon = new JLabel("User Name: Sharon Platkin");
		lblUserNameSharon.setBounds(144, 84, 142, 14);
		contentPane.add(lblUserNameSharon);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(149, 110, 119, 23);
		contentPane.add(btnChangePassword);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Log Out Successful", "Log Out", JOptionPane.PLAIN_MESSAGE);
				View.profilef.setVisible(false);
				View.welcomef.setVisible(true);
			}
		});
		btnLogOut.setBounds(159, 144, 89, 23);
		contentPane.add(btnLogOut);
		
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
