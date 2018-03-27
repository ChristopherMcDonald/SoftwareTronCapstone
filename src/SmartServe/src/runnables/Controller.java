package runnables;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;

//import adt.ShootingParameters;
import adt.Shot;
import adt.ShotDetail;
import controllers.ArduinoController;
import controllers.CVConnector;
import controllers.PanAndRollArduinoController;
import controllers.PitchAndShootArduinoController;
import controllers.SQLConnector;
import controllers.ShotRecommendationController;
import enums.Mode;
import enums.RunState;
import errors.NotConnectedException;
import shootingModel.ShootingDetails;
import shootingModel.ShootingModel;
import ui.Login;

public class Controller implements Runnable {
	
	int userId;
	
	public Controller(int uID){
		userId = uID;
	}
	
	@Override
	public void run() {
		try {
			if(boot()) {
				begin();
				shoot();
			}
		} catch (NotConnectedException | IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// global variables
	private Mode m;
	private RunState state;
	
	// utility classes
	ShootingModel sm;

	// sockets
	ServerSocket welcomeSocket;

	// controllers
	private PanAndRollArduinoController pan;
	private PitchAndShootArduinoController shooter;

	private CVConnector cvController;
	private static final int CV_PORT = 8013;

	private static final int SQL_PORT = 3306;

	/**
	 * initiates all connectors
	 * @return successful boot
	 */
	public boolean boot() {
		try {
			pan = new PanAndRollArduinoController();
			if(!pan.test("cu.usbserial-A700fk4c", 19200)) {
				return false;
			}
			System.out.println("Connected to Panning");
			
			shooter = new PitchAndShootArduinoController();
			if(!shooter.test("cu.usbmodem14141", 9600)) {
				return false;
			}
			System.out.println("Connected to Shooter");

			cvController = new CVConnector();
			if(!cvController.connect(CV_PORT)) {
				return false;
			}
			System.out.println("Connected to CV");

			if(!SQLConnector.connect(SQL_PORT)) {
				return false;
			}
			System.out.println("Connected to SQL");
			
			ShotRecommendationController.setModel();
			System.out.println("Connected to and started SR Model");
			
			sm = new ShootingModel(0.08);
			
		} catch(Exception nce) {
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
	@SuppressWarnings("unused")
	private void shoot() throws NotConnectedException, IOException, InterruptedException, SQLException {
		while(this.state != RunState.TERMINATE) {
			System.out.println("Getting Next Shot...");
			Shot s = ShotRecommendationController.getRecommendation(m);
			Random r = new Random();
			int[] pitches = new int[] {10}; // TODO integrate pitch into possible shot list
			int pitch = pitches[r.nextInt(pitches.length)];
			ShootingDetails sd = sm.getShootingDetails(s.xLoc, s.yLoc, pitch);
			//harit to change velocity -> ifs and elses
			s.velocity = getVelocityTemp(s.yLoc);
			
			shooter.adjustSpeed(s.velocity);
			pan.shoot(sd.getYaw(), s.rollAngle);
			shooter.shoot(pitch);
			
			boolean returned = cvController.start();
			System.out.println(returned ? "Ball Returned" : "Ball Not Returned");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// TODO fill below with userID
			Object[] myReturns = new Object[]{userId, s.shotId, returned ? 1 : 0, sdf.format(new Date(System.currentTimeMillis()))};
			SQLConnector.save("returned", myReturns);
			while(this.state == RunState.PAUSED) {
				System.out.println("System is Paused...");
				Thread.sleep(10);
			}
		}
	}
	
	private double getVelocityTemp(double yLoc) {
		double velocity;
		if(yLoc <= 0.2) {
			velocity = 13;
		} else if(yLoc <= 0.55) {
			velocity = 15;
		} else if(yLoc <= 0.9) {
			velocity = 17;
		} else {
			velocity = 18;
		}
		return velocity;
	}
	
	/**
	 * Close all connections
	 */
	public void close() {
		this.pan.closeConnection();
		this.shooter.closeConnection();
	}
	
	public void begin() { this.state = RunState.RUNNING; }
	
	public void pause() { this.state = RunState.PAUSED; }
	
	public void resume() { this.state = RunState.RUNNING; }
	
	public void terminate() { this.state = RunState.TERMINATE; }

	public void setMode(Mode m) { this.m = m; }
}
