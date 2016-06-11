package com.wonjin.sherlockphones.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wonjin.sherlockphones.mapper.PhotoMapper;
import com.wonjin.sherlockphones.vo.PhotoVo;

@Service
public class PhotoDao {
	@Resource
	PhotoMapper photoMapper;
	
	public int registerPhoto(String p_u_phonenum, String p_photo_url){
		return this.photoMapper.registerPhoto(p_u_phonenum, p_photo_url);
	}
	
	public List<PhotoVo> getSelectPhoto(String p_u_phonenum){
		return this.photoMapper.selectPhoto(p_u_phonenum);
	}
	
}
