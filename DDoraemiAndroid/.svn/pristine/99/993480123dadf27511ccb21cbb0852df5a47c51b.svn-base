package ddoraemi.detailedgroupinfo.model;

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
import android.support.v4.app.FragmentActivity;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInputqna_FinishedListener;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedQnA_reply_FinishedListener;
import ddoraemi.detailediteminfo.presenter.OnDetailedAfterword_reply_FinishedListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class DetailedQnA_reply_Interactor implements DetailedQnA_reply_Interactor_Interface {

	@Override
	public void deleteQnA(final Context context, final OnDetailedQnA_reply_FinishedListener listener,final int q_id) {
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
					retrofit.deleteQnA(q_id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result
								,Response response) {
							if (context != null && result.get("result").getAsString().equalsIgnoreCase("200")) {
								listener.onQnADeleteSuccess(result);
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
	@Override
	public void setReply(final Context context, final int q_id, final String u_id, final String q_r_content,
			final OnDetailedQnA_reply_FinishedListener listener) {
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
					retrofit.setQnAReply(q_id, u_id, q_r_content, new Callback<JsonArray>() {
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
	public void modifyReply(final Context context, final OnDetailedQnA_reply_FinishedListener listener, final int q_r_id, final int q_id,
			final String q_content) {
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
					retrofit.modifyQnAReply(q_id, q_r_id, q_content, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							if (context != null) {
								JsonArray arr = result.getAsJsonArray("question_reply");
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
	public void deleteReply(final Context context, final OnDetailedQnA_reply_FinishedListener listener, final int q_r_id, final int q_id) {
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
					retrofit.deleteQnAReply(q_id, q_r_id, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							if (context != null) {
								JsonArray arr = result.getAsJsonArray("question_reply");
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
					ex.printStackTrace();
					progressdialog.dismiss();
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
