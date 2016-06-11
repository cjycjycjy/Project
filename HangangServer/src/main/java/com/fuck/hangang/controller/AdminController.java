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
import javax.swing.event.ListDataEvent;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.AdminDao;
import com.fuck.hangang.util.GCMSender;
import com.fuck.hangang.vo.AdminBoardCommentOfCommentVo;
import com.fuck.hangang.vo.AdminBoardCommentVo;
import com.fuck.hangang.vo.AdminBoardPhotoVo;
import com.fuck.hangang.vo.AdminBoardVo;
import com.fuck.hangang.vo.AdminMenuTeamVo;
import com.fuck.hangang.vo.AdminReportListVo;
import com.fuck.hangang.vo.AdminRequestVo;
import com.fuck.hangang.vo.CheerfulReplyOfReplyVo;
import com.fuck.hangang.vo.CheerfulReplyVo;
import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;
import com.fuck.hangang.vo.ReportVo;
import com.fuck.hangang.vo.RequestChangeTeamNameVo;
import com.fuck.hangang.vo.UserVo;

@Controller
public class AdminController {
	@Resource
	AdminDao adminDao;

	String filePath = "photo/adminboard/";
	String savePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/adminboard/";

	@RequestMapping(value = "/registadminboard", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> registadminboard(@RequestBody JSONObject json) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			AdminBoardVo adminboardVo = new AdminBoardVo();
			ObjectMapper mapper = new ObjectMapper();
			adminboardVo = mapper.readValue(json.toString(), AdminBoardVo.class);

			if (adminboardVo.getPhoto().size() == 0) {
				adminDao.registadminboard(adminboardVo);
			} else {
				adminDao.registadminboard(adminboardVo);

				List<AdminBoardPhotoVo> a_photo = adminboardVo.getPhoto();

				for (int i = 0; i < a_photo.size(); i++) {
					String a_photo_url = writeFile(a_photo.get(i).getA_photo_url(), savePath, adminboardVo.getA_num(),
							i);
					adminDao.registadminboardphoto(adminboardVo.getA_num(), i, a_photo_url);
				}
			}

			resultHashMap.put("result", (T) "Success");
		
			adminDao.registPushAboutAdminBoard(2, adminboardVo.getA_num());

			List<RegistrationIdVo> resultRegiId = adminDao.getTeamUserRegiId();
			GCMSender gs = new GCMSender();
			gs.sendGCMToAllTeam(resultRegiId, adminboardVo.getA_num());
			
			
			
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}
	
	@RequestMapping(value = "/updateadminboard", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> updateadminboard(@RequestBody JSONObject json) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		AdminBoardVo adminboardVo = null;
		ObjectMapper mapper = new ObjectMapper();

		String ch_photo = null;
		List<AdminBoardPhotoVo> admin_photo = null;
		List<AdminBoardPhotoVo> a_photo = null;
		try {
			
			adminboardVo = mapper.readValue(json.toString(), AdminBoardVo.class);
			int a_num = adminboardVo.getA_num();
			admin_photo = adminDao.getadminphoto(a_num);
			for (int i = 0; i < admin_photo.size(); i++) {
				deleteFile(admin_photo.get(i).getA_photo_url());
			}
			adminDao.deleteadminphoto(a_num);
			
			a_photo = adminboardVo.getPhoto();

			for (int i = 0; i < a_photo.size(); i++) {
				String a_photo_url = writeFile(a_photo.get(i).getA_photo_url(), savePath, adminboardVo.getA_num(),
						i);
				a_photo.get(i).setA_photo_url(a_photo_url);
				adminDao.registadminboardphoto(adminboardVo.getA_num(), i, a_photo_url);
			}
			adminDao.updateadminboard(adminboardVo);
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");

		}

		resultHashMap.put("result", (T) "Success");
		resultHashMap.put("photourl", (T)a_photo);
		return resultHashMap;
	}
	
	@RequestMapping(value = "/getadminboardone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getadminboardone(@RequestParam("a_num") int a_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();


		AdminBoardVo resultAdminBoardVo =  adminDao.getAdminBoardOne(a_num);

		if (resultAdminBoardVo != null){
			int count = adminDao.admincommentcount(resultAdminBoardVo.getA_num());
			resultAdminBoardVo.setC_count(count);;

			
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboard", (T) resultAdminBoardVo);
		}
		else{
			resultHashMap.put("result", (T) "Failed");
		}
		return resultHashMap;

	}


