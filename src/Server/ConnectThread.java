package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
		setDdata();
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
					else if(m.type2.equals("login")) {
						
						if(fbdb.checkCustomerLogin(m.id, m.msg1) == 1) {
							outMsg.println(gson.toJson(new Message(m.id, "님이 로그인에 성공했습니다.", "로그인성공", "customer", "server")));
							logger.info("[로그인 성공]!!");

							String fname = fbdb.fieldCheck();
							outMsg.println(gson.toJson(new Message("", fname, "풋살장이름", "fname", "customer")));
							logger.info("[고객 풋살장 내역 완료!]");
//							// 보내는 부분 정보
//							ArrayList<String> items = fbdb.sendDateInfo();
//							for (String send : items) {
//								outMsg.println(gson.toJson(new Message(m.id, send, "dateinfo", "customer", "server")));
//							}
//							outMsg.println(gson.toJson(new Message(m.id, "finishSetproduct", "dfinish", "customer", "server")));
//							logger.info("[Date 내용 완료]!!");

						}
						else if(fbdb.checkCustomerLogin(m.id, m.msg1) == 0) {
							outMsg.println(gson.toJson(new Message(m.id, "님이 로그인에 실패했습니다.", "비밀번호다름", "customer", "server")));
							logger.info("[로그인 실패(패스워드)]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "님이 로그인에 실패했습니다.", "로그인실패", "customer", "server")));
							logger.info("[로그인 실패!!");
						}
					}else if(m.type2.equals("finddate")) {
						String send = fbdb.sendFieldDateInfo(m);
						outMsg.println(gson.toJson(new Message("", send, "fdate", "customer", "server")));
						logger.info("[고객 풋살장 date 내역 완료!]");
					}
					else if(m.type2.equals("findtime")) {
						String send = fbdb.sendFieldTimeDateInfo(m);
						outMsg.println(gson.toJson(new Message("", send, "ftime", "customer", "server")));
						logger.info("[고객 풋살장 time 내역 완료!]");
					}
					else if(m.type2.equals("reserve")) {
						if(fbdb.reserveCustomer(m)) {
							outMsg.println(gson.toJson(new Message("m.id", "", "reserve", "customer", "server")));
							logger.info("[고객 풋살장 reserve 내역 완료!]");
						}
						else {
							outMsg.println(gson.toJson(new Message("m.id", "", "notreserve", "customer", "server")));
							logger.info("[고객 풋살장 reserve 내역 실패!!]");
						}
						
					}
					//현중이가 좋아하는 스테이트
					else if(m.type2.equals("findstate")) {
						System.out.println(m);
						ArrayList<String>reserve = fbdb.customerR(m);
						for(String send : reserve) {
							outMsg.println(gson.toJson(new Message("", send, "state", "customer", "server")));
						}
						logger.info("[고객 풋살장 state 내역 완료!]");
					}
				}
				else if (m.type1.equals("administer")) {
					//msgSendAll(gson.toJson(new Message(m.id, "", "님이 로그인 했습니다.", "server")));
					logger.info("[administer  들어옴]!!");
					if (m.type2.equals("register")) {
						//데이터 베이스에있는 거랑 확인해야함.
						//데이터베이스에 아이디랑 패스워드 전해줘야한다.
						if(fbdb.idRegistrationAdminister(m)) {
							outMsg.println(gson.toJson(new Message(m.id, "님이 회원가입에 성공했습니다.", "회원가입성공", "administer", "server")));
							logger.info("[db등록 성공]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "님이 회원가입에 실패했습니다.", "회원가입실패", "administer", "server")));
							logger.info("[db등록 실패]!!");
						}
						
						// 해당 클라이언트 스레드 종료 status false
					}
					else if(m.type2.equals("field")) {
						String fname = fbdb.fieldCheck();
						outMsg.println(gson.toJson(new Message("", fname, "풋살장이름", "fname", "administer")));
						logger.info("[풋살장 내역 완료!]");
					}
					else if(m.type2.equals("login")) {
						
						if(fbdb.checkAdministerLogin(m.id, m.msg1) == 1) {
							outMsg.println(gson.toJson(new Message(m.id, "님이 로그인에 성공했습니다.", "로그인성공", "administer", "server")));
							logger.info("[로그인 성공]!!");
						}
						else if(fbdb.checkAdministerLogin(m.id, m.msg1) == 0) {
							outMsg.println(gson.toJson(new Message(m.id, "님이 로그인에 실패했습니다.", "비밀번호다름", "administer", "server")));
							logger.info("[로그인 실패(패스워드)]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "님이 로그인에 실패했습니다.", "로그인실패", "administer", "server")));
							logger.info("[로그인 실패]!!");
						}
					}
					else if(m.type2.equals("setproduct")) {
						ArrayList<String> items = fbdb.getProduct(m);
						
						for(String send : items) {
							outMsg.println(gson.toJson(new Message(m.id, send, "productinfo", "administer", "server")));
							logger.info("[프로덕트 내용 전송중..]!!");
						}
						outMsg.println(gson.toJson(new Message(m.id, "finishSetproduct", "finish", "administer", "server")));
						logger.info("[프로덕트 내용 완료]!!");
					}
					//수정하기
					else if(m.type2.equals("changeproduct")) {
						//db에서 수정
						if(fbdb.sendProductChange(m)) {
							outMsg.println(gson.toJson(new Message(m.id, "", "changed","administer","server")));
							logger.info("[수정 완료]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "", "notchanged","administer","server")));
							logger.info("[수정 실패]!!");
						}
					}
					//물품 추가
					else if(m.type2.equals("addproduct")) {
						//db에서 수정
						if(fbdb.addProduct(m)) {
							outMsg.println(gson.toJson(new Message(m.id, "", "addproduct","administer","server")));
							logger.info("[추가 완료]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "", "notaddproduct","administer","server")));
							logger.info("[추가 실패]!!");
						}
					}
					else if(m.type2.equals("deleteproduct")) {
						if(fbdb.deleteProduct(m)) {
							outMsg.println(gson.toJson(new Message(m.id, "", "deleteproduct","administer","server")));
							logger.info("[삭제 완료]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "", "notdeleteproduct","administer","server")));
							logger.info("[삭제 실패]!!");
						}
					}
					//예약현황
					else if(m.type2.equals("checkreservation")) {
						ArrayList<String> send = fbdb.administerR(m); 
						for(String temp : send) {
							outMsg.println(gson.toJson(new Message(m.id, temp, "checkreservation","administer","server")));
						}
						logger.info("[예약현황 완료]!!");
					}
					//휴무일 관리.
					else if(m.type2.equals("addcozyday")) {
						if(fbdb.setDayOfWeek(m)) {
							outMsg.println(gson.toJson(new Message(m.id, "", "cozyday","administer","server")));
							logger.info("[cozyday 완료]!!");
						}
						else {
							outMsg.println(gson.toJson(new Message(m.id, "", "notcozyday","administer","server")));
							logger.info("[cozyday 실패]!!");
						}
					}
					else if(m.type2.equals("checkcozyday")) {
						ArrayList<String> send = fbdb.checkcozyday(m); 
						for(String temp : send) {
							outMsg.println(gson.toJson(new Message(m.id, temp, "checkcozyday","administer","server")));
						}
						logger.info("[checkcozyday 완료]!!");
					}
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

	//날짜 자동추가
	void setDdata() {
		//누가 들어오면 확인한다.
		// 오늘날짜 확인.
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date currentTime = new Date();
		// 오늘날짜
		String mTime = mSimpleDateFormat.format(currentTime);
		// 날짜 더해주기
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentTime);
		cal.add(Calendar.DATE, 6); // 날짜 더하기
		String addTime = mSimpleDateFormat.format(cal.getTime());
		// System.out.println("더한날짜 확인 : " + addTime);

		if (fbdb.getWeekDdate(addTime)) {
			//System.out.println("이번주거 있다.");
			//fbdb.deleteDateWeek(mTime);
		} else {
			//System.out.println("이번주마지막거 없다 추가해라");
			fbdb.deleteDateWeek(mTime);
			fbdb.addDate(addTime);
		}
	}
}
