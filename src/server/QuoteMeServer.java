package server;

import java.io.IOException;
import java.net.ServerSocket;

public class QuoteMeServer {
	
	private ServerSocket ss;
	private ServerListener serverListener;
	
	public QuoteMeServer()
	{
		try {
			ss = new ServerSocket(6789);
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
		listenForConnections();
	}
	
	public void loadQuoteMeUniverse()
	{
		
	}
	
	private void listenForConnections() {
		serverListener = new ServerListener(ss);
		serverListener.start();
	}
	
	public static void main(String[] args)
	{
		
	}
}
