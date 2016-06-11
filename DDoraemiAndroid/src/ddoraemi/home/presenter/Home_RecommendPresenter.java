package ddoraemi.home.presenter;

import ddoraemi.home.model.ProgramData;
import ddoraemi.home.view.Home_RecommendView;

public class Home_RecommendPresenter implements Home_RecommendPresenterInterface{

	Home_RecommendView view;
	
	public Home_RecommendPresenter(Home_RecommendView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		
		
	}
	@Override
	public void validatecredential(String event, ProgramData item) {
		// TODO Auto-generated method stub
		view.goToDetailedItemInfo(item);
	}
	
}