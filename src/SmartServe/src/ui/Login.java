package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.SQLConnector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameInput;
	private JTextField passwordInput;
	

	static int user_id;
	
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
		lblNewLabel.setBounds(118, 93, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(118, 117, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		userNameInput = new JTextField();
		userNameInput.setBounds(226, 90, 86, 20);
		contentPane.add(userNameInput);
		userNameInput.setColumns(10);
		
		passwordInput = new JTextField();
		passwordInput.setBounds(226, 114, 86, 20);
		contentPane.add(passwordInput);
		passwordInput.setColumns(10);
		
		JLabel errorMsg = new JLabel();
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(118, 187, 194, 20);
		contentPane.add(errorMsg);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(166, 153, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Object[] loginObj = new Object[]{userNameInput.getText(), passwordInput.getText()};
					try {
						ResultSet rs = SQLConnector.query("login_proc",loginObj);
						int counter = 0;
						while(rs.next()) {
							counter++;
						}
						if (counter > 0) {
							rs.previous();
							user_id = rs.getInt(1);
							//System.out.print(user_id);
							View.loginf.setVisible(false);
							View.profilef.setVisible(true);
						}
						else {
							
							errorMsg.setText("Incorrect Email or Password");
							//System.out.println("error");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				
			}
		});
		
		contentPane.add(btnNewButton);
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Century", Font.PLAIN, 35));
		lblLogIn.setBounds(149, 4, 137, 69);
		contentPane.add(lblLogIn);
		
		

	}
}
