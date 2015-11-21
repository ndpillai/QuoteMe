package client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class DataManager implements Serializable {

	private static final long serialVersionUID = 5501158630513607312L;
	
	private Vector<User> allUsers;
	private HashMap<String, User> nameMap; // Usernames to their Users
	private HashMap<String, User> emailMap; // Emails to their users
	private HashMap<User, Quote> speakerToQuoteMap; // Speakers to their Quotes*
	private HashMap<User, Quote> posterToQuoteMap; // Posters to their Quotes

	//	public QuoteMeFrame qmf;
	
	{
		allUsers = new Vector<User>();
		nameMap = new HashMap<String, User>();
		emailMap = new HashMap<String, User>();
		speakerToQuoteMap = new HashMap<User, Quote>();
		posterToQuoteMap = new HashMap<User, Quote>();
	}
	
	public void addQuote(Quote quote) {
		speakerToQuoteMap.put(quote.getSpeaker(), quote);
		posterToQuoteMap.put(quote.getPoster(), quote);		
	}
	
	public void addUser(User user) {
		allUsers.add(user);
		nameMap.put(user.getUserName(), user);	
		emailMap.put(user.getEmail(), user);
	}
	
	// Getters:
	public Vector<User> getAllUsers() {
		return allUsers;
	}
	
	public HashMap<String, User> getNameMap() {
		return nameMap;
	}
	
	public HashMap<String, User> getEmailMap() {
		return emailMap;
	}
	
	public HashMap<User, Quote> getSpeakerToQuoteMap() {
		return speakerToQuoteMap;
	}
	
	public HashMap<User, Quote> getPosterToQuoteMap() {
		return posterToQuoteMap;
	}
	
	public boolean hasName(String name) {
		return this.nameMap.containsKey(name);
	}
	
	public boolean hasEmail(String email) {
		return this.emailMap.containsKey(email);
	}
	
}