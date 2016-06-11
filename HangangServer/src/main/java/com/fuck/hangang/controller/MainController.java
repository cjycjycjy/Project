package com.fuck.hangang.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.MainDao;
import com.fuck.hangang.util.GCMSender;
import com.fuck.hangang.vo.ContestVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.RealtimeScheduleVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamVo;

@Controller
public class MainController {
	@Resource
	private MainDao mainDao;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> main(@RequestParam("year") int year,@RequestParam("month") int month,
			@RequestParam("day") int day) {

	
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<NoticeVo> resultNoticeVo =  mainDao.getnotice();
		for(int i = 0; i < resultNoticeVo.size() ; i++) {
			int count = mainDao.getnoticecommentcount(resultNoticeVo.get(i).getN_num());
			resultNoticeVo.get(i).setN_c_num(count);
		}
		List<ContestVo> resultContestVo = mainDao.getcontest();
		for(int i = 0; i < resultContestVo.size() ; i++) {
			int r_count = mainDao.getContestCommentCount(resultContestVo.get(i).getP_num());
			resultContestVo.get(i).setR_count(r_count);
		}
		
		List<ScheduleVo> resultScheduleVo = mainDao.getschedule(year, month, day);
		List<RealtimeScheduleVo> resultRealtimeScheduleVo = mainDao.getrealtimeschedule();
		if(resultNoticeVo != null){
			resultHashMap.put("result", (T)"Success");
			resultHashMap.put("noticelist", (T)resultNoticeVo);
			resultHashMap.put("contestlist", (T)resultContestVo);
			resultHashMap.put("schedulelist", (T)resultScheduleVo);
			resultHashMap.put("realtimelist", (T)resultRealtimeScheduleVo);
			
			return resultHashMap;

		}else{
			resultHashMap.put("result", (T)"Error");
			return resultHashMap;
		}

	}


}