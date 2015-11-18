package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.DataManager;
import client.Quote;

public class ServerClientCommunicator extends Thread {

	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader br;
	private ServerListener serverListener;
	
	private DataManager dataManager;
	
	public ServerClientCommunicator(Socket socket, ServerListener serverListener, DataManager dataManager) throws IOException
	{
		this.socket = socket;
		this.serverListener = serverListener;
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		this.dataManager = dataManager;
	}
	
	public void sendAppInstance(DataManager dataManager)
	{
		try {
			oos.writeObject(dataManager);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator:33 " + ioe.getMessage());
		}
	}
	
	public void sendQuote(Quote quote) {
		try {
			oos.writeObject(quote);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator.sendQuote()" + ioe.getMessage());
		}
	}
	
	public void run()
	{
		try {
			while (true) {
				Object data = br.readLine();
				
//				String line = br.readLine();
				
				//do stuff with line here
				
				if (data instanceof client.Quote) {
					DataManager _dataManager = dataManager.readDataManagerFromTextFile();
					_dataManager.addQuote((Quote)data);
				}
			}
		} catch (IOException ioe) {
			serverListener.removeServerClientCommunicator(this);
			// this means that the socket is closed since no more lines are being received
			try {
				socket.close();
			} catch (IOException ioe1) {
				System.out.println("IOE in ServerClientCommunicator:50 " + ioe.getMessage());
			}
		}
	}
}
