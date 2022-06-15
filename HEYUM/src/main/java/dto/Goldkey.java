package dto;

public class Goldkey {

	private int gk_no;
	private String gk_name;
	private String gk_content;
	private int gk_value;
	
	public Goldkey() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Goldkey(int gk_no, String gk_name, String gk_content, int gk_value) {
		super();
		this.gk_no = gk_no;
		this.gk_name = gk_name;
		this.gk_content = gk_content;
		this.gk_value = gk_value;
	}


	
	
	

	@Override
	public String toString() {
		return "Goldkey [gk_no=" + gk_no + ", gk_name=" + gk_name + ", gk_content=" + gk_content + ", gk_value="
				+ gk_value + "]";
	}




	public int getGk_value() {
		return gk_value;
	}


	public void setGk_value(int gk_value) {
		this.gk_value = gk_value;
	}


	public int getGk_no() {
		return gk_no;
	}

	public void setGk_no(int gk_no) {
		this.gk_no = gk_no;
	}


	public String getGk_name() {
		return gk_name;
	}

	public void setGk_name(String gk_name) {
		this.gk_name = gk_name;
	}

	public String getGk_content() {
		return gk_content;
	}

	public void setGk_content(String gk_content) {
		this.gk_content = gk_content;
	}

	
	
	
	
}
