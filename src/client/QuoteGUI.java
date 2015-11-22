package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import resources.Constants;

public class QuoteGUI extends JPanel {
	public Quote thisQuote;
	private User poster, speaker;
	private ImageIcon posterAvatar, speakerAvatar;
	private JLabel posterNameLabel, speakerNameLabel, datePostedLabel;
	private JTextArea quoteTextArea;
	private JLabel category, upQuotes;
	private JButton upQuoteButton, posterButton, speakerButton;
	private MainPanel mainPanel;
	
	public QuoteGUI(MainPanel mainPanel, Quote thisQuote) {
		this.mainPanel = mainPanel;
		this.thisQuote = thisQuote;
		this.poster = thisQuote.getPoster();
		this.speaker = thisQuote.getSpeaker();
		this.category = new JLabel(Constants.categoriesList[thisQuote.getCategory()]);
		this.upQuotes = new JLabel("" + thisQuote.getUpQuotes());
		
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		posterNameLabel = new JLabel(poster.getUserName());
		speakerNameLabel = new JLabel(speaker.getUserName());
		datePostedLabel = new JLabel(thisQuote.getDatePosted().toString());
		quoteTextArea = new JTextArea(5, 20);
		quoteTextArea.setText('"' + thisQuote.getText() + '"');
		quoteTextArea.setEditable(false);
		
		upQuoteButton = new JButton("UpQuote");	// maybe add an up arrow?
		
		posterAvatar = poster.getProfilePicture();
		Image posterImage = posterAvatar.getImage();  
		Image newPosterImage = posterImage.getScaledInstance(Constants.AvatarButtonWidth.width, Constants.AvatarButtonWidth.height,  java.awt.Image.SCALE_SMOOTH ) ;  
		posterAvatar = new ImageIcon(newPosterImage);
		
		posterButton = new JButton(posterAvatar); 
		posterButton.setContentAreaFilled(false);
		posterButton.setBorderPainted(false);
		
		speakerAvatar = speaker.getProfilePicture();
		Image speakerImage = speakerAvatar.getImage();
		Image newSpeakerImage = speakerImage.getScaledInstance(Constants.AvatarButtonWidth.width, Constants.AvatarButtonWidth.height, java.awt.Image.SCALE_SMOOTH);
		speakerAvatar = new ImageIcon(newSpeakerImage);
		
		speakerButton = new JButton(speakerAvatar); 
		speakerButton.setContentAreaFilled(false);
		speakerButton.setBorderPainted(false);
		
	}
	
	private void createGUI() {
		
		/*
		setLayout(new BorderLayout());
		//setSize(this.getMaximumSize().width, this.getMaximumSize().height);
		setSize(this.getMaximumSize().width - 50, 100);
		
		JPanel usersPanel = new JPanel();
		usersPanel.setLayout(new BoxLayout(usersPanel,  BoxLayout.Y_AXIS));
		
		speakerButton.setSize(Constants.AvatarButtonWidth);
		posterButton.setSize(Constants.AvatarButtonWidth);
		usersPanel.add(speakerButton);
		usersPanel.add(speakerNameLabel);
		usersPanel.add(posterButton);
		usersPanel.add(posterNameLabel);

		add(usersPanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(quoteTextArea, BorderLayout.CENTER);
		
		JPanel interactPanel = new JPanel(new FlowLayout());
		interactPanel.add(upQuoteButton);
		// need to add categories
		interactPanel.add(new JLabel("CATEGORIES"));
		
		centerPanel.add(interactPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);*/
		
		setLayout(new BorderLayout());
		setSize(this.getMaximumSize().width, 30);
		setPreferredSize(new Dimension(this.getMaximumSize().width - 100, 30));
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.add(datePostedLabel);
		northPanel.setForeground(Color.GRAY);
		northPanel.setSize(this.getMaximumSize().width, 30);
		add(northPanel, BorderLayout.NORTH);
		
		add(quoteTextArea, BorderLayout.CENTER);

		JPanel quoteInfoPanel = new JPanel();
		quoteInfoPanel.add(speakerButton);
		quoteInfoPanel.add(speakerNameLabel);
		quoteInfoPanel.add(posterButton);
		quoteInfoPanel.add(posterNameLabel);
		quoteInfoPanel.add(category);
		quoteInfoPanel.add(upQuoteButton);
		quoteInfoPanel.add(upQuotes);
		quoteInfoPanel.setBackground(Color.ORANGE);
		add(quoteInfoPanel, BorderLayout.SOUTH);
		
//		speakerButton.setSize(Constants.AvatarButtonWidth);
//		posterButton.setSize(Constants.AvatarButtonWidth);
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
				System.out.println("UPQUOTED a quote.");
				//thisQuote.incrementUpQuotes();
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
