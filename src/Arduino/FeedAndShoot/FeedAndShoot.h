#ifndef FeedAndShoot_h
#define FeedAndShoot_h

#include "Arduino.h"

class FeedAndShoot
{
	public:
		FeedAndShoot();
		int get_dcspeed();
		bool set_dcspeed(int);
		//int get_stepperspeed();

	private:
		int stepperspeed, dcspeed;
		
		//void set_stepperspeed(int);


};

#endif