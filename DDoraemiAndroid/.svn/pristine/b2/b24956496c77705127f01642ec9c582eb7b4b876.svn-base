package ddoraemi.adminmodehome.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.adminmodehome.presenter.OnAdminHomeMain_FinishenListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class AdminHomeMainInteractor implements AdminHomeMainInteractorInterface {

	@Override
	public void getProgramInfo(final Context context,final int p_id,
			final OnAdminHomeMain_FinishenListener listener) {
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
					retofit.getAdmin_ProgramInfo(p_id, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							String resultstr = result.get("result")
									.getAsString();
							
							if (resultstr.equals("200"))
								listener.onSuccess(result);
							else
								listener.onFail();


							progressdialog.dismiss();
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							listener.onFail();
							progressdialog.dismiss();

						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					listener.onFail();
				}
			}

		}).start();
		
	}

	@Override
	public void Renew(final Context context,final int p_id, final OnAdminHomeMain_FinishenListener listener) {
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
					retofit.getAdmin_ProgramInfo(p_id, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							String resultstr = result.get("result")
									.getAsString();
							
							if (resultstr.equals("200"))
								listener.onRenew(result);
							else
								listener.onFail();


							progressdialog.dismiss();
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							listener.onFail();

						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					listener.onFail();
				}
			}

		}).start();
		
	}

}
