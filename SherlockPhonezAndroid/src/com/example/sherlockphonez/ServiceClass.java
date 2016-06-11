package com.example.sherlockphonez;
import java.util.ArrayList;







import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.myphonecontroll.view.Preview;
import sherlockphonez.retrofit.Retrofit;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import android.media.MediaPlayer;

public class ServiceClass extends Service
{
	///////////////////// ��ġ���� ������ ���� �κ�///////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	private LocationManager manager;
	private double latitude;
	private double longitude;
	private String phoneNum;
	private String deviceId;
	private MyLocationListener listener;
	private Criteria criteria;
	private String provider;
	private Location location;
	private AudioManager mAudioManager;
	private RingtoneManager ringtoneManager;
	private Ringtone alarmRingtone;
	private AppController app;

	boolean isExecute = false;

	private WindowManager wm;
	private WindowManager.LayoutParams params;
	private RelativeLayout  a=null;
	private Camera mCamera;
	private Preview mPreview;
	private PictureCallback mPicture = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			final String image = Base64.encodeToString(data, Base64.DEFAULT);
			new Thread(new Runnable() {
				public void run() {
					try {
						RestAdapter restAdapter = new RestAdapter.Builder()
						.setEndpoint(Retrofit.ROOT) // call your base url
						.build();
						JsonObject obj = new JsonObject();
						obj.addProperty("p_u_phonenum", phoneNum);
						obj.addProperty("image", image);
						
						Retrofit retrofit = restAdapter.create(Retrofit.class); 
						retrofit.sendImage(obj,new Callback<String>() {
							@Override
							public void success(String result, Response arg1) {
								// TODO Auto-generated method stub
								if(a != null){
									((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(a);
									a = null;
									isExecute = false;
									mCamera = null;
								}
							}
							@Override
							public void failure(RetrofitError arg0) {
								// TODO Auto-generated method stub
								int a =0;
							}
						});
					} catch (Throwable ex) {
						int ab=0;
					}
				}
			}).start();


			//			String ba1 = Base64.encodeToString(data, Base64.DEFAULT);
			//			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//			nameValuePairs.add(new BasicNameValuePair("image", ba1));
			//			nameValuePairs.add(new BasicNameValuePair("phoneNum", phoneNum));    
			//
			//			AsyncCallable<ArrayList<NameValuePair>, String> callable=
			//					new AsyncCallable<ArrayList<NameValuePair>, String>
			//			(app.getServerIp()+"savecamera.php", nameValuePairs);
			//
			//			new AsyncExecutor<ArrayList<NameValuePair>, String>()
			//			.setCallable(callable)
			//			.setCallback(new AsyncCallback<String>() {
			//
			//				@Override
			//				public void onResult(String result) {
			//					// TODO Auto-generated method 
			//					if(a != null)        //���� ����� �� ����. *�߿� : �並 �� ���� �ؾ���.
			//					{
			//						((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(a);
			//						a = null;
			//						isExecute = false;
			//						mCamera = null;
			//					}
			//
			//				}
			//
			//				@Override
			//				public void exceptionOccured(Exception e) {
			//					// TODO Auto-generated method stub
			//
			//				}
			//
			//				@Override
			//				public void cancelled() {
			//					// TODO Auto-generated method stub
			//
			//				}
			//			})
			//			.execute();
		}
	}; 


	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		phoneNum = intent.getStringExtra("phoneNum_not");
		// deviceId = intent.getStringExtra("deviceId");
		getMyLocation();
		IntentFilter intentFilter= new  IntentFilter();   
		intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
		intentFilter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(screenOnOffBroadcastReceiver ,intentFilter);
		Log.d("onPostCreate", "onStartCommand");
		return START_REDELIVER_INTENT;
	}

	private void getMyLocation() {
		if (manager == null) {
			manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		}
		// provider ������||GPS �� ���ؼ� �������� �˷��ִ� Stirng ����
		// minTime �ּ��� �󸶸��� �ð��� �帥�� ��ġ������ �������� �ð������� ���� �����ϴ� ����
		// minDistance �󸶸��� �Ÿ��� �������� ��ġ������ �������� �����ϴ� ����
		// manager.requestLocationUpdates(provider, minTime, minDistance, listener);

		// 10��
		long minTime = 500;

		// �Ÿ��� 0���� ���� 
		// �׷���  �ð��� �Ÿ� ������ ���� ���������ʰ� 10�ʵڿ� �ٽ� ��ġ������ �޴´�
		float minDistance = 30;
		if (manager.isProviderEnabled(manager.NETWORK_PROVIDER) == true) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else{
			provider = LocationManager.GPS_PROVIDER;
		}
		if(listener == null){
			listener = new MyLocationListener();
		}
		manager.requestLocationUpdates(provider, minTime, minDistance, listener);
	}
	class MyLocationListener implements LocationListener {

		
		@Override
		public void onLocationChanged(Location location) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			Toast.makeText(ServiceClass.this,Double.toString(location.getLatitude()),Toast.LENGTH_SHORT).show();
			Toast.makeText(ServiceClass.this,Double.toString(location.getLongitude()),Toast.LENGTH_SHORT).show();
			sendlocation();
		}

		@Override
		public void onProviderDisabled(String provider) {

		}
		@Override
		public void onProviderEnabled(String provider) {

		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}
	public void sendlocation(){
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.registerlocation(phoneNum, latitude, longitude, new Callback<String>() {

						@Override
						public void failure(RetrofitError arg0) {
							// TODO Auto-generated method stub
							int a=0;
						}

						@Override
						public void success(String arg0, Response arg1) {
							// TODO Auto-generated method stub
							int b=0;
						}
					});
				} catch (Throwable ex) {

				}
			}
		}).start();
	}

	BroadcastReceiver screenOnOffBroadcastReceiver = new BroadcastReceiver() {
		public static final String ScreenOff = "android.intent.action.SCREEN_OFF";
		public static final String ScreenOn = "android.intent.action.SCREEN_ON";



		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ScreenOff)){
				
			}

			else if (intent.getAction().equals(ScreenOn)){
				
				//				isForeground=false;
				if(isExecute == false && mCamera == null){
					mCamera=Camera.open(findFrontSideCamera());

					if(a != null)        //���� ����� �� ����. *�߿� : �並 �� ���� �ؾ���.
					{
						((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(a);
						a = null;
					}
					a=new RelativeLayout(context);
					a.setLayoutParams(new RelativeLayout.LayoutParams(1,1));
					a.addView(mPreview=new Preview(context,mCamera, mPicture));

					WindowManager.LayoutParams params = new WindowManager.LayoutParams(
							WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,   
							WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,     
							PixelFormat.TRANSLUCENT);  


					wm.addView(a, params);  
				}


			}
		} 
	};
	@Override
	public void onCreate(){
		super.onCreate();
		app=(AppController)getApplicationContext();
		params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,   
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,      
				PixelFormat.TRANSLUCENT);                                                         

		wm = (WindowManager) getSystemService(WINDOW_SERVICE); //���� �Ŵ���
		Log.d("onPostCreate", "onCreate");

	}


	@Override
	public void onDestroy() { 
		super.onDestroy();
		manager.removeUpdates(listener);
		Log.d("onPostCreate", "onDestroy");

		unregisterReceiver(screenOnOffBroadcastReceiver);
		if(a != null)        
		{
			((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(a);
			a = null;
		}
		wm=null;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d("onPostCreate", "onBind");
		return null;
	}

	private int findFrontSideCamera() {
		int cameraId = -1;
		int numberOfCameras = android.hardware.Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo cmInfo = new CameraInfo();
			android.hardware.Camera.getCameraInfo(i, cmInfo);
			if (cmInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
				cameraId = i;
				break;
			}
		}
		return cameraId;

	}



}







