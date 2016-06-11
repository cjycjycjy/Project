package sherlockphonez.myphonecontroll.view;


import sherlockphonez.dialog.Customdialog_Base;
import sherlockphonez.dialog.Customdialog_changepasswd;
import sherlockphonez.dialog.Customdialog_logout;
import sherlockphonez.myphonecontroll.controller.MyPhoneControllController;
import sherlockphonez.myphonecontroll.controller.MyPhoneControllController_Interface;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.R;
import com.example.sherlockphonez.ResponseGCM;


public class MyPhoneControllView extends Activity
	implements MyPhoneControllView_Interface , OnClickListener, OnDismissListener{


		private String msg;
		private AppController app;

		private MyPhoneControllController_Interface controller;

		private ProgressDialog progress;
		private ProgressBar progress_state;

		private RelativeLayout rl_state_progress;

		private Button btn_state_error;

		private TextView tv_phoneNum_wireless;
		private TextView tv_network_wireless;
		private TextView tv_battery_wireless;
		private TextView tv_service_wireless;

		private TextView tv_state_phoneNum;
		private TextView tv_state_network;
		private TextView tv_state_battery;
		private TextView tv_state_service;
		private IntentFilter intentFilter;
		private CountDownTimer cd_timer;
		
		private Customdialog_logout cusDia_logout;
		private Customdialog_changepasswd cusDialog;

		long mLastClickTime=0;


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_myphonecontroll);

			app = (AppController)getApplicationContext();
			tv_phoneNum_wireless = (TextView)findViewById(R.id.tv_phoneNum_wireless_);

			tv_network_wireless = (TextView)findViewById(R.id.tv_network_wireless_);
			tv_battery_wireless = (TextView)findViewById(R.id.tv_battery_wireless_);
			tv_service_wireless = (TextView)findViewById(R.id.tv_service_wireless_);

			tv_state_service = (TextView)findViewById(R.id.tv_service_wireless);
			tv_state_battery = (TextView)findViewById(R.id.tv_battery_wireless);
			tv_state_phoneNum = (TextView)findViewById(R.id.tv_phoneNum_wireless);
			tv_state_network = (TextView)findViewById(R.id.tv_network_wireless);

			findViewById(R.id.btn_location).setOnClickListener(this);
			findViewById(R.id.btn_camera).setOnClickListener(this);
			findViewById(R.id.btn_lock).setOnClickListener(this);
			findViewById(R.id.btn_alarm).setOnClickListener(this);
			
			// btn_live -> ���� ���� ON    ���߿� �̸��ٲ�
			findViewById(R.id.btn_live).setOnClickListener(this);
			findViewById(R.id.btn_live).setVisibility(View.GONE);
			findViewById(R.id.btn_alarmOff).setOnClickListener(this);
			findViewById(R.id.btn_logout).setOnClickListener(this);
			findViewById(R.id.btn_cancel_wireless).setOnClickListener(this);

			btn_state_error = (Button)findViewById(R.id.btn_state_error);
			btn_state_error.setOnClickListener(this);

			intentFilter= new  IntentFilter();   
			intentFilter.addAction("state");
		
			
			cusDialog = new Customdialog_changepasswd(MyPhoneControllView.this);
			cusDialog.getOkBtn().setOnClickListener(this);
			cusDialog.getCancelBtn().setOnClickListener(this);
			cusDialog.setOnDismissListener(this);
			cusDia_logout = new Customdialog_logout(MyPhoneControllView.this);
			cusDia_logout.getOkBtn().setOnClickListener(this);
			cusDia_logout.getCancelBtn().setOnClickListener(this);
			progress=new ProgressDialog(this);
			progress_state=(ProgressBar)findViewById(R.id.pg_otherphone_state);

			rl_state_progress=(RelativeLayout)findViewById(R.id.rl_otherphone_state_progress);
			registerReceiver(stateBroadcasteReceiver ,intentFilter);
			controller = new MyPhoneControllController(this);

			cd_timer = new CountDownTimer(15000,1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
					if(tv_network_wireless.getText().toString().equalsIgnoreCase("")==false){
						cancel();
					}
				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					if(tv_network_wireless.getText().toString().equalsIgnoreCase("")){
						hideProgressState();
						tv_state_battery.setVisibility(View.GONE);
						tv_state_network.setVisibility(View.GONE);
						tv_state_phoneNum.setVisibility(View.GONE);
						tv_state_service.setVisibility(View.GONE);
						btn_state_error.setVisibility(View.VISIBLE);
					}
				}
			};

		}

		public boolean isNumber(String s){
			try{
				Double.parseDouble(s);
				return true;
			}catch(NumberFormatException e){
				return false;
			}
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
				return;
			}
			mLastClickTime = SystemClock.elapsedRealtime();

			switch(v.getId()){	
			case R.id.btn_cancel_wireless:
				finish();
				break;
			case R.id.btn_setpasswd:
				msg=cusDialog.getPasswd();
				
				if(!isNumber(msg) || msg.length()<4)
					break;
				else{
					controller.validateCredentials(app.getOtherPhoneNum(),msg);
					break;
				}
			case R.id.btn_setpasswd_cancel:
				msg="cancellock";
				controller.validateCredentials(app.getOtherPhoneNum(),msg);
				break;
		
			case R.id.btn_alarm:
				msg="alarmOn";
				controller.validateCredentials(app.getOtherPhoneNum(),msg);
				onResume();
				break;
			case R.id.btn_alarmOff:
				msg="alarmOff";
				controller.validateCredentials(app.getOtherPhoneNum(),msg);
				onResume();
				break;
			
			case R.id.btn_camera:
				controller.startCamera();
				break;
			case R.id.btn_lock:
				msg="Lock";
				controller.validateCredentials(app.getOtherPhoneNum(),msg);
				break;				
			case R.id.btn_location:
				controller.startLocation();
				break;
//			case R.id.btn_live:
//				
			case R.id.btn_logout:
				controller.showDialog_logout();
				break;
			case R.id.btn_out:
				controller.onLogout();
				break;
			case R.id.btn_out_cancel:
				controller.dismissDialog_logout();
				break;
			case R.id.btn_state_error:
				onResume();
				break;
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
		public void showProgressState() {
			// TODO Auto-generated method stub
			rl_state_progress.setVisibility(View.VISIBLE);

		}

		@Override
		public void hideProgressState() {
			// TODO Auto-generated method stub
			//rl_state.setBackgroundResource(R.style.AppBaseTheme);
			rl_state_progress.setVisibility(View.GONE);
		}


		@Override
		public void setNetworkError() {
			// TODO Auto-generated method stub
			new Customdialog_Base(this, "서버와의 통신에 실패하였습니다.").show();
		}

		@Override
		public void showDialogMyphoneLock() {
			// TODO Auto-generated method stub
			cusDialog.show();
		}

		@Override
		public void hideDialogMyphoneLock() {
			// TODO Auto-generated method stub
			cusDialog.dismiss();
			cusDialog.setPasswd();
		}

		@Override
		public void setMyphonePasswd() {
			// TODO Auto-generated method stub
			cusDialog.getPasswd();
		}

		@Override
		public void navigateToLocation() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(this, LocationView.class);
			startActivity(intent);
		}

		@Override
		public void navigateToCamera() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(this, PhotoView.class);
			startActivity(intent);
		}

		@Override
		public void navigateToLive() {
			// TODO Auto-generated method stub

		}

		@Override
		public void setLogout() {
			// TODO Auto-generated method stub
			app.setotherphoneLogin(false);
			app.setotherPhone("");
			//app.setotherphoneLogin(false);
			finish();
		}

		@Override
		public void showDialogLogout() {
			// TODO Auto-generated method stub
			cusDia_logout.show();
		}

		@Override
		public void hideDialogLogout() {
			// TODO Auto-generated method stub
			cusDia_logout.dismiss();
		}

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			btn_state_error.setVisibility(View.GONE);
			tv_state_battery.setVisibility(View.VISIBLE);
			tv_state_network.setVisibility(View.VISIBLE);
			tv_state_phoneNum.setVisibility(View.VISIBLE);
			tv_state_service.setVisibility(View.VISIBLE);
			showProgressState();
			ResponseGCM stateInfo = new ResponseGCM(app,app.getOtherPhoneNum(),"state/"+app.getPhoneNum_not());
			stateInfo.sendStateMessage();
			tv_network_wireless.setText("");
			tv_phoneNum_wireless.setText("");

			tv_network_wireless.setText("");
			tv_battery_wireless.setText("");
			tv_service_wireless.setText("");
			cd_timer.start();

		}

		BroadcastReceiver stateBroadcasteReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String value = intent.getStringExtra("value");
				String[] result = value.split("/");

				if(result[1].equalsIgnoreCase("t")){
					tv_service_wireless.setText("ON");
				}else{
					tv_service_wireless.setText("OFF");
				}
				tv_battery_wireless.setText(result[2]+"%");
				tv_network_wireless.setText("On");
				tv_phoneNum_wireless.setText(app.getOtherPhoneNum());
				hideProgressState();
			}
		};
		


		protected void onDestroy() {
			super.onDestroy();
			unregisterReceiver(stateBroadcasteReceiver);

		}

		@Override
		public void onDismiss(DialogInterface dialog) {
			// TODO Auto-generated method stub
			onResume();
		};
}
