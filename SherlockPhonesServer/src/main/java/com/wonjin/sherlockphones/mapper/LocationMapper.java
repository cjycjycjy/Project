package com.wonjin.sherlockphones.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wonjin.sherlockphones.vo.LocationVo;

@Repository
public interface LocationMapper {
	public void registerLocation(@Param("u_phonenum") String u_phonenum, @Param("l_long") double l_long, @Param("l_lat") double l_lat);
	public List<LocationVo> selectLocation(@Param("u_phonenum") String u_phonenum);
}
