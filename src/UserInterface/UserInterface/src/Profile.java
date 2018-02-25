import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
