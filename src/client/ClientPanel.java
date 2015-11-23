package client;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class ClientPanel extends JPanel {
	private static final long serialVersionUID = 1;
	
	// Elements of the client panel
	// The panel will switch between these
	private CreateUserGUI createUserPanel;
	private HomePageGUI homePagePanel;
	private LoginGUI loginPanel;
	private MainPanel mainPanel;
	
	// Takes in input from the collected info from the other panels
	public QuoteMeClient quoteMeClient;
	private User currentUser;
	
	public ClientPanel(QuoteMeClient quoteMeClient)
	{
		// The home page
		homePagePanel = new HomePageGUI(this);
		this.quoteMeClient = quoteMeClient;
		
		//Set up the panel to display
		setLayout(new BorderLayout());
		add(homePagePanel);
		refreshComponents();
	}

	private void refreshComponents() {
		createUserPanel = new CreateUserGUI(this);
		loginPanel = new LoginGUI(this);
		mainPanel = new MainPanel(this);
	}
	
	public void moveToLoginPanel() {
		ClientPanel.this.removeAll();
		loginPanel.clearFields();
		ClientPanel.this.add(loginPanel);
		ClientPanel.this.revalidate();
		ClientPanel.this.repaint();
	}
	
	public void moveToCreateUserPanel() {
		ClientPanel.this.removeAll();
		createUserPanel.clearFields();
		ClientPanel.this.add(createUserPanel);
		ClientPanel.this.revalidate();
		ClientPanel.this.repaint();
	}
	
	public void moveToHomePanel() {
		ClientPanel.this.removeAll();
		//refreshComponents();
		ClientPanel.this.add(homePagePanel);
		ClientPanel.this.revalidate();
		ClientPanel.this.repaint();
	}
	
	public void moveToMainPanel() {
		ClientPanel.this.removeAll();
		ClientPanel.this.add(mainPanel);
		ClientPanel.this.revalidate();
		ClientPanel.this.repaint();
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
		mainPanel.setCurrentUser(user);
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void refreshFeed(){
		mainPanel.refreshFeed();
	}
	
/*	public void moveToProfilePagePanel(User user) {
		ProfilePageGUI ppg = new ProfilePageGUI(this,user);
		ClientPanel.this.removeAll();
		ClientPanel.this.add(ppg);
		ClientPanel.this.revalidate();
	} */
}
