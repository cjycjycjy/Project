package com.myserver.skp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.VersionDao;
import com.myserver.skp.vo.VersionVo;

@Controller
public class VersionController {
	@Resource
	private VersionDao versionDao;

	@RequestMapping(value = "/getversion", method = RequestMethod.GET)
	public @ResponseBody String getVersion(@RequestParam("version") String u_version) {
		try{
			VersionVo version = versionDao.getSelectVersion();
			if(version.isCheck()){
				return "check";
			}else if(!version.getVersion().equals(u_version)){
				return "update";
			}else
				return "success";
		}catch(Exception e){
			return "failed";
		}
	}
}
