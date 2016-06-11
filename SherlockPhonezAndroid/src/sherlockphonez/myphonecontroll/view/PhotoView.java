package sherlockphonez.myphonecontroll.view;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.retrofit.Retrofit;

import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.GridView;


public class PhotoView extends Activity implements OnClickListener{

	private ArrayList<Photo> photo;

	private GridView gv_photo = null;
	private PhotoAdapter photoAdapter;

	private Photo temp=null;
	private boolean lastitemVisibleFlag= false; 

	private boolean isDelete=false;

	private AppController app;

	private Button delete;
	private Button select;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.photo_view);		

		app = (AppController)getApplicationContext();
		findViewById(R.id.btn_cancel_photoview).setOnClickListener(this);
		select = (Button)findViewById(R.id.btn_select);
		select.setOnClickListener(this);

		delete = (Button)findViewById(R.id.btn_delete);
		delete.setOnClickListener(this);
		delete.setEnabled(false);
		
		select.setVisibility(View.GONE);
		photo = new ArrayList<Photo>();
		gv_photo = (GridView)findViewById(R.id.gv_photo);
		async();
	}

	public void async(){
		photo.clear();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.getImage(app.getOtherPhoneNum(),new Callback<JsonArray>() {

						@Override
						public void failure(RetrofitError arg0) {
							// TODO Auto-generated method stub
							int a=0;
						}

						@Override
						public void success(JsonArray result, Response arg1) {
							// TODO Auto-generated method stub
							
							for(int i=0; i<result.size(); i++){
								JsonObject obj = new JsonObject();
								obj = result.get(i).getAsJsonObject();
								String photoUrl = obj.get("p_photo_url").getAsString();
								String camera_time = obj.get("p_time").getAsString();
								String url = "http://203.252.166.151:9090/sp/photo/"+photoUrl;
								photo.add(new Photo(url, camera_time));
								
							}

							photoAdapter = new PhotoAdapter(PhotoView.this, R.layout.photo_adapter, photo);
							gv_photo.smoothScrollBy(1, 1000);
							gv_photo.setAdapter(photoAdapter);	
							gv_photo.setOnItemClickListener(new OnItemClickListener(){
								@Override
								public void onItemClick(AdapterView parent, View v, int position, long id){
									if(!isDelete){
										callImageViewer(position);
									}else{
										CheckedTextView c=(CheckedTextView)v.findViewById(R.id.rl_photo1);
										c.setChecked(!c.isChecked());							
										if(!photo.get(position).isChecked()){
											photo.get(position).setChecked(true);
										}else{
											photo.get(position).setChecked(false);
										}
									}
								}
							});
						}
					});
				} catch (Throwable ex) {

				}
			}
		}).start();
		
		
//		ArrayList<NameValuePair> nameValuePair=null;
//		try {
//			nameValuePair=new ArrayList<NameValuePair>(1);
//			nameValuePair.add(new BasicNameValuePair("phoneNum",app.getOtherPhoneNum()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		AsyncCallable<ArrayList<NameValuePair>, String> callable=
//				new AsyncCallable<ArrayList<NameValuePair>, String>
//		(app.getServerIp()+"sendcamera.php", nameValuePair);
//
//
//		new AsyncExecutor<ArrayList<NameValuePair>, String>()
//		.setCallable(callable)
//		.setCallback(new AsyncCallback<String>() {
//
//			@Override
//			public void onResult(String result) {
//				// TODO Auto-generated method stub
//
//				JSONArray ja;
//				try {
//					ja = new JSONArray(result);
//					for(int i=0; i<ja.length(); i++)
//					{
//						JSONObject order = ja.getJSONObject(i);
//						temp=new Photo(app.getServerIp(), order.getString("path"),
//								order.getString("camera_time"));
//						photo.add(temp);
//					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				photoAdapter = new PhotoAdapter(PhotoView.this, R.layout.photo_adapter, photo);
//				gv_photo.smoothScrollBy(1, 1000);
//				gv_photo.setAdapter(photoAdapter);	
//				gv_photo.setOnItemClickListener(new OnItemClickListener(){
//					@Override
//					public void onItemClick(AdapterView parent, View v, int position, long id){
//						if(!isDelete){
//							callImageViewer(position);
//						}else{
//							CheckedTextView c=(CheckedTextView)v.findViewById(R.id.rl_photo1);
//							c.setChecked(!c.isChecked());							
//							if(!photo.get(position).isChecked()){
//								photo.get(position).setChecked(true);
//							}else{
//								photo.get(position).setChecked(false);
//							}
//						}
//					}
//				});
//
//			}
//
//			@Override
//			public void exceptionOccured(Exception e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void cancelled() {
//				// TODO Auto-generated method stub
//
//			}
//		})
//		.execute();
	}

	public final void callImageViewer(int selectedIndex){
		Intent i = new Intent(PhotoView.this, ImagePopup.class);
		i.putExtra("photolist",photo);
		i.putExtra("index", selectedIndex);
		startActivityForResult(i, 1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_cancel_photoview){
			finish();
		}else if(v.getId()==R.id.btn_select){
			isDelete=!isDelete;
			delete.setEnabled(isDelete);

			if(isDelete){
				select.setText("���");
			}
			else{
				photoAdapter.setIsCanceled(true);
				for(int i=0; i< photo.size(); i++){
					photo.get(i).setChecked(false);
				}
				photoAdapter.notifyDataSetChanged();
				select.setText("����");
			}

		}else if(v.getId()==R.id.btn_delete){
			ArrayList<NameValuePair> nameValuePair=null;
			try {
				nameValuePair=new ArrayList<NameValuePair>();
				for(int i=0; i<photo.size(); i++){
					if(photo.get(i).isChecked()){
						nameValuePair.add(new BasicNameValuePair(Integer.toString(i), photo.get(i).getPhotoUrl()));
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			AsyncCallable<ArrayList<NameValuePair>, String> callable=
//					new AsyncCallable<ArrayList<NameValuePair>, String>
//			(app.getServerIp()+"deletephoto.php", nameValuePair);
//
//
//			new AsyncExecutor<ArrayList<NameValuePair>, String>()
//			.setCallable(callable)
//			.setCallback(new AsyncCallback<String>() {
//
//				@Override
//				public void onResult(String result) {
//					// TODO Auto-generated method stub
//					//async();
//					async();
//				}
//
//				@Override
//				public void exceptionOccured(Exception e) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void cancelled() {
//					// TODO Auto-generated method stub
//
//				}
//			}).execute(nameValuePair);
		}
	}
}
