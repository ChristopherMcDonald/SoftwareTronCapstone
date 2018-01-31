package runnables;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;

import adt.Mode;
import adt.RunState;
import adt.ShootingParameters;
import adt.Shot;
import adt.ShotDetail;
import controllers.ArduinoController;
import controllers.CVConnector;
import controllers.SQLConnector;
import controllers.ShotRecommendationController;
import errors.NotConnectedException;
import shootingModel.ShootingDetails;
import shootingModel.ShootingModel;

public class Controller implements Runnable {
	
	public static void main(String[] args) throws InterruptedException {
		
		Controller c = new Controller();
		Thread t = new Thread(c);
		t.start();
		
		c.pause();
		
		c.resume();
		
		c.terminate();
	}

	@Override
	public void run() {
		try {
			boot();
			begin();
			shoot();
			close();
		} catch (NotConnectedException | IOException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// global variables
	private ShootingParameters sp;
	private Mode m;
	private RunState state;

	// sockets
	ServerSocket welcomeSocket;

	// controllers
	private ArduinoController pan;
	private ArduinoController shooter;

	private CVConnector cvController;
	private static final int CV_PORT = 8013;

	private static final int SQL_PORT = 3306;

	/**
	 * initiates all connectors
	 * @return successful boot
	 */
	public boolean boot() {
		try {
			pan = new ArduinoController();
			if(!pan.test("cu.usbserial-A700fk4c", 9600)) {
				return false;
			}
			System.out.println("Connected to Panning");
			
			shooter = new ArduinoController();
			if(!shooter.test("cu.usbmodem14131", 19200)) {
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
	private void shoot() throws NotConnectedException, IOException, InterruptedException, SQLException {
		while(this.state != RunState.TERMINATE) {
			System.out.println("Getting Next Shot...");
			Shot s = ShotRecommendationController.getRecommendation(m);
			ShootingDetails sd = (new ShootingModel(0.08, 45)).getShootingDetails(s.x, s.y); 	// TODO get shootingModel outta here
																							// TODO ensure height is functional
			// QUICK YAW FIX
			int[] yaws = new int[]{60, 70, 80, 90, 100, 110, 120};
			Random r = new Random();
			ShotDetail sd1 = new ShotDetail(45f, yaws[r.nextInt(7)], (float) sd.getVelocity(), 0f);	// TODO variable pitch and angular direction
			
			// TODO optimize shots
			
			pan.shoot(sd1);
			shooter.shoot(100);
			boolean returned = cvController.start();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// TODO fill below with userID and shotID
			Object[] myReturns = new Object[]{25, 1, returned ? 1 : 0, sdf.format(new Date(System.currentTimeMillis()))};
			SQLConnector.save("returned", myReturns);
			Thread.sleep(1000);
			while(this.state == RunState.PAUSED) {
				System.out.println("System is Paused...");
				Thread.sleep(10);
			}
		}
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
