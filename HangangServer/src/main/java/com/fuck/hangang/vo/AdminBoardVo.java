package com.fuck.hangang.vo;

import java.util.List;

public class AdminBoardVo {
	int a_num;
	String a_title;
	String a_contents;
	int a_type;
	String a_time;
	int a_year;
	int a_month;
	int a_day;
	int a_hour;
	int a_min;
	List<AdminBoardPhotoVo> photo;

	int c_count;
	
	
	
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	public List<AdminBoardPhotoVo> getPhoto() {
		return photo;
	}
	public void setPhoto(List<AdminBoardPhotoVo> photo) {
		this.photo = photo;
	}
	public int getA_num() {
		return a_num;
	}
	public void setA_num(int a_num) {
		this.a_num = a_num;
	}
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public String getA_contents() {
		return a_contents;
	}
	public void setA_contents(String a_contents) {
		this.a_contents = a_contents;
	}
	public int getA_type() {
		return a_type;
	}
	public void setA_type(int a_type) {
		this.a_type = a_type;
	}
	public String getA_time() {
		return a_time;
	}
	public void setA_time(String a_time) {
		this.a_time = a_time;
	}
	public int getA_year() {
		return a_year;
	}
	public void setA_year(int a_year) {
		this.a_year = a_year;
	}
	public int getA_month() {
		return a_month;
	}
	public void setA_month(int a_month) {
		this.a_month = a_month;
	}
	public int getA_day() {
		return a_day;
	}
	public void setA_day(int a_day) {
		this.a_day = a_day;
	}
	public int getA_hour() {
		return a_hour;
	}
	public void setA_hour(int a_hour) {
		this.a_hour = a_hour;
	}
	public int getA_min() {
		return a_min;
	}
	public void setA_min(int a_min) {
		this.a_min = a_min;
	}
	
}
