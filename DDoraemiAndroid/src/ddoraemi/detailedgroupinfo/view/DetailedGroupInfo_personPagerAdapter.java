package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import ddoraemi.home.model.Group;
import ddoraemi.start.R;
import ddoraemi.start.User;

public class DetailedGroupInfo_personPagerAdapter extends PagerAdapter {
	Context context;
	ArrayList<User> Users;
	DetailedGroupInfo_InfoView m_view;
	int permission;
	boolean ismaster;
	public DetailedGroupInfo_personPagerAdapter(Context context, ArrayList<User> Users, int permission,boolean ismaster) {
		this.context = context;
		this.Users = Users;
		this.permission = permission;
		this.ismaster = ismaster;
	}

	@Override
	public int getCount() {
		if (Users.size() == 0) {
			return 0;
		}
		return ((Users.size() - 1) / 4) + 1;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((GridView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final int po = position;
		CalWidthGridView gridview = new CalWidthGridView(context);
		ArrayList<String> temparr = new ArrayList<>();
		int count = 0;
		for (int i = po * 4; i < Users.size(); i++) {
			if (count == 4)
				break;
			temparr.add(Users.get(i).getU_photo_url());
			count++;
		}
		DetailedGroupInfo_personGridAdapter adapter = new DetailedGroupInfo_personGridAdapter(context,
				R.layout.item_detailgroupinfo_persongrid, temparr);
		gridview.setNumColumns(4);
		gridview.setSelector(new StateListDrawable());
		gridview.setRotationY(180);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (permission == 2 || ismaster) {
					Intent intent = new Intent(context, JoinPersonListView.class);
					intent.putExtra("user", Users);
					context.startActivity(intent);
				}
			}
		});
		((ViewPager) container).addView(gridview, 0);
		return gridview;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((ListView) object);
	}

	@Override
	public int getItemPosition(Object item) {
		return POSITION_NONE;
	}

}