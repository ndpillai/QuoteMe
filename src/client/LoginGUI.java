package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 2335752822050869549L;
	public DataManager dataManager;
	public JTextField usernameTF, passwordTF;
	public JButton loginButton, forgotPasswordButton;
	
	private ClientPanel clientPanel;
	
	// 450 x 700
	
	public LoginGUI(ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		usernameTF = new JTextField();
		passwordTF = new JTextField();
		loginButton = new JButton();
		forgotPasswordButton = new JButton();
	}
	
	private void createGUI() {
		setLayout(null);
		
		JLabel loginPageLabel = new JLabel("LOGIN");
		loginPageLabel.setFont(new Font("Arial", Font.BOLD, 28));
		loginPageLabel.setSize(500, 100);
		loginPageLabel.setLocation(225, 25);
		add(loginPageLabel);
		
		usernameTF.setLocation(100, 100);
		usernameTF.setSize(300, 50);
		add(usernameTF);
		
		passwordTF.setLocation(100, 300);
		passwordTF.setSize(300, 50);
		add(passwordTF);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
		usernameLabel.setSize(200, 100);
		usernameLabel.setLocation(10, 75);
		add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
		passwordLabel.setSize(200, 100);
		passwordLabel.setLocation(10, 275);
		add(passwordLabel);
	}
	
	private void addEvents() {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (userIsValid()) {
					goToFeed(dataManager.nameMap.get(usernameTF.getText()));
				}
			}
		});
		
		forgotPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Forgot Password Button pressed!");
				// go to new Forgot Password page
			}
		});
	}
	
	private void goToFeed(User user) {
		clientPanel.moveToMainPanel();
	}
	
	private boolean userIsValid() {
		if (dataManager.nameMap.containsKey(usernameTF.getText())) {
			if (dataManager.nameMap.get(usernameTF.getText()).getPassword().equals(passwordTF.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
}
