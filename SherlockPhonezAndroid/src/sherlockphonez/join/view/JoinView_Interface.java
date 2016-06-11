package sherlockphonez.join.view;

import android.content.Context;

public interface JoinView_Interface {
	public void showProgress();
	public void hideProgress();
	public void setExistError();
	public void setNetworkError();
	public void showPasswdNotEqualError();
	public void hidePasswdNotEqualError();
	public void showMailError();
	public void hideMailError();
	public void showEmptyError();
	public void hideEmptyError();
	public void setFailedError();
	public void navigateToNext();
	public Context getViewContext();
}
