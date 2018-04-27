package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Welcome extends JFrame {

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
					Welcome frame = new Welcome();
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
	public Welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.setBounds(42, 120, 89, 23);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.welcomef.setVisible(false);
				View.signupf.setVisible(true);
			}
		});
		contentPane.add(btnSignUp);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(42, 154, 89, 23);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.welcomef.setVisible(false);
				View.loginf.setVisible(true);
			}
		});
		contentPane.add(btnLogIn);
		
		JLabel lblSmartServe = new JLabel("Smart Serve");
		lblSmartServe.setForeground(Color.WHITE);
		lblSmartServe.setFont(new Font("Andalus", Font.PLAIN, 53));
		lblSmartServe.setBounds(0, 15, 352, 50);
		contentPane.add(lblSmartServe);
		
		
		JLabel lblWelcome = new JLabel("Welcome to");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Andalus", Font.PLAIN, 22));
		lblWelcome.setBounds(15, 0, 130, 23);
		contentPane.add(lblWelcome);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 450, 300);
		contentPane.add(background);
		ImageIcon bg_old = new ImageIcon(Welcome.class.getResource("/ui/img/welcomeBg.jpg"));
		Image img_old = bg_old.getImage();
		Image img_new = img_old.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bg_new = new ImageIcon(img_new);
		background.setIcon(bg_new);
		
		
		
	}
}
