package client;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WriteQuoteGUI extends JPanel {
	private JLabel speakerLabel;
	private JTextField userSearchField;
	private User speaker;
	private JButton searchButton;
	private JPanel searchPanel;
	private JLabel quoteLabel;
	private JTextArea quoteTextArea;
	private JLabel characterCountLabel;
	private JButton quoteButton;
	private JComboBox<String> categoryComboBox;
	
	private MainPanel mainPanel;
	
	public WriteQuoteGUI(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		speakerLabel = new JLabel("Speaker: ");
		userSearchField = new JTextField();
		searchButton = new JButton("Search");
		searchPanel = new JPanel();
		quoteLabel = new JLabel("Quote:");
		quoteTextArea = new JTextArea();
		characterCountLabel = new JLabel("0");
		quoteButton = new JButton("Send quote request");
		categoryComboBox = new JComboBox<String>();
		categoryComboBox.addItem("Funny");
		categoryComboBox.addItem("Deep");
	}
	
	private void createGUI() {
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(speakerLabel, BorderLayout.WEST);
		searchPanel.add(userSearchField, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		
		setLayout(new BorderLayout());
		add(searchPanel, BorderLayout.NORTH);
		
		middlePanel.add(quoteLabel);
		middlePanel.add(quoteTextArea);
		middlePanel.add(characterCountLabel);
		middlePanel.add(categoryComboBox);
		add(middlePanel, BorderLayout.CENTER);
		
		add(quoteButton, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		
	}
	
	public String getQuoteText() {
		return "";
	}
	
	public User getSpeaker() {
		return null;
	}
	
	public String getCategory() {
		return null;
	}
}
