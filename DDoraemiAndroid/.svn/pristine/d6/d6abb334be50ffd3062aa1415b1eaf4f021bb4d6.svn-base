package ddoraemi.detailedgroupinfo.presenter;

import com.google.gson.JsonArray;

import ddoraemi.detailedgroupinfo.model.JoinGroup_Interactor;
import ddoraemi.detailedgroupinfo.model.JoinGroup_Interactor_Interface;
import ddoraemi.detailedgroupinfo.view.JoinGroupView;
import ddoraemi.home.model.Group;
import ddoraemi.start.User;

public class JoinGroup_presenter implements JoinGroup_presenter_Interface,
		OnJoinGroup_FinishedListener {

	JoinGroupView view;
	JoinGroup_Interactor_Interface interactor;

	public JoinGroup_presenter(JoinGroupView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.interactor = new JoinGroup_Interactor();
	}

	@Override
	public void validatecredential(String event, Group group, User user,
			int hopeperson) {
		// TODO Auto-generated method stub
		if (event.equalsIgnoreCase("showdialog")) {
			view.showJoinDialog(group);
		} else if (event.equalsIgnoreCase("join")) {
			interactor.joinGroup(view, group.getG_id(), user.getU_id(),
					hopeperson, this);
		} else if (event.equalsIgnoreCase("dismiss_dialog")) {
			view.dismissdialog();
		}
	}

	@Override
	public void onJoinSuccess(String result) {
		// TODO Auto-generated method stub
		view.joinsuccess_GoToDetailedGroupInfo();
	}

	@Override
	public void onJoinFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validatecredential(String event) {
		// TODO Auto-generated method stub
		if (event.equals("up")) {
			view.clickPlusBtn();
		} else if (event.equals("down")) {
			view.clickMinusBtn();

		}
	}

}
