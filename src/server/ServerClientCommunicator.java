package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.DataManager;
import client.Quote;

public class ServerClientCommunicator extends Thread {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	//private ServerListener serverListener;
	private QuoteMeServer server;
	
	private DataManager dataManager;
	
	public ServerClientCommunicator(Socket socket, QuoteMeServer server, DataManager dataManager) throws IOException
	{
		this.socket = socket;
		this.server = server;
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		this.ois = new ObjectInputStream(socket.getInputStream());
		
		this.dataManager = dataManager;
	}
	
	public void sendAppInstance(DataManager dataManager) {
		try {
			oos.writeObject(dataManager);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator sendAppInstance() " + ioe.getMessage());
		}
	}
	
	/*Will likely not need this method, but good to have.
	public void sendObject(Object obj) {
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator sendQuote()" + ioe.getMessage());
		}
	}*/
	
	// reading in:
	public void run() {
		try {
			while (true) {
				Object info = null;
				try {
					info = ois.readObject();
				} catch (ClassNotFoundException cnfe) {
					System.out.println("ClassNotFoundException in ServerClientCommunicator run(): " +cnfe.getMessage());
				}
				
			
				if (info instanceof client.Quote) {
					dataManager.addQuote((Quote)info);
					server.sendAppInstanceToAllClients(dataManager);
				}
				
				else if (info instanceof String) {
					
				} 
				
			}
		} catch (IOException ioe) {
			if (ioe.getMessage() == null) {
				System.out.println("Client disconnected.");
				server.pushToTextFile();
			}
			else {
				System.out.println("IOE in ServerClientCommunicator run() " + ioe.getMessage());
			}
			
			server.removeServerClientCommunicator(this);
			// this means that the socket is closed since no more lines are being received
			try {
				socket.close();
			} catch (IOException ioe1) {
				System.out.println("IOE in ServerClientCommunicator run() 2 " + ioe.getMessage());
			} 
		} 
	}
	
}
