package adt;

/**
 * ADT for the details for a Shot for the Arduino
 * @author christophermcdonald
 *
 */
public class ShotDetail {
	
	public float pitch;
	public float yaw;
	public float velocity;
	public float angular; // direction
	
	public ShotDetail(float p, float y, float v, float a) {
		pitch = p;
		yaw = y;
		velocity = v;
		angular = a;
	}
	
	/**
	 * Constructor for a Shot
	 * @param shotDetail
	 */
	public ShotDetail(String shotDetail) {
		String[] details = shotDetail.split(","); // TODO make sure this is okay
		Float[] nums = new Float[details.length];
		for(int i = 0; i < details.length; i++) {
			String num = details[i].substring(2, details[i].length() - 1); // TODO make sure this is okay
			nums[i] = Float.parseFloat(num);
		}
		
		pitch = nums[0];
		yaw = nums[1];
		velocity = nums[2];
		angular = nums[3];
	}
	
	public String toString() {
		return String.format("P=%.2f,Y=%.2f,V=%.2f,W=%.2f", pitch, yaw, velocity, angular);
	}
	
}
