package ddoraemi.creategroup.view;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Query;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.creategroup.presenter.CreateGroup_presenter;
import ddoraemi.creategroup.presenter.CreateGroup_presenter_Interface;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.dialog.Dialog_GroupDateSelect;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.LoginActivity;
import ddoraemi.start.R;
import ddoraemi.start.SplashActivity;

public class CreateGroup_View extends Activity implements
		CreateGroup_view_Interface, OnClickListener {
	ProgramData data;
	TextView tv_p_name;
	TextView tv_p_addr;
	TextView tv_selectdate;
	TextView tv_enddate;
	EditText Edit_gname;
	EditText Edit_introduce;
	ImageView plus, minus, disableminus, disableplus;
	TextView count_hopeperson;
	TextView tv_minmax_persons;
	ArrayList<Group> group;
	Group info;
	Button create;
	int minperson;
	int hopeperson;
	int maxperson;
	
	AppController app;

	// /////////////////다이얼로그에서 얻어올 목록
	int g_open_year;
	int g_open_month;
	int g_open_day;
	int g_close_year;
	int g_close_monte;
	int g_close_day;
	int g_start_year;
	int g_start_month;
	int g_start_day;
	int g_start_hour;
	int g_start_minute;
	int g_end_hour;
	int g_end_minute;
	CreateGroup_presenter_Interface presenter;
	
	ProgressDialog progressdialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creategroup);
		init();

	}

	public void init() {
		Intent intent = getIntent();
		app = (AppController) getApplicationContext();
		data = (ProgramData) intent.getSerializableExtra("item");
		group = (ArrayList<Group>) intent.getSerializableExtra("group");
		
		minperson = data.getP_min_persons();
		maxperson = data.getP_max_persons();
		hopeperson = minperson;
		progressdialog = new ProgressDialog(this);
		progressdialog.setMessage(getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		presenter = new CreateGroup_presenter(this);
		
		
		disableminus = (ImageView) findViewById(R.id.activity_creategroup_persons_minusdisable);
		disableplus = (ImageView) findViewById(R.id.activity_creategroup_persons_plusdisable);
		tv_p_name = (TextView) findViewById(R.id.activity_creategroup_Tv_ChallengeName);
		tv_p_addr = (TextView) findViewById(R.id.activity_creategroup_Tv_Address);
		tv_selectdate = (TextView) findViewById(R.id.activity_creategroup_Tv_date_select);
		tv_enddate = (TextView) findViewById(R.id.activity_creategroup_Tv_date_end);
		tv_minmax_persons = (TextView)findViewById(R.id.tv_max_min_persons);
		String max = "";
		if(data.getP_max_persons()==0){
			tv_minmax_persons.setText("최대-제한없음, 최소-"+data.getP_min_persons()+"명");
		}else{
			tv_minmax_persons.setText("최대-"+data.getP_max_persons()+"명, 최소-"+data.getP_min_persons()+"명");
		}
		plus = (ImageView) findViewById(R.id.activity_creategroup_persons_plus);
		minus = (ImageView) findViewById(R.id.activity_creategroup_persons_minus);
		Edit_gname = (EditText) findViewById(R.id.activity_creategroup_Edit_Groupname);
		Edit_introduce = (EditText) findViewById(R.id.activity_creategroup_Edit_introduce);
		
		
		count_hopeperson = (TextView) findViewById(R.id.activity_creategroup_minpersons_creategroup);
		create = (Button) findViewById(R.id.activity_creategroup_Btn_Create);
		tv_p_name.setText(data.getP_name());
		String addr = data.getP_addr_1();
		if (!data.getP_addr_2().equalsIgnoreCase("")) {
			addr += " " + data.getP_addr_2();
			if (!data.getP_addr_3().equalsIgnoreCase("")) {
				addr += " " + data.getP_addr_3();
				if (!data.getP_addr_4().equalsIgnoreCase("")) {
					addr += " " + data.getP_addr_4();
					if (!data.getP_addr_5().equalsIgnoreCase("")) {
						addr += " " + data.getP_addr_5();
						if (!data.getP_addr_6().equalsIgnoreCase("")) {
							addr += " " + data.getP_addr_6();
						}
					}
				}
			}
		}
		count_hopeperson.setText(hopeperson + "명");
		tv_p_addr.setText(addr);
		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
		tv_selectdate.setOnClickListener(this);
		create.setOnClickListener(this);
	}

	@Override
	public void showDatePopUp() {
		// TODO Auto-generated method stub
		Dialog_GroupDateSelect dialog = new Dialog_GroupDateSelect(this, data,
				group);
		dialog.show();
	}
	public void showDialog(){
		progressdialog.show();
	}
	
	public void showFailureDialog(){
		progressdialog.dismiss();

		Dialog_BaseDialog dialog = new Dialog_BaseDialog(
				CreateGroup_View.this,
				getString(R.string.failed_dialog));
		dialog.show();
	}
	
	public void existempty(){
		Dialog_BaseDialog dialog = new Dialog_BaseDialog(this,
				"정보를 모두 입력해야,\n모임 개설이 가능합니다.");
		dialog.show();
	}
	@Override
	public void showOverlapDialog(){
		progressdialog.dismiss();

		Dialog_BaseDialog dialog = new Dialog_BaseDialog(
				CreateGroup_View.this,
				"겹치는 시간에 모임이 개설되어있습니다.\n개설된 모임을 확인해주세요.");
		dialog.show();
	}
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.activity_creategroup_Tv_date_select) {
			presenter.validatecredential("showDatePopUp",null,null,null,-1,-1);
		} else if (v.getId() == R.id.activity_creategroup_persons_plus) {
			presenter.validatecredential("clickPlusBtn",null,null,null,-1,-1);
		} else if (v.getId() == R.id.activity_creategroup_persons_minus) {
			presenter.validatecredential("clickMinusBtn",null,null,null,-1,-1);
		} else if (v.getId() == R.id.btn_left_menu) {
			finish();		
		} else if (v.getId() == R.id.activity_creategroup_Btn_Create) {
			presenter.validatecredential("createGroup",info,Edit_gname,Edit_introduce,data.getP_id(),hopeperson);
		}
	}

	@Override
	public void clickMinusBtn() {
		hopeperson--;
		if (maxperson - 1 == hopeperson) {
			plus.setVisibility(View.VISIBLE);
		}
		if (hopeperson < 1) {
			hopeperson = 1;
		}

		if (minperson != -1 && hopeperson == minperson) {
			minus.setVisibility(View.GONE);

		}
		count_hopeperson.setText(hopeperson + "명");

	}

	@Override
	public void clickPlusBtn() {
		hopeperson++;
		if (minperson + 1 == hopeperson) {
			minus.setVisibility(View.VISIBLE);
		}

		if (maxperson != -1 && hopeperson == maxperson) {
			plus.setVisibility(View.GONE);

		}
		count_hopeperson.setText(hopeperson + "명");

	}

	@Override
	public void setDate(int g_open_year, int g_open_month, int g_open_day,
			int g_close_year, int g_close_monte, int g_close_day,
			int g_start_year, int g_start_month, int g_start_day,
			int g_start_hour, int g_start_minute, int g_end_hour,
			int g_end_minute)// //다이얼로그에서 시간정보 얻어오는 함수
	{
		this.g_open_year = g_open_year;
		this.g_open_month = g_open_month;
		this.g_open_day = g_open_day;
		this.g_close_year = g_close_year;
		this.g_close_monte = g_close_monte;
		this.g_close_day = g_close_day;
		this.g_start_year = g_start_year;
		this.g_start_month = g_start_month;
		this.g_start_day = g_start_day;
		this.g_start_hour = g_start_hour;
		this.g_start_minute = g_start_minute;
		this.g_end_hour = g_end_hour;
		this.g_end_minute = g_end_minute;
	}

	@Override
	public void getDateInfo(Group temp) {
		info = temp;
		tv_selectdate.setText(info.getG_start_year() + "년 "
				+ info.getG_start_month() + "월 " + info.getG_start_day() + "일");
		tv_enddate.setText(info.getG_close_year() + "년 "
				+ info.getG_close_month() + "월 " + info.getG_close_day() + "일");
	}

	@Override
	public void createfinish(ArrayList<Group> group) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra("grouplist", group);
		CreateGroup_View.this.setResult(100, intent);
		finish();
	}
}
