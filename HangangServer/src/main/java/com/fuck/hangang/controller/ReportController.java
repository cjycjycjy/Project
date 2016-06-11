package com.fuck.hangang.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuck.hangang.dao.ReportDao;
import com.fuck.hangang.vo.AdminBoardPhotoVo;
import com.fuck.hangang.vo.AdminBoardVo;
import com.fuck.hangang.vo.ReportPhotoVo;
import com.fuck.hangang.vo.ReportVo;

@Controller
public class ReportController {

	@Resource
	ReportDao reportDao;

	String filePath = "photo/report/";
	String savePath = "C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/photo/report/";

	@RequestMapping(value = "/registreport", method = RequestMethod.POST)
	public @ResponseBody String registreport(@RequestBody JSONObject json) {

		ReportVo reportVo = new ReportVo();

		ObjectMapper mapper = new ObjectMapper();
		try {
			reportVo = mapper.readValue(json.toString(), ReportVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<ReportPhotoVo> reportphoto = reportVo.getR_photo();

		if (reportphoto.size() == 0) {
			reportDao.registreport(reportVo);
		} else {
			reportDao.registreport(reportVo);
			for (int i = 0; i < reportphoto.size(); i++) {
				String r_photo_url = null;
				try {
					r_photo_url = writeFile(reportphoto.get(i).getR_photo_url(), savePath, reportVo.getReport_num(), i);
				} catch (Exception e) {
					e.printStackTrace();
				}
				reportDao.registreportphoto(reportVo.getReport_num() , filePath + r_photo_url);
			}

		}

		try {
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	@RequestMapping(value = "/updatereport", method = RequestMethod.POST)
	public @ResponseBody <T> HashMap<String, T> updatereport(@RequestBody JSONObject json) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		ReportVo reportVo = null;
		ObjectMapper mapper = new ObjectMapper();

		String ch_photo = null;
		List<ReportPhotoVo> resultReportPhotoVo = null;
		List<ReportPhotoVo> r_photo = null;
		try {
			reportVo = mapper.readValue(json.toString(), ReportVo.class);
			int report_num = reportVo.getReport_num();
			resultReportPhotoVo = reportDao.getreportphoto(report_num);
			deleteFile(resultReportPhotoVo, savePath);

			reportDao.deletereportphoto(report_num);

			resultReportPhotoVo = reportDao.getreportphoto(report_num);



			r_photo = reportVo.getR_photo();

			for (int i = 0; i < r_photo.size(); i++) {
				String r_photo_url = null;
				try {
					r_photo_url = writeFile(r_photo.get(i).getR_photo_url(), savePath, reportVo.getReport_num(), i);
					r_photo.get(i).setR_photo_url(r_photo_url);
				} catch (Exception e) {
					e.printStackTrace();
				}
				reportDao.registreportphoto(reportVo.getReport_num() , filePath + r_photo_url);
			}
			reportDao.updatereport(reportVo);
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");

		}

		resultHashMap.put("result", (T) "Success");
		resultHashMap.put("photourl", (T)r_photo);
		return resultHashMap;
	}

	@RequestMapping(value = "/getreport", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getreport(@RequestParam("r_year") int r_year,
			@RequestParam("r_month") int r_month, @RequestParam("t_num") int t_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {

			if (t_num != -1) {
				List<ReportVo> resultThisMonthTeamReportVo = reportDao.getthismonthteamreport(r_year, r_month, t_num);
				resultHashMap.put("result", (T) "Success");
				if (resultThisMonthTeamReportVo != null) {
					resultHashMap.put("reportlist", (T) resultThisMonthTeamReportVo);
				} else {
					resultHashMap.put("reportlist", (T) "");
				}

			} else {
				List<ReportVo> resultThisMonthReportVo = reportDao.getthismonthreport(r_year, r_month);
				resultHashMap.put("result", (T) "Success");
				if (resultThisMonthReportVo != null) {
					resultHashMap.put("reportlist", (T) resultThisMonthReportVo);
				} else {
					resultHashMap.put("reportlist", (T) "");
				}

			}
			return resultHashMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}

	}

	@RequestMapping(value = "/deletereport", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletereport(@RequestParam("report_num") int report_num) {
		HashMap<String, T> resultHashMap = new HashMap<String, T>();
		try {
			List<ReportPhotoVo> resultReportPhotoVo = reportDao.getreportphoto(report_num);
			deleteFile(resultReportPhotoVo, savePath);

			reportDao.deletereportphoto(report_num);
			reportDao.deletereport(report_num);
			resultHashMap.put("result", (T) "Success");
			return resultHashMap;
		}catch(Exception e) {
			e.printStackTrace();
			resultHashMap.put("result", (T) "Failed");
			return resultHashMap;
		}
	}

	public String writeFile(String file, String savePath, int r_num, int i) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = dateFormat.format(calendar.getTime()).toString();
		String r_photo_url = r_num + "_" + i +"_"+ time+".jpg";

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath + r_photo_url));
		bos.write(Base64.decodeBase64(file));
		bos.flush();
		bos.close();
		return r_photo_url;
	}

	public boolean deleteFile(List<ReportPhotoVo> delete_photo_path, String savePath) throws Exception{

		for(int i=0; i<delete_photo_path.size(); i++){
			File delete_file = new File("C:/Users/user/Desktop/springwork/Hangang/src/main/webapp/"+delete_photo_path.get(i).getR_photo_url());
			if(!delete_file.delete())
				return false;
		}
		return true;
	}

}
