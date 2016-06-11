package com.example.sherlockphonez;

import com.google.android.gcm.GCMRegistrar;

import sherlockphonez.login.model.User;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;



public class AppController extends Application{
	private String phoneNum;
	private String phoneNum_not;
	private String deviceId;
	User user;

	private String resiId; 
	private String login_otherphoneNum;
	private String serverIp = "http://203.252.166.225/php/fuckphp/";	
	private boolean otherphoneLogin;


	public static AudioManager mAudio;
	public static MediaPlayer player;


	public String filename;

	public String streaming_phoneNum;




	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		//otherphoneLogin = false;
		
		user = new User();


		mAudio = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
		player = MediaPlayer.create(AppController.this, R.raw.alarm);
		player.setLooping(true);


		if(tm.getSimState()== TelephonyManager.SIM_STATE_ABSENT){
			
			String num="1";
			phoneNum_not = num;
			phoneNum = num;
			deviceId = num;
		}else{
			
			TelephonyManager systemService = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			phoneNum = systemService.getLine1Number();
			phoneNum = phoneNum.substring(phoneNum.length()-10,phoneNum.length());
			phoneNum="0"+phoneNum;

			phoneNum_not=phoneNum;
			phoneNum = PhoneNumberUtils.formatNumber(phoneNum);			
			deviceId=systemService.getDeviceId();
		}
		registerDevice();


	}
	
	public void registerDevice(){
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		GCMRegistrar.register(getBaseContext(), "490446909885");		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setotherphoneLogin(boolean temp){
		//this.otherphoneLogin=temp;		
		SharedPreferences setting = getSharedPreferences(
				"setting", MODE_PRIVATE);
		SharedPreferences.Editor editor = setting
				.edit();
		editor.putBoolean("auto_login", temp);
		editor.commit();
	}
	public boolean getotherphoneLogin(){
		SharedPreferences setting = getSharedPreferences(
				"setting", MODE_PRIVATE);
		boolean a_login = setting.getBoolean("auto_login", false);
		return a_login;
	}

	public String getPhoneNum_not(){
		return phoneNum_not;
	}

	public String getPhoneNum(){
		return phoneNum;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public String getServerIp(){
		return serverIp;
	}

	public void setResiId(String resiId){
		this.resiId = resiId;
	}

	public void setotherPhone(String other){
		this.login_otherphoneNum = other;
	}

	public String getOtherPhoneNum(){
		return login_otherphoneNum;
	}

	public void setStreamingphoneNum(String s){
		this.streaming_phoneNum=s;
	}


	public AudioManager getAudio(){
		return mAudio;
	}	
	public MediaPlayer getPlayer(){
		return player;
	}	

	public String getFilename(){
		return filename;
	}	

	public void setFileName(String filename){
		this.filename=filename;
	}	


}
