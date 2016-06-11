package sherlockphonez.login.controller;


import sherlockphonez.login.model.LoginInteractor;
import sherlockphonez.login.model.LoginInteractor_Interface;
import sherlockphonez.login.model.User;
import sherlockphonez.login.view.LoginView;
import sherlockphonez.login.view.LoginView_Interface;

public class LoginController implements LoginController_Interface, OnLoginFinishedListener{
	LoginView_Interface view;
	LoginInteractor_Interface interactor;
	public LoginController(LoginView_Interface view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.interactor = new LoginInteractor();
	}
	
	@Override
	public void onNotFoundUserError() {
		// TODO Auto-generated method stub
		view.setNotFoundUserError();
		view.hideProgress();
	}

	@Override
	public void onNetworkError() {
		// TODO Auto-generated method stub
		view.setNetworkError();
		view.hideProgress();
	}

	@Override
	public void onEmptyError() {
		// TODO Auto-generated method stub
		view.setEmptyError();
		view.hideProgress();
	}

	@Override
	public void onPasswdNotEqualError() {
		// TODO Auto-generated method stub
		view.setPasswdNotEqualError();
		view.hideProgress();
	}

	@Override
	public void onMyPhoneError() {
		// TODO Auto-generated method stub
		view.setMyPhoneError();
		view.hideProgress();
	}

	@Override
	public void onFailedError() {
		// TODO Auto-generated method stub
		view.setFailedError();
		view.hideProgress();
	}

	@Override
	public void onSuccess(String phoneNum) {
		// TODO Auto-generated method stub
		view.navigateToNext(phoneNum);
		view.hideProgress();
	}

	@Override
	public void onSendMailSuccess() {
		// TODO Auto-generated method stub
		view.setSendMailSuccess();
		view.hideMailDialog();
		view.hideProgress();
	}

	@Override
	public void onWrongError() {
		// TODO Auto-generated method stub
		view.setWrongError();
		view.hideProgress();
	}




	@Override
	public void validateCredentials(final String PhoneNum, final String myPhoneNum, final String passwd,
			final String serverIp, final String msg) {
		// TODO Auto-generated method stub
		if(PhoneNum.equalsIgnoreCase("") || passwd.equalsIgnoreCase("")){
			onEmptyError();
		}else if(msg.equalsIgnoreCase("login") && PhoneNum.equalsIgnoreCase(myPhoneNum)){
			onMyPhoneError();
		}else{
			view.showProgress();
			interactor.LoginOtherPhone(view.getViewContext(),PhoneNum, passwd,this,msg);
		}
	}

	@Override
	public void sendEmail(final String phoneNum,final String eMail) {
		// TODO Auto-generated method stub
		view.showProgress();
		interactor.SendEMail(phoneNum,eMail, this);
	}
	
	@Override
	public void showDialog_mail() {
		// TODO Auto-generated method stub
		view.showProgress();
		view.showMailDialog();
		view.hideProgress();
	}

	@Override
	public void dismissDialog_mail() {
		// TODO Auto-generated method stub
		view.showProgress();
		view.hideMailDialog();
		view.hideProgress();
	}



	@Override
	public void onChangeInfoLoginSuccess(User user) {
		// TODO Auto-generated method stub
		String name = user.getName();
		String email = user.getEmail();
		String phoneNum = user.getPhoneNum();
		
		view.navigateToNextChangeInfo(name, phoneNum, email);
		view.hideProgress();
	}

	



}
