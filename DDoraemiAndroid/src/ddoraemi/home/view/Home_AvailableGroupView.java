package ddoraemi.home.view;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_View;
import ddoraemi.home.model.Group;
import ddoraemi.home.presenter.Home_AvailableGroupPresenter;
import ddoraemi.home.presenter.Home_AvailableGroupPresenter_Interface;
import ddoraemi.start.R;

public class Home_AvailableGroupView extends Fragment implements
		Home_AvailableGroupView_Interface, OnItemClickListener,
		OnItemSelectedListener {
	ListView listview;// �������� ���� ����Ʈ��
	ArrayList<Group> list;

	Spinner select_sortmode;
	Home_MainViewGroupListAdapter adapter;
	ArrayAdapter spinnerdapter;
	AppController app;
	Home_AvailableGroupPresenter_Interface presenter;
	int s_pos;
	int spinnerpos;

	public Home_AvailableGroupView(ArrayList<Group> list) {
		this.list = new ArrayList<Group>();
		this.list.addAll(list);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_availablegroupview, null);
		init(v);
		return v;
	}

	public void init(View v) {
		app = (AppController) getActivity().getApplicationContext();
		presenter = new Home_AvailableGroupPresenter(this);
		select_sortmode = (Spinner) v
				.findViewById(R.id.fragment_recommend_spinner);
		spinnerdapter = ArrayAdapter
				.createFromResource(getActivity(),
						R.array.recommend_spinner_items,
						R.layout.item_spinnerrecommend);
		spinnerdapter.setDropDownViewResource(R.layout.item_spinnerrecommend);
		select_sortmode.setAdapter(spinnerdapter);
		select_sortmode.setOnItemSelectedListener(this);
		listview = (ListView) v.findViewById(R.id.fragment_available_listview);
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
		s_pos = position;
		presenter.validatecredential(group);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		spinnerpos = arg2;
		presenter.validatecredential("sort", arg2);

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public int get_s_pos() {
		return s_pos;
	}

	public void deleteGroup(Group group) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getG_id() == group.getG_id()) {
				list.remove(i);
				break;
			}
		}
		adapter.notifyDataSetChanged();
		sortList(spinnerpos);
	}

	public void renewList(ArrayList<Group> group) {
		this.list.clear();
		this.list.addAll(group);
		adapter.notifyDataSetChanged();
		sortList(spinnerpos);
	}

	public void renewPersons(Group group) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getG_id() == group.getG_id()) {
				list.get(i).setG_persons(group.getG_persons());
				break;
			}
		}
		adapter.notifyDataSetChanged();
		sortList(spinnerpos);
	}

	@Override
	public void goToDetailedGroupInfo(Group group) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(),
				DetailedGroupInfo_View.class);
		intent.putExtra("group", group);
		getActivity().startActivityForResult(intent, 50);

	}

	private final Comparator<Group> D_DayComparator = new Comparator<Group>() {
		@Override
		public int compare(Group object1, Group object2) {
			Integer dday1 = object1.getD_day();
			Integer dday2 = object2.getD_day();
			return dday1.compareTo(dday2);

		}

	};
	private final Comparator<Group> NewestComparator = new Comparator<Group>() {
		private final Collator collator = Collator.getInstance();

		@Override
		public int compare(Group object1, Group object2) {
			Integer ob1makeday = (object1.getG_open_year() * 10000)
					+ (object1.getG_open_month() * 100)
					+ object1.getG_open_day();
			Integer ob2makeday = (object2.getG_open_year() * 10000)
					+ (object2.getG_open_month() * 100)
					+ object2.getG_open_day();
			return ob2makeday.compareTo(ob1makeday);

		}

	};
	private final Comparator<Group> DinstaceComparator = new Comparator<Group>() {
		private final Collator collator = Collator.getInstance();

		@Override
		public int compare(Group object1, Group object2) {
			Location Group1 = new Location("Group1");
			Location Group2 = new Location("Group2");
			Location user = new Location("User");
			Group1.setLatitude(object1.getLat());
			Group1.setLongitude(object1.getLng());
			Group2.setLatitude(object2.getLat());
			Group2.setLongitude(object2.getLng());
			user.setLatitude(app.getUser().getLat());
			user.setLongitude(app.getUser().getLng());
			Float ob1distance = user.distanceTo(Group1);
			Float ob2distance = user.distanceTo(Group2);
			return ob1distance.compareTo(ob2distance);

		}

	};

	@Override
	public void sortList(int position) {
		if (position == 0) {
			Collections.sort(list, D_DayComparator);
			adapter.notifyDataSetChanged();
		} else if (position == 1) {// 거리순 짜야댐

			Collections.sort(list, DinstaceComparator);
			adapter.notifyDataSetChanged();
		} else if (position == 2) {
			Collections.sort(list, NewestComparator);
			adapter.notifyDataSetChanged();
		}

	}
}
