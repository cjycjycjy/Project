package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.ContestPhotoVo;
import com.fuck.hangang.vo.ContestVo;
import com.fuck.hangang.vo.NoticePhotoVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.ScheduleForPushMessageVo;
import com.fuck.hangang.vo.ScheduleVo;

@Repository
public interface MainMapper {

	public List<NoticeVo> getnotice();
	public List<ContestVo> getcontest();
	public List<ScheduleVo> getschedule(@Param("s_year") int s_year,@Param("s_month") int s_month,@Param("s_day") int s_day);
	public List<RealtimeScheduleVo> getrealtimeschedule();
	public int getnoticecommentcount(@Param("n_num") int n_num);
	public int getContestCommentCount(@Param("num") int num);
	public List<ScheduleForPushMessageVo> getTodayScheduleForGcm(@Param("year") int year, @Param("month") int month, @Param("day") int day);
	public void updateRealtimeScheduleStatus(@Param("r_s_year") int r_s_year, @Param("r_s_month") int r_s_month, @Param("r_s_day") int r_s_day);
}
