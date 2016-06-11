package ddoraemi.detailediteminfo.view;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.start.R;

public class DetailedItemInfo_PopupImage_View extends Activity implements OnClickListener{

	AppController app;
	ViewPager pager;
	Adapter_PopupImager_Pager adapter;
	ArrayList<ChallengePicture> url;
	ImageView btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_popup);
		app = (AppController)getApplicationContext();
		init();

	}
	public void init()
	{
		pager=(ViewPager)findViewById(R.id.activity_ImagePopup_ViewPager);
		Intent intent = getIntent();
		int position=intent.getIntExtra("position", 0);
		url = (ArrayList<ChallengePicture>)intent.getSerializableExtra("url");
		adapter=new Adapter_PopupImager_Pager(this, url);
		pager.setAdapter(adapter);
		pager.setCurrentItem(position);
		
		btn_back=(ImageView)findViewById(R.id.btn_left_menu_challenge_picture_popup);
		btn_back.setOnClickListener(this);
		

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_left_menu_challenge_picture_popup){
			finish();
		}
	}
}
