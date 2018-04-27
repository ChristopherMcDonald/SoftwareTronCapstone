#include "Arduino.h"
#include "Pitch.h"
#include "Servo.h"

#define MaxPitchLimit 70.0
#define MinPitchLimit 30.0
#define HomeLocation 60.0
#define ZeroLocation 65.0

//Note: 0 means perfectly up or 90 degrees from the horizon. 180 deg means perfectly down or 90 degrees below the horizon

Servo myservo; // create servo object to control a servo

Pitch::Pitch() // Constructor
{
	target_location = 60.0; // Go home position by default
	myservo.attach(12);  // attaches the servo on pin 9 to the servo object
    current_location = myservo.read(); // gets the current position
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
			myservo.write(current_location); // move the servo to the desired location
            delay(20);
		}
		return true;
	}
	else
	{
		Serial.println("Target Location Out of Reach");
    	return false;
	}
}

bool Pitch::move_respect_to_horizon(double horizon_location)
{
	if (horizon_location >= 0) // Subtract from Horizon
	{
		return move_to_location(ZeroLocation - horizon_location);
	}
	else // Add from the horizon
	{
		return move_to_location(ZeroLocation + abs(horizon_location));
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
	return home_assembly(); // Call the home method
}
