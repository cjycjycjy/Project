package ddoraemi.myinfopage.presenter;

import ddoraemi.home.model.Group;
import ddoraemi.myinfopage.view.MyPage_ParticipateView;

public class MyPage_ParticipatePresenter implements MyPage_ParticipatePresenterInterface {
	MyPage_ParticipateView view;
	public MyPage_ParticipatePresenter(MyPage_ParticipateView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}
	public void validateCredentials(Group item) {
		// TODO Auto-generated method stub
		view.gotoDetailGroup(item);
		
	}

}
