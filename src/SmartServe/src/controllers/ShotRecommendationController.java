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
	
	private static final String baseURL = "http://localhost:8080/";
	private static final String nextShotURL = baseURL + "nextShot";
	private static final String startURL = baseURL + "start";
	
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
		return makeRequest(new URL(nextShotURL + "?mode=" + m.toString()));
	}
	
	public static Shot getRecommendation(int shotId) throws MalformedURLException {
		return makeRequest(new URL(nextShotURL + "?mode=" + Mode.SINGLEZONE + "&shot=" + shotId));
	}
	
	public static boolean setModel() {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(startURL);
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
		    String res = response.toString();
		    return new Boolean(res);
		    
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  } finally {
		  if (connection != null) {
			  connection.disconnect();
		  }
	  }
	}
}
