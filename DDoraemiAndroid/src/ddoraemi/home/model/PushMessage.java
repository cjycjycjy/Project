package ddoraemi.home.model;

import java.io.Serializable;
import java.util.Calendar;

public class PushMessage implements Serializable {
	int pk;
	String type;
	String u_id;
	String p_g_name;
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int p_g_id;
	String img_url;
	String message;
	int ischecked;
	int d_day;
	private Calendar cal;
	private long now_day;
	String week;

	public PushMessage() {
		// TODO Auto-generated constructor stub
	}

	public PushMessage(int pk, String type, String u_id, String p_g_name,
			int year, int month, int day, int hour, int minute, int p_g_id,
			String img_url, String message, int ischecked) {
		super();
		this.pk = pk;
		this.type = type;
		this.u_id = u_id;
		this.p_g_name = p_g_name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.p_g_id = p_g_id;
		this.img_url = img_url;
		this.message = message;
		this.ischecked = ischecked;

		cal = Calendar.getInstance();
		now_day = cal.getTimeInMillis();
		int year2 = year;
		int month2 = month;
		int day2 = day;
		cal.set(year2, month2 - 1, day2);
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			week = "일";
			break;
		case 2:
			week = "월";
			break;
		case 3:
			week = "화";
			break;
		case 4:
			week = "수";
			break;
		case 5:
			week = "목";
			break;
		case 6:
			week = "금";
			break;
		case 7:
			week = "토";
			break;
		}
		long eventday = cal.getTimeInMillis();
		int result = (int) ((eventday - now_day) / (60 * 60 * 24 * 1000));
		this.d_day = result;
	}
	public String getWeek()
	{
		return week;
	}
	public int getd_day() {
		return d_day;
	}

	public int getIschecked() {
		return ischecked;
	}

	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getP_g_name() {
		return p_g_name;
	}

	public void setP_g_name(String p_n_ame) {
		this.p_g_name = p_n_ame;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getP_g_id() {
		return p_g_id;
	}

	public void setP_g_id(int p_g_id) {
		this.p_g_id = p_g_id;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
