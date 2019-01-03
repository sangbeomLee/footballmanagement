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
	
	int num=0;//재고관리 JTable 행 숫자
	int num2=0;//예약현황  JTable 행 숫자
	int num3=0;//휴무일현황  JTable 행 숫자
	
	String ground;
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
		
<<<<<<< HEAD
	
		administer= new Manager(); //로그인 화면
		appMain();
		
		  
		
		  
		  logger = Logger.getLogger(this.getClass().getName());
=======

		administer= new Manager(); //로그인 화면
		appMain();
		

	      logger = Logger.getLogger(this.getClass().getName());
>>>>>>> bsj
	      
	      connectS = new ConnectManagerServer(this);
	      connectS.connectServer(administer);
	      
	      
	      socket = connectS.setSocket();
	      inMsg = connectS.setInMsg();
	      outMsg = connectS.setOutMsg();
	      thread = connectS.setThread();
	      gson = connectS.setGson();

			
		
	}
	public void appMain() {//로그인ui액션 리스너
	
			administer.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					
<<<<<<< HEAD
					if(obj ==administer.Log) { //로근인 서버에 메시지 보내는거
=======
					if(obj ==administer.Log) { //로그인 서버에 메시지 보내는거
>>>>>>> bsj
						outMsg.println(gson.toJson(new Message(administer.ID.getText(),administer.Pass.getText(),"","administer","login")));
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
					if(obj==administer.ID)
					{
						administer.ID.setText("");
					}
					if(obj==administer.Pass)
					{
						administer.Pass.setText("");
					}
					
					
				}
			});
			

	}//appmain close
	
	
	public void appMain2() {// 관리자UI 액션 리스너
		
		Manager.addButtonActionListener(new ActionListener() { //관리자UI 액션 리스너

				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == Manager.b1)//물품관리 패널 전환
				{
					Manager.frame.setSize(900,500);
					Manager.cardLayout.show(Manager.mainpanel, "stock");
				}
				else if(e.getSource() == Manager.b2)//예약 현황 패널 전환
				{
					Manager.frame.setSize(900,500);
					Manager.cardLayout.show(Manager.mainpanel, "personal_day");
				}
				else if(e.getSource() == Manager.b3)//휴무일 패널 전환
				{
					Manager.model3.setRowCount(0);//jtable 초기화

					
					outMsg.println(gson.toJson(new Message(id,"","","administer","checkcozyday"))); //예약현황 서버에 요청
					
					
					Manager.frame.setSize(400,400);
					Manager.cardLayout.show(Manager.mainpanel, "hol");
				}
				else if(e.getSource() == Manager.stockbtn2)//행 삭제
				{
					
					if(Manager.stocktable.getSelectedRow() == -1)
					{
						return;
					}
					else
					{
						
						//Manager.stocktable.getValueAt(row,column);
						//System.out.println(Manager.stocktable.getSelectedRow());
						
						String send = (String) Manager.stocktable.getValueAt(Manager.stocktable.getSelectedRow(),0);
						
						System.out.println(Manager.stocktable.getValueAt(Manager.stocktable.getSelectedRow(),0) );
						Manager.model1.removeRow(Manager.stocktable.getSelectedRow());//행 삭제
						
						outMsg.println(gson.toJson(new Message(id,send,"","administer","deleteproduct"))); //삭제되었다고 서버에 메세지 보내기
						
						num--;
					}

				}
				else if(e.getSource() == Manager.stockbtn1)//행 추가
				{
					
					num++;//행 증가
					
					String input[] = new String[4];

					input[0] = Manager.t1.getText();
					input[1] = Manager.t2.getText();
					input[2] = Manager.t3.getText();
					Manager.model1.addRow(input);

					Manager.t1.setText("");
					Manager.t2.setText("");
					Manager.t3.setText("");
					
					String send = input[0] + "#" + input[1] + "#" + input[2];
					outMsg.println(gson.toJson(new Message(id,send,"","administer","addproduct"))); //추가되었다고 서버에 메세지 보내기
								
					
				}
				else if(e.getSource() == Manager.daybtn1)//에약현황  업데이트
				{
					Manager.model2.setRowCount(0); //jtable 초기화
					num2=0;//예약현황 배열 초기화
					outMsg.println(gson.toJson(new Message(id,"","","administer","checkreservation"))); //예약현황 서버에 요청
				}
				else if(e.getSource() == Manager.daybtn2)//휴무일 추가 버튼
				{
					num3++;
					String input[] = new String[1];
					input[0] = Manager.pdt.getText();
					String send = Manager.pdt.getText();//2019-09-03
					
					System.out.println(send+"*******");
					Manager.model3.addRow(input);
					
										
					outMsg.println(gson.toJson(new Message(id,send,"","administer","addcozyday"))); //예약현황 서버에 요청
					
					
					Manager.pdt.setText("0000-00-00");
					
				}
				else if(e.getSource() == Manager.pdt)//휴무일 지정 텍스트 필드
				{
					System.out.println("되는건가???");
					Manager.pdt.setText("");
				}
				else if(e.getSource() == Manager.daybtn3)//휴무일행 삭제
				{
					
					if(Manager.table3.getSelectedRow() == -1)
					{
						return;
					}
					else
					{
						
						
						String send = (String) Manager.table3.getValueAt(Manager.table3.getSelectedRow(),0);
						
						System.out.println(Manager.table3.getValueAt(Manager.table3.getSelectedRow(),0) );
						Manager.model3.removeRow(Manager.table3.getSelectedRow());//행 삭제
						
						outMsg.println(gson.toJson(new Message(id,send,"","administer","deleteproduct"))); //삭제되었다고 서버에 메세지 보내기
						
						num3--;
					}
				}
				
				
			}
			
		});//addButtonActionListener close
		

			Manager.addTableModelListener(new TableModelListener() {//테이블에 리스너 

				// 선택한 셀 좌표값 알아오는 변수
				//int row = Manager.stocktable.getEditingRow();
				//int column = Manager.stocktable.getEditingColumn(); 
		
				@Override
				public void tableChanged(TableModelEvent e) {
					//e.getColumn();//이벤트가 발생한 셀의 칼럼
					//e.getFirstRow();//이벤트가 발생한 셀의 행
					
					
					String input[] = new String[4];//서버에 보낼 string 배열
					int eventType = e.getType();
					if(eventType == 0) //eventType( 행이 추가되면 1 , 행이 삭제되면 -1 , 수정되면 0)
					{
						Object va = Manager.model1.getValueAt(e.getFirstRow(), e.getColumn());//이벤트가 발생한 셀의 값
						
						input[0] = (String) Manager.stocktable.getValueAt(Manager.stocktable.getSelectedRow(),0);
						input[1] = (String) Manager.stocktable.getValueAt(Manager.stocktable.getSelectedRow(),1);
						input[2] = (String) Manager.stocktable.getValueAt(Manager.stocktable.getSelectedRow(),2);
						
						String send = input[0] + "#" + input[1] + "#" + input[2];
						
						outMsg.println(gson.toJson(new Message(id,send,"","administer","changeproduct"))); //수정 되었다고 서버에 메세지 보내기
						System.out.println(send);

					}

					//System.out.println(e.getColumn());
					//System.out.println(e.getFirstRow());
					//System.out.println(va);
					
					//Manager.stocktable.getValueAt(row,column);
					//System.out.println(Manager.stocktable.getValueAt(row,column));
					
				}

			}	
		); // addTableModelListener close
			


		
	}//appmain2 close
	
	
	public void setSub(SubFrame sub) {
		   this.sub = sub;
	   }	
	
	public void part2() {//관리자 화면 메소드
		
		
		administer.setVisible(false);//로그인 화면 끄기
		
		id = connectS.setID();
		
		outMsg.println(gson.toJson(new Message(id, "", "product", "administer", "setproduct"))); // 서버에 메세지 보내고
		Manager = new Managerpanel(); // 관리자 화면 객체 생성
		Manager.mangerID.setText(id + "님이 로그인 하였습니다");//화면에 접속한 아이디 갱신 
		Manager.mangerID2.setText(id + "님이 로그인 하였습니다                                                  ");//화면에 접속한 아이디 갱신
		//Manager.show_manger.setText(       );///////////////////// 
		
	}
	
	public void show_stock(String a) {//아이디값에 맟추어 데이터값 뿌려주는 매소드
		
		
		Manager.contents= new String[num++][0];
		
		
		String arr [] = a.split("#");
		
		System.out.println(arr[0]);
		Vector<String> row = new Vector<String>();
		row.add(arr[0]); // 0에는 품목 1에는수량 2에는 수량 가능
		row.add(arr[1]);
		row.add(arr[2]);
		Manager.model1.addRow(row);//테이블에 한행 삽입
		//appMain2();
		
	}
	
	public void show_reservation(String a){
		
		Manager.contents2 = new String[num2++][0];
		
		String arr [] = a.split("#");
		
		//System.out.println(arr[0]);
		Vector<String> row = new Vector<String>();
		row.add(arr[0]); // 0에는 날짜 1에는시간 2에는 유저id
		row.add(arr[1]);
		row.add(arr[2]);
		Manager.model2.addRow(row);//테이블에 한행 삽입
		
		
	}
	
	public void hol_sh(String a){
		
		Manager.contents3 = new String[num3++][0];
		
		String arr = a;
		
		Vector<String> row = new Vector<String>();
		row.add(arr); // 0에는 날짜 1에는시간 2에는 유저id

		Manager.model3.addRow(row);//테이블에 한행 삽입
	
	}
	
	
	
	
}//managerController close













