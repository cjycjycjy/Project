package com.example.sherlockphonez;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.retrofit.Retrofit;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.util.Log;

import com.example.sherlockphonez.DPM.DpmClass;
import com.google.android.gcm.GCMBaseIntentService;
public class GCMIntentService extends GCMBaseIntentService 
{
	private AppController app;

	private int mode;
	private String regiId;
	private String[] result=null;

	private DevicePolicyManager devicePolicyManager;
	private ComponentName adminComponent;
	private int filename;

	private String INTENT_FILTER_GCM =  "com.google.android.c2dm.intent.DISPLAY_MESSAGE";


	public GCMIntentService() {
		// TODO Auto-generated constructor stub
		super("586549777396");

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = (AppController)getApplicationContext();

	}

	@Override
	protected void onError(Context arg0, String arg1) {

	}

	@Override
	protected void onMessage(Context context, Intent intent) {

		final String msg = intent.getStringExtra("message");	
		Log.e("getmessage", "getmessage:" + msg);


		if(msg.equalsIgnoreCase("alarmOn")){
			mode = app.mAudio.getRingerMode();
			if(mode == AudioManager.RINGER_MODE_SILENT || mode == AudioManager.RINGER_MODE_VIBRATE){
				app.mAudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}
			app.mAudio.setStreamVolume(AudioManager.STREAM_MUSIC,
					(int)app.mAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
			app.player.start();
		}
		else if(msg.equalsIgnoreCase("alarmOff")){	
			if(app.player.isPlaying()){
				app.player.pause();
				app.player.stop();
				app.player.prepareAsync();
			}
		}
		else if(msg.toString().length()>3 && msg.substring(0, 4).equalsIgnoreCase("Live")){	
			result = msg.split("/");
			app.setStreamingphoneNum(result[1]);

			Intent service = new Intent("camera");
			service.putExtra("filename", result[2]);
			startService(service);
		}
		else if(msg.equalsIgnoreCase("stopstream")){
			Intent service = new Intent("camera");
			startService(service);
		}else if(msg.equalsIgnoreCase("change")){
			Intent service = new Intent("camera");
			service.putExtra("msg", "change");
			startService(service);
		}else if(msg.toString().length()>5 && msg.substring(0, 6).equalsIgnoreCase("state/")){
			String temp;
			SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
			boolean serviceOnOff=prefs.getBoolean("service", false);
			if(serviceOnOff){
				temp="statevalue/t/";
			}else
				temp="statevalue/f/";

			temp += getBatteryPercentage(this);

			ResponseGCM sendState = new ResponseGCM(app, msg.substring(6), temp);
			sendState.sendStateMessage();

		}else if(msg.toString().length()>10 && msg.toString().substring(0, 11).equalsIgnoreCase("statevalue/")){
			Intent state = new Intent("state");
			state.putExtra("value", msg);
			sendBroadcast(state);
		}else if(msg.toString().contains("service")){
			Intent service = new Intent("com.example.sherlockphonez");
			service.putExtra("phoneNum_not", app.getPhoneNum_not());

			startService(service);
			SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("service", true);
			editor.commit();
			//sendToserver("start",null);
		}

		else
		{
			devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
			adminComponent = new ComponentName(this, DpmClass.class);
			devicePolicyManager.setPasswordMinimumLength(adminComponent, 1);

			devicePolicyManager.resetPassword(msg, DeviceAdminInfo.USES_POLICY_RESET_PASSWORD);
			devicePolicyManager.lockNow();
		}
	}

	public String getBatteryPercentage(Context context){
		Intent batteryStatus = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale;
		int i=(int)(batteryPct * 100);

		return Integer.toString(i);
	}

	
	@Override

	protected void onRegistered(Context context, String reg_id) {

		regiId=reg_id;
		app.setResiId(regiId);
		sendToserver(reg_id,app.getPhoneNum_not());
	}

	public void sendToserver(final String value, final String phoneNum){
		//등록되면 통신해서 보내주기
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.sendRegiId(value,phoneNum, new Callback<String>() {
						@Override
						public void success(String result, Response arg1) {
							// TODO Auto-generated method stub
							int a=0;
						}
						@Override
						public void failure(RetrofitError arg0) {
							// TODO Auto-generated method stub
							int a=0;
						}
					});
				} catch (Throwable ex) {

				}
			}
		}).start();
	}
	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}
}