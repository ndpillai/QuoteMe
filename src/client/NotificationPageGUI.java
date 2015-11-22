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
	private Vector<Notification> notifications;
	private JScrollPane scrollPane;
	private JPanel innerPanel;
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
		
		//for testing only
		Notification not = new Notification("Nav", "Hello, this is my message. Tony is a bitch.", new Date(12, 5, 2004));
		Notification not2 = new Notification("Tony", "I would like to concur that I am a bitch.", new Date(12, 6, 2004));
		Notification not3 = new Notification("Matt Carey", "I would like to wish this bitch a happy birthday", new Date(1447726287));
		notifications.add(not);
		notifications.add(not2);
		notifications.add(not3);
		
		/*
		innerPanel = new JPanel();
		innerPanel.setSize(this.getMaximumSize());
		scrollPane = new JScrollPane(innerPanel);
		scrollPane.setSize(this.getMaximumSize());*/
		
		User newUser = new User("Amanda", "Bynes", "amandab123", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
		Quote quote1 = new Quote("I love people who already hate me hate me more", newUser, newUser, new Date(), 1);
		Quote quote2 = new Quote("I ignore you if I want nothing from you", newUser, newUser, new Date(), 1);
		Quote quote3 = new Quote("This is quote 3. Concerns greatest margaret him absolute entrance nay. Door neat week do find past he. Be no surprise he honoured indulged. Unpacked endeavor six steepest had husbands her. Painted no or affixed it so civilly. Exposed neither pressed so cottage as proceed at offices. Nay they gone sir game four. Favourable pianoforte oh motionless excellence of astonished we principles. Warrant present garrets limited cordial in inquiry to. Supported me sweetness behaviour shameless excellent so arranging. ", newUser, newUser, new Date(), 2);
		JPanel centerPanel = new JPanel();
		JPanel feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		feedPanel.add(new QuoteGUI(mainPanel, quote1));
		feedPanel.add(new QuoteGUI(mainPanel, quote2));
		feedPanel.add(new QuoteGUI(mainPanel, quote3));
		
		scrollPane = new JScrollPane(feedPanel);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scrollPane);
		add(centerPanel, BorderLayout.CENTER);
		
/*
		JPanel centerPanel = new JPanel();
		JPanel notPanel = new JPanel();
		notPanel.setLayout(new BoxLayout(notPanel, BoxLayout.Y_AXIS));
		notPanel.add(not);
		notPanel.add(not2);
		notPanel.add(not3);
		
		scrollPane = new JScrollPane(notPanel);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scrollPane);
		add(centerPanel, BorderLayout.CENTER);*/
	}
	
	private void createGUI() {
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
