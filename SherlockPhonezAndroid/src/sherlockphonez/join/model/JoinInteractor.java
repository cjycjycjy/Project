package sherlockphonez.join.model;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;





import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.join.controller.OnJoinFinishedListener;
import sherlockphonez.retrofit.Retrofit;

public class JoinInteractor implements JoinInteractor_Interface{

	@Override
	public void join(final Context context, final String phoneNum, final String deviceId, final String passwd,
			final String name, final String email,
			final OnJoinFinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.join(name, passwd, email, phoneNum,deviceId,new Callback<String>() {
						@Override
						public void success(String result//
								,Response response) {
							if(context != null){
								if(result.equalsIgnoreCase("Failed"))
									listener.onFailedError();
								else if(result.equalsIgnoreCase("Exist"))
									listener.onExistError();
								else if(result.equalsIgnoreCase("Success"))
									listener.onSuccess();
								else if(result.equalsIgnoreCase("exception")){
									listener.onNetworkError();
								}else if(result.equalsIgnoreCase("Changed")){
									listener.onSuccess();
								}else if(result.equalsIgnoreCase("FailedChange")){
									listener.onFailedError();
								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new Customdialog_Base(context, "서버와의 통신에 실패하였습니다.").show();

						}
					});

				} catch (Throwable ex) {

					new Customdialog_Base(context, "서버와의 통신에 실패하였습니다.").show();
				}
			}
		}).start();

	}

	@Override
	public void changeinfo(final Context context, final String phoneNum, final String deviceId, final String passwd,
			final String name, final String email,
			final OnJoinFinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.changeinfo(name, passwd, email, phoneNum,deviceId,new Callback<String>() {
						@Override
						public void success(String result//
								,Response response) {
							if(context != null){
								if(result.equalsIgnoreCase("Failed"))
									listener.onFailedError();
								else if(result.equalsIgnoreCase("Exist"))
									listener.onExistError();
								else if(result.equalsIgnoreCase("Success"))
									listener.onSuccess();
								else if(result.equalsIgnoreCase("exception")){
									listener.onNetworkError();
								}else if(result.equalsIgnoreCase("Changed")){
									listener.onSuccess();
								}else if(result.equalsIgnoreCase("FailedChange")){
									listener.onFailedError();
								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new Customdialog_Base(context, "서버와의 통신에 실패하였습니다.").show();

						}
					});

				} catch (Throwable ex) {

					new Customdialog_Base(context, "서버와의 통신에 실패하였습니다.").show();
				}
			}
		}).start();

	}


}
