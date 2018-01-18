package main;

import adt.Mode;
import adt.ShootingParameters;
import controllers.*;

public class Controller {
	
	// global variables
	private ShootingParameters sp;
	private Mode m;
	
	// controllers
	private ArduinoController ardController;
	private CVConnector cvController;
	private ShotRecommendationController srController;
	private SQLConnector sqlController;
	
	
	
	public boolean boot() {
		// TODO implement
		return false;
	}
	
	public void startTraining(Mode m) {
		this.m = m;
		while(true) {
			
		}
	}
	
	public boolean stopTraining() {
		// TODO implement
		return false;
	}
	
	public void setShootingParameters(ShootingParameters sp) {
		this.sp = sp;
	}

}
