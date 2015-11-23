package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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

	private static final long serialVersionUID = 4423476724368792142L;
	private User user;
	private ImageIcon userImageIcon;
	private QuoteMeLabel followersLabel, followingLabel;
	private ScrollPane myQuotesScrollPane;
	private JPanel myQuotesPanel;
	private Vector<QuoteGUI> spokenQuotes;
	private JButton followButton;
	private JScrollPane scrollPane;	
	private MainPanel mainPanel;
	private User currUser;
	
	public ProfilePageGUI(MainPanel mainPanel, User user) {
		this.mainPanel = mainPanel;
		this.user = user;
		initializeVariables();
		createGUI();
		addEvents();
		// TODO set layouts and set visibility
	}
	
	private void initializeVariables() {
		currUser = mainPanel.clientPanel.getCurrentUser();
		
		if (user.getProfilePicture() != null) {
			Image image = user.getProfilePicture().getImage().getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height,  java.awt.Image.SCALE_SMOOTH);
			userImageIcon = new ImageIcon(image);
		} else {
			user.setProfilePicture(Images.getRandomAvatar());
			Image image = user.getProfilePicture().getImage().getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height,  java.awt.Image.SCALE_SMOOTH);
			userImageIcon = new ImageIcon(image);
		}
		followersLabel = new QuoteMeLabel(user.getUsersFollowingUs().size() + " followers", JLabel.CENTER);
		followersLabel.setFontSize(18);
		followingLabel = new QuoteMeLabel(user.getUsersWeFollow().size() + " following", JLabel.CENTER);
		followingLabel.setFontSize(18);
		myQuotesPanel = new JPanel();
		myQuotesScrollPane = new ScrollPane();
		
		spokenQuotes = new Vector<QuoteGUI>();
		
		HashMap<User, Quote> speakerToQuoteMap = this.mainPanel.clientPanel.quoteMeClient.dataManager.getSpeakerToQuoteMap();
		Iterator<Entry<User, Quote>> it = speakerToQuoteMap.entrySet().iterator();
		System.err.println("speakerToQuoteMap.size(): " + speakerToQuoteMap.size());
//	    iterate through map
		while (it.hasNext()) {
	    	QuoteGUI quoteGUI;
	    	Map.Entry<User, Quote> pair = (Map.Entry<User, Quote>)it.next();
//    		System.out.println("entry: " + pair.getKey().getUserName() + " " + pair.getValue().getText());
//	    	if this pair's Speaker is the profile Page user, create a QuoteGUI for it and add it to the vector
	    	if (((User)pair.getKey()).getUserName().equals(user.getUserName())) {
	    		System.out.println("***WE IN***");
	    		quoteGUI = new QuoteGUI(mainPanel, (Quote)pair.getValue());
	    		spokenQuotes.add(quoteGUI);
	    	}
//	        it.remove();
	    }
		System.err.println("spokenQuotes.size(): " + spokenQuotes.size());
		
		followButton = new QuoteMeButton(
				"Follow",
				ImageLibrary.getImage(Images.greenButton),
				15,100,25);
		
//		User currUser = mainPanel.clientPanel.getCurrentUser();
		
		if (user == currUser) {
			followButton.setEnabled(false);
		}
		else if (currUser != null && currUser.getUsersWeFollow().contains(user)) {
			System.err.println("We are already following this user.");
			followButton.setText("Unfollow");
		}
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
		statsPanel.add(followingLabel);
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());
		innerPanel.add(userInfoPanel, BorderLayout.WEST);
		innerPanel.add(followButton, BorderLayout.CENTER);
		northPanel.add(innerPanel, BorderLayout.NORTH);
		northPanel.add(statsPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
	
		addQuotes();
		scrollPane = new JScrollPane(myQuotesPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addQuotes() {
		myQuotesPanel.setLayout(new BoxLayout(myQuotesPanel, BoxLayout.Y_AXIS));
		
		for (QuoteGUI q : spokenQuotes) {
			myQuotesPanel.add(q);
			System.out.println("myQuotesPanel.add(q)");
		}
		
		if (spokenQuotes.size() != 0) {
			myQuotesScrollPane.add(myQuotesPanel);
			System.out.println("myQuotesPane.add(myQuotesPanel)");
		}
		
		myQuotesScrollPane.setVisible(true);
		myQuotesPanel.setVisible(true);
	}

	private void addEvents() {
		
		followButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				if (followButton.getText().equals("Follow")) {
					System.out.println(currUser.getUserName() + " just followed " + user.getUserName());
					
					currUser.followThisUser(user);
//					user.addUserFollowingUs(currUser);
					updateNumberFollowers();
					
					mainPanel.clientPanel.quoteMeClient.sendObject("follow," + currUser.getUserName() + "," + user.getUserName());
					followButton.setText("Unfollow");
					
					// Send a notification to current user
					NotificationGUI newFollowerNotification = new NotificationGUI(mainPanel, mainPanel.clientPanel.getCurrentUser(), "New Follower", new Date());
					user.addNotification(newFollowerNotification);
					
				}
				
				else if (followButton.getText().equals("Unfollow")) {
					System.out.println(currUser.getUserName() + " just unfollowed " + user.getUserName());
					
					currUser.unfollowThisUser(user);
//					user.removeUserFollowingUs(currUser);
					updateNumberFollowers();
					
					mainPanel.clientPanel.quoteMeClient.sendObject("unfollow," + currUser.getUserName() + "," + user.getUserName());
					followButton.setText("Follow");
				}
				
			}
		});
	}
	
	private void updateNumberFollowers() {
		followersLabel.setText(user.getUsersFollowingUs().size() + " followers");
	}
}
