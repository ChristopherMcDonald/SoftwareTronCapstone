package errors;

public class NotConnectedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NotConnectedException(String system, int port) {
		super("Not connected to " + system + " on port: " + Integer.toString(port));
	}

}
