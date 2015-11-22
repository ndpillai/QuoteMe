package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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
	private String [] defaultResults = {"Search for a user"};
	private ArrayList<User> userResults; // to be an array or an arraylist?
	private JComboBox searchUserComboBox;
	private JLabel quoteLabel;
	private JTextArea quoteTextArea;
	private JLabel characterCountLabel;
	private JButton submitQuoteButton;
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
		userResults = null;
		searchUserComboBox = new JComboBox(defaultResults);
		searchUserComboBox.setSize(100, searchUserComboBox.getPreferredSize().height);
		
		quoteLabel = new JLabel("Quote:");
		quoteTextArea = new JTextArea();
		characterCountLabel = new JLabel("0");
		submitQuoteButton = new JButton("Submit New Quote");
		
		categoryComboBox = new JComboBox<String>();
		categoryComboBox.addItem("Motivational");
		categoryComboBox.addItem("Funny");
		categoryComboBox.addItem("Sentimental");
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
		
		add(submitQuoteButton, BorderLayout.SOUTH); // Quote details
	}
	
	private void addEvents() {
		userSearchField.addFocusListener(new CustomListeners.RemoveTextAdapter(userSearchField, "Search for a user"));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!userSearchField.getText().equals("Search for a user") && !userSearchField.getText().equals("")) {
					String [] relevantUsers = getRelevantUsers();
					searchUserComboBox.setModel(new DefaultComboBoxModel(relevantUsers));
					System.out.println("Clicked search button.");
				}
				else {
					System.out.println("Wrong search format");
				}

			}
		});
		
		quoteTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				characterCountLabel.setText("" + quoteTextArea.getText().length());
			}
		});
		
		submitQuoteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (searchUserComboBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(WriteQuoteGUI.this, "You forgot to select a speaker, doofus.", "Select speaker!",  JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(WriteQuoteGUI.this, "Wow, congrats on making it big in life.", "Quote submitted!",  JOptionPane.PLAIN_MESSAGE);
					
					Quote newQuote = new Quote(quoteTextArea.getText(), getSpeaker(), getPoster(), new Date(), getCategory());
					mainPanel.clientPanel.quoteMeClient.sendObject(newQuote);
					
					printComponents();
					resetComponents();
				}
			}
		});
	
	}
	
	private String[] getRelevantUsers() {
		String search = userSearchField.getText();
		String[] searchTerms = search.split(" ");
		DataManager dm = mainPanel.clientPanel.quoteMeClient.dataManager;
		Vector<User> users = dm.getAllUsers();
		
		Vector<User> userResults = new Vector<User>();
		
		if (users != null) {
			for (int i=0; i<users.size(); i++) {
				User u = users.elementAt(i);
				userResults.add(u);
				for (int j=0; j<searchTerms.length; j++) {
					if (!u.toString().contains(searchTerms[j])) {
						userResults.remove(u);
						break;
					}
				}
			}
		}
		
		String[] usernameResults = new String[userResults.size()+1];
		usernameResults[0] = "Select a user";
		for (int i=1; i<userResults.size()+1; i++) {
			usernameResults[i] = userResults.elementAt(i-1).getUserName();
		}
		
		return usernameResults;
	}
	
	public String getQuoteText() {
		return quoteTextArea.getText();
	}
	
	public User getPoster() {
		// searchUserComboBox.getSelectedItem().toString()
		// return userResults.get(searchUserComboBox.getSelectedIndex()); // THIS ONE SHOULD HOPEFULLY WORK
		
		return mainPanel.clientPanel.getCurrentUser();
	}
	
	public User getSpeaker() {
		return mainPanel.clientPanel.quoteMeClient.dataManager.getUserFromUserName(searchUserComboBox.getSelectedItem().toString());
	}
	
	public int getCategory() {
		String cat = categoryComboBox.getSelectedItem().toString();
		
		if (cat.equals("Meaningful")) return 0;
		else if (cat.equals("Funny")) return 1;
		else return 2; //"Sentimental"
		
	}
	
	private void printComponents() {
		// TODO for testing purposes to grab all of the info for making a quote
		System.out.println("Printing quote components:");
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
