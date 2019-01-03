package ManagerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.google.gson.Gson;

import ManagerGUI.Manager.SubFrame;
import Server.Message;

public class ManagerController {
	public Manager administer;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	public SubFrame sub;
	
	int num=0;
	String id;
	String outm;
	Socket socket = null;
	Message m;
	Logger logger;
	Thread thread;
	Gson gson;
	ConnectManagerServer connectS;
	Managerpanel Manager; //관리자 UI 변수
	
	public ManagerController() {
		
		 //Manager = new Managerpanel();	

		administer= new Manager(); //로그인 화면
		appMain();
		
		  
		
		  
		 //this.administer = administer;
	      logger = Logger.getLogger(this.getClass().getName());
	      
	      connectS = new ConnectManagerServer(this);
	      connectS.connectServer(administer);
	      
	      
	      socket = connectS.setSocket();
	      inMsg = connectS.setInMsg();
	      outMsg = connectS.setOutMsg();
	      thread = connectS.setThread();
	      gson = connectS.setGson();

	      
		
	}
	public void appMain() {
	
			administer.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					
					if(obj ==administer.Log) { //로근인 서버에 메시지 보내는거
						outMsg.println(gson.toJson(new Message(administer.ID.getText(),administer.Pass.getText(),"","customer","login")));
						logger.info("[로그인 보냄]!!");
					}
					
					if(obj == administer.sub.Signup)
					{
						if(!administer.sub.Passin.getText().equals(administer.sub.Passrein.getText())) {
							JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
							return;
						}
						String id = administer.sub.IDin.getText();
						String pw = administer.sub.Passin.getText();
						String msg = administer.sub.Name.getText()+"#"+administer.sub.Mail.getText()+"#"+administer.sub.Number.getText() + "#" + administer.sub.Field.getSelectedItem();
						String type1 ="administer";
						String type2 = "register";
						m = new Message(id, pw, msg, type1, type2);
						outMsg.println(gson.toJson(m));
						
					}
					if(obj ==administer.Join )
					{
						m = new Message("","","","administer","field");
						outMsg.println(gson.toJson(m));
						
						
					}
					
					
				}
			});
			

	}//appmain close
	
	
	public void appMain2() {// 관리자UI 액션 리스너
		
		Manager.addTableModelListener(new TableModelListener() {//테이블에 리스너 

			// 선택한 셀 좌표값 알아오는 변수
			//int row = Manager.stocktable.getEditingRow();
			//int column = Manager.stocktable.getEditingColumn(); 
			
			@Override
			public void tableChanged(TableModelEvent e) {
				//e.getColumn();//이벤트가 발생한 셀의 칼럼
				//e.getFirstRow();//이벤트가 발생한 셀의 행

				Object va = Manager.model1.getValueAt(e.getFirstRow(), e.getColumn());//이벤트가 발생한 셀의 값

				System.out.println(e.getColumn());
				System.out.println(e.getFirstRow());
				System.out.println(va);
				
				//Manager.stocktable.getValueAt(row,column);
				//System.out.println(Manager.stocktable.getValueAt(row,column));
			}
			
			
		}	
	); // addTableModelListener close
		
		
		Manager.addButtonActionListener(new ActionListener() { //관리자UI 액션 리스너

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == Manager.b1)//물품관리 패널 전환
				{
					Manager.cardLayout.show(Manager.mainpanel, "stock");
				}
				else if(e.getSource() == Manager.b2)//예약 현황 패널 전환
				{
					Manager.cardLayout.show(Manager.mainpanel, "personal_day");
				}
				else if(e.getSource() == Manager.stockbtn2)//삭제
				{
					if(Manager.stocktable.getSelectedRow() == -1)
					{
						return;
					}
					else
					{
						Manager.model1.removeRow(Manager.stocktable.getSelectedRow());
					}

				}
				else if(e.getSource() == Manager.stockbtn1)//추가
				{
					String input[] = new String[4];

					input[0] = Manager.t1.getText();
					input[1] = Manager.t2.getText();
					input[2] = Manager.t3.getText();
					Manager.model1.addRow(input);

					Manager.t1.setText("");
					Manager.t2.setText("");
					Manager.t3.setText("");
				}
				
			}
			
		});
	}
	
	
	public void setSub(SubFrame sub) {
		   this.sub = sub;
	   }
	
	public void part2() {//관리자 화면 메소드
		
		
		administer.setVisible(false);//로그인 화면 끄기
		
		id = connectS.setID();
		outMsg.println(gson.toJson(new Message(id, "", "product", "administer", "setproduct"))); // 서버에 메세지 보내고
		Manager = new Managerpanel(); // 관리자 화면 객체 생성
		//appMain2();
	}
	
	public void show_stock(String a) {//아이디값에 맟추어 데이터값 뿌려주는 매소드
		
		
		Manager.contents= new String[num++][0];

		String arr [] = a.split("#");
		
		System.out.println(arr[0] +"**");
		Vector<String> row = new Vector<String>();
		row.add(arr[0]); // 0에는 품목 1에는수량 2에는 수량 가능
		row.add(arr[1]);
		row.add(arr[2]);
		Manager.model1.addRow(row);//테이블에 한행 삽입
		//appMain2();
		
	}
	
	
	
}













