package client;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ApproveQuoteGUI extends JPanel {
	public Quote thisQuote;
	private String poster, speaker, quoteText;
	private Date datePosted;
	private Vector<String> categories;
	private JButton posterButton, speakerButton, approveButton, denyButton;

	public ApproveQuoteGUI() {
		initializeVariables();
		createGUI();
		addEvents();
		this.setVisible(true);
	}
	
	private void initializeVariables() {
		thisQuote = new Quote(); // TODO change constructor, update items
		poster = new String();
		speaker = new String();
		quoteText = new String();
		datePosted = new Date();
		categories = new Vector<String>();
		posterButton = new JButton();
		speakerButton = new JButton();
		approveButton = new JButton();
		denyButton = new JButton();
	}
	
	private void createGUI() {
		this.setLayout(new BorderLayout()); // TODO decide on which layouts to use
		// TODO finish styling elements
	}
	
	private void addEvents() {
		// TODO add events for buttons
	}

	private void goToUser() {
		// TODO Implement go to user functionality
	}
	
	private void denyQuote() {
		// TODO ...
	}
	
	private void approveQuote() {
		// TODO ...
	}
}
