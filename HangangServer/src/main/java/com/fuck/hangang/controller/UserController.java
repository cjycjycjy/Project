package com.fuck.hangang.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.UserDao;
import com.fuck.hangang.util.MailSender;
import com.fuck.hangang.vo.RequestChangeTeamNameVo;
import com.fuck.hangang.vo.TeamUserVo;
import com.fuck.hangang.vo.TeamVo;
import com.fuck.hangang.vo.UserVo;

@Controller
public class UserController {
	@Resource
	private UserDao userDao;

	String userfilePath = "photo/user/";
	String usersavePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/user/";

	@Autowired
	JavaMailSender javaMailSender;

	MailSender mailSender;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody String signup(@ModelAttribute UserVo userVo) {

		try {
			String email_check = null;
			email_check = userDao.getAllEmail(userVo.getU_email());

			if (email_check == null)
				userDao.signup(userVo);
			else
				return "OverlapEmail";

		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public @ResponseBody String signin(@RequestParam("u_id") String u_id, @RequestParam("u_passwd") String u_passwd) {

		// password = passwordEncoder.encodePassword(password, null);
		UserVo resultUserVo = userDao.signin(u_id, u_passwd);

		if (resultUserVo != null) {
			return "Success";
		} else {
			return "Failed";
		}
	}

	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getUser(@RequestParam("u_id") String u_id,
			@RequestParam("u_register_id") String u_register_id) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		UserVo resultUserVo = userDao.getuser(u_id);
		List<TeamVo> resultBookmarkTeamVo = userDao.getbookmarkteam(u_id);

		userDao.updateRegisterIdById(u_id, u_register_id);
		if (resultUserVo != null) {
			if (resultUserVo.getU_permission() == 2) {
				TeamVo resultMyTeamVo = userDao.getteam(u_id);
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("user", (T) resultUserVo);
				resultHashMap.put("team", (T) resultMyTeamVo);
				resultHashMap.put("bookmark", (T) resultBookmarkTeamVo);
				return resultHashMap;
			} else {
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("user", (T) resultUserVo);
				resultHashMap.put("bookmark", (T) resultBookmarkTeamVo);
				return resultHashMap;
			}
		} else {
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public @ResponseBody String signout(@RequestParam("u_id") String u_id) {

		try {
			int u_permission = userDao.getuser(u_id).getU_permission();

			if (u_permission == 1) {
				userDao.signout(u_id);
			}

			else {
				TeamUserVo resultTeamUserVo = new TeamUserVo();
				resultTeamUserVo = userDao.getteamuser(u_id);
				String leader = userDao.getteamleader(resultTeamUserVo.getT_num());
				int teammembersize = userDao.getteamsize(resultTeamUserVo.getT_num());

				if (teammembersize == 1) {
					userDao.deleteteamuser(u_id);
					userDao.deleteteam(resultTeamUserVo.getT_num());
					userDao.signout(u_id);

				} else {
					if (leader.equals(u_id)) {

						userDao.deleteteamuser(u_id);
						TeamUserVo resultNextLeaderVo = userDao.getrandomleader(resultTeamUserVo.getT_num());
						userDao.updateleader(resultNextLeaderVo.getU_id());

					}

					userDao.signout(u_id);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}

		return "Success";

	}

	@RequestMapping(value = "/getoneuser", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getoneuser(@RequestParam("u_id") String u_id) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		UserVo resultUserVo = new UserVo();

		try {
			resultUserVo = userDao.getoneuser(u_id);
			resultHashMap.put("user", (T) resultUserVo);
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/changeuserphoto", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> changeuserPhoto(@RequestBody JSONObject json) throws Exception {

		UserVo userVo = null;

		ObjectMapper mapper = new ObjectMapper();
		userVo = mapper.readValue(json.toString(), UserVo.class);

		String u_id = userVo.getU_id();
		String u_photo = userVo.getU_photo();

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		if (userVo != null) {
			String userphotourl = userDao.getuserphoto(u_id);
			deleteFile(userphotourl, usersavePath);
			String newuserphotourl = userfilePath + writeFile(u_photo, usersavePath, u_id);

			userDao.changeuserphoto(u_id, newuserphotourl);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("u_photo", (T) newuserphotourl);
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/changepasswd", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> changepasswd(@RequestParam("u_id") String u_id,
			@RequestParam("u_passwd") String u_passwd) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			userDao.changepasswd(u_id, u_passwd);
			resultHashMap.put("result", (T) "Success");
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
		String n_photo_url = name + "_" + dateFormat.format(calender.getTime()) + ".jpg";

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + n_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return n_photo_url;
	}

	public boolean deleteFile(String delete_photo_path, String savePath) throws Exception {

		File delete_file = new File("C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/" + delete_photo_path);

		if (!delete_file.delete())
			return false;

		return true;
	}

	@RequestMapping(value = "/findid", method = RequestMethod.GET)
	public @ResponseBody String findid(@RequestParam("u_email") String u_email) {
		try {
			String user_id = userDao.findid(u_email);
			if (user_id == null) {
				return "NoId";
			} else {
				return user_id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";

		}
	}

	@RequestMapping(value = "/findpassword", method = RequestMethod.GET)
	public @ResponseBody String findPasswordPost(@RequestParam("u_email") String u_email,
			@RequestParam("u_id") String u_id) {

		try {

			String t_u_email = userDao.getSelectUserMail(u_id);
			if (t_u_email.equals(u_email)) {
				Random rand = new Random();
				String password = "" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10)
						+ rand.nextInt(10);
				mailSender = new MailSender();
				String content = "회원님의 임시 비밀 번호는 " + password + " 입니다.";
				if (mailSender.sendMail(javaMailSender, u_email, content)) {
					userDao.updatePassword(u_id, password);
					return "Success";
				} else {
					return "Failed";
				}

			} else {
				return "Failed";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";

		}
	}

	@RequestMapping(value = "/checkChangeTeamName", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> checkChangeTeamName(@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		RequestChangeTeamNameVo resultRequestChangeTeamNameVo = userDao.checkchangeTeamName(t_num);

		if (resultRequestChangeTeamNameVo != null) {
			resultHashMap.put("info", (T) resultRequestChangeTeamNameVo);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} else {
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/cancelChangeTeamName", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> cancelChangeTeamName(@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			userDao.cancelChangeTeamName(t_num);
			resultHashMap.put("result", (T) "Success");

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

	@RequestMapping(value = "/changeteamname", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> changeteamname(@RequestParam("t_num") int t_num,
			@RequestParam("new_t_name") String new_t_name) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			userDao.changeTeamName(t_num, new_t_name);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/changeuserinfo", method = RequestMethod.POST)
	public @ResponseBody String changeuserinfo(@RequestParam("u_id") String u_id,
			@RequestParam("u_passwd") String u_passwd, @RequestParam("u_email") String u_email) {
		try {
			if (u_passwd.equalsIgnoreCase("")) {
				String email_check = null;
				email_check = userDao.getAllEmail(u_email);
				if (email_check == null)
					userDao.changeemail(u_email, u_id);
				else
					return "OverlapEmail";
			} else
				userDao.changeuserinfo(u_passwd, u_email, u_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}

	@RequestMapping(value = "/changepushflag", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> changepushflag(@RequestParam("u_id") String u_id,
			@RequestParam("flag") int flag) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			if (flag == 1)
				flag = 0;
			else
				flag = 1;

			userDao.changepushflag(u_id, flag);
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		resultHashMap.put("result", (T) "Success");
		return resultHashMap;
	}
	
	
	@RequestMapping(value ="/test", method = RequestMethod.GET)
	public @ResponseBody String test(@RequestParam("string") String a) {
		
		if(a==null) {
			return "Failed";
		}
		
		return "Success";
		
	}
	


}
