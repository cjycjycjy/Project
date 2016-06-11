package com.myserver.skp.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.ProgramDao;
import com.myserver.skp.dao.TestDao;
import com.myserver.skp.dao.UserDao;
import com.myserver.skp.mapper.TestMapper;
import com.myserver.skp.vo.ProgramVo;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Resource
	private TestDao testDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private ProgramDao programDao;

	
	private String source = "D:\\Users\\im501\\workspace-sts-3.7.0.RELEASE\\SPK\\src\\main\\webapp\\program_photo\\";
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void home(Locale locale, Model model) {
		for(int i=0; i<103; i++){
			String name = testDao.getSelectP(i+1);
			if(name.length()>8){
			name = name.substring(0, 8);
			if(new File(source+name+".jpg").isFile())
				testDao.update(name+".jpg", i+1);
			else if(new File(source+name+".bmp").isFile()){
				testDao.update(name+".bmp", i+1);
			}
			else if(new File(source+name+".gif").isFile()){
				testDao.update(name+".gif", i+1);
			}
			else if(new File(source+name+".PNG").isFile()){
				testDao.update(name+".PNG", i+1);
			}
			else if(new File(source+name+".png").isFile()){
				testDao.update(name+".png", i+1);
			}
			
			}
		}
	}
	
	@RequestMapping(value = "/asd", method = RequestMethod.GET)
	public @ResponseBody <T> void home1(Locale locale, Model model) {
		List<ProgramVo> programList = this.programDao.getSelectAll();
		List<HashMap<String, T>> users = this.userDao.getSelectUId();
		for(int i=100; i<112; i++){
			List<HashMap<String, Integer>> bookmark = userDao.getSelectBookmark((String)users.get(i).get("u_id"));
			List<HashMap<String, String>> fav = userDao.getSelectFavoritProgram((String)users.get(i).get("u_id"));
			int u_age_s=(Integer)users.get(i).get("u_age");
			for(int j=0; j<programList.size(); j++){
				int cur_grade = testDao.getSelectProgramCurGrade(u_age_s, programList.get(j).getP_id());
				int bookmark_grade = 0;
				int fav_grade = 0;
				int season_grade = 0;
				for(int k=0; k<fav.size(); k++){
					if(fav.get(k).get("e_name").equals(programList.get(j).getE_name())){
						fav_grade=1;
					}
				}
				
				for(int k=0; k<bookmark.size(); k++){
					if(bookmark.get(k).get("p_id")==programList.get(j).getP_id()){
						bookmark_grade=1;
					}
				}
				
				if(programList.get(j).getP_available_season().charAt(2)=='1'){
					season_grade=100;
				}
				
				testDao.insertRecommend(programList.get(j).getP_id(), (String)users.get(i).get("u_id"), cur_grade, bookmark_grade, fav_grade, season_grade);
			}
		}
	}

}