package sherlockphonez.main.model;

import android.content.Context;
import sherlockphonez.main.controller.OnMainFinishedListener;

public interface MainView_InteractorInterface {
	public void main(final Context context , final String PhoneNum, final String deviceId, final String msg,
			 final OnMainFinishedListener listener);
}
