package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

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

		JLabel lblUserName = new JLabel();
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(10, 11, 194, 128);
		contentPane.add(lblUserName);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBackground(Color.WHITE);
		btnChangePassword.setBounds(237, 96, 139, 23);
		btnChangePassword.setMargin(new Insets(2, 2, 2, 2));
		contentPane.add(btnChangePassword);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Log Out Successful", "Log Out", JOptionPane.PLAIN_MESSAGE);
				View.profilef.setVisible(false);
				View.welcomef.setVisible(true);
			}
		});
		btnLogOut.setBounds(287, 119, 89, 23);
		contentPane.add(btnLogOut);

		lblUserName.setText("<html>Welcome<br/> Janak</html>");
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 434, 262);
		contentPane.add(background);
		ImageIcon bg_old = new ImageIcon(Welcome.class.getResource("/ui/img/profileBg.png"));
		Image img_old = bg_old.getImage();
		Image img_new = img_old.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bg_new = new ImageIcon(img_new);
		background.setIcon(bg_new);
		
		setJMenuBar(View.createMenu(this));

	}
}
