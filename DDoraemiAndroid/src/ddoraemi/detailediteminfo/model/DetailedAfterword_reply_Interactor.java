package ddoraemi.detailediteminfo.model;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import ddoraemi.detailediteminfo.presenter.OnDetailedAfterword_reply_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class DetailedAfterword_reply_Interactor implements DetailedAfterword_reply_Interactor_Interface {
	String test = "";
	@Override
	public void setReply(final Context context, final int a_id, final String u_id, final String a_r_content,
			final OnDetailedAfterword_reply_FinishedListener listener) {
		// TODO Auto-generated method stub
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT) // call
																									// your
																									// base
																									// url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.setReply(a_id, u_id, a_r_content, new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result, Response response) {
							if (context != null) {
								listener.onDataSetSuccess(result);

								progressdialog.dismiss();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton("확인", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dia, int which) {
									dia.dismiss();
								}
							}).show();
						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();

	}

	@Override
	public void modifyReply(final Context context,final int a_r_id,final int a_id, final String a_r_content,
			final OnDetailedAfterword_reply_FinishedListener listener) {

		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT)
							// call
							// your
							// base
							// url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.modifyReply(a_id,a_r_id,a_r_content, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							if (context != null) {
								JsonArray arr = result.getAsJsonArray("afterword_reply");
								listener.onDataSetSuccess(arr);
								progressdialog.dismiss();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton("확인", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dia, int which) {
									dia.dismiss();
								}
							}).show();
						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();

	}

	@Override
	public void deleteReply(final Context context, final int a_r_id,final int a_id, final OnDetailedAfterword_reply_FinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT)
							// call
							// your
							// base
							// url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.deleteReply(a_id,a_r_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							if (context != null) {
								JsonArray arr = result.getAsJsonArray("afterword_reply");
								listener.onDataSetSuccess(arr);
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton("확인", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dia, int which) {
									dia.dismiss();
								}
							}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();
	}

	@Override
	public void deleteAfterword(final Context context, final int a_id,
			final OnDetailedAfterword_reply_FinishedListener listener) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT)
							// call
							// your
							// base
							// url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.deleteAfterWord(a_id,new Callback<String>() {
						@Override
						public void success(String result, Response response) {
							if (context != null) {
								listener.onDeleteSuccess(result);
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton("확인", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dia, int which) {
									dia.dismiss();
								}
							}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();
		
	}
}