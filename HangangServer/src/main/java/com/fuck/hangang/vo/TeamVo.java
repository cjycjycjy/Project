package com.fuck.hangang.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class TeamVo {
	String t_name;
	String t_logo;
	String t_facebook;
	String t_youtube;
	String t_info;
	String t_background_img;
	int t_num;
	
	
	public String getT_background_img() {
		return t_background_img;
	}
	public void setT_background_img(String t_background_img) {
		this.t_background_img = t_background_img;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_logo() {
		return t_logo;
	}
	public void setT_logo(String t_logo) {
		this.t_logo = t_logo;
	}
	public String getT_facebook() {
		return t_facebook;
	}
	public void setT_facebook(String t_facebook) {
		this.t_facebook = t_facebook;
	}
	public String getT_youtube() {
		return t_youtube;
	}
	public void setT_youtube(String t_youtube) {
		this.t_youtube = t_youtube;
	}
	public String getT_info() {
		return t_info;
	}
	public void setT_info(String t_info) {
		this.t_info = t_info;
	}
	public int getT_num() {
		return t_num;
	}
	public void setT_num(int t_num) {
		this.t_num = t_num;
	}
	
	
}
