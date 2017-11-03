
public class ShootingModel {
	
	// Instantiate necessary variables
	public double distance;
	public double x_distance;
	public double y_distance;
	public double x_plane_angle;
	public double y_initial_height;
	public double y_plane_angle = 45;
	public double g = 9.8;
	public double velocity;
	
	//Calculates the distance in x plane from the shooter to the landing point
	public double calculate_x_distance(double landing_x_coord){
		//The 0.38125 is the centre of the board in x-plane; location of the shooter.
		double x_dist = Math.abs(0.7625 - landing_x_coord);
		return x_dist;
	}
	
	//Calculates the distance in y plane from the shooter to the landing point
	public double calculate_y_distance(double landing_y_coord){
		//The location of the shooter is at 0 m from the edge
		double y_dist = Math.abs(landing_y_coord - 0);
		return y_dist;
	}
	
	/*
	 * This is for when you allow angle to change - this will need to change the speed
	 * Leave for Iteration 2
	 * 
	 * public double calculate_y_plane_angle(){
		return 0.0;
	}
	*/
	
	//Calculates the total distance from the shooter to the landing point
	public double calculate_distance(double landing_x_coord, double landing_y_coord){
		x_distance = calculate_x_distance(landing_x_coord);
		y_distance = calculate_y_distance(landing_y_coord);	
		double dist_squared = Math.pow(x_distance, 2)+Math.pow(y_distance, 2);
		double total_distance = Math.sqrt(dist_squared);
		System.out.println("Total travel distance: "+total_distance+" meters");
		return total_distance;
	}
	
	public double calculate_x_angle(double y_dist, double x_dist){
		double angle = Math.atan(y_dist/x_dist);
		System.out.println("Angle in the X Plane: "+Math.toDegrees(angle));
		return angle;
	}
	
	//TODO: Change Y angle based on velocity as an input
	public double calculate_y_angle(){
		System.out.println("Angle in the Y Plane: "+y_plane_angle);
		return y_plane_angle;
	}
	
	//Constructor - define variables here
	public ShootingModel(double initial_height, double landing_x_coord, double landing_y_coord){
		y_initial_height = initial_height;
		distance = calculate_distance(landing_x_coord, landing_y_coord);
		x_plane_angle = calculate_x_angle(x_distance, y_distance);
		double y_angle = calculate_y_angle();
		velocity = calculate_velocity();
	}
	
	public double calculate_velocity(){
		double vel = Math.sqrt((Math.pow(distance, 2)*g)/(2*Math.pow(Math.cos(y_plane_angle),2)*(y_initial_height+distance*Math.tan(y_plane_angle))));
		System.out.println("Velocity: "+vel+" m/s");
		return vel;
	}
	
	public static void main(String []args){
		ShootingModel shooter = new ShootingModel(0.0762, 0.38125, 2.055);		
	}	
}
