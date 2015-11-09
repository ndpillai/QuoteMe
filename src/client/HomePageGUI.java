package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HomePageGUI extends JPanel {
	public ImageIcon logo;
	public JButton loginPageButton, newUserPageButton;
	
	public HomePageGUI() {
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		setLayout(new BorderLayout());
		setSize(200, 400);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new ImageIcon();
		loginPageButton = new JButton("Login");
		newUserPageButton = new JButton("New User");
	}
	
	private void createGUI() {
		add(loginPageButton, BorderLayout.NORTH);
		add(newUserPageButton, BorderLayout.SOUTH);
		
	}
	
	private void addEvents() {
		loginPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				goToLoginPage();
			}
		});
		newUserPageButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent ae) {
				goToNewUserPage();
			}
		});
	}
	
	private void goToLoginPage() {
		System.out.println("Going to login page");
	}
	
	private void goToNewUserPage() {
		System.out.println("Going to new user page");
	}
	
}
