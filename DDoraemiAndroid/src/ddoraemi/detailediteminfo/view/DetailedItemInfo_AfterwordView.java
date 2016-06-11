package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import com.android.volley.toolbox.NetworkImageView;

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
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_AfterwordView_presenter;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_AfterwordView_presenter_Interface;
import ddoraemi.start.R;


public class DetailedItemInfo_AfterwordView extends ScrollTabHolderFragment implements DetailedItemInfo_AfterwordView_Interface
,OnScrollListener, OnItemClickListener{

	ListView listview;
	ArrayList<Afterword> list;
	DetailedItemInfo_AfterwordView_presenter_Interface presenter;
	DetailedItemInfo_AfterwordListAdapter adapter;
	
	public DetailedItemInfo_AfterwordView(ArrayList<Afterword> list) {
		// TODO Auto-generated constructor stub
		this.list = new ArrayList<Afterword>();
		this.list = list;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_challengeafterword, null);
		View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, listview, false);
		init(v,placeHolderView);
		return v;
	}
	public void init(View v,View placeHolderView){
		listview=(ListView)v.findViewById(R.id.lv_challenge_afterword);
		listview.addHeaderView(placeHolderView);
		listview.setOnItemClickListener(this);
		presenter = new DetailedItemInfo_AfterwordView_presenter(this);
	
		adapter=new DetailedItemInfo_AfterwordListAdapter(getActivity(), R.layout.item_afterwordlist, list);
		listview.setAdapter(adapter);
		
		listview.setOnScrollListener(this);
		listview.setFocusable(true);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if(position!=0){
			presenter.validatecredential((Afterword)listview.getItemAtPosition(position),position);
		}
	}
	
	@Override
	public void adjustScroll(int scrollHeight) {

		listview.setSelectionFromTop(1, listview.getMeasuredHeight());
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, 1);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// nothing
	}
	@Override
	public void goToDetailedAfterword(Afterword item, int position) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(),DetailedAfterword_reply_view.class);
		//intent.putExtra("replynum", replynum);
		intent.putExtra("position", position);
		intent.putExtra("item", item);
		getActivity().startActivityForResult(intent, 50);
	}
	public void renewData(Afterword data, int position){
		if(position!=0){
			int num = data.getA_r_num();
			data.setA_r_num(data.getAfterword_reply().size());
			this.list.set(position-1, data);
			
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onScrollViewScroll(int scrollheight, int spageCount) {
		// TODO Auto-generated method stub

	}

}
