package controller.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.GameboardDao;

/**
 * Servlet implementation class getmycity
 */
@WebServlet("/game/getmycity")
public class getmycity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getmycity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			DecimalFormat decimalFormat = new DecimalFormat("#,###원"); 
			response.setCharacterEncoding("UTF-8");
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			// 내 도시목록 가져오기
			JSONArray jsonArray = GameboardDao.gameboardDao.getmycity(p_no, groom_no);
			if(jsonArray.isNull(0)) {
				response.getWriter().print(0);
			}else {
				String html = "";
				PrintWriter out = response.getWriter();
				String cityname = "";	// 도시 이름 저장용 변수
				html += "<table class=\"table\">"+
						"<th class=\"text-center\">전체선택<br><input class=\"form-check-input\" type=\"checkbox\" id=\"citycheck\" name=\"citycheck\" onclick=\"selectAll(this)\"></th>"+
						"<th>도시이름</th>"+
						"<th>판매가격</th>"+
						"<th>건설된 건물</th>";
				JSONArray jsonArray2 = new JSONArray();	
				for(int i=0; i<jsonArray.length(); i++) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("gb_location", jsonArray.getJSONObject(i).getInt("gb_location"));
					jsonObject.put("cityname", jsonArray.getJSONObject(i).getString("cityname"));
					int bc_no = jsonArray.getJSONObject(i).getInt("bc_no");
					if(cityname.equals(jsonArray.getJSONObject(i).getString("cityname"))) {
						if(bc_no==1) {html += "빈땅　";}
						else if(bc_no==2) {html += "별장　";}
						else if(bc_no==3) {html += "빌딩　";}
						else if(bc_no==4) {html += "호텔　";}
						
					}else {
						jsonArray2.put(jsonObject);
						int sellmoney = GameboardDao.gameboardDao.sellcity(jsonArray.getJSONObject(i).getInt("gb_location"), groom_no);
						if(!cityname.equals("")) {
							html += "</td></tr>";										
						}
						html += "<tr><td class=\"text-center\"><input class=\"form-check-input\" id=\"citycheck"+i+"\" type=\"checkbox\" name=\"citycheck\" value=\""+jsonArray.getJSONObject(i).getInt("gb_location")+"\" onclick=\"check(this,'"+jsonArray.getJSONObject(i).getString("cityname")+"')\"></td>"
								+"<td><label for=\"citycheck"+i+"\">"+jsonArray.getJSONObject(i).getString("cityname")+"</label></td>"
								+"<td>"+decimalFormat.format(sellmoney)+"</td>";
						if(bc_no==1) {html += "<td>빈땅　";}
						else if(bc_no==2) {html += "<td>별장　";}
						else if(bc_no==3) {html += "<td>빌딩　";}
						else if(bc_no==4) {html += "<td>호텔　";}
					}
					if(i==jsonArray.length()-1) {
						html+="</td></tr>";
					}
					cityname = jsonArray.getJSONObject(i).getString("cityname");
				}
				html += "</table>"
						+"<input type=\"hidden\" id=\"citylist\">"
						+"<input type=\"hidden\" id=\"sumsell\">"
						+"<span id=\"selltext\"></span><span id=\"sumsellmoney\"></span>";
				out.print(html+"@@"+jsonArray2);
			}
			
		}catch(Exception e) {e.printStackTrace();}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
