package sherlockphonez.myphonecontroll.view;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sherlockphonez.myphonecontroll.model.LocationInfo;
import sherlockphonez.retrofit.Retrofit;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.example.sherlockphonez.AppController;
import com.example.sherlockphonez.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LocationView extends FragmentActivity{

	private GoogleMap gmap;
	private ArrayList<LocationInfo> items=new ArrayList<LocationInfo>();

	private MarkerOptions markertemp = null;
	private ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();

	private double latitude = 0;
	private double longitude = 0;

	private Paint paint;
	private Canvas canvas;

	private AppController app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.location_view);

		app = (AppController)getApplicationContext();

		gmap= ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		paint = new Paint();
		paint.setARGB(70,255, 0, 0);  // ������, �� �����ϰ� �ϴϱ� ���� ����
		paint.setStrokeWidth(2);  // ����
		paint.setAntiAlias(true);  // �ε巴�� ó��.
		canvas = new Canvas();

		getposition();




	}


	void init(){
		if(!markers.isEmpty()){

			for(int i=0; i<markers.size(); i++){
				gmap.addMarker(markers.get(i));
				if(i!=0){
					gmap.addPolyline(new PolylineOptions().add(markers.get(i-1).getPosition(),
							markers.get(i).getPosition()).color(Color.RED).width(4));
				}

			}
			LatLng startingPoint = 
					new LatLng(markers.get(markers.size()-1).getPosition().latitude,markers.get(markers.size()-1).getPosition().longitude);
			gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 13));
		}else{
			LatLng startingPoint = 
					new LatLng(37,127);
			gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 8));
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setPositiveButton("aaa", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					
				}
			});
			alert.setMessage("aaaa.");
			alert.show();
		}
	}

	public void getposition(){
		final String phoneNum = app.getOtherPhoneNum();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.getlocation(phoneNum, new Callback<JsonObject>() {

						@Override
						public void failure(RetrofitError arg0) {
							// TODO Auto-generated method stub
							int a=0;
						}

						@Override
						public void success(JsonObject result, Response arg1) {
							// TODO Auto-generated method stub
							if(result.get("result").getAsString().equalsIgnoreCase("Success")){
								JsonArray arr = result.get("location").getAsJsonArray();
								for(int i=0; i<arr.size(); i++){
									JsonObject obj = arr.get(i).getAsJsonObject();
									String phoneNum = obj.get("u_phonenum").getAsString();
									double l_lat = obj.get("l_lat").getAsDouble();
									double l_long = obj.get("l_long").getAsDouble();
									String l_time = obj.get("l_time").getAsString();
									LocationInfo loc = new LocationInfo(phoneNum, l_lat, l_long, l_time);
									
									markertemp = new MarkerOptions()
									.position(new LatLng(l_lat,l_long)).title(loc.locationtime)
									.snippet(loc.locationtime);
									//markertemp.snippet(temp.locationtime);
									markers.add(markertemp);
									items.add(loc);
									
								}
								init();
							}
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
//		(app.getServerIp()+"sendlocation.php", nameValuePair);
//
//
//		new AsyncExecutor<ArrayList<NameValuePair>, String>()
//		.setCallable(callable)
//		.setCallback(new AsyncCallback<String>() {
//
//			@Override
//			public void onResult(String result) {
//				// TODO Auto-generated method stub
//				JSONArray ja;
//				try {
//					ja = new JSONArray(result);
//					for(int i=0; i<ja.length(); i++)
//					{
//						JSONObject order = ja.getJSONObject(i);
//
//						LocationInfo temp=new LocationInfo(order.getString("phoneNum"),order.getString("latitude"),
//								order.getString("longitude"),order.getString("l_time"));
//
//						latitude = Double.valueOf(temp.latitude);
//						longitude = Double.valueOf(temp.longitude);
//
//						markertemp = new MarkerOptions()
//						.position(new LatLng(latitude,longitude)).title(temp.locationtime)
//						.snippet(temp.locationtime);
//						//markertemp.snippet(temp.locationtime);
//						markers.add(markertemp);
//						items.add(temp);
//					}
//
//					init();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
}
