package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.PushMapper;
import com.fuck.hangang.vo.PushVo;

@Service
public class PushDao {
	
	@Resource
	private PushMapper pushMapper;
	
	public List<PushVo> getuserpushPermission1First(String u_id) {
		return this.pushMapper.getuserpushPermission1First(u_id);
	}
	public List<PushVo> getuserpushPermission2First(String u_id) {
		return this.pushMapper.getuserpushPermission2First(u_id);
	}
	public List<PushVo> getuserpushPermission3First(String u_id) {
		return this.pushMapper.getuserpushPermission3First(u_id);
	}
	public List<PushVo> getuserpushPermission1Last(String u_id, int lastnum) {
		return this.pushMapper.getuserpushPermission1Last(u_id, lastnum);
	}
	public List<PushVo> getuserpushPermission2Last(String u_id, int lastnum) {
		return this.pushMapper.getuserpushPermission2Last(u_id, lastnum);
	}
	public List<PushVo> getuserpushPermission3Last(String u_id, int lastnum) {
		return this.pushMapper.getuserpushPermission3Last(u_id, lastnum);
	}
}
