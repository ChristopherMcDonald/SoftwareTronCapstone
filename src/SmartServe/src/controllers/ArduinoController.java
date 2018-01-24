package controllers;

import java.io.IOException;
import adt.Position;

public class ArduinoController {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ArduinoController ac = new ArduinoController();
		ac.shoot(12.3f, 12345.2f, 0.11232f);
	}
	
	private int port; // holds the port if successfully connects
	
	/**
	 * attempts to connect to Arduino over port <code>port</code>
	 * @param port - port to connect over to arduino
	 * @return successful connection
	 */
	public boolean test(int port) {
		// TODO implement
		this.port = port;
		return false;
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
		Process p = Runtime.getRuntime().exec("bash ../Arduino/shoot.sh " + pitch + " " + yaw + " " + angularVelocity);
		p.waitFor();
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
