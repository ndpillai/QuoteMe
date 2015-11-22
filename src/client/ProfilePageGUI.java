package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	}
	
	private void addEvents() {
		// TODO
		followButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Following this person");
				// TODO we need to disable this follow buttn if we are viewing our own profile?
				// TODO we also need to actually add this person as someone we follow, and vice versa
			}
		});
	}
}
