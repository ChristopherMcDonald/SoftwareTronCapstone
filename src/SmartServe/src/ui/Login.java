package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.SQLConnector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameInput;
	private JTextField passwordInput;
	JLabel lblError;
	

	public static int user_id;
	public static String username;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(187, 32, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(187, 57, 118, 14);
		contentPane.add(lblNewLabel_1);
		
		userNameInput = new JTextField();
		userNameInput.setBounds(238, 31, 137, 20);
		contentPane.add(userNameInput);
		userNameInput.setColumns(10);
		
		passwordInput = new JTextField();
		passwordInput.setBounds(269, 56, 106, 20);
		contentPane.add(passwordInput);
		passwordInput.setColumns(10);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setFont(new Font("Century", Font.PLAIN, 35));
		lblLogIn.setBounds(28, 11, 137, 69);
		contentPane.add(lblLogIn);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(286, 98, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Object[] loginObj = new Object[]{userNameInput.getText(), passwordInput.getText()};
					String[] loginTypes = new String[] {"String", "String"};
					try {
						ResultSet rs = SQLConnector.query("login_proc",loginObj, loginTypes);
						int counter = 0;
						while(rs.next()) {
							counter++;
						}
						if (counter > 0) {
							rs.previous();
							View.setUserid(rs.getInt(1));
							View.setUsername(userNameInput.getText());
							View.loginf.setVisible(false);
							View.profilef.setVisible(true);
						}
						else {
							lblError.setText("Incorrect Email or Password");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				
			}
		});
		
		contentPane.add(btnLogin);		
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(187, 98, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View.loginf.setVisible(false);
				View.welcomef.setVisible(true);
			}
		});
		contentPane.add(btnBack);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setBounds(187, 82, 188, 14);
		contentPane.add(lblError);
		
		JLabel background = new JLabel("");
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, 450, 300);
		contentPane.add(background);
		ImageIcon bg_old = new ImageIcon(Welcome.class.getResource("/ui/img/loginBg.jpg"));
		Image img_old = bg_old.getImage();
		Image img_new = img_old.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bg_new = new ImageIcon(img_new);
		background.setIcon(bg_new);

	}
}
