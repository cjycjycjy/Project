package ddoraemi.home.view;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.model.QnA;
import ddoraemi.detailedgroupinfo.model.QnAReply;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_View;
import ddoraemi.detailedgroupinfo.view.DetailedQnA_reply_View;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_View;
import ddoraemi.home.model.AlarmListHelper;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import ddoraemi.home.model.PushMessage;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class AlarmView extends Fragment implements OnItemClickListener{
	ListView listview;
	AppController app;
	SQLiteDatabase db;
	AlarmListHelper helper;
	ArrayList<PushMessage> array;
	Adapter_AlarmList adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_alarm, null);
		listview = (ListView) v.findViewById(R.id.fragment_alarmlistview);
		app = (AppController) getActivity().getApplicationContext();
		helper = new AlarmListHelper(getActivity());
		try {
			db = helper.getWritableDatabase();
		} catch (SQLException e) {
			db = helper.getReadableDatabase();
		}
		array=helper.selectMylist(db,app.getId());
		adapter=new Adapter_AlarmList(getActivity(), R.layout.item_alarm, array);

		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
		return v;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		PushMessage p= adapter.getItem(arg2);
		int pk=p.getPk();
		helper.check(db, pk, app.getId());
		array.clear();
		array.addAll(helper.selectMylist(db, app.getId()));
		adapter.notifyDataSetChanged();
		app.getRootActivity().countMyAlarm();
		String type = adapter.getItem(arg2).getType().toString();
		int id = adapter.getItem(arg2).getP_g_id();
		if(type.equalsIgnoreCase("q_r") || type.equalsIgnoreCase("q")){
			goToSelectedAlarmInfo_detailedQnA(getActivity(),id );
		}
		else if(type.equalsIgnoreCase("b") || type.equalsIgnoreCase("c")){
			goToSelectedAlarmInfo_Group(getActivity(),id);
		}
	}

	public void goToSelectedAlarmInfo_program(final Context context,final int id){
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);

					retrofit.getprogram_pid(id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject result//��Ű��
								,Response response) {
							progressdialog.dismiss();
							String dataresult=result.get("result").getAsString();
							if (context != null) {
								if(dataresult.equals("200"))
								{
								ProgramData data = new ProgramData();
								int p_id = (result.get("p_id")).getAsInt();
								String p_name = (result.get("p_name")).getAsString();
								String p_town_name = (result.get("p_town_name")).getAsString();

								int p_min_persons = (result.get("p_min_persons")).getAsInt();
								int p_max_persons = (result.get("p_max_persons")).getAsInt();
								int p_lead_time = (result.get("p_lead_time")).getAsInt();		

								String p_note = (result.get("p_note")).getAsString();
								String p_addr_1 = (result.get("p_addr_1")).getAsString();
								String p_addr_2 = (result.get("p_addr_2")).getAsString();
								String p_addr_3 = (result.get("p_addr_3")).getAsString();
								String p_addr_4 = (result.get("p_addr_4")).getAsString();
								String p_addr_5 = (result.get("p_addr_5")).getAsString();
								String p_addr_6 = (result.get("p_addr_6")).getAsString();

								int a_id = (result.get("a_id")).getAsInt();
								String p_url = (result.get("p_url")).getAsString();

								String p_info = (result.get("p_info")).getAsString();
								String e_name = (result.get("e_name")).getAsString();
								String p_cost_adult = (result.get("p_cost_adult")).getAsString();
								String p_cost_kid = (result.get("p_cost_kid")).getAsString();

								String p_available_season = (result.get("p_available_season")).getAsString();

								String p_preparation_item = (result.get("p_preparation_item")).getAsString();

								String p_tel = (result.get("p_tel")).getAsString();

								int p_is_available_overnight= (result.get("p_is_available_overnight")).getAsInt(); 
								long p_grade= (result.get("p_grade")).getAsLong();
								String p_photo_url = (result.get("p_photo_url").getAsString());
								JsonArray p_curriculum = (result.get("p_curriculum").getAsJsonArray());

								ArrayList<String> cur_list = new ArrayList<>();

								for(int k=0; k<p_curriculum.size(); k++){
									JsonObject cur = (JsonObject)p_curriculum.get(k);
									String cur_info = cur.get("cur").getAsString();
									cur_list.add(cur_info);
								}
								data= new ProgramData(p_id, p_name, p_info, e_name, p_cost_adult,
										p_cost_kid, p_min_persons, p_max_persons, p_available_season, p_lead_time, p_preparation_item,
										p_town_name, p_addr_1, p_addr_2, p_addr_3, p_addr_4, p_addr_5, p_addr_6, p_tel, 
										p_url, p_is_available_overnight, p_photo_url, 
										cur_list, p_note, p_grade, a_id);
								Intent intent = new Intent(getActivity(), DetailedItemInfo_View.class);
								intent.putExtra("item", data);
								getActivity().startActivity(intent);
							}
							}
							else
							{
								new AlertDialog.Builder(context)
								.setMessage("데이터를 받아오는데 실패하였습니다.")
								.setPositiveButton(
										"확인",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dia,
													int which) {
												dia.dismiss();
											}
										}).show();
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton(
									"확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia,
												int which) {
											dia.dismiss();
										}
									}).show();
						}
					});



				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
					new AlertDialog.Builder(context)
					.setMessage("데이터를 받아오는데 실패하였습니다.")
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();
	}

	public void goToSelectedAlarmInfo_detailedQnA(final Context context,final int id){
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);

					retrofit.getQna(id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject data//��Ű��
								,Response response) {

							progressdialog.dismiss();
							if (context != null) {

								String result =data.get("result").getAsString();
								if(result.equals("200"))
								{
								JsonObject dataobj=data.getAsJsonObject("question");
								int q_id = (dataobj.get("q_id")).getAsInt();
								JsonObject userobj = dataobj.get("q_user").getAsJsonObject(); 
								String u_id = userobj.get("u_id").getAsString();
								int g_id = (dataobj.get("g_id")).getAsInt();
								String q_content = (dataobj.get("q_content")).getAsString();
								int q_year = (dataobj.get("q_year")).getAsInt();
								int q_month = (dataobj.get("q_month")).getAsInt();
								int q_day = (dataobj.get("q_day")).getAsInt();
								int q_r_num = (dataobj.get("q_r_num")).getAsInt();
								ArrayList<QnAReply> question_reply;
								question_reply = new ArrayList<QnAReply>();
								JsonArray reply = dataobj.get("question_reply").getAsJsonArray();
								String u_photo_url = "empty";
								if(!userobj.get("u_photo_url").isJsonNull()){
									u_photo_url = userobj.get("u_photo_url").getAsString();
								}

								for(int j=0; j<reply.size(); j++){
									JsonObject obj = (JsonObject)reply.get(j);
									JsonObject qr_userobj = obj.get("q_r_user").getAsJsonObject();
									int q_r_id = (obj.get("q_r_id")).getAsInt();
									int q_id2 = (obj.get("q_id")).getAsInt();
									String q_r_content= (obj.get("q_r_content")).getAsString();
									int q_r_year = (obj.get("q_r_year")).getAsInt();
									int q_r_month = (obj.get("q_r_month")).getAsInt();
									int q_r_day = (obj.get("q_r_day")).getAsInt();
									String u_id2 = (qr_userobj.get("u_id")).getAsString();
									String qr_u_photo_url = "empty";
									if(!qr_userobj.get("u_photo_url").isJsonNull()){
										qr_u_photo_url = (qr_userobj.get("u_photo_url")).getAsString();
									}		
									question_reply.add(new QnAReply(q_r_id, q_id2, q_r_content, q_r_year, q_r_month, q_r_day, u_id2,qr_u_photo_url));
								}

								QnA qna = new QnA(q_id, u_id, g_id, q_content, q_year, q_month, q_day, 
										q_r_num, question_reply,u_photo_url);
								Intent intent = new Intent(getActivity(), DetailedQnA_reply_View.class);
								intent.putExtra("qna", qna);
								getActivity().startActivity(intent);
								}
								else {
									new AlertDialog.Builder(context)
									.setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton(
											"확인",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dia,
														int which) {
													dia.dismiss();
												}
											}).show();

								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton(
									"확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia,
												int which) {
											dia.dismiss();
										}
									}).show();
						}
					});



				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
					new AlertDialog.Builder(context)
					.setMessage("데이터를 받아오는데 실패하였습니다.")
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();
	}
	public void goToSelectedAlarmInfo_Group(final Context context,final int id){
		final ProgressDialog progressdialog = new ProgressDialog(context);
		progressdialog.setMessage(context.getString(R.string.loading));
		progressdialog.setIndeterminate(true);
		progressdialog.setCancelable(false);
		progressdialog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class);

					retrofit.getGroup(id,new Callback<JsonObject>() {
						@Override
						public void success(JsonObject data//��Ű��
								,Response response) {
							if (context != null) {
								progressdialog.dismiss();
								String result =data.get("result").getAsString();
								if(result.equals("200"))
								{
								JsonObject dataobj=data.getAsJsonObject("gatheing");
								int p_id = (dataobj.get("p_id")).getAsInt();
								String p_name = (dataobj.get("p_name")).getAsString();
								String p_addr = (dataobj.get("p_addr")).getAsString();
								int g_id= (dataobj.get("g_id")).getAsInt();
								String g_name= (dataobj.get("g_name")).getAsString();


								String g_info= (dataobj.get("g_info")).getAsString();
								int g_status= (dataobj.get("g_status")).getAsInt();
								int g_persons= (dataobj.get("g_persons")).getAsInt();
								String e_name = (dataobj.get("e_name")).getAsString();


								String u_id = (dataobj.get("u_id")).getAsString();

								int g_want_persons= (dataobj.get("g_want_persons")).getAsInt();

								int g_open_year= (dataobj.get("g_open_year")).getAsInt();
								int g_open_month= (dataobj.get("g_open_month")).getAsInt();
								int g_open_day= (dataobj.get("g_open_day")).getAsInt();
								int g_close_year= (dataobj.get("g_close_year")).getAsInt();
								int g_close_month= (dataobj.get("g_close_month")).getAsInt();
								int g_close_day= (dataobj.get("g_close_day")).getAsInt();
								int g_start_year= (dataobj.get("g_start_year")).getAsInt();
								int g_start_month= (dataobj.get("g_start_month")).getAsInt();
								int g_start_day= (dataobj.get("g_start_day")).getAsInt();
								int g_start_hour= (dataobj.get("g_start_hour")).getAsInt();
								int g_start_minute= (dataobj.get("g_start_minute")).getAsInt();
								int g_end_hour= (dataobj.get("g_end_hour")).getAsInt();
								int g_end_minute= (dataobj.get("g_end_minute")).getAsInt();
								String p_photo_url = (dataobj.get("p_photo_url")).getAsString();
								Group temp=new Group(g_id, p_id, u_id, g_name, g_persons, g_want_persons,
										g_info, g_status, g_open_year, g_open_month, g_open_day, g_close_year, g_close_month, 
										g_close_day, g_start_year, g_start_month, g_start_day, g_start_hour, g_start_minute, g_end_hour, 
										g_end_minute, e_name, p_name, p_addr,p_photo_url);
								temp.findGeoPoint(temp.getP_addr(), getActivity());

								Intent intent = new Intent(getActivity(), DetailedGroupInfo_View.class);
								intent.putExtra("group", temp);
								getActivity().startActivity(intent);
								}
								else 
								{
									new AlertDialog.Builder(context)
									.setMessage("데이터를 받아오는데 실패하였습니다.")
									.setPositiveButton(
											"확인",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dia,
														int which) {
													dia.dismiss();
												}
											}).show();

								}
							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							progressdialog.dismiss();
							new AlertDialog.Builder(context)
							.setMessage("데이터를 받아오는데 실패하였습니다.")
							.setPositiveButton(
									"확인",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dia,
												int which) {
											dia.dismiss();
										}
									}).show();
						}
					});



				} catch (Throwable ex) {
					ex.printStackTrace();
					progressdialog.dismiss();
					new AlertDialog.Builder(context)
					.setMessage("데이터를 받아오는데 실패하였습니다.")
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dia, int which) {
							dia.dismiss();
						}
					}).show();
				}
			}
		}).start();
	}
}
