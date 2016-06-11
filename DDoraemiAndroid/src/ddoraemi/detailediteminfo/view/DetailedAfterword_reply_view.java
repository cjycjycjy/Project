package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.viewpagerindicator.CirclePageIndicator;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.detailediteminfo.presenter.DetailedAfterword_reply_presenter;
import ddoraemi.detailediteminfo.presenter.DetailedAfterword_reply_presenter_Interface;
import ddoraemi.myinfopage.view.Modify_AfterWordView;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class DetailedAfterword_reply_view extends Activity implements
		OnClickListener, DetailedAfterword_reply_view_Interface {
	DetailedAfterword_reply_presenter_Interface presenter;
	Afterword afterword;
	ListView listview;
	ViewPager pager;
	ImageView[] score;
	CirclePageIndicator mIndicator;
	DetailedAfterword_replyListAdapter adapter;
	ArrayList<AfterwordReply> afterword_reply;
	ImageView btn_modifydelete;
	TextView tv_detailedAfterword_u_id;
	TextView tv_detailedAfterword_date;
	TextView tv_detailedAfterword_text;
	ImageView btn_inputreply;
	ImageView btn_back;
	EditText et_reply;
	final static int MODIFYINTENT=1000;
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	PopupWindow mDropdown;

	int mposition;
	int a_r_position;
	boolean reply_modify_check = false;
	private NetworkImageView userimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailedafterword);
		app = (AppController) getApplicationContext();
		Intent intent = getIntent();
		mposition = intent.getIntExtra("position", 0);
		afterword = (Afterword) intent.getSerializableExtra("item");
		mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(this).getImageLoader();
		init();
	}

	public void init() {
		presenter = new DetailedAfterword_reply_presenter(this);
		afterword_reply = new ArrayList<AfterwordReply>();
		mIndicator=(CirclePageIndicator)findViewById(R.id.activity_detailedafterword_Indicator);
		pager=(ViewPager)findViewById(R.id.activity_detailedafterword_ViewPager);
		Adapter_DetailAfterWord_Pager pageradapter=new Adapter_DetailAfterWord_Pager(this, afterword.getA_photo_url());
		pager.setAdapter(pageradapter);
		mIndicator.setViewPager(pager);
		tv_detailedAfterword_u_id = (TextView) findViewById(R.id.tv_detailed_afterword_u_id);
		tv_detailedAfterword_date = (TextView) findViewById(R.id.tv_detailed_afterword_date);
		tv_detailedAfterword_text = (TextView) findViewById(R.id.tv_detailed_afterword_text);
		btn_inputreply = (ImageView) findViewById(R.id.btn_inputreply);
		et_reply = (EditText) findViewById(R.id.et_inputreply);
		btn_back = (ImageView) findViewById(R.id.btn_detailedafterword_left_menu);
		tv_detailedAfterword_date.setText(afterword.getA_year() + "-"
				+ afterword.getA_month() + "-" + afterword.getA_day());
		tv_detailedAfterword_text.setText(afterword.getA_content());
		tv_detailedAfterword_u_id.setText(afterword.getU_id());
		score=new ImageView[5];
		score[0]=(ImageView)findViewById(R.id.activity_detailAfterword_star1);
		score[1]=(ImageView)findViewById(R.id.activity_detailAfterword_star2);
		score[2]=(ImageView)findViewById(R.id.activity_detailAfterword_star3);
		score[3]=(ImageView)findViewById(R.id.activity_detailAfterword_star4);
		score[4]=(ImageView)findViewById(R.id.activity_detailAfterword_star5);
		for(int i=0; i<afterword.getA_grade(); i++)
		{
			score[i].setVisibility(View.VISIBLE);
		}

		if (!afterword.getU_photo_url().equalsIgnoreCase("empty")) {
			NetworkImageView avatar;
			avatar = (NetworkImageView) findViewById(R.id.detailedafterword_user_photo);
			avatar.setImageUrl(
					app.getServerIp() + "/user_photo/"
							+ afterword.getU_photo_url(), mImageLoader);
		}

		btn_modifydelete = (ImageView) findViewById(R.id.activity_detailAfterword_modify);
		if (app.getId().equals(afterword.getU_id())) {
			btn_modifydelete.setVisibility(View.VISIBLE);
		} else {
			btn_modifydelete.setVisibility(View.GONE);
		}
		btn_modifydelete.setOnClickListener(this);
		btn_modifydelete.setOnClickListener(this);

		listview = (ListView) findViewById(R.id.lv_replylist);
		afterword_reply = afterword.getAfterword_reply();
		adapter = new DetailedAfterword_replyListAdapter(this,
				R.layout.item_afterwordlist_replylist, afterword_reply);
		listview.setAdapter(adapter);
		setListViewHeightBasedOnChildren(listview);
		btn_back.setOnClickListener(this);
		btn_inputreply.setOnClickListener(this);
		if(afterword.getA_photo_url().size()>0)
		{
			pager.setVisibility(View.VISIBLE);
		}
		else
		{
			pager.setVisibility(View.GONE);
		}
		userimg = (NetworkImageView)findViewById(R.id.iv_inputreply_user);
		userimg.setDefaultImageResId(R.drawable.usericon);
		userimg.setImageUrl(
				app.getServerIp() + "/user_photo/"
						+ app.getUser().getU_photo_url(), mImageLoader);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			back();
			return true;
		}
		return false;
	}

	public void setEditText(String text, boolean check, PopupWindow mDropdown,
			int position) {
		et_reply.setText(text);
		et_reply.requestFocus();
		et_reply.setSelection(et_reply.length());
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				mgr.showSoftInput(et_reply, InputMethodManager.SHOW_FORCED);
			}
		}, 50);
		btn_inputreply.setImageResource(R.drawable.btn_commentmodify);
		reply_modify_check = check;
		mDropdown.dismiss();
		a_r_position = position;
	}

	public void deleteReply(PopupWindow mDropdown, int position) {
		presenter.validatecredential("delete", afterword, position,
				afterword.getA_id(), app.getId(),
				et_reply.getText().toString(), mposition);
		mDropdown.dismiss();
	}

	public void back() {
		Intent intent = new Intent();
		// intent.putExtra("replynum", position);
		intent.putExtra("item", afterword);
		intent.putExtra("position", mposition);
		this.setResult(500, intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_inputreply) {
			if (!reply_modify_check) {
				presenter.validatecredential("setReply", afterword, 0,
						afterword.getA_id(), app.getId(), et_reply.getText()
								.toString(), mposition);
				et_reply.setText("");
				v.clearFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(et_reply.getWindowToken(), 0);
			}  else {
				presenter.validatecredential("modify", afterword, a_r_position,
						afterword.getA_id(), app.getId(), et_reply.getText()
								.toString(), mposition);
				et_reply.setText("");
				btn_inputreply.setImageResource(R.drawable.btn_commentsave);
				reply_modify_check = false;
				v.clearFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(et_reply.getWindowToken(), 0);
			}

		}
		else if (v.getId() == R.id.btn_detailedafterword_left_menu) {
			back();
		}
		else if (v.getId() == R.id.btn_left_menu) {
			v.clearFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(et_reply.getWindowToken(), 0);
			finish();
		}
		else if (v.getId() == R.id.activity_detailAfterword_modify) {
			presenter.validatecredential("clickPopmenu", null, 0, 0, "",
					"", 0);
		}
	}

	public PopupWindow initPopupWindow(ImageView pop,
			final TextView tv_afterword_reply_text, final int position) {
		try {
			LayoutInflater mInflater = (LayoutInflater) getApplicationContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = mInflater.inflate(R.layout.item_popupmenu, null);

			// If you want to add any listeners to your textviews, these are two
			// //textviews.
			final ImageView itema = (ImageView) layout.findViewById(R.id.itemA);
			itema.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(DetailedAfterword_reply_view.this,Modify_AfterWordView.class);
					intent.putExtra("afterworditem", DetailedAfterword_reply_view.this.afterword);
					startActivityForResult(intent, DetailedAfterword_reply_view.MODIFYINTENT);
				}
			});

			final ImageView itemb = (ImageView) layout.findViewById(R.id.itemB);
			itemb.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					presenter.validatecredential("deletemodify", afterword, a_r_position,
							afterword.getA_id(), app.getId(), et_reply.getText()
							.toString(), mposition);

				}
			});

			layout.measure(View.MeasureSpec.UNSPECIFIED,
					View.MeasureSpec.UNSPECIFIED);
			mDropdown = new PopupWindow(layout,
					FrameLayout.LayoutParams.WRAP_CONTENT,
					FrameLayout.LayoutParams.WRAP_CONTENT, true);
			Drawable background = getApplicationContext().getResources()
					.getDrawable(
							android.R.drawable.editbox_dropdown_light_frame);
			mDropdown.setBackgroundDrawable(background);
			mDropdown.showAsDropDown(pop, 5, 5);
			return mDropdown;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void notifyListview(ArrayList<AfterwordReply> result) {
		// TODO Auto-generated method stub
		afterword_reply.clear();
		afterword_reply.addAll(result);
		adapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(listview);
	}

	@Override
	public void showPopMenu() {
		// TODO Auto-generated method stub
		initPopupWindow(btn_modifydelete, tv_detailedAfterword_text, mposition);
	}
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent intent) {
		if(arg0==DetailedAfterword_reply_view.MODIFYINTENT&&arg1==100)
		{
			presenter.validatecredential(intent);
		}
	}

	@Override
	public void modifyAfterword(Intent intent) {
		// TODO Auto-generated method stub
		String content=intent.getStringExtra("content");
		float grade=intent.getFloatExtra("grade",0);
		ArrayList<String> photoarray=intent.getStringArrayListExtra("photo");
		if(photoarray.size()>0)
		{
			pager.setVisibility(View.VISIBLE);
		}
		else
		{
			pager.setVisibility(View.GONE);
		}
		afterword.setA_content(content);
		afterword.setA_grade(grade);
		afterword.setA_photo_url(photoarray);
		for(int i=0; i<5; i++)
		{
			score[i].setVisibility(View.GONE);
		}
		for(int i=0; i<afterword.getA_grade(); i++)
		{
			score[i].setVisibility(View.VISIBLE);
		}
		tv_detailedAfterword_text.setText(afterword.getA_content());
		Adapter_DetailAfterWord_Pager pageradapter=new Adapter_DetailAfterWord_Pager(this, afterword.getA_photo_url());
		pager.setAdapter(pageradapter);
	}
	
 

}
