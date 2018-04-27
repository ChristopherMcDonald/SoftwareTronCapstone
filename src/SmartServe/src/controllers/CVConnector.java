package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import errors.NotConnectedException;


public class CVConnector {
	
	public static void main(String[] args) throws NotConnectedException, InterruptedException {
		CVConnector cv = new CVConnector();
		System.out.println(cv.connect(8013));
		System.out.println(cv.start());
	}
	
	private int port = -1; // the port value if successfully connects
	
	/**
	 * attempts to connect to the CV over some port <code>port</code>
	 * @param port - port to connect over
	 * @return successful connection
	 * @throws NotConnectedException 
	 */
	public boolean connect(int port) throws NotConnectedException {
		boolean status = false; // assume connection fails
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			outToServer.writeUTF("TEST");
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotConnectedException("CVConnector", port);
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
	        }
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotConnectedException("CVConnector", port + 1);
		}
		
		if(!status) this.port = -1;
		
		return status;
	}
	
	/**
	 * begins detecting the ball over CV
	 * @return if ball was returned
	 * @throws NotConnectedException 
	 */
	public boolean start() throws NotConnectedException {
		if(port == -1) {
			throw new NotConnectedException("CVConnector", port);
		}
		
		String fromClient;
		Socket clientSocket;
		ServerSocket server;
		
		try {
			clientSocket = new Socket("localhost", port);
			server = new ServerSocket(port + 1);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			System.out.print("STARTING CV... ");
			outToServer.writeUTF("DETECT");
			
			Socket client = server.accept();
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

	        fromClient = in.readLine();
	        clientSocket.close();
	        server.close();
	        System.out.print("GOT CV... ");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotConnectedException("CVConnector", port);
		}
		return fromClient.equals("GOOD");
	}
}
