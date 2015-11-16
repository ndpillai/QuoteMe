package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
		/*
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
		createUserButton.setBounds(150,500,150,50);*/
		
		setLayout(new BorderLayout());
		JPanel createUserPanel = new JPanel();
        createUserPanel.setBackground(new Color(204, 0, 0, 123));

		createUserPanel.setLayout(new BoxLayout(createUserPanel, BoxLayout.Y_AXIS));
		//usernameTF.setSize(new Dimension(20, usernameTF.getPreferredSize().height));
		createUserPanel.add(firstnameTF);
		createUserPanel.add(lastnameTF);
		createUserPanel.add(usernameTF);
		createUserPanel.add(passwordTF);
		createUserPanel.add(confirmPasswordTF);
		createUserPanel.add(createUserButton);
		add(createUserPanel, BorderLayout.SOUTH);
	}
	
	private void addEvents() {
		firstnameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(firstnameTF,"Enter first name"));
		lastnameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(lastnameTF,"Enter last name"));
		usernameTF.addFocusListener(new CustomListeners.RemoveTextAdapter(usernameTF,"Enter desired username"));
		passwordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(passwordTF,"Enter password"));
		confirmPasswordTF.addFocusListener(new CustomListeners.RemoveTextAdapter(confirmPasswordTF,"Confirm password"));
	
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
