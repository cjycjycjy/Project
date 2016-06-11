package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.RequestChangeTeamNameVo;
import com.fuck.hangang.vo.TeamUserVo;
import com.fuck.hangang.vo.TeamVo;
import com.fuck.hangang.vo.UserVo;

@Repository
public interface UserMapper {
	public void signup(UserVo userVo);
	public UserVo signin(@Param("u_id") String u_id, @Param("u_passwd") String u_passwd);
	public UserVo getuser(@Param("u_id") String u_id);
	public List<TeamVo> getbookmarkteam(@Param("u_id") String u_id);
	public TeamVo getteam(@Param("u_id") String u_id);
	public String getuserphoto(@Param("u_id") String u_id);
	public void changeuserphoto(@Param("u_id") String u_id, @Param("u_photo") String u_photo);
	public UserVo getoneuser(@Param("u_id") String u_id);
	public void updateforeignkey(@Param("flag") int flag);
	public void signout(@Param("u_id") String u_id);
	public TeamUserVo getteamuser(@Param("u_id") String u_id);
	public String getteamleader(@Param("t_num") int t_num);
	public int getteamsize(@Param("t_num") int t_num);
	public void deleteteamuser(@Param("u_id") String u_id);
	public void deleteteam(@Param("t_num") int t_num);
	public TeamUserVo getrandomleader(@Param("t_num") int t_num);
	public void updateleader(@Param("u_id") String u_id);
	public void changepasswd(@Param("u_id") String u_id, @Param("u_passwd") String u_passwd);
	public String selectUserMail(@Param("u_id") String u_id);
	public void updatePassword(@Param("u_id") String u_id, @Param("u_passwd") String u_passwd);
	
	public void updateRegisterIdById(@Param("u_id") String u_id, @Param("u_registrationid") String u_registrationid);
	public void updateRegisterId(@Param("u_registrationid") String u_registrationid, @Param("new_register_id") String new_register_id);
	public RequestChangeTeamNameVo checkchangeTeamName(@Param("t_num") int t_num);
	public void changeTeamName(@Param("t_num") int t_num, @Param("new_t_name") String new_t_name);
	public void changeuserinfo(@Param("u_passwd") String u_passwd, @Param("u_email") String u_email, @Param("u_id") String u_id);
	public void cancelChangeTeamName(@Param("t_num") int t_num);
	
	public void changeemail( @Param("u_email") String u_email, @Param("u_id") String u_id);
	public void changepushflag(@Param("u_id") String u_id,@Param("flag") int flag);
	public String getAllEmail(@Param("u_email") String u_email);
	
	public String findid(@Param("u_email") String u_email);
	

}
