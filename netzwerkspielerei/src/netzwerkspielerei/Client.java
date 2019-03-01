package netzwerkspielerei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;

public class Client
{
	private String hostName;
	private Socket echoSocket;
	private PrintWriter out;
	private String userInput;
	private GUIClient gui;
	private DefaultListModel<String> listmodel;
	private ClientListenThread listen;
	
	public static void main(String[] args) throws IOException
	{
		new Client();
	}
	public Client()
	{
		gui=new GUIClient(this);
		getGui().getList().setModel(listmodel = new DefaultListModel<String>());
		getGui().setVisible(true);
		
	}
	public void verbinden()
	{
		hostName = "localhost";
		try
		{
			echoSocket = new Socket(hostName, Integer.parseInt(gui.getTextField_1().getText()));
			out = new PrintWriter(echoSocket.getOutputStream(),true);
			listen = new ClientListenThread(this, echoSocket);
			
		}
		catch(UnknownHostException e)
		{
			System.out.println("Der Host " + hostName + " ist nicht bekannt");
		}
		catch(IOException e)
		{
			System.out.println("IO-Verbindung zu "+ hostName +" ist fehlgeschlagen");
		}
	}
	public void abschicken()
	{
		userInput=getGui().getTextField().getText();
		out.println(userInput);
		out.flush();
	}
	
	public GUIClient getGui()
	{
		return gui;
	}
	public DefaultListModel<String> getListModel()
	{
		return listmodel;
	}
	
	public ClientListenThread getListen()
	{
		return this.listen;
	}
}
