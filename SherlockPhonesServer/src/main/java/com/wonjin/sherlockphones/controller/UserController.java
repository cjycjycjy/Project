package com.wonjin.sherlockphones.controller;

import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonjin.sherlockphones.dao.UserDao;
import com.wonjin.sherlockphones.util.GCMSender;
import com.wonjin.sherlockphones.util.MailSender;
import com.wonjin.sherlockphones.vo.UserVo;

@Controller
public class UserController {
	@Resource
	private UserDao userDao;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	MailSender mailSender;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody String signup(@ModelAttribute UserVo userVo) {

		try {
			userDao.signup(userVo);
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public @ResponseBody String updateUser(@ModelAttribute UserVo userVo) {

		try {
			userDao.updateUser(userVo);
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> signin(@RequestParam("u_phonenum") String u_phonenum, @RequestParam("u_passwd") String u_passwd) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		//password = passwordEncoder.encodePassword(password, null);
		UserVo resultUserVo = userDao.login(u_phonenum, u_passwd);

		if(resultUserVo != null && resultUserVo.getU_phonenum().equals(u_phonenum)){
			resultHashMap.put("result", (T)"Success");
			resultHashMap.put("user", (T)resultUserVo);
		}else{
			resultHashMap.put("result", (T)"Failed");
		}

		return resultHashMap;
	}
	
	@RequestMapping(value = "/sendregistrationid", method = RequestMethod.POST)
	public @ResponseBody String insertRegisterId(@RequestParam("u_phonenum") String u_phonenum, @RequestParam("u_register_id") String u_register_id) {

		try {
			String register_id = this.userDao.getSelectRegisterId(u_phonenum);
			if(register_id==null)
				userDao.insertRegisterId(u_phonenum, u_register_id);
			else if(!register_id.equals(u_register_id)){
				userDao.updateRegisterIdByPhoneNum(u_phonenum, u_register_id);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}
	
	@RequestMapping(value = "/sendgcm", method = RequestMethod.POST)
	public @ResponseBody <T> String sendGCM(@RequestParam("u_phonenum") String u_phonenum, @RequestParam("message") String message) {

		try {
			String u_register_id = this.userDao.getSelectRegisterId(u_phonenum);
			GCMSender gcmSender = new GCMSender();
			HashMap<String, T> resultHashMap = gcmSender.sendGCM(u_register_id, message);
			if(resultHashMap.get("result").equals("Success") && resultHashMap.containsKey("new_register_id")){
				userDao.updateRegisterId(u_register_id, String.valueOf(resultHashMap.get("new_register_id")));
			}
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}
	
	@RequestMapping(value = "/startservice", method = RequestMethod.POST)
	public @ResponseBody <T> String startService(@RequestParam("u_phonenum") String u_phonenum) {
		try {
			this.userDao.startService(u_phonenum);
			
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}
	
	@RequestMapping(value = "/stopservice", method = RequestMethod.POST)
	public @ResponseBody <T> String stopService(@RequestParam("u_phonenum") String u_phonenum) {

		try {
			this.userDao.stopService(u_phonenum);
			
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}
	
	@RequestMapping(value = "/findpassword", method = RequestMethod.POST)
	public @ResponseBody String findPasswordPost(@RequestParam("u_email") String u_email, @RequestParam("u_phonenum") String u_phonenum){
		
		try{
			String t_u_email = userDao.getSelectUserMail(u_phonenum);
			if(t_u_email.equals(u_email)){
				Random rand = new Random();
				String password = ""+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10);
				mailSender = new MailSender();
				String content = "회원님의 임시 비밀 번호는 "+password+" 입니다.";
				if(mailSender.sendMail(javaMailSender, u_email, content)){
					userDao.updatePassword(u_phonenum, password);
					return "Success";
				}else{
					return "Failed";
				}
				
			}else{
				return "Failed";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
			
		}
	}
}
