#include "Arduino.h"
#include "AutomaticPanning.h"

#define Azimuth_Motor_Direction 7 // LOW = CW , HIGH = CCW
#define Azimuth_Motor_Clock 6
#define Azimuth_Optical_Sensor 2 // HIGH = Triggered
#define Stepper_Motor_Delay 1

int AzimuthErrorFlag = 1; // 1 = Error, 0 = No Error
double current_location ; // Home Position -> 0 deg and measured CCW+
double target_location = 0.00;
double location_diff = 0.00;

AutomaticPanning::AutomaticPanning()
{
  Serial.begin(9600); 

  /* Initialize all Sensors and Actuators for the Azimuth Stage */

  // Initialize Azimuth Stage Motors as OUTPUT
  pinMode(Azimuth_Motor_Direction, OUTPUT);
  pinMode(Azimuth_Motor_Clock, OUTPUT);

  // Initialize Azimuth Stage Opto Sensor as INPUT
  pinMode(Azimuth_Optical_Sensor, INPUT);

  Serial.println("\nAutomatic Panning Initialized");
  Serial.println("");
}

void AutomaticPanning::home_azimuth(String motor_direction)
{
  Serial.println("Moving Azimuth Stage to the Home Position");
  for (int i = 0; i < 720; i++) // 360 / 0.5
  {
    if (digitalRead(Azimuth_Optical_Sensor) == HIGH) // Sensor Triggered
    {
      Serial.println("Azimuth Stage = Home Position");
      current_location = 0.00; // Set Current Position to 0.00 as HOME 
      AzimuthErrorFlag = 0;
      return;
    }
    else
    {
      if (motor_direction == "CCW")
      {
        move_to_location("CCW", 0.5); // Move the Azimuth Stage with a precision of 0.5 degree
      }
      else
      {
        move_to_location("CW", 0.5); // Move the Azimuth Stage with a precision of 0.5 degree
      }
    }
  }
  Serial.println("ERROR: Aziumth Stage moved from 0 - 360 degrees CW, Home Position not reached. Try cleaning the optical sensor or perhaps go in CCW position.");
}

void AutomaticPanning::move_to_location(String motor_direction, double degrees_to_move)
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
      current_location = current_location - 0.05; // Current Position is in degrees
    }
    else if (motor_direction == "CCW") // Addition from current pos if direction is CCW
    {
      current_location = current_location + 0.05; // Current Position is in degrees
    }
  }
}