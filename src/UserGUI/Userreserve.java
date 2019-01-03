package UserGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Userreserve extends Frame{
	private JPanel mainPanel,topPanel,MapPanel,bottomPanel;
	protected JTextField time,num;
	protected JComboBox<String> days;
	protected JLabel Ldays,Ltime,Lnum;
	protected JButton statebtn,reservebtn,inquirebtn;
	public State state;
	
	public Userreserve(State state) {
		this.setLayout(null);
		this.state = state;
		
		//프레임에 부칠 패널들
	    topPanel = new JPanel();
		MapPanel =new JPanel();
		bottomPanel =new JPanel();
		mainPanel = new JPanel();
		
		//각패널에 부칠 라벨들
		Ldays = new JLabel("날짜");
		Ltime = new JLabel("시간");
		Lnum= new JLabel("인원수");
		days = new JComboBox<String>();
		time = new JTextField();
		num = new JTextField();
		statebtn = new JButton("내 예약상황");
		reservebtn = new JButton("조회");
		inquirebtn = new JButton("예약");
		
		//폰트크시를 주었다.
		days.setFont(days.getFont().deriveFont(10f));
		time.setFont(time.getFont().deriveFont(10f));
		num.setFont(num.getFont().deriveFont(10f));
		Ldays.setFont(days.getFont().deriveFont(10f));
		Ltime.setFont(time.getFont().deriveFont(10f));
		Lnum.setFont(num.getFont().deriveFont(10f));
		
		//각각의 위치,크기 직접지정
		Ldays.setBounds(80, 20, 50, 50);
		days.setBounds(150, 20, 100, 50);
		Ltime.setBounds(380, 20, 50, 50);
		time.setBounds(450, 20, 100, 50);
		Lnum.setBounds(700, 20, 50, 50);
		num.setBounds(770, 20, 100, 50);
		
		topPanel.add(Ldays);
		topPanel.add(days);
		topPanel.add(Ltime);
		topPanel.add(time);
		topPanel.add(Lnum);
		topPanel.add(num);
		
		
		statebtn.setBounds(0, 0, 130, 50);
		reservebtn.setBounds(450, 0, 130, 50);
		inquirebtn.setBounds(770, 0, 130, 50);
		
		bottomPanel.add(statebtn);
		bottomPanel.add(reservebtn);
		bottomPanel.add(inquirebtn);
		
		//패널의 각각의 위치,크기지정
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 1000, 100);
		MapPanel.setLayout(null);
		MapPanel.setBounds(0, 100, 1000, 400);
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(0, 500, 1000, 100);
		MapPanel.setBackground(Color.BLACK);
		
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 990, 580);
		this.add(mainPanel);
		//프레임에 패널부착
		mainPanel.add(topPanel);
		mainPanel.add(MapPanel);
		mainPanel.add(bottomPanel);
		
		setSize(1000,600);
		setVisible(true);
		
		//프레임 닫을시 지금 프레임만 닫힌다.
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});

	}
	
	//버튼리스너따로 만들어 컨트롤 클래스에 사용.
	public void addButtonActionListener(ActionListener listener) {
		
		statebtn.addActionListener(listener);
		reservebtn.addActionListener(listener);
		inquirebtn.addActionListener(listener);

	}
	
}
