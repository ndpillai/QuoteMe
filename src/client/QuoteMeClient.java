package client;

import java.io.IOException;
import java.net.Socket;

public class QuoteMeClient extends Thread{
	
	private QuoteMeFrame qmf;
	private ClientPanel clientPanel;

	public QuoteMeClient() {
		//try {
			//Socket s = new Socket("localhost", 6789);
			qmf = new QuoteMeFrame(this);
			qmf.setVisible(true);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}
	
	public static void main(String[] args) {
		new QuoteMeClient();
	}
	
	public void run() {
		// TODO
	}
	
	
}
