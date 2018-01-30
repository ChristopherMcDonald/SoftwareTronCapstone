package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import org.omg.CORBA.portable.InputStream;

import adt.Mode;
import adt.Shot;
import errors.NotConnectedException;

public class ShotRecommendationController {
	
	private static final String targetURL = "http://localhost:8080/nextShot";
	
	public static Shot getRecommendation() {
		 
		HttpURLConnection connection = null;
		try {			  
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.close();

		    //Get Response  
		    java.io.InputStream is =  connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); 
		    String line;
		    
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    
		    rd.close();
		    String shotDetail = response.toString();
		    return new Shot(shotDetail);
		    
	  } catch (Exception e) {
		  e.printStackTrace();
		  return null;
	  } finally {
		  if (connection != null) {
			  connection.disconnect();
		  }
	  	}
	}	
}
