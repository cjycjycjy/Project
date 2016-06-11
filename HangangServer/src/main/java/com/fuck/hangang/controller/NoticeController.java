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
import com.fuck.hangang.util.GCMSender;
import com.fuck.hangang.vo.NoticePhotoVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.NoticecommentVo;
import com.fuck.hangang.vo.NoticecommentofcommentVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Controller
public class NoticeController {
	@Resource
	private NoticeDao noticeDao;

	String filePath = "photo/notice/";
	String savePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/notice/";

	@RequestMapping(value = "/getnotice", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getNotice(@RequestParam("lastnum") int lastnum,
			@RequestParam("n_flag") int n_flag) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<NoticeVo> resultNoticeVo = new ArrayList<NoticeVo>();

		if (lastnum == -1) {

			if (n_flag == -1) {
				resultNoticeVo = noticeDao.getnoticefirstnoflag();
			} else {
				resultNoticeVo = noticeDao.getnoticefirst(n_flag);
			}
		}

		else {

			if (n_flag == -1) {
				resultNoticeVo = noticeDao.getnoticelastnoflag(lastnum);
			} else {
				resultNoticeVo = noticeDao.getnoticelast(lastnum, n_flag);
			}

		}

		for (int i = 0; i < resultNoticeVo.size(); i++) {
			int count = noticeDao.getnoticecommentcount(resultNoticeVo.get(i).getN_num());
			resultNoticeVo.get(i).setN_c_num(count);
		}
		resultHashMap.put("result", (T) "Success");
		resultHashMap.put("noticelist", (T) resultNoticeVo);
		return resultHashMap;

	}

