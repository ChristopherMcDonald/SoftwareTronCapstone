#include "Arduino.h"
#include "FeedAndShoot.h"
#include "Stepper.h"

const int stepsPerRevolution = 200;
const int FrequencyMotorMaxSpeed = 150;
const int FrequencyMotorMinSpeed = 5;
const int ShooterMotorMaxSpeed = 255;
const int ShooterMotorMinSpeed = 0;

// Declare DC Shooter Motor Speed PIN for PWM
const int speedPin = 3;

// Initialize Stepper Motor Pins
Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);

FeedAndShoot::FeedAndShoot() // Constructor
{
	//Serial.begin(9600);

	stepperspeed = 100;
	dcspeed = 0; // Speed stored as 0 - 255 PWM values

	// Initialize Shooter DC Motor Pin
  	pinMode(speedPin, OUTPUT);

  	// Print out Setup successful
  	Serial.println("\nFeeder and Shooter Program Initialized");
}


bool FeedAndShoot::set_dcspeed(int desired_speed) // Set DC Motor Speed. Note Desired Speed is from 0 - 100 limit
{
	if(desired_speed < 0 || desired_speed > 100)
	{
		return false;
	}
	else
	{
		dcspeed = map(desired_speed, 0, 100, ShooterMotorMinSpeed, ShooterMotorMaxSpeed);
  		analogWrite(speedPin, dcspeed); // Move the Motor with the desired speed setting
  		return true;
  	}
}

void FeedAndShoot::move_by_steps(int steps)
{
	myStepper.setSpeed(stepperspeed);
	myStepper.step(steps);
}

bool FeedAndShoot::stop_feed_shot()
{
	myStepper.setSpeed(0);
	for(int i = dcspeed; i > 0; i--)
	{
		set_dcspeed(i);
	}
	if(dcspeed == 0 && stepperspeed == 0) // Speeds have reached 0 so set true
	{
		return true;
	}
	else
	{
		return false;
	}
}
