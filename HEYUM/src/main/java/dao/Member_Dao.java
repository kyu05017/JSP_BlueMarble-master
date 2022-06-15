package dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.Account;

public class Member_Dao extends Dao{

	public static Member_Dao m_dao = new Member_Dao();

/////// ouath 2.0 로그인 시스템 ///////////////////////////////////////////////////////////////////////////////
	public Account snsLoginCheck(Account account) {
		String sql = "";
		try {
			sql = "SELECT * FROM bluemarble.aacount where ac_id = '" + account.getAc_id()+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				Account account2 = new Account(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0, rs.getString(10),null,rs.getInt(12),rs.getInt(13));
				return account2;
			}
			else {
				
				String sql2 ="insert into aacount ( ac_id, ac_pw, ac_email, ac_name, ac_nickname,ac_profileimg,ac_phone, ac_type) values (?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql2);
				
				ps.setString(1,account.getAc_id()); 	
				ps.setString(2,account.getAc_pw()); 	
				ps.setString(3,account.getAc_id()); 	
				ps.setString(4,account.getAc_name()); 	
				ps.setString(5,account.getAc_nickname()); 	
				ps.setString(6,account.getAc_profileimg()); 
				ps.setString(7,account.getAc_phone()); 	
				ps.setInt(8,account.getAc_type()); 	
				ps.executeUpdate(); 				
				snsLoginCheck(account);
			}
		}
		catch (Exception e) {
			System.out.println("소셜 계정 로그인 오류 : " + e );
		}
		return null;
	}
/////// ouath 2.0 로그인 시스템 ///////////////////////////////////////////////////////////////////////////////
	
/////// 이메일 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	public boolean email_check(String email) {
		try {
			String sql = "select ac_email from aacount where ac_email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("이메일 중복체크 오류  : " + e );
		}
		return false;
	}
/////// 이메일 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	
/////// 이메일 인증번호 저장 ////////////////////////////////////////////////////////////////////////////////////
	public boolean email_authentification(String email,String authentification) {
		try {
			String sql = "select email from email_authentification where email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if(rs.next()) {
				String sql2 = "update email_authentification set emaill_authentification = ? where email = ?";
				ps = con.prepareStatement(sql2);
				ps.setString(1, authentification);
				ps.setString(2, email);
				ps.executeUpdate();
				return true;
			}
			else {
				String sql2 = "insert into email_authentification ( emaill_authentification, email ) values (?,?)";
				ps = con.prepareStatement(sql2);
				ps.setString(1, authentification);
				ps.setString(2, email);
				ps.executeUpdate();
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("이메일 인증번호 저장 오류  : " + e );
		}
		return false;
	}
/////// 이메일 인증번호 저장 ////////////////////////////////////////////////////////////////////////////////////
	
/////// 이메일 인증번호 ////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean authentification_check(String email,String authentification) {
		try {
			String sql = "select * from email_authentification where emaill_authentification = ? and email = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, authentification);
			ps.setString(2, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				// "delete from board where bno="+bno;
				String sql2 = "delete from email_authentification where email = ?";
				ps = con.prepareStatement(sql2);
				ps.setString(1, email);
				ps.executeUpdate();
				
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("인증번호 인증 오류  : " + e );
		}
		return false;
	}
	
/////// 이메일 인증번호 ////////////////////////////////////////////////////////////////////////////////////////

	
/////// 아이디 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean id_check(String id) {
		try {
			String sql = "SELECT ac_id FROM bluemarble.aacount where ac_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("아이디 중복체크 오류 : " + e );
		}
		return false;
	}
	
/////// 아이디 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	
/////// 맴버 회원가입  ////////////////////////////////////////////////////////////////////////////////////////

	public boolean signup(Account account) {
		try {
			String sql = "insert into aacount ( ac_id, ac_pw,ac_email,ac_name,ac_nickname,ac_profileimg,ac_phone ) values (?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, account.getAc_id());
			ps.setString(2, account.getAc_pw());
			ps.setString(3, account.getAc_email());
			ps.setString(4, account.getAc_name());
			ps.setString(5, account.getAc_nickname());
			ps.setString(6, account.getAc_profileimg());
			ps.setString(7, account.getAc_phone());
			ps.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.out.println("회원가입 오류 : " + e );
		}
		return false;
	}
	
/////// 맴버 회원가입  ////////////////////////////////////////////////////////////////////////////////////////

/////// 맴버 로그인   ////////////////////////////////////////////////////////////////////////////////////////
	
	public Account account_Login(String id, String pw) {
		try {
			String sql = "select * from aacount where ac_id = ? and ac_pw = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if(rs.next()) {
				Account account = new Account(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),null,rs.getInt(12),rs.getInt(13));
				return account;
			}
		}
		catch (Exception e) {
			System.out.println("로그인 오류 : " + e );
		}
		return null;
	}
	
/////// 맴버 로그인   ////////////////////////////////////////////////////////////////////////////////////////

