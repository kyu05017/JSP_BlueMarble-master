package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Player;


public class PlayerDao extends Dao{

	public static PlayerDao playerDao = new PlayerDao();
	
	// 플레이어 등록
	public boolean addplayer(int groom_no, int ac_no) {
		try {
			String sql = "insert into player(groom_no,ac_no) values('"+groom_no+"',"+ac_no+")";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("플레이어 등록 오류 : "+ e);}
		return false;
	}
	
	// 게임방에 등록된 플레이어 불러오기
	public ArrayList<Player> getplayers(int groom_no){
		try {
			ArrayList<Player> plist = new ArrayList<Player>();
			String sql = "select player.*, aacount.ac_nickname, aacount.ac_profileimg from player join aacount on player.ac_no = aacount.ac_no where groom_no="+groom_no+" order by p_order asc";
			ps = con.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				Player player = new Player(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getString(10) );
				plist.add(player);
			}
			return plist;
			}catch(Exception e) {System.out.println("플레이어 불러오기 오류 : "+e);}
		
		return null;
	}
	
	// 회원 번호로 플레이어정보 가져오기
	public Player getplayer(int no, String table,int groomno) {
		try {
			String sql = "select player.*, aacount.ac_nickname, aacount.ac_profileimg from player join aacount on player.ac_no = aacount.ac_no where player."+table+"="+no + " and player.groom_no = " + groomno;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				Player player = new Player(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
				return player;
			}
		}catch(Exception e) {System.out.println("플레이어 정보 오류 : "+e);}
		return null;
	}

	
	// 플레이어 순서 정하기
	public boolean setorder(int p_order, int p_no) {
		try {
			String sql = "update player set p_order="+p_order+" where p_no="+p_no;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("플레이어 순서정하기 오류 : "+ e);}
		return false;
	}
	
	// 턴 시작/종료
	public boolean turn(int p_no){
		try {
			String sql = "select p_turn from player where p_no="+p_no;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				String sql2;
				if(rs.getInt(1)==0) {
					sql2 = "update player set p_turn=1 where p_no="+p_no;
				}else {
					sql2 = "update player set p_turn=0 where p_no="+p_no;
				}
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.executeUpdate();
				return true;
			}
		}catch(Exception e) {System.out.println("턴계산 오류 : "+e);}
		return false;
	}
	
	// 플레이어 위치 움직이기
	public boolean moveplayer(int p_no,int p_location,int dice) {
		try {
			String sql = "update player set p_location=? where p_no="+p_no;
			ps = con.prepareStatement(sql);
			if(p_location+dice>39) {
				ps.setInt(1, p_location+dice-40);
			}else {
				ps.setInt(1, p_location+dice);
			}
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("플레이어 위치 움직이기 오류 : "+e);}
		return false;
	}
	
	// 플레이어 파산
	public boolean bankruptcy(int p_no) {
		try {
			String sql = "update player set p_location=40, p_money=0, p_uisland=0 where p_no="+p_no;
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("플레이어 파산 오류 : "+e);}
		return false;
	}
	
	
	// 플레이어 금액 변동
	public boolean changemoney(int p_no, int price) {
		try {
			String sql = "update player set p_money= p_money+"+price+" where p_no="+p_no;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("금액 변동 오류 : "+e);}
		return false;
	}
	
	
}
