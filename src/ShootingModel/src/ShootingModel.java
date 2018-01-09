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
	public double distance;
	public double xDistance;
	public double yDistance;
	public double yaw;
	public double yInitialHeight;
	public double xInitialDistance;
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
	private double calculateYawAngle(double yDist, double xDist){
		double yawAngle = Math.atan(yDist/xDist);
		System.out.println("Angle in the X Plane: " + Math.toDegrees(yawAngle));
		return Math.toDegrees(yawAngle);
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
	public ShootingModel(double initialHeight, double xInitialDistance, double pitch){
		yInitialHeight = initialHeight;
		this.pitch = pitch;
		this.xInitialDistance = xInitialDistance;
		
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
	
	/**
	 * Method to check if this specific shot will go over the net
	 * @return boolean [true/false indicating whether ball will pass over net]
	 */
	public boolean netHeightChecker(){
		double velocityInX = velocity*Math.cos(pitch);
		double velocityInY = velocity*Math.sin(pitch);
		
		//1.37 is half the length of the table in meters
		double timeToNet = (1.37 - xInitialDistance)/velocityInX;
		double heightAtHalfTable = 2*velocityInY*timeToNet - 0.5*gravity*Math.pow(timeToNet, 2) + yInitialHeight;
		// 0.1524 is the height of the net in meters
		double distanceAboveNet = heightAtHalfTable - 0.1524;
		System.out.println(distanceAboveNet);

		//0.0254 is 1 inch buffer space above net
		if(distanceAboveNet > 0.0254){
			return true;
		} else {
			return false;
		}		
	}
	
	public static void main(String []args){
		ShootingModel shooter = new ShootingModel(0.0762, 0.0762, 45);
		ShootingDetails details = shooter.getShootingDetails(0.6,0.6);
		boolean passNetHeight = shooter.netHeightChecker();
		System.out.println(details.toString());
		System.out.println(passNetHeight);
	}
}
