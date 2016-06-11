package com.wonjin.sherlockphones.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wonjin.sherlockphones.mapper.UserMapper;
import com.wonjin.sherlockphones.vo.UserVo;

@Service
public class UserDao {
	@Resource
	UserMapper userMapper;
	
	public void signup(UserVo userVo){
		this.userMapper.signup(userVo);
	}
	
	public void insertRegisterId(String u_phonenum, String u_register_id){
		this.userMapper.insertRegisterId(u_phonenum, u_register_id);
	}
	
	public String getSelectRegisterId(String u_phonenum){
		return this.userMapper.selectRegisterId(u_phonenum);
	}
	
	public void updateRegisterId(String u_register_id, String new_register_id){
		this.userMapper.updateRegisterId(u_register_id, new_register_id);
	}
	
	public UserVo login(String u_phonenum, String u_passwd){
		return this.userMapper.login(u_phonenum, u_passwd);
	}
	
	public void updateRegisterIdByPhoneNum(String u_phonenum, String u_register_id){
		this.userMapper.updateRegisterIdByPhoneNum(u_phonenum, u_register_id);
	}
	
	public void startService(String u_phonenum){
		this.userMapper.startService(u_phonenum);
	}
	
	public void stopService(String u_phonenum){
		this.userMapper.stopService(u_phonenum);
	}
	
	public void updateUser(UserVo userVo){
		this.userMapper.updateUser(userVo);
	}
	
	public String getSelectUserMail(String u_phonenum){
		return this.userMapper.selectUserMail(u_phonenum);
	}
	
	public void updatePassword(String u_phonenum, String u_passwd){
		this.userMapper.updatePassword(u_phonenum, u_passwd);
	}
}
