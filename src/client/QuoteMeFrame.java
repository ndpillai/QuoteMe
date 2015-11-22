package client;

import javax.swing.JFrame;

public class QuoteMeFrame extends JFrame {

	private static final long serialVersionUID = 5757035426111506095L;
	private QuoteMeClient quoteMeClient;
	
	public QuoteMeFrame(QuoteMeClient quoteMeClient) {
		super("QuoteMe");
		setSize(450, 700);
		setLocation(400,100);
		setResizable(false);
		setLocationRelativeTo(null);
		ClientPanel clientPanel = new ClientPanel();
		add(clientPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.quoteMeClient = quoteMeClient;
		this.quoteMeClient.setClientPanel(clientPanel);
		clientPanel.quoteMeClient = this.quoteMeClient;
	}
	
	private void refreshPanels() {
		// TODO still need?
	}
}
