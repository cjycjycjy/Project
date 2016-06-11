package sherlockphonez.login.model;

import android.content.Context;
import sherlockphonez.login.controller.OnLoginFinishedListener;



public interface LoginInteractor_Interface {
	public void LoginOtherPhone(final Context context, final String PhoneNum, final String passwd,
			 final OnLoginFinishedListener listener, final String msg);
	
	public void SendEMail(final String phoneNum, final String eMail, 
			final OnLoginFinishedListener listener);
}
