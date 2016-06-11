package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_Picture_presenter;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_Picture_presenter_Interface;
import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_PictureListAdapter;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_PopupImage_View;
import ddoraemi.start.R;

public class DetailedGroupInfo_PictureView extends Activity implements DetailedGroupInfo_PictureView_Interface
,OnItemClickListener,OnClickListener{

	DetailedGroupInfo_Picture_presenter_Interface presenter;
	GridView gv_picture;
	ArrayList<ChallengePicture> picturelist;
	DetailedItemInfo_PictureListAdapter adapter;
	ImageView btn_back;
	int p_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_challenge_picture);
		init();
	}

	public void init() {
		presenter = new DetailedGroupInfo_Picture_presenter(this);
		gv_picture = (GridView) findViewById(R.id.gv_photo);
		Intent intent = getIntent();
		p_id = intent.getIntExtra("p_id", -1);
		presenter.validatecredential("getpicture", p_id, null);
		gv_picture.setOnItemClickListener(this);

		btn_back = (ImageView)findViewById(R.id.btn_left_menu_challenge_picture);
		btn_back.setOnClickListener(this);
	}

	public void setadapter(ArrayList<ChallengePicture> picture) {
		this.picturelist = picture;
		adapter = new DetailedItemInfo_PictureListAdapter(this,
				R.layout.item_challengepicturelist, picturelist);
		gv_picture.smoothScrollBy(1, 1000);
		gv_picture.setAdapter(adapter);

	}

	@Override
	public void widening(ChallengePicture picture) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,DetailedItemInfo_PopupImage_View.class);
		intent.putExtra("picture", picture);
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		presenter.validatecredential("widening", -1,
				(ChallengePicture) parent.getItemAtPosition(position));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_left_menu_challenge_picture){
			finish();
		}
	}
}
