package com.myserver.skp.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSender {	
	final String receiver = "kidsfarmerapp1@naver.com";
	final String title = "[Ű���ĸ�]����� ���� �����Դϴ�.";
	
	public boolean sendMail(JavaMailSender mailSender, String sender, String content){
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(receiver);
			messageHelper.setText(content);
			messageHelper.setFrom(sender);
			messageHelper.setSubject(title);	// ���������� ������ �����ϴ�
			
			mailSender.send(message);
		} catch(Exception e){
			return false;
		}
		
		return true;
	}
}
