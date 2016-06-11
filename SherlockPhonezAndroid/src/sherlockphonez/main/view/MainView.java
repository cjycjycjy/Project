package sherlockphonez.main.view;

import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.join.view.JoinView;
import sherlockphonez.login.view.LoginChangeInfo;
import sherlockphonez.login.view.LoginView;
import sherlockphonez.main.controller.MainViewController;
import sherlockphonez.main.controller.MainViewControllerInterface;
import sherlockphonez.myphonecontroll.view.MyPhoneControllView;

import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.DPM.DpmClass;
import com.example.sherlockphonez.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainView  extends Activity implements MainView_Interface, OnClickListener {
	private DevicePolicyManager devicePolicyManager;
	private ComponentName adminComponent;
	private ProgressDialog progress;
	private AppController app;

	private Button btn_service_on;
	private Button btn_service_off;

	private boolean isBackground=false;

	private MainViewControllerInterface controller;

	//private IntentFilter intentFilter;

	//	BroadcastReceiver serviceBroadcasteReceiver = new BroadcastReceiver() {
	//		@Override
	//		public void onReceive(Context context, Intent intent) {
	//			// TODO Auto-generated method stub
	//			String value = intent.getStringExtra("value");
	//			String[] result = value.split("/");
	//			isBackground=true;
	//			if(result[1].equalsIgnoreCase("On")){
	//				Intent i = new Intent("com.example.sherlockphonez");
	//				i.putExtra("phoneNum_not", app.getPhoneNum_not());
	//				
	//				startService(i);
	//			}else{
	//				controller.validateCredentials(app.getPhoneNum_not(), app.getDeviceId(),"start", app.getServerIp());
	//			}
	//
	//
	//		}
	//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mainview);
		adminComponent = new ComponentName(this, DpmClass.class);
		devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		app = (AppController)getApplicationContext();
		//intentFilter = new IntentFilter();
		//intentFilter.addAction("service");
		//registerReceiver(serviceBroadcasteReceiver, intentFilter);

		if(!devicePolicyManager.isAdminActive(adminComponent)){
			Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					"Additional text explaining why this needs to be added.");
			startActivityForResult(intent, 1);
		}


		findViewById(R.id.btn_join).setOnClickListener(this);
		findViewById(R.id.btn_login).setOnClickListener(this);
		btn_service_on=(Button)findViewById(R.id.btn_serviceon);
		btn_service_on.setOnClickListener(this);
		btn_service_off=(Button)findViewById(R.id.btn_serviceoff);
		btn_service_off.setOnClickListener(this);
		findViewById(R.id.btn_changeinfo).setOnClickListener(this);
		findViewById(R.id.btn_help).setOnClickListener(this);

		progress=new ProgressDialog(this);
		controller = new MainViewController(this);

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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setServiceButton();
	}




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_join){
			controller.join();
		}else if(v.getId()==R.id.btn_login){
			//			if(app.getotherphoneLogin()){
			//				controller.autoLogin();
			//			}
			//			else
			controller.otherPhoneLogin();
		}else if(v.getId()==R.id.btn_changeinfo){
			controller.changeInfo();
		}

		else if(v.getId()==R.id.btn_serviceon){
			showProgress();
			controller.validateCredentials(app.getPhoneNum_not(), app.getDeviceId(),"start");
		}else if(v.getId()==R.id.btn_changeinfo){

		}else if(v.getId()==R.id.btn_help){
			new Customdialog_Base(this, "cjy12120@naver.com 으로 문의 부탁드립니다.").show();

		}else{
			controller.validateCredentials(app.getPhoneNum_not(), app.getDeviceId(),"stop");
		}

	}


	@Override
	public void setStartFailedError() {
		// TODO Auto-generated method stub

		new Customdialog_Base(this, "서버와의 통신에 실패하였습니다.").show();

	}

	@Override
	public void setStopFailedError() {
		// TODO Auto-generated method stub

		new Customdialog_Base(this, "서버와의 통신에 실패하였습니다.").show();

	}

	@Override
	public void setServiceStart() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "서비스 ON").show();
		Intent intent = new Intent("com.example.sherlockphonez");
		intent.putExtra("phoneNum_not", app.getPhoneNum_not());
		//���� Ŭ����
		startService(intent);
		SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("service", true);
		editor.commit();
		hideProgress();
		showServiceOnLogo();
		hideServiceOnButton();
		showServiceOffButton();
	}

	@Override
	public void setServiceStop() {
		// TODO Auto-generated method stub
		new Customdialog_Base(this, "서비스 OFF").show();
		stopService(new Intent("com.example.sherlockphonez"));	
		SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("service", false);
		editor.commit();
		hideProgress();
		showServiceOffLogo();
		hideServiceOffButton();
		showServiceOnButton();

	}

	@Override
	public void nevigateToJoin() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this, JoinView.class);
		startActivity(intent);
	}

	@Override
	public void nevigateToOtherPhoneLogin() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this, LoginView.class);
		startActivity(intent);
	}

	@Override
	public void nevigateToChangeInfo() {
		Intent intent = new Intent(this,LoginChangeInfo.class);
		startActivity(intent);
	}

	@Override
	public void setNetworkError() {
		// TODO Auto-generated method stub

		new Customdialog_Base(this, "실패하였습니다.").show();

	}

	@Override
	public void showServiceOnLogo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showServiceOffLogo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showServiceOnButton() {
		// TODO Auto-generated method stub
		btn_service_on.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideServiceOnButton() {
		// TODO Auto-generated method stub
		btn_service_on.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showServiceOffButton() {
		// TODO Auto-generated method stub
		btn_service_off.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideServiceOffButton() {
		// TODO Auto-generated method stub
		btn_service_off.setVisibility(View.INVISIBLE);
	}

	public void setServiceButton(){
		SharedPreferences prefs =getSharedPreferences("PrefName",MODE_PRIVATE);
		boolean service = prefs.getBoolean("service", false);
		if(service==true){
			showServiceOffLogo();
			hideServiceOnButton();
			showServiceOffButton();
		}else{
			showServiceOnLogo();
			hideServiceOffButton();
			showServiceOnButton();
		}
	}

	@Override
	public void nevagateToWirelessControl() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this, MyPhoneControllView.class);
		startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//unregisterReceiver(serviceBroadcasteReceiver);
	}

}
