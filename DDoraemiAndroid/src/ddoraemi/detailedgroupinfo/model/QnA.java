package ddoraemi.detailedgroupinfo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class QnA implements Serializable{
	int q_id;
	String u_id;
	int g_id;
	String q_content;
	int q_year;
	int q_month;
	int q_day;
	int q_r_num;
	ArrayList<QnAReply> question_reply;
	String u_photo_url;
	
	public QnA() {
		// TODO Auto-generated constructor stub
	}
	public QnA(int q_id, String u_id, int g_id,
			String q_content, int q_year, int q_month, int q_day, int q_r_num, 
			ArrayList<QnAReply> question_reply,String u_photo_url) {
		super();
		this.q_id = q_id;
		this.u_id = u_id;
		this.g_id = g_id;
		this.q_content = q_content;
		this.q_year = q_year;
		this.q_month = q_month;
		this.q_day = q_day;
		this.q_r_num = q_r_num;
		this.question_reply = question_reply;
		this.u_photo_url = u_photo_url;
	}
	
	public ArrayList<QnAReply> getQuestion_reply() {
		return question_reply;
	}
	public void setQuestion_reply(ArrayList<QnAReply> question_reply) {
		this.question_reply = question_reply;
	}
	public String getU_photo_url() {
		return u_photo_url;
	}
	public void setU_photo_url(String u_photo_url) {
		this.u_photo_url = u_photo_url;
	}
	public ArrayList<QnAReply> getReply() {
		return question_reply;
	}

	public void setReply(ArrayList<QnAReply> question_reply) {
		this.question_reply = question_reply;
	}

	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
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
	
	
}
