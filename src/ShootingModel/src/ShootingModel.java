/**
 * Represents the Shooting Mechanism, used for determining valid shot parameters 
 * @author Harit Patel
 * @version 1.1
 *
 * TODO items:
 * - add Calculate Y Plane Angle method
 */
public class ShootingModel {

	// Instantiate necessary variables
	public double xCoord;
	public double yCoord;
	public double distance;
	public double xDistance;
	public double yDistance;
	public double yaw;
	public double yInitialHeight;
	public double pitch;
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
		double yDist = Math.abs(landingYCoord - 0);
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
		xCoord = landingXCoord;
		yCoord = landingYCoord;
		xDistance = calculateXDistance(landingXCoord);
		yDistance = calculateYDistance(landingYCoord);
		double distSquared = Math.pow(xDistance, 2) + Math.pow(yDistance, 2);
		double totalDistance = Math.sqrt(distSquared);
		System.out.println("Total travel distance: " + totalDistance + " meters");
		return totalDistance;
	}

	/**
	 * TODO
	 * @param yDist [Distance (meters)the ball must travel in Y direction]
	 * @param xDist [Distance (meters) the ball must travel in X direction]
	 * @return yawAngle [Yaw angle (degrees)]
	 */
	private double calculateYawAngle(double xDist, double yDist){
		double yawAngle = Math.toDegrees(Math.atan(yDist/xDist));
		if (xCoord <= 0.7625) {
			yawAngle = 180-yawAngle;
		} 
		System.out.println("Angle in the X Plane: " + yawAngle);
		return yawAngle;
	}

	/**
	 * Calculates velocity in Yaw direction
	 * @return vel [Velocity (meters/second)]
	 */
	public double calculateVelocity(){
		double vel = Math.sqrt((Math.pow(distance, 2)*gravity)/(2*Math.pow(Math.cos(pitch),2)*(yInitialHeight+distance*Math.tan(pitch))));
		System.out.println("Velocity: "+vel+" m/s");
		return vel;
	}
	
	/**
	 * ShootingModel Constructor
	 * @param initialHeight [Private static var to hold height of shooter]
	 * @param landingXCoord [X Coordinate on the grid for landing position]
	 * @param landingYCoord [X Coordinate on the grid for landing position]
	 */
	public ShootingModel(double initialHeight, double pitch){
		yInitialHeight = initialHeight;
		this.pitch = pitch;
	}
	
	/**
	 * Method to get Yaw,Velocity from abstract data type (ShootingDetails)
	 * @param landingXCoord [X Coordinate on the grid for landing position]
	 * @param landingYCoord [Y Coordinate on the grid for landing position]
	 * @return [ShootingDetails to get Yaw(degrees), Velocity(meters/second)]
	 */
	public ShootingDetails getShootingDetails(double landingXCoord, double landingYCoord){
		distance = calculateDistance(landingXCoord, landingYCoord);
		yaw = calculateYawAngle(xDistance, yDistance);
		velocity = calculateVelocity();
		return new ShootingDetails(yaw, velocity);
	}

	public static void main(String []args){
		ShootingModel shooter = new ShootingModel(0.0762, 45);
		ShootingDetails details = shooter.getShootingDetails(0.6,0.6);
		System.out.println(details.toString());
	}
}
