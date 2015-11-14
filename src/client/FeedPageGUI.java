package client;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FeedPageGUI extends JPanel {
	public JTextField searchBarTF;
	public JCheckBox allCB;
	public JCheckBox[] categoryCB;
	public JComboBox sortCB;
	public JScrollPane scrollPane;
	private ArrayList<QuoteGUI> quoteList;
	private MainPanel mainPanel;
	
	public FeedPageGUI (MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	private void initializeVariables() {
		
	}
	
	private void createGUI () {
		
	}
	
	private void addEvents() {
		
	}
	
	public ArrayList<QuoteGUI> getQuotesToDisplay() {
		return null;
	}
	
	public void refresh() {
		
	}
}
