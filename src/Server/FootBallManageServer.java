package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
		
//		//내 아이피 확인
//		try {
//			InetAddress ip = InetAddress.getLocalHost();
//			System.out.println("Host Name = [" + ip.getHostName() + "]");
//			System.out.println("Host Address = [" + ip.getHostAddress() + "]");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
		//오늘날짜 확인.
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format ( currentTime );
		System.out.println ("오늘날짜 확인" + mTime );
		//날짜 더해주기
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentTime);
		cal.add(Calendar.DATE, 6); // 날짜 더하기
		String addTime = mSimpleDateFormat.format(cal.getTime());
		System.out.println("더한날짜 확인 : " + addTime);
		//fbdb.setDate(1, "2019/01/02", "time1", 1, null);
		//db연결
		dbConnect();
		
		//System.out.println(fbdb.getMinimumDateID());
		//System.out.println(fbdb.getMaxmumDateID());
		fbdb.deleteDateWeek(mTime);
		if(fbdb.getWeekDdate(addTime)) {
			System.out.println("이번주거 있다.");
		}
		else {
			System.out.println("이번주마지막거 없다 추가해라");
			//fbdb.addDate(addTime);
		}
		
		

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
