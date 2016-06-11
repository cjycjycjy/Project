package ddoraemi.applyadmin.view;

import ddoraemi.appcontroller.AppController;
import ddoraemi.applyadmin.presenter.Apply_Admin_Presenter;
import ddoraemi.applyadmin.presenter.Apply_Admin_Presenter_Interface;
import ddoraemi.dialog.Dialog_Admin_SelectProgramName;
import ddoraemi.dialog.Dialog_Admin_Selectlocation;
import ddoraemi.dialog.Dialog_ApplyAdmin;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.start.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Apply_Admin_View extends Activity implements
Apply_Admin_View_Interface,OnClickListener {
	AppController app;
	ImageView btn_apply,btn_back;
	Dialog_ApplyAdmin dialog;
	Dialog_BaseDialog dialog2;
	
	Apply_Admin_Presenter_Interface presenter;
	EditText a_name, a_phonenum, a_p_phonenum, a_email,a_programinfo;
	String selectedLocation;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applyadmin);
		app = (AppController) getApplicationContext();
		init();
	}
	public void init()
	{
		presenter = new Apply_Admin_Presenter(this);
		btn_back = (ImageView)findViewById(R.id.activity_applyadmin_cancel);
		btn_back.setOnClickListener(this);
		btn_apply = (ImageView)findViewById(R.id.iv_sendrequest);
		btn_apply.setOnClickListener(this);
		selectedLocation = "empty";
		dialog = new Dialog_ApplyAdmin(this, "계정인증 신청이 전송되었습니다.\n조금만 기다려주세요!");	
		dialog2 = new Dialog_BaseDialog(this, "정보를 모두 입력 하셔야 합니다.");
		dialog.getBtnOk().setOnClickListener(this);
		a_name = (EditText)findViewById(R.id.et_admin_name);
		a_phonenum = (EditText)findViewById(R.id.et_admin_phonenum);
		a_p_phonenum = (EditText)findViewById(R.id.et_program_phonenum);
		a_email = (EditText)findViewById(R.id.et_admin_email);
		a_programinfo = (EditText)findViewById(R.id.et_admin_programinfo);
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.iv_sendrequest){
			presenter.validatecredentail("sendrequest",app.getUser().getU_id() ,a_name.getText().toString(), a_phonenum.getText().toString(),
					a_p_phonenum.getText().toString(), a_email.getText().toString(),a_programinfo.getText().toString());
		}else if(v.getId() == R.id.dialog_basedialog_btnok){
			presenter.validatecredentail("dismissdialog",null, null, null, null,null, null);
		}
		if(v.getId()==R.id.activity_applyadmin_cancel){
			finish();
		}
		
	}
	
	@Override
	public void showApplyDialog() {
		// TODO Auto-generated method stub
		dialog.show();
		app.getUser().setPermission(1);
	}
	@Override
	public void dismissDialog() {
		// TODO Auto-generated method stub
		dialog.dismiss();
		setResult(1000);
		finish();
	}
	public void showExistEmpty(){
		dialog2.show();
	}
}