#ifndef AutomaticRoll_h
#define AutomaticRoll_h

#include "Arduino.h"

class AutomaticRoll
{
    public:
        AutomaticRoll();
        bool move_to_location(double desired_location);
        bool home_assembly(String motor_direction); 
        double get_current_location();
        bool stop_roll();
    private:
    	// Home Position -> 0 deg and measured CCW+
    	double current_location, target_location, location_diff; 
    	void set_current_location(double location);
    	void move_by_degrees(String motor_direction, double degrees_to_move);
};

#endif