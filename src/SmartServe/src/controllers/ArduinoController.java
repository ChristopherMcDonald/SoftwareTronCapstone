package controllers;

import java.io.IOException;
import adt.Position;
import adt.ShotDetail;
import arduino.Arduino;
import errors.NotConnectedException;

public class ArduinoController {
	
	public static void main(String[] args) throws IOException, InterruptedException, NotConnectedException {
		ArduinoController ac = new ArduinoController();
		ac.test("cu.usbmodem14441");
		ac.shoot(new ShotDetail(41f, 12345.2f, 1.323425f, 1.0f));
	}
	
	private Arduino arduino; // holds the port if successfully connects
	private String port;
	
	/**
	 * attempts to connect to Arduino over port <code>port</code>
	 * @param port - port to connect over to arduino
	 * @return successful connection
	 */
	public boolean test(String port) {
		arduino = new Arduino(port, 9600);
		if(arduino.openConnection()) {
			this.port = port;
			return true; 
		} else {
			arduino = null;
			this.port = null;
			return false;
		}
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
	public void shoot(ShotDetail sd) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		String toSend = String.format("P=%.0f,Y=%.0f,V=%.0f,Z=%.0f", sd.pitch, sd.pitch, sd.velocity, sd.angular);
		
		// DEBUGGING
		System.out.println(toSend);
		
		arduino.serialWrite("");		// TODO understand W(hy)TF this is needed
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
