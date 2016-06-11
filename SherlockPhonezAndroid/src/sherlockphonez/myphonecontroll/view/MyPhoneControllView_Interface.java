package sherlockphonez.myphonecontroll.view;

public interface MyPhoneControllView_Interface {
	public void showProgress();
	public void hideProgress();
	public void showProgressState();
	public void hideProgressState();
	public void setNetworkError();
	public void showDialogMyphoneLock();
	public void hideDialogMyphoneLock();
	public void showDialogLogout();
	public void hideDialogLogout();
	public void setMyphonePasswd();
	public void navigateToLocation();
	public void navigateToCamera();
	public void navigateToLive();
	public void setLogout();
}
