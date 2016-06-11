package ddoraemi.detailedgroupinfo.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.gson.JsonArray;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInputqna_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class DetailedGroup_inputqna_interactor implements DetailedGroup_inputqna_interactor_Interface{

	@Override
	public void setData(final FragmentActivity context,
			final OnDetailedGroupInputqna_FinishedListener listener,final int g_id, final String u_id, final String q_content) {
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
					retrofit.setQnA(u_id, g_id, q_content ,new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result
								,Response response) {
							if (context != null) {
								listener.onDataSetSuccess(result);
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

}
