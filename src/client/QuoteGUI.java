package client;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

public class QuoteGUI extends JPanel {
	public Quote thisQuote;
	private String poster, speaker, datePosted, quoteText;
	private Vector<String> categories;
	private JButton upQuoteButton, saveButton, posterButton, speakerButton;
	
	public QuoteGUI(Quote q) {
		thisQuote = q;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		//TODO
		poster = new String();
		speaker = new String();
		datePosted = new String();
		quoteText = new String();
		categories = new Vector<String>();
		upQuoteButton = new JButton();
		saveButton = new JButton();
		posterButton = new JButton();
		speakerButton = new JButton();
	}
	
	private void createGUI() {
		//TODO
	}
	
	private void addEvents() {
		//TODO
	}
	
	private void incrementUpQuote() {
		//TODO
	}
	
	private void saveToQuoteBook() {
		//TODO
	}
	
	private void goToUser() {
		//TODO
	}
	
	public void refresh() {
		
	}
}