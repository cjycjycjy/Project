package ddoraemi.detailedgroupinfo.view;

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
import ddoraemi.detailedgroupinfo.model.QnA;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_Presenter;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_Presenter_Interface;
import ddoraemi.detailediteminfo.view.ScrollTabHolder;
import ddoraemi.detailediteminfo.view.ScrollTabHolderFragment;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.dialog.Dialog_KakaoLink;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;
import ddoraemi.start.User;
import ddoraemi.volley.VolleySingleton;

public class DetailedGroupInfo_View extends FragmentActivity implements DetailedGroupInfo_View_Interface,
ScrollTabHolder,
ViewPager.OnPageChangeListener , OnClickListener{

	
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private PagerAdapter adapter;
	private Group group;
	private ImageView iv_type;
	private ImageView iv_type_icon;
	private ImageView btn_back;
	//private RelativeLayout t_map = null;
	TMapView t_MapView;
	private int mMinHeaderHeight;//
	private int mHeaderHeight;//
	private int mMinHeaderTranslation;//
	View mHeader;//
	ImageView btn_pic,btn_share;
	DetailedGroupInfo_Presenter_Interface presenter;
	
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	NetworkImageView avatar;
	int curpagenum;
	
	private KakaoLink kakaoLink;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;
    private EditText mEditText;
    private Button mSendBtn;
    private Context context;
    
    Dialog_KakaoLink dialog;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailedgroupinfo);
		context = this;
		mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(this).getImageLoader();
		app = (AppController)getApplicationContext();
		init();
	}
	public DetailedGroupInfo_Presenter_Interface getPresenter()
	{
		return presenter;
	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, intent);
		presenter.validatecredential("renew", group.getG_id(),-1);
		
		
	}
	public Group getGroup()
	{
		return group;
	}

	public void init(){
		mMinHeaderHeight = getResources().getDimensionPixelSize(
				R.dimen.min_header_height);
		mHeaderHeight = getResources().getDimensionPixelSize(
				R.dimen.header_height);
		mMinHeaderTranslation = -mMinHeaderHeight;
		mHeader = findViewById(R.id.detailedgroup_header);
		presenter = new DetailedGroupInfo_Presenter(this);
		Intent intent = getIntent();
		group = (Group) intent.getSerializableExtra("group");
		btn_pic = (ImageView)findViewById(R.id.btn_group_pic);
		btn_pic.setOnClickListener(this);
		btn_share = (ImageView)findViewById(R.id.btn_share);
		btn_share.setOnClickListener(this);
		btn_back = (ImageView) findViewById(R.id.btn_left_menu);
		btn_back.setOnClickListener(this);
		dialog = new Dialog_KakaoLink(context);
		dialog.getBtnOk().setOnClickListener(this);
		presenter.validatecredential("getdata", group.getG_id(),-1);
		avatar = (NetworkImageView)findViewById(R.id.iv_detailedgroup_challenge_image);
		avatar.setDefaultImageResId(R.drawable.img_detail_default);
		avatar.setImageUrl(app.getServerIp()+"/program_photo/"+group.getP_photo_url(),mImageLoader);
		String e_name = group.getE_name();
		iv_type = (ImageView) findViewById(R.id.iv_challenge_type);
		iv_type_icon = (ImageView)findViewById(R.id.iv_challenge_type_icon);
		if (e_name.equalsIgnoreCase(getString(R.string.culture))) {
			iv_type.setImageResource(R.drawable.bg_culture_icon);
			iv_type_icon.setImageResource(R.drawable.culture_icon);
		} else if (e_name.equalsIgnoreCase(getString(R.string.traditionalfood))) {
			iv_type.setImageResource(R.drawable.bg_traditionalfood_icon);
			iv_type_icon.setImageResource(R.drawable.traditional_food_icon);
		} else if (e_name.equalsIgnoreCase(getString(R.string.planting))) {
			iv_type.setImageResource(R.drawable.bg_planting_icon);
			iv_type_icon.setImageResource(R.drawable.planting_icon);
		} else if (e_name.equalsIgnoreCase(getString(R.string.harvest))) {
			iv_type.setImageResource(R.drawable.bg_harvest_icon);
			iv_type_icon.setImageResource(R.drawable.harvest_icon);
		} else if (e_name.equalsIgnoreCase(getString(R.string.craft))) {
			iv_type.setImageResource(R.drawable.bg_craft_icon);
			iv_type_icon.setImageResource(R.drawable.craft_icon);
		} else if (e_name.equalsIgnoreCase(getString(R.string.traditionalplay))) {
			iv_type.setImageResource(R.drawable.bg_traditionalplay_icon);
			iv_type_icon.setImageResource(R.drawable.traditional_play_icon);
		} else if (e_name.equalsIgnoreCase(getString(R.string.nature))) {
			iv_type.setImageResource(R.drawable.bg_nature_icon);
			iv_type_icon.setImageResource(R.drawable.nature_icon);
		} else {
			iv_type.setImageResource(R.drawable.bg_cooking_icon);
			iv_type_icon.setImageResource(R.drawable.cooking_icon);
		}
	}
	
	
	public void removeAllStack()// /Fragment 占쏙옙占쏙옙占쏙옙 占쌕븝옙占쏙옙.
	{
		FragmentManager fragmentmanager = getSupportFragmentManager();
		FragmentTransaction fragmenttransaction = fragmentmanager
				.beginTransaction();
		int cnt = fragmentmanager.getBackStackEntryCount();
		for (int i = 0; i < cnt; i++) {
			fragmentmanager.popBackStack();
		}
	}

	
	public class PagerAdapter extends FragmentPagerAdapter {
		private String[] Title = { "상세정보", "Q&A" };
		Group group;
		ArrayList<QnA> qna;
		ArrayList<User> user;
		private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
		private ScrollTabHolder mListener;

		public PagerAdapter(FragmentManager fm, ArrayList<QnA> qna,
				Group group,ArrayList<User> user) {
			super(fm);
			mScrollTabHolders = new SparseArrayCompat<ScrollTabHolder>();
			this.group = group;
			this.qna = new ArrayList<QnA>();
			this.qna.addAll(qna);
			this.user = new ArrayList<User>();
			this.user.addAll(user);
		}

		public void setUser(ArrayList<User> user) {
			
		}

		public void setGroup(Group group,ArrayList<User> user) {
			this.group = group;
			this.user.clear();
			this.user.addAll(user);
		}

		public void setQna(ArrayList<QnA> qna) {
			this.qna.clear();
			this.qna.addAll(qna);
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

		@Override
		public Fragment getItem(int position) {
			ScrollTabHolderFragment fragment;
			if (position == 0) {
				fragment = new DetailedGroupInfo_InfoView(group,user);
			} else {
				fragment = new DetailedGroupInfo_QnA_View(qna,group.getG_id());
			}
			mScrollTabHolders.put(position, fragment);
			fragment.setScrollTabHolder(mListener);
			return fragment;

		}

		public SparseArrayCompat<ScrollTabHolder> getScrollTabHolders() {
			return mScrollTabHolders;
		}
	}
	public void renewViewPager(ArrayList<QnA> qna,ArrayList<User> user) {
		pager.removeAllViews();
		adapter.setQna(qna);
		adapter.setGroup(group,user);
		adapter.notifyDataSetChanged();
		pager.setCurrentItem(curpagenum);
	}


	public void renewArrayList(ArrayList<QnA> qna,ArrayList<User> user) {
		renewViewPager(qna,user);
	}
	
	@Override
	public void setViewPager(ArrayList<QnA> qna, Group group,ArrayList<User> user) {
		// TODO Auto-generated method stub
		pager = (ViewPager) findViewById(R.id.fragment_detailedgroupinfo_pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.fragment_detailedgroupinfo_tabs);
		adapter = new PagerAdapter(getSupportFragmentManager(), qna, group,user);
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

	@Override
	public void setqnalist(ArrayList<QnA> qna, ArrayList<User> user) {
		// TODO Auto-generated method stub
		setViewPager(qna, group,user);
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
		if (pager.getCurrentItem() == pagePosition) {
			int scrollY = getScrollY(view);
			ViewHelper.setTranslationY(mHeader,
					Math.max(-scrollY, mMinHeaderTranslation));
		}
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_left_menu){
			finish();
		}else if(v.getId()==R.id.btn_group_pic){
			presenter.validatecredential("getpic",-1,group.getP_id());
		}else if(v.getId() == R.id.btn_share){
			dialog.show();
		}else if(v.getId() == R.id.dialog_basedialog_btnok){
			dialog.dismiss();
			sendLink(group);
		}
	}

	
	public void sendLink(Group item){
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
	public void goToPic(int p_id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,DetailedGroupInfo_PictureView.class);
		intent.putExtra("p_id", p_id);
		startActivity(intent);
	}
}
