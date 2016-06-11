package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_AfterwordView_presenter_Interface;
import ddoraemi.myinfopage.view.Adapter_NetworkCustomGallery;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class DetailedItemInfo_AfterwordListAdapter extends
		ArrayAdapter<Afterword> {
	ArrayList<Afterword> items;
	Context context;

	int mPosition;

	AppController app;
	Spinner btn_modifydelete;
	ArrayAdapter modifydelete;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;

	public DetailedItemInfo_AfterwordListAdapter(Context context, int resource,
			ArrayList<Afterword> objects) {
		super(context, resource, objects);
		this.context = context;
		app = (AppController) context.getApplicationContext();
		items = objects;
		//this.presenter = presenter;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		NetworkImageView avatar;
		mPosition = position;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_afterwordlist, null);
		}
		final Afterword p = items.get(position);
		final int mposition = position;
		if (p != null) {
			final GridView afterlistpic=(GridView)v.findViewById(R.id.item_afterwordlist_grid);
			final TextView tv_afterword_u_id = (TextView) v
					.findViewById(R.id.tv_afterword_u_id);
			final TextView tv_afterword_text = (TextView) v
					.findViewById(R.id.tv_afterword_text);
			final TextView tv_afterword_date = (TextView) v
					.findViewById(R.id.tv_afterword_date);
			final TextView tv_reply = (TextView) v
					.findViewById(R.id.tv_add_reply);
			tv_reply.setText("댓글(" + p.getA_r_num() + ")");
			final ImageView btn_modifydelete = (ImageView) v
					.findViewById(R.id.modifydelete);
			ImageView []score=new ImageView[5];
			score[0]=(ImageView)v.findViewById(R.id.star_fill_img1);
			score[1]=(ImageView)v.findViewById(R.id.star_fill_img2);
			score[2]=(ImageView)v.findViewById(R.id.star_fill_img3);
			score[3]=(ImageView)v.findViewById(R.id.star_fill_img4);
			score[4]=(ImageView)v.findViewById(R.id.star_fill_img5);
			for(int i=0; i<p.getA_grade(); i++)
			{
				score[i].setVisibility(View.VISIBLE);
			}
			

			tv_afterword_u_id.setText(p.getU_id());
			tv_afterword_date.setText(String.valueOf(p.getA_year()) + "-"
					+ String.valueOf(p.getA_month()) + "-"
					+ String.valueOf(p.getA_day()));
			tv_afterword_text.setText(p.getA_content());
			avatar = (NetworkImageView) v.findViewById(R.id.afterword_user_photo);
			avatar.setDefaultImageResId(R.drawable.usericon);
			avatar.setImageUrl(
					app.getServerIp() + "/user_photo/"
							+ p.getU_photo_url(), mImageLoader);
			Adapter_NetworkCustomGallery adapter=new Adapter_NetworkCustomGallery(context, R.layout.item_afterwordpiclist, p.getA_photo_url());
			afterlistpic.setAdapter(adapter);
		}
		return v;

	}

	

}
