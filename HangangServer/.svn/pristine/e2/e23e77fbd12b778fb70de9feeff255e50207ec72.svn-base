package com.fuck.hangang.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSender {	
	final String sender = "dlcjfgjs321@naver.com";
	final String title = "[한강 거리공연 예술가]임시 비밀번호 발송입니다.";
	
	public boolean sendMail(JavaMailSender mailSender, String receiver, String content){
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(receiver);
			messageHelper.setText(content);
			messageHelper.setSubject(title);	// 메일제목은 생략이 가능하다
			
			mailSender.send(message);
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
