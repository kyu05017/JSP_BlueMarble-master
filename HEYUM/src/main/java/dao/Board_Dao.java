package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import dto.Bcategory;
import dto.FreeBoard;
import dto.FreeBoard_reply;


public class Board_Dao extends Dao {
	
	
	
	public static Board_Dao boardDao = new Board_Dao();
	
	//xxs
	// 1. 게시물 글 쓰기
	 public String xssFilter(String str) {
	      String result = "";
	      
	      result = str;
	      result = result.replaceAll("[<]", "&lt;");
	      result = result.replaceAll("[>]", "&gt;");

	      return result;
	   }
	public boolean write( FreeBoard board ) {

		String sql = "insert into freeboard( btitle , bcontent , ac_no , bfile, bcategory )values(?,?,?,?,?)";
			try {
				ps = con.prepareStatement(sql);
				ps.setString( 1 , xssFilter(board.getBtitle() ));	ps.setString( 2 , board.getBcontent() );
				ps.setInt( 3 , board.getMno() );		ps.setString( 4 , board.getBfile()); 
				ps.setInt(5, board.getBcateory());
				ps.executeUpdate(); return true;
			}catch (Exception e) { System.out.println("BoardDao 게시물쓰기(write) 에러 : "+e);}
				
		
		return false;
	}
	
	//2-1 게시물 전체 개수 출력 메소드
		public int gettotalrow(String key, String keyword, int category) {
		
			String sql = null;
			if(key.equals("")&& keyword.equals("")) { sql =  "select count(bno) from freeboard where bcategory = " +category;	} // 검색이 없을 경우
			else { sql= "select count(bno) from freeboard left join aacount on freeboard.ac_no=aacount.ac_no where bcategory=" +category+" and "+key+" like '%"+keyword+"%'";} // 검색이 있을 경우
			try{		//select count(bno) from board left join member on board.mno=member.mno where "+key+" like '%"+keyword+"%'";} // 검색이 있을 경우
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
					if(rs.next()) 
					//	System.out.println("2-1sql : "+rs.getInt(1));
						return rs.getInt(1);
					
			}catch (Exception e) {System.out.println("게시물 전체 개수 출력 메소드 dao 2-1 errer : "+e);		}
			return 0 ;
		}
	
	// 2-2. 모든 게시물 출력 메소드 [ 인수 : x  // 추후기능 = 검색 : 조건 ]
		public ArrayList<FreeBoard> getboardlist(int startrow, int listsize,String key, String keyword,int category) {  // category 추가
			ArrayList<FreeBoard> boardlist = new ArrayList<FreeBoard>();
			
			
//			System.out.println("2-2key: "+key);
//			System.out.println("2-2keyword: "+keyword);
			SimpleDateFormat sample_date = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date= new Date(); 

			String sql = null;
			
			if(key.equals("")&&keyword.equals("")) { //검색이 없을 경우
				 sql = "select freeboard.*, aacount.ac_id from freeboard left join aacount on freeboard.ac_no=aacount.ac_no where freeboard.bcategory = "+category+" order by bno desc limit "+startrow+","+listsize; // limit 첫번째 레ㅠㅁ코드의 인덱스(시작인덱스), 표시갯수  -> x부터~y개*
				// select freeboard.*, aacount.ac_id from      freeboard left join aacount on freeboard.ac_no=aacount.ac_no where freeboard.bcategory = 2  order by bno desc  limit 10,10
			}else { // 검색이 있을 경우
				 sql = "select freeboard.*, aacount.ac_id from freeboard left join aacount on freeboard.ac_no=aacount.ac_no where freeboard.bcategory = "+category+" and "+key+" like '%"+keyword+"%'order by bno desc limit "+startrow+","+listsize;
				 //     select freeboard.*, aacount.ac_id from freeboard left join aacount on freeboard.ac_no=aacount.ac_no where freeboard.bcategory = 3            and btitle like       '%dd%'
			}			
			try {	
				ps = con.prepareStatement(sql);
				ps.executeQuery();
				rs = ps.executeQuery();
				while(rs.next()) {
					if(rs.getString(7).split(" ")[0].equals(sample_date.format(date))){
						
					FreeBoard board = new FreeBoard(
							rs.getInt(1), rs.getString(2), 
							rs.getString(3), rs.getInt(4), 
							rs.getString(5), rs.getInt(6), 
							rs.getString(7).split(" ")[1], rs.getInt(8),
							rs.getInt(9),rs.getString(10));
				
					boardlist.add(board);
					
					
					
					}
					else {
						FreeBoard board = new FreeBoard(
								rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getInt(4), 
								rs.getString(5), rs.getInt(6), 
								rs.getString(7).split(" ")[0], rs.getInt(8),
								rs.getInt(9),rs.getString(10));
					
						boardlist.add(board);
					}
					
				}
			
				
				return boardlist;
			}catch (Exception e) {System.out.println("2-2 BoardDao-모든 게시물 출력 sql 오류"+e);		}
			return null; }
	
	
		// 3. 개별 게시물 출력 메소드 [ 인수 : 게시물번호 ]
				public FreeBoard getboard( int bno ) { 
					String sql ="select freeboard.*, aacount.ac_id from freeboard left join aacount on freeboard.ac_no=aacount.ac_no where bno="+bno;	// 1. SQL 작성 
					
					
					try {
						ps = con.prepareStatement(sql);	// 2.연결된DB( con ) 에 SQL문 설정  
						rs = ps.executeQuery(); 		// 3.조작된 SQL를 실행
						if( rs.next() ) { // 4. 검색 결과 [ rs.next() 할 때 마다 결과물에서 레코드 1개씩 호출 ]  
							FreeBoard board = new FreeBoard( 
								rs.getInt(1),rs.getString(2), 
								rs.getString(3),rs.getInt(4),
								rs.getString(5), rs.getInt(6),
								rs.getString(7) ,rs.getInt(8) ,
								rs.getInt(9),rs.getString(10)
							);
							return board;
						}
					}catch ( Exception e) {System.out.println("3. 개별 게시물 출력 메소드 [ 인수 : 게시물번호 ] : "+e);} return null;
					
					
				}
			
