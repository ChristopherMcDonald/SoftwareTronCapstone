package adt;

/**
 * ADT for a Shot made by ShotRecommender subsystem
 * @author christophermcdonald
 *
 */
public class Shot {
	
	public static void main(String[] args) {
		String s = "X=2.34,Y=123.12464312345,V=.2353234,W=0.242353";
		
		Shot s1 = new Shot(s);
		
		System.out.println(s1);
	}
	
	public double xLoc; // x location
	public double yLoc; // y location
	public double velocity; // ball speed
	public double rollAngle; // angular direction
	
	/**
	 * Constructor for a Shot
	 * @param x - x landing location of the ball
	 * @param y - y landing location of the ball
	 * @param v - absolute speed of the ball
	 * @param w - angular direction of the ball
	 */
	public Shot(double x, double y, double v, double w) {
		this.xLoc = x;
		this.yLoc = y;
		this.rollAngle = w;
		this.velocity = v;
	}
	
	/**
	 * Constructor for a Shot
	 * @param shotDetail
	 */
	public Shot(String shotDetail) {
		String[] details = shotDetail.split(","); // TODO make sure this is okay
		Double[] nums = new Double[details.length];
		for(int i = 0; i < details.length; i++) {
			String num = details[i].substring(2, details[i].length() - 1); // TODO make sure this is okay
			nums[i] = Double.parseDouble(num);
		}
		
		xLoc = nums[0];
		yLoc = nums[1];
		velocity = nums[2];
		rollAngle = nums[3];
	}

	@Override
	public String toString() {
		return String.format("X=%1$,.2f,Y=%2$,.2f,V=%3$,.2f,W=%4$,.2f", xLoc, yLoc, velocity, rollAngle);
	}
	
}
