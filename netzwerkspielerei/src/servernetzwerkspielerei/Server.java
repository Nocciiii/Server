package servernetzwerkspielerei;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;


public class Server extends Thread
{
	private ServerSocket serversocket;
	private PrintWriter out;
	private BufferedReader in;
	private int portNumber;
	private Controller c;
	private ClientProxy proxy;
	private ArrayList<ClientProxy> liste=new ArrayList<ClientProxy>();
	private int i=0;
	
	public Server(int portNumber, Controller c)
	{
		this.portNumber=portNumber;
		this.c = c;
	}
	
		
	public void run()
	{
		try
		{
			serversocket = new ServerSocket(portNumber);
			Thread.currentThread().setName(String.valueOf(portNumber));
			System.out.println(Thread.currentThread().getName());
			c.getListModel().addElement(portNumber+"läuft");
		} 
		catch (IOException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while(!isInterrupted() && serversocket != null)
		{
			try
			{				
				serversocket.setSoTimeout(100);
				Socket clientSocket = serversocket.accept();
				getListe().add(new ClientProxy(clientSocket, this));
				clientSocket=null;
				
				

				Thread.sleep(100);
			}
			catch (SocketTimeoutException e)
			{
				// ich mache pause
			}
			catch (IOException e) 
			{
				System.out.println("Exception beim Listen auf Port: " + portNumber ); 
				System.out.println(e.getMessage());
			}
			catch(InterruptedException e)
			{
				interrupt();
			}
			
		}
		try
		{
			serversocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void schreiben(String nachricht)
	{
		for(ClientProxy p : getListe())
		{
			p.schreiben(nachricht);
		}
	}
	public ArrayList<ClientProxy> getListe()
	{
		return liste;
	}


}
