package com.myserver.skp.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myserver.skp.mapper.ProgramMapper;
import com.myserver.skp.vo.CurriculumVo;
import com.myserver.skp.vo.ProgramVo;

@Service
public class ProgramDao {
	@Resource
    private ProgramMapper programMapper;
	
	public List<ProgramVo> getSelectAll() {
        return this.programMapper.selectAll();
    }
	
	public List<ProgramVo> getSelectRecommend(String u_id) {
        return this.programMapper.selectRecommend(u_id);
    }
	
	public List<ProgramVo> getSelectAddr(String p_addr_1) {
        return this.programMapper.selectAddr(p_addr_1);
    }
	
	public ProgramVo getSelectPId(int p_id) {
        return this.programMapper.selectPId(p_id);
    }
	
	public List<ProgramVo> getSelectExperience(String e_name) {
        return this.programMapper.selectExperience(e_name);
    }
	
	public List<HashMap<String, String>> getSelectAfterwordPhotoUrl(int p_id) {
        return this.programMapper.selectAfterwordPhotoUrl(p_id);
    }
	
	public List<CurriculumVo> getSelectCurriculum(int p_id) {
		List<CurriculumVo> curList = this.programMapper.selectCurriculum(p_id);
		for(int i=0; i<curList.size(); i++){
			curList.get(i).setCur(curList.get(i).getCur().replace("\r", ""));
		}
        return curList;
    }
	
	public List<ProgramVo> getSelectBookmark(String u_id){
		return this.programMapper.selectBookmark(u_id);
	}
	
	public List<HashMap<String,String>> getSelectPasser(String u_id){
		return this.programMapper.selectPasser(u_id);
	}
	
	public void updateProgramGrade(int g_id, float p_grade){
		this.programMapper.updateProgramGrade(g_id, p_grade);
	}
	
	public List<ProgramVo> getSelectAdmin(String u_id){
		return this.programMapper.selectAdmin(u_id);
	}
	
	public ProgramVo getSelectAdminProgram(int p_id){
		return this.programMapper.selectAdminProgram(p_id);
	}
	
	public List<ProgramVo> getSelectSearch(String content){
		return this.programMapper.selectSearch(content);
	}
	
	public List<Integer> getSelectPIdEName(String e_name){
		return this.programMapper.selectPIdEName(e_name);
	}
}