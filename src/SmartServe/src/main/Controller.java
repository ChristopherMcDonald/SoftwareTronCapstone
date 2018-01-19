package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import adt.*;
import controllers.*;

/**
 * Main Controller of the SmartServe subsystem
 * @author christophermcdonald
 *
 */
public class Controller {
	
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
		ardController = new ArduinoController();
		if(!ardController.test(ARD_PORT)) {
			return false;
		}
		
		cvController = new CVConnector();
		if(!cvController.connect(CV_PORT)) {
			return false;
		}
		
		srController = new ShotRecommendationController();
		if(!srController.connect(SR_PORT)) {
			return false;
		}
		
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
		
		return true;
	}
	
	/**
	 * starts the training loop
	 * @param m - Mode to train in
	 */
	public void startTraining(Mode m) {
		this.m = m;
		while(true) {
			Shot s = srController.getRecommendation();
			
			// TODO trade shot for position with shooting model
			// TODO optimize shots
			
			cvController.start();
			ardController.shoot(0.0f, 0.0f, 0.0f); // TODO: fill
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

}
