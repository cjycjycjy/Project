package com.wonjin.sherlockphones.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wonjin.sherlockphones.vo.PhotoVo;

@Repository
public interface PhotoMapper {
	public int registerPhoto(@Param("p_u_phonenum") String p_u_phonenum, @Param("p_photo_url") String p_photo_url);
	public List<PhotoVo> selectPhoto(@Param("p_u_phonenum") String p_u_phonenum);
}
