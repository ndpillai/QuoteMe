package client;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Quote implements Serializable {

	private static final long serialVersionUID = 8065199498082554965L;
	
	private String text;
	private User speaker, poster;
	private Date datePosted;
	private int upQuotes;
	private int category; //0 = Meaningful, 1 = Funny, 2 = Sentimental
	public Vector<String> hasUpQuoted;
	private boolean isEmpty;
	
	
	public void printThis() {
		System.out.println("Q: '" + text + "'. Posted by " + poster.getUserName() + ". Spoken by " + speaker.getUserName() + ". Date: " + datePosted);
	}
	
	public Quote() {
		System.out.println("Empty Quote constructor");
		//isEmpty = true;
	}
	
	public Quote(String text, User speaker, User poster, Date datePosted, int category, int upQuotes) {
		this.text = text;
		this.speaker = speaker;
		this.poster = poster;
		this.datePosted = datePosted;
		this.upQuotes = upQuotes;
		this.category = category;
		hasUpQuoted = new Vector<String>();
		isEmpty = false;
	}
	
	public Quote(String text, User speaker, User poster, Date datePosted, int category) {
		this.text = text;
		this.speaker = speaker;
		this.poster = poster;
		this.datePosted = datePosted;
		this.upQuotes = 0;
		this.category = category;
		hasUpQuoted = new Vector<String>();
		isEmpty = false;
	}
	
	// For Sorting and Searching
	public String toString() {
		String quoteString = new String(
				"Quote: " + 
				this.text + " " + 
				this.speaker.toString() + " " +
				this.poster.toString() + " " + 
				this.category);
		return quoteString;
	}
	
	public String getText() {
		return text;
	}
	
	public User getSpeaker() {
		return speaker;
	}
	
	public User getPoster() {
		return poster;
	}
	
	public Date getDatePosted() {
		return datePosted;
	}
	
	public int getCategory() {
		return category;
	}
	
	public Integer getUpQuotes() {
		return upQuotes;
	}
	
	public void incrementUpQuotes() {
		upQuotes++;
	}
	
	public boolean hasQuoted(String username) {
		//System.out.println(username + " hasQuoted()?" + hasUpQuoted.contains(username));
		System.out.println("in hasQuoted() : " + hasUpQuoted.size());
		for (String usedUsername : hasUpQuoted) {
			if (usedUsername.equals(username)) {
				System.out.println("Already upquoted");
				return true;
			}
		}
		//hasUpQuoted.add(username);
		System.out.println("Hasn't upquoted yet");
		return false;
	}
	
	public void addUpQuoter(String username) {
		hasUpQuoted.add(username);
	}
	/*
	public boolean isEmpty() {
		return this.isEmpty();
	}*/
}
