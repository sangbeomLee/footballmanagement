package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FootBallManageServer {

	
	private ServerSocket ss = null;
	private Socket s 		= null;
	FootBallDB fbdb;
	//연결된 클라이언트 스레드 관리 AL
	ArrayList<ConnectThread> connectThreads = new ArrayList<ConnectThread>();
	
	//로거 객체
	Logger logger;
	
	public void start() {
		
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
				s = ss.accept();
				//연결된 클라이언트에 대해 스레드 클래스 생성
				ConnectThread connect = new ConnectThread();
				//클라이언트 리스트 추가
				connectThreads.add(connect);
				connect.setSocekt(s);
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
