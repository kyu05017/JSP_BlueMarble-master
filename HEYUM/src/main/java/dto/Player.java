package dto;

public class Player {
	
	private int p_no;
	private int p_money;
	private int p_location;
	private int p_turn;
	private int p_uisland;
	private int p_order;
	private int groom_no;
	private int ac_no;
	private String ac_nickname; 
	private String ac_profileimg;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(int p_no, int p_money, int p_location, int p_turn, int p_uisland, int p_order, int groom_no,
			int ac_no) {
		super();
		this.p_no = p_no;
		this.p_money = p_money;
		this.p_location = p_location;
		this.p_turn = p_turn;
		this.p_uisland = p_uisland;
		this.p_order = p_order;
		this.groom_no = groom_no;
		this.ac_no = ac_no;
	}


	public Player(int p_no, int p_money, int p_location, int p_turn, int p_uisland, int p_order, int groom_no,
			int ac_no, String ac_nickname, String ac_profileimg) {
		super();
		this.p_no = p_no;
		this.p_money = p_money;
		this.p_location = p_location;
		this.p_turn = p_turn;
		this.p_uisland = p_uisland;
		this.p_order = p_order;
		this.groom_no = groom_no;
		this.ac_no = ac_no;
		this.ac_nickname = ac_nickname;
		this.ac_profileimg = ac_profileimg;
	}
	
	
	
	

	public String getAc_profileimg() {
		return ac_profileimg;
	}

	public void setAc_profileimg(String ac_profileimg) {
		this.ac_profileimg = ac_profileimg;
	}

	public int getP_no() {
		return p_no;
	}



	public void setP_no(int p_no) {
		this.p_no = p_no;
	}



	public int getP_money() {
		return p_money;
	}



	public void setP_money(int p_money) {
		this.p_money = p_money;
	}



	public int getP_location() {
		return p_location;
	}



	public void setP_location(int p_location) {
		this.p_location = p_location;
	}



	public int getP_turn() {
		return p_turn;
	}



	public void setP_turn(int p_turn) {
		this.p_turn = p_turn;
	}



	public int getP_uisland() {
		return p_uisland;
	}



	public void setP_uisland(int p_uisland) {
		this.p_uisland = p_uisland;
	}



	public int getP_order() {
		return p_order;
	}



	public void setP_order(int p_order) {
		this.p_order = p_order;
	}



	public int getGroom_no() {
		return groom_no;
	}



	public void setGroom_no(int groom_no) {
		this.groom_no = groom_no;
	}



	public int getAc_no() {
		return ac_no;
	}



	public void setAc_no(int ac_no) {
		this.ac_no = ac_no;
	}



	public String getAc_nickname() {
		return ac_nickname;
	}



	public void setAc_nickname(String ac_nickname) {
		this.ac_nickname = ac_nickname;
	}

	@Override
	public String toString() {
		return "Player [p_no=" + p_no + ", p_money=" + p_money + ", p_location=" + p_location + ", p_turn=" + p_turn
				+ ", p_uisland=" + p_uisland + ", p_order=" + p_order + ", groom_no=" + groom_no + ", ac_no=" + ac_no
				+ ", ac_nickname=" + ac_nickname + ", ac_profileimg=" + ac_profileimg + "]";
	}



	
	
	
	
		
	
}
