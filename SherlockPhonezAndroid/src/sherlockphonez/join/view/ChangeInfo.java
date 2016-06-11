package sherlockphonez.join.view;




import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.join.controller.JoinController;

import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.R;
import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ChangeInfo extends Activity implements JoinView_Interface,OnClickListener, OnFocusChangeListener, OnTouchListener
,OnDismissListener{
	private AppController app;

	private ProgressDialog progress;
	
	private Button btn_ok;
	private Button btn_cancel;

	private EditText et_passwd_join;
	private EditText et_passwdcheck;
	private EditText et_email_join;
	private EditText et_name_join;

	private TextView tv_phoneNum_join;
	private TextView warning_passwd;
	private TextView warning_mail;
	private TextView warning_empty;

	private RelativeLayout layout;

	private JoinController controller;

	private long mLastClickTime = 0;

	private boolean mailError=false;
	private boolean passwdError=false;
	private boolean emptyError=true;
	public Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.change_info);
		context = this;
		Intent intent = getIntent();
		
		btn_ok=(Button)findViewById(R.id.btn_ok_changeinfo);
		btn_cancel=(Button)findViewById(R.id.btn_cancel_changeinfo);
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);

		app = (AppController)getApplicationContext();

		et_passwd_join = (EditText)findViewById(R.id.et_passwd_join_changeinfo);
		et_passwdcheck = (EditText)findViewById(R.id.et_passwdcheck_changeinfo);
		et_email_join = (EditText)findViewById(R.id.et_email_join_changeinfo);
		et_name_join = (EditText)findViewById(R.id.et_name_join_changeinfo);
		
		et_name_join.setText(intent.getStringExtra("name"));
		et_email_join.setText(intent.getStringExtra("email"));
		

		et_passwdcheck.setOnFocusChangeListener(this);
		et_passwd_join.setOnFocusChangeListener(this);
		et_email_join.setOnFocusChangeListener(this);

		tv_phoneNum_join = (TextView)findViewById(R.id.tv_phonenum_join_auto_changeinfo);
		tv_phoneNum_join.setText(app.getPhoneNum());

		warning_passwd=(TextView)findViewById(R.id.tv_warning_passwd_changeinfo);
		warning_mail=(TextView)findViewById(R.id.tv_warning_mail_changeinfo);
		warning_empty=(TextView)findViewById(R.id.tv_warning_empty_changeinfo);

		layout=(RelativeLayout)findViewById(R.id.rl_join_changeinfo);
		layout.setOnTouchListener(this);

		progress=new ProgressDialog(this);

		controller=new JoinController(this);
	}

	@Override
	public void showProgress() {
		// TODO Auto-generated method stub
		progress.show();
		progress.setCancelable(false);
		progress.setContentView(R.layout.progress);

	}

	@Override
	public void hideProgress() {
		// TODO Auto-generated method stub
		progress.dismiss();
	}

	@Override
	public void setExistError() {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void setNetworkError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "서버와의 통신에 실패했습니다.").show();
	}

	@Override
	public void showPasswdNotEqualError() {
		// TODO Auto-generated method stub
		passwdError=true;
		warning_mail.setVisibility(View.GONE);
		warning_passwd.setVisibility(View.VISIBLE);
		btn_ok.setEnabled(false);
	}

	@Override
	public void hidePasswdNotEqualError() {
		// TODO Auto-generated method stub
		passwdError=false;
		if(mailError==true)
			warning_mail.setVisibility(View.VISIBLE);
		else
			btn_ok.setEnabled(true);
		warning_passwd.setVisibility(View.GONE);			
	}

	@Override
	public void showMailError() {
		// TODO Auto-generated method stub
		mailError=true;
		warning_passwd.setVisibility(View.GONE);
		warning_mail.setVisibility(View.VISIBLE);
		btn_ok.setEnabled(false);
	}

	@Override
	public void hideMailError() {
		// TODO Auto-generated method stub
		mailError=false;
		if(passwdError==true)
			warning_passwd.setVisibility(View.VISIBLE);
		else
			btn_ok.setEnabled(true);
		warning_mail.setVisibility(View.GONE);
	}
	

	@Override
	public void showEmptyError() {
		// TODO Auto-generated method stub
		warning_empty.setVisibility(View.VISIBLE);
		btn_ok.setEnabled(false);
	}

	@Override
	public void hideEmptyError() {
		// TODO Auto-generated method stub
		warning_empty.setVisibility(View.GONE);
		if(mailError==false && passwdError==false)
			btn_ok.setEnabled(true);
		
	}

	@Override
	public void setFailedError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "실패 하였습니다. 이미 등록된 메일 주소 입니다.").show();
	}

	@Override
	public void navigateToNext() {
		// TODO Auto-generated method stub
		Customdialog_Base change_success = new Customdialog_Base(this, "변경되었습니다.");
		change_success.setOnDismissListener(this);
		change_success.show();
	}
	
	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		finish();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
			return;
		}
		mLastClickTime = SystemClock.elapsedRealtime();

		if(v.getId()==R.id.btn_ok_changeinfo){
			controller.validateCredentials(app.getPhoneNum_not(), app.getDeviceId()
					, et_passwd_join.getText().toString(), et_passwdcheck.getText().toString(),
					et_name_join.getText().toString()
					, et_email_join.getText().toString(), "changeinfo");
		}else{
			finish();
		}

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if(v==et_passwdcheck || v==et_passwd_join){
			if(hasFocus==false){
				if(et_passwd_join.getText().toString().length()>5 && et_passwd_join.getText().toString().length() < 13 &&
						et_passwd_join.getText().toString().equalsIgnoreCase(et_passwdcheck.getText().toString())){
					controller.unsetPasswdWarning();
				}else if(!et_passwdcheck.getText().toString().equalsIgnoreCase("")){
					controller.setPasswdWarning();

				}
			}
		}else if(v==et_email_join){
			if(hasFocus==false){
				if((!et_email_join.getText().toString().contains(".")
						|| !et_email_join.getText().toString().contains("@"))
						&& !et_email_join.getText().toString().equalsIgnoreCase("")){
					controller.setMailWarning();
				}else{
					controller.unsetMailWarning();
				}
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(!et_email_join.getText().toString().equalsIgnoreCase("") &&
				!et_name_join.getText().toString().equalsIgnoreCase("") &&
				!et_passwd_join.getText().toString().equalsIgnoreCase("") &&
				!et_passwdcheck.getText().toString().equalsIgnoreCase("")){
			controller.unsetEmptyWarning();
		}
		layout.requestFocus();	
		return false;
	}

	@Override
	public Context getViewContext(){
		return context;
	}




}
