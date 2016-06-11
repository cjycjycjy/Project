package sherlockphonez.join.controller;

import sherlockphonez.join.model.JoinInteractor;
import sherlockphonez.join.model.JoinInteractor_Interface;
import sherlockphonez.join.view.JoinView;
import sherlockphonez.join.view.JoinView_Interface;

public class JoinController implements JoinController_Interface, OnJoinFinishedListener{
	JoinView_Interface view;
	JoinInteractor_Interface interactor;
	public JoinController(JoinView_Interface view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.interactor = new JoinInteractor();
	}
	
	@Override
	public void validateCredentials(String PhoneNum, String deviceId,
			String passwd, String passwdCheck, String name, String eMail, String msg) {
		// TODO Auto-generated method stub
		
		if(name.equalsIgnoreCase("") || passwd.equalsIgnoreCase("")|| eMail.equalsIgnoreCase("")
				|| passwdCheck.equalsIgnoreCase("")){
			setEmptyWarning();
		}
		else if(msg.equalsIgnoreCase("changeinfo")){
			view.showProgress();
			interactor.changeinfo(view.getViewContext(),PhoneNum, deviceId, passwd, name, eMail,this);
		}
		else{
			view.showProgress();
			interactor.join(view.getViewContext(),PhoneNum, deviceId, passwd, name, eMail,this);	
		}
	}
	
	@Override
	public void setPasswdWarning() {
		// TODO Auto-generated method stub
		view.showPasswdNotEqualError();
	}

	@Override
	public void unsetPasswdWarning() {
		// TODO Auto-generated method stub
		view.hidePasswdNotEqualError();
	}
	
	@Override
	public void setMailWarning() {
		// TODO Auto-generated method stub
		view.showMailError();
	}

	@Override
	public void unsetMailWarning() {
		// TODO Auto-generated method stub
		view.hideMailError();
	}
	
	@Override
	public void setEmptyWarning() {
		// TODO Auto-generated method stub
		view.showEmptyError();
	}

	@Override
	public void unsetEmptyWarning() {
		// TODO Auto-generated method stub
		view.hideEmptyError();
	}
	
////////////////////////////////////OnJoinFinishedListener Override Method/////////////////////////////
	
	@Override
	public void onExistError() {
		// TODO Auto-generated method stub
		view.setExistError();	
		view.hideProgress();
	}
	
	@Override
	public void onNetworkError(){
		// TODO Auto-generated method stub
		view.setNetworkError();	
		view.hideProgress();
	}
	
	@Override
	public void onFailedError() {
		// TODO Auto-generated method stub
		view.setFailedError();	
		view.hideProgress();
	}
	
	@Override
	public void onSuccess() {
		// TODO Auto-generated method stub
		view.navigateToNext();	
		view.hideProgress();
	}
}
