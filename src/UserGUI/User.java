package UserGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class User extends JFrame{

		JLabel Title;
		JTextField ID,Pass;
		JButton Log,Join,Find;
		EtchedBorder eborder;
		public User(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(null);
			getContentPane().setBackground(Color.WHITE);
			eborder=new EtchedBorder(EtchedBorder.RAISED);
			
			Title = new JLabel("풋살 통합 관리",SwingConstants.CENTER);
			
			Title.setBorder(eborder);
			Title.setFont(new Font("Serif",Font.BOLD,11));
			Title.setFont(Title.getFont().deriveFont(15f));
			Title.setBounds(375,50,250,70);
			Title.setBackground(Color.black);
			
			ID = new JTextField("");
			ID.setFont(Title.getFont().deriveFont(10f));
			ID.setBounds(350, 330, 150, 20);
			
			Pass = new JTextField("");
			Pass.setFont(Title.getFont().deriveFont(10f));
			Pass.setBounds(350, 360, 150, 20);
			
			Log = new JButton("LOGIN");
			Log.setBounds(530,323,130,60);
			
			Join = new JButton("회원가입");
			Join.setBounds(380,423,130,30);
			
			Find = new JButton("회원가입");
			Find.setBounds(520,423,130,30);
			
			
			getContentPane().add(Find);
			getContentPane().add(Join);
			getContentPane().add(Log);
			getContentPane().add(ID);
			getContentPane().add(Pass);
			getContentPane().add(Title);
			
	
			
			pack();
			setSize(1000,600);
			super.setVisible(true);
		}
	
	
	}
