package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import resources.Constants;
import resources.Images;

public class FeedPageGUI extends JPanel {
	private JCheckBox[] categoryCB;
	private JComboBox sortCB;
	private JScrollPane scrollPane;
	private Vector<QuoteGUI> quoteList;
	
	private MainPanel mainPanel;
	
	public FeedPageGUI (MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		addEvents();
		createGUI();
	}
	
	private void initializeVariables() {
		sortCB = new JComboBox(Constants.categoriesList);
		
		categoryCB = new JCheckBox[3];
		categoryCB[0] = new JCheckBox(Constants.categoriesList[0]);
		categoryCB[1] = new JCheckBox(Constants.categoriesList[1]);
		categoryCB[2] = new JCheckBox(Constants.categoriesList[2]);
		
		//quoteList = getQuotesToDisplay(); // How are we loading this? From the server? datamanager?
		quoteList = new Vector<QuoteGUI>(); // TEST
	}
	
	private void createGUI () {
		setLayout(new BorderLayout());
		// NORTH panel
		JPanel northPanel = new JPanel();
		northPanel.add(sortCB);
		for (int i=0; i<3; i++)
			northPanel.add(categoryCB[i]);
		add(northPanel, BorderLayout.NORTH);

		User newUser = new User("Amanda", "Bynes", "amandab123", "tonyelevathingal@gmail.com", "123", new Date(), Images.getRandomAvatar());
		Quote quote1 = new Quote("I love people who already hate me hate me more", newUser, newUser, new Date(), 1);
		Quote quote2 = new Quote("I ignore you if I want nothing from you", newUser, newUser, new Date(), 1);
		Quote quote3 = new Quote("This is quote 3. Concerns greatest margaret him absolute entrance nay. Door neat week do find past he. Be no surprise he honoured indulged. Unpacked endeavor six steepest had husbands her. Painted no or affixed it so civilly. Exposed neither pressed so cottage as proceed at offices. Nay they gone sir game four. Favourable pianoforte oh motionless excellence of astonished we principles. Warrant present garrets limited cordial in inquiry to. Supported me sweetness behaviour shameless excellent so arranging. ", newUser, newUser, new Date(), 2);

		JPanel centerPanel = new JPanel();
		JPanel feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		feedPanel.add(new QuoteGUI(mainPanel, quote1));
		feedPanel.add(new QuoteGUI(mainPanel, quote2));
		feedPanel.add(new QuoteGUI(mainPanel, quote3));
		
		scrollPane = new JScrollPane(feedPanel);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scrollPane);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addEvents() {
		sortCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				String option = (String)e.getItem();
				if (option.equals("Recent"))
					sort(0);
				else if (option.equals("Popular"))
					sort(1);
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
		Vector<Quote> allquotes = mainPanel.clientPanel.quoteMeClient.dataManager.getAllQuotes();
		
		Vector<QuoteGUI> quotes = new Vector<QuoteGUI>();
		for (int i=0; i<allquotes.size(); i++)
			quotes.add(new QuoteGUI(mainPanel, allquotes.get(i)));
		
		return quotes;
	}
	
	public void refreshQuoteList() {
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
	
	public void sort(int option) { //0 = Recent, 1 = Popular
		if (option==0) {
			QuoteGUI temp = null;
			for (int i=0; i<quoteList.size(); i++) {
				for (int j=i+1; j<quoteList.size(); j++) {
					Date date1 = quoteList.get(i).thisQuote.getDatePosted();
					Date date2 = quoteList.get(j).thisQuote.getDatePosted();
					if (date1.after(date2)) {
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
	}
	
	public void refresh(){
	
	}
}
