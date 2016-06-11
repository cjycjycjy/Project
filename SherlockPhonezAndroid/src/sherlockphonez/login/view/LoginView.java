package sherlockphonez.login.view;


import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.dialog.Customdialog_Email;
import sherlockphonez.login.controller.LoginController;
import sherlockphonez.login.controller.LoginController_Interface;
import sherlockphonez.myphonecontroll.view.MyPhoneControllView;

import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;


public class LoginView extends Activity implements LoginView_Interface, OnClickListener{
	private EditText et_phoneNum=null;	//���� ��� �ڵ��� ��ȣ �Է� �ؽ�Ʈâ
	private EditText et_passwd=null;	//��й�ȣ �Է� �ؽ�Ʈâ

	private String phoneNum=null;	//����ڰ� �Է��� �ڵ��� ��ȣ
	private String passwd=null;		//����ڰ� �Է��� ��й�ȣ

	private AppController app;

	private Customdialog_Email customDialog;
	private LoginController_Interface controller;
	private ProgressDialog progress;
	Context context;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		context = this;
		customDialog = new Customdialog_Email(this);

		findViewById(R.id.btn_login).setOnClickListener(this);
		findViewById(R.id.btn_cancel).setOnClickListener(this);
		findViewById(R.id.btn_findinfo).setOnClickListener(this);
		
	
		
		customDialog.getCancelBtn().findViewById(R.id.btn_send_cancel).setOnClickListener(this);
		customDialog.getOkBtn().findViewById(R.id.btn_sendpasswd).setOnClickListener(this);

		et_phoneNum=(EditText)findViewById(R.id.et_phoneNum);
		et_passwd=(EditText)findViewById(R.id.et_passwd);

		app = (AppController)getApplicationContext();
		
		progress=new ProgressDialog(this);

		controller=new LoginController(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_login){
			controller.validateCredentials(et_phoneNum.getText().toString(), app.getPhoneNum_not(),
					et_passwd.getText().toString()
					, app.getServerIp(),"login");
		}else if(v.getId()==R.id.btn_cancel){
			finish();
		}else if(v.getId()==R.id.btn_findinfo){
			controller.showDialog_mail();
		}else if(v.getId()==R.id.btn_sendpasswd){			
			controller.sendEmail(customDialog.getPoneNum(),customDialog.getEmail());
		}else if(v.getId()==R.id.btn_send_cancel){
			controller.dismissDialog_mail();
		}
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
	public void setEmptyError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "정보를 모두 입력해 주세요.").show();
	}

	@Override
	public void setNetworkError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "실패하였습니다.").show();
	}

	@Override
	public void setPasswdNotEqualError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "실패하였습니다.").show();
	}

	@Override
	public void setMyPhoneError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "실패하였습니다.").show();
	}

	@Override
	public void setFailedError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "잘못된 정보입니다.").show();
	}


	@Override
	public void navigateToNext(String phoneNum) {
		// TODO Auto-generated method stub
		app.setotherPhone(phoneNum);
		app.setotherphoneLogin(true);
		Intent intent=new Intent(this, MyPhoneControllView.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void setNotFoundUserError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "").show();
	}

	@Override
	public void setSendMailSuccess() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "임시 비밀번호가 발송되었습니다.").show();
	}
	@Override
	public void setWrongError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "잘못된 정보입니다.").show();
		
	}
	@Override
	public void showMailDialog() {
		// TODO Auto-generated method stub
		customDialog.show();
	}

	@Override
	public void hideMailDialog() {
		// TODO Auto-generated method stub
		customDialog.dismiss();
	}

	@Override
	public void navigateToNextChangeInfo(String name, String phoneNum,
			String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Context getViewContext() {
		// TODO Auto-generated method stub
		return context;
	}
}

