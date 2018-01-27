package shootingModel;

public class ShootingDetails {
    private double yaw;
    private double velocity;

    /**
     * Constructor
     * @param yaw
     * @param velocity
     */
    public ShootingDetails(double yaw, double velocity){
        this.yaw = yaw;
        this.velocity = velocity;
    }

    /**
     * getter for yaw
     * @return yaw
     */
    public double getYaw(){
        return yaw;
    }

    /**
     * getter for velocity
     * @return
     */
    public double getVelocity(){
        return velocity;
    }

    /**
     * method to get yaw & velocity in String format
     */
    public String toString(){
        return String.format("yaw:%.2f, vel:%.2f", yaw, velocity);
    }
}
