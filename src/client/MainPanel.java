package client;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainPanel extends JPanel {
	
	private JTabbedPane menuTabs;
	public FeedPageGUI feed;
	public PostQuoteGUI postQuote;
	public ProfilePageGUI profilePage;
	public NotificationGUI notifications;
	public User currentuser;
	
	public MainPanel() {
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {

	}
	
	private void createGUI()
	{
		
	}
	
	private void addEvents()
	{
		
	}
	
	public void main(String[] args)
	{
		
	}
}
