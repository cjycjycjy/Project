package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.presenter.JoinPersonInfo_presenter;
import ddoraemi.detailedgroupinfo.presenter.JoinPersoninfo_presenter_interface;
import ddoraemi.home.model.Group;
import ddoraemi.start.R;
import ddoraemi.start.User;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class JoinPersonListView extends Activity implements JoinPersonListView_Interface,
OnClickListener{

	Group group;
	ListView list;
	ArrayList<User> user;
	JoinPersoninfo_presenter_interface presenter;
	JoinPersonList_adapter adapter;
	ImageView btn_muliplesms;
	ImageView btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_personsinfo);
		user = new ArrayList<>();
		init();
		
		
	}
	public void init(){
		presenter = new JoinPersonInfo_presenter(this);
		btn_muliplesms = (ImageView)findViewById(R.id.iv_sendmuliplesms);
		btn_muliplesms.setOnClickListener(this);
		Intent intent = getIntent();
		user = (ArrayList<User>)intent.getSerializableExtra("user");
		list = (ListView)findViewById(R.id.lv_groupperson );	
		setadapter();
		btn_back = (ImageView)findViewById(R.id.activity_groupperson_cancel);
		btn_back.setOnClickListener(this);
			
	}
	
	@Override
	public void sendtextmessage() {
		// TODO Auto-generated method stub
		
	}
	
	public void setadapter(){
		adapter=new JoinPersonList_adapter(this, R.layout.item_joinpersonslist, user);
		list.setAdapter(adapter);	
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.iv_sendmuliplesms){
			presenter.validatecredential("", adapter.getcheckeduser());
		}else if(v.getId() == R.id.activity_groupperson_cancel){
			finish();
		}
	}
	
	public void sendsms(ArrayList<User> user){
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		String smsto="";
		
		for(int i=0; i<user.size(); i++){
			if(i!=0)
				smsto +=","+user.get(i).getU_phone().toString();
			else
				smsto +=user.get(i).getU_phone().toString();
		}
		Uri sms= Uri.parse("sms:"+smsto);
		
		
		intent.setData(sms);
		startActivity(intent);
	}


}
