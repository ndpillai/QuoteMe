package client;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NotificationGUI extends JPanel {
	private Vector<Notification> notifications;
	private JScrollPane scrollPane;
	private JPanel innerPanel;
	private MainPanel mainPanel;

	public NotificationGUI(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
		this.setVisible(true);
	}
	
	private void initializeVariables() {
		notifications = new Vector<Notification>();
		
		//for testing only
		Notification not = new Notification("Nav", "Hello, this is my message. Tony is a bitch.", new Date(12, 5, 2004));
		Notification not2 = new Notification("Tony", "I would like to concur that I am a bitch.", new Date(12, 6, 2004));
		notifications.add(not);
		notifications.add(not2);
		
		innerPanel = new JPanel();
		innerPanel.setSize(this.getMaximumSize());
		scrollPane = new JScrollPane(innerPanel);
		scrollPane.setSize(this.getMaximumSize());
	}
	
	private void createGUI() {
	//	this.setLayout(new BorderLayout()); // TODO decide on which layouts to use
		innerPanel.setLayout(new GridLayout(notifications.size(), 1)); // TODO decide on layout
		// TODO finish styling elements
		for (int i=0; i<notifications.size(); i++) {
			if (i%2==0)
				notifications.get(i).setBackground(Color.blue);
			else
				notifications.get(i).setBackground(Color.green);
					
			innerPanel.add(notifications.get(i));
		}
		
		add(scrollPane);
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}
	
	public void refresh() {
		// TODO add refresh functionality
	}
}
