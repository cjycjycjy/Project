package sherlockphonez.myphonecontroll.view;

import java.util.ArrayList;




//import aaa.ScreenViewFlipper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;




import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sherlockphonez.R;
import com.example.sherlockphonez.VolleySingleton;

public class ImagePopup extends Activity {
	ImageView iv;
	Bitmap bm;
	ArrayList<Photo> photolist;
	ScreenViewFlipper flipper;
	int index=0;
	float downX;
	float upX;
	
	private TextView tv_photo_time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.popup_image);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		Intent intent = getIntent();
		index = intent.getIntExtra("index", -1);
		photolist = (ArrayList<Photo>)getIntent().getSerializableExtra("photolist");
		
		flipper = new ScreenViewFlipper(this,photolist,index);
		setContentView(flipper, params);
		
	}
	
	public class ScreenViewFlipper extends LinearLayout implements OnTouchListener ,OnClickListener{	
		private NetworkImageView[] views;
		private ViewFlipper flipper;
		private float downX;
		private float upX;	
		private int index;
		private Context context;
		private ArrayList<Photo> p;
		private ImageLoader mImageLoader;
		private RequestQueue mRequestQueue;
		private LayoutInflater inflater;
			
		public ScreenViewFlipper(Context context, ArrayList<Photo> photolist, int index) {
			super(context);
			this.context=context;
			this.setBackgroundColor(0xffbfbfbf);
			this.p=photolist;
			this.index=index;
			mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
			mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.screenview, this, true);
			
			findViewById(R.id.btn_cancel_pop).setOnClickListener(this);
			
			flipper = (ViewFlipper) findViewById(R.id.flipper);
			flipper.setOnTouchListener(this);
			tv_photo_time = (TextView)findViewById(R.id.tv_photo_time);
			setFlipper();
		}
		
		public void setFlipper(){	
			views = new NetworkImageView[p.size()];
			for(int i=0; i<p.size(); i++){
				views[i] = new NetworkImageView(this.context);
				views[i].setImageUrl(p.get(i).getUrl(), mImageLoader);
				flipper.addView(views[i]);
			}
			flipper.setDisplayedChild(index);
			String[] temp = p.get(index).getUrl().split("/");
			
			tv_photo_time.setText("촬영된 시각: "+p.get(index).getCamera_time());
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == R.id.btn_cancel_pop){
				flipper.removeAllViews();
				finish();
			}
		}
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (v != flipper)
				return false;
			if (event.getAction() == MotionEvent.ACTION_DOWN ) {
				if(event.getY()<250){
					return false;
				}
				downX = event.getX();
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				upX = event.getX();
				float downY = event.getY();
				Log.i("a", "dX:"+downX+" uX: "+upX);
				Log.i("sdf", "Y: "+downY);
				if (upX < downX && index != p.size()-1) { // in case of right direction
					flipper.setInAnimation(AnimationUtils.loadAnimation(
							getContext(), R.anim.push_left_in));
					flipper.setOutAnimation(AnimationUtils.loadAnimation(
							getContext(), R.anim.push_left_out));
					String[] temp = p.get(index+1).getUrl().split("/");
					
					tv_photo_time.setText("촬영된 시각: "+p.get(index+1).getCamera_time());
					flipper.showNext();
					
					index++;
					
				} else if (upX > downX && index != 0) { // in case of left direction
					flipper.setInAnimation(AnimationUtils.loadAnimation(
							getContext(), R.anim.push_right_in));
					flipper.setOutAnimation(AnimationUtils.loadAnimation(
							getContext(), R.anim.push_right_out));
					String[] temp = p.get(index-1).getUrl().split("/");
					tv_photo_time.setText("촬영된 시각: "+p.get(index-1).getCamera_time());
					flipper.showPrevious();				
					index--;
					
				}
			}
			return true;
		}
	}
}

