package ddoraemi.adminmodehome.view;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;

import ddoraemi.adminmodehome.presenter.AdminHomeMainPresenter;
import ddoraemi.adminmodehome.presenter.AdminHomeMainPresenterInterface;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;

public class AdminHomeMainView extends Fragment implements
		AdminHomeMainViewInterface, ViewPager.OnPageChangeListener {
	int p_num;
	AdminHomeMainPresenterInterface presenter;
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private PagerAdapter adapter;
	ProgressDialog progressdialog;
	int curpage;

	public AdminHomeMainView(int p_num) {
		this.p_num = p_num;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_adminhome, null);
		init(v);
		return v;
	}

	private void init(View v) {
		presenter = new AdminHomeMainPresenter(this);
		pager = (ViewPager) v.findViewById(R.id.fragment_adminhome_pager);
		tabs = (PagerSlidingTabStrip) v
				.findViewById(R.id.fragment_adminhome_tabs);
		progressdialog = new ProgressDialog(getActivity());
		progressdialog.setMessage(getActivity().getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		presenter.validatecredential("start", p_num);
		tabs.setOnPageChangeListener(this);

	}

	public class PagerAdapter extends FragmentPagerAdapter {

		private String[] Title = { getString(R.string.admin_schedule),
				getString(R.string.admin_group),
				getString(R.string.admin_programinfo),
				getString(R.string.afterword) };
		ProgramData programinfo;
		ArrayList<Group> groups;
		ArrayList<Afterword> afterword;
		Hashtable<String, ArrayList<Group>> groupstable;

		public PagerAdapter(FragmentManager fm, ProgramData programinfo,
				ArrayList<Group> groups, ArrayList<Afterword> afterword,
				Hashtable<String, ArrayList<Group>> groupstable) {
			super(fm);
			this.programinfo = programinfo;
			this.groups = groups;
			this.afterword = afterword;
			this.groupstable = groupstable;

		}

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
				return new AdminScheduleView(groupstable);
			} else if (position == 1) {
				return new AdminGroupInfoView(groups);
			} else if (position == 2) {
				return new AdminProgramInfoView(programinfo);
			} else {
				return new AdminAfterwordView(afterword);
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return Title[0];
			case 1:
				return Title[1];
			case 2:
				return Title[2];
			case 3:
			default:
				return Title[3];
			}
		}
		public ProgramData getPrograminfo() {
			return programinfo;
		}

		public void setPrograminfo(ProgramData programinfo) {
			this.programinfo = programinfo;
		}

		public ArrayList<Group> getGroups() {
			return groups;
		}

		public void setGroups(ArrayList<Group> groups) {
			this.groups.clear(); 
			this.groups.addAll(groups);
		}

		public ArrayList<Afterword> getAfterword() {
			return afterword;
		}

		public void setAfterword(ArrayList<Afterword> afterword) {
			this.afterword.clear(); 
			this.afterword.addAll(afterword);
		}

		public Hashtable<String, ArrayList<Group>> getGroupstable() {
			return groupstable;
		}

		public void setGroupstable(Hashtable<String, ArrayList<Group>> groupstable) {
			this.groupstable.clear();
			this.groupstable.putAll(groupstable);
		}

	}

	@Override
	public void setFragment(ProgramData data, ArrayList<Group> groups,
			ArrayList<Afterword> afterword,
			Hashtable<String, ArrayList<Group>> groupstable) {
		adapter = new PagerAdapter(getChildFragmentManager(), data, groups,
				afterword, groupstable);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(4);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		tabs.setShouldExpand(true);
		progressdialog.dismiss();
	}

	@Override
	public void showFailureDialog() {
		// TODO Auto-generated method stub
		if (progressdialog != null) {
			progressdialog.dismiss();
		}
		Dialog_BaseDialog dialog = new Dialog_BaseDialog(getActivity(),
				getString(R.string.failed_dialog));
		dialog.show();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		curpage = arg0;

	}

	@Override
	public void renewFragment(ProgramData data, ArrayList<Group> groups,
			ArrayList<Afterword> afterword,
			Hashtable<String, ArrayList<Group>> groupstable) {
		// TODO Auto-generated method stub
		adapter.setAfterword(afterword);
		adapter.setGroups(groups);
		adapter.setGroupstable(groupstable);
		adapter.setPrograminfo(data);
		adapter.notifyDataSetChanged();
		pager.setCurrentItem(curpage);
		
	}

	@Override
	public void renew() {
		presenter.validatecredential("renew", p_num);
		
	}
}
