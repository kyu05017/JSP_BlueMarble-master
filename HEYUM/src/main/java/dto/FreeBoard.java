package dto;

public class FreeBoard {
	
	
	private int bno; // 게시물 번호 1
	private String btitle; // 제목 2
	private String bcontent; //게시물 내용3
	private int mno; // 글쓴이 4
	private String bfile; // 파일5
	private int bview; // 조회수 6
	private String bdate; // 등록일7
	private int boffer; // 추천수 8
	private int bcateory; // 카테고리 9
	private String mid;	//* 화면 표시용 */10
	public FreeBoard() {
		// TODO Auto-generated constructor stub
	}
	public FreeBoard(int bno, String btitle, String bcontent, int mno, String bfile, int bview, String bdate,
			int boffer, int bcateory, String mid) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.mno = mno;
		this.bfile = bfile;
		this.bview = bview;
		this.bdate = bdate;
		this.boffer = boffer;
		this.bcateory = bcateory;
		this.mid = mid;
	}
	
	public FreeBoard(int bno, String btitle, int bview,  int boffer) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bview = bview;
		this.boffer = boffer;
	}
	
	public FreeBoard(int bno, String btitle, int bcateory) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcateory = bcateory;
	}
	
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getBfile() {
		return bfile;
	}
	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
	public int getBview() {
		return bview;
	}
	public void setBview(int bview) {
		this.bview = bview;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getBoffer() {
		return boffer;
	}
	public void setBoffer(int boffer) {
		this.boffer = boffer;
	}
	public int getBcateory() {
		return bcateory;
	}
	public void setBcateory(int bcateory) {
		this.bcateory = bcateory;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "FreeBoard [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", mno=" + mno + ", bfile="
				+ bfile + ", bview=" + bview + ", bdate=" + bdate + ", boffer=" + boffer + ", bcateory=" + bcateory
				+ ", mid=" + mid + "]";
	}

	
	
	
	
}
