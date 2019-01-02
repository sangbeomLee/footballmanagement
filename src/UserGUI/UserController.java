package UserGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.Message;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {
	public User user;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	
	String outm;
	Socket socket = null;
	Message m;
	Logger logger;
	Thread thread;
	Gson gson;
	ConnectServer connectS;
	
	public UserController(User user) {
		this.user = user;
		logger = Logger.getLogger(this.getClass().getName());
		gson = new Gson();
		connectS = new ConnectServer();
		connectS.connectServer();
		
	}
	/*public void connectServer() {
		try {
			socket = new Socket("172.16.30.242",8888);
			logger.log(INFO,"[Client]Server 연결 성공!!");
			
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
	}*/
	public void appMain() {
			user.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					
					if(obj ==user.Log) {
						outMsg.println(gson.toJson(new Message(user.ID.getText(),user.Pass.getText(),"","customer","login")));
						logger.info("[로그인 보냄]!!");
					}
					
					if(obj == user.sub.Signup)
					{
						if(user.sub.Passin.getText()!=user.sub.Passre.getText()) {
							JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
							return;
						}
						String id = user.sub.IDin.getText();
						String pw = user.sub.Passin.getText();
						String msg = user.sub.Name.getText()+"#"+user.sub.Mail.getText()+"#"+user.sub.Number.getText();
						String type1 ="customer";
						String type2 = "register";
						m = new Message(id, pw, msg, type1, type2);
						outMsg.println(gson.toJson(m));
						
					}
					
				}
			});
			
		
	}
	/*public void run() {
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
					JOptionPane.showMessageDialog(null, "로그인성공");
					user.setVisible(false);
				}
				else if(m.msg2.equals("비밀번호다름"))
				{
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.","" ,JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.log(WARNING,"[User]메시지 스트림 종료!!");

				e.printStackTrace();
			}
			logger.info("[MultiChatUI]"+thread.getName() + "메시지 수진 스레드 종료됨!!");
		}
	}*/
	
}
