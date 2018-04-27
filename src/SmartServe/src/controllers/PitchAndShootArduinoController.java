package controllers;

import errors.NotConnectedException;

public class PitchAndShootArduinoController extends ArduinoController {
	
	public static void main(String[] args) throws NotConnectedException, InterruptedException {
		PitchAndShootArduinoController shooter = new PitchAndShootArduinoController();
		if(!shooter.test("cu.usbmodem14431", 9600)) {
			System.out.println("NOT Connected to Shooter");
		} else {
			System.out.println("Connected to Shooter");
		}
		
		shooter.adjustSpeed(0.0f);
	}
	
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
		System.out.print("Speed: " + speed + ", ");
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
		System.out.println("Pitch:" + pitch);
		arduino.serialWrite(toSend);
	}
	
	/**
	 * instructs Arduino to shoot a ball at a given speed, must be done after shoot(ShotDetail)
	 * @param speed in m/s
	 * @throws NotConnectedException
	 */
	public void stop() throws NotConnectedException {
		if(arduino == null) {
			throw new NotConnectedException("Arduino", port);
		}
		
		String toSend = "S:0.0";
		System.out.println("Closing:" + toSend);
		arduino.serialWrite(toSend);
	}

}
