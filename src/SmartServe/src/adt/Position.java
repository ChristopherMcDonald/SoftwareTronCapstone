package adt;

/**
 * ADT for the position of the shooting mechanism
 * @author christophermcdonald
 *
 */
public class Position {
	
	public float pitch;
	public float yaw;
	public float roll;
	
	/**
	 * 
	 * @param pitch
	 * @param yaw
	 * @param roll
	 */
	public Position(float pitch, float yaw, float roll) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}

}
