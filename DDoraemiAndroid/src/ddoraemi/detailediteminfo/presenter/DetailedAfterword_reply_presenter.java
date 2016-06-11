package ddoraemi.detailediteminfo.presenter;

import java.util.ArrayList;

import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.detailediteminfo.model.DetailedAfterword_reply_Interactor;
import ddoraemi.detailediteminfo.model.DetailedAfterword_reply_Interactor_Interface;
import ddoraemi.detailediteminfo.view.DetailedAfterword_reply_view;
import ddoraemi.dialog.Dialog_BaseDialog;

public class DetailedAfterword_reply_presenter
		implements DetailedAfterword_reply_presenter_Interface, OnDetailedAfterword_reply_FinishedListener {
	DetailedAfterword_reply_view view;
	DetailedAfterword_reply_Interactor_Interface interactor;
	ArrayList<AfterwordReply> reply;
	Afterword afterword;

	public DetailedAfterword_reply_presenter(DetailedAfterword_reply_view view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.reply = new ArrayList<>();
		this.interactor = new DetailedAfterword_reply_Interactor();
	}

	@Override
	public void validatecredential(String type, Afterword afterword, int a_r_id, int a_id, String u_id,
			String a_r_content, int position) {
		// TODO Auto-generated method stub
		if (type.equals("setReply")) {
			interactor.setReply(view, a_id, u_id, a_r_content, this);
			this.afterword = afterword;
		}
		else if(type.equals("modify")){
			interactor.modifyReply(view, a_r_id, a_id, a_r_content, this);
			this.afterword = afterword;
		}
		else if(type.equals("delete")){
			interactor.deleteReply(view, a_r_id, a_id, this);
			this.afterword = afterword;
		}
		else if(type.equals("deletemodify")){
			interactor.deleteAfterword(view, a_id, this);
			this.afterword = afterword;
		}
		else if(type.equals("clickPopmenu"))
		{
			view.showPopMenu();
		}
		
	}

	@Override
	public void onDataSetSuccess(JsonArray result) {
		// TODO Auto-generated method stub
		reply.clear();

		for (int j = 0; j < result.size(); j++) {
			JsonObject obj = (JsonObject) result.get(j);
			int a_r_id = (obj.get("a_r_id")).getAsInt();
			int a_id2 = (obj.get("a_id")).getAsInt();
			String a_r_content = (obj.get("a_r_content")).getAsString();
			int a_r_year = (obj.get("a_r_year")).getAsInt();
			int a_r_month = (obj.get("a_r_month")).getAsInt();
			int a_r_day = (obj.get("a_r_day")).getAsInt();
			String u_id2 = (obj.get("u_id")).getAsString();
			String u_photo_url = "empty";
			if(obj.has("u_photo_url")){
				u_photo_url = obj.get("u_photo_url").getAsString();
			}
			reply.add(new AfterwordReply(a_r_id, a_id2, a_r_content, a_r_year, a_r_month, a_r_day, u_id2, u_photo_url));
		}

		view.notifyListview(reply);
	}

	@Override
	public void onDeleteSuccess(String result) {
		if(result.equals("200"))
		{
			view.finish();
		}
		else
		{
			Dialog_BaseDialog dialog=new Dialog_BaseDialog(view, "삭제에 실패하였습니다.");
			dialog.show();
		}
		
	}

	@Override
	public void validatecredential(Intent intent) {
		// TODO Auto-generated method stub
		view.modifyAfterword(intent);
	}

}
