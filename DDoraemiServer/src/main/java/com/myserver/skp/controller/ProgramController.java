package com.myserver.skp.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.GatheringDao;
import com.myserver.skp.dao.ProgramDao;
import com.myserver.skp.dao.UserDao;
import com.myserver.skp.mapper.UserMapper;
import com.myserver.skp.util.GCMSender;
import com.myserver.skp.vo.ProgramVo;

@Controller
public class ProgramController {
	private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Resource
	private ProgramDao programDao;
	@Resource
	private GatheringDao gatheringDao;
	@Resource
	private UserDao userDao;

	@RequestMapping(value = "/getprogram", params="!category")
	public @ResponseBody <T> HashMap<String, T> getProgram(@RequestParam("u_id") String u_id){
	//public @ResponseBody <T> HashMap<String, T> getProgram(){
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		//List<ProgramVo> programList = this.programDao.getSelectAll();
		List<ProgramVo> programList = this.programDao.getSelectRecommend(u_id);
		List<HashMap<String, String>> gatheringList = this.gatheringDao.getSelectIsAvailable();

		for(int i=0; i<programList.size(); i++){
			if(programList.get(i).getP_addr_1()==null){
				programList.get(i).setP_addr_1("");
			}
			if(programList.get(i).getP_addr_2()==null){
				programList.get(i).setP_addr_2("");
			}
			if(programList.get(i).getP_addr_3()==null){
				programList.get(i).setP_addr_3("");
			}
			if(programList.get(i).getP_addr_4()==null){
				programList.get(i).setP_addr_4("");
			}
			if(programList.get(i).getP_addr_5()==null){
				programList.get(i).setP_addr_5("");
			}
			if(programList.get(i).getP_addr_6()==null){
				programList.get(i).setP_addr_6("");
			}

			if(programList.get(i).getP_note()==null){
				programList.get(i).setP_note("");
			}

			programList.get(i).setP_curriculum(programDao.getSelectCurriculum(programList.get(i).getP_id()));
		}
		
		resultHashMap.put("program", (T)programList);
		resultHashMap.put("gathering", (T)gatheringList);

		return resultHashMap;

	}


	@RequestMapping(value = "/getprogram", method = RequestMethod.GET)
	public @ResponseBody List<ProgramVo> getProgramCategory(@RequestParam("category") int category, HttpServletRequest req){
		List<ProgramVo> programList = null;
		if(category == 1){
			
			String p_addr_1 = req.getParameter("p_addr_1");
			if(p_addr_1.equals("강원도")){
				p_addr_1 = "강원";
				programList = this.programDao.getSelectAddr(p_addr_1);
			}else if(p_addr_1.equals("경상도")){
				p_addr_1 = "경남";
				programList = this.programDao.getSelectAddr(p_addr_1);
				p_addr_1 = "경북";
				programList.addAll(this.programDao.getSelectAddr(p_addr_1));
			}else if(p_addr_1.equals("전라도")){
				p_addr_1 = "전남";
				programList = this.programDao.getSelectAddr(p_addr_1);
				p_addr_1 = "전북";
				programList.addAll(this.programDao.getSelectAddr(p_addr_1));
			}else if(p_addr_1.equals("충청도")){
				p_addr_1 = "충남";
				programList = this.programDao.getSelectAddr(p_addr_1);
				p_addr_1 = "충북";
				programList.addAll(this.programDao.getSelectAddr(p_addr_1));
			}else if(p_addr_1.equals("제주도")){
				p_addr_1 = "제주";
				programList = this.programDao.getSelectAddr(p_addr_1);
			}else if(p_addr_1.equals("경기도")){
				p_addr_1 = "서울";
				programList = this.programDao.getSelectAddr(p_addr_1);
				p_addr_1 = "경기";
				programList.addAll(this.programDao.getSelectAddr(p_addr_1));
			}
			

			for(int i=0; i<programList.size(); i++){
				if(programList.get(i).getP_addr_1()==null){
					programList.get(i).setP_addr_1("");
				}
				if(programList.get(i).getP_addr_2()==null){
					programList.get(i).setP_addr_2("");
				}
				if(programList.get(i).getP_addr_3()==null){
					programList.get(i).setP_addr_3("");
				}
				if(programList.get(i).getP_addr_4()==null){
					programList.get(i).setP_addr_4("");
				}
				if(programList.get(i).getP_addr_5()==null){
					programList.get(i).setP_addr_5("");
				}
				if(programList.get(i).getP_addr_6()==null){
					programList.get(i).setP_addr_6("");
				}

				if(programList.get(i).getP_note()==null){
					programList.get(i).setP_note("");
				}
				programList.get(i).setP_curriculum(programDao.getSelectCurriculum(programList.get(i).getP_id()));

			}
		}else if(category == 2){
			String e_name = req.getParameter("e_name");
			programList = this.programDao.getSelectExperience(e_name);

			for(int i=0; i<programList.size(); i++){
				if(programList.get(i).getP_addr_1()==null){
					programList.get(i).setP_addr_1("");
				}
				if(programList.get(i).getP_addr_2()==null){
					programList.get(i).setP_addr_2("");
				}
				if(programList.get(i).getP_addr_3()==null){
					programList.get(i).setP_addr_3("");
				}
				if(programList.get(i).getP_addr_4()==null){
					programList.get(i).setP_addr_4("");
				}
				if(programList.get(i).getP_addr_5()==null){
					programList.get(i).setP_addr_5("");
				}
				if(programList.get(i).getP_addr_6()==null){
					programList.get(i).setP_addr_6("");
				}

				if(programList.get(i).getP_note()==null){
					programList.get(i).setP_note("");
				}
				programList.get(i).setP_curriculum(programDao.getSelectCurriculum(programList.get(i).getP_id()));
			}

		}
		return programList;

	}

