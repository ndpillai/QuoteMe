package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;

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

/*
added: removeTextAdapter
removed: anything with facebook
 */

public class CreateUserGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public QuoteMeTextField firstnameTF, lastnameTF, emailTF, usernameTF;
	public JPasswordField passwordTF, confirmPasswordTF;
	public QuoteMeButton createUserButton, backButton;
	
	private ClientPanel clientPanel;
	
	public CreateUserGUI (ClientPanel clientPanel) {
		initializeVariables();
		this.clientPanel = clientPanel;
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		firstnameTF = new QuoteMeTextField("Enter first name");
		lastnameTF = new QuoteMeTextField("Enter last name");
		emailTF = new QuoteMeTextField("Enter email address");
		usernameTF = new QuoteMeTextField("Enter desired username");
		passwordTF = new JPasswordField("Enter password");
		confirmPasswordTF = new JPasswordField("Confirm password");
		
		passwordTF.setBackground(Color.BLACK);
		passwordTF.setForeground(Color.WHITE);
		passwordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		passwordTF.setHorizontalAlignment(JTextField.CENTER);
		
		confirmPasswordTF.setBackground(Color.BLACK);
		confirmPasswordTF.setForeground(Color.WHITE);
		confirmPasswordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		confirmPasswordTF.setHorizontalAlignment(JTextField.CENTER);
		
		passwordTF.setEchoChar((char) 0);
		confirmPasswordTF.setEchoChar((char) 0);
		createUserButton = new QuoteMeButton("Create User", ImageLibrary.getImage(Images.greenButton),
				15,100,25);
		backButton = new QuoteMeButton("Back", ImageLibrary.getImage(Images.greyButton), 15, 100, 25);
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
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(backButton);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(createUserButton);
		buttonPanel.setBackground(new Color(204, 0, 0, 123));
		
		createUserPanel.add(Box.createVerticalStrut(7));
		createUserPanel.add(buttonPanel);
		createUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createUserPanel.add(Box.createVerticalStrut(7));
		add(createUserPanel, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		firstnameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(firstnameTF,"Enter first name"));
		lastnameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(lastnameTF,"Enter last name"));
		emailTF.addFocusListener(new CustomListeners.RemoveTextAdapter(emailTF,"Enter email address"));
		usernameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(usernameTF,"Enter desired username"));
		passwordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(passwordTF,"Enter password"));
		confirmPasswordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(confirmPasswordTF,"Confirm password"));
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
		confirmPasswordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				confirmPasswordTF.setFont(new JLabel().getFont());
				confirmPasswordTF.setEchoChar('•');
			}
			@Override
			public void focusLost(FocusEvent e) {
				String password = new String(confirmPasswordTF.getPassword());
				if (password.isEmpty() || password.equals("Confirm password")) {
					confirmPasswordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
					confirmPasswordTF.setEchoChar((char) 0); 
					confirmPasswordTF.setText("Confirm password");
				}
			}
		});
	
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	

			System.out.println("ClientPanel null?: " + (CreateUserGUI.this.clientPanel == null));
			System.out.println("QuoteMeClient null?: " + (CreateUserGUI.this.clientPanel.quoteMeClient == null));
			System.out.println("DataManager null?: " + (CreateUserGUI.this.clientPanel.quoteMeClient.dataManager == null));
			if (!CreateUserGUI.this.clientPanel.quoteMeClient.dataManager.hasName(usernameTF.getText())) { // Check name
				if (!CreateUserGUI.this.clientPanel.quoteMeClient.dataManager.hasEmail(emailTF.getText())) { // Check email
					String password = new String(passwordTF.getPassword());
					String confirmPassword = new String(confirmPasswordTF.getPassword());
					if (password.equals(confirmPassword)) {
						User newUser = new User(	// Create new user
								firstnameTF.getText(), 
								lastnameTF.getText(), 
								usernameTF.getText().toLowerCase(), 
								emailTF.getText(),
								password,
								new Date(),
								Images.getRandomAvatar());
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
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clientPanel.moveToHomePanel();
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
	
	public void clearFields() {
		firstnameTF.setText("Enter first name");
		lastnameTF.setText("Enter last name");
		emailTF.setText("Enter email address");
		usernameTF.setText("Enter desired username");
		passwordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		passwordTF.setEchoChar((char) 0); 
		passwordTF.setText("Enter password");
		confirmPasswordTF.setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		confirmPasswordTF.setEchoChar((char) 0); 
		confirmPasswordTF.setText("Confirm password");


	}
}
