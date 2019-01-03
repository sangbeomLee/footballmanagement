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
   String fname;
   Socket socket = null;
   Message m;
   Logger logger;
   Thread thread;
   Gson gson;
   boolean status;
   SubFrame sub;
   public void connectServer(Manager manager) {
      logger = Logger.getLogger(this.getClass().getName());
      this.manager = manager;
      try {
         socket = new Socket("172.16.30.242",8888);
         logger.log(INFO,"[Manager]Server ���� ����!!");
         gson = new Gson();
         inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         outMsg = new PrintWriter(socket.getOutputStream(),true);
         thread = new Thread(this);
         thread.start();
         
      } catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         logger.log(WARNING,"[MultiChatUI]connectServer() Exception �߻�");
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
            m = gson.fromJson(msg, Message.class);//Message Ŭ���� �������� ��ȯ���ش�.
            System.out.println(m.msg1);
            if(m.msg2.equals("ȸ�����Խ���"))
            {
               JOptionPane.showMessageDialog(null,"���̵� �ߺ��˴ϴ�.","", JOptionPane.WARNING_MESSAGE);
            }
            else if(m.msg2.equals("ȸ�����Լ���")) {
               JOptionPane.showMessageDialog(null, "ȸ�������̵Ǿ����ϴ�");
            }
            
            else if(m.msg2.equals("�α��μ���"))
            {
               JOptionPane.showMessageDialog(null, "�α��μ���");
               manager.setVisible(false);
            }
            else if(m.msg2.equals("��й�ȣ�ٸ�"))
            {
               JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٸ��ϴ�.","" ,JOptionPane.WARNING_MESSAGE);
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
            logger.log(WARNING,"[manager]�޽��� ��Ʈ�� ����!!");

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
   
}