				//3-2   
				//select board.*, member.mid from board left join member on board.mno=member.mno
			public ArrayList<FreeBoard> getmyboard( String mid ) { 
					
			
					ArrayList<FreeBoard> myboardlist = new ArrayList<FreeBoard>();
					String sql = " select freeboard.*, aacount.ac_id from freeboard left join aacount on freeboard.ac_no=aacount.ac_no where aacount.ac_id=? order by bno desc";
					SimpleDateFormat sample_date = new SimpleDateFormat("yyyy-MM-dd");
					Date date= new Date();  
					
					try {
						
						ps = con.prepareStatement(sql);
						ps.setString(1, mid);
						ps.executeQuery();
						rs = ps.executeQuery();
						while(rs.next()) {
							if(rs.getString(7).split(" ")[0].equals(sample_date.format(date))){
								FreeBoard board = new FreeBoard(
									rs.getInt(1), rs.getString(2), 
									rs.getString(3), rs.getInt(4), 
									rs.getString(5), rs.getInt(6), 
									rs.getString(7).split(" ")[1], 
									rs.getInt(8),rs.getInt(9),
									null);
							myboardlist.add(board);
							}
						else {
							FreeBoard board = new FreeBoard(
									rs.getInt(1), rs.getString(2), 
									rs.getString(3), rs.getInt(4), 
									rs.getString(5), rs.getInt(6), 
									rs.getString(7).split(" ")[1], 
									rs.getInt(8),rs.getInt(9),
									null);
							myboardlist.add(board);
							
						}
							
							}
						return myboardlist;
					}catch (Exception e) {} 
					return null;
							
				}
					
				
			// 4. 게시물 수정 메소드 	[ 인수 : 수정할 게시물번호  / 수정된 내용 ]
			public boolean update( FreeBoard board ) { 
				
				String sql = "update freeboard set btitle=? , bcontent=? , bfile=? where bno=?";
				
				try {
					ps=con.prepareStatement(sql);
					ps.setString(1, board.getBtitle());
					ps.setString(2, board.getBcontent());
					ps.setString(3, board.getBfile());
					ps.setInt(4, board.getBno());
					ps.executeUpdate();
					return true;
				}catch(Exception e) {System.out.println("수정 실패"+e);}
				return false;
			}
			
			// 5. 게시물 삭제 메소드 	[ 인수 : 삭제할 게시물번호 
			public boolean delete( int bno ) { 
			
				
					String sql = "delete from freeboard where bno="+bno;	
				try {	
					ps = con.prepareStatement(sql);
					//ps.setInt(1, bno);
					ps.executeUpdate(); 
					return true;
						
				}catch(Exception e ) { System.out.println( "delete[SQL 오류]"+e  ); }
				return false;
			}
			
			// 5-2 첨부파일만 삭제(null 로 변경)
			public boolean file_delete(int bno) {
				String sql = "update freeboard set bfile=null where bno="+bno;
				try {
					ps= con.prepareStatement(sql);
					ps.executeUpdate();
					return true;
				}catch (Exception e) {System.out.println("첨부 파일삭제오류 : "+ e);}
				return false;
			}
			
