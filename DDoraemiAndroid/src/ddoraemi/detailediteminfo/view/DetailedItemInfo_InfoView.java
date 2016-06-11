package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;
import com.skp.Tmap.TMapView.OnClickListenerCallback;
import com.skp.openplatform.android.sdk.api.APIRequest;
import com.skp.openplatform.android.sdk.common.RequestBundle;

import ddoraemi.creategroup.view.CreateGroup_View;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_ViewPresenter;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_ViewPresenter_Interface;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;

public class DetailedItemInfo_InfoView extends ScrollTabHolderFragment
		implements OnScrollListener, DetailedItemInfo_InfoView_Interface,
		OnClickListener, OnScrollChangedListener {

	ProgramData data;
	TextView tv_challengeName;
	TextView tv_comment;

	TextView tv_cost;
	TextView tv_leadtime;
	TextView tv_supplied;
	TextView tv_website;
	TextView tv_phonenum;
	TextView tv_curriculum;

	ListView lv_challengeInfo;
	ImageView iv_createGroup;
	RelativeLayout t_map;
	ScrollView content;
	ArrayList<Group> group;
	DetailedItemInfo_ViewPresenter_Interface presenter;
	ImageView star[];
	APIRequest api;
	RequestBundle requestBundle;
	String URL = "https://apis.skplanetx.com/tmap/geo/geocoding";
	Map<String, Object> param;
	String hndResult = "";
	JSONObject jObject;
	JSONObject coordinateInfo;
	TMapView t_MapView;
	TMapData tmapdata;
	RelativeLayout menu;
	ImageView gotoBigTmap;

	ImageView spring, summer, fall, winter;
	private TextView textOnthemap;

	public void setMarker() {
		data.findGeoPoint(data.get_AddressText() + "번지", getActivity());
		TMapPoint point = new TMapPoint(data.getLat(), data.getLng());
		TMapMarkerItem marker = new TMapMarkerItem();
		marker.setTMapPoint(point);
		marker.setName(data.getE_name());
		marker.setVisible(TMapMarkerItem.VISIBLE);
		t_MapView.addMarkerItem(data.getP_name(), marker);

		t_MapView.setCenterPoint(data.getLng(), data.getLat());
	}

	public DetailedItemInfo_InfoView(ProgramData data, ArrayList<Group> group) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.presenter = new DetailedItemInfo_ViewPresenter(this);
		this.group = group;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detailedmeetinginfo, null);
		init(v);
		return v;
	}

	public void init(View v) {
		star = new ImageView[5];
		star[0] = (ImageView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_img1);
		star[1] = (ImageView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_img2);
		star[2] = (ImageView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_img3);
		star[3] = (ImageView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_img4);
		star[4] = (ImageView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_img5);
		int score = (int) (data.getP_grade() + 0.5);
		for (int i = 0; i < score; i++) {
			star[i].setVisibility(View.VISIBLE);
		}
		content = (ScrollView) v
				.findViewById(R.id.fragment_detailedmeetinginfo_scrollview);
		tv_challengeName = (TextView) v
				.findViewById(R.id.fragment_detailedmeetinginfo_tv_challenge_name);
		tv_comment = (TextView) v
				.findViewById(R.id.fragment_detailedmeetinginfo_tv_comment);
		tv_challengeName.setText(data.getP_name());
		tv_comment.setText(data.getP_info());

		tv_cost = (TextView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_tv_cost);
		if (!data.getP_cost_adult().isEmpty())
			tv_cost.setText("성인-" + data.getP_cost_adult() + "원,아이-"
					+ data.getP_cost_kid() + "원");
		else
			tv_cost.setText("가격 정보 없음");
		tv_leadtime = (TextView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_tv_leadtime);
		if (data.getP_lead_time() != 0) {
			tv_leadtime.setText(String.valueOf(data.getP_lead_time()) + "분");
		} else {
			tv_leadtime.setText("-분");
		}
		tv_supplied = (TextView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_tv_supplied);
		tv_supplied.setText(data.getP_preparation_item());
		tv_website = (TextView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_tv_website);
		tv_website.setText(data.getP_url());
		tv_phonenum = (TextView) v
				.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_tv_phonenum);
		tv_phonenum.setText(data.getP_tel());
		tv_curriculum=(TextView)v.findViewById(R.id.fragment_detailmeeetinginfo_star_fill_tv_curriculum);
		String cur="";
		for(int i=0; i<data.getP_curriculum().size(); i++)
		{
			if(i<data.getP_curriculum().size())
			cur+=data.getP_curriculum().get(i)+"\n";
			else
				cur+=data.getP_curriculum().get(i);
			
				
		}
		tv_curriculum.setText(cur);
		tv_website.setOnClickListener(this);
		tv_phonenum.setOnClickListener(this);
		iv_createGroup = (ImageView) v.findViewById(R.id.iv_creategroup);
		iv_createGroup.setOnClickListener(this);
		content.getViewTreeObserver().addOnScrollChangedListener(this);
		t_map = (RelativeLayout) v.findViewById(R.id.rl_map);
		spring = (ImageView) v
				.findViewById(R.id.fragment_detailmeetinginfo_img_spring);
		summer = (ImageView) v
				.findViewById(R.id.fragment_detailmeetinginfo_img_summer);
		fall = (ImageView) v
				.findViewById(R.id.fragment_detailmeetinginfo_img_autumn);
		winter = (ImageView) v
				.findViewById(R.id.fragment_detailmeetinginfo_img_winter);
		if (data.getP_available_season().toString().substring(0, 1)
				.equalsIgnoreCase("1")) {
			spring.setVisibility(View.VISIBLE);
		}
		if (data.getP_available_season().toString().substring(1, 2)
				.equalsIgnoreCase("1")) {
			summer.setVisibility(View.VISIBLE);
		}
		if (data.getP_available_season().toString().substring(2, 3)
				.equalsIgnoreCase("1")) {
			fall.setVisibility(View.VISIBLE);
		}
		if (data.getP_available_season().toString().substring(3, 4)
				.equalsIgnoreCase("1")) {
			winter.setVisibility(View.VISIBLE);
		}

		t_MapView = new TMapView(getActivity()); // TmapView생성
		t_map.addView(t_MapView);
		menu=(RelativeLayout)v.findViewById(R.id.infomap_menulayout);
		menu.bringToFront();
		textOnthemap=(TextView)v.findViewById(R.id.TvAddressOntheMAP);
		gotoBigTmap=(ImageView)v.findViewById(R.id.goToBigTmap);
		gotoBigTmap.setOnClickListener(this);
		textOnthemap.setText(data.get_AddressText());
		t_MapView.setSKPMapApiKey("829de05c-a841-3a5f-98ef-d575aa7ca4e8");
		t_MapView.setZoomLevel(18);
		setMarker();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.iv_creategroup) {
			presenter.validatecredential("createGroup", data);
		} else if (v.getId() == R.id.fragment_detailmeeetinginfo_star_fill_tv_website) {
			presenter.goToWebsite();
		} else if (v.getId() == R.id.fragment_detailmeeetinginfo_star_fill_tv_phonenum) {
			presenter.callToAdmin();
		}else if(v.getId()==R.id.goToBigTmap)
		{
			presenter.validatecredential("goToMap", null);
		}
	}

	@Override
	public void goToCreateGroup(ProgramData item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(), CreateGroup_View.class);
		intent.putExtra("item", data);
		intent.putExtra("group", group);
		getActivity().startActivityForResult(intent, 100);
	}

	@Override
	public void adjustScroll(int scrollHeight) {
		content.setScrollY(0);

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount, 0);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// nothing
	}

	@Override
	public void onScrollViewScroll(int scrollheight, int spageCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollChanged() {
		if (mScrollTabHolder != null) {
			int scrollY = content.getScrollY();
			mScrollTabHolder.onScrollViewScroll(scrollY, 0);
		}

	}

	@Override
	public void callToAdmin() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
				+ tv_phonenum.getText().toString()));
		startActivity(intent);
	}

	@Override
	public void goToWebsite() {
		// TODO Auto-generated method stub
		Intent i = new Intent(Intent.ACTION_VIEW);
		String url = tv_website.getText().toString();
		if (!url.substring(0, 7).equalsIgnoreCase("http://"))
			url = "http://" + url;
		Uri u = Uri.parse(url);
		i.setData(u);
		startActivity(i);
	}

	@Override
	public void goToTmap() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(getActivity(),Activity_BigTmap.class);
		intent.putExtra("lng", data.getLng());
		intent.putExtra("lat", data.getLat());
		intent.putExtra("name", data.getP_name());
		intent.putExtra("address", data.get_AddressText());
		getActivity().startActivity(intent);		
	}

}
