package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.SQLConnector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField nameInput;
	private JTextField emailInput;
	private JTextField passwordInput1;
	private JTextField passwordInput2;
	private JButton btnSignUp;
	private JLabel lblSignUp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(121, 84, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(121, 109, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(121, 134, 46, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(121, 159, 94, 14);
		contentPane.add(lblConfirmPassword);
		
		nameInput = new JTextField();
		nameInput.setBounds(226, 81, 86, 20);
		contentPane.add(nameInput);
		nameInput.setColumns(10);
		
		emailInput = new JTextField();
		emailInput.setBounds(226, 106, 86, 20);
		contentPane.add(emailInput);
		emailInput.setColumns(10);
		
		passwordInput1 = new JTextField();
		passwordInput1.setBounds(226, 131, 86, 20);
		contentPane.add(passwordInput1);
		passwordInput1.setColumns(10);
		
		passwordInput2 = new JTextField();
		passwordInput2.setBounds(226, 156, 86, 20);
		contentPane.add(passwordInput2);
		passwordInput2.setColumns(10);
		
		JLabel errorMsg = new JLabel("");
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(121, 220, 191, 14);
		contentPane.add(errorMsg);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] signUpObj = new Object[]{emailInput.getText(), passwordInput1.getText()};
				try {
					//TODO: fix to check only username, not both
					ResultSet rs = SQLConnector.query("login_proc",signUpObj);
					int counter = 0;
					while(rs.next()) {
						counter++;
					}
					if (counter > 1) {
						errorMsg.setText("User exists, please create a new user");
					}
					else if(!passwordInput1.getText().equals(passwordInput2.getText())){
						errorMsg.setText("Passwords do not match"); 
					}
					else {
						SQLConnector.save("signup_proc",signUpObj);
						JOptionPane.showMessageDialog(null, "Sign Up Successful", "Confirmation", JOptionPane.PLAIN_MESSAGE);
						View.signupf.setVisible(false);
						View.welcomef.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSignUp.setBounds(166, 184, 89, 23);
		contentPane.add(btnSignUp);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Century", Font.PLAIN, 35));
		lblSignUp.setBounds(149, 4, 137, 69);
		contentPane.add(lblSignUp);
		
	}
}
