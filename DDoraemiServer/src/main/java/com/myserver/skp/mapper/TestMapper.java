package com.myserver.skp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myserver.skp.vo.TestVo;

@Repository
public interface TestMapper {
	List<TestVo> select();
	
	String selectP(@Param("p_id") int p_id);
	void photoSet(@Param("p_photo_url") String p_photo_url, @Param("p_id") int p_id);
	public int selectProgramCurGrade(@Param("age") int age, @Param("p_id") int p_id);
	public void insertRecomment(@Param("p_id") int p_id, @Param("u_id") String u_id, @Param("cur_grade") int cur_grade, @Param("wish_grade") int wish_grade, @Param("fav_grade") int fav_grade, @Param("season_grade")int season_grade);
	public void updateRecommendWishAdd(@Param("p_id") int p_id, @Param("u_id") String u_id);
	public void updateRecommendWishDel(@Param("p_id") int p_id, @Param("u_id") String u_id);
	public void updateRecommendFavAdd(@Param("p_id") int p_id, @Param("u_id") String u_id);
	public void updateRecommendFavDel(@Param("u_id") String u_id);
}
