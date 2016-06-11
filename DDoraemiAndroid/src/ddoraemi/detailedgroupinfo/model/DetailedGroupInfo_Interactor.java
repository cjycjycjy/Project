package ddoraemi.detailedgroupinfo.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInfo_FinishedListener;
import ddoraemi.detailedgroupinfo.presenter.OnJoinPersonInfo_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class DetailedGroupInfo_Interactor implements DetailedGroupInfo_Interactor_Interface{
	
	JsonObject personResult;
	
	public void getpersoninfo(final FragmentActivity context,final int g_id , final OnDetailedGroupInfo_FinishedListener listener) {
		// TODO Auto-generated method stub
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);		
					retrofit.getjoinuser(g_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result
								,Response response) {
							if (context != null) {
								if(result.get("result").getAsString().equalsIgnoreCase("200")){
									personResult = result;
									getData(context, listener, g_id);
								}else{
									
								}

								progressdialog.dismiss();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context)
									.setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton(
											"확인",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dia,
														int which) {
													dia.dismiss();
												}
											}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
					new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia, int which) {
											dia.dismiss();
										}
									}).show();
				}
			}
		}).start();
	}
	
	@Override
	public void getData(final FragmentActivity context,
			final OnDetailedGroupInfo_FinishedListener listener, final int g_id) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);		
					retrofit.getQuestion(g_id,new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result
								,Response response) {
							if (context != null) {
								listener.onDataGetSuccess(result, personResult);
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new AlertDialog.Builder(context)
									.setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton(
											"확인",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dia,
														int which) {
													dia.dismiss();
												}
											}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia, int which) {
											dia.dismiss();
										}
									}).show();
				}
			}
		}).start();
	}
	
	
	
	public void getrenewpersoninfo(final FragmentActivity context,final int g_id , final OnDetailedGroupInfo_FinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);		
					retrofit.getjoinuser(g_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result
								,Response response) {
							if (context != null) {
								if(result.get("result").getAsString().equalsIgnoreCase("200")){
									personResult = result;
									renewData(context, listener, g_id);
								}else{
									
								}
								
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new AlertDialog.Builder(context)
									.setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton(
											"확인",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dia,
														int which) {
													dia.dismiss();
												}
											}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia, int which) {
											dia.dismiss();
										}
									}).show();
				}
			}
		}).start();
	}
	
	public void renewData(final FragmentActivity context,
			final OnDetailedGroupInfo_FinishedListener listener, final int g_id) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);		
					retrofit.getQuestion(g_id,new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result
								,Response response) {
							if (context != null) {
								listener.onRenewDataGetSuccess(result, personResult);
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new AlertDialog.Builder(context)
									.setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton(
											"확인",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dia,
														int which) {
													dia.dismiss();
												}
											}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia, int which) {
											dia.dismiss();
										}
									}).show();
				}
			}
		}).start();
	}
}
