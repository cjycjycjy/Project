package ddoraemi.myinfopage.presenter;

import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.myinfopage.view.MyPage_PastView;

public class MyPage_PastPresenter implements MyPage_PastPresenterInterface{
	MyPage_PastView view;
	public MyPage_PastPresenter(MyPage_PastView view)
	{
		this.view=view;
	}
	
	@Override
	public void validateCredentials(Afterword item) 
	{
		view.goTodetail(item);
	}

}
