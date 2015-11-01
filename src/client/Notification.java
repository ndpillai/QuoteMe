package client;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notification {
	private User sender;
	private String message;
	private Date notificationDate;
	private JPanel linkedPanel, infoPanel;
	private JLabel senderLabel, messageLabel, dateLabel;

	public Notification(JPanel linkedPanel) {
		this.linkedPanel = linkedPanel;
		initializeVariables();
		createGUI();
		addEvents();
		this.linkedPanel.setVisible(false);
		infoPanel.setVisible(false);
	}
	
	private void initializeVariables() {
		//sender = new User(); // TODO user constructor
		message = new String();
		notificationDate = new Date();
		linkedPanel = new JPanel();
		infoPanel = new JPanel();
	}
	
	private void createGUI() {
		linkedPanel.setLayout(new BorderLayout()); // TODO decide on which layouts to use
		infoPanel.setLayout(new BorderLayout());
		// TODO finish styling elements
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
		return notificationDate;
	}
}
