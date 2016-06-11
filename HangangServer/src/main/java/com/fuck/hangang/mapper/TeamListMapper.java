package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.ScheduleVo;
import com.fuck.hangang.vo.TeamGalleryVo;
import com.fuck.hangang.vo.TeamUserVo;
import com.fuck.hangang.vo.TeamVo;

@Repository
public interface TeamListMapper {
	public List<TeamVo> getteaminfo();
	public List<ScheduleVo> getteamschedule(@Param("t_num") int t_num, @Param("year") int year, @Param("month") int month, @Param("nextmonth") int nextmonth);
	public TeamVo getteamdata(@Param("t_num") int t_num);
	public List<CheerfulVo> getteamcheerful(@Param("t_num") int t_num);
	public int getcheerfulcommentnum(@Param("c_num") int c_num);
	public List<TeamGalleryVo> getteamgallery(@Param("t_num") int t_num);
	public String getteamlogourl(@Param("t_num") int t_num);
	public String getteambackgroundurl(@Param("t_num") int t_num);
	public String getteamname(@Param("t_num") int t_num);
	public void changeteamlogoimage(@Param("t_num") int t_num, @Param("t_logo") String t_logo);
	public void changebackgroundimage(@Param("t_num") int t_num, @Param("t_background_img") String t_background_img);
	public void changeteamfacebook(@Param("t_num") int t_num, @Param("t_facebook") String t_facebook);
	public void changeteamyoutube(@Param("t_num") int t_num, @Param("t_youtube") String t_youtube);
	public void deleteteamfacebook(@Param("t_num") int t_num);
	public void deleteteamyoutube(@Param("t_num") int t_num);
	public void registbookmark(@Param("u_id") String u_id, @Param("t_num") int t_num);
	public void deletebookmark(@Param("u_id") String u_id, @Param("t_num") int t_num);
	public void registteamgallery(@Param("t_num") int t_num, @Param("p_url") String p_url);
	public List<TeamGalleryVo> getteamgalleryfirst(@Param("t_num") int t_num, @Param("lastnum") int lastnum, @Param("count") int count);
	public List<TeamGalleryVo> getteamgallerylast(@Param("t_num") int t_num, @Param("lastnum") int lastnum);
	public void changeteaminfo(@Param("t_num") int t_num, @Param("t_info") String t_info);
	public List<TeamUserVo> getteamuser(@Param("t_num") int t_num);
	public void registteamuser(@Param("u_id") String u_id, @Param("t_num") int t_num);
	public TeamUserVo getduplicationteamuser(@Param("u_id") String u_id);
	public void deleteteamuser(@Param("u_id") String u_id);
	public String getteamleader(@Param("t_num") int t_num);
	public void updateoldleader(@Param("u_id") String u_id);
	public void updatenewleader(@Param("u_id") String u_id);
	public void changeuserpermission(@Param("u_id") String u_id, @Param("u_permission") int u_permission);
	public TeamGalleryVo getteamgalleryurl(@Param("t_num") int t_num, @Param("q_num") int q_num);
	public void deleteteamgallery(@Param("t_num") int t_num, @Param("q_num") int q_num);
}
