package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import resources.Images;

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
		
		//for testing only
		NotificationGUI not = new NotificationGUI(mainPanel, "Nav", "Hello, this is my message. Tony is a bitch.", new Date(12, 5, 2004));
		NotificationGUI not2 = new NotificationGUI(mainPanel, "Tony", "I would like to concur that I am a bitch.", new Date(12, 6, 2004));
		NotificationGUI not3 = new NotificationGUI(mainPanel, "Matt Carey", "I would like to wish this bitch a happy birthday", new Date(1447726287));
		notifications.add(not);
		notifications.add(not2);
		notifications.add(not3);
		
		centerPanel = new JPanel();
		notPanel = new JPanel();
		notPanel.setLayout(new BoxLayout(notPanel, BoxLayout.Y_AXIS));
		notPanel.add(not);
		notPanel.add(not2);
		notPanel.add(not3);
		
		scrollPane = new JScrollPane(notPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scrollPane);
		add(centerPanel, BorderLayout.CENTER);
		
		//innerPanel.setLayout(new GridLayout(notifications.size(), 1)); // TODO decide on layout
		// TODO finish styling elements
		
		/*
		for (int i=0; i<notifications.size(); i++) {
			if (i%2==0)
				notifications.get(i).setBackground(Color.blue);
			else
				notifications.get(i).setBackground(Color.green);
					
			innerPanel.add(notifications.get(i));
		}*/
		
		//add(scrollPane);
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}
	
	public void refresh() {
		// TODO add refresh functionality
	}
}
