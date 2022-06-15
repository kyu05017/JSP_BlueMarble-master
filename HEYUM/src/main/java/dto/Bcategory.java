package dto;

public class Bcategory {
	
	private int bc_no;
	private String bc_category;
	
	public Bcategory() {
		// TODO Auto-generated constructor stub
	}

	public Bcategory(int bc_no, String bc_category) {
		super();
		this.bc_no = bc_no;
		this.bc_category = bc_category;
	}

	public int getBc_no() {
		return bc_no;
	}

	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}

	public String getBc_category() {
		return bc_category;
	}

	public void setBc_category(String bc_category) {
		this.bc_category = bc_category;
	}

	@Override
	public String toString() {
		return "Bcategory [bc_no=" + bc_no + ", bc_category=" + bc_category + "]";
	}
	
	
	
}
