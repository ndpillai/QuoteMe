package client;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

/*
 * 
 * Member variables added:
 * CardLayout layout
 * Container pane
 * 
	Methods added:
	initializeComponents
	create GUI
	addEvents
	main
*/


public class QuoteMeFrame extends JFrame {
	
	private DataManager dataManager;
	private CardLayout layout;
	private Container pane;
	
	private HomePageGUI homePagePanel;
	
	public QuoteMeFrame() {
		super("QuoteMe");
		initializeComponents();
		createGUI();
		addEvents();
		displayTab("Main Menu");
	}
	
	private void initializeComponents() {
		layout = new CardLayout();
		pane = getContentPane();
		pane.setLayout(layout);
		
		homePagePanel = new HomePageGUI();
		//homePagePanel = dataManager.getHomePageGUI();
	}
	
	private void createGUI() {
		setSize(450, 700);
		setLocation(400,100);
		setResizable(false);
		pane.add(homePagePanel, "Home Page");
		displayTab("Home Page");
	}
	
	private void addEvents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void displayTab(String name) {
		layout.show(this.getContentPane(), name);
	}
	
	public static void main(String [] args) {
		QuoteMeFrame qmf = new QuoteMeFrame();
		qmf.setVisible(true);
	}
}
