package com.fuck.hangang.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.executor.ReuseExecutor;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.ContestDao;
import com.fuck.hangang.util.GCMSender;
import com.fuck.hangang.vo.ContestPhotoVo;
import com.fuck.hangang.vo.ContestReplyOfReplyVo;
import com.fuck.hangang.vo.ContestReplyVo;
import com.fuck.hangang.vo.ContestVo;
import com.fuck.hangang.vo.NoticePhotoVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.QnaReplyOfReplyVo;
import com.fuck.hangang.vo.QnaVo;
import com.fuck.hangang.vo.QnareplyVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Controller
public class ContestController {
	@Resource
	ContestDao contestDao;
	String filePath = "photo/contest/";
	String savePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/contest/";

	@RequestMapping(value = "/registphotocontest", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> registphotocontest(@RequestBody JSONObject json) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		ContestVo contestVo = new ContestVo();

		ObjectMapper mapper = new ObjectMapper();
		try {
			contestVo = mapper.readValue(json.toString(), ContestVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<ContestPhotoVo> photo = contestVo.getPhoto();
			if (photo == null)
				contestDao.registcontest(contestVo);

			else {
				contestDao.registcontest(contestVo);
				String contestphotourl = null;
				for (int i = 0; i < photo.size(); i++) {
					contestphotourl = writeFile(photo.get(i).getPhoto(), savePath, contestVo.getP_num(), i);
					contestDao.registcontestphoto(contestVo.getP_num(), i, contestphotourl);
				}

			}

			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/updatecontest", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> updatecontest(@RequestBody JSONObject json) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		ContestVo contestVo = new ContestVo();
		List<ContestPhotoVo> photo;
		try {

			ObjectMapper mapper = new ObjectMapper();
			contestVo = mapper.readValue(json.toString(), ContestVo.class);


			photo = contestVo.getPhoto();
			List<ContestPhotoVo> del_photo = contestDao.getcontestphoto(contestVo.getP_num());
			deleteFile(del_photo, savePath);
			contestDao.deletecontestphoto(contestVo.getP_num());

			String contestphotourl = null;
			for (int i = 0; i < photo.size(); i++) {
				contestphotourl = writeFile(photo.get(i).getPhoto(), savePath, contestVo.getP_num(), i);
				photo.get(i).setPhoto(contestphotourl);
				contestDao.registcontestphoto(contestVo.getP_num(), i, contestphotourl);
			}


			contestDao.updatephotocontest(contestVo);

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T)"Failed");
			return resultHashMap;
		}


		resultHashMap.put("photourl", (T)photo);
		resultHashMap.put("result", (T)"Success");
		return resultHashMap;


	}

