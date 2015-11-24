package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
			
			User amanda = new User("Amanda", "Bynes", "amanda", "ingridwa@usc.edu", "123", new Date(), Images.getRandomAvatar());
			User tony = new User("Tony", "Elevathingal", "tony", "elevathi@usc.edu", "123", new Date(), Images.getRandomAvatar());
			User ingrid = new User("Ingrid", "Wang", "ingrid", "lgouillon120@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User nav = new User("Nav", "Pillai", "nav", "navneeth.pillai@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User laura = new User("Laura", "Gouillon", "laura", "lgouillon@yahoo.com", "123", new Date(), Images.getRandomAvatar());
			User michael = new User("Michael", "Scott", "michael", "lgduckie@gmail.com", "123", new Date(), Images.getRandomAvatar());
			User nikias = new User("Max", "Nikias", "nikias", "gouillon@usc.edu", "123", new Date(), Images.getRandomAvatar());
			
			// Add users
			newDataManager.addUser(amanda);
			newDataManager.addUser(tony);
			newDataManager.addUser(ingrid);
			newDataManager.addUser(nav);
			newDataManager.addUser(laura);
			newDataManager.addUser(michael);
			newDataManager.addUser(nikias);
			
			//Follow
			ingrid.followThisUser(tony);
			nav.followThisUser(tony);
			laura.followThisUser(tony);
			amanda.followThisUser(tony);
			
			tony.followThisUser(amanda);
			laura.followThisUser(amanda);
			
			nav.followThisUser(ingrid);
			laura.followThisUser(ingrid);
			tony.followThisUser(ingrid);
			amanda.followThisUser(ingrid);
			
			nikias.followThisUser(nav);
			laura.followThisUser(nav);
			tony.followThisUser(nav);
			ingrid.followThisUser(nav);
			amanda.followThisUser(nav);
			
			amanda.followThisUser(laura);
			michael.followThisUser(laura);
			ingrid.followThisUser(laura);
			nav.followThisUser(laura);
			tony.followThisUser(laura);
			
			nav.followThisUser(michael);
			laura.followThisUser(michael);
			tony.followThisUser(michael);
			amanda.followThisUser(michael);
			
			nav.followThisUser(nikias);
			laura.followThisUser(nikias);
			tony.followThisUser(nikias);
			ingrid.followThisUser(nikias);	
			amanda.followThisUser(nikias);
			
			// You can add the number of upQuotes a quote starts with at the end of the constructor, after category
			// e.g. Quote cote1 = new Quote("Math.", cote, nav, incrementAndGetDate(), 1, 50000000000000); // Lots of upquotes
			// But don't do that because the gui will probably be messed up with too many upQuotes. That is all.

			//26 total quotes
			// Amanda 2
			newDataManager.addQuote(new Quote("In order to succeed, we must first believe that we can.", amanda, tony, incrementAndGetDate(), 0, 10));
			newDataManager.addQuote(new Quote("The important thing is not to stop questioning. Curiosity "
					+ "has its own reason for existing.", amanda, laura, incrementAndGetDate(), 0, 11));
			
			// Tony 3
			newDataManager.addQuote(new Quote("Dreams feel real while we’re in them. It’s only when we wake up that we realize" 
					+" something was actually strange.", tony, ingrid, incrementAndGetDate(), 2, 5));
			newDataManager.addQuote(new Quote("I love lamp.", tony, laura, incrementAndGetDate(), 2, 7));
			newDataManager.addQuote(new Quote("Look, I know 'all opinions are valid' or whatever, but I think yours might " 
					+ "actually be the first one that's wrong.", tony, nav, incrementAndGetDate(), 1, 7));


			// Ingrid 4
			newDataManager.addQuote(new Quote("I'm gonna make him an offer he can't refuse.", ingrid, laura, incrementAndGetDate(), 0, 3));
			newDataManager.addQuote(new Quote("I am Groot.", ingrid, tony, incrementAndGetDate(), 1, 6));
			newDataManager.addQuote(new Quote("It’s leviooosa, not leviosaaa", ingrid, nav, incrementAndGetDate(), 1, 4));
			newDataManager.addQuote(new Quote("Use the Force, Frodo.", ingrid, amanda, incrementAndGetDate(), 0, 7));

			
			// Nav 3
			newDataManager.addQuote(new Quote("Clear eyes, full hearts, can’t lose.", nav, laura, incrementAndGetDate(), 0, 2));
			newDataManager.addQuote(new Quote("Get in loser, we're going shopping.", nav, ingrid, incrementAndGetDate(), 1, 8));
			newDataManager.addQuote(new Quote("I've already hit rock bottom, now I'm just setting up camp.", nav, tony, incrementAndGetDate(), 1, 12));

			
			// Laura 3
			newDataManager.addQuote(new Quote("Fish and chips for days. So yummy. Or rather scallops.", laura, tony, incrementAndGetDate(), 1, 8));
			newDataManager.addQuote(new Quote("I don't know what I'm doing, but actually I think I do.", laura, nav, incrementAndGetDate(), 0, 3));
			newDataManager.addQuote(new Quote("The word paren is so weird. I don't understand it.", laura, ingrid, incrementAndGetDate(), 1, 4));

			// Michael 8
			newDataManager.addQuote(new Quote("I have flaws. What are they? "
					+ "I sing in the shower. Sometimes I spend too much time volunteering. "
					+ "Occassionally I hit somebody with my car. So sue me", michael, laura, incrementAndGetDate(), 1, 18));
			newDataManager.addQuote(new Quote("The worst thing about prison was - was the dementors.", michael, ingrid, incrementAndGetDate(), 1, 4));
			newDataManager.addQuote(new Quote("You know what they say. 'Fool me once, strike one. But fool me twice"
					+"...strike three.", michael, nav, incrementAndGetDate(), 1, 11));
			newDataManager.addQuote(new Quote("Would I rather be feared or loved? Easy, both. I want people to be afraid"
					+ " of how much they love me.", michael, nav, incrementAndGetDate(), 1, 9));
			newDataManager.addQuote(new Quote("You wanna hear a lie? I... think you're great. You're my best friend.", michael, tony, incrementAndGetDate(), 2, 23));
			newDataManager.addQuote(new Quote("And I knew exactly what to do. But in a much more real sense, I"
					+" had no idea what to do.", michael, tony, incrementAndGetDate(), 1, 3));
			newDataManager.addQuote(new Quote("I'm always Beyonce.", michael, laura, incrementAndGetDate(), 1, 31));
			newDataManager.addQuote(new Quote("Boom, roasted.", michael, ingrid, incrementAndGetDate(), 1, 17));

			// Nikias 3
			newDataManager.addQuote(new Quote("Fight on.", nikias, ingrid, incrementAndGetDate(), 1, 36));
			newDataManager.addQuote(new Quote("Beat the Bruins!", nikias, ingrid, incrementAndGetDate(), 1, 34));
			newDataManager.addQuote(new Quote("Chip Kelly to USC?", nikias, nav, incrementAndGetDate(), 1, 41));
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QuoteMeUniverse.txt"));
			oos.writeObject(newDataManager);
			oos.flush();
			oos.close();
			
		} catch (FileNotFoundException fnfe) { System.out.println("FileNotFoundException: " + fnfe.getMessage()); } catch (IOException ioe) { System.out.println("IOException: " + ioe.getMessage()); }
	}
	
	public Date incrementAndGetDate() {
		Random rand = new Random();
		int year = rand.nextInt((2015 - 2000) + 1) + 2000;
		int month = rand.nextInt((11-0) + 1) + 0;
		int day = rand.nextInt((28 - 1) + 1) + 1;

		
		cal.set(year, month, day);
		return cal.getTime();
	}
}
