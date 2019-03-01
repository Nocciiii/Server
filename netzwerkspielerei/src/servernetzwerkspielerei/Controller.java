package servernetzwerkspielerei;

import java.io.IOException;

import javax.swing.DefaultListModel;

public class Controller
{
	private GUIController gui;
	private DefaultListModel listmodel;
	private Server s;
	private Thread server;
	
	public static void main(String[] args)throws IOException
	{	
		new Controller();
	}
	public Controller()
	{
		gui=new GUIController(this);
		gui.setVisible(true);
		gui.getList().setModel(listmodel=new DefaultListModel());
	}
	
	public DefaultListModel getListModel()
	{
		return listmodel;
	}
	public void start(int portNumber)
	{
		this.server = new Server(portNumber,this);

		server.start();
	}
	public void killgil(int portNumber)
	{
		server.interrupt();
		System.out.println("leb wohl du grausame Welt");
	}
}
