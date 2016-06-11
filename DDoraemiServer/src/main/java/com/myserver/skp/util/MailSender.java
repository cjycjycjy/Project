package com.myserver.skp.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSender {	
	final String receiver = "kidsfarmerapp1@naver.com";
	final String title = "[키즈파머]담당자 인증 메일입니다.";
	
	public boolean sendMail(JavaMailSender mailSender, String sender, String content){
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(receiver);
			messageHelper.setText(content);
			messageHelper.setFrom(sender);
			messageHelper.setSubject(title);	// 메일제목은 생략이 가능하다
			
			mailSender.send(message);
		} catch(Exception e){
			return false;
		}
		
		return true;
	}
}
