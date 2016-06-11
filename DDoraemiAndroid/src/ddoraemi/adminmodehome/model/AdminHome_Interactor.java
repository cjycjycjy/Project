package ddoraemi.adminmodehome.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.adminmodehome.presenter.OnAdminHome_FinishenListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class AdminHome_Interactor implements AdminHome_InteratorInterface {

	@Override
	public void getProgram(final Context context,final String u_id,
			final OnAdminHome_FinishenListener listener) {
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
					retofit.getAdmin_Program(u_id, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							String resultstr = result.get("result")
									.getAsString();
							JsonArray resulyarray = result
									.getAsJsonArray("program");
							if (resultstr.equals("200"))
								listener.onSuccess(resulyarray);
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
