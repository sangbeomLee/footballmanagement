package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class ConnectThread extends Thread {
	
	//db 생성
	FootBallDB fbdb;
	private Socket s 		= null;
	
	//logger생성
	Logger logger;

	// 수신 메시지 및 파싱 메시지 처리
	String msg;

	// 메시지 객체 생성
	Message m = new Message();

	// JSON 파서 초기화
	Gson gson = new Gson();

	// 입출력 스트림
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	boolean status = true;

	public ConnectThread() {
		logger = Logger.getLogger(this.getClass().getName());
		logger.info(this.getName() + "생성됨!!");
	}

	public void run() {
		status = true;
		//logger.info("[스레드 들어옴]!!");
		try {
			//logger.info("[try스레드 들어옴]!!");
			inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
			outMsg = new PrintWriter(s.getOutputStream(), true);

			while (status) {
				logger.info("[while스레드 들어옴]!!");
				// 수신된 메시지를 msg 변수에 저장
				msg = inMsg.readLine();
				//logger.info("[read 들어옴]!!");
				m = gson.fromJson(msg, Message.class);

				// 파싱된 문자열 배열의 두 번째 요소값
				// 로그아웃 메시지
				if (m.type1.equals("customer")) {
					logger.info("[커스토머  들어옴]!!");
					if (m.type2.equals("register")) {
						//데이터 베이스에있는 거랑 확인해야함.
						//데이터베이스에 아이디랑 패스워드 전해줘야한다.
						if(fbdb.idRegistrationCustomer(m)) {
							outMsg.println(gson.toJson(new Message(m.id, "님이 회원가입에 성공했습니다.", "회원가입성공", "customer", "server")));
							logger.info("[db등록 성공]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "님이 회원가입에 실패했습니다.", "회원가입실패", "customer", "server")));
							logger.info("[db등록 실패]!!");
						}
						
						// 해당 클라이언트 스레드 종료 status false
					}
				}
				else if (m.type1.equals("administer")) {
					//msgSendAll(gson.toJson(new Message(m.id, "", "님이 로그인 했습니다.", "server")));
					
				}
				else{
					//잘못된 접근입니다 띄우기
					//status = false;
					//logger.info("엘스문 들어옴");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			//logger.info("[e 들어옴]!!");
		}
		// 루프를 벗어나면 클라이언트 연결 종료
		this.interrupt();
		logger.info(this.getName() + "종료됨!!");
	}

	void setSocekt(Socket s) {
		this.s	= s;
	}
	void setDB(FootBallDB db) {
		this.fbdb = db;
	}

}
