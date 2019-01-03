package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FootBallDB {

	// DB접속을 위한 정보
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/footballmanage";
	Connection conn;

	PreparedStatement pstmt;
	ResultSet rs;

	// db접속 위함
	String dbUrl = "jdbc:mysql://localhost:3306/footballmanage";
	String dbUser = "root";
	String dbPw = "802406aa";

	// 로거 생성
	Logger logger;

	// sql
	String sql;

	public FootBallDB() {
		logger = Logger.getLogger(this.getClass().getName());
		connectionDB();
	}

	// DB연결
	public void connectionDB() {

		try {
			// JDBC 드라이버 로드
			Class.forName(jdbcDriver);

			// 데이터베이스 연결
			conn = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPw);
			logger.info("[footballmanage DB 접속 성공!!]");

		} catch (Exception e) {
			e.printStackTrace();
			logger.warning("[footballmanage DB 접속 실패(FootBallDB.java/connectionDB)!!]");
		}
	}

	// 풋살장 내역조회
	public String fieldCheck() {

		sql = "select fName from footballfield";

		String fname = "";
		// 전체 검색 데이터를 전달하는 ArrayList
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				if (fname.equals("")) {
					fname = fname + rs.getString("fName");
				} else {
					fname = fname + "#" + rs.getString("fName");
				}

			}
			logger.info("[fieldCheck] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[fieldCheck] 문제!!");
		}
		return fname;
	}

	// id 등록
	public boolean idRegistrationCustomer(Message m) {

		// id, passward 옮겨놓는다.
		String id = m.id;
		String pw = m.msg1;
		String msg2 = m.msg2;
		// 중복 검사.
		ArrayList<String> cID;
		cID = getAllCustomerID();
		for (String check : cID) {
			if (check.equals(id)) {
				return false;
			}
			;
		}
		// 중복검사를 마친(같은 id값이 없는)메시지로 진행한다.
		// msg를 나눈다 (이름/이메일/전화번호)

		String[] msgArray = msg2.split("#");
		
		try {

			sql = "INSERT INTO customer values(?, ?, ?, ?, ?)";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);
			logger.info("[sql 문제 있니?!!]");

			// pstmt.set
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, msgArray[0]);
			pstmt.setString(4, msgArray[1]);
			pstmt.setString(5, msgArray[2]);

			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[idRegistrationCustomer 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[idRegistrationCustomer 실패!!]");
			return false;
		}
	}

	public boolean idRegistrationAdminister(Message m) {

		// id, passward 옮겨놓는다.
		String id = m.id;
		String pw = m.msg1;
		String msg2 = m.msg2;
		// 중복 검사.
		ArrayList<String> aID;
		aID = getAllAdministerID();
		for (String check : aID) {
			if (check.equals(id)) {
				return false;
			}
			;
		}
		
		// 중복검사를 마친(같은 id값이 없는)메시지로 진행한다.
		// msg를 나눈다 (이름/이메일/전화번호)

		String[] msgArray = msg2.split("#");
		int fID = changeF(msgArray[3]);
		// int fID = Integer.parseInt(msgArray[3])''
		
		try {
			
			sql = "INSERT INTO administer values(?, ?, ?, ?, ?, ?)";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);
			logger.info("[sql 문제 있니?!!]");

			// pstmt.set
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, msgArray[0]);
			pstmt.setString(4, msgArray[1]);
			pstmt.setString(5, msgArray[2]);
			pstmt.setInt(6, fID);
			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[idRegistrationAdminister 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[idRegistrationAdminister 실패!!]");
			return false;
		}
	}

	// 풋살장 이름을 풋살장 id로 바꿔주는 함수
	public int changeF(String fName) {
		sql = "select fID,fName from footballfield";

		int fID = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.

				if (fName.equals(rs.getString("fName"))) {
					fID = rs.getInt("fID");
					return fID;
				}
			}
			logger.info("[footballfieldchangeF] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[footballfieldchangeF] 문제!!");
		}
		return fID;
	}

	// administer 의 fID찾는 함수
	public int findFID(String cid) {
		sql = "select fID,aID from administer";

		int fID = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.

				if (cid.equals(rs.getString("aID"))) {
					fID = rs.getInt("fID");
					return fID;
				}
			}
			logger.info("[findFID] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[findFID] 문제!!");
		}
		return fID;
	}

	// 전체상품을 가져온다.
	public ArrayList<String> getAllCustomerID() {
		sql = "select cID from customer";

		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> cID = new ArrayList<String>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				cID.add(rs.getString("cID"));
			}
			logger.info("[getAllCustomerID] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[getAllCustomerID] 문제!!");
		}
		return cID;
	}

	// administer 아이디를 가져온다.
	public ArrayList<String> getAllAdministerID() {
		sql = "select aID from administer";

		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> aID = new ArrayList<String>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				aID.add(rs.getString("aID"));
			}
			logger.info("[getAllAdministerID] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[getAllAdministerID] 문제!!");
		}
		return aID;
	}

	// login check
	public int checkCustomerLogin(String id, String pw) {
		sql = "select cID,cPassword from customer";
		String cID;
		String cPassword;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cID = rs.getString("cID");
				cPassword = rs.getString("cPassword");
				if (cID.equals(id)) {
					if (cPassword.equals(pw)) {
						logger.info("[checkID login] 성공!!");
						return 1;
					} else {
						logger.info("[checkID login] 비밀번호오류!!!");
						return 0;
					}
				}
			}
			logger.info("[checkID login] 실패!!");
			return -1;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[checkID login] 실패!!");
		}
		logger.info("[checkID login] 실패!!");
		return -1;
	}

	public int checkAdministerLogin(String id, String pw) {
		sql = "select aID,aPassword from administer";
		String aID;
		String aPassword;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aID = rs.getString("aID");
				aPassword = rs.getString("aPassword");
				if (aID.equals(id)) {
					if (aPassword.equals(pw)) {
						logger.info("[checkID login] 성공!!");
						return 1;
					} else {
						logger.info("[checkID login] 비밀번호오류!!!");
						return 0;
					}
				}
			}
			logger.info("[checkID login] 실패!!");
			return -1;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[checkID login] 실패!!");
		}
		logger.info("[checkID login] 실패!!");
		return -1;
	}

	public ArrayList<String> getProduct(Message m) {
		// fid 바꾼다.1더해준다.
		int fID = findFID(m.id);

		sql = "select pName,pQuntity,pRepair from product where fID = " + fID;
		
		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> product = new ArrayList<String>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				String temp = "";
				temp = temp + rs.getString("pName");
				temp = temp + "#" + Integer.toString(rs.getInt("pQuntity"));
				temp = temp + "#" + Integer.toString(rs.getInt("pRepair"));
				
				product.add(temp);
			}
			logger.info("[getProduct] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[getProduct] 문제!!");
		}
		return product;

	}

	// datemanage 데이터 베이스에 저장하기위한 함수
	public void setDate(int id, String date, String time, int fID, String week) {
		try {
			sql = "INSERT INTO datemanage values(?, ?, ?, ?, ?)";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);

			// pstmt.set
			pstmt.setInt(1, id);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			pstmt.setInt(4, fID);
			pstmt.setString(5, week);

			// SQL문 전송
			pstmt.executeUpdate();

			// logger.info("[setDate 완료!!]");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[setDate 실패!!]");
		}
	}

	// 날짜를 알기위한 함수
	public ArrayList<String> getDate() {
		// 년-월-일 순으로 나오게 했다.
		sql = "select date_format(dDate,'%Y-%m-%d') as date from datemanage";
		ArrayList<String> date = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 안에있는 dDate 를 넣는다.
				date.add(rs.getString("date"));
			}

			logger.info("[getDate] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[getDate] 문제!!");
		}
		return date;
	}

	// ID값이 1이 있는지를 알기 위한 함수.
	public int getMinimumDateID() {
		sql = "select dID from datemanage ORDER BY dID asc LIMIT 1;";

		int dID;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			dID = rs.getInt("dID");

			logger.info("[getMinimumDateID] 성공!!");
			return dID;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[getMinimumDateID] 실패!!");
		}
		logger.info("[getMinimumDateID] 실패!!");
		return -1;
	}

	// 마지막 dateID값을 알기 위한 함수
	public int getMaxmumDateID() {
		sql = "select dID from datemanage ORDER BY dID DESC LIMIT 1;";

		int dID;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			dID = rs.getInt("dID");

			logger.info("[getMaxmumDateID] 성공!!");
			return dID;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[getMaxmumDateID] 실패!!");
		}
		logger.info("[getMinimumDateID] 실패!!");
		return -1;
	}

	// 오늘 날짜면 DB data 지우는 함수.
	public void deleteDateWeek(String today) {
		String day = today;
		
		try {
			// sql
			sql = "DELETE FROM datemanage where dID>0 and dDate <" + "'" + day + "'";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);
			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[deleteDateWeek 완료!!]");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[deleteDateWeek 실패!!]");
		}
	}

	// 날짜가 지날때마다 자동으로 넣어주는 함수.
	public void addDate(String date) {
		int id = 0;
		// int minimum = getMinimumDateID();

		// 시간 넣기.
		ArrayList<String> times = new ArrayList<String>();
		times.add("10시-12시");
		times.add("12시-14시");
		times.add("14시-16시");
		times.add("16시-18시");
		times.add("18시-20시");

		// id값 자동생성!!
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 5; j++) {
				setDate(id, date, times.get(j), i, "");
			}
		}
		logger.info("[addDate 성공!!]");

	}

	// 1주일 후 날짜가 있는지 확인.
	public boolean getWeekDdate(String after) {

		sql = "select dDate from datemanage where dDate>=" + "'" + after + "'";
		ArrayList<String> date = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 안에있는 dDate 를 넣는다.
				date.add(rs.getString("dDate"));
			}

			logger.warning("[getDdate] 실행!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[getDdate] 문제!!");
		}

		if (date.size() > 1) {
			
			return true;
		} else {
			return false;
		}
	}

	// dateinfo를 보낸다.
	public ArrayList<String> sendDateInfo() {
		// fid 바꾼다.1더해준다.

		sql = "select DISTINCT dm.fID fid,dm.dDate dDate,dm.dTime from datemanage dm inner join reservation on dm.dID <> reservation.dID";
		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> dInfo = new ArrayList<String>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 첫번째는 디폴트 값이 들어가있어서 빼줬다.
			rs.next();
			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				String temp = "";
				temp = temp + Integer.toString(rs.getInt("fid"));
				temp = temp + "#" + rs.getString("dDate");
				temp = temp + "#" + rs.getString("dTime");
				dInfo.add(temp);
			}
			logger.info("[sendDateInfo] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[sendDateInfo] 문제!!");
		}
		return dInfo;

	}
	//date info중 날짜 보낸
	public String sendFieldDateInfo(Message m) {
		
		int fID = changeF(m.msg2);
        String dDate = "";
       
        try {
               sql = "select DISTINCT date_format(dm.dDate,'%Y-%m-%d') as dDate from datemanage dm where dm.fID = ? and dm.dID <> 1 and dm.dDayOfWeek = 0";
               pstmt = conn.prepareStatement(sql);
  
               // pstmt.set
               pstmt.setInt(1, fID);
               
               // SQL문 전송
               rs = pstmt.executeQuery();
               rs.next();
               dDate = dDate + rs.getString("dDate");
               while (rs.next()) {
                      // date#붙이기.
                      dDate = dDate + "#" + rs.getString("dDate");
               }
               logger.info("[sendFieldDateInfo] 성공!!");
        } catch (Exception e) {
               // TODO: handle exception
               logger.warning("[sendFieldDateInfo] 문제!!");
        }
        return dDate;

	}
	//date info중 시간 보낸다.
	public String sendFieldTimeDateInfo(Message m) {

		String choicedate = m.msg1;
		int fID = changeF(m.msg2);
		String dDate = "";
		
		try {
			sql = "select dm.dTime from datemanage dm where dm.dID <> 1 and dm.fID = ? and dm.dDate = ? and dm.dID not in (select dID from reservation)";
			
			pstmt = conn.prepareStatement(sql);
			// pstmt.set
			pstmt.setInt(1, fID);
			pstmt.setString(2, choicedate);
			
			
			// SQL문 전송
			rs = pstmt.executeQuery();
			rs.next();
			dDate = dDate + rs.getString("dTime");
			
			while (rs.next()) {
				// date#붙이기.
				dDate = dDate + "#" + rs.getString("dTime");
			}
			logger.info("[sendFieldTimeDateInfo] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.warning("[sendFieldTimeDateInfo] 문제!!");
		}
		return dDate;

	}

	//administer 물품 수정
	public synchronized boolean sendProductChange(Message m) {
		/// UPDATE tablename SET filedA='456' WHERE test='123'
		//이사람의 fid를 알아야한다. 함수로 만들자
		
		String array[] = m.msg1.split("#");
		String pName = array[0];
		int pQ	 = Integer.parseInt(array[1]);
		int pR	 = Integer.parseInt(array[2]);
		int fID 	 = findFID(m.id);
		try {
			sql = "update product set pQuntity = ?, pRepair = ? where pName = ? and fID = ?";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);

			// pstmt.set
			pstmt.setInt(1, pQ);
			pstmt.setInt(2, pR);
			pstmt.setString(3, pName);
			pstmt.setInt(4, fID);
			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[sendProductChange 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[sendProductChange 실패!!]");
			return false;
		}
	}
	//물품 추가
	public synchronized boolean addProduct(Message m) {
		// id, passward 옮겨놓는다.
		
		String id = m.id;
		String msg1 = m.msg1;
		String array[] = msg1.split("#");
		String pName = array[0];
		int pQ	 = Integer.parseInt(array[1]);
		int pR	 = Integer.parseInt(array[2]);
		int fID 	 = findFID(m.id);
		
		try {
			sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?);";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);

			// pstmt.set
			pstmt.setInt(1, 0);
			pstmt.setInt(2, fID);
			pstmt.setInt(3, pQ);
			pstmt.setInt(4, pR);
			pstmt.setString(5, pName);
			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[addProduct 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[addProduct 실패!!]");
			return false;
		}
	}
	//물품 지우기
	public synchronized boolean deleteProduct(Message m) {	
		//정보
		String pName = m.msg1;
		int fID 	 = findFID(m.id);
	
		try {
			// sql
			sql = "delete from product where pID>0 and fID = ? and pName = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fID);
			pstmt.setString(2, pName);

			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[deleteProduct 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[deleteProduct 실패!!]");
			return false;
		}
	}

	//관리자 예약상황
	public ArrayList<String> administerR(Message m) {
	
		int fID = findFID(m.id);

		sql = "select date_format(dm.dDate,'%Y-%m-%d') as dDate,dm.dTime as dTime,rv.cID as cID from datemanage dm,reservation rv where rv.dID = dm.dID and dm.dID <> 1 and rv.fid = " + fID + " ORDER BY dDate";
		
		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> reserveinfo = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				String temp = "";
				temp = temp + rs.getString("dDate");
				temp = temp + "#" + rs.getString("dTime");
				temp = temp + "#" + rs.getString("cID");
				reserveinfo.add(temp);
			}
			
			logger.info("[administerR] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[administerR] 문제!!");
		}
		return reserveinfo;

	}
	//유저 예약 완료!
	public synchronized boolean reserveCustomer(Message m) {
		// id, passward 옮겨놓는다.
		String id = m.id;
		String msg1 = m.msg1;
		String msg2 = m.msg2;
		String array[] = msg2.split("#");
		
		int fID  = changeF(array[0]);
		String Date = array[1];
		String Time = array[2];
		int dID = findDID(Date,Time,fID);
		//고사이 누가 예약한것
		
		if(!compareDID(dID)) {
			return false;
		}
		try {
			sql = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?);";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);

			// pstmt.set
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			pstmt.setInt(3, fID);
			pstmt.setInt(4, dID);
			pstmt.setString(5, msg1);
			// SQL문 전송
			
			pstmt.executeUpdate();

			logger.info("[reserveCustomer 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[reserveCustomer 실패!!]");
			return false;
		}
	}
	//dID찾는다.
	public int findDID(String dDate, String dTime, int fID) {
		int dID = -1;
		try {
			sql = "select dID from datemanage where dDate = ? and dTime = ? and fID = ? ";
			pstmt = conn.prepareStatement(sql);

			// pstmt.set
			pstmt.setString(1, dDate);
			pstmt.setString(2, dTime);
			pstmt.setInt(3, fID);

			// SQL문 전송
			rs = pstmt.executeQuery();
			rs.next();
			dID = rs.getInt("dID");
			logger.info("[findDID] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[findDID] 문제!!");
		}
		return dID;

	}

	//dID reservation 과 비교한다.
	public boolean compareDID(int dID) {
		int check = 0;
		try {
			sql = "select dID from reservation where dID = ?";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);

			// pstmt.set
			pstmt.setInt(1, dID);

			// SQL문 전송
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				logger.info("[compareDID 실패[중복]!!]");
				return false;
			}
			else {
				logger.info("[compareDID 완료!!]");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[compareDID 실패!!]");
			return false;
		}
	}
	//dDayOfWeek 추가하기.휴무일
	public boolean setDayOfWeek(Message m) {
		
		String dDate = m.msg1;
	
		int fID 	 = findFID(m.id);
		try {
			sql = "update datemanage set dDayOfWeek = 1 where dDate = ? and fID = ?";
			// pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);
		
			// pstmt.set
			pstmt.setString(1, dDate);
			pstmt.setInt(2, fID);
			// SQL문 전송
			pstmt.executeUpdate();

			logger.info("[setDayOfWeek 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[setDayOfWeek 실패!!]");
			return false;
		}
	}
	//휴일 뿌려주기
	public ArrayList<String> checkcozyday(Message m){
		int fID = findFID(m.id);

		sql = "select distinct date_format(dm.dDate,'%Y-%m-%d') as dDate from datemanage dm where dm.dID <> 1 and dm.fid = ? and dm.dDayOfWeek = 1";
		
		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> cozyday = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				String temp = rs.getString("dDate");
				cozyday.add(temp);
			}
			logger.info("[checkcozyday] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[checkcozyday] 문제!!");
		}
		return cozyday;

	}
	//예약 상황 보내주기
	public ArrayList<String> customerR(Message m) {
		
		String cID = m.id;

		sql = "select distinct rv.fID, date_format(dm.dDate,'%Y-%m-%d') dDate, dm.dTime dTime, rv.rHelper from reservation rv,datemanage dm where rv.cID = ? and rv.dID = dm.dID order by dDate";
		
		// 전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> reserveinfo = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cID);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				String temp = "";
				temp = temp + rs.getInt("fID");
				System.out.println("1" + temp);
				temp = temp + "#" + rs.getString("dDate");
				System.out.println("2" + temp);
				temp = temp + "#" + rs.getString("dTime");
				System.out.println("3" + temp);
				temp = temp + "#" + Integer.toString(rs.getInt("rHelper"));
				System.out.println("####2222#");
				reserveinfo.add(temp);
			}
			
			logger.info("[customerR] 성공!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[customerR] 문제!!");
		}
		return reserveinfo;

	}
	//풋살장 id를 이름으로 바꿔주는 함수
	public String changeFname(int fID) {
		sql = "select fID,fName from footballfield";

		String fName = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.

				if (fID == (rs.getInt("fID"))) {
					fName = rs.getString("fName");
					logger.info("[changeFname] 성공!!");
					return fName;
				}
			}
			logger.info("[changeFname] 실패!!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[changeFname] 문제!!");
		}
		return fName;
	}
}
