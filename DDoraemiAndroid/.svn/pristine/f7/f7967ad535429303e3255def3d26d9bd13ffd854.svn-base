package ddoraemi.myinfopage.view;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import ddoraemi.appcontroller.AppController;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.dialog.Dialog_ChangeFavorite;
import ddoraemi.dialog.Dialog_confirmPassword;
import ddoraemi.joining.view.Fragment_JoinTwo;
import ddoraemi.joining.view.JoinActivity;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.LoginActivity;
import ddoraemi.start.R;
import ddoraemi.start.SplashActivity;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyPage_PasswdSettingView extends Activity implements OnClickListener {

	ImageView sendbt, back;
	AppController app;
	EditText edit_passwd, edit_confirmpasswd;
	Dialog_confirmPassword dialog;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_setting);
		app = (AppController) getApplicationContext();
		init();
	}

	public void init() {

		sendbt = (ImageView) findViewById(R.id.iv_complete);
		sendbt.setOnClickListener(this);

		back = (ImageView) findViewById(R.id.btn_left_menu);
		back.setOnClickListener(this);

		edit_passwd = (EditText)findViewById(R.id.join_fragmentone_PasswdEditText);
		edit_confirmpasswd = (EditText)findViewById(R.id.join_fragmentone_ConfirmPasswdEditText);
		dialog = new Dialog_confirmPassword(this,"비밀번호가 변경되었습니다.");
		dialog.getOkBtn().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_left_menu) {
			finish();
		}
		if (v.getId() == R.id.iv_complete) {
			// //입력조건전부 따진후에
			if (ConfirmForNext()) {
				sendChangePasswd();
			}
		}
		if(v.getId()==R.id.dialog_basedialog_btnok){
			finish();
		}
	}
	
	public boolean ConfirmForNext() {
		String passwd = edit_passwd.getText().toString();// 비밀번호
		String confirmpasswd = edit_confirmpasswd.getText().toString();// 비밀번호확인
		
		if (!(passwd.length() >= 6 && passwd.length() < 12)
				&& passwd.equals(confirmpasswd)) {
			Dialog_BaseDialog dialog = new Dialog_BaseDialog(this,
					"잘못된 비밀번호입니다.\n다시 입력해주세요.");
			dialog.show();
			return false;
		} else if (passwd.length() >= 6 && passwd.length() < 12// 비밀번호 길이 확인
				&& passwd.equals(confirmpasswd)// /비밀번호 일치 및 길이
		) {
			return true;// 모든 항목 일치하면
		} else {
			Dialog_BaseDialog dialog = new Dialog_BaseDialog(this,
					"비밀번호가 다릅니다.\n비밀번호를 확인해주세요.");
			dialog.show();
			return false;
		}
	}
	
	public void sendChangePasswd() {
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
					retofit.requestChangePasswd(app.getId(),edit_passwd.getText().toString(), new Callback<String>() {
						@Override
						public void success(String result, Response response) {
							progressdialog.dismiss();
							if (context != null) {
								if (result.equals("200")) {
									dialog.show();
									SharedPreferences setting = getSharedPreferences("setting",
											MODE_PRIVATE);
									SharedPreferences.Editor editor = setting
											.edit();
									editor.clear();
									editor.putString("u_id", app.getId());
									editor.putString("password", edit_passwd.getText().toString());
									editor.putBoolean("auto_login", true);
									editor.commit();
								} else {
									Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,
											"비밀번호 변경에 실패하였습니다.\n잠시후 다시 시도해주세요.");
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
