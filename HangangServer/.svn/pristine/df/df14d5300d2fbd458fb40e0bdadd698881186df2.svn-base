package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.MainMapper;
import com.fuck.hangang.vo.ContestVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.ScheduleForPushMessageVo;
import com.fuck.hangang.vo.ScheduleVo;

@Service
public class MainDao {
	@Resource
	MainMapper mapper;
	
	public List<NoticeVo> getnotice(){
		return this.mapper.getnotice();
	}
	
	public List<ContestVo> getcontest(){
		return this.mapper.getcontest();
	}
	
	public List<ScheduleVo> getschedule(int s_year, int s_month, int s_day){
		return this.mapper.getschedule(s_year, s_month, s_day);
	}
	public List<RealtimeScheduleVo> getrealtimeschedule(){
		return this.mapper.getrealtimeschedule();
	}
	
	public int getnoticecommentcount(@Param("n_num") int n_num) {
		return this.mapper.getnoticecommentcount(n_num);
	}
	public int getContestCommentCount(int num){
		return this.mapper.getContestCommentCount(num);
	}
	public List<ScheduleForPushMessageVo> getTodayScheduleForGcm(int year, int month, int day){
		return this.mapper.getTodayScheduleForGcm(year, month, day);
	}
	public void updateRealtimeScheduleStatus(int r_s_year, int r_s_month, int r_s_day){
		this.mapper.updateRealtimeScheduleStatus(r_s_year, r_s_month, r_s_day);
	}
}
