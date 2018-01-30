package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import adt.*;
import controllers.*;
import errors.NotConnectedException;
import shootingModel.ShootingDetails;
import shootingModel.ShootingModel;

/**
 * Main Controller of the SmartServe subsystem
 * @author christophermcdonald
 *
 */
public class Controller {
	
	public static void main(String[] args) throws Exception {
		Controller c = new Controller();
		c.boot();
		c.startTraining(Mode.RANDOM);
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
	private static final int CV_PORT = 9003;
	private static final int CV_IN_PORT = 9004;
	
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
			if(!ardController.test("cu.usbmodem14531")) {
				return false;
			}
			
			cvController = new CVConnector();
			if(!cvController.connect(CV_PORT)) {
				return false;
			}
			
			if(!SQLConnector.connect(SQL_PORT)) {
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
	 * @throws SQLException 
	 */
	public void startTraining(Mode m) throws NotConnectedException, IOException, InterruptedException, SQLException {
		this.m = m;
		while(true) {
			Shot s = ShotRecommendationController.getRecommendation();
			ShootingDetails sd = (new ShootingModel(0.08, 45)).getShootingDetails(s.x, s.y);
			ShotDetail sd1 = new ShotDetail(45f, (float) sd.getYaw(), (float) sd.getVelocity(), 0f);
			
			// TODO optimize shots
			
			ardController.shoot(sd1); // TODO: fill
			boolean returned = cvController.start();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// TODO fill below with userID and shotID
			Object[] myReturns = new Object[]{25, 1, returned ? 1 : 0, sdf.format(new Date(System.currentTimeMillis()))};
			SQLConnector.save("returned", myReturns);
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