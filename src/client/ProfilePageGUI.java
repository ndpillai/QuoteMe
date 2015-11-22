package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
import library.FontLibrary;
import library.ImageLibrary;
import resources.Images;

public class ProfilePageGUI extends JPanel {
	public long serialVersionUID;
	private User user;
	private ImageIcon userImageIcon;
	private QuoteMeLabel followersLabel, followedLabel;
	private ScrollPane myQuotesPane;
	private JPanel myQuotesPanel;
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
		userImageIcon = user.getProfilePicture();
		followersLabel = new QuoteMeLabel("Followers: " + user.getUsersFollowingUs().size());
		followedLabel = new QuoteMeLabel("Following: " + user.getUsersWeFollow().size());
		myQuotesPanel = new JPanel();
		myQuotesPane = new ScrollPane();
		followButton = new QuoteMeButton(
				"Follow",
				ImageLibrary.getImage(Images.greenButton),
				15,100,25);
		if (user == mainPanel.clientPanel.getCurrentUser()) {
			followButton.setEnabled(false);
			followButton.setForeground(Color.GRAY);
		} 
//		else if (mainPanel.clientPanel.getCurrentUser().getUsersWeFollow().contains(user)) {
//			followButton.setText("Unfollow");
//		}
	}
	
	private void createGUI() {
		// TODO set layouts
		/*
		setLayout(new BorderLayout());
	
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setLayout(new BorderLayout());
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
		QuoteMeLabel userFullName = new QuoteMeLabel(user.getFirstName() + " " + user.getLastName());
		QuoteMeLabel usernameLabel = new QuoteMeLabel("Username: " + user.getUserName());
		usernamePanel.add(userFullName);
		usernamePanel.add(usernameLabel);
		
		userInfoPanel.add(new JLabel(userImageIcon), BorderLayout.WEST);
		userInfoPanel.add(usernamePanel, BorderLayout.CENTER);
		
		northPanel.add(userInfoPanel, BorderLayout.NORTH);
		northPanel.add(followersLabel, BorderLayout.WEST);
		northPanel.add(followedLabel, BorderLayout.EAST);
		add(northPanel, BorderLayout.CENTER);
		*/
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
					followButton.setText("Unfollow");
				}
				
				else if (followButton.getText().equals("Unfollow")) {
					System.out.println(currUser.getUserName() + " just unfollowed " + user.getUserName());
					currUser.unfollowThisUser(user);
					user.removeUserFollowingUs(currUser);
					followButton.setText("Follow");
				}
				
			}
		});
	}
}
