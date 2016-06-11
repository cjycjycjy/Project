package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_View;
import ddoraemi.detailediteminfo.view.ScrollTabHolderFragment;
import ddoraemi.home.model.Group;
import ddoraemi.home.view.HomeView;
import ddoraemi.myinfopage.presenter.MyPage_ParticipatePresenter;
import ddoraemi.myinfopage.presenter.MyPage_ParticipatePresenterInterface;
import ddoraemi.start.R;

public class MyPage_ParticipateView extends ScrollTabHolderFragment implements
		OnScrollListener,MyPage_ParticipateViewInterface,OnItemClickListener {
	ArrayList<Group> joining_list;
	ListView listview;
	MyPage_Participage_ListAdapter adapter;
	MyPage_ParticipatePresenterInterface presenter;

	public MyPage_ParticipateView() {
		super();
	}

	public MyPage_ParticipateView(ArrayList<Group> joining_list) {
		this.joining_list = joining_list;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mypage_participatelist,
				null);
		View placeHolderView = inflater.inflate(
				R.layout.view_mypage_placeholder, listview, false);
		init(v, placeHolderView);
		return v;
	}

	public void init(View v, View placeHolderView) {
		presenter=new MyPage_ParticipatePresenter(this);
		listview = (ListView) v.findViewById(R.id.lv_mypage_participatelist);
		listview.addHeaderView(placeHolderView);
		adapter = new MyPage_Participage_ListAdapter(getActivity(),
				R.layout.item_mypage_joininggroup, joining_list);
		listview.setAdapter(adapter);
		listview.setOnScrollListener(this);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void adjustScroll(int scrollHeight) {
		// TODO Auto-generated method stub
		listview.setSelectionFromTop(1, listview.getMeasuredHeight());

	}

	@Override
	public void onScrollViewScroll(int scrollheight, int spageCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount, 0);

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gotoDetailGroup(Group item) {
		// TODO Auto-generated method stub	
		Intent intent=new Intent(getActivity(), DetailedGroupInfo_View.class);
		intent.putExtra("group", item);
		getActivity().startActivityForResult(intent, HomeView.MYPAGEINTENT);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if(arg2!=0)
			presenter.validateCredentials(adapter.getItem(arg2-1));
	}

}
