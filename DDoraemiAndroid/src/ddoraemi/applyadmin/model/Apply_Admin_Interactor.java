package ddoraemi.applyadmin.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.gson.JsonObject;

import ddoraemi.applyadmin.presenter.OnApply_Admin_Request_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;
import android.app.ProgressDialog;
import android.content.Context;

public class Apply_Admin_Interactor implements Apply_Admin_Interactor_Interface{

	@Override
	public void sendRequest(final Context context,final String u_id,final String a_name,final String a_phonenum,
			final String a_p_phonenum,final String a_email, final String a_programinfo,
			final OnApply_Admin_Request_FinishedListener listener) {
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
					.setEndpoint(Retrofit.ROOT) // call your base
					// url
					.build();
					Retrofit retofit = restAdapter.create(Retrofit.class);
					retofit.requestadmin(u_id, a_name, a_phonenum, a_p_phonenum, a_email,a_programinfo,
							new Callback<String>() {
						@Override
						public void success(String result,
								Response response) {
							if(result.equalsIgnoreCase("200"))
								listener.onRequestSuccess(result);
							else
								listener.onFailed();

							progressdialog.dismiss();
						}
						@Override
						public void failure(
								RetrofitError retrofitError) {
							progressdialog.dismiss();
							listener.onFailed();

						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					listener.onFailed();
				}
			}

		}).start();

	}

}
