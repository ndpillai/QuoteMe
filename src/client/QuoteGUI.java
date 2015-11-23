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

import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private QuoteMeLabel posterNameLabel, quotedLabel, speakerNameLabel, datePostedLabel;
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
		quotedLabel = new QuoteMeLabel("quoted by", 8, true);
		String date = thisQuote.getDatePosted().toString();
		datePostedLabel = new QuoteMeLabel(date.substring(3,10) + date.toString().substring(23) + date.substring(10, 16), 16, true);
		quoteTextArea = new JTextArea(2, 20);
		quoteTextArea.setBorder(new EmptyBorder(7,7,7,7));
		quoteTextArea.setText('"' + thisQuote.getText() + '"');
		quoteTextArea.setEditable(false);
		quoteTextArea.setLineWrap(true);
		quoteTextArea.setWrapStyleWord(true);
		quoteTextArea.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		
		upQuoteButton = new QuoteMeButton("UpQuote", ImageLibrary.getImage(Images.greenButton), 15, 100, 25);	// maybe add an up arrow?
		checkUpQuoteButton();
		
		posterAvatar = poster.getProfilePicture();
		if (posterAvatar != null) {
			Image posterImage = posterAvatar.getImage();
			Image newPosterImage = posterImage.getScaledInstance(Constants.AvatarButtonSize.width/2, Constants.AvatarButtonSize.height/2,  java.awt.Image.SCALE_SMOOTH ) ;  
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
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		northPanel.add(Box.createHorizontalStrut(7));
		northPanel.add(category);
		northPanel.add(Box.createGlue());
		northPanel.add(datePostedLabel);
		northPanel.add(Box.createHorizontalStrut(7));
		northPanel.setForeground(Color.GRAY);
		//northPanel.setSize(this.getMaximumSize().width, 30);
		northPanel.setSize(400, 30);
		add(northPanel, BorderLayout.NORTH);
		
		add(quoteTextArea, BorderLayout.CENTER);

		JPanel quoteInfoPanel = new JPanel();
		quoteInfoPanel.setLayout(new BoxLayout(quoteInfoPanel, BoxLayout.X_AXIS));
		quoteInfoPanel.add(speakerButton);
		quoteInfoPanel.add(speakerNameLabel);
		quoteInfoPanel.add(Box.createHorizontalStrut(7));
		quoteInfoPanel.add(quotedLabel);
		quoteInfoPanel.add(posterButton);
		quoteInfoPanel.add(posterNameLabel);
		quoteInfoPanel.add(Box.createGlue());
		quoteInfoPanel.add(upQuoteButton);
		quoteInfoPanel.add(Box.createHorizontalStrut(7));
		quoteInfoPanel.add(upQuotes);
		quoteInfoPanel.add(Box.createHorizontalStrut(7));
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
				
				thisQuote.incrementUpQuotes();
				upQuotes.setText("" + thisQuote.getUpQuotes());
				System.out.println("thisQuote.getUpQuotes() after: " + thisQuote.getUpQuotes());
				thisQuote.addUpQuoter(mainPanel.clientPanel.getCurrentUser().getUserName());
				checkUpQuoteButton();
				mainPanel.clientPanel.quoteMeClient.sendObject(thisQuote);
				upQuoteButton.setEnabled(false);

				Notification newUpQuoteNotification = new Notification(thisQuote.getSpeaker(), mainPanel.clientPanel.getCurrentUser().getUserName(), "New UpQuote", new Date(), quoteTextArea.getText());
				thisQuote.getSpeaker().addNotification(newUpQuoteNotification);
				mainPanel.clientPanel.quoteMeClient.sendObject(newUpQuoteNotification);

				repaint();
				revalidate();
				
				
				// We need to add a notification about this
				//SendEmail.sendUpQuoteEmail(speaker, poster, thisQuote);
			}
		});
	}
	
	private void checkUpQuoteButton() {
		if (thisQuote.hasQuoted(mainPanel.clientPanel.getCurrentUser().getUserName())) {
			upQuoteButton.setEnabled(false);
		}
		else {
			upQuoteButton.setEnabled(true);
		}
	}
	private void goToUser(User user) {
		ProfilePageGUI userPage = new ProfilePageGUI(mainPanel, user);
		mainPanel.displayPage(userPage); // Figure out moving to panel
	}
	
	public void refresh() {
		// TODO
	}
}
