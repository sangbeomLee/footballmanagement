package ManagerGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class Manager extends JFrame implements ActionListener{

		public JLabel Title;
		public JTextField ID,Pass;
		public JButton Log,Join,Find;
		EtchedBorder eborder;
		public SubFrame sub;
		public Manager(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			getContentPane().setBackground(Color.WHITE);
			
			sub = new SubFrame();
			
			eborder=new EtchedBorder(EtchedBorder.RAISED);
			Title = new JLabel("풋살 통합 관리",SwingConstants.CENTER);
			Title.setBorder(eborder);
			Title.setFont(new Font("Serif",Font.BOLD,11));
			Title.setFont(Title.getFont().deriveFont(15f));
			Title.setBounds(375,50,250,70);
			Title.setBackground(Color.black);

			
			ID = new JTextField("아이디");
			ID.setFont(ID.getFont().deriveFont(10f));
			ID.setBounds(350, 330, 150, 20);
			ID.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					ID.setText("");
				}
			});
			Pass = new JTextField("비밀번호");
			Pass.setFont(Pass.getFont().deriveFont(10f));
			Pass.setBounds(350, 360, 150, 20);
			Pass.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					Pass.setText("");
				}
			});
			Log = new JButton("LOGIN");
			Log.setBounds(530,323,130,60);
			
			Join = new JButton("회원가입");
			Join.setBounds(380,423,130,30);
			
			
			Join.addActionListener(this);
			
			Find = new JButton("아이디/비번찾기");
			Find.setBounds(520,423,130,30);
			
			//Log.addActionListener(this);
			
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
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==Join) {
			//sub.setSize(100,100);
			sub.setLocation(200, 50);
			sub.setVisible(true);
			
			
		}
	
	}
		
	public class SubFrame extends Frame{
		public JTextField IDin,Passin,Passrein,Name,Mail,Number,Field;
		
		public JLabel LIDin,LPassin,LPassrein,LName,LMail,LNumber,Passre,LField;
		public JButton Check,Signup;
		boolean tatus=false;
		String pass1,pass2;
		public SubFrame() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(null);
			setTitle("회원가입");
			this.setSize(500,600);
			IDin = new JTextField("");
			IDin.setFont(IDin.getFont().deriveFont(10f));
			IDin.setBounds(100, 60, 150, 40);
			LIDin = new JLabel("아이디");
			LIDin.setFont(LIDin.getFont().deriveFont(10f));
			LIDin.setBounds(20, 60, 50, 40);
			
			
			Passin = new JTextField();
			Passin.setFont(Passin.getFont().deriveFont(10f));
			Passin.setBounds(100, 130, 150, 40);
			LPassin = new JLabel("비밀번호");
			LPassin.setFont(LPassin.getFont().deriveFont(10f));
			LPassin.setBounds(20, 130, 50, 40);
			
			Passrein = new JTextField();
			Passrein.setFont(Passrein.getFont().deriveFont(10f));
			Passrein.setBounds(100, 210, 150, 40);
			LPassrein = new JLabel("비밀번호 확인");
			LPassrein.setFont(LPassrein.getFont().deriveFont(10f));
			LPassrein.setBounds(20, 210, 70, 40);
		
			
			Name = new JTextField();
			Name.setFont(Name.getFont().deriveFont(10f));
			Name.setBounds(100, 300, 150, 40);
			LName = new JLabel("이름");
			LName.setFont(LName.getFont().deriveFont(10f));
			LName.setBounds(20, 300, 50, 40);
		
			
			Mail = new JTextField();
			Mail.setFont(Mail.getFont().deriveFont(10f));
			Mail.setBounds(100, 390, 150, 40);
			LMail = new JLabel("이메일");
			LMail.setFont(LMail.getFont().deriveFont(10f));
			LMail.setBounds(20, 390, 50, 40);
			
			Number = new JTextField();
			Number.setFont(Number.getFont().deriveFont(10f));
			Number.setBounds(100, 480, 150, 40);
			LNumber = new JLabel("전화번호");
			LNumber.setFont(LNumber.getFont().deriveFont(10f));
			LNumber.setBounds(20, 480, 50, 40);
			
			Field = new JTextField();
			Field.setFont(Field.getFont().deriveFont(10f));
			Field.setBounds(100, 510, 150, 40);
			LField = new JLabel("풋살장");
			LField.setFont(LField.getFont().deriveFont(10f));
			LField.setBounds(20, 510, 50, 40);
			
			Passre = new JLabel("sss");
			Passre.setSize(30, 30);
			Passre.setBounds(300,270,100,50);
			pass1 = Passin.getText();
			pass2 = Passrein.getText();
			
			//Passrein.add
			if(pass1==pass2)
			{
				Passre.setText("Good");
			}
			else {
				Passre.setText("다름");
			}
			Signup = new JButton("등록");
			Signup.setSize(30, 30);
			Signup.setBounds(400,70,100,50);
			add(IDin);
			add(LIDin);
			add(Passin);
			add(LPassin);
			add(Passrein);
			add(LPassrein);
			add(Name);
			add(LName);
			add(Mail);
			add(LMail);
			add(Number);
			add(Passre);
			add(Signup);
			add(Field);
			add(LField);
			
			//id = IDin.getText();
			//msg1 = Pass.getText();
			//msg2 = Name.getText()+"#"+Mail.getText()+"#"+Number.getText();
			//type1 = "customer";
			//type2 = "register";
			
			addWindowListener(new WindowAdapter() {
			
				public void windowClosing(WindowEvent e) {
					setVisible(false);
					dispose();
				}

			});
		}
		
	}
	public void addButtonActionListener(ActionListener listener) {
		sub.Signup.addActionListener(listener);
		Log.addActionListener(listener);
	}
	}
