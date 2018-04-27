package ui;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View {

	public static Welcome welcomef = new Welcome();
	public static Signup signupf = new Signup();
	public static Login loginf = new Login();
	public static Profile profilef = new Profile();
	public static Control controlf = new Control();
	public static Statistics statsf = new Statistics();
	public static Tests testf = new Tests();

	public static int user_id;
	public static String username;

	public static void main(String[] args) {
		welcomef.setVisible(true);
	}
	public static void setUsername(String name) {
		username = name;
	}
	public static String getUsername() {
		return username;
	}

	public static void setUserid(int id) {
		user_id = id;
	}

	public static int getUserid() {
		return user_id;
	}
	
	public static JMenuBar createMenu(JFrame currentPage) {
		JMenuBar menuBar = new JMenuBar();
		JMenuItem mntmProfile;
		JMenuItem mntmControl;
		JMenuItem mntmStatistics;
		JMenuItem mntmTests;
		
		mntmProfile = new JMenuItem("Profile");
		menuBar.add(mntmProfile);
		
		mntmControl = new JMenuItem("Control");
		menuBar.add(mntmControl);
		
		mntmStatistics = new JMenuItem("Statistics");
		menuBar.add(mntmStatistics);
		
		if(currentPage.toString().contains("Profile")) {
			mntmProfile.setBackground(SystemColor.activeCaption);
		}
		
		else if(currentPage.toString().contains("Control")) {
			mntmControl.setBackground(SystemColor.activeCaption);
		}
		
		else if(currentPage.toString().contains("Statistics")) {
			mntmStatistics.setBackground(SystemColor.activeCaption);
		}
		
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage.setVisible(false);
				profilef.setVisible(true);
			}
		});
		
		mntmControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage.setVisible(false);
				controlf.setVisible(true);
			}
		});
	
		mntmStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage.setVisible(false);
				statsf.setVisible(true);
			}
		});

		//testing
		mntmTests = new JMenuItem("Testing");
		mntmTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statsf.setVisible(false);
				testf.setVisible(true);
			}
		});
		menuBar.add(mntmTests);

        return menuBar;
	}
}
