package com.fuck.hangang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.PushDao;
import com.fuck.hangang.vo.PushVo;

@Controller
public class PushController {
	@Resource
	PushDao pushDao;

	@RequestMapping(value = "/getuserpush", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getuserpush(@RequestParam("u_id") String u_id,
			@RequestParam("u_permission") int u_permission, @RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<PushVo> resultPushVo = new ArrayList<PushVo>();
		try {
			if (lastnum == -1) {
				switch (u_permission) {
				case 1:
					resultPushVo = pushDao.getuserpushPermission1First(u_id);
					break;
				case 2:
					resultPushVo = pushDao.getuserpushPermission2First(u_id);
					break;
				case 3:
					resultPushVo = pushDao.getuserpushPermission3First(u_id);
					break;
				default:
					resultHashMap.put("result", (T) "Failed");
					break;
				}
			}else {
				switch (u_permission) {
				case 1:
					resultPushVo = pushDao.getuserpushPermission1Last(u_id, lastnum);
					break;
				case 2:
					resultPushVo = pushDao.getuserpushPermission2Last(u_id, lastnum);
					break;
				case 3:
					resultPushVo = pushDao.getuserpushPermission3Last(u_id, lastnum);
					break;
				default:
					resultHashMap.put("result", (T) "Failed");
					break;
				}
			}
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("pushlist", (T) resultPushVo);
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

}
