#include <FeedAndShoot.h>
//#include <Pitch.h> TODO use this guy
#include <Servo.h>

FeedAndShoot* myfeedandshoot = new FeedAndShoot();
Servo myservo;

String data;
int servoPos = 0;

void setup() {
  // make sure this matches one in Java
  Serial.begin(9600);
  myservo.attach(12);
  myservo.write(60);
  myfeedandshoot->set_dcspeed(00);
  Serial.write('A');
}

void loop() {
  if (Serial.available() > 0) {               // if data has been written to the Serial stream
    data = Serial.readString();               // strings are in form "45.0,45.0"

    int comma1 = data.indexOf(',');
    String val1 = data.substring(0, comma1);  // returns "45.0"
    double vel = val1.toDouble();             // converts ^ to Double

    data = data.substring(comma1 + 1);        // strip first comma off
    double pitch = data.toDouble();           // convert to Double

    double intensity = 40 + (vel - 10) * 10;  // maps possible velocities to 40 - 90%

    myservo.write(pitch + 30);                // corrects for zero being off on servo
    if (!(myfeedandshoot->set_dcspeed(intensity))) {
      Serial.println("Error in Setting DC Speed");
    }
    delay(1000);                              // allows speed up of motor
    myfeedandshoot->move_by_steps(550);       // 600 Steps for 1 shot or 550 tested
  }
}
