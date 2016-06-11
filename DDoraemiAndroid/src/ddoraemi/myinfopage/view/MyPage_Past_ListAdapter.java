package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.home.model.Group;
import ddoraemi.home.view.HomeView;
import ddoraemi.home.view.Home_MainView;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class MyPage_Past_ListAdapter extends ArrayAdapter<Group> {

	ArrayList<Group> items;
	Context context;
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	public MyPage_Past_ListAdapter(Context context, int resource, ArrayList<Group> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)getContext().getApplicationContext();
	}

	public View getView(int position,View convertView, ViewGroup parent){
		View v = convertView;
		NetworkImageView avatar;
		if(v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
			v = vi.inflate(R.layout.item_mypage_pastlist,null);
		}
		final int po=position;
		final Group p = items.get(position);
		if(p!=null){
			TextView p_name = (TextView) v.findViewById(R.id.item_mypage_pastlist_programname);
			TextView g_date = (TextView) v.findViewById(R.id.item_mypage_pastlist_date);
			TextView p_addr = (TextView) v.findViewById(R.id.item_mypage_pastlist_Addr);
			TextView mycore =(TextView)v.findViewById(R.id.item_mypage_pastlist_scoreTv);
			ImageView star[] = new ImageView[5];
			star[0]=(ImageView)v.findViewById(R.id.item_mypage_pastlist_star1);
			star[1]=(ImageView)v.findViewById(R.id.item_mypage_pastlist_star2);
			star[2]=(ImageView)v.findViewById(R.id.item_mypage_pastlist_star3);
			star[3]=(ImageView)v.findViewById(R.id.item_mypage_pastlist_star4);
			star[4]=(ImageView)v.findViewById(R.id.item_mypage_pastlist_star5);
			ImageView question=(ImageView)v.findViewById(R.id.item_mypage_pastlist_questionimg);
			ImageView delete=(ImageView)v.findViewById(R.id.item_mypage_pastlist_delete);
			ImageView write=(ImageView)v.findViewById(R.id.item_mypage_pastlist_write);
			question.setVisibility(View.VISIBLE);

			for(int i=0; i<5; i++)
			{
				star[i].setVisibility(View.GONE);
			}

			mycore.setText("0.0점");
			if(p_name != null){
				p_name.setText(p.getP_name());
			}
			if(g_date != null){
				g_date.setText(String.valueOf(p.getG_start_year())+"."+String.valueOf(p.getG_start_month()+".")
						+String.valueOf(p.getG_start_day()));
			}
			
			if(p_addr != null){
				p_addr.setText(p.getP_addr());
			}
			if(p.getMyafterwordnum()!=-1)//모임에대해서 후기를 작성했으면
			{
				mycore.setText(p.getMyafterwordscore()+"점");
				int score=(int)p.getMyafterwordscore();
				for(int i=0; i<score; i++)
				{
					star[i].setVisibility(View.VISIBLE);
				}
				write.setVisibility(View.GONE);
				question.setVisibility(View.GONE);
				delete.setVisibility(View.VISIBLE);
			}
			else
			{
				write.setVisibility(View.VISIBLE);
				delete.setVisibility(View.GONE);
			}
			write.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(context,Write_AfterwordView.class);
					intent.putExtra("item", p);
					((Activity)context).startActivityForResult(intent, HomeView.MYPAGEINTENT);
				}
			});
			delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					deleteAfterword(p.getMyafterwordnum(),p);
					
				}
			});
			avatar = (NetworkImageView)v.findViewById(R.id.item_mypage_pastlist_profileIMG);
            avatar.setDefaultImageResId(R.drawable.img_list_default);
			avatar.setImageUrl(app.getServerIp()+"/program_photo/"+p.getP_photo_url(),mImageLoader);
		}
		return v;
	}
	public void deleteAfterword(final int a_id,final Group p)
	{
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT)
							// call
							// your
							// base
							// url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.deleteAfterWord(a_id,new Callback<String>() {
						@Override
						public void success(String result, Response response) {
							if (context != null) {
								if(result.equals("200"))
								{
									p.setMyafterwordnum(-1);
									p.setMyafterwordscore(0);
									MyPage_Past_ListAdapter.this.notifyDataSetChanged();
								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton("확인", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dia, int which) {
									dia.dismiss();
								}
							}).show();
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new AlertDialog.Builder(context).setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();
	}


}