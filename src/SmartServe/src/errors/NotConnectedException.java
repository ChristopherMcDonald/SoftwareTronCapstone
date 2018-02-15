package errors;

public class NotConnectedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NotConnectedException(String system, int port) {
		super("Not connected to " + system + " on port: " + Integer.toString(port));
	}

	public NotConnectedException(String system, String port) {
		super("Not connected to " + system + " on port: " + port);
	}

}
