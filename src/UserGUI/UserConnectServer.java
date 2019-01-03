package UserGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;
//import com.sun.corba.se.spi.activation.Server;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.Message;
import java.io.IOException;
import java.io.InputStreamReader;


public class UserConnectServer implements Runnable{
	public User user;
    public Userreserve reserve;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	String [] ground = new String[4];
	String [] days = new String[7];
	String [] time = new String[5];
	String outm;
	Socket socket = null;
	Message m;
	Logger logger;
	Thread thread;
	Gson gson;
	boolean status;
	String id;
	int cnt=0;
	int gk=0,dk=0,tk=0;
	UserController userconnect;
	
	
	public UserConnectServer(UserController con) {
		userconnect = con;
	}
	
	public void connectServer(User user, Userreserve reserve) {
		this.user = user;
		this.reserve = reserve;
		logger = Logger.getLogger(this.getClass().getName());
		
		try {
			socket = new Socket("172.16.30.242",8888);
			logger.log(INFO,"[Client]Server 연결 성공!!");
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
					JOptionPane.showMessageDialog(null, "로그인성공");
					user.setVisible(false);
					user.dispose();
					reserve.setVisible(true);
				}
				else if(m.msg2.equals("비밀번호다름"))
				{
					
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.","" ,JOptionPane.WARNING_MESSAGE);
				}
	    		
	    		  
	    		else if(m.msg2.equals("풋살장이름"))
		        {

	    			//int j=0;
	    			//System.out.println(m.msg1);
	    			String fname = m.msg1;
	    			//reserve.days.removeAllItems();
	    			//reserve.time.removeAllItems();
	    			String[] Array = fname.split("#");
		            	for(int i=0;i<Array.length;i++)
		            		reserve.ground.addItem(Array[i]);
		            	/*for(j=0;)
		            	if(Array[0]=="1")
		            		
		            	.Field.addItem(frameArray[i]);
		            

*/
		        }
	    		else if(m.msg2.equals("fdate"))
	    		{
	    			String fname = m.msg1;
	    			String[] Array = fname.split("#");
	    			///	System.out.println("#############################################");
	    			//reserve.time.removeAllItems();
	    			
	            	for(int i=0;i<Array.length;i++) {
	            		reserve.days.addItem(Array[i]);
	            		System.out.println(Array[i]);
	            	}
	            
	     		}
	    		else if(m.msg2.equals("ftime"))
	    		{
	    	    	String fname = m.msg1;
	            	String[] Array = fname.split("#");
	    			for(int i=0;i<Array.length;i++)
	            		reserve.time.addItem(Array[i]);
	    			
	    		}
	    		else if(m.msg2.equals("ftime"))
	    		{
	    	    	String fname = m.msg1;
	            	String[] Array = fname.split("#");
	    			for(int i=0;i<Array.length;i++)
	            		reserve.time.addItem(Array[i]);
	    		}
	    		else if(m.msg2.equals("notreserve"))
	    		{
	    			JOptionPane.showMessageDialog(null, "예약 실패");
	    		}
	    		else if(m.msg2.equals("reserve"))
	    		{
	    			JOptionPane.showMessageDialog(null, "로그인성공");
	    		}
	    		else if(m.msg2.equals("state"))
	    		{
	    			String a = m.msg1;
	            	System.out.println(a+"**");
	    			userconnect.show_stock(a);
	    		}
	    		/*else if(m.msg2.equals(""))
	    		{
	
	    		}*/
			

			} catch (IOException e) {
				logger.log(WARNING,"[User]메시지 스트림 종료!!");

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
	
}

