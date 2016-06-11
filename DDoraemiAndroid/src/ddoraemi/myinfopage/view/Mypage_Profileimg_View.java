package ddoraemi.myinfopage.view;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import ddoraemi.appcontroller.AppController;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class Mypage_Profileimg_View extends Activity implements OnClickListener {

	AppController app;
	ImageView btn_back, iv_profile;
	TextView btn_save;
	public static String path;
	public static Bitmap image_bitmap = null;
	final int REQ_CODE_SELECT_IMAGE = 100;
	final int REQ_CODE_CAPTURE_IMAGE = 200;
	Uri uri;
	private static final String TYPE_IMAGE = "image/*";
	private Uri mTempImageUri;
	int type = 0;
	public static Context mcontext;
	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profileimage_popup);
		app = (AppController) getApplicationContext();
		type = getIntent().getIntExtra("type", -1);
		mcontext = this;
		init();

	}

	public void init() {

		if (type == 1) {
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType( TYPE_IMAGE );
			intent.putExtra( "outputFormat", Bitmap.CompressFormat.JPEG.toString() );
			intent.putExtra( MediaStore.EXTRA_OUTPUT, mTempImageUri );
			startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

		} else if (type == 2) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, REQ_CODE_CAPTURE_IMAGE);
		}

		btn_back = (ImageView) findViewById(R.id.btn_left_menu_profileimg_popup);
		btn_back.setOnClickListener(this);

		btn_save = (TextView) findViewById(R.id.btn_save);
		btn_save.setOnClickListener(this);

		iv_profile = (ImageView) findViewById(R.id.btn_profileimg_popup);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_left_menu_profileimg_popup:
			finish();
			break;
		case R.id.btn_save:
			requestChangeProfileImg(this, app.getId());
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Toast.makeText(getContext(), "resultCode :
		// "+resultCode,Toast.LENGTH_SHORT).show();

		if (requestCode == REQ_CODE_SELECT_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {
					 uri=data.getData();
					 InputStream stream;
					try {
						stream = getContentResolver().openInputStream(uri);
						 bitmap=BitmapFactory.decodeStream(stream);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					iv_profile.setImageBitmap(bitmap);
					
			} else {
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(mcontext, "사진가져오기실패");
				dialog.show();
			}
		}
		if (requestCode == REQ_CODE_CAPTURE_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {
				bitmap=(Bitmap)data.getExtras().get("data");
				iv_profile.setImageBitmap(bitmap);
				

		
			} else {
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(mcontext, "사진가져오기실패.");
				dialog.show();
			}
		}
	}

	public synchronized static Bitmap GetRotatedBitmap(Bitmap bitmap, int degrees) {
		if (degrees != 0 && bitmap != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != b2) {
					bitmap.recycle();
					bitmap = b2;
				}
			} catch (OutOfMemoryError ex) {
				// We have no memory to rotate. Return the original bitmap.
			}
		}

		return bitmap;
	}

	public synchronized static int GetExifOrientation(String filepath) {
		int degree = 0;
		ExifInterface exif = null;

		try {
			exif = new ExifInterface(filepath);
		} catch (IOException e) {
			Log.e("TAG", "cannot read exif");
			e.printStackTrace();
		}

		if (exif != null) {
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

			if (orientation != -1) {
				// We only recognize a subset of orientation tag values.
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;

				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;

				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
				}

			}
		}

		return degree;
	}



	public void requestChangeProfileImg(final Context context, final String u_id) {
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage("잠시만 기다려주세요");
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();

		new Thread(new Runnable() {
			public void run() {
				try {
					JsonObject info = new JsonObject();
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(CompressFormat.JPEG, 100, stream);
					final byte[] bytearray = stream.toByteArray();
					final String s = Base64.encodeToString(bytearray, Base64.DEFAULT);
					info.addProperty("u_id", u_id);
					info.addProperty("photo", s);
					RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Retrofit.ROOT) // call
																									// your
																									// base
																									// url
							.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);
					retrofit.modifyUserPhoto(info, new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result, Response response) {
							progressdialog.dismiss();
							String resultstr=result.get("result").getAsString();
								if (resultstr.equals("200")) {
									String url=result.get("u_photo_url").getAsString();
									app.getUser().setU_photo_url(url);
									Intent intent=new Intent();
									intent.putExtra("type", type);
									if(type==2)
									intent.putExtra("bitmap", bytearray);
									else
									{
										intent.putExtra("uri", uri);
									}
									Mypage_Profileimg_View.this.setResult(Activity.RESULT_OK, intent);
									Mypage_Profileimg_View.this.finish();
								} else {
									Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,
											"프로필사진 변경에 실패하였습니다.\n잠시후 다시 시도해주세요.");
									dialog.show();
								}
							
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();

				            Log.e("failure", String.valueOf(retrofitError.getResponse().getStatus()));
				            Log.e("failure", String.valueOf(retrofitError.getResponse().getBody()));
				            Log.v("fail", retrofitError.getMessage());

								Dialog_BaseDialog dialog = new Dialog_BaseDialog(context,
										"사진전송 실패");
								dialog.show();
							
						}
					});

				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
						Dialog_BaseDialog dialog = new Dialog_BaseDialog(context, getString(R.string.failed_dialog));
						dialog.show();
					
				}
			}
		}).start();

	}
}
