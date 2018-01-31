package controllers;

import java.io.IOException;
import adt.Position;
import adt.ShotDetail;
import arduino.Arduino;
import errors.NotConnectedException;

public class ArduinoController {
	
	public static void main(String[] args) throws IOException, InterruptedException, NotConnectedException {
		ArduinoController pan = new ArduinoController();
		ArduinoController shooter = new ArduinoController();
		String port2 = "cu.usbserial-A700fk4c";
		String port1 = "cu.usbmodem14131";
		System.out.println(pan.test(port2, 9600));
		System.out.println(shooter.test(port1, 19200));

		pan.shoot(new ShotDetail(0.0f, 80.0f, 0.0f, 0.0f));
		shooter.shoot(100);
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
			
			while(b[0] != 65) {
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
		String toSend = String.format("P=%.0f,Y=%.0f,V=%.0f,Z=%.0f", sd.pitch, sd.yaw, sd.velocity, sd.angular);

		// DEBUGGING
		System.out.println("Panning to " + sd.yaw);
		
		arduino.serialWrite(toSend);
		
		byte[] b = new byte[1];
		arduino.getSerialPort().readBytes(b, 1);
		
		while(b[0] != 67) {
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
	public void shoot(double speed) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
//		double intensity = (speed - 10)*10 + 50; // scales speed which is 10 - 15 to 50% - 100%
		
		String toSend = Double.toString(speed);
		
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
