package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import custom.QuoteMeButton;
import custom.QuoteMeLabel;
import custom.QuoteMeTextField;
import library.FontLibrary;
import library.ImageLibrary;
import resources.Constants;
import resources.CustomListeners;
import resources.Images;

public class WriteQuoteGUI extends JPanel {
	private QuoteMeLabel speakerLabel;
	private QuoteMeTextField userSearchField;
	private User speaker;
	private QuoteMeButton searchButton;
	private JPanel searchPanel;
	
	// Added this to display results of finding user
	private String [] defaultResults = {"Search for a user"};
	private JComboBox<String> searchUserComboBox;
	private JTextArea quoteTextArea;
	private QuoteMeLabel characterCountLabel, categoryLabel;
	private QuoteMeButton submitQuoteButton;
	private JComboBox<String> categoryComboBox;
	private Insets spacing = new Insets(10,10,10,10);
	
	private MainPanel mainPanel;
	
	public WriteQuoteGUI(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		speakerLabel = new QuoteMeLabel("Speaker: ");
		userSearchField = new QuoteMeTextField("Search for a user");
		userSearchField.setSize(new Dimension(10, userSearchField.getPreferredSize().height));
		searchButton = new QuoteMeButton("Search", ImageLibrary.getImage(Images.greenButton), 15, 100, 25);
		searchPanel = new JPanel();
		searchUserComboBox = new JComboBox<String>(defaultResults);
		searchUserComboBox.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		searchUserComboBox.setSize(100, searchUserComboBox.getPreferredSize().height);
		
		quoteTextArea = new JTextArea();
		quoteTextArea.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		quoteTextArea.setText("Enter your quote here");
		quoteTextArea.setForeground(Color.GRAY);
		quoteTextArea.setWrapStyleWord(true);
		quoteTextArea.setLineWrap(true);
		characterCountLabel = new QuoteMeLabel("Characters: 0");
		characterCountLabel.setFontSize(18);
		submitQuoteButton = new QuoteMeButton("Submit New Quote", ImageLibrary.getImage(Images.greenButton), 15, 150, 25);
		
		categoryLabel = new QuoteMeLabel("Category:");
		categoryComboBox = new JComboBox<String>();
		categoryComboBox.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		categoryComboBox.addItem("Motivational");
		categoryComboBox.addItem("Funny");
		categoryComboBox.addItem("Sentimental");
	}
	
	private void createGUI() {
		setBorder(new EmptyBorder(spacing));
		setLayout(new BorderLayout());
		
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		JPanel userSearchPanel = new JPanel();
		userSearchPanel.setLayout(new BorderLayout());
		userSearchPanel.add(userSearchField, BorderLayout.CENTER);
		userSearchPanel.add(searchButton, BorderLayout.EAST);
		JPanel userSelectPanel = new JPanel();
		userSelectPanel.setLayout(new BoxLayout(userSelectPanel, BoxLayout.X_AXIS));
		userSelectPanel.add(speakerLabel);
		userSelectPanel.add(searchUserComboBox);
		searchPanel.add(userSearchPanel);
		searchPanel.add(userSelectPanel);
		add(searchPanel, BorderLayout.NORTH);	// Search, textfield, results combo box, search button
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

		quoteTextArea.setBorder(new EmptyBorder(spacing));
		middlePanel.add(quoteTextArea);
		
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.X_AXIS));
		categoryPanel.add(Box.createHorizontalStrut(10));
		categoryPanel.add(categoryLabel);
		categoryPanel.add(categoryComboBox);
		categoryPanel.add(Box.createGlue());
		categoryPanel.add(characterCountLabel);
		categoryPanel.add(Box.createHorizontalStrut(10));
		characterCountLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		middlePanel.add(categoryPanel);
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
					searchUserComboBox.setModel(new DefaultComboBoxModel<String>(relevantUsers));
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
				characterCountLabel.setText("Characters: " + quoteTextArea.getText().length());
			}
		});
		
		quoteTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (quoteTextArea.getText().equals("Enter your quote here")) {
					quoteTextArea.setText("");
					quoteTextArea.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (quoteTextArea.getText().isEmpty() || quoteTextArea.getText().equals("Enter your quote here")) {
					quoteTextArea.setText("Enter your quote here");
					quoteTextArea.setForeground(Color.GRAY);
				}
			}
		});
		
		//quoteTextArea.addFocusListener(new CustomListeners.RemoveTextAdapter(quoteTextArea,"Enter your quote here."));
		
		submitQuoteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (searchUserComboBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(WriteQuoteGUI.this, "You forgot to select a speaker, doofus.", "Select speaker!",  JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(WriteQuoteGUI.this, "Wow, congrats on making it big in life.", "Quote submitted!",  JOptionPane.PLAIN_MESSAGE);
					
					System.out.println("you wrote a quote");
					
					// Send a notification to current user

					System.out.println("In WriteQuoteGUI: Adding newQuoteNotification");
					Notification newQuoteNotification = new Notification(mainPanel.clientPanel.getCurrentUser().getUserName(), "New Quote", new Date(), quoteTextArea.getText());
					getSpeaker().addNotification(newQuoteNotification);
					
					Quote newQuote = new Quote(quoteTextArea.getText(), getSpeaker(), getPoster(), new Date(), getCategory());
					mainPanel.clientPanel.quoteMeClient.sendObject(newQuote);
					mainPanel.clientPanel.quoteMeClient.dataManager.addQuote(newQuote);
					mainPanel.refreshFeed();
					
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
				if (!u.equals(mainPanel.clientPanel.getCurrentUser())) {
					userResults.add(u);
					for (int j=0; j<searchTerms.length; j++) {
						if (!u.toString().contains(searchTerms[j])) {
							userResults.remove(u);
							break;
						}
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
		searchUserComboBox.setModel(new DefaultComboBoxModel<String>(defaultResults));
		quoteTextArea.setText("");
		characterCountLabel.setText("0");
		categoryComboBox.setSelectedIndex(0);
	}
}
