package com.myserver.skp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.GatheringDao;
import com.myserver.skp.dao.ProgramDao;
import com.myserver.skp.dao.TestDao;
import com.myserver.skp.dao.UserDao;
import com.myserver.skp.util.GCMSender;
import com.myserver.skp.util.SendSMS;
import com.myserver.skp.vo.ProgramVo;
import com.myserver.skp.vo.UserVo;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	private String savePath = "D:\\Users\\im501\\workspace-sts-3.7.0.RELEASE\\SPK\\src\\main\\webapp\\user_photo\\";
	private String smsMsg = "Kids Farmer 인증번호 ";
	
	@Resource
	private UserDao userDao;

	@Resource
	private ProgramDao programDao;

	@Resource
	private GatheringDao gatheringDao;
	
	@Resource
	private TestDao testDao;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody String signup_post(@RequestBody JSONObject json) 
	{
		UserVo user=null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			user = mapper.readValue(json.toString(), UserVo.class);
			//user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
			userDao.insertBase(user);
			for(int i=0; i<user.getFavorit_program().size(); i++)
				userDao.insertFavoritProgram(user.getU_id(), user.getFavorit_program().get(i).get("e_name"));
			
			List<ProgramVo> programList = this.programDao.getSelectAll();
			List<HashMap<String, Integer>> bookmark = userDao.getSelectBookmark(user.getU_id());
			List<HashMap<String, String>> fav = userDao.getSelectFavoritProgram(user.getU_id());
			for(int j=0; j<programList.size(); j++){
				int cur_grade = testDao.getSelectProgramCurGrade(user.getU_age(), programList.get(j).getP_id());
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
				
				testDao.insertRecommend(programList.get(j).getP_id(), user.getU_id(), cur_grade, bookmark_grade, fav_grade, season_grade);
			}
			
			return "200";

		}  catch (IOException e){
			return "500";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> login_post(@RequestParam("u_id") String u_id, 
			@RequestParam("password") String password) 
	{
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		//password = passwordEncoder.encodePassword(password, null);
		UserVo resultUserVo = userDao.login(u_id, password);

		if(resultUserVo != null && resultUserVo.getU_id().equals(u_id)){
			resultHashMap.put("result", (T)"200");
			resultUserVo.setBookmark(userDao.getSelectBookmark(u_id));
			resultUserVo.setFavorit_program(userDao.getSelectFavoritProgram(u_id));
			resultUserVo.setG_id(gatheringDao.getSelectJoinGatheringId(u_id));
			resultHashMap.put("user", (T)resultUserVo);
			resultHashMap.put("g_id", (T)gatheringDao.getSelectJoinGatheringId(u_id));
		}else{
			resultHashMap.put("result", (T)"500");
		}

		return resultHashMap;

	}


	@RequestMapping(value = "/idcheck", method = RequestMethod.GET)
	public @ResponseBody String idCheck_get(@RequestParam("u_id") String u_id) 
	{
		UserVo user= userDao.idCheck(u_id);

		if(user==null)
			return "200";
		else
			return "500";
	}

	@RequestMapping(value = "/findpassword", method = RequestMethod.POST)
	public @ResponseBody String findPasswordPost(@RequestParam("u_id") String u_id, @RequestParam("u_phone") String u_phone){
		
		try{
			String u_phone_num = userDao.getSelectUserPhone(u_id);
			if(u_phone_num.equals(u_phone)){
				SendSMS sendSMS = new SendSMS();
				Random rand = new Random();
				String password = ""+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10);
				sendSMS.senSMS(u_phone, "Kids Farmer 임시 비밀번호 : "+password);
				userDao.updatePassword(u_id, password);
				return "200";
			}else{
				return "500";
			}
			
		}catch(Exception e){
			return "500";
		}
	}

	@RequestMapping(value = "/confirmphone", method = RequestMethod.POST)
	public @ResponseBody String confirmPhone(@RequestParam("u_phone") String u_phone){
		return "500";
		
	}

	@RequestMapping(value = "/getbookmark", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, Integer>> bookmark(@RequestParam("u_id") String u_id){
		List<HashMap<String, Integer>> bookmark = userDao.getSelectBookmark(u_id);
		return bookmark;
	}

	@RequestMapping(value = "/registerbookmark", method = RequestMethod.GET)
	public @ResponseBody String registerBookmark(@RequestParam("u_id") String u_id, @RequestParam("p_id") int p_id){
		userDao.insertBookmark(u_id, p_id);
		testDao.updateRecommendWishAdd(p_id, u_id);
		return "200";
	}

	@RequestMapping(value = "/unregisterbookmark", method = RequestMethod.GET)
	public @ResponseBody String unregisterBookmark(@RequestParam("u_id") String u_id, @RequestParam("p_id") int p_id){
		userDao.deleteBookmark(u_id, p_id);
		testDao.updateRecommendWishDel(p_id, u_id);
		return "200";
	}

	@RequestMapping(value = "/getmypage", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getMyPage(@RequestParam("u_id") String u_id){
		HashMap<String, T> result = new HashMap<String, T>();
		List<ProgramVo> bookmarkList = programDao.getSelectBookmark(u_id);
		for(int i=0; i<bookmarkList.size(); i++){
			bookmarkList.get(i).setP_curriculum(programDao.getSelectCurriculum(bookmarkList.get(i).getP_id()));
		}

		List<HashMap<String,String>> passerList = programDao.getSelectPasser(u_id);

		result.put("bookmark", (T)bookmarkList);
		result.put("passer_program", (T)passerList);
		result.put("join_gathering", (T)gatheringDao.getSelectJoinGathering(u_id));
		return result;
	}

	@RequestMapping(value = "/modifyfavoriteprogram", method = RequestMethod.POST)
	public @ResponseBody String modifyFavoriteProgram(@RequestBody JSONObject json){

		UserVo user=null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			user = mapper.readValue(json.toString(), UserVo.class);
			userDao.deleteFavoritProgram(user.getU_id());
			testDao.updateRecommendFavDel(user.getU_id());
			for(int i=0; i<user.getFavorit_program().size(); i++){
				userDao.insertFavoritProgram(user.getU_id(), user.getFavorit_program().get(i).get("e_name"));
				List<Integer> p_id = programDao.getSelectPIdEName(user.getFavorit_program().get(i).get("e_name"));
				for(int j=0; j<p_id.size(); j++){
					testDao.updateRecommendFavAdd(p_id.get(j), user.getU_id());
				}
			}
			
			
			return "200";

		}  catch (IOException e){
			return "500";
		}
	}


	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public @ResponseBody String changePassword(@RequestParam("u_id") String u_id, @RequestParam("password") String password) 
	{
		try{
			userDao.updatePassword(u_id, password);
			return "200";
		}catch(Exception e){
			return "500";
		}
	}

	@RequestMapping(value = "/gcm", method = RequestMethod.GET)
	public @ResponseBody void gcm() 
	{
		GCMSender gcmSender = new GCMSender();
		//gcmSender.sendGCM("APA91bGZJL8icF-ewWJynl6Fk-cYVafJlXnxtb4xBa5crx-wM7BJ-n_pYuG6gq_QfVunp4gk1fcJ5RB65nGi7SFly5TScFO3LAy_xZA1BDWXz1-XEnqTNLU", "KIDS FARMER","시발년아");
	}

	@RequestMapping(value = "/getjoinuser", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getJoinUser(@RequestParam("g_id") int g_id) 
	{
		HashMap<String, T> resultMap = new HashMap<String, T>();
		try{
			List<UserVo> userList = userDao.getSelectJoinGatheringUser(g_id);
			resultMap.put("result", (T)"200");
			resultMap.put("join_user", (T)userList);
		}catch(Exception e){
			resultMap.put("result", (T)"200");
		}
		return resultMap;
	}

	@RequestMapping(value = "/modifyuserphoto", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> modifyUserPhoto(@RequestBody JSONObject json) 
	{
		HashMap<String, String> result = new HashMap<String, String>();
		String u_id = json.get("u_id").toString();
		String photo = json.get("photo").toString();

		try{
			String u_photo_url = writeFile(photo, u_id);
			//deleteFile(u_photo_url);
			userDao.updateUserPhoto(u_id, u_photo_url);
			result.put("result", "200");
			result.put("u_photo_url", u_photo_url);
			return result;
		}catch(Exception e){
			result.put("result", "500");
			return result;
		}
	}

	@RequestMapping(value = "/getverificationcode", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getVerificationCode(@RequestParam("u_phone") String u_phone) 
	{
		HashMap<String, String> resultHashMap = new HashMap<String, String>();
		try{
			SendSMS sendSMS = new SendSMS();
			Random rand = new Random();
			String verification_code = ""+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10);
			sendSMS.senSMS(u_phone, smsMsg+verification_code);
			//userDao.deleteVerificationCode(u_id);
			//userDao.insertVerificationCode(u_id, verification_code);
			resultHashMap.put("result", "200");
			resultHashMap.put("verification_code", verification_code);
			return resultHashMap;

		}catch(Exception e){
			resultHashMap.put("result", "500");
			return resultHashMap;
		}


	}

	@RequestMapping(value = "/confirmcode", method = RequestMethod.GET)
	public @ResponseBody String confimPhonenum(@RequestParam("u_id") String u_id, @Param("verification_code") String verification_code) 
	{
		try{
			String code = userDao.getSelectVerificationCode(u_id);
			if(verification_code.equals(code)){
				userDao.deleteVerificationCode(u_id);
				return "200";
			}
			else{
				return "500";
			}
		}catch(Exception e){
			return "500";
		}
	}
	
	@RequestMapping(value = "/setalarm", method = RequestMethod.GET)
	public @ResponseBody String setAlarm(@RequestParam("u_id") String u_id, @Param("alarm") int alarm) 
	{
		try{
			userDao.updateAlarm(u_id, alarm);
			return "200";
		}catch(Exception e){
			return "500";
		}
	}

	public String writeFile(String file, String u_id) throws Exception{
		String u_photo_url = u_id+"_"+System.currentTimeMillis()+".jpg";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath+u_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return u_photo_url;
	}

	public boolean deleteFile(String delete_photo_path) throws Exception{
		File delete_file = new File(savePath+delete_photo_path);
		if(!delete_file.delete())
			return false;
		return true;
	}
	
	@RequestMapping(value = "/updateregisterid", method = RequestMethod.GET)
	public @ResponseBody String updateRegisterIdUId(@RequestParam("u_id") String u_id, @Param("u_register_id") String u_register_id) 
	{
		try{
			userDao.updateRegisterIdUId(u_id, u_register_id);
			return "200";
		}catch(Exception e){
			return "500";
		}
	}
}
