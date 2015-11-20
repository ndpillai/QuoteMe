package server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import client.DataManager;

public class ServerListener extends Thread {
	
	private ServerSocket ss;
	private Vector<ServerClientCommunicator> sccVector;
	private DataManager dataManager;
	
	public ServerListener(ServerSocket ss) {
		this.ss = ss;
		sccVector = new Vector<ServerClientCommunicator>();
	}
	
	public void sendAppInstance(DataManager dataManager) {
		this.dataManager = dataManager;
		for (ServerClientCommunicator scc : sccVector) {
			scc.sendAppInstance(dataManager);
		}
	}
	
	public void removeServerClientCommunicator(ServerClientCommunicator scc) {
		sccVector.remove(scc);
	}
	
	public void run() {
		try {
			while(true) {
				Socket s = ss.accept();		
				try {
//					DataManager dataManager = new DataManager(); // TODO FOR TEST PURPOSES. NON-STATIC VERSION HERE.
					
					ServerClientCommunicator scc = new ServerClientCommunicator(s, this, dataManager);
					scc.start();
					sccVector.add(scc);
					
					if (dataManager != null) {
						scc.sendAppInstance(dataManager);
					}
					
				} catch (IOException ioe) {
					System.out.println("IOE in ServerListener:57 " + ioe.getMessage());
				}
			}
		}	catch (IOException ioe) {
			System.out.println("IOE in ServerListener:61 " + ioe.getMessage());
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException ioe) {
					System.out.println("IOE closing ss " + ioe.getMessage());
				}
			}
		}
	}
}
