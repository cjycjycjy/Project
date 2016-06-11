package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.PushVo;

@Repository
public interface PushMapper {
	
	public List<PushVo> getuserpushPermission1First(@Param("u_id") String u_id);
	public List<PushVo> getuserpushPermission2First(@Param("u_id") String u_id);
	public List<PushVo> getuserpushPermission3First(@Param("u_id") String u_id);
	public List<PushVo> getuserpushPermission1Last(@Param("u_id") String u_id, @Param("lastnum") int lastnum);
	public List<PushVo> getuserpushPermission2Last(@Param("u_id") String u_id, @Param("lastnum") int lastnum);
	public List<PushVo> getuserpushPermission3Last(@Param("u_id") String u_id, @Param("lastnum") int lastnum);

}
