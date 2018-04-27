#include "Arduino.h"
#include "AutomaticPanning.h"

#define Azimuth_Motor_Direction 7 // LOW = CW , HIGH = CCW
#define Azimuth_Motor_Clock 6
#define Azimuth_Optical_Sensor 2 // HIGH = Triggered
#define Stepper_Motor_Delay 1
#define location_limit_max 178 // You dont want the system to exceed 178 deg, if exceeded the limit switch would be interrupted at 180 deg
#define location_limit_min 0

int AzimuthErrorFlag = 1; // 1 = Error, 0 = No Error

AutomaticPanning::AutomaticPanning() // Constructor
{
  // Serial.begin(9600);

  target_location = 0.0;
  location_diff = 0.0;
  current_location = 0.0;

  /* Initialize all Sensors and Actuators for the Azimuth Stage */

  // Initialize Azimuth Stage Motors as OUTPUT
  pinMode(Azimuth_Motor_Direction, OUTPUT);
  pinMode(Azimuth_Motor_Clock, OUTPUT);

  // Initialize Azimuth Stage Opto Sensor as INPUT
  pinMode(Azimuth_Optical_Sensor, INPUT);

  Serial.println("\nAutomatic Panning Initialized");
  Serial.println("");
}

bool AutomaticPanning::home_assembly(String motor_direction)
{
  Serial.println("Moving Azimuth Stage to the Home Position");
  for (int i = 0; i < 720; i++) // 360 / 0.5
  {
    if (digitalRead(Azimuth_Optical_Sensor) == HIGH) // Sensor Triggered
    {
      Serial.println("Azimuth Stage = Home Position");
      current_location = 0.00; // Set Current Position to 0.00 as HOME
      return true;
    }
    else
    {
      if (motor_direction == "CCW")
      {
        move_by_degrees("CCW", 0.5); // Move the Azimuth Stage with a precision of 0.5 degree
      }
      else
      {
        move_by_degrees("CW", 0.5); // Move the Azimuth Stage with a precision of 0.5 degree
      }
    }
  }
  Serial.println("ERROR: Aziumth Stage moved from 0 - 360 degrees CW, Home Position not reached. Try cleaning the optical sensor or perhaps go in CCW position.");
  return false;
}

void AutomaticPanning::move_by_degrees(String motor_direction, double degrees_to_move)
{
  /* Convert degrees to the # of steps */
  int azimuth_steps = (degrees_to_move / 0.05);
  Serial.print("Azimuth Steps > ");
  Serial.print(azimuth_steps);
  Serial.println("");

  /* Set Direction PIN on Azimuth Stage */
  if (motor_direction == "CW")
  {
    digitalWrite(Azimuth_Motor_Direction, LOW); // LOW = Clock Wise direction
    Serial.println("Azimuth Motor Direction -> Clockwise");
  }
  else if (motor_direction == "CCW")
  {
    digitalWrite(Azimuth_Motor_Direction, HIGH); // HIGH = Counter Clock Wise direction
    Serial.println("Azimuth Motor Direction -> Counter Clockwise");
  }
  else
  {
    Serial.println("ERROR: motor direction not specified properly...setting Azimuth to CW");
    motor_direction = "CW";
    digitalWrite(Azimuth_Motor_Direction, LOW); // LOW = Clock Wise direction
  }

  /* Send Clock Signals to the Stepper Motor to move in the direction specified */
  for (int i = 0; i < azimuth_steps; i++)
  {
    digitalWrite(Azimuth_Motor_Clock, HIGH);
    delay(Stepper_Motor_Delay);
    digitalWrite(Azimuth_Motor_Clock, LOW);
    delay(Stepper_Motor_Delay);

    // Update current position
    if (motor_direction == "CW") // Subtraction from current pos if direction is CW
    {
      set_current_location(get_current_location() - 0.05); // Current Position is in degrees
    }
    else if (motor_direction == "CCW") // Addition from current pos if direction is CCW
    {
      set_current_location(get_current_location() + 0.05); // Update Current Position is in degrees
    }
  }
}

bool AutomaticPanning::move_to_location(double desired_location)
{
  target_location = desired_location;
  if (target_location <= location_limit_max && target_location >= location_limit_min) // Target Location within the max and min limits, now only proceed to calculation and movement
  {
    location_diff = target_location - current_location;
    if (location_diff >= 0)
    {
      move_by_degrees("CCW", location_diff);
      return true;
    }
    else
    {
      location_diff = -1*location_diff;
      move_by_degrees("CW", location_diff);
      return true;
    }
   }
  else
  {
    Serial.println("Target Location Out of Reach");
    return false;
  }
}

double AutomaticPanning::get_current_location()
{
	return current_location;
}

void AutomaticPanning::set_current_location(double location)
{
	current_location = location;
}
