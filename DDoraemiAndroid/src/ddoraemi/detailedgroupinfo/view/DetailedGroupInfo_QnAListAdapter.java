package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.model.QnA;
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

public class DetailedGroupInfo_QnAListAdapter extends ArrayAdapter<QnA> {

	ArrayList<QnA> item;
	Context context;
	ImageLoader mImageLoader;
	RequestQueue mRequestQueue;
	AppController app;

	public DetailedGroupInfo_QnAListAdapter(Context context, int resource,
			ArrayList<QnA> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.item = objects;
		this.context = context;

		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController) context.getApplicationContext();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_qnalist, null);

		}
		QnA qna = item.get(position);
		if (qna != null) {
			TextView tv_qna_u_id = (TextView) v.findViewById(R.id.tv_qna_u_id);
			TextView tv_qna_text = (TextView) v.findViewById(R.id.tv_qna_text);
			TextView tv_qna_date = (TextView) v.findViewById(R.id.tv_qna_date);
			ImageView modify = (ImageView) v.findViewById(R.id.modifydelete);
			modify.setVisibility(View.GONE);
			TextView tv_reply = (TextView) v
					.findViewById(R.id.tv_qna_add_reply);

			tv_reply.setText("댓글(" + qna.getQ_r_num() + ")");
			tv_qna_u_id.setText(qna.getU_id());
			tv_qna_date.setText(String.valueOf(qna.getQ_year()) + "-"
					+ String.valueOf(qna.getQ_month()) + "-"
					+ String.valueOf(qna.getQ_day()));
			tv_qna_text.setText(qna.getQ_content());
			NetworkImageView avatar;
			avatar = (NetworkImageView) v.findViewById(R.id.qna_user_photo);
			avatar.setDefaultImageResId(R.drawable.usericon);
			avatar.setImageUrl(
					app.getServerIp() + "/user_photo/"
							+ qna.getU_photo_url(), mImageLoader);

		}
		return v;
	}

}
