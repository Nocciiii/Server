package servernetzwerkspielerei;

import java.io.IOException;

import javax.swing.DefaultListModel;

public class Controller
{
	private GUIServer gui;
	private DefaultListModel listmodel;
	private Server s;
	private Thread server;
	
	public static void main(String[] args)throws IOException
	{	
		new Controller();
	}
	public Controller()
	{
		gui=new GUIServer(this);
		gui.setVisible(true);
		gui.getList().setModel(listmodel=new DefaultListModel());
	}
	
	public DefaultListModel getListModel()
	{
		return listmodel;
	}
	public void start(int portNumber)
	{
		this.server = new Thread (new Server(portNumber,this));
		server.start();

		server.interrupt();
		gui.getBtnStart().setEnabled(false);
	}
	public void killgil(int portNumber)
	{
		// System.out.println("leb wohl du grausame Welt");
	}
}
