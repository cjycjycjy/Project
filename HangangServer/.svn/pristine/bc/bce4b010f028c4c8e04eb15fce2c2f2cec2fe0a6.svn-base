package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.AdminMapper;
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

@Service
public class AdminDao {

	@Resource
	private AdminMapper adminMapper;
	
	public void registadminboard(AdminBoardVo adminboardVo) {
		this.adminMapper.registadminboard(adminboardVo);
	}
	
	public void registadminboardphoto(int a_num, int a_index, String a_photo_url) {
		this.adminMapper.registadminboardphoto(a_num, a_index, a_photo_url);
	}
	
	public List<AdminBoardVo> getadminboardfirst(int flag) {
		return this.adminMapper.getadminboardfirst(flag);
	}
	
	public List<AdminBoardVo> getadminboardlast(int lastnum, int flag) {
		return this.adminMapper.getadminboardlast(lastnum, flag);
	}
	
	public List<AdminBoardVo> getadminboardfirstnoflag() {
		return this.adminMapper.getadminboardfirstnoflag();
	}
	
	public List<AdminBoardVo> getadminboardlastnoflag(int lastnum) {
		return this.adminMapper.getadminboardlastnoflag(lastnum);
	}
	
	public int admincommentcount(int a_num) {
		return this.adminMapper.admincommentcount(a_num);
	}
	
	public List<AdminBoardPhotoVo> getadminphoto(int a_num) {
		return this.adminMapper.getadminphoto(a_num);
	}
	
	public void deleteadminphoto(int a_num) {
		this.adminMapper.deleteadminphoto(a_num);
	}
	
	public void deleteadminboard(int a_num) {
		this.adminMapper.deleteadminboard(a_num);
	}
	
	public void deleteadminboard_allcomment(int a_num) {
		this.adminMapper.deleteadminboard_allcomment(a_num);
	}
	
	public void deleteadminboard_commentofallcomment(int a_num) {
		this.adminMapper.deleteadminboard_commentofallcomment(a_num);
	}
	
	public void deleteadminboard_allrequest(int a_num) {
		this.adminMapper.deleteadminboard_allrequest(a_num);
	}
	
	public List<AdminBoardCommentVo> getadminboardcommentfirst(int a_num) {
		return this.adminMapper.getadminboardcommentfirst(a_num);
	}
	
	public List<AdminBoardCommentVo> getadminboardcommentlast(int a_num, int lastnum) {
		return this.adminMapper.getadminboardcommentlast(a_num, lastnum);
	}
	
	public int getadminboardcommentofcommentcount(int a_num, int a_c_num) {
		return this.adminMapper.getadminboardcommentofcommentcount(a_num, a_c_num);
	}
	
	
	
	public void registadminboardcomment(int a_num, String u_id, String a_c_content) {
		this.adminMapper.registadminboardcomment(a_num, u_id, a_c_content);
	}
	
	public void updateadminboardcomment(int a_num, int a_c_num, String a_c_content) {
		this.adminMapper.updateadminboardcomment(a_num, a_c_num, a_c_content);
	}
	
	public void deleteadminboardcomment(int a_num, int a_c_num) {
		this.adminMapper.deleteadminboardcomment(a_num, a_c_num);
	}
	
	public void deleteadminboardcommentofallcomment(int a_num, int a_c_num) {
		this.adminMapper.deleteadminboardcommentofallcomment(a_num, a_c_num);
	}
	
	public List<AdminBoardCommentOfCommentVo> getadminboardcommentofcommentfirst(int a_num, int a_c_num) {
		return this.adminMapper.getadminboardcommentofcommentfirst(a_num, a_c_num);
	}
	
	public List<AdminBoardCommentOfCommentVo> getadminboardcommentofcommentlast(int a_num, int a_c_num, int lastnum) {
		return this.adminMapper.getadminboardcommentofcommentlast(a_num, a_c_num, lastnum);
	}
	

	public void registadminboardcommentofcomment(int a_num, int a_c_num, String u_id, String a_cc_content) {
		this.adminMapper.registadminboardcommentofcomment(a_num, a_c_num, u_id, a_cc_content);
	}
	
	public void updateadminboardcommentofcomment(int a_num, int a_c_num, int a_cc_num, String a_cc_content) {
		this.adminMapper.updateadminboardcommentofcomment(a_num, a_c_num, a_cc_num, a_cc_content);
	}
	
	public void deleteadminboardcommentofcomment(int a_num, int a_c_num, int a_cc_num) {
		this.adminMapper.deleteadminboardcommentofcomment(a_num, a_c_num, a_cc_num);
	}
	
	public void registadminboardrequest(int a_num, int t_num, String contents) {
		this.adminMapper.registadminboardrequest(a_num, t_num, contents);
	}
	
	public List<AdminRequestVo> getadminboardrequest(int a_num) {
		return this.adminMapper.getadminboardrequest(a_num);
	}
	
	public void deleteadminboardrequest(int a_num, int t_num) {
		this.adminMapper.deleteadminboardrequest(a_num, t_num);
	}
	public List<RequestChangeTeamNameVo> getChangeTeamName(){
		return this.adminMapper.getChangeTeamName();
	}
	public void permitChangeTeamName(int t_num){
		this.adminMapper.permitChangeTeamName(t_num);
	}
	public void rejectChangeTeamName(int t_num){
		this.adminMapper.rejectChangeTeamName(t_num);
	}
	public void setNewTeamName(int t_num){
		this.adminMapper.setNewTeamName(t_num);
	}
	public List<AdminMenuTeamVo> getAdminMenuTeamList(){
		return this.adminMapper.getAdminMenuTeamList();
	}
	public void deleteTeam(int t_num){
		this.adminMapper.deleteTeam(t_num);
	}
	public List<AdminReportListVo> getReportList(int year, int month){
		return this.adminMapper.getReportList(year, month);
	}
	public List<ReportVo> getReportOfTeam(int year, int t_num){
		return this.adminMapper.getReportOfTeam(year, t_num);
	}
	public List<RegistrationIdVo> getTeamUserRegiId(){
		return this.adminMapper.getTeamUserRegiId();
	}
	public AdminBoardVo getAdminBoardOne(int a_num){
		return this.adminMapper.getAdminBoardOne(a_num);
	}
	public String checkReplyUser(int c_num){
		return this.adminMapper.checkReplyUser(c_num);
	}
	public int checkPushUser(String id) {
		return this.adminMapper.checkPushUser(id);
	}
	public RegistrationIdVo getRegiIdOfUserComment(int a_c_num){
		return this.adminMapper.getRegiIdOfUserComment(a_c_num);
	}
	public AdminBoardCommentVo getadminboardcommentone(int a_num, int a_c_num){
		return this.adminMapper.getadminboardcommentone(a_num, a_c_num);
	}
	
	public void registPushList(PushVo pushVo) {
		this.adminMapper.registPushList(pushVo);
	}
		
	public void registPushAboutAdminBoard(int flag, int num) {
		this.adminMapper.registPushAboutAdminBoard(flag, num);
	}
	
	public List<UserVo> getAllAdmin() {
		return this.adminMapper.getAllAdmin();
	}
	
	public void registPushAboutAdminBoardComment(String id, String u_id, int flag, int num) {
		this.adminMapper.registPushAboutAdminBoardComment(id, u_id, flag, num);
	}
	
	public void updatePushisChecked(int pm_num) {
		this.adminMapper.updatePushisChecked(pm_num);
	}
	public void updateadminboard(AdminBoardVo adminboardVo){
		this.adminMapper.updateadminboard(adminboardVo);
	}
}
