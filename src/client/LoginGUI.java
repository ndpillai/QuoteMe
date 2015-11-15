package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 2335752822050869549L;
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
				if (loginIsValid()) {
//					goToFeed(DataManager.getNameMap().get(usernameTF.getText()));
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
	
	private boolean loginIsValid() {
		if (DataManager.getNameMap().containsKey(usernameTF.getText())) {
			if (DataManager.getNameMap().get(usernameTF.getText()).getPassword().equals(passwordTF.getText())) {
				System.out.println("Username and password are correct, congratulations. "
						+ "Welcome to the most exclusive and innovative online experience of your life.");
				return true;
			}
			else {
				System.out.println("Sorry, the entered password does not match the username. Please try again.");
				usernameTF.setText("");
				passwordTF.setText("");
				return false;
			}
		}
		else {
			System.out.println("The entered username does not exist. And yet. I'm going to let you in. Have fun! But not too much fun.");
			usernameTF.setText("");
			return true;
//			return false;
		}
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
