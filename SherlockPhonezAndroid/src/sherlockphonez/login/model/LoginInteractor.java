package sherlockphonez.login.model;


import org.json.JSONException;
import org.json.JSONObject;

import com.example.sherlockphonez.AppController;
import com.google.gson.JsonObject;

import android.content.Context;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.login.controller.OnLoginFinishedListener;
import sherlockphonez.retrofit.Retrofit;

public class LoginInteractor implements LoginInteractor_Interface{
	AppController app;
	@Override
	public void LoginOtherPhone(final Context context, final String phoneNum, final String passwd,
			final OnLoginFinishedListener listener, final String msg) {
		// TODO Auto-generated method stub
		app = (AppController)context.getApplicationContext();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.login(phoneNum,passwd,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result//
								,Response response) {
							String r = result.get("result").getAsString();
							if(context != null){
								if(r.equalsIgnoreCase("Success")){
									JsonObject userobj = result.get("user").getAsJsonObject();
									String name = userobj.get("u_name").getAsString();
									String email = userobj.get("u_email").getAsString();
									String phone = userobj.get("u_phonenum").getAsString();
									User user = new User(phone, email, name);
									app.setUser(user);
									if(msg.equalsIgnoreCase("login")){
										listener.onSuccess(phoneNum);
									}else{
										listener.onChangeInfoLoginSuccess(user);
									}
									
								}else if(r.equalsIgnoreCase("Incorrect phoneNum")){
									listener.onNotFoundUserError();
								}else if(r.equalsIgnoreCase("Incorrect passwd")){
									listener.onPasswdNotEqualError();
								}else if(r.equalsIgnoreCase("exception")){
									listener.onNetworkError();
								}else if(r.equalsIgnoreCase("Failed")){
									listener.onFailedError();
								}else{
									

								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							listener.onNetworkError();

						}
					});

				} catch (Throwable ex) {
					listener.onNetworkError();
				}
			}
		}).start();
	}

	@Override
	public void SendEMail(final String phoneNum,final String eMail, 
			final OnLoginFinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.findpasswd(phoneNum,eMail,new Callback<String>() {
						@Override
						public void success(String result//
								,Response response) {
							if(result.equalsIgnoreCase("Success")){
								listener.onSendMailSuccess();
							}else{
								listener.onFailedError();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
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
