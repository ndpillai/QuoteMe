package client;

import java.util.Vector;

import javax.swing.JPanel;

public class SearchResultsGUI extends JPanel {
	private Vector<User> users;
	private Vector<Quote> quotes;
	private MainPanel mp;

	public SearchResultsGUI(Vector<User> u, Vector<Quote> q, MainPanel mp) {
		users = u;
		quotes = q;
		this.mp = mp;
		createGUI();
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		
	}
}
