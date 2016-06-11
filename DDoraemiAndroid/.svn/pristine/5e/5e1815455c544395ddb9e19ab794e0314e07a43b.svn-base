package ddoraemi.dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import retrofit.http.EncodedQueryMap;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.creategroup.view.CalData;
import ddoraemi.creategroup.view.CreateGroup_View;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;

public class Dialog_GroupDateSelect extends Dialog implements
		android.view.View.OnClickListener, OnItemClickListener {
	Context mContext;
	Calendar mCal;
	TextView title;
	LinearLayout next, prev;
	ArrayList<CalData> arrData;
	GridView gridview;
	ListView listview;
	DateAdapter adapter;
	Group temp;
	Hashtable<String, ArrayList<Group>> scheduletable;
	int nullcount;
	ImageView amorpmup, amorpmdown, hourup, hourdown, minup, mindown, btn_add;
	int hour, min;
	TextView tvamorpm;
	EditText tvhour, tvmin;
	boolean amorpm;
	int startday;
	int todayyear;
	int todaymonth;
	int todaydate;
	int thisYear;
	int thisMonth;
	int selectyear;
	int selectmonth;
	int selectday;
	int endyear;
	int endmonth;
	int enddate;
	int selpo = -1;
	int afterweektodayvalue;
	ImageView plus;
	String timeregex = "^[0-9]{1,2}$";// 핸드폰조건
	ArrayList<Group> group;
	int selectpostion = -1;
	Adapter_Dialog_DateList listadapter;
	ArrayList<Group> listviewArray;
	ProgramData data;
	boolean nullarray;
	boolean pushplusbt;
	boolean selecteddate;
	AppController app;
	Button ok;

	public Dialog_GroupDateSelect(Context context, ProgramData data,
			ArrayList<Group> group) {
		super(context);

		app = (AppController) context.getApplicationContext();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_groupdateselect);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(true);
		mContext = context;
		this.selecteddate=false;
		this.group = group;
		this.data = data;
		initComponent();
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		nullcount = 3;
		hour = 2;
		min = 0;
		amorpm = false;// true면 오전
		mCal = Calendar.getInstance();
		todayyear = mCal.get(Calendar.YEAR);
		todaymonth = mCal.get(Calendar.MONTH) + 1;
		todaydate = mCal.get(Calendar.DATE);
		thisMonth = todaymonth;
		thisYear = todayyear;
		mCal.add(Calendar.DATE, 7);
		afterweektodayvalue = (mCal.get(Calendar.YEAR) * 10000)
				+ ((mCal.get(Calendar.MONTH) + 1) * 100)
				+ mCal.get(Calendar.DATE);
		arrData = new ArrayList<CalData>();
		ok = (Button) findViewById(R.id.dialog_groupdateselect_ok);
		listview = (ListView) findViewById(R.id.dialog_groupdateselect_listview);
		gridview = (GridView) findViewById(R.id.dialog_groupdateselect_gridview);
		title = (TextView) findViewById(R.id.dialog_groupdateselect_date);
		next = (LinearLayout) findViewById(R.id.dialog_groupdateselect_next);
		prev = (LinearLayout) findViewById(R.id.dialog_groupdateselect_prev);
		next.setOnClickListener(this);
		prev.setOnClickListener(this);
		setCalendarDate(thisYear, thisMonth);
		amorpmup = (ImageView) findViewById(R.id.dialog_groupdateselect_AMorPMup);
		amorpmdown = (ImageView) findViewById(R.id.dialog_groupdateselect_AMorPMdown);
		hourup = (ImageView) findViewById(R.id.dialog_groupdateselect_Hourup);
		hourdown = (ImageView) findViewById(R.id.dialog_groupdateselect_Hourdown);
		minup = (ImageView) findViewById(R.id.dialog_groupdateselect_Minup);
		mindown = (ImageView) findViewById(R.id.dialog_groupdateselect_Mindown);
		tvamorpm = (TextView) findViewById(R.id.dialog_groupdateselect_AMPM);
		tvhour = (EditText) findViewById(R.id.dialog_groupdateselect_Edit_Hour);
		plus = (ImageView) findViewById(R.id.dialog_groupdateselect_Plusbt);
		tvhour.addTextChangedListener(hourTextWatcher);
		tvmin = (EditText) findViewById(R.id.dialog_groupdateselect_Edit_min);
		tvmin.addTextChangedListener(minTextWatcher);
		amorpmup.setOnClickListener(this);
		amorpmdown.setOnClickListener(this);
		hourup.setOnClickListener(this);
		hourdown.setOnClickListener(this);
		minup.setOnClickListener(this);
		mindown.setOnClickListener(this);
		ok.setOnClickListener(this);
		gridview.setOnItemClickListener(this);
		plus.setOnClickListener(this);
		tvhour.setText(String.valueOf(hour));
		tvmin.setText(String.valueOf(min));
		scheduletable = new Hashtable<>();

		listviewArray = new ArrayList<>();
		int leadtime=this.data.getP_lead_time();
		if(leadtime==0)
			leadtime=60;
		listadapter = new Adapter_Dialog_DateList(mContext, this,
				R.layout.item_groupdateselectlist, this.listviewArray,
				leadtime);

		listview.setAdapter(listadapter);
		setListViewHeightBasedOnChildren(listview);
		setHashTable();

		initTempArrayList();

	}

	public void pushdeletelist(int position) {
		listviewArray.remove(position);
		adapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(listview);
		pushplusbt = false;
	}

	public void initTempArrayList() {
		nullarray = true;
		listviewArray.clear();
		for (int i = 0; i < 3; i++) {
			listviewArray.add(null);
		}

		listadapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(listview);
	}

	public void setHashTable() {
		for (int i = 0; i < group.size(); i++) {
			Group groupitem = group.get(i);
			int day = (groupitem.getG_start_year() * 10000)
					+ (groupitem.getG_start_month() * 100)
					+ groupitem.getG_start_day();
			String key = String.valueOf(day);
			if (scheduletable.containsKey(key)) {
				scheduletable.get(key).add(groupitem);
			} else {
				scheduletable.put(key, new ArrayList<Group>());
				scheduletable.get(key).add(groupitem);
			}
		}
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
			arrData.add(new CalData((i + 1), mCal.get(Calendar.DAY_OF_WEEK),
					year, month));
		}
		adapter = new DateAdapter(mContext, arrData, todayyear, todaymonth,
				todaydate, afterweektodayvalue);
		gridview.setAdapter(adapter);
		title.setText(year + "년 " + month + "월");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.dialog_groupdateselect_next) {
			pushplusbt = false;
			selpo = -1;
			if (thisMonth < 12) {
				thisMonth++;
				setCalendarDate(thisYear, thisMonth);
			} else {
				thisYear++;
				thisMonth = 1;
				setCalendarDate(thisYear, thisMonth);
			}

		} else if (v.getId() == R.id.dialog_groupdateselect_prev) {
			pushplusbt = false;
			selpo = -1;
			if (thisMonth > 1) {
				thisMonth--;
				setCalendarDate(thisYear, thisMonth);
				initTempArrayList();
			} else {
				thisYear--;
				thisMonth = 12;
				setCalendarDate(thisYear, thisMonth);
				initTempArrayList();
			}
		} else if (v.getId() == R.id.dialog_groupdateselect_AMorPMdown
				|| v.getId() == R.id.dialog_groupdateselect_AMorPMup) {
			amorpm = !amorpm;
			setAmPmText();

		}

		else if (v.getId() == R.id.dialog_groupdateselect_Hourup) {
			hour++;
			if (hour > 12) {
				hour = 1;
			}
			tvhour.setText(String.valueOf(hour));
		} else if (v.getId() == R.id.dialog_groupdateselect_Hourdown) {
			hour--;
			if (hour < 1) {
				hour = 12;
			}
			tvhour.setText(String.valueOf(hour));

		} else if (v.getId() == R.id.dialog_groupdateselect_Minup) {
			min++;
			if (min > 59) {
				min = 0;
			}
			tvmin.setText(String.valueOf(min));

		} else if (v.getId() == R.id.dialog_groupdateselect_Mindown) {

			min--;
			if (min < 0) {
				min = 59;
			}
			tvmin.setText(String.valueOf(min));
		} else if (v.getId() == R.id.dialog_groupdateselect_Plusbt) {
			if(selecteddate)
			{
			if (!pushplusbt) {
				pushplusbt = true;
				if (selpo != -1) {
					int leadtime=data.getP_lead_time();
					if(leadtime==0)
						leadtime=60;
					int endhour = ((hour * 60) + min + leadtime) / 60;
					int endmin = ((hour * 60) + min + leadtime) % 60;
					int temphour = this.hour;
					if (!amorpm) {
						temphour += 12;
						endhour +=12;
					}
					temp = new Group(0, data.getP_id(), app.getId(), "", 0, 0,
							"", 3, todayyear, todaymonth, todaydate, endyear,
							endmonth, enddate, selectyear, selectmonth,
							selectday, temphour, min, endhour, endmin, "",
							data.getP_name(), "","");
					if (nullarray) {
						listviewArray.clear();
						listviewArray.add(temp);
						listadapter.notifyDataSetChanged();
						setListViewHeightBasedOnChildren(listview);
					} else {
						listviewArray.add(temp);
						listadapter.notifyDataSetChanged();
						setListViewHeightBasedOnChildren(listview);
					}
				}
			}
			}
			else
			{
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(mContext,
						"날짜를 먼저 선택해주세요.");
				dialog.show();
			}
		} else if (v.getId() == R.id.dialog_groupdateselect_ok) {
			if (pushplusbt == true) {
				((CreateGroup_View) mContext).getDateInfo(temp);
				this.dismiss();
			} else {
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(mContext,
						"모임 시간을 추가하셔야 가능합니다.");
				dialog.show();
			}
		}

	}

	public void setAmPmText() {
		if (amorpm) {
			tvamorpm.setText("오전");
		} else {
			tvamorpm.setText("오후");
		}
	}

	private TextWatcher minTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s.length() == 0) {

			} else if (s.toString().matches(timeregex)) {
				min = Integer.parseInt(s.toString());
			}

			else {
				tvmin.setText(String.valueOf(min));
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(mContext,
						"잘못된 입력입니다.");
				dialog.show();

			}

		}

	};
	private TextWatcher hourTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s.length() == 0) {

			} else if (s.toString().matches(timeregex)) {
				hour = Integer.parseInt(s.toString());
			}

			else {
				tvhour.setText(String.valueOf(hour));
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(mContext,
						"잘못된 입력입니다.");
				dialog.show();

			}

		}

	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		CalData data = (CalData) adapter.getItem(arg2);
		if (data.getisPossibleCreate()) {
			selecteddate=true;
			pushplusbt = false;
			if (selpo != -1) {
				arg0.getChildAt(selpo).setBackgroundColor(
						Color.parseColor("#FFFFFF"));
			}
			arg0.getChildAt(arg2).setBackgroundColor(
					Color.parseColor("#FFE57F"));
			selpo = arg2;
			selectmonth = data.getMonth();
			selectyear = data.getYear();
			selectday = data.getDay();
			mCal.set(selectyear, selectmonth - 1, selectday);
			mCal.add(Calendar.DATE, -7);
			endyear = mCal.get(Calendar.YEAR);
			endmonth = mCal.get(Calendar.MONTH) + 1;
			enddate = mCal.get(Calendar.DATE);
			int intkey = (data.getYear() * 10000) + (data.getMonth() * 100)
					+ data.getDay();
			if (scheduletable.containsKey(String.valueOf(intkey))) {
				nullarray = false;
				listviewArray.clear();
				listviewArray.addAll(scheduletable.get(String.valueOf(intkey)));
				listadapter.notifyDataSetChanged();
				setListViewHeightBasedOnChildren(listview);
			} else {
				initTempArrayList();
			}
		} else {
			selecteddate=false;
			Dialog_BaseDialog dialog = new Dialog_BaseDialog(mContext,
					"선택 불가능한 날짜입니다.");
			dialog.show();
		}
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		Adapter_Dialog_DateList listAdapter = (Adapter_Dialog_DateList) listView
				.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}
}
