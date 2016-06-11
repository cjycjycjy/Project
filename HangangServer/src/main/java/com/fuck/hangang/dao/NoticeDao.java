package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fuck.hangang.mapper.NoticeMapper;
import com.fuck.hangang.vo.NoticePhotoVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.NoticecommentVo;
import com.fuck.hangang.vo.NoticecommentofcommentVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;
import com.fuck.hangang.vo.UserVo;

@Service
public class NoticeDao {
	@Resource
	NoticeMapper noticeMapper;

	public List<NoticeVo> getnotice() {
		return this.noticeMapper.getnotice();
	}
	
	public List<NoticeVo> getnoticefirstnoflag() {
		return this.noticeMapper.getnoticefirstnoflag();
	}
	
	public List<NoticeVo> getnoticefirst(int n_flag) {
		return this.noticeMapper.getnoticefirst(n_flag);
	}
	
	public List<NoticeVo> getnoticelastnoflag(int lastnum) {
		return this.noticeMapper.getnoticelastnoflag(lastnum);
	}
	
	public List<NoticeVo> getnoticelast(int lastnum, int n_flag) {
		return this.noticeMapper.getnoticelast(lastnum, n_flag);
	}
	
	public List<NoticePhotoVo> getnoticephoto(@Param("n_num") int n_num) {
		return this.noticeMapper.getnoticephoto(n_num);
	}

	public String getnoticecommentu_id(@Param("n_c_num") int n_c_num) {
		return this.noticeMapper.getnoticecommentu_id(n_c_num);
	}

	public String getnoticecommentofcommentu_id(@Param("n_cc_num") int n_cc_num) {
		return this.noticeMapper.getnoticecommentofcommentu_id(n_cc_num);
	}

	public void registnotice(NoticeVo noticeVo) {
		this.noticeMapper.registnotice(noticeVo);
	}

	public void registnoticephoto(@Param("n_num") int n_num, @Param("n_index") int n_index,
			@Param("n_photo_url") String n_photo_url) {
		this.noticeMapper.registnoticephoto(n_num, n_index, n_photo_url);
	}

	public List<NoticecommentVo> getnoticecommentfirst(@Param("n_num") int n_num, @Param("lastnum") int lastnum,
			@Param("count") int count) {
		return this.noticeMapper.getnoticecommentfirst(n_num, lastnum, count);
	}

	public List<NoticecommentVo> getnoticecomment(@Param("n_num") int n_num, @Param("lastnum") int lastnum) {
		return this.noticeMapper.getnoticecomment(n_num, lastnum);
	}
	


	public void registnoticecomment(@Param("n_num") int n_num, @Param("u_id") String u_id,
			@Param("n_c_content") String n_c_content) {
		this.noticeMapper.registnoticecomment(n_num, u_id, n_c_content);

	}

	public List<NoticecommentofcommentVo> getnoticecommentofcommentfirst(@Param("n_num") int n_num,
			@Param("n_c_num") int n_c_num, @Param("lastnum") int lastnum, @Param("count") int count) {
		return this.noticeMapper.getnoticecommentofcommentfirst(n_num, n_c_num, lastnum, count);
	}

	public List<NoticecommentofcommentVo> getnoticecommentofcomment(@Param("n_num") int n_num,
			@Param("n_c_num") int n_c_num, @Param("lastnum") int lastnum) {
		return this.noticeMapper.getnoticecommentofcomment(n_num, n_c_num, lastnum);
	}

	public void registnoticecommentofcomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num,
			@Param("n_cc_content") String n_cc_content, @Param("u_id") String u_id) {
		this.noticeMapper.registnoticecommentofcomment(n_num, n_c_num, n_cc_content, u_id);

	}

	public void deletenotice(@Param("n_num") int n_num) {
		this.noticeMapper.deletenotice(n_num);
	}
	

	public void deletenoticecomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num) {
		this.noticeMapper.deletenoticecomment(n_num, n_c_num);
	}

	public void deletenoticecommentofcomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num, @Param("n_cc_num") int n_cc_num) {
		this.noticeMapper.deletenoticecommentofcomment(n_num, n_c_num, n_cc_num);
	}

	public int getnoticecommentcount(@Param("n_num") int n_num) {
		return this.noticeMapper.getnoticecommentcount(n_num);
	}

	public int getnoticecommentofcommentcount(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num) {
		return this.noticeMapper.getnoticecommentofcommentcount(n_num, n_c_num);
	}

	public void updatenoticecomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num,
			@Param("n_c_content") String n_c_content) {
		this.noticeMapper.updatenoticecomment(n_num, n_c_num, n_c_content);
	}

	public void updatenoticecommentofcomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num,
			@Param("n_cc_num") int n_cc_num, @Param("n_cc_content") String n_cc_content) {
		this.noticeMapper.updatenoticecommentofcomment(n_num, n_c_num, n_cc_num, n_cc_content);
	}
	public NoticeVo getnoticeone(int n_num){
		return this.noticeMapper.getnoticeone(n_num);
	}
	public List<RegistrationIdVo> getAllRegiId(){
		return this.noticeMapper.getAllRegiId();
	}
	public String checkReplyUser(int num){
		return this.noticeMapper.checkReplyUser(num);
	}
	public RegistrationIdVo getRegiIdOfUser( int num){
		return this.noticeMapper.getRegiIdOfUser(num);
	}
	public NoticecommentVo getNoticeCommentOne(int n_c_num, int n_num){
		return this.noticeMapper.getNoticeCommentOne(n_c_num, n_num);
	}
	public List<NoticecommentofcommentVo> getNoticeCommentOfCommentAll(int n_num, int n_c_num){
		return this.noticeMapper.getNoticeCommentOfCommentAll(n_num, n_c_num);
	}
	public int checkPushUser(String id) {
		return this.noticeMapper.checkPushUser(id);
	}
	
	public void registPushList(PushVo pushVo) {
		this.noticeMapper.registPushList(pushVo);
	}	
	public void registPushListAboutNotice(PushVo pushVo) {
		this.noticeMapper.registPushListAboutNotice(pushVo);
	}
	
	public void updatePushisChecked(int pm_num) {
		this.noticeMapper.updatePushisChecked(pm_num);
	}
	public void updatenotice(NoticeVo noticeVo){
		this.noticeMapper.updatenotice(noticeVo);
	}
	public void updatenoticephoto(int n_num, int n_index, String n_photo_url){
		this.noticeMapper.updatenoticephoto(n_num, n_index, n_photo_url);
	}
	public void deletenoticephoto(int n_num){
		this.noticeMapper.deletenoticephoto(n_num);
	}

}