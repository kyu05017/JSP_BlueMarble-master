package dto;

public class Building {

	private int b_no;
	private int bc_no;
	private int p_no;
	private int gb_location;
	private int groom_no;
	
	public Building() {
		// TODO Auto-generated constructor stub
	}

	public Building(int b_no, int bc_no, int p_no, int gb_location, int groom_no) {
		super();
		this.b_no = b_no;
		this.bc_no = bc_no;
		this.p_no = p_no;
		this.gb_location = gb_location;
		this.groom_no = groom_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getBc_no() {
		return bc_no;
	}

	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public int getGb_location() {
		return gb_location;
	}

	public void setGb_location(int gb_location) {
		this.gb_location = gb_location;
	}

	public int getGroom_no() {
		return groom_no;
	}

	public void setGroom_no(int groom_no) {
		this.groom_no = groom_no;
	}

	@Override
	public String toString() {
		return "Building [b_no=" + b_no + ", bc_no=" + bc_no + ", p_no=" + p_no + ", gb_location=" + gb_location
				+ ", groom_no=" + groom_no + "]";
	}
	
	
	
	
}
