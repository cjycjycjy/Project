package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamVo;

@Repository
public interface ScheduleMapper {
	public List<ScheduleVo> getschedule(@Param("year") int year, @Param("month") int month,
			@Param("nextmonth") int nextmonth);

	public List<ScheduleVo> checkschedule(@Param("s_year") int s_year, @Param("s_month") int s_month,
			@Param("s_day") int s_day);

	public void registschedule(ScheduleVo scheduleVo);

	public List<ScheduleVo> checklocation(@Param("s_year") int s_year, @Param("s_month") int s_month,
			@Param("s_day") int s_day, @Param("s_park") String s_park);

	public void deleteschedule(@Param("s_num") int s_num);

	public List<ScheduleVo> getscheduleofteam(@Param("t_num") int t_num, @Param("s_year") int s_year,
			@Param("s_month") int s_month, @Param("nextmonth") int nextmonth);

	public List<ScheduleVo> getscheduleofallteam();

	public ScheduleVo gettodayteamschedule(@Param("t_num") int t_num, @Param("s_year") int s_year,
			@Param("s_month") int s_month, @Param("s_day") int s_day);

	public RealtimeScheduleVo gettodayrealtimeschedule(@Param("t_num") int t_num, @Param("s_year") int s_year,
			@Param("s_month") int s_month, @Param("s_day") int s_day);

	
}