	@RequestMapping(value = "/getphotocontest", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getphotocontest(@RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			if (lastnum == -1) {
				List<ContestVo> resultContestVo = contestDao.getcontestfirst();
				for (int i = 0; i < resultContestVo.size(); i++) {
					int r_count = contestDao.getcontestreplycount(resultContestVo.get(i).getP_num());
					resultContestVo.get(i).setR_count(r_count);
				}
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("contestlist", (T) resultContestVo);
				return resultHashMap;
			} else {
				List<ContestVo> resultContestVo = contestDao.getcontestlast(lastnum);
				for (int i = 0; i < resultContestVo.size(); i++) {
					int r_count = contestDao.getcontestreplycount(resultContestVo.get(i).getP_num());
					resultContestVo.get(i).setR_count(r_count);
				}
				resultHashMap.put("result", (T) "x1Success");
				resultHashMap.put("contestlist", (T) resultContestVo);
				return resultHashMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/getphotocontestreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getphotocontestreply(@RequestParam("num") int num,
			@RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {

			if (lastnum == -1) {
				List<ContestReplyVo> resultContestReplyVo = contestDao.getphotocontestreplyfirst(num);
				for (int i = 0; i < resultContestReplyVo.size(); i++) {
					int rr_count = contestDao.getcontestreplyofreplycount(resultContestReplyVo.get(i).getNum(),
							resultContestReplyVo.get(i).getR_num());
					resultContestReplyVo.get(i).setRr_count(rr_count);
				}

				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("photocontestreplylist", (T) resultContestReplyVo);
				return resultHashMap;
			} else {
				List<ContestReplyVo> resultContestReplyVo = contestDao.getphotocontestreplylast(num, lastnum);
				for (int i = 0; i < resultContestReplyVo.size(); i++) {
					int rr_count = contestDao.getcontestreplyofreplycount(resultContestReplyVo.get(i).getNum(),
							resultContestReplyVo.get(i).getR_num());
					resultContestReplyVo.get(i).setRr_count(rr_count);
				}
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("photocontestreplylist", (T) resultContestReplyVo);
				return resultHashMap;
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/getphotocontestreplyone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getphotocontestreplyone(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num, @RequestParam("pm_num") int pm_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		ContestReplyVo resultContestReplyVo = contestDao.getphotocontestreplyone(num, r_num);

		if (resultContestReplyVo != null) {
			int rr_count = contestDao.getcontestreplyofreplycount(resultContestReplyVo.getNum(),
					resultContestReplyVo.getR_num());
			resultContestReplyVo.setRr_count(rr_count);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("comment", (T) resultContestReplyVo);

			List<ContestReplyOfReplyVo> resultContestReplyOfReplyVo = contestDao.getphotocontestreplyofreplyfirst(num,
					r_num);

			resultHashMap.put("commentofcomment", (T) resultContestReplyOfReplyVo);
			contestDao.updatePushisChecked(pm_num);
			// resultHashMap.put("cheerfulreply",(T) replyVo );
		} else {
			resultHashMap.put("result", (T) "Failed");
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/getphotocontestone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getcheerfulone(@RequestParam("num") int num, @RequestParam("pm_num") int pm_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		ContestVo resultContestVo = contestDao.getContestOne(num);

		if (resultContestVo != null) {
			int r_count = contestDao.getcontestreplycount(resultContestVo.getP_num());
			resultContestVo.setR_count(r_count);

			contestDao.updatePushisChecked(pm_num);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("photocontest", (T) resultContestVo);

			// resultHashMap.put("cheerfulreply",(T) replyVo );
		} else {
			resultHashMap.put("result", (T) "Failed");
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/registphotocontestreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registphotocontestreply(@RequestParam("num") int num,
			@RequestParam("u_id") String u_id, @RequestParam("r_content") String r_content) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {

			contestDao.registphotocontestreply(num, u_id, r_content);

			String r_count = Integer.toString(contestDao.getcontestreplycount(num));
			List<ContestReplyVo> resultContestReplyVo = contestDao.getphotocontestreplyfirst(num);

			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("photocontestreplylist", (T) resultContestReplyVo);
			resultHashMap.put("r_count", (T) r_count);

		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}

		String id = contestDao.checkReplyUser(num);
		PushVo pushVo = new PushVo();
		pushVo.setU_id(id); pushVo.setReply_writer(u_id);
		pushVo.setFlag(7); pushVo.setNum(num);

		if(!id.equalsIgnoreCase(u_id))
			contestDao.registPushListAboutReply(pushVo);
		int push = contestDao.checkPushUser(id);
		if (push != 0) {

			if (!id.equalsIgnoreCase(u_id)) {
				RegistrationIdVo resultRegiId = contestDao.getRegiIdOfUser(num);
				GCMSender gs = new GCMSender();
				gs.sendGCMToOneUser(resultRegiId, num, 7, pushVo.getPm_num());
			}
		}

		return resultHashMap;

	}

	@RequestMapping(value = "/getphotocontestreplyofreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getphotocontestreplyofreply(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num, @RequestParam("lastnum") int lastnum) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {

			if (lastnum == -1) {
				List<ContestReplyOfReplyVo> resultContestReplyOfReplyVo = contestDao
						.getphotocontestreplyofreplyfirst(num, r_num);
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("photocontestreplyofreplylist", (T) resultContestReplyOfReplyVo);
				return resultHashMap;
			} else {
				List<ContestReplyOfReplyVo> resultContestReplyOfReplyVo = contestDao
						.getphotocontestreplyofreplylast(num, r_num, lastnum);
				resultHashMap.put("result", (T) "Success");
				resultHashMap.put("photocontestreplyofreplylist", (T) resultContestReplyOfReplyVo);
				return resultHashMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/registphotocontestreplyofreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> registphotocontestreplyofreply(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num, @RequestParam("u_id") String u_id,
			@RequestParam("rr_content") String rr_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {

			contestDao.registphotocontestreplyofreply(num, r_num, u_id, rr_content);

			List<ContestReplyOfReplyVo> resultContestReplyOfReplyVo = contestDao.getphotocontestreplyofreplyfirst(num,
					r_num);
			String rr_count = Integer.toString(contestDao.getcontestreplyofreplycount(num, r_num));
			resultHashMap.put("result", (T) "Success");
			resultHashMap.put("photocontestreplyofreplylist", (T) resultContestReplyOfReplyVo);
			resultHashMap.put("rr_count", (T) rr_count);

			String id = contestDao.checkReplyOfReplyUser(num, r_num);
			PushVo pushVo = new PushVo();
			pushVo.setU_id(id); pushVo.setReply_writer(u_id);
			pushVo.setFlag(8); pushVo.setNum(num); pushVo.setReply_num(r_num);

			if(!id.equalsIgnoreCase(u_id))
				contestDao.registPushListAboutReplyOfReply(pushVo);
			int push = contestDao.checkPushUser(id);
			if (push != 0) {
				if (!id.equalsIgnoreCase(u_id)) {
					RegistrationIdVo resultRegiId = contestDao.getRegiIdOfUserComment(r_num);
					GCMSender gs = new GCMSender();
					gs.sendGCMToOneUserAboutCommentOfComment(resultRegiId, num, r_num, 8, pushVo.getPm_num());
				}
			}

			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Error");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/updatephotocontestreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updatephotocontestreply(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num, @RequestParam("r_content") String r_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			contestDao.updatephotocontestreply(num, r_num, r_content);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/updatephotocontestreplyofreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> updatephotocontestreplyofreply(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num, @RequestParam("rr_num") int rr_num,
			@RequestParam("rr_content") String rr_content) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			contestDao.updatephotocontestreplyofreply(num, r_num, rr_num, rr_content);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	@RequestMapping(value = "/deletephotocontestreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletephotocontestreply(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			contestDao.deletephotocontestreply(num, r_num);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

	@RequestMapping(value = "/deletephotocontestreplyofreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletephotocontestreplyofreply(@RequestParam("num") int num,
			@RequestParam("r_num") int r_num, @RequestParam("rr_num") int rr_num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			contestDao.deletephotocontestreplyofreply(num, r_num, rr_num);
			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;

	}

	@RequestMapping(value = "/deletephotocontest", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletephotocontest(@RequestParam("num") int num) {

		HashMap<String, T> resultHashMap = new HashMap<String, T>();

		try {
			List<ContestPhotoVo> photo = contestDao.getcontestphoto(num);
			deleteFile(photo, savePath);
			contestDao.deletephotocontest(num);

			resultHashMap.put("result", (T) "Success");
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
		return resultHashMap;
	}

	public String writeFile(String file, String savePath, int p_num, int i) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = dateFormat.format(calendar.getTime()).toString();
		String p_photo_url = p_num + "_" + i + "_"+time+".jpg";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + p_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();

		return filePath + p_photo_url;
	}

	public boolean deleteFile(List<ContestPhotoVo> delete_photo_path, String savePath) throws Exception {

		for (int i = 0; i < delete_photo_path.size(); i++) {
			File delete_file = new File(
					"C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/" + delete_photo_path.get(i).getPhoto());
			if (!delete_file.delete())
				return false;
		}
		return true;
	}

}