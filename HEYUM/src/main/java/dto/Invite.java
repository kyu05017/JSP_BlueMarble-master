package dto;

public class Invite {

	private int i_no;
	private int i_frommno;
	private int i_tomno;
	private int groom_no;
	
	public Invite() {
		// TODO Auto-generated constructor stub
	}

	public Invite(int i_no, int i_frommno, int i_tomno, int groom_no) {
		super();
		this.i_no = i_no;
		this.i_frommno = i_frommno;
		this.i_tomno = i_tomno;
		this.groom_no = groom_no;
	}

	public int getI_no() {
		return i_no;
	}

	public void setI_no(int i_no) {
		this.i_no = i_no;
	}

	public int getI_frommno() {
		return i_frommno;
	}

	public void setI_frommno(int i_frommno) {
		this.i_frommno = i_frommno;
	}

	public int getI_tomno() {
		return i_tomno;
	}

	public void setI_tomno(int i_tomno) {
		this.i_tomno = i_tomno;
	}

	public int getGroom_no() {
		return groom_no;
	}

	public void setGroom_no(int groom_no) {
		this.groom_no = groom_no;
	}

	@Override
	public String toString() {
		return "Invite [i_no=" + i_no + ", i_frommno=" + i_frommno + ", i_tomno=" + i_tomno + ", groom_no=" + groom_no
				+ "]";
	}
	
	
	
}
