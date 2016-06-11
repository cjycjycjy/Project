package com.myserver.skp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myserver.skp.vo.QuestionVo;

@Repository
public interface QuestionMapper {
	public List<QuestionVo> selectAllofQuestion(@Param("g_id") int g_id);
	public void updateQuestionReplyNumAdd(@Param("q_id") int q_id);
	public void updateQuestionReplyNumSub(@Param("q_id") int q_id);
	public void insertQuestion(QuestionVo questionVo);
	public String selectQuestionUId(@Param("q_id") int q_id);
	public QuestionVo selectQuestion(@Param("q_id")int q_id);
	public void deleteQuestion(@Param("q_id")int q_id);
	public int selectGId(@Param("q_id")int q_id);
}
