package ddoraemi.detailediteminfo.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.gson.JsonArray;

import ddoraemi.detailediteminfo.presenter.OnGetPicture_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class DetailedItemInfo_Picture_Interactor implements DetailedItemInfo_Picture_Interactor_Interface{

	@Override
	public void getPicture(final int p_id, final Context context,
			final OnGetPicture_FinishedListener listener) {
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
					retrofit.getPicture(p_id, new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result
								,Response response) {
							if (context != null) {
								listener.onGetPictureSuccess(result);
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
