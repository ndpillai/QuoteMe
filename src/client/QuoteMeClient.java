package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class QuoteMeClient extends Thread{
	
	private QuoteMeFrame qmf;
	private ClientPanel clientPanel;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket socket;
	
	public DataManager dataManager = new DataManager();

	public QuoteMeClient() {
		try {
			socket = new Socket("localhost", 6789);
			qmf = new QuoteMeFrame(this);
			qmf.setVisible(true);
			
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		
			this.start();
		} catch (IOException ioe) {
			System.out.println("IOE in client: " + ioe.getMessage());
		} finally {
		/*	try {
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
			} */
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
				Object data = null;
				try {
					data = ois.readObject();
				} catch (ClassNotFoundException cnfe) {
					System.out.println("ClassNotFoundException in ServerClientCommunicator run(): " +cnfe.getMessage());
				}

				if (data == null) {
					throw new IOException("Null dataManager received by QuoteMeClient.");
				}
				
				if (data instanceof client.DataManager) {
					System.out.println("we are receiving an instance of DataManager in QuoteMeClient");
					dataManager = (DataManager)data;
				}
			}
		} catch (IOException ioe) {
			System.out.println("IOE in client run(): " + ioe.getMessage());
			
			try {
				if (ois != null) {
					ois.close();
				}
				if (oos != null) {
					oos.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ioe2) {
				System.out.println("IOE closing things in client constructor: " + ioe2.getMessage());
			} 
		}
	}
	
	public void setClientPanel(ClientPanel clientPanel) {
		this.clientPanel = clientPanel;
	}
	
	public static void main(String[] args) {
		new QuoteMeClient();
	}
}