			// 6-1. 게시물 조회 증가 메소드 	[ 인수 : 증가할 게시물번호 ]
			public boolean increview( int bno ) { 
				String sql = "update freeboard set bview = bview+1 where bno="+bno;
				try {
					ps = con.prepareStatement(sql);
					ps.executeUpdate();
					return true;
				}catch (Exception e) {System.out.println("조회수 증가 메소드 에러 :"+e);}
				
				return false; }
			
			
			
			
			
			// 6-2. 추천 증가 메소드 	[ 인수 : 증가할 게시물번호 ]
			public boolean increoffer( int bno ) {
				// 1차 체크
				String sql = "update freeboard set boffer = boffer+1 where bno="+bno;
				try {
					ps = con.prepareStatement(sql);
					ps.executeUpdate();
					return true;
				}catch (Exception e) {System.out.println("추천 증가 메소드 에러 :"+e);}
				
				return false; }
			
			
			
			
			// 7. 댓글 작성 메소드 		[ 인수 : 작성된 데이터들 = dto ]
			public boolean replywrite(FreeBoard_reply reply) { 
				
				String sql = "insert into freeboard_reply(rcontent,rindex,bno,ac_no)values(?,?,?,?)";
				try {
					ps= con.prepareStatement(sql);
					ps.setString(1, reply.getRcontent());
					ps.setInt(2, reply.getRindex());
					ps.setInt(3, reply.getBno());
					ps.setInt(4, reply.getMno());
					ps.executeUpdate();
					return true;
				}catch (Exception e) {System.out.println("댓글작성sql오류: "+e);}
				
				return false; 
				
			}
			// 8. 댓글 출력 메소드 		[ 인수 : 현재 게시물번호 ]
				public ArrayList<FreeBoard_reply> replylist( int bno ) { 
					ArrayList<FreeBoard_reply> replylist = new ArrayList<FreeBoard_reply>();
					String sql = "select * from freeboard_reply where bno = "+bno+" and rindex = 0"; // rindex = 0  : 댓글만 출력 [ 대댓글 제외 ] 
					try {
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery(); 
						while( rs.next() ) { 
							FreeBoard_reply reply = new FreeBoard_reply( 
									rs.getInt(1) , rs.getString(2) , 
									rs.getString(3) , rs.getInt(4) , 
									rs.getInt(5), rs.getInt(6), null);
							replylist.add(reply);
						}
						return replylist;
					}catch (Exception e) { System.out.println( e ); } return null; 
				}
				// 8-2 대댓글 출력 메소드 
				public ArrayList<FreeBoard_reply> rereplylist( int bno , int  rno ){
					ArrayList<FreeBoard_reply> rereplylist = new ArrayList<FreeBoard_reply>();
					String sql = "select * from freeboard_reply where bno = "+bno+" and rindex = "+rno;
					try { 
						ps = con.prepareStatement(sql); rs= ps.executeQuery();
						while( rs.next() ) {
							FreeBoard_reply reply = new FreeBoard_reply(
									rs.getInt(1) , rs.getString(2),
									rs.getString(3), rs.getInt(4), 
									rs.getInt(5), rs.getInt(6), null);
							rereplylist.add(reply);
						}
						return rereplylist;
					}catch (Exception e) { System.out.println(e); } return null;
					
				}

			// 9. 댓글 수정 메소드 1 		[ 인수 : 수정할 댓글 번호 ]
			public boolean replyupdate(int rno,int bno, String content) { 
				
				String sql = "update freeboard_reply set rcontent=? where rno=? and bno=?";
				
				try {
					ps=con.prepareStatement(sql);
					ps.setString(1, content);
					ps.setInt(2, rno);
					ps.setInt(3, bno);
					ps.executeUpdate();
					return true;
				}catch(Exception e) {System.out.println("댓글수정 실패"+e);}
			
				return false; }
			// 10. 댓글 삭제 메소드 		[ 인수 : 삭제할 댓글 번호 ] 
			public boolean replydelete(int rno) { 
			//	System.out.println(rno);
				String sql = "delete from freeboard_reply where rno = "+rno+" or rindex = "+rno; 
				try {
					ps=con.prepareStatement(sql);
					ps.executeUpdate();
					return true;
				}catch (Exception e) {System.out.println("댓글삭제sql실패:"+e);}
				
				return false; }
			
