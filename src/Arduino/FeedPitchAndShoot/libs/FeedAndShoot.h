#ifndef FeedAndShoot_h
#define FeedAndShoot_h

#include "Arduino.h"

class FeedAndShoot
{
	public:
		FeedAndShoot();
		bool set_dcspeed(int desired_speed);
		void move_by_steps(int steps);
		bool stop_feed_shot();

	private:
		int stepperspeed, dcspeed;

		//void set_stepperspeed(int);


};

#endif
