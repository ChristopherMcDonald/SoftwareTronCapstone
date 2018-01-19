package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class CVConnector {
	
	private int port; // the port value if successfully connects
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// below is an example of how to in/out comm with python
		int port = 8013;
		Socket clientSocket = new Socket("localhost", port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		outToServer.writeUTF("TEST");
		clientSocket.close();
		
		ServerSocket server = new ServerSocket(port + 1);
		Socket client = server.accept();
        System.out.println("got connection on port 8014");
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String fromClient = in.readLine();
        System.out.println("received: " + fromClient);
        server.close();
	}
	
	/**
	 * attempts to connect to the CV over some port <code>port</code>
	 * @param port - port to connect over
	 * @return successful connection
	 */
	public boolean connect(int port) {
		// TODO implement
		return false;
	}
	
	/**
	 * begins detecting the ball over CV
	 * @return if ball was returned
	 */
	public boolean start() {
		// TODO implement
		return false;
	}
}
