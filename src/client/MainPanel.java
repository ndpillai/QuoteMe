package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {
	
	private ApproveQuoteGUI approveQuotePanel;
	private FeedPageGUI feed;
	private PostQuoteGUI postQuote;
	private ProfilePageGUI profilePage;
	private NotificationGUI notifications;
	private QuoteGUI quotePanel;
	private WriteQuoteGUI writeQuotePanel;
	
	private JTextField searchField;
	private JButton notificationButton, writeQuoteButton, profilePageButton, feedPageButton;
	
	public ClientPanel clientPanel;

	public User currentuser;
	
	public MainPanel(ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		//approveQuotePanel = new ApproveQuoteGUI(this, new Quote()); // need to add a new quote to this
		feed = new FeedPageGUI(this);
		postQuote = new PostQuoteGUI(this);
		profilePage = new ProfilePageGUI(this, new User());
		notifications = new NotificationGUI(this);
		quotePanel = new QuoteGUI(this, new Quote());
		writeQuotePanel = new WriteQuoteGUI(this);
		
		notificationButton = new JButton("Notifications");
		writeQuoteButton = new JButton("Write Quote");
		profilePageButton = new JButton("My Profile");
		feedPageButton = new JButton("Feed");
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		
		// NORTH Panel
		// will need an image for our logo, and a textfield for searching?
		JLabel northLabel = new JLabel("We need to add a logo here");
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));
		JPanel northPanel = new JPanel();
		northPanel.add(northLabel);
		northPanel.add(searchField);
		add(northPanel, BorderLayout.NORTH);
		
		add(writeQuotePanel, BorderLayout.CENTER);
		add(notifications, BorderLayout.CENTER);
		add(profilePage, BorderLayout.CENTER);
		add(feed, BorderLayout.CENTER);
		feed.setVisible(true);

		//add(new JLabel("Stuff goes here"), BorderLayout.CENTER);
		
		// SOUTH Panel
		JPanel southPanel = new JPanel(new GridLayout(1,4));
		southPanel.add(feedPageButton);
		southPanel.add(writeQuoteButton);
		southPanel.add(notificationButton);
		southPanel.add(profilePageButton);
		add(southPanel, BorderLayout.SOUTH);
		add(writeQuotePanel, BorderLayout.CENTER);
		writeQuotePanel.setVisible(false);
		add(feed, BorderLayout.CENTER);
	}
	
	private void addEvents() {
		feedPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displayFeedPage();
				System.out.println("Clicked Feed Button");
			}
		});
		writeQuoteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displayWriteQuotePage();		
				System.out.println("Clicked Write Quote Button");
			}
		});
		notificationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displayNotificationPage();
				System.out.println("Clicked Notifications Button");
			}
		});
		profilePageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//displayProfilePage(new User());
				System.out.println("Clicked Profile Page");
			}
		});
	}
	
	private void refreshComponents() {
		System.out.println("Refreshed components in main panel");
	}
	
	private void hideAll() {
		feed.setVisible(false);
		writeQuotePanel.setVisible(false);
		notifications.setVisible(false);
		profilePage.setVisible(false);
	}
	
	public void displayFeedPage() {
		//refreshComponents();
		hideAll();
		feed.setVisible(true);
	}
	
	public void displayWriteQuotePage() {
		hideAll();
		writeQuotePanel.setVisible(true);
	}
	
	public void displayNotificationPage() {
		hideAll();
		notifications.setVisible(true);
	}
	
	public void displayProfilePage(User user) {
		hideAll();
		profilePage.setVisible(true);
	}
}
