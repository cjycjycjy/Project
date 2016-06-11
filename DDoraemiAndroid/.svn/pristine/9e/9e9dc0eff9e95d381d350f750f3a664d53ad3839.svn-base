package ddoraemi.search.view;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.appcontroller.AppController;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.search.model.MySearchListHelper;
import ddoraemi.start.R;

public class SearchView extends FragmentActivity implements
		SearchViewInterface, OnFocusChangeListener, OnTouchListener,
		OnItemClickListener, OnClickListener {
	String recentstr;
	private PagerSlidingTabStrip tabs;
	EditText et_search;
	LinearLayout mysearchLayout;
	MySearchListHelper helper;
	SQLiteDatabase db;
	TextView searchbt;
	AppController app;
	ArrayList<String> mysearchtext;
	ListView listview;
	Adapter_SearchViewMylist adapter;
	ArrayList<ProgramData> dataarray;
	ArrayList<Group> grouparray;
	private ViewPager pager;
	private PagerAdapter pageradapter;
	RelativeLayout none;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		app = (AppController) getApplicationContext();
		searchbt = (TextView) findViewById(R.id.activity_search_searchbt);
		et_search = (EditText) findViewById(R.id.activity_search_edit);
		mysearchLayout = (LinearLayout) findViewById(R.id.activity_search_mysearchlistlayout);
		listview = (ListView) findViewById(R.id.activity_search_mysearchlistview);
		searchbt.setOnClickListener(this);
		et_search.setOnFocusChangeListener(this);
		none=(RelativeLayout)findViewById(R.id.activity_search_nonsearchlayout);
		FrameLayout touchInterceptor = (FrameLayout) findViewById(R.id.touchInterceptor);
		touchInterceptor.setOnTouchListener(this);
		helper = new MySearchListHelper(this);
		try {
			db = helper.getWritableDatabase();
		} catch (SQLException e) {
			db = helper.getReadableDatabase();
		}
		mysearchtext = helper.selectMylist(db, app.getId());
		if(mysearchtext.size()!=0)
		{
			none.setVisibility(View.GONE);
		}
		adapter = new Adapter_SearchViewMylist(this, R.layout.item_mylist,
				mysearchtext);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
		dataarray = new ArrayList<>();
		grouparray = new ArrayList<>();
		setViewPager();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if (hasFocus == false) {
			mysearchLayout.setVisibility(View.GONE);
		} else {
			mysearchLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (et_search.isFocused()) {
				Rect outRect = new Rect();
				et_search.getGlobalVisibleRect(outRect);
				if (!outRect.contains((int) event.getRawX(),
						(int) event.getRawY())) {
					et_search.clearFocus();
					InputMethodManager imm = (InputMethodManager) v
							.getContext().getSystemService(
									Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (et_search.isFocused()) {
			et_search.clearFocus();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void insertDB() {
		// TODO Auto-generated method stub
		none.setVisibility(View.GONE);
		et_search.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
		String searchtext = et_search.getText().toString();
		recentstr = searchtext;
		et_search.setText("");
		if (searchtext.length() > 0) {
			String thistime = String.valueOf(System.currentTimeMillis());
			helper.insert(db, app.getId(), searchtext, thistime);
		}
		mysearchtext.clear();
		mysearchtext.addAll(helper.selectMylist(db, app.getId()));
		adapter.notifyDataSetChanged();
		getSearchList(searchtext);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.activity_search_searchbt) {
			insertDB();
		}

	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		if (arg0 != 0) {
			getSearchList(recentstr);
		}
	}

	public void getSearchList(final String str) {
		final ProgressDialog progressdialog = new ProgressDialog(this);
		progressdialog.setMessage(getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retofit = restAdapter.create(Retrofit.class);
					retofit.getSearch(str, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject data, Response response) {
							dataarray.clear();
							grouparray.clear();
							JsonArray program = data.getAsJsonArray("program");
							JsonArray group = data.getAsJsonArray("gathering");

							for (int i = 0; i < program.size(); i++) {
								JsonObject dataobj = (JsonObject) program
										.get(i);
								int p_id = (dataobj.get("p_id")).getAsInt();
								String p_name = (dataobj.get("p_name"))
										.getAsString();
								String p_town_name = (dataobj
										.get("p_town_name")).getAsString();

								int p_min_persons = (dataobj
										.get("p_min_persons")).getAsInt();
								int p_max_persons = (dataobj
										.get("p_max_persons")).getAsInt();
								int p_lead_time = (dataobj.get("p_lead_time"))
										.getAsInt();

								String p_note = (dataobj.get("p_note"))
										.getAsString();
								String p_addr_1 = (dataobj.get("p_addr_1"))
										.getAsString();
								String p_addr_2 = (dataobj.get("p_addr_2"))
										.getAsString();
								String p_addr_3 = (dataobj.get("p_addr_3"))
										.getAsString();
								String p_addr_4 = (dataobj.get("p_addr_4"))
										.getAsString();
								String p_addr_5 = (dataobj.get("p_addr_5"))
										.getAsString();
								String p_addr_6 = (dataobj.get("p_addr_6"))
										.getAsString();

								int a_id = (dataobj.get("a_id")).getAsInt();
								String p_url = (dataobj.get("p_url"))
										.getAsString();

								String p_info = (dataobj.get("p_info"))
										.getAsString();
								String e_name = (dataobj.get("e_name"))
										.getAsString();
								String p_cost_adult = (dataobj
										.get("p_cost_adult")).getAsString();
								String p_cost_kid = (dataobj.get("p_cost_kid"))
										.getAsString();

								String p_available_season = (dataobj
										.get("p_available_season"))
										.getAsString();

								String p_preparation_item = (dataobj
										.get("p_preparation_item"))
										.getAsString();

								String p_tel = (dataobj.get("p_tel"))
										.getAsString();

								int p_is_available_overnight = (dataobj
										.get("p_is_available_overnight"))
										.getAsInt();
								long p_grade = (dataobj.get("p_grade"))
										.getAsLong();
								String p_photo_url = (dataobj
										.get("p_photo_url").getAsString());
								JsonArray p_curriculum = (dataobj
										.get("p_curriculum").getAsJsonArray());

								ArrayList<String> cur_list = new ArrayList<>();

								for (int k = 0; k < p_curriculum.size(); k++) {
									JsonObject cur = (JsonObject) p_curriculum
											.get(k);
									String cur_info = cur.get("cur")
											.getAsString();
									cur_list.add(cur_info);
								}
								dataarray.add(new ProgramData(p_id, p_name,
										p_info, e_name, p_cost_adult,
										p_cost_kid, p_min_persons,
										p_max_persons, p_available_season,
										p_lead_time, p_preparation_item,
										p_town_name, p_addr_1, p_addr_2,
										p_addr_3, p_addr_4, p_addr_5, p_addr_6,
										p_tel, p_url, p_is_available_overnight,
										p_photo_url, cur_list, p_note, p_grade,
										a_id));

							}

							for (int i = 0; i < group.size(); i++) {
								JsonObject dataobj = (JsonObject) group.get(i);
								int p_id = (dataobj.get("p_id")).getAsInt();
								String p_name = (dataobj.get("p_name"))
										.getAsString();
								String p_addr = (dataobj.get("p_addr"))
										.getAsString();
								int g_id = (dataobj.get("g_id")).getAsInt();
								String g_name = (dataobj.get("g_name"))
										.getAsString();

								String g_info = (dataobj.get("g_info"))
										.getAsString();
								int g_status = (dataobj.get("g_status"))
										.getAsInt();
								int g_persons = (dataobj.get("g_persons"))
										.getAsInt();
								String e_name = (dataobj.get("e_name"))
										.getAsString();

								String u_id = (dataobj.get("u_id"))
										.getAsString();

								int g_want_persons = (dataobj
										.get("g_want_persons")).getAsInt();

								int g_open_year = (dataobj.get("g_open_year"))
										.getAsInt();
								int g_open_month = (dataobj.get("g_open_month"))
										.getAsInt();
								int g_open_day = (dataobj.get("g_open_day"))
										.getAsInt();
								int g_close_year = (dataobj.get("g_close_year"))
										.getAsInt();
								int g_close_month = (dataobj
										.get("g_close_month")).getAsInt();
								int g_close_day = (dataobj.get("g_close_day"))
										.getAsInt();
								int g_start_year = (dataobj.get("g_start_year"))
										.getAsInt();
								int g_start_month = (dataobj
										.get("g_start_month")).getAsInt();
								int g_start_day = (dataobj.get("g_start_day"))
										.getAsInt();
								int g_start_hour = (dataobj.get("g_start_hour"))
										.getAsInt();
								int g_start_minute = (dataobj
										.get("g_start_minute")).getAsInt();
								int g_end_hour = (dataobj.get("g_end_hour"))
										.getAsInt();
								int g_end_minute = (dataobj.get("g_end_minute"))
										.getAsInt();
								String p_photo_url = (dataobj
										.get("p_photo_url")).getAsString();
								Group temp = new Group(g_id, p_id, u_id,
										g_name, g_persons, g_want_persons,
										g_info, g_status, g_open_year,
										g_open_month, g_open_day, g_close_year,
										g_close_month, g_close_day,
										g_start_year, g_start_month,
										g_start_day, g_start_hour,
										g_start_minute, g_end_hour,
										g_end_minute, e_name, p_name, p_addr,
										p_photo_url);
								grouparray.add(temp);
							}
							pageradapter.notifyDataSetChanged();
							progressdialog.dismiss();
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							Dialog_BaseDialog dialog = new Dialog_BaseDialog(
									SearchView.this,
									getString(R.string.failed_dialog));
							dialog.show();

						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();

					Dialog_BaseDialog dialog = new Dialog_BaseDialog(
							SearchView.this, getString(R.string.failed_dialog));
					dialog.show();

				}
			}
		}).start();
	}

	public class PagerAdapter extends FragmentPagerAdapter {
		private String[] Title = { "체험 활동", "진행 중인 모임" };
		ArrayList<ProgramData> recommenddata;
		ArrayList<Group> group;

		public PagerAdapter(FragmentManager fm, ArrayList<ProgramData> data,
				ArrayList<Group> group) {
			super(fm);
			this.recommenddata = data;
			this.group = group;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return Title[position];
		};

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public int getCount() {
			return Title.length;
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return new Search_ProgramView(recommenddata);
			} else {
				return new Search_GroupView(group);
			}

		}

	}

	public void setViewPager() {
		pager = (ViewPager) findViewById(R.id.activity_search_pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_search_tabs);
		pageradapter = new PagerAdapter(getSupportFragmentManager(), dataarray,
				grouparray);
		pager.setAdapter(pageradapter);
		pager.setOffscreenPageLimit(2);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		tabs.setShouldExpand(true);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String text = adapter.getItem(arg2);
		et_search.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
		recentstr = text;
		et_search.setText("");
		String thistime = String.valueOf(System.currentTimeMillis());
		helper.insert(db, app.getId(), text, thistime);
		mysearchtext.clear();
		mysearchtext.addAll(helper.selectMylist(db, app.getId()));
		adapter.notifyDataSetChanged();
		getSearchList(text);

	}

}
