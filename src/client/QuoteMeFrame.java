package client;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

/*
 * 
 * Member variables added:
 * CardLayout layout
 * Container pane
 * 
	Methods added:
	initializeComponents
	create GUI
	addEvents
	main
	all of the panels
*/

public class QuoteMeFrame extends JFrame {
	
	private DataManager dataManager;
	private CardLayout layout;
	private Container pane;
	
	// Panels we can change to
	private HomePageGUI homePagePanel;
	private LoginGUI loginPanel;
	private CreateUserGUI createUserPanel;
	
	private MainPanel mainPanel;
	/* All of these are already in mainPanel
	private FeedPageGUI feedPagePanel;
	private PostQuoteGUI postQuotePanel;
	private ProfilePageGUI profilePagePanel;
	private NotificationGUI notificationPanel;
	private QuoteGUI quotePanel;*/
	
	private WriteQuotePanel writeQuotePanel;
	private ApproveQuoteGUI approveQuotePanel;
	private ExpandedQuoteGUI expandedQuotePanel;
	
	public QuoteMeFrame() {
		super("QuoteMe");
		initializeComponents();
		createGUI();
		addEvents();
		displayTab("Main Menu");
	}
	
	private void initializeComponents() {
		layout = new CardLayout();
		pane = getContentPane();
		pane.setLayout(layout);
		
		homePagePanel = new HomePageGUI();
		loginPanel = new LoginGUI();
		createUserPanel = new CreateUserGUI();
		mainPanel = new MainPanel();
		//writeQuotePanel = new WriteQuotePanel(); Not sure where we are getting the info to populate these
		//approveQuotePanel = new ApproveQuoteGUI();
		//expandedQuotePanel = new ExpandedQuoteGUI();
		
	}
	
	private void createGUI() {
		setSize(450, 700);
		setLocation(400,100);
		setResizable(false);
		pane.add(homePagePanel, "Home Page");
		pane.add(loginPanel, "Login Page");
		pane.add(createUserPanel, "Create User Page");
		pane.add(mainPanel, "Main Page");
		displayTab("Home Page");
	}
	
	private void addEvents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void displayTab(String name) {
		layout.show(this.getContentPane(), name);
	}
	
	private void refreshPanels() {
		
	}
	
	public static void main(String [] args) {
		QuoteMeFrame qmf = new QuoteMeFrame();
		qmf.setVisible(true);
	}
}
