package ddoraemi.home.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStrip.IconTabProvider;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_groupView;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.home.presenter.Home_MainViewPresenter;
import ddoraemi.home.presenter.Home_MainViewPresenterInterface;
import ddoraemi.start.R;

public class Home_MainView extends Fragment implements Home_MainViewInterface {
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private PagerAdapter adapter;
	private Home_MainViewPresenterInterface presenter;
	boolean is_renew;
	String u_id;
	AppController app;
	ArrayList<ProgramData> data;
	ArrayList<Group> group;
	public static Home_MainView mcontext;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		init();
		presenter.validateCredentials(u_id, "getdata", null);
		return v;
	}

	public Home_MainViewPresenterInterface getPresenter() {
		return presenter;
	}

	public void init() {
		presenter = new Home_MainViewPresenter(this);
		data = new ArrayList<>();
		group = new ArrayList<>();
		app = (AppController) getContext().getApplicationContext();
		u_id = app.getUser().getU_id();
		mcontext = this;
	}

	@Override
	public void setViewPager(View v, ArrayList<ProgramData> data, ArrayList<Group> group) {
		this.data = data;
		this.group = group;
		pager = (ViewPager) v.findViewById(R.id.fragment_home_pager);
		tabs = (PagerSlidingTabStrip) v.findViewById(R.id.fragment_home_tabs);
		adapter = new PagerAdapter(getChildFragmentManager(), data, group);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(2);
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
				getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		tabs.setShouldExpand(true);
	}

	public class PagerAdapter extends FragmentPagerAdapter {
		private String[] Title = { "추천 체험 활동", "진행 중인 모임" };
		ArrayList<ProgramData> recommenddata;
		ArrayList<Group> group;

		public PagerAdapter(FragmentManager fm, ArrayList<ProgramData> data, ArrayList<Group> group) {
			super(fm);
			this.recommenddata = new ArrayList<ProgramData>();
			this.recommenddata.addAll(data);
			this.group = new ArrayList<Group>();
			this.group.addAll(group);
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
				return new Home_RecommendView(recommenddata);
			} else {
				return new Home_AvailableGroupView(group);
			}

		}

	}

	@Override
	public void setProgramList(ArrayList<ProgramData> data, ArrayList<Group> group) {
		// TODO Auto-generated method stub
		if (!is_renew)
			setViewPager(getView(), data, group);
		else {
			this.group = new ArrayList<>();
			this.group.addAll(group);
			List<Fragment> fragments = getChildFragmentManager().getFragments();
			if (fragments != null) {
				for (Fragment fragment : fragments) {
					if (fragment instanceof Home_AvailableGroupView)
						((Home_AvailableGroupView) fragment).renewList(group);
					else if (fragment instanceof Home_RecommendView)
						((Home_RecommendView) fragment).renew(data);
				}
			}
			is_renew = false;
		}

	}

	public void renewList() {
		is_renew = true;
		presenter.validateCredentials(u_id, "getdata", null);
	}

}
