package client;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import custom.QuoteMeLabel;

public class SearchResultsGUI extends JPanel {
	private static final long serialVersionUID = -4746601401215212911L;
	private Vector<User> users;
	private Vector<Quote> quotes;
	
	private Vector<UserResultGUI> userResults;
	private Vector<QuoteGUI> quoteResults;
	private JPanel northPanel;
	private JLabel searchLabel;
	
	private MainPanel mp;
	
	private JPanel outerPanel;
	private JScrollPane jsp;


	public SearchResultsGUI(Vector<User> users, Vector<Quote> quotes, MainPanel mp) {
		this.users = users;
		this.quotes = quotes;
		this.mp = mp;
		initializeComponents();
		createGUI();
	}

	private void initializeComponents() {
		
		northPanel = new JPanel();
		searchLabel = new QuoteMeLabel("Showing results for: " + mp.getSearchInput());
		outerPanel = new JPanel();
		userResults = new Vector<UserResultGUI>();
		
		if (users != null) {
			for (User u : users) {
				UserResultGUI userResult = new UserResultGUI(mp, u);
				userResults.add(userResult);
			}
		}
		
		quoteResults = new Vector<QuoteGUI>();
		if (quotes != null) {
			for (Quote q : quotes) {
				QuoteGUI quoteResult = new QuoteGUI(mp, q);
				quoteResults.add(quoteResult);
			}
		}
	}

	private void createGUI() {
		setLayout(new BorderLayout());
		northPanel.add(searchLabel);
		add(northPanel, BorderLayout.NORTH);
		
		JPanel resultsPanel = new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
		// Add stuff to resultsPanel
		for (UserResultGUI u : userResults) {
			resultsPanel.add(u);
		}
		for (QuoteGUI q : quoteResults) {
			resultsPanel.add(q);
		}
		
		if (resultsPanel.getSize().getHeight() < 5) {
			outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
			outerPanel.add(resultsPanel);
			add(outerPanel, BorderLayout.NORTH);
		} else {
			jsp = new JScrollPane(resultsPanel);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			add(jsp, BorderLayout.CENTER);
		}
	}
}
