package ddoraemi.adminmodehome.presenter;

import ddoraemi.adminmodehome.view.AdminAfterwordView;
import ddoraemi.detailediteminfo.model.Afterword;

public class AdminAfterwordPresenter implements AdminAfterwordPresenterInterface{

	AdminAfterwordView view;
	
	public AdminAfterwordPresenter(AdminAfterwordView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}

	@Override
	public void validatecredential(Afterword item, int position) {
		// TODO Auto-generated method stub
		view.goToDetailedAfterword(item, position);
	}
}
