package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.presenter.OnDetailedItemInfo_FinishedListener;
import ddoraemi.home.model.ProgramData;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class MyPage_Wish_ListAdapter extends ArrayAdapter<ProgramData> {

	ArrayList<ProgramData> items;
	Context context;
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;

	public MyPage_Wish_ListAdapter(Context context, int resource,
			ArrayList<ProgramData> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController) getContext().getApplicationContext();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		NetworkImageView avatar;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_mypage_wishlist, null);
		}
		ProgramData p = items.get(position);
		if (p != null) {
			final int po = position;
			TextView p_name = (TextView) v
					.findViewById(R.id.item_mypage_wishlist_programname);
			TextView p_sort = (TextView) v
					.findViewById(R.id.item_mypage_wishlist_Sort);
			TextView p_addr = (TextView) v
					.findViewById(R.id.item_mypage_wishlist_Addr);
			TextView scoreTv = (TextView) v
					.findViewById(R.id.item_mypage_wishlist_scoreTv);
			ImageView star[] = new ImageView[5];
			star[0] = (ImageView) v
					.findViewById(R.id.item_mypage_wishlist_star1);
			star[1] = (ImageView) v
					.findViewById(R.id.item_mypage_wishlist_star2);
			star[2] = (ImageView) v
					.findViewById(R.id.item_mypage_wishlist_star3);
			star[3] = (ImageView) v
					.findViewById(R.id.item_mypage_wishlist_star4);
			star[4] = (ImageView) v
					.findViewById(R.id.item_mypage_wishlist_star5);
			int score = (int) (p.getP_grade());
			for (int i = 0; i < score; i++) {
				star[i].setVisibility(View.VISIBLE);
			}
			if (p_name != null) {
				p_name.setText(p.getP_name());
			}
			if (p_sort != null) {
				p_sort.setText(p.getE_name());
			}
			if (p_addr != null) {
				p_addr.setText(p.get_AddressText());
			}
			if (scoreTv != null) {
				scoreTv.setText(p.getP_grade() + "점");
			}
			avatar = (NetworkImageView) v
					.findViewById(R.id.item_mypage_wishlist_profileIMG);
			avatar.setDefaultImageResId(R.drawable.img_list_default);
			avatar.setImageUrl(
					app.getServerIp() + "/program_photo/" + p.getP_photo_url(),
					mImageLoader);
			ImageView bookmark = (ImageView) v
					.findViewById(R.id.item_mypage_wishlist_delbookmarkImg);
			bookmark.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					delBookmark(po);
				}
			});
		}
		return v;
	}

	public void delBookmark(final int po) {

		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(Retrofit.ROOT) // call your base url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.unregisterbookmark(items.get(po).getP_id(),
							app.getId(), new Callback<String>() {
								@Override
								public void success(String result,
										Response response) {
									progressdialog.dismiss();
									if (context != null) {
										if (result.equals("200")) {
											app.delbookmark(items.get(po)
													.getP_id());
											items.remove(po);
											notifyDataSetChanged();
											Toast.makeText(context,
													"북마크 삭제 완료",
													Toast.LENGTH_SHORT).show();
										}
									}
								}

								@Override
								public void failure(RetrofitError retrofitError) {
									progressdialog.dismiss();
									new AlertDialog.Builder(context)
											.setMessage("데이터를 받아오는데 실패하였습니다.")
											.setPositiveButton(
													"확인",
													new DialogInterface.OnClickListener() {
														@Override
														public void onClick(
																DialogInterface dia,
																int which) {
															dia.dismiss();
														}
													}).show();
								}
							});

				} catch (Throwable ex) {
					progressdialog.dismiss();
					ex.printStackTrace();
					new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia, int which) {
											dia.dismiss();
										}
									}).show();
				}
			}
		}).start();
	}
}
