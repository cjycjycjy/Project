package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;








import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.model.QnAReply;
import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.detailediteminfo.view.DetailedAfterword_reply_view;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DetailedQnA_replyListAdapter extends ArrayAdapter<QnAReply>{

	PopupWindow mDropdown;
	
	Context context;
	ArrayList<QnAReply> items;
	AppController app;
	ImageLoader mImageLoader;
	RequestQueue mRequestQueue;
	
	public DetailedQnA_replyListAdapter(Context context, int resource, ArrayList<QnAReply> objects) {
		// TODO Auto-generated constructor stub
		super(context, resource, objects);
		this.context = context;
		this.items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)context.getApplicationContext();
	}
	
	public View getView(int position,View convertView, ViewGroup parent){
		View v = convertView;
		if(v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
			v = vi.inflate(R.layout.item_afterwordlist_replylist,null);
			
		}
		final QnAReply p = items.get(position);
		if(p!=null){
			TextView tv_qna_reply_u_id = (TextView)v.findViewById(R.id.tv_afterword_reply_u_id);
			final TextView tv_qna_reply_text = (TextView)v.findViewById(R.id.tv_afterword_reply_text);
			TextView tv_qna_reply_date = (TextView)v.findViewById(R.id.tv_afterword_reply_date);

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
					initPopupWindow(btn_modifydelete,tv_qna_reply_text,p.getQ_r_id());
				}
			});
			tv_qna_reply_u_id.setText(p.getU_id());
			tv_qna_reply_date.setText(String.valueOf(p.getQ_r_year())+"-"+String.valueOf(p.getQ_r_month())
					+"-"+String.valueOf(p.getQ_r_day()));
			tv_qna_reply_text.setText(p.getQ_r_content());   
			NetworkImageView avatar;
			avatar = (NetworkImageView) v.findViewById(R.id.iv_afterword_reply_u_img);
			avatar.setDefaultImageResId(R.drawable.usericon);
			avatar.setImageUrl(
					app.getServerIp() + "/user_photo/"
							+ p.getU_photo_url(), mImageLoader);

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
					((DetailedQnA_reply_View)context).setEditText(tv_afterword_reply_text.getText().toString(),true,mDropdown,position);
				}
			});

			final ImageView itemb = (ImageView) layout.findViewById(R.id.itemB);
			itemb.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((DetailedQnA_reply_View)context).deleteReply(mDropdown,position);
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
