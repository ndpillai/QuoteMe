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
	
	{
		// The home page
		homePagePanel = new HomePageGUI(this);
		
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
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
/*	public void moveToProfilePagePanel(User user) {
		ProfilePageGUI ppg = new ProfilePageGUI(this,user);
		ClientPanel.this.removeAll();
		ClientPanel.this.add(ppg);
		ClientPanel.this.revalidate();
	} */
}
