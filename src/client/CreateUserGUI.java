package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
added: removeTextAdapter
removed: anything with facebook
 */

public class CreateUserGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JTextField firstnameTF, lastnameTF, usernameTF, passwordTF, confirmPasswordTF;
	public JButton createUserButton;
	
	private ClientPanel clientPanel;
	
	public CreateUserGUI (ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		firstnameTF = new JTextField("Enter first name");
		lastnameTF = new JTextField("Enter last name");
		usernameTF = new JTextField("Enter desired username");
		passwordTF = new JTextField("Enter password");
		confirmPasswordTF = new JTextField("Confirm password");
		createUserButton = new JButton("Create User");
	}
	
	private void createGUI() {
		setLayout(null);
		add(firstnameTF);
		add(lastnameTF);
		add(usernameTF);
		add(passwordTF);
		add(confirmPasswordTF);
		add(createUserButton);
		
		firstnameTF.setBounds(100,100,250,20);
		lastnameTF.setBounds(100,175,250,20);
		usernameTF.setBounds(100,250,250,20);
		passwordTF.setBounds(100,325,250,20);
		confirmPasswordTF.setBounds(100,400,250,20);
		createUserButton.setBounds(150,500,150,50);
	}
	
	private void addEvents() {
		firstnameTF.addFocusListener(new RemoveTextAdapter(firstnameTF,"Enter first name"));
		lastnameTF.addFocusListener(new RemoveTextAdapter(lastnameTF,"Enter last name"));
		usernameTF.addFocusListener(new RemoveTextAdapter(usernameTF,"Enter desired username"));
		passwordTF.addFocusListener(new RemoveTextAdapter(passwordTF,"Enter password"));
		confirmPasswordTF.addFocusListener(new RemoveTextAdapter(confirmPasswordTF,"Confirm password"));
	
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			
//			*****Actual logic for User creation*****
//			if (!DataManager.getNameMap().containsKey(usernameTF.getText())) {
//				User newUser = new User(firstnameTF.getText(), lastnameTF.getText(), usernameTF.getText(), "email", 
//						passwordTF.getText(), new Date());
//				DataManager.addUser(newUser);
//				clientPanel.moveToLoginPanel();
//			}
//			else {
//				System.out.println("This username is already taken. Please try another!");
//				usernameTF.setText("");
//			}

//			*****For testing purposes only*****
			if (passwordTF.getText().equals(confirmPasswordTF.getText())) {
				System.out.println("valid");
				clientPanel.moveToLoginPanel();
			}
			else {
				System.out.println("invalid");
			}
			
			}
		});
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
