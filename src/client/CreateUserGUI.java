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
	public JTextField usernameTF, passwordTF, confirmPasswordTF;
	public JButton createUserButton;
	
	private ClientPanel clientPanel;
	
	public CreateUserGUI (ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		usernameTF = new JTextField("Enter username");
		passwordTF = new JTextField("Enter password");
		confirmPasswordTF = new JTextField("Confirm password");
		createUserButton = new JButton("Create User");
	}
	
	private void createGUI() {
		setLayout(null);
		add(usernameTF);
		add(passwordTF);
		add(confirmPasswordTF);
		add(createUserButton);
		
		usernameTF.setBounds(100,100,250,20);
		passwordTF.setBounds(100,200,250,20);
		confirmPasswordTF.setBounds(100,300,250,20);
		createUserButton.setBounds(150,500,150,50);
	}
	
	private void addEvents() {
		usernameTF.addFocusListener(new RemoveTextAdapter(usernameTF,"Enter username"));
		passwordTF.addFocusListener(new RemoveTextAdapter(passwordTF,"Enter password"));
		confirmPasswordTF.addFocusListener(new RemoveTextAdapter(confirmPasswordTF,"Confirm password"));
	
		createUserButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			//Add in all the stuff to check if a username is in the database
				//if (usernameTF.getText() is valid)
				if (passwordTF.getText().equals(confirmPasswordTF.getText()))
				{
					System.out.println("valid"); //for testing
					//add user to manager
					clientPanel.moveToLoginPanel();
				}
				else
					System.out.println("invalid"); //for testing
			}
		});
	}
	
	public void createNewUser() {
		// TODO
	}
	
	public void addNamesToMap(String username) {
		// TODO what map are we talking about? Oh are we creating a new User and sending it to the DataManager?
		// How many names? 
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
