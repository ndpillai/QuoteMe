package client;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FeedPageGUI extends JPanel {
	private JCheckBox allCB;
	private JCheckBox[] categoryCB;
	private JComboBox sortCB;
	private JScrollPane scrollPane;
	private ArrayList<QuoteGUI> quoteList;
	
	private MainPanel mainPanel;
	
	public FeedPageGUI (MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		initializeVariables();
		createGUI();
		addEvents();
	}
	
	private void initializeVariables() {
		allCB = new JCheckBox();
		categoryCB = new JCheckBox[3];
		sortCB = new JComboBox();
		scrollPane = new JScrollPane();
		quoteList = new ArrayList<QuoteGUI>(); // How are we loading this? From the server? datamanager?
	}
	
	private void createGUI () {
		setLayout(new BorderLayout());
		// NORTH panel
		add(new JLabel("FEED PAGE"), BorderLayout.NORTH);
		
		// CENTER panel
		// add things to the scrollpane
		add(scrollPane, BorderLayout.CENTER);
		//setVisible(true);
		

		
	}
	
	private void addEvents() {
		
	}
	
	public ArrayList<QuoteGUI> getQuotesToDisplay() {
		return null;
	}
	
	public void refresh() {
		
	}
}
