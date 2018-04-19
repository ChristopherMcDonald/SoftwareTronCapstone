/**
 * Represents the Shooting Mechanism, used for determining valid shot parameters 
 * @author Harit Patel
 * @version 2.0
 */
public class ShootingModel {
	
	public double xInitialDistance = 0;
	public double yInitialHeight;
	public double pitch;
	public double xDistance;
	public double yDistance;
	public double distance;
	public double gravity = 9.8;


	/**
	 * ShootingModel Constructor
	 * @param initialHeight [Private static var to hold height of shooter]
	 * @param landingXCoord [X Coordinate on the grid for landing position]
	 * @param landingYCoord [X Coordinate on the grid for landing position]
	 */
	public ShootingModel(double initialHeight, double pitch){
		this.yInitialHeight = initialHeight;
		this.pitch = pitch;
	}

	/**
	 * Calculates the distance in x plane from the shooter to the landing point
	 * @param  landingXCoord [X Coordinate on the grid for landing position]
	 * @return xDist [Distance (meters) the ball must travel in X direction]
	 */
	private double calculateXDistance(double landingXCoord){
		//The 0.7625 is the centre of the board in x-plane; location of the shooter.
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
		double yDist = Math.abs(landingYCoord + 1.37 + xInitialDistance);
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
	private double calculateYawAngle(double xDist, double yDist, double xCoord){
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
	public double calculateVelocity() {
		double vel = Math.sqrt(gravity/(2.0*Math.tan(pitch)*distance-2.0*yInitialHeight))*(distance/Math.cos(pitch));
		System.out.println("Velocity: "+vel+" m/s");
		return vel;
	}

	public double calculateVelocityWithDrag(double initVelocity) {

		double timeIncrement = 0.1;
		double p = 1.0;
		double dragConstant = 0.0014362336;
		double area = Math.PI*(0.02*0.02);

		double airTime = distance/(initVelocity*Math.cos(pitch));
		double velocityWithDrag = initVelocity;
		double flag = 0.0;

		//TODO: Add while loop for getting effects of drag on velocity
		while (flag <= airTime) {
			flag = flag + timeIncrement;
			double theta = Math.acos(distance/(initVelocity*flag));
			double drag = 0.5*p*area*dragConstant*Math.pow(initVelocity, 2)*Math.sin(theta);
			velocityWithDrag = velocityWithDrag - gravity*timeIncrement;
			velocityWithDrag = velocityWithDrag + drag;			
		}

		return velocityWithDrag;
	}

	/**
	 * Method to get Yaw,Velocity from abstract data type (ShootingDetails)
	 * @param landingXCoord [X Coordinate on the grid for landing position]
	 * @param landingYCoord [Y Coordinate on the grid for landing position]
	 * @return [ShootingDetails to get Yaw(degrees), Velocity(meters/second)]
	 */
	public ShootingDetails getShootingDetails(double landingXCoord, double landingYCoord){
		distance = calculateDistance(landingXCoord, landingYCoord);
		double yaw = calculateYawAngle(xDistance, yDistance, landingXCoord);
		double velocity = calculateVelocity();
		double dragVelocity = calculateVelocityWithDrag(velocity);
		return new ShootingDetails(yaw, dragVelocity);
	}

	public static void main(String []args){
		ShootingModel shooter = new ShootingModel(0.0762, 45);
		ShootingDetails details = shooter.getShootingDetails(0.6,0.6);
		System.out.println(details.toString());
	}
}
