package ddoraemi.detailedgroupinfo.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.gson.JsonArray;

import ddoraemi.detailedgroupinfo.presenter.OnJoinGroup_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class JoinGroup_Interactor implements JoinGroup_Interactor_Interface{

	@Override
	public void joinGroup(final Context context, final int g_id,final String u_id, final int hopeperson,
			final OnJoinGroup_FinishedListener listener) {
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
					retrofit.joingroup(g_id, u_id,hopeperson,new Callback<String>() {
						@Override
						public void success(String result
								,Response response) {
							if (context != null) {
								if(result.equalsIgnoreCase("200")){
									listener.onJoinSuccess(result);
								}else{
									listener.onJoinFailed();
								}
								
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
