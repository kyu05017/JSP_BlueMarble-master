package dto;

public class Gameroom {

	private int groom_no;
	private String groom_name;
	private String groom_pw;
	
	public Gameroom() {
		// TODO Auto-generated constructor stub
	}

	public Gameroom(int groom_no, String groom_name, String groom_pw) {
		super();
		this.groom_no = groom_no;
		this.groom_name = groom_name;
		this.groom_pw = groom_pw;
	}

	@Override
	public String toString() {
		return "Gameroom [groom_no=" + groom_no + ", groom_name=" + groom_name + ", groom_pw=" + groom_pw + "]";
	}
	
	
	
	
}
