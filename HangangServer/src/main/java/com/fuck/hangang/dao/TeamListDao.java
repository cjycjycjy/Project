package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.TeamListMapper;
import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamGalleryVo;
import com.fuck.hangang.vo.TeamUserVo;
import com.fuck.hangang.vo.TeamVo;

@Service
public class TeamListDao {
	@Resource
	TeamListMapper mapper;
	
	public List<TeamVo> getteaminfo(){
		return this.mapper.getteaminfo();
	}
	
	public List<ScheduleVo> getteamschedule(int t_num, int year, int month, int nextmonth){
		return this.mapper.getteamschedule(t_num, year, month, nextmonth);
	}
	
	public TeamVo getteamdata(int t_num){
		return this.mapper.getteamdata(t_num);
	}
	
	public List<TeamGalleryVo> getteamgallery(int t_num){
		return this.mapper.getteamgallery(t_num);
	}
	public List<CheerfulVo> getteamcheerful(int t_num){
		return this.mapper.getteamcheerful(t_num);
	}
	public int getcheerfulcommentnum(int c_num){
		return this.mapper.getcheerfulcommentnum(c_num);
	}
	
	public String getteamlogourl(@Param("t_num") int t_num) {
		return this.mapper.getteamlogourl(t_num);
	}
	
	public String getteambackgroundurl(@Param("t_num") int t_num) {
		return this.mapper.getteambackgroundurl(t_num);
	}
	
	public String getteamname(@Param("t_num") int t_num) {
		return this.mapper.getteamname(t_num);
	}
	
	public void changeteamlogoimage(@Param("t_num") int t_num, @Param("t_logo") String t_logo) {
		this.mapper.changeteamlogoimage(t_num, t_logo);
	}
	
	public void changebackgroundimage(@Param("t_num") int t_num, @Param("t_background_img") String t_background_img) {
		this.mapper.changebackgroundimage(t_num, t_background_img);
	}
	
	public void changeteamfacebook(@Param("t_num") int t_num, @Param("t_facebook") String t_facebook) {
		this.mapper.changeteamfacebook(t_num, t_facebook);
	}
	
	public void changeteamyoutube(@Param("t_num") int t_num, @Param("t_youtube") String t_youtube) {
		this.mapper.changeteamyoutube(t_num, t_youtube);
	}
	
	public void deleteteamfacebook(@Param("t_num") int t_num) {
		this.mapper.deleteteamfacebook(t_num);
	}
	
	public void deleteteamyoutube(@Param("t_num") int t_num) {
		this.mapper.deleteteamyoutube(t_num);
	}
	
	public void registbookmark(@Param("u_id") String u_id, @Param("t_num") int t_num) {
		this.mapper.registbookmark(u_id, t_num);
	}
	
	public void deletebookmark(@Param("u_id") String u_id, @Param("t_num") int t_num) {
		this.mapper.deletebookmark(u_id, t_num);
	}
	
	public void registteamgallery(@Param("t_num") int t_num, @Param("p_url") String p_url) {
		this.mapper.registteamgallery(t_num, p_url);
	}
	
	public List<TeamGalleryVo> getteamgalleryfirst(@Param("t_num") int t_num, @Param("lastnum") int lastnum, @Param("count") int count) {
		return this.mapper.getteamgalleryfirst(t_num, lastnum, count);
	}
	
	public List<TeamGalleryVo> getteamgallerylast(@Param("t_num") int t_num, @Param("lastnum") int lastnum) {
		return this.mapper.getteamgallerylast(t_num, lastnum);
	}
	
	public void changeteaminfo(@Param("t_num") int t_num, @Param("t_info") String t_info) {
		this.mapper.changeteaminfo(t_num, t_info);
	}
	
	public List<TeamUserVo> getteamuser(int t_num) {
		return this.mapper.getteamuser(t_num);
	}
	
	public void registteamuser(String u_id, int t_num) {
		this.mapper.registteamuser(u_id, t_num);
	}
	
	public TeamUserVo getduplicationteamuser(String u_id) {
		return this.mapper.getduplicationteamuser(u_id);
	}
	
	public void deleteteamuser(String u_id) {
		this.mapper.deleteteamuser(u_id);
	}
	
	public String getteamleader(int t_num) {
		return this.mapper.getteamleader(t_num);
	}
	
	public void updateoldleader(String u_id) {
		this.mapper.updateoldleader(u_id);
	}
	
	public void updatenewleader(String u_id) {
		this.mapper.updatenewleader(u_id);
	}
	
	public void changeuserpermission(String u_id, int u_permission) {
		this.mapper.changeuserpermission(u_id, u_permission);
	}
	
	public TeamGalleryVo getteamgalleryurl(int t_num, int q_num) {
		return this.mapper.getteamgalleryurl(t_num, q_num);
	}
	
	public void deleteteamgallery(int t_num, int q_num) {
		this.mapper.deleteteamgallery(t_num, q_num);
	}
}