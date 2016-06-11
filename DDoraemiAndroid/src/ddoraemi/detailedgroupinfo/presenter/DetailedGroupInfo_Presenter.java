package ddoraemi.detailedgroupinfo.presenter;

import java.util.ArrayList;

import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailedgroupinfo.model.DetailedGroupInfo_Interactor;
import ddoraemi.detailedgroupinfo.model.DetailedGroupInfo_Interactor_Interface;
import ddoraemi.detailedgroupinfo.model.QnA;
import ddoraemi.detailedgroupinfo.model.QnAReply;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_View;
import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.Group;
import ddoraemi.start.User;

public class DetailedGroupInfo_Presenter implements DetailedGroupInfo_Presenter_Interface,OnDetailedGroupInfo_FinishedListener{

	DetailedGroupInfo_View view;
	ArrayList<QnA> qna;
	DetailedGroupInfo_Interactor_Interface interactor;
	ArrayList<User> user;

	public DetailedGroupInfo_Presenter(DetailedGroupInfo_View view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.qna = new ArrayList<QnA>();
		this.user = new ArrayList<User>();
		this.interactor = new DetailedGroupInfo_Interactor();

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
	public void validatecredential(String event, int g_id,int p_id) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("renew")){
			interactor.getrenewpersoninfo(view, g_id,this);
		}else if(event.equalsIgnoreCase("getdata")){
			interactor.getpersoninfo(view, g_id,this);
		}else if(event.equalsIgnoreCase("getpic")){
			view.goToPic(p_id);
		}
	}

	@Override
	public void onDataGetSuccess(JsonArray data, JsonObject personinfo) {
		// TODO Auto-generated method stub
		qna.clear();

		user.clear();
		String r = personinfo.get("result")
				.getAsString();

		if (r.equals("200")) {
			JsonArray userarr = personinfo.get("join_user")
					.getAsJsonArray();
			for(int i=0; i<userarr.size(); i++){
				JsonObject userobj = userarr.get(i).getAsJsonObject();


				String u_id = userobj.get("u_id")
						.getAsString();
				int permission = userobj.get(
						"permission").getAsInt();
				String password = userobj.get(
						"password").getAsString();
				String u_phone = userobj.get("u_phone")
						.getAsString();
				char parent_gender = userobj.get(
						"parent_gender")
						.getAsCharacter();
				int parent_age = userobj.get(
						"parent_age").getAsInt();
				String u_addr1 = userobj
						.get("u_addr_1").getAsString();
				String u_addr2 = userobj
						.get("u_addr_2").getAsString();
				String u_addr3 = userobj
						.get("u_addr_3").getAsString();
				char u_gender = userobj.get("u_gender")
						.getAsCharacter();
				int u_age = userobj.get("u_age")
						.getAsInt();
				String u_photo_url = "empty";
				if (!userobj.get("u_photo_url")
						.isJsonNull()) {
					u_photo_url = userobj.get(
							"u_photo_url")
							.getAsString();
				}
				int u_alarm = userobj.get("alarm")
						.getAsInt();
				this.user.add(new User(u_id, password,
						u_phone, parent_gender,
						parent_age, u_addr1, u_addr2,
						u_addr3, u_gender, u_age,
						null, null,
						u_photo_url, null,
						permission,u_alarm));


			}

		} else {
			Dialog_BaseDialog dialog = new Dialog_BaseDialog(
					view,
					"목록을 받아오는데 실패하였습니다.");
			dialog.show();
			return;
		}

		for(int i=0; i<data.size(); i++){
			JsonObject dataobj = (JsonObject)data.get(i);
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

			qna.add(new QnA(q_id, u_id, g_id, q_content, q_year, q_month, q_day, q_r_num, question_reply,u_photo_url));
		}
		view.setqnalist(qna, user);

	}
	@Override
	public void onRenewDataGetSuccess(JsonArray data,JsonObject personinfo) {
		// TODO Auto-generated method stub
		qna.clear();

		user.clear();
		String r = personinfo.get("result")
				.getAsString();

		if (r.equals("200")) {
			JsonArray userarr = personinfo.get("join_user")
					.getAsJsonArray();
			for(int i=0; i<userarr.size(); i++){
				JsonObject userobj = userarr.get(i).getAsJsonObject();


				String u_id = userobj.get("u_id")
						.getAsString();
				int permission = userobj.get(
						"permission").getAsInt();
				String password = userobj.get(
						"password").getAsString();
				String u_phone = userobj.get("u_phone")
						.getAsString();
				char parent_gender = userobj.get(
						"parent_gender")
						.getAsCharacter();
				int parent_age = userobj.get(
						"parent_age").getAsInt();
				String u_addr1 = userobj
						.get("u_addr_1").getAsString();
				String u_addr2 = userobj
						.get("u_addr_2").getAsString();
				String u_addr3 = userobj
						.get("u_addr_3").getAsString();
				char u_gender = userobj.get("u_gender")
						.getAsCharacter();
				int u_age = userobj.get("u_age")
						.getAsInt();
				String u_photo_url = "empty";
				if (!userobj.get("u_photo_url")
						.isJsonNull()) {
					u_photo_url = userobj.get(
							"u_photo_url")
							.getAsString();
				}
				int u_alarm = userobj.get("alarm").getAsInt();
				this.user.add(new User(u_id, password,
						u_phone, parent_gender,
						parent_age, u_addr1, u_addr2,
						u_addr3, u_gender, u_age,
						null, null,
						u_photo_url, null,
						permission,u_alarm));


			}

		} else {
			Dialog_BaseDialog dialog = new Dialog_BaseDialog(
					view,
					"목록을 받아오는데 실패하였습니다.");
			dialog.show();
			return;
		}
		
		for(int i=0; i<data.size(); i++){
			JsonObject dataobj = (JsonObject)data.get(i);
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

			qna.add(new QnA(q_id, u_id, g_id, q_content, q_year, q_month, q_day, q_r_num, question_reply,u_photo_url));
		}
		view.renewArrayList(qna,user);
	}

}