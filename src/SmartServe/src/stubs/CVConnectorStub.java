package stubs;

import controllers.CVConnector;
import errors.NotConnectedException;

public class CVConnectorStub extends CVConnector {
	
	private boolean[] answers = {true, true, false, false};
	private int pos;
	
	@Override
	public boolean connect(int port) throws NotConnectedException {
		 answers = new boolean[]{true, true, false, false};
		 pos = 0;
		 return true;
	}
	
	@Override
	public boolean start() throws NotConnectedException {
		boolean ret = answers[pos];
		pos++;
		return ret;
	}
}
