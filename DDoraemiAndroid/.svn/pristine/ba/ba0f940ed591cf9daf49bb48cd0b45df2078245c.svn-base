package ddoraemi.detailedgroupinfo.presenter;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailedgroupinfo.model.DetailedGroupInfo_Picture_Interactor;
import ddoraemi.detailedgroupinfo.model.DetailedGroupInfo_Picture_Interactor_Interface;
import ddoraemi.detailedgroupinfo.view.DetailedGroupInfo_PictureView;
import ddoraemi.detailediteminfo.model.ChallengePicture;
import ddoraemi.detailediteminfo.presenter.OnGetPicture_FinishedListener;

public class DetailedGroupInfo_Picture_presenter implements DetailedGroupInfo_Picture_presenter_Interface,
OnGetPicture_FinishedListener{
	DetailedGroupInfo_PictureView view;
	DetailedGroupInfo_Picture_Interactor_Interface interactor;
	ArrayList<ChallengePicture> list;

	public DetailedGroupInfo_Picture_presenter(DetailedGroupInfo_PictureView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		interactor = new DetailedGroupInfo_Picture_Interactor();
		this.list = new ArrayList<>();
	}

	@Override
	public void validatecredential(String event,int p_id, ChallengePicture picture) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("widening")){
			view.widening(picture);
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
