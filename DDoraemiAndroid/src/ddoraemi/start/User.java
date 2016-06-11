package ddoraemi.start;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class User implements Serializable{
	String u_id;
	String password;
	String u_phone;
	char parent_gender;
	int parent_age;
	String u_addr1;
	String u_addr2;
	String u_addr3;
	char u_gender;
	int u_age;
	int permission;
	ArrayList<String> favorit_program;
	ArrayList<Integer> bookmark; 
	ArrayList<Integer> participatedgroup;
	double lng;
	double lat;
	String u_photo_url;
	int u_alarm;
	
	public User(String u_id, String password, String u_phone,
			char parent_gender, int parent_age, String u_addr1,
			String u_addr2, String u_addr3, char u_gender, int u_age,
			ArrayList<String> favorit_program, ArrayList<Integer> bookmark
			,String u_photo_url, ArrayList<Integer> participatedgroup,int permission,int u_alarm) {
		super();
		this.u_id = u_id;
		this.password = password;
		this.u_phone = u_phone;
		this.parent_gender = parent_gender;
		this.parent_age = parent_age;
		this.u_addr1 = u_addr1;
		this.u_addr2 = u_addr2;
		this.u_addr3 = u_addr3;
		this.u_gender = u_gender;
		this.u_age = u_age;
		this.favorit_program = favorit_program;
		this.bookmark = bookmark;
		this.u_photo_url = u_photo_url;
		this.participatedgroup = participatedgroup;
		this.permission=permission;
		this.u_alarm = u_alarm;
	}
	public void setPermission(int permission)
	{
		this.permission=permission;
	}
	public int getPermission()
	{
		return permission;
	}
	public ArrayList<Integer> getParticipatedgroup() {
		return participatedgroup;
	}

	public void setParticipatedgroup(ArrayList<Integer> participatedgroup) {
		this.participatedgroup = participatedgroup;
	}

	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public char getParent_gender() {
		return parent_gender;
	}
	public void setParent_gender(char parent_gender) {
		this.parent_gender = parent_gender;
	}
	public int getParent_age() {
		return parent_age;
	}
	public void setParent_age(int parent_age) {
		this.parent_age = parent_age;
	}
	public String getU_addr1() {
		return u_addr1;
	}
	public void setU_addr1(String u_addr1) {
		this.u_addr1 = u_addr1;
	}
	public String getU_addr2() {
		return u_addr2;
	}
	public void setU_addr2(String u_addr2) {
		this.u_addr2 = u_addr2;
	}
	public String getU_addr3() {
		return u_addr3;
	}
	public void setU_addr3(String u_addr3) {
		this.u_addr3 = u_addr3;
	}
	public char getU_gender() {
		return u_gender;
	}
	public void setU_gender(char u_gender) {
		this.u_gender = u_gender;
	}
	public int getU_age() {
		return u_age;
	}
	public void setU_age(int u_age) {
		this.u_age = u_age;
	}
	public ArrayList<String> getFavorit_program() {
		return favorit_program;
	}
	public void setFavorit_program(ArrayList<String> favorit_program) {
		this.favorit_program = favorit_program;
	}
	public ArrayList<Integer> getbookmark() {
		return bookmark;
	}
	public void setbookmark(ArrayList<Integer> bookmark) {
		this.bookmark = bookmark;
	}
	public String getU_photo_url() {
		return u_photo_url;
	}
	public void setU_photo_url(String u_photo_url) {
		this.u_photo_url = u_photo_url;
	}
	
	public String get_AddressText()
	{
		String[] textAddress=new String[6];
		String result="";
		textAddress[0]=u_addr1;
		textAddress[1]=u_addr2;
		textAddress[2]=u_addr3;
		for(int i=0; i<3; i++)
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

	public void setU_alarm(int u_alarm) {
		this.u_alarm = u_alarm;
	}
	public int getU_alarm() {
		return u_alarm;
	}
}
