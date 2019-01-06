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

	//frame에들어갈 패널들
	JFrame frame = new JFrame();
	CardLayout cardLayout = new CardLayout(0,0);
	JPanel mainpanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	JLabel mangerID = new JLabel(); //관리자 로그인 아이디를 표시할 라벨
	JLabel mangerID2 = new JLabel(); //관리자 로그인 아이디를 표시할 라벨
	
	//재고관리 UI
	JPanel stockPanel = new JPanel();
	JPanel Inner_stock1 = new JPanel();//관리자 Id가 들어가있는 패널
	JPanel Inner_stock2 = new JPanel();// 품목,수량,대여가능이 들어가있는 라벨과, 물품을 추가시킬 텍스트필드가 들어가있는 라벨 크기 지정
	JPanel Inner_stock3 = new JPanel();//재고 추가 삭제가 들어가있는 패널
	JPanel Inner_stock4 = new JPanel();//재고관리 테이블이 들어가있는 패널
	JTextField t1 = new JTextField();//품목을 적을수있는 JTextField
	JTextField t2 = new JTextField();//수량을 적을수있는 JTextField
	JTextField t3 = new JTextField();//대여가능수량을 적을수있는 JTextField
	JLabel l1 = new JLabel("품목");//재고관리 품목 라벨
	JLabel l2 = new JLabel("수량");//재고관리 수량 라벨
	JLabel l3 = new JLabel("대여 가능");//재고관리 대여가능 라벨
	JButton stockbtn1 = new JButton("추가"); //재고 추가버튼
	JButton stockbtn2 = new JButton("삭제");//재고 삭제버튼
	JTable stocktable;//재고관리 테이블
	JScrollPane stock_sc;//재고관리에 부착할 JScrollPane
	DefaultTableModel model1;//테이블에  추가 ,삭제,수정 이 쉽게 가능하게 하기위해서 사용하는 DefaultTableModel
    String header [] = {"품명","수량","대여가능"};
    String contents[][];
	
    //예약현황 관리 UI
	JPanel personal_day = new JPanel();
	JPanel Inner_day1 = new JPanel();//예약현황을 보여주는 JTable이 들어가있는 패널
	JPanel Inner_day2 = new JPanel();//업데이트버튼이 부착되어있는 패널
	JButton daybtn1 = new JButton("업데이트");//예약현황을 실시간으로 업데이트할수있는 버튼
	JTable table;//에약현황 테이블
	JScrollPane sc;
	DefaultTableModel model2;
	String header2 [] = {"날짜","시간","고객ID"};
	String contents2 [][];
	
	
	
	//휴무일 관리 UI
	JPanel Inner_day3 = new JPanel();// h1패널과 h2과 패널을 부착할 패널
	JPanel h1 = new JPanel();//추가 삭제 버튼과 휴무일을 적을수있는 JTextField가 있는 패널
	JPanel h2 = new JPanel();//휴무일을 보여주는 테이블을 부착할 패널
	JTextField pdt;//휴무일을 적을 수 있는 JTextField
	JTable table3;//휴무일을 보여주는 테이블
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

	
	public void holl() {//휴무일 메소드
		
		
		pdt = new JTextField("0000-00-00");//휴무일을 적을수있는 JTextField ex) 0000-00-00(년도 -월-날짜)

		//추가 삭제 버튼과 휴무일을 적을수있는 JTextField 부착
		h1.add(pdt);
		h1.add(daybtn2);
		h1.add(daybtn3);
		
		//JTable 설정
		contents3 = new String[0][0];
		model3 = new DefaultTableModel(contents3,header3);
		table3 = new JTable(model3);
		sc3 = new JScrollPane(table3);
		sc3.setPreferredSize(new Dimension(300,300));///스크롤 크기 지정
		
		h2.add(sc3);
		
		Inner_day3.setLayout(new BorderLayout());
		Inner_day3.setBorder(new EmptyBorder(5, 5, 5, 5));

		Inner_day3.add(h1,BorderLayout.SOUTH);// 추가삭제버튼이있는 패널 SOUTH로 설정
		Inner_day3.add(h2,BorderLayout.CENTER);//휴무일을 보여주는 JTable CENTER로설정


	}
	
	public void stock() {//재고관리 매소드
		
		
		//JTable 설정
	    contents= new String[0][0];
		model1 = new DefaultTableModel(contents,header);//DefaultTableModel에 JTable에 들어갈 행과열의 정보값을 넣어줌
		stocktable = new JTable(model1);//JTable에 행과열의 정보값이 들어간 DefaultTableModel을 JTable객체에 넣어줌
		stock_sc = new JScrollPane(stocktable);//JScrollPane에 JTable부착
		Inner_stock4.add(stock_sc);//스크롤에 테이블 부착

		Inner_stock1.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		Inner_stock1.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_stock1.add(mangerID);//관리자 아이디 표시 라벨

		// 품목,수량,대여가능이 들어가있는 라벨과, 물품을 추가시킬 텍스트필드가 들어가있는 라벨 설정
		Inner_stock2.setPreferredSize(new Dimension(300, 400));
		Inner_stock2.setLayout(new GridLayout(3,2,30,50));
		Inner_stock2.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_stock2.add(l1);
		Inner_stock2.add(t1);
		Inner_stock2.add(l2);
		Inner_stock2.add(t2);
		Inner_stock2.add(l3);
		Inner_stock2.add(t3);

		//물품 추가삭제 버튼 부착
		Inner_stock3.setBorder(new EmptyBorder(10, 10, 10, 10));
		Inner_stock3.add(stockbtn1);
		Inner_stock3.add(stockbtn2);


		stockPanel.setLayout(new BorderLayout());//stockPanel을 BorderLayout으로 설정
		stockPanel.add(Inner_stock1,BorderLayout.NORTH);//관리자 아이디 표시 라벨이들어가있는 패널 NORTH설정
		stockPanel.add(Inner_stock2,BorderLayout.WEST);//품목,수량,대여가능이 들어가있는 라벨과, 물품을 추가시킬 텍스트필드가 들어가있는 라벨 WEST으로 설정
		stockPanel.add(Inner_stock3,BorderLayout.SOUTH);//버튼이들어가있는 패널 SOUTH 지정
		stockPanel.add(Inner_stock4,BorderLayout.CENTER);//JTabl이 들어가있는 패널 CENTER 설정



	}

	public void day() {//예약 현황 매소드

		//JTable 설정
		contents2 = new String[0][0];
		model2 = new DefaultTableModel(contents2,header2);//DefaultTableModel에 JTable에 들어갈 행과열의 정보값을 넣어줌
		table = new JTable(model2);//JTable에 행과열의 정보값이 들어간 DefaultTableModel을 JTable객체에 넣어줌
		sc = new JScrollPane(table);//스크롤에 테이블 부착
		
		Inner_day1.setBorder(new EmptyBorder(5, 5, 5, 5));//메인패널 안에 padding 5씩 설정
		Inner_day1.add(mangerID2);//로그인한 아이디를 보여주는 라벨을 부착
		Inner_day1.add(daybtn1);//업데이트 버튼 부착
		
		sc.setPreferredSize(new Dimension(400,300));///스크롤 크기 지정
		Inner_day2.add(sc);
		
		
		personal_day.setLayout(new BorderLayout());//예약현황 패널 레이아웃 BorderLayout설정
		personal_day.add(Inner_day1,BorderLayout.NORTH);//업데이트버튼이있는 패널을 위쪽에 설정
		personal_day.add(Inner_day2,BorderLayout.CENTER);//예약현황을보여주는 테이블을 센터에 설정	
		
	}



	public Managerpanel(){//생성자
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));//frame BorderLayout 설정 

		buttonpanel.add(b1);
		buttonpanel.add(b2);
		buttonpanel.add(b3);

		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10));//메인패널 안에 padding 10씩 설정


		mainpanel.setBackground(Color.BLACK);
		frame.add(mainpanel,BorderLayout.CENTER);//mainpanel 센터 부착
		mainpanel.setLayout(cardLayout);//mainpanel은 cardLayout설정
		frame.add(buttonpanel,BorderLayout.NORTH);//buttonpanel 위쪽 지정
		mainpanel.add(stockPanel,"stock");//mainpanel cardLayout에 재고 관리 패널,예약현황 패널,휴무일 패널 설정
		mainpanel.add(personal_day,"personal_day");		
		mainpanel.add(Inner_day3,"hol");
		
		
		stock();//재고관리 매소드
		day();//예약현환 메소드
		holl();//휴무일 매소드

		frame.setTitle("관리자 화면");
		frame.setSize(900, 500);
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
