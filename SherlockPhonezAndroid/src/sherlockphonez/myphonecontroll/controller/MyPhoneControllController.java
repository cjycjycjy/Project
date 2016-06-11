package sherlockphonez.myphonecontroll.controller;

import sherlockphonez.myphonecontroll.model.MyPhoneControllInteractor;
import sherlockphonez.myphonecontroll.view.MyPhoneControllView;

public class MyPhoneControllController implements MyPhoneControllController_Interface,OnMyPhoneControlFinishedListener{
	MyPhoneControllView view;
	MyPhoneControllInteractor interactor;
	public MyPhoneControllController(MyPhoneControllView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.interactor = new MyPhoneControllInteractor();
		
	}

	@Override
	public void validateCredentials(String otherPhoneNum, String msg) {
		// TODO Auto-generated method stub
		view.showProgress();
		if(msg.equalsIgnoreCase("lock")){
			view.showDialogMyphoneLock();
			view.hideProgress();
		}else if(msg.equalsIgnoreCase("cancellock")){
			view.hideDialogMyphoneLock();
			view.hideProgress();
		}		
		else{
			interactor.sendGCM(view, otherPhoneNum, msg, this);
		}
		
	}

	@Override
	public void startLocation() {
		// TODO Auto-generated method stub
		view.navigateToLocation();
	}

	@Override
	public void startCamera() {
		// TODO Auto-generated method stub
		view.navigateToCamera();
	}

	@Override
	public void showDialog_logout() {
		// TODO Auto-generated method stub
		view.showDialogLogout();
	}

	@Override
	public void dismissDialog_logout() {
		// TODO Auto-generated method stub
		view.hideDialogLogout();
	}
	
	

	@Override
	public void onNetworkError() {
		// TODO Auto-generated method stub
		view.setNetworkError();
		view.hideProgress();
	}

	@Override
	public void onSetPasswd() {
		// TODO Auto-generated method stub
		view.hideDialogMyphoneLock();	
		view.hideProgress();
	}

	@Override
	public void onAlarmOn() {
		// TODO Auto-generated method stub
		view.hideProgress();
	}

	@Override
	public void onAlarmOff() {
		// TODO Auto-generated method stub
		view.hideProgress();
	}

	@Override
	public void onLogout() {
		// TODO Auto-generated method stub
		view.hideDialogLogout();
		view.hideProgress();
		view.setLogout();

	}

}
