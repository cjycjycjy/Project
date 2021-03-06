package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.ContestPhotoVo;
import com.fuck.hangang.vo.ContestReplyOfReplyVo;
import com.fuck.hangang.vo.ContestReplyVo;
import com.fuck.hangang.vo.ContestVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Repository
public interface ContestMapper {
	
	public void registcontest(ContestVo contestVo);
	public void registcontestphoto(@Param("p_num") int p_num, @Param("p_index") int p_index, @Param("photo") String photo);
	public List<ContestVo> getcontestfirst();
	public List<ContestVo> getcontestlast(@Param("lastnum") int lastnum);
	public List<ContestPhotoVo> getcontestphoto(@Param("p_num") int p_num);
	public int getcontestreplycount(@Param("num") int num);
	public int getcontestreplyofreplycount(@Param("num") int num, @Param("r_num") int r_num);
	public List<ContestReplyVo> getphotocontestreplyfirst(@Param("num") int num);
	public List<ContestReplyVo> getphotocontestreplylast(@Param("num") int num, @Param("lastnum") int lastnum);
	public void registphotocontestreply(@Param("num") int num, @Param("u_id") String u_id, @Param("r_content") String r_content);
	public List<ContestReplyOfReplyVo> getphotocontestreplyofreplyfirst(@Param("num") int num, @Param("r_num") int r_num);
	public List<ContestReplyOfReplyVo> getphotocontestreplyofreplylast(@Param("num") int num, @Param("r_num") int r_num, @Param("lastnum") int lastnum);
	public void registphotocontestreplyofreply(@Param("num") int num, @Param("r_num") int r_num, @Param("u_id") String u_id, @Param("rr_content") 
	String rr_content);
	public void updatephotocontestreply(@Param("num") int num, @Param("r_num") int r_num, @Param("r_content") String r_content);
	public void updatephotocontestreplyofreply(@Param("num") int num, @Param("r_num") int r_num, @Param("rr_num") int rr_num, @Param("rr_content") String rr_content);
	public void deletephotocontestreply(@Param("num") int num, @Param("r_num") int r_num);
	public void deletephotocontestreplyofreply(@Param("num") int num, @Param("r_num") int r_num, @Param("rr_num") int rr_num);
	public void deletephotocontest(@Param("num") int num);
	
	
	public RegistrationIdVo getRegiIdOfUser(@Param("num") int num);
	public RegistrationIdVo getRegiIdOfUserComment(@Param("r_num") int r_num);
	public ContestVo getContestOne(@Param("num") int num);
	public String checkReplyUser(@Param("num") int num);
	public String checkReplyOfReplyUser(@Param("num") int num, @Param("r_num") int r_num);
	public int checkPushUser(@Param("id") String id);
	public ContestReplyVo getphotocontestreplyone(@Param("num") int num,@Param("r_num") int r_num);
	public void registPushListAboutReply(PushVo pushVo);
	public void registPushListAboutReplyOfReply(PushVo pushVo);
	
	public void updatePushisChecked(@Param("pm_num") int pm_num);
	public void deletecontestphoto(@Param("num") int num);
	public void updatecontestphoto(@Param("num") int num, @Param("p_index") int p_index, @Param("url") String url);
	public void updatephotocontest(ContestVo contestVo);
	
}
