package ddoraemi.home.presenter;

import ddoraemi.home.view.HomeView;

public class HomeViewPresenter implements HomeViewPresenterInterface{
	HomeView view;
	public HomeViewPresenter(HomeView view)
	{
		this.view=view;
	}
	@Override
	public void goToMyPage() {
		// TODO Auto-generated method stub
		view.goMyPage();
	}

	@Override
	public void goToHome() {
		// TODO Auto-generated method stub
		view.goToHome();
		
	}

	@Override
	public void goToShowMenu() {
		// TODO Auto-generated method stub
		view.goToshowMenu();
		
	}

	@Override
	public void goToChallenge() {
		// TODO Auto-generated method stub
		view.goChallenge();
		
	}

	@Override
	public void goToLocationChallenge() {
		// TODO Auto-generated method stub
		view.goLocationChallenge();
	}

	@Override
	public void goToApplyAdmin() {
		// TODO Auto-generated method stub
		view.goApplyAdmin();
	}
	@Override
	public void goToAdminMode() {
		// TODO Auto-generated method stub
		view.goAdminMode();
		
	}
	@Override
	public void goToSearch() {
		// TODO Auto-generated method stub
		view.goSearch();
		
	}
	

}