class AutomaticPanning
{
    public:
        AutomaticPanning();
        void home_azimuth(String motor_direction);

    private:
        void move_azimuth(String motor_direction, double azimuth_move_degrees);
};