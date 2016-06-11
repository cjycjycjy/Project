package com.myserver.skp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.QuestionReplyMapper;
import com.myserver.skp.vo.QuestionReplyVo;

@Service
public class QuestionReplyDao {
	@Resource
	QuestionReplyMapper questionReplyMapper;
	
	public List<QuestionReplyVo> getSelectAllofQuestionReply(int q_id) {
        return this.questionReplyMapper.selectAllofQuestionReply(q_id);
    }
	
	public void insertQuestionReply(QuestionReplyVo questionReplyVo){
		this.questionReplyMapper.insertQuestionReply(questionReplyVo);
	}
	
	public void updateQuestionReply(int q_r_id, String q_r_content){
		this.questionReplyMapper.updateQuestionReply(q_r_id, q_r_content);
	}
	
	public void deleteQuestionReply(int q_r_id){
		this.questionReplyMapper.deleteQuestionReply(q_r_id);
	}
	
	public void deleteQuestionReplyQId(int q_id){
		this.questionReplyMapper.deleteQuestionReplyQId(q_id);
	}
}
