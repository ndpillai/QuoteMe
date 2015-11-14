package client;

import java.awt.BorderLayout;
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
	private WriteQuotePanel writeQuotePanel;
	
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
		approveQuotePanel = new ApproveQuoteGUI(this);
		feed = new FeedPageGUI(this);
		postQuote = new PostQuoteGUI(this);
		profilePage = new ProfilePageGUI(this);
		notifications = new NotificationGUI(this);
		quotePanel = new QuoteGUI(this);
		writeQuotePanel = new WriteQuotePanel(this);
		
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
		JPanel northPanel = new JPanel();
		northPanel.add(northLabel);
		northPanel.add(searchField);
		add(northPanel, BorderLayout.NORTH);
		
		// SOUTH Panel
		JPanel southPanel = new JPanel(new GridLayout(4,1));
		southPanel.add(feedPageButton);
		southPanel.add(writeQuoteButton);
		southPanel.add(notificationButton);
		southPanel.add(profilePageButton);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		feedPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displayFeedPage();
			}
		});
		writeQuoteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displayWriteQuotePage();			
			}
		});
		notificationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				displayNotificationPage();
			}
		});
		profilePageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//displayProfilePage();
			}
		});
	}
	
	private void refreshComponents() {
		System.out.println("Refreshed components in main panel");
	}
	
	public void displayFeedPage() {
		//refreshComponents();
		add(feed, BorderLayout.CENTER);
	}
	
	public void displayWriteQuotePage() {
		
	}
	
	public void displayNotificationPage() {
		
	}
	
	public void displayProfilePage(User user) {
		
	}
}
