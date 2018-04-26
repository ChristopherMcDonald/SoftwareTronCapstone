package shootingModel;

public class ShootingModel {
    // Instantiate necessary variables
	public double xCoord;
	public double yCoord;
    public double distance;
    public double xDistance;
    public double yDistance;
    public double yaw;
    public double yInitialHeight;
    public double gravity = 9.8;
    public double velocity;

    /**
     * Calculates the distance in x plane from the shooter to the landing point
     * @param  landingXCoord [X Coordinate on the grid for landing position]
     * @return xDist [Distance (meters) the ball must travel in X direction]
     */
    private double calculateXDistance(double landingXCoord){
        //The 0.38125 is the centre of the board in x-plane; location of the shooter.
        double xDist = Math.abs(0.7625 - landingXCoord);
        return xDist;
    }

    /**
     * Calculates the distance in y plane from the shooter to the landing point
     * @param  landingYCoord [Y Coordinate on the grid for landing position]
     * @return yDist [Distance (meters) the ball must travel in Y direction]
     */
    private double calculateYDistance(double landingYCoord){
        //The location of the shooter is at 0 m from the edge
        double yDist = Math.abs(landingYCoord + 1.37);
        return yDist;
    }

    //Calculates the total distance from the shooter to the landing point
    /**
     * Calculates the total distance from the shooter to the landing point
     * @param landingXCoord [X Coordinate on the grid for landing position]
     * @param landingYCoord [Y Coordinate on the grid for landing position]
     * @return totalDistance [Total distance (meters) the ball must travel]
     */
    public double calculateDistance(double landingXCoord, double landingYCoord){
        xDistance = calculateXDistance(landingXCoord);
        yDistance = calculateYDistance(landingYCoord);
        double distSquared = Math.pow(xDistance, 2) + Math.pow(yDistance, 2);
        double totalDistance = Math.sqrt(distSquared);
        //System.out.println("Total travel distance: " + totalDistance + " meters");
        return totalDistance;
    }

    /**
     * TODO
     * @param yDist [Distance (meters)the ball must travel in Y direction]
     * @param xDist [Distance (meters) the ball must travel in X direction]
     * @return yawAngle [Yaw angle (degrees)]
     */
    private double calculateYawAngle(double yDist, double xDist){
    		double yawAngle = Math.toDegrees(Math.atan(xDist/yDist));
		if (xCoord <= 0.7625) {
			yawAngle += 90;
		} else {
			yawAngle = 90 - yawAngle;
		}
		return yawAngle;
    }

    /**
     * Calculates velocity in Yaw direction
     * @return vel [Velocity (meters/second)]
     */
    public double calculateVelocity(double pitch){
        double vel = Math.sqrt((Math.pow(distance, 2)*gravity)/(2*Math.pow(Math.cos(pitch),2)*(yInitialHeight+distance*Math.tan(pitch))));
        //System.out.println("Velocity: "+vel+" m/s");
        return vel;
    }

    /**
     * ShootingModel Constructor
     * @param initialHeight [Private static var to hold height of shooter]
     * @param landingXCoord [X Coordinate on the grid for landing position]
     * @param landingYCoord [X Coordinate on the grid for landing position]
     */
    public ShootingModel(double initialHeight){
        yInitialHeight = initialHeight;
    }

    /**
     * Method to get Yaw,Velocity from abstract data type (ShootingDetails)
     * @param landingXCoord [X Coordinate on the grid for landing position]
     * @param landingYCoord [Y Coordinate on the grid for landing position]
     * @return [ShootingDetails to get Yaw(degrees), Velocity(meters/second)]
     */
    public ShootingDetails getShootingDetails(double landingXCoord, double landingYCoord, double pitch){
    		xCoord = landingXCoord;
    		yCoord = landingYCoord;
        distance = calculateDistance(landingXCoord, landingYCoord);
        yaw = calculateYawAngle(yDistance, xDistance);
        velocity = calculateVelocity(pitch);
        return new ShootingDetails(yaw, velocity);
    }

//    public static void main(String []args){
//        ShootingModel shooter = new ShootingModel(0.0762);
//        ShootingDetails details = shooter.getShootingDetails(0.6,0.6,45);
//        System.out.println(details.toString());
//    }
}
