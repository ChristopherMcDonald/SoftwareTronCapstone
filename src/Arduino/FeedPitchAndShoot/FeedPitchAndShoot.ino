#include <FeedAndShoot.h>
#include <Servo.h>
#include <Pitch.h>

Pitch* mypitch;
FeedAndShoot* myfeedandshoot = new FeedAndShoot();
String data;

void setup() {
    mypitch = new Pitch();
    mypitch->home_assembly();
    // make sure this matches one in Java
    Serial.begin(9600);
    myfeedandshoot->set_dcspeed(0);
    Serial.write('A');
}

void loop() {
    if (Serial.available() > 0) {                      // if data has been written to the Serial stream
        data = Serial.readString();                     // strings are in form "P:45.0"

        int sep = data.indexOf(':');
        String type = data.substring(0, sep);           // returns "45.0"
        double val = data.substring(sep + 1).toDouble();// converts ^ to Double

        if(type == "V") {
            myfeedandshoot->set_dcspeed(val);
        } else { // type == "P"
            mypitch->move_to_location(val);
            myfeedandshoot->move_by_steps(515);
        }
    }
}
