package ddoraemi.detailediteminfo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Afterword implements Serializable{
	   int a_id;
	   int g_id;
	   String u_id;
	   String a_content;
	   float a_grade;
	   int a_year;
	   int a_month;
	   int a_day;
	   int a_r_num;
	   String u_photo_url;
	   ArrayList<AfterwordReply> afterword_reply;
	   ArrayList<String> a_photo_url;


	public Afterword(int a_id, int g_id, String u_id, String a_content,
			int a_grade, int a_year, int a_month, int a_day, int a_r_num,
			ArrayList<AfterwordReply> afterword_reply,ArrayList<String> a_photo_url,String u_photo_url) {
		super();
		this.a_id = a_id;
		this.g_id = g_id;
		this.u_id = u_id;
		this.a_content = a_content;
		this.a_grade = a_grade;
		this.a_year = a_year;
		this.a_month = a_month;
		this.a_day = a_day;
		this.a_r_num = a_r_num;
		this.afterword_reply = afterword_reply;
		this.a_photo_url = a_photo_url;
		this.u_photo_url = u_photo_url;
	}

	public String getU_photo_url() {
		return u_photo_url;
	}

	public void setU_photo_url(String u_photo_url) {
		this.u_photo_url = u_photo_url;
	}

	public ArrayList<String> getA_photo_url() {
		return a_photo_url;
	}

	public void setA_photo_url(ArrayList<String> a_photo_url) {
		this.a_photo_url = a_photo_url;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public int getg_id() {
		return g_id;
	}

	public void setg_id(int g_id) {
		this.g_id = g_id;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public float getA_grade() {
		return a_grade;
	}

	public void setA_grade(float a_grade) {
		this.a_grade = a_grade;
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

	public int getA_r_num() {
		return a_r_num;
	}

	public void setA_r_num(int a_r_num) {
		this.a_r_num = a_r_num;
	}

	public ArrayList<AfterwordReply> getAfterword_reply() {
		return afterword_reply;
	}

	public void setAfterword_reply(ArrayList<AfterwordReply> afterword_reply) {
		this.afterword_reply = afterword_reply;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}


}