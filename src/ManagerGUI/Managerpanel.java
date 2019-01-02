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

	//�������� ������
	JPanel stockPanel = new JPanel();
	JPanel Inner_stock1 = new JPanel();//stock�г� ����
	JPanel Inner_stock2 = new JPanel();// stock�г� ����
	JPanel Inner_stock3 = new JPanel();//stock�г� �Ʒ���
	JPanel Inner_stock4 = new JPanel();//stock�г�  �߾�
	JPanel btn_label1 = new JPanel();
	JPanel btn_label2 = new JPanel();
	JPanel btn_label3 = new JPanel();
	JComboBox stockcombo = new JComboBox();
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JLabel l1 = new JLabel("�ӽ�1");
	JLabel l2 = new JLabel("�ӽ�2");
	JLabel l3 = new JLabel("�ӽ�3");
	JButton stockbtn1 = new JButton("�߰�");
	JButton stockbtn2 = new JButton("����");
	JButton stockbtn3 = new JButton("����");
	JTable stocktable;
	JScrollPane stock_sc;
	DefaultTableModel model1;
	TableCellRenderer renderer;
    final TableCellEditor editor = new DefaultCellEditor(new JTextField());


	//���� ��ȸ ���� ������
	JPanel personal_day = new JPanel();
	JPanel Inner_day1 = new JPanel();//personal_day�г� �ȿ��ִ� ���� �г�
	JPanel Inner_day2 = new JPanel();//personal_day�߾ӿ� �ִ� �г�
	JPanel Inner_day3 = new JPanel();//personal_day �Ʒ��� �ִ��г�
	JButton daybtn1 = new JButton("�ӽ�1");
	JButton daybtn2 = new JButton("�ӽ�2");
	JComboBox combo = new JComboBox();
	JTable table;
	JScrollPane sc;



	JButton b1 = new JButton("���� ����");
	JButton b2 = new JButton("�޹��� ����");


	public void stock() {

		String header [] = {"��ǰ","����","�뿩����"};
		String contents [][] = new String[3][3];
		contents[0][0] = "��";
		contents[1][0] = "����";
		contents[2][0] = "����";


		for(int i=0;i<3;i++)//ó�� ���� �ʱ�ȭ ���߿� ��������
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

		String header [] = {"��¥ �� �ð�","�ο�","��������"};
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



	Managerpanel(){//������
		System.out.println("helllllll");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));

		//�׼� ������ �ٴ°�
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

		frame.setTitle("������ UI");
		frame.setSize(900, 500);
		//frame.pack();
		frame.setVisible(true);

	}

	 class MyMouseListener2 implements ActionListener,CellEditorListener{

		/*
		 public void keyPressed(KeyEvent e) {
			 if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				 System.out.println("�ȴ���");
			 }

		 }
		 */

		@Override
		public void actionPerformed(ActionEvent e) {

		     //System.out.println("action performed");
			//int row = stocktable.getSelectedRow();//������ ���� ��������
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
			else if(e.getSource() == stockbtn2)//����
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
			else if(e.getSource() == stockbtn1)//�߰�
			{
				String input[] = new String[4];

				input[0] = t1.getText();
				input[1] = t2.getText();
				input[2] = t3.getText();
				model1.addRow(input);

				t1.setText("");//�ؽ�Ʈ�ʵ忡�ִ� �۾� �ʱ�ȭ
				t2.setText("");
				t3.setText("");
				System.out.println("�ȴ���");
			}
			else if(e.getSource() == stockbtn3)//����
			{
				/*
				 int row = stocktable.getSelectedRow();//������ ���� ��������
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
			System.out.println("�ȴ���");

			String value = (String) editor.getCellEditorValue();
            TableModel ex = stocktable.getModel();

			int row = stocktable.getSelectedRow();//������ ���� ��������
			int col = stocktable.getSelectedColumn();


			ex.setValueAt(value,row, col);
		}






	 }//ActionListener close


}
