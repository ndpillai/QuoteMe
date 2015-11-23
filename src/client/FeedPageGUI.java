package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.FontLibrary;
import resources.Constants;

public class FeedPageGUI extends JPanel {
	private JCheckBox[] categoryCB;
	private JComboBox sortCB;
	private JScrollPane scrollPane;
	public Vector<QuoteGUI> quoteList;
	
	private MainPanel mainPanel;
	
	private JPanel feedPanel;
	
	public FeedPageGUI (MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		addEvents();
		createGUI();
	}
	
	private void initializeVariables() {
		String[] options = {"Recent","Popular"};
		sortCB = new JComboBox(options);
		sortCB.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		
		categoryCB = new JCheckBox[3];
		categoryCB[0] = new JCheckBox(Constants.categoriesList[0]);
		categoryCB[0].setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		categoryCB[1] = new JCheckBox(Constants.categoriesList[1]);
		categoryCB[1].setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		categoryCB[2] = new JCheckBox(Constants.categoriesList[2]);
		categoryCB[2].setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 14));
		
		quoteList = getQuotesToDisplay(); // How are we loading this? From the server? datamanager?
		//	quoteList = new Vector<QuoteGUI>(); // TEST
	}
	
	private void createGUI () {
		setLayout(new BorderLayout());
		// NORTH panel
		JPanel northPanel = new JPanel();
		northPanel.add(sortCB);
		for (int i=0; i<3; i++)
			northPanel.add(categoryCB[i]);
		add(northPanel, BorderLayout.NORTH);
		
		feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		refreshQuoteList();
		scrollPane = new JScrollPane(feedPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
	/*	JPanel centerPanel = new JPanel();
		feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		for (int i=0; i<quoteList.size(); i++)
			feedPanel.add(quoteList.get(i));
		
		JScrollPane = new JScrollPane(feedPanel);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scrollPane); 
		add(centerPanel, BorderLayout.CENTER);
		
		sort(sortCB.getSelectedIndex()); */
	}
	
	private void addEvents() {
		sortCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				sort();
			}
		});
		
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
		/*
		DataManager dm = mainPanel.clientPanel.quoteMeClient.dataManager;
		HashMap<String, Vector<Quote> > quoteMap = dm.getSpeakerToQuoteMap();
		
		Vector<QuoteGUI> quotes = new Vector<QuoteGUI>();
		User u = mainPanel.clientPanel.getCurrentUser();

		if (u!=null) {
		Vector<User> users = u.getUsersWeFollow();
		System.out.println("User isn't null " + users.size());
		
		for (int i=0; i< users.size(); i++) {
			System.out.println(users.get(i).getUserName());
			
			if (quoteMap.containsKey(users.get(i).getUserName())) {
				System.out.println(users.get(i).getUserName() + " exists");

				for (int j=0; j<quoteMap.get(users.get(i).getUserName()).size(); j++)
					quotes.add(new QuoteGUI(mainPanel, quoteMap.get(users.get(i).getUserName()).get(j)));
			}
			}
		}
		
		System.out.println("refreshing quotes: "+quotes.size()); 
		return quotes; */
		return new Vector<QuoteGUI>();
	}
	
	//with updated quoteList
	public void repopulate() {
		feedPanel.removeAll();

		for (int i=0; i<quoteList.size(); i++)
			feedPanel.add(quoteList.get(i));
		
	//	feedPanel.setPreferredSize(new Dimension(quoteList.get(0).getWidth(), quoteList.get(0).getHeight()*quoteList.size()));
		revalidate();
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
			
			//if everything is deselected
			if (!categoryCB[0].isSelected() && !categoryCB[1].isSelected() && !categoryCB[2].isSelected())
			{
				newlist = quoteList;
			}
		}

		quoteList = newlist;
		sort();
		
		repopulate();
	}
	
	public void sort() { //0 = Recent, 1 = Popular
		int option = sortCB.getSelectedIndex();
		System.out.println("It's sortin time " + option + " " + quoteList.size());
		if (option==0) {
			QuoteGUI temp = null;
			for (int i=0; i<quoteList.size(); i++) {
				for (int j=i+1; j<quoteList.size(); j++) {
					Date date1 = quoteList.get(i).thisQuote.getDatePosted();
					Date date2 = quoteList.get(j).thisQuote.getDatePosted();
					if (date1.before(date2)) {
						temp = quoteList.get(i);
						quoteList.set(i,quoteList.get(j));
						quoteList.set(j, temp);
					}
				}
			}
		}
		else {
			QuoteGUI temp = null;
			for (int i=0; i<quoteList.size(); i++) {
				for (int j=i+1; j<quoteList.size(); j++) {
					int upQuotes1 = quoteList.get(i).thisQuote.getUpQuotes();
					int upQuotes2 = quoteList.get(j).thisQuote.getUpQuotes();
					if (upQuotes1<upQuotes2) {
						temp = quoteList.get(i);
						quoteList.set(i,quoteList.get(j));
						quoteList.set(j, temp);
					}
				}
			}
		}
		repopulate();
		
	}
}
