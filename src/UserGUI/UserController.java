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
	
	public UserController(User user,Userreserve reserve) {
		this.user = user;
		this.reserve = reserve;
		logger = Logger.getLogger(this.getClass().getName());
		
		connectS = new UserConnectServer();
		connectS.connectServer(user,reserve);
		socket = connectS.setSocket();
		inMsg = connectS.setInMsg();
		outMsg = connectS.setOutMsg();
		thread = connectS.setThread();
		gson = connectS.setGson();
		
	}
	
	public void appMain() {
			user.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					
					if(obj ==user.Log) {
						outMsg.println(gson.toJson(new Message(user.ID.getText(),user.Pass.getText(),"","customer","login")));
						logger.info("[로그인 보냄]!!");
						user.setVisible(false);
						reserve.setVisible(true);
					}
				
					else if(obj == user.sub.Signup)
					{
						if(user.sub.Passin.getText().equals(user.sub.Passrein.getText()))
						{

						System.out.println(user.sub.Passin.getText()+ "    " + user.sub.Passre.getText());
						id = user.sub.IDin.getText();
						pw = user.sub.Passin.getText();
						msg = user.sub.Name.getText()+"#"+user.sub.Mail.getText()+"#"+user.sub.Number.getText();
						type1 ="customer";
						type2 = "register";
					//	String msg = administer.sub.Name.getText()+"#"+administer.sub.Mail.getText()+"#"+administer.sub.Number.getText() + "#" + administer.sub.Field.getSelectedItem();
						
						m = new Message(id, pw, msg, type1, type2);
						outMsg.println(gson.toJson(m));
						JOptionPane.showMessageDialog(null, "생성되었습니다.");
						
						}
						else if(!(user.sub.Passin.getText().equals(user.sub.Passrein.getText())) ) {
							JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
							System.out.println(user.sub.Passin.getText()+ "    " + user.sub.Passre.getText());
							return;
						}
						
					}
					else if(obj == user.Join)
					{
						user.sub.setLocation(200, 50);
						user.sub.setVisible(true);
					}
					
					
				}
			});
			reserve.addButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Object obj = e.getSource();
					if(obj==reserve.reservebtn)
					{
						m = new Message(id, pw, msg, type1, type2);
						outMsg.println(gson.toJson(m));
						JOptionPane.showMessageDialog(null, "예약되었습니다.");
					
					}
					else if(obj==reserve.statebtn)
					{
						//m = new Message(id, pw, msg, type1, type2);
						//outMsg.println(gson.toJson(m));
						reserve.state.setVisible(true);
					}
					
				}
			});
			
			
		
	}
	
	
}
