package client;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;

public class User implements Serializable {

	private static final long serialVersionUID = -3635855323004697057L;
	
	private String firstName, lastName, userName, email, password;
	private Date memberSince;
	private ImageIcon profilePicture;
	private Vector<Quote> myQuotes, postedQuotes, favoriteQuotes;
	private Vector<Notification> myNotifications;
	private Vector<User> usersWeFollow, usersFollowingUs;
	private Vector<Quote> feedQuotesBuffer;
	
	public void printThis() {
		System.out.println("U: " + firstName + " " + lastName + " " + email + " " + userName + " " + password);
	}
	
	// Probs need to remove this
	public User() {
		//System.out.println("Fake user with fake information. You know who it is.");
		this.firstName = "Aaron";
		this.lastName = "Cote";
		this.userName = "ac";
		this.email = "gouillon@usc.edu";
		this.password = "1234";
		this.memberSince = new Date();
		myQuotes = new Vector<Quote>();
		postedQuotes = new Vector<Quote>();
		favoriteQuotes = new Vector<Quote>();
		myNotifications = new Vector<Notification>();
		usersWeFollow = new Vector<User>();
		usersFollowingUs = new Vector<User>();
		feedQuotesBuffer = new Vector<Quote>();	
	}
	
	public User(String firstName, String lastName, String userName, String email, String password, Date memberSince, ImageIcon avatar) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.memberSince = memberSince;
		this.profilePicture = avatar;
		myQuotes = new Vector<Quote>();
		postedQuotes = new Vector<Quote>();
		favoriteQuotes = new Vector<Quote>();
		myNotifications = new Vector<Notification>();
		usersWeFollow = new Vector<User>();
		usersFollowingUs = new Vector<User>();
		feedQuotesBuffer = new Vector<Quote>();
	}
	
	// For Sorting and Searching
	public String toString() {
		String userString = new String(
				"User: " + 
				this.firstName + " " +
				this.lastName + " " + 
				this.userName + " " +
				this.email + " ");
		return userString;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}
	
	public Date getMemberSince() {
		return memberSince;
	}
	
	public void setProfilePicture(ImageIcon profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public ImageIcon getProfilePicture() {
		return this.profilePicture;
	}
	
	public void addMyQuote(Quote q) {
		myQuotes.add(q);
	}
	
	public Vector<Quote> getMyQuotes() {
		return myQuotes;
	}
	
	public void addPostedQuote(Quote q) {
		postedQuotes.add(q);
	}
	
	public Vector<Quote> getPostedQuotes() {
		return postedQuotes;
	}
	
	public void addFavoriteQuote(Quote q) {
		favoriteQuotes.add(q);
	}
	
	public Vector<Quote> getFavoriteQuotes() {
		return favoriteQuotes;
	}
	
	public void addNotification(Notification n) {
		myNotifications.add(n);
	}
	
	public Vector<Notification> getNotifications() {
		return myNotifications;
	}
	
	public void addUserWeFollow(User u) {
		usersWeFollow.add(u);
	}
	
	public Vector<User> getUsersWeFollow() {
		return usersWeFollow;
	}
	
	public void addUserFollowingUs(User u) {
		usersFollowingUs.add(u);
	}
	
	public Vector<User> getUsersFollowingUs() {
		return usersFollowingUs;
	}
	
	public void addFeedQuoteToBuffer(Quote q) {
		feedQuotesBuffer.add(q);
	}
	
	public Vector<Quote> getFeedQuotesBuffer() {
		return feedQuotesBuffer;
	}
}
