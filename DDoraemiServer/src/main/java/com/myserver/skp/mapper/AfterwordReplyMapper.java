package com.myserver.skp.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.myserver.skp.vo.AfterwordReplyVo;

@Repository
public interface AfterwordReplyMapper {
	public <T> List<HashMap<String, T>> selectAllofAfterwordReply(@Param("a_id") int a_id);
	public void updateAfterwordReply(@Param("a_r_id") int a_r_id, @Param("a_r_content") String a_r_content);
	public void deleteAfterwordReply(@Param("a_r_id") int a_r_id);
	public void insertAfterwordReply(AfterwordReplyVo afterwordVo); 
	public void deleteAfterwordReplyofAfterword(@Param("a_id") int a_id);
}
