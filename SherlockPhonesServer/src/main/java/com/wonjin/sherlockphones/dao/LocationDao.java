package com.wonjin.sherlockphones.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wonjin.sherlockphones.mapper.LocationMapper;
import com.wonjin.sherlockphones.vo.LocationVo;

@Service
public class LocationDao {
	
	@Resource
	LocationMapper locationMapper;
	
	public void registerLocation(String u_phonenum, double l_long, double l_lat){
		this.locationMapper.registerLocation(u_phonenum, l_long, l_lat);
	}
	
	public List<LocationVo> selectLocation(String u_phonenum){
		return this.locationMapper.selectLocation(u_phonenum);
	}
}
