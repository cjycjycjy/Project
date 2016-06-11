package ddoraemi.search.view;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_View;
import ddoraemi.home.model.Group;
import ddoraemi.home.presenter.Home_AvailableGroupPresenter;
import ddoraemi.home.presenter.Home_AvailableGroupPresenter_Interface;
import ddoraemi.home.view.Home_AvailableGroupView_Interface;
import ddoraemi.home.view.Home_MainViewGroupListAdapter;
import ddoraemi.start.R;

public class Search_GroupView extends Fragment implements OnItemClickListener{
	ListView listview;// �������� ���� ����Ʈ��
	ArrayList<Group> list;

	Home_MainViewGroupListAdapter adapter;
	AppController app;
	Home_AvailableGroupPresenter_Interface presenter;
	int s_pos;
	int spinnerpos;

	public Search_GroupView(ArrayList<Group> list) {
		this.list = list;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceSrtate) {
		View v = inflater.inflate(R.layout.fragment_searchprogram, null);
		init(v);
		return v;
	}

	public void init(View v) {
		app = (AppController) getActivity().getApplicationContext();
		listview = (ListView) v.findViewById(R.id.fragment_searchprogram_listview);
		adapter = new Home_MainViewGroupListAdapter(getActivity(),
				R.layout.item_grouplist, list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Group group = (Group) parent.getItemAtPosition(position);
		goToDetailedGroupInfo(group);

	}

	public void goToDetailedGroupInfo(Group group) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(),
				DetailedGroupInfo_View.class);
		intent.putExtra("group", group);
		getActivity().startActivityForResult(intent, 50);

	}
}