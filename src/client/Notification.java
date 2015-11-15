package client;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notification extends JPanel {
	private User sender;
	private String user;
	private String message;
	private Date date;
	private JLabel senderLabel, messageLabel, dateLabel;

	//eventually also want to add in an action listener
	public Notification(User sender, String message, Date date) {
		this.sender = sender;
		this.message = message;
		this.date = date;
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	public Notification(String sender, String message, Date date) {
		this.user = sender;
		this.message = message;
		this.date = date;
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		
	//	senderLabel = new JLabel(sender.getFirstName()+" "+sender.getLastName());
		senderLabel = new JLabel(user);
		messageLabel = new JLabel(message);
		dateLabel = new JLabel(date.toString());
	}
	
	private void createGUI() {
		setLayout(new GridLayout(3,1));
		add(senderLabel);
		add(dateLabel);
		add(messageLabel);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}
	
	private User getSender() {
		return sender;
	}
	
	private String getMessage() {
		return message;
	}
	
	private Date getDate() {
		return date;
	}
}
