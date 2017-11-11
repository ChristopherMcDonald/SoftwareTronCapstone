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
	public double xPlaneAngle;
	public double yInitialHeight;
	public double yPlaneAngle = 45;
	public double gravity = 9.8;
	public double velocity;

	/**
	 * Calculates the distance in x plane from the shooter to the landing point
	 * @param  double landingXCoord [TODO description]
	 * @return        [TODO description]
	 */
	public double calculateXDistance(double landingXCoord){
		//The 0.38125 is the centre of the board in x-plane; location of the shooter.
		double xDist = Math.abs(0.7625 - landingXCoord);
		return xDist;
	}

	/**
	 * Calculates the distance in y plane from the shooter to the landing point
	 * @param  double landingYCoord [TODO description]
	 * @return        [TODO description]
	 */
	public double calculateYDistance(double landingYCoord){
		//The location of the shooter is at 0 m from the edge
		double yDist = Math.abs(landingYCoord - 0);
		return yDist;
	}

	//Calculates the total distance from the shooter to the landing point
	/**
	 * Calculates the total distance from the shooter to the landing point
	 * @param landingXCoord TODO descr
	 * @param landingYCoord TODO descr
	 * @return 
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
	 * @param yDist TODO
	 * @param xDist TODO
	 * @return TODO
	 */
	public double calculateXAngle(double yDist, double xDist){
		double angle = Math.atan(yDist/xDist);
		System.out.println("Angle in the X Plane: " + Math.toDegrees(angle));
		return angle;
	}

	//TODO: Change Y angle based on velocity as an input
	/**
	 * TODO
	 * @return TODO
	 */
	public double calculateYAngle(){
		System.out.println("Angle in the Y Plane: " + yPlaneAngle);
		return yPlaneAngle;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public double calculateVelocity(){
		double vel = Math.sqrt((Math.pow(distance, 2)*gravity)/(2*Math.pow(Math.cos(yPlaneAngle),2)*(yInitialHeight+distance*Math.tan(yPlaneAngle))));
		System.out.println("Velocity: "+vel+" m/s");
		return vel;
	}
	
	/**
	 * ShootingModel Constructor
	 * @param initialHeight
	 * @param landingXCoord
	 * @param landingYCoord
	 */
	public ShootingModel(double initialHeight, double landingXCoord, double landingYCoord){
		yInitialHeight = initialHeight;
		distance = calculateDistance(landingXCoord, landingYCoord);
		xPlaneAngle = calculateXAngle(xDistance, yDistance);
		double y_angle = calculateYAngle();
		velocity = calculateVelocity();
	}

	public static void main(String []args){
		ShootingModel shooter = new ShootingModel(0.0762, 0.38125, 2.055);
	}
}
