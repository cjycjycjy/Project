package ddoraemi.home.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class ProgramData implements Serializable{



	int p_id;
	String p_name;
	String p_info;
	String e_name;
	String p_cost_adult;
	String p_cost_kid;
	int p_min_persons;
	int p_max_persons;
	String p_available_season;
	int p_lead_time;
	String p_preparation_item;
	String p_town_name;
	String p_addr_1;
	String p_addr_2;
	String p_addr_3;
	String p_addr_4;
	String p_addr_5;
	String p_addr_6;
	String p_tel;
	String p_url;
	int p_is_available_overnight;
	String p_photo_url;
	ArrayList<String> p_curriculum;

	String p_note;
	float p_grade;
	int a_id;   //담당자 foreign key 
	double lat;
	double lng;


	public ProgramData() {
		// TODO Auto-generated constructor stub

	}




	public ProgramData(int p_id, String p_name, String p_info, String e_name,
			String p_cost_adult, String p_cost_kid, int p_min_persons,
			int p_max_persons, String p_available_season, int p_lead_time,
			String p_preparation_item, String p_town_name, String p_addr_1,
			String p_addr_2, String p_addr_3, String p_addr_4, String p_addr_5,
			String p_addr_6, String p_tel, String p_url,
			int p_is_available_overnight, String p_photo_url,
			ArrayList<String> p_curriculum, String p_note, long p_grade, int a_id) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_info = p_info;
		this.e_name = e_name;
		this.p_cost_adult = p_cost_adult;
		this.p_cost_kid = p_cost_kid;
		this.p_min_persons = p_min_persons;
		this.p_max_persons = p_max_persons;
		this.p_available_season = p_available_season;
		this.p_lead_time = p_lead_time;
		this.p_preparation_item = p_preparation_item;
		this.p_town_name = p_town_name;
		this.p_addr_1 = p_addr_1;
		this.p_addr_2 = p_addr_2;
		this.p_addr_3 = p_addr_3;
		this.p_addr_4 = p_addr_4;
		this.p_addr_5 = p_addr_5;
		this.p_addr_6 = p_addr_6;
		this.p_tel = p_tel;
		this.p_url = p_url;
		this.p_is_available_overnight = p_is_available_overnight;
		this.p_photo_url = p_photo_url;
		this.p_curriculum = p_curriculum;
		this.p_note = p_note;
		this.p_grade = p_grade;
		this.a_id = a_id;
	}




	public int getP_id() {
		return p_id;
	}



	public void setP_id(int p_id) {
		this.p_id = p_id;
	}



	public String getP_name() {
		return p_name;
	}



	public void setP_name(String p_name) {
		this.p_name = p_name;
	}



	public String getP_info() {
		return p_info;
	}



	public void setP_info(String p_info) {
		this.p_info = p_info;
	}



	public String getE_name() {
		return e_name;
	}



	public void setE_name(String e_name) {
		this.e_name = e_name;
	}



	public String getP_cost_adult() {
		return p_cost_adult;
	}



	public void setP_cost_adult(String p_cost_adult) {
		this.p_cost_adult = p_cost_adult;
	}



	public String getP_cost_kid() {
		return p_cost_kid;
	}



	public void setP_cost_kid(String p_cost_kid) {
		this.p_cost_kid = p_cost_kid;
	}



	public int getP_min_persons() {
		return p_min_persons;
	}



	public void setP_min_persons(int p_min_persons) {
		this.p_min_persons = p_min_persons;
	}



	public int getP_max_persons() {
		return p_max_persons;
	}



	public void setP_max_persons(int p_max_persons) {
		this.p_max_persons = p_max_persons;
	}



	public String getP_available_season() {
		return p_available_season;
	}



	public void setP_available_season(String p_available_season) {
		this.p_available_season = p_available_season;
	}



	public int getP_lead_time() {
		return p_lead_time;
	}



	public void setP_lead_time(int p_lead_time) {
		this.p_lead_time = p_lead_time;
	}



	public String getP_preparation_item() {
		return p_preparation_item;
	}



	public void setP_preparation_item(String p_preparation_item) {
		this.p_preparation_item = p_preparation_item;
	}



	public String getP_town_name() {
		return p_town_name;
	}



	public void setP_town_name(String p_town_name) {
		this.p_town_name = p_town_name;
	}



	public String getP_addr_1() {
		return p_addr_1;
	}



	public void setP_addr_1(String p_addr_1) {
		this.p_addr_1 = p_addr_1;
	}



	public String getP_addr_2() {
		return p_addr_2;
	}



	public void setP_addr_2(String p_addr_2) {
		this.p_addr_2 = p_addr_2;
	}



	public String getP_addr_3() {
		return p_addr_3;
	}



	public void setP_addr_3(String p_addr_3) {
		this.p_addr_3 = p_addr_3;
	}



	public String getP_addr_4() {
		return p_addr_4;
	}



	public void setP_addr_4(String p_addr_4) {
		this.p_addr_4 = p_addr_4;
	}



	public String getP_addr_5() {
		return p_addr_5;
	}



	public void setP_addr_5(String p_addr_5) {
		this.p_addr_5 = p_addr_5;
	}



	public String getP_addr_6() {
		return p_addr_6;
	}



	public void setP_addr_6(String p_addr_6) {
		this.p_addr_6 = p_addr_6;
	}



	public String getP_tel() {
		return p_tel;
	}



	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}



	public String getP_url() {
		return p_url;
	}



	public void setP_url(String p_url) {
		this.p_url = p_url;
	}



	public int getP_is_available_overnight() {
		return p_is_available_overnight;
	}



	public void setP_is_available_overnight(int p_is_available_overnight) {
		this.p_is_available_overnight = p_is_available_overnight;
	}



	public String getP_photo_url() {
		return p_photo_url;
	}



	public void setP_photo_url(String p_photo_url) {
		this.p_photo_url = p_photo_url;
	}



	public ArrayList<String> getP_curriculum() {
		return p_curriculum;
	}



	public void setP_curriculum(ArrayList<String> p_curriculum) {
		this.p_curriculum = p_curriculum;
	}



	public String getP_note() {
		return p_note;
	}



	public void setP_note(String p_note) {
		this.p_note = p_note;
	}



	public float getP_grade() {
		return p_grade;
	}



	public void setP_grade(float p_grade) {
		this.p_grade = p_grade;
	}



	public int getA_id() {
		return a_id;
	}



	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String get_AddressText()
	{
		String[] textAddress=new String[6];
		String result="";
		textAddress[0]=p_addr_1;
		textAddress[1]=p_addr_2;
		textAddress[2]=p_addr_3;
		textAddress[3]=p_addr_4;
		textAddress[4]=p_addr_5;
		textAddress[5]=p_addr_6;
		for(int i=0; i<6; i++)
		{
			if(textAddress.length!=0)
			{
				result+=(textAddress[i]+" ");
			}
			else
			{
				break;
			}
		}
		return result;
	}
	public void findGeoPoint(String Address,Context context)
	{
		Geocoder geocoder=new Geocoder(context);
		Address addr;
		try{ 
			List<Address> listaddress=geocoder.getFromLocationName(Address, 1);
			if(listaddress.size()>0)
			{
				addr=listaddress.get(0);
				lat=addr.getLatitude();
				lng=addr.getLongitude();
			}
		}catch(Exception o)
		{
			o.printStackTrace();
		}
		
	}




	public double getLat() {
		return lat;
	}




	public void setLat(double lat) {
		this.lat = lat;
	}




	public double getLng() {
		return lng;
	}




	public void setLng(double lng) {
		this.lng = lng;
	}

}