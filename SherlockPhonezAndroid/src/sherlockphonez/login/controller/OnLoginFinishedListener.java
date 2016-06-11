package sherlockphonez.login.controller;

import sherlockphonez.login.model.User;

public interface OnLoginFinishedListener {
	public void onNotFoundUserError();
	public void onNetworkError();
	public void onEmptyError();
	public void onPasswdNotEqualError();
	public void onMyPhoneError();
	public void onFailedError();
	public void onSuccess(String phoneNum);
	public void onSendMailSuccess();
	public void onWrongError();
	
	public void onChangeInfoLoginSuccess(User user);
}
