package controllers;

import java.util.Map;
import java.sql.ResultSet;

public class SQLConnector {
	
	private int port; // port if successfully connect
	private String address; // address if successfully connect
	
	
	public boolean connect(int port) {
		// TODO implement
		return false;
	}
	
	public boolean connect(int port, String address) {
		// TODO implement
		return false;
	}
	
	public ResultSet query(String proc, Map<String, String> values) {
		// TODO implement
		return null;
	}
	
	public boolean save(String proc, Map<String, String> values) {
		// TODO implement
		return false;
	}

}
