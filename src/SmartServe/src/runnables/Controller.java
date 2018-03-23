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
import controllers.SQLConnector;
import controllers.ShotRecommendationController;
import enums.Mode;
import enums.RunState;
import errors.NotConnectedException;
import shootingModel.ShootingDetails;
import shootingModel.ShootingModel;
import ui.Login;

public class Controller implements Runnable {
	
	int user_id;
	
	public Controller(int uID){
		user_id = uID;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Controller c = new Controller(Login.user_id);
		Thread t = new Thread(c);
		c.m = Mode.TRAIN;
		t.start();
	}

	@Override
	public void run() {
		try {
			boot();
			begin();
			
			shoot(); // training
			//for (int i=0; i<2;i++) {
			//	demoShoot(); //fixed positions
			//}

			
			close();
		} catch (NotConnectedException | IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// global variables
//	private ShootingParameters sp;
	private Mode m;
	private RunState state;
	
	// utility classes
	ShootingModel sm;

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
			if(!pan.test("cu.usbserial-A700fk4c", 19200)) {
				return false;
			}
			System.out.println("Connected to Panning");
			
			shooter = new ArduinoController();
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
			
			if(s.rollAngle > 270) {
				s.rollAngle = 270;
			}
			
			pan.shoot(new ShotDetail(pitch, (float) sd.getYaw(), (float) s.velocity, (float) s.rollAngle));
			//harit to change velocity -> ifs and elses
			s.velocity = getVelocityTemp(s.yLoc);
				
			shooter.shoot(s.velocity, pitch);
			boolean returned = cvController.start();
			System.out.println(returned ? "Ball Returned" : "Ball Not Returned");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// TODO fill below with userID
			Object[] myReturns = new Object[]{25, s.shotId, returned ? 1 : 0, sdf.format(new Date(System.currentTimeMillis()))};
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
	
	private void demoShoot() throws MalformedURLException, NotConnectedException, InterruptedException, SQLException {
//		int[] shots = {12, 60, 252, 204, 12, 60, 252, 204};
//		
//		for(int id : shots) {
			System.out.println("Getting Next Shot...");
			Shot s = ShotRecommendationController.getRecommendation(7);
			int pitch30 = 30;
			int pitch20 = 20;
			int pitch10 = 10;
			int power12 = 12;
			int power15 = 15;
			int power17 = 17;
			int power18 = 18;
			int power14 = 14;
//			ShootingDetails sd = sm.getShootingDetails(s.xLoc, s.yLoc, pitch);
//			
//			double velocity = 13 + ( Math.round(s.yLoc - 0.171) / 0.343)*2;
			
			/*pitch,yaw,power
			 * zone 2- 
			 * zone 3-
			 * zone 4-
			 * zone 5-10,100,18
			 * zone 6-
			 * zone 7-20,95,18
			 * zone 8-
			 * zone 9-
			 * zone 10-
			 * zone 11-20,85,18
			 * zone 12-
			 * zone 13-
			 * zone 14-
			 * zone 15-
			 * zone 16-
			 * zone 17-10,78,18
			 */
			
			
			//5, 17, 7, 11
			//first is fake shot
			pan.shoot(new ShotDetail(0, 100, 0, (float) s.rollAngle));
			shooter.shoot(power18, 60 - pitch10);
			
			Thread.sleep(10000);
			
			pan.shoot(new ShotDetail(0, 100, 0, (float) s.rollAngle));
			shooter.shoot(power18, 60 - pitch10);
			
			Thread.sleep(10000);
			
			
			pan.shoot(new ShotDetail(0, 78, (float) 0, (float) s.rollAngle));
			shooter.shoot(power18, 60 - pitch10);
			
			Thread.sleep(10000);
			
			
			pan.shoot(new ShotDetail(0, 95, 0, (float) s.rollAngle));
			shooter.shoot(power14, 60 - pitch10);
			
			Thread.sleep(10000);
			
			
			pan.shoot(new ShotDetail(0, 85, (float) 0, (float) s.rollAngle));
			shooter.shoot(power14, 60 - pitch10);
			
			Thread.sleep(10000);
			

			
			
			
			
			
//		}
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
