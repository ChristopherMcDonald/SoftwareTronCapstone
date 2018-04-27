#include <FeedAndShoot.h>
#include <Pitch.h>
#define Steps_For_1_REV 515

Pitch* mypitch;
FeedAndShoot* myfeedandshoot;
String data;

void setup() 
{
    mypitch = new Pitch();
    myfeedandshoot = new FeedAndShoot();
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
        } 
        else if (type == "S")
        {
        	myfeedandshoot->stop_feed_shot();
        	mypitch->stop_pitch();
        }
        else 
        { // type == "P"
            delay(1000);
            mypitch->move_respect_to_horizon(val);
            myfeedandshoot->move_by_steps(Steps_For_1_REV);
        }
    }
}
