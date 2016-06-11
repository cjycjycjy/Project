package ddoraemi.detailedgroupinfo.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInfo_Info_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class DetailedGroupInfo_Info_Interactor implements DetailedGroupInfo_Info_Interactor_Interface{

	
	@Override
	public void cancelGroup(final FragmentActivity context, final int g_id,final String u_id,
			final OnDetailedGroupInfo_Info_FinishedListener listener) {
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
					retrofit.cancelgroup(g_id, u_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result
								,Response response) {
							if (context != null) {
								String r = result.get("result").getAsString();
								if(r.equalsIgnoreCase("200")){
									listener.onCancelGroupSuccess(result.get("persons").getAsInt());
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
					progressdialog.dismiss();
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

	@Override
	public void deleteGroup(final FragmentActivity context, final int g_id,
			final OnDetailedGroupInfo_Info_FinishedListener listener) {
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
					retrofit.deletegroup(g_id,new Callback<String>() {
						@Override
						public void success(String result
								,Response response) {
							if (context != null && result.equalsIgnoreCase("200")) {
								listener.onDeleteGroupSuccess();
								
							}else{
								
							}
							progressdialog.dismiss();
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
	public void getinfo(final FragmentActivity context, final int p_id,
			final OnDetailedGroupInfo_Info_FinishedListener listener) {
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
					retrofit.getprogram_pid(p_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result
								,Response response) {
							if (context != null) {
								listener.onGoToIteminfo(result);

								progressdialog.dismiss();
								
							}else{
								
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
					progressdialog.dismiss();
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
