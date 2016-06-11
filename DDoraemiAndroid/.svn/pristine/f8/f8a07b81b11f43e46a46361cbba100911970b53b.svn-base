package ddoraemi.detailedgroupinfo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.presenter.JoinGroup_presenter;
import ddoraemi.detailedgroupinfo.presenter.JoinGroup_presenter_Interface;
import ddoraemi.dialog.Dialog_confirm_JoinGroup;
import ddoraemi.home.model.Group;
import ddoraemi.start.R;

public class JoinGroupView extends Activity implements JoinGroupView_Interface,OnClickListener{
	
	Button btn_joingroup;
	JoinGroup_presenter_Interface presenter;
	Group group;
	AppController app;
	TextView tv_join_p_name;
	TextView tv_join_addr;
	TextView tv_join_date;
	TextView tv_join_time;
	Dialog_confirm_JoinGroup dialog;
	
	TextView count_hopeperson;
	int minperson;
	int hopeperson;
	int maxperson;
	ImageView plus, minus, disableminus, disableplus, btn_back;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupjoin);
		init();
	}
	public void init(){
		app = (AppController)getApplicationContext();
		btn_joingroup =(Button)findViewById(R.id.btn_joingroupbutton);
		btn_joingroup.setOnClickListener(this);
		presenter = new JoinGroup_presenter(this);
		tv_join_p_name = (TextView)findViewById(R.id.tv_join_programname);
		tv_join_addr = (TextView)findViewById(R.id.tv_join_programaddress);
		tv_join_date = (TextView)findViewById(R.id.tv_join_date_inbox);
		tv_join_time = (TextView)findViewById(R.id.tv_join_time_inbox);
		btn_back = (ImageView)findViewById(R.id.btn_left_menu);
		btn_back.setOnClickListener(this);
		Intent intent = getIntent();
		this.group = (Group)intent.getSerializableExtra("group");
		minperson=	1;
		maxperson = group.getG_want_persons()-group.getG_persons();
		hopeperson = minperson;
		disableminus = (ImageView) findViewById(R.id.activity_joingroup_persons_minusdisable);
		disableplus = (ImageView) findViewById(R.id.activity_joingroup_persons_plusdisable);
		plus = (ImageView) findViewById(R.id.activity_joingroup_persons_plus);
		minus = (ImageView) findViewById(R.id.activity_joingroup_persons_minus);
		count_hopeperson = (TextView) findViewById(R.id.activity_joingroup_minpersons_creategroup);

		tv_join_p_name.setText(group.getP_name().toString());
		tv_join_addr.setText(group.getP_addr());
		tv_join_date.setText(group.getG_start_year()+"."+group.getG_start_month()+"."+group.getG_start_day());
		tv_join_time.setText(group.getG_start_hour()+"시 "+group.getG_start_minute()+"분 - "+group.getG_end_hour()+"시 "+
		group.getG_end_minute()+"분");
		String date = group.getG_start_year()+"."+group.getG_start_month()+"."+group.getG_start_day()+"  "+
				group.getG_start_hour()+"시 "+group.getG_start_minute()+"분 - "+group.getG_end_hour()+"시 "+
				group.getG_end_minute()+"분";
		dialog = new Dialog_confirm_JoinGroup(this, group.getP_name(),date);
		dialog.getOkBtn().setOnClickListener(this);
		dialog.getCancelBtn().setOnClickListener(this);
		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
	}
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
	public void showJoinDialog(Group group) {
		// TODO Auto-generated method stub
		dialog.show();
	}
	@Override
	public void dismissdialog() {
		// TODO Auto-generated method stub
		dialog.dismiss();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			back(1);
			return true;
		}
		return false;
	}
	
	public void back(int event) {
		Intent intent = new Intent();
		intent.putExtra("persons", hopeperson);
		intent.putExtra("event", event);
		this.setResult(0, intent);
		finish();
		
	}
	
	@Override
	public void joinsuccess_GoToDetailedGroupInfo() {
		// TODO Auto-generated method stub
		back(2);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_joingroupbutton){
			presenter.validatecredential("showdialog", group, app.getUser(),-1);
		}else if(v.getId() == R.id.btn_dialog_confirmjoin_ok){
			presenter.validatecredential("join", group, app.getUser(),hopeperson);
		}else if(v.getId()== R.id.btn_dialog_confirmjoin_cancel){
			presenter.validatecredential("dismiss_dialog", group, app.getUser(),-1);
		}else if(v.getId()==R.id.activity_joingroup_persons_minus)
		{
			presenter.validatecredential("down");
		}else if(v.getId()==R.id.activity_joingroup_persons_plus)
		{
			presenter.validatecredential("up");
			
		}else if(v.getId() == R.id.btn_left_menu){
			back(1);
		}
	}


}
