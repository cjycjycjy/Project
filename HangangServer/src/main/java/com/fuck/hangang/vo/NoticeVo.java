package com.fuck.hangang.vo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.json.simple.JSONArray;


public class NoticeVo {
	

	int n_num;
	String n_title;
	String n_contents;
	int n_type;
	String n_time;
	int n_c_num;
	List<NoticePhotoVo> photo;
	
	public int getN_c_num() { 
		return n_c_num;
	}
	public void setN_c_num(int n_c_num) {
		this.n_c_num = n_c_num;
	}
	

	public List<NoticePhotoVo> getPhoto() {
		return photo;
	}
	public void setPhoto(List<NoticePhotoVo> photo) {
		this.photo = photo;
	}
	public int getN_num() {
		return n_num;
	}
	public void setN_num(int n_num) {
		this.n_num = n_num;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_contents() {
		return n_contents;
	}
	public void setN_contents(String n_contents) {
		this.n_contents = n_contents;
	}
	public int getN_type() {
		return n_type;
	}
	public void setN_type(int n_type) {
		this.n_type = n_type;
	}
	public String getN_time() {
		return n_time;
	}
	public void setN_time(String n_time) {
		this.n_time = n_time;
	}
	
	
}