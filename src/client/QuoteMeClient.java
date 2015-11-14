package client;

import java.io.IOException;
import java.net.Socket;

public class QuoteMeClient {

	public QuoteMeClient() {
		QuoteMeFrame qmf = new QuoteMeFrame();
		qmf.setVisible(true);
		try {
			Socket s = new Socket("localhost", 6789);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new QuoteMeClient();
	}
}
