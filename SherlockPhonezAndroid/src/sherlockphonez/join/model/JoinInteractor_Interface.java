package sherlockphonez.join.model;

import android.content.Context;
import sherlockphonez.join.controller.OnJoinFinishedListener;



public interface JoinInteractor_Interface {
	public void join(final Context context,final String PhoneNum,final String deviceId,final String passwd,final 
			String name,final String eMail,
			final OnJoinFinishedListener listener);
	public void changeinfo(final Context context,final String PhoneNum,final String deviceId,final String passwd,final 
			String name,final String eMail,
			final OnJoinFinishedListener listener);
}
