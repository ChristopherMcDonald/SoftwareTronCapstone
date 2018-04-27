package runnables;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
	int[] shotIds;
    int singleZone;

	public Controller(int uID){
		userId = uID;
		singleZone = -1;
	}

	public Controller(int uID, int zone){
		userId = uID;
		singleZone = zone;
		//TODO: map this to the shot if mode is single shot
	}

	@Override
	public void run() {
		try {
			if(boot()) {
				begin();
				if(shotIds != null) {
					shooter.adjustSpeed(15);
					for(int id : shotIds)
						shoot(id);
					shooter.stop();
				} else if(singleZone != -1) {
					shoot(getXLoc(singleZone), getYLoc(singleZone));
				} else {
					shoot();
				}
			}
			
			close();
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
	private static final String PAN_PORT = "cu.usbserial-A700fk4c";
	private static final String SHOOT_PORT = "cu.usbmodem14441";
	private static final int PAN_SERIAL = 19200;
	private static final int SHOOT_SERIAL = 9600;


	/**
	 * initiates all connectors
	 * @return successful boot
	 */
	public boolean boot() {
		try {
			pan = new PanAndRollArduinoController();
			if(!pan.test(PAN_PORT, PAN_SERIAL)) {
				return false;
			}
			System.out.println("Connected to Panning");

			shooter = new PitchAndShootArduinoController();
			if(!shooter.test(SHOOT_PORT, SHOOT_SERIAL)) {
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
	 * @throws NotConnectedException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private void shoot() throws NotConnectedException, IOException, InterruptedException, SQLException {
		shooter.adjustSpeed(15);	// on boot, speed it up
		Thread.sleep(2000);			// give it time fam!
		while(this.state != RunState.TERMINATE) {
			Shot s = ShotRecommendationController.getRecommendation(m);
			System.out.println();
			System.out.print("Zone: " + getZone(s.xLoc, s.yLoc) + ", ");
			ShootingDetails sd = sm.getShootingDetails(s.xLoc, s.yLoc, s.pitch);
			//harit to change velocity -> ifs and elses
			double vel = getVelocityTemp(s.yLoc, s.pitch);

			shooter.adjustSpeed(vel);
			pan.shoot(sd.getYaw(), s.rollAngle);
			shooter.shoot(s.pitch);

			boolean returned = cvController.start();
			System.out.println(returned ? "Ball Returned" : "Ball Not Returned");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Object[] myReturns = new Object[]{userId, s.shotId, returned ? 1 : 0, sdf.format(new Date(System.currentTimeMillis()))};
			String[] returnTypes = new String[] {"Integer", "Integer", "Integer", "String"};
			SQLConnector.save("returned", myReturns, returnTypes);
			if(this.state == RunState.PAUSED) {
				shooter.adjustSpeed(0.0f);
				while(this.state == RunState.PAUSED) {
					System.out.println("System is Paused...");
					Thread.sleep(10);
				}
			}
		}

		// if connected, init shutdown prod
		if(shooter.arduino != null) {
			shooter.stop();
		}
	}

	/**
	 * starts the training loop
	 * @param id - the shot to shoot
	 * @throws NotConnectedException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private void shoot(int id) throws NotConnectedException, IOException, InterruptedException, SQLException {
		Shot s = ShotRecommendationController.getRecommendation(id);
		System.out.println();
		System.out.print("Zone: " + getZone(s.xLoc, s.yLoc) + ", ");
		ShootingDetails sd = sm.getShootingDetails(s.xLoc, s.yLoc, s.pitch);
		//harit to change velocity -> ifs and elses
		double vel = getVelocityTemp(s.yLoc, s.pitch);

		shooter.adjustSpeed(vel);
		pan.shoot(sd.getYaw(), s.rollAngle);
		shooter.shoot(s.pitch);

		boolean returned = cvController.start();
		System.out.println(returned ? "Ball Returned" : "Ball Not Returned");
	}
	
	/**
	 * shoots at one place, at pitch 10
	 * @param id - the shot to shoot
	 * @throws NotConnectedException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private void shoot(double xloc, double yloc) throws NotConnectedException, IOException, InterruptedException, SQLException {
		shooter.adjustSpeed(15);	// on boot, speed it up
		Thread.sleep(2000);			// give it time fam!
		ShootingDetails sd = sm.getShootingDetails(xloc, yloc, 20.0f);
		while(this.state != RunState.TERMINATE) {
			System.out.println();
			System.out.print("Zone: " + getZone(xloc, yloc) + ", ");
			double vel = getVelocityTemp(yloc, 20.0f);
	
			shooter.adjustSpeed(vel);
			pan.shoot(sd.getYaw(), 0);
			shooter.shoot(10.0f);
	
			boolean returned = cvController.start();
			System.out.println(returned ? "Ball Returned" : "Ball Not Returned");
			if(this.state == RunState.PAUSED) {
				shooter.adjustSpeed(0.0f);
				while(this.state == RunState.PAUSED) {
					System.out.println("System is Paused...");
					Thread.sleep(10);
				}
			}
		}
		
		// if connected, init shutdown prod
		if(shooter.arduino != null) {
			shooter.stop();
		}
	}

	private double getVelocityTemp(double yLoc, double pitch) {
		if(pitch <= 10.1) {
			if(yLoc <= 0.2) {
				return 10;
			} else if(yLoc <= 0.55) {
				 return 18;
			} else if(yLoc <= 0.9) {
				return 21;
			} else {
				return 23;
			}
		} else if(pitch <= 20.1) {
			if(yLoc <= 0.2) {
				return 11;
			} else if(yLoc <= 0.55) {
				 return 16;
			} else if(yLoc <= 0.9) {
				return 18;
			} else {
				return 20;
			}
		} else { // pitch is 30
			if(yLoc <= 0.2) {
				return 7;
			} else if(yLoc <= 0.55) {
				 return 14;
			} else if(yLoc <= 0.9) {
				return 15;
			} else {
				return 16;
			}
		}
	}
	
	private static int getZone(double xloc, double yloc) {
		int id;
		if(yloc <= 0.2) {
			id = 2;
		} else if(yloc <= 0.55) {
			id = 3;
		} else if(yloc <= 0.9) {
			id = 4;
		} else {
			id = 5;
		}
		
		if(xloc <= 0.2) {
			id += 0;
		} else if(xloc <= 0.58) {
			id += 4;
		} else if(xloc <= 1.0) {
			id += 8;
		} else {
			id += 12;
		}
		
		return id;
	}
	
	private static double getYLoc(int id) {
		switch(id % 4) {
			case 0: return 0.856;
			case 1: return 1.199;
			case 2: return 0.171;
			default: return 0.514;
		}
	}
	
	private static double getXLoc(int id) {
		if(id <= 5)
			return 0.191;
		else if(id <= 9)
			return 0.572;
		else if(id <= 13)
			return 0.953;
		else
			return 1.334;
			
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

	public void setShots(int... id) { this.shotIds = id; }
}
