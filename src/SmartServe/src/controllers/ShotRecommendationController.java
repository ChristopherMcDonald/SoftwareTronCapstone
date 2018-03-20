package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import adt.Shot;
import enums.Mode;

public class ShotRecommendationController {
	
	private static final String targetURL = "http://localhost:8080/nextShot";
	
	private static Shot makeRequest(URL url) {
		HttpURLConnection connection = null;
		try {
			
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");

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
	
	public static Shot getRecommendation(Mode m) throws MalformedURLException {
		return makeRequest(new URL(targetURL + "?mode=" + m.toString()));
	}
	
	public static Shot getRecommendation(int shotId) throws MalformedURLException {
		return makeRequest(new URL(targetURL + "?mode=" + Mode.ONESHOT + "&shot=" + shotId));
	}
}
