package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import adt.*;
import controllers.*;
import errors.NotConnectedException;

/**
 * Main Controller of the SmartServe subsystem
 * @author christophermcdonald
 *
 */
public class Controller {
	
	public static void main(String[] args) {
		Object b = "1234553";
		System.out.println((String) b);
	}
	
	// global variables
	private ShootingParameters sp;
	private Mode m;
	
	// sockets
	ServerSocket welcomeSocket;
	
	// controllers
	private ArduinoController ardController;
	private static final int ARD_PORT = 8000;
	
	private CVConnector cvController;
	private static final int CV_PORT = 8010;
	private static final int CV_IN_PORT = 8011;
	
	private ShotRecommendationController srController;
	private static final int SR_PORT = 8020;
	
	private SQLConnector sqlController;
	private static final int SQL_PORT = 3306;
	
	/**
	 * initiates all connectors
	 * @return successful boot
	 */
	public boolean boot() {
		try {
			ardController = new ArduinoController();
			if(!ardController.test("cu.usbmodem14431")) {
				return false;
			}
			
			cvController = new CVConnector();
			if(!cvController.connect(CV_PORT)) {
				return false;
			}
			
			srController = new ShotRecommendationController();
			/*if(!srController.connect(SR_PORT)) {
				return false;
			}*/
			
			sqlController = new SQLConnector();
			if(!sqlController.connect(SQL_PORT)) {
				return false;
			}
			
			try {
				welcomeSocket = new ServerSocket(CV_IN_PORT);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch(NotConnectedException nce) {
			nce.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * starts the training loop
	 * @param m - Mode to train in
	 * @throws NotConnectedException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void startTraining(Mode m) throws NotConnectedException, IOException, InterruptedException {
		this.m = m;
		while(true) {
			Shot s = srController.getRecommendation();
			
			// TODO trade shot for position with shooting model
			// TODO optimize shots
			
			ardController.shoot(0.0f, 0.0f, 0.0f); // TODO: fill
			boolean returned = cvController.start();
			Map<String, String> values = new HashMap<String, String>();
			values.put("shot", s.toString());
			values.put("returned", returned ? "true" : "false");
//			sqlController.save("returnedShot", values);
		}
	}
	
	/**
	 * stop training loop
	 * @return successful stop
	 */
	public boolean stopTraining() {
		// TODO implement
		return false;
	}
	
	/**
	 * sets the shooting parameters
	 * @param sp - ADT to hold length and width
	 */
	public void setShootingParameters(ShootingParameters sp) {
		this.sp = sp;
	}
	
	public static void main(String[] args) throws NotConnectedException, IOException, InterruptedException{
		ShotRecommendationController recommender = new ShotRecommendationController();
		Shot nextShot = recommender.getRecommendation();
		System.out.println(nextShot.toString());
		
		/*
		Controller controller = new Controller();
		controller.boot();
		controller.startTraining(Mode.TRAIN);
		*/
	}

}