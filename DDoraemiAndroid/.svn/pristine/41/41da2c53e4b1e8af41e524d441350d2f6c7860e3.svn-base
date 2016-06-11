package ddoraemi.home.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_View;
import ddoraemi.home.model.ProgramData;
import ddoraemi.home.presenter.Home_RecommendPresenter;
import ddoraemi.home.presenter.Home_RecommendPresenterInterface;
import ddoraemi.start.R;

public class Home_RecommendView extends Fragment implements Home_RecommendViewInterface, OnItemClickListener{
	ListView listview;//��õ ���� ����Ʈ��
	ArrayList<ProgramData> list;///������� �Ѿ�� ����Ʈ
	Home_MainViewChallengeListAdapter adapter;
	Home_RecommendPresenterInterface presenter;
	TextView title;
	AppController app;
	public static Home_RecommendView mcontext;
	public Home_RecommendView() {
		// TODO Auto-generated constructor stub
	}
	public Home_RecommendView(ArrayList<ProgramData> list)
	{
		this.list=new ArrayList<ProgramData>();
		this.list.addAll(list);
		presenter = new Home_RecommendPresenter(this);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_recommendview, null);
		init(v);
		return v;
	}

	public void init(View v) {
		mcontext = this;
		app=(AppController)getActivity().getApplicationContext();
		listview=(ListView)v.findViewById(R.id.fragment_recommend_listview);
		adapter=new Home_MainViewChallengeListAdapter(getActivity(), R.layout.item_challengelist, list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
		title=(TextView)v.findViewById(R.id.fragment_recommend_Tv);
		title.setText(app.getId() +" 님에게 가장 맞는 체험 활동은?");

	}
	public void renew(ArrayList<ProgramData> lists){
		list.clear();
		list.addAll(lists);
		adapter.notifyDataSetChanged();
		//setListViewHeightBasedOnChildren(listview);
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ProgramData item = (ProgramData)parent.getItemAtPosition(position);
		presenter.validatecredential("getItemInfo",item);	
	}
	@Override
	public void goToDetailedItemInfo(ProgramData item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), DetailedItemInfo_View.class);
		intent.putExtra("item",item);
		getActivity().startActivityForResult(intent, 50);
	}
	
	
	
}
