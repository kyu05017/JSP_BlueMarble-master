package dto;

public class Account {
	

	private int ac_no;
	private String ac_id;
	private String ac_pw;
	private String ac_email;
	private String ac_name;
	private String ac_nickname;
	private String ac_profileimg;
	private String ac_phone;
	private int ac_type;
	private String ac_since;
	private String ac_pw_sec;
	private int win;
	private int lose;

	public Account() {}
	

	public Account(int ac_no, String ac_nickname) {
		this.ac_no = ac_no;
		this.ac_nickname = ac_nickname;
	}



	public Account(int ac_no, String ac_id, String ac_pw, String ac_email, String ac_name, String ac_nickname,
			String ac_profileimg, String ac_phone, int ac_type, String ac_since, String ac_pw_sec, int win, int lose) {
		super();
		this.ac_no = ac_no;
		this.ac_id = ac_id;
		this.ac_pw = ac_pw;
		this.ac_email = ac_email;
		this.ac_name = ac_name;
		this.ac_nickname = ac_nickname;
		this.ac_profileimg = ac_profileimg;
		this.ac_phone = ac_phone;
		this.ac_type = ac_type;
		this.ac_since = ac_since;
		this.ac_pw_sec = ac_pw_sec;
		this.win = win;
		this.lose = lose;
	}


	
	
	public Account(int ac_no, String ac_id, String ac_nickname, int win, int lose) {
		super();
		this.ac_no = ac_no;
		this.ac_id = ac_id;
		this.ac_nickname = ac_nickname;
		this.win = win;
		this.lose = lose;
	}


	public int getWin() {
		return win;
	}


	public void setWin(int win) {
		this.win = win;
	}


	public int getLose() {
		return lose;
	}


	public void setLose(int lose) {
		this.lose = lose;
	}


	public String getAc_since() {
		return ac_since;
	}


	public void setAc_since(String ac_since) {
		this.ac_since = ac_since;
	}

	public String getAc_pw_sec() {
		return ac_pw_sec;
	}

	public void setAc_pw_sec(String ac_pw_sec) {
		this.ac_pw_sec = ac_pw_sec;
	}

	public String getAc_name() {
		return ac_name;
	}

	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}

	public int getAc_no() {
		return ac_no;
	}

	public void setAc_no(int ac_no) {
		this.ac_no = ac_no;
	}

	public String getAc_id() {
		return ac_id;
	}

	public void setAc_id(String ac_id) {
		this.ac_id = ac_id;
	}

	public String getAc_pw() {
		return ac_pw;
	}

	public void setAc_pw(String ac_pw) {
		this.ac_pw = ac_pw;
	}

	public String getAc_email() {
		return ac_email;
	}

	public void setAc_email(String ac_email) {
		this.ac_email = ac_email;
	}

	public String getAc_nickname() {
		return ac_nickname;
	}

	public void setAc_nickname(String ac_nickname) {
		this.ac_nickname = ac_nickname;
	}

	public String getAc_profileimg() {
		return ac_profileimg;
	}

	public void setAc_profileimg(String ac_profileimg) {
		this.ac_profileimg = ac_profileimg;
	}

	public String getAc_phone() {
		return ac_phone;
	}

	public void setAc_phone(String ac_phone) {
		this.ac_phone = ac_phone;
	}

	public int getAc_type() {
		return ac_type;
	}

	public void setAc_type(int ac_type) {
		this.ac_type = ac_type;
	}


	@Override
	public String toString() {
		return "Account [ac_no=" + ac_no + ", ac_id=" + ac_id + ", ac_pw=" + ac_pw + ", ac_email=" + ac_email
				+ ", ac_name=" + ac_name + ", ac_nickname=" + ac_nickname + ", ac_profileimg=" + ac_profileimg
				+ ", ac_phone=" + ac_phone + ", ac_type=" + ac_type + ", ac_pw_sec=" + ac_pw_sec + "]";
	}
	
	
	
	
}
