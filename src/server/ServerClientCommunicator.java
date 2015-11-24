package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.DataManager;
import client.Notification;
import client.Quote;
import client.User;

public class ServerClientCommunicator extends Thread {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	//private ServerListener serverListener;
	private QuoteMeServer server;
	
	private DataManager dataManager;
	
	public ServerClientCommunicator(Socket socket, QuoteMeServer server, DataManager dataManager) throws IOException
	{
		this.socket = socket;
		this.server = server;
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		this.ois = new ObjectInputStream(socket.getInputStream());
		
		this.dataManager = dataManager;
	}
	
	public void sendAppInstance(DataManager dataManager) {
		try {
            System.out.println("---> SCC: Sending DM Instance to my Client: ");
			oos.writeObject(dataManager);
            dataManager.printThis();
			oos.flush();
			oos.reset();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator sendAppInstance() " + ioe.getMessage());
		}
	}
	
	public void sendObject(Object info) {
		dataManager = server.getDataManager();
		try {
            System.out.println("---> SCC: Sending some object to my Client: ");
			oos.writeObject(info);
            dataManager.printThis();
			oos.flush();
			oos.reset();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator sendAppInstance() " + ioe.getMessage());
		}
	}
	
	/*Will likely not need this method, but good to have.
	public void sendObject(Object obj) {
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("IOE in ServerClientCommunicator sendQuote()" + ioe.getMessage());
		}
	}*/
	
	// reading in:
	public void run() {
		try {
			while (true) {
				Object info = null;
				try {
					info = ois.readObject();
				} catch (ClassNotFoundException cnfe) {
					System.out.println("ClassNotFoundException in ServerClientCommunicator run(): " +cnfe.getMessage());
				}
				
			
				if (info instanceof client.Quote) {
                    System.out.println("SCC: Received Object of type Quote." + '\n');
					server.sendObjectToAllClients((Quote)info);
				}
				
				else if (info instanceof client.User) {
                    System.out.println("SCC: Received Object of type User." + '\n');
					server.sendObjectToAllClients((User)info);
				}
				
				else if (info instanceof client.Notification) {
                    System.out.println("SCC: Received Object of type Notification." + '\n');
					server.sendObjectToAllClients((Notification)info);
				}
				
				else if (info instanceof String) {
                    System.out.println("SCC: Received Object of type String: " + (String)info + '\n');
					
					String delims = "[,]";
					String[] tokens = ((String)info).split(delims);
										
					if (tokens[0].equals("follow")) {
						User currUser = dataManager.getUserFromUserName(tokens[1]);
						User user = dataManager.getUserFromUserName(tokens[2]);
						
						currUser.followThisUser(user);
//						user.addUserFollowingUs(currUser);
					}
					
					else if (tokens[0].equals("unfollow")) {
						User currUser = dataManager.getUserFromUserName(tokens[1]);
						User user = dataManager.getUserFromUserName(tokens[2]);
						
						currUser.unfollowThisUser(user);
//						user.removeUserFollowingUs(currUser);
					}
					
					else if (tokens[0].equals("upquote")) {
						
					}
					
					server.sendAppInstanceToAllClients(dataManager);
				} 
				
			}
		} catch (IOException ioe) {
			if (ioe.getMessage() == null) {
				System.out.println("Client disconnected.");
				server.pushToTextFile();
			}
			else {
				System.out.println("IOE in ServerClientCommunicator run() " + ioe.getMessage());
			}
			
			server.removeServerClientCommunicator(this);
			// this means that the socket is closed since no more lines are being received
			try {
				socket.close();
			} catch (IOException ioe1) {
				System.out.println("IOE in ServerClientCommunicator run() 2 " + ioe.getMessage());
			} 
		} 
	}
	
}
