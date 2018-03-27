package controllers;

import errors.NotConnectedException;

public class PitchAndShootArduinoController extends ArduinoController {
	
	/**
	 * instructs Arduino to shoot a ball at a given speed, must be done after shoot(ShotDetail)
	 * @param speed in m/s
	 * @throws NotConnectedException
	 */
	public void adjustSpeed(double speed) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		
		String toSend = "V:" + Double.toString(speed);
		System.out.println("Sending Speed:" + toSend);
		arduino.serialWrite(toSend);
	}
	
	/**
	 * instructs Arduino to shoot a ball
	 * @param pitch in degrees from horizon
	 * @throws NotConnectedException
	 */
	public void shoot(double pitch) throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		
		String toSend = "P:" + Double.toString(pitch);
		System.out.println("Sending Pitch:" + toSend);
		arduino.serialWrite(toSend);
	}

}
