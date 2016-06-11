package sherlockphonez.login.view;

import android.content.Context;

public interface LoginView_Interface {
	public void showProgress();
	public void hideProgress();
	public void setNotFoundUserError();
	public void setEmptyError();
	public void setNetworkError();
	public void setPasswdNotEqualError();
	public void setMyPhoneError();
	public void setFailedError();
	public void setWrongError();
	public void showMailDialog();
	public void hideMailDialog();
	public void navigateToNext(String phoneNum);
	public void setSendMailSuccess();
	public void navigateToNextChangeInfo(String name, String phoneNum, String email);
	public Context getViewContext();
}
