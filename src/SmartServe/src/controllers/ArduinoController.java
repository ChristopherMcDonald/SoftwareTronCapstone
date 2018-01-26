package controllers;

import java.io.IOException;
import adt.Position;
import arduino.Arduino;

public class ArduinoController {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ArduinoController ac = new ArduinoController();
		ac.test("cu.usbmodem14431");
		ac.shoot(12.3f, 12345.2f, 1.0f);
	}
	
	private Arduino arduino; // holds the port if successfully connects
	
	/**
	 * attempts to connect to Arduino over port <code>port</code>
	 * @param port - port to connect over to arduino
	 * @return successful connection
	 */
	public boolean test(String port) {
		arduino = new Arduino(port, 9600);
		if(arduino.openConnection()) {
			return true; 
		} else {
			arduino = null;
			return false;
		}
	}
	
	/**
	 * instructs Arduino to shoot at a certain pitch and yaw and angularVelocity
	 * @param pitch
	 * @param yaw
	 * @param angularVelocity
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void shoot(float pitch, float yaw, float angularVelocity) throws IOException, InterruptedException {
		String toSend = String.format("P=%.0f,Y=%.0f,Z=%.0f", pitch, yaw, angularVelocity);
		
		// DEBUGGING
		System.out.println(toSend);
		
		arduino.serialWrite(toSend); 
		arduino.closeConnection();
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
