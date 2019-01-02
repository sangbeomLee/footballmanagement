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

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


public class Managerpanel{
	
	JFrame frame = new JFrame();
	CardLayout cardLayout = new CardLayout(0,0);
	JPanel mainpanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	
	//재고관리 변수들
	JPanel stockPanel = new JPanel();
	JPanel Inner_stock1 = new JPanel();//stock패널 위쪽
	JPanel Inner_stock2 = new JPanel();// stock패널 왼쪽
	JPanel Inner_stock3 = new JPanel();//stock패널 아래쪽
	JPanel Inner_stock4 = new JPanel();//stock패널  중앙
	JPanel btn_label1 = new JPanel();
	JPanel btn_label2 = new JPanel();
	JPanel btn_label3 = new JPanel();
	JComboBox stockcombo = new JComboBox();
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JLabel l1 = new JLabel("임시1"); 
	JLabel l2 = new JLabel("임시2");
	JLabel l3 = new JLabel("임시3");
	JButton stockbtn1 = new JButton("추가");
	JButton stockbtn2 = new JButton("삭제");
	JButton stockbtn3 = new JButton("수정");
	JTable stocktable;
	JScrollPane stock_sc;
	DefaultTableModel model1;
	TableCellRenderer renderer;
    final TableCellEditor editor = new DefaultCellEditor(new JTextField());

    
	//예약 조회 관리 변수들
	JPanel personal_day = new JPanel();
	JPanel Inner_day1 = new JPanel();//personal_day패널 안에있는 위쪽 패널
	JPanel Inner_day2 = new JPanel();//personal_day중앙에 있는 패널
	JPanel Inner_day3 = new JPanel();//personal_day 아래에 있는패널
	JButton daybtn1 = new JButton("임시1");
	JButton daybtn2 = new JButton("임시2");
	JComboBox combo = new JComboBox();
	JTable table;
	JScrollPane sc;
	
	
	
	JButton b1 = new JButton("재고 관리");
	JButton b2 = new JButton("휴무일 관리");
	

	public void stock() {
		
		String header [] = {"물품","수량","대여가능"};
		String contents [][] = new String[3][3];
		contents[0][0] = "공";
		contents[1][0] = "조끼";
		contents[2][0] = "골대";
		
		
		for(int i=0;i<3;i++)//처음 수량 초기화 나중에 지워야함
		{
			for(int j=1;j<3;j++)
			{
				contents[i][j] = "0";
			}
		}
		
	
		model1 = new DefaultTableModel(contents,header);

		stocktable = new JTable(model1);
		stock_sc = new JScrollPane(stocktable);
		Inner_stock4.add(stock_sc);
		
		renderer = new DefaultTableCellRenderer();
		
		
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
		
		String header [] = {"날짜 및 시간","인원","예약유무"};
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
	
	
	
	Managerpanel(){//생성자
		System.out.println("helllllll");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));

		//액션 리스너 다는곳
		b1.addActionListener(new MyMouseListener2());
		b2.addActionListener(new MyMouseListener2());
		stockbtn1.addActionListener(new MyMouseListener2());
		stockbtn2.addActionListener(new MyMouseListener2());
		stockbtn3.addActionListener(new MyMouseListener2());
        editor.addCellEditorListener(new MyMouseListener2());

		
		//buttonpanel.setPreferredSize(new Dimension(1000, 100));
		buttonpanel.add(b1);
		buttonpanel.add(b2);
		
		
		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10));		
		
		
		mainpanel.setBackground(Color.BLACK);
		frame.add(mainpanel,BorderLayout.CENTER);
		mainpanel.setLayout(cardLayout); 
		
		stockPanel.setBackground(Color.YELLOW);
		frame.add(buttonpanel,BorderLayout.NORTH);
		mainpanel.add(stockPanel,"stock");
		
		//personal_day.setBackground(Color.BLUE);
		mainpanel.add(personal_day,"personal_day");
		
		stock();
		day();

		frame.setTitle("관리자 UI");
		frame.setSize(900, 500);
		//frame.pack();
		frame.setVisible(true);
		
	}

	 class MyMouseListener2 implements ActionListener,CellEditorListener{

		/*
		 public void keyPressed(KeyEvent e) { 
			 if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
				 
				 System.out.println("된다잇");
			 }
			 
		 }
		 */
		 
		@Override
		public void actionPerformed(ActionEvent e) {
			
		     //System.out.println("action performed");
			//int row = stocktable.getSelectedRow();//선택한 행렬 가져오기
			//int col = stocktable.getSelectedColumn();

			//stocktable.editCellAt(row, col);
			//System.out.println(stocktable.getValueAt(row, col));
			
			
			
			if(e.getSource() == b1)
			{
				cardLayout.show(mainpanel, "stock");
				//System.out.println(stocktable.getColumnName(arg0));
			}
			else if(e.getSource() == b2)
			{
				cardLayout.show(mainpanel, "personal_day");
			}
			else if(e.getSource() == stockbtn2)//삭제
			{
				if(stocktable.getSelectedRow() == -1)
				{
					return;
				}
				else
				{
					model1.removeRow(stocktable.getSelectedRow());
				}
				
			}
			else if(e.getSource() == stockbtn1)//추가
			{
				String input[] = new String[4];
				
				input[0] = t1.getText();
				input[1] = t2.getText();		
				input[2] = t3.getText();
				model1.addRow(input);
				
				t1.setText("");//텍스트필드에있는 글씨 초기화
				t2.setText("");
				t3.setText("");
				System.out.println("된다잇");
			}
			else if(e.getSource() == stockbtn3)//수정
			{
				/*
				 int row = stocktable.getSelectedRow();//선택한 행렬 가져오기
				 int col = stocktable.getSelectedColumn();
				 
				 stocktable.setValueAt(t1.getText(),row,col);
				 stocktable.setValueAt(t2.getText(),row,col);
				 stocktable.setValueAt(t3.getText(),row,col);
				 */
				
			}
	
			
			
		}//actionPerformed close

		@Override
		public void editingCanceled(ChangeEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void editingStopped(ChangeEvent e) {
			//System.out.println(stocktable.getValueAt(row, col));
			System.out.println("된다잇");

			String value = (String) editor.getCellEditorValue();
            TableModel ex = stocktable.getModel();
			
			int row = stocktable.getSelectedRow();//선택한 행렬 가져오기
			int col = stocktable.getSelectedColumn();
			
			
			ex.setValueAt(value,row, col);
		}



		


	 }//ActionListener close

	 
}
