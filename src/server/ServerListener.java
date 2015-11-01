package server;

import java.net.ServerSocket;
import java.util.Vector;

import client.DataManager;

public class ServerListener extends Thread {
	
	private ServerSocket ss;
	private Vector<ServerClientCommunicator> sccVector;
	private DataManager appInstance;
	
	public ServerListener()
	{
		
	}
	
	public void sendAppInstance(DataManager dataManager)
	{
		
	}
	
	public void removeServerClientCommunicator(ServerClientCommunicator scc)
	{
		
	}
	
	public void run()
	{
		
	}
}
