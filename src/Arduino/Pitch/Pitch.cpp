#include "Arduino.h"
#include "Pitch.h"
#include "Servo.h"

#define MaxPitchLimit 70.0
#define MinPitchLimit 30.0
#define HomeLocation 60.0

Servo myservo; // create servo object to control a servo

Pitch::Pitch() // Constructor
{
	current_location = 0.0;
	target_location = 90.0; // Go home position by default
	myservo.attach(12);  // attaches the servo on pin 9 to the servo object
}

bool Pitch::move_to_location(double desired_location)
{
	if (MinPitchLimit <= desired_location && desired_location <= MaxPitchLimit)
	{
		while(current_location != desired_location)
		{
			if(current_location > desired_location)
			{
				current_location--;
			}
			else
			{
				current_location++;
			}
			myservo.write(desired_location); // move the servo to the desired location
			set_current_location(desired_location);
		}
		return true;
	}	
	else
	{
		Serial.println("Target Location Out of Reach");
    	return false;
	}
}

bool Pitch::home_assembly()
{
	if(get_current_location() != HomeLocation)
	{
		return move_to_location(HomeLocation);
	}
	else if (get_current_location() == HomeLocation)
	{
		Serial.println("Pitch System is already home");
		return true;
	}
	else
	{
		Serial.println("Unable to Home the Servo Motor");
		return false;
	}	
}

double Pitch::get_current_location()
{
	return current_location;
}

void Pitch::set_current_location(double location)
{
	current_location = location;
}

bool Pitch::stop_pitch() // Exit sequence, home the assembly upon power OFF
{
	home_assembly(); // Call the home method
}