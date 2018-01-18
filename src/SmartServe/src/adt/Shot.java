package adt;

/**
 * ADT for a Shot made by ShotRecommender subsystem
 * Where
 * @author christophermcdonald
 *
 */
public class Shot {
	
	public double x; // x location
	public double y; // y location
	public double v; // ball speed
	public double w; // angular direction
	
	/**
	 * Constructor for a Shot
	 * @param x - x landing location of the ball
	 * @param y - y landing location of the ball
	 * @param v - absolute speed of the ball
	 * @param w - angular direction of the ball
	 */
	public Shot(double x, double y, double v, double w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.v = v;
	}
	
}
