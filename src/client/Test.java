package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import resources.Images;

public class Test {
	
	private Vector<Quote> amandaQuotes, tonyQuotes, ingridQuotes, navQuotes, lauraQuotes, michaelQuotes, nikiasQuotes;

	private Calendar cal = Calendar.getInstance();
	private int i = 10;
	
	public static void main(String[] args) {
		new Test();
	}
	
	public Test() {
		try {
			
			DataManager newDataManager = new DataManager();
			
			amandaQuotes = new Vector<Quote>();
			tonyQuotes = new Vector<Quote>();
			ingridQuotes = new Vector<Quote>();
			navQuotes = new Vector<Quote>();
			lauraQuotes = new Vector<Quote>();
			michaelQuotes = new Vector<Quote>();
			nikiasQuotes = new Vector<Quote>();
			
			User amanda = new User("Amanda", "Bynes", "amanda", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User tony = new User("Tony", "Elevathingal", "tony", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User ingrid = new User("Ingrid", "Wang", "ingrid", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User nav = new User("Nav", "Pillai", "nav", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User laura = new User("Laura", "Gouillon", "laura", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User michael = new User("Michael", "Scott", "michael", "gouillon@usc.edu", "123", new Date(), Images.getRandomAvatar());
			User nikias = new User("Max", "Nikias", "nikias", "gouillon@usc.edu", "123", new Date(), Images.getRandomAvatar());
			
			amanda.followThisUser(tony);
			amanda.followThisUser(laura);
			amanda.followThisUser(nav);
			amanda.followThisUser(ingrid);
			tony.followThisUser(amanda);
			michael.followThisUser(laura);
			laura.followThisUser(michael);
			
			// You can add the number of upQuotes a quote starts with at the end of the constructor, after category
			// e.g. Quote cote1 = new Quote("Math.", cote, nav, incrementAndGetDate(), 1, 50000000000000); // Lots of upquotes
			// But don't do that because the gui will probably be messed up with too many upQuotes. That is all.

			// Amanda 
			Quote amanda1 = new Quote("I make people who already hate me hate me more", amanda, tony, incrementAndGetDate(), 0, 10);
			Quote amanda2 = new Quote("I ignore you if I want nothing from you", amanda, nav, incrementAndGetDate(), 1);
			newDataManager.addQuote(amanda1);
			newDataManager.addQuote(amanda2);
			
			// Tony
			Quote tony1 = new Quote("My name is Tony. Ingrid is quoting me.", tony, ingrid, incrementAndGetDate(), 2);
			newDataManager.addQuote(tony1);

			// Ingrid
			Quote ingrid1 = new Quote("My name is Ingrid. Nav is quoting me.", ingrid, nav, incrementAndGetDate(), 0);
			Quote ingrid2 = new Quote("Test test test test.", ingrid, tony, incrementAndGetDate(), 0);
			Quote ingrid3 = new Quote("QuoteMe on that.", ingrid, nav, incrementAndGetDate(), 0);
			Quote ingrid4 = new Quote("Please work. PLEASE.", ingrid, amanda, incrementAndGetDate(), 0);
			newDataManager.addQuote(ingrid1);
			newDataManager.addQuote(ingrid2);
			newDataManager.addQuote(ingrid2);
			newDataManager.addQuote(ingrid4);
			
			// Nav
			Quote nav1 = new Quote("My name is Nav. Laura is quoting me.", nav, laura, incrementAndGetDate(), 1);
			newDataManager.addQuote(nav1);
			
			// Laura
			Quote laura1 = new Quote("My name is Laura. Tony is quoting me.", laura, tony, incrementAndGetDate(), 2);
			Quote laura2 = new Quote("Fish and chips for days. So yummy. Or rather scallops.", laura, tony, incrementAndGetDate(), 2);
			Quote laura3 = new Quote("I don't know what I'm doing, but actually I think I do.", laura, tony, incrementAndGetDate(), 2);
			Quote laura4 = new Quote("The word paren is so weird. I don't understand it.", laura, tony, incrementAndGetDate(), 2);
			newDataManager.addQuote(laura1);
			newDataManager.addQuote(laura2);
			newDataManager.addQuote(laura3);
			newDataManager.addQuote(laura4);

			// Michael
			Quote michael1 = new Quote("I have flaws. What are they? "
					+ "I sing in the shower. Sometimes I spend too much time volunteering. "
					+ "Occassionally I hit somebody with my car.", michael, laura, incrementAndGetDate(), 1);
			newDataManager.addQuote(michael1);

			// Nikias
			Quote nikias1 = new Quote("Fight on.", nikias, ingrid, incrementAndGetDate(), 1, 22);
			newDataManager.addQuote(nikias1);
			
			// Add users
			newDataManager.addUser(amanda);
			newDataManager.addUser(tony);
			newDataManager.addUser(ingrid);
			newDataManager.addUser(nav);
			newDataManager.addUser(laura);
			newDataManager.addUser(michael);
			newDataManager.addUser(nikias);

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
