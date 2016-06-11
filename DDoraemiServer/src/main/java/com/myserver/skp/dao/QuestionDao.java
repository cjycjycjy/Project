package com.myserver.skp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.QuestionMapper;
import com.myserver.skp.vo.QuestionVo;

@Service
public class QuestionDao {
	@Resource
	QuestionMapper questionMapper;
	
	public List<QuestionVo> getSelectAllofQuestion(int g_id) {
        return this.questionMapper.selectAllofQuestion(g_id);
    }
	
	public void updateQuestionReplyNumAdd(int q_id) {
        this.questionMapper.updateQuestionReplyNumAdd(q_id);
    }
	
	public void updateQuestionReplyNumSub(int q_id) {
        this.questionMapper.updateQuestionReplyNumSub(q_id);
    }
	
	public void insertQeustion(QuestionVo questionVo){
		this.questionMapper.insertQuestion(questionVo);
	}
	
	public String getSelectQuestionUId(int q_id){
		return this.questionMapper.selectQuestionUId(q_id);
	}
	
	public QuestionVo getSelectQuestion(int q_id){
		return this.questionMapper.selectQuestion(q_id);
	}
	
	public void deleteQuestion(int q_id){
		this.questionMapper.deleteQuestion(q_id);
	}
	
	public int getSelectGId(int q_id){
		return this.questionMapper.selectGId(q_id);
	}
	
}