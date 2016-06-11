package com.myserver.skp.scheduler;

import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myserver.skp.dao.GatheringDao;


@Component
public class ConfirmGatheringScheduler {
	
	@Resource
	GatheringDao gatherinDao;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void updateGatheringStatus(){
		GregorianCalendar today = new GregorianCalendar();
		int year = today.get ( today.YEAR );
		int month = today.get ( today.MONTH ) + 1;
		int day = today.get ( today.DAY_OF_MONTH );
		
		gatherinDao.updateGathering(year, month, day);
	}
}
