package com.myserver.skp.util;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.myserver.skp.dao.UserDao;

@Service
public class GCMSender {
	final String API_KEY = "AIzaSyCHNDhsozVX4B4sFvUy6_Z9mpRw5TH3nFY";
	final int retries = 5;
	String register_id;
	String message;

	@Resource
	UserDao userDao;

	public <T> HashMap<String, T> sendGCM(String register_id, String title, String type, String u_id,
	   String p_g_name,
	   int year,
	   int month,
	   int day,
	   int hour,
	   int minute,
	   String p_g_id,
	   String img_url, String message, String get_u_id){
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		Sender sender = new Sender(API_KEY);
		Message msg = new Message.Builder().addData("title", title).addData("type", type)
				.addData("u_id", u_id)
				.addData("p_g_name", p_g_name)
				.addData("year", String.valueOf(year))
				.addData("month", String.valueOf(month))
				.addData("day", String.valueOf(day))
				.addData("hour", String.valueOf(hour))
				.addData("minute", String.valueOf(minute))
				.addData("p_g_id", p_g_id)
				.addData("img_url", img_url)
				.addData("get_u_id", get_u_id)
				.addData("message", message).build();
		try {

			Result result = sender.send(msg, register_id, retries);

			if (StringUtils.isEmpty(result.getErrorCodeName())) {
				System.out.println(msg.toString() + "GCM Notification is sent successfully" + result.toString());
				//userDao.updateRegisterId(register_id, register_id);
				if(!StringUtils.isEmpty(result.getCanonicalRegistrationId())){
					String new_register_id = result.getCanonicalRegistrationId();
					System.out.println(new_register_id);
					resultHashMap.put("new_register_id", (T)new_register_id);
					//userDao.updateRegisterId(register_id, new_register_id);
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
