package client;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserResultGUI extends JPanel {

	private static final long serialVersionUID = 8501394373973058352L;
	
	private User thisUser;
	private JLabel speakerNameLabel, usernameLabel;
	private ImageIcon profilePicture;
	
	private MainPanel mainPanel;
	
	public UserResultGUI(MainPanel mainPanel, User thisUser) {
		this.thisUser = thisUser;
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		speakerNameLabel = new JLabel(thisUser.getFirstName() + " " + thisUser.getLastName());
		usernameLabel = new JLabel(thisUser.getUserName());
		profilePicture = thisUser.getProfilePicture();
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		setSize(this.getMaximumSize().width - 50, 50);
		
		JButton profilePictureButton = new JButton(profilePicture);
		
		this.add(profilePictureButton, BorderLayout.WEST);
		this.add(speakerNameLabel, BorderLayout.CENTER);
		this.add(usernameLabel, BorderLayout.EAST);
		
	}
	
	private void addEvents() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked UserResultGUI");
				goToUser(thisUser);
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("Pressed UserResultGUI");
				goToUser(thisUser);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	private void goToUser(User user) {
		ProfilePageGUI userPage = new ProfilePageGUI(mainPanel, user);
		mainPanel.displayPage(userPage); // TODO - Figure out moving to panel
	}
}
