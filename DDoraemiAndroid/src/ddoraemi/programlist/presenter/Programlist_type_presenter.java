package ddoraemi.programlist.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.home.model.ProgramData;
import ddoraemi.programlist.model.Programlist_type_Interactor;
import ddoraemi.programlist.model.Programlist_type_Interactor_Interface;
import ddoraemi.programlist.view.Programlist_type_View;

public class Programlist_type_presenter implements Programlist_type_presenter_Interface, OnProgramlist_type_FinishedListener{

	Programlist_type_View view;
	Programlist_type_Interactor_Interface interactor;
	ArrayList<ProgramData> list;
	String type="";

	public Programlist_type_presenter(Programlist_type_View view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.list = new ArrayList<ProgramData>();
		this.interactor = new Programlist_type_Interactor();

	}
	@Override
	public void validatecredential(String event) {
		// TODO Auto-generated method stub
		type = event;
		interactor.getData(view,this,"2",event);
	}
	@Override
	public void getDataSuccess(JsonArray result) {
		// TODO Auto-generated method stub
		list.clear();

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
		   ArrayList<String> photo_urllist = new ArrayList<>();
		   ArrayList<String> cur_list = new ArrayList<>();
		   
	
		   for(int k=0; k<p_curriculum.size(); k++){
			   JsonObject cur = (JsonObject)p_curriculum.get(k);
			   String cur_info = cur.get("cur").getAsString();
			   cur_list.add(cur_info);
		   }
		   this.list.add(new ProgramData(p_id, p_name, p_info, e_name, p_cost_adult,
				   p_cost_kid, p_min_persons, p_max_persons, p_available_season, p_lead_time, p_preparation_item,
				   p_town_name, p_addr_1, p_addr_2, p_addr_3, p_addr_4, p_addr_5, p_addr_6, p_tel, 
				   p_url, p_is_available_overnight, p_photo_url, 
				   cur_list, p_note, p_grade, a_id));
		
		}
		view.goToList(list,type);
	}

}