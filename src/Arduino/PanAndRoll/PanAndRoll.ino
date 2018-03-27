#include <AutomaticPanning.h>
#include <AutomaticRoll.h>

AutomaticPanning* myautopan = new AutomaticPanning();
AutomaticRoll* myautoroll = new AutomaticRoll();

String data;        //variable to store incoming data from JAVA

void setup() {
    // make sure this matches one in Java
    Serial.begin(19200);
    myautoroll->home_assembly("CCW");
    myautopan->home_assembly("CW");
}

void loop() {
    // pan values from 0 to 180
    // roll 0, 45, 135

    if(Serial.available() > 0) {                  // if data has been written to the Serial stream
        data = Serial.readString();               // strings are in form "45.0,45.0"

        int comma1 = data.indexOf(',');
        String val1 = data.substring(0, comma1);  // returns "45.0"
        double pan = val1.toDouble();             // convert ^ to Double

        data = data.substring(comma1 + 1);        // strip first value and comma
        double roll = data.toDouble();            // convert ^ to Double

        if(!(myautopan->move_to_location(pan))) { // pans first
            Serial.write('C');                      // "not done" flag
        } else {
            if(!(myautoroll->move_to_location(roll))) { // then rolls
                Serial.write('C');
            } else {
                Serial.write('B');                    // "done" flag
            }
        }
    }
}
