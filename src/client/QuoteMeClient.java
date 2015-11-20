package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class QuoteMeClient extends Thread{
	
	private QuoteMeFrame qmf;
	private ClientPanel clientPanel;
	private PrintWriter pw;
	private BufferedReader br;
	private Socket socket;

	public QuoteMeClient() {
		try {
			socket = new Socket("localhost", 6789);
			qmf = new QuoteMeFrame(this);
			qmf.setVisible(true);
			
			this.pw = new PrintWriter(socket.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
			this.start();
		} catch (IOException ioe) {
			System.out.println("IOE in client: " + ioe.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (pw != null) {
					pw.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ioe) {
				System.out.println("IOE closing things in client constructor: " + ioe.getMessage());
			}
		}
	}
	
	//can be used to send Strings as well
	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
	}
	
	public void run() {
		try {
			while (true) {
				String line = br.readLine();

				//TODO: when they get the line
			}
		} catch (IOException ioe) {
			System.out.println("IOE in client run(): " + ioe.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new QuoteMeClient();
	}
}
