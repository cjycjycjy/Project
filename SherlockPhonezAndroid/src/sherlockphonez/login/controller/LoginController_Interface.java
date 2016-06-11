package sherlockphonez.login.controller;

public interface LoginController_Interface {
	public void validateCredentials(final String PhoneNum, final String myPhoneNum, final String passwd,
			final String serverIp,final String msg);
	public void sendEmail(final String phoneNum,final String eMail);
	public void showDialog_mail();
	public void dismissDialog_mail();
	
	
}
