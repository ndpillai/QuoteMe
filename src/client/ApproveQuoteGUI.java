package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ApproveQuoteGUI extends JPanel {
	public Quote thisQuote;
	private String poster, speaker, quoteText;
	private Date datePosted;
	private int category;
	private JLabel promptLabel;
	private JTextArea quoteTA;
	private JButton posterButton, speakerButton, approveButton, denyButton;
	private MainPanel mainPanel;

	public ApproveQuoteGUI(MainPanel mainPanel, Quote quote) {
		this.mainPanel = mainPanel;
		thisQuote = quote;
		initializeVariables();
		createGUI();
		addEvents();
		this.setVisible(true);
	}
	
	private void initializeVariables() {
		// TODO change constructor, update items
		poster = thisQuote.getPoster().getUserName();
		speaker = thisQuote.getSpeaker().getUserName();
		quoteText = thisQuote.getText();
		datePosted = thisQuote.getDatePosted();
		category = thisQuote.getCategory();
		promptLabel = new JLabel(poster + " would like to quote you:");
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		quoteTA = new JTextArea(quoteText);
		quoteTA.setBorder(padding);
		posterButton = new JButton(poster + ":");
		posterButton.setBorderPainted(false);
		speakerButton = new JButton(speaker);
		speakerButton.setBorderPainted(false);
		approveButton = new JButton("Approve");
		denyButton = new JButton("Deny");
	}
	
	private void createGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // TODO decide on which layouts to use
		// TODO finish styling elements
		this.add(promptLabel);
		this.add(posterButton);
		this.add(quoteTA);
		this.add(speakerButton);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(denyButton);
		gbc.gridx = 1;
		buttonPanel.add(approveButton);
		
		this.add(Box.createGlue());
		this.add(buttonPanel);
	}
	
	private void addEvents() {
		// TODO add events for buttons
		approveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				approveQuote();
			}
		});
		denyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				denyQuote();
			}
		});
		posterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				goToUser(thisQuote.getPoster());
			}
		});
		speakerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				goToUser(thisQuote.getSpeaker());
			}
		});
	}

	private void goToUser(User u) {
		// TODO Implement go to user functionality
	}
	
	private void denyQuote() {
		// TODO ...
	}
	
	private void approveQuote() {
		// TODO ...
	}
}
