package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.CreateUserGUI.RemoveTextAdapter;

public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 2335752822050869549L;
	public DataManager dataManager;
	private JTextField usernameTF, passwordTF;
	private JButton loginButton;
//	private JButton forgotUserButton;
	
	private ClientPanel clientPanel;
	
	// 450 x 700
	
	public LoginGUI(ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		usernameTF = new JTextField("Enter username");
		passwordTF = new JTextField("Enter password");
		loginButton = new JButton("Login");
	//	confirmPasswordTF = new JTextField("Confirm password");
	//	createUserButton = new JButton("Create User");
	}
	
	private void createGUI() {
		setLayout(null);
		add(usernameTF);
		add(passwordTF);
		add(loginButton);
		
		usernameTF.setBounds(100,150,250,20);
		passwordTF.setBounds(100,300,250,20);
		loginButton.setBounds(150,500,150,50);
	}
	
	private void addEvents() {
		usernameTF.addFocusListener(new RemoveTextAdapter(usernameTF,"Enter username"));
		passwordTF.addFocusListener(new RemoveTextAdapter(passwordTF,"Enter password"));
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (userIsValid()) {
					// Commented out for testing purposes
					//goToFeed(dataManager.nameMap.get(usernameTF.getText()));
					goToFeed(new User());
				}
			}
		});
		
	/*	forgotPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Forgot Password Button pressed!");
				// go to new Forgot Password page
			}
		}); */
	}
	
	private void goToFeed(User user) {
		clientPanel.moveToMainPanel();
	}
	
	private boolean userIsValid() {
		/* COMMENTED OUT FOR TESTING
		if (dataManager.nameMap.containsKey(usernameTF.getText())) {
			if (dataManager.nameMap.get(usernameTF.getText()).getPassword().equals(passwordTF.getText())) {
				return true;
			}
		}
		
		return false;*/
		return true;
	}
	
	class RemoveTextAdapter extends FocusAdapter{
		String textToPlace;
		JTextField jtf;
		
		public RemoveTextAdapter(JTextField jtf, String s) {
			this.jtf = jtf;
			textToPlace = s;
		}
		public void focusGained(FocusEvent e) {
			if (jtf.getText().equals(textToPlace))
				jtf.setText(""); 
		}
		
		public void focusLost(FocusEvent e) {
			if (jtf.getText().isEmpty())
				jtf.setText(textToPlace);
		}
	}
}
