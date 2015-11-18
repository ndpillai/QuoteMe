package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Vector;

public class DataManager {

	private static final long serialVersionUID = 5501158630513607312L;
	
	private Vector<User> allUsers;
	private HashMap<String, User> nameMap; // Usernames to their Users
	private HashMap<User, Quote> speakerToQuoteMap; // Speakers to their Quotes
	private HashMap<User, Quote> posterToQuoteMap; // Posters to their Quotes

	//	public QuoteMeFrame qmf;
	
	 {
		allUsers = new Vector<User>();
		nameMap = new HashMap<String, User>();
		speakerToQuoteMap = new HashMap<User, Quote>();
		posterToQuoteMap = new HashMap<User, Quote>();
	}
	
	public void addQuote(Quote quote) {
		speakerToQuoteMap.put(quote.getSpeaker(), quote);
		posterToQuoteMap.put(quote.getPoster(), quote);
		
		this.refreshTextFile();
	}
	
	public void addUser(User user) {
		allUsers.add(user);
		nameMap.put(user.getUserName(), user);
		
		this.refreshTextFile();
	}
	
	// Getters:
	public Vector<User> getAllUsers() {
		return allUsers;
	}
	
	public HashMap<String, User> getNameMap() {
		return nameMap;
	}
	
	public HashMap<User, Quote> getSpeakerToQuoteMap() {
		return speakerToQuoteMap;
	}
	
	public HashMap<User, Quote> getPosterToQuoteMap() {
		return posterToQuoteMap;
	}
	
	// Text File Interaction:
	
	public void refreshTextFile() {		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DataManager.txt"));
			oos.writeObject(this); // takes the current state of the DataManager and pushes it to the client's text file
			oos.flush();
			oos.close();
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); }
	}
	
	public DataManager readDataManagerFromTextFile() {
		DataManager dataManager = new DataManager();
		
		try {
			File file = new File("DataManager.txt");
			
			if(!file.exists()) { // if file doesn't exist, create it and push an empty Data Manager to it
				file.createNewFile();
				
				DataManager newDataManager = new DataManager();
				
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DataManager.txt"));
				oos.writeObject(newDataManager);
				oos.flush();
				oos.close();
			}
			
			// read in the Data Manager
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DataManager.txt"));
			dataManager = (DataManager)ois.readObject();
			ois.close();
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); } catch (ClassNotFoundException cnfe) { System.out.println("ClassNotFoundException: " + cnfe.getMessage()); }
		
		return dataManager;
	}
}