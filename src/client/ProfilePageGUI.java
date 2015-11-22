package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
import library.ImageLibrary;
import resources.Constants;
import resources.Images;

public class ProfilePageGUI extends JPanel {
	public long serialVersionUID;
	private User user;
	private ImageIcon userImageIcon;
	private QuoteMeLabel followersLabel, followedLabel;
	private ScrollPane myQuotesPane;
	private JPanel myQuotesPanel;
	private Vector<QuoteGUI> myQuotes;
	private JButton followButton;
	private JScrollPane scrollPane;	
	private MainPanel mainPanel;
	
	public ProfilePageGUI(MainPanel mainPanel, User user) {
		this.mainPanel = mainPanel;
		this.user = user;
		initializeVariables();
		createGUI();
		addEvents();
		// TODO set layouts and set visibility
	}
	
	private void initializeVariables() {
		if (user.getProfilePicture() != null) {
			Image image = user.getProfilePicture().getImage().getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height,  java.awt.Image.SCALE_SMOOTH);
			userImageIcon = new ImageIcon(image);
		} else {
			user.setProfilePicture(Images.getRandomAvatar());
			Image image = user.getProfilePicture().getImage().getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height,  java.awt.Image.SCALE_SMOOTH);
			userImageIcon = new ImageIcon(image);
		}
		followersLabel = new QuoteMeLabel(user.getUsersFollowingUs().size() + " following", JLabel.CENTER);
		followersLabel.setFontSize(18);
		followedLabel = new QuoteMeLabel(user.getUsersWeFollow().size() + " followers", JLabel.CENTER);
		followedLabel.setFontSize(18);
		myQuotesPanel = new JPanel();
		myQuotesPane = new ScrollPane();
		myQuotes = new Vector<QuoteGUI>();
		if (user.getMyQuotes() != null) {
			Vector<Quote> quotes = user.getMyQuotes();
			for (Quote q : quotes) {
				QuoteGUI quoteGUI = new QuoteGUI(mainPanel, q);
				myQuotes.add(quoteGUI);
			}
		}
		followButton = new QuoteMeButton(
				"Follow",
				ImageLibrary.getImage(Images.greenButton),
				15,100,25);
		if (user == mainPanel.clientPanel.getCurrentUser()) {
			followButton.setEnabled(false);
		} 
//		else if (mainPanel.clientPanel.getCurrentUser().getUsersWeFollow().contains(user)) {
//			followButton.setText("Unfollow");
//		}
	}
	
	private void createGUI() {
		// TODO set layouts
		
		setLayout(new BorderLayout());
	
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.X_AXIS));
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
		QuoteMeLabel userFullName = new QuoteMeLabel(user.getFirstName() + " " + user.getLastName());
		QuoteMeLabel usernameLabel = new QuoteMeLabel(user.getUserName());
		usernamePanel.add(userFullName);
		usernamePanel.add(usernameLabel);
		
		userInfoPanel.add(Box.createHorizontalStrut(10));
		userInfoPanel.add(new JLabel(userImageIcon));
		userInfoPanel.add(Box.createHorizontalStrut(10));
		userInfoPanel.add(usernamePanel);
		
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new GridLayout(1,2));
		statsPanel.add(followersLabel);
		statsPanel.add(followedLabel);
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());
		innerPanel.add(userInfoPanel, BorderLayout.WEST);
		innerPanel.add(followButton, BorderLayout.CENTER);
		northPanel.add(innerPanel, BorderLayout.NORTH);
		northPanel.add(statsPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);

		
		User newUser = new User("Amanda", "Bynes", "amandab123", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
		Quote quote1 = new Quote("I love people who already hate me hate me more", newUser, newUser, new Date(), 1);
		Quote quote2 = new Quote("I ignore you if I want nothing from you", newUser, newUser, new Date(), 1);
		Quote quote3 = new Quote("This is quote 3. Concerns greatest margaret him absolute entrance nay. Door neat week do find past he. Be no surprise he honoured indulged. Unpacked endeavor six steepest had husbands her. Painted no or affixed it so civilly. Exposed neither pressed so cottage as proceed at offices. Nay they gone sir game four. Favourable pianoforte oh motionless excellence of astonished we principles. Warrant present garrets limited cordial in inquiry to. Supported me sweetness behaviour shameless excellent so arranging. ", newUser, newUser, new Date(), 2);
		JPanel centerPanel = new JPanel();
		//feedPanel.add(northPanel);
	
		addQuotes();
		
		myQuotesPanel.add(new QuoteGUI(mainPanel, quote1));
		myQuotesPanel.add(new QuoteGUI(mainPanel, quote2));
		myQuotesPanel.add(new QuoteGUI(mainPanel, quote3));
		scrollPane = new JScrollPane(myQuotesPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scrollPane);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addQuotes() {
		myQuotesPanel.setLayout(new BoxLayout(myQuotesPanel, BoxLayout.Y_AXIS));
		for (QuoteGUI q : myQuotes) {
			myQuotesPanel.add(q);
		}
		/*
		if (myQuotes.size() != 0) {
			myQuotesPane.add(myQuotesPanel);
		}*/
	}

	private void addEvents() {
		
		User currUser = mainPanel.clientPanel.getCurrentUser();
				
		followButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				if (followButton.getText().equals("Follow")) {
					System.out.println(currUser.getUserName() + " just followed " + user.getUserName());
					currUser.followThisUser(user);
					user.addUserFollowingUs(currUser);
					updateNumberFollowers();
					followButton.setText("Unfollow");
				}
				
				else if (followButton.getText().equals("Unfollow")) {
					System.out.println(currUser.getUserName() + " just unfollowed " + user.getUserName());
					currUser.unfollowThisUser(user);
					user.removeUserFollowingUs(currUser);
					updateNumberFollowers();
					followButton.setText("Follow");
				}
				
			}
		});
	}
	
	private void updateNumberFollowers() {
		followersLabel.setText(user.getUsersFollowingUs().size() + " following");
	}
}
