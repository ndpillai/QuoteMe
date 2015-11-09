package client;

import java.util.HashMap;
import java.util.Vector;

public class DataManager {

	private Vector<User> existingUsers;
	private User currentUser;
	private int portNumber;
	private HashMap<String, User> nameMap;
	private HashMap<String, Quote> quoteMap;
	private QuoteMeFrame qmf;
	
	public DataManager(QuoteMeFrame qmf) {
		existingUsers = new Vector<User>();
		currentUser = null;
		portNumber = 0;
		nameMap = new HashMap<String, User>();
		quoteMap = new HashMap<String, Quote>();
		this.qmf = qmf;
	}
	
	public Vector<User> getExistingUsers() {
		return existingUsers;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void addQuote(Quote q) {
		// TODO
	}
	
	public void refresh() {
		// TODO
	}
	
	// To allow different GUI's to call data manager to switch pages
	public void displayTab(String tabName) {
		qmf.displayTab(tabName);
	}
}
