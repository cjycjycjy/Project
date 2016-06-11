package com.wonjin.sherlockphones.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wonjin.sherlockphones.vo.UserVo;

@Repository
public interface UserMapper {
	public void signup(UserVo userVo);
	public void insertRegisterId(@Param("u_phonenum") String u_phonenum, @Param("u_register_id") String u_register_id);
	public String selectRegisterId(@Param("u_phonenum") String u_phonenum);
	public void updateRegisterId(@Param("u_register_id") String u_register_id, @Param("new_register_id") String new_register_id);
	public void updateRegisterIdByPhoneNum(@Param("u_phonenum") String u_phonenum, @Param("u_register_id") String u_register_id);
	public UserVo login(@Param("u_phonenum") String u_phonenum, @Param("u_passwd") String u_passwd);
	public void startService(@Param("u_phonenum") String u_phonenum);
	public void stopService(@Param("u_phonenum") String u_phonenum);
	public void updateUser(UserVo userVo);
	public String selectUserMail(@Param("u_phonenum") String u_phonenum);
	public void updatePassword(@Param("u_phonenum") String u_phonenum, @Param("u_passwd") String u_passwd);
	
}
