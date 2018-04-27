package ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.StringUtils;

import enums.Mode;
import runnables.Controller;

public class Control extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private static Controller control;
	private static boolean paused = true;
	private Object currentMode;
	private JLabel lblZone;
	private JLabel lblError;
	private JTextField zoneInput;
	private static int zone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Control frame = new Control();
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
	public Control() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblControl = new JLabel("Control");
		lblControl.setForeground(Color.WHITE);
		lblControl.setFont(new Font("Century", Font.BOLD, 33));
		lblControl.setBounds(120, 21, 137, 69);
		contentPane.add(lblControl);

		JButton startBtn = new JButton("Start");
		startBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		startBtn.setBackground(Color.WHITE);
		startBtn.setForeground(Color.BLACK);
		startBtn.setBounds(26,87,100, 40);
		contentPane.add(startBtn);

		JButton pauseBtn = new JButton("Pause");
		pauseBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		pauseBtn.setBackground(Color.WHITE);
		pauseBtn.setBounds(136,87,100, 40);
		contentPane.add(pauseBtn);
		pauseBtn.setEnabled(false);

		JButton stopBtn = new JButton("Stop");
		stopBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		stopBtn.setBackground(Color.WHITE);
		stopBtn.setBounds(246,87,100, 40);
		contentPane.add(stopBtn);
		stopBtn.setEnabled(false);

		String[] modes = { Mode.TRAIN.toString(), Mode.SINGLEZONE.toString(), Mode.RANDOM.toString()};
	    final JComboBox<String> modeDropDown = new JComboBox<String>(modes);
	    currentMode = Mode.TRAIN.toString();

        lblZone = new JLabel("Which Zone? (2-17)");
        lblZone.setForeground(Color.WHITE);
		lblZone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblZone.setBounds(62, 169, 163, 14);
		contentPane.add(lblZone);
		lblZone.setVisible(false);

		zoneInput = new JTextField();
		zoneInput.setBounds(219, 168, 54, 20);
		contentPane.add(zoneInput);
		zoneInput.setColumns(10);
		zoneInput.setVisible(false);

	    modeDropDown.addActionListener (new ActionListener () {
	        public void actionPerformed(ActionEvent e) {
	        	currentMode = modeDropDown.getSelectedItem();
	        	if (modeDropDown.getSelectedItem().equals("SINGLEZONE")){
	        		lblZone.setVisible(true);
	        		zoneInput.setVisible(true);
	        	}
	        	else {
	        		lblZone.setVisible(false);
	        		zoneInput.setVisible(false);
	        	}
	        }
	    });

		modeDropDown.setFont(new Font("Tahoma", Font.PLAIN, 17));
		modeDropDown.setBounds(136, 138, 100, 20);
		contentPane.add(modeDropDown);

		lblError = new JLabel("Please enter valid zone!");
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblError.setBounds(72, 194, 194, 14);
		contentPane.add(lblError);
		lblError.setVisible(false);

		startBtn.addActionListener(new ActionListener() { // buttons active when start is pressed
		     public void actionPerformed(ActionEvent ae) {
		        if (currentMode.equals("SINGLEZONE") && !(isValid(zoneInput.getText()))) {
		        	lblError.setVisible(true);
		        }
		        else {
		        	if (currentMode.equals("SINGLEZONE")) {
		        		control = new Controller(View.getUserid(),zone);
		        	}
		        	else {
		        		control = new Controller(View.getUserid());
		        	}
		        	control.setMode(Mode.valueOf(modeDropDown.getSelectedItem().toString()));
					Thread t = new Thread(control);
					t.start();
					lblError.setVisible(false);
					startBtn.setEnabled(false);
			        pauseBtn.setEnabled(true);
			        stopBtn.setEnabled(true);
			        modeDropDown.setEnabled(false);
		        }
		     }
		   }
		 );

		pauseBtn.addActionListener(new ActionListener() { //buttons active when pause is pressed
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(false);
		        pauseBtn.setEnabled(true);
		        stopBtn.setEnabled(true);
		        modeDropDown.setEnabled(false);

		        if(paused) { 				//if pause then continue
		        	paused = false;
		        	pauseBtn.setText("Continue");
		        	control.pause();
		        }
		        else {						//if continue then pause
		        	paused = true;
		        	pauseBtn.setText("Pause");
		        	control.resume();
		        }
		     }
		   }
		 );

		stopBtn.addActionListener(new ActionListener() { //buttons active when stop is pressed
		     public void actionPerformed(ActionEvent ae) {
		        startBtn.setEnabled(true);
		        pauseBtn.setEnabled(false);
		        stopBtn.setEnabled(false);
		        modeDropDown.setEnabled(true);
		        control.terminate();
		     }

		   }
		 );


		JLabel background = new JLabel("");
		background.setBounds(0, 0, 434, 262);
		contentPane.add(background);
		ImageIcon bg_old = new ImageIcon(Welcome.class.getResource("/ui/img/controlBg.jpg"));
		Image img_old = bg_old.getImage();
		Image img_new = img_old.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bg_new = new ImageIcon(img_new);
		background.setIcon(bg_new);

		setJMenuBar(View.createMenu(this));



	}

	public static boolean isValid(String input) {
		boolean isNum;
		int number;

		isNum = StringUtils.isStrictlyNumeric(input);

		if(isNum) {
			number = Integer.parseInt(input);
			if (number >= 2 && number <= 17) {
				zone = number;
				return true;
			}
		}
		return false;
	}




}
