package ddoraemi.creategroup.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonObject;

import ddoraemi.creategroup.presenter.OnCreateGroup_FinishenListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class CreateGroup_Interactor implements CreateGroup_Interactor_Inteface{

	@Override
	public void createGroup(final Context context,final int p_id, final String u_id,final String g_name,
			final int g_want_persons,final String g_info,final int g_open_year,
			final int g_open_month,final int g_open_day,final int g_close_year,
			final int g_close_month,final int g_close_day,final int g_start_year,
			final int g_start_month,final int g_start_day,final int g_start_hour,
			final int g_start_min,final int g_end_hour,final int g_end_min, final OnCreateGroup_FinishenListener listener) {
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
					retofit.sendCreateGroupInfo(p_id, u_id, g_name,
							g_want_persons, g_info, g_open_year,
							g_open_month, g_open_day, g_close_year,
							g_close_month, g_close_day, g_start_year,
							g_start_month, g_start_day, g_start_hour,
							g_start_min, g_end_hour, g_end_min,
							new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result,
								Response response) {
							listener.onCreateGroupSuccess(result);
							progressdialog.dismiss();
						}
						@Override
						public void failure(
								RetrofitError retrofitError) {
							progressdialog.dismiss();
							listener.onfailure();

						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					listener.onfailure();
				}
			}

		}).start();
	}
}


