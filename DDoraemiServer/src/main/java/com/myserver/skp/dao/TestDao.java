package com.myserver.skp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.TestMapper;
import com.myserver.skp.vo.TestVo;

@Service
public class TestDao {
	@Resource
    private TestMapper testMapper;
	
	public List<TestVo> getSelect() {
        return this.testMapper.select();
    }
	
	public String getSelectP(int p_id){
		return this.testMapper.selectP(p_id);
	}
	
	public void update(String p_photo_url, int p_id){
		this.testMapper.photoSet(p_photo_url, p_id);
	}
	
	public int getSelectProgramCurGrade(int age, int p_id){
		return this.testMapper.selectProgramCurGrade(age, p_id);
	}
	
	public void insertRecommend(int p_id, String u_id, int cur_grade, int wish_grade, int fav_grade, int season_grade){
		this.testMapper.insertRecomment(p_id, u_id, cur_grade, wish_grade, fav_grade, season_grade);
	}
	
	public void updateRecommendWishAdd(int p_id, String u_id){
		this.testMapper.updateRecommendWishAdd(p_id, u_id);
	}
	
	public void updateRecommendWishDel(int p_id, String u_id){
		this.testMapper.updateRecommendWishDel(p_id, u_id);
	}
	
	public void updateRecommendFavAdd(int p_id, String u_id){
		this.testMapper.updateRecommendFavAdd(p_id, u_id);
	}
	
	public void updateRecommendFavDel(String u_id){
		this.testMapper.updateRecommendFavDel(u_id);
	}
	
}

