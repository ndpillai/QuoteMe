package client;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NotificationGUI extends JPanel {
	private Vector<Notification> notifications;
	private JScrollPane scrollPane;
	private JPanel innerNotificationsPanel;

	public NotificationGUI() {
		initializeVariables();
		createGUI();
		addEvents();
		this.setVisible(true);
	}
	
	private void initializeVariables() {
		notifications = new Vector<Notification>();
		scrollPane = new JScrollPane();
		innerNotificationsPanel = new JPanel();
	}
	
	private void createGUI() {
		this.setLayout(new BorderLayout()); // TODO decide on which layouts to use
		innerNotificationsPanel.setLayout(new BorderLayout()); // TODO decide on layout
		// TODO finish styling elements
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}
	
	public void refresh() {
		// TODO add refresh functionality
	}
}
