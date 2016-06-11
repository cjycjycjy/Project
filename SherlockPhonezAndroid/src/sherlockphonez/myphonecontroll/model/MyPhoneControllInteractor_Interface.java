package sherlockphonez.myphonecontroll.model;

import sherlockphonez.myphonecontroll.controller.OnMyPhoneControlFinishedListener;
import android.content.Context;

public interface MyPhoneControllInteractor_Interface {
	public void sendGCM(final Context context,final String phoneNum, final String msg, 
			final OnMyPhoneControlFinishedListener listener);
}
