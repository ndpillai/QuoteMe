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
		add(new ClientPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.quoteMeClient = quoteMeClient;
	}
	
	private void refreshPanels() {
		// TODO still need?
	}
}
