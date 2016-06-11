package ddoraemi.myinfopage.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import com.google.gson.JsonObject;

import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.myinfopage.presenter.OnMyPageFinishedListner;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class MyPageInteractor implements MyPageInteractorInterface{
	
	@Override
	public void getMyPageInfo(final FragmentActivity context,
			final OnMyPageFinishedListner listener,final String user_id) {

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
					retrofit.getMyPageInfo(user_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result//��Ű��
								,Response response) {
							if (context != null) {
								listener.OnGetDataSuccess(result);
								progressdialog.dismiss();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							Dialog_BaseDialog dialog=new Dialog_BaseDialog(context, "데이터를 받아오는데 실패하였습니다.");
							dialog.show();
							progressdialog.dismiss();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					Dialog_BaseDialog dialog=new Dialog_BaseDialog(context, "데이터를 받아오는데 실패하였습니다.");
					dialog.show();
					progressdialog.dismiss();
				}
			}
		}).start();
		
	}

	@Override
	public void renewMyPageInfo(final FragmentActivity context,
			final OnMyPageFinishedListner listener, final String user_id) {
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
					retrofit.getMyPageInfo(user_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result//��Ű��
								,Response response) {
							if (context != null) {
								listener.OnGetDataSuccessForRenew(result);
							}
							progressdialog.dismiss();
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							Dialog_BaseDialog dialog=new Dialog_BaseDialog(context, "데이터를 받아오는데 실패하였습니다.");
							dialog.show();
							progressdialog.dismiss();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					Dialog_BaseDialog dialog=new Dialog_BaseDialog(context, "데이터를 받아오는데 실패하였습니다.");
					dialog.show();

					progressdialog.dismiss();
				}
			}
		}).start();
		
		
	}
}
