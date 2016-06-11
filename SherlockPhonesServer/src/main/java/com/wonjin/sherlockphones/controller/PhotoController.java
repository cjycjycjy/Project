package com.wonjin.sherlockphones.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import com.wonjin.sherlockphones.dao.PhotoDao;
import com.wonjin.sherlockphones.vo.PhotoVo;

@Controller
public class PhotoController {

	@Resource
	PhotoDao photoDao;
	
	private String savePath = "D:\\Users\\im501\\workspace-sts-3.7.0.RELEASE\\SherlockPhones\\src\\main\\webapp\\photo\\";
	
	@RequestMapping(value = "/sendimage", method = RequestMethod.POST)
	public @ResponseBody String registerPhoto(@RequestBody JSONObject json) {
		System.out.println("aa");
		try {
			PhotoVo photoVo=null;
			ObjectMapper mapper = new ObjectMapper();

			photoVo = mapper.readValue(json.toString(), PhotoVo.class);
			photoDao.registerPhoto(photoVo.getP_u_phonenum(), writeFile(photoVo.getImage(), photoVo.getP_u_phonenum()));
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
		
		return "Success";
	}
	
	@RequestMapping(value = "/getimage", method = RequestMethod.POST)
	public @ResponseBody List<PhotoVo> getPhoto(@RequestParam("u_phonenum") String p_u_phonenum) {
		return this.photoDao.getSelectPhoto(p_u_phonenum);
	}
	
	
	public String writeFile(String file, String u_phonenum) throws Exception{
		String a_photo_url = u_phonenum+"_"+System.currentTimeMillis()+".jpg";
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
