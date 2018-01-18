package controllers;

import adt.Position;

public class ArduinoController {
	
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
	 */
	public void shoot(float pitch, float yaw, float angularVelocity) {
		// TODO implement
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
