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
import ddoraemi.detailediteminfo.view.DetailedItemInfo_View;
import ddoraemi.detailediteminfo.view.ScrollTabHolderFragment;
import ddoraemi.home.model.ProgramData;
import ddoraemi.home.view.HomeView;
import ddoraemi.myinfopage.presenter.MyPage_WishPresenter;
import ddoraemi.myinfopage.presenter.MyPage_WishPresenterInterface;
import ddoraemi.start.R;

public class MyPage_WishView extends ScrollTabHolderFragment implements OnScrollListener,MyPage_WishViewInterface,OnItemClickListener{
	ListView listview;
	MyPage_Wish_ListAdapter adapter;
	ArrayList<ProgramData> items;
	MyPage_WishPresenterInterface presenter;
	public MyPage_WishView()
	{
		
	}
	public MyPage_WishView(ArrayList<ProgramData> list)
	{
		items=list;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mypage_wishlist, null);
		View placeHolderView = inflater.inflate(
				R.layout.view_mypage_placeholder, listview, false);
		init(v,placeHolderView);
		return v;
	}

	public void init(View v, View placeHolderView) {
		presenter=new MyPage_WishPresenter(this);
		listview=(ListView)v.findViewById(R.id.lv_mypage_wishlist);
		listview.addHeaderView(placeHolderView);
		adapter=new MyPage_Wish_ListAdapter(getActivity(), R.layout.item_mypage_wishlist, items);
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
					totalItemCount, 1);

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(arg2 !=0)
			presenter.validateCredentials(adapter.getItem(arg2-1));
		
	}
	@Override
	public void gotoDetailProgramInfo(ProgramData item) {
		Intent intent=new Intent(getActivity(), DetailedItemInfo_View.class);
		intent.putExtra("item", item);
		getActivity().startActivityForResult(intent, HomeView.MYPAGEINTENT);
		
	}

}
