package ddoraemi.creategroup.presenter;

import java.util.ArrayList;

import android.widget.EditText;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.appcontroller.AppController;
import ddoraemi.creategroup.model.CreateGroup_Interactor;
import ddoraemi.creategroup.model.CreateGroup_Interactor_Inteface;
import ddoraemi.creategroup.view.CreateGroup_View;
import ddoraemi.home.model.Group;

public class CreateGroup_presenter implements CreateGroup_presenter_Interface,OnCreateGroup_FinishenListener{

	CreateGroup_View view;
	CreateGroup_Interactor_Inteface interactor;
	ArrayList<Group> group;
	AppController app;
	public CreateGroup_presenter(CreateGroup_View view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		app = (AppController)view.getApplicationContext();
		this.group = new ArrayList<>();
		interactor = new CreateGroup_Interactor();
	}
	@Override
	public void validatecredential(String event,Group info,EditText Edit_gname,EditText Edit_introduce,int p_id
			,int hopeperson) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("showDatePopUp")){
			view.showDatePopUp();
		}else if(event.equalsIgnoreCase("clickPlusBtn")){
			view.clickPlusBtn();
		}else if(event.equalsIgnoreCase("clickMinusBtn")){
			view.clickMinusBtn();
		}else if(event.equalsIgnoreCase("createGroup")){
			if (info != null && Edit_gname.getTextSize() > 0
					&& Edit_introduce.getTextSize() > 0) {
				view.showDialog();
				String u_id = app.getId();
				String g_name = Edit_gname.getText().toString();
				int g_want_persons = hopeperson;
				String g_info = Edit_introduce.getText().toString();
				int g_open_year = info.getG_open_year();
				int g_open_month = info.getG_open_month();
				int g_open_day = info.getG_open_day();
				int g_close_year = info.getG_close_year();
				int g_close_month = info.getG_close_month();
				int g_close_day = info.getG_close_day();
				int g_start_year = info.getG_start_year();
				int g_start_month = info.getG_start_month();
				int g_start_day = info.getG_start_day();
				int g_start_hour = info.getG_start_hour();
				int g_start_min = info.getG_start_minute();
				int g_end_hour = info.getG_end_hour();
				int g_end_min = info.getG_end_minute();
				interactor.createGroup(view,p_id, u_id, g_name, g_want_persons, g_info, g_open_year,
						g_open_month, g_open_day, g_close_year, g_close_month, g_close_day, g_start_year, 
						g_start_month, g_start_day, g_start_hour, g_start_min, g_end_hour, g_end_min, this);
			}else{
				view.existempty();
			}
		}
	}

	@Override
	public void onCreateGroupSuccess(JsonObject result) {
		// TODO Auto-generated method stub
		String r = result.get("result").getAsString();
		if(r.equalsIgnoreCase("200")){
			JsonArray result_arr = result.get("gathering").getAsJsonArray();
			group.clear();
			for (int i = 0; i < result_arr.size(); i++) {
				JsonObject dataobj = (JsonObject)result_arr.get(i);
				int p_id = (dataobj.get("p_id"))
						.getAsInt();
				String p_name = (dataobj
						.get("p_name"))
						.getAsString();
				String p_addr = (dataobj
						.get("p_addr"))
						.getAsString();
				int g_id = (dataobj.get("g_id"))
						.getAsInt();
				String g_name = (dataobj
						.get("g_name"))
						.getAsString();

				String g_info = (dataobj
						.get("g_info"))
						.getAsString();
				int g_status = (dataobj
						.get("g_status"))
						.getAsInt();
				int g_persons = (dataobj
						.get("g_persons"))
						.getAsInt();
				String e_name = (dataobj
						.get("e_name"))
						.getAsString();

				String u_id = (dataobj.get("u_id"))
						.getAsString();

				int g_want_persons = (dataobj
						.get("g_want_persons"))
						.getAsInt();

				int g_open_year = (dataobj
						.get("g_open_year"))
						.getAsInt();
				int g_open_month = (dataobj
						.get("g_open_month"))
						.getAsInt();
				int g_open_day = (dataobj
						.get("g_open_day"))
						.getAsInt();
				int g_close_year = (dataobj
						.get("g_close_year"))
						.getAsInt();
				int g_close_month = (dataobj
						.get("g_close_month"))
						.getAsInt();
				int g_close_day = (dataobj
						.get("g_close_day"))
						.getAsInt();
				int g_start_year = (dataobj
						.get("g_start_year"))
						.getAsInt();
				int g_start_month = (dataobj
						.get("g_start_month"))
						.getAsInt();
				int g_start_day = (dataobj
						.get("g_start_day"))
						.getAsInt();
				int g_start_hour = (dataobj
						.get("g_start_hour"))
						.getAsInt();
				int g_start_minute = (dataobj
						.get("g_start_minute"))
						.getAsInt();
				int g_end_hour = (dataobj
						.get("g_end_hour"))
						.getAsInt();
				int g_end_minute = (dataobj
						.get("g_end_minute"))
						.getAsInt();
				String p_photo_url = (dataobj.get("p_photo_url")).getAsString();
				group.add(new Group(g_id, p_id,
						u_id, g_name, g_persons,
						g_want_persons, g_info,
						g_status, g_open_year,
						g_open_month, g_open_day,
						g_close_year,
						g_close_month, g_close_day,
						g_start_year,
						g_start_month, g_start_day,
						g_start_hour,
						g_start_minute, g_end_hour,
						g_end_minute, e_name,
						p_name, p_addr,p_photo_url));
			}


			view.createfinish(group);
		}else if(r.equalsIgnoreCase("500")){
			view.showOverlapDialog();
		}
	}
	@Override
	public void onfailure() {
		// TODO Auto-generated method stub
		view.showFailureDialog();
	}

}
