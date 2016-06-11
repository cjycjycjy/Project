package com.wonjin.sherlockphones.util;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.wonjin.sherlockphones.dao.UserDao;

@Service
public class GCMSender {
	final String API_KEY = "AIzaSyAiVSANPLXMCYsn8uk8R1z8cbak3vLViwI";
	final int retries = 5;
	String register_id;

	@Resource
	UserDao userDao;

	public <T> HashMap<String, T> sendGCM(String register_id, String message){
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		Sender sender = new Sender(API_KEY);
		Message msg = new Message.Builder()
				.addData("message", message).build();
		try {

			Result result = sender.send(msg, register_id, retries);

			if (StringUtils.isEmpty(result.getErrorCodeName())) {
				System.out.println(msg.toString() + "GCM Notification is sent successfully" + result.toString());
				if(!StringUtils.isEmpty(result.getCanonicalRegistrationId())){
					String new_register_id = result.getCanonicalRegistrationId();
					System.out.println(new_register_id);
					resultHashMap.put("new_register_id", (T)new_register_id);
				}
				resultHashMap.put("result", (T)"Success");
				return resultHashMap;
			}

			System.out.println("Error occurred while sending push notification :" + result.getErrorCodeName());

		} catch (InvalidRequestException e) {
			System.out.println("Invalid Request");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		resultHashMap.put("result", (T)"Failed");
		return resultHashMap;
	}

}
