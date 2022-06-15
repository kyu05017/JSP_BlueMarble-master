package controller.member;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;

/**
 * Servlet implementation class findid
 */
@WebServlet("/member/findpw")
public class findpw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public findpw() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");

	
		if(type.equals("email")) {
			String email2= request.getParameter("email");
			
			boolean id = Member_Dao.m_dao.email_find2(email2);
			
			
			if(id) {
				
				
				String host = "smtp.naver.com";
				String user = "kyu0501@naver.com"; // 자신의 네이버 계정
				String password = "RlaRla4339";// 자신의 네이버 패스워드
				
				String to_email = email2;
				
				Properties props = new Properties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", 465);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.debug", "true");
				
				StringBuffer temp = new StringBuffer();
				Random rnd = new Random();
				for (int i = 0; i < 10; i++) {
					int rIndex = rnd.nextInt(3);
					switch (rIndex) {
					case 0:
						// a-z
						temp.append((char) ((int) (rnd.nextInt(26)) + 97));
						break;
					case 1:
						// A-Z
						temp.append((char) ((int) (rnd.nextInt(26)) + 65));
						break;
					case 2:
						// 0-9
						temp.append((rnd.nextInt(10)));
						break;
					}
				}
				String AuthenticationKey = temp.toString();
				

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
				MimeMessage msg = new MimeMessage(session);
				try {
					msg.setFrom(new InternetAddress(user));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

					// 메일 제목
					msg.setSubject("안녕하세요. 마블 해윰 임시 비밀번호 발급 입니다.");
					// 메일 내용
					//msg.setText("인증 번호는 :" + temp);
					

					msg.setContent(
							"<div style=\"margin: 0 auto; height:500px; \">"+
					        "<div style=\"border-top: 5px solid #56FAB4; margin-top: 10px;\"></div>"+
					        "<br>"+
					        "<h1 style=\"text-align:center\">마블 해윰</h1><br><h4> 안녕하세요 </h4><h4> 마블해윰을 이용해 주셔서 감사합니다. </h4><h4> 임시 비밀번호를 발급 하였습니다 빠른 시일안에 변경해주시길 바랍니다.. </h4><br>"+
					        "<h3 style=\"margin: 0 auto;text-align:center\">임시 비밀번호 : <label style=\"color:blue\">"+temp +"</label></h3>"+
					        "<div style=\"border-top: 5px solid #56FAB4; margin-top: 50px;\"></div>"+
					        "</div>"
							
							, "text/html;charset=UTF-8");
					
					Transport.send(msg);
					
					MessageDigest md = MessageDigest.getInstance("SHA-512");
					md.update(temp.toString().getBytes());
					String hex = String.format("%0128x", new BigInteger(1, md.digest()));
					
					boolean result = Member_Dao.m_dao.email_instance_pw(hex, email2);
					if(result) {
						response.getWriter().print(1);
						return;
					}
				} catch (Exception e) {
					System.out.println("메일 전송오류 : " + e);
					
				}
			}
			else {
				response.getWriter().print(2);
			}
			return;
		}
		if(type.equals("phone")) {
			String phone= request.getParameter("phone");
			
			String email = Member_Dao.m_dao.phone_find2(phone);	
			
			if(email != null) {
				
				String host = "smtp.naver.com";
				String user = "kyu0501@naver.com"; // 자신의 네이버 계정
				String password = "RlaRla4339";// 자신의 네이버 패스워드
				
				String to_email = email;
				
				Properties props = new Properties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", 465);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.debug", "true");
				
				StringBuffer temp = new StringBuffer();
				Random rnd = new Random();
				for (int i = 0; i < 10; i++) {
					int rIndex = rnd.nextInt(3);
					switch (rIndex) {
					case 0:
						// a-z
						temp.append((char) ((int) (rnd.nextInt(26)) + 97));
						break;
					case 1:
						// A-Z
						temp.append((char) ((int) (rnd.nextInt(26)) + 65));
						break;
					case 2:
						// 0-9
						temp.append((rnd.nextInt(10)));
						break;
					}
				}
				String AuthenticationKey = temp.toString();
				

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
				MimeMessage msg = new MimeMessage(session);
				try {
					msg.setFrom(new InternetAddress(user));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

					// 메일 제목
					msg.setSubject("안녕하세요. 마블 해윰 임시 비밀번호 발급 입니다.");
					// 메일 내용
					//msg.setText("인증 번호는 :" + temp);
					

					msg.setContent(
							"<div style=\"margin: 0 auto; height:500px; \">"+
					        "<div style=\"border-top: 5px solid #56FAB4; margin-top: 10px;\"></div>"+
					        "<br>"+
					        "<h1 style=\"text-align:center\">마블 해윰</h1><br><h4> 안녕하세요 </h4><h4> 마블해윰을 이용해 주셔서 감사합니다. </h4><h4> 임시 비밀번호를 발급 하였습니다 빠른 시일안에 변경해주시길 바랍니다.. </h4><br>"+
					        "<h3 style=\"margin: 0 auto;text-align:center\">임시 비밀번호 : <label style=\"color:blue\">"+temp +"</label></h3>"+
					        "<div style=\"border-top: 5px solid #56FAB4; margin-top: 50px;\"></div>"+
					        "</div>"
							
							, "text/html;charset=UTF-8");
					
					Transport.send(msg);
					
					MessageDigest md = MessageDigest.getInstance("SHA-512");
					md.update(temp.toString().getBytes());
					String hex = String.format("%0128x", new BigInteger(1, md.digest()));
					
					boolean result = Member_Dao.m_dao.email_instance_pw(hex, email);
					if(result) {
						response.getWriter().print(1);
						return;
					}
				} catch (Exception e) {
					System.out.println("메일 전송오류 : " + e);
					
				}
				
			}
			else {
				response.getWriter().print(2);
			}
			return;
		}
		
	}

}
