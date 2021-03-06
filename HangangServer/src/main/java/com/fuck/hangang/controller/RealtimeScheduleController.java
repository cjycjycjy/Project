package com.fuck.hangang.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.RealtimeScheduleDao;
import com.fuck.hangang.util.GCMSender;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Controller
public class RealtimeScheduleController {
	@Resource
	RealtimeScheduleDao realtimeScheduleDao;

	@RequestMapping(value = "/registrealtimeschedule", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registRealtimeSchedule(@RequestParam("r_year") int r_year,
			@RequestParam("r_month") int r_month, @RequestParam("r_day") int r_day,
			@RequestParam("start_hour") int start_hour, @RequestParam("start_min") int start_min,
			@RequestParam("t_num") int t_num, @RequestParam("r_lat") double r_lat, @RequestParam("r_lng") double r_lng,
			@RequestParam("r_park") String r_park, @RequestParam("s_num") int s_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			realtimeScheduleDao.registrealtimeSchedule(r_year, r_month, r_day, start_hour, start_min, t_num, r_lat,
					r_lng, r_park);
			realtimeScheduleDao.updateScheduleStatement(s_num);
			
			GCMSender gcmSender = new GCMSender();
			List<RegistrationIdVo> resultRegiVo = realtimeScheduleDao.getRegiIdForPush(s_num);
			gcmSender.sendGCM_aboutRealtimeSchedule(resultRegiVo);
			

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		resultHashMap.put("result", (T) "Success");
		return resultHashMap;
	}

	@RequestMapping(value = "/getrealtimeschedule", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getRealtimeSchedule(@RequestParam("r_year") int r_year,
			@RequestParam("r_month") int r_month, @RequestParam("r_day") int r_day) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<RealtimeScheduleVo> resultRealtimeScheduleVo = realtimeScheduleDao.getRealtimeSchedule(r_year, r_month,
				r_day);
		if (resultRealtimeScheduleVo != null) {
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("realtime", (T) resultRealtimeScheduleVo);
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/endrealtimeschedule", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registRealtimeSchedule(@RequestParam("r_num") int r_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			realtimeScheduleDao.endrealtimeschedule(r_num);
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result",	(T) "Failed");
			return resultHashMap;
		}
		resultHashMap.put("result",	(T) "Success");
		return resultHashMap;
	}
	
	

}
