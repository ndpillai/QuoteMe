package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class QuoteMeClient extends Thread{
	
	private QuoteMeFrame qmf;
	private ClientPanel clientPanel;
	private ObjectOutputStream oos;
	private BufferedReader br;
	private Socket socket;
	
	private DataManager dataManager;

	public QuoteMeClient() {
		try {
			socket = new Socket("localhost", 6789);
			qmf = new QuoteMeFrame(this);
			qmf.setVisible(true);
			
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
			this.start();
		} catch (IOException ioe) {
			System.out.println("IOE in client: " + ioe.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (oos != null) {
					oos.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ioe) {
				System.out.println("IOE closing things in client constructor: " + ioe.getMessage());
			}
		}
	}
	
	//can be used to send Strings as well	
	public void sendObject(Object object) {
		try {
			oos.writeObject(object);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("IOE in QuoteMeClient:33 " + ioe.getMessage());
		}
	}
	
	public void run() {
		try {
			while (true) {
				Object data = br.readLine();

				if (data == null) {
					throw new IOException("Null dataManager received by QuoteMeClient.");
				}
				
				else if (data instanceof client.DataManager) {
					dataManager = (DataManager)data;
				}
			}
		} catch (IOException ioe) {
			System.out.println("IOE in client run(): " + ioe.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new QuoteMeClient();
	}
}
