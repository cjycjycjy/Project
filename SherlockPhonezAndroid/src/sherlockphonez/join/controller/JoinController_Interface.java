package sherlockphonez.join.controller;

public interface JoinController_Interface {
	public void validateCredentials(String PhoneNum, String deviceId, String passwd,
			String passwdCheck, String name, 
			String eMail, String msg);

	public void setPasswdWarning();
	public void unsetPasswdWarning();
	public void setMailWarning();
	public void unsetMailWarning();
	public void setEmptyWarning();
	public void unsetEmptyWarning();
}
