package ddoraemi.detailediteminfo.view;

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
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_groupView_presenter;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_groupView_presenter_Interface;
import ddoraemi.home.model.Group;
import ddoraemi.home.view.Home_MainViewGroupListAdapter;
import ddoraemi.start.R;

public class DetailedItemInfo_groupView extends ScrollTabHolderFragment implements DetailedItemInfo_groupView_Interface,
OnScrollListener, OnItemClickListener{

	ListView listview;
	ArrayList<Group> list;
	DetailedItemInfo_groupView_presenter_Interface presenter;
	Home_MainViewGroupListAdapter adapter;
	int s_pos;

	public DetailedItemInfo_groupView(ArrayList<Group> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_challengegroupview, null);
		View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, listview, false);
		init(v,placeHolderView);
		return v;
	}

	public void init(View v,View placeHolderView){
		listview=(ListView)v.findViewById(R.id.lv_challenge_group);

		listview.addHeaderView(placeHolderView);
		listview.setOnItemClickListener(this);
		adapter=new Home_MainViewGroupListAdapter(getActivity(), R.layout.item_grouplist, list);
		listview.setAdapter(adapter);
		presenter = new DetailedItemInfo_groupView_presenter(this);
		listview.setOnScrollListener(this);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		s_pos = position;
		presenter.validatecredential((Group)parent.getItemAtPosition(position));

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, 2);

	}

	public void deleteGroup(Group group){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getG_id() == group.getG_id()){
				list.remove(i);
				break;
			}
		}
		adapter.notifyDataSetChanged();
	}
	public void renewPersons(Group group){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getG_id() == group.getG_id()){
				list.get(i).setG_persons(group.getG_persons());
				break;
			}
		}
		adapter.notifyDataSetChanged();
	}
	
	
	@Override
	public void goToDetailedGroupInfo(Group group) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(), DetailedGroupInfo_View.class);
		intent.putExtra("group", group);
		getActivity().startActivityForResult(intent, 50);
	}

	@Override
	public void renewData(ArrayList<Group> item) {
		list.clear();
		list.addAll(item);
		adapter.notifyDataSetChanged();

	}

}
