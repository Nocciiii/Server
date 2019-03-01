package servernetzwerkspielerei;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUIController extends JFrame
{
	private Controller controller;
	private JPanel contentPane;
	private JList list;
	private JButton btnStart;
	private JLabel lblPortnr;
	private JTextField textField;
	private JButton btnVergifteteGuillotineAktivieren;
	private JButton btnKillgil;


	public GUIController(Controller controller)
	{
		this.controller=controller;
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getList());
		contentPane.add(getBtnStart());
		contentPane.add(getLblPortnr());
		contentPane.add(getTextField());
		contentPane.add(getBtnKillgil());
	}

	public JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(29, 22, 281, 185);
		}
		return list;
	}
	JButton getBtnStart() {
		if (btnStart == null) 
		{
			btnStart = new JButton("Start");
			btnStart.addActionListener(e->controller.start(Integer.parseInt(getTextField().getText())));
			btnStart.setBounds(320, 123, 104, 53);
		}
		return btnStart;
	}
	private JLabel getLblPortnr() {
		if (lblPortnr == null) {
			lblPortnr = new JLabel("Portnr.");
			lblPortnr.setBounds(338, 36, 46, 14);
		}
		return lblPortnr;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(323, 61, 86, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	JButton getBtnKillgil() {
		if (btnKillgil == null) {
			btnKillgil = new JButton("killgil");
			btnKillgil.setBounds(330, 187, 89, 23);
			btnKillgil.addActionListener(e->controller.killgil(Integer.parseInt(getTextField().getText())));
		}
		return btnKillgil;
	}
}
