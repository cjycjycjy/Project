package sherlockphonez.main.controller;

import sherlockphonez.main.model.MainViewInteractor;
import sherlockphonez.main.model.MainView_InteractorInterface;
import sherlockphonez.main.view.MainView;

public class MainViewController implements MainViewControllerInterface,OnMainFinishedListener{
	MainView view;
	MainView_InteractorInterface interactor;
	public MainViewController(MainView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		interactor = new MainViewInteractor();
	}

	@Override
	public void validateCredentials(final String PhoneNum, final String deviceId, final String msg 
			) {
		// TODO Auto-generated method stub
		view.showProgress();
		interactor.main(view,PhoneNum, deviceId, msg, this);
	}
	

	@Override
	public void startService() {
		// TODO Auto-generated method stub
		view.setServiceStart();
		view.hideProgress();
	}
	@Override
	public void stopService() {
		// TODO Auto-generated method stub
		view.setServiceStop();
		view.hideProgress();
	}


	@Override
	public void setStartFailedError() {
		// TODO Auto-generated method stub
		view.setStartFailedError();
		view.hideProgress();
	}

	@Override
	public void setStopFailedError() {
		// TODO Auto-generated method stub
		view.setStopFailedError();
		view.hideProgress();
	}

	@Override
	public void join() {
		// TODO Auto-generated method stub
		view.nevigateToJoin();
		
	}

	@Override
	public void otherPhoneLogin() {
		// TODO Auto-generated method stub
		view.nevigateToOtherPhoneLogin();
	}
	
	@Override
	public void autoLogin(){
		view.nevagateToWirelessControl();
	}
	
	@Override
	public void changeInfo() {
		view.nevigateToChangeInfo();
	}

	@Override
	public void onNetworkError() {
		// TODO Auto-generated method stub
		view.setNetworkError();
		view.hideProgress();
		
	}

	
}