	@RequestMapping(value = "/getnoticeone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getnoticeone(@RequestParam("n_num") int n_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		NoticeVo resultNoticeVo = noticeDao.getnoticeone(n_num);

		if (resultNoticeVo != null) {
			int count = noticeDao.getnoticecommentcount(resultNoticeVo.getN_num());
			resultNoticeVo.setN_c_num(count);
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("notice", (T) resultNoticeVo);
		} else {
			resultHashMap.put("result", (T) "Failed");
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/registnotice", method = RequestMethod.POST)
	public @ResponseBody String registNotice(@RequestBody JSONObject json) {
		NoticeVo noticeVo = new NoticeVo();
		try {

			ObjectMapper mapper = new ObjectMapper();
			noticeVo = mapper.readValue(json.toString(), NoticeVo.class);

			if (noticeVo.getPhoto().size() == 0) {
				noticeDao.registnotice(noticeVo);
			}

			else {
				
				noticeDao.registnotice(noticeVo);
				List<NoticePhotoVo> photo = noticeVo.getPhoto();

				for (int i = 0; i < photo.size(); i++) {
					String url = writeFile(photo.get(i).getN_photo_url(), savePath, noticeVo.getN_num(), i);
					photo.get(i).setN_photo_url(filePath + noticeVo.getN_num() + "_" + i + ".jpg");
					noticeDao.registnoticephoto(noticeVo.getN_num(), photo.get(i).getN_index(),
							filePath+url);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}


		PushVo pushVo = new PushVo();
		pushVo.setFlag(1);
		pushVo.setNum(noticeVo.getN_num());
		noticeDao.registPushListAboutNotice(pushVo);

		List<RegistrationIdVo> resultRegiId = noticeDao.getAllRegiId();
		GCMSender gs = new GCMSender();
		gs.sendGCMToAllUser(resultRegiId, noticeVo.getN_num(), pushVo.getPm_num());

		return "Success";

	}

	@RequestMapping(value = "/updatenotice", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> updateNotice(@RequestBody JSONObject json) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		NoticeVo noticeVo = new NoticeVo();
		List<NoticePhotoVo> photo;
		try {

			ObjectMapper mapper = new ObjectMapper();
			noticeVo = mapper.readValue(json.toString(), NoticeVo.class);


			photo = noticeVo.getPhoto();
			List<NoticePhotoVo> del_photo = noticeDao.getnoticephoto(noticeVo.getN_num());
			deleteFile(del_photo, savePath);
			noticeDao.deletenoticephoto(noticeVo.getN_num());
			if(photo.size()!=0){
				
				for (int i = 0; i < photo.size(); i++) {
					String url = writeFile(photo.get(i).getN_photo_url(), savePath, noticeVo.getN_num(), i);
					photo.get(i).setN_photo_url(filePath + url);
					noticeDao.registnoticephoto(noticeVo.getN_num(), photo.get(i).getN_index(),
							filePath+url);
				}
			}
			noticeDao.updatenotice(noticeVo);

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T)"Failed");
			return resultHashMap;
		}
		

		resultHashMap.put("photourl", (T)photo);
		resultHashMap.put("result", (T)"Success");
		return resultHashMap;


	}

	@RequestMapping(value = "/getnoticecomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getnoticeComment(@RequestParam("n_num") int n_num,
			@RequestParam("lastnum") int lastnum) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<NoticecommentVo> resultNoticecommentVo = null;
		if (lastnum == -1) {
			lastnum = 0;
			int count = 20;
			resultNoticecommentVo = noticeDao.getnoticecommentfirst(n_num, lastnum, count);
		} else {
			resultNoticecommentVo = noticeDao.getnoticecomment(n_num, lastnum);
		}

		if (resultNoticecommentVo != null) {
			resultHashMap.put("result", (T) "Success");

			for (int i = 0; i < resultNoticecommentVo.size(); i++) {
				resultNoticecommentVo.get(i).setN_cc_count(noticeDao.getnoticecommentofcommentcount(
						resultNoticecommentVo.get(i).getN_num(), resultNoticecommentVo.get(i).getN_c_num()));
			}
			resultHashMap.put("n_commentlist", (T) resultNoticecommentVo);
			return resultHashMap;

		} else {
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/registnoticecomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registnoticeComment(@RequestParam("n_num") int n_num,
			@RequestParam("u_id") String u_id, @RequestParam("n_c_content") String n_c_content) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		int lastnum = 0;
		int count = lastnum + 20;

		try {

			noticeDao.registnoticecomment(n_num, u_id, n_c_content);

			List<NoticecommentVo> resultNoticecommentVo = noticeDao.getnoticecommentfirst(n_num, lastnum, count);

			if (resultNoticecommentVo != null) {
				for (int i = 0; i < resultNoticecommentVo.size(); i++) {

					resultNoticecommentVo.get(i).setN_cc_count(noticeDao.getnoticecommentofcommentcount(
							resultNoticecommentVo.get(i).getN_num(), resultNoticecommentVo.get(i).getN_c_num()));

				}

			}
			String commentcount = Integer
					.toString(noticeDao.getnoticecommentcount(resultNoticecommentVo.get(0).getN_num()));
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("n_commentlist", (T) resultNoticecommentVo);
			resultHashMap.put("n_c_count", (T) commentcount);

		} catch (Exception e) {
			resultHashMap.put("result", (T) "Error");
			e.printStackTrace();
			return resultHashMap;
		}
		return resultHashMap;
	}

	@RequestMapping(value = "/getnoticecommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getnoticecommentOfcomment(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num, @RequestParam("lastnum") int lastnum) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		List<NoticecommentofcommentVo> resultNoticecommentofcommentVo = null;
		if (lastnum == -1) {
			lastnum = 0;
			int count = lastnum + 20;
			resultNoticecommentofcommentVo = noticeDao.getnoticecommentofcommentfirst(n_num, n_c_num, lastnum, count);
		}

		else {

			resultNoticecommentofcommentVo = noticeDao.getnoticecommentofcomment(n_num, n_c_num, lastnum);
		}

		if (resultNoticecommentofcommentVo != null) {
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("n_commentofcommentlist", (T) resultNoticecommentofcommentVo);
			return resultHashMap;

		} else {
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/getnoticecommentone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getcheerfulone(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num, @RequestParam("pm_num") int pm_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		NoticecommentVo resultNoticecommentVo = noticeDao.getNoticeCommentOne(n_c_num, n_num);

		if (resultNoticecommentVo != null) {
			resultNoticecommentVo.setN_cc_count(noticeDao.getnoticecommentofcommentcount(
					resultNoticecommentVo.getN_num(), resultNoticecommentVo.getN_c_num()));

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("comment", (T) resultNoticecommentVo);

			List<NoticecommentofcommentVo> resultCommentOfCommentVo = noticeDao.getNoticeCommentOfCommentAll(n_num,
					n_c_num);

			resultHashMap.put("commentofcomment", (T) resultCommentOfCommentVo);
			noticeDao.updatePushisChecked(pm_num);
			// resultHashMap.put("cheerfulreply",(T) replyVo );
		} else {
			resultHashMap.put("result", (T) "Failed");
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/registnoticecommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registnoticecommentOfcomment(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num, @RequestParam("n_cc_content") String n_cc_content,
			@RequestParam("u_id") String u_id) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		int lastnum = 0;
		int count = lastnum + 20;

		try {

			noticeDao.registnoticecommentofcomment(n_num, n_c_num, n_cc_content, u_id);

			List<NoticecommentofcommentVo> resultNoticecommentofcommentVo = noticeDao
					.getnoticecommentofcommentfirst(n_num, n_c_num, lastnum, count);

			if (resultNoticecommentofcommentVo != null) {
				String commentofcommentcount = Integer.toString(
						noticeDao.getnoticecommentofcommentcount(resultNoticecommentofcommentVo.get(0).getN_num(),
								resultNoticecommentofcommentVo.get(0).getN_c_num()));

				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("n_commentofcommentlist", (T) resultNoticecommentofcommentVo);
				resultHashMap.put("n_cc_count", (T) commentofcommentcount);

			}

		} catch (Exception e) {
			resultHashMap.put("result", (T) "Error");
			e.printStackTrace();
			return resultHashMap;
		}

		String id = noticeDao.checkReplyUser(n_c_num);
		PushVo pushVo = new PushVo();
		pushVo.setU_id(id); pushVo.setReply_writer(u_id);
		pushVo.setFlag(9); pushVo.setNum(n_num); pushVo.setReply_num(n_c_num);
		if(!id.equalsIgnoreCase(u_id))
			noticeDao.registPushList(pushVo);
		int push = noticeDao.checkPushUser(id);
		if (push != 0) {
			if (!id.equalsIgnoreCase(u_id)) {
				RegistrationIdVo resultRegiId = noticeDao.getRegiIdOfUser(n_c_num);
				GCMSender gs = new GCMSender();
				gs.sendGCMToOneUserAboutCommentOfComment(resultRegiId, n_num, n_c_num, 9, pushVo.getPm_num());
			}
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/deletenotice", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletenotice(@RequestParam("n_num") int n_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			List<NoticePhotoVo> photo = noticeDao.getnoticephoto(n_num);
			deleteFile(photo, savePath);
			noticeDao.deletenotice(n_num);

			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

	@RequestMapping(value = "/deletenoticecomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletenoticeComment(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			noticeDao.deletenoticecomment(n_num, n_c_num);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

	@RequestMapping(value = "/deletenoticecommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletenoticecommentOfcomment(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num, @RequestParam("n_cc_num") int n_cc_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			noticeDao.deletenoticecommentofcomment(n_num, n_c_num, n_cc_num);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/updatenoticecomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updatenoticeComment(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num, @RequestParam("n_c_content") String n_c_content) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			noticeDao.updatenoticecomment(n_num, n_c_num, n_c_content);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/updatenoticecommentofcomment", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updatenoticeComment(@RequestParam("n_num") int n_num,
			@RequestParam("n_c_num") int n_c_num, @RequestParam("n_cc_num") int n_cc_num,
			@RequestParam("n_cc_content") String n_cc_content) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			noticeDao.updatenoticecommentofcomment(n_num, n_c_num, n_cc_num, n_cc_content);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;

	}

	public String writeFile(String file, String savePath, int n_num, int i) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = dateFormat.format(calendar.getTime()).toString();
		String n_photo_url = n_num + "_" + i +"_"+ time+".jpg";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + n_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return n_photo_url;
	}

	public boolean deleteFile(List<NoticePhotoVo> delete_photo_path, String savePath) throws Exception {
		for (int i = 0; i < delete_photo_path.size(); i++) {
			File delete_file = new File("C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/"
					+ delete_photo_path.get(i).getN_photo_url());
			if (!delete_file.delete())
				return false;
		}
		return true;
	}

}
