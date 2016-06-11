package sherlockphonez.login.view;




import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.R;

import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.join.view.ChangeInfo;
import sherlockphonez.login.controller.LoginController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;


public class LoginChangeInfo extends Activity implements LoginView_Interface, OnClickListener{
	private EditText et_phoneNum=null;	//���� ��� �ڵ��� ��ȣ �Է� �ؽ�Ʈâ
	private EditText et_passwd=null;	//��й�ȣ �Է� �ؽ�Ʈâ

	private String phoneNum=null;	//����ڰ� �Է��� �ڵ��� ��ȣ
	private String passwd=null;		//����ڰ� �Է��� ��й�ȣ

	private AppController app;

	private LoginController controller;
	private ProgressDialog progress;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_change_info);
		context = this;
		findViewById(R.id.btn_cancel_login_changeinfo).setOnClickListener(this);
		findViewById(R.id.btn_login_changeinfo).setOnClickListener(this);

		et_phoneNum=(EditText)findViewById(R.id.et_phoneNum_login_changeinfo);
		et_passwd=(EditText)findViewById(R.id.et_passwd_login_changeinfo);

		app = (AppController)getApplicationContext();

		progress=new ProgressDialog(this);

		controller=new LoginController(this); 
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
	public void setNotFoundUserError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "존재하지 않는 정보입니다.").show();
	}

	@Override
	public void setEmptyError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "정보를 모두 입력해 주세요.").show();
	}

	@Override
	public void setNetworkError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "네트워크 불안정").show();
	}

	@Override
	public void setPasswdNotEqualError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "일치 하지않는 정보입니다.").show();
	}

	@Override
	public void setWrongError() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setFailedError() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "정보가 올바르지 않습니다.").show();
	}

	@Override
	public void setMyPhoneError() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showMailDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideMailDialog() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSendMailSuccess() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void navigateToNext(String phoneNum) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_login_changeinfo){
			controller.validateCredentials(et_phoneNum.getText().toString(),null,et_passwd.getText().toString(),
					app.getServerIp(),"changeinfologin");
		}else{
			finish();
		}
	}
	
	@Override
	public void navigateToNextChangeInfo(String name, String phoneNum,
			String email) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ChangeInfo.class);
		intent.putExtra("name", name);
		intent.putExtra("phoneNum", phoneNum);
		intent.putExtra("email", email);
		startActivity(intent);
		finish();
	}

	@Override
	public Context getViewContext(){
		return context;
	}
}
