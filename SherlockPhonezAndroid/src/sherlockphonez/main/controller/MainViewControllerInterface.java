package sherlockphonez.main.controller;

public interface MainViewControllerInterface {
	public void validateCredentials(final String PhoneNum, final String deviceId, 
			final String msg);
	public void join();
	public void otherPhoneLogin();
	public void autoLogin();
	public void changeInfo();
}
