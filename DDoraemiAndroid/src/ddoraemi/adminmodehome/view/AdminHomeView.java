package ddoraemi.adminmodehome.view;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;

import ddoraemi.adminmodehome.presenter.AdminHomePresenter;
import ddoraemi.adminmodehome.presenter.AdminHomePresenterInterface;
import ddoraemi.appcontroller.AppController;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.ProgramData;
import ddoraemi.home.view.HomeView;
import ddoraemi.home.view.Home_MainView;
import ddoraemi.myinfopage.view.MyPageView;
import ddoraemi.programlist.view.Programlist_type_View;
import ddoraemi.start.R;
import ddoraemi.start.SplashActivity;
import ddoraemi.volley.VolleySingleton;

public class AdminHomeView extends FragmentActivity implements
		AdminHomeViewInterface, OnClickListener, OnItemClickListener {
	private SlidingMenu slidingMenu;// �����̵� �޴� ���� ����
	private ImageView leftmenu;
	private ImageView btn_gotoUsermode;
	private TextView sidemeun_id;
	AdminHomeSideMenu_Challenge_adapter adapter;
	AppController app;
	NetworkImageView avatar;
	ImageLoader mImageLoader;
	RequestQueue mRequestQueue;
	ArrayList<ProgramData> sidemenuarray;
	ListView menulist;
	AdminHomePresenterInterface presenter;
	LinearLayout opacity;
	static final int IntentAdmin = 12345;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity_adminhome);
		app = (AppController) getApplicationContext();
		mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(this).getImageLoader();
		init();
	}

	public void init() {
		opacity=(LinearLayout)findViewById(R.id.opcityview);
		presenter = new AdminHomePresenter(this);
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setShadowWidthRes(R.dimen.slidingmenuWidth);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenuOffset);
		slidingMenu.setMenu(R.layout.slidemenu_admin);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setOnOpenListener(new OnOpenListener() {
			public void onOpen() {
				// TODO Auto-generated method stub
				opacity.setVisibility(View.VISIBLE);

			}
		});
		slidingMenu.setOnCloseListener(new OnCloseListener() {

			@Override
			public void onClose() {
				// TODO Auto-generated method stub

				opacity.setVisibility(View.GONE);
			}
		});
		leftmenu = (ImageView) findViewById(R.id.admin_btn_left_menu);
		leftmenu.setOnClickListener(this);
		menulist = (ListView) findViewById(R.id.sidemenu_admin_menulist);
		avatar = (NetworkImageView) findViewById(R.id.sidemenu_admin_userphoto);
		avatar.setImageUrl(app.getServerIp() + "/user_photo/"
				+ app.getUser().getU_photo_url(), mImageLoader);

		// ////////////////////////////
		sidemeun_id = (TextView) findViewById(R.id.sidemenu_admin_loginstate);
		sidemeun_id.setText(app.getId());
		presenter.validatecredential("getAdminList", app.getId());
		btn_gotoUsermode = (ImageView) findViewById(R.id.sidemenu_admin_switch_usermode);
		btn_gotoUsermode.setOnClickListener(this);

	}

	public void showFailureDialog() {

		Dialog_BaseDialog dialog = new Dialog_BaseDialog(AdminHomeView.this,
				getString(R.string.failed_dialog));
		dialog.show();
	}

	@Override
	public void setSideMenu(ArrayList<ProgramData> array) {
		// TODO Auto-generated method stub
		this.sidemenuarray = array;
		adapter = new AdminHomeSideMenu_Challenge_adapter(this,
				R.layout.item_adminsidemenu_cha_list, array);
		menulist.setAdapter(adapter);
		menulist.setOnItemClickListener(this);
		FragmentManager fragmentmanager = getSupportFragmentManager();
		FragmentTransaction fragmenttransaction = fragmentmanager
				.beginTransaction();
		fragmenttransaction.replace(R.id.adminFragment, new AdminHomeMainView(
				adapter.getItem(0).getP_id()));
		fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
		fragmenttransaction.commit();

	}

	@Override
	public void showSideMenu() {
		slidingMenu.showMenu();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.admin_btn_left_menu) {
			// presenter.validatecredential("clickSideMenu");
			slidingMenu.showMenu();
		} else if (v.getId() == R.id.sidemenu_admin_switch_usermode) {
			presenter.validatecredential("goToUserMode");
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		ProgramData data = adapter.getItem(arg2);
		presenter.selectProgram(data.getP_id());

	}

	@Override
	public void selectProgram(int p_id) {
		// TODO Auto-generated method stub
		slidingMenu.showContent(true);
		FragmentManager fragmentmanager = getSupportFragmentManager();
		FragmentTransaction fragmenttransaction = fragmentmanager
				.beginTransaction();
		fragmenttransaction.replace(R.id.adminFragment, new AdminHomeMainView(
				p_id));
		fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
		fragmenttransaction.commit();
	}

	@Override
	public void goToUserMode() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(AdminHomeView.this, HomeView.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, intent);
		if (arg0 == IntentAdmin) {
			if (getSupportFragmentManager()
					.findFragmentById(R.id.adminFragment) instanceof AdminHomeMainView) {
				((AdminHomeMainView) (getSupportFragmentManager()
						.findFragmentById(R.id.adminFragment))).renew();
			}
		}

	}
}