package ddoraemi.joining.view;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class FindPasswordAcitivity extends Activity implements OnClickListener {
	EditText phonenum;
	EditText u_id;
	ImageView sendbt, back;
	private String phoneregex = "^[0-9]{10,11}$";
	String str_phone = "";
	String str_id = "";
	Context context;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassword);
		init();
	}

	public void init() {
		phonenum = (EditText) findViewById(R.id.FindPasswordAcitivity_EditPhone);
		u_id = (EditText) findViewById(R.id.FindPasswordAcitivity_EditId);
		sendbt = (ImageView) findViewById(R.id.FindPasswordAcitivity_Imgfind);
		sendbt.setOnClickListener(this);
		back = (ImageView) findViewById(R.id.FindPasswordAcitivity_ImgBack);
		back.setOnClickListener(this);
	}

	public void goToDone(){
		Intent intent = new Intent(FindPasswordAcitivity.this, FindPasswordDoneAcitvity.class);
		intent.putExtra("phonenum", str_phone);
		startActivity(intent);
		finish();
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.FindPasswordAcitivity_Imgfind) {
			str_phone = phonenum.getText().toString();
			str_id = u_id.getText().toString();
			if (str_phone.matches(phoneregex) || str_id.isEmpty()) {
				getTemporaryPasswd(this, str_phone, str_id);
			} else {
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(FindPasswordAcitivity.this,
						"입력이 잘못되었습니다.");
				dialog.show();
			}
		} else {
			finish();
		}
	}
	public void getTemporaryPasswd(final Context context, final String phonenum, final String u_id ){
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base
					// url
					.build();
					Retrofit retofit = restAdapter.create(Retrofit.class);
					retofit.findpasswd(u_id, phonenum,
							new Callback<String>() {
						@Override
						public void success(String result,
								Response response) {
							if(result.equalsIgnoreCase("200")){
								goToDone();
							}
								
							else{
								new Dialog_BaseDialog(FindPasswordAcitivity.this,
										"정보가 일치하지 않습니다.").show();
							}
								
						}
						@Override
						public void failure(
								RetrofitError retrofitError) {
							new Dialog_BaseDialog(FindPasswordAcitivity.this,
									"서버와의 통신에 실패하였습니다.").show();

						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					new Dialog_BaseDialog(FindPasswordAcitivity.this,
							"서버와의 통신에 실패하였습니다.").show();
				}
			}

		}).start();

	}

}
