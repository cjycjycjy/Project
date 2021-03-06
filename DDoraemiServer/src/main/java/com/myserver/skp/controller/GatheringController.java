package com.myserver.skp.controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.GatheringDao;
import com.myserver.skp.dao.UserDao;
import com.myserver.skp.util.GCMSender;
import com.myserver.skp.vo.GatheringVo;

@Controller
public class GatheringController {
	private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Resource
	private GatheringDao gatheringDao;
	
	@Resource
	UserDao userDao;

	@RequestMapping(value = "/getgathering", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getGatheringAllofProgram(@RequestParam("p_id") int p_id) {
		logger.info("This is Test");
		List<HashMap<String, String>> list = this.gatheringDao.getSelectAllofGathering(p_id);
		return list;
	}

	@RequestMapping(value = "/getgatheringavailable", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getGatheringAvailable() {
		List<HashMap<String, String>> list = this.gatheringDao.getSelectIsAvailable();
		return list;
	}

	@RequestMapping(value = "/registergathering", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registerGathering(@ModelAttribute("GatheringVo") GatheringVo gatheringVo) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<HashMap<String, String>> gatheringList = null;
		try{
			int p_lead_time = 60*(gatheringVo.getG_end_hour()-gatheringVo.getG_start_hour())
					-(gatheringVo.getG_end_minute()-gatheringVo.getG_start_minute());
			gatheringDao.insertGathering(gatheringVo);
			gatheringDao.insertJoinGathering(gatheringVo.getG_id(), gatheringVo.getU_id(), 1);
			gatheringList = this.gatheringDao.getSelectAllofGathering(gatheringVo.getP_id());
			
			GregorianCalendar today = new GregorianCalendar();
			int year = today.get ( today.YEAR );
			int month = today.get ( today.MONTH ) + 1;
			int day = today.get ( today.DAY_OF_MONTH ); 
			int hour = today.get(today.HOUR_OF_DAY);
			int minute = today.get(today.MINUTE);
			
			GCMSender gcmSender = new GCMSender();
			List<HashMap<String, String>> gcmBookmark = userDao.getSelectGCMBookmark(gatheringVo.getP_id());
			for(int i=0; i<gcmBookmark.size(); i++){
				HashMap<String, T> resultHashMapGcm = null;
				resultHashMapGcm = gcmSender.sendGCM(gcmBookmark.get(i).get("u_register_id"), "KIDS FARMER","b","", "["+gcmBookmark.get(i).get("p_name")+"]",year, month, day, hour, minute, String.valueOf(gatheringVo.getG_id()), gcmBookmark.get(i).get("p_photo_url"), "회원님이 북마크한"+"["+gcmBookmark.get(i).get("p_name")+"]"+" 관련 모임이 생성되었습니다.", gcmBookmark.get(i).get("u_id"));
				if(resultHashMapGcm.get("result").equals("Success") && resultHashMapGcm.containsKey("new_register_id")){
					userDao.updateRegisterId(gcmBookmark.get(i).get("u_register_id"), String.valueOf(resultHashMap.get("new_register_id")));
				}
			}
			
			resultHashMap.put("result", (T)"200");
			resultHashMap.put("gathering", (T)gatheringList);
		}catch(Exception ie){
			resultHashMap.put("result", (T)"500");
		}

		return resultHashMap;
	}

	@RequestMapping(value = "/joingathering", method = RequestMethod.GET)
	public @ResponseBody String joinGathering(@RequestParam("g_id") int g_id, @RequestParam("u_id") String u_id, @RequestParam("persons") int j_g_persons) {
		try{

			gatheringDao.insertJoinGathering(g_id, u_id, j_g_persons);
			gatheringDao.updateJonGatheringNumAdd(g_id, j_g_persons);
			return "200";
		}catch(Exception e){
			return "500";
		}
	}
	
	@RequestMapping(value = "/getgatheringone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getGatheringOne(@RequestParam("g_id") int g_id) {
		HashMap<String, T> result = new HashMap<String, T>();
		try{

			HashMap<String, T> gathering = gatheringDao.getSelectGathering(g_id);
			result.put("result", (T)"200");
			result.put("gatheing", (T)gathering);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}
	
	@RequestMapping(value = "/outgathering", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> outGathering(@RequestParam("g_id") int g_id, @RequestParam("u_id") String u_id) {
		HashMap<String, String> result = new HashMap<String, String>();
		try{
			int j_g_persons = gatheringDao.getSelectJoinGatheringPersons(g_id, u_id);
			gatheringDao.deleteJoinGathering(g_id, u_id);
			gatheringDao.updateJonGatheringNumSub(g_id, j_g_persons);
			result.put("result", "200");
			result.put("persons", String.valueOf(j_g_persons));
		}catch(Exception e){
			result.put("result", "500");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/deletegathering", method = RequestMethod.GET)
	public @ResponseBody String deleteGathering(@RequestParam("g_id") int g_id) {
		HashMap<String, String> result = new HashMap<String, String>();
		try{
			gatheringDao.deleteJoinGatheringAll(g_id);
			gatheringDao.deleteGathering(g_id);
			return "200";
		}catch(Exception e){
			return "500";
		}

	}
	


}