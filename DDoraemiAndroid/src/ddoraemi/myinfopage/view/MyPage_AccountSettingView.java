package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import ddoraemi.appcontroller.AppController;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.dialog.Dialog_ChangeFavorite;
import ddoraemi.home.view.Home_MainView;
import ddoraemi.home.view.Home_RecommendView;
import ddoraemi.joining.view.JoinActivity;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;
import ddoraemi.start.SplashActivity;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyPage_AccountSettingView extends Activity implements OnClickListener {
	EditText phonenum;
	ImageView sendbt, back;
	final int COUNTINTEREST = 8;
	AppController app;

	TextView tv_account_id;
	TextView tv_adult_gender;
	TextView tv_adult_age;
	TextView tv_child_gender;
	TextView tv_child_age;
	TextView tv_regidential_district;
	TextView tv_challenge_type;
	TextView tv_change_password;

	ArrayList<String> favorite_str=new ArrayList<String>();
	
	Dialog_ChangeFavorite changefavoritedialog;
	
	JsonArray favorite_program = new JsonArray();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_setting);
		app = (AppController) getApplicationContext();
		init();
	}

	public void init() {
		sendbt = (ImageView) findViewById(R.id.btn_save);
		sendbt.setOnClickListener(this);

		back = (ImageView) findViewById(R.id.btn_left_menu);
		back.setOnClickListener(this);

		tv_account_id = (TextView) findViewById(R.id.tv_account_id);
		tv_adult_gender = (TextView) findViewById(R.id.tv_adult_gender);
		tv_adult_age = (TextView) findViewById(R.id.tv_adult_age);
		tv_child_gender = (TextView) findViewById(R.id.tv_child_gender);
		tv_child_age = (TextView) findViewById(R.id.tv_child_age);
		tv_regidential_district = (TextView) findViewById(R.id.tv_regidential_district);
		tv_challenge_type = (TextView) findViewById(R.id.tv_challenge_type);
		tv_change_password = (TextView) findViewById(R.id.tv_change_password);

		tv_change_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MyPage_AccountSettingView.this, MyPage_PasswdSettingView.class);
				startActivity(i);
			}
		});
		tv_account_id.setText(app.getId());

		if (String.valueOf(app.getUser().getParent_gender()).equals("M"))
			tv_adult_gender.setText("남성");
		else
			tv_adult_gender.setText("여성");

		tv_adult_age.setText(String.valueOf(app.getUser().getParent_age()) + "세");

		if (String.valueOf(app.getUser().getU_gender()).equals("M"))
			tv_child_gender.setText("남성");
		else
			tv_child_gender.setText("여성");

		tv_child_age.setText(String.valueOf(app.getUser().getU_age()) + "세");

		String str = "";
		if (!app.getUser().getU_addr1().isEmpty())
			str += app.getUser().getU_addr1() + "시 ";
		if (!app.getUser().getU_addr2().isEmpty())
			str += app.getUser().getU_addr2();
		if (!app.getUser().getU_addr3().isEmpty())
			str += app.getUser().getU_addr3();
		tv_regidential_district.setText(str);

		str = "";
		for (int i = 0; i < app.getUser().getFavorit_program().size(); i++) {
			if (!app.getUser().getFavorit_program().get(i).isEmpty()) {
				if (i > 0)
					str += ",";
				str += app.getUser().getFavorit_program().get(i);
			}
		}

		changefavoritedialog = new Dialog_ChangeFavorite(this, app.getUser().getFavorit_program());
		changefavoritedialog.getOkBtn().setOnClickListener(this);
		changefavoritedialog.getCancelBtn().setOnClickListener(this);

		tv_challenge_type.setText(str);
		tv_challenge_type.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changefavoritedialog.show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_left_menu) {
			finish();
		}
		if (v.getId() == R.id.btn_dialog_confirmcancel_ok) {
			String str = "";
			ToggleButton[] toggle = changefavoritedialog.getToggle();
			int count = 0;
			for (int i = 0; i < COUNTINTEREST; i++) {
				if (toggle[i].isChecked()) {
					JsonObject interest = new JsonObject();
					if (count > 0)
						str += ",";
					count++;
					switch (i) {
					case 0:
						interest.addProperty("e_name", "전통음식체험");
						favorite_str.add("전통음식체험");
						str += "전통음식체험";
						break;
					case 1:
						interest.addProperty("e_name", "경작체험");
						favorite_str.add("경작체험");
						str += "경작체험";
						break;
					case 2:
						interest.addProperty("e_name", "수확체험");
						favorite_str.add("수확체험");
						str += "수확체험";
						break;
					case 3:
						interest.addProperty("e_name", "전통놀이체험");
						favorite_str.add("전통놀이체험");
						str += "전통놀이체험";
						break;
					case 4:
						interest.addProperty("e_name", "요리체험");
						favorite_str.add("요리체험");
						str += "요리체험";
						break;
					case 5:
						interest.addProperty("e_name", "공예체험");
						favorite_str.add("공예체험");
						str += "공예체험";
						break;
					case 6:
						interest.addProperty("e_name", "문화체험");
						favorite_str.add("문화체험");
						str += "문화체험";
						break;
					case 7:
						interest.addProperty("e_name", "자연체험");
						favorite_str.add("자연체험");
						str += "자연체험";
						break;
					}
					favorite_program.add(interest);
				}
			}
			tv_challenge_type.setText(str);
			changefavoritedialog.dismiss();
		}
		if (v.getId() == R.id.btn_dialog_confirmcancel_cancel) {
			changefavoritedialog.dismiss();
		}
		if (v.getId() == R.id.btn_save) {
			sendChangeFavoriteRequest(favorite_program);
			
		}
	}

	public void sendChangeFavoriteRequest(final JsonArray favorite) {
		final Context context = this;
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage("잠시만 기다려주세요");
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();

		new Thread(new Runnable() {

			public void run() {
				try {
					JsonObject info = new JsonObject();
					info.add("favorit_program", favorite);
					info.addProperty("u_id", app.getId());
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT) // call
																									// your
																									// base
																									// url
							.build();
					Retrofit retofit = restAdapter.create(Retrofit.class);
					retofit.requestChangeFavorite(info, new Callback<String>() {
						@Override
						public void success(String result, Response response) {
							progressdialog.dismiss();
							if (context != null) {
								if (result.equals("200")) {
									app.getUser().setFavorit_program(favorite_str);
									((Home_MainView)Home_MainView.mcontext).renewList();
									finish();
								}
								else{
									Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,"변경에 실패했습니다.");
									dialog.show();
								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							if (context != null) {

								Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,
										getString(R.string.failed_dialog));
								dialog.show();
							}
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
					if (context != null) {
						Dialog_BaseDialog dialog = new Dialog_BaseDialog(context, getString(R.string.failed_dialog));
						dialog.show();
					}
				}
			}
		}).start();
	}

}
