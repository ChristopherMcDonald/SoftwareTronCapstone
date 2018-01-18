package main;

import adt.Mode;
import adt.ShootingParameters;
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
	
	// controllers
	private ArduinoController ardController;
	private CVConnector cvController;
	private ShotRecommendationController srController;
	private SQLConnector sqlController;
	
	/**
	 * initiates all connectors
	 * @return successful boot
	 */
	public boolean boot() {
		// TODO implement
		return false;
	}
	
	/**
	 * starts the training loop
	 * @param m - Mode to train in
	 */
	public void startTraining(Mode m) {
		this.m = m;
		while(true) {
			
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
