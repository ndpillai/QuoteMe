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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
import library.FontLibrary;
import library.ImageLibrary;
import resources.Constants;
import resources.Images;

public class QuoteGUI extends JPanel {
	public Quote thisQuote;
	private User poster, speaker;
	private ImageIcon posterAvatar, speakerAvatar;
	private QuoteMeLabel posterNameLabel, speakerNameLabel, datePostedLabel;
	private JTextArea quoteTextArea;
	private QuoteMeLabel category, upQuotes;
	private JButton posterButton, speakerButton;
	private QuoteMeButton upQuoteButton;
	private MainPanel mainPanel;
	
	public QuoteGUI(MainPanel mainPanel, Quote thisQuote) {
		this.mainPanel = mainPanel;
		this.thisQuote = thisQuote;
		this.poster = thisQuote.getPoster();
		this.speaker = thisQuote.getSpeaker();
		this.category = new QuoteMeLabel("              " + Constants.categoriesList[thisQuote.getCategory()], 16, true);
		this.upQuotes = new QuoteMeLabel("" + thisQuote.getUpQuotes(), 16, true);
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		posterNameLabel = new QuoteMeLabel(poster.getUserName(), 16, true);
		speakerNameLabel = new QuoteMeLabel(speaker.getUserName(), 16, true);
		datePostedLabel = new QuoteMeLabel(thisQuote.getDatePosted().toString(), 16, true);
		quoteTextArea = new JTextArea(2, 20);
		quoteTextArea.setText('"' + thisQuote.getText() + '"');
		quoteTextArea.setEditable(false);
		quoteTextArea.setLineWrap(true);
		quoteTextArea.setWrapStyleWord(true);
		quoteTextArea.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		
		upQuoteButton = new QuoteMeButton("UpQuote", ImageLibrary.getImage(Images.greenButton), 15, 100, 25);	// maybe add an up arrow?
		
		posterAvatar = poster.getProfilePicture();
		if (posterAvatar != null) {
			Image posterImage = posterAvatar.getImage();
			Image newPosterImage = posterImage.getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height,  java.awt.Image.SCALE_SMOOTH ) ;  
			posterAvatar = new ImageIcon(newPosterImage);
		}
		
		posterButton = new JButton(posterAvatar); 
		posterButton.setContentAreaFilled(false);
		posterButton.setBorderPainted(false);
		
		speakerAvatar = speaker.getProfilePicture();
		if (speakerAvatar != null) {
			Image speakerImage = speakerAvatar.getImage();
			Image newSpeakerImage = speakerImage.getScaledInstance(Constants.AvatarButtonSize.width, Constants.AvatarButtonSize.height, java.awt.Image.SCALE_SMOOTH);
			speakerAvatar = new ImageIcon(newSpeakerImage);
		}
		
		speakerButton = new JButton(speakerAvatar); 
		speakerButton.setContentAreaFilled(false);
		speakerButton.setBorderPainted(false);
		
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		setSize(400, 30);
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.GRAY);
		northPanel.add(datePostedLabel);
		northPanel.add(category);
		northPanel.setForeground(Color.GRAY);
		//northPanel.setSize(this.getMaximumSize().width, 30);
		northPanel.setSize(400, 30);
		add(northPanel, BorderLayout.NORTH);
		
		add(quoteTextArea, BorderLayout.CENTER);

		JPanel quoteInfoPanel = new JPanel();
		quoteInfoPanel.add(speakerButton);
		quoteInfoPanel.add(speakerNameLabel);
		quoteInfoPanel.add(posterButton);
		quoteInfoPanel.add(posterNameLabel);
		quoteInfoPanel.add(upQuoteButton);
		quoteInfoPanel.add(upQuotes);
		quoteInfoPanel.setBackground(Color.ORANGE);
		quoteInfoPanel.setSize(new Dimension(quoteInfoPanel.getMaximumSize().width, 10));
		add(quoteInfoPanel, BorderLayout.SOUTH);
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	private void addEvents() {
		posterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Clicked the POSTER image");
				goToUser(poster);
			}
		});
		
		speakerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Clicked the SPEAKER image");
				goToUser(speaker);
			}
		});
		
		upQuoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("thisQuote.getUpQuotes() before: " + thisQuote.getUpQuotes());
				thisQuote.incrementUpQuotes();
				upQuotes.setText("" + thisQuote.getUpQuotes());
				System.out.println("thisQuote.getUpQuotes() after: " + thisQuote.getUpQuotes());
				upQuoteButton.setEnabled(false);
				
				NotificationGUI newUpQuoteNotification = new NotificationGUI(mainPanel, mainPanel.clientPanel.getCurrentUser(), "New UpQuote", new Date());
				thisQuote.getSpeaker().addNotification(newUpQuoteNotification);
				
				repaint();
				revalidate();
				
				// We need to add a notification about this
				//SendEmail.sendUpQuoteEmail(speaker, poster, thisQuote);
			}
		});
	}
	
	private void goToUser(User user) {
		ProfilePageGUI userPage = new ProfilePageGUI(mainPanel, user);
		mainPanel.displayPage(userPage); // Figure out moving to panel
	}
	
	public void refresh() {
		// TODO
	}
}
