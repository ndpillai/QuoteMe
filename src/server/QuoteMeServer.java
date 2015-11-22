package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import client.DataManager;

public class QuoteMeServer {
	
	private ServerSocket ss;
//	private ServerListener serverListener;
	private Vector<ServerClientCommunicator> sccVector = new Vector<ServerClientCommunicator>();
	private DataManager dataManager;
	
	public QuoteMeServer()
	{
		loadQuoteMeUniverse(); // sets dataManager
		
		try {
			ss = new ServerSocket(6789);
			while (true) {
				System.out.println("Waiting for client to connect...");
				Socket s = ss.accept();
				System.out.println("Client " + s.getInetAddress() + ":" + s.getPort() + " connected");
				
				ServerClientCommunicator scc = new ServerClientCommunicator(s, this, dataManager);
				sccVector.add(scc);
				System.out.println("About to start");
				scc.start();
				System.out.println("AFTER START");
				System.out.println("DataManager in server try null?: " + (dataManager==null));

				if (dataManager != null) {
					System.out.println("Sending app instance");
					scc.sendAppInstance(dataManager);
				}
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} finally {
						
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException ioe) {
					System.out.println("IOE closing ss " + ioe.getMessage());
				}
			}
		}
	}
	
	public void loadQuoteMeUniverse() {
		System.out.println("LOAD QUOTE ME UNIVERSE");
		try {
			File file = new File("QuoteMeUniverse.txt");
			
			// if file doesn't exist, create it and push an empty Data Manager to it
			if(!file.exists()) {
				file.createNewFile();
				DataManager newDataManager = new DataManager();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QuoteMeUniverse.txt"));
				oos.writeObject(newDataManager);
				oos.flush();
				oos.close();
			}
			
			// read the Data Manager
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("QuoteMeUniverse.txt"));
			dataManager = (DataManager)ois.readObject();
			System.out.println("DataManager in server null?: " + (dataManager==null));
			ois.close();
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); } catch (ClassNotFoundException cnfe) { System.out.println("ClassNotFoundException: " + cnfe.getMessage()); }
	}
	
	public void sendAppInstanceToAllClients(DataManager updatedDataManager) {
		this.dataManager = updatedDataManager;
		System.out.println("Sending app instance to all clients:");
		this.dataManager.printThis();	// Print to see what we are sending
		for (ServerClientCommunicator scc : sccVector) {
			scc.sendAppInstance(updatedDataManager);
		}
	}
	
	public void removeServerClientCommunicator(ServerClientCommunicator scc) {
		sccVector.remove(scc);
	}
	
	/* Will likely not need this method, but good to have.
	public void sendObjectToClients(ServerClientCommunicator comm, Object obj) {
		for (ServerClientCommunicator scc : sccVector) {
			if (!comm.equals(scc)) {
				scc.sendObject(obj);
			}
		}
	} */
	
	public void pushToTextFile() {
		
		System.out.println("In pushToTextFile()");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QuoteMeUniverse.txt"));
			oos.writeObject(dataManager);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); }
	}
	
	public static void main(String[] args) {
		new QuoteMeServer();
	}	
}