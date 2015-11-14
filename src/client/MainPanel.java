package client;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainPanel extends JPanel {
	
	private JTabbedPane menuTabs;
	private ApproveQuoteGUI approveQuotePanel;
	public FeedPageGUI feed;
	public PostQuoteGUI postQuote;
	public ProfilePageGUI profilePage;
	public NotificationGUI notifications;
	private QuoteGUI quotePanel;
	private WriteQuotePanel writeQuotePanel;
	
	private ClientPanel clientPanel;

	public User currentuser;
	
	public MainPanel(ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
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
