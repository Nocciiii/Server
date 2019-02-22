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
	String hostName;
	int portNumber;
	Socket echoSocket;
	PrintWriter out;
	BufferedReader in;
	BufferedReader stdln;
	String userInput;
	GUIClient gui;
	DefaultListModel listmodel;
	public static void main(String[] args) throws IOException
	{
		new Client();
	}
	public Client()
	{
		gui=new GUIClient(this);
		gui.getList().setModel(listmodel = new DefaultListModel());
		gui.setVisible(true);
		
	}
	public void verbinden()
	{
		hostName = "localhost";
		try
		{
			echoSocket = new Socket(hostName, Integer.parseInt(gui.getTextField_1().getText()));
			out = new PrintWriter(echoSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			stdln=new BufferedReader(new InputStreamReader(System.in));
			
			String pruefen=in.readLine();
			System.out.println(pruefen);
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
		userInput=gui.getTextField().getText();
		out.println(userInput);
		out.flush();
		try
		{
			listmodel.addElement(in.readLine());
		}
		catch(IOException e)
		{
			System.out.println("IO-Verbindung zu "+ hostName +" ist fehlgeschlagen");
		}
	}
}
