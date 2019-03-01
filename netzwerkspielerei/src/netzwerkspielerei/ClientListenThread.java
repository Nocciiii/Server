package netzwerkspielerei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListenThread extends Thread
{
	private Client client;
	private BufferedReader in;
	private Socket socket;
	
	public  ClientListenThread(Client client, Socket socket) 
	{
		this.client=client;
		this.socket=socket;
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		this.start();
	}
	
	public void run()
	{
		String inputLine;
		while(!this.isInterrupted())
		{
			try
			{
				while((inputLine = in.readLine()) != null)
				{
					client.getListModel().addElement(inputLine);
				}
				Thread.sleep(100);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
