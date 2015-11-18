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

	private static Vector<User> allUsers;
	private static HashMap<String, User> nameMap; // Usernames to their Users
	private static HashMap<User, Quote> speakerToQuoteMap; // Speakers to their Quotes
	private static HashMap<User, Quote> posterToQuoteMap; // Posters to their Quotes

	//	public static QuoteMeFrame qmf;
	
	static {
		allUsers = new Vector<User>();
		nameMap = new HashMap<String, User>();
		speakerToQuoteMap = new HashMap<User, Quote>();
		posterToQuoteMap = new HashMap<User, Quote>();
	}
	
	public static void addQuote(Quote quote) {
		speakerToQuoteMap.put(quote.getSpeaker(), quote);
		posterToQuoteMap.put(quote.getPoster(), quote);
	}
	
	public static void addUser(User user) {
		allUsers.add(user);
		nameMap.put(user.getUserName(), user);
	}
	
	public static void refresh() {
		
	}
	
	// Getters
	public static Vector<User> getAllUsers() {
		return allUsers;
	}
	
	public static HashMap<String, User> getNameMap() {
		return nameMap;
	}
	
	public static HashMap<User, Quote> getSpeakerToQuoteMap() {
		return speakerToQuoteMap;
	}
	
	public static HashMap<User, Quote> getPosterToQuoteMap() {
		return posterToQuoteMap;
	}
	
	public static void updateDataManagerTextFile() {		
		try {
			// pull table model
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DataManager.txt"));
			DataManager dataManager = (DataManager)ois.readObject();
			ois.close();
			
			// modify Data Manager somehow
//			**************
			
			// push modified Data Manager
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DataManager.txt"));
			oos.writeObject(dataManager);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); } catch (ClassNotFoundException cnfe) { System.out.println("ClassNotFoundException: " + cnfe.getMessage()); }
	}
	
	public static DataManager readDataManagerFromTextFile() {
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