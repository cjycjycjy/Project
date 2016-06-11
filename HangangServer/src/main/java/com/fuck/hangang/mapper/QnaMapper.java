package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.QnaReplyOfReplyVo;
import com.fuck.hangang.vo.QnaVo;
import com.fuck.hangang.vo.QnareplyVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Repository
public interface QnaMapper {
	public List<QnaVo> getqnafirst();
	public List<QnaVo> getqnalast(@Param("lastnum") int lastnum);
	public int getqnareplycount(@Param("q_num") int q_num);
	public List<QnareplyVo> getqnareplyfirst(@Param("q_num") int q_num);
	public List<QnareplyVo> getqnareplylast(@Param("q_num") int q_num, @Param("lastnum") int lastnum);
	public int getqnareplyofreplycount(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num);
	public void registqna(@Param("u_id") String u_id, @Param("title") String title, @Param("content") String content);
	public void registqnareply(@Param("q_num") int q_num, @Param("u_id") String u_id, @Param("q_r_content") String q_r_content);
	public List<QnaReplyOfReplyVo> getqnareplyofreplyfirst(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num);
	public List<QnaReplyOfReplyVo> getqnareplyofreplylast(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num, @Param("lastnum") int lastnum);
	public void registqnareplyofreply(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num, @Param("u_id") String u_id,
			@Param("q_rr_content") String q_rr_content);
	public void updateqnareply(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num, @Param("q_r_content") String q_r_content);
	public void updateqnareplyofreply(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num, @Param("q_rr_num") int q_rr_num, @Param("q_rr_content") String q_rr_content);
	public void deleteqnareplyofreply(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num, @Param("q_rr_num") int q_rr_num);
	public void deleteqnareply(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num);
	public void deleteqna(@Param("q_num") int q_num);
	
	
	public RegistrationIdVo getRegiIdOfUser(@Param("q_num") int q_num);
	public RegistrationIdVo getRegiIdOfUserComment(@Param("q_r_num") int q_r_num);
	public QnaVo getQnAOne(@Param("q_num") int q_num);
	public String checkReplyUser(@Param("q_num") int q_num);
	public String checkReplyOfReplyUser(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num);
	public int checkPushUser(@Param("id") String id);
	public QnareplyVo getqnareplyone(@Param("q_num") int q_num, @Param("q_r_num") int q_r_num);
	
	public void registPushListAboutReply(PushVo pushVo);
	public void registPushListAboutReplyOfReply(PushVo pushVo);
	
	public void updatePushisChecked(@Param("pm_num") int pm_num);
	public void updateqna(@Param("q_num") int q_num, @Param("title") String title, @Param("content") String content);
}
