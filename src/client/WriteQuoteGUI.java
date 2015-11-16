package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import resources.CustomListeners;

public class WriteQuoteGUI extends JPanel {
	private JLabel speakerLabel;
	private JTextField userSearchField;
	private User speaker;
	private JButton searchButton;
	private JPanel searchPanel;
	
	// Added this to display results of finding user
	private String [] defaultResults = {"Results"};
	private JComboBox searchUserComboBox;
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
		userSearchField = new JTextField("Search for a user");
		userSearchField.setSize(new Dimension(10, userSearchField.getPreferredSize().height));
		searchButton = new JButton("Search");
		searchPanel = new JPanel();
		searchUserComboBox = new JComboBox(defaultResults);
		searchUserComboBox.setSize(100, searchUserComboBox.getPreferredSize().height);
		
		quoteLabel = new JLabel("Quote:");
		quoteTextArea = new JTextArea();
		characterCountLabel = new JLabel("0");
		quoteButton = new JButton("Send quote request");
		categoryComboBox = new JComboBox<String>();
		categoryComboBox.addItem("Funny");
		categoryComboBox.addItem("Deep");
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(speakerLabel);
		searchPanel.add(userSearchField);
		searchPanel.add(searchUserComboBox);
		searchPanel.add(searchButton);
		add(searchPanel, BorderLayout.NORTH);	// Search, textfield, results combo box, search button
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
				
		middlePanel.add(quoteLabel);
		middlePanel.add(quoteTextArea);
		middlePanel.add(characterCountLabel);
		middlePanel.add(categoryComboBox);
		add(middlePanel, BorderLayout.CENTER); // Write the quote
		
		add(quoteButton, BorderLayout.SOUTH); // Quote details
	}
	
	private void addEvents() {
		userSearchField.addFocusListener(new CustomListeners.RemoveTextAdapter(userSearchField, "Search for a user"));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!userSearchField.getText().equals("Search for a user") && !userSearchField.getText().equals("")) {
					//displaySearchResultsPage(userSearchField.getText());
					String [] relevantUsers = {"Select User", "Nav", "Ingrid", "Tony", "Simone"};
					searchUserComboBox.setModel(new DefaultComboBoxModel(relevantUsers));
					System.out.println("Clicked search button.");
				}
				else {
					System.out.println("Wrong search format");
				}

			}
		});
		
		quoteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (searchUserComboBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(WriteQuoteGUI.this, "You forgot to select a speaker, doofus.", "Select speaker!",  JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(WriteQuoteGUI.this, "Wow, congrats on making it big in life.", "Quote submitted!",  JOptionPane.PLAIN_MESSAGE);
					printComponents();
					resetComponents();
				}
			}
		});
	
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
	
	private void printComponents() {
		// TODO for testing purposes to grab all of the info for making a quote
		// Cna probably just be copied for quote making / instantiation
		System.out.println("/nPrinting quote components:");
		System.out.println("User search term: " + userSearchField.getText());
		System.out.println("SearchUserComboBox selection: " + searchUserComboBox.getSelectedItem().toString());
		System.out.println("Quote text area: " + quoteTextArea.getText());
		System.out.println("Character count: " + characterCountLabel.getText());
		System.out.println("CategoryComboBox selection: " + categoryComboBox.getSelectedItem().toString());
	}
	
	private void resetComponents() {
		userSearchField.setText("Search for a user");
		searchUserComboBox.setModel(new DefaultComboBoxModel(defaultResults));
		quoteTextArea.setText("");
		characterCountLabel.setText("0");
		categoryComboBox.setSelectedIndex(0);
	}
}
