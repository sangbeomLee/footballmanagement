package Server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FootBallManageServer {

	
	private ServerSocket ss = null;
//	private ArrayList<Socket> socketList;
//	private int sidx;
	FootBallDB fbdb;
	//연결된 클라이언트 스레드 관리 AL
	ArrayList<ConnectThread> connectThreads = new ArrayList<ConnectThread>();
	
	//로거 객체
	Logger logger;
	
//	public FootBallManageServer() {
//		for(int i=0;i<100;i++) {
//			socketList.add(new Socket());
//		}
//		sidx = 0;
//	}
	public void start() {
		
//		//내 아이피 확인
//		try {
//			InetAddress ip = InetAddress.getLocalHost();
//			System.out.println("Host Name = [" + ip.getHostName() + "]");
//			System.out.println("Host Address = [" + ip.getHostAddress() + "]");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		//db연결
		dbConnect();
	
		//로거 객체 생성
		logger = Logger.getLogger(this.getClass().getName());
		
		try {
			//서버 소켓 생성
			ss = new ServerSocket(8888);
			logger.info("FootBallManageServer Start!!");
			
			//무한 루프 돌면서 클라이언트 연결 기다린다.
			while(true) {
				Socket pSocket = new Socket();
				pSocket = ss.accept();
				//연결된 클라이언트에 대해 스레드 클래스 생성
				ConnectThread connect = new ConnectThread();
				//클라이언트 리스트 추가
				connectThreads.add(connect);
				connect.setSocekt(pSocket);
				connect.setDB(fbdb);
				//스레드 시작
				connect.start();
			}
		} catch(Exception e) {
			logger.info("[FootBallManageServer]start(); Exception 발생!!");
			e.printStackTrace();
		}
	}
	
	public void dbConnect() {
		fbdb = new FootBallDB();
		
	}
	
	public static void main(String[] args) {
		FootBallManageServer fbms = new FootBallManageServer();
		fbms.start();
	}

	
}
