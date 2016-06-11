package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;





import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class DetailedItemInfo_PictureListAdapter extends ArrayAdapter<ChallengePicture>{

	private ArrayList<ChallengePicture> items;
	private Context context;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	AppController app;
	private ChallengePicture temp;
	public DetailedItemInfo_PictureListAdapter(Context context, int resource, ArrayList<ChallengePicture> items) {
		super(context, resource, items);
		// TODO Auto-generated constructor stub
		this.items=items;
		this.context=context;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)getContext().getApplicationContext();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public ChallengePicture getItem(int position) {
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
		View view = convertView;
		if(view == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.item_challengepicturelist, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.image = (NetworkImageView)view.findViewById(R.id.iv_photo);
			view.setTag(holder);

		}else
			view = convertView; 
		avatar = (NetworkImageView)view.findViewById(R.id.iv_photo);
		avatar.setImageUrl(app.getServerIp()+"/afterword_photo/"+items.get(position).getPhoto_url(),mImageLoader);
		
		return view;      
	}


	static class ViewHolder{
		public NetworkImageView image;
	}
}
