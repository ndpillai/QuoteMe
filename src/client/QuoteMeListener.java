package client;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class QuoteMeListener {

	private Socket socket;
	private ObjectInputStream ois;
	private PrintWriter pw;
	private DataManager dataManager;
	private QuoteMeFrame clientGUI;
	
	public QuoteMeListener(DataManager dataManager, QuoteMeFrame clientGUI, Socket socket)
	{
		
	}
	
	private void initializeVariables()
	{

	}
	
	public void sendMessage(String message)
	{
		
	}
	
	public void run()
	{
		
	}
}
