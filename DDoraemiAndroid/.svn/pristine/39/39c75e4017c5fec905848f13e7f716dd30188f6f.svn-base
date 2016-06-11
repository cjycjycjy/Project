package ddoraemi.detailediteminfo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.start.R;

public class Activity_BigTmap extends Activity implements OnClickListener,Activity_BigTmapInterface{
	RelativeLayout bigtmap;
	double lng,lat;
	String name;
	String address;
	TMapView t_MapView;
	TMapData tmapdata;
	TextView tv_Address;
	ImageView Mylocation,back;
	LinearLayout menu;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_bigtmap);
		init();
	}
	public void init()
	{
		Intent intent=getIntent();
		lng=intent.getExtras().getDouble("lng");
		lat=intent.getExtras().getDouble("lat");
		name=intent.getStringExtra("name");
		address=intent.getStringExtra("address");
		back=(ImageView)findViewById(R.id.activity_bigtmap_btn_back);
		bigtmap=(RelativeLayout)findViewById(R.id.activity_bigtmap);
		tv_Address=(TextView)findViewById(R.id.activity_bigtmap_TvAddressOntheMAP);
		Mylocation=(ImageView)findViewById(R.id.activity_bigtmap_mylocation);
		Mylocation.setVisibility(View.GONE);
		menu=(LinearLayout)findViewById(R.id.activity_bigtmap_menu);
		back.setOnClickListener(this);
		Mylocation.setOnClickListener(this);
		tv_Address.setText(address);
		t_MapView = new TMapView(this); // TmapView생성
		bigtmap.addView(t_MapView);
		t_MapView.setSKPMapApiKey("829de05c-a841-3a5f-98ef-d575aa7ca4e8");
		t_MapView.setZoomLevel(18);
		TMapPoint point = new TMapPoint(lat, lng);
		TMapMarkerItem marker = new TMapMarkerItem();
		marker.setTMapPoint(point);
		marker.setName(name);
		marker.setVisible(TMapMarkerItem.VISIBLE);
		t_MapView.addMarkerItem(name, marker);
		t_MapView.setCenterPoint(lng, lat);
		menu.bringToFront();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.activity_bigtmap_mylocation)
		{
			goToMyLocation();
		}
		else if(v.getId()==R.id.activity_bigtmap_btn_back)
		{
			finish();
		}
		
	}
	@Override
	public void goToMyLocation() {
		// TODO Auto-generated method stub
		Dialog_BaseDialog dialog=new Dialog_BaseDialog(this,"서비스 준비중입니다.");
		dialog.show();
	}
}
