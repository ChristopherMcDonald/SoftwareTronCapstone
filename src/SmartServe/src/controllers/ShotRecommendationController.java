package controllers;

import adt.Shot;

public class ShotRecommendationController {
	
	private int port; // port of ShotRecommeder on successful connection
	private String address; // address of SR on successful connection
	
	/**
	 * attempts to connect to the SR over some port <code>port</code> on localhost
	 * @param port - port to connect over
	 * @return successful connection
	 */
	public boolean connect(int port) {
		// TODO implement 
		return false;
		
	}
	
	/**
	 * attempts to connect to the SR over some port <code>port</code> and address <code>address</code>
	 * @param port - port to connect over
	 * @param address - address to connect to
	 * @return successful connection
	 */
	public boolean connect(int port, String address) {
		// TODO implement
		return false;
	}
	
	/**
	 * gets the next shot to shoot
	 * @return Shot to be shot
	 */
	public Shot getRecommendation() {
		// TODO implement
		return null;
	}
	
	/**
	 * updates the ML model based on outcome of some shot
	 * @param shot - shot in question
	 * @param returned - if it were returned
	 */
	public void updateModel(Shot shot, boolean returned) {
		// TODO implement
		return;
	}
	
}
