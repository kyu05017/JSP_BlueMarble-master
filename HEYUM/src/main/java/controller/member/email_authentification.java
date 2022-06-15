package controller.member;

import java.util.Random;

import java.util.Properties;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import dao.Member_Dao;
import dto.Account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class email_authentification
 */
@WebServlet("/member/emailauthentification")
public class email_authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public email_authentification() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session2 = request.getSession();
		String email = request.getParameter("email");
		
		String host = "smtp.....";
		String user = "email.com"; 
		String password = "1234";
		
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
			msg.setSubject("안녕하세요. 마블 해윰 인증 메일입니다.");
			// 메일 내용
			//msg.setText("인증 번호는 :" + temp);
			

			msg.setContent(
					"<div style=\"margin: 0 auto; height:500px; \">"+
			        "<div style=\"border-top: 5px solid #56FAB4; margin-top: 10px;\"></div>"+
			        "<br>"+
			        "<h1 style=\"text-align:center\">마블 해윰</h1><br><h4> 안녕하세요 </h4><h4> 마블해윰을 이용해 주셔서 감사합니다. </h4><h4> 아래의 인증번호로 인증을 완료해 주시몁 됩니다. </h4><br>"+
			        "<h3 style=\"margin: 0 auto;text-align:center\">이메일 인증번호 : <label style=\"color:blue\">"+temp +"</label></h3>"+
			        "<div style=\"border-top: 5px solid #56FAB4; margin-top: 50px;\"></div>"+
			        "</div>"
					
					, "text/html;charset=UTF-8");
			
			Transport.send(msg);
			
			boolean result = Member_Dao.m_dao.email_authentification(to_email, temp+"");

		} catch (Exception e) {
			System.out.println("메일 전송오류 : " + e);
			
		}

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
