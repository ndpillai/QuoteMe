package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import custom.QuoteMeLabel;

public class NotificationPageGUI extends JPanel {
	private static final long serialVersionUID = -5389368101810559809L;
	private Vector<Notification> notifications;
	private JScrollPane scrollPane;
	private JPanel northPanel, notPanel, outerPanel;
	private QuoteMeLabel title;
	private MainPanel mainPanel;

	public NotificationPageGUI(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
		this.setVisible(true);
	}
	
	private void initializeVariables() {
		notifications = new Vector<Notification>();
		outerPanel = new JPanel();
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		setSize(400, 30);
		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		title = new QuoteMeLabel("Notifications");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		northPanel.add(title);
		add(northPanel, BorderLayout.NORTH);

		notPanel = new JPanel();
		notPanel.setLayout(new BoxLayout(notPanel, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(notPanel);
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void addEvents() {
		
	}
	
	private void addNotifications() {
		notifications = new Vector<Notification>();
		Vector<User> allUsers = mainPanel.clientPanel.quoteMeClient.dataManager.getAllUsers();
		for (int i=0; i<allUsers.size(); i++) {
			System.out.println(mainPanel.clientPanel.getCurrentUser().getUserName() + " " + allUsers.get(i).getUserName());
			if (mainPanel.clientPanel.getCurrentUser().getUserName().equals(allUsers.get(i).getUserName())) {
				notifications = allUsers.get(i).getNotifications();
				System.out.println("Success " + allUsers.get(i).getNotifications().size());
			}
		} 
	//	usersNotifications = mainPanel.clientPanel.getCurrentUser().getNotifications();
		
		if (notifications.size() == 0) {
			QuoteMeLabel noNotifs = new QuoteMeLabel("No new notifications");
			noNotifs.setAlignmentX(Component.CENTER_ALIGNMENT);
			notPanel.add(noNotifs);
		}
		else {
			System.out.println("\n\nABOUT TO ADD NOTIFICATION GUI\n\n");
		//	for (Notification notification : usersNotifications) {
			for (int i=notifications.size()-1; i>=0; i--) {
				NotificationGUI notGUI = new NotificationGUI(mainPanel,  notifications.get(i));
				notPanel.add(notGUI);
			}
		}
		notPanel.add(Box.createGlue());
	}
	
	public void refresh() {
		removeAll();
		northPanel.removeAll();
		northPanel.add(title);
		notPanel.removeAll();
		addNotifications();
		if (notifications.size() < 7) {
			northPanel.add(notPanel);
			add(northPanel, BorderLayout.NORTH);
		} else {
			scrollPane = new JScrollPane(notPanel);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			add(northPanel, BorderLayout.NORTH);
			add(scrollPane, BorderLayout.CENTER);
		}
	}
}
