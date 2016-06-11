package ddoraemi.detailediteminfo.model;

import java.io.Serializable;

public class AfterwordReply implements Serializable{
	int a_r_id;
	int a_id;
	String a_r_content;
	int a_r_year;
	int a_r_month;
	int a_r_day;
	String u_id;
	String u_photo_url;
	public AfterwordReply(int a_r_id, int a_id, String a_r_content,
			int a_r_year, int a_r_month, int a_r_day, String u_id,String u_photo_url) {
		super();
		this.a_r_id = a_r_id;
		this.a_id = a_id;
		this.a_r_content = a_r_content;
		this.a_r_year = a_r_year;
		this.a_r_month = a_r_month;
		this.a_r_day = a_r_day;
		this.u_id = u_id;
		this.u_photo_url = u_photo_url;
	}
	
	public String getU_photo_url() {
		return u_photo_url;
	}

	public void setU_photo_url(String u_photo_url) {
		this.u_photo_url = u_photo_url;
	}

	public int getA_r_id() {
		return a_r_id;
	}
	public void setA_r_id(int a_r_id) {
		this.a_r_id = a_r_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_r_content() {
		return a_r_content;
	}
	public void setA_r_content(String a_r_content) {
		this.a_r_content = a_r_content;
	}
	public int getA_r_year() {
		return a_r_year;
	}
	public void setA_r_year(int a_r_year) {
		this.a_r_year = a_r_year;
	}
	public int getA_r_month() {
		return a_r_month;
	}
	public void setA_r_month(int a_r_month) {
		this.a_r_month = a_r_month;
	}
	public int getA_r_day() {
		return a_r_day;
	}
	public void setA_r_day(int a_r_day) {
		this.a_r_day = a_r_day;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	
}