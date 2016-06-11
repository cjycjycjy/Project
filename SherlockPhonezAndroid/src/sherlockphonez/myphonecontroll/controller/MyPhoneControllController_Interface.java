package sherlockphonez.myphonecontroll.controller;

public interface MyPhoneControllController_Interface {
	public void validateCredentials(String otherPhoneNum, String msg);
	public void startLocation();
	public void startCamera();
	//public void startLive();
	public void showDialog_logout();
	public void dismissDialog_logout();
	public void onLogout();
}
