package ddoraemi.detailediteminfo.presenter;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.detailediteminfo.model.DetailedItemInfo_Picture_Interactor;
import ddoraemi.detailediteminfo.model.DetailedItemInfo_Picture_Interactor_Interface;
import ddoraemi.detailediteminfo.view.DetailedItemInfo_PictureView;

public class DetailedItemInfo_Picture_presenter implements DetailedItemInfo_Picture_presenter_Interface,
OnGetPicture_FinishedListener
{
	DetailedItemInfo_PictureView view;
	DetailedItemInfo_Picture_Interactor_Interface interactor;
	ArrayList<ChallengePicture> list;

	public DetailedItemInfo_Picture_presenter(DetailedItemInfo_PictureView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		interactor = new DetailedItemInfo_Picture_Interactor();
		this.list = new ArrayList<>();
	}

	@Override
	public void validatecredential(String event,int p_id,int position) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("widening")){
			view.widening(position);
		}else if(event.equalsIgnoreCase("getpicture")){
			interactor.getPicture(p_id,view, this);
		}
	}

	@Override
	public void onGetPictureSuccess(JsonArray data) {
		// TODO Auto-generated method stub
		list.clear();
		
		for(int i=0; i<data.size(); i++){
			JsonObject dataobj = (JsonObject)data.get(i);
			String a_photo_url = dataobj.get("a_photo_url").getAsString();
			
			list.add(new ChallengePicture(a_photo_url));
		}
		view.setadapter(list);
	}



}
