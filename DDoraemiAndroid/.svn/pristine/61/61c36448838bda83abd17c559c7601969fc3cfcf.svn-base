package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class DetailedGroupInfo_personGridAdapter extends ArrayAdapter<String>{

	private ArrayList<String> url;
	private Context context;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	AppController app;
	
	
	public DetailedGroupInfo_personGridAdapter(Context context, int resource, ArrayList<String> items) {
		super(context, resource, items);
		// TODO Auto-generated constructor stub
		this.url=items;
		this.context=context;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)getContext().getApplicationContext();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return url.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return url.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		NetworkImageView avatar;
		View view = convertView;
		if(view == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.item_detailgroupinfo_persongrid, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.image = (NetworkImageView)view.findViewById(R.id.iv_photo);
			view.setTag(holder);

		}else
			view = convertView; 
		avatar = (NetworkImageView)view.findViewById(R.id.user_photo);
		avatar.setImageUrl(app.getServerIp()+"/user_photo/"+url.get(position),mImageLoader);
		
		return view;      
	}


	static class ViewHolder{
		public NetworkImageView image;
	}



}