	@RequestMapping(value = "/getprogramafterwordphoto", method=RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getAfterwordPhotoUrl(@RequestParam("p_id") int p_id){

		List<HashMap<String, String>> afterwordPhotoList = programDao.getSelectAfterwordPhotoUrl(p_id);

		return afterwordPhotoList;

	}
	
	@RequestMapping(value = "/getprogrampid", method=RequestMethod.GET)
	public @ResponseBody ProgramVo getProgramPId(@RequestParam("p_id") int p_id){

		ProgramVo result = programDao.getSelectPId(p_id);
			if(result.getP_addr_1()==null){
				result.setP_addr_1("");
			}
			if(result.getP_addr_2()==null){
				result.setP_addr_2("");
			}
			if(result.getP_addr_3()==null){
				result.setP_addr_3("");
			}
			if(result.getP_addr_4()==null){
				result.setP_addr_4("");
			}
			if(result.getP_addr_5()==null){
				result.setP_addr_5("");
			}
			if(result.getP_addr_6()==null){
				result.setP_addr_6("");
			}

			if(result.getP_note()==null){
				result.setP_note("");
			}

			result.setP_curriculum(programDao.getSelectCurriculum(result.getP_id()));


		return result;

	}
	
	@RequestMapping(value = "/getsearch", method=RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getSearch(@RequestParam("content") String content){

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		content = "%"+content+"%";
		List<ProgramVo> programList = this.programDao.getSelectSearch(content);
		List<HashMap<String, String>> gatheringList = this.gatheringDao.getSelectSearch(content);

		for(int i=0; i<programList.size(); i++){
			if(programList.get(i).getP_addr_1()==null){
				programList.get(i).setP_addr_1("");
			}
			if(programList.get(i).getP_addr_2()==null){
				programList.get(i).setP_addr_2("");
			}
			if(programList.get(i).getP_addr_3()==null){
				programList.get(i).setP_addr_3("");
			}
			if(programList.get(i).getP_addr_4()==null){
				programList.get(i).setP_addr_4("");
			}
			if(programList.get(i).getP_addr_5()==null){
				programList.get(i).setP_addr_5("");
			}
			if(programList.get(i).getP_addr_6()==null){
				programList.get(i).setP_addr_6("");
			}

			if(programList.get(i).getP_note()==null){
				programList.get(i).setP_note("");
			}

			programList.get(i).setP_curriculum(programDao.getSelectCurriculum(programList.get(i).getP_id()));
		}
		
		resultHashMap.put("program", (T)programList);
		resultHashMap.put("gathering", (T)gatheringList);

		return resultHashMap;

	}
	
	@RequestMapping(value = "/getprogramone", method=RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getProgramOne(@RequestParam("p_id") int p_id){

		HashMap<String, T> result = new HashMap<String, T>();
		try{
			ProgramVo program=programDao.getSelectPId(p_id);
			program.setP_curriculum(programDao.getSelectCurriculum(p_id));
			result.put("result", (T)"200");
			result.put("program", (T)program);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}
}