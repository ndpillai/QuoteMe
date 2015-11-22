package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resources.CustomListeners;
import resources.Images;

/*
added: removeTextAdapter
removed: anything with facebook
 */

public class CreateUserGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JTextField firstnameTF, lastnameTF, emailTF, usernameTF, passwordTF, confirmPasswordTF;
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
		emailTF = new JTextField("Enter email address");
		usernameTF = new JTextField("Enter desired username");
		passwordTF = new JTextField("Enter password");
		confirmPasswordTF = new JTextField("Confirm password");
		createUserButton = new JButton("Create User");
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		JPanel createUserPanel = new JPanel();
        createUserPanel.setBackground(new Color(204, 0, 0, 123));

		createUserPanel.setLayout(new BoxLayout(createUserPanel, BoxLayout.Y_AXIS));
		//usernameTF.setSize(new Dimension(20, usernameTF.getPreferredSize().height));
		createUserPanel.add(firstnameTF);
		createUserPanel.add(lastnameTF);
		createUserPanel.add(emailTF);
		createUserPanel.add(usernameTF);
		createUserPanel.add(passwordTF);
		createUserPanel.add(confirmPasswordTF);
		createUserPanel.add(createUserButton);
		add(createUserPanel, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		firstnameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(firstnameTF,"Enter first name"));
		lastnameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(lastnameTF,"Enter last name"));
		emailTF.addFocusListener(new CustomListeners.RemoveTextAdapter(emailTF,"Enter email address"));
		usernameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(usernameTF,"Enter desired username"));
		passwordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(passwordTF,"Enter password"));
		confirmPasswordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(confirmPasswordTF,"Confirm password"));
	
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	

			System.out.println("ClientPanel null?: " + (CreateUserGUI.this.clientPanel == null));
			System.out.println("QuoteMeClient null?: " + (CreateUserGUI.this.clientPanel.quoteMeClient == null));
			System.out.println("DataManager null?: " + (CreateUserGUI.this.clientPanel.quoteMeClient.dataManager == null));
			if (!CreateUserGUI.this.clientPanel.quoteMeClient.dataManager.hasName(usernameTF.getText())) { // Check name
				if (!CreateUserGUI.this.clientPanel.quoteMeClient.dataManager.hasEmail(emailTF.getText())) { // Check email
					if (passwordTF.getText().equals(confirmPasswordTF.getText())) {
						User newUser = new User(	// Create new user
								firstnameTF.getText(), 
								lastnameTF.getText(), 
								usernameTF.getText(), 
								emailTF.getText(),
								passwordTF.getText(),
								new Date());
						printInputs();
						CreateUserGUI.this.clientPanel.quoteMeClient.dataManager.addUser(newUser);
						clientPanel.quoteMeClient.sendObject(newUser);
						
						JOptionPane.showMessageDialog(
								CreateUserGUI.this, 
								"Welcome to the QuoteMe Universe!\nLogin with your username and password to access QuoteMe universe.", 
								"Account successfully created! ",  
								JOptionPane.PLAIN_MESSAGE);
						clientPanel.moveToLoginPanel();
						SendEmail.sendWelcomeEmail(emailTF.getText(), firstnameTF.getText(), usernameTF.getText());
					}
					else {
						System.out.println("Passwords don't match!");
						JOptionPane.showMessageDialog(
								CreateUserGUI.this, 
								"I'm sorry, passwords don't match.\nTry re-entering password to create your profile.", 
								"Create User Error",  
								JOptionPane.PLAIN_MESSAGE);
					}
				}
				else {
					System.out.println("This email is already taken. Please try another!");
					JOptionPane.showMessageDialog(
							CreateUserGUI.this, 
							"I'm sorry, this email has already been registered!\nPick a new email to create your profile.", 
							"Create User Error",  
							JOptionPane.PLAIN_MESSAGE);
					//emailTF.setText("");
				}
			}
			else {
				System.out.println("This username is already taken. Please try another!");
				JOptionPane.showMessageDialog(
						CreateUserGUI.this, 
						"I'm sorry, this username is already taken!\nChoose a different username to create your profile.", 
						"Create User Error",  
						JOptionPane.PLAIN_MESSAGE);
				//usernameTF.setText("");
			}

			/*****For testing purposes only*****
			if (passwordTF.getText().equals(confirmPasswordTF.getText())) {
				System.out.println("valid");
				JOptionPane.showMessageDialog(
						CreateUserGUI.this, 
						"Welcome to the QuoteMe Universe!\nLogin with your username and password to access QuoteMe universe.", 
						"Account successfully created! ",  
						JOptionPane.PLAIN_MESSAGE);
				clientPanel.moveToLoginPanel();
				SendEmail.sendWelcomeEmail(emailTF.getText(), firstnameTF.getText(), usernameTF.getText());
			}
			else {
				System.out.println("invalid");
			}*/
			}
		});
	}
	
	private void printInputs() {
		System.out.println("First Name: " + firstnameTF.getText());
		System.out.println("Last Name: " + lastnameTF.getText());
		System.out.println("Email: " + emailTF.getText());
		System.out.println("Username: " + usernameTF.getText());
		System.out.println("Password: " + passwordTF.getText());
		System.out.println("Confirm Password: " + confirmPasswordTF.getText());
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			//Image panelBackground = ImageIO.read(new File(Images.plainHomePageBackground));
			Image panelBackground = ImageIO.read(new File(Images.parrotHomePageBackgroundPixellated19));
			g.drawImage(panelBackground, 0, 0, getWidth(), getHeight(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