	@RequestMapping(value = "/getadminboard", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getadminboard(@RequestParam("lastnum") int lastnum,
			@RequestParam("flag") int flag) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<AdminBoardVo> resultadminboardVo = new ArrayList<AdminBoardVo>();

		try {

			if (lastnum == -1) {
				if (flag == -1) {
					resultadminboardVo = adminDao.getadminboardfirstnoflag();
				} else {
					resultadminboardVo = adminDao.getadminboardfirst(flag);
				}
			}

			else {
				if (flag == -1) {
					resultadminboardVo = adminDao.getadminboardlastnoflag(lastnum);

				} else {
					resultadminboardVo = adminDao.getadminboardlast(lastnum, flag);
				}
			}

			for (int i = 0; i < resultadminboardVo.size(); i++) {
				int c_count = adminDao.admincommentcount(resultadminboardVo.get(i).getA_num());
				resultadminboardVo.get(i).setC_count(c_count);
			}

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboardlist", (T) resultadminboardVo);
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/deleteadminboard", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteadminboard(@RequestParam("a_num") int a_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			List<AdminBoardPhotoVo> admin_photo = adminDao.getadminphoto(a_num);
			for (int i = 0; i < admin_photo.size(); i++) {
				deleteFile(admin_photo.get(i).getA_photo_url());
			}
			adminDao.deleteadminphoto(a_num);
			adminDao.deleteadminboard_allrequest(a_num);
			adminDao.deleteadminboard_commentofallcomment(a_num);
			adminDao.deleteadminboard_allcomment(a_num);
			adminDao.deleteadminboard(a_num);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/getadminboardcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getadminboardcomment(@RequestParam("a_num") int a_num,
			@RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<AdminBoardCommentVo> resultAdminBoardCommentVo = new ArrayList<AdminBoardCommentVo>();

		try {

			if (lastnum == -1) {
				resultAdminBoardCommentVo = adminDao.getadminboardcommentfirst(a_num);
			}

			else {
				resultAdminBoardCommentVo = adminDao.getadminboardcommentlast(a_num, lastnum);
			}

			for (int i = 0; i < resultAdminBoardCommentVo.size(); i++) {
				int cc_count = adminDao.getadminboardcommentofcommentcount(a_num,
						resultAdminBoardCommentVo.get(i).getA_c_num());
				resultAdminBoardCommentVo.get(i).setCc_count(cc_count);
			}

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboardcommentlist", (T) resultAdminBoardCommentVo);
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}
	
	@RequestMapping(value = "/getadminboardcommentone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getadminboardcommentone(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num, @RequestParam("pm_num") int pm_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		AdminBoardCommentVo resultAdminBoardCommentVo = adminDao.getadminboardcommentone(a_num, a_c_num);

		if (resultAdminBoardCommentVo != null) {
			int cc_count = adminDao.getadminboardcommentofcommentcount(a_num,
					resultAdminBoardCommentVo.getA_c_num());
			resultAdminBoardCommentVo.setCc_count(cc_count);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("comment", (T) resultAdminBoardCommentVo);

			List<AdminBoardCommentOfCommentVo> resultAdminBoardCommentOfCommentVo = adminDao.getadminboardcommentofcommentfirst(a_num, a_c_num);

			resultHashMap.put("commentofcomment", (T) resultAdminBoardCommentOfCommentVo);
			adminDao.updatePushisChecked(pm_num);
			// resultHashMap.put("cheerfulreply",(T) replyVo );
		} else {
			resultHashMap.put("result", (T) "Failed");
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/registadminboardcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registadminboardcomment(@RequestParam("a_num") int a_num,
			@RequestParam("u_id") String u_id, @RequestParam("a_c_content") String a_c_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			
				adminDao.registadminboardcomment(a_num, u_id, a_c_content);	
			
			List<AdminBoardCommentVo> resultAdminBoardCommentVo = adminDao.getadminboardcommentfirst(a_num);

			for (int i = 0; i < resultAdminBoardCommentVo.size(); i++) {
				int cc_count = adminDao.getadminboardcommentofcommentcount(a_num,
						resultAdminBoardCommentVo.get(i).getA_c_num());
				resultAdminBoardCommentVo.get(i).setCc_count(cc_count);
			}
			String a_c_count = Integer.toString(adminDao.admincommentcount(a_num));
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboardcommentlist", (T) resultAdminBoardCommentVo);
			resultHashMap.put("a_c_count", (T) a_c_count);
			
			List<UserVo> resultAllAdmin = adminDao.getAllAdmin();
			for(int i = 0; i < resultAllAdmin.size(); i++) {
				if(!resultAllAdmin.get(i).getU_id().equalsIgnoreCase(u_id))
					adminDao.registPushAboutAdminBoardComment(resultAllAdmin.get(i).getU_id(), u_id, 13, a_num);
			}
			
			
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/updateadminboardcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updateadminboardcomment(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num, @RequestParam("a_c_content") String a_c_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			adminDao.updateadminboardcomment(a_num, a_c_num, a_c_content);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}
	
	

	@RequestMapping(value = "/deleteadminboardcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteadminboardcomment(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			adminDao.deleteadminboardcommentofallcomment(a_num, a_c_num);
			adminDao.deleteadminboardcomment(a_num, a_c_num);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/getadminboardcommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getadminboardcommentofcomment(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num, @RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<AdminBoardCommentOfCommentVo> resultAdminBoardCommentOfCommentVo = new ArrayList<AdminBoardCommentOfCommentVo>();
		try {

			if (lastnum == -1) {
				resultAdminBoardCommentOfCommentVo = adminDao.getadminboardcommentofcommentfirst(a_num, a_c_num);
			}

			else {
				resultAdminBoardCommentOfCommentVo = adminDao.getadminboardcommentofcommentlast(a_num, a_c_num,
						lastnum);
			}

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboardcommentofcommentlist", (T) resultAdminBoardCommentOfCommentVo);
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/registadminboardcommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registadminboardcommentofcomment(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num, @RequestParam("u_id") String u_id,
			@RequestParam("a_cc_content") String a_cc_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			
			adminDao.registadminboardcommentofcomment(a_num, a_c_num, u_id, a_cc_content);
			
			List<AdminBoardCommentOfCommentVo> resultAdminBoardCommentOfCommentVo = adminDao
					.getadminboardcommentofcommentfirst(a_num, a_c_num);
			String a_cc_count = Integer.toString(adminDao.getadminboardcommentofcommentcount(a_num, a_c_num));

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboardcommentofcommentlist", (T) resultAdminBoardCommentOfCommentVo);
			resultHashMap.put("a_cc_count", (T) a_cc_count);
			
			String id = adminDao.checkReplyUser(a_c_num);
			
			PushVo pushVo = new PushVo();
			pushVo.setU_id(id); pushVo.setReply_writer(u_id);
			pushVo.setFlag(10); pushVo.setNum(a_num); pushVo.setReply_num(a_c_num);
			
			if(!id.equalsIgnoreCase(u_id))
				adminDao.registPushList(pushVo);
			int push = adminDao.checkPushUser(id);
			if (push != 0) {
				if (!id.equalsIgnoreCase(u_id)) {
					RegistrationIdVo resultRegiId = adminDao.getRegiIdOfUserComment(a_c_num);
					GCMSender gs = new GCMSender();
					gs.sendGCMToOneUserAboutCommentOfComment(resultRegiId, a_num, a_c_num, 10, pushVo.getPm_num());
				}
			}
			return resultHashMap;

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/updateadminboardcommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updateadminboardcommentofcomment(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num, @RequestParam("a_cc_num") int a_cc_num,
			@RequestParam("a_cc_content") String a_cc_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			adminDao.updateadminboardcommentofcomment(a_num, a_c_num, a_cc_num, a_cc_content);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/deleteadminboardcommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteadminboardcommentofcomment(@RequestParam("a_num") int a_num,
			@RequestParam("a_c_num") int a_c_num, @RequestParam("a_cc_num") int a_cc_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			adminDao.deleteadminboardcommentofcomment(a_num, a_c_num, a_cc_num);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/registadminboardrequest", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registadminboardrequest(@RequestParam("a_num") int a_num,
			@RequestParam("t_num") int t_num, @RequestParam("contents") String contents) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			adminDao.registadminboardrequest(a_num, t_num, contents);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/getadminboardrequest", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getadminboardrequest(@RequestParam("a_num") int a_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<AdminRequestVo> resultAdminRequestVo = new ArrayList<AdminRequestVo>();
		try {
			resultAdminRequestVo = adminDao.getadminboardrequest(a_num);
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("adminboardrequestlist", (T) resultAdminRequestVo);
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/deleteadminboardrequest", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteadminboardrequest(@RequestParam("a_num") int a_num,
			@RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			adminDao.deleteadminboardrequest(a_num, t_num);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/getChangeTeamName", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getChangeTeamName() {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<RequestChangeTeamNameVo> resultRequestChangeTeamNameVo = adminDao.getChangeTeamName();

		try {
			if (resultRequestChangeTeamNameVo != null) {
				resultHashMap.put("info", (T) resultRequestChangeTeamNameVo);
				resultHashMap.put("result", (T) "Success");

			} else {
				resultHashMap.put("info", (T) null);
				resultHashMap.put("result", (T) "Success");

			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			resultHashMap.put("info", (T) null);
			return resultHashMap;
		}
		return resultHashMap;

	}

	public String writeFile(String file, String savePath, int a_num, int a_index) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = dateFormat.format(calendar.getTime()).toString();
		String a_photo_url = a_num + "_" + a_index +"_"+ time+".jpg";
	
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + a_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return filePath + a_photo_url;
	}

	public boolean deleteFile(String filePath) throws Exception {

		File delete_file = new File("C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/" + filePath);
		if (!delete_file.delete())
			return false;

		return true;
	}

	@RequestMapping(value = "/permitChangeTeamName", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> permitChangeTeamName(@RequestBody JSONObject json) throws Exception {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = json.toString();
			JSONObject obj = (JSONObject) new JSONParser().parse(s);
			JSONArray jsonarr = (JSONArray) obj.get("permitlist");

			if (jsonarr != null) {
				for (int i = 0; i < jsonarr.size(); i++) {
					JSONObject jsonobj = (JSONObject) jsonarr.get(i);
					// RequestChangeTeamNameVo =
					// mapper.readValue(jsonarr_tostring,
					// RequestChangeTeamNameVo.class);
					int t_num = Integer.parseInt(jsonobj.get("t_num").toString());
					adminDao.setNewTeamName(t_num);
					adminDao.permitChangeTeamName(t_num);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

		resultHashMap.put("result", (T) "Success");
		return resultHashMap;
	}

	@RequestMapping(value = "/rejectChangeTeamName", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> rejectChangeTeamName(@RequestBody JSONObject json) throws Exception {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = json.toString();
			JSONObject obj = (JSONObject) new JSONParser().parse(s);
			JSONArray jsonarr = (JSONArray) obj.get("rejectlist");

			if (jsonarr != null) {
				for (int i = 0; i < json.size(); i++) {
					JSONObject jsonobj = (JSONObject) jsonarr.get(i); 
					// RequestChangeTeamNameVo =
					// mapper.readValue(jsonarr_tostring,
					// RequestChangeTeamNameVo.class);
					adminDao.rejectChangeTeamName(Integer.parseInt(jsonobj.get("t_num").toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

		resultHashMap.put("result", (T) "Success");
		return resultHashMap;
	}

	@RequestMapping(value = "/getAdminMenuTeamList", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getAdminMenuTeamList() {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<AdminMenuTeamVo> resultVo = new ArrayList<AdminMenuTeamVo>();

		try {
			resultVo = adminDao.getAdminMenuTeamList();
			if (resultVo != null) {
				resultHashMap.put("teamlist", (T) resultVo);
				resultHashMap.put("result", (T) "Success");
			} else {
				resultHashMap.put("result", (T) "Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

	@RequestMapping(value = "/deleteTeam", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> deleteTeam(@RequestBody JSONObject json) throws Exception {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {

			ObjectMapper mapper = new ObjectMapper();
			String s = json.toString();
			JSONObject obj = (JSONObject) new JSONParser().parse(s);
			JSONArray jsonarr = (JSONArray) obj.get("teamlist");

			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonobj = (JSONObject) jsonarr.get(i);
				// RequestChangeTeamNameVo = mapper.readValue(jsonarr_tostring,
				// RequestChangeTeamNameVo.class);
				adminDao.deleteTeam(Integer.parseInt(jsonobj.get("t_num").toString()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

		resultHashMap.put("result", (T) "Success");
		return resultHashMap;

	}
	
	
	@RequestMapping(value = "/getAdminReportList", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getAdminReportList(@RequestParam("year") int year, @RequestParam("month") int month) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<AdminReportListVo> resultVo = new ArrayList<AdminReportListVo>();

		try {
			resultVo = adminDao.getReportList(year, month);
			if (resultVo != null) {
				resultHashMap.put("teamresultlist", (T) resultVo);
				resultHashMap.put("result", (T) "Success");
			} else {
				resultHashMap.put("result", (T) "Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}
	
	
	@RequestMapping(value = "/getReportOfTeam", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getReportOfTeam(@RequestParam("year") int year, @RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<ReportVo> resultVo = new ArrayList<ReportVo>();

		try {
			resultVo = adminDao.getReportOfTeam(year, t_num);
			if (resultVo != null) {
				resultHashMap.put("reportlist", (T) resultVo);
				resultHashMap.put("result", (T) "Success");
			} else {
				resultHashMap.put("result", (T) "Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

}