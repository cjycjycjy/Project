package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.myinfopage.model.CustomGallery;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class Adapter_NetworkCustomGallery extends BaseAdapter{
	private Context mContext;
	private LayoutInflater infalter;
	private ArrayList<CustomGallery> data = new ArrayList<CustomGallery>();
	ImageLoader imageLoader;
	AppController app;
	private ArrayList<String> items;
	int layout;
	private boolean isActionMultiplePick;

	public Adapter_NetworkCustomGallery(Context c,int layout, ArrayList<String> items) {
		infalter = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = c;
		this.layout=layout;
		this.imageLoader = VolleySingleton.getInstance(mContext).getImageLoader();
		this.items=items;
		app=(AppController)mContext.getApplicationContext();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public String getItem(int position) {
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
			LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.item_afterwordpiclist, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.image = (NetworkImageView)view.findViewById(R.id.iv_photo);
			view.setTag(holder);

		}else
			view = convertView; 
		avatar = (NetworkImageView)view.findViewById(R.id.item_modifypiclist_imageview);
		avatar.setImageUrl(app.getServerIp()+"/afterword_photo/"+items.get(position),imageLoader);
		
		return view;      
	}


	static class ViewHolder{
		public NetworkImageView image;
	}

	
}
