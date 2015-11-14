package client;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class QuoteMeFrame extends JFrame {
	private static final long serialVersionUID = 1;
	
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
