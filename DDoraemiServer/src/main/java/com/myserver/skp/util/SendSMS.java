package com.myserver.skp.util;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class SendSMS
{
	String api_key = "NCS55FBBF38AB51A";
	String api_secret = "0C9AD6D03BBD7B671362892402E4DCC9";
	Coolsms coolsms = new Coolsms(api_key, api_secret);
	
	public void senSMS(String u_phone, String msg) {	
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", u_phone); // �޴»�� ��ȣ
		set.put("from", "01037798072"); // �����»�� ��ȣ
		set.put("text", msg); // ���ڳ���
		set.put("type", "sms"); // ���� Ÿ��

		JSONObject result = coolsms.send(set); // ������&���۰���ޱ�
		if ((Boolean) result.get("status") == true) {
			// �޽��� ������ ���� �� ���۰�� ���
			System.out.println("����");			
			System.out.println(result.get("group_id")); // �׷���̵�
			System.out.println(result.get("result_code")); // ����ڵ�
			System.out.println(result.get("result_message"));  // ����޽���
			System.out.println(result.get("success_count")); // ��������
			System.out.println(result.get("error_count"));  // �߼۽��� �޽��� ��
		} else {
			// �޽��� ������ ����
			System.out.println("����");
			System.out.println(result.get("code")); // REST API �����ڵ�
			System.out.println(result.get("message")); // �����޽���
		}		
	}

}