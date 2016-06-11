package com.fuck.hangang.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.NoticeDao;
import com.fuck.hangang.dao.TeamListDao;
import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.NoticePhotoVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamGalleryVo;
import com.fuck.hangang.vo.TeamUserVo;
import com.fuck.hangang.vo.TeamVo;

@Controller
public class TeamListController {
	@Resource
	TeamListDao teamListDao;

	String logofilePath = "photo/teamlogo/";
	String logosavePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/teamlogo/";
	String backgroundfilePath = "photo/teambackgroundimg/";
	String backgroundsavePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/teambackgroundimg/";
	String teamgalleryfilePath = "photo/teamgallery/";
	String teamgallerysavePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/teamgallery/";

	@RequestMapping(value = "/getteaminfo")
	public @ResponseBody <T> HashMap<String, T> getteaminfo() {
		List<TeamVo> resultTeamVo = teamListDao.getteaminfo();
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		if (resultTeamVo != null) {
			resultHashMap.put("team", (T) resultTeamVo);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/getteambloginfo", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getteambloginfo(@RequestParam("t_num") int t_num,
			@RequestParam("year") int year, @RequestParam("month") int month) {
		int nextmonth = month + 1;
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<ScheduleVo> resultScheduleVo = teamListDao.getteamschedule(t_num, year, month, nextmonth);
		List<TeamGalleryVo> resultTeamGalleryVo = teamListDao.getteamgallery(t_num);
		TeamVo resultTeamdata = teamListDao.getteamdata(t_num);
		List<CheerfulVo> resultCheerfulVo = teamListDao.getteamcheerful(t_num);
		for (int i = 0; i < resultCheerfulVo.size(); i++) {
			resultCheerfulVo.get(i)
					.setCh_c_count(teamListDao.getcheerfulcommentnum(resultCheerfulVo.get(i).getCh_num()));
		}
		resultHashMap.put("schedule", (T) resultScheduleVo);
		resultHashMap.put("teaminfo", (T) resultTeamdata);
		resultHashMap.put("cheerful", (T) resultCheerfulVo);
		resultHashMap.put("gallery", (T) resultTeamGalleryVo);
		resultHashMap.put("result", (T) "Success");
		return resultHashMap;

	}

	@RequestMapping(value = "/changeteamlogoimage", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> changeteamlogoimage(@RequestBody JSONObject json) throws Exception {

		TeamVo teamVo = null;

		ObjectMapper mapper = new ObjectMapper();
		teamVo = mapper.readValue(json.toString(), TeamVo.class);

		int t_num = teamVo.getT_num();
		String t_logoImg = teamVo.getT_logo();

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		if (teamVo != null) {
			String teamname = teamListDao.getteamname(t_num);
			String logourl = teamListDao.getteamlogourl(t_num);
			deleteFile(logourl, logosavePath);
			String newlogourl = logofilePath + writeFile(t_logoImg, logosavePath, teamname);

			teamListDao.changeteamlogoimage(t_num, newlogourl);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("t_logo", (T) newlogourl);
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/changebackgroundimage", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> changebackgroundimage(@RequestBody JSONObject json) throws Exception {

		TeamVo teamVo = null;

		ObjectMapper mapper = new ObjectMapper();
		teamVo = mapper.readValue(json.toString(), TeamVo.class);

		int t_num = teamVo.getT_num();
		String t_background_img = teamVo.getT_background_img();

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		if (teamVo != null) {
			String teamname = teamListDao.getteamname(t_num);
			String backgroundurl = teamListDao.getteambackgroundurl(t_num);
			deleteFile(backgroundurl, backgroundsavePath);
			String newbackgroundurl = backgroundfilePath + writeFile(t_background_img, backgroundsavePath, teamname);

			teamListDao.changebackgroundimage(t_num, newbackgroundurl);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("t_background_img", (T) newbackgroundurl);
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/changeteamfacebook", method = RequestMethod.GET)
	public @ResponseBody String changeteamfacebook(@RequestParam("t_num") int t_num,
			@RequestParam("t_facebook") String t_facebook) {

		try {

			teamListDao.changeteamfacebook(t_num, t_facebook);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();

			return "Failed";
		}
	}

	@RequestMapping(value = "/changeteamyoutube", method = RequestMethod.GET)
	public @ResponseBody String changeteamyoutube(@RequestParam("t_num") int t_num,
			@RequestParam("t_youtube") String t_youtube) {

		try {

			teamListDao.changeteamyoutube(t_num, t_youtube);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();

			return "Failed";
		}
	}

	@RequestMapping(value = "/deleteteamfacebook", method = RequestMethod.GET)
	public @ResponseBody String deleteteamfacebook(@RequestParam("t_num") int t_num) {

		try {

			teamListDao.deleteteamfacebook(t_num);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();

			return "Failed";
		}
	}

	@RequestMapping(value = "/deleteteamyoutube", method = RequestMethod.GET)
	public @ResponseBody String deleteteamyoutube(@RequestParam("t_num") int t_num) {

		try {

			teamListDao.deleteteamyoutube(t_num);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();

			return "Failed";
		}
	}

	@RequestMapping(value = "/registbookmark", method = RequestMethod.GET)
	public @ResponseBody String registbookmark(@RequestParam("u_id") String u_id, @RequestParam("t_num") int t_num,
			@RequestParam("event") String event) {

		try {

			if (event.equals("delete")) {
				teamListDao.deletebookmark(u_id, t_num);
			}

			else {
				teamListDao.registbookmark(u_id, t_num);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();

			return "Failed";
		}
	}

	@RequestMapping(value = "/deletebookmark", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletebookmark(@RequestParam("u_id") String u_id,
			@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			teamListDao.deletebookmark(u_id, t_num);

			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/registteamgallery", method = RequestMethod.POST)
	public @ResponseBody String registteamgallery(@RequestBody JSONObject json) throws Exception {

		TeamGalleryVo teamgalleryVo = null;

		ObjectMapper mapper = new ObjectMapper();
		teamgalleryVo = mapper.readValue(json.toString(), TeamGalleryVo.class);

		int t_num = teamgalleryVo.getT_num();
		String tg_photo = teamgalleryVo.getPhoto_url();

		if (teamgalleryVo != null) {

			String newgalleryurl = teamgalleryfilePath
					+ writeFile(tg_photo, teamgallerysavePath, Integer.toString(t_num));

			teamListDao.registteamgallery(t_num, newgalleryurl);

		         return "Success";

		} else {

			return "Failed";
		}
	}

	@RequestMapping(value = "/getteamgallery", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getteamgallery(@RequestParam("t_num") int t_num,
			@RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<TeamGalleryVo> resultTeamGalleryVo = null;
		if (lastnum == -1) {
			lastnum = 0;
			int count = lastnum + 20;
			resultTeamGalleryVo = teamListDao.getteamgalleryfirst(t_num, lastnum, count);
		}

		else {

			resultTeamGalleryVo = teamListDao.getteamgallerylast(t_num, lastnum);

		}

		if (resultTeamGalleryVo != null) {
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("teamgallery", (T) resultTeamGalleryVo);
			return resultHashMap;
		}

		else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/deleteteamgallery", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteteamgallery(@RequestParam("t_num") int t_num,
			@RequestParam("p_num") int p_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		TeamGalleryVo resultTeamGalleryVo = new TeamGalleryVo();
		try {
			resultTeamGalleryVo = teamListDao.getteamgalleryurl(t_num, p_num);
			deleteFile(resultTeamGalleryVo.getPhoto_url(), teamgallerysavePath);
			teamListDao.deleteteamgallery(t_num, p_num);

			resultHashMap.put("result", (T) "Success");
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/changeteaminfo", method = RequestMethod.GET)
	public @ResponseBody String changeteaminfo(@RequestParam("t_num") int t_num,
			@RequestParam("t_info") String t_info) {

		try {
			teamListDao.changeteaminfo(t_num, t_info);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	@RequestMapping(value = "/getteamuser", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getteamuser(@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		List<TeamUserVo> resultTeamUserVo = new ArrayList<TeamUserVo>();

		try {
			resultTeamUserVo = teamListDao.getteamuser(t_num);
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("teamuser", (T) resultTeamUserVo);
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/registteamuser", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registteamuser(@RequestParam("u_id") String u_id,
			@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		TeamUserVo duplicationTeamUserVo = new TeamUserVo();

		duplicationTeamUserVo = teamListDao.getduplicationteamuser(u_id);

		if (duplicationTeamUserVo != null) {
			resultHashMap.put("result", (T) "Duplication");
			return resultHashMap;
		} else {
			teamListDao.deletebookmark(u_id, t_num);
			teamListDao.registteamuser(u_id, t_num);
			teamListDao.changeuserpermission(u_id, 2);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/deleteteamuser", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteteamuser(@RequestParam("u_id") String u_id) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			teamListDao.deleteteamuser(u_id);
			teamListDao.changeuserpermission(u_id, 1);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/updateteamleader", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updateteamleader(@RequestParam("u_id") String u_id,
			@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<TeamUserVo> resultTeamUserVo = new ArrayList<TeamUserVo>();

		try {
			String old_leader = teamListDao.getteamleader(t_num);
			teamListDao.updateoldleader(old_leader);
			teamListDao.updatenewleader(u_id);
			resultTeamUserVo = teamListDao.getteamuser(t_num);
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("teamuser", (T) resultTeamUserVo);
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	public String writeFile(String file, String savePath, String name) throws Exception {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileurl = name + "_" + dateFormat.format(calender.getTime()) + ".jpg";

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + fileurl));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return fileurl;
	}

	public boolean deleteFile(String delete_file_name, String savePath) throws Exception {

		File delete_file = new File("C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/" + delete_file_name);

		if (!delete_file.delete())
			return false;

		return true;
	}

}
