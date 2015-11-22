package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FeedPageGUI extends JPanel {
	private JCheckBox[] categoryCB;
	private JComboBox sortCB;
	private JScrollPane scrollPane;
	private Vector<QuoteGUI> quoteList;
	
	private MainPanel mainPanel;
	
	public FeedPageGUI (MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		String[] categories = {"Recent", "Popular"};
		sortCB = new JComboBox(categories);
		
		categoryCB = new JCheckBox[3];
		categoryCB[0] = new JCheckBox("Motivational");
		categoryCB[1] = new JCheckBox("Funny");
		categoryCB[2] = new JCheckBox("Sentimental");
		
		scrollPane = new JScrollPane();
		quoteList = getQuotesToDisplay(); // How are we loading this? From the server? datamanager?
	}
	
	private void createGUI () {
		setLayout(new BorderLayout());
		// NORTH panel
		JPanel northPanel = new JPanel();
		northPanel.add(sortCB);
		for (int i=0; i<3; i++)
			northPanel.add(categoryCB[i]);
		add(northPanel, BorderLayout.NORTH);
		//add(new JLabel("FEED PAGE"), BorderLayout.NORTH);
		
		// CENTER panel
		// add things to the scrollpane
		JPanel feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		
		
		User newUser = new User("Amanda", "Bynes", "amandab", "tonyelevathingal@gmail.com", "123", new Date());
		
		//public Quote(String text, User speaker, User poster, Date datePosted, Vector<String> categories) {
		Quote quote1 = new Quote("I love people who already hate me hate me more", newUser, newUser, new Date(), 1);
		Quote quote2 = new Quote("I ignore you if I want nothing from you", newUser, newUser, new Date(), 1);
		feedPanel.add(new QuoteGUI(mainPanel, quote1));
		feedPanel.add(new QuoteGUI(mainPanel, quote2));

		//scrollPane.add(feedPanel);
		//add(scrollPane, BorderLayout.CENTER);
		add(feedPanel, BorderLayout.CENTER);
	}
	
	private void addEvents() {
		for (int i=0; i<3; i++) {
			categoryCB[i].addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					refreshQuoteList();
				}
			});
		}
	}
	
	public class newProfileAL implements ActionListener {
		String imagePath;
		public newProfileAL(String imagePath) {
			this.imagePath = imagePath;
		}
		public void actionPerformed(ActionEvent ae) {
			User u = new User();
			u.setProfilePicture(new ImageIcon(this.imagePath));
			ProfilePageGUI ppg = new ProfilePageGUI(mainPanel, u);
			mainPanel.displayPage(ppg);
		}
	}
	
	public Vector<QuoteGUI> getQuotesToDisplay() {
		//change this later, right now it gets all quotes
		Vector<Quote> allquotes = mainPanel.clientPanel.quoteMeClient.dataManager.getAllQuotes();
		
		Vector<QuoteGUI> quotes = new Vector<QuoteGUI>();
		for (int i=0; i<allquotes.size(); i++)
			quotes.add(new QuoteGUI(mainPanel, allquotes.get(i)));
		
		return quotes;
	}
	
	public void refreshQuoteList() {
		quoteList = getQuotesToDisplay();
		Vector<QuoteGUI> newlist = new Vector<QuoteGUI>();
		for (int i=0; i<quoteList.size(); i++) {
			if (categoryCB[0].isSelected() 
					&& quoteList.get(i).thisQuote.getCategory()==0
					&& !newlist.contains(quoteList.get(i)))
				newlist.add(quoteList.get(i));
			if (categoryCB[1].isSelected() 
					&& quoteList.get(i).thisQuote.getCategory()==1
					&& !newlist.contains(quoteList.get(i)))
				newlist.add(quoteList.get(i));
			if (categoryCB[2].isSelected() 
					&& quoteList.get(i).thisQuote.getCategory()==2
					&& !newlist.contains(quoteList.get(i)))
				newlist.add(quoteList.get(i));
		}
		quoteList = newlist;
	}
	
	public void refresh(){
	
	}
}
