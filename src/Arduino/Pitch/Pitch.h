#ifndef Pitch_h
#define Pitch_h

#include "Arduino.h"

class Pitch
{
	public:
		Pitch();
		bool move_to_location(double desired_location);
		bool home_assembly();
		double get_current_location();
		bool stop_pitch();
		bool move_respect_to_horizon(double horizon_location);

	private:
		double current_location, target_location;
		void set_current_location(double location);
};

#endif