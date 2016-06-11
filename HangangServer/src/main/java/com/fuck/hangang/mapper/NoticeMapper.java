package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.junit.runners.Parameterized.Parameters;

import com.fuck.hangang.vo.NoticePhotoVo;
import com.fuck.hangang.vo.NoticeVo;
import com.fuck.hangang.vo.NoticecommentVo;
import com.fuck.hangang.vo.NoticecommentofcommentVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;
import com.fuck.hangang.vo.UserVo;

public interface NoticeMapper {
	public List<NoticeVo> getnotice();
	
	public List<NoticeVo> getnoticefirstnoflag();
	public List<NoticeVo> getnoticefirst(@Param("n_flag") int n_flag);
	public List<NoticeVo> getnoticelastnoflag(@Param("lastnum") int lastnum);
	public List<NoticeVo> getnoticelast(@Param("lastnum") int lastnum, @Param("n_flag") int n_flag);
	
	public List<NoticePhotoVo> getnoticephoto(@Param("n_num") int n_num);

	public String getnoticecommentofcommentu_id(@Param("n_cc_num") int n_cc_num);

	public String getnoticecommentu_id(@Param("n_c_num") int n_c_num);

	public void registnotice(NoticeVo noticeVo);

	public void registnoticephoto(@Param("n_num") int n_num, @Param("n_index") int n_index,
			@Param("n_photo_url") String n_photo_url);

	public List<NoticecommentVo> getnoticecommentfirst(@Param("n_num") int n_num, @Param("lastnum") int lastnum,
			@Param("count") int count);

	public List<NoticecommentVo> getnoticecomment(@Param("n_num") int n_num, @Param("lastnum") int lastnum);

	
	public void registnoticecomment(@Param("n_num") int n_num, @Param("u_id") String u_id,
			@Param("n_c_content") String n_c_content);

	public List<NoticecommentofcommentVo> getnoticecommentofcommentfirst(@Param("n_num") int n_num,
			@Param("n_c_num") int n_c_num, @Param("lastnum") int lastnum, @Param("count") int count);

	public List<NoticecommentofcommentVo> getnoticecommentofcomment(@Param("n_num") int n_num,
			@Param("n_c_num") int n_c_num, @Param("lastnum") int lastnum);

	public void registnoticecommentofcomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num,
			@Param("n_cc_content") String n_cc_content, @Param("u_id") String u_id);

	public void deletenotice(@Param("n_num") int n_num);

	public void deletenoticecomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num);

	public void deletenoticecommentofcomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num, @Param("n_cc_num") int n_cc_num);

	public int getnoticecommentcount(@Param("n_num") int n_num);

	public int getnoticecommentofcommentcount(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num);

	public void updatenoticecomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num,
			@Param("n_c_content") String n_c_content);

	public void updatenoticecommentofcomment(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num,
			@Param("n_cc_num") int n_cc_num, @Param("n_cc_content") String n_cc_content);
	public NoticeVo getnoticeone(@Param("n_num") int n_num);
	public List<RegistrationIdVo> getAllRegiId();
	
	public String checkReplyUser(@Param("n_c_num") int num);
	public RegistrationIdVo getRegiIdOfUser(@Param("n_c_num") int num);
	public NoticecommentVo getNoticeCommentOne(@Param("n_c_num") int n_c_num, @Param("n_num") int n_num);
	public List<NoticecommentofcommentVo> getNoticeCommentOfCommentAll(@Param("n_num") int n_num, @Param("n_c_num") int n_c_num);
	public int checkPushUser(@Param("id") String id);
	public void registPushList(PushVo pushVo);
	public void registPushListAboutNotice(PushVo pushVo);
	
	public void updatePushisChecked(@Param("pm_num") int pm_num);
	public void updatenotice(NoticeVo noticeVo);
	public void updatenoticephoto(@Param("n_num") int n_num, @Param("n_index") int n_index,
			@Param("n_photo_url") String n_photo_url);
	public void deletenoticephoto(@Param("n_num") int n_num);
}
