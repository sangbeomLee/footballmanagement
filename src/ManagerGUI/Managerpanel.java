package ManagerGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Managerpanel{
	
	JPanel mainpanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	
	JButton b1 = new JButton("犁绊 包府");
	JButton b2 = new JButton("绒公老 包府");
	JFrame frame = new JFrame();
	
	
	
	Managerpanel(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.setBounds(500,500,500,500);
		

		buttonpanel.setPreferredSize(new Dimension(1000, 100));
		buttonpanel.add(b1);
		buttonpanel.add(b2);
		
		mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainpanel.setLayout(new BorderLayout(0, 0));
		


		mainpanel.setBackground(Color.BLACK);
		
		frame.add(buttonpanel,BorderLayout.NORTH);
		frame.add(mainpanel);
		
		//frame.setLayout(null);
		frame.setSize(1000, 600);
		frame.setVisible(true);
		//frame.pack();
		
	}
	
	
	
	
	

}
