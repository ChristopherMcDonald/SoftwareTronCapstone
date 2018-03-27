package controllers;

import java.io.IOException;

import adt.ShotDetail;
import errors.NotConnectedException;

public class PanAndRollArduinoController extends ArduinoController {
	
	/**
	 * instructs Arduino to shoot at a certain pitch and yaw and angularVelocity
	 * @param pitch
	 * @param yaw
	 * @param angularVelocity
	 * @throws NotConnectedException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public int shoot(double yaw, double roll) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		String toSend = Double.toString(yaw) + "," + Double.toString(roll);

		// DEBUGGING
		System.out.println("Sending to Panner " + Double.toString(yaw) + "," + Double.toString(roll));
		
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

}
