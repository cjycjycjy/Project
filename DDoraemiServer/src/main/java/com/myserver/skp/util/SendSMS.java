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
		set.put("to", u_phone); // 받는사람 번호
		set.put("from", "01037798072"); // 보내는사람 번호
		set.put("text", msg); // 문자내용
		set.put("type", "sms"); // 문자 타입

		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		if ((Boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");			
			System.out.println(result.get("group_id")); // 그룹아이디
			System.out.println(result.get("result_code")); // 결과코드
			System.out.println(result.get("result_message"));  // 결과메시지
			System.out.println(result.get("success_count")); // 성공갯수
			System.out.println(result.get("error_count"));  // 발송실패 메시지 수
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
		}		
	}

}