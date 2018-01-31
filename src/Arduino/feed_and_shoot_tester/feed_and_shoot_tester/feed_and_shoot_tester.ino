#include <FeedAndShoot.h>

FeedAndShoot* myfeedandshoot = new FeedAndShoot();

void setup() 
{
  // put your setup code here, to run once:
  Serial.begin(9600);

}

void loop() 
{
  if(!(myfeedandshoot->set_dcspeed(50)))
  {
    Serial.println("Error in Setting DC Speed");
  }
  myfeedandshoot->move_by_steps(600); // 600 Steps for 1 shot or 550 tested
  delay(3000);
}
