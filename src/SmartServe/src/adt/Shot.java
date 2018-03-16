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
		
		x = nums[0];
		y = nums[1];
		v = nums[2];
		w = nums[3];
	}

	@Override
	public String toString() {
		return String.format("X=%1$,.2f,Y=%2$,.2f,V=%3$,.2f,W=%4$,.2f", x, y, v, w);
	}
	
}
