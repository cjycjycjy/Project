package com.myserver.skp.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.AfterwordReplyMapper;
import com.myserver.skp.vo.AfterwordReplyVo;

@Service
public class AfterwordReplyDao {
	@Resource
	AfterwordReplyMapper afterwordReplyMapper;
	
	public <T> List<HashMap<String, T>> getSelectAllofAfterwordReply(int a_id) {
        return this.afterwordReplyMapper.selectAllofAfterwordReply(a_id);
    }
	
	public void insertAfterwordReply(AfterwordReplyVo afterwordReplyVo){
		this.afterwordReplyMapper.insertAfterwordReply(afterwordReplyVo);
	}
	
	public void updateAfterwordReply(int a_r_id, String a_r_content){
		this.afterwordReplyMapper.updateAfterwordReply(a_r_id, a_r_content);
	}
	
	public void deleteAfterwordReply(int a_r_id){
		this.afterwordReplyMapper.deleteAfterwordReply(a_r_id);
	}
	
	public void deleteAfterwordReplyofAfterword(int a_id){
		this.afterwordReplyMapper.deleteAfterwordReplyofAfterword(a_id);
	}
	
}
