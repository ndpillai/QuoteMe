package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

import resources.Images;

public class Test {

	private Calendar cal = Calendar.getInstance();
	private int i = 10;
	
	public static void main(String[] args) {
		new Test();
	}
	
	public Test() {
		try {
			
			DataManager newDataManager = new DataManager();
			
			User amanda = new User("Amanda", "Bynes", "amanda", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User tony = new User("Tony", "Elevathingal", "tony", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User ingrid = new User("Ingrid", "Wang", "ingrid", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User nav = new User("Nav", "Pillai", "nav", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User laura = new User("Laura", "Gouillon", "laura", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			
			amanda.followThisUser(tony);
			amanda.followThisUser(laura);
			amanda.followThisUser(nav);
			amanda.followThisUser(ingrid);
			tony.followThisUser(amanda);

			Quote quote1 = new Quote("I make people who already hate me hate me more", amanda, tony, incrementAndGetDate(), 0);
			Quote quote2 = new Quote("I ignore you if I want nothing from you", amanda, nav, incrementAndGetDate(), 1);
			Quote quote3 = new Quote("My name is Tony. Ingrid is quoting me.", tony, ingrid, incrementAndGetDate(), 2);
			Quote quote4 = new Quote("My name is Laura. Tony is quoting me.", laura, tony, incrementAndGetDate(), 2);
			Quote quote5 = new Quote("My name is Nav. Laura is quoting me.", nav, laura, incrementAndGetDate(), 1);
			Quote quote6 = new Quote("My name is Ingrid. Nav is quoting me.", ingrid, nav, incrementAndGetDate(), 0);
			
			for (int i=0; i<10; i++)
				quote1.incrementUpQuotes();
			for (int i=0; i<3; i++)
				quote2.incrementUpQuotes();
			for (int i=0; i<7; i++)
				quote3.incrementUpQuotes();
			for (int i=0; i<18; i++)
				quote4.incrementUpQuotes();
			
			newDataManager.addUser(amanda);
			newDataManager.addUser(tony);
			newDataManager.addUser(ingrid);
			newDataManager.addUser(nav);
			newDataManager.addUser(laura);
			newDataManager.addQuote(quote1);
			newDataManager.addQuote(quote2);
			newDataManager.addQuote(quote3);
			newDataManager.addQuote(quote4);
			newDataManager.addQuote(quote5);
			newDataManager.addQuote(quote6);
			
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QuoteMeUniverse.txt"));
			oos.writeObject(newDataManager);
			oos.flush();
			oos.close();
			
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); }
	}
	
	public Date incrementAndGetDate() {
		i++;
		cal.set(i + 1900, 5, 24);
		return cal.getTime();
	}
}
