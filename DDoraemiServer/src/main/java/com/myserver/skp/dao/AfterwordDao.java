package com.myserver.skp.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.AfterwordMapper;
import com.myserver.skp.vo.AfterwordVo;

@Service
public class AfterwordDao {
	@Resource
	AfterwordMapper afterwordMapper;
	
	public <T> List<HashMap<String, T>> getSelectAllofAfterword(int p_id) {
        return this.afterwordMapper.selectAllofAfterword(p_id);
    }
	
	public void updateAfterwordReplyNumAdd(int a_id) {
        this.afterwordMapper.updateAfterwordReplyNumAdd(a_id);
    }
	
	public void updateAfterwordReplyNumSub(int a_id) {
        this.afterwordMapper.updateAfterwordReplyNumSub(a_id);
    }
	
	public void insertAfterword(AfterwordVo afterwordVo){
		this.afterwordMapper.insertAfterword(afterwordVo);
	}
	
	public List<HashMap<String, String>> getSelectAfterwordPhotoUrl(int a_id){
		return this.afterwordMapper.selectAfterwordPhotoUrl(a_id);
	}
	
	public void insertAfterwordPhoto(int a_id, String a_photo_url){
		this.afterwordMapper.insertAfterwordPhoto(a_id, a_photo_url);
	}
	
	public float getSelectAVGofProgram(int g_id){
		return this.afterwordMapper.selectAVGofProgram(g_id);
	}
	
	public void updateAfterword(AfterwordVo afterwordVo){
		this.afterwordMapper.updateAfterword(afterwordVo);
	}
	
	public void deleteAfterword(int a_id){
		this.afterwordMapper.deleteAfterword(a_id);
	}
	
	public void deleteAfterwordPhoto(int a_id){
		this.afterwordMapper.deleteAfterwordPhoto(a_id);
	}
	
	public void updateAfterwordPhoto(int a_id, String a_photo_url){
		this.afterwordMapper.updateAfterwordPhoto(a_id, a_photo_url);
	}
	
	public <T> HashMap<String, T> getSelectAfterword(int a_id){
		return this.afterwordMapper.selectAfterword(a_id);
	}
}
