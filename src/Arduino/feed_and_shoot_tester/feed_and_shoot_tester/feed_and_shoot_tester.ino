#include <FeedAndShoot.h>

FeedAndShoot* myfeedandshoot = new FeedAndShoot();

void setup() 
{
  // put your setup code here, to run once:
  Serial.begin(9600);

}

void loop() 
{
  if(!(myfeedandshoot->set_dcspeed(100)))
  {
    Serial.println("Error in Setting DC Speed");
  }

}
