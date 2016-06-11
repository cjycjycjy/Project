package com.wonjin.sherlockphones.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonjin.sherlockphones.dao.LocationDao;
import com.wonjin.sherlockphones.vo.LocationVo;

@Controller
public class LocationController {
	@Resource
	private LocationDao locationDao;
	
	@RequestMapping(value = "/registerlocation", method = RequestMethod.POST)
	public @ResponseBody String registerLocation(@RequestParam("u_phonenum") String u_phonenum, @RequestParam("l_long") double l_long, @RequestParam("l_lat") double l_lat) {
		try {
			locationDao.registerLocation(u_phonenum, l_long, l_lat);
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		
		return "Success";
	}
	
	@RequestMapping(value = "/getlocation", method = RequestMethod.POST)
	public @ResponseBody<T> HashMap<String, T> selectLocation(@RequestParam("u_phonenum") String u_phonenum) {
			HashMap<String, T> resultHasmMap = new HashMap<String, T>();
			try{
				List<LocationVo> location = this.locationDao.selectLocation(u_phonenum);
				resultHasmMap.put("result", (T)"Success");
				resultHasmMap.put("location", (T)location);
			}catch(Exception e){
				resultHasmMap.put("result", (T)"Failed");
			}finally {
				return resultHasmMap;
			}
			
	}
}
