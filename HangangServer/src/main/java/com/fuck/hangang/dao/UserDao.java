package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.UserMapper;
import com.fuck.hangang.vo.RequestChangeTeamNameVo;
import com.fuck.hangang.vo.TeamUserVo;
import com.fuck.hangang.vo.TeamVo;
import com.fuck.hangang.vo.UserVo;

@Service
public class UserDao {

	@Resource
	UserMapper userMapper;
	
	public void signup(UserVo userVo){
		this.userMapper.signup(userVo);
	}
	
	public UserVo signin(String u_id, String u_passwd){
		return this.userMapper.signin(u_id, u_passwd);
	}
	
	public UserVo getuser(String u_id){
		return this.userMapper.getuser(u_id);
	}
	public List<TeamVo> getbookmarkteam(String u_id){
		return this.userMapper.getbookmarkteam(u_id);
	}
	public TeamVo getteam(String u_id){
		return this.userMapper.getteam(u_id);
	}
	
	public String getuserphoto(@Param("u_id") String u_id) {
		return this.userMapper.getuserphoto(u_id);
	}
	
	public void changeuserphoto(@Param("u_id") String u_id, @Param("u_photo") String u_photo) {
		this.userMapper.changeuserphoto(u_id, u_photo);
	}
	
	public UserVo getoneuser(String u_id) {
		return this.userMapper.getoneuser(u_id);
	}
	
	public void updateforeignkey(int flag) {
		this.userMapper.updateforeignkey(flag);
	}
	
	public void signout(String u_id) {
		this.userMapper.signout(u_id);
	}
	
	public TeamUserVo getteamuser(String u_id) {
		return this.userMapper.getteamuser(u_id);
	}
	
	public String getteamleader(int t_num) {
		return this.userMapper.getteamleader(t_num);
	}
	
	public int getteamsize(int t_num) {
		return this.userMapper.getteamsize(t_num);
	}
	
	public void deleteteamuser(String u_id) {
		this.userMapper.deleteteamuser(u_id);
	}
	
	public void deleteteam(int t_num) {
		this.userMapper.deleteteam(t_num);
	}
	
	public TeamUserVo getrandomleader(int t_num) {
		return this.userMapper.getrandomleader(t_num);
	}
	
	public void updateleader(String u_id) {
		this.userMapper.updateleader(u_id);
	}
	
	public void changepasswd(String u_id, String u_passwd) {
		this.userMapper.changepasswd(u_id, u_passwd);
	}
	
	public String getSelectUserMail(String u_id){
		return this.userMapper.selectUserMail(u_id);
	}
	
	public void updatePassword(String u_id, String u_passwd){
		this.userMapper.updatePassword(u_id, u_passwd);
	}
	
	public void updateRegisterIdById(String u_id, String u_registrationid){
		this.userMapper.updateRegisterIdById(u_id, u_registrationid);
	}
	public RequestChangeTeamNameVo checkchangeTeamName(int t_num){
		return this.userMapper.checkchangeTeamName(t_num);
	}
	public void changeTeamName(int t_num, String new_t_name){
		this.userMapper.changeTeamName(t_num, new_t_name);
	}
	
	public void changeuserinfo(String u_passwd, String u_email, String u_id){
		this.userMapper.changeuserinfo(u_passwd, u_email, u_id);
	}
	public void cancelChangeTeamName(int t_num){
		this.userMapper.cancelChangeTeamName(t_num);
	}
	public void updateRegisterId(String u_registrationid, String new_register_id){
		this.userMapper.updateRegisterId(u_registrationid, new_register_id);
	}
	
	public void changeemail(String u_email, String u_id){
		this.userMapper.changeemail(u_email, u_id);
	}
	public void changepushflag(String u_id,int flag){
		userMapper.changepushflag(u_id,flag);
	}
	public String getAllEmail(String u_email){
		return this.userMapper.getAllEmail(u_email);
	}
	
	public String findid(String u_email) {
		return this.userMapper.findid(u_email);
	}
}