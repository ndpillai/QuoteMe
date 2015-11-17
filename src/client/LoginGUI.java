package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import javax.mail.Message.RecipientType;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.ws.api.message.Message;

import resources.CustomListeners;
import resources.Images;
import sun.rmi.transport.Transport;


public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 2335752822050869549L;
	private JTextField usernameTF, passwordTF;
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
		passwordTF = new JTextField("Enter password");
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
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (loginIsValid()) {
//					goToFeed(DataManager.getNameMap().get(usernameTF.getText()));
					goToFeed(new User());
				}
			}
		});
		
		forgotUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Forgot Password Button pressed!");
				String emailAddress = JOptionPane.showInputDialog(LoginGUI.this, "Enter an email for username and password recovery:");
				if (emailAddress == null) { // Probably chose cancel
					System.out.println("Chose cancel?");
				}
				else {
					JOptionPane.showMessageDialog(LoginGUI.this, "Username/Password recovery email sent to: " + emailAddress,  "Email sent!", JOptionPane.PLAIN_MESSAGE);

					// TODO email validation
					// For now, sendEmail with hardcoded email
					//sendEmail("gouillon@usc.edu");
					sendEmail(emailAddress); 
					// go to new Forgot Password page
				}
			}
		}); 
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
	
	private void sendEmail(String emailAddress) {
		System.out.println("Before sending email");
		System.out.println("Email to send to: " + emailAddress);
		String from = SendRecoveryEmail.USER_NAME;
        String pass = SendRecoveryEmail.PASSWORD;
        String[] to = { emailAddress }; // list of recipient email addresses
        String subject = "QuoteMe Password Recovery.";
        String body = "How could you not remember your password? Absurd. What a real Pillai."
        		+ " Well, I'm not going to give it to you. You don't belong in the QuoteMe universe."
        		+ " Carry on.";		
		SendRecoveryEmail.sendFromGMail(from, pass, to, subject, body);
		System.out.println("Sending an email (but not actually, not yet that is)");
	}
}
