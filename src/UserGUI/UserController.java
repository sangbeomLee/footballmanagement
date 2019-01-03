package UserGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;

import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.Message;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {
	public User user;
	public Userreserve reserve;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	String outm;
	Socket socket = null;
	Message m;
	Logger logger;
	Thread thread;
	Gson gson;
	UserConnectServer connectS;
	String id,pw,msg,type1,type2;
	State state;
	int num=0;
	
	//유저클래스와 유저 서버의 객체들을 받는다.
	public UserController(User user,Userreserve reserve) {
		this.user = user;
		this.reserve = reserve;
		state = reserve.state;
		//logger = Logger.getLogger(this.getClass().getName());
		
		connectS = new UserConnectServer(this);
		connectS.connectServer(user,reserve);
		socket = connectS.setSocket();
		inMsg = connectS.setInMsg();
		outMsg = connectS.setOutMsg();
		thread = connectS.setThread();
		gson = connectS.setGson();
		//서버를 객체를 만들어서 각변수에 저장.
	}
	
	public void appMain() {
		//각 UI마다 이벤트들을 주었음.
			user.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					
					if(obj ==user.Log) {
						id=user.ID.getText();
						outMsg.println(gson.toJson(new Message(user.ID.getText(),user.Pass.getText(),"","customer","login")));
						//logger.info("[로그인 보냄]!!");
						
					}//로그인 버튼시 메세지서버에 주어 프레임전환
				
					else if(obj == user.sub.Signup)
					{
						if(user.sub.Passin.getText().equals(user.sub.Passrein.getText()))
						{

						id = user.sub.IDin.getText();
						pw = user.sub.Passin.getText();
						msg = user.sub.Name.getText()+"#"+user.sub.Mail.getText()+"#"+user.sub.Number.getText();
						type1 ="customer";
						type2 = "register";
						
						m = new Message(id, pw, msg, type1, type2);
						outMsg.println(gson.toJson(m));
						
						}
						else if(!(user.sub.Passin.getText().equals(user.sub.Passrein.getText())) ) {
							JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
							System.out.println(user.sub.Passin.getText()+ "    " + user.sub.Passre.getText());
							return;
						}
						
					}//회원가입 이벤트 서버에 메세지를 주어 회원가입실시
					else if(obj == user.Join)
					{
						user.sub.setLocation(200, 50);
						user.sub.setVisible(true);
					}//회원가입 버튼이벤트  회원가입창나옴.
					
					
					
				}
			});
			reserve.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stubsgi
					Object obj = e.getSource();
					if(obj==reserve.reservebtn)
					{
						state.model1.setRowCount(0); //jtable 초기화
						String type1 ="customer";
						String type2 = "reserve";
						String msg = reserve.ground.getSelectedItem().toString() +"#"+reserve.days.getSelectedItem().toString()+"#"+reserve.time.getSelectedItem().toString();
						m = new Message(id, reserve.num.getText().toString(), msg, type1, type2);
						outMsg.println(gson.toJson(m));
						System.out.println("예약됨");
					
					}
					else if(obj==reserve.statebtn)//예약현황버튼
					{
						reserve.state.setVisible(true);
						String type1 ="customer";
						String type2 = "findstate";
						m = new Message(id, pw, "state", type1, type2);
						outMsg.println(gson.toJson(m));
						
					}
					else if(obj == reserve.ground)
					{
						System.out.println("메세지가 안와요");
						System.out.println(reserve.ground.getSelectedItem().toString());
					
						String type1 ="customer";
						String type2 = "finddate";
					
						m = new Message(id, pw, reserve.ground.getSelectedItem().toString(), type1, type2);
						outMsg.println(gson.toJson(m));
						reserve.days.removeAllItems();
					}
					else if(obj == reserve.days)
					{
						String type1 ="customer";
						String type2 = "findtime";
						try {
							thread.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						m = new Message(id, reserve.days.getSelectedItem().toString(),reserve.ground.getSelectedItem().toString() , type1, type2);
						outMsg.println(gson.toJson(m));
						
						reserve.time.removeAllItems();
						
					}
//					else if(obj == reserve.time)
//					{
//						
//					}
					else if(obj ==reserve.solbtn)
					{
						reserve.cardLayout.show(reserve.p, "sol");
					}
					else if(obj ==reserve.teambtn)
					{
						reserve.cardLayout.show(reserve.p, "main");
					}
					
					else if(obj == reserve.togeterbtn) 
					{
						
						//reserve.table2.getValueAt(reserve.table2.getSelectedRow(),0);
						int result = JOptionPane.showConfirmDialog(null, "용병지원할것입니까?","",JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION) {
							//예, 아니오버튼이아닌걸 누렀을때
						}
						else if(result == JOptionPane.YES_OPTION){
							//예버튼
						}
						else {
							//아니오 버튼
						}
						
						
					}
					
					
				}
			});
			state.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					if(obj ==state.statecancelbtn)
					{
						 if(state.table1.getSelectedRow() == -1)
			               {
			                  return;
			               }
			               else
			               {
			            	   
			
			                  String send = (String) state.table1.getValueAt(state.table1.getSelectedRow(),0);
			                  
			                  System.out.println(state.table1.getValueAt(state.table1.getSelectedRow(),0) );
			                  state.model1.removeRow(state.table1.getSelectedRow());//행 삭제
			                  String type1 ="customer";
							  String type2 = "findtime";
			                 // outMsg.println(gson.toJson(new Message(id,send,"",type1,type2))); //삭제되었다고 서버에 메세지 보내기
			                  
			                  num--;
			               }
						
						
					}
						
				}
			});
			
			
			
		
	}
	public void show_stock(String a)
	{
		state.contents1 = new String[num++][0];

		String arr [] = a.split("#");
		
		//System.out.println(arr[0] +"**");
		Vector<String> row = new Vector<String>();
		row.add("풋살장" + arr[0]); // 0에는 품목 1에는수량 2에는 수량 가능
		row.add(arr[1]);
		row.add(arr[2]);
		row.add(arr[3]);
		state.model1.addRow(row);//테이블에 한행 삽입
		//appMain2();
	}
	
	
}
