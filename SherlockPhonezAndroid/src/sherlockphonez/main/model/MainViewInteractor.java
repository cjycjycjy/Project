package sherlockphonez.main.model;

import android.content.Context;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.main.controller.OnMainFinishedListener;
import sherlockphonez.retrofit.Retrofit;

public class MainViewInteractor implements MainView_InteractorInterface{

	@Override
	public void main(final Context context, final String PhoneNum, final String deviceId, final String msg,
			final OnMainFinishedListener listener) {
		// TODO Auto-generated method stub
		if(msg.equalsIgnoreCase("start")){
			new Thread(new Runnable() {
				public void run() {
					try {
						RestAdapter restAdapter = new RestAdapter.Builder()
						.setEndpoint(Retrofit.ROOT) // call your base url
						.build();
						Retrofit retrofit = restAdapter.create(Retrofit.class); 
						retrofit.startService(PhoneNum, new Callback<String>() {
							@Override
							public void success(String result, Response arg1) {
								// TODO Auto-generated method stub
								if(context != null){

									if(result.equalsIgnoreCase("Success")){
										listener.startService();
									}
									else if(result.equalsIgnoreCase("Failed")){
										listener.setStartFailedError();
									}
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
		}else{
			new Thread(new Runnable() {
				public void run() {
					try {
						RestAdapter restAdapter = new RestAdapter.Builder()
						.setEndpoint(Retrofit.ROOT) // call your base url
						.build();
						Retrofit retrofit = restAdapter.create(Retrofit.class); 
						retrofit.stopService(PhoneNum, new Callback<String>() {
							@Override
							public void success(String result, Response arg1) {
								// TODO Auto-generated method stub
								if(context != null){

									if(result.equalsIgnoreCase("Success")){
										listener.stopService();
									}
									else if(result.equalsIgnoreCase("Failed")){
										listener.setStopFailedError();
									}														
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

}



