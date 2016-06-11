package ddoraemi.myinfopage.view;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyPage_AlarmSettingView extends Activity implements OnClickListener {
	EditText phonenum;
	ImageView sendbt, back, iv_alarm,iv_complete;
	
	AppController app;
	int u_alarm;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_setting);
		app = (AppController)getApplicationContext();
		init();
	}

	public void init() {
		back = (ImageView) findViewById(R.id.btn_left_menu);
		back.setOnClickListener(this);
		iv_alarm = (ImageView)findViewById(R.id.iv_alarm);
		iv_alarm.setOnClickListener(this);
		u_alarm=app.getUser().getU_alarm();
		if(u_alarm==1){
			iv_alarm.setImageResource(R.drawable.icon_checkactive);
		}
		else{
			iv_alarm.setImageResource(R.drawable.icon_check);
		}
		iv_complete = (ImageView)findViewById(R.id.iv_complete);
		iv_complete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_left_menu){
			finish();
		}
		if(v.getId()==R.id.iv_alarm){
			if(u_alarm==1){
				iv_alarm.setImageResource(R.drawable.icon_check);
				u_alarm=0;
			}
			else{
				iv_alarm.setImageResource(R.drawable.icon_checkactive);
				u_alarm=1;
			}
		}
		if(v.getId()==R.id.iv_complete){
			setAlarm(u_alarm);
		}
	}
	public void setAlarm(final int alarm) {
		final Context context = this;
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage("잠시만 기다려주세요");
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
					Retrofit retofit = restAdapter.create(Retrofit.class);
					retofit.setAlarm(app.getId(),alarm, new Callback<String>() {
						@Override
						public void success(String result, Response response) {
							progressdialog.dismiss();
							if (context != null) {
								if (result.equals("200")) {
									app.getUser().setU_alarm(alarm);
									finish();
								}
								else{
									Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,"변경에 실패했습니다.");
									dialog.show();
								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							if (context != null) {

								Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,
										getString(R.string.failed_dialog));
								dialog.show();
							}
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
					if (context != null) {
						Dialog_BaseDialog dialog = new Dialog_BaseDialog(context, getString(R.string.failed_dialog));
						dialog.show();
					}
				}
			}
		}).start();
	}
}
