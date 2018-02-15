package adt;

/**
 * ADT for shooting parameters
 * - size of the table (length and width)
 * @author christophermcdonald
 *
 */
public class ShootingParameters {
	
	public double length; // long side of table, perpendicular to shooting robot
	public double width; // short side of table
	
	/**
	 * Constructor for ShootingParameter
	 * @param l - length of table
	 * @param w - width of table
	 */
	public ShootingParameters(double l, double w) {
		length = l;
		width = w;
	}
}
