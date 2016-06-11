package ddoraemi.detailediteminfo.presenter;

import ddoraemi.detailediteminfo.view.DetailedItemInfo_groupView;
import ddoraemi.home.model.Group;


public class DetailedItemInfo_groupView_presenter implements DetailedItemInfo_groupView_presenter_Interface{

	DetailedItemInfo_groupView view;
	
	public DetailedItemInfo_groupView_presenter(DetailedItemInfo_groupView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}

	@Override
	public void validatecredential(Group group) {
		// TODO Auto-generated method stub
		view.goToDetailedGroupInfo(group);
		
	}
}
