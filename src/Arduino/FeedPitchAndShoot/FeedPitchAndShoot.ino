#include <FeedAndShoot.h>
#include <Servo.h>

FeedAndShoot* myfeedandshoot = new FeedAndShoot();
Servo myservo;

String data;
int servoPos = 0;

void setup() 
{
  // put your setup code here, to run once:
  Serial.begin(19200);
  myservo.attach(12);
  myservo.write(0);
  myfeedandshoot->set_dcspeed(0);
  Serial.write('A');
}

void loop() 
{

  myfeedandshoot->set_dcspeed(80);
  
  for (; servoPos <= 30; servoPos += 1) { // goes from 0 degrees to 180 degrees
    myservo.write(servoPos);              // tell servo to go to position in variable 'pos'
    delay(15);                       // waits 15ms for the servo to reach the position
  }
  myfeedandshoot->move_by_steps(550);

  for (; servoPos >= 0; servoPos -= 1) { // goes from 0 degrees to 180 degrees
    myservo.write(servoPos);              // tell servo to go to position in variable 'pos'
    delay(15);                       // waits 15ms for the servo to reach the position
  }

  

  


  
//  if(Serial.available() > 0) {
//    data = Serial.readString();
//
//    int comma1 = data.indexOf(',');           // ex. 4
//    String val1 = data.substring(0,comma1);   // ex. P=45
//    double vel = val1.toDouble();
//
//    data = data.substring(comma1 + 1);        // Z=1
//    double pitch = data.toDouble();
//
//    double intensity = 50 + (vel - 10)*10;
//
//    if(pitch > servoPos) {
//      for (; servoPos >= pitch; servoPos -= 1) { // goes from 0 degrees to 180 degrees
//        myservo.write(servoPos);              // tell servo to go to position in variable 'pos'
//        delay(15);                       // waits 15ms for the servo to reach the position
//      }
//    } else {
//      for (; servoPos <= pitch; servoPos += 1) { // goes from 0 degrees to 180 degrees
//        myservo.write(servoPos);              // tell servo to go to position in variable 'pos'
//        delay(15);                       // waits 15ms for the servo to reach the position
//      }
//    }
//    
//    if(!(myfeedandshoot->set_dcspeed(intensity)))
//    {
//      Serial.println("Error in Setting DC Speed");
//    }
//    delay(2000);
//    myfeedandshoot->move_by_steps(550); // 600 Steps for 1 shot or 550 tested
//  }
}
