package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import resources.Images;

public class ProfilePageGUI extends JPanel {
	public long serialVersionUID;
	private User user;
	private ImageIcon userImageIcon;
	private JLabel followersLabel, followedLabel;
//	private JTabbedPane profileQuoteTabs;
	private JPanel postedQuotesPanel;
	private ScrollPane postedQuotesPane;
//	private JPanel myQuotesPanel, favoriteQuotesPanel;
//	private ScrollPane myQuotesPane, postedQuotesPane, favoriteQuotesPane;
	private JButton followButton;
	
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
		// TODO initialize stuff correctly
//		userImageIcon = user.getProfilePicture();
		
		followersLabel = new JLabel("Followers: " + user.getUsersFollowingUs().size());
		followedLabel = new JLabel("Following: " + user.getUsersWeFollow().size());
	//	profileQuoteTabs = new JTabbedPane();
	//	myQuotesPanel = new JPanel();
		postedQuotesPanel = new JPanel();
	//	favoriteQuotesPanel = new JPanel();
	//	myQuotesPane = new ScrollPane();
		postedQuotesPane = new ScrollPane();
	//	favoriteQuotesPane = new ScrollPane();
		followButton = new JButton("Follow");
	}
	
	private void createGUI() {
		// TODO set layouts
		setLayout(new BorderLayout());
		
		
	/*	JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		northPanel.add(new JLabel(userImageIcon));
		northPanel.add(new JLabel(user.getFirstName() + " " + user.getLastName()));
		northPanel.add(new JLabel("Username: " + user.getUserName()));
		northPanel.add(followersLabel);
		northPanel.add(followedLabel);
		add(northPanel, BorderLayout.CENTER); */
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
