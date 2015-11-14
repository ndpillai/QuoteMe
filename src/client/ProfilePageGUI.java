package client;

import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ProfilePageGUI extends JPanel {
	public long serialVersionUID;
	private User user;
	private ImageIcon userImageIcon;
	private JLabel followersLabel, followedLabel;
	private JTabbedPane profileQuoteTabs;
	private JPanel myQuotesPanel, postedQuotesPanel, favoriteQuotesPanel;
	private ScrollPane myQuotesPane, postedQuotesPane, favoriteQuotesPane;
	private MainPanel mainPanel;
	
	public ProfilePageGUI(MainPanel mainPanel, User u) {
		this.mainPanel = mainPanel;
		user = u;
		initializeVariables();
		createGUI();
		addEvents();
		// TODO set layouts and set visibility
	}
	
	private void initializeVariables() {
		// TODO initialize stuff correctly
		userImageIcon = new ImageIcon();
		followersLabel = new JLabel();
		followedLabel = new JLabel();
		profileQuoteTabs = new JTabbedPane();
		myQuotesPanel = new JPanel();
		postedQuotesPanel = new JPanel();
		favoriteQuotesPanel = new JPanel();
		myQuotesPane = new ScrollPane();
		postedQuotesPane = new ScrollPane();
		favoriteQuotesPane = new ScrollPane();
	}
	
	private void createGUI() {
		// TODO set layouts
	}
	
	private void addEvents() {
		// TODO
	}
	
	private void refresh() {
		// TODO
	}
}
