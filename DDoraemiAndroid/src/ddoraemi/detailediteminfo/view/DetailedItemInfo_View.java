package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.astuetz.PagerSlidingTabStrip;
import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.nineoldandroids.view.ViewHelper;
import com.skp.Tmap.TMapView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_Presenter;
import ddoraemi.detailediteminfo.presenter.DetailedItemInfo_Presenter_Interface;
import ddoraemi.dialog.Dialog_KakaoLink;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class DetailedItemInfo_View extends FragmentActivity implements
		DetailedIteminfo_View_Interface, ScrollTabHolder,
		ViewPager.OnPageChangeListener, OnClickListener {

	DetailedItemInfo_Presenter_Interface presenter;

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private PagerAdapter adapter;
	private ProgramData item;
	private ImageView iv_type;
	private ImageView iv_type_icon;
	private ImageView btn_back;
	private RelativeLayout t_map = null;
	private int mMinHeaderHeight;//
	private int mHeaderHeight;//
	private int mMinHeaderTranslation;//
	TMapView t_MapView;
	View mHeader;//
	ArrayList<Afterword> afterword;
	ArrayList<Group> group;
	ImageView btn_pic;

	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	NetworkImageView avatar;
	ImageView btn_bookmark,btn_share;
	boolean bookmarked;
	int curpagenum;
	
	
	private KakaoLink kakaoLink;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;
    private EditText mEditText;
    private Button mSendBtn;
    private Context context;
    
    Dialog_KakaoLink dialog;

	public DetailedItemInfo_View() {
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_detailedinfo);
		context = this;
		
		mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(this).getImageLoader();
		app = (AppController) getApplicationContext();
		init();
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, intent);
		presenter.validatecredential("renewdata", item.getP_id(), null, false);
	}

	public void init() {
		mMinHeaderHeight = getResources().getDimensionPixelSize(
				R.dimen.min_header_height);
		mHeaderHeight = getResources().getDimensionPixelSize(
				R.dimen.header_height);
		mMinHeaderTranslation = -mMinHeaderHeight;
		mHeader = findViewById(R.id.header);
		presenter = new DetailedItemInfo_Presenter(this);

		item = new ProgramData();
		Intent intent = getIntent();
		item = (ProgramData) intent.getSerializableExtra("item");
		avatar = (NetworkImageView) findViewById(R.id.iv_detaileditem_challenge_image);
		avatar.setDefaultImageResId(R.drawable.img_detail_default);
		avatar.setImageUrl(
				app.getServerIp() + "/program_photo/" + item.getP_photo_url(),
				mImageLoader);
		presenter.validatecredential("detailedinfo", item.getP_id(), null,
				false);
		btn_pic = (ImageView) findViewById(R.id.btn_pic);
		btn_share = (ImageView)findViewById(R.id.btn_share);
		iv_type = (ImageView) findViewById(R.id.iv_challenge_type);
		iv_type_icon = (ImageView) findViewById(R.id.iv_challenge_type_icon);
		btn_back = (ImageView) findViewById(R.id.btn_left_menu);
		btn_back.setOnClickListener(this);
		btn_pic.setOnClickListener(this);
		btn_share.setOnClickListener(this);
		dialog = new Dialog_KakaoLink(context);
		dialog.getBtnOk().setOnClickListener(this);
		String p_type = item.getE_name();

		btn_bookmark = (ImageView) findViewById(R.id.cha_bookmark);
		int p_id = item.getP_id();
		if (app.searchbookmark(p_id)) {
			btn_bookmark.setImageResource(R.drawable.icon_bookmarked);
			bookmarked = true;
		} else {
			btn_bookmark.setImageResource(R.drawable.icon_bookmark);
			bookmarked = false;
		}
		btn_bookmark.setOnClickListener(this);
		iv_type = (ImageView) findViewById(R.id.iv_challenge_type);
		if (p_type.equalsIgnoreCase(getString(R.string.culture))) {
			iv_type.setImageResource(R.drawable.bg_culture_icon);
			iv_type_icon.setImageResource(R.drawable.culture_icon);
		} else if (p_type.equalsIgnoreCase(getString(R.string.traditionalfood))) {
			iv_type.setImageResource(R.drawable.bg_traditionalfood_icon);
			iv_type_icon.setImageResource(R.drawable.traditional_food_icon);
		} else if (p_type.equalsIgnoreCase(getString(R.string.planting))) {
			iv_type.setImageResource(R.drawable.bg_planting_icon);
			iv_type_icon.setImageResource(R.drawable.planting_icon);
		} else if (p_type.equalsIgnoreCase(getString(R.string.harvest))) {
			iv_type.setImageResource(R.drawable.bg_harvest_icon);
			iv_type_icon.setImageResource(R.drawable.harvest_icon);
		} else if (p_type.equalsIgnoreCase(getString(R.string.craft))) {
			iv_type.setImageResource(R.drawable.bg_craft_icon);
			iv_type_icon.setImageResource(R.drawable.craft_icon);
		} else if (p_type.equalsIgnoreCase(getString(R.string.traditionalplay))) {
			iv_type.setImageResource(R.drawable.bg_traditionalplay_icon);
			iv_type_icon.setImageResource(R.drawable.traditional_play_icon);
		} else if (p_type.equalsIgnoreCase(getString(R.string.nature))) {
			iv_type.setImageResource(R.drawable.bg_nature_icon);
			iv_type_icon.setImageResource(R.drawable.nature_icon);
		} else {
			iv_type.setImageResource(R.drawable.bg_cooking_icon);
			iv_type_icon.setImageResource(R.drawable.cooking_icon);
		}
	}

	public void removeAllStack()// /Fragment �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙐釉앹삕�뜝�룞�삕.
	{
		FragmentManager fragmentmanager = getSupportFragmentManager();
		FragmentTransaction fragmenttransaction = fragmentmanager
				.beginTransaction();
		int cnt = fragmentmanager.getBackStackEntryCount();
		for (int i = 0; i < cnt; i++) {
			fragmentmanager.popBackStack();
		}
	}

	@Override
	public void setViewPager(ArrayList<Afterword> afterword, ProgramData item,
			ArrayList<Group> group) {
		// TODO Auto-generated method stub
		pager = (ViewPager) findViewById(R.id.fragment_detailediteminfo_pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.fragment_detailediteminfo_tabs);
		adapter = new PagerAdapter(getSupportFragmentManager(), afterword,
				item, group);
		adapter.setTabHolderScrollingContent(this);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(2);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		tabs.setOnPageChangeListener(this);
	}

	public class PagerAdapter extends FragmentPagerAdapter {
		private String[] Title = { getString(R.string.detailedinfo),
				getString(R.string.group), getString(R.string.afterword) };
		ProgramData item;
		ArrayList<Afterword> afterword;
		ArrayList<Group> group;
		private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
		private ScrollTabHolder mListener;

		public PagerAdapter(FragmentManager fm, ArrayList<Afterword> afterword,
				ProgramData item, ArrayList<Group> group) {
			super(fm);
			mScrollTabHolders = new SparseArrayCompat<ScrollTabHolder>();
			this.item = item;

			this.afterword = new ArrayList<Afterword>();
			this.afterword.addAll(afterword);

			this.group = new ArrayList<Group>();
			this.group.addAll(group);
		}

		public void setTabHolderScrollingContent(ScrollTabHolder listener) {
			mListener = listener;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public int getCount() {
			return Title.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return Title[position];
		};

		public void setItem(ProgramData item) {
			this.item = item;
		}

		public void setAfterword(ArrayList<Afterword> afterword) {
			this.afterword.clear();
			this.afterword.addAll(afterword);
		}

		public void setGroup(ArrayList<Group> group) {
			this.group.clear();
			this.group.addAll(group);
		}

		@Override
		public Fragment getItem(int position) {
			ScrollTabHolderFragment fragment;
			if (position == 0) {
				fragment = new DetailedItemInfo_InfoView(item, group);
			} else if (position == 1) {
				fragment = new DetailedItemInfo_groupView(group);
			} else {
				fragment = new DetailedItemInfo_AfterwordView(afterword);
			}
			mScrollTabHolders.put(position, fragment);
			fragment.setScrollTabHolder(mListener);
			return fragment;

		}

		public SparseArrayCompat<ScrollTabHolder> getScrollTabHolders() {
			return mScrollTabHolders;
		}
	}

	@Override
	public void setProgramList(ArrayList<Afterword> data, ArrayList<Group> group) {
		// TODO Auto-generated method stub
		this.afterword = data;
		this.group = group;
		setViewPager(afterword, item, group);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// nothing
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// nothing
	}

	@Override
	public void onPageSelected(int position) {
		curpagenum = position;
		SparseArrayCompat<ScrollTabHolder> scrollTabHolders = adapter
				.getScrollTabHolders();
		ScrollTabHolder currentHolder = scrollTabHolders.valueAt(position);
		currentHolder.adjustScroll((int) (mHeader.getHeight() + ViewHelper
				.getTranslationY(mHeader)));
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount, int pagePosition) {
		int scrollY = getScrollY(view);
		ViewHelper.setTranslationY(mHeader,
				Math.max(-scrollY, mMinHeaderTranslation));
	}

	@Override
	public void adjustScroll(int scrollHeight) {
		// nothing
	}

	public int getScrollY(AbsListView view) {
		View c = view.getChildAt(0);
		if (c == null) {
			return 0;
		}

		int firstVisiblePosition = view.getFirstVisiblePosition();
		int top = c.getTop();

		int headerHeight = 0;
		if (firstVisiblePosition >= 1) {
			headerHeight = mHeaderHeight;
		}

		return -top + firstVisiblePosition * c.getHeight() + headerHeight;
	}

	@Override
	public void onScrollViewScroll(int scrollheight, int pagePosition) {
		if (pager.getCurrentItem() == pagePosition) {
			int scrollY = scrollheight;
			ViewHelper.setTranslationY(mHeader,
					Math.max(-scrollY, mMinHeaderTranslation));
		}
		// TODO Auto-generated method stub

	}

	public void re_bookmark(String event) {
		Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
	}

	public void setbookmark() {
		btn_bookmark.setImageResource(R.drawable.icon_bookmarked);
		bookmarked = true;
	}

	public void delbookmark() {
		btn_bookmark.setImageResource(R.drawable.icon_bookmark);
		bookmarked = false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_left_menu) {
			this.finish();
		} else if (v.getId() == R.id.btn_pic) {
			presenter.validatecredential("getpic", item.getP_id(), null, false);
		} else if (v.getId() == R.id.cha_bookmark) {
			presenter.validatecredential("bookmark", item.getP_id(), null,
					bookmarked);
		}else if(v.getId() == R.id.btn_share){
			dialog.show();
		}else if(v.getId() == R.id.dialog_basedialog_btnok){
			dialog.dismiss();
			sendLink(item);
		}
	}

	public void sendLink(ProgramData item){
		try {
			kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
			kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
		} catch (KakaoParameterException e) {
			e.getMessage();
		}
		try {
			//		kakaoTalkLinkMessageBuilder.addText(text);			
					String imageSrc = app.getServerIp() + "/program_photo/" + item.getP_photo_url();
					int width = 512;  
					int height = 503;
//					kakaoTalkLinkMessageBuilder.addImage(imageSrc, width, height).addText("asd")
//					.addAppButton("앱으로 이동", new AppActionBuilder().setUrl("https://www.naver.com").build());
					kakaoTalkLinkMessageBuilder.addText("키즈파머 - "+item.getP_name()+" 함께 가실까요?").addImage(imageSrc, width, height)
					.addAppButton("자세히 보기",
							new AppActionBuilder().addActionInfo(AppActionInfoBuilder
		                            .createAndroidActionInfoBuilder()
		                            //.setExecuteParam(getString(R.string.kakao_app_key))
		                            
		                            //.setMarketParam("market://details?id=ddoraemi.start")
		                            .build()).build());
					
					final String linkContents = kakaoTalkLinkMessageBuilder.build();
					kakaoLink.sendMessage(linkContents, this);

				} catch (KakaoParameterException e) {
					e.getMessage();
				}
	}
	@Override
	public void goToPicture(int p_id) {
		Intent intent = new Intent(this, DetailedItemInfo_PictureView.class);
		intent.putExtra("p_id", p_id);
		startActivity(intent);
	}

	public void renewViewPager(ArrayList<Group> group,
			ArrayList<Afterword> afterword) {
		pager.removeAllViews();
		adapter.setGroup(group);
		adapter.setAfterword(afterword);
		adapter.notifyDataSetChanged();
		pager.setCurrentItem(curpagenum);
	}

	public void renewArrayList(ArrayList<Group> group,
			ArrayList<Afterword> afterword) {
		renewViewPager(group, afterword);
	}
}
