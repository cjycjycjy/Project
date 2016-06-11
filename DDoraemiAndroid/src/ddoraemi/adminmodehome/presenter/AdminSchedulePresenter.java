package ddoraemi.adminmodehome.presenter;

import ddoraemi.adminmodehome.view.AdminScheduleView;

public class AdminSchedulePresenter implements AdminSchedulePresenterInterface{
	AdminScheduleView view;
	public AdminSchedulePresenter(AdminScheduleView adminScheduleView) {
		// TODO Auto-generated constructor stub
		this.view=adminScheduleView;
	}

	@Override
	public void nextMonth() {
		// TODO Auto-generated method stub
		view.goToNextMonth();
	}

	@Override
	public void prevMonth() {
		// TODO Auto-generated method stub
		view.goToPrevMonth();
	}

}
