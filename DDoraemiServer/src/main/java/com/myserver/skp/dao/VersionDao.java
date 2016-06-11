package com.myserver.skp.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.VersionMapper;
import com.myserver.skp.vo.VersionVo;


@Service
public class VersionDao {
	@Resource
	VersionMapper versionMapper;
	
	public VersionVo getSelectVersion(){
		return this.versionMapper.selectVersion();
	}
}
