package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameDao extends Dao {
	
	public static GameDao gameDao = new GameDao();
	public static GameDao getGameDao() {return gameDao;};
	
	public GameDao() {
		super();
	}
	
	public JSONArray getplocation(String loginid, int dice) {
		JSONArray jsonArray = new JSONArray();
		
		try {
			String sql = "update player set p_location ="+dice+" where p_nickname = ?" ;
			ps = con.prepareStatement(sql);
			ps.setString(1, loginid);
			ps.executeUpdate();
			sql = "select p_nickname, p_location from player";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("닉네임", rs.getString(1));
				jsonObject.put("현재위치", rs.getInt(2));
				jsonArray.put(jsonObject);	
			}
			return jsonArray;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONArray gameready(String loginid, int dice) {
		JSONArray jsonArray = new JSONArray();
		
		try {
			String sql = "SELECT * FROM bluemarble.player where p_nickname = ?" ;
			ps = con.prepareStatement(sql);
			ps.setString(1, loginid);
			ps.executeUpdate();
			sql = "select p_nickname, p_location from player";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("닉네임", rs.getString(1));
				jsonObject.put("현재위치", rs.getInt(2));
				jsonArray.put(jsonObject);	
			}
			return jsonArray;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public JSONArray getgamelist() { // 대기실에서 사용할 방 리스트
		JSONArray jsonArray = new JSONArray();
		try {
			String sql = "select a.*, count(b.p_no) from gameroom a join player b on a.groom_no=b.groom_no group by a.groom_no" ;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("방번호", rs.getInt(1));
				jsonObject.put("방제목", rs.getString(2));
				//jsonObject.put("비밀번호", rs.getString(3));
				jsonObject.put("게임중", rs.getInt(4));
				jsonObject.put("인원수", rs.getInt(5));
				jsonArray.put(jsonObject);	
			}
			return jsonArray;		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int enterroom(int groomno, String roomname, int acno) {
		try {
			if(groomno==0) {
				String sql = "insert into gameroom (groom_name) values ('"+roomname+"')";
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();// 생성된 pk 값 빼오기
				if(rs.next()) {
					sql = "insert into player (p_money,p_location,p_turn,p_uisland,p_order,groom_no,ac_no) values (4000000,0,1,0,0,"+rs.getInt(1)+","+acno+")";
					ps = con.prepareStatement(sql);
					ps.executeUpdate();
					return rs.getInt(1);
				}
			}
			else { // 게임방으로 이동할때 p_order받는거로 수정해보려고 먼저 쓰던코드는 주석처리
//				String sql = "select count(*) from player where groom_no = " + groomno ; 
//				ps = con.prepareStatement(sql);
//				ResultSet rs2 = ps.executeQuery();
//				if(rs2.next()) {
//					sql = "insert into player (p_money,p_location,p_turn,p_uisland,p_order,groom_no,ac_no) values (4000000,0,0,0,"+(rs2.getInt(1)+1)+","+groomno+","+acno+")";
//					ps = con.prepareStatement(sql);
//					ps.executeUpdate();
//					return groomno;
//				}
				String sql = "insert into player (p_money,p_location,p_turn,p_uisland,p_order,groom_no,ac_no) values (4000000,0,0,0,0,"+groomno+","+acno+")";
				ps = con.prepareStatement(sql);
				ps.executeUpdate();	
				return groomno;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0 ;
	}
	
	public boolean giveporder(int groomno) {
		try {
			String sql = "select * from player where groom_no = " + groomno;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ArrayList<Integer> pnolist = new ArrayList<Integer>();
			while(rs.next()) {
				pnolist.add(rs.getInt(1));
			}
			Random random = new Random();              // 랜덤으로 주려다가 전체 랜덤이 아니고 랜덤1등 -> 시계방향으로 가야해서 일단 안씀
			int[] randomorder = new int[pnolist.size()];	
			for(int i = 0 ; i < pnolist.size() ; i++) {
				boolean pass = true ;
				int randomno = random.nextInt(pnolist.size())+1;
				for(int temp : randomorder) {
					if(temp==randomno) {
						i--;
						pass = false;
					}
				}
				if (pass) randomorder[i] = randomno ;
			}	
			for(int i = 0 ; i < pnolist.size() ; i++) {		
				sql = "update player set p_order = ?, p_turn = ? where groom_no = ? and p_no = ?" ;
				ps = con.prepareStatement(sql);
				if(randomorder[i]==1) {
					ps.setInt(1, randomorder[i]);
					ps.setInt(2, 1);					
				}
				else {
					ps.setInt(1, randomorder[i]);
					ps.setInt(2, 0);					
				}
				ps.setInt(3, groomno);
				ps.setInt(4, pnolist.get(i));
				ps.executeUpdate();
			}
//			for(int i = 0 ; i < pnolist.size() ; i++) {		
//				sql = "update player set p_order = ? where groom_no = ? and p_no = ?" ;
//				ps = con.prepareStatement(sql);
//				ps.setInt(1, (i+1));
//				ps.setInt(2, groomno);
//				ps.setInt(3, pnolist.get(i));
//				ps.executeUpdate();
//			}
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean outroom(int groomno, int acno) {// 일단 플레이어, 게임방을 삭제하지 않는쪽으로 짜놓음 // 일단 삭제하는 쪽으로 바꿈 (브라우저 강제로 닫았을때 대비)
		try {
//			String sql = "update player set groom_no = 0  where groom_no = "+groomno+" and ac_no = " + acno;
			String sql = "delete from player where groom_no = "+groomno+" and ac_no = " + acno;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JSONArray getroomlist(int roomno) {
		try {
			JSONArray jsonArray = new JSONArray();
			String sql = "select a.groom_no, b.ac_nickname, b.ac_id ,b.ac_profileimg,b.win,b.lose from gameroom a left join (select a.groom_no,b.ac_nickname,b.ac_id,b.ac_profileimg,b.win,b.lose from player a left join aacount b on a.ac_no=b.ac_no) b on a.groom_no=b.groom_no where a.groom_no ="+roomno;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("nickname", rs.getString(2));
				jsonObject.put("loginid", rs.getString(3));
				jsonObject.put("profile", rs.getString(4));
				jsonObject.put("win", rs.getInt(5));
				jsonObject.put("lose", rs.getInt(6));
				jsonArray.put(jsonObject);
			}
			return jsonArray;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 게임 결과 저장
	public boolean gameresultsave(int groom_no, int ac_no, String r_result) {
		try {
			
			if(r_result.equals("승리")) {
				String sql = "update aacount set win = (win+1) where ac_no =?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, ac_no);
				ps.executeUpdate();
				return true;
			}
			else if(r_result.equals("패배")) {
				String sql = "update aacount set lose = (lose+1) where ac_no =?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, ac_no);
				ps.executeUpdate();
				return true;
			}
			
//			String sql = "insert into result(groom_no,ac_no,r_result) values(?,?,?)";
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, groom_no);	
//			ps.setInt(2, ac_no);
//			ps.setString(3, r_result);
//			ps.executeUpdate();
//			return true;
		}catch(Exception e) {System.out.println("게임 결과 저장 오류 : "+e);}
		return false;
	}
	
	public boolean gamestart(int groom_no) {
		try {
			String sql = "update gameroom set groom_playing = 1 where groom_no = "+ groom_no ;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
