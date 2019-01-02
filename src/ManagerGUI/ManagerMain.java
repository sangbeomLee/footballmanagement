package ManagerGUI;


public class ManagerMain {

	public static void main(String[] args) {
		
		
		Manager frame = new Manager();
		
		ManagerController app =new ManagerController(frame);
		app.appMain();

		
		//ConnectManagerServer connectS = new ConnectManagerServer();
		//connectS.connectServer();
		//new Managerpanel();	
		
	}

}
