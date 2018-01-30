#include "Arduino.h"
#include "FeedAndShoot.h"
#include "Stepper.h"

FeedAndShoot::FeedAndShoot() // Constructor
{
	Serial.begin(9600);

	// Initialize Stepper Motor Pins
	Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);
}