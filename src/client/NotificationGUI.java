package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
import library.ImageLibrary;
import resources.Constants;
import resources.Images;

public class NotificationGUI extends JPanel {
	private static final long serialVersionUID = 1L;
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
	private Quote thisQuote;
	
	public NotificationGUI(MainPanel mainPanel, Notification notification) {
		System.out.println("Created Notification GUI from Notification");
		this.mainPanel = mainPanel;
		this.username = notification.getSenderName();
		this.message = notification.getMessage();
		this.date = notification.getDate();
		this.sender = this.mainPanel.clientPanel.quoteMeClient.dataManager.getUserFromUserName(notification.getSenderName());
		this.senderAvatar = this.sender.getProfilePicture();
		this.senderAvatarButton = new JButton(senderAvatar);
		this.quoteString = notification.getQuoteString();
		this.thisQuote = notification.getQuote();
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		senderLabel = new QuoteMeLabel(username);
		messageLabel = new QuoteMeLabel(message);
		String dateString = date.toString();
		dateLabel = new QuoteMeLabel(dateString.substring(4,10) + dateString.toString().substring(23) + dateString.substring(10, 16));
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
		setSize(200, 30);
		System.out.println("\n\nIN CREATE GUI, message is: " + message);
		
		if (message.equals("New Quote")) {
			System.out.println("New Quote createGUI()");
			JPanel northPanel = new JPanel();
			northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
			northPanel.setBackground(Color.BLUE);
			northPanel.add(Box.createHorizontalStrut(7));
			northPanel.add(senderAvatarButton);
			
			JPanel inNorthPanel = new JPanel();
			inNorthPanel.setLayout(new GridLayout(2,1));
			senderLabel.setText(senderLabel.getText() + " just quoted you!");
			inNorthPanel.add(senderLabel);
			inNorthPanel.add(dateLabel);
			inNorthPanel.setBackground(Color.BLUE);
			
			northPanel.add(Box.createHorizontalStrut(7));
			northPanel.add(inNorthPanel);
			add(northPanel, BorderLayout.NORTH);
			
			JPanel previewPanel = new JPanel();
			previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.X_AXIS));
			String substring = "";
			if (quoteString.length() < 25) substring = quoteString;
			else substring = quoteString.substring(0, 25) + "..." ;
			QuoteMeLabel quotePreviewLabel = new QuoteMeLabel("New Quote: " + '"' + substring + '"');
			
			previewPanel.add(Box.createHorizontalStrut(7));
			previewPanel.add(quotePreviewLabel);
			previewPanel.add(Box.createGlue());
			previewPanel.add(viewButton);
			previewPanel.add(Box.createHorizontalStrut(7));
			previewPanel.setBackground(Color.WHITE);
			previewPanel.setBorder(new EmptyBorder(7,7,7,7));
			add(previewPanel, BorderLayout.CENTER);
			setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		else if (message.equals("New Follower")) {
			System.out.println("New Follower createGUI()");
			JPanel northPanel = new JPanel();
			northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
			northPanel.setBackground(Color.PINK);
			northPanel.add(Box.createHorizontalStrut(7));
			northPanel.add(senderAvatarButton);
			
			JPanel inNorthPanel = new JPanel();
			inNorthPanel.setLayout(new GridLayout(2,1));
			senderLabel.setText(senderLabel.getText() + " just followed you!");
			inNorthPanel.add(senderLabel);
			inNorthPanel.add(dateLabel);
			inNorthPanel.setBackground(Color.PINK);
			
			northPanel.add(Box.createHorizontalStrut(7));
			northPanel.add(inNorthPanel);
			add(northPanel, BorderLayout.NORTH);
			
			JPanel previewPanel = new JPanel();
			previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.X_AXIS));
			QuoteMeLabel quotePreviewLabel = new QuoteMeLabel("Follow them back! You know you want to...");
			
			previewPanel.add(Box.createHorizontalStrut(7));
			previewPanel.add(quotePreviewLabel);
			viewButton.setText("View Profile");
			previewPanel.add(Box.createGlue());
			previewPanel.add(viewButton);
			previewPanel.add(Box.createHorizontalStrut(7));
			previewPanel.setBackground(Color.WHITE);
			add(previewPanel, BorderLayout.CENTER);
			setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		else if (message.equals("New UpQuote")) {
			System.out.println("New UpQuote createGUI()");
			JPanel northPanel = new JPanel();
			northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
			northPanel.setBackground(Color.GREEN);
			northPanel.add(Box.createHorizontalStrut(7));
			northPanel.add(senderAvatarButton);
			
			JPanel inNorthPanel = new JPanel();
			inNorthPanel.setLayout(new GridLayout(2,1));
			senderLabel.setText(senderLabel.getText() + " just upQuoted your Quote!");
			inNorthPanel.add(senderLabel);
			inNorthPanel.add(dateLabel);
			inNorthPanel.setBackground(Color.GREEN);
			
			northPanel.add(Box.createHorizontalStrut(7));
			northPanel.add(inNorthPanel);
			add(northPanel, BorderLayout.NORTH);
			
			JPanel previewPanel = new JPanel();
			previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.X_AXIS));
			String substring = "";
			if (quoteString.length() < 25) substring = quoteString;
			else substring = quoteString.substring(0, 25) + "...\"" ;
			QuoteMeLabel quotePreviewLabel = new QuoteMeLabel("UpQuoted: " + substring);
			
			previewPanel.add(Box.createHorizontalStrut(7));
			previewPanel.add(quotePreviewLabel);
			previewPanel.add(Box.createGlue());
			previewPanel.add(viewButton);
			previewPanel.add(Box.createHorizontalStrut(7));
			previewPanel.setBackground(Color.WHITE);
			add(previewPanel, BorderLayout.CENTER);
			setBorder(new EmptyBorder(10, 10, 10, 10));
		}
	}
	
	private void addEvents() {
		senderAvatarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Clicked the Sender Avatar image");
				goToUser(sender);
			}
		});
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Going to the quote");
				JPanel jp = new JPanel();
				jp.add(new QuoteGUI(mainPanel, thisQuote));
				mainPanel.displayPage(jp);
			}
		});
	}

	private void goToUser(User user) {
		ProfilePageGUI userPage = new ProfilePageGUI(mainPanel, user);
		mainPanel.displayPage(userPage); 
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
