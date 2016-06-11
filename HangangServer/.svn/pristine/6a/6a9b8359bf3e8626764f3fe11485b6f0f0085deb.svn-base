package com.fuck.hangang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.ScheduleDao;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamVo;
import com.fuck.hangang.vo.UserVo;

@Controller
public class ScheduleController {
	@Resource
	private ScheduleDao scheduleDao;

	@RequestMapping(value = "/getschedule", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getschedule(@RequestParam("year") int year,
			@RequestParam("month") int month) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		int nextmonth = month + 1;
		if (nextmonth == 13) {
			nextmonth = 1;
		}
		List<ScheduleVo> resultScheduleVo = scheduleDao.getschedule(year, month, nextmonth);
		if (resultScheduleVo != null) {
			resultHashMap.put("schedule", (T) resultScheduleVo);
			resultHashMap.put("result", (T) "Sucess");
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/getscheduleofteam", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getscheduleofteam(@RequestParam("t_num") int t_num,
			@RequestParam("s_year") int s_year, @RequestParam("s_month") int s_month) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<ScheduleVo> resultTeamScheduleVo = null;
		int nextmonth = 0;
		if (s_month == 12) {
			nextmonth = 1;
		} else {
			nextmonth = s_month + 1;
		}

		try {
			resultTeamScheduleVo = scheduleDao.getscheduleofteam(t_num, s_year, s_month, nextmonth);
			if (resultTeamScheduleVo != null) {
				resultHashMap.put("result", (T) "Sucess");
				resultHashMap.put("schedulelist", (T) resultTeamScheduleVo);
				return resultHashMap;
			} else {
				resultHashMap.put("result", (T) "Failed");
				return resultHashMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/checkschedule", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> checkschedule(@RequestParam("s_year") int s_year,
			@RequestParam("s_month") int s_month, @RequestParam("s_day") int s_day,
			@RequestParam("start_hour") int start_hour, @RequestParam("start_min") int start_min,
			@RequestParam("end_hour") int end_hour, @RequestParam("end_min") int end_min) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		int st = start_hour * 60 + start_min;
		int et = end_hour * 60 + end_min;
		List<ScheduleVo> resultScheduleVo = scheduleDao.checkschedule(s_year, s_month, s_day);
		List<ScheduleVo> duplicatedScheduleVo = new ArrayList<ScheduleVo>();

		for (int i = 0; i < resultScheduleVo.size(); i++) {
			int my_start_time = resultScheduleVo.get(i).getStart_hour() * 60 + resultScheduleVo.get(i).getStart_min();
			int my_end_time = resultScheduleVo.get(i).getEnd_hour() * 60 + resultScheduleVo.get(i).getEnd_min();

			if (my_start_time < st && my_end_time <= st) {
				continue;
			}

			else if (my_start_time >= et && my_end_time > et) {
				continue;
			} else {
				duplicatedScheduleVo.add(resultScheduleVo.get(i));
			}
		}

		resultHashMap.put("schedule", (T) duplicatedScheduleVo);
		return resultHashMap;

	}

	@RequestMapping(value = "/registschedule", method = RequestMethod.GET)
	public @ResponseBody String registschedule(@ModelAttribute ScheduleVo scheduleVo) {

		List<ScheduleVo> resultScheduleVo = scheduleDao.checklocation(scheduleVo.getS_year(), scheduleVo.getS_month(),
				scheduleVo.getS_day(), scheduleVo.getS_park()); // 같은 공원의 스케쥴 불러옴
		
		int st = scheduleVo.getStart_hour() * 60 + scheduleVo.getStart_min();
		int et = scheduleVo.getEnd_hour() * 60 + scheduleVo.getEnd_min();
		
		for (int i = 0; i < resultScheduleVo.size(); i++) {
			int my_start_time = resultScheduleVo.get(i).getStart_hour() * 60 + resultScheduleVo.get(i).getStart_min();
			int my_end_time = resultScheduleVo.get(i).getEnd_hour() * 60 + resultScheduleVo.get(i).getEnd_min();
			if (!((Math.abs(calDistance(scheduleVo.getS_long(), scheduleVo.getS_lat(),
					resultScheduleVo.get(i).getS_lat(), resultScheduleVo.get(i).getS_long())) < 30) && 
					(((my_start_time < st && my_end_time <= st) || (my_start_time >= et && my_end_time > et)))) ) 
					
			 {
				return "Overlap";
			}
		}
		scheduleDao.registschedule(scheduleVo);
		return "Success";

	}

	@RequestMapping(value = "/deleteschedule", method = RequestMethod.GET)
	public @ResponseBody String deleteSchedule(@RequestParam("s_num") int s_num) {
		try {
			scheduleDao.deleteschedule(s_num);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}

	}

	@RequestMapping(value = "/getscheduleofallteam", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getscheduleofallteam() {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		List<ScheduleVo> resultTeamScheduleVo = new ArrayList<ScheduleVo>();

		try {
			resultTeamScheduleVo = scheduleDao.getscheduleofallteam();
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("teamschedulelist", (T) resultTeamScheduleVo);
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/checktodayschedule", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> checktodayschedule(@RequestParam("t_num") int t_num,
			@RequestParam("s_year") int s_year, @RequestParam("s_month") int s_month,
			@RequestParam("s_day") int s_day) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		ScheduleVo resultScheduleTodayVo = new ScheduleVo();

		resultScheduleTodayVo = scheduleDao.gettodayteamschedule(t_num, s_year, s_month, s_day);

		if (resultScheduleTodayVo == null) {
			RealtimeScheduleVo resultTodayRealtimeScheduleVo = scheduleDao.gettodayrealtimeschedule(t_num, s_year, s_month,
					s_day);
			if (resultTodayRealtimeScheduleVo != null) {
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("realtime", (T) resultTodayRealtimeScheduleVo);
				return resultHashMap;
			} else {
				resultHashMap.put("result", (T) "Failed");
				return resultHashMap;
			}
		} else {
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("schedule", (T) resultScheduleTodayVo);
			return resultHashMap;
		}

	}

	public double calDistance(double lat1, double lon1, double lat2, double lon2) {

		double theta, dist;
		theta = lon1 - lon2;
		dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);

		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344; // 단위 mile 에서 km 변환.
		dist = dist * 1000.0; // 단위 km 에서 m 로 변환

		return dist;
	}

	private double deg2rad(double deg) {
		return (double) (deg * Math.PI / (double) 180d);
	}

	// 주어진 라디언(radian) 값을 도(degree) 값으로 변환
	private double rad2deg(double rad) {
		return (double) (rad * (double) 180d / Math.PI);
	}

}
