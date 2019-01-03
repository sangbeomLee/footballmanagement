package ManagerGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class Managerpanel{

	JFrame frame = new JFrame();
	CardLayout cardLayout = new CardLayout(0,0);
	JPanel mainpanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	JLabel mangerID = new JLabel(); 
	JLabel mangerID2 = new JLabel(); 
	JLabel show_manger = new JLabel(); 
	
	//재고관리 UI
	JPanel stockPanel = new JPanel();
	JPanel Inner_stock1 = new JPanel();
	JPanel Inner_stock2 = new JPanel();
	JPanel Inner_stock3 = new JPanel();
	JPanel Inner_stock4 = new JPanel();
	JPanel btn_label1 = new JPanel();
	JPanel btn_label2 = new JPanel();
	JPanel btn_label3 = new JPanel();
	JTextField t1 = new JTextField(5);
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JLabel l1 = new JLabel("품목");
	JLabel l2 = new JLabel("수량");
	JLabel l3 = new JLabel("대여 가능");
	JButton stockbtn1 = new JButton("추가");
	JButton stockbtn2 = new JButton("삭제");
	//JButton stockbtn3 = new JButton("수정");
	JTable stocktable;
	JScrollPane stock_sc;
	DefaultTableModel model1;
    String header [] = {"품명","수량","대여가능"};
    String contents[][];
	
    //예약현황 관리 UI
	JPanel personal_day = new JPanel();
	JPanel Inner_day1 = new JPanel();
	JPanel Inner_day2 = new JPanel();
	JButton daybtn1 = new JButton("업데이트");
	JTable table;
	JScrollPane sc;
	DefaultTableModel model2;
	String header2 [] = {"날짜","시간","고객ID"};
	String contents2 [][];
	
	
	
	//휴무일 관리 UI
	JPanel Inner_day3 = new JPanel();//휴무일 패널
	JPanel h1 = new JPanel();
	JPanel h2 = new JPanel();
	JTextField pdt;
	JTable table3;
	JScrollPane sc3;
	DefaultTableModel model3;
	String header3 [] = {"휴무일 현황"};
	String contents3 [][];
	JButton daybtn2 = new JButton("휴무일 지정");
	JButton daybtn3 = new JButton("삭제");

	
	//메인패널버튼
	JButton b1 = new JButton("재고 관리");
	JButton b2 = new JButton("예약 현황");
	JButton b3 = new JButton("휴무일");

	
	public void holl() {
		
		
		pdt = new JTextField("0000-00-00");

		h1.add(pdt);
		h1.add(daybtn2);
		h1.add(daybtn3);
		
		contents3 = new String[0][0];
		model3 = new DefaultTableModel(contents3,header3);
		table3 = new JTable(model3);
		sc3 = new JScrollPane(table3);
		sc3.setPreferredSize(new Dimension(300,300));///스크롤 크기 지정
		
		h2.add(sc3);
		
		Inner_day3.setLayout(new BorderLayout());
		Inner_day3.setBorder(new EmptyBorder(5, 5, 5, 5));

		Inner_day3.add(h1,BorderLayout.SOUTH);
		Inner_day3.add(h2,BorderLayout.CENTER);

		
		
		
		//Inner_day3.add(pdt);
		//Inner_day3.add(daybtn2);
	}
	
	public void stock() {

	   contents= new String[0][0];

		model1 = new DefaultTableModel(contents,header);

		stocktable = new JTable(model1);
		stock_sc = new JScrollPane(stocktable);
		Inner_stock4.add(stock_sc);

		//제발
		//stocktable.getSelectionModel().addListSelectionListener(this);

		
		Inner_stock1.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		Inner_stock1.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_stock1.add(mangerID);//관리자 아이디 표시 라벨

		Inner_stock2.setPreferredSize(new Dimension(300, 400));
		Inner_stock2.setLayout(new GridLayout(3,2,30,50));
		Inner_stock2.setBorder(new EmptyBorder(10, 10, 10, 10));

		Inner_stock2.add(l1);
		Inner_stock2.add(t1);

		Inner_stock2.add(l2);
		Inner_stock2.add(t2);

		Inner_stock2.add(l3);
		Inner_stock2.add(t3);



		Inner_stock3.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_stock3.add(stockbtn1);
		Inner_stock3.add(stockbtn2);
		//Inner_stock3.add(stockbtn3);


		stockPanel.setLayout(new BorderLayout());
		stockPanel.add(Inner_stock1,BorderLayout.NORTH);
		stockPanel.add(Inner_stock2,BorderLayout.WEST);
		stockPanel.add(Inner_stock3,BorderLayout.SOUTH);
		stockPanel.add(Inner_stock4,BorderLayout.CENTER);



	}

	public void day() {

		contents2 = new String[0][0];
		model2 = new DefaultTableModel(contents2,header2);
		table = new JTable(model2);
		sc = new JScrollPane(table);
		
		//Inner_day3.setLayout(null);
		//Inner_day3.setPreferredSize(new Dimension(900,50));
		//pdt = new JTextField("0000-00-00");
		//pdt.setBounds(330, 0, 100, 30); //휴무일 텍스트필드 위치 지정
		//daybtn2.setBounds(440, 0, 110, 30);//휴무일 버튼
		
		//Inner_day3.add(daybtn1);
		//Inner_day3.add(pdt);
		//Inner_day3.add(daybtn2);
		
		
		
		
		Inner_day1.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Inner_day1.add(mangerID2);
		Inner_day1.add(daybtn1);
		
		sc.setPreferredSize(new Dimension(400,300));///스크롤 크기 지정
		Inner_day2.add(sc);
		
		
		personal_day.setLayout(new BorderLayout());
		personal_day.add(Inner_day1,BorderLayout.NORTH);
		personal_day.add(Inner_day2,BorderLayout.CENTER);
		//personal_day.add(Inner_day3,BorderLayout.SOUTH);
		
		
	}



	public Managerpanel(){//생성자
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));

		buttonpanel.add(b1);
		buttonpanel.add(b2);
		buttonpanel.add(b3);
		buttonpanel.add(show_manger);//풋살장 라벨

		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10));


		mainpanel.setBackground(Color.BLACK);
		frame.add(mainpanel,BorderLayout.CENTER);
		mainpanel.setLayout(cardLayout);

		stockPanel.setBackground(Color.YELLOW);
		frame.add(buttonpanel,BorderLayout.NORTH);
		mainpanel.add(stockPanel,"stock");

		mainpanel.add(personal_day,"personal_day");

		
		mainpanel.add(Inner_day3,"hol");
		
		
		stock();
		day();
		holl();

		frame.setTitle("관리자 화면");
		frame.setSize(900, 500);
		//frame.pack();
		frame.setVisible(true);

	}
	
		public void addButtonActionListener(ActionListener listener) {//액션리스너 추가 메소드
			b1.addActionListener(listener);
			b2.addActionListener(listener);
			b3.addActionListener(listener);
			stockbtn1.addActionListener(listener);
			stockbtn2.addActionListener(listener);
			daybtn1.addActionListener(listener);
			daybtn2.addActionListener(listener);
			daybtn3.addActionListener(listener);
			pdt.addActionListener(listener);
			
		}
		
		public void addTableModelListener(TableModelListener listener) {//테이블에 리스너 부착
			
			stocktable.getModel().addTableModelListener(listener); //재고관리 리스너
			table3.getModel().addTableModelListener(listener);//휴무일 리스너

		}
		
	
}// Managerpanel close
