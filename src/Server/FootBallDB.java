package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;
public class FootBallDB {

	//DB접속을 위한 정보
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/footballmanage";
	Connection conn;
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	//db접속 위함
	String dbUrl = "jdbc:mysql://localhost:3306/footballmanage";
	String dbUser = "root";
	String dbPw = "802406aa";
	
	//로거 생성
	Logger logger;
	
	//sql
	String sql;
	
	public FootBallDB() {
		logger = Logger.getLogger(this.getClass().getName());
		connectionDB();
	}
	
	//DB연결
	public void connectionDB() {
		
		try {
			// JDBC 드라이버 로드
			Class.forName(jdbcDriver);
			
			// 데이터베이스 연결
			conn = (Connection) DriverManager.getConnection(dbUrl,dbUser, dbPw);
			logger.info("[footballmanage DB 접속 성공!!]");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning("[footballmanage DB 접속 실패(FootBallDB.java/connectionDB)!!]");
		}
	}
	
	public boolean idRegistrationCustomer(Message m) {
		
		//id, passward 옮겨놓는다.
		String id = m.id;
		String pw = m.msg1;
		String msg2 = m.msg2;
		//중복 검사.
		ArrayList<String> cID;
		cID = getAllCustomerID();
		for(String check : cID) {
			if(check.equals(id)) {
				return false;
			};
		}
		System.out.println(msg2);
		//중복검사를 마친(같은 id값이 없는)메시지로 진행한다.
		//msg를 나눈다 (이름/이메일/전화번호)
		
		String[] msgArray = msg2.split("#");
		System.out.println(id+pw+msgArray[0]+msgArray[1]+msgArray[2]);
		try {
			
			sql = "INSERT INTO customer values(?, ?, ?, ?, ?)";
			//pstmt객체 생성, SQL 문장 저장
			pstmt = conn.prepareStatement(sql);
			logger.info("[sql 문제 있니?!!]");
			
			//pstmt.set
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, msgArray[0]);
			pstmt.setString(4, msgArray[1]);
			pstmt.setString(5, msgArray[2]);
			
			//SQL문 전송
			pstmt.executeUpdate();
			
			logger.info("[idRegistrationCustomer 완료!!]");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("[idRegistrationCustomer 실패!!]");	
			return false;
		}
	}
	
	//전체상품을 가져온다.
	public ArrayList<String> getAllCustomerID(){
		sql = "select cID from customer";
		
		//전체 검색 데이터를 전달하는 ArrayList
		ArrayList<String> cID = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//프로덕트에 값을 넣고 데이터 어레이리스트에 넣는다.
				cID.add(rs.getString("cID"));
				logger.info("[getAllCustomerID] 성공!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.warning("[getAllCustomerID] 문제!!");
		}
		return cID;
	}
}
