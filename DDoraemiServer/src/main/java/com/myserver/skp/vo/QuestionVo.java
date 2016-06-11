package com.myserver.skp.vo;

import java.util.List;

public class QuestionVo {
	int q_id;
	UserVo q_user;
	int g_id;
	String q_content;
	int q_year;
	int q_month;
	int q_day;
	int q_r_num;
	List<QuestionReplyVo> question_reply;
	
	public QuestionVo() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public QuestionVo(UserVo q_user, int g_id, String q_content,
			int q_year, int q_month, int q_day) {
		super();
		this.q_user = q_user;
		this.g_id = g_id;
		this.q_content = q_content;
		this.q_year = q_year;
		this.q_month = q_month;
		this.q_day = q_day;
	}
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public UserVo getQ_user() {
		return q_user;
	}

	public void setQ_user(UserVo q_user) {
		this.q_user = q_user;
	}

	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public int getQ_year() {
		return q_year;
	}
	public void setQ_year(int q_year) {
		this.q_year = q_year;
	}
	public int getQ_month() {
		return q_month;
	}
	public void setQ_month(int q_month) {
		this.q_month = q_month;
	}
	public int getQ_day() {
		return q_day;
	}
	public void setQ_day(int q_day) {
		this.q_day = q_day;
	}
	public int getQ_r_num() {
		return q_r_num;
	}
	public void setQ_r_num(int q_r_num) {
		this.q_r_num = q_r_num;
	}
	public List<QuestionReplyVo> getQuestion_reply() {
		return question_reply;
	}
	public void setQuestion_reply(List<QuestionReplyVo> question_reply) {
		this.question_reply = question_reply;
	}
	
	
}