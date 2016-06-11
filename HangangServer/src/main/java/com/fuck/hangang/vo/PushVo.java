package com.fuck.hangang.vo;

public class PushVo {

	String u_id;
	int pm_num;
	String reply_writer;
	int flag;
	int num;
	int reply_num;
	int isChecked;
	String pm_time;
	int reply_writer_permission;
	String reply_writer_photo;
	//reply_writer의 퍼미션이랑 포토
	
	
	
	
	public int getPm_num() {
		return pm_num;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public int getReply_writer_permission() {
		return reply_writer_permission;
	}

	public void setReply_writer_permission(int reply_writer_permission) {
		this.reply_writer_permission = reply_writer_permission;
	}
	

	public String getReply_writer_photo() {
		return reply_writer_photo;
	}

	public void setReply_writer_photo(String reply_writer_photo) {
		this.reply_writer_photo = reply_writer_photo;
	}

	public void setPm_num(int pm_num) {
		this.pm_num = pm_num;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public int getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
	public String getPm_time() {
		return pm_time;
	}
	public void setPm_time(String pm_time) {
		this.pm_time = pm_time;
	}
		
	
}
