package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import adt.Shot;
import errors.NotConnectedException;

public class ShotRecommendationController {
	
	private int port = -1; // port of ShotRecommeder on successful connection
	private String address = null; // address of SR on successful connection
	
	/**
	 * attempts to connect to the SR over some port <code>port</code> on localhost
	 * @param port - port to connect over
	 * @return successful connection
	 * @throws NotConnectedException 
	 */
	public boolean connect(int port) throws NotConnectedException {
		return this.connect(port, "localhost");
	}
	
	/**
	 * attempts to connect to the SR over some port <code>port</code> and address <code>address</code>
	 * @param port - port to connect over
	 * @param address - address to connect to
	 * @return successful connection
	 * @throws NotConnectedException 
	 */
	public boolean connect(int port, String address) throws NotConnectedException {
		boolean status = false; // assume connection fails
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			outToServer.writeUTF("TEST");
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotConnectedException("SRConnector at " + address, port);
		}
		
		ServerSocket server;
		try {
			server = new ServerSocket(port + 1);
			Socket client = server.accept();
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

	        String fromClient = in.readLine();
	        server.close();
	        
	        if(fromClient.equals("ALLGOOD")) {
	        	status = true;
	        	this.port = port;
	        	this.address = address;
	        }
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotConnectedException("SRConnector at " + address, port + 1);
		}
		
		if(!status) {
			this.port = -1;
			this.address = null;
		}
		
		return status;	
	}
	
	/**
	 * gets the next shot to shoot
	 * @return Shot to be shot
	 * @throws NotConnectedException 
	 */
	public Shot getRecommendation() throws NotConnectedException {
		if(port == -1 || address == null) {
			throw new NotConnectedException("SRConnector at " + address, port);
		}
		
		String shotDetail;
		Socket clientSocket;
		
		try {
			clientSocket = new Socket("localhost", port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			outToServer.writeUTF("SHOT");
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotConnectedException("SRConnector at " + address, port);
		}
		
		ServerSocket server;
		try {
			server = new ServerSocket(port + 1);
			Socket client = server.accept();
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

	        shotDetail = in.readLine();
	        server.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotConnectedException("SRConnector at " + address, port + 1);
		}
		
		Shot shot = new Shot(shotDetail);
		return shot;
	}
	
	/**
	 * updates the ML model based on outcome of some shot
	 * @param shot - shot in question
	 * @param returned - if it were returned
	 * @throws NotConnectedException 
	 */
	public void updateModel(Shot shot, boolean returned) throws NotConnectedException {
		if(port == -1 || address == null) {
			throw new NotConnectedException("SRConnector at " + address, port);
		}
		
		String shotDetail = shot.toString() + ",returned=" + (returned ? 1 : 0);
		Socket clientSocket;
		
		try {
			clientSocket = new Socket("localhost", port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			outToServer.writeUTF(shotDetail);
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotConnectedException("SRConnector at " + address, port);
		}
		
		return;
	}
	
}
