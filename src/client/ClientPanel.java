package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ClientPanel extends JPanel {
	private static final long serialVersionUID = 1;
	
	// Elements of the client panel
	// The panel will switch between these
	private ApproveQuoteGUI approveQuotePanel;
	private CreateUserGUI createUserPanel;
	private FeedPageGUI feedPagePanel;
	private HomePageGUI homePagePanel;
	private LoginGUI loginPanel;
	private MainPanel mainPanel;
	private ExpandedQuoteGUI expandedQuotePanel;
	private NotificationGUI notificationPanel;
	private PostQuoteGUI postQuotePanel;
	private ProfilePageGUI profilePagePanel;
	private QuoteGUI quotePanel;
	private WriteQuotePanel writeQuotePanel;
	
	// Takes in input from the collected info from the other panels
	private DataManager dataManager;
	public QuoteMeClient quoteMeClient;
	private User currentUser;
	
	{
		// The home page
		homePagePanel = new HomePageGUI(this);
		
		//Set up the panel to display
		setLayout(new BorderLayout());
		add(homePagePanel);
		refreshComponents();
	}

	private void refreshComponents() {
		approveQuotePanel = null;
		createUserPanel = new CreateUserGUI();
		feedPagePanel = new FeedPageGUI();
		loginPanel = new LoginGUI();
		mainPanel = new MainPanel();
		expandedQuotePanel = null;
		notificationPanel = new NotificationGUI();
		postQuotePanel = new PostQuoteGUI();
		profilePagePanel = null;
		quotePanel = null;
		writeQuotePanel = new WriteQuotePanel();
	}
	
	public void moveToLoginPanel() {
		ClientPanel.this.removeAll();
		ClientPanel.this.add(loginPanel);
		ClientPanel.this.revalidate();
	}
	
	public void moveToCreateUserPanel() {
		ClientPanel.this.removeAll();
		ClientPanel.this.add(createUserPanel);
		ClientPanel.this.revalidate();
	}
	
	public void moveToMainPanel() {
		ClientPanel.this.removeAll();
		ClientPanel.this.add(mainPanel);
		ClientPanel.this.revalidate();
	}
	
	public void moveToProfilePagePanel(User user) {
		ProfilePageGUI ppg = new ProfilePageGUI(user);
		ClientPanel.this.removeAll();
		ClientPanel.this.add(ppg);
		ClientPanel.this.revalidate();
	}
}
