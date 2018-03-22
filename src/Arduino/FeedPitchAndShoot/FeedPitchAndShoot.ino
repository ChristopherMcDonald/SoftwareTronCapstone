#include <FeedAndShoot.h>
#include <Servo.h>
#include <Pitch.h>
#include <AutomaticRoll.h>


AutomaticRoll* myautoroll;
Pitch* mypitch;
FeedAndShoot* myfeedandshoot = new FeedAndShoot();

String data;

void setup() {
  myautoroll = new AutomaticRoll();
  myautoroll->home_assembly("CW");
  mypitch = new Pitch();
  mypitch->home_assembly();
  // make sure this matches one in Java
  Serial.begin(9600);
  myfeedandshoot->set_dcspeed(0);
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

    if (!(myfeedandshoot->set_dcspeed(vel))) {
      Serial.println("Error in Setting DC Speed");
    }
    delay(5000);                              // allows speed up of motor
    mypitch->move_to_location(pitch);         // corrects for zero being off on servo, 60 is home
    myfeedandshoot->move_by_steps(550);       // 600 Steps for 1 shot or 550 tested
  }
  
  
}
