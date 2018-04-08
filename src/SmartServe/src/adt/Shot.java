package adt;

/**
 * ADT for a Shot made by ShotRecommender subsystem
 * @author christophermcdonald
 *
 */
public class Shot {
	
	public double xLoc; // x location
	public double yLoc; // y location
	public double pitch; // ball pitch
	public double rollAngle; // angular direction
	public int shotId;
	
	/**
	 * Constructor for a Shot
	 * @param x - x landing location of the ball
	 * @param y - y landing location of the ball
	 * @param p - pitch of the ball trajectory
	 * @param w - angular direction of the ball
	 */
	public Shot(double x, double y, double p, double w, int id) {
		this.xLoc = x;
		this.yLoc = y;
		this.rollAngle = w;
		this.pitch = p;
		this.shotId = id;
	}
	
	/**
	 * Constructor for a Shot
	 * @param shotDetail
	 */
	public Shot(String shotDetail) {
		String[] details = shotDetail.split(",");
		Double[] nums = new Double[details.length];
		for(int i = 0; i < details.length; i++) {
			String num = details[i].substring(2, details[i].length() - 1);
			nums[i] = Double.valueOf(num);
		}
		
		xLoc = nums[0];
		yLoc = nums[1];
		pitch = nums[2];
		rollAngle = nums[3];
		shotId = Integer.parseInt(details[4].substring(2, details[4].length() - 1));
	}

	@Override
	public String toString() {
		return String.format("X=%1$,.2f,Y=%2$,.2f,P=%3$,.2f,W=%4$,.2f,ID=%d", xLoc, yLoc, pitch, rollAngle, shotId);
	}
	
}
