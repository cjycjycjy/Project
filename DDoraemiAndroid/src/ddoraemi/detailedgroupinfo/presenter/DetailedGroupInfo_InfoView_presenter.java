package ddoraemi.detailedgroupinfo.presenter;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.model.DetailedGroupInfo_Info_Interactor;
import ddoraemi.detailedgroupinfo.model.DetailedGroupInfo_Info_Interactor_Interface;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_InfoView;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;

public class DetailedGroupInfo_InfoView_presenter implements DetailedGroupInfo_InfoView_presenter_Interface,
OnDetailedGroupInfo_Info_FinishedListener{

	DetailedGroupInfo_InfoView view;
	DetailedGroupInfo_Info_Interactor_Interface interactor;
	AppController app;
	public DetailedGroupInfo_InfoView_presenter(DetailedGroupInfo_InfoView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.interactor = new DetailedGroupInfo_Info_Interactor();
		this.app = (AppController)view.getContext().getApplicationContext();
	}
	@Override
	public void validatecredential(String event, Group group) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("joingroup")){
			view.goToJoinGroup(group);
		}else if(event.equalsIgnoreCase("cancel")){
			view.showCancelDialog();
		}else if(event.equalsIgnoreCase("dismiss_cancel_dialog")){
			view.dismissCancelDialog();
		}else if(event.equalsIgnoreCase("cancelGroup")){
			//통신해서 그룹참여 취소
			interactor.cancelGroup(view.getActivity(), group.getG_id(),app.getUser().getU_id() ,this);
		}
		else if(event.equalsIgnoreCase("gotoTmap"))
		{
			view.goToTmap();
		}
		else if(event.equalsIgnoreCase("delete")){
			//모임 삭제
			if(group.getG_persons()>1){
				view.showCannotDelete();
			}else{
				view.showDeleteDialog();
			}
			
		}else if(event.equalsIgnoreCase("dismiss_delete_dialog")){
			view.dismissDeleteDialog();
		}else if(event.equalsIgnoreCase("deleteGroup")){
			//통신 해서 지우기
			interactor.deleteGroup(view.getActivity(), group.getG_id(), this);
		}
	}

	@Override
	public void validatecredential(String event, int p_id){
		if(event.equalsIgnoreCase("getdata")){
			interactor.getinfo(view.getActivity(), p_id, this);
		}else if(event.equalsIgnoreCase("gotoIteminfo")){
			view.goToItemInfo();
		}
	}
			
	@Override
	public void validatecredential(String event, Group group, boolean ismaster) {
		if(ismaster){
			view.goToPersonList(group);
		}
	}
	@Override
	public void onCancelGroupSuccess(int minus) {
		// TODO Auto-generated method stub	
		view.cancelGroup(minus);
	}
	@Override
	public void onDeleteGroupSuccess() {
		// TODO Auto-generated method stub
		view.deleteGroup();
	}
	@Override
	public void onGoToIteminfo(JsonObject result) {
		// TODO Auto-generated method stub

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

		view.setItem(data);
	}


}
