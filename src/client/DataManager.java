package client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class DataManager implements Serializable {

	private static final long serialVersionUID = 5501158630513607312L;
	
	private Vector<User> allUsers;
	private Vector<Quote> allQuotes;
	private HashMap<String, User> nameMap; // Usernames to their Users
	private HashMap<String, User> emailMap; // Emails to their users
	private HashMap<String, Vector<Quote> > speakerToQuoteMap; // Speakers to their Quotes*
	private HashMap<String, Vector<Quote> > posterToQuoteMap; // Posters to their Quotes
	private HashMap<User,Quote> speakerUserMap;

	//	public QuoteMeFrame qmf;
	
	public void printThis() {
		System.out.println('\n'+ "***DATA MANAGER CONTENTS***" + '\n');
		
		System.out.println("Users:");
		for (int i = 0; i < allUsers.size(); i++) {
			allUsers.get(i).printThis();
		}
		System.out.println("allUsers size " + allUsers.size());
		
		System.out.println('\n' + "Quotes:");
		for (int i = 0; i < allQuotes.size(); i++) {
			allQuotes.get(i).printThis();
		}
		System.out.println("allQuotes size " + allQuotes.size());

	}
	
	public DataManager() {
		allUsers = new Vector<User>();
		allQuotes = new Vector<Quote>();
		
		nameMap = new HashMap<String, User>();
		emailMap = new HashMap<String, User>();
		speakerToQuoteMap = new HashMap<String, Vector<Quote> >();
		posterToQuoteMap = new HashMap<String, Vector<Quote> >();
		speakerUserMap = new HashMap<User, Quote>();
	}
	
	public void addQuote(Quote quote) {
		allQuotes.add(quote);
		
		if (!speakerToQuoteMap.containsKey(quote.getSpeaker().getUserName()))
			speakerToQuoteMap.put(quote.getSpeaker().getUserName(), new Vector<Quote>());
		speakerToQuoteMap.get(quote.getSpeaker().getUserName()).add(quote);
		
		if (!posterToQuoteMap.containsKey(quote.getPoster().getUserName()))
			posterToQuoteMap.put(quote.getPoster().getUserName(), new Vector<Quote>());
		posterToQuoteMap.get(quote.getPoster().getUserName()).add(quote);	
		
		speakerUserMap.put(quote.getSpeaker(), quote);
	}
	
	public void addUser(User user) {
		allUsers.add(user);
		nameMap.put(user.getUserName(), user);	
		emailMap.put(user.getEmail(), user);
	}
	
	// Getters:
	public Vector<Quote> getAllQuotes() {
		return allQuotes;
	}
	
	public Vector<User> getAllUsers() {
		return allUsers;
	}
	
	public HashMap<String, User> getNameMap() {
		return nameMap;
	}
	
	public HashMap<String, User> getEmailMap() {
		return emailMap;
	}
	
	public HashMap<String, Vector<Quote> > getSpeakerToQuoteMap() {
		return speakerToQuoteMap;
	}
	
	public HashMap<String, Vector<Quote> > getPosterToQuoteMap() {
		return posterToQuoteMap;
	}
	
	public HashMap<User, Quote> getSpeakerUserMap() {
		System.out.println("Hello");
		return speakerUserMap;
	}
	
	public boolean hasName(String name) {
		return this.nameMap.containsKey(name);
	}
	
	public boolean hasEmail(String email) {
		return this.emailMap.containsKey(email);
	}
	
	public User getUserFromEmail(String email) {	// For checking forgot password functionality
		if (this.emailMap.containsKey(email)) {
			return emailMap.get(email);
		}
		else {
			return null;
		}
	}
	
	public User getUserFromUserName(String username) {
		if (this.nameMap.containsKey(username)) {
			return nameMap.get(username);
		}
		else {
			return null;
		}
	}
	
}