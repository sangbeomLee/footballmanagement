package Server;

public class Message {
	public String id; //login ID
	public String msg1;
	public String msg2;
	public String type1;
	public String type2;
	
	public Message() {}
	
	public Message(String id, String msg1, String msg2, String type1, String type2) {
		// TODO Auto-generated constructor stub
		this.id 	= id;
		this.msg1 	= msg1;
		this.msg2 	= msg2;
		this.type1 	= type1;
		this.type2  = type2;
	}
}

