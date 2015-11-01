package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.DataManager;

public class ServerClientCommunicator extends Thread {

	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader br;
	private ServerListener serverListener;
	
	public ServerClientCommunicator(Socket socket, ServerListener serverListener) throws IOException
	{
		
	}
	
	public void sendAppInstance(DataManager dataManager)
	{
		
	}
	
	public void run()
	{
		
	}
}
