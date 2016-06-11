package com.example.sherlockphonez;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.retrofit.Retrofit;



public class ResponseGCM {
	AppController app;
	String otherPhoneNum;
	String msg;


	public ResponseGCM(AppController app, String phoneNum, String msg){
		this.app=app;
		this.otherPhoneNum = phoneNum;
		this.msg = msg;
	}
	public void sendStateMessage(){
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.sendgcm(msg, otherPhoneNum, new Callback<String>() {
						@Override
						public void success(String result, Response arg1) {
							// TODO Auto-generated method stub
							int a=0;
						}
						@Override
						public void failure(RetrofitError arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				} catch (Throwable ex) {

				}
			}
		}).start();

//		ArrayList<NameValuePair> nameValuePair=null;
//		try {
//			nameValuePair=new ArrayList<NameValuePair>(2);
//			nameValuePair.add(new BasicNameValuePair("phoneNum", otherPhoneNum));
//			nameValuePair.add(new BasicNameValuePair("msg", msg));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		AsyncCallable<ArrayList<NameValuePair>, String> callable=
//				new AsyncCallable<ArrayList<NameValuePair>, String>
//		(app.getServerIp()+"alarm.php", nameValuePair);
//
//
//		new AsyncExecutor<ArrayList<NameValuePair>, String>()
//		.setCallable(callable)
//		.setCallback(new AsyncCallback<String>() {
//
//			@Override
//			public void onResult(String result) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void exceptionOccured(Exception e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void cancelled() {
//				// TODO Auto-generated method stub
//
//			}
//		})
//		.execute();
	}
}
