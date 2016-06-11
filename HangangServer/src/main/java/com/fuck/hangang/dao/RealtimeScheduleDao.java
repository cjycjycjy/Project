package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.RealtimeScheduleMapper;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Service
public class RealtimeScheduleDao {
	@Resource
	private RealtimeScheduleMapper mapper;

	public void registrealtimeSchedule(int r_year, int r_month,
			int r_day, int start_hour, int start_min,
			int t_num, double r_lat, double r_lng,
			String r_park) {
			this.mapper.registrealtimeSchedule(r_year, r_month, r_day, start_hour, start_min, t_num, r_lat, r_lng, r_park);
	}

	public void updateScheduleStatement(int s_num) {
		this.mapper.updateScheduleStatement(s_num);
	}

	public List<RealtimeScheduleVo> getRealtimeSchedule(int r_year, int r_month, int r_day) {
		return this.mapper.getRealtimeSchedule(r_year, r_month, r_day);
	}

	public void endrealtimeschedule(int r_num) {
		this.mapper.endrealtimeschedule(r_num);
	}
	
	public List<RegistrationIdVo> getRegiIdForPush(int s_num){
		return this.mapper.getRegiIdForPush(s_num);
	}
}
