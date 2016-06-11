package ddoraemi.detailediteminfo.presenter;

import java.util.ArrayList;

import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.detailediteminfo.model.DetailedItemInfo_Interactor;
import ddoraemi.detailediteminfo.model.DetailedItemInfo_Interactor_Interface;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_View;
import ddoraemi.home.model.Group;

public class DetailedItemInfo_Presenter implements DetailedItemInfo_Presenter_Interface,
OnDetailedItemInfo_FinishedListener
{

	DetailedItemInfo_View view;
	DetailedItemInfo_Interactor_Interface interactor;
	ArrayList<Afterword> afterword;
	ArrayList<Group> group;
	AppController app;



	public DetailedItemInfo_Presenter(DetailedItemInfo_View view) {
		// TODO Auto-generated constructor stub

		this.view = view;
		interactor = new DetailedItemInfo_Interactor();
		afterword = new ArrayList<Afterword>();
		group = new ArrayList<Group>();
		app = (AppController)view.getApplicationContext();
	}

	@Override
	public void createGroup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMap() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validatecredential(String event, int p_id, Intent intent , boolean bookmarked) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("detailedinfo")){
			interactor.getData(view, this, p_id);
		}else if(event.equalsIgnoreCase("renewdata")){
			interactor.renewData(view, this, p_id);
		}
		else if(event.equalsIgnoreCase("getpic")){
			view.goToPicture(p_id);
		}else if(event.equalsIgnoreCase("bookmark")){
			if(bookmarked){
				interactor.unregisterbookmark(view, this, p_id, app.getId());
			}else{
				interactor.registerbookmark(view, this, p_id, app.getId());
			}
		}

	}

	@Override
	public void OnDataGetSuccess(JsonObject data) {
		// TODO Auto-generated method stub
		afterword.clear();
		group.clear();

		JsonArray json_afterword = (JsonArray)data.get("afterword");
		JsonArray json_group = (JsonArray)data.get("gathering");

		for(int i=0; i<json_afterword.size(); i++){
			JsonObject dataobj = (JsonObject)json_afterword.get(i);
			int a_id = (dataobj.get("a_id")).getAsInt();
			int g_id = (dataobj.get("g_id")).getAsInt();
			String u_id = (dataobj.get("u_id")).getAsString();
			String a_content = (dataobj.get("a_content")).getAsString();
			int a_grade = (dataobj.get("a_grade")).getAsInt();
			int a_year = (dataobj.get("a_year")).getAsInt();
			int a_month = (dataobj.get("a_month")).getAsInt();
			int a_day = (dataobj.get("a_day")).getAsInt();
			int a_r_num = (dataobj.get("a_r_num")).getAsInt();
			ArrayList<AfterwordReply> afterword_reply;
			afterword_reply = new ArrayList<AfterwordReply>();
			JsonArray reply = dataobj.get("afterword_reply").getAsJsonArray();
			JsonArray photo = dataobj.get("a_photo_url").getAsJsonArray();
			ArrayList<String> a_photo_url = new ArrayList<String>();
			String a_u_photo_url = "empty";
			if(dataobj.has("u_photo_url")){
				a_u_photo_url = dataobj.get("u_photo_url").getAsString();
			}
			
			for(int j=0; j<reply.size(); j++){
				JsonObject obj = (JsonObject)reply.get(j);
				int a_r_id = (obj.get("a_r_id")).getAsInt();
				int a_id2 = (obj.get("a_id")).getAsInt();
				String a_r_content= (obj.get("a_r_content")).getAsString();
				int a_r_year = (obj.get("a_r_year")).getAsInt();
				int a_r_month = (obj.get("a_r_month")).getAsInt();
				int a_r_day = (obj.get("a_r_day")).getAsInt();
				String u_id2 = (obj.get("u_id")).getAsString();
				String ar_u_photo_url = "empty";
				if(obj.has("u_photo_url")){
					a_u_photo_url = obj.get("u_photo_url").getAsString();
				}
				afterword_reply.add(new AfterwordReply(a_r_id, a_id2, a_r_content, a_r_year, a_r_month,
						a_r_day, u_id2,ar_u_photo_url));
			}
			if(photo.size()==0){
				
			}else{
				for(int k=0; k<photo.size(); k++){
					JsonObject obj = (JsonObject)photo.get(k);
					String url = obj.get("a_photo_url").getAsString();
					a_photo_url.add(url);
				}
			}
			afterword.add(new Afterword(a_id, g_id, u_id, a_content, a_grade, a_year, a_month, a_day, a_r_num, afterword_reply,
					a_photo_url,a_u_photo_url));
		}

		for(int i=0; i<json_group.size(); i++){
			JsonObject dataobj = (JsonObject)json_group.get(i);
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

			group.add(new Group(g_id, p_id, u_id, g_name, g_persons, g_want_persons,
					g_info, g_status, g_open_year, g_open_month, g_open_day, g_close_year, g_close_month, 
					g_close_day, g_start_year, g_start_month, g_start_day, g_start_hour, g_start_minute, g_end_hour, 
					g_end_minute, e_name, p_name, p_addr,p_photo_url));
		}
		view.setProgramList(afterword,group);

	}

	@Override
	public void onRegisterSuccess(String result, int p_id) {
		// TODO Auto-generated method stub
		if(result.equalsIgnoreCase("200")){
			view.re_bookmark("위시 체험으로 등록되었습니다.");
			app.setbookmark(p_id);
			view.setbookmark();
		}else{
			view.re_bookmark("위시 체험 등록 실패(다시 시도해 주세요)");
		}
	}

	@Override
	public void onUnRegisterSuccess(String result, int p_id) {
		// TODO Auto-generated method stub
		if(result.equalsIgnoreCase("200")){
			view.re_bookmark("위시 체험이 삭제되었습니다.");
			app.delbookmark(p_id);
			view.delbookmark();
		}else{
			view.re_bookmark("위시 체험 삭제 실패(다시 시도해 주세요)");
		}
	}

	@Override
	public void onRenewDataGetSuccess(JsonObject data) {
		// TODO Auto-generated method stub
		afterword.clear();
		group.clear();

		JsonArray json_afterword = (JsonArray)data.get("afterword");
		JsonArray json_group = (JsonArray)data.get("gathering");

		for(int i=0; i<json_afterword.size(); i++){
			JsonObject dataobj = (JsonObject)json_afterword.get(i);
			int a_id = (dataobj.get("a_id")).getAsInt();
			int g_id = (dataobj.get("g_id")).getAsInt();
			String u_id = (dataobj.get("u_id")).getAsString();
			String a_content = (dataobj.get("a_content")).getAsString();
			int a_grade = (dataobj.get("a_grade")).getAsInt();
			int a_year = (dataobj.get("a_year")).getAsInt();
			int a_month = (dataobj.get("a_month")).getAsInt();
			int a_day = (dataobj.get("a_day")).getAsInt();
			int a_r_num = (dataobj.get("a_r_num")).getAsInt();
			ArrayList<AfterwordReply> afterword_reply;
			afterword_reply = new ArrayList<AfterwordReply>();
			JsonArray reply = dataobj.get("afterword_reply").getAsJsonArray();
			JsonArray photo = dataobj.get("a_photo_url").getAsJsonArray();
			ArrayList<String> a_photo_url = new ArrayList<String>();
			String a_u_photo_url = "empty";
			if(dataobj.has("u_photo_url")){
				a_u_photo_url = dataobj.get("u_photo_url").getAsString();
			}
			
			for(int j=0; j<reply.size(); j++){
				JsonObject obj = (JsonObject)reply.get(j);
				int a_r_id = (obj.get("a_r_id")).getAsInt();
				int a_id2 = (obj.get("a_id")).getAsInt();
				String a_r_content= (obj.get("a_r_content")).getAsString();
				int a_r_year = (obj.get("a_r_year")).getAsInt();
				int a_r_month = (obj.get("a_r_month")).getAsInt();
				int a_r_day = (obj.get("a_r_day")).getAsInt();
				String u_id2 = (obj.get("u_id")).getAsString();
				String ar_u_photo_url = "empty";
				if(obj.has("u_photo_url")){
					a_u_photo_url = obj.get("u_photo_url").getAsString();
				}
				afterword_reply.add(new AfterwordReply(a_r_id, a_id2, a_r_content, a_r_year, a_r_month,
						a_r_day, u_id2,ar_u_photo_url));
			}
			if(photo.size()==0){
			}else{
				for(int k=0; k<photo.size(); k++){
					JsonObject obj = (JsonObject)photo.get(k);
					String url = obj.get("a_photo_url").getAsString();
					a_photo_url.add(url);
				}
			}
			afterword.add(new Afterword(a_id, g_id, u_id, a_content, a_grade, a_year, a_month, a_day, a_r_num, afterword_reply,
					a_photo_url,a_u_photo_url));
		}

		for(int i=0; i<json_group.size(); i++){
			JsonObject dataobj = (JsonObject)json_group.get(i);
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

			group.add(new Group(g_id, p_id, u_id, g_name, g_persons, g_want_persons,
					g_info, g_status, g_open_year, g_open_month, g_open_day, g_close_year, g_close_month, 
					g_close_day, g_start_year, g_start_month, g_start_day, g_start_hour, g_start_minute, g_end_hour, 
					g_end_minute, e_name, p_name, p_addr,p_photo_url));
		}
		view.renewArrayList(group, afterword);
	}



}
