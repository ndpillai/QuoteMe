package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import resources.Images;

public class Test {

	public static void main(String[] args) {
	
		try {
			
			DataManager newDataManager = new DataManager();
			
			User newUser = new User("Amanda", "Bynes", "amandab123", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			Quote quote1 = new Quote("I love people who already hate me hate me more", newUser, newUser, new Date(), 1);
			Quote quote2 = new Quote("I ignore you if I want nothing from you", newUser, newUser, new Date(), 1);
			Quote quote3 = new Quote("This is quote 3", newUser, newUser, new Date(), 2);
			
			newDataManager.addQuote(quote1);
			newDataManager.addQuote(quote2);
			newDataManager.addQuote(quote3);
			newDataManager.addUser(newUser);
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QuoteMeUniverse.txt"));
			oos.writeObject(newDataManager);
			oos.flush();
			oos.close();
			
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); }

	}
}