/////// 회원정보 가져오기 //////////////////////////////////////////////////////////////////////////////////////

	public JSONObject get_account(int ac_no) {
		try {
			JSONObject object = new JSONObject();
			String sql = "select * from aacount where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,ac_no);
			ResultSet rs3;
			rs3 = ps.executeQuery();
			if(rs3.next()) {
				
				object.put("ac_no", rs3.getInt(1));
				object.put("ac_id", rs3.getString(2));
				object.put("ac_email", rs3.getString(4));
				object.put("ac_name", rs3.getString(5));
				object.put("ac_nickname", rs3.getString(6));
				object.put("ac_profileimg", rs3.getString(7));
				object.put("ac_phone", rs3.getString(8));


				return object;
			}
		}
		catch (Exception e) {
			System.out.println("회원 정보 JSON 오브젝트 오류: " + e );
		}
		return null;
	}
	
/////// 회원정보 가져오기 //////////////////////////////////////////////////////////////////////////////////////
	
/////// 닉네임 중복체크   //////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean nick_check(String nick) {
		try {
			String sql = "select ac_nickname from aacount where ac_nickname = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, nick);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
/////// 닉네임 중복체크    //////////////////////////////////////////////////////////////////////////////////////	

/////// 핸드폰 중복체크   //////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean phone_check(String phone) {
		try {
			String sql = "select ac_phone from aacount where ac_phone = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
/////// 핸드폰 중복체크    //////////////////////////////////////////////////////////////////////////////////////	
	
/////// 회원정보 수정    //////////////////////////////////////////////////////////////////////////////////////	
	public boolean nick_change(String nickname, int ac_no) {
		try {
			String sql = "update aacount set ac_nickname=? where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setInt(2, ac_no);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean email_change(String email, int ac_no) {
		try {
			String sql = "update aacount set ac_email=? where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, ac_no);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean phone_change(String phone, int ac_no) {
		try {
			String sql = "update aacount set ac_phone=? where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setInt(2, ac_no);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
/////// 회원정보 수정    //////////////////////////////////////////////////////////////////////////////////////
	
/////// 회원프로필 수정   //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean profile_change(String img, int ac_no) {
		try {
			String sql = "update aacount set ac_profileimg=? where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, img);
			ps.setInt(2, ac_no);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			System.out.println("프로필사진 업로드 오류 : " + e);
		}
		return false;
	}
	
/////// 회원프로필 수정    //////////////////////////////////////////////////////////////////////////////////////
	
/////// 회원비밀번호 수정   //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean pw_check(int no, String pw) {
		try {
			String sql = "select * from aacount where ac_pw=? and ac_no=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setInt(2, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("비밀번호 체크 오류 :: " + e );
		}
		return false;
	}
	
	public boolean pw_update(int no, String pw) {
		try {
			String sql = "update aacount set ac_pw=? where ac_no=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setInt(2, no);
			ps.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.out.println("비밀번호 수정 오류 :: " + e );
		}
		return false;
	}
	
	
/////// 회원비밀번호 수정   //////////////////////////////////////////////////////////////////////////////////////
	
/////// 아이디  찾기      //////////////////////////////////////////////////////////////////////////////////////
	
	public String find_email(String email) {
		try {
			String sql = "select ac_id from aacount where ac_email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("이메일로 찾기 오류 :: " + e );
		}
		return null;
	}
	
	public String find_phone(String phone) {
		try {
			String sql = "select ac_id from aacount where ac_phone = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("핸드폰으로ㄴ 찾기 오류 :: " + e );
		}
		return null;
	}
	
	
/////// 아이디  찾기      //////////////////////////////////////////////////////////////////////////////////////
	
/////// 비밀번호   찾기    //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean email_find2(String email) {
		try {
			String sql = "select * from aacount where ac_email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("이메일로 찾기 오류 :: " + e );
		}
		return false;
	}
	
	public String phone_find2(String phone) {
		try {
			String sql = "select ac_email from aacount where ac_phone = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("핸드폰으로ㄴ 찾기 오류 :: " + e );
		}
		return null;
	}
	
	public boolean email_instance_pw(String temp, String email) {
		try {
			String sql = "update aacount set ac_pw=? where ac_email=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, temp);
			ps.setString(2, email);
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("임시 비밀번호 오류 :: " + e );
		}
		return false;
	}
	
	
/////// 비밀번호  찾기     //////////////////////////////////////////////////////////////////////////////////////

/////// 회원탈퇴    //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean memberout(int no) {
		try {
			String sql = "delete from aacount where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.out.println("회원탈퇴 오류 :: " + e );
		}
		return false;
	}
	
	
/////// 회원탈퇴     //////////////////////////////////////////////////////////////////////////////////////
	
/////// 게임 전적  //////////////////////////////////////////////////////////////////////////////////////
	
	public JSONObject getwin(int no) {
		try {
			String sql = "SELECT win FROM bluemarble.aacount where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			JSONObject jsonObject = new JSONObject();
			while(rs.next()) {
				
				
				jsonObject.put("category", "win");
				jsonObject.put("value", rs.getInt(1));
			

			}
			return jsonObject;
		}
		catch (Exception e) {
			System.out.println("전적 가져오기 어류 :: " + e );
		}
		return null;
	}
	public JSONObject getlose(int no) {
		try {
			String sql = "SELECT lose FROM bluemarble.aacount where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			JSONObject jsonObject = new JSONObject();
			while(rs.next()) {
				
				
				jsonObject.put("category", "lose");
				jsonObject.put("value", rs.getInt(1));
			

			}
			return jsonObject;
		}
		catch (Exception e) {
			System.out.println("전적 가져오기 어류 :: " + e );
		}
		return null;
	}
/////// 게임 전적     //////////////////////////////////////////////////////////////////////////////////////
	
/////// 다른플레이어 정보//////////////////////////////////////////////////////////////////////////////////////

	public Account getother(int no) {
		try {
			String sql = "select * from aacount where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				Account account = new Account(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0, rs.getString(10),null,rs.getInt(12),rs.getInt(13));
				return account;
			}
		}
		catch (Exception e) {
			System.out.println("다른회원 가져오기 어류 :: " + e );
		}
		return null;
	}
	
	public String getpro(String id) {
		try {
			String sql = "select ac_profileimg from aacount where ac_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("다른회원 가져오기 어류 :: " + e );
		}
		return null;
	}
	
/////// 다른플레이어 정보//////////////////////////////////////////////////////////////////////////////////////
	
/////// 내글 호출 //////////////////////////////////////////////////////////////////////////////////////

	public JSONArray getmyboard(int no) {
		try {
			JSONArray array = new JSONArray();
			String sql = "select * from freeboard where ac_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("bno", rs.getInt(1));
				jsonObject.put("btitle", rs.getString(2));
				jsonObject.put("bcontent", rs.getString(3));
				jsonObject.put("ac_no", rs.getInt(4));
				jsonObject.put("bfile", rs.getString(5));
				jsonObject.put("bview", rs.getInt(6));
				jsonObject.put("bdate", rs.getString(7));
				jsonObject.put("boffer", rs.getInt(8));
				jsonObject.put("bcategory", rs.getInt(9));
				
				array.put(jsonObject);
			}
			return array;
		}
		catch (Exception e) {
			System.out.println("내글 가져오기 오류 :: " + e );
		}
		return null;
	}
	
	
	public JSONArray getmyquestion(int no) {
		try {
			JSONArray array = new JSONArray();
			String sql = "select * from freeboard where ac_no = ? and bcategory = 3";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("bno", rs.getInt(1));
				jsonObject.put("btitle", rs.getString(2));
				jsonObject.put("bcontent", rs.getString(3));
				jsonObject.put("ac_no", rs.getInt(4));
				jsonObject.put("bfile", rs.getString(5));
				jsonObject.put("bview", rs.getInt(6));
				jsonObject.put("bdate", rs.getString(7));
				jsonObject.put("boffer", rs.getInt(8));
				jsonObject.put("bcategory", rs.getInt(9));
				
				array.put(jsonObject);
			}
			return array;
		}
		catch (Exception e) {
			System.out.println("내글 가져오기 오류 :: " + e );
		}
		return null;
	}

	public JSONArray getallmember() {
		try {
			String sql = "select ac_id,ac_profileimg,win,lose from aacount order by win desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			JSONArray array = new JSONArray();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("id", rs.getString(1));
				jsonObject.put("profile", rs.getString(2));
				jsonObject.put("win", rs.getInt(3));
				jsonObject.put("lose", rs.getInt(4));
				
				array.put(jsonObject);
			}
			return array;
		}
		catch (Exception e) {
			System.out.println("모든 회원 가져오기 오류 :: " + e );
		}
		return null;
	}
	
	
	public boolean deletepro(int no) {
		try {
			String sql = "update aacount set ac_profileimg='default.jpg' where ac_no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public String getnick(String id) {
		try {
			String sql = "select ac_nickname from aacount where ac_id = ?";
			ps=con.prepareStatement(sql); 
			ps.setString(1, id);
			rs=ps.executeQuery(); 
			if( rs.next() ) {
				return rs.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("오류나니 ? : " + e);
		}
		return null;
	}
	
/////// 내글 호출 //////////////////////////////////////////////////////////////////////////////////////
		// 회원번호 출력 메소드 
		public int getmno( String mid ) {
			String sql = "select ac_no from aacount where ac_id = '"+mid+"'";
			try { 
				ps=con.prepareStatement(sql); rs=ps.executeQuery(); 
				if( rs.next() ) return rs.getInt(1) ; 
			}
			catch (Exception e) {} return 0;
		}
		// 회원id 출력 메소드 
		public String getmid( int mno ) {
			String sql = "select ac_id from aacount where ac_no = "+mno;
			try { ps=con.prepareStatement(sql); rs=ps.executeQuery(); 
				if(rs.next() ) return rs.getString( 1 ); 
			}
			catch (Exception e) {} return null;
		}
		
}
