package ddoraemi.programlist.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.gson.JsonArray;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import ddoraemi.programlist.presenter.OnProgramlist_type_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class Programlist_type_Interactor implements Programlist_type_Interactor_Interface{

	@Override
	public void getData(final Fragment context,
			final OnProgramlist_type_FinishedListener listener,final String category, final String e_name) {
		// TODO Auto-generated method stub

		final ProgressDialog progressdialog = new ProgressDialog(context.getActivity());
		progressdialog.setMessage(context.getActivity().getString(R.string.loading));
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
					retrofit.getProgramList_type(category,e_name,new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result//��Ű��
								,Response response) {
							if (context != null) {
								listener.getDataSuccess(result);
								progressdialog.dismiss();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context.getActivity())
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
					new AlertDialog.Builder(context.getActivity())
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
