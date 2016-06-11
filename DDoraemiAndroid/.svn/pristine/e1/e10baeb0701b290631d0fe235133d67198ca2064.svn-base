package ddoraemi.adminmodehome.view;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.home.model.Group;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class Adapter_AdminGroupList extends ArrayAdapter<Group> {

	ArrayList<Group> items;
	Context context;
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	Calendar cal;

	long now_day;

	public Adapter_AdminGroupList(Context context, int resource,
			ArrayList<Group> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController) getContext().getApplicationContext();
		cal = Calendar.getInstance();
		now_day = cal.getTimeInMillis();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		NetworkImageView avatar;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_grouplist, null);
		}
		Group p = items.get(position);
		if (p != null) {
			TextView p_name = (TextView) v.findViewById(R.id.tv_group_p_name);
			TextView g_name = (TextView) v.findViewById(R.id.tv_group_name);
			TextView g_date = (TextView) v.findViewById(R.id.tv_group_date);
			TextView g_addr = (TextView) v.findViewById(R.id.tv_group_addr);
			TextView g_persons = (TextView) v
					.findViewById(R.id.tv_group_persons);
			TextView g_dday = (TextView) v.findViewById(R.id.tv_group_dday);

			if (p_name != null) {
				p_name.setText(p.getP_name());
			}
			if (g_name != null) {
				g_name.setText(p.getG_name());
			}
			if (g_date != null) {
				g_date.setText(String.valueOf(p.getG_start_year()) + "-"
						+ String.valueOf(p.getG_start_month() + "-")
						+ String.valueOf(p.getG_start_day()) + " "
						+ String.valueOf(p.getG_start_hour()) + "시 "
						+ String.valueOf(p.getG_start_minute()) + "분 - "
						+ String.valueOf(p.getG_end_hour()) + "시 "
						+ String.valueOf(p.getG_end_minute()) + "분");
			}
			if (g_date != null) {
				g_addr.setText(p.getP_addr());
			}
			if (g_persons != null) {
				g_persons.setText(String.valueOf(p.getG_persons()) + "명 참석");
			}
			if (g_dday != null) {
				if (p.getG_status() == 1) {
					int d_day = p.getD_day();
					if (d_day == 0) {

						g_dday.setText("D-DAY");
					} else {
						g_dday.setText("D-" + String.valueOf(d_day));
					}

				}else if(p.getG_status() == 2)
				{
					g_dday.setText("확정");
					g_dday.setTextColor(Color.parseColor("#ae0037"));
				}
			}
			avatar = (NetworkImageView) v.findViewById(R.id.iv_group_img);
			avatar.setDefaultImageResId(R.drawable.img_list_default);
			avatar.setImageUrl(
					app.getServerIp() + "/program_photo/"
							+ getItem(position).getP_photo_url(), mImageLoader);
		}
		return v;
	}

}
