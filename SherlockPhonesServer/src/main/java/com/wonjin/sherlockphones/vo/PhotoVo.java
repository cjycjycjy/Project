package com.wonjin.sherlockphones.vo;

public class PhotoVo {
	String p_u_phonenum;
	String p_photo_url;
	String image;
	String p_time;
	public String getP_u_phonenum() {
		return p_u_phonenum;
	}
	public void setP_u_phonenum(String p_u_phonenum) {
		this.p_u_phonenum = p_u_phonenum;
	}
	public String getP_photo_url() {
		return p_photo_url;
	}
	public void setP_photo_url(String p_photo_url) {
		this.p_photo_url = p_photo_url;
	}
	public String getP_time() {
		return p_time;
	}
	public void setP_time(String p_time) {
		this.p_time = p_time.replace(".0", "");
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
