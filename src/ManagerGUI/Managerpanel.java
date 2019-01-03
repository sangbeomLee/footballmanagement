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

	
	JPanel stockPanel = new JPanel();
	JPanel Inner_stock1 = new JPanel();
	JPanel Inner_stock2 = new JPanel();
	JPanel Inner_stock3 = new JPanel();
	JPanel Inner_stock4 = new JPanel();
	JPanel btn_label1 = new JPanel();
	JPanel btn_label2 = new JPanel();
	JPanel btn_label3 = new JPanel();
	JComboBox stockcombo = new JComboBox();
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JLabel l1 = new JLabel("품목");
	JLabel l2 = new JLabel("수량");
	JLabel l3 = new JLabel("대여 가능");
	JButton stockbtn1 = new JButton("추가");
	JButton stockbtn2 = new JButton("삭제");
	JButton stockbtn3 = new JButton("수정");
	JTable stocktable;
	JScrollPane stock_sc;
	DefaultTableModel model1;
    String header [] = {"품명","수량","대여가능"};
    String contents[][];
	
	JPanel personal_day = new JPanel();
	JPanel Inner_day1 = new JPanel();//personal_day
	JPanel Inner_day2 = new JPanel();//personal_day
	JPanel Inner_day3 = new JPanel();//personal_day 
	JButton daybtn1 = new JButton("재고관리");
	JButton daybtn2 = new JButton("예약 현황");
	JComboBox combo = new JComboBox();
	JTable table;
	JScrollPane sc;

	JButton b1 = new JButton("재고 관리");
	JButton b2 = new JButton("예약 현황");


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
		Inner_stock1.add(stockcombo);

		Inner_stock2.setPreferredSize(new Dimension(300, 600));
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
		Inner_stock3.add(stockbtn3);


		stockPanel.setLayout(new BorderLayout());
		stockPanel.add(Inner_stock1,BorderLayout.NORTH);
		stockPanel.add(Inner_stock2,BorderLayout.WEST);
		stockPanel.add(Inner_stock3,BorderLayout.SOUTH);
		stockPanel.add(Inner_stock4,BorderLayout.CENTER);



	}

	public void day() {

		String header [] = {"날짜","시간","예약현황"};
		String contents [][] = new String[100][3];

		table = new JTable(contents,header);
		sc = new JScrollPane(table);

	Inner_day1.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		Inner_day1.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_day1.add(combo,BorderLayout.WEST);

		Inner_day2.add(sc);

		Inner_day3.setLayout(new FlowLayout(FlowLayout.RIGHT,10,0));
		Inner_day3.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_day3.add(daybtn1);
		Inner_day3.add(daybtn2);

		personal_day.setLayout(new BorderLayout());
		personal_day.add(Inner_day1,BorderLayout.NORTH);
		personal_day.add(Inner_day2,BorderLayout.CENTER);
		personal_day.add(Inner_day3,BorderLayout.SOUTH);

	}



	public Managerpanel(){//생성자
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));

		buttonpanel.add(b1);
		buttonpanel.add(b2);


		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10));


		mainpanel.setBackground(Color.BLACK);
		frame.add(mainpanel,BorderLayout.CENTER);
		mainpanel.setLayout(cardLayout);

		stockPanel.setBackground(Color.YELLOW);
		frame.add(buttonpanel,BorderLayout.NORTH);
		mainpanel.add(stockPanel,"stock");

		mainpanel.add(personal_day,"personal_day");

		stock();
		day();

		frame.setTitle("관리자 화면");
		frame.setSize(900, 500);
		//frame.pack();
		frame.setVisible(true);

	}
	
		public void addButtonActionListener(ActionListener listener) {//액션리스너 추가 메소드
			b1.addActionListener(listener);
			b2.addActionListener(listener);
			stockbtn1.addActionListener(listener);
			stockbtn2.addActionListener(listener);
			stockbtn3.addActionListener(listener);
			
		}
		
		public void addTableModelListener(TableModelListener listener) {//테이블에 리스너 부착
			
			stocktable.getModel().addTableModelListener(listener);
			
		}
		
		/*

		@Override
		public void valueChanged(ListSelectionEvent e) {

				
		  int sel = table.getSelectedRow();
		  System.out.println(sel);
		  
		 }
		*/

	
}// Managerpanel close
