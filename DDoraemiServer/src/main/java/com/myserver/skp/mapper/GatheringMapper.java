package com.myserver.skp.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myserver.skp.vo.GatheringVo;

@Repository
public interface GatheringMapper {
	public List<HashMap<String, String>> selectAllofGathering(@Param("p_id") int p_id);
	public List<HashMap<String, String>> selectAllofGatheringAdmin(@Param("p_id") int p_id);
	public List<HashMap<String, String>> selectIsAvailable();
	public void insertGathering(GatheringVo gatheringVo);
	public void insertJoinGathering(@Param("g_id") int g_id, @Param("u_id") String u_id, @Param("j_g_persons") int j_g_persons);
	public void updateJonGatheringNumAdd(@Param("g_id") int g_id, @Param("j_g_persons") int j_g_persons);
	public List<HashMap<String, String>> selectJoinGathering(@Param("u_id") String u_id);
	public List<HashMap<String, Integer>> selectJoinGatheringId(@Param("u_id") String u_id);
	public void deleteJoinGathering(@Param("g_id") int g_id, @Param("u_id") String u_id);
	public void updateJonGatheringNumSub(@Param("g_id") int g_id, @Param("j_g_persons") int j_g_persons);
	public int selectJoinGatheringPersons(@Param("g_id")int g_id, @Param("u_id")String u_id);
	public void deleteGathering(@Param("g_id")int g_id);
	public void deleteJoinGatheringAll(@Param("g_id")int g_id);
	public void updateGathering(@Param("year") int year, @Param("month") int month, @Param("day") int day);
	public String selectGatheringUId(@Param("g_id") int g_id);
	public List<HashMap<String, String>> selectSearch(@Param("content") String content);
	public <T> HashMap<String, T> selectGathering(@Param("g_id") int g_id);
}