// Proof Of Concept Code

#include <Stepper.h>

const int stepsPerRevolution = 200;
const int FrequencyMotorMaxSpeed = 150;
const int FrequencyMotorMinSpeed = 5;

int stepperspeed = 0;
int dcspeed = 0;

/* Initialize the sensor values */
int DC_motor_sens_value = 0;
int Stepper_motor_sens_value = 0;

// Initialize Stepper Motor Pins
Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);

void setup() 
{
  // put your setup code here, to run once:
  
  // Initialize Shooter DC Motor Pin
  //pinMode(5, OUTPUT);
  //pinMode(6, OUTPUT);
  
  // Set initial speeds for the motors
  //myStepper.setSpeed(FrequencyMotorMinSpeed); //30
  
  // Initialize the serial port
  Serial.begin(9600);

  // Print out Setup successful
  Serial.println("\nProof Of Concept Program Initialized");
  Serial.println("Setup Successful");
  Serial.print("DC Motor Speed");
  Serial.print("\t");
  Serial.println("Stepper Motor Speed");
}

void loop() 
{
  // Read Analog Inputs, Speed Control Knobs
  // A0 -> DC Motor Speed Knob. Controls the shooter speed
  // A1 -> Stepper Motor Speed Knob. Controls the frequency of the feeder
  DC_motor_sens_value = analogRead(A0);
  Stepper_motor_sens_value = analogRead(A1);
  delay(1);
  
  Serial.print(stepperspeed);
  Serial.print("\t");
  Serial.println(dcspeed);

  /* Update Stepper Motor Speed based on the Analog Value provided by the Stepper Speed Knob */
  stepperspeed = map(Stepper_motor_sens_value, 0, 1023, FrequencyMotorMinSpeed, FrequencyMotorMaxSpeed);
  myStepper.setSpeed(stepperspeed);
  myStepper.step(stepsPerRevolution/100);

}
