package controllers;

import java.io.IOException;
import adt.Position;
import adt.ShotDetail;
import arduino.Arduino;
import errors.NotConnectedException;

public class ArduinoController {
	
	public static void main(String[] args) throws IOException, InterruptedException, NotConnectedException {
		ArduinoController pan = new ArduinoController();
		String port2 = "cu.usbserial-A700fk4c";
		System.out.println(pan.test(port2, 19200));

		
		ArduinoController shooter = new ArduinoController();
		String port1 = "cu.usbmodem14641";
		System.out.println(shooter.test(port1, 9600));

		pan.shoot(new ShotDetail(0.0f, 45.0f, 0.0f, 30.0f));
		shooter.shoot(15, 30); // 20 -> 45, 65 -> 0
	}
	
	private Arduino arduino; // holds the port if successfully connects
	private String port;
	
	/**
	 * attempts to connect to Arduino over port <code>port</code>
	 * @param port - port to connect over to arduino
	 * @return successful connection
	 */
	public boolean test(String port, int baud) {
		arduino = new Arduino(port, baud);
		if(arduino.openConnection()) {
			byte[] b = new byte[1];
			arduino.getSerialPort().readBytes(b, 1);
			
			while(b[0] != 'A') {
				b = new byte[1];
				arduino.getSerialPort().readBytes(b, 1);
			}
			
			System.out.println("Connected to Arduino: " + port);
			this.port = port;
			return true; 
		} else {
			arduino = null;
			this.port = null;
			return false;
		}
	}
	
	/**
	 * closes the connection to the Arduino
	 */
	public void closeConnection() {
		this.arduino.closeConnection();
	}
	
	/**
	 * instructs Arduino to shoot at a certain pitch and yaw and angularVelocity
	 * @param pitch
	 * @param yaw
	 * @param angularVelocity
	 * @throws NotConnectedException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public int shoot(ShotDetail sd) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		String toSend = Double.toString(sd.yaw) + "," + Double.toString(sd.angular);

		// DEBUGGING
		System.out.println("Sending to Panner " + Double.toString(sd.yaw) + "," + Double.toString(sd.angular));
		
		arduino.serialWrite(toSend);
		
		byte[] b = new byte[1];
		arduino.getSerialPort().readBytes(b, 1);
		
		while(b[0] != 'B') {
			b = new byte[1];
			arduino.getSerialPort().readBytes(b, 1);
		}
		
		System.out.println("Panning Completed");
		
		return 0;
	}
	
	/**
	 * instructs Arduino to shoot a ball at a given speed, must be done after shoot(ShotDetail)
	 * @param speed in m/s
	 * @throws NotConnectedException
	 */
	public void shoot(double speed, double pitch) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		
		String toSend = Double.toString(speed) + "," + Double.toString(pitch);
		System.out.println("Sending Speed and Pitch:" + toSend);
		arduino.serialWrite(toSend);
	}
	
	/**
	 * reads the position of the shooting mechanism
	 * @return the position of the mechanism
	 */
	public Position getPosition() {
		// TODO implement
		return null;
	}

}
