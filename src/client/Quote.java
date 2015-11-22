package client;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Quote implements Serializable {

	private static final long serialVersionUID = 8065199498082554965L;
	
	private String text;
	private User speaker, poster;
	private Date datePosted;
	private Integer upQuotes;
	private String category;
	
	// Probs need to remove
	public Quote() {
		System.out.println("Adding an empty quote");
	}
	
	public Quote(String text, User speaker, User poster, Date datePosted, String category) {
		this.text = text;
		this.speaker = speaker;
		this.poster = poster;
		this.datePosted = datePosted;
		this.upQuotes = 0;
		this.category = category;
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
	
	public String getCategory() {
		return category;
	}
	
	public Integer getUpQuotes() {
		return upQuotes;
	}
	
	public void incrementUpQuotes() {
		upQuotes++;
	}
}
