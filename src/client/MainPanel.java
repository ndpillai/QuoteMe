package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resources.CustomListeners;

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
	private JButton searchButton, logoutButton;
	
	public ClientPanel clientPanel;

	private User currentuser;
	private JPanel currentPanelShown;
	
	public MainPanel(ClientPanel clientPanel) {
		this.clientPanel = clientPanel;
		
		//needs to be initialized
		currentuser = new User();
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		//approveQuotePanel = new ApproveQuoteGUI(this, new Quote()); // need to add a new quote to this
		feed = new FeedPageGUI(this);
		postQuote = new PostQuoteGUI(this);
		profilePage = new ProfilePageGUI(this, currentuser);
		notifications = new NotificationGUI(this);
//		quotePanel = new QuoteGUI(this, new Quote()); // TODO - ALL THIS TIME. THIS WAS THE SOURCE OF ALL OUR PAIN.
		writeQuotePanel = new WriteQuoteGUI(this);
		
		notificationButton = new JButton("Notifications");
		writeQuoteButton = new JButton("Write Quote");
		profilePageButton = new JButton("My Profile");
		feedPageButton = new JButton("Feed");
		
		searchButton = new JButton("Search");
		logoutButton = new JButton("Logout");
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		
		// NORTH Panel
		// will need an image for our logo, and a textfield for searching?
		JLabel northLabel = new JLabel("QuoteMe© ");
		searchField = new JTextField("Search QuoteMe");
		searchField.setPreferredSize(new Dimension(160, searchField.getPreferredSize().height));
		JPanel northPanel = new JPanel();
        northPanel.setBackground(new Color(204, 0, 0, 123));
		northPanel.add(northLabel);
		northPanel.add(searchField);
		northPanel.add(searchButton);
		northPanel.add(logoutButton);
		add(northPanel, BorderLayout.NORTH);

		add(feed, BorderLayout.CENTER);
		feed.setVisible(true);
		currentPanelShown = feed;
		
		// SOUTH Panel
		JPanel southPanel = new JPanel(new GridLayout(1,4));
        southPanel.setBackground(new Color(204, 0, 0, 123));

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
		searchField.addFocusListener(new CustomListeners.RemoveTextAdapter(searchField, "Search QuoteMe"));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!searchField.getText().equals("Search QuoteMe") && !searchField.getText().equals("")) {
					displaySearchResultsPage(searchField.getText());
					System.out.println("Clicked search button.");
					search(searchField.getText());
				}
				else {
					System.out.println("Wrong search format");
				}

			}
		});
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int n = JOptionPane.showConfirmDialog(
			            null,
			            "Are you sure you want to logout?",
			            "Confirm logout",
			            JOptionPane.YES_NO_OPTION);
				if(n == 0) {	// User wants to logout
					System.out.println("User confirms logout.");
					// TODO implement starting over of quoteme
				}
				else {
					System.out.println("User does not confirm logout.");
				}
			}
		});
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
				displayProfilePage(new User());
				System.out.println("Clicked Profile Page");
			}
		});
	}
	
	private void refreshComponents() {
		System.out.println("Refreshed components in main panel");
	}
	
	public void removeCurrentPanel() {
		this.remove(currentPanelShown);
	}
	
	public void addNewPanel(JPanel jp) {
		this.add(jp, BorderLayout.CENTER);
		currentPanelShown = jp;
		this.repaint();
		this.revalidate();
	}
	
	private void search(String text) {
		String[] searchTerms = text.split(" ");
		for (int i=0; i<searchTerms.length; i++) {
			System.out.println(searchTerms[i]);
		}
	}
	
	public void displaySearchResultsPage(String text) {
		JPanel jp = new JPanel();
		jp.add(new JLabel("Search results page"));
		jp.add(new JLabel(text));
		jp.setVisible(true);
		removeCurrentPanel();
		addNewPanel(jp);
	}
	
	public void displayFeedPage() {
		feed.setVisible(true);
		removeCurrentPanel();
		addNewPanel(feed);
	}
	
	public void displayWriteQuotePage() {
		writeQuotePanel.setVisible(true);
		removeCurrentPanel();
		addNewPanel(writeQuotePanel);
	}
	
	public void displayNotificationPage() {
		notifications.setVisible(true);
		removeCurrentPanel();
		addNewPanel(notifications);
	}
	
	public void displayProfilePage(User user) {
		profilePage = new ProfilePageGUI(this, user);
		profilePage.setVisible(true);
		removeCurrentPanel();
		addNewPanel(profilePage);
	}
	
	public void displayPage(JPanel page) {
		removeCurrentPanel();
		addNewPanel(page);
	}
}
