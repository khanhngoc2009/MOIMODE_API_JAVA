package com.it15306.controller.client;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.EmailDto;
import com.it15306.entities.User;
import com.it15306.services.UserServiceImpl;
import com.it15306.servicesImpl.MailServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class SendMail {
//	@Autowired MailServiceImpl mailServiceImpl;
//	@Autowired UserServiceImpl userServiceImpl;
	
//	@RequestMapping(value = "/send_mail/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	   public EmailDto toSendEmail(@PathVariable String email){
//			EmailDto eBody= new EmailDto();
//			User user = userServiceImpl.getByEmail(email);
//			eBody.setEmail(email);
//			if(user!= null) {
//				try {
//					int code = mailServiceImpl.SendEmailToCustomer(email);
//					eBody.setMessage("Đã gửi mã đến email " + email);
//					eBody.setCode(code);
//					return eBody;
//				} catch (MailException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					eBody.setMessage("Fail");
//					return eBody;
//				} catch (MessagingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					eBody.setMessage("Fail");
//					return eBody;
//				}
//			}else {
//				eBody.setMessage("Email không tồn tại, vui lòng kiểm tra ");
//				return eBody;
//			}
//			
////			return "Email sent successfully";
//	   }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
