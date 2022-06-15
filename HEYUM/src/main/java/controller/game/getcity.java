package controller.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GameboardDao;
import dao.PlayerDao;
import dto.Building;
import dto.Gameboard;



/**
 * Servlet implementation class getcity
 */
@WebServlet("/game/getcity")
public class getcity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getcity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DecimalFormat decimalFormat = new DecimalFormat("#,###원"); 
		response.setCharacterEncoding("UTF-8");
		int location = Integer.parseInt(request.getParameter("p_location"));
		int turncheck = Integer.parseInt(request.getParameter("turncheck"));
		int groom_no = Integer.parseInt(request.getParameter("groom_no"));
		Gameboard gameboard = GameboardDao.gameboardDao.getcityinfo(location);
		ArrayList<Building> blist = GameboardDao.gameboardDao.getbuilding(location, groom_no);
		PrintWriter out = response.getWriter();
		String html = ""; // 응답할 문자열
		int type = Integer.parseInt(request.getParameter("type"));
		if(turncheck==0) {	// 한바퀴 돌기전
			if(type==1) {	// 처음 빈땅에 도착했을때
				if(location==5 || location==15 || location==25 || location==32 || location==39) {
					if(location == 15 || location == 32) {
						html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+"</h4>"
								+"<div class=\"col-md-4 offset-4 card text-center\"><input style=\"margin-left:70px;\" onclick=\"getCheckboxValue()\"  type=\"checkbox\" id=\"buildingclass1\" value=\"1\"><label for=\"buildingclass1\"><span>가격</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
					}else {
						html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+" <img width=\"35px;\" src=\"/HEYUM/img/game/flag/flag"+gameboard.getGb_location()+".png\"></h4>"
								+"<div class=\"col-md-4 offset-4 card text-center\"><input style=\"margin-left:70px;\" onclick=\"getCheckboxValue()\" type=\"checkbox\" id=\"buildingclass1\" value=\"1\"><label for=\"buildingclass1\" ><span>가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
					}
				}else {
					html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+" <img width=\"35px;\" src=\"/HEYUM/img/game/flag/flag"+gameboard.getGb_location()+".png\"></h4><div class=\"row\">"
							+ "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\"  type=\"checkbox\" checked=\"checked\" onclick=\"return false\" id=\"buildingclass1\" value=\"1\"><label ><span>빈땅 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>"
							+ "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass2\" value=\"2\"><label for=\"buildingclass2\"><span>별장 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_villaprice())+"</span><br><span>통행료</span> <br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_villafee())+"</span><br></label></div>"
							+ "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"buildingclass3\" value=\"3\"><label for=\"buildingclass3\"><span>빌딩 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_buildingprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_buildingfee())+"</span><br></label></div>"
							+ "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"buildingclass4\" value=\"4\"><label for=\"buildingclass4\"><span>호텔 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_hotelprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\" >"+decimalFormat.format(gameboard.getGb_hotelfee())+"</span><br></label></div>"
							+ "<span style=\"font-size : 13px; color:red;\">* 한 바퀴를 돌기 전에는 빌딩,호텔을 구매하실 수 없습니다.</span><br>"
							+ "</div>";
				}
				html += "<br><br><div id=\"result\">전체가격 : "+decimalFormat.format(gameboard.getGb_emptyprice())+"</div><input hidden=\"\" id=\"sumprice\">";
			}else {	// 본인이 사놓은 땅에 도착했을때
				// 제주도, 콩고드여객기, 부산, 컬럼비아호, 서울일 경우(건물 종류가 없는경우)
				if(location==5 || location==15 || location==25 || location==32 || location==39) {
					out.print(1); // 구매 불가 메세지 출력
				}else {
					boolean emptycheck = false;
					boolean villacheck = false;
					boolean buildingcheck = false;
					boolean hotelcheck = false;
					
					// 해당위치 건물 리스트안에 구매한 건물종류 체크
					for(Building building : blist) {
						if(building.getBc_no()==1) {emptycheck=true;}
						else if(building.getBc_no()==2) {villacheck=true;}
						else if(building.getBc_no()==3) {buildingcheck=true;}
						else if(building.getBc_no()==4) {hotelcheck=true;}
					}
					// 전부 구매했을 시 더이상 구매불가
					if(emptycheck && villacheck && buildingcheck && hotelcheck) {
						out.print(1); // 구매 불가 메세지 출력
					}else {
						html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+" <img width=\"35px;\" src=\"/HEYUM/img/game/flag/flag"+gameboard.getGb_location()+".png\"></h4><div class=\"row\">";
						// 빈땅 구매여부 판단 후 출력
						if(emptycheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" checked=\"checked\" onclick=\"return false\" id=\"checkedclass1\" value=\"1\"><label ><span>빈땅 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
						}else {
							html += "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\"  type=\"checkbox\" checked=\"checked\" onclick=\"return false\" id=\"buildingclass1\" value=\"1\"><label ><span>빈땅 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
						}
						
						// 별장 구매여부 판단 후 출력
						if(villacheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" checked=\"checked\" onclick=\"return false\" id=\"checkedclass2\" value=\"2\"><label for=\"buildingclass2\"><span>별장 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_villaprice())+"</span><br><span>통행료</span> <br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_villafee())+"</span><br></label></div>";
						}else {
							html += "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass2\" value=\"2\"><label for=\"buildingclass2\"><span>별장 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_villaprice())+"</span><br><span>통행료</span> <br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_villafee())+"</span><br></label></div>";
						}

						// 빌딩 구매여부 판단 후 출력
						if(buildingcheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"checkedclass3\" value=\"3\"><label for=\"buildingclass3\"><span>빌딩 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_buildingprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_buildingfee())+"</span><br></label></div>";
						}else {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"buildingclass3\" value=\"3\"><label for=\"buildingclass3\"><span>빌딩 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_buildingprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_buildingfee())+"</span><br></label></div>";
						}
						
						// 호텔 구매여부 판단 후 출력
						if(hotelcheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"checkedclass4\" value=\"4\"><label for=\"buildingclass4\"><span>호텔 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_hotelprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\" >"+decimalFormat.format(gameboard.getGb_hotelfee())+"</span><br></label></div>";
						}else {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"buildingclass4\" value=\"4\"><label for=\"buildingclass4\"><span>호텔 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_hotelprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\" >"+decimalFormat.format(gameboard.getGb_hotelfee())+"</span><br></label></div>";
						}
						
						html +=  "</div><br><br><span style=\"font-size : 13px; color:red;\">* 한 바퀴를 돌기 전에는 빌딩,호텔을 구매하실 수 없습니다.</span><br>";
						
						html += "<div id=\"result\"></div><input hidden=\"\" id=\"sumprice\">";
					}
					
				}
			}
			
		}else {	// 한바퀴 돌고나서
			if(type==1) {	// 처음 빈땅에 도착했을때
				if(location==5 || location==15 || location==25 || location==32 || location==39) {
					if(location == 15 || location == 32) {
						html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+"</h4>"
								+"<div class=\"col-md-4 offset-4 card text-center\"><input style=\"margin-left:70px;\" onclick=\"getCheckboxValue()\"  type=\"checkbox\" id=\"buildingclass1\" value=\"1\"><label for=\"buildingclass1\"><span>가격</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
					}else {
						html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+" <img width=\"35px;\" src=\"/HEYUM/img/game/flag/flag"+gameboard.getGb_location()+".png\"></h4>"
								+"<div class=\"col-md-4 offset-4 card text-center\"><input style=\"margin-left:70px;\" onclick=\"getCheckboxValue()\" type=\"checkbox\" id=\"buildingclass1\" value=\"1\"><label for=\"buildingclass1\" ><span>가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
					}
				}else {
					html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+" <img width=\"35px;\" src=\"/HEYUM/img/game/flag/flag"+gameboard.getGb_location()+".png\"></h4><div class=\"row\">"
							+ "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\"  type=\"checkbox\" checked=\"checked\" onclick=\"return false\" id=\"buildingclass1\" value=\"1\"><label ><span>빈땅 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>"
							+ "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass2\" value=\"2\"><label for=\"buildingclass2\"><span>별장 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_villaprice())+"</span><br><span>통행료</span> <br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_villafee())+"</span><br></label></div>"
							+ "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass3\" value=\"3\"><label for=\"buildingclass3\"><span>빌딩 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_buildingprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_buildingfee())+"</span><br></label></div>"
							+ "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass4\" value=\"4\"><label for=\"buildingclass4\"><span>호텔 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_hotelprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\" >"+decimalFormat.format(gameboard.getGb_hotelfee())+"</span><br></label></div>"
							+ "</div>";
				}
				html += "<br><br><div id=\"result\">전체가격 : "+decimalFormat.format(gameboard.getGb_emptyprice())+"</div><input hidden=\"\" id=\"sumprice\">";
			}else {	// 본인이 사놓은 땅에 도착했을때
				// 제주도, 콩고드여객기, 부산, 컬럼비아호, 서울일 경우(건물 종류가 없는경우)
				if(location==5 || location==15 || location==25 || location==32 || location==39) {
					out.print(1); // 구매 불가 메세지 출력
				}else {
					boolean emptycheck = false;
					boolean villacheck = false;
					boolean buildingcheck = false;
					boolean hotelcheck = false;
					
					// 해당위치 건물 리스트안에 구매한 건물종류 체크
					for(Building building : blist) {
						if(building.getBc_no()==1) {emptycheck=true;}
						else if(building.getBc_no()==2) {villacheck=true;}
						else if(building.getBc_no()==3) {buildingcheck=true;}
						else if(building.getBc_no()==4) {hotelcheck=true;}
					}
					// 전부 구매했을 시 더이상 구매불가
					if(emptycheck && villacheck && buildingcheck && hotelcheck) {
						out.print(1); // 구매 불가 메세지 출력
					}else {
						html += "<h4 class=\"text-center my-3\">"+gameboard.getGb_cityname()+" <img width=\"35px;\" src=\"/HEYUM/img/game/flag/flag"+gameboard.getGb_location()+".png\"></h4><div class=\"row\">";
						// 빈땅 구매여부 판단 후 출력
						if(emptycheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" disabled=\"disabled\" checked=\"checked\" onclick=\"getCheckboxValue()\" id=\"checkedclass1\" value=\"1\"><label for=\"buildingclass1\"><span>빈땅 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_emptyprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
						}else {
							html += "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\"  type=\"checkbox\" checked=\"checked\" onclick=\"return false\" id=\"buildingclass1\" value=\"1\"><label ><span>빈땅 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyprice()) +"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_emptyfee())+"</span><br></label></div>";
						}
						
						// 별장 구매여부 판단 후 출력
						if(villacheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"checkedclass2\" value=\"2\"><label for=\"buildingclass2\"><span>별장 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_villaprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_villafee())+"</span><br></label></div>";
						}else {
							html += "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass2\" value=\"2\"><label for=\"buildingclass2\"><span>별장 가격</span><br>	<span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_villaprice())+"</span><br><span>통행료</span> <br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_villafee())+"</span><br></label></div>";
						}
						
						// 빌딩 구매여부 판단 후 출력
						if(buildingcheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"checkedclass3\" value=\"3\"><label for=\"buildingclass3\"><span>빌딩 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_buildingprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_buildingfee())+"</span><br></label></div>";
						}else {
							html += "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass3\" value=\"3\"><label for=\"buildingclass3\"><span>빌딩 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_buildingprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\">"+decimalFormat.format(gameboard.getGb_buildingfee())+"</span><br></label></div>";
						}
						
						// 호텔 구매여부 판단 후 출력
						if(hotelcheck) {
							html += "<div style=\"background-color: #eeeeee; color: #999999;\" class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" onclick=\"getCheckboxValue()\" id=\"checkedclass4\" value=\"4\"><label for=\"buildingclass4\"><span>호텔 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_hotelprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\" >"+decimalFormat.format(gameboard.getGb_hotelfee())+"</span><br></label></div>";
						}else {
							html += "<div class=\"col-md-3 card text-center\"><input style=\"margin-left:45px;\" type=\"checkbox\" onclick=\"getCheckboxValue()\" id=\"buildingclass4\" value=\"4\"><label for=\"buildingclass4\"><span>호텔 가격</span><br><span class=\"text-center\" style=\"font-size: 13px;\">"+decimalFormat.format(gameboard.getGb_hotelprice())+"</span><br><span>통행료</span><br><span style=\"font-size: 13px;\" class=\"text-center\" >"+decimalFormat.format(gameboard.getGb_hotelfee())+"</span><br></label></div>";
						}
						
						html += "</div><div id=\"result\"></div><input hidden=\"\" id=\"sumprice\">";
					}
					
				}
			}
		}
		
		
		out.print(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
