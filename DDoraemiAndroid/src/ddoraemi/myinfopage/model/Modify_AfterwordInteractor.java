package ddoraemi.myinfopage.model;

import java.io.ByteArrayOutputStream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.myinfopage.presenter.OnModify_AfterwordFinishedListner;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class Modify_AfterwordInteractor implements Modify_AfterwordInteractorInterface {

	@Override
	public void saveAfterword(final Activity context,
			final OnModify_AfterwordFinishedListner listener, final int a_id,
			final String a_content, final float a_grade, final String[] all_path,final boolean photochange) {
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					JsonObject info=new JsonObject();
					JsonArray photo=new JsonArray();
					for(String string : all_path)
					{
						Bitmap photobmp;
						BitmapFactory.Options option=new BitmapFactory.Options();
						option.inSampleSize=2;
						photobmp=BitmapFactory.decodeFile(string,option);
						ByteArrayOutputStream stream=new ByteArrayOutputStream();
						photobmp.compress(CompressFormat.JPEG, 100, stream);
						byte[] bytearray=stream.toByteArray();
						String s=Base64.encodeToString(bytearray, Base64.DEFAULT);
						JsonObject photoobj=new JsonObject();
						photoobj.addProperty("photo", s);
						photo.add(photoobj);
					
					}
					info.addProperty("a_id", a_id);
					info.addProperty("photochange", photochange);
					info.addProperty("a_content", a_content);
					info.addProperty("a_grade", a_grade);
					info.add("photo", photo);
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.modifyAfterWord(info,new Callback<JsonObject >() {
						@Override
						public void success(JsonObject result
								,Response response) {
							if (context != null) {
								listener.modifyAfterword(result);
								progressdialog.dismiss();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							Dialog_BaseDialog dialog=new Dialog_BaseDialog(context, "데이터를 받아오는데 실패하였습니다.");
							dialog.show();
						}
					});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					Dialog_BaseDialog dialog=new Dialog_BaseDialog(context, "데이터를 받아오는데 실패하였습니다.");
					dialog.show();
				}
			}
		}).start();
		
	
		
	}

}
