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
	private JPanel notPanel;
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

		notPanel = new JPanel();
		notPanel.setLayout(new BoxLayout(notPanel, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(notPanel);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}
	
	private void addNotifications() {
		// TODO sort notifications by time?, make it a s
		Vector<Notification> usersNotifications = new Vector<Notification>();
		Vector<User> allUsers = mainPanel.clientPanel.quoteMeClient.dataManager.getAllUsers();
		for (int i=0; i<allUsers.size(); i++) {
			System.out.println(mainPanel.clientPanel.getCurrentUser().getUserName() + " " + allUsers.get(i).getUserName());
			if (mainPanel.clientPanel.getCurrentUser().getUserName().equals(allUsers.get(i).getUserName())) {
				usersNotifications = allUsers.get(i).getNotifications();
				System.out.println("Success " + allUsers.get(i).getNotifications().size());
			}
		} 
	//	usersNotifications = mainPanel.clientPanel.getCurrentUser().getNotifications();
		
		if (usersNotifications.size() == 0) {
			notPanel.add(new QuoteMeLabel("No new notifications"));
		}
		else {
			System.out.println("\n\nABOUT TO ADD NOTIFICATION GUI\n\n");
		//	for (Notification notification : usersNotifications) {
			for (int i=usersNotifications.size()-1; i>=0; i--) {
				NotificationGUI notGUI = new NotificationGUI(mainPanel, usersNotifications.get(i));
				notPanel.add(notGUI);
			}
		}
		notPanel.add(Box.createGlue());
	}
	
	public void refresh() {
		addNotifications();
		scrollPane = new JScrollPane(notPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
	}
}
