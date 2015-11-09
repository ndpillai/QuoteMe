package client;

import java.util.Date;
import java.util.Vector;

public class Quote {
	private String text;
	private User speaker, poster;
	private Date datePosted;
	private Vector<String> categories;
	
	public Quote(String text, User speaker, User poster, Date datePosted, Vector<String> categories) {
		this.text = text;
		this.speaker = speaker;
		this.poster = poster;
		this.datePosted = datePosted;
		this.categories = categories;
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
	
	public Vector<String> getCategories() {
		return categories;
	}
}
