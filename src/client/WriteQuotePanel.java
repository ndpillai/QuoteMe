package client;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WriteQuotePanel extends JPanel {
	private JTextField userSearchField;
	private User speaker;
	private JButton searchButton;
	private JPanel searchPanel;
	private JLabel titleLabel;
	private JTextArea quoteTextArea;
	private JLabel characterCountLabel;
	private JComboBox<String> categoryComboBox;
	
	private MainPanel mainPanel;
	
	public WriteQuotePanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		
	}
	
	private void createGUI() {
		setLayout(new BorderLayout());
		add(new JLabel("This is write quote panel"), BorderLayout.CENTER);
	}
	
	private void addEvents() {
		
	}
	
	public String getQuoteText() {
		return "";
	}
	
	public User getSpeaker() {
		return null;
	}
	
	public String getCategory() {
		return null;
	}
}
