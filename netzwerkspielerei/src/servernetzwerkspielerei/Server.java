package servernetzwerkspielerei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.DefaultListModel;

public class Server extends Thread
{
	private ServerSocket socket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int portNumber;
	private Controller c;
	
	public Server(int portNumber, Controller c)
	{
		this.portNumber=portNumber;
		this.c = c;
	}
	
		
	public void run()
	{
		try
		{
			socket = new ServerSocket(portNumber);
			Thread.currentThread().setName(String.valueOf(portNumber));
			System.out.println(Thread.currentThread().getName());
			c.getListModel().addElement(portNumber+"läuft");
		} 
		catch (IOException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while(!isInterrupted())
		{
			try
			{				
				socket.setSoTimeout(100);
				clientSocket=socket.accept();
				/*out = new PrintWriter(clientSocket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out.println("Verbindung erfolgreich");
				String inputLine;
				while((inputLine=in.readLine())!= null)
				{
					out.println(inputLine);
				}*/
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
				out.println("Ciao umu");
				try
				{
					//in.close();
					//out.close();
					socket.close();
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}

}
