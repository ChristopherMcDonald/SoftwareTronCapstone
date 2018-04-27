package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.SQLConnector;

import javax.swing.JLabel;
import javax.swing.JTextField;
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

public class Signup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(92, 106, 81, 14);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(92, 128, 81, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(92, 150, 81, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setBounds(92, 172, 129, 14);
		contentPane.add(lblConfirmPassword);
		
		nameInput = new JTextField();
		nameInput.setBounds(212, 104, 115, 20);
		contentPane.add(nameInput);
		nameInput.setColumns(10);
		
		emailInput = new JTextField();
		emailInput.setBounds(212, 126, 115, 20);
		contentPane.add(emailInput);
		emailInput.setColumns(10);
		
		passwordInput1 = new JTextField();
		passwordInput1.setBounds(212, 148, 115, 20);
		contentPane.add(passwordInput1);
		passwordInput1.setColumns(10);
		
		passwordInput2 = new JTextField();
		passwordInput2.setBounds(212, 170, 115, 20);
		contentPane.add(passwordInput2);
		passwordInput2.setColumns(10);
		
		JLabel errorMsg = new JLabel("");
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(114, 197, 191, 14);
		contentPane.add(errorMsg);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] signUpObj = new Object[]{emailInput.getText(), passwordInput1.getText()};
				String[] signTypes = new String[] {"String", "String"};
				try {
					//Note: only if both email and password matches
					ResultSet rs = SQLConnector.query("login_proc",signUpObj, signTypes);
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
						SQLConnector.save("signup_proc",signUpObj, signTypes);
						JOptionPane.showMessageDialog(null, "Sign Up Successful", "Confirmation", JOptionPane.PLAIN_MESSAGE);
						View.signupf.setVisible(false);
						View.welcomef.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSignUp.setBounds(223, 222, 89, 23);
		contentPane.add(btnSignUp);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setFont(new Font("Century", Font.BOLD, 38));
		lblSignUp.setBounds(47, 11, 173, 98);
		contentPane.add(lblSignUp);
		
		JButton btnBack = new JButton("Cancel");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View.signupf.setVisible(false);
				View.welcomef.setVisible(true);
			}
		});
		btnBack.setBounds(107, 222, 89, 23);
		contentPane.add(btnBack);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 450, 300);
		contentPane.add(background);
		ImageIcon bg_old = new ImageIcon(Welcome.class.getResource("/ui/img/signupBg.jpg"));
		Image img_old = bg_old.getImage();
		Image img_new = img_old.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bg_new = new ImageIcon(img_new);
		background.setIcon(bg_new);
		
	}
}
