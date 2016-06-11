package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.ReportPhotoVo;
import com.fuck.hangang.vo.ReportVo;

@Repository
public interface ReportMapper {
	public void registreport(ReportVo reportVo);

	public void registreportphoto(@Param("report_num") int report_num, @Param("r_photo_url") String r_photo_url);

	public List<ReportVo> getthismonthteamreport(@Param("r_year") int r_year, @Param("r_month") int r_month,
			@Param("t_num") int t_num);

	public List<ReportVo> getthismonthreport(@Param("r_year") int r_year, @Param("r_month") int r_month);

	public List<ReportPhotoVo> getreportphoto(@Param("report_num") int report_num);

	public void deletereportphoto(@Param("report_num") int report_num);

	public void deletereport(@Param("report_num") int report_num);
	
	public int getlastreport_num();
	public void updatereport(ReportVo reportVo);
}
