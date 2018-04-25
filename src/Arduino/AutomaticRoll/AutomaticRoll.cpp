#include "Arduino.h"
#include "AutomaticRoll.h"

#define Roll_Motor_Direction 5 // LOW = CW , HIGH = CCW
#define Roll_Motor_Clock 4
#define Roll_Optical_Sensor 3 // HIGH = Triggered
#define Stepper_Motor_Delay 1
#define location_limit_max 270 // You dont want the system to exceed 178 deg, if exceeded the limit switch would be interrupted at 180 deg
#define location_limit_min 0

int ErrorFlag = 1; // 1 = Error, 0 = No Error

AutomaticRoll::AutomaticRoll() // Constructor
{
  // Serial.begin(9600);

  target_location = 0.0;
  location_diff = 0.0;
  current_location = 0.0;

  /* Initialize all Sensors and Actuators for the Roll */

  // Initialize Roll Motor as OUTPUT
  pinMode(Roll_Motor_Direction, OUTPUT);
  pinMode(Roll_Motor_Clock, OUTPUT);

  // Initialize Roll Opto Sensor as INPUT
  pinMode(Roll_Optical_Sensor, INPUT);

//  Serial.println("\nAutomatic Roll Initialized");
//  Serial.println("");
}

bool AutomaticRoll::home_assembly(String motor_direction)
{
  //Serial.println("Moving Roll to the Home Position");
  move_by_degrees("CW", 2.1); // jitter to fix homing problem
  for (int i = 0; i < 1200; i++) // 360 / 0.5
  {
    if (digitalRead(Roll_Optical_Sensor) == HIGH) // Sensor Triggered
    {
      //Serial.println("Roll = Home Position");
      current_location = 0.00; // Set Current Position to 0.00 as HOME
      return true;
    }
    else
    {
      if (motor_direction == "CCW")
      {
        move_by_degrees("CCW", 0.3); // Move the Roll with a precision of 0.5 degree
      }
      else
      {
        move_by_degrees("CW", 0.3); // Move the Roll with a precision of 0.5 degree
      }
    }
  }
  //Serial.println("ERROR: Roll moved from 0 - 360 degrees CW, Home Position not reached. Try cleaning the optical sensor or perhaps go in CCW position.");
  return false;
}

void AutomaticRoll::move_by_degrees(String motor_direction, double degrees_to_move)
{
  /* Convert degrees to the # of steps */
  int steps_to_take = (degrees_to_move / 0.3);
//  Serial.print("Roll Steps > ");
//  Serial.print(steps_to_take);
//  Serial.println("");

  /* Set Direction PIN on Roll */
  if (motor_direction == "CW")
  {
    digitalWrite(Roll_Motor_Direction, LOW); // LOW = Clock Wise direction
//    Serial.println("Roll Motor Direction -> Clockwise");
  }
  else if (motor_direction == "CCW")
  {
    digitalWrite(Roll_Motor_Direction, HIGH); // HIGH = Counter Clock Wise direction
//    Serial.println("Roll Motor Direction -> Counter Clockwise");
  }
  else
  {
//    Serial.println("ERROR: motor direction not specified properly...setting Roll to CW");
    motor_direction = "CW";
    digitalWrite(Roll_Motor_Direction, LOW); // LOW = Clock Wise direction
  }

  /* Send Clock Signals to the Stepper Motor to move in the direction specified */
  for (int i = 0; i < steps_to_take; i++)
  {
    digitalWrite(Roll_Motor_Clock, HIGH);
    delay(Stepper_Motor_Delay);
    digitalWrite(Roll_Motor_Clock, LOW);
    delay(Stepper_Motor_Delay);

    // Update current position
    if (motor_direction == "CW") // Subtraction from current pos if direction is CW
    {
      set_current_location(get_current_location() + 0.3); // Current Position is in degrees
    }
    else if (motor_direction == "CCW") // Addition from current pos if direction is CCW
    {
      set_current_location(get_current_location() - 0.3); // Update Current Position is in degrees
    }
  }
}

bool AutomaticRoll::move_to_location(double desired_location)
{
  target_location = desired_location;
  if (target_location <= location_limit_max && target_location >= location_limit_min) // Target Location within the max and min limits, now only proceed to calculation and movement
  {
    location_diff = target_location - current_location;
    if (location_diff >= 0)
    {
      move_by_degrees("CW", location_diff);
      return true;
    }
    else
    {
      location_diff = -1*location_diff;
      move_by_degrees("CCW", location_diff);
      return true;
    }
   }
  else
  {
//    Serial.println("Target Location Out of Reach");
    return false;
  }
}

double AutomaticRoll::get_current_location()
{
	return current_location;
}

void AutomaticRoll::set_current_location(double location)
{
	current_location = location;
}

bool AutomaticRoll::stop_roll()
{
	return home_assembly("CW");
}
