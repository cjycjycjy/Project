package ddoraemi.adminmodehome.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import ddoraemi.adminmodehome.presenter.AdminSchedulePresenter;
import ddoraemi.adminmodehome.presenter.AdminSchedulePresenterInterface;
import ddoraemi.creategroup.view.CalData;
import ddoraemi.home.model.Group;
import ddoraemi.start.R;

public class AdminScheduleView extends Fragment implements
		AdminScheduleViewInterface, OnClickListener ,OnItemClickListener{
	AdminSchedulePresenterInterface presenter;
	ArrayList<CalData> arrData;
	Hashtable<String, ArrayList<Group>>groups;
	ArrayList<Group> grouparray;
	int startday;
	Adapter_AdminSchedule adapter;
	Calendar mCal;
	GridView gridview;
	LinearLayout next;
	LinearLayout prev;
	TextView tv_date;
	ListView listview;
	int selpo;
	int todayyear;
	int todaymonth;
	int todaydate;
	int thisMonth;
	int thisYear;

	public AdminScheduleView(Hashtable<String, ArrayList<Group>>groups) {
		selpo = -1;
		presenter = new AdminSchedulePresenter(this);
		this.groups = groups;
		mCal = Calendar.getInstance();
		todayyear = mCal.get(Calendar.YEAR);
		todaymonth = mCal.get(Calendar.MONTH) + 1;
		todaydate = mCal.get(Calendar.DATE);
		thisMonth = todaymonth;
		thisYear = todayyear;
		grouparray=new ArrayList<>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_admin_schedule, null);
		tv_date = (TextView) v.findViewById(R.id.fragment_admin_schedule_date);
		next = (LinearLayout) v.findViewById(R.id.fragment_admin_schedule_next);
		prev = (LinearLayout) v.findViewById(R.id.fragment_admin_schedule_prev);
		gridview = (GridView) v
				.findViewById(R.id.fragment_admin_schedule_gridview);
		listview=(ListView)v.findViewById(R.id.fragment_admin_schedule_listview);
		gridview.setOnItemClickListener(this);
		next.setOnClickListener(this);
		prev.setOnClickListener(this);
		arrData = new ArrayList<>();
		setCalendarDate(thisYear, thisMonth);
		return v;
	}

	public void setCalendarDate(int year, int month) {
		arrData.clear();
		mCal.set(year, month - 1, 1);
		startday = mCal.get(Calendar.DAY_OF_WEEK);
		if (startday != 1) {
			for (int i = 0; i < startday - 1; i++) {
				arrData.add(null);
			}
		}

		for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			mCal.set(mCal.get(Calendar.YEAR), month - 1, (i + 1));
			CalData data=new CalData((i + 1), mCal.get(Calendar.DAY_OF_WEEK),
					year, month);
			String key=String.valueOf(data.getYear()*10000)+String.valueOf(data.getMonth()*100)+String.valueOf(data.getDay());
			if(groups.containsKey(key))
			{
				data.setPerformDay(true);
			}
			arrData.add(data);
		}
		adapter = new Adapter_AdminSchedule(getActivity(), arrData, todayyear,
				todaymonth, todaydate);
		gridview.setAdapter(adapter);
		tv_date.setText(year + "년 " + month + "월");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.fragment_admin_schedule_next)
		{
			presenter.nextMonth();
		}
		else if(v.getId()==R.id.fragment_admin_schedule_prev)
		{
			presenter.prevMonth();
		}

	}

	@Override
	public void goToNextMonth() {
		// TODO Auto-generated method stub
		selpo = -1;
		listview.setVisibility(View.GONE);
		if (thisMonth < 12) {
			thisMonth++;
			setCalendarDate(thisYear, thisMonth);
		} else {
			thisYear++;
			thisMonth = 1;
			setCalendarDate(thisYear, thisMonth);
		}

	}

	@Override
	public void goToPrevMonth() {
		selpo = -1;
		listview.setVisibility(View.GONE);
		if (thisMonth > 1) {
			thisMonth--;
			setCalendarDate(thisYear, thisMonth);
		} else {
			thisYear--;
			thisMonth = 12;
			setCalendarDate(thisYear, thisMonth);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if (selpo != -1) {
			arg0.getChildAt(selpo).setBackgroundColor(
					Color.parseColor("#FFFFFF"));
		}
		arg0.getChildAt(arg2).setBackgroundColor(
				Color.parseColor("#FFE57F"));
		selpo=arg2;
		CalData data=(CalData) adapter.getItem(arg2);
		if(data.isPerformDay())
		{
			listview.setVisibility(View.VISIBLE);
			String key=String.valueOf(data.getYear()*10000)+String.valueOf(data.getMonth()*100)+String.valueOf(data.getDay());
			Adapter_AdminScheduleList listadapter=new Adapter_AdminScheduleList(getActivity(), R.layout.item_adminschedulelist, groups.get(key));
			listview.setAdapter(listadapter);
		}
		else
		{
			listview.setVisibility(View.GONE);
		}
	}

	@Override
	public void makeNullList() {
		for(int i=0; i<3; i++)
		{
			grouparray.add(null);
		}
	}
}
