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
		if (dataManager.nameMap.containsKey(usernameTF.getText())) {
			if (dataManager.nameMap.get(usernameTF.getText()).getPassword().equals(passwordTF.getText())) {
				return true;
			}
		}
		
		return false;
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
