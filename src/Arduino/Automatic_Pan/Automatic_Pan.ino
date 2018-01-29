#define Azimuth_Motor_Direction 7 // LOW = CW , HIGH = CCW
#define Azimuth_Motor_Clock 6
#define Azimuth_Optical_Sensor 2 // HIGH = Triggered
#define Stepper_Motor_Delay 1
#define location_limit_max 178 // You dont want the system to exceed 178 deg, if exceeded the limit switch would be interrupted at 180 deg
#define location_limit_min 0

int AzimuthErrorFlag = 1; // 1 = Error, 0 = No Error
double current_location ; // Home Position -> 0 deg and measured CCW+
double target_location = 0.00;
double location_diff = 0.00;

void setup()
{
  Serial.begin(9600); // initialize the serial port

  /* Print message on the serial monitor */
  Serial.println("Automatic Panning Initialized");
  Serial.println("");

  /* Initialize all Sensors and Actuators for the Azimuth Stage */

  // Initialize Azimuth Stage Motors as OUTPUT
  pinMode(Azimuth_Motor_Direction, OUTPUT);
  pinMode(Azimuth_Motor_Clock, OUTPUT);

  // Initialize Azimuth Stage Opto Sensor as INPUT
  pinMode(Azimuth_Optical_Sensor, INPUT);

  /* Home Azimuth and Zenith Stage */
  home_azimuth("CW"); // Calls the homing function to home zenith  

  target_location = -90.0;
}

void loop()
{
  while(AzimuthErrorFlag) // Error Homing Stages then get stuck in this loop and do not go further
  {
    Serial.println("Error in homing one of the stages");
  }
//  move_azimuth("CCW",45);
//  move_azimuth("CW",15);
//  move_azimuth("CCW",95);
  Serial.print("Current Location = ");
  Serial.println(current_location);
  Serial.print("Target Location = ");
  Serial.println(target_location);

  if (target_location <= location_limit_max && target_location >= location_limit_min) // Target Location within the max and min limits, now only proceed to calculation and movement
  {
    location_diff = target_location - current_location;
    if (location_diff >= 0)
    {
      move_azimuth("CCW", location_diff);
    }
    else
    {
      location_diff = -1*location_diff;
      move_azimuth("CW", location_diff);
    }
   }
  else
  {
    Serial.println("Target Location Out of Reach");
  }
}

void home_azimuth(String motor_direction) // This function will place the Azimuth stage to the home position
{
  Serial.println("Moving Azimuth Stage to the Home Position");
  for (int i = 0; i < 720; i++) // 360 / 0.5
  {
    //Serial.print("i > ");
    //Serial.print(i);
    //Serial.println("");
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
        move_azimuth("CCW", 0.5); // Move the Azimuth Stage with a precision of 0.5 degree
      }
      else
      {
        move_azimuth("CW", 0.5); // Move the Azimuth Stage with a precision of 0.5 degree
      }
    }
  }
  Serial.println("ERROR: Aziumth Stage moved from 0 - 360 degrees CW, Home Position not reached. Try cleaning the optical sensor or perhaps go in CCW position.");
}



void move_azimuth(String motor_direction, double azimuth_move_degrees) // This function will send a signal to the stepper motor to move the azimuth stage
{
  /* Convert degrees to the # of steps */
  int azimuth_steps = (azimuth_move_degrees / 0.05);
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
