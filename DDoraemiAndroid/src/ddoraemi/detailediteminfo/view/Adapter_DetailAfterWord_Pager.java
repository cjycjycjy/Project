package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.volley.VolleySingleton;

public class Adapter_DetailAfterWord_Pager extends PagerAdapter {
	Context context;
	ArrayList url;
	AppController app;
	ImageLoader mImageLoader;
	public Adapter_DetailAfterWord_Pager(Context context,ArrayList url){
		this.context=context;
		this.url=url;
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app=(AppController)context.getApplicationContext();
	}
	@Override
	public int getCount() {
		return url.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((ImageView) object);
	}
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final int po=position;
		NetworkImageView imageView = new NetworkImageView(context);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		imageView.setLayoutParams(layoutParams);
		imageView.setImageUrl(app.getServerIp() + "/afterword_photo/"
				+url.get(position).toString(), mImageLoader);
		((ViewPager) container).addView(imageView, 0);
		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((ImageView) object);
	}

}
