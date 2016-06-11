package sherlockphonez.myphonecontroll.model;

import android.content.Context;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.myphonecontroll.controller.OnMyPhoneControlFinishedListener;
import sherlockphonez.retrofit.Retrofit;

public class MyPhoneControllInteractor implements MyPhoneControllInteractor_Interface{

	@Override
	public void sendGCM(final Context context, final String phoneNum, final String msg,
			final OnMyPhoneControlFinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.sendgcm(msg, phoneNum, new Callback<String>() {
						@Override
						public void success(String result, Response arg1) {
							// TODO Auto-generated method stub
							if(context != null && result.equalsIgnoreCase("Success")){
								if(msg.equalsIgnoreCase("alarmOn")){
									listener.onAlarmOn();
								}else if(msg.equalsIgnoreCase("alarmOff")){
									listener.onAlarmOff();
								}
								else{
									listener.onSetPasswd();
								}
							}else{
								listener.onNetworkError();
							}
						}
						@Override
						public void failure(RetrofitError arg0) {
							// TODO Auto-generated method stub
							listener.onNetworkError();
						}
					});
				} catch (Throwable ex) {
					listener.onNetworkError();
				}
			}
		}).start();

	}

}
