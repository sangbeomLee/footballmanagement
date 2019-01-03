package UserGUI;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class State extends Frame{
	protected JPanel mainPanel;
	protected JTextArea stateArea;
	protected JButton statebtn;
	protected JLabel Lstate;
	public State(){
		
		this.setLayout(null);
		mainPanel = new JPanel();
		stateArea = new JTextArea();
		statebtn = new JButton("예약취소");
		Lstate = new JLabel("예약현황");
		Lstate.setFont(Lstate.getFont().deriveFont(12f));
		Lstate.setBounds(100, 5, 100, 15);
		statebtn.setBounds(700, 460, 100, 70);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1000, 600);
		stateArea.setBounds(100, 50, 800, 400);
		mainPanel.add(Lstate);
		mainPanel.add(statebtn);
		mainPanel.add(stateArea);
		this.add(mainPanel);
		setSize(1000,600);
		setVisible(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
	}
	
}
