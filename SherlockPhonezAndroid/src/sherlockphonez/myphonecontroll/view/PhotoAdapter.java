package sherlockphonez.myphonecontroll.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sherlockphonez.R;
import com.example.sherlockphonez.VolleySingleton;


public class PhotoAdapter extends ArrayAdapter<Photo>{
	private ArrayList<Photo> items=new ArrayList<Photo>();
	private Context context;
	private RequestQueue mRequestQueue;
	private Photo temp;
	private ImageLoader mImageLoader;
	private boolean isCanceled=false;


	public PhotoAdapter(Context context, int resource, ArrayList<Photo> items){
		super(context, resource, items);
		// TODO Auto-generated constructor stub
		this.items=items;
		this.context=context;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();


	}   

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Photo getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		NetworkImageView avatar;
		boolean isChecked;
		if(isCanceled){
			CheckedTextView c=(CheckedTextView)convertView.findViewById(R.id.rl_photo1);
			c.setChecked(false);
		}else{
		if(convertView == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.photo_adapter, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.image = (NetworkImageView)convertView.findViewById(R.id.iv_photo1);
			holder.isChecked = false;
			convertView.setTag(holder);

		}else{
			ViewHolder viewHolder = (ViewHolder)convertView.getTag();
			avatar=viewHolder.image;
			isChecked=viewHolder.isChecked;
		}

		avatar = (NetworkImageView)convertView.findViewById(R.id.iv_photo1);
		avatar.setImageUrl(getItem(position).url,mImageLoader);
		isChecked=getItem(position).isChecked();
		}
		return convertView;      
	}
	
	public void setIsCanceled(boolean isCanceled){
		this.isCanceled=isCanceled;
	}

	static class ViewHolder{
		public NetworkImageView image;
		public boolean isChecked;
	}
}
