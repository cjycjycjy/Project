package com.wonjin.sherlockphones.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSender {	
	final String sender = "kidsfarmerapp1@naver.com";
	final String title = "[�ȷ�����]�ӽ� ��й�ȣ �߼��Դϴ�.";
	
	public boolean sendMail(JavaMailSender mailSender, String receiver, String content){
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(receiver);
			messageHelper.setText(content);
			messageHelper.setSubject(title);	// ���������� ������ �����ϴ�
			
			mailSender.send(message);
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}