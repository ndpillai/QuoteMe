package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import custom.QuoteMeButton;
import library.ImageLibrary;
import resources.CustomListeners;
import resources.Images;


public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 2335752822050869549L;
	private JTextField usernameTF;
	private JPasswordField passwordTF;
	private JButton loginButton;
	private JButton forgotUserButton;
	
	private ClientPanel clientPanel;
	
	// 450 x 700
	
	public LoginGUI(ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
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
	
	private void initializeVariables() {
		usernameTF = new JTextField("Enter username");
		loginButton = new QuoteMeButton(
				"Login",
				ImageLibrary.getImage("img/buttons/green_button00.png"),
				ImageLibrary.getImage("img/buttons/green_button01.png"),
				22
				);
		passwordTF = new JPasswordField("Enter password");
		passwordTF.setEchoChar((char) 0);
		loginButton = new JButton("Login");
		forgotUserButton = new JButton("Forgot Username / Password");
	}
	
	private void createGUI() {
		/*
		setLayout(null);
		add(usernameTF);
		add(passwordTF);
		add(loginButton);
		
		usernameTF.setBounds(100,150,250,20);
		passwordTF.setBounds(100,300,250,20);
		loginButton.setBounds(150,500,150,50);*/
		
		// JUST TESTING A NEW GUI FOR LOGIN, didn't delete previous gui
		setLayout(new BorderLayout());
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(new Color(204, 0, 0, 123));

		// BELOW: trying to make username TF not go entire length of BoxLayout panel
		usernameTF.setSize(new Dimension(20, usernameTF.getPreferredSize().height));
		loginPanel.add(usernameTF);
		loginPanel.add(passwordTF);
		loginPanel.add(loginButton);
		loginPanel.add(forgotUserButton);
		add(loginPanel, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		usernameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(usernameTF,"Enter username"));
		passwordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(passwordTF,"Enter password"));
		passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordTF.setEchoChar('•');
				
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				checkIfLoginIsValid();
				/*
				if (loginIsValid()) {
//					goToFeed(DataManager.getNameMap().get(usernameTF.getText()));
					//goToFeed(new User());
					clientPanel.moveToMainPanel();
				}*/
			}
		});
		
		forgotUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Forgot Password Button pressed!");
				String email = JOptionPane.showInputDialog(LoginGUI.this, "Enter an email for username and password recovery:", "Recover your username/password");
				checkForgotPasswordEmail(email);
				
			}
		}); 
	}
	
	private void checkForgotPasswordEmail(String email) {
		if (email == null) { // Probably chose cancel
			System.out.println("Chose cancel?");
		}
		
		else { // Given an email string
			User user = LoginGUI.this.clientPanel.quoteMeClient.dataManager.getUserFromEmail(email);
			if (user != null) {	// Email exists in our dataManager
				/* ATTEMPT TO INCLUDE A CUTE PICTURE IN THE JOPTIONPANE POPUP
				JOptionPane.showConfirmDialog(LoginGUI.this,
						"Your login details will be sent to: " + emailAddress,
						"Username/Password Recover",
						JOptionPane.PLAIN_MESSAGE, null,
						new ImageIcon(Images.parrotAvatarRedPixellated));*/
				
				JOptionPane.showMessageDialog(
						LoginGUI.this, 
						"Your login details will be sent to: " + email,
						"Username/Password Recovery", 
						JOptionPane.PLAIN_MESSAGE);
				
				/* Hardcoded test email
				SendEmail.sendRecoveryEmail(
						email, 
						"Simone", 
						"lgduckie", 
						"1234");*/
				
				SendEmail.sendRecoveryEmail(
						email, 
						user.getFirstName(), 
						user.getUserName(), 
						user.getPassword());
			}
			else {	// Email does not exist in our dataManager
				JOptionPane.showMessageDialog(
						LoginGUI.this, 
						"I'm sorry, this email does not exist in our database.\nCheck your spelling to make sure you input an existing email address.", 
						"Email Validation Error",  
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	private void checkIfLoginIsValid() {
		User user = this.clientPanel.quoteMeClient.dataManager.getUserFromUserName(usernameTF.getText());
		if (user != null) {
			
			String password = new String(passwordTF.getPassword());
			System.out.println(password + " " + user.getPassword());
			
			if (password.equals(user.getPassword())) {
				
				clientPanel.setCurrentUser(user);
				clientPanel.moveToMainPanel();
			}
			else { // Wrong password, ERROR
				JOptionPane.showMessageDialog(
						LoginGUI.this, 
						"I'm sorry, password and username do not match. Please try logging in again.\n", 
						"Login Error",  
						JOptionPane.PLAIN_MESSAGE);
				clearFields();
			}
			
		}
		else { // User does not exist, ERROR
			JOptionPane.showMessageDialog(
					LoginGUI.this, 
					"I'm sorry, the entered username/user does not exist.\n", 
					"Login Error",  
					JOptionPane.PLAIN_MESSAGE);
			clearFields();
		}
	}
	
	private void clearFields() {
		usernameTF.setText("");
		passwordTF.setText("");
	}
	
	
	/*
	private boolean loginIsValid() {
		
		DataManager dataManager = new DataManager(); // USING THIS FOR TEST PURPOSES. NON-STATIC VERSION HERE. TODO
		
		if (dataManager.getNameMap().containsKey(usernameTF.getText())) {
			if (dataManager.getNameMap().get(usernameTF.getText()).getPassword().equals(passwordTF.getText())) {
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
	}*/
}
