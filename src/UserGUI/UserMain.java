package UserGUI;

public class UserMain {

	public UserMain() {}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test
		User frame = new User();
		UserController app =new UserController(frame);
		app.appMain();
	
	}

}
