package dto;

public class Result {

	private int r_no;
	private int groom_no;
	private int ac_no;
	private String r_result;
	
	public Result() {
		// TODO Auto-generated constructor stub
	}

	public Result(int r_no, int groom_no, int ac_no, String r_result) {
		super();
		this.r_no = r_no;
		this.groom_no = groom_no;
		this.ac_no = ac_no;
		this.r_result = r_result;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public int getGroom_no() {
		return groom_no;
	}

	public void setGroom_no(int groom_no) {
		this.groom_no = groom_no;
	}

	public int getac_no() {
		return ac_no;
	}

	public void setac_no(int ac_no) {
		this.ac_no = ac_no;
	}

	public String getR_result() {
		return r_result;
	}

	public void setR_result(String r_result) {
		this.r_result = r_result;
	}

	@Override
	public String toString() {
		return "Result [r_no=" + r_no + ", groom_no=" + groom_no + ", ac_no=" + ac_no + ", r_result=" + r_result + "]";
	}
	
	
	
}
