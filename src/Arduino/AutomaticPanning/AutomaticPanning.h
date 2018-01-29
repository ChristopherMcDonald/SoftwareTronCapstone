#ifndef AutomaticPanning_h
#define AutomaticPanning_h

#include "Arduino.h"

class AutomaticPanning
{
    public:
        AutomaticPanning();
        void move_to_location(String motor_direction, double degrees_to_move);
        void home_azimuth(String motor_direction);  	
};

#endif