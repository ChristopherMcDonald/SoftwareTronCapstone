#include <Pitch.h>
#include <FeedAndShoot.h>


Pitch* mypitch;
FeedAndShoot* myfeedandshoot;

void setup() {
    mypitch = new Pitch();
    myfeedandshoot = new FeedAndShoot();
    mypitch->home_assembly();
    myfeedandshoot->set_dcspeed(0);

}

void loop() 
{
//  mypitch->move_to_location(60);
//  myfeedandshoot->set_dcspeed(15);
//  delay(5000);
//  myfeedandshoot->move_by_steps(550);
}
