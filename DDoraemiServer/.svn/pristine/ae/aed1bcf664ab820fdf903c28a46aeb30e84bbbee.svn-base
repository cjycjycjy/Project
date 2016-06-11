package com.myserver.skp.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.AdminDao;
import com.myserver.skp.dao.AfterwordDao;
import com.myserver.skp.dao.AfterwordReplyDao;
import com.myserver.skp.dao.GatheringDao;
import com.myserver.skp.dao.ProgramDao;
import com.myserver.skp.dao.UserDao;
import com.myserver.skp.util.MailSender;
import com.myserver.skp.vo.ProgramVo;

@Controller
public class AdminController {

	@Resource
	AdminDao adminDao;

	@Resource
	UserDao userDao;

	@Resource
	ProgramDao programDao;

	@Resource
	GatheringDao gatheringDao;
	
	@Resource
	AfterwordDao afterwordDao;
	
	@Resource
	AfterwordReplyDao afterwordReplyDao;

	@Autowired
	JavaMailSender javaMailSender;

	MailSender mailSender;

	@RequestMapping(value = "/requestprogram", method = RequestMethod.GET)
	public @ResponseBody String requestProgram(@RequestParam("u_id") String u_id, @RequestParam("a_name") String a_name, @RequestParam("a_phonenum") String a_phonenum, @RequestParam("a_p_phonenum") String a_p_phonenum, @RequestParam("a_email") String a_email, @RequestParam("a_programinfo") String a_programinfo) {

		try{
			mailSender = new MailSender();
			String content = "아이디 : "+u_id+"\n"+
					"이   름 : "+a_name+"\n"+
					"연락처 : "+a_phonenum+"\n"+
					"기관 연락처 : "+a_p_phonenum+"\n"+
					"이메일 : "+a_email+"\n"+
					"체험 정보 : "+a_programinfo+"\n";
			if(mailSender.sendMail(javaMailSender, a_email, content)){
				userDao.updatePermissionAdminRequest(u_id);
				return "200";
			}
		}catch(Exception e){
			return "500";
		}
		return "500";
	}

	@RequestMapping(value = "/getadminprogram", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getAdminProgram(@RequestParam("u_id") String u_id){
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			List<ProgramVo> programList = programDao.getSelectAdmin(u_id);
			result.put("result", (T)"200");
			result.put("program", (T)programList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}

	@RequestMapping(value = "/getadminprograminfo", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getAdminProgram(@RequestParam("p_id") int p_id){
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			ProgramVo programList = programDao.getSelectAdminProgram(p_id);
			List<HashMap<String, String>> gatheringList = gatheringDao.getSelectAllofGatheringAdmin(p_id);
			result.put("result", (T)"200");
			result.put("program", (T)programList);
			if(programList.getP_addr_1()==null){
				programList.setP_addr_1("");
			}
			if(programList.getP_addr_2()==null){
				programList.setP_addr_2("");
			}
			if(programList.getP_addr_3()==null){
				programList.setP_addr_3("");
			}
			if(programList.getP_addr_4()==null){
				programList.setP_addr_4("");
			}
			if(programList.getP_addr_5()==null){
				programList.setP_addr_5("");
			}
			if(programList.getP_addr_6()==null){
				programList.setP_addr_6("");
			}

			if(programList.getP_note()==null){
				programList.setP_note("");
			}
			
			

			programList.setP_curriculum(programDao.getSelectCurriculum(programList.getP_id()));
			
			
			result.put("gathering", (T)gatheringList);
			
			List<HashMap<String, T>> afterwordList = afterwordDao.getSelectAllofAfterword(p_id);
			for(int i=0; i<afterwordList.size(); i++){
				int a_id = (Integer)afterwordList.get(i).get("a_id");
				afterwordList.get(i).put("afterword_reply",(T)afterwordReplyDao.getSelectAllofAfterwordReply(a_id));
				afterwordList.get(i).put("a_photo_url", (T)afterwordDao.getSelectAfterwordPhotoUrl(a_id));
			}
			
			result.put("afterword", (T)afterwordList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}
}
