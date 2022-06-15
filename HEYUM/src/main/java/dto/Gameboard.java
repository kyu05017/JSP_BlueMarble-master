package dto;

public class Gameboard {
	
	private int gb_location;
	private String gb_cityname;
	private int gb_emptyprice;
	private int gb_villaprice;
	private int gb_buildingprice;
	private int gb_hotelprice;
	private int gb_emptyfee;
	private int gb_villafee;
	private int gb_buildingfee;
	private int gb_hotelfee;
	
	public Gameboard() {
		// TODO Auto-generated constructor stub
	}

	public Gameboard(int gb_location, String gb_cityname, int gb_emptyprice, int gb_villaprice, int gb_buildingprice,
			int gb_hotelprice, int gb_emptyfee, int gb_villafee, int gb_buildingfee, int gb_hotelfee) {
		super();
		this.gb_location = gb_location;
		this.gb_cityname = gb_cityname;
		this.gb_emptyprice = gb_emptyprice;
		this.gb_villaprice = gb_villaprice;
		this.gb_buildingprice = gb_buildingprice;
		this.gb_hotelprice = gb_hotelprice;
		this.gb_emptyfee = gb_emptyfee;
		this.gb_villafee = gb_villafee;
		this.gb_buildingfee = gb_buildingfee;
		this.gb_hotelfee = gb_hotelfee;
	}

	public int getGb_location() {
		return gb_location;
	}

	public void setGb_location(int gb_location) {
		this.gb_location = gb_location;
	}

	public String getGb_cityname() {
		return gb_cityname;
	}

	public void setGb_cityname(String gb_cityname) {
		this.gb_cityname = gb_cityname;
	}

	public int getGb_emptyprice() {
		return gb_emptyprice;
	}

	public void setGb_emptyprice(int gb_emptyprice) {
		this.gb_emptyprice = gb_emptyprice;
	}

	public int getGb_villaprice() {
		return gb_villaprice;
	}

	public void setGb_villaprice(int gb_villaprice) {
		this.gb_villaprice = gb_villaprice;
	}

	public int getGb_buildingprice() {
		return gb_buildingprice;
	}

	public void setGb_buildingprice(int gb_buildingprice) {
		this.gb_buildingprice = gb_buildingprice;
	}

	public int getGb_hotelprice() {
		return gb_hotelprice;
	}

	public void setGb_hotelprice(int gb_hotelprice) {
		this.gb_hotelprice = gb_hotelprice;
	}

	public int getGb_emptyfee() {
		return gb_emptyfee;
	}

	public void setGb_emptyfee(int gb_emptyfee) {
		this.gb_emptyfee = gb_emptyfee;
	}

	public int getGb_villafee() {
		return gb_villafee;
	}

	public void setGb_villafee(int gb_villafee) {
		this.gb_villafee = gb_villafee;
	}

	public int getGb_buildingfee() {
		return gb_buildingfee;
	}

	public void setGb_buildingfee(int gb_buildingfee) {
		this.gb_buildingfee = gb_buildingfee;
	}

	public int getGb_hotelfee() {
		return gb_hotelfee;
	}

	public void setGb_hotelfee(int gb_hotelfee) {
		this.gb_hotelfee = gb_hotelfee;
	}

	@Override
	public String toString() {
		return "Gameboard [gb_location=" + gb_location + ", gb_cityname=" + gb_cityname + ", gb_emptyprice="
				+ gb_emptyprice + ", gb_villaprice=" + gb_villaprice + ", gb_buildingprice=" + gb_buildingprice
				+ ", gb_hotelprice=" + gb_hotelprice + ", gb_emptyfee=" + gb_emptyfee + ", gb_villafee=" + gb_villafee
				+ ", gb_buildingfee=" + gb_buildingfee + ", gb_hotelfee=" + gb_hotelfee + "]";
	}
	
	
	
	
}
