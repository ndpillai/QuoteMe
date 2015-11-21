package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import resources.Images;

public class FeedPageGUI extends JPanel {
	private JCheckBox allCB;
	private JCheckBox[] categoryCB;
	private JComboBox sortCB;
	private JScrollPane scrollPane;
	private ArrayList<QuoteGUI> quoteList;
	
	private JButton j1, j2, j3, j4, j5, j6, j7, j8;
	
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
		j1 = new JButton("Blue");
		j2 = new JButton("Gray");
		j3 = new JButton("Green");
		j4 = new JButton("Orange");
		j5 = new JButton("Pink");
		j6 = new JButton("Purple");
		j7 = new JButton("Red");
		j8 = new JButton("Yellow");
	}
	
	private void createGUI () {
		setLayout(new BorderLayout());
		// NORTH panel
		JPanel northPanel = new JPanel();
		northPanel.add(allCB); // TODO load this too?
		northPanel.add(sortCB); // TODO load the sort combo box with values
		add(northPanel, BorderLayout.NORTH);
		//add(new JLabel("FEED PAGE"), BorderLayout.NORTH);
		
		// CENTER panel
		// add things to the scrollpane
		JPanel feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		feedPanel.add(j1);
		feedPanel.add(j2);
		feedPanel.add(j3);
		feedPanel.add(j4);
		feedPanel.add(j5);
		feedPanel.add(j6);
		feedPanel.add(j7);
		feedPanel.add(j8);

		//scrollPane.add(feedPanel);
		//add(scrollPane, BorderLayout.CENTER);
		add(feedPanel, BorderLayout.CENTER);
	}
	
	private void addEvents() {
		j1.addActionListener(new newProfileAL(Images.parrotAvatarBluePixellated));
		j2.addActionListener(new newProfileAL(Images.parrotAvatarGrayPixellated));
		j3.addActionListener(new newProfileAL(Images.parrotAvatarGreenPixellated));
		j4.addActionListener(new newProfileAL(Images.parrotAvatarOrangePixellated));
		j5.addActionListener(new newProfileAL(Images.parrotAvatarPinkPixellated));
		j6.addActionListener(new newProfileAL(Images.parrotAvatarPurplePixellated));
		j7.addActionListener(new newProfileAL(Images.parrotAvatarRedPixellated));
		j8.addActionListener(new newProfileAL(Images.parrotAvatarYellowPixellated));
	}
	
	public class newProfileAL implements ActionListener {
		String imagePath;
		public newProfileAL(String imagePath) {
			this.imagePath = imagePath;
		}
		public void actionPerformed(ActionEvent ae) {
			User u = new User();
			u.setProfilePicture(new ImageIcon(this.imagePath));
			ProfilePageGUI ppg = new ProfilePageGUI(mainPanel, u);
			mainPanel.displayPage(ppg);
		}
	}
	
	public ArrayList<QuoteGUI> getQuotesToDisplay() {
		return null;
	}
	
	public void refresh() {
		
	}
}
