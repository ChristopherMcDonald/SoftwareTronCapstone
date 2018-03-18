#include <AutomaticPanning.h>
#include <AutomaticRoll.h>

AutomaticPanning* myautopan = new AutomaticPanning();
AutomaticRoll* myautoroll = new AutomaticRoll();

String data;        //variable to store incoming data from JAVA

void setup() {
  Serial.begin(9600);
  myautoroll->home_assembly("CW");
  myautopan->home_assembly("CW");
  delay(1000);
}

void loop() {

    // pan values from 0 to 180
    // roll 0 to 270
  
  if(Serial.available() > 0)
  { //if data has been written to the Serial stream
      data = Serial.readString();                 // ex. P=45,Y=-123,V=1,W=24
      int comma1 = data.indexOf(',');           // ex. 4
      String val1 = data.substring(0,comma1);   // ex. P=45
  
      data = data.substring(comma1 + 1);        // Y=-123,Z=1
      int comma2 = data.indexOf(',');           // 6
      String val2 = data.substring(0,comma2);   // Y=-123
      
      data = data.substring(comma2 + 1);        // Y=-123,Z=1
      int comma3 = data.indexOf(',');           // 6
      String val3 = data.substring(0,comma3);   // Y=-123
  
      data = data.substring(comma3 + 1);        // Z=1
  
      double pitch = val1.substring(2).toDouble();
      double pan = val2.substring(2).toDouble();
      double vel = val3.substring(2).toDouble();
      double ang = data.substring(2).toDouble();
     
      if(myautopan->move_to_location(pan)) {
        if(myautoroll->move_to_location(ang/3)) {
          Serial.write('C');
          } else {
            Serial.write('B');
          }
        } else {
        Serial.write('B');
      }
}
