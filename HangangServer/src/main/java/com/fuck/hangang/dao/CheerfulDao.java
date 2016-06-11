package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.CheerfulMapper;
import com.fuck.hangang.vo.CheerfulReplyOfReplyVo;
import com.fuck.hangang.vo.CheerfulReplyVo;
import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;
import com.fuck.hangang.vo.UserVo;

@Service
public class CheerfulDao {
	@Resource
	private CheerfulMapper mapper;

	public List<CheerfulVo> getteamcheerfulfirst(int t_num, int lastnum, int count) {
		return this.mapper.getteamcheerfulfirst(t_num, lastnum, count);
	}

	public List<CheerfulVo> getteamcheerfullast(@Param("t_num") int t_num, @Param("lastnum") int lastnum) {
		return this.mapper.getteamcheerfullast(t_num, lastnum);
	}

	public List<CheerfulVo> gettotalcheerfulfirst(int lastnum, int count) {
		return this.mapper.gettotalcheerfulfirst(lastnum, count);
	}

	public List<CheerfulVo> gettotalcheerfullast(@Param("lastnum") int lastnum) {
		return this.mapper.gettotalcheerfullast(lastnum);
	}

	public int getcheerfulcommentnum(int c_num) {
		return this.mapper.getcheerfulcommentnum(c_num);
	}

	public void registcheerful(String u_id, int t_num, String ch_content, String ch_photo) {
		this.mapper.registcheerful(u_id, t_num, ch_content, ch_photo);
	}

	public UserVo getcheerfuluserinfo(String u_id) {
		return this.mapper.getcheerfuluserinfo(u_id);
	}

	public List<CheerfulReplyVo> getcheerfulreplyfirst(@Param("c_num") int c_num, @Param("lastnum") int lastnum,
			@Param("count") int count) {
		return this.mapper.getcheerfulreplyfirst(c_num, lastnum, count);
	}

	public List<CheerfulReplyVo> getcheerfulreplylast(@Param("c_num") int c_num, @Param("lastnum") int lastnum) {
		return this.mapper.getcheerfulreplylast(c_num, lastnum);
	}
	


	public void registcheerfulreply(@Param("c_num") int c_num, @Param("u_id") String u_id,
			@Param("c_r_content") String c_r_content) {
		this.mapper.registcheerfulreply(c_num, u_id, c_r_content);
	}

	public List<CheerfulReplyOfReplyVo> getcheerfulreplyofreplyfirst(@Param("c_num") int c_num,
			@Param("c_r_num") int c_r_num) {
		return this.mapper.getcheerfulreplyofreplyfirst(c_num, c_r_num);
	}

	public List<CheerfulReplyOfReplyVo> getcheerfulreplyofreplylast(@Param("c_num") int c_num,
			@Param("c_r_num") int c_r_num, @Param("lastnum") int lastnum) {
		return this.mapper.getcheerfulreplyofreplylast(c_num, c_r_num, lastnum);
	}
	

	public void registcheerfulreplyofreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("u_id") String u_id, @Param("c_rr_content") String c_rr_content) {
		this.mapper.registcheerfulreplyofreply(c_num, c_r_num, u_id, c_rr_content);
	}

	public void deletecheerfulreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num) {
		this.mapper.deletecheerfulreply(c_num, c_r_num);
	}

	public void deletecheerfulreplyofreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("c_rr_num") int c_rr_num) {
		this.mapper.deletecheerfulreplyofreply(c_num, c_r_num, c_rr_num);
	}

	public String getcheerfulphotourl(@Param("c_num") int c_num) {
		return this.mapper.getcheerfulphotourl(c_num);
	}

	public void deletecheerful(@Param("c_num") int c_num) {
		this.mapper.deletecheerful(c_num);
	}

	public void updatecheerfulreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("c_r_content") String c_r_content) {
		this.mapper.updatecheerfulreply(c_num, c_r_num, c_r_content);
	}

	public void updatecheerfulreplyofreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("c_rr_num") int c_rr_num, @Param("c_rr_content") String c_rr_content) {
		this.mapper.updatecheerfulreplyofreply(c_num, c_r_num, c_rr_num, c_rr_content);
	}
	
	public int getcheerfulreplycount(@Param("c_num") int c_num) {
		return this.mapper.getcheerfulreplycount(c_num);
	}
	
	public int getcheerfulreplyofreplycount(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num) {
		return this.mapper.getcheerfulreplyofreplycount(c_num, c_r_num);
	}
	public RegistrationIdVo getRegiIdOfUser(int c_num){
		return this.mapper.getRegiIdOfUser(c_num);
	}
	public RegistrationIdVo getRegiIdOfUserComment(int c_r_num){
		return this.mapper.getRegiIdOfUserComment(c_r_num);
	}
	public CheerfulVo getcheerfulOne(int c_num){
		return this.mapper.getcheerfulOne(c_num);
	}
	public String checkReplyUser(int c_num){
		return this.mapper.checkReplyUser(c_num);
	}
	public String checkReplyOfReplyUser(int c_num, int c_r_num) {
		return this.mapper.checkReplyOfReplyUser(c_num, c_r_num);
	}
	public int checkPushUser(String id) {
		return this.mapper.checkPushUser(id);
	}
	public CheerfulReplyVo getcheerfulreplyone(int c_num, int c_r_num){
		return this.mapper.getcheerfulreplyone(c_num, c_r_num);
	}
	
	public void registPushListAboutReply(PushVo pushVo) {
		this.mapper.registPushListAboutReply(pushVo);
	}
	
	public void registPushListAboutReplyOfReply(PushVo pushVo) {
		this.mapper.registPushListAboutReplyOfReply(pushVo);
	}

	public void updatePushisChecked(int pm_num) {
		this.mapper.updatePushisChecked(pm_num);
	}
	public void updatecheerful(int c_num, String c_content, String c_photourl){
		this.mapper.updatecheerful(c_num, c_content, c_photourl);
	}
}
