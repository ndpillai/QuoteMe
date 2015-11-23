package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
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
	private QuoteMeLabel senderLabel, messageLabel, dateLabel;
	
	private String quoteString;
	
	private QuoteMeButton viewButton;
	
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
	
	
	public NotificationGUI(MainPanel mainPanel, String senderName, String message, Date date) {
		this.mainPanel = mainPanel;
		this.username = senderName;
		this.message = message;
		this.date = date;
		this.sender = this.mainPanel.clientPanel.quoteMeClient.dataManager.getUserFromUserName(senderName);
		//this.senderAvatar = new ImageIcon(Images.parrotAvatarBluePixellated);
		this.senderAvatar = this.sender.getProfilePicture();
		this.senderAvatarButton = new JButton(senderAvatar);
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	public NotificationGUI(MainPanel mainPanel, String senderName, String message, Date date, String quoteString) {
		this.mainPanel = mainPanel;
		this.username = senderName;
		this.message = message;
		this.date = date;
		this.sender = this.mainPanel.clientPanel.quoteMeClient.dataManager.getUserFromUserName(senderName);
		//this.senderAvatar = new ImageIcon(Images.parrotAvatarBluePixellated);
		this.senderAvatar = this.sender.getProfilePicture();
		this.senderAvatarButton = new JButton(senderAvatar);
		this.quoteString = quoteString;
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		senderLabel = new QuoteMeLabel(username);
		messageLabel = new QuoteMeLabel(message);
		//dateLabel = new QuoteMeLabel("                " + date.toString());
		dateLabel = new QuoteMeLabel(date.toString());
		viewButton = new QuoteMeButton("View", ImageLibrary.getImage(Images.greenButton), 15, 100, 25);

		read = false;

		// Scale the sender's avatar
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
		
		if (message.equals("New Quote")) {
			System.out.println("New Quote createGUI()");
			JPanel northPanel = new JPanel();
			//northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
			northPanel.setBackground(Color.BLUE);
			northPanel.add(senderAvatarButton);
			
			JPanel inNorthPanel = new JPanel();
			//inNorthPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
			inNorthPanel.setLayout(new GridLayout(2,1));
			senderLabel.setText(senderLabel.getText() + " just quoted you!");
			inNorthPanel.add(senderLabel);
			inNorthPanel.add(dateLabel);
			inNorthPanel.setBackground(Color.BLUE);
			
			northPanel.add(inNorthPanel);
			add(northPanel, BorderLayout.NORTH);
			
			JPanel previewPanel = new JPanel();
			String substring = "";
			if (quoteString.length() < 70) substring = quoteString;
			else substring =  quoteString.substring(0, 70) + "..." ;
			JLabel quotePreviewLabel = new JLabel('"' + substring + '"');
			
			previewPanel.add(quotePreviewLabel);
			previewPanel.add(viewButton);
			previewPanel.setBackground(Color.WHITE);
			add(previewPanel, BorderLayout.SOUTH);
		}
		else if (message.equals("New Follower")) {
			System.out.println("New Follower createGUI()");
		}
		else if (message.equals("New UpQuote")) {
			System.out.println("New UpQuote createGUI()");
		}
		
		/*
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
		//setBackground(Color.ORANGE);*/
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
