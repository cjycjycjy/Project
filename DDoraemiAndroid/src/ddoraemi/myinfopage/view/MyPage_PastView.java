package ddoraemi.myinfopage.view;

import java.util.ArrayList;
import java.util.Hashtable;

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
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.view.DetailedAfterword_reply_view;
import ddoraemi.detailediteminfo.view.ScrollTabHolderFragment;
import ddoraemi.home.model.Group;
import ddoraemi.home.view.HomeView;
import ddoraemi.myinfopage.presenter.MyPage_PastPresenter;
import ddoraemi.myinfopage.presenter.MyPage_PastPresenterInterface;
import ddoraemi.start.R;

public class MyPage_PastView extends ScrollTabHolderFragment implements OnScrollListener,MyPage_PastViewInterface,OnItemClickListener{
	ListView listview;
	MyPage_Past_ListAdapter adapter;
	ArrayList<Group> items;
	Hashtable<Integer, Afterword> afterword;
	MyPage_PastPresenterInterface presenter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mypage_pastlist, null);
		View placeHolderView = inflater.inflate(
				R.layout.view_mypage_placeholder, listview, false);
		init(v,placeHolderView);
		return v;
	}
	
	public MyPage_PastView()
	{
		
	}
	public MyPage_PastView(ArrayList<Group> list,Hashtable<Integer, Afterword> afterword)
	{
		items=list;
		this.afterword=afterword;
		presenter=new MyPage_PastPresenter(this);
	}
	public void init(View v, View placeHolderView) {
		listview=(ListView)v.findViewById(R.id.lv_mypage_pastlist);
		listview.addHeaderView(placeHolderView);
		adapter=new MyPage_Past_ListAdapter(getActivity(), R.layout.item_mypage_pastlist, items);
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
					totalItemCount, 2);

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void notifyWritenAfterword(ArrayList<Group> items) {
		this.items.clear();
		this.items.addAll(items);
		adapter.notifyDataSetChanged();
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(adapter.getItem(arg2-1).getMyafterwordnum()!=-1)
		{
		Afterword item=afterword.get(adapter.getItem(arg2-1).getG_id());
		presenter.validateCredentials(item);
		}
		
	}
	@Override
	public void goTodetail(Afterword item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(),DetailedAfterword_reply_view.class);
		intent.putExtra("item", item);
		getActivity().startActivityForResult(intent, HomeView.MYPAGEINTENT);
		
	}

}
