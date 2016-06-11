package com.myserver.skp.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myserver.skp.vo.CurriculumVo;
import com.myserver.skp.vo.ProgramVo;

@Repository
public interface ProgramMapper {
	List<ProgramVo> selectAll();
	List<ProgramVo> selectRecommend(@Param("u_id") String u_id);
	List<ProgramVo> selectAddr(@Param("p_addr_1") String p_addr_1);
	ProgramVo selectPId(@Param("p_id") int p_id);
	List<ProgramVo> selectExperience(@Param("e_name") String e_name);
	List<HashMap<String, String>> selectAfterwordPhotoUrl(@Param("p_id") int p_id);
	List<CurriculumVo> selectCurriculum(@Param("p_id") int p_id);
	List<ProgramVo> selectBookmark(@Param("u_id")  String u_id);
	List<HashMap<String,String>> selectPasser(@Param("u_id")  String u_id);
	public void updateProgramGrade(@Param("g_id") int g_id, @Param("p_grade") float p_grade);
	public List<ProgramVo> selectAdmin(@Param("u_id") String u_id);
	public ProgramVo selectAdminProgram(@Param("p_id") int p_id);
	List<ProgramVo> selectSearch(@Param("content") String content);
	List<Integer> selectPIdEName(@Param("e_name") String e_name);
	
}
