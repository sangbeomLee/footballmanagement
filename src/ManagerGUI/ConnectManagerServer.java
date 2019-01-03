package ManagerGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;

import ManagerGUI.Manager.SubFrame;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.Message;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConnectManagerServer implements Runnable{
   public Manager manager;
   private BufferedReader inMsg = null;
   private PrintWriter outMsg = null;
   ManagerController macontroll;
   String fname;
   Socket socket = null;
   Message m;
   Logger logger;
   Thread thread;
   Gson gson;
   boolean status;
   SubFrame sub;
   String id;
   
   public ConnectManagerServer(ManagerController control) {
	   macontroll = control;
   }
   
   public void connectServer(Manager manager) {
      logger = Logger.getLogger(this.getClass().getName());
      this.manager = manager;
      try {
         socket = new Socket("172.16.30.242",8888); //연결할 ip주소
         logger.log(INFO,"[Manager]Server 연결 성공!!");
         gson = new Gson();
         inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         outMsg = new PrintWriter(socket.getOutputStream(),true);
         thread = new Thread(this);
         thread.start();
         
      } catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         logger.log(WARNING,"[MultiChatUI]connectServer() Exception 발생");
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void run() {
      status = true;
      String msg;
      
      m = new Message();
      
      while(status) {
         try {
         
        	msg = inMsg.readLine();
            m = gson.fromJson(msg, Message.class);//Message 클래스 형식으로 변환해준다.
            System.out.println(m.msg1 + "###" + m.msg2);
            
            if(m.msg2.equals("productinfo")) // 관리자에 재고 메세지 받는 조건
            {
            	String a = m.msg1;
            	macontroll.show_stock(a);
            }
            
            else if(m.msg2.equals("checkreservation")) // 관리자에 예약 현황 메세지 받는 조건
            {
            	String a = m.msg1;
            	macontroll.show_reservation(a);
            }
            
            else if(m.msg2.equals("finish")) // 관리자 화면에 재고 다뿌려주었다고 완료 메세지 받는 조건
            {
            	macontroll.appMain2();
            }
            
            
            else if(m.msg2.equals("changed")) //재고 수정되었다고 서버에서 받아옴
            {
                JOptionPane.showMessageDialog(null,"수정 되었습니다.");
            }
            
            else if(m.msg2.equals("notchanged")) //재고 수정안되었다고 서버에서 받아옴
            {
                JOptionPane.showMessageDialog(null,"수정 실패하였습니다.","", JOptionPane.WARNING_MESSAGE);

            }
            
            else if(m.msg2.equals("addproduct")) //재고 추가되었다고 서버에서 받아옴
            {
            
                JOptionPane.showMessageDialog(null,"추가 되었습니다.");
            }
            
            else if(m.msg2.equals("notaddproduc")) //재고 추가 안되었다고 서버에서 받아옴
            {
                JOptionPane.showMessageDialog(null,"추가 실패하였습니다.","", JOptionPane.WARNING_MESSAGE);

            }
            
            else if(m.msg2.equals("deleteproduct")) // 재고 삭제가되었다고 서버에서 받아옴
            {
            	
                JOptionPane.showMessageDialog(null,"삭제 되었습니다.");
            }
            
            else if(m.msg2.equals("notdeleteproduct")) // 재고 삭제 안되었다고 서버에서 받아옴
            {
                JOptionPane.showMessageDialog(null,"삭제가 실패하였습니다.","", JOptionPane.WARNING_MESSAGE);

            }
            else if(m.msg2.equals("checkcozyday")) // 휴무일 현황 메세지 받는 조건
            {
            	String a = m.msg1;
            	macontroll.hol_sh(a);
            }
            else if(m.msg2.equals("cozyday")) // 휴무일 현황 메세지 받는 조건
            {
            	JOptionPane.showMessageDialog(null,"추가 되었습니다.");
            }
            
            
            //회원쪽 
            if(m.msg2.equals("회원가입실패"))
            {
               JOptionPane.showMessageDialog(null,"아이디가 중복됩니다.","", JOptionPane.WARNING_MESSAGE);
            }
            else if(m.msg2.equals("회원가입성공")) {
               JOptionPane.showMessageDialog(null, "회원가입이되었습니다");
            
            }
            
            else if(m.msg2.equals("로그인성공"))
            {
            	id = m.id;
               JOptionPane.showMessageDialog(null, "로그인성공");//메세지 받는거
               macontroll.part2();//관리자 화면으로 전환
            }
            else if(m.msg2.equals("비밀번호다름"))
            {
               JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.","" ,JOptionPane.WARNING_MESSAGE);
            }
            else if(m.type1.equals("fname"))
            {
            	//System.out.println(m.msg1);
            	fname = m.msg1;
            	String[] frameArray = fname.split("#");
            	System.out.println(frameArray[0]);
            	
            	sub = manager.sub;
				for(int i=0;i<frameArray.length;i++)
				sub.Field.addItem(frameArray[i]);
				sub.setLocation(200, 50);
				sub.setVisible(true);
            }
         } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.log(WARNING,"[manager]메시지 스트림 종료!!");

            e.printStackTrace();
         }       
      }

   
   }
   
   public Socket setSocket() {
      return this.socket;
   }
   public Thread setThread() {
      return this.thread;
   }
   public BufferedReader setInMsg() {
      return this.inMsg;
   }
   public PrintWriter setOutMsg() {
      return this.outMsg;
   }
   public Gson setGson() {
      return this.gson;
   }
   public String Setfname() {
	   return fname;
   }
   public void setSub(SubFrame sub) {
	   this.sub = sub;
   }
   public String setID() {
		return this.id;
	}
   
}