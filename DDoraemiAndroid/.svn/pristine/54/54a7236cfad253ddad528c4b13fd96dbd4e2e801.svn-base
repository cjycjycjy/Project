package ddoraemi.detailedgroupinfo.presenter;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailedgroupinfo.model.DetailedQnA_reply_Interactor;
import ddoraemi.detailedgroupinfo.model.DetailedQnA_reply_Interactor_Interface;
import ddoraemi.detailedgroupinfo.model.QnA;
import ddoraemi.detailedgroupinfo.model.QnAReply;
import ddoraemi.detailedgroupinfo.view.DetailedQnA_reply_View;


public class DetailedQnA_reply_presenter implements DetailedQnA_reply_Presenter_Interface, OnDetailedQnA_reply_FinishedListener{

	DetailedQnA_reply_View view;
	DetailedQnA_reply_Interactor_Interface interactor;
	ArrayList<QnAReply> reply;
	
	public DetailedQnA_reply_presenter(DetailedQnA_reply_View view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.reply = new ArrayList<>();
		this.interactor = new DetailedQnA_reply_Interactor();
	}
	
	@Override
	public void validatecredential(String string, QnA qna, int q_r_id, int q_id, String u_id, String q_r_content,
			int position) {
		// TODO Auto-generated method stub
		if (string.equals("setReply")) {
			interactor.setReply(view, q_id, u_id, q_r_content, this);
		}
		if(string.equals("modify")){
			interactor.modifyReply(view, this, q_r_id, q_id, q_r_content);
		}
		if(string.equals("delete")){
			interactor.deleteReply(view, this, q_r_id, q_id);
		}
		
	}
	@Override
	public void deleteQnA(int q_id){
		interactor.deleteQnA(view, this, q_id);
	}
	
	@Override
	public void onDataSetSuccess(JsonArray result) {
		// TODO Auto-generated method stub
		reply.clear();

		for(int j=0; j<result.size(); j++){
			JsonObject obj = (JsonObject)result.get(j);
			JsonObject userobj = obj.get("q_r_user").getAsJsonObject();
			int q_r_id = (obj.get("q_r_id")).getAsInt();
			int q_id2 = (obj.get("q_id")).getAsInt();
			String q_r_content= (obj.get("q_r_content")).getAsString();
			int q_r_year = (obj.get("q_r_year")).getAsInt();
			int q_r_month = (obj.get("q_r_month")).getAsInt();
			int q_r_day = (obj.get("q_r_day")).getAsInt();
			String u_id2 = (userobj.get("u_id")).getAsString();
			String qr_u_photo_url = "empty";
			if(!userobj.get("u_photo_url").isJsonNull()){
				qr_u_photo_url = userobj.get("u_photo_url").getAsString();
			}
			reply.add(new QnAReply(q_r_id, q_id2, q_r_content, q_r_year, q_r_month, q_r_day, u_id2,qr_u_photo_url));
		}
		view.notifyListview(reply);
	}

	@Override
	public void onQnADeleteSuccess(JsonObject result) {
		// TODO Auto-generated method stub
		JsonArray data = result.get("question").getAsJsonArray();
		ArrayList<QnA> qna = new ArrayList<>();
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
		view.deleteQnA(qna);
	}
	

}
