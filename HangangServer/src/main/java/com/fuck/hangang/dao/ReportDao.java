package com.fuck.hangang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fuck.hangang.mapper.ReportMapper;
import com.fuck.hangang.vo.ReportPhotoVo;
import com.fuck.hangang.vo.ReportVo;

@Service
public class ReportDao {

	@Resource
	private ReportMapper reportmapper;

	public void registreport(ReportVo reportVo) {
		this.reportmapper.registreport(reportVo);
	}

	public void registreportphoto(int report_num, String r_photo_url) {
		this.reportmapper.registreportphoto(report_num, r_photo_url);
	}

	public List<ReportVo> getthismonthteamreport(int r_year, int r_month, int t_num) {
		return this.reportmapper.getthismonthteamreport(r_year, r_month, t_num);
	}

	public List<ReportVo> getthismonthreport(int r_year, int r_month) {
		return this.reportmapper.getthismonthreport(r_year, r_month);
	}

	public List<ReportPhotoVo> getreportphoto(int report_num) {
		return this.reportmapper.getreportphoto(report_num);
	}

	public void deletereportphoto(int report_num) {
		this.reportmapper.deletereportphoto(report_num);
	}

	public void deletereport(int report_num) {
		this.reportmapper.deletereport(report_num);
	}

	public int getlastreport_num() {
		return this.reportmapper.getlastreport_num();
	}
	public void updatereport(ReportVo reportVo){
		this.reportmapper.updatereport(reportVo);
	}
}
