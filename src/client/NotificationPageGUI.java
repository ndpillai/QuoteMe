package client;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import custom.QuoteMeLabel;

public class NotificationPageGUI extends JPanel {
	private Vector<NotificationGUI> notifications;
	private JScrollPane scrollPane;
	private JPanel notPanel, centerPanel;
	private MainPanel mainPanel;

	public NotificationPageGUI(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
		this.setVisible(true);
	}
	
	private void initializeVariables() {
		notifications = new Vector<NotificationGUI>();
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		setSize(400, 30);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		northPanel.add(new QuoteMeLabel("Notifications"));
		add(northPanel, BorderLayout.NORTH);

		
		//for testing only
//		NotificationGUI not = new NotificationGUI(mainPanel, "Nav", "Hello, this is my message. Tony is a bitch.", new Date(12, 5, 2004));
//		NotificationGUI not2 = new NotificationGUI(mainPanel, "Tony", "I would like to concur that I am a bitch.", new Date(12, 6, 2004));
//		NotificationGUI not3 = new NotificationGUI(mainPanel, "Matt Carey", "I would like to wish this bitch a happy birthday", new Date(1447726287));
//		notifications.add(not);
//		notifications.add(not2);
//		notifications.add(not3);
		
		notPanel = new JPanel();
		notPanel.setLayout(new BoxLayout(notPanel, BoxLayout.Y_AXIS));
		
//		notPanel.add(not);
//		notPanel.add(not2);
//		notPanel.add(not3);
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}
	
	private void addNotifications() {
		// TODO sort notifications by time?, make it a s
		Vector<Notification> usersNotifications = mainPanel.clientPanel.getCurrentUser().getNotifications();
		if (usersNotifications.size() == 0) {
			notPanel.add(new QuoteMeLabel("No new notifications"));
		}
		else {
			System.out.println("\n\nABOUT TO ADD NOTIFICATION GUI\n\n");
			for (Notification notification : usersNotifications) {
				NotificationGUI notGUI = new NotificationGUI(mainPanel, notification);
				notPanel.add(notGUI);
			}
		}
		notPanel.add(Box.createGlue());
	}
	
	public void refresh() {
		// TODO add refresh functionality
		addNotifications();
		scrollPane = new JScrollPane(notPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
	}
}
