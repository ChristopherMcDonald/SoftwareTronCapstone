package controllers;

import java.io.IOException;
import adt.Position;
import adt.ShotDetail;
import arduino.Arduino;
import errors.NotConnectedException;

public class ArduinoController {
	
	public Arduino arduino; // holds the port if successfully connects
	protected String port;
	
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

}
