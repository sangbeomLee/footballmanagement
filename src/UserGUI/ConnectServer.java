package UserGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;
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


public class ConnectServer implements Runnable{
	public User user;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	String outm;
	Socket socket = null;
	Message m;
	Logger logger;
	Thread thread;
	Gson gson;
	boolean status;

	public void connectServer(User user) {
		this.user = user;
		logger = Logger.getLogger(this.getClass().getName());

		try {
			socket = new Socket("172.16.30.242",8888);
			logger.log(INFO,"[Client]Server ���� ����!!");
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
				if(m.msg2.equals("ȸ�����Խ���"))
				{
					JOptionPane.showMessageDialog(null,"���̵��� �ߺ��˴ϴ�.","", JOptionPane.WARNING_MESSAGE);
				}
				else if(m.msg2.equals("ȸ�����Լ���")) {
					JOptionPane.showMessageDialog(null, "ȸ�������̵Ǿ����ϴ�");
				}

				else if(m.msg2.equals("�α��μ���"))
				{
					JOptionPane.showMessageDialog(null, "�α��μ���");
				}
				else if(m.msg2.equals("���й�ȣ�ٸ�"))
				{
					JOptionPane.showMessageDialog(null, "���й�ȣ�� �ٸ��ϴ�.","" ,JOptionPane.WARNING_MESSAGE);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.log(WARNING,"[User]�޽��� ��Ʈ�� ����!!");

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
