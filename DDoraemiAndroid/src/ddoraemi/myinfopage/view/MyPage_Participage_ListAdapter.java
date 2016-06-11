package ddoraemi.myinfopage.view;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
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

public class MyPage_Participage_ListAdapter extends ArrayAdapter<Group>{

	ArrayList<Group> items;
	Context context;
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	public MyPage_Participage_ListAdapter(Context context, int resource, ArrayList<Group> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)getContext().getApplicationContext();
	}

	public View getView(int position,View convertView, ViewGroup parent){
		View v = convertView;
		NetworkImageView avatar;
		if(v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
			v = vi.inflate(R.layout.item_mypage_joininggroup,null);
		}
		Group p = items.get(position);
		if(p!=null){
			TextView p_name = (TextView) v.findViewById(R.id.item_mypage_joininggroup_programname);
			TextView g_name = (TextView) v.findViewById(R.id.item_mypage_joininggroup_groupname);
			TextView g_date = (TextView) v.findViewById(R.id.item_mypage_joininggroup_time);
			TextView g_persons = (TextView) v.findViewById(R.id.item_mypage_joininggroup_countcurpersons);

			if(p_name != null){
				p_name.setText(p.getP_name());
			}
			if(g_name != null){
				g_name.setText(p.getG_name());
			}
			if(g_date != null){
				g_date.setText(String.valueOf(p.getG_start_year())+"-"+String.valueOf(p.getG_start_month()+"-")
						+String.valueOf(p.getG_start_day())+" "+String.valueOf(p.getG_start_hour())+"시 "+String.valueOf(p.getG_start_minute())+"분 - "+String.valueOf(p.getG_end_hour())+"시 "+String.valueOf(p.getG_end_minute())+"분");
			}
			
			if(g_persons != null){
				g_persons.setText(String.valueOf(p.getG_persons())+"명 참석");
			}
			
			avatar = (NetworkImageView)v.findViewById(R.id.item_mypage_joininggroup_profileIMG);
            avatar.setDefaultImageResId(R.drawable.img_list_default);
			avatar.setImageUrl(app.getServerIp()+"/program_photo/"+p.getP_photo_url(),mImageLoader);
		}
		return v;
	}

}
