#include <FeedAndShoot.h>

FeedAndShoot* myfeedandshoot = new FeedAndShoot();

String data;

void setup() 
{
  // put your setup code here, to run once:
  Serial.begin(19200);
  myfeedandshoot->set_dcspeed(0);
}

void loop() 
{
  if(Serial.available() > 0) {
    data = Serial.readString();
    double num = data.toDouble();

    if(!(myfeedandshoot->set_dcspeed(100)))
    {
      Serial.println("Error in Setting DC Speed");
    }
    myfeedandshoot->move_by_steps(550); // 600 Steps for 1 shot or 550 tested
  }
}
