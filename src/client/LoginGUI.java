package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import custom.QuoteMeButton;
import custom.QuoteMeTextField;
import library.FontLibrary;
import library.ImageLibrary;
import resources.Constants;
import resources.CustomListeners;
import resources.Images;


public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 2335752822050869549L;
	private QuoteMeTextField usernameTF;
	private JPasswordField passwordTF;
	private QuoteMeButton backButton;
	private QuoteMeButton loginButton;
	private QuoteMeButton forgotUserButton;
	
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
		usernameTF = new QuoteMeTextField("Enter username");
		loginButton = new QuoteMeButton(
				"Login",
				ImageLibrary.getImage(Images.greenButton),
				15,120,25);
		passwordTF = new JPasswordField("Enter password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBackground(Color.BLACK);
		passwordTF.setForeground(Color.WHITE);
		passwordTF.setHorizontalAlignment(JTextField.CENTER);
		passwordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		forgotUserButton = new QuoteMeButton(
				"Forgot Username/Password",
				ImageLibrary.getImage(Images.greyButton),
				15,250,25);
		backButton = new QuoteMeButton("Back", ImageLibrary.getImage(Images.greyButton), 15, 120, 25);
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(new Color(204, 0, 0, 123));

		// BELOW: trying to make username TF not go entire length of BoxLayout panel
        JPanel TFPanel = new JPanel();
        TFPanel.setLayout(new BoxLayout(TFPanel, BoxLayout.Y_AXIS));
        TFPanel.setBackground(new Color(204, 0, 0, 123));
		usernameTF.setMaximumSize(new Dimension(250, usernameTF.getPreferredSize().height));
		passwordTF.setMaximumSize(new Dimension(250, passwordTF.getPreferredSize().height));
		TFPanel.add(usernameTF);
		TFPanel.add(passwordTF);
		loginPanel.add(TFPanel);
		
		JPanel buttonPanel = new JPanel();
		JPanel topButtonPanel = new JPanel();
		topButtonPanel.setLayout(new BoxLayout(topButtonPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		topButtonPanel.add(backButton);
		topButtonPanel.add(Box.createHorizontalStrut(10));
		topButtonPanel.add(loginButton);
		buttonPanel.add(topButtonPanel);
		buttonPanel.add(Box.createVerticalStrut(7));
		buttonPanel.add(forgotUserButton);
		topButtonPanel.setBackground(new Color(204, 0, 0, 123));
		topButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.setBackground(new Color(204, 0, 0, 123));
		loginPanel.add(Box.createVerticalStrut(7));
		loginPanel.add(buttonPanel);
		loginPanel.add(Box.createVerticalStrut(7));
		add(loginPanel, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		usernameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(usernameTF,"Enter username"));
		passwordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(passwordTF,"Enter password"));
		passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordTF.setFont(new JLabel().getFont());
				passwordTF.setEchoChar('•');
			}
			@Override
			public void focusLost(FocusEvent e) {
				String password = new String(passwordTF.getPassword());
				if (password.isEmpty() || password.equals("Enter password")) {
					passwordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
					passwordTF.setEchoChar((char) 0); 
					passwordTF.setText("Enter password");
				}
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				usernameTF.setText(usernameTF.getText().toLowerCase());
				checkIfLoginIsValid();
			}
		});
		
		forgotUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Forgot Password Button pressed!");
				String email = JOptionPane.showInputDialog(LoginGUI.this, "Enter an email for username and password recovery:", "Recover your username/password");
				checkForgotPasswordEmail(email);
				
			}
		}); 
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clientPanel.moveToHomePanel();
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
		System.out.println("UsernameTF : " + usernameTF.getText());
		System.out.println("PasswordTF : " + new String(passwordTF.getPassword()));
		User user = this.clientPanel.quoteMeClient.dataManager.getUserFromUserName(usernameTF.getText().toLowerCase());
		if (user != null) {
			
			String password = new String(passwordTF.getPassword());
			System.out.println(password + " " + user.getPassword());
			
			if (password.equals(user.getPassword())) {
				
				clientPanel.setCurrentUser(user);
				clientPanel.refreshFeed();
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
	
	public void clearFields() {
		usernameTF.setText("Enter username");
		passwordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		passwordTF.setEchoChar((char) 0); 
		passwordTF.setText("Enter password");
	}
}
