package com.myserver.skp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.AfterwordDao;
import com.myserver.skp.dao.AfterwordReplyDao;
import com.myserver.skp.dao.GatheringDao;
import com.myserver.skp.dao.ProgramDao;
import com.myserver.skp.vo.AfterwordReplyVo;
import com.myserver.skp.vo.AfterwordVo;
import com.myserver.skp.vo.UserVo;
import com.mysql.jdbc.util.Base64Decoder;


@Controller
public class AfterwordController {
	private static final Logger logger = LoggerFactory.getLogger(AfterwordController.class);
	private String savePath = "D:\\Users\\im501\\workspace-sts-3.7.0.RELEASE\\SPK\\src\\main\\webapp\\afterword_photo\\";
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Resource
	private AfterwordDao afterwordDao;

	@Resource
	private AfterwordReplyDao afterwordReplyDao;

	@Resource
	private GatheringDao gatheringDao;

	@Resource
	private ProgramDao programDao;

	@RequestMapping(value = "/getafterword", method = RequestMethod.GET)
	public @ResponseBody <T> JSONObject getAfterword(@RequestParam("p_id") int p_id) {
		JSONObject json = new JSONObject();
		List<HashMap<String, T>> afterwordList = afterwordDao.getSelectAllofAfterword(p_id);
		for(int i=0; i<afterwordList.size(); i++){
			int a_id = (Integer)afterwordList.get(i).get("a_id");
			afterwordList.get(i).put("afterword_reply",(T)afterwordReplyDao.getSelectAllofAfterwordReply(a_id));
			afterwordList.get(i).put("a_photo_url", (T)afterwordDao.getSelectAfterwordPhotoUrl(a_id));
		}

		List<HashMap<String, String>> gatheringList = this.gatheringDao.getSelectAllofGathering(p_id);
		json.put("gathering", gatheringList);
		json.put("afterword", afterwordList);

		return json;
	}

	@RequestMapping(value = "/registerafterwordreply", method = RequestMethod.GET)
	public @ResponseBody <T> List<HashMap<String, T>> registerAfterwordreply(@RequestParam("u_id") String u_id,
			@RequestParam("a_id") int a_id,
			@RequestParam("a_r_content") String a_r_content) {

		GregorianCalendar today = new GregorianCalendar();
		int a_r_year = today.get ( today.YEAR );
		int a_r_month = today.get ( today.MONTH ) + 1;
		int a_r_day = today.get ( today.DAY_OF_MONTH ); 

		AfterwordReplyVo afterwordReplyVo = new AfterwordReplyVo(u_id, a_id, a_r_content, a_r_year, a_r_month, a_r_day);

		afterwordReplyDao.insertAfterwordReply(afterwordReplyVo);
		afterwordDao.updateAfterwordReplyNumAdd(a_id);


		List<HashMap<String, T>> afterwordReplyList = afterwordReplyDao.getSelectAllofAfterwordReply(a_id);

		return afterwordReplyList;
	}

