package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class QuoteGUI extends JPanel {
	public Quote thisQuote;
	private User poster, speaker;
	private JLabel posterNameLabel, speakerNameLabel, datePostedLabel;
	private JTextArea quoteTextArea;
	private Vector<String> categories;
	private JButton upQuoteButton, posterButton, speakerButton;
	private MainPanel mainPanel;
	
	public QuoteGUI(MainPanel mainPanel, Quote thisQuote) {
		this.mainPanel = mainPanel;
		this.thisQuote = thisQuote;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		//TODO
		poster = thisQuote.getPoster();
		speaker = thisQuote.getSpeaker();
		//posterNameLabel = new JLabel(poster.getUserName());
		posterNameLabel = new JLabel("Poster Name");
		//speakerNameLabel = new JLabel(speaker.getUserName());
		speakerNameLabel = new JLabel("Speaker Name");
		//datePostedLabel = new JLabel(thisQuote.getDatePosted().toString());
		datePostedLabel = new JLabel("Date Posted");
		//quoteTextArea = new JTextArea(thisQuote.getText());
		quoteTextArea = new JTextArea("Quote Text Area");
		//categories = thisQuote.getCategories(); // what is with this? booleans for which categories have been selected?
		upQuoteButton = new JButton("UpQuote");	// maybe add an up arrow?
		//posterButton = new JButton(poster.getProfilePicture()); // replace with images
		posterButton = new JButton("POSTER IMAGE");
		//speakerButton = new JButton(speaker.getProfilePicture()); // replace with images
		speakerButton = new JButton("SPEAKER IMAGE");
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		//setSize(this.getMaximumSize().width, this.getMaximumSize().height);
		setSize(this.getMaximumSize().width - 50, 100);
		
		JPanel usersPanel = new JPanel(new GridLayout(4,1));
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
		add(centerPanel, BorderLayout.CENTER);
		
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
