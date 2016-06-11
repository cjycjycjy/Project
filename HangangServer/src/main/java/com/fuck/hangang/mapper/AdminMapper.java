package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.AdminBoardCommentOfCommentVo;
import com.fuck.hangang.vo.AdminBoardCommentVo;
import com.fuck.hangang.vo.AdminBoardPhotoVo;
import com.fuck.hangang.vo.AdminBoardVo;
import com.fuck.hangang.vo.AdminMenuTeamVo;
import com.fuck.hangang.vo.AdminReportListVo;
import com.fuck.hangang.vo.AdminRequestVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;
import com.fuck.hangang.vo.ReportVo;
import com.fuck.hangang.vo.RequestChangeTeamNameVo;
import com.fuck.hangang.vo.UserVo;

@Repository
public interface AdminMapper {

	public void registadminboard(AdminBoardVo adminboardVo);
	public void updateadminboard(AdminBoardVo adminboardVo);
	public void registadminboardphoto(@Param("a_num") int a_num, @Param("a_index") int a_index, @Param("a_photo_url") String a_photo_url);
	public List<AdminBoardVo> getadminboardfirst(@Param("flag") int flag);
	public List<AdminBoardVo> getadminboardlast(@Param("lastnum") int lastnum, @Param("flag") int flag);
	public List<AdminBoardVo> getadminboardfirstnoflag();
	public List<AdminBoardVo> getadminboardlastnoflag(@Param("lastnum") int lastnum);
	public int admincommentcount(@Param("a_num") int a_num);
	public List<AdminBoardPhotoVo> getadminphoto(@Param("a_num") int a_num);
	public void deleteadminphoto(@Param("a_num") int a_num);
	public void deleteadminboard(@Param("a_num") int a_num);
	public void deleteadminboard_allcomment(@Param("a_num") int a_num);
	public void deleteadminboard_commentofallcomment(@Param("a_num") int a_num);
	public void deleteadminboard_allrequest(@Param("a_num") int a_num);
	public List<AdminBoardCommentVo> getadminboardcommentfirst(@Param("a_num") int a_num);
	public List<AdminBoardCommentVo> getadminboardcommentlast(@Param("a_num") int a_num, @Param("lastnum") int lastnum);
	public int getadminboardcommentofcommentcount(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num);

	public void registadminboardcomment(@Param("a_num") int a_num, @Param("u_id") String u_id, @Param("a_c_content") String a_c_content);
	public void updateadminboardcomment(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num, @Param("a_c_content") String a_c_content);
	public void deleteadminboardcomment(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num);
	public void deleteadminboardcommentofallcomment(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num);
	public List<AdminBoardCommentOfCommentVo> getadminboardcommentofcommentfirst(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num);
	public List<AdminBoardCommentOfCommentVo> getadminboardcommentofcommentlast(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num, @Param("lastnum") int lastnum);
	public void registadminboardcommentofcomment(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num, @Param("u_id") String u_id, @Param("a_cc_content") String a_cc_content);
	public void updateadminboardcommentofcomment(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num, @Param("a_cc_num") int a_cc_num, @Param("a_cc_content") String a_cc_content);
	public void deleteadminboardcommentofcomment(@Param("a_num") int a_num, @Param("a_c_num") int a_c_num, @Param("a_cc_num") int a_cc_num);
	public void registadminboardrequest(@Param("a_num") int a_num, @Param("t_num") int t_num, @Param("contents") String contents);
	public List<AdminRequestVo> getadminboardrequest(@Param("a_num") int a_num);
	public void deleteadminboardrequest(@Param("a_num") int a_num, @Param("t_num") int t_num);
	public List<RequestChangeTeamNameVo> getChangeTeamName();
	public void permitChangeTeamName(@Param("t_num") int t_num);
	public void rejectChangeTeamName(@Param("t_num") int t_num);
	public void setNewTeamName(@Param("t_num") int t_num);
	public List<AdminMenuTeamVo> getAdminMenuTeamList();
	public void deleteTeam(@Param("t_num") int t_num);
	public List<AdminReportListVo> getReportList(@Param("year") int year, @Param("month") int month);
	public List<ReportVo> getReportOfTeam(@Param("year") int year, @Param("t_num") int t_num);
	public List<RegistrationIdVo> getTeamUserRegiId();
	public AdminBoardVo getAdminBoardOne(@Param("a_num") int a_num);
	
	public RegistrationIdVo getRegiIdOfUserComment(@Param("a_c_num") int a_c_num);
	public String checkReplyUser(@Param("a_c_num") int a_c_num);
	public int checkPushUser(@Param("id") String id);
	public AdminBoardCommentVo getadminboardcommentone(@Param("a_num") int a_num,@Param("a_c_num") int a_c_num);
	
	public void registPushList(PushVo pushVo);
	
	public void registPushAboutAdminBoard(@Param("flag") int flag, @Param("num") int num);
	public List<UserVo> getAllAdmin();
	public void registPushAboutAdminBoardComment(@Param("id") String id, @Param("u_id") String u_id, @Param("flag") int flag, @Param("num") int num);
	
	public void updatePushisChecked(@Param("pm_num") int pm_num);
}