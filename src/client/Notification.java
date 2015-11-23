package client;

import java.io.Serializable;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;

public class Notification implements Serializable {
	private static final long serialVersionUID = 1;
	private String senderName;
	private String message;
	private Date date;
	private String quoteString;

	public Notification(String senderName, String message, Date date, String quoteString) {
		this.senderName = senderName;
		this.message = message;
		this.date = date;
		this.quoteString = quoteString;
	}
	
	public String getSenderName() {
		return this.senderName;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getQuoteString() {
		return this.quoteString;
	}
}
