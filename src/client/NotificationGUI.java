package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
import library.FontLibrary;
import library.ImageLibrary;
import resources.Constants;
import resources.Images;

public class NotificationGUI extends JPanel {
	private MainPanel mainPanel;
	private User sender;
	private String username;
	private ImageIcon senderAvatar;
	private JButton senderAvatarButton;
	private String message;
	private Date date;
	private JLabel senderLabel, messageLabel, dateLabel;
	
	private boolean read;
	

	//eventually also want to add in an action listener
	public NotificationGUI(MainPanel mainPanel, User sender, String message, Date date) {
		this.mainPanel = mainPanel;
		this.sender = sender;
		this.senderAvatar = sender.getProfilePicture();
		this.senderAvatarButton = new JButton(senderAvatar);
		this.message = message;
		this.date = date;
		this.username = sender.getUserName();
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	
	public NotificationGUI(MainPanel mainPanel, String sender, String message, Date date) {
		this.mainPanel = mainPanel;
		this.username = sender;
		this.message = message;
		this.date = date;
		this.senderAvatar = new ImageIcon(Images.parrotAvatarBluePixellated);
		this.senderAvatarButton = new JButton(senderAvatar);
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		senderLabel = new JLabel(username);
		messageLabel = new JLabel(message);
		dateLabel = new JLabel(date.toString());
		
		read = false;
		

		// Scale the sender's avatar
		//senderAvatar = sender.getProfilePicture(); // NEED TO UNCOMMENT
		senderAvatar = new ImageIcon(Images.parrotAvatarGreenPixellated);
		if (senderAvatar != null) {
			Image senderImage = senderAvatar.getImage();
			Image newSenderImage = senderImage.getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height, java.awt.Image.SCALE_SMOOTH);
			senderAvatar = new ImageIcon(newSenderImage);
		}
		senderAvatarButton = new JButton(senderAvatar); 
		senderAvatarButton.setContentAreaFilled(false);
		senderAvatarButton.setBorderPainted(false);
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.YELLOW);
		northPanel.add(senderAvatarButton);
		northPanel.add(senderLabel);
		northPanel.add(dateLabel);
		northPanel.add(messageLabel);
		//northPanel.setSize(this.getMaximumSize().width, 30);
		northPanel.setSize(400, 30);
		add(northPanel, BorderLayout.NORTH);
		
		//add(dateLabel);
		add(messageLabel, BorderLayout.CENTER);
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(Color.ORANGE);
	}
	
	private void addEvents() {
		senderAvatarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Clicked the Sender Avatar image");
				goToUser(sender);
			}
		});
		
		/* read or not read notification
		this.addMouseAdapter(new MouseListener() {
			@Override
			public void mouseClicked()
		});*/
	}


	private void goToUser(User user) {
		ProfilePageGUI userPage = new ProfilePageGUI(mainPanel, user);
		mainPanel.displayPage(userPage); // Figure out moving to panel
	}
	
	private User getSender() {
		return sender;
	}
	
	private String getMessage() {
		return message;
	}
	
	private Date getDate() {
		return date;
	}
}
