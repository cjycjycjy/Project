package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_InfoView_presenter;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_InfoView_presenter_Interface;
import ddoraemi.detailediteminfo.view.Activity_BigTmap;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_View;
import ddoraemi.detailediteminfo.view.ScrollTabHolderFragment;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.dialog.Dialog_cancelGroup;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;
import ddoraemi.start.User;

public class DetailedGroupInfo_InfoView extends ScrollTabHolderFragment
		implements OnScrollListener, OnClickListener, OnScrollChangedListener, DetailedGroupInfo_InfoView_Interface {
	ViewPager pager;
	Group group;
	ScrollView content;
	ImageView btn_joingroup;
	DetailedGroupInfo_InfoView_presenter_Interface presenter;
	int persons;
	AppController app;
	boolean participated;
	Dialog_cancelGroup dialog;
	Dialog_cancelGroup dialog_join;
	boolean ismaster;
	String date;
	TextView btn_joinperson;
	TextView tv_cost;
	TextView tv_supplies;
	ImageView moreimg;
	ProgramData item;
	ArrayList<User> user;
	APIRequest api;
	RequestBundle requestBundle;
	String URL = "https://apis.skplanetx.com/tmap/geo/geocoding";
	Map<String, Object> param;
	String hndResult = "";
	JSONObject jObject;
	JSONObject coordinateInfo;
	TMapView t_MapView;
	TMapData tmapdata;
	RelativeLayout t_map;
	TextView personcount;
	TextView textOnthemap;
	private RelativeLayout menu;
	private ImageView gotoBigTmap;

	public DetailedGroupInfo_InfoView(Group group, ArrayList<User> user) {
		// TODO Auto-generated constructor stub
		this.group = group;
		this.user = user;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detailedgroupinfo, null);
		app = (AppController) getActivity().getApplicationContext();
		item = new ProgramData();
		presenter = new DetailedGroupInfo_InfoView_presenter(this);
		presenter.validatecredential("getdata", group.getP_id());
		init(v);
		return v;
	}

	public void init(View v) {

		personcount = (TextView) v.findViewById(R.id.tv_personcount);
		content = (ScrollView) v.findViewById(R.id.fragment_detailedgroupinfo_scrollview);
		content.getViewTreeObserver().addOnScrollChangedListener(this);
		btn_joingroup = (ImageView) v.findViewById(R.id.iv_joingroup);
		btn_joingroup.setImageResource(R.drawable.btn_participating);
		date = group.getG_start_year() + "." + group.getG_start_month() + "." + group.getG_start_day() + "  "
				+ group.getG_start_hour() + "시 " + group.getG_start_minute() + "분 - " + group.getG_end_hour() + "시 "
				+ group.getG_end_minute() + "분";
		if (app.getUser().getU_id().equalsIgnoreCase(this.group.getU_id())) {
			ismaster = true;
			participated = true;
		} else {

			setjoinbtn();

		}

		moreimg = (ImageView) v.findViewById(R.id.fragment_detailedgroupinfo_moreparticipant);

		btn_joingroup.setOnClickListener(this);
		dialog = new Dialog_cancelGroup(this.getContext(), group.getP_name(), date);
		dialog.getOkBtn().setOnClickListener(this);
		dialog.getCancelBtn().setOnClickListener(this);

		dialog_join = new Dialog_cancelGroup(this.getContext(), group.getP_name(), date);
		dialog_join.getOkBtn().setOnClickListener(this);
		dialog_join.getCancelBtn().setOnClickListener(this);

		TextView tv_p_name = (TextView) v.findViewById(R.id.fragment_detailedgroupinfo_tv_challenge_name);
		TextView tv_g_name = (TextView) v.findViewById(R.id.tv_groupname);
		TextView tv_g_comment = (TextView) v.findViewById(R.id.fragment_detailedgroupinfo_tv_comment);
		TextView tv_g_date = (TextView) v.findViewById(R.id.fragment_detailedgroupinfo_tv_dateinfo);
		pager = (ViewPager) v.findViewById(R.id.fragment_detailedgroupinfo_joinpersonpager);
		DetailedGroupInfo_personPagerAdapter adapter = new DetailedGroupInfo_personPagerAdapter(getActivity(), user ,app.getUser().getPermission(),ismaster);
		if (user.size() < 5) {
			moreimg.setVisibility(View.GONE);
		}
		pager.setAdapter(adapter);
		t_map = (RelativeLayout) v.findViewById(R.id.rl_map);

		TextView tv_persons = (TextView) v.findViewById(R.id.fragment_detailedgroupinfo_persons);
		tv_cost = (TextView) v.findViewById(R.id.fragment_detailedgroupinfo_cost);
		tv_supplies = (TextView) v.findViewById(R.id.fragment_detailedgroupinfo_supplies);
		LinearLayout btn_gotoiteminfo = (LinearLayout) v.findViewById(R.id.layout_gotoiteminfo);
		btn_gotoiteminfo.setOnClickListener(this);
		btn_joinperson = (TextView) v.findViewById(R.id.tv_personinfobtn);
		btn_joinperson.setOnClickListener(this);

		tv_g_comment.setText(group.getG_info());
		tv_g_name.setText(group.getG_name());
		tv_p_name.setText(group.getP_name());
		tv_g_date.setText(group.getG_start_year() + "-" + group.getG_start_month() + "-" + group.getG_start_day());

		textOnthemap = (TextView) v.findViewById(R.id.TvAddressOntheMAP);
		textOnthemap.setText(group.getP_addr());
		personcount.setText(String.valueOf(group.getG_persons()) + "명");
		tv_persons.setText(String.valueOf(group.getG_want_persons() + "명"));
		t_MapView = new TMapView(getActivity()); // TmapView생성
		t_map.addView(t_MapView);
		menu = (RelativeLayout) v.findViewById(R.id.infomap_menulayout);
		menu.bringToFront();
		textOnthemap = (TextView) v.findViewById(R.id.TvAddressOntheMAP);
		gotoBigTmap = (ImageView) v.findViewById(R.id.goToBigTmap);
		gotoBigTmap.setOnClickListener(this);
		t_MapView.setSKPMapApiKey("829de05c-a841-3a5f-98ef-d575aa7ca4e8");
		t_MapView.setZoomLevel(18);
		setMarker();
	}
	
	public void setMarker() {
		group.findGeoPoint(group.getP_addr(), getActivity());
		TMapPoint point = new TMapPoint(group.getLat(), group.getLng());
		TMapMarkerItem marker = new TMapMarkerItem();
		marker.setTMapPoint(point);
		marker.setName(group.getP_name());
		marker.setVisible(TMapMarkerItem.VISIBLE);
		t_MapView.addMarkerItem(group.getP_name(), marker);

		t_MapView.setCenterPoint(group.getLng(), group.getLat());
	}

	public void setjoinbtn() {
		ArrayList<Integer> arr = app.getUser().getParticipatedgroup();
		int id = this.group.getG_id();
		btn_joingroup.setImageResource(R.drawable.btn_willyouparticipate);
		participated = false;

		for (int i = 0; i < arr.size(); i++) {
			if (id == arr.get(i)) {
				btn_joingroup.setImageResource(R.drawable.btn_participating);
				participated = true;
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, 0);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// nothing
	}

	@Override
	public void adjustScroll(int scrollHeight) {
		// TODO Auto-generated method stub
		content.setScrollY(0);

	}

	@Override
	public void onScrollViewScroll(int scrollheight, int spageCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollChanged() {
		// TODO Auto-generated method stub
		if (mScrollTabHolder != null) {
			int scrollY = content.getScrollY();
			mScrollTabHolder.onScrollViewScroll(scrollY, 0);
		}
	}

	@Override
	public void showMap() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showDetailedChallengeInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 50) {
			if (data.getIntExtra("event", 1) == 2) {
				int person = this.group.getG_persons();
				this.persons = data.getIntExtra("persons", persons);
				this.group.setG_persons(person + persons);
				ArrayList<Integer> arr = app.getUser().getParticipatedgroup();
				arr.add(this.group.getG_id());
				app.getUser().setParticipatedgroup(arr);
				btn_joingroup.setImageResource(R.drawable.btn_participation);
				participated = true;
				Intent intent = new Intent();
				intent.putExtra("group", group);
				intent.putExtra("event", "renewpersons");
				this.getActivity().setResult(100, intent);
			}
		}

	}

	@Override
	public void goToJoinGroup(Group group) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(), JoinGroupView.class);
		intent.putExtra("group", group);
		startActivityForResult(intent, 50);
	}

	public void goToPersonList(Group group) {
		Intent intent = new Intent(this.getActivity(), JoinPersonListView.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}

	@Override
	public void showCancelDialog() {
		dialog_join.show();

	}

	public void showDeleteDialog() {
		dialog.show();
	}

	public void dismissDeleteDialog() {
		dialog.dismiss();
	}

	@Override
	public void cancelGroup(int minus) {
		dialog_join.dismiss();
		int person = this.group.getG_persons();
		this.group.setG_persons(person - minus);
		ArrayList<Integer> arr = app.getUser().getParticipatedgroup();
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) == group.getG_id()) {
				arr.remove(i);
			}
		}
		app.getUser().setParticipatedgroup(arr);
		participated = false;
		btn_joingroup.setImageResource(R.drawable.btn_willyouparticipate);
		personcount.setText(String.valueOf(group.getG_persons()) + "명");
		

		Intent intent = new Intent();
		intent.putExtra("group", group);
		intent.putExtra("event", "cancelgroup");
		this.getActivity().setResult(100, intent);
		((DetailedGroupInfo_View)(this.getActivity())).getPresenter().validatecredential("renew", ((DetailedGroupInfo_View)(this.getActivity())).getGroup().getG_id(),-1);
	}

	@Override
	public void dismissCancelDialog() {
		dialog_join.dismiss();
	}

	public void deleteGroup() {
		dialog.dismiss();
		Intent intent = new Intent();
		intent.putExtra("group", group);
		intent.putExtra("event", "deletegroup");
		this.getActivity().setResult(100, intent);
		getActivity().finish();
	}

	public void showCannotDelete() {
		new Dialog_BaseDialog(this.getContext(), "참여자가 있어 모임을 삭제할 수 없습니다.").show();
	}

	public void goToItemInfo() {
		Intent intent = new Intent(this.getActivity(), DetailedItemInfo_View.class);
		intent.putExtra("item", this.item);
		startActivity(intent);
	}

	public void setItem(ProgramData item) {
		this.item = item;
		if (!item.getP_cost_adult().equalsIgnoreCase(""))
			tv_cost.setText("성인-" + item.getP_cost_adult() + "원,아이:" + item.getP_cost_kid() + "원");
		else
			tv_cost.setText("가격정보 없음");
		tv_supplies.setText(item.getP_note());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// 마스터도 아니고 참여자도 아닐때
		if (v.getId() == R.id.iv_joingroup && participated == false && ismaster == false) {
			presenter.validatecredential("joingroup", group);
		}
		// 마스터가 아니고 참여자일때
		else if (v.getId() == R.id.iv_joingroup && participated == true && ismaster == false) {
			presenter.validatecredential("cancel", group);
		} else if (v.getId() == R.id.btn_dialog_confirmcancel_ok && participated == true && ismaster == false) {
			presenter.validatecredential("cancelGroup", group);
		} else if (v.getId() == R.id.btn_dialog_confirmcancel_cancel && participated == true && ismaster == false) {
			presenter.validatecredential("dismiss_cancel_dialog", group);
		}
		// 마스터 일때
		else if (v.getId() == R.id.iv_joingroup && participated == true && ismaster == true) {
			presenter.validatecredential("delete", group);
		} else if (v.getId() == R.id.btn_dialog_confirmcancel_ok && participated == true && ismaster == true) {
			presenter.validatecredential("deleteGroup", group);
		} else if (v.getId() == R.id.btn_dialog_confirmcancel_cancel && participated == true && ismaster == true) {
			presenter.validatecredential("dismiss_delete_dialog", group);
//		} else if (v.getId() == R.id.tv_personinfobtn) {
//			presenter.validatecredential("personsinfo", group, ismaster);
		} else if (v.getId() == R.id.layout_gotoiteminfo) {
			presenter.validatecredential("gotoIteminfo", group.getP_id());
		} else if (v.getId() == R.id.goToBigTmap) {
			presenter.validatecredential("gotoTmap", null);
		} 
	}
	
	@Override
	public void goToTmap() {
		// TODO Auto-generated method stub

		Intent intent = new Intent(getActivity(), Activity_BigTmap.class);
		intent.putExtra("lng", group.getLng());
		intent.putExtra("lat", group.getLat());
		intent.putExtra("name", group.getP_name());
		intent.putExtra("address", group.getP_addr());
		getActivity().startActivity(intent);
	}

}
