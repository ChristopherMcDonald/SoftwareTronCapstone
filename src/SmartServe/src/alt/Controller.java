package alt;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
			startTraining(Mode.RANDOM);
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
	private ArduinoController ardController;

	private CVConnector cvController;
	private static final int CV_PORT = 9003;

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
		begin();
		while(this.state != RunState.TERMINATE) {
//			Shot s = ShotRecommendationController.getRecommendation(); // TODO pass in Mode
//			ShootingDetails sd = (new ShootingModel(0.08, 45)).getShootingDetails(s.x, s.y);
//			ShotDetail sd1 = new ShotDetail(45f, (float) sd.getYaw(), (float) sd.getVelocity(), 0f);
//			
//			// TODO optimize shots
//			
//			ardController.shoot(sd1); // TODO: fill
//			boolean returned = cvController.start();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			// TODO fill below with userID and shotID
//			Object[] myReturns = new Object[]{25, 1, returned ? 1 : 0, sdf.format(new Date(System.currentTimeMillis()))};
//			SQLConnector.save("returned", myReturns);
			
			while(this.state == RunState.PAUSED) {
				Thread.sleep(10);
				System.out.println("My state is: " + state);
			}
			Thread.sleep(10);
			System.out.println("My state is: " + state);
		}
	}
	
	private void begin() { this.state = RunState.RUNNING; }
	
	public void pause() { this.state = RunState.PAUSED; }
	
	public void resume() { this.state = RunState.RUNNING; }
	
	public void terminate() { this.state = RunState.TERMINATE; }
}
