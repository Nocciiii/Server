package netzwerkspielerei;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;

public class GUIClient extends JFrame
{

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	private Client client;
	private JTextField textField_1;

	
	public GUIClient(Client client)
	{
		initialize();
		this.client=client;
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextField());
		contentPane.add(getList());
		contentPane.add(getTextField_1());
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(20, 230, 388, 20);
			textField.setColumns(10);
			textField.addActionListener(e->getClient().abschicken());
		}
		return textField;
	}
	 public JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(20, 31, 388, 188);
		}
		return list;
	}
	
	public Client getClient()
	{
		return client;
	}
	public JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(31, 0, 86, 20);
			textField_1.setColumns(10);
			textField_1.addActionListener(e->getClient().verbinden());
		}
		return textField_1;
	}
}
