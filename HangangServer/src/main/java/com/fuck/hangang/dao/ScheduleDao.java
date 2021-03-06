package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.ScheduleMapper;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamVo;

@Service
public class ScheduleDao {
	@Resource
	ScheduleMapper mapper;

	public List<ScheduleVo> getschedule(int year, int month, int nextmonth) {
		return this.mapper.getschedule(year, month, nextmonth);
	}

	public List<ScheduleVo> checkschedule(int s_year, int s_month, int s_day) {
		return this.mapper.checkschedule(s_year, s_month, s_day);
	}

	public void registschedule(ScheduleVo scheduleVo) {
		this.mapper.registschedule(scheduleVo);
	}

	public List<ScheduleVo> checklocation(int s_year, int s_month, int s_day, String s_park) {
		return this.mapper.checklocation(s_year, s_month, s_day, s_park);
	}

	public void deleteschedule(int s_num) {
		this.mapper.deleteschedule(s_num);
	}

	public List<ScheduleVo> getscheduleofteam(@Param("t_num") int t_num, @Param("s_year") int s_year,
			@Param("s_month") int s_month, @Param("nextmonth") int nextmonth) {
		return this.mapper.getscheduleofteam(t_num, s_year, s_month, nextmonth);
	}

	public List<ScheduleVo> getscheduleofallteam() {
		return this.mapper.getscheduleofallteam();
	}

	public ScheduleVo gettodayteamschedule(@Param("t_num") int t_num, @Param("s_year") int s_year,
			@Param("s_month") int s_month, @Param("s_day") int s_day) {
		return this.mapper.gettodayteamschedule(t_num, s_year, s_month, s_day);
	}

	public RealtimeScheduleVo gettodayrealtimeschedule(@Param("t_num") int t_num, @Param("s_year") int s_year,
			@Param("s_month") int s_month, @Param("s_day") int s_day) {
		return this.mapper.gettodayrealtimeschedule(t_num, s_year, s_month, s_day);
	}


}
