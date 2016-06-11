package com.myserver.skp.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.AdminMapper;

@Service
public class AdminDao {
	@Resource
	AdminMapper adminMapper;
	
	public void insertAdminReauest(String u_id, int p_id){
		this.adminMapper.insertAdminRequest(u_id, p_id);
	}
	
	public void insertAdmin(String u_id, int p_id){
		this.adminMapper.insertAdmin(u_id, p_id);
	}
	
	public void deleteAdminReauest(String u_id, int p_id){
		this.adminMapper.deleteAdminRequest(u_id, p_id);
	}
	
	public void deleteAdmin(String u_id, int p_id){
		this.adminMapper.deleteAdmin(u_id, p_id);
	}
}