		////
			// offer  게시물 출력 메소드 [ 인수 : x  // 추후기능 = 검색 : 조건 ]
			public ArrayList<FreeBoard> getofferlist(int offernum, int category){ 
			
			
			ArrayList<FreeBoard> offerlist = new ArrayList<FreeBoard>();
			String offer_sql = "select * from freeboard where freeboard.bcategory = "+category+" order by boffer desc limit " +offernum;
							//select * from freeboard where freeboard.bcategory = 2 order by boffer desc limit 5
			try {	
				ps = con.prepareStatement(offer_sql);
				ps.executeQuery();
				rs = ps.executeQuery();
				while(rs.next()) {
					FreeBoard offerboard = new FreeBoard(
						rs.getInt(1), rs.getString(2), 
						rs.getInt(6),rs.getInt(8));
				offerlist.add(offerboard);
				}
				return offerlist;
			}catch (Exception e) {System.out.println("offer 출력 오류 :"+e);}
			
			
			return null;
			}
			
			
/////
				/// view 용 출력
				public ArrayList<FreeBoard> getviewlist(int offernum, int category){ 
				ArrayList<FreeBoard> viewlist = new ArrayList<FreeBoard>();
				String view_sql = "select * from freeboard where freeboard.bcategory = "+category+" order by bview desc limit " +offernum;
				//                 
				try {	
					ps = con.prepareStatement(view_sql);
					ps.executeQuery();
					rs = ps.executeQuery();
					while(rs.next()) {
						FreeBoard viewboard = new FreeBoard(
								rs.getInt(1), rs.getString(2), 
								rs.getInt(6),rs.getInt(8));
						viewlist.add(viewboard);
					}
					return viewlist;
				}catch (Exception e) {System.out.println("offer 출력 오류 :"+e);}
				
				
				
				return null;
				}
				
				///main view 용 출력 (최신글)
				public ArrayList<FreeBoard> getmainviewlist(int offernum, int category){ 
				ArrayList<FreeBoard> main_viewlist = new ArrayList<FreeBoard>();
				String view_sql = "select * from freeboard where freeboard.bcategory = "+category+" order by bdate desc limit " +offernum;
				//                 
				try {	
					ps = con.prepareStatement(view_sql);
					ps.executeQuery();
					rs = ps.executeQuery();
					while(rs.next()) {
						FreeBoard viewboard = new FreeBoard(
								rs.getInt(1), rs.getString(2), 
								rs.getInt(9));
						main_viewlist.add(viewboard);
					}
					return main_viewlist;
				}catch (Exception e) {System.out.println("offer 출력 오류 :"+e);}
				
				
				
				return null;
				}
				// 카테고리 리스트 빼오기
				public ArrayList<Bcategory> getcategory_num(){
					ArrayList<Bcategory> categorylist = new ArrayList<Bcategory>();
				
					String sql = "SELECT * FROM bluemarble.board_category ";
					try {
						ps = con.prepareStatement(sql);
						ps.executeQuery();
						rs= ps.executeQuery();
						while(rs.next()) {
							Bcategory categories = new Bcategory(
									rs.getInt(1),rs.getString(2)
									);
							categorylist.add(categories);
									
						}
						return categorylist;
					}catch (Exception e) { System.out.println("카테고리 리스트 빼오기 실패 : "+e);}
					return categorylist;
				}
				

				// 3. 카테고리 이름  출력 메소드 [ 인수 : 카테고리 번호 ]
				public Bcategory getCategoryname( int ca_no ) { 
					String sql ="select ca_name from board_category where ca_no = ?" +ca_no; 
					
					
					try {
						ps = con.prepareStatement(sql);	// 2.연결된DB( con ) 에 SQL문 설정  
						rs = ps.executeQuery(); 		// 3.조작된 SQL를 실행
						if( rs.next() ) { // 4. 검색 결과 [ rs.next() 할 때 마다 결과물에서 레코드 1개씩 호출 ]  
							Bcategory categorylist = new Bcategory( 
								rs.getInt(1),rs.getString(2)
							);
							return categorylist;
						}
					}catch ( Exception e) {System.out.println("카테고리 이름  출력 메소드 [ 인수 : 카테고리 번호 ] : "+e);} return null;
					
					
				}
				
				// 댓글 갯수 
				public int getReplyCount(int bno) {
					
					String sql = " SELECT count(*) FROM bluemarble.freeboard_reply where bno= " +bno;
					
					try {
						ps = con.prepareStatement(sql);
						rs=ps.executeQuery();
						if(rs.next()) {
						
						}
						
						return 	rs.getInt(1);
					}catch (Exception e) {System.out.println("댓글갯수 BoardDao  오류 : " +e);
					}
					
					return 0;
				}
				
	
				
				
				
				
}