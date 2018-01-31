#ifndef FeedAndShoot_h
#define FeedAndShoot_h

#include "Arduino.h"

class FeedAndShoot
{
	public:
		FeedAndShoot();
		bool set_dcspeed(int desired_speed);
		void move_by_degrees(double degrees_to_move);

	private:
		int stepperspeed, dcspeed;
		
		//void set_stepperspeed(int);


};

#endif