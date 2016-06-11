package com.myserver.skp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myserver.skp.vo.QuestionReplyVo;

@Repository
public interface QuestionReplyMapper {
	public List<QuestionReplyVo> selectAllofQuestionReply(@Param("q_id") int q_id);
	public void updateQuestionReply(@Param("q_r_id") int q_r_id, @Param("q_r_content") String q_r_content);
	public void deleteQuestionReply(@Param("q_r_id") int q_r_id);
	public void insertQuestionReply(QuestionReplyVo questionReplyVo); 
	public void deleteQuestionReplyQId(@Param("q_id")int q_id);
}
