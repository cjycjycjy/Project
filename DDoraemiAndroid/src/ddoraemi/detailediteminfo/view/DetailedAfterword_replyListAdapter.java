package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.detailediteminfo.presenter.DetailedAfterword_reply_presenter;
import ddoraemi.detailediteminfo.presenter.DetailedAfterword_reply_presenter_Interface;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_ViewPresenter_Interface;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailedAfterword_replyListAdapter extends ArrayAdapter<AfterwordReply> {
	PopupWindow mDropdown;
	ArrayList<AfterwordReply> items;
	Context context;

	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	

	public DetailedAfterword_replyListAdapter(Context context, int resource, ArrayList<AfterwordReply> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
	}


	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		NetworkImageView avatar;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_afterwordlist_replylist, null);

			app = (AppController) v.getContext().getApplicationContext();
		}
		final AfterwordReply p = items.get(position);
		if (p != null) {
			TextView tv_afterword_reply_u_id = (TextView) v.findViewById(R.id.tv_afterword_reply_u_id);
			final TextView tv_afterword_reply_text = (TextView) v.findViewById(R.id.tv_afterword_reply_text);
			TextView tv_afterword_reply_date = (TextView) v.findViewById(R.id.tv_afterword_reply_date);
			
			final ImageView btn_modifydelete = (ImageView) v.findViewById(R.id.modifydelete);
			if (app.getId().equals(items.get(position).getU_id())) {
				btn_modifydelete.setVisibility(View.VISIBLE);
			} else {
				btn_modifydelete.setVisibility(View.GONE);
			}
			btn_modifydelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					initPopupWindow(btn_modifydelete,tv_afterword_reply_text,p.getA_r_id());
				}
			});

			tv_afterword_reply_u_id.setText(p.getU_id());
			tv_afterword_reply_date.setText(String.valueOf(p.getA_r_year())+"-"+String.valueOf(p.getA_r_month())
					+"-"+String.valueOf(p.getA_r_day()));
			tv_afterword_reply_text.setText(p.getA_r_content());  
			avatar = (NetworkImageView) v.findViewById(R.id.iv_afterword_reply_u_img);
			avatar.setDefaultImageResId(R.drawable.usericon);
			avatar.setImageUrl(
					app.getServerIp() + "/user_photo/"
							+ p.getU_photo_url(), mImageLoader);

			tv_afterword_reply_date.setText(String.valueOf(p.getA_r_year()) + "-" + String.valueOf(p.getA_r_month())
					+ "-" + String.valueOf(p.getA_r_day()));
			tv_afterword_reply_text.setText(p.getA_r_content());
		}
		return v;
	}
	
	public PopupWindow initPopupWindow(ImageView pop,final TextView tv_afterword_reply_text,final int position) {
		try {
			LayoutInflater mInflater = (LayoutInflater) context
					.getApplicationContext().getSystemService(
							Context.LAYOUT_INFLATER_SERVICE);
			View layout = mInflater.inflate(R.layout.item_popupmenu, null);

			// If you want to add any listeners to your textviews, these are two
			// //textviews.
			final ImageView itema = (ImageView) layout.findViewById(R.id.itemA);
			itema.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((DetailedAfterword_reply_view)context).setEditText(tv_afterword_reply_text.getText().toString(),true,mDropdown,position);
				}
			});

			final ImageView itemb = (ImageView) layout.findViewById(R.id.itemB);
			itemb.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((DetailedAfterword_reply_view)context).deleteReply(mDropdown,position);
				}
			});

			layout.measure(View.MeasureSpec.UNSPECIFIED,
					View.MeasureSpec.UNSPECIFIED);
			mDropdown = new PopupWindow(layout,
					FrameLayout.LayoutParams.WRAP_CONTENT,
					FrameLayout.LayoutParams.WRAP_CONTENT, true);
			Drawable background = context.getResources().getDrawable(android.R.drawable.editbox_dropdown_light_frame);
			mDropdown.setBackgroundDrawable(background);
			mDropdown.showAsDropDown(pop, 5, 5);
			return mDropdown;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