	@RequestMapping(value = "/modifyafterwordreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> modifyAfterwordReply(@RequestParam("a_id") int a_id, @RequestParam("a_r_id") int a_r_id,
			@RequestParam("a_r_content") String a_r_content) {
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			afterwordReplyDao.updateAfterwordReply(a_r_id, a_r_content);
			result.put("result", (T)"200");
			List<HashMap<String, T>> afterwordReplyList = afterwordReplyDao.getSelectAllofAfterwordReply(a_id);
			result.put("afterword_reply", (T)afterwordReplyList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;

	}

	@RequestMapping(value = "/deleteafterwordreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteAfterwordreply(@RequestParam("a_id") int a_id, @RequestParam("a_r_id") int a_r_id) {
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			afterwordReplyDao.deleteAfterwordReply(a_r_id);
			afterwordDao.updateAfterwordReplyNumSub(a_id);
			result.put("result", (T)"200");
			List<HashMap<String, T>> afterwordReplyList = afterwordReplyDao.getSelectAllofAfterwordReply(a_id);
			result.put("afterword_reply", (T)afterwordReplyList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}

	@RequestMapping(value = "/registerafterword", method = RequestMethod.POST)
	public @ResponseBody String registerAfterword(@RequestBody JSONObject json){
		try{
			AfterwordVo afterwordVo=null;
			ObjectMapper mapper = new ObjectMapper();

			afterwordVo = mapper.readValue(json.toString(), AfterwordVo.class);

			GregorianCalendar today = new GregorianCalendar();
			afterwordVo.setA_year(today.get ( today.YEAR ));
			afterwordVo.setA_month(today.get ( today.MONTH ) + 1);
			afterwordVo.setA_day(today.get ( today.DAY_OF_MONTH )); 

			afterwordDao.insertAfterword(afterwordVo);

			List<HashMap<String, String>> photo = afterwordVo.getPhoto();

			for(int i=0; i<photo.size(); i++)
				afterwordDao.insertAfterwordPhoto(afterwordVo.getA_id(), writeFile(photo.get(i).get("photo"), afterwordVo.getU_id(), i));


			float p_grade = afterwordDao.getSelectAVGofProgram(afterwordVo.getG_id());

			programDao.updateProgramGrade(afterwordVo.getG_id(), p_grade);

			return "200";


		}  catch (Exception e){
			return "500";
		}

	}

	@RequestMapping(value = "/modifyafterword", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> modifyAfterword(@RequestBody JSONObject json){
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			boolean photochange = json.get("photochange").equals(true);
			json.remove("photochange");
			AfterwordVo afterwordVo=null;
			ObjectMapper mapper = new ObjectMapper();

			afterwordVo = mapper.readValue(json.toString(), AfterwordVo.class);

			afterwordDao.updateAfterword(afterwordVo);

			List<HashMap<String, String>> photo = afterwordVo.getPhoto();



			if(photochange){
				deleteFile(afterwordDao.getSelectAfterwordPhotoUrl(afterwordVo.getA_id()));
				afterwordDao.deleteAfterwordPhoto(afterwordVo.getA_id());

				if(photo!=null)
					for(int i=0; i<photo.size(); i++)
						afterwordDao.insertAfterwordPhoto(afterwordVo.getA_id(), writeFile(photo.get(i).get("photo"), afterwordVo.getU_id(), i));
			}

			HashMap<String,T> afterword = afterwordDao.getSelectAfterword(afterwordVo.getA_id());

			int g_id = (Integer)afterword.get("g_id");

			afterword.put("a_photo_url", (T)afterwordDao.getSelectAfterwordPhotoUrl(afterwordVo.getA_id()));



			float p_grade = afterwordDao.getSelectAVGofProgram(g_id);

			programDao.updateProgramGrade(g_id, p_grade);

			result.put("result", (T)"200");
			result.put("afterword", (T)afterword);

		}catch(Exception e){
			e.printStackTrace();
			result.put("result", (T)"500");
		}

		return result;
	}

	@RequestMapping(value = "/deleteafterword", method = RequestMethod.GET)
	public @ResponseBody String deleteAfterword(@RequestParam("a_id") int a_id){
		try{
			afterwordReplyDao.deleteAfterwordReplyofAfterword(a_id);
			deleteFile(afterwordDao.getSelectAfterwordPhotoUrl(a_id));
			afterwordDao.deleteAfterwordPhoto(a_id);
			afterwordDao.deleteAfterword(a_id);
			return "200";
		}catch(Exception e){
			return "500";
		}
	}
	
	@RequestMapping(value = "/getafterwordone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getAfterwordOne(@RequestParam("a_id") int a_id){
		HashMap<String,T> result = new HashMap<String, T>();
		try{
			HashMap<String,T> afterword = afterwordDao.getSelectAfterword(a_id);
			result.put("result", (T)"200");
			result.put("afterword", (T)afterword);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}

	public String writeFile(String file, String u_id, int num) throws Exception{
		String a_photo_url = u_id+"_"+System.currentTimeMillis()+"_"+num+".jpg";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath+a_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return a_photo_url;
	}

	public boolean deleteFile(List<HashMap<String, String>> delete_photo_path) throws Exception{
		for(int i=0; i<delete_photo_path.size(); i++){
			File delete_file = new File(savePath+delete_photo_path.get(i).get("a_photo_url"));
			if(!delete_file.delete())
				return false;
		}
		return true;
	}
}