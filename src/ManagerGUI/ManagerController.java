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

public class ManagerController {
	public Manager administer;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	public SubFrame sub;
	
	String outm;
	Socket socket = null;
	Message m;
	Logger logger;
	Thread thread;
	Gson gson;
	ConnectManagerServer connectS;
	
	public ManagerController(Manager administer) {
		 this.administer = administer;
	      logger = Logger.getLogger(this.getClass().getName());
	      
	      connectS = new ConnectManagerServer();
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
					
					if(obj ==administer.Log) {
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
			

	}
	public void setSub(SubFrame sub) {
		   this.sub = sub;
	   }
	
}
