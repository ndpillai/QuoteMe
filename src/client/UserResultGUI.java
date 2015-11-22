package client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import custom.QuoteMeLabel;
import resources.Constants;
import resources.Images;

public class UserResultGUI extends JPanel {

	private static final long serialVersionUID = 8501394373973058352L;
	
	private User thisUser;
	private QuoteMeLabel fullNameLabel, usernameLabel;
	private ImageIcon profilePicture;
	
	private MainPanel mainPanel;
	
	public UserResultGUI(MainPanel mainPanel, User thisUser) {
		this.thisUser = thisUser;
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		fullNameLabel = new QuoteMeLabel(thisUser.getFirstName() + " " + thisUser.getLastName());
		usernameLabel = new QuoteMeLabel(thisUser.getUserName());
		fullNameLabel.setFontSize(16);
		usernameLabel.setFontSize(16);
		profilePicture = thisUser.getProfilePicture();
		if (profilePicture != null) {
			Image image = profilePicture.getImage().getScaledInstance(Constants.AvatarButtonWidth.width, Constants.AvatarButtonWidth.height,  java.awt.Image.SCALE_SMOOTH);
			profilePicture = new ImageIcon(image);
		} else {
			Image image = Images.getRandomAvatar().getImage().getScaledInstance(Constants.AvatarButtonWidth.width, Constants.AvatarButtonWidth.height,  java.awt.Image.SCALE_SMOOTH);
			profilePicture = new ImageIcon(image);
		}
		//setPreferredSize(new Dimension(400, Constants.AvatarButtonSize.height));
	}
	
	private void createGUI() {
		JPanel panel = new JPanel();
		
		JLabel profilePictureLabel = new JLabel(profilePicture);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
		usernamePanel.add(fullNameLabel);
		usernamePanel.add(usernameLabel);
		
		panel.add(Box.createHorizontalStrut(10));
		panel.add(profilePictureLabel);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(usernamePanel);
		
		setLayout(new BorderLayout());
		add(panel);
	}
	
	private void addEvents() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked UserResultGUI");
				goToUser(thisUser);
			}
		});
	}
	
	private void goToUser(User user) {
		ProfilePageGUI userPage = new ProfilePageGUI(mainPanel, user);
		mainPanel.displayPage(userPage); // TODO - Figure out moving to panel
	}
}
