package com.it15306.servicesImpl;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.it15306.config.ConfigOrder;
import com.it15306.entities.Code_Forgot_Password;
import com.it15306.entities.Product;
import com.it15306.repository.ForgotCodeRepository;
import com.it15306.repository.SkuRepository;

@Service
public class MailServiceImpl {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public MailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Autowired
	private ForgotCodeRepository forgotCodeRepository;
	
	public Integer SendEmailToCustomer(String email) throws MailException, MessagingException {
		
		Random rn = new Random();
		int code = rn.nextInt(999999-100000) + 100000;
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<div>\n"
				+ "<div style=\"display: flex; justify-content: center;\">\n"
				+ "<img src=\"https://scontent-sin6-3.xx.fbcdn.net/v/t1.6435-9/187504051_1217782755359416_5142020989699695036_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=0jXSz-5PUf0AX8p-Fr0&tn=8S6VBRg10QN5-hNs&_nc_ht=scontent-sin6-3.xx&oh=ed0548faf5af1220e28ee61870db5d78&oe=6189FAF1\" alt=\"Girl in a jacket\" width=\"250\" height=\"250\" style=\"border-radius: 25px;\">\n"
				+ "<div/>\n"
				+ "<div style=\"width: 100;padding-left: 50px\">\n"
				+ "<h3 style=\"justify-content: flex-start;color: black;\">Email xác thực danh tính </h3>\n"
				+ "<p style=\"color: black\">Bạn phải sử dụng mã này để thao tác với trang web</p>\n"
				+ "<p style=\"color: black\">Mã của bạn</p>\n"
				+ "<h3 style=\"text-decoration-line: underline;color: red\">"+String.valueOf(code)+"</h3>\n"
				+ "<p style=\"color: black;\">Mọi thắc mắc vui lòng liên hệ:</p>\n"
				+ "<p style=\"color: black;\">Facebook: <a href=\"https://www.facebook.com/mie.quynh.568\" style=\"color: blue\">https://www.facebook.com/mie.quynh.568</a><p/>\n"
				+ "<p style=\"color: black;\">Số điện thoại: 0983141636<p/>\n"
				+ "<div/>\n"
				+ "<div/>";
		//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
		helper.setText(htmlMsg, true); // Use this or above line.
		helper.setTo(email);
		helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
		helper.setFrom("khanhpvph10443@fpt.edu.vn");
		javaMailSender.send(mimeMessage);
		return code;
	}
	
	public void sendMailOrder(String email,Integer status) throws MailException, MessagingException {
		ConfigOrder config = new ConfigOrder();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<div style=\"display: flex; justify-content: center;\">\n"
				+ "        <div style=\"display: flex; justify-content: center;\">\n"
				+ "            <img src=\"https://scontent.fhan3-4.fna.fbcdn.net/v/t1.6435-9/187504051_1217782755359416_5142020989699695036_n.jpg?_nc_cat=106&ccb=1-						5&_nc_sid=174925&_nc_ohc=VKTLxdgzF0AAX9bczEi&tn=8S6VBRg10QN5-									hNs&_nc_ht=scontent.fhan3-4.fna&oh=6422040dcbb57d63c44a3fe505f24b31&oe=61C159F1\"\n"
				+ "                alt=\"Girl in a jacket\" width=\"250\" height=\"250\" style=\"border-radius: 25px;\">\n"
				+ "        </div>\n"
				+ "        <div style=\"width: 100;padding-left: 50px\">\n"
				+ "            <h2 style=\"justify-content: flex-start;color: black;\">Thông báo đơn hàng</h2>\n"
				+ "            <h4 style=\"color: red\">Đơn hàng của bạn " + config.renderStatusOrder(status) + "</h4>\n"
				+ "            <p style=\"color: black;\">Mọi thắc mắc vui lòng liên hệ:</p>\n"
				+ "            <p style=\"color: black;\">\n"
				+ "                Facebook:\n"
				+ "                <a href=\"https://www.facebook.com/mie.quynh.568\"\n"
				+ "                    style=\"color: blue\">https://www.facebook.com/mie.quynh.568</a>\n"
				+ "            </p>\n"
				+ "            <p style=\"color: black;\">Số điện thoại: 0983141636</p>\n"
				+ "        </div>\n"
				+ "    </div>";
		helper.setText(htmlMsg, true); // Use this or above line.
		helper.setTo(email);
		helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
		helper.setFrom("khanhpvph10443@fpt.edu.vn");
		javaMailSender.send(mimeMessage);
	}
	
	public Code_Forgot_Password findByEmail(String email,Integer code) {
//		forgotCodeRepository.findByEmail(email);
		return forgotCodeRepository.findByEmail(email,code);
	}
	
	public Code_Forgot_Password saveCode(Code_Forgot_Password code) {
		return forgotCodeRepository.save(code);
	}
	
	public void delete(Code_Forgot_Password code) {
		 forgotCodeRepository.delete(code);
	}
}
