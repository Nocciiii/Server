package servernetzwerkspielerei;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProxy extends Thread
{
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private Server server;
	
	public ClientProxy(Socket socket,Server server)
	{
		try
		{
			this.socket=socket;
			this.server=server;
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public void run()
	{
		while(!this.isInterrupted())
		{
			try
			{
				Thread.sleep(100);
				lesen();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void lesen()
	{
		String inputLine;
		
		try
		{
			while((inputLine = in.readLine()) != null)
			{
				server.schreiben(inputLine);
			}
		}catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void schreiben(String nachricht)
	{
		out.write(nachricht);
		out.flush();
	}

}
