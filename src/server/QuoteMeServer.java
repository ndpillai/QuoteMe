package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import client.DataManager;

public class QuoteMeServer {
	
	private ServerSocket ss;
	private ServerListener serverListener;
	private DataManager dataManager;
	
	public QuoteMeServer()
	{
		try {
			ss = new ServerSocket(6789);
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
		loadQuoteMeUniverse(); // sets dataManager
		listenForConnections();
	}
	
	public void loadQuoteMeUniverse() {
		try {
			File file = new File("QuoteMeUniverse.txt");
			
			if(!file.exists()) { // if file doesn't exist, create it and push an empty Data Manager to it
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
			ois.close();
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); } catch (ClassNotFoundException cnfe) { System.out.println("ClassNotFoundException: " + cnfe.getMessage()); }
	}
	
	private void listenForConnections() {
		serverListener = new ServerListener(ss);
		serverListener.start();
	}
	
	public static void main(String[] args) {
		
	}
}
