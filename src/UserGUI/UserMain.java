package UserGUI;

import java.util.Date;

public class UserMain {

	public UserMain() {}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test

		User frame = new User();
		State state = new State();
		Userreserve  reserve= new Userreserve(state);
		UserController app =new UserController(frame,reserve);
		app.appMain();
		
	
	}

}
