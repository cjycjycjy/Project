package com.myserver.skp.util;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class RegisterSender {
	public static void main(String[] args) {
		/*
		 * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
		 */
		String api_key = "NCS55FBBF38AB51A";
		String api_secret = "0C9AD6D03BBD7B671362892402E4DCC9";
		Coolsms coolsms = new Coolsms(api_key, api_secret);
	
		/*
		 * Parameters
		 * 관련정보 : http://www.coolsms.co.kr/SenderID_API#POSTregister
		 */
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("phone", "01037798072"); // 등록할 발신번호
		
		JSONObject result = coolsms.register(set); // 발신번호 등록요청
		if ((Boolean) result.get("status") == true) {
			// 성공 및 전송결과 출력
			System.out.println("성공");			
			System.out.println(result.toString());
			System.out.println(result.get("handle_key")); // 그룹아이디
			System.out.println(result.get("ars_number")); // 결과코드
		} else {
			// 실패
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
		}		
	}	
}
