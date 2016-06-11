package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.ContestMapper;
import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.ContestPhotoVo;
import com.fuck.hangang.vo.ContestReplyOfReplyVo;
import com.fuck.hangang.vo.ContestReplyVo;
import com.fuck.hangang.vo.ContestVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;

@Service
public class ContestDao {
	@Resource
	private ContestMapper contestMapper;
	
	public void registcontest(ContestVo contestVo) {
		this.contestMapper.registcontest(contestVo);
	}
	
	public void registcontestphoto(int p_num, int p_index, String photo) {
		this.contestMapper.registcontestphoto(p_num, p_index, photo);
	}
	
	public List<ContestVo> getcontestfirst() {
		return this.contestMapper.getcontestfirst();
	}
	
	public List<ContestVo> getcontestlast(int lastnum) {
		return this.contestMapper.getcontestlast(lastnum);
	}
	
	public List<ContestPhotoVo> getcontestphoto(int p_num) {
		return this.contestMapper.getcontestphoto(p_num);
	}
	
	public int getcontestreplycount(int num) {
		return this.contestMapper.getcontestreplycount(num);
	}
	
	public int getcontestreplyofreplycount(int num, int r_num) {
		return this.contestMapper.getcontestreplyofreplycount(num, r_num);
	}
	
	public List<ContestReplyVo> getphotocontestreplyfirst(int num) {
		return this.contestMapper.getphotocontestreplyfirst(num);
	}
	
	public List<ContestReplyVo> getphotocontestreplylast(int num, int lastnum) {
		return this.contestMapper.getphotocontestreplylast(num, lastnum);
	}
	

	public void registphotocontestreply(int num, String u_id, String r_content) {
		this.contestMapper.registphotocontestreply(num, u_id, r_content);
	}
	
	public List<ContestReplyOfReplyVo> getphotocontestreplyofreplyfirst(int num, int r_num) {
		return this.contestMapper.getphotocontestreplyofreplyfirst(num, r_num);
	}
	
	public List<ContestReplyOfReplyVo> getphotocontestreplyofreplylast(int num, int r_num, int lastnum) {
		return this.contestMapper.getphotocontestreplyofreplylast(num, r_num, lastnum);
	}
	

	public void registphotocontestreplyofreply(int num, int r_num, String u_id, String rr_content) {
		this.contestMapper.registphotocontestreplyofreply(num, r_num, u_id, rr_content);
	}
	
	public void updatephotocontestreply(int num, int r_num, String r_content) {
		this.contestMapper.updatephotocontestreply(num, r_num, r_content);
	}
	
	public void updatephotocontestreplyofreply(int num, int r_num, int rr_num, String rr_content) {
		this.contestMapper.updatephotocontestreplyofreply(num, r_num, rr_num, rr_content);
	}
	
	public void deletephotocontestreply(int num, int r_num) {
		this.contestMapper.deletephotocontestreply(num, r_num);
	}
	
	public void deletephotocontestreplyofreply(int num, int r_num, int rr_num) {
		this.contestMapper.deletephotocontestreplyofreply(num, r_num, rr_num);
	}
	
	public void deletephotocontest(int num) {
		this.contestMapper.deletephotocontest(num);
	}
	
	public RegistrationIdVo getRegiIdOfUser(int num){
		return this.contestMapper.getRegiIdOfUser(num);
	}
	public RegistrationIdVo getRegiIdOfUserComment(int r_num){
		return this.contestMapper.getRegiIdOfUserComment(r_num);
	}
	public ContestVo getContestOne(int num){
		return this.contestMapper.getContestOne(num);
	}
	public String checkReplyUser(int num){
		return this.contestMapper.checkReplyUser(num);
	}
	public String checkReplyOfReplyUser(int num, int r_num) {
		return this.contestMapper.checkReplyOfReplyUser(num, r_num);
	}
	public int checkPushUser(String id) {
		return this.contestMapper.checkPushUser(id);
	}
	public ContestReplyVo getphotocontestreplyone(int num, int r_num){
		return this.contestMapper.getphotocontestreplyone(num, r_num);
	}
	
	public void registPushListAboutReply(PushVo pushVo) {
		this.contestMapper.registPushListAboutReply(pushVo);
	}
	public void registPushListAboutReplyOfReply(PushVo pushVo) {
		this.contestMapper.registPushListAboutReplyOfReply(pushVo);
	}
	
	public void updatePushisChecked(int pm_num) {
		this.contestMapper.updatePushisChecked(pm_num);
	}
	public void deletecontestphoto(int num){
		this.contestMapper.deletecontestphoto(num);
	}
	public void updatecontestphoto(int num, int p_index, String url){
		this.contestMapper.updatecontestphoto(num, p_index, url);
	}
	public void updatephotocontest(ContestVo contestVo){
		this.contestMapper.updatephotocontest(contestVo);
	}
}
