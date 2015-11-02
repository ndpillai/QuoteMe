package client;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateUserGUI extends JPanel {
	public JTextField usernameTF, passwordTF, confirmPasswordTF;
	public JButton createUserButton, connectToFacebookButton;
	
	public CreateUserGUI () {
		// TODO
	}
	
	private void initializeVariables() {
		usernameTF = new JTextField();
		passwordTF = new JTextField();
		confirmPasswordTF = new JTextField();
		createUserButton = new JButton();
		connectToFacebookButton = new JButton();	// TODO facebook or no?
	}
	
	private void createGUI() {
		// TODO
	}
	
	private void addEvents() {
		// TODO
	}
	
	public void createNewUser() {
		// TODO
	}
	
	public void createNewFacebookUser() {
		// TODO decide if we need this or not
	}
	
	public void addNamesToMap(String username) {
		// TODO what map are we talking about? Oh are we creating a new User and sending it to the DataManager?
		// How many names? 
	}
}
