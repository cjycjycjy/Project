package ddoraemi.adminmodehome.presenter;

import ddoraemi.adminmodehome.view.AdminGroupInfoView;
import ddoraemi.home.model.Group;

public class AdminGroupInfoPresenter implements AdminGroupInfoPresenterInterface {
	AdminGroupInfoView view;
	
	public AdminGroupInfoPresenter(AdminGroupInfoView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}
	public void validatecredential(Group group) {
		// TODO Auto-generated method stub
		view.goToDetailedGroupInfo(group);
	}

}
