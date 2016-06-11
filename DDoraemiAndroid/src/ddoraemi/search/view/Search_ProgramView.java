package ddoraemi.search.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_View;
import ddoraemi.home.model.ProgramData;
import ddoraemi.home.presenter.Home_RecommendPresenterInterface;
import ddoraemi.home.view.Home_MainViewChallengeListAdapter;
import ddoraemi.start.R;

public class Search_ProgramView extends Fragment implements OnItemClickListener {
	ListView listview;// ��õ ���� ����Ʈ��
	ArrayList<ProgramData> list;// /������� �Ѿ�� ����Ʈ
	Home_MainViewChallengeListAdapter adapter;
	Home_RecommendPresenterInterface presenter;
	TextView title;
	AppController app;

	public Search_ProgramView() {
		// TODO Auto-generated constructor stub
	}

	public Search_ProgramView(ArrayList<ProgramData> list) {
		this.list = list;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_searchprogram, null);
		init(v);
		return v;
	}

	public void init(View v) {
		app = (AppController) getActivity().getApplicationContext();
		listview = (ListView) v
				.findViewById(R.id.fragment_searchprogram_listview);
		adapter = new Home_MainViewChallengeListAdapter(getActivity(),
				R.layout.item_challengelist, list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ProgramData item = (ProgramData) parent.getItemAtPosition(position);
		goToDetailedItemInfo(item);
	}

	public void goToDetailedItemInfo(ProgramData item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), DetailedItemInfo_View.class);
		intent.putExtra("item", item);
		getActivity().startActivityForResult(intent, 50);
	}
}
