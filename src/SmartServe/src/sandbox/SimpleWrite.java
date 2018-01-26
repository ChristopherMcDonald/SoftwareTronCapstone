package sandbox;

import java.util.Scanner;

import arduino.*;

public class SimpleWrite {
	
	public static void main(String[] args) {
		Scanner ob = new Scanner(System.in);
		Arduino arduino = new Arduino("cu.usbmodem14431", 9600);
		arduino.openConnection();
		System.out.println("Enter 1 to switch LED on and 0  to switch LED off");
		char input = ob.nextLine().charAt(0);
		while(input != 'n'){
			arduino.serialWrite("P=45,Y=-123,Z=1.2344354353534534432234324");
			input = ob.nextLine().charAt(0);
		}
		ob.close();
		arduino.closeConnection();
	}
	
}