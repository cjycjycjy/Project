package ddoraemi.dialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.ClipData.Item;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import ddoraemi.home.model.ProgramData;
import ddoraemi.programlist.presenter.OnProgramlist_location_FinishenListener;
import ddoraemi.retrofit.Retrofit;
import ddoraemi.start.R;

public class Dialog_Admin_SelectProgramName extends Dialog{

	ListView list;
	ArrayList<ProgramData> program;
	String location;
	ImageView btn_ok;
	Dialog_Admin_SelectProgramlist_adapter adapter;
	public Dialog_Admin_SelectProgramName(Context context, String location) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);//
		setContentView(R.layout.dialog_admin_selectprogram);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		btn_ok = (ImageView)findViewById(R.id.iv_admin_selectprogram);
		this.location = location;
		program = new ArrayList<ProgramData>();
		list = (ListView)findViewById(R.id.lv_admin_programlist);
		
		getData(getContext(), "1", location);
		
						
		
	}
	public void getData(final Context context, final String category, final String p_addr_1) {
		// TODO Auto-generated method stub

		new Thread(new Runnable() {
			public void run() {
				try {
					RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(Retrofit.ROOT) // call your base url
					.build();
					Retrofit retrofit = restAdapter.create(Retrofit.class); 
					retrofit.getProgramList_location(category,p_addr_1,new Callback<JsonArray>() {
						@Override
						public void success(JsonArray result//
								,Response response) {
							if (context != null) {
								program.clear();

								for(int i=0; i<result.size(); i++){
									JsonObject dataobj = (JsonObject)result.get(i);
									int p_id = (dataobj.get("p_id")).getAsInt();
									String p_name = (dataobj.get("p_name")).getAsString();
									String p_town_name = (dataobj.get("p_town_name")).getAsString();

									int p_min_persons = (dataobj.get("p_min_persons")).getAsInt();
									int p_max_persons = (dataobj.get("p_max_persons")).getAsInt();
									int p_lead_time = (dataobj.get("p_lead_time")).getAsInt();		

									String p_note = (dataobj.get("p_note")).getAsString();
									String p_addr_1 = (dataobj.get("p_addr_1")).getAsString();
									String p_addr_2 = (dataobj.get("p_addr_2")).getAsString();
									String p_addr_3 = (dataobj.get("p_addr_3")).getAsString();
									String p_addr_4 = (dataobj.get("p_addr_4")).getAsString();
									String p_addr_5 = (dataobj.get("p_addr_5")).getAsString();
									String p_addr_6 = (dataobj.get("p_addr_6")).getAsString();

									int a_id = (dataobj.get("a_id")).getAsInt();
									String p_url = (dataobj.get("p_url")).getAsString();

									String p_info = (dataobj.get("p_info")).getAsString();
									String e_name = (dataobj.get("e_name")).getAsString();
									String p_cost_adult = (dataobj.get("p_cost_adult")).getAsString();
									String p_cost_kid = (dataobj.get("p_cost_kid")).getAsString();

									String p_available_season = (dataobj.get("p_available_season")).getAsString();

									String p_preparation_item = (dataobj.get("p_preparation_item")).getAsString();

									String p_tel = (dataobj.get("p_tel")).getAsString();

									int p_is_available_overnight= (dataobj.get("p_is_available_overnight")).getAsInt(); 
									long p_grade= (dataobj.get("p_grade")).getAsLong();
									String p_photo_url = (dataobj.get("p_photo_url").getAsString());
									JsonArray p_curriculum = (dataobj.get("p_curriculum").getAsJsonArray());

									ArrayList<String> cur_list = new ArrayList<>();

									//담당자 foreign key 

									for(int k=0; k<p_curriculum.size(); k++){
										JsonObject cur = (JsonObject)p_curriculum.get(k);
										String cur_info = cur.get("cur").getAsString();
										cur_list.add(cur_info);
									}
									program.add(new ProgramData(p_id, p_name, p_info, e_name, p_cost_adult,
											p_cost_kid, p_min_persons, p_max_persons, p_available_season, p_lead_time, p_preparation_item,
											p_town_name, p_addr_1, p_addr_2, p_addr_3, p_addr_4, p_addr_5, p_addr_6, p_tel, 
											p_url, p_is_available_overnight, p_photo_url, 
											cur_list, p_note, p_grade, a_id));

								}
								adapter = new Dialog_Admin_SelectProgramlist_adapter(getContext(),
										R.layout.item_admin_selectprogramlist, program);
								list.setAdapter(adapter);

							}
						}

						@Override
						public void failure(RetrofitError retrofitError) {
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
